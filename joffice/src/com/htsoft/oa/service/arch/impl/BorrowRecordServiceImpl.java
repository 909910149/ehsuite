/*    */ package com.htsoft.oa.service.arch.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.arch.BorrowRecordDao;
/*    */ import com.htsoft.oa.model.arch.BorrowRecord;
/*    */ import com.htsoft.oa.service.arch.BorrowRecordService;
/*    */ 
/*    */ public class BorrowRecordServiceImpl extends BaseServiceImpl<BorrowRecord>
/*    */   implements BorrowRecordService
/*    */ {
/*    */   private BorrowRecordDao dao;
/*    */ 
/*    */   public BorrowRecordServiceImpl(BorrowRecordDao dao)
/*    */   {
/* 15 */     super(dao);
/* 16 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.arch.impl.BorrowRecordServiceImpl
 * JD-Core Version:    0.6.0
 */