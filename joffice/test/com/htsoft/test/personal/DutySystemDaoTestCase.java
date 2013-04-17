/*    */ package com.htsoft.test.personal;
/*    */ 
/*    */ import com.htsoft.oa.dao.personal.DutySystemDao;
/*    */ import com.htsoft.oa.model.personal.DutySystem;
/*    */ import com.htsoft.test.BaseTestCase;
/*    */ import javax.annotation.Resource;
/*    */ import org.junit.Test;
/*    */ import org.springframework.test.annotation.Rollback;
/*    */ 
/*    */ public class DutySystemDaoTestCase extends BaseTestCase
/*    */ {
/*    */ 
/*    */   @Resource
/*    */   private DutySystemDao dutySystemDao;
/*    */ 
/*    */   @Test
/*    */   @Rollback(false)
/*    */   public void add()
/*    */   {
/* 19 */     DutySystem dutySystem = new DutySystem();
/*    */ 
/* 22 */     this.dutySystemDao.save(dutySystem);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.personal.DutySystemDaoTestCase
 * JD-Core Version:    0.6.0
 */