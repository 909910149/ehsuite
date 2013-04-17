/*    */ package com.htsoft.test.communicate;
/*    */ 
/*    */ import com.htsoft.oa.dao.communicate.PhoneGroupDao;
/*    */ import com.htsoft.oa.model.communicate.PhoneGroup;
/*    */ import com.htsoft.test.BaseTestCase;
/*    */ import javax.annotation.Resource;
/*    */ import org.junit.Test;
/*    */ import org.springframework.test.annotation.Rollback;
/*    */ 
/*    */ public class PhoneGroupDaoTestCase extends BaseTestCase
/*    */ {
/*    */ 
/*    */   @Resource
/*    */   private PhoneGroupDao phoneGroupDao;
/*    */ 
/*    */   @Test
/*    */   @Rollback(false)
/*    */   public void add()
/*    */   {
/* 19 */     PhoneGroup phoneGroup = new PhoneGroup();
/*    */ 
/* 22 */     this.phoneGroupDao.save(phoneGroup);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.communicate.PhoneGroupDaoTestCase
 * JD-Core Version:    0.6.0
 */