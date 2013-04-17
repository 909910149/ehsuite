/*    */ package com.htsoft.test.system;
/*    */ 
/*    */ import com.htsoft.oa.dao.system.UserJobDao;
/*    */ import com.htsoft.oa.model.system.UserJob;
/*    */ import com.htsoft.test.BaseTestCase;
/*    */ import javax.annotation.Resource;
/*    */ import org.junit.Test;
/*    */ import org.springframework.test.annotation.Rollback;
/*    */ 
/*    */ public class UserJobDaoTestCase extends BaseTestCase
/*    */ {
/*    */ 
/*    */   @Resource
/*    */   private UserJobDao userJobDao;
/*    */ 
/*    */   @Test
/*    */   @Rollback(false)
/*    */   public void add()
/*    */   {
/* 22 */     UserJob userJob = new UserJob();
/*    */ 
/* 25 */     this.userJobDao.save(userJob);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.system.UserJobDaoTestCase
 * JD-Core Version:    0.6.0
 */