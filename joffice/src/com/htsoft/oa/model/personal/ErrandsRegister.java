/*     */ package com.htsoft.oa.model.personal;
/*     */ 
/*     */ import com.htsoft.core.model.BaseModel;
/*     */ import com.htsoft.oa.model.system.AppUser;
/*     */ import java.util.Date;
/*     */ import org.apache.commons.lang.builder.EqualsBuilder;
/*     */ import org.apache.commons.lang.builder.HashCodeBuilder;
/*     */ import org.apache.commons.lang.builder.ToStringBuilder;
/*     */ 
/*     */ public class ErrandsRegister extends BaseModel
/*     */ {
/*     */   protected Long dateId;
/*     */   protected Long approvalId;
/*     */   protected String descp;
/*     */   protected Date startTime;
/*     */   protected Date endTime;
/*     */   protected Short status;
/*     */   protected String approvalOption;
/*     */   protected String approvalName;
/*     */   protected Short flag;
/*     */   protected Long runId;
/*     */   protected AppUser appUser;
/*  35 */   public static final Short STATUS_UNCHECKED = Short.valueOf((short)0);
/*     */ 
/*  39 */   public static final Short STATUS_APPROVAL = Short.valueOf((short)1);
/*     */ 
/*  43 */   public static final Short STATUS_UNAPPROVAL = Short.valueOf((short)2);
/*     */ 
/*  46 */   public static final Short FLAG_OVERTIME = Short.valueOf((short)0);
/*     */ 
/*  48 */   public static final Short FLAG_LEAVE = Short.valueOf((short)1);
/*     */ 
/*  50 */   public static final Short FLAG_OUT = Short.valueOf((short)2);
/*     */ 
/*     */   public ErrandsRegister()
/*     */   {
/*     */   }
/*     */ 
/*     */   public ErrandsRegister(Long in_dateId)
/*     */   {
/*  65 */     setDateId(in_dateId);
/*     */   }
/*     */ 
/*     */   public AppUser getAppUser()
/*     */   {
/*  70 */     return this.appUser;
/*     */   }
/*     */ 
/*     */   public void setAppUser(AppUser in_appUser) {
/*  74 */     this.appUser = in_appUser;
/*     */   }
/*     */ 
/*     */   public Long getDateId()
/*     */   {
/*  83 */     return this.dateId;
/*     */   }
/*     */ 
/*     */   public void setDateId(Long aValue)
/*     */   {
/*  90 */     this.dateId = aValue;
/*     */   }
/*     */ 
/*     */   public Long getUserId()
/*     */   {
/*  98 */     return getAppUser() == null ? null : getAppUser().getUserId();
/*     */   }
/*     */ 
/*     */   public void setUserId(Long aValue)
/*     */   {
/* 106 */     if (aValue == null) {
/* 107 */       this.appUser = null;
/* 108 */     } else if (this.appUser == null) {
/* 109 */       this.appUser = new AppUser(aValue);
/* 110 */       this.appUser.setVersion(new Integer(0));
/*     */     } else {
/* 112 */       this.appUser.setUserId(aValue);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getDescp()
/*     */   {
/* 121 */     return this.descp;
/*     */   }
/*     */ 
/*     */   public void setDescp(String aValue)
/*     */   {
/* 129 */     this.descp = aValue;
/*     */   }
/*     */ 
/*     */   public Date getStartTime()
/*     */   {
/* 137 */     return this.startTime;
/*     */   }
/*     */ 
/*     */   public void setStartTime(Date aValue)
/*     */   {
/* 145 */     this.startTime = aValue;
/*     */   }
/*     */ 
/*     */   public Date getEndTime()
/*     */   {
/* 153 */     return this.endTime;
/*     */   }
/*     */ 
/*     */   public void setEndTime(Date aValue)
/*     */   {
/* 161 */     this.endTime = aValue;
/*     */   }
/*     */ 
/*     */   public Long getApprovalId()
/*     */   {
/* 168 */     return this.approvalId;
/*     */   }
/*     */ 
/*     */   public void setApprovalId(Long aValue)
/*     */   {
/* 175 */     this.approvalId = aValue;
/*     */   }
/*     */ 
/*     */   public Short getStatus()
/*     */   {
/* 183 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(Short aValue)
/*     */   {
/* 191 */     this.status = aValue;
/*     */   }
/*     */ 
/*     */   public String getApprovalOption()
/*     */   {
/* 199 */     return this.approvalOption;
/*     */   }
/*     */ 
/*     */   public void setApprovalOption(String aValue)
/*     */   {
/* 206 */     this.approvalOption = aValue;
/*     */   }
/*     */ 
/*     */   public String getApprovalName()
/*     */   {
/* 214 */     return this.approvalName;
/*     */   }
/*     */ 
/*     */   public void setApprovalName(String aValue)
/*     */   {
/* 222 */     this.approvalName = aValue;
/*     */   }
/*     */ 
/*     */   public Short getFlag()
/*     */   {
/* 232 */     return this.flag;
/*     */   }
/*     */ 
/*     */   public void setFlag(Short aValue)
/*     */   {
/* 239 */     this.flag = aValue;
/*     */   }
/*     */ 
/*     */   public Long getRunId() {
/* 243 */     return this.runId;
/*     */   }
/*     */ 
/*     */   public void setRunId(Long runId) {
/* 247 */     this.runId = runId;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 254 */     if (!(object instanceof ErrandsRegister)) {
/* 255 */       return false;
/*     */     }
/* 257 */     ErrandsRegister rhs = (ErrandsRegister)object;
/* 258 */     return new EqualsBuilder()
/* 259 */       .append(this.dateId, rhs.dateId)
/* 260 */       .append(this.approvalId, rhs.approvalId)
/* 261 */       .append(this.descp, rhs.descp)
/* 262 */       .append(this.startTime, rhs.startTime)
/* 263 */       .append(this.endTime, rhs.endTime)
/* 264 */       .append(this.status, rhs.status)
/* 265 */       .append(this.approvalOption, rhs.approvalOption)
/* 266 */       .append(this.approvalName, rhs.approvalName)
/* 267 */       .append(this.flag, rhs.flag)
/* 268 */       .append(this.runId, rhs.runId)
/* 269 */       .isEquals();
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 276 */     return new HashCodeBuilder(-82280557, -700257973)
/* 277 */       .append(this.dateId)
/* 278 */       .append(this.approvalId)
/* 279 */       .append(this.descp)
/* 280 */       .append(this.startTime)
/* 281 */       .append(this.endTime)
/* 282 */       .append(this.status)
/* 283 */       .append(this.approvalOption)
/* 284 */       .append(this.approvalName)
/* 285 */       .append(this.flag)
/* 286 */       .append(this.runId)
/* 287 */       .toHashCode();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 294 */     return new ToStringBuilder(this)
/* 295 */       .append("dateId", this.dateId)
/* 296 */       .append("userId", this.approvalId)
/* 297 */       .append("descp", this.descp)
/* 298 */       .append("startTime", this.startTime)
/* 299 */       .append("endTime", this.endTime)
/* 300 */       .append("status", this.status)
/* 301 */       .append("approvalOption", this.approvalOption)
/* 302 */       .append("approvalName", this.approvalName)
/* 303 */       .append("flag", this.flag)
/* 304 */       .append("runId", this.runId)
/* 305 */       .toString();
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.personal.ErrandsRegister
 * JD-Core Version:    0.6.0
 */