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
/*     */ public class RollFile extends BaseModel
/*     */ {
/*     */ 
/*     */   @Expose
/*     */   protected Long rollFileId;
/*     */ 
/*     */   @Expose
/*     */   protected String typeName;
/*     */ 
/*     */   @Expose
/*     */   protected String fileName;
/*     */ 
/*     */   @Expose
/*     */   protected String fileNo;
/*     */ 
/*     */   @Expose
/*     */   protected String dutyPerson;
/*     */ 
/*     */   @Expose
/*     */   protected String afNo;
/*     */ 
/*     */   @Expose
/*     */   protected String catNo;
/*     */ 
/*     */   @Expose
/*     */   protected String rollNo;
/*     */ 
/*     */   @Expose
/*     */   protected Integer seqNo;
/*     */ 
/*     */   @Expose
/*     */   protected Integer pageNo;
/*     */ 
/*     */   @Expose
/*     */   protected Integer pageNums;
/*     */ 
/*     */   @Expose
/*     */   protected String secretLevel;
/*     */ 
/*     */   @Expose
/*     */   protected String timeLimit;
/*     */ 
/*     */   @Expose
/*     */   protected String openStyle;
/*     */ 
/*     */   @Expose
/*     */   protected String keyWords;
/*     */ 
/*     */   @Expose
/*     */   protected String notes;
/*     */ 
/*     */   @Expose
/*     */   protected String content;
/*     */ 
/*     */   @Expose
/*     */   protected Date fileTime;
/*     */ 
/*     */   @Expose
/*     */   protected String creatorName;
/*     */ 
/*     */   @Expose
/*     */   protected Date createTime;
/*     */ 
/*     */   @Expose
/*     */   protected Short archStatus;
/*     */ 
/*     */   @Expose
/*     */   protected ArchRoll archRoll;
/*     */ 
/*     */   @Expose
/*     */   protected GlobalType globalType;
/*     */ 
/*     */   @Expose
/*  68 */   protected Set<BorrowFileList> borrowFileList = new HashSet();
/*     */ 
/*     */   @Expose
/*  70 */   protected Set rollFileLists = new HashSet();
/*     */ 
/*     */   @Expose
/*     */   protected String tidyName;
/*     */ 
/*     */   @Expose
/*     */   protected Date tidyTime;
/*     */ 
/*  77 */   public Set getBorrowFileList() { return this.borrowFileList; }
/*     */ 
/*     */   public void setBorrowFileList(Set<BorrowFileList> borrowFileList)
/*     */   {
/*  81 */     this.borrowFileList = borrowFileList;
/*     */   }
/*     */ 
/*     */   public String getTidyName() {
/*  85 */     return this.tidyName;
/*     */   }
/*     */ 
/*     */   public void setTidyName(String tidyName) {
/*  89 */     this.tidyName = tidyName;
/*     */   }
/*     */ 
/*     */   public Date getTidyTime() {
/*  93 */     return this.tidyTime;
/*     */   }
/*     */ 
/*     */   public void setTidyTime(Date tidyTime) {
/*  97 */     this.tidyTime = tidyTime;
/*     */   }
/*     */ 
/*     */   public String getKeyWords()
/*     */   {
/* 102 */     return this.keyWords;
/*     */   }
/*     */ 
/*     */   public void setKeyWords(String keyWords) {
/* 106 */     this.keyWords = keyWords;
/*     */   }
/*     */ 
/*     */   public String getCreatorName() {
/* 110 */     return this.creatorName;
/*     */   }
/*     */ 
/*     */   public void setCreatorName(String creatorName) {
/* 114 */     this.creatorName = creatorName;
/*     */   }
/*     */ 
/*     */   public Date getCreateTime() {
/* 118 */     return this.createTime;
/*     */   }
/*     */ 
/*     */   public void setCreateTime(Date createTime) {
/* 122 */     this.createTime = createTime;
/*     */   }
/*     */ 
/*     */   public RollFile()
/*     */   {
/*     */   }
/*     */ 
/*     */   public RollFile(Long in_rollFileId)
/*     */   {
/* 139 */     setRollFileId(in_rollFileId);
/*     */   }
/*     */ 
/*     */   public ArchRoll getArchRoll()
/*     */   {
/* 144 */     return this.archRoll;
/*     */   }
/*     */ 
/*     */   public void setArchRoll(ArchRoll in_archRoll) {
/* 148 */     this.archRoll = in_archRoll;
/*     */   }
/*     */ 
/*     */   public GlobalType getGlobalType() {
/* 152 */     return this.globalType;
/*     */   }
/*     */ 
/*     */   public void setGlobalType(GlobalType in_globalType) {
/* 156 */     this.globalType = in_globalType;
/*     */   }
/*     */ 
/*     */   public Set getRollFileLists()
/*     */   {
/* 162 */     return this.rollFileLists;
/*     */   }
/*     */ 
/*     */   public void setRollFileLists(Set in_rollFileLists) {
/* 166 */     this.rollFileLists = in_rollFileLists;
/*     */   }
/*     */ 
/*     */   public Long getRollFileId()
/*     */   {
/* 175 */     return this.rollFileId;
/*     */   }
/*     */ 
/*     */   public void setRollFileId(Long aValue)
/*     */   {
/* 182 */     this.rollFileId = aValue;
/*     */   }
/*     */ 
/*     */   public Long getProTypeId()
/*     */   {
/* 189 */     return getGlobalType() == null ? null : getGlobalType().getProTypeId();
/*     */   }
/*     */ 
/*     */   public void setProTypeId(Long aValue)
/*     */   {
/* 196 */     if (aValue == null) {
/* 197 */       this.globalType = null;
/* 198 */     } else if (this.globalType == null) {
/* 199 */       this.globalType = new GlobalType(aValue);
/* 200 */       this.globalType.setVersion(new Integer(0));
/*     */     } else {
/* 202 */       this.globalType.setProTypeId(aValue);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getTypeName()
/*     */   {
/* 211 */     return this.typeName;
/*     */   }
/*     */ 
/*     */   public void setTypeName(String aValue)
/*     */   {
/* 218 */     this.typeName = aValue;
/*     */   }
/*     */ 
/*     */   public Long getRollId()
/*     */   {
/* 225 */     return getArchRoll() == null ? null : getArchRoll().getRollId();
/*     */   }
/*     */ 
/*     */   public void setRollId(Long aValue)
/*     */   {
/* 232 */     if (aValue == null) {
/* 233 */       this.archRoll = null;
/* 234 */     } else if (this.archRoll == null) {
/* 235 */       this.archRoll = new ArchRoll(aValue);
/* 236 */       this.archRoll.setVersion(new Integer(0));
/*     */     } else {
/* 238 */       this.archRoll.setRollId(aValue);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getFileName()
/*     */   {
/* 247 */     return this.fileName;
/*     */   }
/*     */ 
/*     */   public void setFileName(String aValue)
/*     */   {
/* 255 */     this.fileName = aValue;
/*     */   }
/*     */ 
/*     */   public String getFileNo()
/*     */   {
/* 263 */     return this.fileNo;
/*     */   }
/*     */ 
/*     */   public void setFileNo(String aValue)
/*     */   {
/* 271 */     this.fileNo = aValue;
/*     */   }
/*     */ 
/*     */   public String getDutyPerson()
/*     */   {
/* 279 */     return this.dutyPerson;
/*     */   }
/*     */ 
/*     */   public void setDutyPerson(String aValue)
/*     */   {
/* 286 */     this.dutyPerson = aValue;
/*     */   }
/*     */ 
/*     */   public String getAfNo()
/*     */   {
/* 294 */     return this.afNo;
/*     */   }
/*     */ 
/*     */   public void setAfNo(String aValue)
/*     */   {
/* 301 */     this.afNo = aValue;
/*     */   }
/*     */ 
/*     */   public String getCatNo()
/*     */   {
/* 309 */     return this.catNo;
/*     */   }
/*     */ 
/*     */   public void setCatNo(String aValue)
/*     */   {
/* 316 */     this.catNo = aValue;
/*     */   }
/*     */ 
/*     */   public String getRollNo()
/*     */   {
/* 324 */     return this.rollNo;
/*     */   }
/*     */ 
/*     */   public void setRollNo(String aValue)
/*     */   {
/* 331 */     this.rollNo = aValue;
/*     */   }
/*     */ 
/*     */   public Integer getSeqNo()
/*     */   {
/* 339 */     return this.seqNo;
/*     */   }
/*     */ 
/*     */   public void setSeqNo(Integer aValue)
/*     */   {
/* 346 */     this.seqNo = aValue;
/*     */   }
/*     */ 
/*     */   public Integer getPageNo()
/*     */   {
/* 354 */     return this.pageNo;
/*     */   }
/*     */ 
/*     */   public void setPageNo(Integer aValue)
/*     */   {
/* 361 */     this.pageNo = aValue;
/*     */   }
/*     */ 
/*     */   public Integer getPageNums()
/*     */   {
/* 369 */     return this.pageNums;
/*     */   }
/*     */ 
/*     */   public void setPageNums(Integer aValue)
/*     */   {
/* 376 */     this.pageNums = aValue;
/*     */   }
/*     */ 
/*     */   public String getSecretLevel()
/*     */   {
/* 384 */     return this.secretLevel;
/*     */   }
/*     */ 
/*     */   public void setSecretLevel(String aValue)
/*     */   {
/* 391 */     this.secretLevel = aValue;
/*     */   }
/*     */ 
/*     */   public String getTimeLimit()
/*     */   {
/* 399 */     return this.timeLimit;
/*     */   }
/*     */ 
/*     */   public void setTimeLimit(String aValue)
/*     */   {
/* 406 */     this.timeLimit = aValue;
/*     */   }
/*     */ 
/*     */   public String getOpenStyle()
/*     */   {
/* 414 */     return this.openStyle;
/*     */   }
/*     */ 
/*     */   public void setOpenStyle(String aValue)
/*     */   {
/* 421 */     this.openStyle = aValue;
/*     */   }
/*     */ 
/*     */   public String getNotes()
/*     */   {
/* 431 */     return this.notes;
/*     */   }
/*     */ 
/*     */   public void setNotes(String aValue)
/*     */   {
/* 438 */     this.notes = aValue;
/*     */   }
/*     */ 
/*     */   public String getContent()
/*     */   {
/* 446 */     return this.content;
/*     */   }
/*     */ 
/*     */   public void setContent(String aValue)
/*     */   {
/* 453 */     this.content = aValue;
/*     */   }
/*     */ 
/*     */   public Date getFileTime()
/*     */   {
/* 461 */     return this.fileTime;
/*     */   }
/*     */ 
/*     */   public void setFileTime(Date aValue)
/*     */   {
/* 468 */     this.fileTime = aValue;
/*     */   }
/*     */ 
/*     */   public Short getArchStatus()
/*     */   {
/* 477 */     return this.archStatus;
/*     */   }
/*     */ 
/*     */   public void setArchStatus(Short aValue)
/*     */   {
/* 484 */     this.archStatus = aValue;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 491 */     if (!(object instanceof RollFile)) {
/* 492 */       return false;
/*     */     }
/* 494 */     RollFile rhs = (RollFile)object;
/* 495 */     return new EqualsBuilder()
/* 496 */       .append(this.rollFileId, rhs.rollFileId)
/* 497 */       .append(this.typeName, rhs.typeName)
/* 498 */       .append(this.fileName, rhs.fileName)
/* 499 */       .append(this.fileNo, rhs.fileNo)
/* 500 */       .append(this.dutyPerson, rhs.dutyPerson)
/* 501 */       .append(this.afNo, rhs.afNo)
/* 502 */       .append(this.catNo, rhs.catNo)
/* 503 */       .append(this.rollNo, rhs.rollNo)
/* 504 */       .append(this.seqNo, rhs.seqNo)
/* 505 */       .append(this.pageNo, rhs.pageNo)
/* 506 */       .append(this.pageNums, rhs.pageNums)
/* 507 */       .append(this.secretLevel, rhs.secretLevel)
/* 508 */       .append(this.timeLimit, rhs.timeLimit)
/* 509 */       .append(this.openStyle, rhs.openStyle)
/* 510 */       .append(this.keyWords, rhs.keyWords)
/* 511 */       .append(this.notes, rhs.notes)
/* 512 */       .append(this.content, rhs.content)
/* 513 */       .append(this.fileTime, rhs.fileTime)
/* 514 */       .append(this.creatorName, rhs.creatorName)
/* 515 */       .append(this.createTime, rhs.createTime)
/* 516 */       .append(this.archStatus, rhs.archStatus)
/* 517 */       .isEquals();
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 524 */     return new HashCodeBuilder(-82280557, -700257973)
/* 525 */       .append(this.rollFileId)
/* 526 */       .append(this.typeName)
/* 527 */       .append(this.fileName)
/* 528 */       .append(this.fileNo)
/* 529 */       .append(this.dutyPerson)
/* 530 */       .append(this.afNo)
/* 531 */       .append(this.catNo)
/* 532 */       .append(this.rollNo)
/* 533 */       .append(this.seqNo)
/* 534 */       .append(this.pageNo)
/* 535 */       .append(this.pageNums)
/* 536 */       .append(this.secretLevel)
/* 537 */       .append(this.timeLimit)
/* 538 */       .append(this.openStyle)
/* 539 */       .append(this.keyWords)
/* 540 */       .append(this.notes)
/* 541 */       .append(this.content)
/* 542 */       .append(this.fileTime)
/* 543 */       .append(this.creatorName)
/* 544 */       .append(this.createTime)
/* 545 */       .append(this.archStatus)
/* 546 */       .toHashCode();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 553 */     return new ToStringBuilder(this)
/* 554 */       .append("rollFileId", this.rollFileId)
/* 555 */       .append("typeName", this.typeName)
/* 556 */       .append("fileName", this.fileName)
/* 557 */       .append("fileNo", this.fileNo)
/* 558 */       .append("dutyPerson", this.dutyPerson)
/* 559 */       .append("afNo", this.afNo)
/* 560 */       .append("catNo", this.catNo)
/* 561 */       .append("rollNo", this.rollNo)
/* 562 */       .append("seqNo", this.seqNo)
/* 563 */       .append("pageNo", this.pageNo)
/* 564 */       .append("pageNums", this.pageNums)
/* 565 */       .append("secretLevel", this.secretLevel)
/* 566 */       .append("timeLimit", this.timeLimit)
/* 567 */       .append("openStyle", this.openStyle)
/* 568 */       .append("keyWords", this.keyWords)
/* 569 */       .append("notes", this.notes)
/* 570 */       .append("content", this.content)
/* 571 */       .append("fileTime", this.fileTime)
/* 572 */       .append("creatorName", this.creatorName)
/* 573 */       .append("createTime", this.createTime)
/* 574 */       .append("archStatus", this.archStatus)
/* 575 */       .toString();
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.arch.RollFile
 * JD-Core Version:    0.6.0
 */