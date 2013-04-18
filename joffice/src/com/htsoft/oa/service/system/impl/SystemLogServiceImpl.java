/*    */ package com.htsoft.oa.service.system.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.system.SystemLogDao;
/*    */ import com.htsoft.oa.model.system.SystemLog;
/*    */ import com.htsoft.oa.service.system.SystemLogService;
/*    */ 
/*    */ public class SystemLogServiceImpl extends BaseServiceImpl<SystemLog>
/*    */   implements SystemLogService
/*    */ {
/*    */   private SystemLogDao dao;
/*    */ 
/*    */   public SystemLogServiceImpl(SystemLogDao dao)
/*    */   {
/* 15 */     super(dao);
/* 16 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.system.impl.SystemLogServiceImpl
 * JD-Core Version:    0.6.0
 */