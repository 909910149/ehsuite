 package com.htsoft.oa.action.flow;
 
 import com.google.gson.Gson;
 import com.htsoft.core.model.DynaModel;
 import com.htsoft.core.service.DynamicService;
 import com.htsoft.core.util.AppUtil;
 import com.htsoft.core.util.BeanUtil;
 import com.htsoft.core.util.ContextUtil;
 import com.htsoft.core.util.FunctionsUtil;
 import com.htsoft.core.util.JsonUtil;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.oa.model.flow.FieldRights;
 import com.htsoft.oa.model.flow.FormDef;
 import com.htsoft.oa.model.flow.FormDefMapping;
 import com.htsoft.oa.model.flow.FormField;
 import com.htsoft.oa.model.flow.FormTable;
 import com.htsoft.oa.model.flow.ProDefinition;
 import com.htsoft.oa.model.flow.ProcessRun;
 import com.htsoft.oa.model.flow.TaskSignData;
 import com.htsoft.oa.model.flow.Transform;
 import com.htsoft.oa.model.system.AppUser;
 import com.htsoft.oa.service.flow.FieldRightsService;
 import com.htsoft.oa.service.flow.FlowFormService;
 import com.htsoft.oa.service.flow.FormDefMappingService;
 import com.htsoft.oa.service.flow.FormDefService;
 import com.htsoft.oa.service.flow.JbpmService;
 import com.htsoft.oa.service.flow.ProDefinitionService;
 import com.htsoft.oa.service.flow.ProcessRunService;
 import com.htsoft.oa.service.flow.ProcessService;
 import com.htsoft.oa.service.flow.RunDataService;
 import com.htsoft.oa.service.flow.TaskSignDataService;
 import com.htsoft.oa.service.system.AppUserService;
 import com.htsoft.oa.util.FlowUtil;
 import flexjson.JSONSerializer;
 import java.io.File;
 import java.io.Serializable;
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.Iterator;
 import java.util.List;
 import java.util.Map;
 import java.util.Set;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 import org.apache.commons.lang.StringUtils;
 import org.apache.commons.logging.Log;
 import org.apache.velocity.app.VelocityEngine;
 import org.jbpm.api.ProcessDefinition;
 import org.jbpm.api.model.Activity;
 import org.jbpm.api.model.Transition;
 import org.jbpm.api.task.Task;
 import org.jbpm.pvm.internal.model.ExecutionImpl;
 import org.springframework.ui.velocity.VelocityEngineUtils;
 
 public class ProcessActivityAction extends BaseAction
 {
 
   @Resource
   private ProDefinitionService proDefinitionService;
 
   @Resource
   private ProcessRunService processRunService;
 
   @Resource
   private JbpmService jbpmService;
 
   @Resource
   private RunDataService runDataService;
 
   @Resource
   private ProcessService processService;
 
   @Resource
   private FormDefMappingService formDefMappingService;
 
   @Resource
   private FieldRightsService fieldRightsService;
 
   @Resource
   private FormDefService formDefService;
 
   @Resource
   private VelocityEngine flowVelocityEngine;
 
   @Resource
   private TaskSignDataService taskSignDataService;
 
   @Resource
   private FlowFormService flowFormService;
 
   @Resource
   private AppUserService appUserService;
   private String activityName;
   private Long runId;
   private Long taskId;
   private Long defId;
 
   public Long getTaskId()
   {
     return this.taskId;
   }
 
   public void setTaskId(Long taskId) {
     this.taskId = taskId;
   }
 
   public Long getRunId() {
     return this.runId;
   }
 
   public void setRunId(Long runId) {
     this.runId = runId;
   }
 
   public String getActivityName() {
     return this.activityName;
   }
 
   public void setActivityName(String activityName) {
     this.activityName = activityName;
   }
 
   public Long getDefId()
   {
     return this.defId;
   }
 
   public void setDefId(Long defId) {
     this.defId = defId;
   }
 
   public String get()
     throws Exception
   {
     HttpServletRequest request = getRequest();
     String deployId = null;
     ProcessRun processRun = null;
 
     Map formVars = new HashMap();
 
     ProDefinition proDefinition = null;
 
     FormDef formDef = null;
 
     String taskName = this.activityName;
     if (this.taskId != null) {
       deployId = this.jbpmService.getProcessDefinitionByTaskId(this.taskId.toString()).getDeploymentId();
       request.setAttribute("taskId", this.taskId);
 
       ExecutionImpl pi = (ExecutionImpl)this.jbpmService.getProcessInstanceByTaskId(this.taskId.toString());
       String piId = pi.getId();
       if (pi.getSuperProcessExecution() != null) {
         piId = pi.getSuperProcessExecution().getId();
       }
 
       processRun = this.processRunService.getByPiId(piId);
 
       if (processRun.getFormDefId() != null) {
         formDef = (FormDef)this.formDefService.get(processRun.getFormDefId());
       }
 
       Serializable pkValue = processRun.getEntityId();
       String entityName = processRun.getEntityName();
 
       if (entityName != null) {
         DynamicService dynamicService = BeanUtil.getDynamicServiceBean(entityName);
         DynaModel dyModel = (DynaModel)FlowUtil.DynaModelMap.get(entityName);
         if (pkValue != null) {
           Object entity = dynamicService.get(new Long(pkValue.toString()));
 
           request.setAttribute("entityJson", JsonUtil.mapEntity2Json((Map)entity, entityName));
           request.setAttribute("pkValue", pkValue);
           if (dyModel != null)
             request.setAttribute("pkName", dyModel.getPrimaryFieldName());
         }
       }
     }
     else {
       request.setAttribute("defId", this.defId);
       proDefinition = (ProDefinition)this.proDefinitionService.get(this.defId);
       if (this.activityName == null) {
         taskName = this.jbpmService.getStartNodeName(proDefinition);
       }
       deployId = proDefinition.getDeployId();
     }
 
     FormDefMapping fdm = this.formDefMappingService.getByDeployId(deployId);
 
     List rights = new ArrayList();
     if (fdm != null) {
       proDefinition = (ProDefinition)this.proDefinitionService.get(fdm.getDefId());
       if (FormDefMapping.USE_TEMPLATE.equals(fdm.getUseTemplate()))
       {
         formVars.put("activityName", taskName);
         if (this.taskId != null) {
           Map vars = this.jbpmService.getVarsByTaskId(this.taskId.toString());
           formVars.putAll(vars);
 
           if (processRun != null) {
             Map fVars = this.runDataService.getMapByRunId(processRun.getRunId());
             formVars.putAll(fVars);
           }
         }
 
         String dirPath = "/" + proDefinition.getName() + "/" + fdm.getVersionNo() + "/";
         String formUiJs = null;
 
         String nodeVmPath = dirPath + taskName + ".vm";
 
         String tempPath = dirPath + "Template.vm";
 
         String absPath = AppUtil.getAppAbsolutePath() + "/WEB-INF/FlowForm";
 
         if (new File(absPath + nodeVmPath).exists()) {
           formUiJs = VelocityEngineUtils.mergeTemplateIntoString(this.flowVelocityEngine, nodeVmPath, "UTF-8", formVars);
         } else if (new File(absPath + tempPath).exists()) {
           formUiJs = VelocityEngineUtils.mergeTemplateIntoString(this.flowVelocityEngine, tempPath, "UTF-8", formVars);
         } else {
           String comFormPath = "/通用/表单.vm";
           formUiJs = VelocityEngineUtils.mergeTemplateIntoString(this.flowVelocityEngine, comFormPath, "UTF-8", formVars);
         }
 
         request.setAttribute("formUiJs", formUiJs);
         return "formExt";
       }
       if (formDef == null) {
         formDef = fdm.getFormDef();
       }
       if (formDef == null) {
         formDef = (FormDef)this.formDefService.get(FormDef.DEFAULT_FLOW_FORMID);
       }
 
       rights = this.fieldRightsService.getByMappingIdAndTaskName(fdm.getMappingId(), taskName);
     }
     else {
       formDef = (FormDef)this.formDefService.get(FormDef.DEFAULT_FLOW_FORMID);
     }
 
     request.setAttribute("formRights", getRights(rights));
 
     Iterator it = formDef.getFormTables().iterator();
     List reList = new ArrayList();
     List tables = new ArrayList();
     while (it.hasNext()) {
       FormTable formTable = (FormTable)it.next();
       if (FormTable.MAIN_TABLE.equals(formTable.getIsMain())) {
         request.setAttribute("mainTable", formTable);
       }
       else {
         DynaModel subModel = (DynaModel)FlowUtil.DynaModelMap.get(formTable.getEntityName());
         if (subModel != null) {
           reList.add(subModel);
         }
         tables.add(formTable);
 
         String subSetVarName = FunctionsUtil.makeFirstLetterUpperCase(formTable.getEntityName()) + "s";
         request.setAttribute("subSetVarName", subSetVarName);
       }
     }
     request.setAttribute("subModels", reList);
     request.setAttribute("subTables", tables);
     request.setAttribute("formDef", formDef);
     return "formHtml";
   }
 
   public String flowForm()
   {
     return "formHtml";
   }
 
   public String getForm()
   {
     HttpServletRequest request = getRequest();
     String runId = request.getParameter("runId");
     String defId = request.getParameter("defId");
     ProcessRun processRun = (ProcessRun)this.processRunService.get(new Long(runId));
 
     if (StringUtils.isEmpty(defId)) {
       defId = processRun.getProDefinition().getDefId().toString();
     }
     ProDefinition proDefinition = (ProDefinition)this.proDefinitionService.get(new Long(defId));
     String deployId = proDefinition.getDeployId();
     FormDefMapping fdm = this.formDefMappingService.getByDeployId(deployId);
     FormDef formDef = null;
 
     if ((processRun != null) && (processRun.getFormDefId() != null)) {
       formDef = (FormDef)this.formDefService.get(processRun.getFormDefId());
     }
 
     if (fdm != null) {
       proDefinition = (ProDefinition)this.proDefinitionService.get(fdm.getDefId());
       if (FormDefMapping.USE_TEMPLATE.equals(fdm.getUseTemplate())) {
         return "formHtml";
       }
       if (formDef == null) {
         formDef = fdm.getFormDef();
       }
       if (formDef == null)
         formDef = (FormDef)this.formDefService.get(FormDef.DEFAULT_FLOW_FORMID);
     }
     else
     {
       formDef = (FormDef)this.formDefService.get(FormDef.DEFAULT_FLOW_FORMID);
     }
     Serializable pkValue = processRun.getEntityId();
     String entityName = processRun.getEntityName();
 
     if (entityName != null) {
       DynamicService dynamicService = BeanUtil.getDynamicServiceBean(entityName);
       DynaModel dyModel = (DynaModel)FlowUtil.DynaModelMap.get(entityName);
       if (pkValue != null) {
         Object entity = dynamicService.get(new Long(pkValue.toString()));
 
         request.setAttribute("entityJson", JsonUtil.mapEntity2Json((Map)entity, entityName));
         request.setAttribute("pkValue", pkValue);
         if (dyModel != null) {
           request.setAttribute("pkName", dyModel.getPrimaryFieldName());
         }
       }
     }
 
     Iterator it = formDef.getFormTables().iterator();
     List reList = new ArrayList();
     List tables = new ArrayList();
     while (it.hasNext()) {
       FormTable formTable = (FormTable)it.next();
       if (FormTable.MAIN_TABLE.equals(formTable.getIsMain())) {
         request.setAttribute("mainTable", formTable);
       }
       else {
         DynaModel subModel = (DynaModel)FlowUtil.DynaModelMap.get(formTable.getEntityName());
         if (subModel != null) {
           request.setAttribute("subPkName", subModel.getPrimaryFieldName());
           reList.add(subModel);
         }
 
         tables.add(formTable);
 
         String subSetVarName = FunctionsUtil.makeFirstLetterUpperCase(formTable.getEntityName()) + "s";
         request.setAttribute("subSetVarName", subSetVarName);
       }
     }
     request.setAttribute("subModels", reList);
     request.setAttribute("subTables", tables);
     request.setAttribute("defId", defId);
     request.setAttribute("runId", runId);
     request.setAttribute("formDef", formDef);
     return "formHtml";
   }
 
   public String check()
   {
     Task task = this.jbpmService.getTaskById(String.valueOf(this.taskId));
 
     if (task != null) {
       String assignId = task.getAssignee();
       Long curUserId = ContextUtil.getCurrentUserId();
 
       if (curUserId.toString().equals(assignId)) {
         this.jsonString = "{success:true,isValid:true,msg:''}";
       } else if (StringUtils.isNotEmpty(assignId)) {
         this.jsonString = "{success:true,isValid:false,msg:'该任务已经被其他成员锁定执行！'}";
       } else {
         this.jbpmService.assignTask(task.getId(), curUserId.toString());
         this.jsonString = "{success:true,isValid:true,msg:'该任务已经被您锁定执行!'}";
       }
     } else {
       this.jsonString = "{success:true,isValid:false,msg:'该任务已经完成了'}";
     }
 
     return "success";
   }
 
   public String save()
   {
     if (this.logger.isDebugEnabled())
       this.logger.info("start process..............");
     try
     {
       this.processService.doStartFlow(getRequest());
     } catch (Exception ex) {
       this.logger.error("error:" + ex.getMessage());
       ex.printStackTrace();
       setJsonString("{success:false}");
     }
     return "success";
   }
 
   public String next()
   {
     if (this.logger.isDebugEnabled())
       this.logger.info("process jump to next ..............");
     try
     {
       this.processService.doNextFlow(getRequest());
     } catch (Exception ex) {
       this.logger.error("error:" + ex.getMessage());
       ex.printStackTrace();
       setJsonString("{success:false}");
     }
     return "success";
   }
 
   public String allowBack()
   {
     if (this.logger.isDebugEnabled()) {
       this.logger.info("allown black ");
     }
 
     boolean isAllown = this.jbpmService.isAllownBack(getRequest().getParameter("taskId"));
 
     setJsonString("{success:" + isAllown + "}");
     return "success";
   }
 
   public String startTrans()
   {
     ProDefinition proDef = (ProDefinition)this.proDefinitionService.get(this.defId);
     if (proDef != null)
     {
       List<Transition> trans = this.jbpmService.getStartOutTransByDeployId(proDef.getDeployId());
 
       List allTrans = new ArrayList();
 
       for (Transition tran : trans) {
         if ((tran != null) && (tran.getDestination() != null)) {
           allTrans.add(new Transform(tran));
         }
       }
 
       JSONSerializer serializer = JsonUtil.getJSONSerializer();
       String result = serializer.serialize(allTrans);
 
       setJsonString("{success:true,data:" + result + "}");
     }
 
     return "success";
   }
 
   public String freeTrans()
   {
     Gson gson = new Gson();
     StringBuffer sb = new StringBuffer();
 
     sb.append("[");
 
     List<Transition> trans = this.jbpmService.getFreeTransitionsByTaskId(this.taskId.toString());
 
     for (Transition tran : trans) {
       sb.append("[").append(gson.toJson(tran.getName())).append(",").append(gson.toJson(tran.getDestination().getName()))
         .append(",").append(gson.toJson(tran.getDestination().getType()))
         .append("],");
     }
 
     if (trans.size() > 0) {
       sb.deleteCharAt(sb.length() - 1);
     }
 
     sb.append("]");
 
     setJsonString(sb.toString());
 
     return "success";
   }
 
   public String outerTrans()
   {
     ProcessDefinition pd = null;
     if (this.taskId != null)
       pd = this.jbpmService.getProcessDefinitionByTaskId(this.taskId.toString());
     else {
       pd = this.jbpmService.getProcessDefinitionByDefId(this.defId);
     }
 
     String nodeName = getRequest().getParameter("nodeName");
     List<Transition> trans = this.jbpmService.getNodeOuterTrans(pd, nodeName);
 
     Gson gson = new Gson();
     StringBuffer sb = new StringBuffer();
 
     sb.append("[");
 
     for (Transition tran : trans) {
       sb.append("[").append(gson.toJson(tran.getName())).append(",")
         .append(gson.toJson(tran.getDestination().getName()))
         .append(",").append(gson.toJson(tran.getDestination().getType()))
         .append(",").append(getUsers(pd, tran.getDestination().getName()))
         .append("],");
     }
 
     if (trans.size() > 0) {
       sb.deleteCharAt(sb.length() - 1);
     }
 
     sb.append("]");
 
     setJsonString(sb.toString());
 
     return "success";
   }
 
   private String getUsers(ProcessDefinition pd, String activityName) {
     Set users = null;
 
     if (this.taskId != null) {
       Long startUserId = this.jbpmService.getFlowStartUserId(this.taskId.toString());
       users = this.jbpmService.getNodeHandlerUsers(pd, activityName, startUserId);
     } else {
       users = this.jbpmService.getNodeHandlerUsers(pd, activityName, ContextUtil.getCurrentUserId());
     }
     StringBuffer uIds = new StringBuffer();
     StringBuffer uNames = new StringBuffer();
     Iterator it = users.iterator();
     int i = 0;
     while (it.hasNext()) {
       AppUser user = (AppUser)it.next();
       if (i > 0) {
         uIds.append(",");
         uNames.append(",");
       }
       uIds.append(user.getUserId());
       uNames.append(user.getFullname());
       i++;
     }
     return "\"" + uIds.toString() + "\",\"" + uNames.toString() + "\"";
   }
 
   public String users()
   {
     ProcessDefinition pd = null;
     Set users = null;
 
     if (this.taskId != null) {
       pd = this.jbpmService.getProcessDefinitionByTaskId(this.taskId.toString());
       Long startUserId = this.jbpmService.getFlowStartUserId(this.taskId.toString());
       users = this.jbpmService.getNodeHandlerUsers(pd, this.activityName, startUserId);
     } else {
       pd = this.jbpmService.getProcessDefinitionByDefId(this.defId);
       users = this.jbpmService.getNodeHandlerUsers(pd, this.activityName, ContextUtil.getCurrentUserId());
     }
 
     StringBuffer uIds = new StringBuffer();
     StringBuffer uNames = new StringBuffer();
     Iterator it = users.iterator();
     int i = 0;
     while (it.hasNext()) {
       AppUser user = (AppUser)it.next();
       if (i > 0) {
         uIds.append(",");
         uNames.append(",");
       }
       uIds.append(user.getUserId());
       uNames.append(user.getFullname());
       i++;
     }
 
     this.jsonString = ("{success:true,userIds:'" + uIds.toString() + "',userNames:'" + uNames.toString() + "'}");
 
     return "success";
   }
 
   public String trans()
   {
     String preTaskName = this.jbpmService.getPreTask(this.taskId.toString());
     if (preTaskName == null) {
       preTaskName = "";
     }
 
     boolean isSignTask = this.jbpmService.isSignTask(this.taskId.toString());
 
     List allTrans = new ArrayList();
 
     List<Transition> trans = this.jbpmService.getTransitionsByTaskId(this.taskId.toString());
 
     for (Transition tran : trans) {
       if ((tran != null) && (tran.getDestination() != null)) {
         allTrans.add(new Transform(tran));
       }
     }
 
     JSONSerializer serializer = JsonUtil.getJSONSerializer();
     String result = serializer.serialize(allTrans);
 
     setJsonString("{success:true,preTaskName:'" + preTaskName + "',isSignTask:" + isSignTask + ",data:" + result + "}");
     return "success";
   }
 
   public String signList()
   {
     Task parentTask = this.jbpmService.getParentTask(this.taskId.toString());
 
     List signDataList = this.taskSignDataService.getByTaskId(parentTask.getId());
 
     List<String> unHandleUserList = this.jbpmService.getAssigneeByTaskId(parentTask.getId());
     for (String userId : unHandleUserList) {
       TaskSignData data = new TaskSignData();
       AppUser user = (AppUser)this.appUserService.get(new Long(userId));
       data.setVoteName(user.getFullname());
 
       signDataList.add(data);
     }
     getRequest().setAttribute("signDataList", signDataList);
     return "signList";
   }
 
   protected ProDefinition getProDefinition()
   {
     ProDefinition proDefinition = null;
     if (this.runId != null) {
       ProcessRun processRun = (ProcessRun)this.processRunService.get(this.runId);
       proDefinition = processRun.getProDefinition();
     } else if (this.defId != null) {
       proDefinition = (ProDefinition)this.proDefinitionService.get(this.defId);
     } else {
       ProcessRun processRun = this.processRunService.getByTaskId(this.taskId.toString());
       proDefinition = processRun.getProDefinition();
     }
     return proDefinition;
   }
 
   protected String getRights(List<FieldRights> rights)
   {
     StringBuffer sb = new StringBuffer();
     sb.append("{");
     for (FieldRights right : rights) {
       sb.append("'").append(right.getFormField().getFieldName()).append("':'").append(right.getReadWrite()).append("',");
     }
     if (rights.size() > 0) {
       sb.deleteCharAt(sb.length() - 1);
     }
     sb.append("}");
     return sb.toString();
   }
 
   public String delItems()
   {
     String strIds = getRequest().getParameter("ids");
     String tableId = getRequest().getParameter("tableId");
     this.flowFormService.deleteItems(strIds, new Long(tableId));
     setJsonString("{success:true}");
     return "success";
   }
 }

