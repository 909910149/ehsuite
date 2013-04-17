/*    */ package com.htsoft.oa.service.flow.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.flow.ProcessModuleDao;
/*    */ import com.htsoft.oa.model.flow.ProcessModule;
/*    */ import com.htsoft.oa.service.flow.ProcessModuleService;
/*    */ 
/*    */ public class ProcessModuleServiceImpl extends BaseServiceImpl<ProcessModule>
/*    */   implements ProcessModuleService
/*    */ {
/*    */   private ProcessModuleDao dao;
/*    */ 
/*    */   public ProcessModuleServiceImpl(ProcessModuleDao dao)
/*    */   {
/* 16 */     super(dao);
/* 17 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   public ProcessModule getByKey(String string)
/*    */   {
/* 22 */     return this.dao.getByKey(string);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.flow.impl.ProcessModuleServiceImpl
 * JD-Core Version:    0.6.0
 */