/*    */ package com.htsoft.oa.service.customer.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.customer.ProductDao;
/*    */ import com.htsoft.oa.model.customer.Product;
/*    */ import com.htsoft.oa.service.customer.ProductService;
/*    */ 
/*    */ public class ProductServiceImpl extends BaseServiceImpl<Product>
/*    */   implements ProductService
/*    */ {
/*    */   private ProductDao dao;
/*    */ 
/*    */   public ProductServiceImpl(ProductDao dao)
/*    */   {
/* 15 */     super(dao);
/* 16 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.customer.impl.ProductServiceImpl
 * JD-Core Version:    0.6.0
 */