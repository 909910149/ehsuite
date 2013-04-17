/*    */ package com.htsoft.oa.service.archive.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.archive.DocHistoryDao;
/*    */ import com.htsoft.oa.model.archive.DocHistory;
/*    */ import com.htsoft.oa.service.archive.DocHistoryService;
/*    */ 
/*    */ public class DocHistoryServiceImpl extends BaseServiceImpl<DocHistory>
/*    */   implements DocHistoryService
/*    */ {
/*    */   private DocHistoryDao dao;
/*    */ 
/*    */   public DocHistoryServiceImpl(DocHistoryDao dao)
/*    */   {
/* 15 */     super(dao);
/* 16 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.archive.impl.DocHistoryServiceImpl
 * JD-Core Version:    0.6.0
 */