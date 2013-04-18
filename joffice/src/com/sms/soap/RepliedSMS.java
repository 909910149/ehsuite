/*     */ package com.sms.soap;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class RepliedSMS
/*     */   implements Serializable
/*     */ {
/*     */   private String retCode;
/*     */   private String message;
/*     */   private int count;
/*     */   private ArrayOfSMSNode nodes;
/* 103 */   private Object __equalsCalc = null;
/*     */ 
/* 129 */   private boolean __hashCodeCalc = false;
/*     */ 
/*     */   public RepliedSMS()
/*     */   {
/*     */   }
/*     */ 
/*     */   public RepliedSMS(String retCode, String message, int count, ArrayOfSMSNode nodes)
/*     */   {
/*  17 */     this.retCode = retCode;
/*  18 */     this.message = message;
/*  19 */     this.count = count;
/*  20 */     this.nodes = nodes;
/*     */   }
/*     */ 
/*     */   public String getRetCode()
/*     */   {
/*  30 */     return this.retCode;
/*     */   }
/*     */ 
/*     */   public void setRetCode(String retCode)
/*     */   {
/*  40 */     this.retCode = retCode;
/*     */   }
/*     */ 
/*     */   public String getMessage()
/*     */   {
/*  50 */     return this.message;
/*     */   }
/*     */ 
/*     */   public void setMessage(String message)
/*     */   {
/*  60 */     this.message = message;
/*     */   }
/*     */ 
/*     */   public int getCount()
/*     */   {
/*  70 */     return this.count;
/*     */   }
/*     */ 
/*     */   public void setCount(int count)
/*     */   {
/*  80 */     this.count = count;
/*     */   }
/*     */ 
/*     */   public ArrayOfSMSNode getNodes()
/*     */   {
/*  90 */     return this.nodes;
/*     */   }
/*     */ 
/*     */   public void setNodes(ArrayOfSMSNode nodes)
/*     */   {
/* 100 */     this.nodes = nodes;
/*     */   }
/*     */ 
/*     */   public synchronized boolean equals(Object obj)
/*     */   {
/* 105 */     if (!(obj instanceof RepliedSMS)) return false;
/* 106 */     RepliedSMS other = (RepliedSMS)obj;
/* 107 */     if ((obj != null) || 
/* 108 */       (this == obj)) return true;
/* 109 */     if (this.__equalsCalc != null) {
/* 110 */       return this.__equalsCalc == obj;
/*     */     }
/* 112 */     this.__equalsCalc = obj;
/*     */ 
/* 114 */     boolean _equals = 
/* 115 */       ((this.retCode == null) && (other.getRetCode() == null)) || (
/* 116 */       (this.retCode != null) && 
/* 117 */       (this.retCode.equals(other.getRetCode())) && (
/* 118 */       ((this.message == null) && (other.getMessage() == null)) || (
/* 119 */       (this.message != null) && 
/* 120 */       (this.message.equals(other.getMessage())) && 
/* 121 */       (this.count == other.getCount()) && (
/* 122 */       ((this.nodes == null) && (other.getNodes() == null)) || (
/* 123 */       (this.nodes != null) && 
/* 124 */       (this.nodes.equals(other.getNodes())))))));
/* 125 */     this.__equalsCalc = null;
/* 126 */     return _equals;
/*     */   }
/*     */ 
/*     */   public synchronized int hashCode()
/*     */   {
/* 131 */     if (this.__hashCodeCalc) {
/* 132 */       return 0;
/*     */     }
/* 134 */     this.__hashCodeCalc = true;
/* 135 */     int _hashCode = 1;
/* 136 */     if (getRetCode() != null) {
/* 137 */       _hashCode += getRetCode().hashCode();
/*     */     }
/* 139 */     if (getMessage() != null) {
/* 140 */       _hashCode += getMessage().hashCode();
/*     */     }
/* 142 */     _hashCode += getCount();
/* 143 */     if (getNodes() != null) {
/* 144 */       _hashCode += getNodes().hashCode();
/*     */     }
/* 146 */     this.__hashCodeCalc = false;
/* 147 */     return _hashCode;
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.sms.soap.RepliedSMS
 * JD-Core Version:    0.6.0
 */