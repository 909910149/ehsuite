/*    */ package com.htsoft.oa.service.admin.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.admin.FixedAssetsDao;
/*    */ import com.htsoft.oa.model.admin.FixedAssets;
/*    */ import com.htsoft.oa.service.admin.FixedAssetsService;
/*    */ 
/*    */ public class FixedAssetsServiceImpl extends BaseServiceImpl<FixedAssets>
/*    */   implements FixedAssetsService
/*    */ {
/*    */   private FixedAssetsDao dao;
/*    */ 
/*    */   public FixedAssetsServiceImpl(FixedAssetsDao dao)
/*    */   {
/* 15 */     super(dao);
/* 16 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.admin.impl.FixedAssetsServiceImpl
 * JD-Core Version:    0.6.0
 */