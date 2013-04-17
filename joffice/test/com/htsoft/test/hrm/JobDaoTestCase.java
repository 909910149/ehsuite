/*    */ package com.htsoft.test.hrm;
/*    */ 
/*    */ import com.htsoft.oa.dao.hrm.JobDao;
/*    */ import com.htsoft.oa.model.hrm.Job;
/*    */ import com.htsoft.test.BaseTestCase;
/*    */ import javax.annotation.Resource;
/*    */ import org.junit.Test;
/*    */ import org.springframework.test.annotation.Rollback;
/*    */ 
/*    */ public class JobDaoTestCase extends BaseTestCase
/*    */ {
/*    */ 
/*    */   @Resource
/*    */   private JobDao jobDao;
/*    */ 
/*    */   @Test
/*    */   @Rollback(false)
/*    */   public void add()
/*    */   {
/* 22 */     Job job = new Job();
/*    */ 
/* 25 */     this.jobDao.save(job);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.hrm.JobDaoTestCase
 * JD-Core Version:    0.6.0
 */