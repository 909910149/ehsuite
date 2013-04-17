/*    */ package com.htsoft.test.bsh;
/*    */ 
/*    */ import bsh.Interpreter;
/*    */ import java.io.PrintStream;
/*    */ 
/*    */ public class ExmBsh
/*    */ {
/*    */   public static void main(String[] args)
/*    */   {
/*  7 */     System.out.println("start:");
/*    */ 
/*  9 */     String code = "String result=\"Demox\";System.out.println(\"ABC\");";
/*    */     try
/*    */     {
/* 12 */       Interpreter i = new Interpreter();
/*    */ 
/* 31 */       i.eval(code);
/* 32 */       System.out.println("name:" + i.get("result"));
/*    */     }
/*    */     catch (Exception e) {
/* 35 */       e.printStackTrace();
/*    */     }
/* 37 */     System.out.println("end:");
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.bsh.ExmBsh
 * JD-Core Version:    0.6.0
 */