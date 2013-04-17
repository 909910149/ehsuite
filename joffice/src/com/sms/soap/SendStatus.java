/*     */ package com.sms.soap;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class SendStatus
/*     */   implements Serializable
/*     */ {
/*     */   private String retCode;
/*     */   private String message;
/*     */   private int jobID;
/*     */   private int OKPhoneCounts;
/*     */   private int stockReduced;
/*     */   private String errPhones;
/* 149 */   private Object __equalsCalc = null;
/*     */ 
/* 177 */   private boolean __hashCodeCalc = false;
/*     */ 
/*     */   public SendStatus()
/*     */   {
/*     */   }
/*     */ 
/*     */   public SendStatus(String retCode, String message, int jobID, int OKPhoneCounts, int stockReduced, String errPhones)
/*     */   {
/*  21 */     this.retCode = retCode;
/*  22 */     this.message = message;
/*  23 */     this.jobID = jobID;
/*  24 */     this.OKPhoneCounts = OKPhoneCounts;
/*  25 */     this.stockReduced = stockReduced;
/*  26 */     this.errPhones = errPhones;
/*     */   }
/*     */ 
/*     */   public String getRetCode()
/*     */   {
/*  36 */     return this.retCode;
/*     */   }
/*     */ 
/*     */   public void setRetCode(String retCode)
/*     */   {
/*  46 */     this.retCode = retCode;
/*     */   }
/*     */ 
/*     */   public String getMessage()
/*     */   {
/*  56 */     return this.message;
/*     */   }
/*     */ 
/*     */   public void setMessage(String message)
/*     */   {
/*  66 */     this.message = message;
/*     */   }
/*     */ 
/*     */   public int getJobID()
/*     */   {
/*  76 */     return this.jobID;
/*     */   }
/*     */ 
/*     */   public void setJobID(int jobID)
/*     */   {
/*  86 */     this.jobID = jobID;
/*     */   }
/*     */ 
/*     */   public int getOKPhoneCounts()
/*     */   {
/*  96 */     return this.OKPhoneCounts;
/*     */   }
/*     */ 
/*     */   public void setOKPhoneCounts(int OKPhoneCounts)
/*     */   {
/* 106 */     this.OKPhoneCounts = OKPhoneCounts;
/*     */   }
/*     */ 
/*     */   public int getStockReduced()
/*     */   {
/* 116 */     return this.stockReduced;
/*     */   }
/*     */ 
/*     */   public void setStockReduced(int stockReduced)
/*     */   {
/* 126 */     this.stockReduced = stockReduced;
/*     */   }
/*     */ 
/*     */   public String getErrPhones()
/*     */   {
/* 136 */     return this.errPhones;
/*     */   }
/*     */ 
/*     */   public void setErrPhones(String errPhones)
/*     */   {
/* 146 */     this.errPhones = errPhones;
/*     */   }
/*     */ 
/*     */   public synchronized boolean equals(Object obj)
/*     */   {
/* 151 */     if (!(obj instanceof SendStatus)) return false;
/* 152 */     SendStatus other = (SendStatus)obj;
/* 153 */     if ((obj != null) || 
/* 154 */       (this == obj)) return true;
/* 155 */     if (this.__equalsCalc != null) {
/* 156 */       return this.__equalsCalc == obj;
/*     */     }
/* 158 */     this.__equalsCalc = obj;
/*     */ 
/* 160 */     boolean _equals = 
/* 161 */       ((this.retCode == null) && (other.getRetCode() == null)) || (
/* 162 */       (this.retCode != null) && 
/* 163 */       (this.retCode.equals(other.getRetCode())) && (
/* 164 */       ((this.message == null) && (other.getMessage() == null)) || (
/* 165 */       (this.message != null) && 
/* 166 */       (this.message.equals(other.getMessage())) && 
/* 167 */       (this.jobID == other.getJobID()) && 
/* 168 */       (this.OKPhoneCounts == other.getOKPhoneCounts()) && 
/* 169 */       (this.stockReduced == other.getStockReduced()) && (
/* 170 */       ((this.errPhones == null) && (other.getErrPhones() == null)) || (
/* 171 */       (this.errPhones != null) && 
/* 172 */       (this.errPhones.equals(other.getErrPhones())))))));
/* 173 */     this.__equalsCalc = null;
/* 174 */     return _equals;
/*     */   }
/*     */ 
/*     */   public synchronized int hashCode()
/*     */   {
/* 179 */     if (this.__hashCodeCalc) {
/* 180 */       return 0;
/*     */     }
/* 182 */     this.__hashCodeCalc = true;
/* 183 */     int _hashCode = 1;
/* 184 */     if (getRetCode() != null) {
/* 185 */       _hashCode += getRetCode().hashCode();
/*     */     }
/* 187 */     if (getMessage() != null) {
/* 188 */       _hashCode += getMessage().hashCode();
/*     */     }
/* 190 */     _hashCode += getJobID();
/* 191 */     _hashCode += getOKPhoneCounts();
/* 192 */     _hashCode += getStockReduced();
/* 193 */     if (getErrPhones() != null) {
/* 194 */       _hashCode += getErrPhones().hashCode();
/*     */     }
/* 196 */     this.__hashCodeCalc = false;
/* 197 */     return _hashCode;
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.sms.soap.SendStatus
 * JD-Core Version:    0.6.0
 */