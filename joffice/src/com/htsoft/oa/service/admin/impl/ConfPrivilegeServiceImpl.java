/*    */ package com.htsoft.oa.service.admin.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.core.util.ContextUtil;
/*    */ import com.htsoft.oa.dao.admin.ConfPrivilegeDao;
/*    */ import com.htsoft.oa.model.admin.ConfPrivilege;
/*    */ import com.htsoft.oa.service.admin.ConfPrivilegeService;
/*    */ 
/*    */ public class ConfPrivilegeServiceImpl extends BaseServiceImpl<ConfPrivilege>
/*    */   implements ConfPrivilegeService
/*    */ {
/*    */   private ConfPrivilegeDao dao;
/*    */ 
/*    */   public ConfPrivilegeServiceImpl(ConfPrivilegeDao dao)
/*    */   {
/* 24 */     super(dao);
/* 25 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   public Short getPrivilege(Long confId, Short s)
/*    */   {
/* 38 */     return this.dao.getPrivilege(ContextUtil.getCurrentUserId(), confId, s);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.admin.impl.ConfPrivilegeServiceImpl
 * JD-Core Version:    0.6.0
 */