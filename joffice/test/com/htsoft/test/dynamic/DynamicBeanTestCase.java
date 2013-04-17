/*    */ package com.htsoft.test.dynamic;
/*    */ 
/*    */ import com.htsoft.core.service.DynamicService;
/*    */ import com.htsoft.core.util.BeanUtil;
/*    */ import com.htsoft.test.BaseTestCase;
/*    */ import java.io.PrintStream;
/*    */ import org.junit.Test;
/*    */ 
/*    */ public class DynamicBeanTestCase extends BaseTestCase
/*    */ {
/*    */   @Test
/*    */   public void testSessionFactoryBean()
/*    */     throws Exception
/*    */   {
/* 14 */     DynamicService dynamicService = BeanUtil.getDynamicServiceBean("com.htsoft.oa.entity.Order");
/*    */ 
/* 16 */     Object obj = dynamicService.get(new Long(1L));
/*    */ 
/* 18 */     System.out.println("order details:" + obj.toString());
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.dynamic.DynamicBeanTestCase
 * JD-Core Version:    0.6.0
 */