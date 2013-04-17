/*    */ package com.htsoft.oa.dao.flow.impl;
/*    */ 
/*    */ import com.htsoft.core.dao.impl.BaseDaoImpl;
/*    */ import com.htsoft.oa.dao.flow.ProcessFormDao;
/*    */ import com.htsoft.oa.model.flow.ProcessForm;
/*    */ import java.util.List;
/*    */ 
/*    */ public class ProcessFormDaoImpl extends BaseDaoImpl<ProcessForm>
/*    */   implements ProcessFormDao
/*    */ {
/*    */   public ProcessFormDaoImpl()
/*    */   {
/* 19 */     super(ProcessForm.class);
/*    */   }
/*    */ 
/*    */   public List getByRunId(Long runId)
/*    */   {
/* 26 */     String hql = "from ProcessForm pf where pf.processRun.runId=? and pf.endtime is not null order by pf.formId asc";
/* 27 */     return findByHql(hql, new Object[] { runId });
/*    */   }
/*    */ 
/*    */   public ProcessForm getByRunIdActivityName(Long runId, String activityName)
/*    */   {
/* 36 */     Integer maxSn = Integer.valueOf(getActvityExeTimes(runId, activityName).intValue());
/* 37 */     String hql = "from ProcessForm pf where pf.processRun.runId=? and pf.activityName=? and pf.sn=?";
/* 38 */     return (ProcessForm)findUnique(hql, new Object[] { runId, activityName, maxSn });
/*    */   }
/*    */ 
/*    */   public Long getActvityExeTimes(Long runId, String activityName)
/*    */   {
/* 68 */     String hql = "select count(pf.formId) from ProcessForm pf where pf.processRun.runId=? and pf.activityName=? ";
/* 69 */     return (Long)findUnique(hql, new Object[] { runId, activityName });
/*    */   }
/*    */ 
/*    */   public ProcessForm getByTaskId(String taskId)
/*    */   {
/* 77 */     String hql = "from ProcessForm pf where pf.taskId=? order by pf.createtime desc";
/* 78 */     List pfs = findByHql(hql, new Object[] { taskId });
/* 79 */     if (pfs.size() > 0) {
/* 80 */       return (ProcessForm)pfs.get(0);
/*    */     }
/* 82 */     return null;
/*    */   }
/*    */ 
/*    */   public ProcessForm getByRunIdTaskName(Long runId, String taskName)
/*    */   {
/* 90 */     String hql = "from ProcessForm pf where pf.processRun.runId=?  and pf.activityName=?";
/* 91 */     List pfs = findByHql(hql, new Object[] { runId, taskName });
/* 92 */     if (pfs.size() > 0) {
/* 93 */       return (ProcessForm)pfs.get(0);
/*    */     }
/* 95 */     return null;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.flow.impl.ProcessFormDaoImpl
 * JD-Core Version:    0.6.0
 */