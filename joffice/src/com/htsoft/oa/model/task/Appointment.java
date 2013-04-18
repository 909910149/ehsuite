/*     */ package com.htsoft.oa.model.task;
/*     */ 
/*     */ import com.google.gson.annotations.Expose;
/*     */ import com.htsoft.core.model.BaseModel;
/*     */ import com.htsoft.oa.model.system.AppUser;
/*     */ import java.util.Date;
/*     */ import org.apache.commons.lang.builder.EqualsBuilder;
/*     */ import org.apache.commons.lang.builder.HashCodeBuilder;
/*     */ import org.apache.commons.lang.builder.ToStringBuilder;
/*     */ 
/*     */ public class Appointment extends BaseModel
/*     */ {
/*     */ 
/*     */   @Expose
/*     */   protected Long appointId;
/*     */ 
/*     */   @Expose
/*     */   protected String subject;
/*     */ 
/*     */   @Expose
/*     */   protected Date startTime;
/*     */ 
/*     */   @Expose
/*     */   protected Date endTime;
/*     */ 
/*     */   @Expose
/*     */   protected String content;
/*     */ 
/*     */   @Expose
/*     */   protected String notes;
/*     */ 
/*     */   @Expose
/*     */   protected String location;
/*     */ 
/*     */   @Expose
/*     */   protected String inviteEmails;
/*     */ 
/*     */   @Expose
/*     */   protected Short sendMessage;
/*     */ 
/*     */   @Expose
/*     */   protected Short sendMail;
/*     */ 
/*     */   @Expose
/*     */   protected AppUser appUser;
/*     */ 
/*     */   public Short getSendMessage()
/*     */   {
/*  44 */     return this.sendMessage;
/*     */   }
/*     */ 
/*     */   public void setSendMessage(Short sendMessage) {
/*  48 */     this.sendMessage = sendMessage;
/*     */   }
/*     */ 
/*     */   public Short getSendMail() {
/*  52 */     return this.sendMail;
/*     */   }
/*     */ 
/*     */   public void setSendMail(Short sendMail) {
/*  56 */     this.sendMail = sendMail;
/*     */   }
/*     */ 
/*     */   public Appointment()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Appointment(Long in_appointId)
/*     */   {
/*  76 */     setAppointId(in_appointId);
/*     */   }
/*     */ 
/*     */   public AppUser getAppUser()
/*     */   {
/*  81 */     return this.appUser;
/*     */   }
/*     */ 
/*     */   public void setAppUser(AppUser in_appUser) {
/*  85 */     this.appUser = in_appUser;
/*     */   }
/*     */ 
/*     */   public Long getAppointId()
/*     */   {
/*  94 */     return this.appointId;
/*     */   }
/*     */ 
/*     */   public void setAppointId(Long aValue)
/*     */   {
/* 101 */     this.appointId = aValue;
/*     */   }
/*     */ 
/*     */   public Long getUserId()
/*     */   {
/* 108 */     return getAppUser() == null ? null : getAppUser().getUserId();
/*     */   }
/*     */ 
/*     */   public void setUserId(Long aValue)
/*     */   {
/* 115 */     if (aValue == null) {
/* 116 */       this.appUser = null;
/* 117 */     } else if (this.appUser == null) {
/* 118 */       this.appUser = new AppUser(aValue);
/* 119 */       this.appUser.setVersion(new Integer(0));
/*     */     } else {
/* 121 */       this.appUser.setUserId(aValue);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getSubject()
/*     */   {
/* 130 */     return this.subject;
/*     */   }
/*     */ 
/*     */   public void setSubject(String aValue)
/*     */   {
/* 138 */     this.subject = aValue;
/*     */   }
/*     */ 
/*     */   public Date getStartTime()
/*     */   {
/* 146 */     return this.startTime;
/*     */   }
/*     */ 
/*     */   public void setStartTime(Date aValue)
/*     */   {
/* 154 */     this.startTime = aValue;
/*     */   }
/*     */ 
/*     */   public Date getEndTime()
/*     */   {
/* 162 */     return this.endTime;
/*     */   }
/*     */ 
/*     */   public void setEndTime(Date aValue)
/*     */   {
/* 170 */     this.endTime = aValue;
/*     */   }
/*     */ 
/*     */   public String getContent()
/*     */   {
/* 178 */     return this.content;
/*     */   }
/*     */ 
/*     */   public void setContent(String aValue)
/*     */   {
/* 186 */     this.content = aValue;
/*     */   }
/*     */ 
/*     */   public String getNotes()
/*     */   {
/* 194 */     return this.notes;
/*     */   }
/*     */ 
/*     */   public void setNotes(String aValue)
/*     */   {
/* 201 */     this.notes = aValue;
/*     */   }
/*     */ 
/*     */   public String getLocation()
/*     */   {
/* 209 */     return this.location;
/*     */   }
/*     */ 
/*     */   public void setLocation(String aValue)
/*     */   {
/* 217 */     this.location = aValue;
/*     */   }
/*     */ 
/*     */   public String getInviteEmails()
/*     */   {
/* 225 */     return this.inviteEmails;
/*     */   }
/*     */ 
/*     */   public void setInviteEmails(String aValue)
/*     */   {
/* 232 */     this.inviteEmails = aValue;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 239 */     if (!(object instanceof Appointment)) {
/* 240 */       return false;
/*     */     }
/* 242 */     Appointment rhs = (Appointment)object;
/* 243 */     return new EqualsBuilder()
/* 244 */       .append(this.appointId, rhs.appointId)
/* 245 */       .append(this.subject, rhs.subject)
/* 246 */       .append(this.startTime, rhs.startTime)
/* 247 */       .append(this.endTime, rhs.endTime)
/* 248 */       .append(this.content, rhs.content)
/* 249 */       .append(this.notes, rhs.notes)
/* 250 */       .append(this.location, rhs.location)
/* 251 */       .append(this.inviteEmails, rhs.inviteEmails)
/* 252 */       .isEquals();
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 259 */     return new HashCodeBuilder(-82280557, -700257973)
/* 260 */       .append(this.appointId)
/* 261 */       .append(this.subject)
/* 262 */       .append(this.startTime)
/* 263 */       .append(this.endTime)
/* 264 */       .append(this.content)
/* 265 */       .append(this.notes)
/* 266 */       .append(this.location)
/* 267 */       .append(this.inviteEmails)
/* 268 */       .toHashCode();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 275 */     return new ToStringBuilder(this)
/* 276 */       .append("appointId", this.appointId)
/* 277 */       .append("subject", this.subject)
/* 278 */       .append("startTime", this.startTime)
/* 279 */       .append("endTime", this.endTime)
/* 280 */       .append("content", this.content)
/* 281 */       .append("notes", this.notes)
/* 282 */       .append("location", this.location)
/* 283 */       .append("inviteEmails", this.inviteEmails)
/* 284 */       .toString();
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.task.Appointment
 * JD-Core Version:    0.6.0
 */