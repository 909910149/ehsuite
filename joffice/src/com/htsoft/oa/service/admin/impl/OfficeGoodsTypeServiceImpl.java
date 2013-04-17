/*    */ package com.htsoft.oa.service.admin.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.admin.OfficeGoodsTypeDao;
/*    */ import com.htsoft.oa.model.admin.OfficeGoodsType;
/*    */ import com.htsoft.oa.service.admin.OfficeGoodsTypeService;
/*    */ 
/*    */ public class OfficeGoodsTypeServiceImpl extends BaseServiceImpl<OfficeGoodsType>
/*    */   implements OfficeGoodsTypeService
/*    */ {
/*    */   private OfficeGoodsTypeDao dao;
/*    */ 
/*    */   public OfficeGoodsTypeServiceImpl(OfficeGoodsTypeDao dao)
/*    */   {
/* 15 */     super(dao);
/* 16 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.admin.impl.OfficeGoodsTypeServiceImpl
 * JD-Core Version:    0.6.0
 */