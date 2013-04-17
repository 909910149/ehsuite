/*    */ package com.htsoft.test.task;
/*    */ 
/*    */ import com.htsoft.oa.dao.task.PlanTypeDao;
/*    */ import com.htsoft.oa.model.task.PlanType;
/*    */ import com.htsoft.test.BaseTestCase;
/*    */ import javax.annotation.Resource;
/*    */ import org.junit.Test;
/*    */ import org.springframework.test.annotation.Rollback;
/*    */ 
/*    */ public class PlanTypeDaoTestCase extends BaseTestCase
/*    */ {
/*    */ 
/*    */   @Resource
/*    */   private PlanTypeDao planTypeDao;
/*    */ 
/*    */   @Test
/*    */   @Rollback(false)
/*    */   public void add()
/*    */   {
/* 19 */     PlanType planType = new PlanType();
/*    */ 
/* 22 */     this.planTypeDao.save(planType);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.task.PlanTypeDaoTestCase
 * JD-Core Version:    0.6.0
 */