/*    */ package com.htsoft.test.hrm;
/*    */ 
/*    */ import com.htsoft.oa.dao.hrm.EmpProfileDao;
/*    */ import com.htsoft.oa.model.hrm.EmpProfile;
/*    */ import com.htsoft.test.BaseTestCase;
/*    */ import javax.annotation.Resource;
/*    */ import org.junit.Test;
/*    */ import org.springframework.test.annotation.Rollback;
/*    */ 
/*    */ public class EmpProfileDaoTestCase extends BaseTestCase
/*    */ {
/*    */ 
/*    */   @Resource
/*    */   private EmpProfileDao empProfileDao;
/*    */ 
/*    */   @Test
/*    */   @Rollback(false)
/*    */   public void add()
/*    */   {
/* 22 */     EmpProfile empProfile = new EmpProfile();
/*    */ 
/* 25 */     this.empProfileDao.save(empProfile);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.hrm.EmpProfileDaoTestCase
 * JD-Core Version:    0.6.0
 */