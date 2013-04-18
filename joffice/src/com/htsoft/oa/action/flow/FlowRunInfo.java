 package com.htsoft.oa.action.flow;
 
 import com.htsoft.core.jbpm.pv.ParamField;
 import com.htsoft.oa.model.flow.ProcessRun;
 import java.io.Serializable;
 import java.util.HashMap;
 import java.util.Map;
 import javax.servlet.http.HttpServletRequest;
 import org.apache.commons.lang.StringUtils;
 
 public class FlowRunInfo
 {
   public static final String ENTITY_PK = "entityId";
   public static final String ENTITY_NAME = "entityName";
   public static final String PROCESS_RUNID = "runId";
   public static final String START_USER_ID = "startUserId";
   private boolean useTemplate = false;
   private ProcessRun processRun;
   private Map variables = new HashMap();
 
   private Map<String, ParamField> paramFields = new HashMap();
 
   private boolean isStartFlow = false;
   private HttpServletRequest request;
   private String processName = "通用";
 
   private String activityName = "开始";
 
   private String destName = null;
   private String transitionName;
   private String preHandler;
   private String afterHandler;
   private String taskId;
   private String piId;
   private String defId;
   private String formDefId;
   private Serializable entityPK;
   private String entityName;
   private String flowSubject;
   private boolean back = false;
 
   private String comments = null;
 
   private Short signVoteType = null;
 
   private boolean sendMsg = false;
 
   private boolean sendMail = false;
 
   public FlowRunInfo(HttpServletRequest request)
   {
     this.request = request;
 
     String signUserIds = request.getParameter("signUserIds");
     if (StringUtils.isNotEmpty(signUserIds)) {
       this.variables.put("signUserIds", signUserIds);
     }
 
     String flowAssignId = request.getParameter("flowAssignId");
     if (StringUtils.isNotEmpty(flowAssignId)) {
       this.variables.put("flowAssignId", flowAssignId);
     }
 
     String pSignVoteType = request.getParameter("signVoteType");
     if (StringUtils.isNotEmpty(pSignVoteType)) {
       this.variables.put("signVoteType", new Short(pSignVoteType));
     }
 
     if ("true".equals(request.getParameter("startFlow"))) {
       this.isStartFlow = true;
       this.defId = request.getParameter("defId");
     }
 
     String pTaskId = request.getParameter("taskId");
     if (StringUtils.isNotEmpty(pTaskId)) {
       this.taskId = pTaskId;
     }
 
     String pFormDefId = request.getParameter("formDefId");
     if (StringUtils.isNotEmpty(pFormDefId)) {
       this.formDefId = pFormDefId;
     }
 
     String pPiId = request.getParameter("piId");
 
     if (StringUtils.isNotEmpty(pPiId)) {
       this.piId = pPiId;
     }
 
     String pActivityName = request.getParameter("activityName");
     if (StringUtils.isNotEmpty(pActivityName)) {
       this.activityName = pActivityName;
     }
 
     String pTaskName = request.getParameter("taskName");
     if (StringUtils.isNotEmpty(pTaskName)) {
       this.activityName = pTaskName;
     }
 
     String pDestName = request.getParameter("destName");
 
     if (StringUtils.isNotEmpty(pDestName)) {
       this.destName = pDestName;
     }
 
     String pSignName = request.getParameter("signalName");
     if (StringUtils.isNotEmpty(pSignName)) {
       this.transitionName = pSignName;
     }
 
     String pBack = request.getParameter("back");
     if ("true".equals(pBack)) {
       this.back = true;
     }
 
     String pComments = request.getParameter("comments");
     if (StringUtils.isNotEmpty(pComments)) {
       this.comments = pComments;
     }
 
     String pHandler = request.getParameter("preHandler");
     if (StringUtils.isNotEmpty(pHandler)) {
       this.preHandler = pHandler;
     }
 
     String aHandler = request.getParameter("afterHandler");
     if (StringUtils.isNotEmpty(aHandler)) {
       this.afterHandler = aHandler;
     }
 
     String pSendMsg = request.getParameter("sendMsg");
     if ("true".equals(pSendMsg)) {
       this.sendMsg = true;
     }
 
     String pSendMail = request.getParameter("sendMail");
     if ("true".equals(pSendMail)) {
       this.sendMail = true;
     }
 
     String pUseTemplate = request.getParameter("useTemplate");
     if ("true".equals(pUseTemplate))
       this.useTemplate = true;
   }
 
   public FlowRunInfo()
   {
   }
 
   public Map getVariables()
   {
     return this.variables;
   }
 
   public void setVariables(Map variables) {
     this.variables = variables;
   }
 
   public boolean isStartFlow() {
     return this.isStartFlow;
   }
 
   public void setStartFlow(boolean isStartFlow) {
     this.isStartFlow = isStartFlow;
   }
 
   public HttpServletRequest getRequest() {
     return this.request;
   }
 
   public void setRequest(HttpServletRequest request) {
     this.request = request;
   }
 
   public String getProcessName() {
     return this.processName;
   }
 
   public void setProcessName(String processName) {
     this.processName = processName;
   }
 
   public String getActivityName() {
     return this.activityName;
   }
 
   public void setActivityName(String activityName) {
     this.activityName = activityName;
   }
 
   public Map<String, ParamField> getParamFields() {
     return this.paramFields;
   }
 
   public void setParamFields(Map<String, ParamField> paramFields) {
     this.paramFields = paramFields;
   }
 
   public String getTransitionName() {
     return this.transitionName;
   }
 
   public void setTransitionName(String transitionName) {
     this.transitionName = transitionName;
   }
 
   public String getTaskId() {
     return this.taskId;
   }
 
   public void setTaskId(String taskId) {
     this.taskId = taskId;
   }
 
   public String getPiId() {
     return this.piId;
   }
 
   public void setPiId(String piId) {
     this.piId = piId;
   }
 
   public String getDestName() {
     return this.destName;
   }
 
   public void setDestName(String destName) {
     this.destName = destName;
   }
 
   public void setdAssignId(String assignId)
   {
     this.variables.put("flowAssignId", assignId);
   }
 
   public void setMultipleTask(String userIds)
   {
     this.variables.put("signUserIds", userIds);
   }
 
   public String getPreHandler() {
     return this.preHandler;
   }
 
   public void setPreHandler(String preHandler) {
     this.preHandler = preHandler;
   }
 
   public String getAfterHandler() {
     return this.afterHandler;
   }
 
   public void setAfterHandler(String afterHandler) {
     this.afterHandler = afterHandler;
   }
 
   public String getDefId() {
     return this.defId;
   }
 
   public void setDefId(String defId) {
     this.defId = defId;
   }
 
   public boolean isBack() {
     return this.back;
   }
 
   public void setBack(boolean back) {
     this.back = back;
   }
 
   public String getComments() {
     return this.comments;
   }
 
   public void setComments(String comments) {
     this.comments = comments;
   }
 
   public String getFormDefId() {
     return this.formDefId;
   }
 
   public void setFormDefId(String formDefId) {
     this.formDefId = formDefId;
   }
 
   public Serializable getEntityPK() {
     return this.entityPK;
   }
 
   public void setEntityPK(Serializable entityPK) {
     this.entityPK = entityPK;
   }
 
   public String getEntityName() {
     return this.entityName;
   }
 
   public void setEntityName(String entityName) {
     this.entityName = entityName;
   }
 
   public String getFlowSubject() {
     return this.flowSubject;
   }
 
   public void setFlowSubject(String flowSubject) {
     this.flowSubject = flowSubject;
   }
 
   public Short getSignVoteType() {
     return this.signVoteType;
   }
 
   public void setSignVoteType(Short signVoteType) {
     this.signVoteType = signVoteType;
   }
 
   public boolean isSendMsg() {
     return this.sendMsg;
   }
 
   public void setSendMsg(boolean sendMsg) {
     this.sendMsg = sendMsg;
   }
 
   public boolean isSendMail() {
     return this.sendMail;
   }
 
   public void setSendMail(boolean sendMail) {
     this.sendMail = sendMail;
   }
 
   public ProcessRun getProcessRun() {
     return this.processRun;
   }
 
   public void setProcessRun(ProcessRun processRun) {
     this.processRun = processRun;
   }
 
   public boolean isUseTemplate() {
     return this.useTemplate;
   }
 
   public void setUseTemplate(boolean useTemplate) {
     this.useTemplate = useTemplate;
   }
 }

