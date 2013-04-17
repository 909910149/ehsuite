/*    */ package com.htsoft.oa.service.admin.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.admin.CartRepairDao;
/*    */ import com.htsoft.oa.model.admin.CartRepair;
/*    */ import com.htsoft.oa.service.admin.CartRepairService;
/*    */ 
/*    */ public class CartRepairServiceImpl extends BaseServiceImpl<CartRepair>
/*    */   implements CartRepairService
/*    */ {
/*    */   private CartRepairDao dao;
/*    */ 
/*    */   public CartRepairServiceImpl(CartRepairDao dao)
/*    */   {
/* 15 */     super(dao);
/* 16 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.admin.impl.CartRepairServiceImpl
 * JD-Core Version:    0.6.0
 */