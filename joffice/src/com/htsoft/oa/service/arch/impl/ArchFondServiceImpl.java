/*    */ package com.htsoft.oa.service.arch.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.arch.ArchFondDao;
/*    */ import com.htsoft.oa.model.arch.ArchFond;
/*    */ import com.htsoft.oa.service.arch.ArchFondService;
/*    */ 
/*    */ public class ArchFondServiceImpl extends BaseServiceImpl<ArchFond>
/*    */   implements ArchFondService
/*    */ {
/*    */   private ArchFondDao dao;
/*    */ 
/*    */   public ArchFondServiceImpl(ArchFondDao dao)
/*    */   {
/* 16 */     super(dao);
/* 17 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.arch.impl.ArchFondServiceImpl
 * JD-Core Version:    0.6.0
 */