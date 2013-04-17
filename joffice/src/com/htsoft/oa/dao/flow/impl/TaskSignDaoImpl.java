/*    */ package com.htsoft.oa.dao.flow.impl;
/*    */ 
/*    */ import com.htsoft.core.dao.impl.BaseDaoImpl;
/*    */ import com.htsoft.oa.dao.flow.TaskSignDao;
/*    */ import com.htsoft.oa.model.flow.TaskSign;
/*    */ 
/*    */ public class TaskSignDaoImpl extends BaseDaoImpl<TaskSign>
/*    */   implements TaskSignDao
/*    */ {
/*    */   public TaskSignDaoImpl()
/*    */   {
/* 25 */     super(TaskSign.class);
/*    */   }
/*    */ 
/*    */   public TaskSign findByAssignId(Long assignId)
/*    */   {
/* 33 */     String hql = "from TaskSign ts where ts.proUserAssign.assignId=? ";
/* 34 */     return (TaskSign)findUnique(hql, new Object[] { assignId });
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.flow.impl.TaskSignDaoImpl
 * JD-Core Version:    0.6.0
 */