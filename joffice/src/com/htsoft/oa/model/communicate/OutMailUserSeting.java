/*     */ package com.htsoft.oa.model.communicate;
/*     */ 
/*     */ import com.google.gson.annotations.Expose;
/*     */ import com.htsoft.core.model.BaseModel;
/*     */ import com.htsoft.oa.model.system.AppUser;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang.builder.EqualsBuilder;
/*     */ import org.apache.commons.lang.builder.HashCodeBuilder;
/*     */ import org.apache.commons.lang.builder.ToStringBuilder;
/*     */ 
/*     */ public class OutMailUserSeting extends BaseModel
/*     */ {
/*     */ 
/*     */   @Expose
/*     */   protected Long id;
/*     */ 
/*     */   @Expose
/*     */   protected String userName;
/*     */ 
/*     */   @Expose
/*     */   protected Long reuserId;
/*     */ 
/*     */   @Expose
/*     */   protected String mailAddress;
/*     */ 
/*     */   @Expose
/*     */   protected String mailPass;
/*     */ 
/*     */   @Expose
/*     */   protected String smtpHost;
/*     */ 
/*     */   @Expose
/*     */   protected String smtpPort;
/*     */ 
/*     */   @Expose
/*     */   protected String popHost;
/*     */ 
/*     */   @Expose
/*     */   protected String popPort;
/*     */ 
/*     */   @Expose
/*     */   protected AppUser appUser;
/*     */ 
/*     */   @Expose
/*  43 */   protected Set<OutMail> outMails = new HashSet();
/*     */ 
/*     */   public OutMailUserSeting()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Long getReuserId()
/*     */   {
/*  53 */     return this.reuserId;
/*     */   }
/*     */ 
/*     */   public void setReuserId(Long reuserId) {
/*  57 */     this.reuserId = reuserId;
/*     */   }
/*     */ 
/*     */   public OutMailUserSeting(Long in_id)
/*     */   {
/*  66 */     setId(in_id);
/*     */   }
/*     */ 
/*     */   public AppUser getAppUser()
/*     */   {
/*  71 */     return this.appUser;
/*     */   }
/*     */ 
/*     */   public void setAppUser(AppUser in_appUser) {
/*  75 */     this.appUser = in_appUser;
/*     */   }
/*     */ 
/*     */   public Set<OutMail> getOutMails() {
/*  79 */     return this.outMails;
/*     */   }
/*     */ 
/*     */   public void setOutMails(Set<OutMail> in_outMails) {
/*  83 */     this.outMails = in_outMails;
/*     */   }
/*     */ 
/*     */   public Long getId()
/*     */   {
/*  92 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Long aValue)
/*     */   {
/*  99 */     this.id = aValue;
/*     */   }
/*     */ 
/*     */   public Long getUserId()
/*     */   {
/* 106 */     return getAppUser() == null ? null : getAppUser().getUserId();
/*     */   }
/*     */ 
/*     */   public void setUserId(Long aValue)
/*     */   {
/* 113 */     if (aValue == null) {
/* 114 */       this.appUser = null;
/* 115 */     } else if (this.appUser == null) {
/* 116 */       this.appUser = new AppUser(aValue);
/* 117 */       this.appUser.setVersion(new Integer(0));
/*     */     } else {
/* 119 */       this.appUser.setUserId(aValue);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getUserName()
/*     */   {
/* 128 */     return this.userName;
/*     */   }
/*     */ 
/*     */   public void setUserName(String aValue)
/*     */   {
/* 136 */     this.userName = aValue;
/*     */   }
/*     */ 
/*     */   public String getMailAddress()
/*     */   {
/* 144 */     return this.mailAddress;
/*     */   }
/*     */ 
/*     */   public void setMailAddress(String aValue)
/*     */   {
/* 152 */     this.mailAddress = aValue;
/*     */   }
/*     */ 
/*     */   public String getMailPass()
/*     */   {
/* 160 */     return this.mailPass;
/*     */   }
/*     */ 
/*     */   public void setMailPass(String aValue)
/*     */   {
/* 168 */     this.mailPass = aValue;
/*     */   }
/*     */ 
/*     */   public String getSmtpHost()
/*     */   {
/* 176 */     return this.smtpHost;
/*     */   }
/*     */ 
/*     */   public void setSmtpHost(String aValue)
/*     */   {
/* 184 */     this.smtpHost = aValue;
/*     */   }
/*     */ 
/*     */   public String getSmtpPort()
/*     */   {
/* 192 */     return this.smtpPort;
/*     */   }
/*     */ 
/*     */   public void setSmtpPort(String aValue)
/*     */   {
/* 200 */     this.smtpPort = aValue;
/*     */   }
/*     */ 
/*     */   public String getPopHost()
/*     */   {
/* 208 */     return this.popHost;
/*     */   }
/*     */ 
/*     */   public void setPopHost(String aValue)
/*     */   {
/* 216 */     this.popHost = aValue;
/*     */   }
/*     */ 
/*     */   public String getPopPort()
/*     */   {
/* 224 */     return this.popPort;
/*     */   }
/*     */ 
/*     */   public void setPopPort(String aValue)
/*     */   {
/* 232 */     this.popPort = aValue;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 239 */     if (!(object instanceof OutMailUserSeting)) {
/* 240 */       return false;
/*     */     }
/* 242 */     OutMailUserSeting rhs = (OutMailUserSeting)object;
/* 243 */     return new EqualsBuilder()
/* 244 */       .append(this.id, rhs.id)
/* 245 */       .append(this.userName, rhs.userName)
/* 246 */       .append(this.mailAddress, rhs.mailAddress)
/* 247 */       .append(this.mailPass, rhs.mailPass)
/* 248 */       .append(this.smtpHost, rhs.smtpHost)
/* 249 */       .append(this.smtpPort, rhs.smtpPort)
/* 250 */       .append(this.popHost, rhs.popHost)
/* 251 */       .append(this.popPort, rhs.popPort)
/* 252 */       .isEquals();
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 259 */     return new HashCodeBuilder(-82280557, -700257973)
/* 260 */       .append(this.id)
/* 261 */       .append(this.userName)
/* 262 */       .append(this.mailAddress)
/* 263 */       .append(this.mailPass)
/* 264 */       .append(this.smtpHost)
/* 265 */       .append(this.smtpPort)
/* 266 */       .append(this.popHost)
/* 267 */       .append(this.popPort)
/* 268 */       .toHashCode();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 275 */     return new ToStringBuilder(this)
/* 276 */       .append("id", this.id)
/* 277 */       .append("userName", this.userName)
/* 278 */       .append("mailAddress", this.mailAddress)
/* 279 */       .append("mailPass", this.mailPass)
/* 280 */       .append("smtpHost", this.smtpHost)
/* 281 */       .append("smtpPort", this.smtpPort)
/* 282 */       .append("popHost", this.popHost)
/* 283 */       .append("popPort", this.popPort)
/* 284 */       .toString();
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.communicate.OutMailUserSeting
 * JD-Core Version:    0.6.0
 */