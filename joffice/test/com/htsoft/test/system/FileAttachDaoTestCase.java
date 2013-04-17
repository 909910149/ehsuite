/*    */ package com.htsoft.test.system;
/*    */ 
/*    */ import com.htsoft.oa.dao.system.FileAttachDao;
/*    */ import com.htsoft.oa.model.system.FileAttach;
/*    */ import com.htsoft.test.BaseTestCase;
/*    */ import javax.annotation.Resource;
/*    */ import org.junit.Test;
/*    */ import org.springframework.test.annotation.Rollback;
/*    */ 
/*    */ public class FileAttachDaoTestCase extends BaseTestCase
/*    */ {
/*    */ 
/*    */   @Resource
/*    */   private FileAttachDao fileAttachDao;
/*    */ 
/*    */   @Test
/*    */   @Rollback(false)
/*    */   public void add()
/*    */   {
/* 19 */     FileAttach fileAttach = new FileAttach();
/*    */ 
/* 23 */     this.fileAttachDao.removeByPath("communicate/mail/200910/2a4367669a1a4ea2b811013ceed199ce.png");
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.system.FileAttachDaoTestCase
 * JD-Core Version:    0.6.0
 */