/*    */ package com.htsoft.oa.service.communicate.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.core.web.paging.PagingBean;
/*    */ import com.htsoft.oa.dao.communicate.PhoneBookDao;
/*    */ import com.htsoft.oa.model.communicate.PhoneBook;
/*    */ import com.htsoft.oa.service.communicate.PhoneBookService;
/*    */ import java.util.List;
/*    */ 
/*    */ public class PhoneBookServiceImpl extends BaseServiceImpl<PhoneBook>
/*    */   implements PhoneBookService
/*    */ {
/*    */   private PhoneBookDao dao;
/*    */ 
/*    */   public PhoneBookServiceImpl(PhoneBookDao dao)
/*    */   {
/* 18 */     super(dao);
/* 19 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   public List<PhoneBook> sharedPhoneBooks(String fullname, String ownerName, PagingBean pb)
/*    */   {
/* 25 */     return this.dao.sharedPhoneBooks(fullname, ownerName, pb);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.communicate.impl.PhoneBookServiceImpl
 * JD-Core Version:    0.6.0
 */