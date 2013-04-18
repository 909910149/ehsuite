/*    */ package com.htsoft.oa.model.flow;
/*    */ 
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ import org.jbpm.api.model.Activity;
/*    */ import org.jbpm.api.model.Transition;
/*    */ 
/*    */ public class Transform
/*    */ {
/* 19 */   private static final Log logger = LogFactory.getLog(Transform.class);
/*    */   private String name;
/*    */   private String destination;
/*    */   private String source;
/*    */   private String destType;
/*    */ 
/*    */   public String getName()
/*    */   {
/* 40 */     return this.name;
/*    */   }
/*    */ 
/*    */   public void setName(String name) {
/* 44 */     this.name = name;
/*    */   }
/*    */ 
/*    */   public String getDestination() {
/* 48 */     return this.destination;
/*    */   }
/*    */ 
/*    */   public void setDestination(String destination) {
/* 52 */     this.destination = destination;
/*    */   }
/*    */ 
/*    */   public String getSource() {
/* 56 */     return this.source;
/*    */   }
/*    */ 
/*    */   public void setSource(String source) {
/* 60 */     this.source = source;
/*    */   }
/*    */ 
/*    */   public Transform()
/*    */   {
/*    */   }
/*    */ 
/*    */   public Transform(Transition jbpmtran)
/*    */   {
/*    */     try {
/* 70 */       this.name = jbpmtran.getName();
/* 71 */       this.source = jbpmtran.getSource().getName();
/* 72 */       Activity destAct = jbpmtran.getDestination();
/* 73 */       this.destination = destAct.getName();
/* 74 */       this.destType = destAct.getType();
/*    */     } catch (Exception ex) {
/* 76 */       logger.error(ex.getMessage());
/*    */     }
/*    */   }
/*    */ 
/*    */   public String getDestType() {
/* 81 */     return this.destType;
/*    */   }
/*    */ 
/*    */   public void setDestType(String destType) {
/* 85 */     this.destType = destType;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.flow.Transform
 * JD-Core Version:    0.6.0
 */