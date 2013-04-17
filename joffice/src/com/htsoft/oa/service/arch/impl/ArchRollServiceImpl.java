/*    */ package com.htsoft.oa.service.arch.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.arch.ArchRollDao;
/*    */ import com.htsoft.oa.model.arch.ArchRoll;
/*    */ import com.htsoft.oa.service.arch.ArchRollService;
/*    */ 
/*    */ public class ArchRollServiceImpl extends BaseServiceImpl<ArchRoll>
/*    */   implements ArchRollService
/*    */ {
/*    */   private ArchRollDao dao;
/*    */ 
/*    */   public ArchRollServiceImpl(ArchRollDao dao)
/*    */   {
/* 16 */     super(dao);
/* 17 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.arch.impl.ArchRollServiceImpl
 * JD-Core Version:    0.6.0
 */