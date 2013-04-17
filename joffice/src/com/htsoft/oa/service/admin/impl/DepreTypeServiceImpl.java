/*    */ package com.htsoft.oa.service.admin.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.admin.DepreTypeDao;
/*    */ import com.htsoft.oa.model.admin.DepreType;
/*    */ import com.htsoft.oa.service.admin.DepreTypeService;
/*    */ 
/*    */ public class DepreTypeServiceImpl extends BaseServiceImpl<DepreType>
/*    */   implements DepreTypeService
/*    */ {
/*    */   private DepreTypeDao dao;
/*    */ 
/*    */   public DepreTypeServiceImpl(DepreTypeDao dao)
/*    */   {
/* 15 */     super(dao);
/* 16 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.admin.impl.DepreTypeServiceImpl
 * JD-Core Version:    0.6.0
 */