/*    */ package com.htsoft.test.flow;
/*    */ 
/*    */ import com.htsoft.oa.dao.flow.ProUserAssignDao;
/*    */ import com.htsoft.oa.model.flow.ProUserAssign;
/*    */ import com.htsoft.test.BaseTestCase;
/*    */ import javax.annotation.Resource;
/*    */ import org.junit.Test;
/*    */ import org.springframework.test.annotation.Rollback;
/*    */ 
/*    */ public class ProUserAssignDaoTestCase extends BaseTestCase
/*    */ {
/*    */ 
/*    */   @Resource
/*    */   private ProUserAssignDao proUserAssignDao;
/*    */ 
/*    */   @Test
/*    */   @Rollback(false)
/*    */   public void add()
/*    */   {
/* 19 */     ProUserAssign proUserAssign = new ProUserAssign();
/*    */ 
/* 22 */     this.proUserAssignDao.save(proUserAssign);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.flow.ProUserAssignDaoTestCase
 * JD-Core Version:    0.6.0
 */