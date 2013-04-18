/*     */ package com.htsoft.oa.service.flow.impl;
/*     */ 
/*     */ import com.htsoft.core.jms.MailMessageProducer;
/*     */ import com.htsoft.core.jms.MobileMessageProducer;
/*     */ import com.htsoft.core.model.DynaModel;
/*     */ import com.htsoft.core.model.MailModel;
/*     */ import com.htsoft.core.service.DynamicService;
/*     */ import com.htsoft.core.util.AppUtil;
/*     */ import com.htsoft.core.util.BeanUtil;
/*     */ import com.htsoft.core.util.StringUtil;
/*     */ import com.htsoft.oa.action.flow.FlowRunInfo;
/*     */ import com.htsoft.oa.model.communicate.SmsMobile;
/*     */ import com.htsoft.oa.model.flow.ProDefinition;
/*     */ import com.htsoft.oa.model.flow.ProcessRun;
/*     */ import com.htsoft.oa.model.system.AppUser;
/*     */ import com.htsoft.oa.service.communicate.SmsMobileService;
/*     */ import com.htsoft.oa.service.flow.FlowFormService;
/*     */ import com.htsoft.oa.service.flow.JbpmService;
/*     */ import com.htsoft.oa.service.flow.ProDefinitionService;
/*     */ import com.htsoft.oa.service.flow.ProcessRunService;
/*     */ import com.htsoft.oa.service.flow.ProcessService;
/*     */ import com.htsoft.oa.service.flow.RunDataService;
/*     */ import com.htsoft.oa.service.system.AppUserService;
/*     */ import java.lang.reflect.Method;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Resource;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.jbpm.api.task.Task;
/*     */ import org.jbpm.pvm.internal.task.ParticipationImpl;
/*     */ import org.jbpm.pvm.internal.task.TaskImpl;
/*     */ 
/*     */ public class ProcessServiceImpl
/*     */   implements ProcessService
/*     */ {
/*  45 */   private final Log logger = LogFactory.getLog(ProcessServiceImpl.class);
/*     */ 
/*     */   @Resource
/*     */   private ProcessRunService processRunService;
/*     */ 
/*     */   @Resource
/*     */   ProDefinitionService proDefinitionService;
/*     */ 
/*     */   @Resource
/*     */   private JbpmService jbpmService;
/*     */ 
/*     */   @Resource
/*     */   private FlowFormService flowFormService;
/*     */ 
/*     */   @Resource
/*     */   private AppUserService appUserService;
/*     */ 
/*     */   @Resource
/*     */   private MailMessageProducer mailMessageProducer;
/*     */ 
/*     */   @Resource
/*     */   private MobileMessageProducer mobileMessageProducer;
/*     */ 
/*     */   @Resource
/*     */   private SmsMobileService smsMobileService;
/*     */ 
/*     */   @Resource
/*     */   private RunDataService runDataService;
/*     */ 
/*  80 */   public ProcessRun doStartFlow(HttpServletRequest request) throws Exception { FlowRunInfo startInfo = getFlowRunInfo(request);
/*  81 */     ProcessRun processRun = null;
/*     */ 
/*  83 */     String useTemplate = request.getParameter("useTemplate");
/*     */ 
/*  86 */     int result = invokeHandler(startInfo, "PRE");
/*     */ 
/*  88 */     if ((result == -1) || (result >= 1)) {
/*  89 */       DynaModel entity = null;
/*  90 */       if (!"true".equals(useTemplate))
/*     */       {
/*  92 */         entity = this.flowFormService.doSaveData(startInfo);
/*     */ 
/*  94 */         startInfo.getVariables().putAll(entity.getDatas());
/*     */       }
/*     */ 
/*  97 */       processRun = this.jbpmService.doStartProcess(startInfo);
/*     */ 
/*  99 */       if ("true".equals(useTemplate)) {
/* 100 */         startInfo.getVariables().putAll(BeanUtil.getMapFromRequest(request));
/*     */       }
/*     */ 
/* 103 */       this.runDataService.saveFlowVars(processRun.getRunId(), startInfo.getVariables());
/*     */ 
/* 106 */       startInfo.setProcessRun(processRun);
/*     */ 
/* 109 */       notice(processRun, startInfo);
/*     */ 
/* 111 */       if (entity != null) {
/*     */         try
/*     */         {
/* 114 */           entity.set("runId", processRun.getRunId());
/* 115 */           DynamicService service = BeanUtil.getDynamicServiceBean((String)entity.get("entityName"));
/* 116 */           service.save(entity.getDatas());
/*     */         } catch (Exception ex) {
/* 118 */           ex.printStackTrace();
/* 119 */           this.logger.debug("error:" + ex.getMessage());
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 124 */     invokeHandler(startInfo, "AFT");
/*     */ 
/* 126 */     return processRun;
/*     */   }
/*     */ 
/*     */   public ProcessRun doNextFlow(HttpServletRequest request)
/*     */     throws Exception
/*     */   {
/* 136 */     String useTemplate = request.getParameter("useTemplate");
/*     */ 
/* 138 */     FlowRunInfo nextInfo = getFlowRunInfo(request);
/*     */ 
/* 140 */     ProcessRun processRun = null;
/*     */ 
/* 142 */     int result = invokeHandler(nextInfo, "PRE");
/*     */ 
/* 144 */     if ((result == -1) || (result >= 1)) {
/* 145 */       if (!"true".equals(useTemplate))
/*     */       {
/* 147 */         DynaModel entity = this.flowFormService.doSaveData(nextInfo);
/*     */ 
/* 149 */         nextInfo.getVariables().putAll(entity.getDatas());
/*     */       }
/*     */ 
/* 153 */       processRun = this.jbpmService.doNextStep(nextInfo);
/*     */ 
/* 155 */       if ("true".equals(useTemplate)) {
/* 156 */         nextInfo.getVariables().putAll(BeanUtil.getMapFromRequest(request));
/*     */       }
/*     */ 
/* 159 */       this.runDataService.saveFlowVars(processRun.getRunId(), nextInfo.getVariables());
/*     */ 
/* 161 */       nextInfo.setProcessRun(processRun);
/*     */ 
/* 163 */       notice(processRun, nextInfo);
/*     */     }
/*     */ 
/* 166 */     invokeHandler(nextInfo, "AFT");
/*     */ 
/* 168 */     return processRun;
/*     */   }
/*     */ 
/*     */   private void notice(ProcessRun processRun, FlowRunInfo flowInfo)
/*     */   {
/* 176 */     if (processRun.getPiId() == null) return;
/* 177 */     List<Task> taskList = this.jbpmService.getTasksByPiId(processRun.getPiId());
/*     */ 
/* 179 */     for (Task task : taskList) {
/* 180 */       TaskImpl taskImpl = (TaskImpl)task;
/* 181 */       if (taskImpl.getAssignee() == null) {
/* 182 */         Iterator partIt = taskImpl.getAllParticipants().iterator();
/* 183 */         while (partIt.hasNext()) {
/* 184 */           ParticipationImpl part = (ParticipationImpl)partIt.next();
/* 185 */           if ((part.getGroupId() != null) && (StringUtil.isNumeric(part.getGroupId())))
/*     */           {
/* 187 */             List<AppUser> appUserList = this.appUserService.findByRoleId(new Long(part.getGroupId()));
/* 188 */             for (AppUser appUser : appUserList)
/* 189 */               sendMailNotice(processRun.getSubject(), taskImpl, appUser, flowInfo);
/*     */           }
/* 191 */           else if ((part.getUserId() != null) && (StringUtil.isNumeric(part.getUserId()))) {
/* 192 */             AppUser appUser = (AppUser)this.appUserService.get(new Long(part.getUserId()));
/* 193 */             sendMailNotice(processRun.getSubject(), taskImpl, appUser, flowInfo);
/*     */           }
/*     */         }
/* 196 */       } else if (StringUtil.isNumeric(taskImpl.getAssignee())) {
/* 197 */         AppUser appUser = (AppUser)this.appUserService.get(new Long(taskImpl.getAssignee()));
/* 198 */         sendMailNotice(processRun.getSubject(), taskImpl, appUser, flowInfo);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void sendMailNotice(String piSubject, Task task, AppUser appUser, FlowRunInfo flowRunInfo)
/*     */   {
/* 210 */     Date curDate = new Date();
/* 211 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
/* 212 */     String curDateStr = sdf.format(curDate);
/* 213 */     if (flowRunInfo.isSendMail())
/*     */     {
/* 215 */       if (appUser.getEmail() != null) {
/* 216 */         if (this.logger.isDebugEnabled()) {
/* 217 */           this.logger.info("Notice " + appUser.getFullname() + " by mail:" + appUser.getEmail());
/*     */         }
/*     */ 
/* 220 */         String tempPath = "mail/flowMail.vm";
/* 221 */         Map model = new HashMap();
/* 222 */         model.put("curDateStr", curDateStr);
/* 223 */         model.put("appUser", appUser);
/* 224 */         model.put("task", task);
/* 225 */         String subject = "来自" + AppUtil.getCompanyName() + "办公系统的待办任务(" + piSubject + "--" + task.getName() + ")提醒";
/*     */ 
/* 227 */         MailModel mailModel = new MailModel();
/* 228 */         mailModel.setMailTemplate(tempPath);
/* 229 */         mailModel.setTo(appUser.getEmail());
/* 230 */         mailModel.setSubject(subject);
/* 231 */         mailModel.setMailData(model);
/*     */ 
/* 233 */         this.mailMessageProducer.send(mailModel);
/*     */       }
/*     */     }
/* 236 */     if (flowRunInfo.isSendMsg())
/*     */     {
/* 238 */       if (appUser.getMobile() != null) {
/* 239 */         if (this.logger.isDebugEnabled()) {
/* 240 */           this.logger.info("Notice " + appUser.getFullname() + " by mobile:" + appUser.getMobile());
/*     */         }
/*     */ 
/* 243 */         if (appUser.getMobile() != null) {
/* 244 */           String content = AppUtil.getCompanyName() + "办公系统于" + curDateStr + "产生了一项待办事项(" + piSubject + "--" + task.getName() + ")，请您在规定时间内完成审批~";
/* 245 */           SmsMobile smsMobile = new SmsMobile();
/* 246 */           smsMobile.setPhoneNumber(appUser.getMobile());
/* 247 */           smsMobile.setSmsContent(content);
/* 248 */           smsMobile.setSendTime(new Date());
/* 249 */           smsMobile.setUserId(Long.valueOf(-1L));
/* 250 */           smsMobile.setUserName("system user");
/* 251 */           smsMobile.setStatus(SmsMobile.STATUS_NOT_SENDED);
/*     */ 
/* 253 */           this.smsMobileService.save(smsMobile);
/*     */ 
/* 255 */           this.mobileMessageProducer.send(smsMobile);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public ProcessRun getInitNewProcessRun(HttpServletRequest request)
/*     */   {
/* 266 */     String defId = request.getParameter("defId");
/* 267 */     ProDefinition proDefinition = (ProDefinition)this.proDefinitionService.get(new Long(defId));
/*     */ 
/* 269 */     return this.processRunService.getInitNewProcessRun(proDefinition);
/*     */   }
/*     */ 
/*     */   protected FlowRunInfo getFlowRunInfo(HttpServletRequest request)
/*     */   {
/* 276 */     FlowRunInfo info = new FlowRunInfo(request);
/*     */ 
/* 279 */     return info;
/*     */   }
/*     */ 
/*     */   protected ProDefinition getProDefinition(HttpServletRequest request)
/*     */   {
/* 287 */     ProDefinition proDefinition = null;
/* 288 */     String defId = request.getParameter("defId");
/* 289 */     if (defId != null) {
/* 290 */       proDefinition = (ProDefinition)this.proDefinitionService.get(new Long(defId));
/*     */     } else {
/* 292 */       String taskId = request.getParameter("taskId");
/* 293 */       ProcessRun processRun = this.processRunService.getByTaskId(taskId.toString());
/* 294 */       proDefinition = processRun.getProDefinition();
/*     */     }
/* 296 */     return proDefinition;
/*     */   }
/*     */ 
/*     */   public int invokeHandler(FlowRunInfo flowRunInfo, String preAfterMethodFlag)
/*     */     throws Exception
/*     */   {
/* 306 */     String handler = null;
/*     */ 
/* 308 */     if ("PRE".equals(preAfterMethodFlag))
/* 309 */       handler = flowRunInfo.getPreHandler();
/*     */     else {
/* 311 */       handler = flowRunInfo.getAfterHandler();
/*     */     }
/*     */ 
/* 314 */     if (handler == null) return -1;
/*     */ 
/* 316 */     String[] beanMethods = handler.split("[.]");
/* 317 */     if (beanMethods != null) {
/* 318 */       String beanId = beanMethods[0];
/* 319 */       String method = beanMethods[1];
/*     */ 
/* 321 */       Object serviceBean = AppUtil.getBean(beanId);
/* 322 */       if (serviceBean != null) {
/* 323 */         Method invokeMethod = serviceBean.getClass().getDeclaredMethod(method, new Class[] { FlowRunInfo.class });
/* 324 */         return ((Integer)invokeMethod.invoke(serviceBean, new Object[] { flowRunInfo })).intValue();
/*     */       }
/*     */     }
/* 327 */     this.logger.error("error invoke handler " + handler);
/* 328 */     return 0;
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.flow.impl.ProcessServiceImpl
 * JD-Core Version:    0.6.0
 */