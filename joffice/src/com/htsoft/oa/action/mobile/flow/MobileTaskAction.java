/*     */ package com.htsoft.oa.action.mobile.flow;
/*     */ 
/*     */ import com.htsoft.core.jbpm.pv.ParamField;
/*     */ import com.htsoft.core.util.ContextUtil;
/*     */ import com.htsoft.core.web.action.BaseAction;
/*     */ import com.htsoft.core.web.paging.PagingBean;
/*     */ import com.htsoft.oa.action.flow.FlowRunInfo;
/*     */ import com.htsoft.oa.action.flow.ProcessActivityAssistant;
/*     */ import com.htsoft.oa.model.flow.ProDefinition;
/*     */ import com.htsoft.oa.model.flow.ProcessForm;
/*     */ import com.htsoft.oa.model.flow.ProcessRun;
/*     */ import com.htsoft.oa.model.flow.Transform;
/*     */ import com.htsoft.oa.service.flow.JbpmService;
/*     */ import com.htsoft.oa.service.flow.ProDefinitionService;
/*     */ import com.htsoft.oa.service.flow.ProcessRunService;
/*     */ import com.htsoft.oa.service.flow.TaskService;
/*     */ import java.io.File;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Resource;
/*     */ import javax.servlet.ServletContext;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.jbpm.api.ProcessDefinition;
/*     */ import org.jbpm.api.model.Transition;
/*     */ import org.jbpm.pvm.internal.task.TaskImpl;
/*     */ 
/*     */ public class MobileTaskAction extends BaseAction
/*     */ {
/*     */ 
/*     */   @Resource
/*     */   private ProcessRunService processRunService;
/*     */ 
/*     */   @Resource
/*     */   private ProDefinitionService proDefinitionService;
/*     */   private Long defId;
/*     */   private String taskId;
/*     */   private String processName;
/*     */   private String taskName;
/*  83 */   private List outTrans = new ArrayList();
/*     */   private String vmPath;
/*     */ 
/*     */   @Resource(name="flowTaskService")
/*     */   private TaskService flowTaskService;
/*     */ 
/*     */   @Resource
/*     */   private JbpmService jbpmService;
/*     */ 
/*     */   public String getProcessName()
/*     */   {
/*  65 */     return this.processName;
/*     */   }
/*     */ 
/*     */   public void setProcessName(String processName) {
/*  69 */     this.processName = processName;
/*     */   }
/*     */ 
/*     */   public String getTaskName() {
/*  73 */     return this.taskName;
/*     */   }
/*     */ 
/*     */   public void setTaskName(String taskName) {
/*  77 */     this.taskName = taskName;
/*     */   }
/*     */ 
/*     */   public String getVmPath()
/*     */   {
/*  92 */     return this.vmPath;
/*     */   }
/*     */ 
/*     */   public void setVmPath(String vmPath) {
/*  96 */     this.vmPath = vmPath;
/*     */   }
/*     */ 
/*     */   public List getOutTrans() {
/* 100 */     return this.outTrans;
/*     */   }
/*     */ 
/*     */   public void setOutTrans(List outTrans) {
/* 104 */     this.outTrans = outTrans;
/*     */   }
/*     */ 
/*     */   public String list()
/*     */   {
/* 114 */     PagingBean pb = getInitPagingBean();
/* 115 */     List tasks = this.flowTaskService.getTaskInfosByUserId(ContextUtil.getCurrentUserId().toString(), pb);
/* 116 */     getRequest().setAttribute("taskList", tasks);
/* 117 */     return "success";
/*     */   }
/*     */ 
/*     */   public String next() {
/* 121 */     this.taskId = getRequest().getParameter("taskId");
/*     */ 
/* 123 */     if (StringUtils.isNotEmpty(this.taskId))
/*     */     {
/* 125 */       TaskImpl task = (TaskImpl)this.jbpmService.getTaskById(this.taskId);
/* 126 */       this.taskName = task.getName();
/*     */ 
/* 128 */       ProcessDefinition pd = this.jbpmService.getProcessDefinitionByTaskId(this.taskId);
/* 129 */       ProDefinition systemProDef = this.proDefinitionService.getByDeployId(pd.getDeploymentId());
/* 130 */       this.processName = systemProDef.getName();
/*     */ 
/* 132 */       this.vmPath = (this.processName + "/" + this.taskName);
/*     */ 
/* 134 */       String viewPath = getSession().getServletContext().getRealPath("") + 
/* 135 */         "/mobile/flow/FlowForm/" + this.vmPath + ".vm";
/* 136 */       if (this.logger.isDebugEnabled()) {
/* 137 */         this.logger.debug("viewPath:" + viewPath);
/*     */       }
/*     */ 
/* 140 */       if (!new File(viewPath).exists()) {
/* 141 */         this.vmPath = "通用/表单";
/*     */       }
/*     */ 
/* 145 */       List<Transition> trans = this.jbpmService.getTransitionsByTaskId(this.taskId.toString());
/* 146 */       for (Transition tran : trans) {
/* 147 */         if (tran.getDestination() != null) {
/* 148 */           this.outTrans.add(new Transform(tran));
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 153 */     return "next";
/*     */   }
/*     */ 
/*     */   public String start()
/*     */   {
/* 172 */     ProDefinition systemProDef = (ProDefinition)this.proDefinitionService.get(this.defId);
/* 173 */     this.taskName = this.jbpmService.getStartNodeName(systemProDef);
/* 174 */     this.processName = systemProDef.getName();
/*     */ 
/* 176 */     this.vmPath = (this.processName + "/" + this.taskName);
/*     */ 
/* 178 */     String viewPath = getSession().getServletContext().getRealPath("") + "/mobile/flow/FlowForm/" + this.vmPath + ".vm";
/* 179 */     if (this.logger.isDebugEnabled()) {
/* 180 */       this.logger.debug("viewPath:" + viewPath);
/*     */     }
/*     */ 
/* 183 */     if (!new File(viewPath).exists()) {
/* 184 */       this.vmPath = "通用/开始";
/*     */     }
/*     */ 
/* 187 */     return "start";
/*     */   }
/*     */ 
/*     */   public String saveStart() {
/* 191 */     FlowRunInfo flowRunInfo = getFlowRunInfo();
/*     */ 
/* 196 */     this.jbpmService.doStartProcess(flowRunInfo);
/* 197 */     return "list";
/*     */   }
/*     */ 
/*     */   protected Map<String, ParamField> constructFieldMap() {
/* 201 */     HttpServletRequest request = getRequest();
/*     */ 
/* 204 */     if (StringUtils.isEmpty(this.taskName)) {
/* 205 */       ProDefinition systemProDef = null;
/* 206 */       if (StringUtils.isNotEmpty(this.taskId)) {
/* 207 */         ProcessDefinition pd = this.jbpmService.getProcessDefinitionByTaskId(this.taskId);
/* 208 */         systemProDef = this.proDefinitionService.getByDeployId(pd.getDeploymentId());
/*     */       } else {
/* 210 */         systemProDef = (ProDefinition)this.proDefinitionService.get(this.defId);
/*     */       }
/* 212 */       this.taskName = this.jbpmService.getStartNodeName(systemProDef);
/* 213 */       this.processName = systemProDef.getName();
/*     */     }
/* 215 */     else if ((StringUtils.isEmpty(this.processName)) && (StringUtils.isNotEmpty(this.taskId))) {
/* 216 */       ProcessDefinition pd = this.jbpmService.getProcessDefinitionByTaskId(this.taskId);
/* 217 */       ProDefinition systemProDef = this.proDefinitionService.getByDeployId(pd.getDeploymentId());
/* 218 */       this.processName = systemProDef.getName();
/*     */     }
/*     */ 
/* 222 */     Map map = ProcessActivityAssistant.constructMobileFieldMap(this.processName, this.taskName);
/*     */ 
/* 224 */     Iterator fieldNames = map.keySet().iterator();
/* 225 */     while (fieldNames.hasNext()) {
/* 226 */       String name = (String)fieldNames.next();
/* 227 */       ParamField pf = (ParamField)map.get(name);
/*     */ 
/* 230 */       pf.setName(pf.getName().replace(".", "_"));
/* 231 */       pf.setValue(request.getParameter(name));
/*     */     }
/* 233 */     return map;
/*     */   }
/*     */ 
/*     */   protected ProcessRun initNewProcessRun()
/*     */   {
/* 241 */     ProDefinition proDefinition = (ProDefinition)this.proDefinitionService.get(this.defId);
/* 242 */     return this.processRunService.getInitNewProcessRun(proDefinition);
/*     */   }
/*     */ 
/*     */   protected ProcessForm initNewProcessForm(ProcessRun processRun) {
/* 246 */     ProcessForm processForm = new ProcessForm();
/* 247 */     processForm.setActivityName(this.taskName);
/* 248 */     processForm.setProcessRun(processRun);
/* 249 */     return processForm;
/*     */   }
/*     */ 
/*     */   protected FlowRunInfo getFlowRunInfo()
/*     */   {
/* 256 */     FlowRunInfo info = new FlowRunInfo(getRequest());
/* 257 */     Map fieldMap = constructFieldMap();
/* 258 */     info.setParamFields(fieldMap);
/* 259 */     return info;
/*     */   }
/*     */ 
/*     */   public String getTaskId() {
/* 263 */     return this.taskId;
/*     */   }
/*     */ 
/*     */   public void setTaskId(String taskId) {
/* 267 */     this.taskId = taskId;
/*     */   }
/*     */ 
/*     */   public Long getDefId() {
/* 271 */     return this.defId;
/*     */   }
/*     */ 
/*     */   public void setDefId(Long defId) {
/* 275 */     this.defId = defId;
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.action.mobile.flow.MobileTaskAction
 * JD-Core Version:    0.6.0
 */