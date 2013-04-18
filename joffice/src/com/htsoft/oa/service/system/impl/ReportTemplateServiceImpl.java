/*    */ package com.htsoft.oa.service.system.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.system.ReportTemplateDao;
/*    */ import com.htsoft.oa.model.system.ReportTemplate;
/*    */ import com.htsoft.oa.service.system.ReportTemplateService;
/*    */ 
/*    */ public class ReportTemplateServiceImpl extends BaseServiceImpl<ReportTemplate>
/*    */   implements ReportTemplateService
/*    */ {
/*    */   private ReportTemplateDao dao;
/*    */ 
/*    */   public ReportTemplateServiceImpl(ReportTemplateDao dao)
/*    */   {
/* 15 */     super(dao);
/* 16 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.system.impl.ReportTemplateServiceImpl
 * JD-Core Version:    0.6.0
 */