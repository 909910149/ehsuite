/*    */ package com.htsoft.oa.model.flow;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ /** @deprecated */
/*    */ public class FlowStartInfo
/*    */ {
/* 21 */   private boolean isStartFlow = false;
/*    */ 
/* 26 */   private Map variables = new HashMap();
/*    */ 
/*    */   public FlowStartInfo(boolean isStartFlow, Map variables)
/*    */   {
/* 30 */     this.isStartFlow = isStartFlow;
/* 31 */     this.variables = variables;
/*    */   }
/*    */ 
/*    */   public FlowStartInfo(boolean isStartFlow) {
/* 35 */     this.isStartFlow = isStartFlow;
/*    */   }
/*    */ 
/*    */   public FlowStartInfo()
/*    */   {
/*    */   }
/*    */ 
/*    */   public boolean isStartFlow() {
/* 43 */     return this.isStartFlow;
/*    */   }
/*    */ 
/*    */   public void setStartFlow(boolean isStartFlow) {
/* 47 */     this.isStartFlow = isStartFlow;
/*    */   }
/*    */ 
/*    */   public Map getVariables() {
/* 51 */     return this.variables;
/*    */   }
/*    */ 
/*    */   public void setVariables(Map variables) {
/* 55 */     this.variables = variables;
/*    */   }
/*    */ 
/*    */   public void setdAssignId(String assignId)
/*    */   {
/* 63 */     this.variables.put("flowAssignId", assignId);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.flow.FlowStartInfo
 * JD-Core Version:    0.6.0
 */