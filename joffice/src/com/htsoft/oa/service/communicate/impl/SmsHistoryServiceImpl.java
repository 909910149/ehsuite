/*    */ package com.htsoft.oa.service.communicate.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.communicate.SmsHistoryDao;
/*    */ import com.htsoft.oa.model.communicate.SmsHistory;
/*    */ import com.htsoft.oa.service.communicate.SmsHistoryService;
/*    */ 
/*    */ public class SmsHistoryServiceImpl extends BaseServiceImpl<SmsHistory>
/*    */   implements SmsHistoryService
/*    */ {
/*    */   private SmsHistoryDao dao;
/*    */ 
/*    */   public SmsHistoryServiceImpl(SmsHistoryDao dao)
/*    */   {
/* 15 */     super(dao);
/* 16 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.communicate.impl.SmsHistoryServiceImpl
 * JD-Core Version:    0.6.0
 */