/*    */ package com.htsoft.oa.service.system.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.system.TypeKeyDao;
/*    */ import com.htsoft.oa.model.system.TypeKey;
/*    */ import com.htsoft.oa.service.system.TypeKeyService;
/*    */ 
/*    */ public class TypeKeyServiceImpl extends BaseServiceImpl<TypeKey>
/*    */   implements TypeKeyService
/*    */ {
/*    */   private TypeKeyDao dao;
/*    */ 
/*    */   public TypeKeyServiceImpl(TypeKeyDao dao)
/*    */   {
/* 15 */     super(dao);
/* 16 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.system.impl.TypeKeyServiceImpl
 * JD-Core Version:    0.6.0
 */