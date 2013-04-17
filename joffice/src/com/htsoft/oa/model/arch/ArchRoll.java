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
/*     */ public class ArchRoll extends BaseModel
/*     */ {
/*     */ 
/*     */   @Expose
/*     */   protected Integer fileNums;
/*     */ 
/*     */   @Expose
/*     */   protected Long archFondId;
/*     */ 
/*     */   @Expose
/*     */   protected Long rollId;
/*     */ 
/*     */   @Expose
/*     */   protected String typeName;
/*     */ 
/*     */   @Expose
/*     */   protected String rolllName;
/*     */ 
/*     */   @Expose
/*     */   protected String afNo;
/*     */ 
/*     */   @Expose
/*     */   protected String rollNo;
/*     */ 
/*     */   @Expose
/*     */   protected String catNo;
/*     */ 
/*     */   @Expose
/*     */   protected String timeLimit;
/*     */ 
/*     */   @Expose
/*     */   protected Date startTime;
/*     */ 
/*     */   @Expose
/*     */   protected Date endTime;
/*     */ 
/*     */   @Expose
/*     */   protected String openStyle;
/*     */ 
/*     */   @Expose
/*     */   protected String author;
/*     */ 
/*     */   @Expose
/*     */   protected Date setupTime;
/*     */ 
/*     */   @Expose
/*     */   protected String checker;
/*     */ 
/*     */   @Expose
/*     */   protected String creatorName;
/*     */ 
/*     */   @Expose
/*     */   protected Date createTime;
/*     */ 
/*     */   @Expose
/*     */   protected String keyWords;
/*     */ 
/*     */   @Expose
/*     */   protected String editCompany;
/*     */ 
/*     */   @Expose
/*     */   protected String editDep;
/*     */ 
/*     */   @Expose
/*     */   protected String decp;
/*     */ 
/*     */   @Expose
/*     */   protected Short status;
/*     */ 
/*     */   @Expose
/*     */   protected ArchFond archFond;
/*     */ 
/*     */   @Expose
/*     */   protected GlobalType globalType;
/*     */ 
/*     */   @Expose
/*  69 */   protected Set<BorrowFileList> borrowFileList = new HashSet();
/*     */ 
/*     */   @Expose
/*  71 */   protected Set rollFiles = new HashSet();
/*     */ 
/*     */   public Integer getFileNums()
/*     */   {
/*  80 */     return this.fileNums;
/*     */   }
/*     */ 
/*     */   public void setFileNums(Integer fileNums) {
/*  84 */     this.fileNums = fileNums;
/*     */   }
/*     */   public Set getBorrowFileList() {
/*  87 */     return this.borrowFileList;
/*     */   }
/*     */ 
/*     */   public void setBorrowFileList(Set<BorrowFileList> borrowFileList) {
/*  91 */     this.borrowFileList = borrowFileList;
/*     */   }
/*     */   public String getCreatorName() {
/*  94 */     return this.creatorName;
/*     */   }
/*     */ 
/*     */   public void setCreatorName(String creatorName) {
/*  98 */     this.creatorName = creatorName;
/*     */   }
/*     */ 
/*     */   public Date getCreateTime() {
/* 102 */     return this.createTime;
/*     */   }
/*     */ 
/*     */   public void setCreateTime(Date createTime) {
/* 106 */     this.createTime = createTime;
/*     */   }
/*     */ 
/*     */   public String getKeyWords() {
/* 110 */     return this.keyWords;
/*     */   }
/*     */ 
/*     */   public void setKeyWords(String keyWords) {
/* 114 */     this.keyWords = keyWords;
/*     */   }
/*     */ 
/*     */   public ArchRoll()
/*     */   {
/*     */   }
/*     */ 
/*     */   public ArchRoll(Long in_rollId)
/*     */   {
/* 129 */     setRollId(in_rollId);
/*     */   }
/*     */ 
/*     */   public ArchFond getArchFond()
/*     */   {
/* 134 */     return this.archFond;
/*     */   }
/*     */ 
/*     */   public void setArchFond(ArchFond in_archFond) {
/* 138 */     this.archFond = in_archFond;
/*     */   }
/*     */ 
/*     */   public GlobalType getGlobalType() {
/* 142 */     return this.globalType;
/*     */   }
/*     */ 
/*     */   public void setGlobalType(GlobalType in_globalType) {
/* 146 */     this.globalType = in_globalType;
/*     */   }
/*     */ 
/*     */   public Set getRollFiles()
/*     */   {
/* 152 */     return this.rollFiles;
/*     */   }
/*     */ 
/*     */   public void setRollFiles(Set in_rollFiles) {
/* 156 */     this.rollFiles = in_rollFiles;
/*     */   }
/*     */ 
/*     */   public Long getRollId()
/*     */   {
/* 165 */     return this.rollId;
/*     */   }
/*     */ 
/*     */   public void setRollId(Long aValue)
/*     */   {
/* 172 */     this.rollId = aValue;
/*     */   }
/*     */ 
/*     */   public Long getProTypeId()
/*     */   {
/* 179 */     return getGlobalType() == null ? null : getGlobalType().getProTypeId();
/*     */   }
/*     */ 
/*     */   public void setProTypeId(Long aValue)
/*     */   {
/* 186 */     if (aValue == null) {
/* 187 */       this.globalType = null;
/* 188 */     } else if (this.globalType == null) {
/* 189 */       this.globalType = new GlobalType(aValue);
/* 190 */       this.globalType.setVersion(new Integer(0));
/*     */     } else {
/* 192 */       this.globalType.setProTypeId(aValue);
/*     */     }
/*     */   }
/*     */ 
/*     */   public Long getArchFondId()
/*     */   {
/* 200 */     return getArchFond() == null ? null : getArchFond().getArchFondId();
/*     */   }
/*     */ 
/*     */   public void setArchFondId(Long aValue)
/*     */   {
/* 207 */     if (aValue == null) {
/* 208 */       this.archFond = null;
/* 209 */     } else if (this.archFond == null) {
/* 210 */       this.archFond = new ArchFond(aValue);
/* 211 */       this.archFond.setVersion(new Integer(0));
/*     */     } else {
/* 213 */       this.archFond.setArchFondId(aValue);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getTypeName()
/*     */   {
/* 222 */     return this.typeName;
/*     */   }
/*     */ 
/*     */   public void setTypeName(String aValue)
/*     */   {
/* 229 */     this.typeName = aValue;
/*     */   }
/*     */ 
/*     */   public String getRolllName()
/*     */   {
/* 237 */     return this.rolllName;
/*     */   }
/*     */ 
/*     */   public void setRolllName(String aValue)
/*     */   {
/* 245 */     this.rolllName = aValue;
/*     */   }
/*     */ 
/*     */   public String getAfNo()
/*     */   {
/* 253 */     return this.afNo;
/*     */   }
/*     */ 
/*     */   public void setAfNo(String aValue)
/*     */   {
/* 261 */     this.afNo = aValue;
/*     */   }
/*     */ 
/*     */   public String getRollNo()
/*     */   {
/* 269 */     return this.rollNo;
/*     */   }
/*     */ 
/*     */   public void setRollNo(String aValue)
/*     */   {
/* 277 */     this.rollNo = aValue;
/*     */   }
/*     */ 
/*     */   public String getCatNo()
/*     */   {
/* 285 */     return this.catNo;
/*     */   }
/*     */ 
/*     */   public void setCatNo(String aValue)
/*     */   {
/* 292 */     this.catNo = aValue;
/*     */   }
/*     */ 
/*     */   public String getTimeLimit()
/*     */   {
/* 301 */     return this.timeLimit;
/*     */   }
/*     */ 
/*     */   public void setTimeLimit(String aValue)
/*     */   {
/* 308 */     this.timeLimit = aValue;
/*     */   }
/*     */ 
/*     */   public Date getStartTime()
/*     */   {
/* 316 */     return this.startTime;
/*     */   }
/*     */ 
/*     */   public void setStartTime(Date aValue)
/*     */   {
/* 323 */     this.startTime = aValue;
/*     */   }
/*     */ 
/*     */   public Date getEndTime()
/*     */   {
/* 331 */     return this.endTime;
/*     */   }
/*     */ 
/*     */   public void setEndTime(Date aValue)
/*     */   {
/* 338 */     this.endTime = aValue;
/*     */   }
/*     */ 
/*     */   public String getOpenStyle()
/*     */   {
/* 346 */     return this.openStyle;
/*     */   }
/*     */ 
/*     */   public void setOpenStyle(String aValue)
/*     */   {
/* 353 */     this.openStyle = aValue;
/*     */   }
/*     */ 
/*     */   public String getAuthor()
/*     */   {
/* 361 */     return this.author;
/*     */   }
/*     */ 
/*     */   public void setAuthor(String aValue)
/*     */   {
/* 368 */     this.author = aValue;
/*     */   }
/*     */ 
/*     */   public Date getSetupTime()
/*     */   {
/* 376 */     return this.setupTime;
/*     */   }
/*     */ 
/*     */   public void setSetupTime(Date aValue)
/*     */   {
/* 383 */     this.setupTime = aValue;
/*     */   }
/*     */ 
/*     */   public String getChecker()
/*     */   {
/* 391 */     return this.checker;
/*     */   }
/*     */ 
/*     */   public void setChecker(String aValue)
/*     */   {
/* 398 */     this.checker = aValue;
/*     */   }
/*     */ 
/*     */   public String getEditCompany()
/*     */   {
/* 410 */     return this.editCompany;
/*     */   }
/*     */ 
/*     */   public void setEditCompany(String aValue)
/*     */   {
/* 417 */     this.editCompany = aValue;
/*     */   }
/*     */ 
/*     */   public String getEditDep()
/*     */   {
/* 425 */     return this.editDep;
/*     */   }
/*     */ 
/*     */   public void setEditDep(String aValue)
/*     */   {
/* 432 */     this.editDep = aValue;
/*     */   }
/*     */ 
/*     */   public String getDecp()
/*     */   {
/* 440 */     return this.decp;
/*     */   }
/*     */ 
/*     */   public void setDecp(String aValue)
/*     */   {
/* 447 */     this.decp = aValue;
/*     */   }
/*     */ 
/*     */   public Short getStatus()
/*     */   {
/* 455 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(Short aValue)
/*     */   {
/* 462 */     this.status = aValue;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 469 */     if (!(object instanceof ArchRoll)) {
/* 470 */       return false;
/*     */     }
/* 472 */     ArchRoll rhs = (ArchRoll)object;
/* 473 */     return new EqualsBuilder()
/* 474 */       .append(this.rollId, rhs.rollId)
/* 475 */       .append(this.typeName, rhs.typeName)
/* 476 */       .append(this.rolllName, rhs.rolllName)
/* 477 */       .append(this.afNo, rhs.afNo)
/* 478 */       .append(this.rollNo, rhs.rollNo)
/* 479 */       .append(this.catNo, rhs.catNo)
/* 480 */       .append(this.timeLimit, rhs.timeLimit)
/* 481 */       .append(this.startTime, rhs.startTime)
/* 482 */       .append(this.endTime, rhs.endTime)
/* 483 */       .append(this.openStyle, rhs.openStyle)
/* 484 */       .append(this.author, rhs.author)
/* 485 */       .append(this.setupTime, rhs.setupTime)
/* 486 */       .append(this.checker, rhs.checker)
/* 487 */       .append(this.creatorName, rhs.creatorName)
/* 488 */       .append(this.createTime, rhs.createTime)
/* 489 */       .append(this.keyWords, rhs.keyWords)
/* 490 */       .append(this.editCompany, rhs.editCompany)
/* 491 */       .append(this.editDep, rhs.editDep)
/* 492 */       .append(this.decp, rhs.decp)
/* 493 */       .append(this.status, rhs.status)
/* 494 */       .isEquals();
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 501 */     return new HashCodeBuilder(-82280557, -700257973)
/* 502 */       .append(this.rollId)
/* 503 */       .append(this.typeName)
/* 504 */       .append(this.rolllName)
/* 505 */       .append(this.afNo)
/* 506 */       .append(this.rollNo)
/* 507 */       .append(this.catNo)
/* 508 */       .append(this.timeLimit)
/* 509 */       .append(this.startTime)
/* 510 */       .append(this.endTime)
/* 511 */       .append(this.openStyle)
/* 512 */       .append(this.author)
/* 513 */       .append(this.setupTime)
/* 514 */       .append(this.checker)
/* 515 */       .append(this.creatorName)
/* 516 */       .append(this.createTime)
/* 517 */       .append(this.keyWords)
/* 518 */       .append(this.editCompany)
/* 519 */       .append(this.editDep)
/* 520 */       .append(this.decp)
/* 521 */       .append(this.status)
/* 522 */       .toHashCode();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 529 */     return new ToStringBuilder(this)
/* 530 */       .append("rollId", this.rollId)
/* 531 */       .append("typeName", this.typeName)
/* 532 */       .append("rolllName", this.rolllName)
/* 533 */       .append("afNo", this.afNo)
/* 534 */       .append("rollNo", this.rollNo)
/* 535 */       .append("catNo", this.catNo)
/* 536 */       .append("timeLimit", this.timeLimit)
/* 537 */       .append("startTime", this.startTime)
/* 538 */       .append("endTime", this.endTime)
/* 539 */       .append("openStyle", this.openStyle)
/* 540 */       .append("author", this.author)
/* 541 */       .append("setupTime", this.setupTime)
/* 542 */       .append("checker", this.checker)
/* 543 */       .append("creatorName", this.creatorName)
/* 544 */       .append("createTime", this.createTime)
/* 545 */       .append("keyWords", this.keyWords)
/* 546 */       .append("editCompany", this.editCompany)
/* 547 */       .append("editDep", this.editDep)
/* 548 */       .append("decp", this.decp)
/* 549 */       .append("status", this.status)
/* 550 */       .toString();
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.arch.ArchRoll
 * JD-Core Version:    0.6.0
 */