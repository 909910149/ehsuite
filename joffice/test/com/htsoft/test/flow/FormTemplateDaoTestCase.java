/*    */ package com.htsoft.test.flow;
/*    */ 
/*    */ import com.htsoft.oa.dao.flow.FormTemplateDao;
/*    */ import com.htsoft.oa.model.flow.FormTemplate;
/*    */ import com.htsoft.test.BaseTestCase;
/*    */ import javax.annotation.Resource;
/*    */ import org.junit.Test;
/*    */ import org.springframework.test.annotation.Rollback;
/*    */ 
/*    */ public class FormTemplateDaoTestCase extends BaseTestCase
/*    */ {
/*    */ 
/*    */   @Resource
/*    */   private FormTemplateDao formTemplateDao;
/*    */ 
/*    */   @Test
/*    */   @Rollback(false)
/*    */   public void add()
/*    */   {
/* 22 */     FormTemplate formTemplate = new FormTemplate();
/*    */ 
/* 25 */     this.formTemplateDao.save(formTemplate);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.flow.FormTemplateDaoTestCase
 * JD-Core Version:    0.6.0
 */