/*    */ package com.htsoft.test.task;
/*    */ 
/*    */ import com.htsoft.oa.dao.task.CalendarPlanDao;
/*    */ import com.htsoft.oa.model.task.CalendarPlan;
/*    */ import com.htsoft.test.BaseTestCase;
/*    */ import javax.annotation.Resource;
/*    */ import org.junit.Test;
/*    */ import org.springframework.test.annotation.Rollback;
/*    */ 
/*    */ public class CalendarPlanDaoTestCase extends BaseTestCase
/*    */ {
/*    */ 
/*    */   @Resource
/*    */   private CalendarPlanDao calendarPlanDao;
/*    */ 
/*    */   @Test
/*    */   @Rollback(false)
/*    */   public void add()
/*    */   {
/* 19 */     CalendarPlan calendarPlan = new CalendarPlan();
/*    */ 
/* 22 */     this.calendarPlanDao.save(calendarPlan);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.task.CalendarPlanDaoTestCase
 * JD-Core Version:    0.6.0
 */