/*    */ package com.htsoft.test.admin;
/*    */ 
/*    */ import com.htsoft.oa.dao.admin.InStockDao;
/*    */ import com.htsoft.test.BaseTestCase;
/*    */ import java.io.PrintStream;
/*    */ import javax.annotation.Resource;
/*    */ import org.junit.Test;
/*    */ 
/*    */ public class InStockDaoTestCase extends BaseTestCase
/*    */ {
/*    */ 
/*    */   @Resource
/*    */   private InStockDao inStockDao;
/*    */ 
/*    */   @Test
/*    */   public void test1()
/*    */   {
/* 19 */     Integer inCount = this.inStockDao.findInCountByBuyId(Long.valueOf(1L));
/* 20 */     System.out.println(inCount);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.admin.InStockDaoTestCase
 * JD-Core Version:    0.6.0
 */