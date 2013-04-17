/*    */ package com.htsoft.oa.service.flow.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.flow.TaskSignDao;
/*    */ import com.htsoft.oa.model.flow.TaskSign;
/*    */ import com.htsoft.oa.service.flow.TaskSignService;
/*    */ 
/*    */ public class TaskSignServiceImpl extends BaseServiceImpl<TaskSign>
/*    */   implements TaskSignService
/*    */ {
/*    */   private TaskSignDao dao;
/*    */ 
/*    */   public TaskSignServiceImpl(TaskSignDao dao)
/*    */   {
/* 24 */     super(dao);
/* 25 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   public TaskSign findByAssignId(Long assignId)
/*    */   {
/* 33 */     return this.dao.findByAssignId(assignId);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.flow.impl.TaskSignServiceImpl
 * JD-Core Version:    0.6.0
 */