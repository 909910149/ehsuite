/*    */ package com.htsoft.oa.service.flow.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.core.util.ContextUtil;
/*    */ import com.htsoft.oa.dao.flow.ProcessFormDao;
/*    */ import com.htsoft.oa.model.flow.ProcessForm;
/*    */ import com.htsoft.oa.model.system.AppUser;
/*    */ import com.htsoft.oa.service.flow.ProcessFormService;
/*    */ import java.util.Date;
/*    */ import java.util.List;
/*    */ 
/*    */ public class ProcessFormServiceImpl extends BaseServiceImpl<ProcessForm>
/*    */   implements ProcessFormService
/*    */ {
/*    */   private ProcessFormDao dao;
/*    */ 
/*    */   public ProcessFormServiceImpl(ProcessFormDao dao)
/*    */   {
/* 21 */     super(dao);
/* 22 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   public List getByRunId(Long runId)
/*    */   {
/* 31 */     return this.dao.getByRunId(runId);
/*    */   }
/*    */ 
/*    */   public ProcessForm getByRunIdActivityName(Long runId, String activityName)
/*    */   {
/* 41 */     return this.dao.getByRunIdActivityName(runId, activityName);
/*    */   }
/*    */ 
/*    */   public Long getActvityExeTimes(Long runId, String activityName)
/*    */   {
/* 49 */     return this.dao.getActvityExeTimes(runId, activityName);
/*    */   }
/*    */ 
/*    */   public ProcessForm getInitProcessForm()
/*    */   {
/* 65 */     ProcessForm processForm = new ProcessForm();
/*    */ 
/* 67 */     processForm.setCreatetime(new Date());
/* 68 */     AppUser curUser = ContextUtil.getCurrentUser();
/* 69 */     processForm.setCreatorId(curUser.getUserId());
/* 70 */     processForm.setCreatorName(curUser.getFullname());
/*    */ 
/* 72 */     processForm.setStatus(ProcessForm.STATUS_INIT);
/* 73 */     processForm.setDurTimes(0L);
/* 74 */     processForm.setEndtime(new Date());
/*    */ 
/* 76 */     return processForm;
/*    */   }
/*    */ 
/*    */   public ProcessForm getByTaskId(String taskId)
/*    */   {
/* 85 */     return this.dao.getByTaskId(taskId);
/*    */   }
/*    */ 
/*    */   public ProcessForm getByRunIdTaskName(Long runId, String taskName)
/*    */   {
/* 92 */     return this.dao.getByRunIdTaskName(runId, taskName);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.flow.impl.ProcessFormServiceImpl
 * JD-Core Version:    0.6.0
 */