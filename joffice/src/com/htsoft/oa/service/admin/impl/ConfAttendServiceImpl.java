/*    */ package com.htsoft.oa.service.admin.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.admin.ConfAttendDao;
/*    */ import com.htsoft.oa.model.admin.ConfAttend;
/*    */ import com.htsoft.oa.service.admin.ConfAttendService;
/*    */ 
/*    */ public class ConfAttendServiceImpl extends BaseServiceImpl<ConfAttend>
/*    */   implements ConfAttendService
/*    */ {
/*    */   private ConfAttendDao dao;
/*    */ 
/*    */   public ConfAttendServiceImpl(ConfAttendDao dao)
/*    */   {
/* 24 */     super(dao);
/* 25 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.admin.impl.ConfAttendServiceImpl
 * JD-Core Version:    0.6.0
 */