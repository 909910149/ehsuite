/*    */ package com.htsoft.oa.core.dynamicPwd;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.HashMap;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ 
/*    */ public class YooeResponse
/*    */ {
/*    */   protected static final String RET_CMD_OK = "OK";
/*    */   protected static final String RET_CMD_ERR = "ERR";
/* 12 */   protected static final String[] RET_CMD = { "OK", "ERR" };
/*    */   private String ret_cmd;
/*    */   private HashMap<String, String> vars_dict;
/*    */   private List<String> response;
/*    */ 
/*    */   private void parse_response()
/*    */     throws Exception
/*    */   {
/* 19 */     if (this.response.isEmpty()) {
/* 20 */       throw new Exception();
/*    */     }
/*    */ 
/* 23 */     Iterator i = this.response.iterator();
/*    */ 
/* 25 */     this.ret_cmd = ((String)i.next());
/* 26 */     if (!Arrays.asList(RET_CMD).contains(this.ret_cmd)) {
/* 27 */       throw new Exception();
/*    */     }
/*    */ 
/* 30 */     this.vars_dict = new HashMap();
/* 31 */     while (i.hasNext()) {
/* 32 */       String[] reline = ((String)i.next()).split(":", 2);
/* 33 */       if (reline.length < 2)
/* 34 */         this.vars_dict.put(reline[0].trim(), "");
/*    */       else
/* 36 */         this.vars_dict.put(reline[0].trim(), reline[1].trim());
/*    */     }
/*    */   }
/*    */ 
/*    */   public String getRetCmd() throws Exception
/*    */   {
/* 42 */     if (this.ret_cmd == null) {
/* 43 */       parse_response();
/*    */     }
/* 45 */     return this.ret_cmd;
/*    */   }
/*    */ 
/*    */   public HashMap<String, String> getVarsDict() throws Exception {
/* 49 */     if (this.ret_cmd == null) {
/* 50 */       parse_response();
/*    */     }
/* 52 */     return this.vars_dict;
/*    */   }
/*    */ 
/*    */   public YooeResponse(List<String> response)
/*    */   {
/* 57 */     this.response = response;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.core.dynamicPwd.YooeResponse
 * JD-Core Version:    0.6.0
 */