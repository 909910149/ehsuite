/*    */ package com.htsoft.oa.service.admin.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.admin.ConfSummaryDao;
/*    */ import com.htsoft.oa.model.admin.ConfSummary;
/*    */ import com.htsoft.oa.service.admin.ConfSummaryService;
/*    */ 
/*    */ public class ConfSummaryServiceImpl extends BaseServiceImpl<ConfSummary>
/*    */   implements ConfSummaryService
/*    */ {
/*    */   private ConfSummaryDao dao;
/*    */ 
/*    */   public ConfSummaryServiceImpl(ConfSummaryDao dao)
/*    */   {
/* 24 */     super(dao);
/* 25 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   public ConfSummary send(ConfSummary cm, String fileIds)
/*    */   {
/* 36 */     return this.dao.send(cm, fileIds);
/*    */   }
/*    */ 
/*    */   public ConfSummary save(ConfSummary cm, String fileIds)
/*    */   {
/* 47 */     return this.dao.save(cm, fileIds);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.admin.impl.ConfSummaryServiceImpl
 * JD-Core Version:    0.6.0
 */