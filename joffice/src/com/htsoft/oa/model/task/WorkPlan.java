/*     */ package com.htsoft.oa.model.task;
/*     */ 
/*     */ import com.google.gson.annotations.Expose;
/*     */ import com.htsoft.core.model.BaseModel;
/*     */ import com.htsoft.oa.model.system.AppUser;
/*     */ import com.htsoft.oa.model.system.FileAttach;
/*     */ import com.htsoft.oa.model.system.GlobalType;
/*     */ import java.util.Date;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang.builder.EqualsBuilder;
/*     */ import org.apache.commons.lang.builder.HashCodeBuilder;
/*     */ import org.apache.commons.lang.builder.ToStringBuilder;
/*     */ 
/*     */ public class WorkPlan extends BaseModel
/*     */ {
/*     */ 
/*     */   @Expose
/*     */   protected Long planId;
/*     */ 
/*     */   @Expose
/*     */   protected String planName;
/*     */ 
/*     */   @Expose
/*     */   protected String planContent;
/*     */ 
/*     */   @Expose
/*     */   protected Date startTime;
/*     */ 
/*     */   @Expose
/*     */   protected Date endTime;
/*     */ 
/*     */   @Expose
/*     */   protected String issueScope;
/*     */ 
/*     */   @Expose
/*     */   protected String participants;
/*     */ 
/*     */   @Expose
/*     */   protected String principal;
/*     */ 
/*     */   @Expose
/*     */   protected String note;
/*     */ 
/*     */   @Expose
/*     */   protected Short status;
/*     */ 
/*     */   @Expose
/*     */   protected Short isPersonal;
/*     */ 
/*     */   @Expose
/*     */   protected String icon;
/*     */ 
/*     */   @Expose
/*     */   protected String typeName;
/*     */ 
/*     */   @Expose
/*     */   protected GlobalType globalType;
/*     */ 
/*     */   @Expose
/*     */   protected AppUser appUser;
/*     */ 
/*     */   @Expose
/*  60 */   protected Set<FileAttach> planFiles = new HashSet();
/*  61 */   protected Set<PlanAttend> planAttends = new HashSet();
/*     */ 
/*     */   public WorkPlan()
/*     */   {
/*     */   }
/*     */ 
/*     */   public WorkPlan(Long in_planId)
/*     */   {
/*  76 */     setPlanId(in_planId);
/*     */   }
/*     */ 
/*     */   public Set<PlanAttend> getPlanAttends()
/*     */   {
/*  81 */     return this.planAttends;
/*     */   }
/*     */ 
/*     */   public void setPlanAttends(Set<PlanAttend> planAttends) {
/*  85 */     this.planAttends = planAttends;
/*     */   }
/*     */ 
/*     */   public AppUser getAppUser()
/*     */   {
/*  97 */     return this.appUser;
/*     */   }
/*     */ 
/*     */   public void setAppUser(AppUser in_appUser) {
/* 101 */     this.appUser = in_appUser;
/*     */   }
/*     */ 
/*     */   public Set<FileAttach> getPlanFiles() {
/* 105 */     return this.planFiles;
/*     */   }
/*     */ 
/*     */   public void setPlanFiles(Set<FileAttach> in_planFiles) {
/* 109 */     this.planFiles = in_planFiles;
/*     */   }
/*     */ 
/*     */   public Long getPlanId()
/*     */   {
/* 118 */     return this.planId;
/*     */   }
/*     */ 
/*     */   public void setPlanId(Long aValue)
/*     */   {
/* 125 */     this.planId = aValue;
/*     */   }
/*     */ 
/*     */   public String getPlanName()
/*     */   {
/* 133 */     return this.planName;
/*     */   }
/*     */ 
/*     */   public void setPlanName(String aValue)
/*     */   {
/* 141 */     this.planName = aValue;
/*     */   }
/*     */ 
/*     */   public String getPlanContent()
/*     */   {
/* 149 */     return this.planContent;
/*     */   }
/*     */ 
/*     */   public void setPlanContent(String aValue)
/*     */   {
/* 156 */     this.planContent = aValue;
/*     */   }
/*     */ 
/*     */   public Date getStartTime()
/*     */   {
/* 164 */     return this.startTime;
/*     */   }
/*     */ 
/*     */   public void setStartTime(Date aValue)
/*     */   {
/* 172 */     this.startTime = aValue;
/*     */   }
/*     */ 
/*     */   public Date getEndTime()
/*     */   {
/* 180 */     return this.endTime;
/*     */   }
/*     */ 
/*     */   public void setEndTime(Date aValue)
/*     */   {
/* 188 */     this.endTime = aValue;
/*     */   }
/*     */ 
/*     */   public Long getUserId()
/*     */   {
/* 216 */     return getAppUser() == null ? null : getAppUser().getUserId();
/*     */   }
/*     */ 
/*     */   public void setUserId(Long aValue)
/*     */   {
/* 223 */     if (aValue == null) {
/* 224 */       this.appUser = null;
/* 225 */     } else if (this.appUser == null) {
/* 226 */       this.appUser = new AppUser(aValue);
/* 227 */       this.appUser.setVersion(new Integer(0));
/*     */     } else {
/* 229 */       this.appUser.setUserId(aValue);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getIssueScope()
/*     */   {
/* 241 */     return this.issueScope;
/*     */   }
/*     */ 
/*     */   public void setIssueScope(String aValue)
/*     */   {
/* 248 */     this.issueScope = aValue;
/*     */   }
/*     */ 
/*     */   public String getParticipants()
/*     */   {
/* 258 */     return this.participants;
/*     */   }
/*     */ 
/*     */   public void setParticipants(String aValue)
/*     */   {
/* 265 */     this.participants = aValue;
/*     */   }
/*     */ 
/*     */   public String getPrincipal()
/*     */   {
/* 273 */     return this.principal;
/*     */   }
/*     */ 
/*     */   public void setPrincipal(String aValue)
/*     */   {
/* 281 */     this.principal = aValue;
/*     */   }
/*     */ 
/*     */   public String getNote()
/*     */   {
/* 289 */     return this.note;
/*     */   }
/*     */ 
/*     */   public void setNote(String aValue)
/*     */   {
/* 296 */     this.note = aValue;
/*     */   }
/*     */ 
/*     */   public Short getStatus()
/*     */   {
/* 306 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(Short aValue)
/*     */   {
/* 314 */     this.status = aValue;
/*     */   }
/*     */ 
/*     */   public Short getIsPersonal()
/*     */   {
/* 324 */     return this.isPersonal;
/*     */   }
/*     */ 
/*     */   public void setIsPersonal(Short aValue)
/*     */   {
/* 332 */     this.isPersonal = aValue;
/*     */   }
/*     */ 
/*     */   public String getIcon()
/*     */   {
/* 340 */     return this.icon;
/*     */   }
/*     */ 
/*     */   public void setIcon(String aValue)
/*     */   {
/* 347 */     this.icon = aValue;
/*     */   }
/*     */ 
/*     */   public Long getProTypeId() {
/* 351 */     return this.globalType == null ? null : this.globalType.getProTypeId();
/*     */   }
/*     */ 
/*     */   public void setProTypeId(Long typeId) {
/* 355 */     if (typeId == null) {
/* 356 */       this.globalType = null;
/* 357 */     } else if (this.globalType == null) {
/* 358 */       this.globalType = new GlobalType(typeId);
/* 359 */       this.globalType.setVersion(new Integer(0));
/*     */     } else {
/* 361 */       this.globalType.setProTypeId(typeId);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getTypeName()
/*     */   {
/* 368 */     return this.typeName;
/*     */   }
/*     */ 
/*     */   public void setTypeName(String typeName) {
/* 372 */     this.typeName = typeName;
/*     */   }
/*     */ 
/*     */   public GlobalType getGlobalType() {
/* 376 */     return this.globalType;
/*     */   }
/*     */ 
/*     */   public void setGlobalType(GlobalType globalType) {
/* 380 */     this.globalType = globalType;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 387 */     if (!(object instanceof WorkPlan)) {
/* 388 */       return false;
/*     */     }
/* 390 */     WorkPlan rhs = (WorkPlan)object;
/* 391 */     return new EqualsBuilder()
/* 392 */       .append(this.planId, rhs.planId)
/* 393 */       .append(this.planName, rhs.planName)
/* 394 */       .append(this.planContent, rhs.planContent)
/* 395 */       .append(this.startTime, rhs.startTime)
/* 396 */       .append(this.endTime, rhs.endTime)
/* 397 */       .append(this.issueScope, rhs.issueScope)
/* 398 */       .append(this.participants, rhs.participants)
/* 399 */       .append(this.principal, rhs.principal)
/* 400 */       .append(this.note, rhs.note)
/* 401 */       .append(this.status, rhs.status)
/* 402 */       .append(this.isPersonal, rhs.isPersonal)
/* 403 */       .append(this.icon, rhs.icon)
/* 404 */       .isEquals();
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 411 */     return new HashCodeBuilder(-82280557, -700257973)
/* 412 */       .append(this.planId)
/* 413 */       .append(this.planName)
/* 414 */       .append(this.planContent)
/* 415 */       .append(this.startTime)
/* 416 */       .append(this.endTime)
/* 417 */       .append(this.issueScope)
/* 418 */       .append(this.participants)
/* 419 */       .append(this.principal)
/* 420 */       .append(this.note)
/* 421 */       .append(this.status)
/* 422 */       .append(this.isPersonal)
/* 423 */       .append(this.icon)
/* 424 */       .toHashCode();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 431 */     return new ToStringBuilder(this)
/* 432 */       .append("planId", this.planId)
/* 433 */       .append("planName", this.planName)
/* 434 */       .append("planContent", this.planContent)
/* 435 */       .append("startTime", this.startTime)
/* 436 */       .append("endTime", this.endTime)
/* 437 */       .append("issueScope", this.issueScope)
/* 438 */       .append("participants", this.participants)
/* 439 */       .append("principal", this.principal)
/* 440 */       .append("note", this.note)
/* 441 */       .append("status", this.status)
/* 442 */       .append("isPersonal", this.isPersonal)
/* 443 */       .append("icon", this.icon)
/* 444 */       .toString();
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.task.WorkPlan
 * JD-Core Version:    0.6.0
 */