/*    */ package com.htsoft.test.admin;
/*    */ 
/*    */ import com.htsoft.oa.dao.admin.BookSnDao;
/*    */ import com.htsoft.oa.model.admin.BookSn;
/*    */ import com.htsoft.test.BaseTestCase;
/*    */ import javax.annotation.Resource;
/*    */ import org.junit.Test;
/*    */ import org.springframework.test.annotation.Rollback;
/*    */ 
/*    */ public class BookSnDaoTestCase extends BaseTestCase
/*    */ {
/*    */ 
/*    */   @Resource
/*    */   private BookSnDao bookSnDao;
/*    */ 
/*    */   @Test
/*    */   @Rollback(false)
/*    */   public void add()
/*    */   {
/* 19 */     BookSn bookSn = new BookSn();
/*    */ 
/* 22 */     this.bookSnDao.save(bookSn);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.admin.BookSnDaoTestCase
 * JD-Core Version:    0.6.0
 */