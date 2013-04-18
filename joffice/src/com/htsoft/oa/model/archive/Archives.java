/*     */ package com.htsoft.oa.model.archive;
/*     */ 
/*     */ import com.google.gson.annotations.Expose;
/*     */ import com.htsoft.core.model.BaseModel;
/*     */ import com.htsoft.oa.model.system.Department;
/*     */ import com.htsoft.oa.model.system.GlobalType;
/*     */ import java.util.Date;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang.builder.EqualsBuilder;
/*     */ import org.apache.commons.lang.builder.HashCodeBuilder;
/*     */ import org.apache.commons.lang.builder.ToStringBuilder;
/*     */ 
/*     */ public class Archives extends BaseModel
/*     */ {
/*  26 */   public static final Short STATUS_DRAFT = Short.valueOf((short)0);
/*     */ 
/*  30 */   public static final Short STATUS_ISSUE = Short.valueOf((short)1);
/*     */ 
/*  34 */   public static final Short STATUS_ARCHIVE = Short.valueOf((short)2);
/*     */ 
/*  38 */   public static final Short STATUS_HANDLE = Short.valueOf((short)1);
/*     */ 
/*  42 */   public static final Short STATUS_HANDLEING = Short.valueOf((short)2);
/*     */ 
/*  46 */   public static final Short STATUS_LEADERREAD = Short.valueOf((short)3);
/*     */ 
/*  50 */   public static final Short STATUS_LEADERREAD2 = Short.valueOf((short)4);
/*     */ 
/*  54 */   public static final Short STATUS_DISPATCH = Short.valueOf((short)5);
/*     */ 
/*  58 */   public static final Short STATUS_READ = Short.valueOf((short)6);
/*     */ 
/*  62 */   public static final Short STATUS_READING = Short.valueOf((short)7);
/*     */ 
/*  66 */   public static final Short STATUS_OVER = Short.valueOf((short)8);
/*     */ 
/*  70 */   public static final Short STATUS_END = Short.valueOf((short)9);
/*     */ 
/*  74 */   public static final Short ARCHIVE_TYPE_DISPATCH = Short.valueOf((short)0);
/*     */ 
/*  79 */   public static final Short ARCHIVE_TYPE_RECEIVE = Short.valueOf((short)1);
/*     */ 
/*  84 */   public static final Short END_FLOW_NONE = Short.valueOf((short)0);
/*     */ 
/*  89 */   public static final Short END_FLOW = Short.valueOf((short)1);
/*     */ 
/*     */   @Expose
/*     */   protected Long archivesId;
/*     */ 
/*     */   @Expose
/*     */   protected String typeName;
/*     */ 
/*     */   @Expose
/*     */   protected String archivesNo;
/*     */ 
/*     */   @Expose
/*     */   protected String issueDep;
/*     */ 
/*     */   @Expose
/*     */   protected String subject;
/*     */ 
/*     */   @Expose
/*     */   protected Date issueDate;
/*     */ 
/*     */   @Expose
/*     */   protected Date createtime;
/*     */ 
/*     */   @Expose
/*     */   protected String status;
/*     */ 
/*     */   @Expose
/*     */   protected String shortContent;
/*     */ 
/*     */   @Expose
/*     */   protected Integer fileCounts;
/*     */ 
/*     */   @Expose
/*     */   protected String privacyLevel;
/*     */ 
/*     */   @Expose
/*     */   protected String urgentLevel;
/*     */ 
/*     */   @Expose
/*     */   protected String issuer;
/*     */ 
/*     */   @Expose
/*     */   protected Long issuerId;
/*     */ 
/*     */   @Expose
/*     */   protected String keywords;
/*     */ 
/*     */   @Expose
/*     */   protected String sources;
/*     */ 
/*     */   @Expose
/*     */   protected Short archType;
/*     */ 
/*     */   @Expose
/*     */   protected String recDepIds;
/*     */ 
/*     */   @Expose
/*     */   protected String recDepNames;
/*     */ 
/*     */   @Expose
/*     */   protected String handlerUids;
/*     */ 
/*     */   @Expose
/*     */   protected String handlerUnames;
/*     */ 
/*     */   @Expose
/*     */   protected Long orgArchivesId;
/*     */ 
/*     */   @Expose
/*     */   protected String depSignNo;
/*     */ 
/*     */   @Expose
/*     */   protected Long runId;
/*     */ 
/*     */   @Expose
/*     */   protected GlobalType archivesType;
/*     */ 
/*     */   @Expose
/*     */   protected GlobalType archivesRecType;
/*     */ 
/*     */   @Expose
/*     */   protected Short archStatus;
/*     */ 
/*     */   @Expose
/*     */   protected Department department;
/* 147 */   protected Set archivesDeps = new HashSet();
/*     */ 
/*     */   @Expose
/* 149 */   protected Set archivesDocs = new HashSet();
/* 150 */   protected Set archivesDispatch = new HashSet();
/*     */ 
/*     */   public Set getArchivesDispatch() {
/* 153 */     return this.archivesDispatch;
/*     */   }
/*     */ 
/*     */   public void setArchivesDispatch(Set archivesDispatch) {
/* 157 */     this.archivesDispatch = archivesDispatch;
/*     */   }
/*     */ 
/*     */   public String getHandlerUids() {
/* 161 */     return this.handlerUids;
/*     */   }
/*     */ 
/*     */   public void setHandlerUids(String handlerUids) {
/* 165 */     this.handlerUids = handlerUids;
/*     */   }
/*     */ 
/*     */   public String getHandlerUnames() {
/* 169 */     return this.handlerUnames;
/*     */   }
/*     */ 
/*     */   public void setHandlerUnames(String handlerUnames) {
/* 173 */     this.handlerUnames = handlerUnames;
/*     */   }
/*     */ 
/*     */   public Archives()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Archives(Long in_archivesId)
/*     */   {
/* 189 */     setArchivesId(in_archivesId);
/*     */   }
/*     */ 
/*     */   public GlobalType getArchivesType() {
/* 193 */     return this.archivesType;
/*     */   }
/*     */ 
/*     */   public void setArchivesType(GlobalType in_archivesType) {
/* 197 */     this.archivesType = in_archivesType;
/*     */   }
/*     */ 
/*     */   public Department getDepartment() {
/* 201 */     return this.department;
/*     */   }
/*     */ 
/*     */   public void setDepartment(Department in_department) {
/* 205 */     this.department = in_department;
/*     */   }
/*     */ 
/*     */   public Set getArchivesDeps() {
/* 209 */     return this.archivesDeps;
/*     */   }
/*     */ 
/*     */   public void setArchivesDeps(Set in_archivesDeps) {
/* 213 */     this.archivesDeps = in_archivesDeps;
/*     */   }
/*     */ 
/*     */   public Long getArchivesId()
/*     */   {
/* 221 */     return this.archivesId;
/*     */   }
/*     */ 
/*     */   public void setArchivesId(Long aValue)
/*     */   {
/* 228 */     this.archivesId = aValue;
/*     */   }
/*     */ 
/*     */   public Long getTypeId()
/*     */   {
/* 235 */     return getArchivesType() == null ? null : getArchivesType().getProTypeId();
/*     */   }
/*     */ 
/*     */   public void setTypeId(Long aValue)
/*     */   {
/* 242 */     if (aValue == null) {
/* 243 */       this.archivesType = null;
/* 244 */     } else if (this.archivesType == null) {
/* 245 */       this.archivesType = new GlobalType(aValue);
/* 246 */       this.archivesType.setVersion(new Integer(0));
/*     */     } else {
/* 248 */       this.archivesType.setProTypeId(aValue);
/*     */     }
/*     */   }
/*     */ 
/*     */   public GlobalType getArchivesRecType() {
/* 253 */     return this.archivesRecType;
/*     */   }
/*     */ 
/*     */   public void setArchivesRecType(GlobalType archivesRecType) {
/* 257 */     this.archivesRecType = archivesRecType;
/*     */   }
/*     */ 
/*     */   public Long getRecTypeId()
/*     */   {
/* 263 */     return getArchivesRecType() == null ? null : getArchivesRecType().getProTypeId();
/*     */   }
/*     */ 
/*     */   public void setRecTypeId(Long aValue)
/*     */   {
/* 270 */     if (aValue == null) {
/* 271 */       this.archivesRecType = null;
/* 272 */     } else if (this.archivesRecType == null) {
/* 273 */       this.archivesRecType = new GlobalType(aValue);
/* 274 */       this.archivesRecType.setVersion(new Integer(0));
/*     */     } else {
/* 276 */       this.archivesRecType.setProTypeId(aValue);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getTypeName()
/*     */   {
/* 284 */     return this.typeName;
/*     */   }
/*     */ 
/*     */   public void setTypeName(String aValue)
/*     */   {
/* 292 */     this.typeName = aValue;
/*     */   }
/*     */ 
/*     */   public String getArchivesNo()
/*     */   {
/* 300 */     return this.archivesNo;
/*     */   }
/*     */ 
/*     */   public void setArchivesNo(String aValue)
/*     */   {
/* 308 */     this.archivesNo = aValue;
/*     */   }
/*     */ 
/*     */   public String getIssueDep()
/*     */   {
/* 316 */     return this.issueDep;
/*     */   }
/*     */ 
/*     */   public void setIssueDep(String aValue)
/*     */   {
/* 324 */     this.issueDep = aValue;
/*     */   }
/*     */ 
/*     */   public Long getDepId()
/*     */   {
/* 332 */     return getDepartment() == null ? null : getDepartment().getDepId();
/*     */   }
/*     */ 
/*     */   public void setDepId(Long aValue)
/*     */   {
/* 339 */     if (aValue == null) {
/* 340 */       this.department = null;
/* 341 */     } else if (this.department == null) {
/* 342 */       this.department = new Department(aValue);
/* 343 */       this.department.setVersion(new Integer(0));
/*     */     } else {
/* 345 */       this.department.setDepId(aValue);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getSubject()
/*     */   {
/* 354 */     return this.subject;
/*     */   }
/*     */ 
/*     */   public void setSubject(String aValue)
/*     */   {
/* 362 */     this.subject = aValue;
/*     */   }
/*     */ 
/*     */   public Date getIssueDate()
/*     */   {
/* 370 */     return this.issueDate;
/*     */   }
/*     */ 
/*     */   public void setIssueDate(Date aValue)
/*     */   {
/* 378 */     this.issueDate = aValue;
/*     */   }
/*     */ 
/*     */   public String getShortContent()
/*     */   {
/* 395 */     return this.shortContent;
/*     */   }
/*     */ 
/*     */   public String getStatus() {
/* 399 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(String status) {
/* 403 */     this.status = status;
/*     */   }
/*     */ 
/*     */   public void setShortContent(String aValue)
/*     */   {
/* 410 */     this.shortContent = aValue;
/*     */   }
/*     */ 
/*     */   public Integer getFileCounts()
/*     */   {
/* 418 */     return this.fileCounts;
/*     */   }
/*     */ 
/*     */   public void setFileCounts(Integer aValue)
/*     */   {
/* 426 */     this.fileCounts = aValue;
/*     */   }
/*     */ 
/*     */   public String getPrivacyLevel()
/*     */   {
/* 438 */     return this.privacyLevel;
/*     */   }
/*     */ 
/*     */   public void setPrivacyLevel(String aValue)
/*     */   {
/* 445 */     this.privacyLevel = aValue;
/*     */   }
/*     */ 
/*     */   public String getUrgentLevel()
/*     */   {
/* 457 */     return this.urgentLevel;
/*     */   }
/*     */ 
/*     */   public void setUrgentLevel(String aValue)
/*     */   {
/* 464 */     this.urgentLevel = aValue;
/*     */   }
/*     */ 
/*     */   public String getIssuer()
/*     */   {
/* 472 */     return this.issuer;
/*     */   }
/*     */ 
/*     */   public void setIssuer(String aValue)
/*     */   {
/* 479 */     this.issuer = aValue;
/*     */   }
/*     */ 
/*     */   public Long getIssuerId()
/*     */   {
/* 487 */     return this.issuerId;
/*     */   }
/*     */ 
/*     */   public void setIssuerId(Long aValue)
/*     */   {
/* 494 */     this.issuerId = aValue;
/*     */   }
/*     */ 
/*     */   public String getKeywords()
/*     */   {
/* 502 */     return this.keywords;
/*     */   }
/*     */ 
/*     */   public void setKeywords(String aValue)
/*     */   {
/* 509 */     this.keywords = aValue;
/*     */   }
/*     */ 
/*     */   public String getSources()
/*     */   {
/* 520 */     return this.sources;
/*     */   }
/*     */ 
/*     */   public void setSources(String aValue)
/*     */   {
/* 527 */     this.sources = aValue;
/*     */   }
/*     */ 
/*     */   public Short getArchType()
/*     */   {
/* 535 */     return this.archType;
/*     */   }
/*     */ 
/*     */   public void setArchType(Short aValue)
/*     */   {
/* 543 */     this.archType = aValue;
/*     */   }
/*     */ 
/*     */   public String getRecDepIds() {
/* 547 */     return this.recDepIds;
/*     */   }
/*     */ 
/*     */   public void setRecDepIds(String recDepIds) {
/* 551 */     this.recDepIds = recDepIds;
/*     */   }
/*     */ 
/*     */   public String getRecDepNames() {
/* 555 */     return this.recDepNames;
/*     */   }
/*     */ 
/*     */   public void setRecDepNames(String recDepNames) {
/* 559 */     this.recDepNames = recDepNames;
/*     */   }
/*     */ 
/*     */   public Long getOrgArchivesId()
/*     */   {
/* 564 */     return this.orgArchivesId;
/*     */   }
/*     */ 
/*     */   public void setOrgArchivesId(Long orgArchivesId) {
/* 568 */     this.orgArchivesId = orgArchivesId;
/*     */   }
/*     */ 
/*     */   public String getDepSignNo() {
/* 572 */     return this.depSignNo;
/*     */   }
/*     */ 
/*     */   public void setDepSignNo(String depSignNo) {
/* 576 */     this.depSignNo = depSignNo;
/*     */   }
/*     */ 
/*     */   public Date getCreatetime() {
/* 580 */     return this.createtime;
/*     */   }
/*     */ 
/*     */   public void setCreatetime(Date createtime) {
/* 584 */     this.createtime = createtime;
/*     */   }
/*     */ 
/*     */   public Long getRunId() {
/* 588 */     return this.runId;
/*     */   }
/*     */ 
/*     */   public void setRunId(Long runId) {
/* 592 */     this.runId = runId;
/*     */   }
/*     */ 
/*     */   public Short getArchStatus() {
/* 596 */     return this.archStatus;
/*     */   }
/*     */ 
/*     */   public void setArchStatus(Short archStatus) {
/* 600 */     this.archStatus = archStatus;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 607 */     if (!(object instanceof Archives)) {
/* 608 */       return false;
/*     */     }
/* 610 */     Archives rhs = (Archives)object;
/* 611 */     return new EqualsBuilder()
/* 612 */       .append(this.archivesId, rhs.archivesId)
/* 613 */       .append(this.typeName, rhs.typeName)
/* 614 */       .append(this.archivesNo, rhs.archivesNo)
/* 615 */       .append(this.issueDep, rhs.issueDep)
/* 616 */       .append(this.subject, rhs.subject)
/* 617 */       .append(this.issueDate, rhs.issueDate)
/* 618 */       .append(this.status, rhs.status)
/* 619 */       .append(this.shortContent, rhs.shortContent)
/* 620 */       .append(this.fileCounts, rhs.fileCounts)
/* 621 */       .append(this.privacyLevel, rhs.privacyLevel)
/* 622 */       .append(this.urgentLevel, rhs.urgentLevel)
/* 623 */       .append(this.issuer, rhs.issuer)
/* 624 */       .append(this.issuerId, rhs.issuerId)
/* 625 */       .append(this.keywords, rhs.keywords)
/* 626 */       .append(this.sources, rhs.sources)
/* 627 */       .append(this.archType, rhs.archType)
/* 628 */       .append(this.recDepIds, rhs.recDepIds)
/* 629 */       .append(this.recDepNames, rhs.recDepNames)
/* 630 */       .append(this.orgArchivesId, rhs.orgArchivesId)
/* 631 */       .append(this.depSignNo, rhs.depSignNo)
/* 632 */       .append(this.runId, rhs.runId)
/* 633 */       .isEquals();
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 640 */     return new HashCodeBuilder(-82280557, -700257973)
/* 641 */       .append(this.archivesId)
/* 642 */       .append(this.typeName)
/* 643 */       .append(this.archivesNo)
/* 644 */       .append(this.issueDep)
/* 645 */       .append(this.subject)
/* 646 */       .append(this.issueDate)
/* 647 */       .append(this.status)
/* 648 */       .append(this.shortContent)
/* 649 */       .append(this.fileCounts)
/* 650 */       .append(this.privacyLevel)
/* 651 */       .append(this.urgentLevel)
/* 652 */       .append(this.issuer)
/* 653 */       .append(this.issuerId)
/* 654 */       .append(this.keywords)
/* 655 */       .append(this.sources)
/* 656 */       .append(this.archType)
/* 657 */       .append(this.recDepIds)
/* 658 */       .append(this.recDepNames)
/* 659 */       .append(this.orgArchivesId)
/* 660 */       .append(this.depSignNo)
/* 661 */       .append(this.runId)
/* 662 */       .toHashCode();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 669 */     return new ToStringBuilder(this)
/* 670 */       .append("archivesId", this.archivesId)
/* 671 */       .append("typeName", this.typeName)
/* 672 */       .append("archivesNo", this.archivesNo)
/* 673 */       .append("issueDep", this.issueDep)
/* 674 */       .append("subject", this.subject)
/* 675 */       .append("issueDate", this.issueDate)
/* 676 */       .append("status", this.status)
/* 677 */       .append("shortContent", this.shortContent)
/* 678 */       .append("fileCounts", this.fileCounts)
/* 679 */       .append("privacyLevel", this.privacyLevel)
/* 680 */       .append("urgentLevel", this.urgentLevel)
/* 681 */       .append("issuer", this.issuer)
/* 682 */       .append("issuerId", this.issuerId)
/* 683 */       .append("keywords", this.keywords)
/* 684 */       .append("sources", this.sources)
/* 685 */       .append("archType", this.archType)
/* 686 */       .append("recDepIds", this.recDepIds)
/* 687 */       .append("recDepNames", this.recDepNames)
/* 688 */       .append("orgArchivesId", this.orgArchivesId)
/* 689 */       .append("depSignNo", this.depSignNo)
/* 690 */       .append("runId", this.runId)
/* 691 */       .toString();
/*     */   }
/*     */ 
/*     */   public Set getArchivesDocs() {
/* 695 */     return this.archivesDocs;
/*     */   }
/*     */ 
/*     */   public void setArchivesDocs(Set archivesDocs) {
/* 699 */     this.archivesDocs = archivesDocs;
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.archive.Archives
 * JD-Core Version:    0.6.0
 */