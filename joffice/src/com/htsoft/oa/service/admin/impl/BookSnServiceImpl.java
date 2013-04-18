/*    */ package com.htsoft.oa.service.admin.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.admin.BookSnDao;
/*    */ import com.htsoft.oa.model.admin.BookSn;
/*    */ import com.htsoft.oa.service.admin.BookSnService;
/*    */ import java.util.List;
/*    */ 
/*    */ public class BookSnServiceImpl extends BaseServiceImpl<BookSn>
/*    */   implements BookSnService
/*    */ {
/*    */   private BookSnDao dao;
/*    */ 
/*    */   public BookSnServiceImpl(BookSnDao dao)
/*    */   {
/* 18 */     super(dao);
/* 19 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   public List<BookSn> findByBookId(Long bookId)
/*    */   {
/* 24 */     return this.dao.findByBookId(bookId);
/*    */   }
/*    */ 
/*    */   public List<BookSn> findBorrowByBookId(Long bookId)
/*    */   {
/* 29 */     return this.dao.findBorrowByBookId(bookId);
/*    */   }
/*    */ 
/*    */   public List<BookSn> findReturnByBookId(Long bookId)
/*    */   {
/* 34 */     return this.dao.findReturnByBookId(bookId);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.admin.impl.BookSnServiceImpl
 * JD-Core Version:    0.6.0
 */