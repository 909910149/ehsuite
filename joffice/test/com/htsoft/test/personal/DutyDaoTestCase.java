/*    */ package com.htsoft.test.personal;
/*    */ 
/*    */ import com.htsoft.oa.dao.personal.DutyDao;
/*    */ import com.htsoft.oa.model.personal.Duty;
/*    */ import com.htsoft.test.BaseTestCase;
/*    */ import javax.annotation.Resource;
/*    */ import org.junit.Test;
/*    */ import org.springframework.test.annotation.Rollback;
/*    */ 
/*    */ public class DutyDaoTestCase extends BaseTestCase
/*    */ {
/*    */ 
/*    */   @Resource
/*    */   private DutyDao dutyDao;
/*    */ 
/*    */   @Test
/*    */   @Rollback(false)
/*    */   public void add()
/*    */   {
/* 19 */     Duty duty = new Duty();
/*    */ 
/* 22 */     this.dutyDao.save(duty);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.personal.DutyDaoTestCase
 * JD-Core Version:    0.6.0
 */