/*    */ package com.htsoft.test.system;
/*    */ 
/*    */ import com.htsoft.oa.dao.system.DepUsersDao;
/*    */ import com.htsoft.oa.model.system.AppUser;
/*    */ import com.htsoft.oa.model.system.DepUsers;
/*    */ import com.htsoft.oa.model.system.Department;
/*    */ import com.htsoft.test.BaseTestCase;
/*    */ import javax.annotation.Resource;
/*    */ import org.junit.Test;
/*    */ import org.springframework.test.annotation.Rollback;
/*    */ 
/*    */ public class DepUsersDaoTestCase extends BaseTestCase
/*    */ {
/*    */ 
/*    */   @Resource
/*    */   private DepUsersDao depUsersDao;
/*    */ 
/*    */   @Test
/*    */   @Rollback(false)
/*    */   public void add()
/*    */   {
/* 24 */     for (int i = 0; i < 5; i++) {
/* 25 */       DepUsers depUsers = new DepUsers();
/* 26 */       depUsers.setAppUser(new AppUser(new Long(i)));
/* 27 */       depUsers.setDepartment(new Department(new Long(i)));
/* 28 */       depUsers.setSn(Integer.valueOf(i));
/*    */ 
/* 32 */       this.depUsersDao.save(depUsers);
/*    */     }
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.system.DepUsersDaoTestCase
 * JD-Core Version:    0.6.0
 */