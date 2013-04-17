/*    */ package com.htsoft.test.flow;
/*    */ 
/*    */ import com.htsoft.core.service.DynamicService;
/*    */ import com.htsoft.core.util.BeanUtil;
/*    */ import com.htsoft.core.util.JsonUtil;
/*    */ import com.htsoft.test.BaseTestCase;
/*    */ import java.io.PrintStream;
/*    */ import java.util.Collection;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import org.junit.Test;
/*    */ 
/*    */ public class DynamicTestCase extends BaseTestCase
/*    */ {
/*    */   @Test
/*    */   public void test()
/*    */   {
/* 31 */     DynamicService service = BeanUtil.getDynamicServiceBean("WF_Order");
/*    */ 
/* 34 */     Object obj = service.get(new Long(1L));
/*    */ 
/* 43 */     Map map = (Map)obj;
/*    */ 
/* 45 */     System.out.println("sjo:" + JsonUtil.mapEntity2Json(map, "WF_Order"));
/*    */ 
/* 50 */     Iterator it = map.values().iterator();
/*    */ 
/* 52 */     while (it.hasNext())
/* 53 */       System.out.println("val:" + it.next());
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.flow.DynamicTestCase
 * JD-Core Version:    0.6.0
 */