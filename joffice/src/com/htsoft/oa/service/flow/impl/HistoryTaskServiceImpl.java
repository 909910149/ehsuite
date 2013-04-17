/*    */ package com.htsoft.oa.service.flow.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.flow.HistoryTaskDao;
/*    */ import com.htsoft.oa.service.flow.HistoryTaskService;
/*    */ import java.util.List;
/*    */ import org.jbpm.pvm.internal.history.model.HistoryTaskInstanceImpl;
/*    */ 
/*    */ public class HistoryTaskServiceImpl extends BaseServiceImpl<HistoryTaskInstanceImpl>
/*    */   implements HistoryTaskService
/*    */ {
/*    */   private HistoryTaskDao dao;
/*    */ 
/*    */   public HistoryTaskServiceImpl(HistoryTaskDao dao)
/*    */   {
/* 13 */     super(dao);
/* 14 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   public List<HistoryTaskInstanceImpl> getByPiIdAssigneeOutcome(String piId, String assignee, String activityName, String outcome)
/*    */   {
/* 21 */     return this.dao.getByPiIdAssigneeOutcome(piId, assignee, activityName, outcome);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.flow.impl.HistoryTaskServiceImpl
 * JD-Core Version:    0.6.0
 */