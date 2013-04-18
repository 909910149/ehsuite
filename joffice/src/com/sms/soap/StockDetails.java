/*     */ package com.sms.soap;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class StockDetails
/*     */   implements Serializable
/*     */ {
/*     */   private String retCode;
/*     */   private String message;
/*     */   private int stockRemain;
/*     */   private int points;
/*     */   private int sendTotal;
/*     */   private int curDaySend;
/* 151 */   private Object __equalsCalc = null;
/*     */ 
/* 177 */   private boolean __hashCodeCalc = false;
/*     */ 
/*     */   public StockDetails()
/*     */   {
/*     */   }
/*     */ 
/*     */   public StockDetails(String retCode, String message, int stockRemain, int points, int sendTotal, int curDaySend)
/*     */   {
/*  23 */     this.retCode = retCode;
/*  24 */     this.message = message;
/*  25 */     this.stockRemain = stockRemain;
/*  26 */     this.points = points;
/*  27 */     this.sendTotal = sendTotal;
/*  28 */     this.curDaySend = curDaySend;
/*     */   }
/*     */ 
/*     */   public String getRetCode()
/*     */   {
/*  38 */     return this.retCode;
/*     */   }
/*     */ 
/*     */   public void setRetCode(String retCode)
/*     */   {
/*  48 */     this.retCode = retCode;
/*     */   }
/*     */ 
/*     */   public String getMessage()
/*     */   {
/*  58 */     return this.message;
/*     */   }
/*     */ 
/*     */   public void setMessage(String message)
/*     */   {
/*  68 */     this.message = message;
/*     */   }
/*     */ 
/*     */   public int getStockRemain()
/*     */   {
/*  78 */     return this.stockRemain;
/*     */   }
/*     */ 
/*     */   public void setStockRemain(int stockRemain)
/*     */   {
/*  88 */     this.stockRemain = stockRemain;
/*     */   }
/*     */ 
/*     */   public int getPoints()
/*     */   {
/*  98 */     return this.points;
/*     */   }
/*     */ 
/*     */   public void setPoints(int points)
/*     */   {
/* 108 */     this.points = points;
/*     */   }
/*     */ 
/*     */   public int getSendTotal()
/*     */   {
/* 118 */     return this.sendTotal;
/*     */   }
/*     */ 
/*     */   public void setSendTotal(int sendTotal)
/*     */   {
/* 128 */     this.sendTotal = sendTotal;
/*     */   }
/*     */ 
/*     */   public int getCurDaySend()
/*     */   {
/* 138 */     return this.curDaySend;
/*     */   }
/*     */ 
/*     */   public void setCurDaySend(int curDaySend)
/*     */   {
/* 148 */     this.curDaySend = curDaySend;
/*     */   }
/*     */ 
/*     */   public synchronized boolean equals(Object obj)
/*     */   {
/* 153 */     if (!(obj instanceof StockDetails)) return false;
/* 154 */     StockDetails other = (StockDetails)obj;
/* 155 */     if ((obj != null) || 
/* 156 */       (this == obj)) return true;
/* 157 */     if (this.__equalsCalc != null) {
/* 158 */       return this.__equalsCalc == obj;
/*     */     }
/* 160 */     this.__equalsCalc = obj;
/*     */ 
/* 162 */     boolean _equals = 
/* 163 */       ((this.retCode == null) && (other.getRetCode() == null)) || (
/* 164 */       (this.retCode != null) && 
/* 165 */       (this.retCode.equals(other.getRetCode())) && (
/* 166 */       ((this.message == null) && (other.getMessage() == null)) || (
/* 167 */       (this.message != null) && 
/* 168 */       (this.message.equals(other.getMessage())) && 
/* 169 */       (this.stockRemain == other.getStockRemain()) && 
/* 170 */       (this.points == other.getPoints()) && 
/* 171 */       (this.sendTotal == other.getSendTotal()) && 
/* 172 */       (this.curDaySend == other.getCurDaySend()))));
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
/* 190 */     _hashCode += getStockRemain();
/* 191 */     _hashCode += getPoints();
/* 192 */     _hashCode += getSendTotal();
/* 193 */     _hashCode += getCurDaySend();
/* 194 */     this.__hashCodeCalc = false;
/* 195 */     return _hashCode;
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.sms.soap.StockDetails
 * JD-Core Version:    0.6.0
 */