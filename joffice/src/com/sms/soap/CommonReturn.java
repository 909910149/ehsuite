/*    */ package com.sms.soap;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class CommonReturn
/*    */   implements Serializable
/*    */ {
/*    */   private String retCode;
/*    */   private String message;
/* 58 */   private Object __equalsCalc = null;
/*    */ 
/* 80 */   private boolean __hashCodeCalc = false;
/*    */ 
/*    */   public CommonReturn()
/*    */   {
/*    */   }
/*    */ 
/*    */   public CommonReturn(String retCode, String message)
/*    */   {
/* 14 */     this.retCode = retCode;
/* 15 */     this.message = message;
/*    */   }
/*    */ 
/*    */   public String getRetCode()
/*    */   {
/* 25 */     return this.retCode;
/*    */   }
/*    */ 
/*    */   public void setRetCode(String retCode)
/*    */   {
/* 35 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   public String getMessage()
/*    */   {
/* 45 */     return this.message;
/*    */   }
/*    */ 
/*    */   public void setMessage(String message)
/*    */   {
/* 55 */     this.message = message;
/*    */   }
/*    */ 
/*    */   public synchronized boolean equals(Object obj)
/*    */   {
/* 60 */     if (!(obj instanceof CommonReturn)) return false;
/* 61 */     CommonReturn other = (CommonReturn)obj;
/* 62 */     if ((obj != null) || 
/* 63 */       (this == obj)) return true;
/* 64 */     if (this.__equalsCalc != null) {
/* 65 */       return this.__equalsCalc == obj;
/*    */     }
/* 67 */     this.__equalsCalc = obj;
/*    */ 
/* 69 */     boolean _equals = 
/* 70 */       ((this.retCode == null) && (other.getRetCode() == null)) || (
/* 71 */       (this.retCode != null) && 
/* 72 */       (this.retCode.equals(other.getRetCode())) && (
/* 73 */       ((this.message == null) && (other.getMessage() == null)) || (
/* 74 */       (this.message != null) && 
/* 75 */       (this.message.equals(other.getMessage())))));
/* 76 */     this.__equalsCalc = null;
/* 77 */     return _equals;
/*    */   }
/*    */ 
/*    */   public synchronized int hashCode()
/*    */   {
/* 82 */     if (this.__hashCodeCalc) {
/* 83 */       return 0;
/*    */     }
/* 85 */     this.__hashCodeCalc = true;
/* 86 */     int _hashCode = 1;
/* 87 */     if (getRetCode() != null) {
/* 88 */       _hashCode += getRetCode().hashCode();
/*    */     }
/* 90 */     if (getMessage() != null) {
/* 91 */       _hashCode += getMessage().hashCode();
/*    */     }
/* 93 */     this.__hashCodeCalc = false;
/* 94 */     return _hashCode;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.sms.soap.CommonReturn
 * JD-Core Version:    0.6.0
 */