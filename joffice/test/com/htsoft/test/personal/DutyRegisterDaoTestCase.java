/*    */ package com.htsoft.test.personal;
/*    */ 
/*    */ import com.htsoft.oa.dao.personal.DutyRegisterDao;
/*    */ import com.htsoft.oa.model.personal.DutyRegister;
/*    */ import com.htsoft.test.BaseTestCase;
/*    */ import javax.annotation.Resource;
/*    */ import org.junit.Test;
/*    */ import org.springframework.test.annotation.Rollback;
/*    */ 
/*    */ public class DutyRegisterDaoTestCase extends BaseTestCase
/*    */ {
/*    */ 
/*    */   @Resource
/*    */   private DutyRegisterDao dutyRegisterDao;
/*    */ 
/*    */   @Test
/*    */   @Rollback(false)
/*    */   public void add()
/*    */   {
/* 19 */     DutyRegister dutyRegister = new DutyRegister();
/*    */ 
/* 22 */     this.dutyRegisterDao.save(dutyRegister);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.personal.DutyRegisterDaoTestCase
 * JD-Core Version:    0.6.0
 */