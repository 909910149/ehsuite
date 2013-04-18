/*    */ package com.htsoft.oa.service.document.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.document.SealDao;
/*    */ import com.htsoft.oa.model.document.Seal;
/*    */ import com.htsoft.oa.service.document.SealService;
/*    */ 
/*    */ public class SealServiceImpl extends BaseServiceImpl<Seal>
/*    */   implements SealService
/*    */ {
/*    */   private SealDao dao;
/*    */ 
/*    */   public SealServiceImpl(SealDao dao)
/*    */   {
/* 16 */     super(dao);
/* 17 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.document.impl.SealServiceImpl
 * JD-Core Version:    0.6.0
 */