/*     */ package com.htsoft.oa.action.personal;
/*     */ 
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.GsonBuilder;
/*     */ import com.htsoft.core.command.QueryFilter;
/*     */ import com.htsoft.core.jbpm.pv.ParamField;
/*     */ import com.htsoft.core.util.BeanUtil;
/*     */ import com.htsoft.core.util.ContextUtil;
/*     */ import com.htsoft.core.util.JsonUtil;
/*     */ import com.htsoft.core.web.action.BaseAction;
/*     */ import com.htsoft.core.web.paging.PagingBean;
/*     */ import com.htsoft.oa.action.flow.FlowRunInfo;
/*     */ import com.htsoft.oa.action.flow.ProcessActivityAssistant;
/*     */ import com.htsoft.oa.model.flow.ProDefinition;
/*     */ import com.htsoft.oa.model.flow.ProcessForm;
/*     */ import com.htsoft.oa.model.flow.ProcessModule;
/*     */ import com.htsoft.oa.model.flow.ProcessRun;
/*     */ import com.htsoft.oa.model.personal.ErrandsRegister;
/*     */ import com.htsoft.oa.model.system.AppUser;
/*     */ import com.htsoft.oa.service.flow.JbpmService;
/*     */ import com.htsoft.oa.service.flow.ProcessModuleService;
/*     */ import com.htsoft.oa.service.flow.ProcessRunService;
/*     */ import com.htsoft.oa.service.personal.ErrandsRegisterService;
/*     */ import com.htsoft.oa.service.system.AppUserService;
/*     */ import flexjson.JSONSerializer;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.annotation.Resource;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.jbpm.api.task.Task;
/*     */ 
/*     */ public class ErrandsRegisterAction extends BaseAction
/*     */ {
/*     */ 
/*     */   @Resource
/*     */   private ProcessRunService processRunService;
/*     */ 
/*     */   @Resource
/*     */   private JbpmService jbpmService;
/*     */ 
/*     */   @Resource
/*     */   private ErrandsRegisterService errandsRegisterService;
/*     */ 
/*     */   @Resource
/*     */   private ProcessModuleService processModuleService;
/*     */ 
/*     */   @Resource
/*     */   private AppUserService appUserService;
/*     */   private ErrandsRegister errandsRegister;
/*     */   private Long dateId;
/*     */ 
/*     */   public Long getDateId()
/*     */   {
/*  64 */     return this.dateId;
/*     */   }
/*     */ 
/*     */   public void setDateId(Long dateId) {
/*  68 */     this.dateId = dateId;
/*     */   }
/*     */ 
/*     */   public ErrandsRegister getErrandsRegister() {
/*  72 */     return this.errandsRegister;
/*     */   }
/*     */ 
/*     */   public void setErrandsRegister(ErrandsRegister errandsRegister) {
/*  76 */     this.errandsRegister = errandsRegister;
/*     */   }
/*     */ 
/*     */   public String list()
/*     */   {
/*  83 */     Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
/*  84 */     QueryFilter filter = new QueryFilter(getRequest());
/*     */ 
/*  86 */     List<ErrandsRegister> list = this.errandsRegisterService.getAll(filter);
/*  87 */     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
/*  88 */       .append(filter.getPagingBean().getTotalItems()).append(",result:[");
/*     */ 
/*  90 */     JSONSerializer serializer = JsonUtil.getJSONSerializer(new String[] { "startTime", "endTime" });
/*  91 */     for (ErrandsRegister er : list) {
/*  92 */       buff.append(serializer.prettyPrint(true).serialize(er));
/*  93 */       if (er.getRunId() != null)
/*     */       {
/*  95 */         ProcessRun processRun = (ProcessRun)this.processRunService.get(er.getRunId());
/*  96 */         if (processRun.getPiId() != null) {
/*  97 */           buff.deleteCharAt(buff.length() - 1);
/*  98 */           List<Task> curTasks = this.jbpmService.getTasksByPiId(processRun.getPiId());
/*  99 */           buff.append(",tasks:[");
/* 100 */           for (Task task : curTasks) {
/* 101 */             buff.append("{taskId:").append(task.getId()).append(",taskName:").append(gson.toJson(task.getName()));
/* 102 */             if (task.getAssignee() != null) {
/* 103 */               AppUser user = (AppUser)this.appUserService.get(new Long(task.getAssignee()));
/* 104 */               if (user != null) {
/* 105 */                 buff.append(",userId:").append(task.getAssignee()).append(",fullname:").append(gson.toJson(user.getFullname()));
/*     */               }
/*     */             }
/* 108 */             buff.append("},");
/*     */           }
/* 110 */           if (curTasks.size() > 0) {
/* 111 */             buff.deleteCharAt(buff.length() - 1);
/*     */           }
/* 113 */           buff.append("]");
/* 114 */           buff.append("}");
/*     */         }
/*     */       }
/* 117 */       buff.append(",");
/*     */     }
/*     */ 
/* 120 */     if (list.size() > 0) {
/* 121 */       buff.deleteCharAt(buff.length() - 1);
/*     */     }
/* 123 */     buff.append("]}");
/*     */ 
/* 125 */     this.jsonString = buff.toString();
/*     */ 
/* 127 */     return "success";
/*     */   }
/*     */ 
/*     */   public String multiDel()
/*     */   {
/* 135 */     String[] ids = getRequest().getParameterValues("ids");
/* 136 */     if (ids != null) {
/* 137 */       for (String id : ids) {
/* 138 */         this.errandsRegisterService.remove(new Long(id));
/*     */       }
/*     */     }
/*     */ 
/* 142 */     this.jsonString = "{success:true}";
/*     */ 
/* 144 */     return "success";
/*     */   }
/*     */ 
/*     */   public String get()
/*     */   {
/* 152 */     ErrandsRegister errandsRegister = (ErrandsRegister)this.errandsRegisterService.get(this.dateId);
/*     */ 
/* 154 */     Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
/*     */ 
/* 156 */     StringBuffer sb = new StringBuffer("{success:true,data:");
/* 157 */     sb.append(gson.toJson(errandsRegister));
/* 158 */     sb.append("}");
/* 159 */     setJsonString(sb.toString());
/* 160 */     return "success";
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/* 169 */     if (this.errandsRegister.getDateId() != null)
/*     */     {
/* 171 */       ErrandsRegister org = (ErrandsRegister)this.errandsRegisterService.get(this.errandsRegister.getDateId());
/*     */       try
/*     */       {
/* 174 */         BeanUtil.copyNotNullProperties(org, this.errandsRegister);
/* 175 */         this.errandsRegisterService.save(org);
/*     */       } catch (Exception ex) {
/* 177 */         this.logger.error(ex.getMessage());
/*     */       }
/*     */     }
/*     */     else {
/* 181 */       this.errandsRegister.setAppUser(ContextUtil.getCurrentUser());
/* 182 */       this.errandsRegister.setStatus(Short.valueOf((short) 0));
/* 183 */       this.errandsRegisterService.save(this.errandsRegister);
/*     */ 
/* 185 */       Map fieldMap = constructStartFlowMap(this.errandsRegister);
/*     */ 
/* 187 */       ProcessModule processModule = this.processModuleService.getByKey("ERRANDS_REGISTER");
/* 188 */       ProDefinition proDefinition = null;
/* 189 */       if (processModule != null) {
/* 190 */         proDefinition = processModule.getProDefinition();
/*     */       }
/* 192 */       if (proDefinition != null) {
/* 193 */         ProcessRun processRun = this.processRunService.getInitNewProcessRun(proDefinition);
/*     */ 
/* 195 */         ProcessForm processForm = new ProcessForm();
/* 196 */         processForm.setActivityName("开始");
/* 197 */         processForm.setProcessRun(processRun);
/*     */ 
/* 200 */         FlowRunInfo runInfo = new FlowRunInfo();
/* 201 */         runInfo.setParamFields(fieldMap);
/* 202 */         runInfo.setStartFlow(true);
/* 203 */         runInfo.setDefId(proDefinition.getDefId().toString());
/* 204 */         runInfo.getVariables().put("dateId", this.errandsRegister.getDateId());
/*     */ 
/* 206 */         runInfo.setdAssignId(this.errandsRegister.getApprovalId().toString());
/*     */ 
/* 209 */         processRun = this.jbpmService.doStartProcess(runInfo);
/*     */ 
/* 212 */         this.errandsRegister.setRunId(processRun.getRunId());
/* 213 */         this.errandsRegisterService.save(this.errandsRegister);
/*     */       }
/*     */       else {
/* 216 */         this.logger.error("请假流程没有定义！");
/*     */       }
/*     */     }
/*     */ 
/* 220 */     setJsonString("{success:true}");
/* 221 */     return "success";
/*     */   }
/*     */ 
/*     */   protected Map<String, ParamField> constructStartFlowMap(ErrandsRegister register)
/*     */   {
/* 227 */     String activityName = "开始";
/* 228 */     String processName = "请假外出";
/*     */ 
/* 230 */     Map map = ProcessActivityAssistant.constructFieldMap(processName, activityName);
/*     */ 
/* 232 */     ParamField pfDateId = (ParamField)map.get("dateId");
/*     */ 
/* 234 */     if (pfDateId != null) {
/* 235 */       pfDateId.setValue(register.getDateId().toString());
/*     */     }
/*     */ 
/* 238 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/* 239 */     ParamField pfOption = (ParamField)map.get("reqDesc");
/* 240 */     if (pfOption != null) {
/* 241 */       pfOption.setValue(register.getDescp());
/*     */     }
/*     */ 
/* 244 */     ParamField pfStartTime = (ParamField)map.get("startTime");
/* 245 */     if (pfStartTime != null) {
/* 246 */       pfStartTime.setValue(sdf.format(register.getStartTime()));
/*     */     }
/*     */ 
/* 249 */     ParamField pfEndTime = (ParamField)map.get("endTime");
/* 250 */     if (pfEndTime != null) {
/* 251 */       pfEndTime.setValue(sdf.format(register.getEndTime()));
/*     */     }
/*     */ 
/* 254 */     ParamField pfApprovalName = (ParamField)map.get("approvalName");
/* 255 */     if (pfApprovalName != null) {
/* 256 */       pfApprovalName.setValue(register.getApprovalName());
/*     */     }
/*     */ 
/* 259 */     return map;
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.action.personal.ErrandsRegisterAction
 * JD-Core Version:    0.6.0
 */