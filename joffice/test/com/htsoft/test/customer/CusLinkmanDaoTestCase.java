/*    */ package com.htsoft.test.customer;
/*    */ 
/*    */ import com.htsoft.oa.dao.customer.CusLinkmanDao;
/*    */ import com.htsoft.oa.model.customer.CusLinkman;
/*    */ import com.htsoft.test.BaseTestCase;
/*    */ import javax.annotation.Resource;
/*    */ import org.junit.Test;
/*    */ import org.springframework.test.annotation.Rollback;
/*    */ 
/*    */ public class CusLinkmanDaoTestCase extends BaseTestCase
/*    */ {
/*    */ 
/*    */   @Resource
/*    */   private CusLinkmanDao cusLinkmanDao;
/*    */ 
/*    */   @Test
/*    */   @Rollback(false)
/*    */   public void add()
/*    */   {
/* 19 */     CusLinkman cusLinkman = new CusLinkman();
/*    */ 
/* 22 */     this.cusLinkmanDao.save(cusLinkman);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.customer.CusLinkmanDaoTestCase
 * JD-Core Version:    0.6.0
 */