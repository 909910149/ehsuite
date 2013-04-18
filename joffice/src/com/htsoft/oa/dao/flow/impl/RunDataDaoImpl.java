/*    */ package com.htsoft.oa.dao.flow.impl;
/*    */ 
/*    */ import com.htsoft.core.dao.impl.BaseDaoImpl;
/*    */ import com.htsoft.oa.dao.flow.RunDataDao;
/*    */ import com.htsoft.oa.model.flow.RunData;
/*    */ import java.util.List;
/*    */ 
/*    */ public class RunDataDaoImpl extends BaseDaoImpl<RunData>
/*    */   implements RunDataDao
/*    */ {
/*    */   public RunDataDaoImpl()
/*    */   {
/* 16 */     super(RunData.class);
/*    */   }
/*    */ 
/*    */   public RunData getByRunIdFieldName(Long runId, String fieldName) {
/* 20 */     String hql = "from RunData rd where rd.processRun.runId=? and rd.fieldName=?";
/* 21 */     return (RunData)findUnique(hql, new Object[] { runId, fieldName });
/*    */   }
/*    */ 
/*    */   public List<RunData> getByRunId(Long runId)
/*    */   {
/* 28 */     String hql = "from RunData rd where rd.processRun.runId=?";
/* 29 */     return findByHql(hql, new Object[] { runId });
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.flow.impl.RunDataDaoImpl
 * JD-Core Version:    0.6.0
 */