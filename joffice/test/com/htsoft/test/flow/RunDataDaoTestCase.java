/*    */ package com.htsoft.test.flow;
/*    */ 
/*    */ import com.htsoft.oa.dao.flow.RunDataDao;
/*    */ import com.htsoft.oa.model.flow.RunData;
/*    */ import com.htsoft.test.BaseTestCase;
/*    */ import javax.annotation.Resource;
/*    */ import org.junit.Test;
/*    */ import org.springframework.test.annotation.Rollback;
/*    */ 
/*    */ public class RunDataDaoTestCase extends BaseTestCase
/*    */ {
/*    */ 
/*    */   @Resource
/*    */   private RunDataDao formDataDao;
/*    */ 
/*    */   @Test
/*    */   @Rollback(false)
/*    */   public void add()
/*    */   {
/* 19 */     RunData formData = new RunData();
/*    */ 
/* 22 */     this.formDataDao.save(formData);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.flow.RunDataDaoTestCase
 * JD-Core Version:    0.6.0
 */