/*    */ package com.htsoft.oa.dao.system.impl;
/*    */ 
/*    */ import com.htsoft.core.dao.impl.BaseDaoImpl;
/*    */ import com.htsoft.oa.dao.system.ReportParamDao;
/*    */ import com.htsoft.oa.model.system.ReportParam;
/*    */ import java.util.List;
/*    */ 
/*    */ public class ReportParamDaoImpl extends BaseDaoImpl<ReportParam>
/*    */   implements ReportParamDao
/*    */ {
/*    */   public ReportParamDaoImpl()
/*    */   {
/* 15 */     super(ReportParam.class);
/*    */   }
/*    */ 
/*    */   public List<ReportParam> findByRepTemp(Long reportId)
/*    */   {
/* 20 */     String hql = "from ReportParam vo where vo.reportTemplate.reportId=? order by vo.sn asc";
/* 21 */     Object[] objs = { reportId };
/* 22 */     return findByHql(hql, objs);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.system.impl.ReportParamDaoImpl
 * JD-Core Version:    0.6.0
 */