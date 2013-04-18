/*    */ package com.htsoft.oa.service.arch.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.arch.RollFileListDao;
/*    */ import com.htsoft.oa.model.arch.RollFileList;
/*    */ import com.htsoft.oa.service.arch.RollFileListService;
/*    */ 
/*    */ public class RollFileListServiceImpl extends BaseServiceImpl<RollFileList>
/*    */   implements RollFileListService
/*    */ {
/*    */   private RollFileListDao dao;
/*    */ 
/*    */   public RollFileListServiceImpl(RollFileListDao dao)
/*    */   {
/* 15 */     super(dao);
/* 16 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.arch.impl.RollFileListServiceImpl
 * JD-Core Version:    0.6.0
 */