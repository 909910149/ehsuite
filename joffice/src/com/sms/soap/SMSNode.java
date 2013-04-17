/*     */ package com.sms.soap;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.axis.encoding.SimpleType;
/*     */ 
/*     */ public class SMSNode
/*     */   implements Serializable, SimpleType
/*     */ {
/*     */   private String _value;
/*     */   private String phone;
/*     */   private String recDateTime;
/*     */   private String postFixNumber;
/* 102 */   private Object __equalsCalc = null;
/*     */ 
/* 130 */   private boolean __hashCodeCalc = false;
/*     */ 
/*     */   public SMSNode()
/*     */   {
/*     */   }
/*     */ 
/*     */   public SMSNode(String _value)
/*     */   {
/*  15 */     this._value = _value;
/*     */   }
/*     */ 
/*     */   public String toString() {
/*  19 */     return this._value;
/*     */   }
/*     */ 
/*     */   public String get_value()
/*     */   {
/*  29 */     return this._value;
/*     */   }
/*     */ 
/*     */   public void set_value(String _value)
/*     */   {
/*  39 */     this._value = _value;
/*     */   }
/*     */ 
/*     */   public String getPhone()
/*     */   {
/*  49 */     return this.phone;
/*     */   }
/*     */ 
/*     */   public void setPhone(String phone)
/*     */   {
/*  59 */     this.phone = phone;
/*     */   }
/*     */ 
/*     */   public String getRecDateTime()
/*     */   {
/*  69 */     return this.recDateTime;
/*     */   }
/*     */ 
/*     */   public void setRecDateTime(String recDateTime)
/*     */   {
/*  79 */     this.recDateTime = recDateTime;
/*     */   }
/*     */ 
/*     */   public String getPostFixNumber()
/*     */   {
/*  89 */     return this.postFixNumber;
/*     */   }
/*     */ 
/*     */   public void setPostFixNumber(String postFixNumber)
/*     */   {
/*  99 */     this.postFixNumber = postFixNumber;
/*     */   }
/*     */ 
/*     */   public synchronized boolean equals(Object obj)
/*     */   {
/* 104 */     if (!(obj instanceof SMSNode)) return false;
/* 105 */     SMSNode other = (SMSNode)obj;
/* 106 */     if ((obj != null) || 
/* 107 */       (this == obj)) return true;
/* 108 */     if (this.__equalsCalc != null) {
/* 109 */       return this.__equalsCalc == obj;
/*     */     }
/* 111 */     this.__equalsCalc = obj;
/*     */ 
/* 113 */     boolean _equals = 
/* 114 */       ((this._value == null) && (other.get_value() == null)) || (
/* 115 */       (this._value != null) && 
/* 116 */       (this._value.equals(other.get_value())) && (
/* 117 */       ((this.phone == null) && (other.getPhone() == null)) || (
/* 118 */       (this.phone != null) && 
/* 119 */       (this.phone.equals(other.getPhone())) && (
/* 120 */       ((this.recDateTime == null) && (other.getRecDateTime() == null)) || (
/* 121 */       (this.recDateTime != null) && 
/* 122 */       (this.recDateTime.equals(other.getRecDateTime())) && (
/* 123 */       ((this.postFixNumber == null) && (other.getPostFixNumber() == null)) || (
/* 124 */       (this.postFixNumber != null) && 
/* 125 */       (this.postFixNumber.equals(other.getPostFixNumber())))))))));
/* 126 */     this.__equalsCalc = null;
/* 127 */     return _equals;
/*     */   }
/*     */ 
/*     */   public synchronized int hashCode()
/*     */   {
/* 132 */     if (this.__hashCodeCalc) {
/* 133 */       return 0;
/*     */     }
/* 135 */     this.__hashCodeCalc = true;
/* 136 */     int _hashCode = 1;
/* 137 */     if (get_value() != null) {
/* 138 */       _hashCode += get_value().hashCode();
/*     */     }
/* 140 */     if (getPhone() != null) {
/* 141 */       _hashCode += getPhone().hashCode();
/*     */     }
/* 143 */     if (getRecDateTime() != null) {
/* 144 */       _hashCode += getRecDateTime().hashCode();
/*     */     }
/* 146 */     if (getPostFixNumber() != null) {
/* 147 */       _hashCode += getPostFixNumber().hashCode();
/*     */     }
/* 149 */     this.__hashCodeCalc = false;
/* 150 */     return _hashCode;
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.sms.soap.SMSNode
 * JD-Core Version:    0.6.0
 */