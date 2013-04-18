/*     */ package com.htsoft.oa.model.communicate;
/*     */ 
/*     */ import com.htsoft.core.model.BaseModel;
/*     */ import java.util.Date;
/*     */ import org.apache.commons.lang.builder.EqualsBuilder;
/*     */ import org.apache.commons.lang.builder.HashCodeBuilder;
/*     */ import org.apache.commons.lang.builder.ToStringBuilder;
/*     */ 
/*     */ public class SmsHistory extends BaseModel
/*     */ {
/*     */   protected Long smsId;
/*     */   protected Date sendTime;
/*     */   protected String recipients;
/*     */   protected String phoneNumber;
/*     */   protected Long userId;
/*     */   protected String userName;
/*     */   protected String smsContent;
/*     */   protected Short status;
/*     */ 
/*     */   public SmsHistory()
/*     */   {
/*     */   }
/*     */ 
/*     */   public SmsHistory(Long in_smsId)
/*     */   {
/*  42 */     setSmsId(in_smsId);
/*     */   }
/*     */ 
/*     */   public Long getSmsId()
/*     */   {
/*  52 */     return this.smsId;
/*     */   }
/*     */ 
/*     */   public void setSmsId(Long aValue)
/*     */   {
/*  59 */     this.smsId = aValue;
/*     */   }
/*     */ 
/*     */   public Date getSendTime()
/*     */   {
/*  67 */     return this.sendTime;
/*     */   }
/*     */ 
/*     */   public void setSendTime(Date aValue)
/*     */   {
/*  75 */     this.sendTime = aValue;
/*     */   }
/*     */ 
/*     */   public String getRecipients()
/*     */   {
/*  83 */     return this.recipients;
/*     */   }
/*     */ 
/*     */   public void setRecipients(String aValue)
/*     */   {
/*  90 */     this.recipients = aValue;
/*     */   }
/*     */ 
/*     */   public String getPhoneNumber()
/*     */   {
/*  98 */     return this.phoneNumber;
/*     */   }
/*     */ 
/*     */   public void setPhoneNumber(String aValue)
/*     */   {
/* 106 */     this.phoneNumber = aValue;
/*     */   }
/*     */ 
/*     */   public Long getUserId()
/*     */   {
/* 114 */     return this.userId;
/*     */   }
/*     */ 
/*     */   public void setUserId(Long aValue)
/*     */   {
/* 121 */     this.userId = aValue;
/*     */   }
/*     */ 
/*     */   public String getUserName()
/*     */   {
/* 129 */     return this.userName;
/*     */   }
/*     */ 
/*     */   public void setUserName(String aValue)
/*     */   {
/* 136 */     this.userName = aValue;
/*     */   }
/*     */ 
/*     */   public String getSmsContent()
/*     */   {
/* 144 */     return this.smsContent;
/*     */   }
/*     */ 
/*     */   public void setSmsContent(String aValue)
/*     */   {
/* 152 */     this.smsContent = aValue;
/*     */   }
/*     */ 
/*     */   public Short getStatus()
/*     */   {
/* 160 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(Short aValue)
/*     */   {
/* 168 */     this.status = aValue;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 175 */     if (!(object instanceof SmsHistory)) {
/* 176 */       return false;
/*     */     }
/* 178 */     SmsHistory rhs = (SmsHistory)object;
/* 179 */     return new EqualsBuilder()
/* 180 */       .append(this.smsId, rhs.smsId)
/* 181 */       .append(this.sendTime, rhs.sendTime)
/* 182 */       .append(this.recipients, rhs.recipients)
/* 183 */       .append(this.phoneNumber, rhs.phoneNumber)
/* 184 */       .append(this.userId, rhs.userId)
/* 185 */       .append(this.userName, rhs.userName)
/* 186 */       .append(this.smsContent, rhs.smsContent)
/* 187 */       .append(this.status, rhs.status)
/* 188 */       .isEquals();
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 195 */     return new HashCodeBuilder(-82280557, -700257973)
/* 196 */       .append(this.smsId)
/* 197 */       .append(this.sendTime)
/* 198 */       .append(this.recipients)
/* 199 */       .append(this.phoneNumber)
/* 200 */       .append(this.userId)
/* 201 */       .append(this.userName)
/* 202 */       .append(this.smsContent)
/* 203 */       .append(this.status)
/* 204 */       .toHashCode();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 211 */     return new ToStringBuilder(this)
/* 212 */       .append("smsId", this.smsId)
/* 213 */       .append("sendTime", this.sendTime)
/* 214 */       .append("recipients", this.recipients)
/* 215 */       .append("phoneNumber", this.phoneNumber)
/* 216 */       .append("userId", this.userId)
/* 217 */       .append("userName", this.userName)
/* 218 */       .append("smsContent", this.smsContent)
/* 219 */       .append("status", this.status)
/* 220 */       .toString();
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.communicate.SmsHistory
 * JD-Core Version:    0.6.0
 */