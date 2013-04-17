/*     */ package com.htsoft.oa.model.arch;
/*     */ 
/*     */ import com.google.gson.annotations.Expose;
/*     */ import com.htsoft.core.model.BaseModel;
/*     */ import com.htsoft.oa.model.system.GlobalType;
/*     */ import java.util.Date;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang.builder.EqualsBuilder;
/*     */ import org.apache.commons.lang.builder.HashCodeBuilder;
/*     */ import org.apache.commons.lang.builder.ToStringBuilder;
/*     */ 
/*     */ public class ArchFond extends BaseModel
/*     */ {
/*     */ 
/*     */   @Expose
/*     */   protected Long archFondId;
/*     */ 
/*     */   @Expose
/*     */   protected String afNo;
/*     */ 
/*     */   @Expose
/*     */   protected String afName;
/*     */ 
/*     */   @Expose
/*     */   protected String shortDesc;
/*     */ 
/*     */   @Expose
/*     */   protected String descp;
/*     */ 
/*     */   @Expose
/*     */   protected String clearupDesc;
/*     */ 
/*     */   @Expose
/*     */   protected Date createTime;
/*     */ 
/*     */   @Expose
/*     */   protected Date updateTime;
/*     */ 
/*     */   @Expose
/*     */   protected String creatorName;
/*     */ 
/*     */   @Expose
/*     */   protected Long creatorId;
/*     */ 
/*     */   @Expose
/*     */   protected Integer caseNums;
/*     */ 
/*     */   @Expose
/*     */   protected Short status;
/*     */ 
/*     */   @Expose
/*     */   protected String typeName;
/*     */ 
/*     */   @Expose
/*     */   protected String openStyle;
/*     */ 
/*     */   @Expose
/*     */   protected GlobalType globalType;
/*     */ 
/*     */   @Expose
/*  79 */   protected Set<ArchRoll> archRolls = new HashSet();
/*     */ 
/*     */   @Expose
/*  81 */   protected Set<BorrowFileList> borrowFileList = new HashSet();
/*     */ 
/*     */   public Date getCreateTime()
/*     */   {
/*  38 */     return this.createTime;
/*     */   }
/*     */ 
/*     */   public void setCreateTime(Date createTime) {
/*  42 */     this.createTime = createTime;
/*     */   }
/*     */ 
/*     */   public Date getUpdateTime() {
/*  46 */     return this.updateTime;
/*     */   }
/*     */ 
/*     */   public void setUpdateTime(Date updateTime) {
/*  50 */     this.updateTime = updateTime;
/*     */   }
/*     */ 
/*     */   public String getCreatorName() {
/*  54 */     return this.creatorName;
/*     */   }
/*     */ 
/*     */   public void setCreatorName(String creatorName) {
/*  58 */     this.creatorName = creatorName;
/*     */   }
/*     */ 
/*     */   public Set getBorrowFileList()
/*     */   {
/*  84 */     return this.borrowFileList;
/*     */   }
/*     */ 
/*     */   public void setBorrowFileList(Set<BorrowFileList> borrowFileList) {
/*  88 */     this.borrowFileList = borrowFileList;
/*     */   }
/*     */ 
/*     */   public ArchFond()
/*     */   {
/*     */   }
/*     */ 
/*     */   public ArchFond(Long in_archFondId)
/*     */   {
/* 104 */     setArchFondId(in_archFondId);
/*     */   }
/*     */ 
/*     */   public GlobalType getGlobalType() {
/* 108 */     return this.globalType;
/*     */   }
/*     */ 
/*     */   public void setGlobalType(GlobalType in_globalType)
/*     */   {
/* 113 */     this.globalType = in_globalType;
/*     */   }
/*     */ 
/*     */   public Set getArchRolls() {
/* 117 */     return this.archRolls;
/*     */   }
/*     */ 
/*     */   public void setArchRolls(Set in_archRolls) {
/* 121 */     this.archRolls = in_archRolls;
/*     */   }
/*     */ 
/*     */   public Long getArchFondId()
/*     */   {
/* 131 */     return this.archFondId;
/*     */   }
/*     */ 
/*     */   public void setArchFondId(Long aValue)
/*     */   {
/* 138 */     this.archFondId = aValue;
/*     */   }
/*     */ 
/*     */   public String getAfNo()
/*     */   {
/* 148 */     return this.afNo;
/*     */   }
/*     */ 
/*     */   public void setAfNo(String aValue)
/*     */   {
/* 157 */     this.afNo = aValue;
/*     */   }
/*     */ 
/*     */   public String getAfName()
/*     */   {
/* 167 */     return this.afName;
/*     */   }
/*     */ 
/*     */   public void setAfName(String aValue)
/*     */   {
/* 176 */     this.afName = aValue;
/*     */   }
/*     */ 
/*     */   public String getShortDesc()
/*     */   {
/* 186 */     return this.shortDesc;
/*     */   }
/*     */ 
/*     */   public void setShortDesc(String aValue)
/*     */   {
/* 193 */     this.shortDesc = aValue;
/*     */   }
/*     */ 
/*     */   public String getDescp()
/*     */   {
/* 203 */     return this.descp;
/*     */   }
/*     */ 
/*     */   public void setDescp(String aValue)
/*     */   {
/* 210 */     this.descp = aValue;
/*     */   }
/*     */ 
/*     */   public String getClearupDesc()
/*     */   {
/* 220 */     return this.clearupDesc;
/*     */   }
/*     */ 
/*     */   public void setClearupDesc(String aValue)
/*     */   {
/* 227 */     this.clearupDesc = aValue;
/*     */   }
/*     */ 
/*     */   public Long getCreatorId()
/*     */   {
/* 244 */     return this.creatorId;
/*     */   }
/*     */ 
/*     */   public void setCreatorId(Long aValue)
/*     */   {
/* 251 */     this.creatorId = aValue;
/*     */   }
/*     */ 
/*     */   public Integer getCaseNums()
/*     */   {
/* 261 */     return this.caseNums;
/*     */   }
/*     */ 
/*     */   public void setCaseNums(Integer aValue)
/*     */   {
/* 268 */     this.caseNums = aValue;
/*     */   }
/*     */ 
/*     */   public Short getStatus()
/*     */   {
/* 278 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(Short aValue)
/*     */   {
/* 285 */     this.status = aValue;
/*     */   }
/*     */ 
/*     */   public Long getProTypeId()
/*     */   {
/* 292 */     return getGlobalType() == null ? null : getGlobalType()
/* 293 */       .getProTypeId();
/*     */   }
/*     */ 
/*     */   public void setProTypeId(Long aValue)
/*     */   {
/* 300 */     if (aValue == null) {
/* 301 */       this.globalType = null;
/* 302 */     } else if (this.globalType == null) {
/* 303 */       this.globalType = new GlobalType(aValue);
/* 304 */       this.globalType.setVersion(new Integer(0));
/*     */     }
/*     */     else {
/* 307 */       this.globalType.setProTypeId(aValue);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getTypeName()
/*     */   {
/* 318 */     return this.typeName;
/*     */   }
/*     */ 
/*     */   public void setTypeName(String aValue)
/*     */   {
/* 325 */     this.typeName = aValue;
/*     */   }
/*     */ 
/*     */   public String getOpenStyle()
/*     */   {
/* 335 */     return this.openStyle;
/*     */   }
/*     */ 
/*     */   public void setOpenStyle(String aValue)
/*     */   {
/* 342 */     this.openStyle = aValue;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 349 */     if (!(object instanceof ArchFond)) {
/* 350 */       return false;
/*     */     }
/* 352 */     ArchFond rhs = (ArchFond)object;
/* 353 */     return new EqualsBuilder().append(this.archFondId, rhs.archFondId)
/* 354 */       .append(this.afNo, rhs.afNo).append(this.afName, rhs.afName)
/* 355 */       .append(this.shortDesc, rhs.shortDesc).append(this.descp, 
/* 356 */       rhs.descp).append(this.clearupDesc, rhs.clearupDesc)
/* 357 */       .append(this.createTime, rhs.createTime).append(
/* 358 */       this.updateTime, rhs.updateTime).append(
/* 359 */       this.creatorName, rhs.creatorName).append(
/* 360 */       this.creatorId, rhs.creatorId).append(this.caseNums, 
/* 361 */       rhs.caseNums).append(this.status, rhs.status).append(
/* 362 */       this.typeName, rhs.typeName).append(this.openStyle, 
/* 363 */       rhs.openStyle).isEquals();
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 370 */     return new HashCodeBuilder(-82280557, -700257973).append(
/* 371 */       this.archFondId).append(this.afNo).append(this.afName).append(
/* 372 */       this.shortDesc).append(this.descp).append(this.clearupDesc)
/* 373 */       .append(this.createTime).append(this.updateTime).append(
/* 374 */       this.creatorName).append(this.creatorId).append(
/* 375 */       this.caseNums).append(this.status)
/* 376 */       .append(this.typeName).append(this.openStyle).toHashCode();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 383 */     return new ToStringBuilder(this).append("archFondId", this.archFondId)
/* 384 */       .append("afNo", this.afNo).append("afName", this.afName)
/* 385 */       .append("shortDesc", this.shortDesc)
/* 386 */       .append("descp", this.descp).append("clearupDesc", 
/* 387 */       this.clearupDesc).append("createtime", this.createTime)
/* 388 */       .append("updatetime", this.updateTime).append("creator", 
/* 389 */       this.creatorName).append("creatorId", this.creatorId)
/* 390 */       .append("caseNums", this.caseNums)
/* 391 */       .append("status", this.status)
/* 392 */       .append("typeName", this.typeName).append("openStyle", 
/* 393 */       this.openStyle).toString();
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.arch.ArchFond
 * JD-Core Version:    0.6.0
 */