/*    */ package com.htsoft.test.personal;
/*    */ 
/*    */ import com.htsoft.oa.dao.personal.DutySectionDao;
/*    */ import com.htsoft.oa.model.personal.DutySection;
/*    */ import com.htsoft.test.BaseTestCase;
/*    */ import javax.annotation.Resource;
/*    */ import org.junit.Test;
/*    */ import org.springframework.test.annotation.Rollback;
/*    */ 
/*    */ public class DutySectionDaoTestCase extends BaseTestCase
/*    */ {
/*    */ 
/*    */   @Resource
/*    */   private DutySectionDao dutySectionDao;
/*    */ 
/*    */   @Test
/*    */   @Rollback(false)
/*    */   public void add()
/*    */   {
/* 19 */     DutySection dutySection = new DutySection();
/*    */ 
/* 22 */     this.dutySectionDao.save(dutySection);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.personal.DutySectionDaoTestCase
 * JD-Core Version:    0.6.0
 */