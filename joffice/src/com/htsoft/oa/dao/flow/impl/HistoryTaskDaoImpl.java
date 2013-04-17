/*    */ package com.htsoft.oa.dao.flow.impl;
/*    */ 
/*    */ import com.htsoft.core.dao.impl.BaseDaoImpl;
/*    */ import com.htsoft.oa.dao.flow.HistoryTaskDao;
/*    */ import java.util.List;
/*    */ import org.jbpm.pvm.internal.history.model.HistoryTaskImpl;
/*    */ import org.jbpm.pvm.internal.history.model.HistoryTaskInstanceImpl;
/*    */ 
/*    */ public class HistoryTaskDaoImpl extends BaseDaoImpl<HistoryTaskInstanceImpl>
/*    */   implements HistoryTaskDao
/*    */ {
/*    */   public HistoryTaskDaoImpl()
/*    */   {
/* 13 */     super(HistoryTaskImpl.class);
/*    */   }
/*    */ 
/*    */   public List<HistoryTaskInstanceImpl> getByPiIdAssigneeOutcome(String piId, String assignee, String activityName, String outcome) {
/* 17 */     String hql = "from HistoryTaskInstanceImpl hti where hti.executionId=? and hti.historyTask.assignee=? and hti.activityName=? and hti.transitionName=?";
/* 18 */     return findByHql(hql, new Object[] { piId, assignee, activityName, outcome });
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.flow.impl.HistoryTaskDaoImpl
 * JD-Core Version:    0.6.0
 */