/*     */ package com.htsoft.oa.model.communicate;
/*     */ 
/*     */ import com.htsoft.core.model.BaseModel;
/*     */ import java.util.Date;
/*     */ import org.apache.commons.lang.builder.EqualsBuilder;
/*     */ import org.apache.commons.lang.builder.HashCodeBuilder;
/*     */ import org.apache.commons.lang.builder.ToStringBuilder;
/*     */ 
/*     */ public class SmsMobile extends BaseModel
/*     */ {
/*  22 */   public static final Short STATUS_SENDED = Short.valueOf((short)1);
/*     */ 
/*  26 */   public static final Short STATUS_NOT_SENDED = Short.valueOf((short)0);
/*     */   protected Long smsId;
/*     */   protected Date sendTime;
/*     */   protected String recipients;
/*     */   protected String phoneNumber;
/*     */   protected Long userId;
/*     */   protected String userName;
/*     */   protected String smsContent;
/*     */   protected Short status;
/*     */ 
/*     */   public SmsMobile()
/*     */   {
/*     */   }
/*     */ 
/*     */   public SmsMobile(Long in_smsId)
/*     */   {
/*  51 */     setSmsId(in_smsId);
/*     */   }
/*     */ 
/*     */   public Long getSmsId()
/*     */   {
/*  61 */     return this.smsId;
/*     */   }
/*     */ 
/*     */   public void setSmsId(Long aValue)
/*     */   {
/*  68 */     this.smsId = aValue;
/*     */   }
/*     */ 
/*     */   public Date getSendTime()
/*     */   {
/*  76 */     return this.sendTime;
/*     */   }
/*     */ 
/*     */   public void setSendTime(Date aValue)
/*     */   {
/*  84 */     this.sendTime = aValue;
/*     */   }
/*     */ 
/*     */   public String getRecipients()
/*     */   {
/*  92 */     return this.recipients;
/*     */   }
/*     */ 
/*     */   public void setRecipients(String aValue)
/*     */   {
/*  99 */     this.recipients = aValue;
/*     */   }
/*     */ 
/*     */   public String getPhoneNumber()
/*     */   {
/* 107 */     return this.phoneNumber;
/*     */   }
/*     */ 
/*     */   public void setPhoneNumber(String aValue)
/*     */   {
/* 115 */     this.phoneNumber = aValue;
/*     */   }
/*     */ 
/*     */   public Long getUserId()
/*     */   {
/* 123 */     return this.userId;
/*     */   }
/*     */ 
/*     */   public void setUserId(Long aValue)
/*     */   {
/* 130 */     this.userId = aValue;
/*     */   }
/*     */ 
/*     */   public String getUserName()
/*     */   {
/* 138 */     return this.userName;
/*     */   }
/*     */ 
/*     */   public void setUserName(String aValue)
/*     */   {
/* 145 */     this.userName = aValue;
/*     */   }
/*     */ 
/*     */   public String getSmsContent()
/*     */   {
/* 153 */     return this.smsContent;
/*     */   }
/*     */ 
/*     */   public void setSmsContent(String aValue)
/*     */   {
/* 161 */     this.smsContent = aValue;
/*     */   }
/*     */ 
/*     */   public Short getStatus()
/*     */   {
/* 169 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(Short aValue)
/*     */   {
/* 177 */     this.status = aValue;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 184 */     if (!(object instanceof SmsMobile)) {
/* 185 */       return false;
/*     */     }
/* 187 */     SmsMobile rhs = (SmsMobile)object;
/* 188 */     return new EqualsBuilder()
/* 189 */       .append(this.smsId, rhs.smsId)
/* 190 */       .append(this.sendTime, rhs.sendTime)
/* 191 */       .append(this.recipients, rhs.recipients)
/* 192 */       .append(this.phoneNumber, rhs.phoneNumber)
/* 193 */       .append(this.userId, rhs.userId)
/* 194 */       .append(this.userName, rhs.userName)
/* 195 */       .append(this.smsContent, rhs.smsContent)
/* 196 */       .append(this.status, rhs.status)
/* 197 */       .isEquals();
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 204 */     return new HashCodeBuilder(-82280557, -700257973)
/* 205 */       .append(this.smsId)
/* 206 */       .append(this.sendTime)
/* 207 */       .append(this.recipients)
/* 208 */       .append(this.phoneNumber)
/* 209 */       .append(this.userId)
/* 210 */       .append(this.userName)
/* 211 */       .append(this.smsContent)
/* 212 */       .append(this.status)
/* 213 */       .toHashCode();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 220 */     return new ToStringBuilder(this)
/* 221 */       .append("smsId", this.smsId)
/* 222 */       .append("sendTime", this.sendTime)
/* 223 */       .append("recipients", this.recipients)
/* 224 */       .append("phoneNumber", this.phoneNumber)
/* 225 */       .append("userId", this.userId)
/* 226 */       .append("userName", this.userName)
/* 227 */       .append("smsContent", this.smsContent)
/* 228 */       .append("status", this.status)
/* 229 */       .toString();
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.communicate.SmsMobile
 * JD-Core Version:    0.6.0
 */