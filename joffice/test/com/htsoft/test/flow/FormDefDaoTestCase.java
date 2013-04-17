/*    */ package com.htsoft.test.flow;
/*    */ 
/*    */ import com.htsoft.oa.dao.flow.FormDefDao;
/*    */ import com.htsoft.oa.model.flow.FormDef;
/*    */ import com.htsoft.test.BaseTestCase;
/*    */ import javax.annotation.Resource;
/*    */ import org.junit.Test;
/*    */ import org.springframework.test.annotation.Rollback;
/*    */ 
/*    */ public class FormDefDaoTestCase extends BaseTestCase
/*    */ {
/*    */ 
/*    */   @Resource
/*    */   private FormDefDao formDefDao;
/*    */ 
/*    */   @Test
/*    */   @Rollback(false)
/*    */   public void add()
/*    */   {
/* 22 */     FormDef formDef = new FormDef();
/*    */ 
/* 25 */     this.formDefDao.save(formDef);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.flow.FormDefDaoTestCase
 * JD-Core Version:    0.6.0
 */