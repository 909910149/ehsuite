/*     */ package com.htsoft.oa.model.admin;
/*     */ 
/*     */ import com.htsoft.core.model.BaseModel;
/*     */ import com.htsoft.oa.model.system.GlobalType;
/*     */ import java.util.Date;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang.builder.EqualsBuilder;
/*     */ import org.apache.commons.lang.builder.HashCodeBuilder;
/*     */ import org.apache.commons.lang.builder.ToStringBuilder;
/*     */ 
/*     */ public class Regulation extends BaseModel
/*     */ {
/*  22 */   public static final Short STATUS_DRAFT = Short.valueOf((short)0);
/*     */ 
/*  26 */   public static final Short STATUS_EFFECT = Short.valueOf((short)1);
/*     */   protected Long regId;
/*     */   protected String subject;
/*     */   protected Date issueDate;
/*     */   protected Long issueUserId;
/*     */   protected String issueFullname;
/*     */   protected Long issueDepId;
/*     */   protected String issueDep;
/*     */   protected String recDeps;
/*     */   protected String recDepIds;
/*     */   protected String recUsers;
/*     */   protected String recUserIds;
/*     */   protected String content;
/*     */   protected String keywords;
/*     */   protected Short status;
/*     */   protected GlobalType globalType;
/*  44 */   protected Set regAttachs = new HashSet();
/*     */ 
/*     */   public Regulation()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Regulation(Long in_regId)
/*     */   {
/*  59 */     setRegId(in_regId);
/*     */   }
/*     */ 
/*     */   public GlobalType getGlobalType()
/*     */   {
/*  64 */     return this.globalType;
/*     */   }
/*     */ 
/*     */   public void setGlobalType(GlobalType in_globalType) {
/*  68 */     this.globalType = in_globalType;
/*     */   }
/*     */ 
/*     */   public Set getRegAttachs() {
/*  72 */     return this.regAttachs;
/*     */   }
/*     */ 
/*     */   public void setRegAttachs(Set in_regAttachs) {
/*  76 */     this.regAttachs = in_regAttachs;
/*     */   }
/*     */ 
/*     */   public Long getRegId()
/*     */   {
/*  85 */     return this.regId;
/*     */   }
/*     */ 
/*     */   public void setRegId(Long aValue)
/*     */   {
/*  92 */     this.regId = aValue;
/*     */   }
/*     */ 
/*     */   public Long getProTypeId()
/*     */   {
/*  99 */     return getGlobalType() == null ? null : getGlobalType().getProTypeId();
/*     */   }
/*     */ 
/*     */   public void setProTypeId(Long aValue)
/*     */   {
/* 106 */     if (aValue == null) {
/* 107 */       this.globalType = null;
/* 108 */     } else if (this.globalType == null) {
/* 109 */       this.globalType = new GlobalType(aValue);
/* 110 */       this.globalType.setVersion(new Integer(0));
/*     */     } else {
/* 112 */       this.globalType.setProTypeId(aValue);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getSubject()
/*     */   {
/* 121 */     return this.subject;
/*     */   }
/*     */ 
/*     */   public void setSubject(String aValue)
/*     */   {
/* 129 */     this.subject = aValue;
/*     */   }
/*     */ 
/*     */   public Date getIssueDate()
/*     */   {
/* 137 */     return this.issueDate;
/*     */   }
/*     */ 
/*     */   public void setIssueDate(Date aValue)
/*     */   {
/* 144 */     this.issueDate = aValue;
/*     */   }
/*     */ 
/*     */   public Long getIssueUserId()
/*     */   {
/* 152 */     return this.issueUserId;
/*     */   }
/*     */ 
/*     */   public void setIssueUserId(Long aValue)
/*     */   {
/* 159 */     this.issueUserId = aValue;
/*     */   }
/*     */ 
/*     */   public String getIssueFullname()
/*     */   {
/* 167 */     return this.issueFullname;
/*     */   }
/*     */ 
/*     */   public void setIssueFullname(String aValue)
/*     */   {
/* 174 */     this.issueFullname = aValue;
/*     */   }
/*     */ 
/*     */   public Long getIssueDepId()
/*     */   {
/* 182 */     return this.issueDepId;
/*     */   }
/*     */ 
/*     */   public void setIssueDepId(Long aValue)
/*     */   {
/* 189 */     this.issueDepId = aValue;
/*     */   }
/*     */ 
/*     */   public String getIssueDep()
/*     */   {
/* 197 */     return this.issueDep;
/*     */   }
/*     */ 
/*     */   public void setIssueDep(String aValue)
/*     */   {
/* 204 */     this.issueDep = aValue;
/*     */   }
/*     */ 
/*     */   public String getRecDeps()
/*     */   {
/* 212 */     return this.recDeps;
/*     */   }
/*     */ 
/*     */   public void setRecDeps(String aValue)
/*     */   {
/* 219 */     this.recDeps = aValue;
/*     */   }
/*     */ 
/*     */   public String getRecDepIds()
/*     */   {
/* 227 */     return this.recDepIds;
/*     */   }
/*     */ 
/*     */   public void setRecDepIds(String aValue)
/*     */   {
/* 234 */     this.recDepIds = aValue;
/*     */   }
/*     */ 
/*     */   public String getRecUsers()
/*     */   {
/* 242 */     return this.recUsers;
/*     */   }
/*     */ 
/*     */   public void setRecUsers(String aValue)
/*     */   {
/* 249 */     this.recUsers = aValue;
/*     */   }
/*     */ 
/*     */   public String getRecUserIds()
/*     */   {
/* 257 */     return this.recUserIds;
/*     */   }
/*     */ 
/*     */   public void setRecUserIds(String aValue)
/*     */   {
/* 264 */     this.recUserIds = aValue;
/*     */   }
/*     */ 
/*     */   public String getContent()
/*     */   {
/* 272 */     return this.content;
/*     */   }
/*     */ 
/*     */   public void setContent(String aValue)
/*     */   {
/* 279 */     this.content = aValue;
/*     */   }
/*     */ 
/*     */   public String getKeywords()
/*     */   {
/* 287 */     return this.keywords;
/*     */   }
/*     */ 
/*     */   public void setKeywords(String aValue)
/*     */   {
/* 294 */     this.keywords = aValue;
/*     */   }
/*     */ 
/*     */   public Short getStatus()
/*     */   {
/* 302 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(Short aValue)
/*     */   {
/* 309 */     this.status = aValue;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 316 */     if (!(object instanceof Regulation)) {
/* 317 */       return false;
/*     */     }
/* 319 */     Regulation rhs = (Regulation)object;
/* 320 */     return new EqualsBuilder()
/* 321 */       .append(this.regId, rhs.regId)
/* 322 */       .append(this.subject, rhs.subject)
/* 323 */       .append(this.issueDate, rhs.issueDate)
/* 324 */       .append(this.issueUserId, rhs.issueUserId)
/* 325 */       .append(this.issueFullname, rhs.issueFullname)
/* 326 */       .append(this.issueDepId, rhs.issueDepId)
/* 327 */       .append(this.issueDep, rhs.issueDep)
/* 328 */       .append(this.recDeps, rhs.recDeps)
/* 329 */       .append(this.recDepIds, rhs.recDepIds)
/* 330 */       .append(this.recUsers, rhs.recUsers)
/* 331 */       .append(this.recUserIds, rhs.recUserIds)
/* 332 */       .append(this.content, rhs.content)
/* 333 */       .append(this.keywords, rhs.keywords)
/* 334 */       .append(this.status, rhs.status)
/* 335 */       .isEquals();
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 342 */     return new HashCodeBuilder(-82280557, -700257973)
/* 343 */       .append(this.regId)
/* 344 */       .append(this.subject)
/* 345 */       .append(this.issueDate)
/* 346 */       .append(this.issueUserId)
/* 347 */       .append(this.issueFullname)
/* 348 */       .append(this.issueDepId)
/* 349 */       .append(this.issueDep)
/* 350 */       .append(this.recDeps)
/* 351 */       .append(this.recDepIds)
/* 352 */       .append(this.recUsers)
/* 353 */       .append(this.recUserIds)
/* 354 */       .append(this.content)
/* 355 */       .append(this.keywords)
/* 356 */       .append(this.status)
/* 357 */       .toHashCode();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 364 */     return new ToStringBuilder(this)
/* 365 */       .append("regId", this.regId)
/* 366 */       .append("subject", this.subject)
/* 367 */       .append("issueDate", this.issueDate)
/* 368 */       .append("issueUserId", this.issueUserId)
/* 369 */       .append("issueFullname", this.issueFullname)
/* 370 */       .append("issueDepId", this.issueDepId)
/* 371 */       .append("issueDep", this.issueDep)
/* 372 */       .append("recDeps", this.recDeps)
/* 373 */       .append("recDepIds", this.recDepIds)
/* 374 */       .append("recUsers", this.recUsers)
/* 375 */       .append("recUserIds", this.recUserIds)
/* 376 */       .append("content", this.content)
/* 377 */       .append("keywords", this.keywords)
/* 378 */       .append("status", this.status)
/* 379 */       .toString();
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.admin.Regulation
 * JD-Core Version:    0.6.0
 */