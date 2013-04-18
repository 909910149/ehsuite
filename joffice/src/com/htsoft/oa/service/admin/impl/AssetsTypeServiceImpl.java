/*    */ package com.htsoft.oa.service.admin.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.admin.AssetsTypeDao;
/*    */ import com.htsoft.oa.model.admin.AssetsType;
/*    */ import com.htsoft.oa.service.admin.AssetsTypeService;
/*    */ 
/*    */ public class AssetsTypeServiceImpl extends BaseServiceImpl<AssetsType>
/*    */   implements AssetsTypeService
/*    */ {
/*    */   private AssetsTypeDao dao;
/*    */ 
/*    */   public AssetsTypeServiceImpl(AssetsTypeDao dao)
/*    */   {
/* 15 */     super(dao);
/* 16 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.admin.impl.AssetsTypeServiceImpl
 * JD-Core Version:    0.6.0
 */