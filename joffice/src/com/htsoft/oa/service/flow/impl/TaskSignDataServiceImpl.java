/*    */ package com.htsoft.oa.service.flow.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.core.util.ContextUtil;
/*    */ import com.htsoft.oa.dao.flow.TaskSignDataDao;
/*    */ import com.htsoft.oa.model.flow.TaskSignData;
/*    */ import com.htsoft.oa.model.system.AppUser;
/*    */ import com.htsoft.oa.service.flow.TaskSignDataService;
/*    */ import java.util.Date;
/*    */ import java.util.List;
/*    */ 
/*    */ public class TaskSignDataServiceImpl extends BaseServiceImpl<TaskSignData>
/*    */   implements TaskSignDataService
/*    */ {
/*    */   private TaskSignDataDao dao;
/*    */ 
/*    */   public TaskSignDataServiceImpl(TaskSignDataDao dao)
/*    */   {
/* 21 */     super(dao);
/* 22 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   public void addVote(String taskId, Short isAgree)
/*    */   {
/* 30 */     AppUser curUser = ContextUtil.getCurrentUser();
/*    */ 
/* 32 */     TaskSignData data = new TaskSignData();
/*    */ 
/* 34 */     data.setTaskId(taskId);
/* 35 */     data.setIsAgree(isAgree);
/* 36 */     data.setVoteId(curUser.getUserId());
/* 37 */     data.setVoteName(curUser.getFullname());
/* 38 */     data.setVoteTime(new Date());
/*    */ 
/* 40 */     save(data);
/*    */   }
/*    */ 
/*    */   public Long getVoteCounts(String taskId, Short isAgree)
/*    */   {
/* 48 */     return this.dao.getVoteCounts(taskId, isAgree);
/*    */   }
/*    */ 
/*    */   public List<TaskSignData> getByTaskId(String taskId)
/*    */   {
/* 57 */     return this.dao.getByTaskId(taskId);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.flow.impl.TaskSignDataServiceImpl
 * JD-Core Version:    0.6.0
 */