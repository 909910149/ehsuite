/*     */ package com.htsoft.oa.service.flow.impl;
/*     */ 
/*     */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*     */ import com.htsoft.core.util.ContextUtil;
/*     */ import com.htsoft.core.web.paging.PagingBean;
/*     */ import com.htsoft.oa.action.flow.FlowRunInfo;
/*     */ import com.htsoft.oa.dao.flow.ProcessFormDao;
/*     */ import com.htsoft.oa.dao.flow.ProcessRunDao;
/*     */ import com.htsoft.oa.model.flow.ProDefinition;
/*     */ import com.htsoft.oa.model.flow.ProcessForm;
/*     */ import com.htsoft.oa.model.flow.ProcessRun;
/*     */ import com.htsoft.oa.model.system.AppUser;
/*     */ import com.htsoft.oa.service.flow.JbpmService;
/*     */ import com.htsoft.oa.service.flow.ProDefinitionService;
/*     */ import com.htsoft.oa.service.flow.ProcessRunService;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import javax.annotation.Resource;
/*     */ import org.jbpm.api.ProcessInstance;
/*     */ 
/*     */ public class ProcessRunServiceImpl extends BaseServiceImpl<ProcessRun>
/*     */   implements ProcessRunService
/*     */ {
/*     */   private ProcessRunDao dao;
/*     */ 
/*     */   @Resource
/*     */   private ProcessFormDao processFormDao;
/*     */ 
/*     */   @Resource
/*     */   private ProDefinitionService proDefinitionService;
/*     */ 
/*     */   @Resource
/*     */   private JbpmService jbpmService;
/*     */ 
/*     */   public ProcessRunServiceImpl(ProcessRunDao dao)
/*     */   {
/*  44 */     super(dao);
/*  45 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */   public ProcessRun getByExeId(String exeId)
/*     */   {
/*  54 */     ProcessInstance pi = this.jbpmService.getProcessInstanceByExeId(exeId);
/*  55 */     if (pi != null) {
/*  56 */       return getByPiId(pi.getId());
/*     */     }
/*  58 */     return null;
/*     */   }
/*     */ 
/*     */   public ProcessRun getByTaskId(String taskId) {
/*  62 */     ProcessInstance pi = this.jbpmService.getProcessInstanceByTaskId(taskId);
/*  63 */     if (pi != null) {
/*  64 */       return getByPiId(pi.getId());
/*     */     }
/*  66 */     return null;
/*     */   }
/*     */ 
/*     */   public ProcessRun getByPiId(String piId) {
/*  70 */     return this.dao.getByPiId(piId);
/*     */   }
/*     */ 
/*     */   public ProcessRun getInitNewProcessRun(ProDefinition proDefinition)
/*     */   {
/*  79 */     ProcessRun processRun = new ProcessRun();
/*  80 */     AppUser curUser = ContextUtil.getCurrentUser();
/*     */ 
/*  82 */     Date curDate = new Date();
/*  83 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
/*     */ 
/*  85 */     processRun.setSubject(proDefinition.getName() + sdf.format(curDate));
/*  86 */     processRun.setCreator(curUser.getFullname());
/*  87 */     processRun.setAppUser(curUser);
/*  88 */     processRun.setCreatetime(curDate);
/*  89 */     processRun.setProDefinition(proDefinition);
/*     */ 
/*  91 */     return processRun;
/*     */   }
/*     */ 
/*     */   public ProcessRun getInitFromFlowRunInfo(FlowRunInfo runInfo)
/*     */   {
/* 100 */     ProDefinition proDefinition = (ProDefinition)this.proDefinitionService.get(new Long(runInfo.getDefId()));
/* 101 */     ProcessRun processRun = getInitNewProcessRun(proDefinition);
/* 102 */     if (runInfo.getFlowSubject() != null) {
/* 103 */       processRun.setSubject(runInfo.getFlowSubject());
/*     */     }
/* 105 */     return processRun;
/*     */   }
/*     */ 
/*     */   public void remove(Long runId)
/*     */   {
/* 170 */     ProcessRun processRun = (ProcessRun)this.dao.get(runId);
/* 171 */     if (ProcessRun.RUN_STATUS_INIT.equals(processRun.getRunStatus())) {
/* 172 */       List<ProcessForm> processForms = this.processFormDao.getByRunId(runId);
/* 173 */       for (ProcessForm processForm : processForms) {
/* 174 */         this.processFormDao.remove(processForm);
/*     */       }
/*     */     }
/* 177 */     this.dao.remove(processRun);
/*     */   }
/*     */ 
/*     */   public void removeByDefId(Long defId)
/*     */   {
/* 186 */     List processRunList = this.dao.getByDefId(defId, new PagingBean(0, 25));
/* 187 */     for (int i = 0; i < processRunList.size(); i++) {
/* 188 */       this.dao.remove((ProcessRun)processRunList.get(i));
/*     */     }
/*     */ 
/* 191 */     if (processRunList.size() == 25)
/* 192 */       removeByDefId(defId);
/*     */   }
/*     */ 
/*     */   public List<ProcessRun> getByUserIdSubject(Long userId, String subject, PagingBean pb)
/*     */   {
/* 204 */     return this.dao.getByUserIdSubject(userId, subject, pb);
/*     */   }
/*     */ 
/*     */   public Boolean checkRun(Long defId)
/*     */   {
/* 212 */     return Boolean.valueOf(this.dao.checkRun(defId));
/*     */   }
/*     */ 
/*     */   public Integer countRunningProcess(Long defId)
/*     */   {
/* 217 */     List list = this.dao.getProcessRunning(defId);
/* 218 */     if (list.size() > 0) {
/* 219 */       return Integer.valueOf(list.size());
/*     */     }
/* 221 */     return Integer.valueOf(0);
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.flow.impl.ProcessRunServiceImpl
 * JD-Core Version:    0.6.0
 */