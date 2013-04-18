/*    */ package com.htsoft.oa.service.communicate.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.communicate.MailDao;
/*    */ import com.htsoft.oa.model.communicate.Mail;
/*    */ import com.htsoft.oa.service.communicate.MailService;
/*    */ 
/*    */ public class MailServiceImpl extends BaseServiceImpl<Mail>
/*    */   implements MailService
/*    */ {
/*    */   private MailDao dao;
/*    */ 
/*    */   public MailServiceImpl(MailDao dao)
/*    */   {
/* 15 */     super(dao);
/* 16 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.communicate.impl.MailServiceImpl
 * JD-Core Version:    0.6.0
 */