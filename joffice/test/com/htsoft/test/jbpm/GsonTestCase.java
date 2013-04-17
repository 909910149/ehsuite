/*    */ package com.htsoft.test.jbpm;
/*    */ 
/*    */ import com.htsoft.core.util.XmlUtil;
/*    */ import java.io.PrintStream;
/*    */ import java.util.HashSet;
/*    */ import org.dom4j.Document;
/*    */ import org.dom4j.Element;
/*    */ 
/*    */ public class GsonTestCase
/*    */ {
/*    */   public static void main(String[] args)
/*    */   {
/* 37 */     testArray();
/*    */   }
/*    */ 
/*    */   public static void test() {
/* 41 */     String path = "L:/devtools/workspace/joffice/test/com/htsoft/test/jbpm/jbpmdef.xml";
/*    */ 
/* 43 */     String defXml = XmlUtil.load(path).getRootElement().asXML();
/*    */ 
/* 45 */     System.out.println("xml:" + defXml);
/*    */   }
/*    */ 
/*    */   public static void testArray() {
/* 49 */     HashSet userIds = new HashSet();
/*    */ 
/* 51 */     userIds.add(new Long(1L));
/* 52 */     userIds.add(new Long(2L));
/*    */ 
/* 54 */     Long[] uIds = (Long[])userIds.toArray(new Long[0]);
/*    */ 
/* 56 */     for (Long u : uIds)
/* 57 */       System.out.println("out:" + u);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.jbpm.GsonTestCase
 * JD-Core Version:    0.6.0
 */