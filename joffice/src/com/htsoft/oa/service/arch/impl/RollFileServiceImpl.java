/*    */ package com.htsoft.oa.service.arch.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.arch.RollFileDao;
/*    */ import com.htsoft.oa.model.arch.RollFile;
/*    */ import com.htsoft.oa.service.arch.RollFileService;
/*    */ 
/*    */ public class RollFileServiceImpl extends BaseServiceImpl<RollFile>
/*    */   implements RollFileService
/*    */ {
/*    */   private RollFileDao dao;
/*    */ 
/*    */   public RollFileServiceImpl(RollFileDao dao)
/*    */   {
/* 15 */     super(dao);
/* 16 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.arch.impl.RollFileServiceImpl
 * JD-Core Version:    0.6.0
 */