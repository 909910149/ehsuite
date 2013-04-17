/*    */ package com.htsoft.test;
/*    */ 
/*    */ import com.google.gson.Gson;
/*    */ import com.google.gson.reflect.TypeToken;
/*    */ import java.io.PrintStream;
/*    */ import java.util.HashMap;
/*    */ import java.util.Iterator;
import java.util.Map;
/*    */ import java.util.Map.Entry;
import java.util.Set;
/*    */ 
/*    */ public class JsonMap
/*    */ {
/*    */   public static void main(String[] args)
/*    */   {
/* 11 */     String json = "{'productName':'ABC,aaa','price':12321,'quantity':'1','descp':'122'}";
/* 12 */     Gson gson = new Gson();
/*    */ 
/* 14 */     HashMap map = (HashMap)gson.fromJson(json, new TypeToken() {
/* 15 */     }.getType());
/*    */ 
/* 17 */     Iterator it = map.entrySet().iterator();
/*    */ 
/* 19 */     while (it.hasNext()) {
/* 20 */       Map.Entry entry = (Map.Entry)it.next();
/*    */ 
/* 22 */       System.out.println("key:" + entry.getKey() + " VAL:" + entry.getValue());
/*    */     }
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.JsonMap
 * JD-Core Version:    0.6.0
 */