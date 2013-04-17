/*    */ package com.htsoft.oa.service.admin.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.admin.InStockDao;
/*    */ import com.htsoft.oa.model.admin.InStock;
/*    */ import com.htsoft.oa.service.admin.InStockService;
/*    */ 
/*    */ public class InStockServiceImpl extends BaseServiceImpl<InStock>
/*    */   implements InStockService
/*    */ {
/*    */   private InStockDao dao;
/*    */ 
/*    */   public InStockServiceImpl(InStockDao dao)
/*    */   {
/* 15 */     super(dao);
/* 16 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   public Integer findInCountByBuyId(Long buyId)
/*    */   {
/* 21 */     return this.dao.findInCountByBuyId(buyId);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.admin.impl.InStockServiceImpl
 * JD-Core Version:    0.6.0
 */