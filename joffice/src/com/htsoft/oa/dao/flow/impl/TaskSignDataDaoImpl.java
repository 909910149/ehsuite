/*    */ package com.htsoft.oa.dao.flow.impl;
/*    */ 
/*    */ import com.htsoft.core.dao.impl.BaseDaoImpl;
/*    */ import com.htsoft.oa.dao.flow.TaskSignDataDao;
/*    */ import com.htsoft.oa.model.flow.TaskSignData;
/*    */ import java.util.List;
/*    */ 
/*    */ public class TaskSignDataDaoImpl extends BaseDaoImpl<TaskSignData>
/*    */   implements TaskSignDataDao
/*    */ {
/*    */   public TaskSignDataDaoImpl()
/*    */   {
/* 15 */     super(TaskSignData.class);
/*    */   }
/*    */ 
/*    */   public Long getVoteCounts(String taskId, Short isAgree)
/*    */   {
/* 23 */     String hql = "select count(dataId) from TaskSignData tsd where tsd.taskId=? and tsd.isAgree=?";
/* 24 */     Object count = findUnique(hql, new Object[] { taskId, isAgree });
/* 25 */     return new Long(count.toString());
/*    */   }
/*    */ 
/*    */   public List<TaskSignData> getByTaskId(String taskId)
/*    */   {
/* 34 */     String hql = "from TaskSignData tsd where tsd.taskId=?";
/* 35 */     return findByHql(hql, new Object[] { taskId });
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.flow.impl.TaskSignDataDaoImpl
 * JD-Core Version:    0.6.0
 */