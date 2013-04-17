/*    */ package com.htsoft.oa.service.arch.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.arch.BorrowFileListDao;
/*    */ import com.htsoft.oa.model.arch.BorrowFileList;
/*    */ import com.htsoft.oa.service.arch.BorrowFileListService;
/*    */ 
/*    */ public class BorrowFileListServiceImpl extends BaseServiceImpl<BorrowFileList>
/*    */   implements BorrowFileListService
/*    */ {
/*    */   private BorrowFileListDao dao;
/*    */ 
/*    */   public BorrowFileListServiceImpl(BorrowFileListDao dao)
/*    */   {
/* 15 */     super(dao);
/* 16 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.arch.impl.BorrowFileListServiceImpl
 * JD-Core Version:    0.6.0
 */