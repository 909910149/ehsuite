/*      */ package com.htsoft.oa.service.flow.impl;
/*      */ 
/*      */ import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Attribute;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.hibernate.Session;
import org.jbpm.api.Execution;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.HistoryService;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.RepositoryService;
import org.jbpm.api.history.HistoryProcessInstance;
import org.jbpm.api.model.Activity;
import org.jbpm.api.model.Transition;
import org.jbpm.api.task.Task;
import org.jbpm.pvm.internal.env.EnvironmentFactory;
import org.jbpm.pvm.internal.env.EnvironmentImpl;
import org.jbpm.pvm.internal.history.model.HistoryProcessInstanceImpl;
import org.jbpm.pvm.internal.model.ActivityImpl;
import org.jbpm.pvm.internal.model.ExecutionImpl;
import org.jbpm.pvm.internal.model.ProcessDefinitionImpl;
import org.jbpm.pvm.internal.model.TransitionImpl;
import org.jbpm.pvm.internal.repository.RepositoryCache;
import org.jbpm.pvm.internal.svc.TaskServiceImpl;
import org.jbpm.pvm.internal.task.TaskDefinitionImpl;
import org.jbpm.pvm.internal.task.TaskImpl;
import org.jbpm.pvm.internal.wire.descriptor.ObjectDescriptor;
import org.jbpm.pvm.internal.wire.descriptor.StringDescriptor;
import org.jbpm.pvm.internal.wire.operation.FieldOperation;
import org.jbpm.pvm.internal.wire.usercode.UserCodeReference;

import com.htsoft.core.jbpm.jpdl.Node;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.oa.action.flow.FlowRunInfo;
import com.htsoft.oa.dao.flow.JbpmDao;
import com.htsoft.oa.model.flow.FormDef;
import com.htsoft.oa.model.flow.FormDefMapping;
import com.htsoft.oa.model.flow.ProDefinition;
import com.htsoft.oa.model.flow.ProUserAssign;
import com.htsoft.oa.model.flow.ProcessForm;
import com.htsoft.oa.model.flow.ProcessRun;
import com.htsoft.oa.model.flow.TaskSign;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.service.flow.FormDefMappingService;
import com.htsoft.oa.service.flow.JbpmService;
import com.htsoft.oa.service.flow.ProDefinitionService;
import com.htsoft.oa.service.flow.ProUserAssignService;
import com.htsoft.oa.service.flow.ProcessFormService;
import com.htsoft.oa.service.flow.ProcessRunService;
import com.htsoft.oa.service.flow.TaskSignDataService;
import com.htsoft.oa.service.flow.TaskSignService;
import com.htsoft.oa.service.system.AppUserService;
import com.htsoft.oa.service.system.RelativeUserService;
import com.htsoft.oa.service.system.UserJobService;
/*      */ 
/*      */ public class JbpmServiceImpl
/*      */   implements JbpmService
/*      */ {
/*   88 */   private static final Log logger = LogFactory.getLog(JbpmServiceImpl.class);
/*      */ 
/*      */   @Resource
/*      */   private JbpmDao jbpmDao;
/*      */ 
/*      */   @Resource
/*      */   private ProcessEngine processEngine;
/*      */ 
/*      */   @Resource
/*      */   private RepositoryService repositoryService;
/*      */ 
/*      */   @Resource
/*      */   private ExecutionService executionService;
/*      */ 
/*      */   @Resource
/*      */   private ProDefinitionService proDefinitionService;
/*      */ 
/*      */   @Resource
/*      */   private org.jbpm.api.TaskService taskService;
/*      */ 
/*      */   @Resource
/*      */   private AppUserService appUserService;
/*      */ 
/*      */   @Resource
/*      */   private HistoryService historyService;
/*      */ 
/*      */   @Resource
/*      */   private ProUserAssignService proUserAssignService;
/*      */ 
/*      */   @Resource
/*      */   private RelativeUserService relativeUserService;
/*      */ 
/*      */   @Resource
/*      */   private ProcessRunService processRunService;
/*      */ 
/*      */   @Resource
/*      */   private ProcessFormService processFormService;
/*      */ 
/*      */   @Resource
/*      */   private UserJobService userJobService;
/*      */ 
/*      */   @Resource
/*      */   private TaskSignDataService taskSignDataService;
/*      */ 
/*      */   @Resource
/*      */   private TaskSignService taskSignService;
/*      */ 
/*      */   @Resource
/*      */   FormDefMappingService formDefMappingService;
/*      */ 
/*      */   @Resource(name="flowTaskService")
/*      */   private com.htsoft.oa.service.flow.TaskService flowTaskService;
/*      */ 
/*  133 */   public ProcessRun doStartProcess(FlowRunInfo startInfo) { ProcessRun processRun = this.processRunService.getInitFromFlowRunInfo(startInfo);
/*      */ 
/*  135 */     if (!startInfo.isUseTemplate()) {
/*  136 */       ProDefinition proDefinition = (ProDefinition)this.proDefinitionService.get(new Long(startInfo.getDefId()));
/*      */ 
/*  138 */       Long formDefId = FormDef.DEFAULT_FLOW_FORMID;
/*  139 */       FormDefMapping fdm = this.formDefMappingService.getByDeployId(proDefinition.getDeployId().toString());
/*      */ 
/*  141 */       if (fdm != null) {
/*  142 */         formDefId = fdm.getFormDefId();
/*      */       }
/*  144 */       processRun.setFormDefId(formDefId);
/*      */     }
/*      */ 
/*  148 */     if (startInfo.getEntityPK() != null)
/*      */     {
/*  150 */       processRun.setEntityId(startInfo.getEntityPK().toString());
/*  151 */       processRun.setEntityName(startInfo.getEntityName());
/*      */     }
/*  153 */     this.processRunService.save(processRun);
/*      */ 
/*  156 */     startInfo.getVariables().put("startUserId", ContextUtil.getCurrentUserId());
/*      */ 
/*  158 */     startInfo.getVariables().put("runId", processRun.getRunId());
/*      */ 
/*  160 */     startInfo.getVariables().put("entityId", startInfo.getEntityPK());
/*      */ 
/*  162 */     startInfo.getVariables().put("entityName", startInfo.getEntityName());
/*      */ 
/*  165 */     ProcessInstance pi = startProcess(processRun.getProDefinition().getDeployId(), startInfo.getDestName(), startInfo.getVariables());
/*      */ 
/*  167 */     processRun.setPiId(pi.getId());
/*  168 */     processRun.setRunStatus(ProcessRun.RUN_STATUS_RUNNING);
/*  169 */     this.processRunService.save(processRun);
/*      */ 
/*  171 */     saveInitProcessForm(processRun, startInfo);
/*      */ 
/*  175 */     String flowAssignId = (String)startInfo.getVariables().get("flowAssignId");
/*  176 */     assignTask(pi, flowAssignId);
/*  177 */     if (pi.getSubProcessInstance() != null) {
/*  178 */       logger.info("debug for subProcessinstance...........");
/*  179 */       assignTask((ProcessInstance)pi.getSubProcessInstance(), flowAssignId);
/*      */     }
/*      */ 
/*  182 */     return processRun;
/*      */   }
/*      */ 
/*      */   public ProcessRun doNextStep(FlowRunInfo nextInfo)
/*      */   {
/*      */     ProcessInstance pi;
///*      */     ProcessInstance pi;
/*      */     String nodeType;
/*  193 */     if (StringUtils.isNotEmpty(nextInfo.getTaskId())) {
/*  194 */       nodeType = "task";
/*  195 */       pi = getProcessInstanceByTaskId(nextInfo.getTaskId());
/*      */     } else {
/*  197 */       pi = getProcessInstance(nextInfo.getPiId());
/*  198 */       String xml = getDefinitionXmlByPiId(pi.getId());
/*  199 */       nodeType = getNodeType(xml, nextInfo.getActivityName());
/*      */     }
/*      */ 
/*  202 */     ProcessRun processRun = this.processRunService.getByPiId(pi.getId());
/*  203 */     AppUser curUser = ContextUtil.getCurrentUser();
/*      */ 
/*  206 */     if ("task".equals(nodeType)) {
/*  207 */       TaskImpl curTask = (TaskImpl)this.taskService.getTask(nextInfo.getTaskId());
/*      */ 
/*  209 */       String curTaskId = curTask.getId();
/*  210 */       String curActivityName = curTask.getActivityName();
/*      */ 
/*  213 */       String taskId = nextInfo.getTaskId();
/*  214 */       if (curTask.getSuperTask() != null) {
/*  215 */         taskId = curTask.getSuperTask().getId();
/*      */       }
/*      */ 
/*  218 */       ProcessForm curTaskForm = this.processFormService.getByTaskId(taskId);
/*      */ 
/*  220 */       if ((nextInfo.isBack()) && (curTaskForm != null)) {
/*  221 */         ProcessForm preTaskForm = null;
/*  222 */         if (curTaskForm.getPreFormId() == null)
/*  223 */           preTaskForm = (ProcessForm)this.processFormService.get(curTaskForm.getPreFormId());
/*      */         else {
/*  225 */           preTaskForm = (ProcessForm)this.processFormService.get(curTaskForm.getPreFormId());
/*      */         }
/*      */ 
/*  228 */         logger.debug("准备从任务" + curTaskForm.getActivityName() + "========回退");
/*      */ 
/*  230 */         jumpToPreTask(pi.getId(), preTaskForm.getCreatorId().toString(), preTaskForm.getActivityName());
/*      */ 
/*  232 */         nextInfo.setDestName(preTaskForm.getActivityName());
/*      */ 
/*  234 */         logger.debug("成功产生回退任务至＝＝＝＝＝＝＝＝＝＝" + preTaskForm.getActivityName());
/*      */       }
/*      */       else {
/*  237 */         completeTask(nextInfo.getTaskId(), nextInfo.getTransitionName(), nextInfo.getDestName(), nextInfo.getVariables());
/*      */       }
/*      */ 
/*  240 */       if (curTaskForm != null)
/*      */       {
/*  242 */         curTaskForm.setCreatorId(curUser.getUserId());
/*  243 */         curTaskForm.setCreatorName(curUser.getFullname());
/*  244 */         curTaskForm.setEndtime(new Date());
/*  245 */         curTaskForm.setTransTo(nextInfo.getTransitionName());
/*  246 */         if (nextInfo.isBack())
/*  247 */           curTaskForm.setStatus(ProcessForm.STATUS_BACK);
/*      */         else {
/*  249 */           curTaskForm.setStatus(ProcessForm.STATUS_PASS);
/*      */         }
/*  251 */         long durTimes = new Date().getTime() - curTaskForm.getCreatetime().getTime();
/*  252 */         curTaskForm.setDurTimes(durTimes);
/*      */ 
/*  254 */         curTaskForm.setComments(nextInfo.getComments());
/*  255 */         this.processFormService.save(curTaskForm);
/*      */       }
/*  257 */       if (!ProcessRun.RUN_STATUS_FINISHED.equals(processRun.getRunStatus()))
/*      */       {
/*  259 */         List<Task> tasks = getTasksByPiId(processRun.getPiId());
/*      */ 
/*  261 */         for (Task task : tasks) {
/*  262 */           logger.debug("cur task:===" + task.getActivityName());
/*  263 */           ProcessForm taskForm = new ProcessForm();
/*  264 */           taskForm.setActivityName(task.getActivityName());
/*      */ 
/*  266 */           taskForm.setCreatetime(new Date());
/*  267 */           taskForm.setTaskId(task.getId());
/*  268 */           if (curTaskForm != null) {
/*  269 */             taskForm.setPreFormId(curTaskForm.getFormId());
/*      */           }
/*  271 */           taskForm.setFromTask(curActivityName);
/*  272 */           taskForm.setFromTaskId(curTaskId);
/*  273 */           taskForm.setStatus(ProcessForm.STATUS_INIT);
/*  274 */           taskForm.setProcessRun(processRun);
/*  275 */           this.processFormService.save(taskForm);
/*      */         }
/*      */       }
/*      */     } else {
/*  279 */       signalProcess(pi.getId(), nextInfo.getTransitionName(), nextInfo.getVariables());
/*      */     }
/*      */ 
/*  282 */     return processRun;
/*      */   }
/*      */ 
/*      */   public Task jumpToPreTask(Task curTask, String preTaskName, String assignee)
/*      */   {
/*  293 */     EnvironmentImpl env = null;
/*      */     try {
/*  295 */       TaskImpl task = (TaskImpl)curTask;
/*  296 */       env = ((EnvironmentFactory)this.processEngine).openEnvironment();
/*  297 */       ProcessInstance pi = getProcessInstanceByTaskId(curTask.getId());
/*      */ 
/*  299 */       ProcessDefinitionImpl pd = (ProcessDefinitionImpl)getProcessDefinitionByTaskId(curTask.getId());
/*  300 */       TaskDefinitionImpl taskDef = pd.getTaskDefinition(preTaskName);
/*      */ 
/*  302 */       ExecutionImpl exeImpl = (ExecutionImpl)pi;
/*      */ 
/*  304 */       Activity preActivity = pd.findActivity(preTaskName);
/*  305 */       exeImpl.setActivity(preActivity);
/*  306 */       task.setActivityName(preTaskName);
/*  307 */       task.setName(preTaskName);
/*  308 */       task.setDescription(preTaskName);
/*  309 */       task.setExecution(exeImpl);
/*      */ 
/*  311 */       task.setAssignee(assignee);
/*  312 */       task.setCreateTime(new Date());
/*  313 */       task.setSignalling(true);
/*      */ 
/*  316 */       if (taskDef != null) {
/*  317 */         task.setTaskDefinition(taskDef);
/*      */       }
/*      */       else
/*      */       {
/*  321 */         TaskDefinitionImpl taskDefinition = new TaskDefinitionImpl();
/*  322 */         taskDefinition.setName(preTaskName);
/*  323 */         taskDefinition.setPriority(1);
/*  324 */         taskDefinition.setProcessDefinition(pd);
/*      */ 
/*  326 */         ActivityImpl startActivityImpl = pd.findActivity(preTaskName);
/*      */ 
/*  328 */         ActivityImpl startTaskImpl = pd.createActivity();
/*  329 */         startTaskImpl.setName(preTaskName);
/*  330 */         List outTrans = new ArrayList();
/*  331 */         outTrans.addAll(startActivityImpl.getOutgoingTransitions());
/*  332 */         startTaskImpl.setOutgoingTransitions(outTrans);
/*      */       }
/*      */ 
/*  335 */       this.taskService.saveTask(task);
/*  336 */       TaskImpl localTaskImpl1 = task;
/*      */       return localTaskImpl1;
/*      */     } finally {
/*  338 */       if (env != null) env.close(); 
/*      */     }
///*  339 */       throw localObject;
/*      */   }
/*      */ 
/*      */   public boolean isAllownBack(String taskId)
/*      */   {
/*  347 */     TaskImpl task = (TaskImpl)getTaskById(taskId);
/*  348 */     if (task.getSuperTask() != null) {
/*  349 */       taskId = task.getSuperTask().getId();
/*      */     }
/*      */ 
/*  352 */     ProcessForm taskForm = this.processFormService.getByTaskId(taskId);
/*      */ 
/*  356 */     return (taskForm != null) && (taskForm.getPreFormId() != null) && (taskForm.getFromTaskId() != null);
/*      */   }
/*      */ 
/*      */   public String getPreTask(String taskId)
/*      */   {
/*  368 */     TaskImpl task = (TaskImpl)getTaskById(taskId);
/*  369 */     if (task.getSuperTask() != null) {
/*  370 */       taskId = task.getSuperTask().getId();
/*      */     }
/*  372 */     ProcessForm taskForm = this.processFormService.getByTaskId(taskId);
/*  373 */     if ((taskForm != null) && (taskForm.getPreFormId() != null) && (taskForm.getFromTaskId() != null)) {
/*  374 */       ProcessForm preProcessForm = (ProcessForm)this.processFormService.get(taskForm.getPreFormId());
/*  375 */       return preProcessForm.getActivityName();
/*      */     }
/*  377 */     return null;
/*      */   }
/*      */ 
/*      */   public boolean isSignTask(String taskId)
/*      */   {
/*  388 */     TaskImpl task = (TaskImpl)getTaskById(taskId);
/*  389 */     if (task.getSuperTask() != null) {
/*  390 */       taskId = task.getSuperTask().getId();
/*  391 */       return true;
/*      */     }
/*  393 */     return false;
/*      */   }
/*      */ 
/*      */   private void saveInitProcessForm(ProcessRun processRun, FlowRunInfo startInfo)
/*      */   {
/*  403 */     String startNode = getStartNodeName(processRun.getProDefinition());
/*  404 */     ProcessForm startForm = this.processFormService.getInitProcessForm();
/*  405 */     startForm.setActivityName(startNode);
/*  406 */     startForm.setProcessRun(processRun);
/*  407 */     String transName = startInfo.getTransitionName();
/*      */ 
/*  409 */     if (transName == null) {
/*  410 */       List trans = getStartNodeTransByDeployId(processRun.getProDefinition().getDeployId());
/*  411 */       if (trans.size() > 0) {
/*  412 */         transName = (String)trans.get(0);
/*      */       }
/*      */     }
/*  415 */     startForm.setComments(startInfo.getComments());
/*  416 */     startForm.setStatus(ProcessForm.STATUS_PASS);
/*  417 */     startForm.setTransTo(transName);
/*      */ 
/*  419 */     this.processFormService.save(startForm);
/*      */ 
/*  423 */     List<Task> tasks = getTasksByPiId(processRun.getPiId());
/*      */ 
/*  425 */     for (Task task : tasks) {
/*  426 */       ProcessForm taskForm = new ProcessForm();
/*  427 */       taskForm.setActivityName(task.getActivityName());
/*  428 */       taskForm.setCreatetime(new Date());
/*  429 */       taskForm.setTaskId(task.getId());
/*  430 */       taskForm.setFromTask(startNode);
/*  431 */       taskForm.setFromTaskId(null);
/*  432 */       taskForm.setPreFormId(startForm.getFormId());
/*      */ 
/*  434 */       taskForm.setStatus(ProcessForm.STATUS_INIT);
/*  435 */       taskForm.setProcessRun(processRun);
/*  436 */       this.processFormService.save(taskForm);
/*      */     }
/*      */   }
/*      */ 
/*      */   public Task getTaskById(String taskId)
/*      */   {
/*  444 */     Task task = this.taskService.getTask(taskId);
/*      */ 
/*  446 */     return task;
/*      */   }
/*      */ 
/*      */   public void assignTask(String taskId, String userId)
/*      */   {
/*  455 */     this.taskService.assignTask(taskId, userId);
/*      */   }
/*      */ 
/*      */   public void doUnDeployProDefinition(Long defId)
/*      */   {
/*  465 */     this.processRunService.removeByDefId(defId);
/*      */ 
/*  467 */     ProDefinition pd = (ProDefinition)this.proDefinitionService.get(defId);
/*  468 */     if ((pd != null) && (pd.getDeployId() != null)) {
/*      */       try
/*      */       {
/*  471 */         this.repositoryService.deleteDeploymentCascade(pd.getDeployId());
/*      */       } catch (Exception ex) {
/*  473 */         logger.error(ex);
/*      */       }
/*      */     }
/*      */ 
/*  477 */     this.proDefinitionService.remove(pd);
/*      */   }
/*      */ 
/*      */   public ProDefinition saveOrUpdateDeploy(ProDefinition proDefinition)
/*      */   {
/*  487 */     if (logger.isDebugEnabled()) {
/*  488 */       logger.debug("deploy jbpm jpdl now===========");
/*      */     }
/*  490 */     String deployId = this.repositoryService.createDeployment().addResourceFromString("process.jpdl.xml", proDefinition.getDefXml()).deploy();
/*  491 */     ProcessDefinition processDefinition = this.repositoryService.createProcessDefinitionQuery().deploymentId(deployId).uniqueResult();
/*  492 */     proDefinition.setDeployId(deployId);
/*      */ 
/*  494 */     proDefinition.setProcessName(processDefinition.getName());
/*  495 */     proDefinition.setNewVersion(Integer.valueOf(processDefinition.getVersion()));
/*  496 */     this.proDefinitionService.save(proDefinition);
/*      */ 
/*  498 */     return proDefinition;
/*      */   }
/*      */ 
/*      */   public ProcessDefinition getProcessDefinitionByKey(String processKey)
/*      */   {
/*  507 */     List list = this.repositoryService.createProcessDefinitionQuery()
/*  508 */       .processDefinitionKey(processKey).orderDesc("versionProperty.longValue").list();
/*  509 */     if ((list != null) && (list.size() > 0)) {
/*  510 */       return (ProcessDefinition)list.get(0);
/*      */     }
/*  512 */     return null;
/*      */   }
/*      */ 
/*      */   public ProDefinition getProDefinitionByKey(String processKey)
/*      */   {
/*  520 */     ProcessDefinition processDefinition = getProcessDefinitionByKey(processKey);
/*  521 */     if (processDefinition != null) {
/*  522 */       ProDefinition proDef = this.proDefinitionService.getByDeployId(processDefinition.getDeploymentId());
/*  523 */       return proDef;
/*      */     }
/*  525 */     return null;
/*      */   }
/*      */ 
/*      */   public ProDefinition getProDefinitionByPiId(String piId)
/*      */   {
/*  533 */     ProcessInstance pi = getProcessInstance(piId);
/*  534 */     ProcessDefinition processDefinition = this.repositoryService.createProcessDefinitionQuery().processDefinitionId(pi.getProcessDefinitionId()).uniqueResult();
/*  535 */     return this.proDefinitionService.getByDeployId(processDefinition.getDeploymentId());
/*      */   }
/*      */ 
/*      */   public ProcessDefinition getProcessDefinitionByDefId(Long defId)
/*      */   {
/*  542 */     ProDefinition proDef = (ProDefinition)this.proDefinitionService.get(defId);
/*  543 */     if (proDef != null) {
/*  544 */       ProcessDefinition processDefinition = this.repositoryService.createProcessDefinitionQuery().deploymentId(proDef.getDeployId()).uniqueResult();
/*  545 */       return processDefinition;
/*      */     }
/*  547 */     return null;
/*      */   }
/*      */ 
/*      */   public String getDefinitionXmlByDefId(Long defId)
/*      */   {
/*  556 */     ProDefinition proDefinition = (ProDefinition)this.proDefinitionService.get(defId);
/*  557 */     return this.jbpmDao.getDefXmlByDeployId(proDefinition.getDeployId());
/*      */   }
/*      */ 
/*      */   public String getDefinitionXmlByDpId(String deployId)
/*      */   {
/*  564 */     return this.jbpmDao.getDefXmlByDeployId(deployId);
/*      */   }
/*      */ 
/*      */   public String getDefinitionXmlByExeId(String exeId)
/*      */   {
/*  572 */     String pdId = this.executionService.findExecutionById(exeId).getProcessDefinitionId();
/*  573 */     String deployId = this.repositoryService.createProcessDefinitionQuery().processDefinitionId(pdId).uniqueResult().getDeploymentId();
/*      */ 
/*  575 */     return this.jbpmDao.getDefXmlByDeployId(deployId);
/*      */   }
/*      */ 
/*      */   public String getDefinitionXmlByPiId(String piId)
/*      */   {
/*  582 */     ProcessInstance pi = this.executionService.createProcessInstanceQuery().processInstanceId(piId).uniqueResult();
/*  583 */     ProcessDefinition pd = this.repositoryService.createProcessDefinitionQuery().processDefinitionId(pi.getProcessDefinitionId()).uniqueResult();
/*  584 */     return this.jbpmDao.getDefXmlByDeployId(pd.getDeploymentId());
/*      */   }
/*      */ 
/*      */   public ProcessDefinition getProcessDefinitionByTaskId(String taskId)
/*      */   {
/*  595 */     TaskImpl task = (TaskImpl)this.taskService.getTask(taskId);
/*  596 */     ProcessInstance pi = null;
/*  597 */     if (task.getSuperTask() != null)
/*  598 */       pi = task.getSuperTask().getProcessInstance();
/*      */     else {
/*  600 */       pi = task.getProcessInstance();
/*      */     }
/*  602 */     ProcessDefinition pd = this.repositoryService.createProcessDefinitionQuery().processDefinitionId(pi.getProcessDefinitionId()).uniqueResult();
/*  603 */     return pd;
/*      */   }
/*      */ 
/*      */   public ProcessInstance getProcessInstance(String piId)
/*      */   {
/*  614 */     ProcessInstance pi = this.executionService.createProcessInstanceQuery().processInstanceId(piId).uniqueResult();
/*  615 */     return pi;
/*      */   }
/*      */ 
/*      */   public List<Node> getTaskNodesByDefId(Long defId)
/*      */   {
/*  624 */     ProDefinition proDefinition = (ProDefinition)this.proDefinitionService.get(defId);
/*  625 */     String defXml = this.jbpmDao.getDefXmlByDeployId(proDefinition.getDeployId());
/*  626 */     return getTaskNodesFromXml(defXml, false, false);
/*      */   }
/*      */ 
/*      */   public List<Node> getTaskNodesByDefId(Long defId, boolean start, boolean end)
/*      */   {
/*  633 */     ProDefinition proDefinition = (ProDefinition)this.proDefinitionService.get(defId);
/*  634 */     String defXml = this.jbpmDao.getDefXmlByDeployId(proDefinition.getDeployId());
/*  635 */     return getTaskNodesFromXml(defXml, start, end);
/*      */   }
/*      */ 
/*      */   public List<Node> getJumpNodesByDeployId(String deployId)
/*      */   {
/*  644 */     String defXml = this.jbpmDao.getDefXmlByDeployId(deployId);
/*  645 */     return getTaskNodesFromXml(defXml, false, true);
/*      */   }
/*      */ 
/*      */   public List<Node> getFormNodesByDeployId(Long deployId)
/*      */   {
/*  654 */     String defXml = this.jbpmDao.getDefXmlByDeployId(deployId.toString());
/*  655 */     return getTaskNodesFromXml(defXml, true, false);
/*      */   }
/*      */ 
/*      */   public String getStartNodeName(ProDefinition proDefinition)
/*      */   {
/*  663 */     return getStartNodeNameByDeployId(proDefinition.getDeployId());
/*      */   }
/*      */ 
/*      */   private String getStartNodeNameByDeployId(String deployId) {
/*      */     try {
/*  668 */       String defXml = this.jbpmDao.getDefXmlByDeployId(deployId);
/*  669 */       Element root = DocumentHelper.parseText(defXml).getRootElement();
				 List<Element> elements = root.elements();
/*  670 */       for (Element elem : elements) {
/*  671 */         String tagName = elem.getName();
/*  672 */         if ("start".equals(tagName)) {
/*  673 */           Attribute nameAttr = elem.attribute("name");
/*  674 */           if (nameAttr == null) break;
/*  675 */           return nameAttr.getValue();
/*      */         }
/*      */       }
/*      */     }
/*      */     catch (Exception ex)
/*      */     {
/*  681 */       logger.error(ex.getMessage());
/*      */     }
/*  683 */     return null;
/*      */   }
/*      */ 
/*      */   public List<String> getStartNodeTransByDeployId(String deployId)
/*      */   {
/*  692 */     String defXml = this.jbpmDao.getDefXmlByDeployId(deployId);
/*  693 */     return getStartNodeTransByXml(defXml);
/*      */   }
/*      */ 
/*      */   public List<String> getStartNodeTransByXml(String defXml)
/*      */   {
/*  702 */     List trans = new ArrayList();
/*      */     try
/*      */     {
/*  705 */       Element root = DocumentHelper.parseText(defXml).getRootElement();
				 List<Element> elements	= root.elements();
/*  706 */       for (Element elem : elements) {
/*  707 */         String tagName = elem.getName();
/*  708 */         if ("start".equals(tagName)) {
/*  709 */           Iterator tranIt = elem.elementIterator();
/*  710 */           while (tranIt.hasNext()) {
/*  711 */             trans.add(((Element)tranIt.next()).attributeValue("name"));
/*      */           }
/*  713 */           break;
/*      */         }
/*      */       }
/*      */     } catch (Exception ex) {
/*  717 */       logger.error(ex.getMessage());
/*      */     }
/*  719 */     return trans;
/*      */   }
/*      */ 
/*      */   private List<Node> getTaskNodesFromXml(String xml, boolean includeStart, boolean includeEnd)
/*      */   {
/*  730 */     List nodes = new ArrayList();
/*      */     try {
/*  732 */       Element root = DocumentHelper.parseText(xml).getRootElement();
				 List<Element> elements = root.elements();
/*  733 */       for (Element elem : elements) {
/*  734 */         String type = elem.getQName().getName();
/*  735 */         if ("task".equalsIgnoreCase(type)) {
/*  736 */           if (elem.attribute("name") != null) {
/*  737 */             Node node = new Node(elem.attribute("name").getValue(), "任务节点");
/*  738 */             nodes.add(node);
/*      */           }
/*  740 */         } else if ((includeStart) && ("start".equalsIgnoreCase(type))) {
/*  741 */           if (elem.attribute("name") != null) {
/*  742 */             Node node = new Node(elem.attribute("name").getValue(), "开始节点");
/*  743 */             nodes.add(node);
/*      */           }
/*  745 */         } else if ((includeEnd) && (type.startsWith("end"))) {
/*  746 */           Node node = new Node(elem.attribute("name").getValue(), "结束节点");
/*  747 */           nodes.add(node);
/*      */         }
/*      */       }
/*      */     } catch (Exception ex) {
/*  751 */       logger.error(ex.getMessage());
/*      */     }
/*  753 */     return nodes;
/*      */   }
/*      */ 
/*      */   public ProcessInstance startProcess(String deployId, String destTaskName, Map variables)
/*      */   {
/*  762 */     ProcessDefinition pd = this.repositoryService.createProcessDefinitionQuery().deploymentId(deployId).uniqueResult();
/*      */ 
/*  765 */     ProcessInstance pi = this.executionService.startProcessInstanceById(pd.getId(), variables);
/*      */ 
/*  768 */     if (destTaskName != null) {
/*  769 */       List tasks = getTasksByPiId(pi.getId());
/*      */ 
/*  771 */       for (int i = 0; i < tasks.size(); i++) {
/*  772 */         Task task = (Task)tasks.get(i);
/*  773 */         if ((i == 0) && (destTaskName.equals(task.getName()))) {
/*      */           break;
/*      */         }
/*  776 */         jumpTaskToAnother(task, destTaskName, variables);
/*      */       }
/*      */     }
/*      */ 
/*  780 */     return pi;
/*      */   }
/*      */ 
/*      */   private Integer jumpTaskToAnother(Task task, String destTaskName, Map variables)
/*      */   {
/*  794 */     Integer formalJump = Integer.valueOf(1);
/*      */ 
/*  796 */     ProcessDefinition pd = getProcessDefinitionByTaskId(task.getId());
/*  797 */     String signalName = null;
/*  798 */     List<Transition> trans = getTransitionsByTaskId(task.getId(), false);
/*      */ 
/*  800 */     boolean isExistTran = false;
/*      */ 
/*  802 */     for (Transition tran : trans) {
/*  803 */       if (destTaskName.equals(tran.getDestination().getName())) {
/*  804 */         signalName = tran.getName();
/*  805 */         isExistTran = true;
/*  806 */         break;
/*      */       }
/*      */     }
/*      */ 
/*  810 */     if (!isExistTran) {
/*  811 */       addOutTransition((ProcessDefinitionImpl)pd, task.getActivityName(), destTaskName);
/*  812 */       signalName = "to" + destTaskName;
/*      */     }
/*  814 */     this.taskService.setVariables(task.getId(), variables);
/*  815 */     this.taskService.completeTask(task.getId(), signalName);
/*      */ 
/*  817 */     if (!isExistTran) {
/*  818 */       removeOutTransition((ProcessDefinitionImpl)pd, task.getActivityName(), destTaskName);
/*  819 */       formalJump = Integer.valueOf(0);
/*      */     }
/*  821 */     return formalJump;
/*      */   }
/*      */ 
/*      */   public ProcessInstance getProcessInstanceByExeId(String executionId)
/*      */   {
/*  830 */     Execution execution = this.executionService.findExecutionById(executionId);
/*  831 */     return (ProcessInstance)execution.getProcessInstance();
/*      */   }
/*      */ 
/*      */   public ProcessInstance getProcessInstanceByTaskId(String taskId) {
/*  835 */     TaskImpl taskImpl = (TaskImpl)this.taskService.getTask(taskId.toString());
/*  836 */     if (taskImpl.getSuperTask() != null) {
/*  837 */       taskImpl = taskImpl.getSuperTask();
/*      */     }
/*  839 */     return taskImpl.getProcessInstance();
/*      */   }
/*      */ 
/*      */   private Map<String, String> getUserIdsMap(String flowAssignId)
/*      */   {
/*  847 */     HashMap taskUserIdsMap = new HashMap();
/*      */ 
/*  849 */     if (StringUtils.isNotEmpty(flowAssignId)) {
/*  850 */       if (logger.isDebugEnabled()) {
/*  851 */         logger.debug("===>assignId:" + flowAssignId);
/*      */       }
/*  853 */       String[] assignIds = flowAssignId.split("[|]");
/*  854 */       if ((assignIds != null) && (assignIds.length == 2)) {
/*  855 */         String[] destTasks = assignIds[0].split("[:]");
/*  856 */         String[] destUserIds = assignIds[1].split("[:]");
/*      */ 
/*  858 */         if ((destTasks != null) && (destUserIds != null)) {
/*  859 */           for (int i = 0; i < destTasks.length; i++) {
/*  860 */             taskUserIdsMap.put(destTasks[i], destUserIds[i]);
/*      */           }
/*      */         }
/*      */       }
/*  864 */       else if (assignIds.length == 1) {
/*  865 */         taskUserIdsMap.put("CommonTask", flowAssignId);
/*      */       }
/*      */     }
/*  868 */     return taskUserIdsMap;
/*      */   }
/*      */ 
/*      */   public ProcessRun initSuProcessRun(String parentPiId, String subPiId)
/*      */   {
/*  879 */     ProcessRun parentProcessRun = this.processRunService.getByPiId(parentPiId);
/*  880 */     ProcessRun subProcessRun = new ProcessRun();
/*      */ 
/*  882 */     subProcessRun.setAppUser(parentProcessRun.getAppUser());
/*  883 */     subProcessRun.setBusDesc(parentProcessRun.getBusDesc());
/*  884 */     subProcessRun.setSubject(parentProcessRun.getSubject());
/*  885 */     subProcessRun.setCreatetime(new Date());
/*  886 */     subProcessRun.setCreator(parentProcessRun.getCreator());
/*  887 */     subProcessRun.setRunStatus(ProcessRun.RUN_STATUS_INIT);
/*  888 */     subProcessRun.setPiId(subPiId);
/*  889 */     subProcessRun.setEntityId(parentProcessRun.getEntityId());
/*  890 */     subProcessRun.setEntityName(parentProcessRun.getEntityName());
/*  891 */     subProcessRun.setFormDefId(parentProcessRun.getFormDefId());
/*  892 */     ProDefinition proDefinition = getProDefinitionByPiId(subPiId);
/*  893 */     subProcessRun.setProDefinition(proDefinition);
/*      */ 
/*  895 */     this.processRunService.save(subProcessRun);
/*      */ 
/*  897 */     return subProcessRun;
/*      */   }
/*      */ 
/*      */   public void assignTask(ProcessInstance pi, String flowAssignId)
/*      */   {
/*  909 */     ProcessDefinition pd = this.repositoryService.createProcessDefinitionQuery().processDefinitionId(pi.getProcessDefinitionId()).uniqueResult();
/*      */ 
/*  912 */     List<Task> taskList = getTasksByPiId(pi.getId());
/*      */ 
/*  915 */     Map taskUserIdsMap = getUserIdsMap(flowAssignId);
/*      */ 
/*  917 */     ExecutionImpl piExeImpl = (ExecutionImpl)pi;
/*  918 */     String piId = null;
/*  919 */     if (piExeImpl.getSuperProcessExecution() != null) {
/*  920 */       piId = piExeImpl.getSuperProcessExecution().getId();
/*      */ 
/*  922 */       ProcessRun subProcessRun = this.processRunService.getByPiId(pi.getId());
/*  923 */       if (subProcessRun == null) {
/*  924 */         initSuProcessRun(piId, pi.getId());
/*      */       }
/*      */ 
/*  927 */       taskUserIdsMap = null;
/*      */     } else {
/*  929 */       piId = pi.getId();
/*      */     }
/*      */ 
/*  932 */     Long flowStartUserId = (Long)this.executionService.getVariable(piId, "startUserId");
/*      */ 
/*  934 */     if (flowStartUserId == null) {
/*  935 */       ProcessRun processRun = this.processRunService.getByPiId(piId);
/*  936 */       flowStartUserId = processRun.getUserId();
/*      */     }
/*      */ 
/*  941 */     for (Task task : taskList) {
/*  942 */       TaskImpl taskImpl = (TaskImpl)task;
/*      */ 
/*  944 */       if ((task.getAssignee() != null) || (taskImpl.getAllParticipants().size() > 0)) {
/*      */         continue;
/*      */       }
/*  947 */       if (taskImpl.getSubTasks().size() > 0) {
/*      */         continue;
/*      */       }
/*  950 */       boolean isAssign = false;
/*      */ 
/*  952 */       Long exeUserId = null;
/*      */ 
/*  954 */       HashSet candidateUserIds = new HashSet();
/*      */ 
/*  956 */       HashSet candidateGroupIds = new HashSet();
/*      */ 
/*  959 */       ProUserAssign assignSetting = this.proUserAssignService.getByDeployIdActivityName(pd.getDeploymentId(), task.getActivityName());
/*      */       String[] arrayOfString2;
/*      */       int i;
/*      */       int str1;
/*  961 */       if ((taskUserIdsMap != null) && (taskUserIdsMap.size() > 0)) {
/*  962 */         String userIds = "";
/*      */ 
/*  964 */         if (taskUserIdsMap.containsKey("CommonTask"))
/*  965 */           userIds = (String)taskUserIdsMap.get("CommonTask");
/*      */         else {
/*  967 */           userIds = (String)taskUserIdsMap.get(taskImpl.getName());
/*      */         }
/*  969 */         String[] assignIds = userIds.split("[,]");
/*  970 */         if ((assignIds != null) && (assignIds.length > 1)) {
/*  971 */           i = (arrayOfString2 = assignIds).length; 
					 for (str1 = 0; str1 < i; str1++) { String aId = arrayOfString2[str1];
/*  972 */             candidateUserIds.add(new Long(aId)); }
/*      */         }
/*      */         else {
/*  975 */           exeUserId = new Long(userIds);
/*      */         }
/*  977 */       } else if (assignSetting != null)
/*      */       {
/*      */         int userId;
/*  979 */         if (StringUtils.isNotEmpty(assignSetting.getUserId()))
/*      */         {
/*  981 */           if ("__start".equals(assignSetting.getUserId())) {
/*  982 */             exeUserId = flowStartUserId;
/*      */           } else {
/*  984 */             String[] exeUserIds = assignSetting.getUserId().split("[,]");
/*  985 */             if ((exeUserIds != null) && (exeUserIds.length == 1)) {
/*  986 */               exeUserId = new Long(exeUserIds[0]);
/*      */             } else {
/*  988 */               String[] userIds = assignSetting.getUserId().split("[,]");
/*  989 */               i = (arrayOfString2 = userIds).length; 
					      for (str1 = 0; str1 < i; str1++) {
					       String userId1 = arrayOfString2[str1];
/*  990 */                 candidateUserIds.add(new Long(userId1));
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*  995 */         if (StringUtils.isNotEmpty(assignSetting.getRoleId()))
/*      */         {
/*  997 */           candidateGroupIds.add(new Long(assignSetting.getRoleId()));
/*      */         }
/*      */         String[] arrayOfString1;
/*  999 */         if (StringUtils.isNotEmpty(assignSetting.getJobId()))
/*      */         {
/* 1001 */           String[] jobIds = assignSetting.getJobId().split("[,]");
/* 1002 */           str1 = (arrayOfString1 = jobIds).length; 
					 for (userId = 0; userId < str1; userId++) { 
						 String jbId = arrayOfString1[userId];
/* 1003 */             List userIds = this.userJobService.getUserIdsByJobId(new Long(jbId));
/* 1004 */             candidateUserIds.addAll(userIds);
/*      */           }
/*      */         }
/* 1007 */         if (StringUtils.isNotEmpty(assignSetting.getReJobId()))
/*      */         {
/* 1009 */           String[] reJobIds = assignSetting.getReJobId().split("[,]");
/* 1010 */           int str2 = (arrayOfString1 = reJobIds).length; 
					 for (userId = 0; userId < str2; userId++) { 
					   String reJbId = arrayOfString1[userId];
/* 1011 */             List userIds = this.relativeUserService.getReJobUserIds(flowStartUserId, new Long(reJbId));
/* 1012 */             candidateUserIds.addAll(userIds);
/*      */           }
/*      */ 
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/* 1020 */       if ((assignSetting != null) && (ProUserAssign.IS_SIGNED_TASK.equals(assignSetting.getIsSigned())) && 
/* 1021 */         (candidateUserIds.size() > 1)) {
/* 1022 */         Long[] uIds = (Long[])candidateUserIds.toArray(new Long[0]);
/* 1023 */         newSubTask(task.getId(), uIds);
/*      */       }
/*      */       else
/*      */       {
/* 1028 */         if (exeUserId != null) {
/* 1029 */           this.taskService.assignTask(task.getId(), exeUserId.toString());
/* 1030 */           isAssign = true;
/*      */         }
/* 1032 */         if (candidateUserIds.size() == 1) {
/* 1033 */           this.taskService.assignTask(task.getId(), ((Long)candidateUserIds.iterator().next()).toString());
/* 1034 */           isAssign = true;
/* 1035 */         } else if (candidateUserIds.size() > 1) {
/* 1036 */           isAssign = true;
/* 1037 */           Iterator its = candidateUserIds.iterator();
/* 1038 */           while (its.hasNext()) {
/* 1039 */             Long userId = (Long)its.next();
/* 1040 */             this.taskService.addTaskParticipatingUser(task.getId(), userId.toString(), "candidate");
/*      */           }
/*      */         }
/* 1043 */         if (candidateGroupIds.size() > 0) {
/* 1044 */           isAssign = true;
/* 1045 */           Iterator its = candidateGroupIds.iterator();
/* 1046 */           while (its.hasNext()) {
/* 1047 */             Long roleId = (Long)its.next();
/* 1048 */             this.taskService.addTaskParticipatingGroup(task.getId(), roleId.toString(), "candidate");
/*      */           }
/*      */         }
/*      */ 
/* 1052 */         if (!isAssign) {
/* 1053 */           if (logger.isDebugEnabled()) {
/* 1054 */             logger.debug("------->Task " + task.getActivityName() + " is assign to the flow start there:");
/*      */           }
/* 1056 */           this.taskService.assignTask(task.getId(), flowStartUserId.toString());
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public void assignUnHandlerTask(String piId, String userId)
/*      */   {
/* 1070 */     List<Task> tasks = this.taskService.createTaskQuery()
/* 1071 */       .processInstanceId(piId).unassigned().list();
/*      */ 
/* 1073 */     for (Task task : tasks) {
/* 1074 */       TaskImpl taskImpl = (TaskImpl)task;
/*      */ 
/* 1076 */       if ((taskImpl.getAssignee() == null) || (taskImpl.getAllParticipants().size() == 0))
/* 1077 */         this.taskService.assignTask(task.getId(), userId);
/*      */     }
/*      */   }
/*      */ 
/*      */   public List<Transition> getTransitionsForSignalProcess(String piId)
/*      */   {
/* 1088 */     ProcessInstance pi = this.executionService.findProcessInstanceById(piId);
/* 1089 */     EnvironmentFactory environmentFactory = (EnvironmentFactory)this.processEngine;
/* 1090 */     EnvironmentImpl env = environmentFactory.openEnvironment();
/*      */     try {
/* 1092 */       ExecutionImpl executionImpl = (ExecutionImpl)pi;
/* 1093 */       ActivityImpl activity = executionImpl.getActivity();
/* 1094 */       List outTrans = activity.getOutgoingTransitions();
/* 1095 */       List localList1 = outTrans;
/*      */       return localList1;
/*      */     } finally {
/* 1097 */       env.close();
/* 1098 */     }
//				throw localObject;
/*      */   }
/*      */ 
/*      */   public List<Transition> getStartOutTransByDeployId(String deployId)
/*      */   {
/* 1108 */     ProcessDefinitionImpl pd = (ProcessDefinitionImpl)this.repositoryService.createProcessDefinitionQuery().deploymentId(deployId).uniqueResult();
/*      */ 
/* 1110 */     String startName = getStartNodeNameByDeployId(deployId);
/* 1111 */     EnvironmentFactory environmentFactory = (EnvironmentFactory)this.processEngine;
/* 1112 */     EnvironmentImpl env = environmentFactory.openEnvironment();
/*      */     try
/*      */     {
/*      */       List localList1;
/* 1114 */       if (startName != null) {
/* 1115 */         ActivityImpl activityFind = pd.findActivity(startName);
/* 1116 */         if (activityFind != null) {
/* 1117 */           List outTrans = activityFind.getOutgoingTransitions();
/* 1118 */           localList1 = outTrans;
/*      */           return localList1;
/*      */         }
/*      */       } else {
/* 1121 */         List activitys = pd.getActivities();
/* 1122 */         for (int i = 0; i < activitys.size(); i++) {
/* 1123 */           Activity act = (Activity)activitys.get(i);
/* 1124 */           if ("start".equals(act.getType())) {
/* 1125 */             List outTrans = act.getOutgoingTransitions();
/* 1126 */             localList1 = outTrans;
/*      */             return localList1;
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     finally {
/* 1132 */       env.close(); } env.close();
/*      */ 
/* 1134 */     return new ArrayList();
/*      */   }
/*      */ 
/*      */   public List<Transition> getTransitionsByTaskId(String taskId) {
/* 1138 */     return getTransitionsByTaskId(taskId, false);
/*      */   }
/*      */ 
/*      */   public List<Transition> getTransitionsByTaskId(String taskId, boolean isInTransition)
/*      */   {
/* 1149 */     TaskImpl task = (TaskImpl)this.taskService.getTask(taskId);
/* 1150 */     if (task.getSuperTask() != null) {
/* 1151 */       task = task.getSuperTask();
/*      */     }
/* 1153 */     EnvironmentFactory environmentFactory = (EnvironmentFactory)this.processEngine;
/* 1154 */     EnvironmentImpl env = environmentFactory.openEnvironment();
/*      */     try {
/* 1156 */       ProcessDefinitionImpl pd = task.getProcessInstance().getProcessDefinition();
/* 1157 */       ActivityImpl activityFind = pd.findActivity(task.getActivityName());
/* 1158 */       if (activityFind != null) {
/* 1159 */         List outTrans = null;
/* 1160 */         if (isInTransition)
/* 1161 */           outTrans = activityFind.getIncomingTransitions();
/*      */         else {
/* 1163 */           outTrans = activityFind.getOutgoingTransitions();
/*      */         }
/* 1165 */         return outTrans;
/*      */       }
/*      */     } finally {
/* 1168 */       env.close();
/*      */     }
/* 1170 */     return new ArrayList();
/*      */   }
/*      */ 
/*      */   public List<Transition> getInTransForTask(String taskId) {
/* 1174 */     return getTransitionsByTaskId(taskId, true);
/*      */   }
/*      */ 
/*      */   public void addOutTransition(ProcessDefinitionImpl pd, String sourceName, String destName)
/*      */   {
/* 1185 */     EnvironmentFactory environmentFactory = (EnvironmentFactory)this.processEngine;
/* 1186 */     EnvironmentImpl env = null;
/*      */     try {
/* 1188 */       env = environmentFactory.openEnvironment();
/*      */ 
/* 1191 */       ActivityImpl sourceActivity = pd.findActivity(sourceName);
/*      */ 
/* 1193 */       ActivityImpl destActivity = pd.findActivity(destName);
/*      */ 
/* 1196 */       TransitionImpl transition = sourceActivity.createOutgoingTransition();
/* 1197 */       transition.setName("to" + destName);
/* 1198 */       transition.setDestination(destActivity);
/*      */ 
/* 1203 */       sourceActivity.addOutgoingTransition(transition);
/*      */     }
/*      */     catch (Exception ex) {
/* 1206 */       logger.error(ex.getMessage());
/*      */     } finally {
/* 1208 */       if (env != null) env.close();
/*      */     }
/*      */   }
/*      */ 
/*      */   public void removeOutTransition(ProcessDefinitionImpl pd, String sourceName, String destName)
/*      */   {
/* 1220 */     EnvironmentFactory environmentFactory = (EnvironmentFactory)this.processEngine;
/* 1221 */     EnvironmentImpl env = null;
/*      */     try {
/* 1223 */       env = environmentFactory.openEnvironment();
/*      */ 
/* 1225 */       ActivityImpl sourceActivity = pd.findActivity(sourceName);
/*      */ 
/* 1228 */       List trans = sourceActivity.getOutgoingTransitions();
/* 1229 */       for (int i = 0; i < trans.size(); i++) {
/* 1230 */         Transition tran = (Transition)trans.get(i);
/* 1231 */         if (destName.equals(tran.getDestination().getName())) {
/* 1232 */           trans.remove(tran);
/* 1233 */           break;
/*      */         }
/*      */       }
/*      */     } catch (Exception ex) {
/* 1237 */       logger.error(ex.getMessage());
/*      */     } finally {
/* 1239 */       if (env != null) env.close();
/*      */     }
/*      */   }
/*      */ 
/*      */   public List<Transition> getFreeTransitionsByTaskId(String taskId)
/*      */   {
/* 1249 */     TaskImpl task = (TaskImpl)this.taskService.getTask(taskId);
/*      */ 
/* 1251 */     List outTrans = new ArrayList();
/*      */ 
/* 1253 */     if (task.getSuperTask() != null) {
/* 1254 */       task = task.getSuperTask();
/*      */     }
/* 1256 */     EnvironmentFactory environmentFactory = (EnvironmentFactory)this.processEngine;
/* 1257 */     EnvironmentImpl env = null;
/*      */     try {
/* 1259 */       env = environmentFactory.openEnvironment();
/* 1260 */       ProcessDefinitionImpl pd = task.getProcessInstance().getProcessDefinition();
/* 1261 */       ActivityImpl curActivity = pd.findActivity(task.getActivityName());
/* 1262 */       String defXml = this.jbpmDao.getDefXmlByDeployId(pd.getDeploymentId());
/*      */ 
/* 1264 */       List<Node> allTaskNodes = getValidNodesFromXml(defXml);
/*      */ 
/* 1266 */       for (Node taskNode : allTaskNodes) {
/* 1267 */         if (taskNode.getName().equals(task.getActivityName()))
/*      */           continue;
/* 1269 */         TransitionImpl transition = curActivity.createOutgoingTransition();
/*      */ 
/* 1271 */         transition.setName("to" + taskNode.getName());
/* 1272 */         transition.setDestination(pd.findActivity(taskNode.getName()));
/*      */ 
/* 1274 */         curActivity.getOutgoingTransitions().remove(transition);
/*      */ 
/* 1276 */         outTrans.add(transition);
/*      */       }
/*      */     }
/*      */     catch (Exception ex) {
/* 1280 */       logger.error(ex.getMessage());
/*      */     } finally {
/* 1282 */       if (env != null) env.close();
/*      */     }
/*      */ 
/* 1285 */     return outTrans;
/*      */   }
/*      */ 
/*      */   public String getProcessDefintionXMLByPiId(String piId)
/*      */   {
/* 1293 */     ProcessRun processRun = this.processRunService.getByPiId(piId);
/* 1294 */     String defXml = this.jbpmDao.getDefXmlByDeployId(processRun.getProDefinition().getDeployId());
/* 1295 */     return defXml;
/*      */   }
/*      */ 
/*      */   public List<Task> getTasksByPiId(String piId)
/*      */   {
/* 1304 */     List taskList = this.taskService.createTaskQuery().processInstanceId(piId).list();
/* 1305 */     return taskList;
/*      */   }
/*      */ 
/*      */   public String getNodeType(String xml, String nodeName)
/*      */   {
/* 1315 */     String type = "";
/*      */     try {
/* 1317 */       Element root = DocumentHelper.parseText(xml).getRootElement();
				 List<Element> elements = root.elements();
/* 1318 */       for (Element elem : elements)
/* 1319 */         if (elem.attribute("name") != null) {
/* 1320 */           String value = elem.attributeValue("name");
/* 1321 */           if (value.equals(nodeName)) {
/* 1322 */             type = elem.getQName().getName();
/* 1323 */             return type;
/*      */           }
/*      */         }
/*      */     }
/*      */     catch (Exception ex) {
/* 1328 */       logger.info(ex.getMessage());
/*      */     }
/* 1330 */     return type;
/*      */   }
/*      */ 
/*      */   protected void clearSession() {
/* 1334 */     EnvironmentFactory environmentFactory = (EnvironmentFactory)this.processEngine;
/* 1335 */     EnvironmentImpl env = environmentFactory.openEnvironment();
/*      */     try {
/* 1337 */       Session session = (Session)env.get(Session.class);
/* 1338 */       session.clear();
/*      */     } finally {
/* 1340 */       env.close();
/*      */     }
/*      */   }
/*      */ 
/*      */   protected void clear() {
/* 1345 */     EnvironmentFactory environmentFactory = (EnvironmentFactory)this.processEngine;
/* 1346 */     EnvironmentImpl env = environmentFactory.openEnvironment();
/*      */     try {
/* 1348 */       Session session = (Session)env.get(Session.class);
/* 1349 */       session.clear();
/*      */     } finally {
/* 1351 */       env.close();
/*      */     }
/*      */   }
/*      */ 
/*      */   protected void flush() {
/* 1356 */     EnvironmentFactory environmentFactory = (EnvironmentFactory)this.processEngine;
/* 1357 */     EnvironmentImpl env = environmentFactory.openEnvironment();
/*      */     try {
/* 1359 */       Session session = (Session)env.get(Session.class);
/* 1360 */       session.flush();
/*      */     } finally {
/* 1362 */       env.close();
/*      */     }
/*      */   }
/*      */ 
/*      */   protected void evict(Object obj) {
/* 1367 */     EnvironmentFactory environmentFactory = (EnvironmentFactory)this.processEngine;
/* 1368 */     EnvironmentImpl env = environmentFactory.openEnvironment();
/*      */     try {
/* 1370 */       Session session = (Session)env.get(Session.class);
/* 1371 */       session.evict(obj);
/*      */     } finally {
/* 1373 */       env.close();
/*      */     }
/*      */   }
/*      */ 
/*      */   public void completeTask(String taskId, String signalName, String destName, Map variables)
/*      */   {
/* 1385 */     TaskImpl taskImpl = (TaskImpl)this.taskService.getTask(taskId);
/* 1386 */     if (destName == null) {
/* 1387 */       List<Transition> trans = getTransitionsByTaskId(taskId);
/* 1388 */       for (Transition tran : trans) {
/* 1389 */         if (tran.getName().equals(signalName)) {
/* 1390 */           destName = tran.getDestination().getName();
/* 1391 */           break;
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/* 1396 */     TaskImpl parentTask = taskImpl.getSuperTask();
/* 1397 */     ProcessInstance pi = null;
/*      */ 
/* 1399 */     if (parentTask != null) {
/* 1400 */       pi = parentTask.getProcessInstance();
/* 1401 */       if (logger.isDebugEnabled()) {
/* 1402 */         logger.debug("Super task is not null, task name is:" + parentTask.getActivityName());
/*      */       }
/* 1404 */       completeSignSubTask(parentTask, taskImpl, signalName, variables);
/*      */     } else {
/* 1406 */       pi = taskImpl.getProcessInstance();
/* 1407 */       completeTaskAndJump(taskId, destName, variables);
/*      */     }
/*      */ 
/* 1411 */     boolean isEndProcess = isProcessInstanceEnd(pi.getId());
/* 1412 */     if (isEndProcess) {
/* 1413 */       ProcessRun processRun = this.processRunService.getByPiId(pi.getId());
/* 1414 */       if (processRun != null) {
/* 1415 */         processRun.setPiId(null);
/* 1416 */         processRun.setRunStatus(ProcessRun.RUN_STATUS_FINISHED);
/* 1417 */         this.processRunService.save(processRun);
/*      */       }
/* 1419 */       return;
/*      */     }
/*      */ 
/* 1422 */     String flowAssignId = (String)variables.get("flowAssignId");
/*      */ 
/* 1424 */     assignTask(pi, flowAssignId);
/*      */ 
/* 1426 */     if (pi.getSubProcessInstance() != null) {
/* 1427 */       logger.info("debug for subProcessinstance...........");
/* 1428 */       assignTask((ProcessInstance)pi.getSubProcessInstance(), flowAssignId);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void completeSignSubTask(TaskImpl parentTask, TaskImpl subTask, String signalName, Map variables)
/*      */   {
/* 1442 */     int subTasksSize = parentTask.getSubTasks().size();
/*      */ 
/* 1445 */     ProcessInstance pi = parentTask.getProcessInstance();
/* 1446 */     ProcessDefinition pd = this.repositoryService.createProcessDefinitionQuery().processDefinitionId(pi.getProcessDefinitionId()).uniqueResult();
/*      */ 
/* 1448 */     ProUserAssign assignSetting = this.proUserAssignService.getByDeployIdActivityName(pd.getDeploymentId(), parentTask.getActivityName());
/*      */ 
/* 1450 */     evict(subTask);
/* 1451 */     evict(parentTask);
/*      */ 
/* 1453 */     if (assignSetting != null)
/*      */     {
/* 1455 */       TaskSign taskSign = this.taskSignService.findByAssignId(assignSetting.getAssignId());
/*      */ 
/* 1457 */       if (taskSign != null)
/*      */       {
/* 1459 */         boolean isFinishSupTask = false;
/*      */ 
/* 1462 */         Short isAgree = (Short)variables.get("signVoteType");
/*      */ 
/* 1464 */         if (isAgree == null) {
/* 1465 */           isAgree = TaskSign.DECIDE_TYPE_PASS;
/*      */         }
/*      */ 
/* 1469 */         this.taskSignDataService.addVote(parentTask.getId(), isAgree);
/*      */ 
/* 1472 */         this.taskService.setVariables(subTask.getId(), variables);
/*      */ 
/* 1474 */         this.taskService.completeTask(subTask.getId());
/*      */ 
/* 1478 */         Long voteCounts = this.taskSignDataService.getVoteCounts(parentTask.getId(), taskSign.getDecideType());
/*      */ 
/* 1480 */         if (taskSign.getVoteCounts() != null) {
/* 1481 */           if (voteCounts.longValue() >= taskSign.getVoteCounts().intValue())
/* 1482 */             isFinishSupTask = true;
/*      */         }
/* 1484 */         else if (taskSign.getVotePercents() != null)
/*      */         {
/* 1486 */           Integer taskSignCounts = (Integer)this.taskService.getVariable(parentTask.getId(), "taskSignCounts");
/* 1487 */           if ((taskSignCounts == null) || (taskSignCounts.intValue() == 0)) {
/* 1488 */             taskSignCounts = Integer.valueOf(1);
/*      */           }
/* 1490 */           BigDecimal totalSubTasks = new BigDecimal(taskSignCounts.intValue());
/*      */ 
/* 1492 */           BigDecimal tempPercent = new BigDecimal(voteCounts.longValue()).divide(totalSubTasks);
/* 1493 */           Integer curPercent = new Integer(tempPercent.multiply(new BigDecimal(100)).intValue());
/*      */ 
/* 1495 */           if (curPercent.intValue() >= taskSign.getVotePercents().intValue()) {
/* 1496 */             isFinishSupTask = true;
/*      */           }
/*      */         }
/*      */ 
/* 1500 */         Map varsMap = new HashMap();
/*      */ 
/* 1504 */         if ((isFinishSupTask) || ((!isFinishSupTask) && (subTasksSize == 1))) {
/* 1505 */           String passRefuse = null;
/* 1506 */           if (isFinishSupTask)
/* 1507 */             passRefuse = TaskSign.DECIDE_TYPE_PASS == taskSign.getDecideType() ? "pass" : "refuse";
/*      */           else {
/* 1509 */             passRefuse = taskSign.getDecideType() == TaskSign.DECIDE_TYPE_PASS ? "refuse" : "pass";
/*      */           }
/* 1511 */           logger.debug("会签投票结果：" + passRefuse);
/* 1512 */           varsMap.put("decisionType", passRefuse);
/* 1513 */           this.taskService.setVariables(parentTask.getId(), varsMap);
/*      */ 
/* 1515 */           this.taskService.completeTask(parentTask.getId(), signalName);
/*      */         }
/*      */       }
/*      */       else {
/* 1519 */         logger.error("Task " + parentTask.getActivityName() + " is not config right sign config in process admin console.");
/*      */ 
/* 1521 */         if (parentTask.getSubTasks().size() == 1) {
/* 1522 */           this.taskService.setVariables(subTask.getId(), variables);
/*      */ 
/* 1524 */           this.taskService.completeTask(subTask.getId());
/*      */ 
/* 1526 */           this.taskService.completeTask(parentTask.getId(), signalName);
/*      */         } else {
/* 1528 */           this.taskService.setVariables(subTask.getId(), variables);
/*      */ 
/* 1530 */           this.taskService.completeTask(subTask.getId());
/* 1531 */           return;
/*      */         }
/*      */       }
/*      */     }
/*      */     else {
/* 1536 */       logger.error("Task " + parentTask.getActivityName() + "is not config the setting in process admin console.");
/*      */     }
/*      */   }
/*      */ 
/*      */   protected boolean isProcessInstanceEnd(String piId)
/*      */   {
/* 1547 */     HistoryProcessInstance hpi = this.historyService.createHistoryProcessInstanceQuery().processInstanceId(piId).uniqueResult();
/* 1548 */     if (hpi != null) {
/* 1549 */       String endActivityName = ((HistoryProcessInstanceImpl)hpi).getEndActivityName();
/* 1550 */       if (endActivityName != null) {
/* 1551 */         return true;
/*      */       }
/*      */     }
/* 1554 */     return false;
/*      */   }
/*      */ 
/*      */   public void newSubTask(String parentTaskId, Long[] userIds)
/*      */   {
/* 1564 */     TaskServiceImpl taskServiceImpl = (TaskServiceImpl)this.taskService;
/* 1565 */     Task parentTask = taskServiceImpl.getTask(parentTaskId);
/*      */ 
/* 1568 */     Map vars = new HashMap();
/* 1569 */     vars.put("taskSignCounts", new Integer(userIds.length));
/* 1570 */     taskServiceImpl.setVariables(parentTaskId, vars);
/*      */ 
/* 1572 */     for (int i = 0; i < userIds.length; i++) {
/* 1573 */       String userId = userIds[i].toString();
/* 1574 */       TaskImpl task = (TaskImpl)taskServiceImpl.newTask(parentTaskId);
/* 1575 */       task.setAssignee(userId);
/* 1576 */       task.setName(parentTask.getName() + "-" + (i + 1));
/* 1577 */       task.setActivityName(parentTask.getName());
/* 1578 */       task.setDescription(parentTask.getDescription());
/*      */ 
/* 1580 */       taskServiceImpl.saveTask(task);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void signalProcess(String executionId, String transitionName, Map<String, Object> variables)
/*      */   {
/* 1595 */     this.executionService.setVariables(executionId, variables);
/* 1596 */     this.executionService.signalExecutionById(executionId, transitionName);
/*      */   }
/*      */ 
/*      */   public void endProcessInstance(String piId)
/*      */   {
/* 1601 */     ExecutionService executionService = this.processEngine.getExecutionService();
/* 1602 */     executionService.endProcessInstance(piId, "ended");
/*      */   }
/*      */ 
/*      */   public void addAssignHandler(ProUserAssign proUserAssign)
/*      */   {
/* 1611 */     ProcessDefinitionImpl pd = (ProcessDefinitionImpl)this.repositoryService.createProcessDefinitionQuery().deploymentId(proUserAssign.getDeployId()).uniqueResult();
/* 1612 */     EnvironmentImpl env = null;
/*      */     try {
/* 1614 */       env = getEnvImpl();
/*      */ 
/* 1616 */       TaskDefinitionImpl taskDef = pd.getTaskDefinition(proUserAssign.getActivityName());
/* 1617 */       UserCodeReference userCodeReference = new UserCodeReference();
/* 1618 */       ObjectDescriptor descriptor = new ObjectDescriptor();
/*      */ 
/* 1620 */       descriptor.setClassName("com.htsoft.core.jbpm.UserAssignHandler");
/*      */ 
/* 1622 */       FieldOperation userIdsFo = new FieldOperation();
/* 1623 */       userIdsFo.setFieldName("userIds");
/* 1624 */       userIdsFo.setDescriptor(new StringDescriptor(proUserAssign.getUserId()));
/*      */ 
/* 1626 */       FieldOperation groupIdsFo = new FieldOperation();
/* 1627 */       groupIdsFo.setFieldName("groupIds");
/* 1628 */       groupIdsFo.setDescriptor(new StringDescriptor(proUserAssign.getRoleId()));
/*      */ 
/* 1630 */       List listOp = new ArrayList();
/* 1631 */       listOp.add(userIdsFo);
/* 1632 */       listOp.add(groupIdsFo);
/* 1633 */       descriptor.setOperations(listOp);
/*      */ 
/* 1635 */       userCodeReference.setCached(false);
/* 1636 */       userCodeReference.setDescriptor(descriptor);
/* 1637 */       taskDef.setAssignmentHandlerReference(userCodeReference);
/*      */     }
/*      */     catch (Exception ex) {
/* 1640 */       logger.error("ADD AssignHandler Error:" + ex.getMessage());
/*      */     } finally {
/* 1642 */       if (env != null)
/* 1643 */         env.close();
/*      */     }
/*      */   }
/*      */ 
/*      */   private EnvironmentImpl getEnvImpl()
/*      */   {
/* 1650 */     EnvironmentImpl env = ((EnvironmentFactory)this.processEngine).openEnvironment();
/* 1651 */     return env;
/*      */   }
/*      */ 
/*      */   public Set<AppUser> getNodeHandlerUsers(ProcessDefinition pd, String activityName, Long startUserId)
/*      */   {
/* 1659 */     Set users = new HashSet();
/*      */ 
/* 1661 */     ProUserAssign proUserAssign = this.proUserAssignService.getByDeployIdActivityName(pd.getDeploymentId(), activityName);
/*      */ 
/* 1663 */     if (proUserAssign != null)
/*      */     {
/*      */       AppUser appUser;
/* 1664 */       if ("__start".equals(proUserAssign.getUserId())) {
/* 1665 */         if (startUserId != null)
/* 1666 */           users.add(this.appUserService.get(startUserId));
/*      */         else
/* 1668 */           users.add(ContextUtil.getCurrentUser());
/*      */       }
/* 1670 */       else if (StringUtils.isNotEmpty(proUserAssign.getUserId())) {
/* 1671 */         String[] userIds = proUserAssign.getUserId().split("[,]");
/* 1672 */         for (int i = 0; i < userIds.length; i++) {
/* 1673 */           appUser = (AppUser)this.appUserService.get(new Long(userIds[i]));
/* 1674 */           users.add(appUser);
/*      */         }
/*      */       }
/*      */ 
/* 1678 */       if (StringUtils.isNotEmpty(proUserAssign.getRoleId())) {
/* 1679 */         List userList = this.appUserService.findUsersByRoleIds(proUserAssign.getRoleId());
/* 1680 */         users.addAll(userList);
/*      */       }
/*      */       String[] arrayOfString1;
/* 1683 */       if (StringUtils.isNotEmpty(proUserAssign.getJobId())) {
/* 1684 */         String[] jobIds = proUserAssign.getJobId().split("[,]");
/* 1685 */         int localAppUser1 = (arrayOfString1 = jobIds).length; 
				   for (int i = 0; i < localAppUser1; i++) { 
					 String jobId = arrayOfString1[i];
/* 1686 */           List jobUsers = this.userJobService.getUsersByJobId(new Long(jobId));
/* 1687 */           users.addAll(jobUsers);
/*      */         }
/*      */       }
/*      */ 
/* 1691 */       if (StringUtils.isNotEmpty(proUserAssign.getReJobId())) {
/* 1692 */         String[] reJobIds = proUserAssign.getReJobId().split("[,]");
/* 1693 */         int localAppUser2 = (arrayOfString1 = reJobIds).length; 
                   for (int i  = 0; i < localAppUser2; i++) { String jobId = arrayOfString1[i];
/* 1694 */           List userList = this.relativeUserService.findByUserIdReJobId(startUserId, new Long(jobId));
/* 1695 */           users.addAll(userList);
/*      */         }
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1701 */     if (users.size() == 0) {
/* 1702 */       if (startUserId != null)
/* 1703 */         users.add(this.appUserService.get(startUserId));
/*      */       else {
/* 1705 */         users.add(ContextUtil.getCurrentUser());
/*      */       }
/*      */     }
/* 1708 */     return users;
/*      */   }
/*      */ 
/*      */   public Set<AppUser> getNodeHandlerUsers(Long defId, String activityName)
/*      */   {
/* 1718 */     ProcessDefinition pd = getProcessDefinitionByDefId(defId);
/* 1719 */     return getNodeHandlerUsers(pd, activityName, null);
/*      */   }
/*      */ 
/*      */   public Set<AppUser> getNodeHandlerUsers(String taskId, String nextActivityName)
/*      */   {
/* 1730 */     ProcessInstance pi = getProcessInstanceByTaskId(taskId);
/*      */ 
/* 1732 */     ProcessDefinition pd = getProcessDefinitionByTaskId(taskId);
/*      */ 
/* 1734 */     Long startUserId = (Long)this.executionService.getVariable(pi.getId(), "startUserId");
/*      */ 
/* 1736 */     return getNodeHandlerUsers(pd, nextActivityName, startUserId);
/*      */   }
/*      */ 
/*      */   public Long getFlowStartUserId(String taskId)
/*      */   {
/* 1745 */     ProcessInstance pi = getProcessInstanceByTaskId(taskId);
/* 1746 */     Long startUserId = (Long)this.executionService.getVariable(pi.getId(), "startUserId");
/* 1747 */     if (startUserId == null) {
/* 1748 */       ProcessRun processRun = this.processRunService.getByPiId(pi.getId());
/* 1749 */       if (processRun != null) {
/* 1750 */         return processRun.getUserId();
/*      */       }
/*      */     }
/* 1753 */     return startUserId;
/*      */   }
/*      */ 
/*      */   public Object getVarByTaskIdVarName(String taskId, String varName)
/*      */   {
/* 1765 */     TaskImpl task = (TaskImpl)getTaskById(taskId);
/* 1766 */     if (task.getSuperTask() != null) {
/* 1767 */       taskId = task.getSuperTask().getId();
/*      */     }
/* 1769 */     return this.taskService.getVariable(taskId, varName);
/*      */   }
/*      */ 
/*      */   public Map<String, Object> getVarsByTaskId(String taskId)
/*      */   {
/* 1778 */     Task task = getParentTask(taskId);
/* 1779 */     Map varMap = new HashMap();
/* 1780 */     Set varNames = this.taskService.getVariableNames(task.getId());
/* 1781 */     Iterator nameIt = varNames.iterator();
/* 1782 */     while (nameIt.hasNext()) {
/* 1783 */       String varName = (String)nameIt.next();
/* 1784 */       Object objVal = this.taskService.getVariable(task.getId(), varName);
/* 1785 */       varMap.put(varName, objVal);
/*      */     }
/* 1787 */     return varMap;
/*      */   }
/*      */ 
/*      */   private List<Node> getValidNodesFromXml(String xml)
/*      */   {
/* 1796 */     List nodes = new ArrayList();
/*      */     try {
/* 1798 */       Element root = DocumentHelper.parseText(xml).getRootElement();
				 List<Element> elements = root.elements();
/* 1799 */       for (Element elem : elements) {
/* 1800 */         String type = elem.getQName().getName();
/* 1801 */         if ("task".equalsIgnoreCase(type)) {
/* 1802 */           if (elem.attribute("name") != null) {
/* 1803 */             Node node = new Node(elem.attribute("name").getValue(), "任务节点");
/* 1804 */             nodes.add(node);
/*      */           }
/* 1806 */         } else if ("fork".equalsIgnoreCase(type)) {
/* 1807 */           if (elem.attribute("name") != null) {
/* 1808 */             Node node = new Node(elem.attribute("name").getValue(), "同步节点");
/* 1809 */             nodes.add(node);
/*      */           }
/* 1811 */         } else if ("join".equalsIgnoreCase(type)) {
/* 1812 */           if (elem.attribute("name") != null) {
/* 1813 */             Node node = new Node(elem.attribute("name").getValue(), "汇集节点");
/* 1814 */             nodes.add(node);
/*      */           }
/*      */         }
/* 1817 */         else if (type.startsWith("end")) {
/* 1818 */           if (elem.attribute("name") != null) {
/* 1819 */             Node node = new Node(elem.attribute("name").getValue(), "分支节点");
/* 1820 */             nodes.add(node);
/*      */           }
/* 1822 */         } else if (type.startsWith("end")) {
/* 1823 */           Node node = new Node(elem.attribute("name").getValue(), "结束节点");
/* 1824 */           nodes.add(node);
/*      */         }
/*      */       }
/*      */     } catch (Exception ex) {
/* 1828 */       logger.error(ex.getMessage());
/*      */     }
/* 1830 */     return nodes;
/*      */   }
/*      */ 
/*      */   public List<Transition> getNodeOuterTrans(ProcessDefinition definition, String nodeName)
/*      */   {
/* 1836 */     EnvironmentFactory environmentFactory = (EnvironmentFactory)this.processEngine;
/* 1837 */     EnvironmentImpl env = environmentFactory.openEnvironment();
/*      */     try {
/* 1839 */       ProcessDefinitionImpl pd = (ProcessDefinitionImpl)definition;
/* 1840 */       ActivityImpl activityFind = pd.findActivity(nodeName);
/*      */ 
/* 1842 */       if (activityFind != null)
/* 1843 */         return (List<Transition>) activityFind.getOutgoingTransitions();
/*      */     }
/*      */     finally {
/* 1846 */       env.close();
/*      */     }
/* 1848 */     return new ArrayList();
/*      */   }
/*      */ 
/*      */   public List<String> getAssigneeByTaskId(String parentTaskId)
/*      */   {
/* 1856 */     List list = new ArrayList();
/* 1857 */     TaskImpl taskImpl = (TaskImpl)getTaskById(parentTaskId);
/* 1858 */     if (taskImpl.getAssignee() != null) {
/* 1859 */       list.add(taskImpl.getAssignee());
/*      */     }
/* 1861 */     Set subTasks = taskImpl.getSubTasks();
/* 1862 */     if (subTasks != null) {
/* 1863 */       Iterator it = subTasks.iterator();
/* 1864 */       while (it.hasNext()) {
/* 1865 */         Task subTask = (Task)it.next();
/* 1866 */         if (subTask.getAssignee() != null) {
/* 1867 */           list.add(subTask.getAssignee());
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/* 1872 */     return list;
/*      */   }
/*      */ 
/*      */   public Task getParentTask(String subTaskId)
/*      */   {
/* 1881 */     TaskImpl taskImpl = (TaskImpl)getTaskById(subTaskId);
/* 1882 */     if (taskImpl.getSuperTask() != null) {
/* 1883 */       return taskImpl.getSuperTask();
/*      */     }
/* 1885 */     return taskImpl;
/*      */   }
/*      */ 
/*      */   public void jumpToPreTask(String piId, String assignee, String preTaskName)
/*      */   {
/* 1896 */     List tasks = getTasksByPiId(piId);
/*      */ 
/* 1898 */     Task nowTask = null;
/*      */ 
/* 1902 */     Long piDbId = null;
/*      */ 
/* 1904 */     for (int i = 0; i < tasks.size(); i++) {
/* 1905 */       TaskImpl task = (TaskImpl)tasks.get(i);
/* 1906 */       this.flowTaskService.evict(task);
/* 1907 */       if (i == 0) {
/* 1908 */         nowTask = task;
/*      */       } else {
/* 1910 */         piDbId = Long.valueOf(task.getProcessInstance().getDbid());
/* 1911 */         this.taskService.completeTask(task.getId());
/*      */       }
/*      */     }
/*      */ 
/* 1915 */     if (nowTask != null) {
/* 1916 */       jumpTaskToAnother(nowTask, preTaskName, null);
/*      */ 
/* 1918 */       List<Task> newTasks = getTasksByPiId(piId);
/*      */       Task newTask;
/* 1922 */       if (piDbId != null) {
/* 1923 */         ExecutionImpl execution = (ExecutionImpl)this.flowTaskService.getExecutionByDbid(piDbId);
/*      */ 
/* 1925 */         execution.setState("active-root");
/*      */ 
/* 1927 */         this.flowTaskService.save(execution);
/*      */ 
/* 1929 */         for (Iterator localIterator = newTasks.iterator(); localIterator.hasNext(); ) { newTask = (Task)localIterator.next();
/* 1930 */           TaskImpl newTaskImpl = (TaskImpl)newTask;
/* 1931 */           newTaskImpl.setProcessInstance(execution);
/* 1932 */           newTaskImpl.setExecution(execution);
/* 1933 */           newTaskImpl.setAssignee(assignee);
/* 1934 */           newTaskImpl.setActivityName(newTask.getName());
/* 1935 */           ProcessDefinitionImpl pd = (ProcessDefinitionImpl)getProcessDefinitionByTaskId(newTask.getId());
/*      */ 
/* 1937 */           Activity activity = pd.findActivity(newTask.getName());
/* 1938 */           if (activity != null) {
/* 1939 */             execution.setActivity(activity);
/*      */           }
/* 1941 */           this.flowTaskService.save(newTaskImpl);
/*      */         }
/*      */ 
/* 1945 */         this.flowTaskService.removeExeByParentId(piDbId);
/*      */       } else {
/* 1947 */           for (Task newTask1 : newTasks)
/* 1948 */           this.taskService.assignTask(newTask1.getId(), assignee);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public void completeTaskAndJump(String taskId, String destName, Map variables)
/*      */   {
/* 1961 */     TaskImpl curTaskImpl = (TaskImpl)getTaskById(taskId);
/* 1962 */     String piId = curTaskImpl.getProcessInstance().getId();
/*      */ 
/* 1964 */     List<Task> tasks = getTasksByPiId(piId);
/*      */ 
/* 1967 */     boolean isConcurrent = "active-concurrent".equals(curTaskImpl.getExecution().getState());
/*      */ 
/* 1970 */     Integer formalJump = jumpTaskToAnother(curTaskImpl, destName, variables);
/*      */ 
/* 1972 */     if ((formalJump.intValue() == 0) && (isConcurrent)) {
/* 1973 */       for (Task task : tasks) {
/* 1974 */         if (!task.getId().equals(curTaskImpl.getId())) {
/* 1975 */           this.taskService.completeTask(task.getId());
/*      */         }
/*      */       }
/* 1978 */       List<Task> newTasks = getTasksByPiId(piId);
/*      */ 
/* 1980 */       Long piDbId = Long.valueOf(curTaskImpl.getProcessInstance().getDbid());
/* 1981 */       ExecutionImpl execution = (ExecutionImpl)this.flowTaskService.getExecutionByDbid(piDbId);
/*      */ 
/* 1983 */       execution.setState("active-root");
/*      */ 
/* 1985 */       this.flowTaskService.save(execution);
/*      */ 
/* 1987 */       for (Task newTask : newTasks) {
/* 1988 */         TaskImpl newTaskImpl = (TaskImpl)newTask;
/* 1989 */         newTaskImpl.setProcessInstance(execution);
/* 1990 */         newTaskImpl.setExecution(execution);
/*      */ 
/* 1992 */         newTaskImpl.setActivityName(newTask.getName());
/* 1993 */         ProcessDefinitionImpl pd = (ProcessDefinitionImpl)getProcessDefinitionByTaskId(newTask.getId());
/*      */ 
/* 1995 */         Activity activity = pd.findActivity(newTask.getName());
/* 1996 */         if (activity != null) {
/* 1997 */           execution.setActivity(activity);
/*      */         }
/* 1999 */         this.flowTaskService.save(newTaskImpl);
/*      */       }
/*      */ 
/* 2002 */       this.flowTaskService.removeExeByParentId(piDbId);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void wirteDefXml(String deployId, String defXml)
/*      */   {
/* 2012 */     EnvironmentFactory environmentFactory = (EnvironmentFactory)this.processEngine;
/* 2013 */     EnvironmentImpl env = environmentFactory.openEnvironment();
/*      */     try
/*      */     {
/* 2016 */       RepositoryCache repositoryCache = (RepositoryCache)EnvironmentImpl.getFromCurrent(RepositoryCache.class);
/* 2017 */       repositoryCache.set(deployId, null);
/*      */     } finally {
/* 2019 */       env.close();
/*      */     }
/*      */ 
/* 2022 */     this.jbpmDao.wirteDefXml(deployId, defXml);
/*      */   }
/*      */ }

/* Location:           E:\Workspace\Template Projects\estbpm-orcl\estbpm\WEB-INF\classes\
 * Qualified Name:     com.htsoft.est.service.flow.impl.JbpmServiceImpl
 * JD-Core Version:    0.6.0
 */