/*    */ package com.htsoft.test.flow;
/*    */ 
/*    */ import com.htsoft.oa.dao.flow.ProcessFormDao;
/*    */ import com.htsoft.oa.model.flow.ProcessForm;
/*    */ import com.htsoft.test.BaseTestCase;
/*    */ import javax.annotation.Resource;
/*    */ import org.junit.Test;
/*    */ import org.springframework.test.annotation.Rollback;
/*    */ 
/*    */ public class ProcessFormDaoTestCase extends BaseTestCase
/*    */ {
/*    */ 
/*    */   @Resource
/*    */   private ProcessFormDao processFormDao;
/*    */ 
/*    */   @Test
/*    */   @Rollback(false)
/*    */   public void add()
/*    */   {
/* 19 */     ProcessForm processForm = new ProcessForm();
/*    */ 
/* 22 */     this.processFormDao.save(processForm);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.flow.ProcessFormDaoTestCase
 * JD-Core Version:    0.6.0
 */