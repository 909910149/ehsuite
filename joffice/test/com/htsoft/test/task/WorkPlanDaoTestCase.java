/*    */ package com.htsoft.test.task;
/*    */ 
/*    */ import com.htsoft.oa.dao.task.WorkPlanDao;
/*    */ import com.htsoft.oa.model.task.WorkPlan;
/*    */ import com.htsoft.test.BaseTestCase;
/*    */ import javax.annotation.Resource;
/*    */ import org.junit.Test;
/*    */ import org.springframework.test.annotation.Rollback;
/*    */ 
/*    */ public class WorkPlanDaoTestCase extends BaseTestCase
/*    */ {
/*    */ 
/*    */   @Resource
/*    */   private WorkPlanDao workPlanDao;
/*    */ 
/*    */   @Test
/*    */   @Rollback(false)
/*    */   public void add()
/*    */   {
/* 19 */     WorkPlan workPlan = new WorkPlan();
/*    */ 
/* 22 */     this.workPlanDao.save(workPlan);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.task.WorkPlanDaoTestCase
 * JD-Core Version:    0.6.0
 */