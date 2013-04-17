/*     */ package com.htsoft.oa.model.arch;
/*     */ 
/*     */ import com.google.gson.annotations.Expose;
/*     */ import com.htsoft.core.model.BaseModel;
/*     */ import com.htsoft.oa.model.system.AppUser;
/*     */ import java.util.Date;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang.builder.EqualsBuilder;
/*     */ import org.apache.commons.lang.builder.HashCodeBuilder;
/*     */ import org.apache.commons.lang.builder.ToStringBuilder;
/*     */ 
/*     */ public class BorrowRecord extends BaseModel
/*     */ {
/*     */ 
/*     */   @Expose
/*     */   protected String borrowRemark;
/*     */ 
/*     */   @Expose
/*     */   protected Long checkId;
/*     */ 
/*     */   @Expose
/*     */   protected String checkName;
/*     */ 
/*     */   @Expose
/*     */   protected String checkContent;
/*     */ 
/*     */   @Expose
/*     */   protected Long recordId;
/*     */ 
/*     */   @Expose
/*     */   protected Date borrowDate;
/*     */ 
/*     */   @Expose
/*     */   protected String borrowType;
/*     */ 
/*     */   @Expose
/*     */   protected String borrowReason;
/*     */ 
/*     */   @Expose
/*     */   protected String checkUserName;
/*     */ 
/*     */   @Expose
/*     */   protected Date checkDate;
/*     */ 
/*     */   @Expose
/*     */   protected Date returnDate;
/*     */ 
/*     */   @Expose
/*     */   protected Short returnStatus;
/*     */ 
/*     */   @Expose
/*     */   protected String borrowNum;
/*     */ 
/*     */   @Expose
/*     */   protected AppUser appUser;
/*  50 */   protected Set borrowFileLists = new HashSet();
/*     */ 
/*     */   public String getBorrowRemark()
/*     */   {
/*  57 */     return this.borrowRemark;
/*     */   }
/*     */ 
/*     */   public void setBorrowRemark(String borrowRemark) {
/*  61 */     this.borrowRemark = borrowRemark;
/*     */   }
/*     */ 
/*     */   public Long getCheckId() {
/*  65 */     return this.checkId;
/*     */   }
/*     */ 
/*     */   public void setCheckId(Long checkId) {
/*  69 */     this.checkId = checkId;
/*     */   }
/*     */ 
/*     */   public String getCheckName() {
/*  73 */     return this.checkName;
/*     */   }
/*     */ 
/*     */   public void setCheckName(String checkName) {
/*  77 */     this.checkName = checkName;
/*     */   }
/*     */ 
/*     */   public String getCheckContent() {
/*  81 */     return this.checkContent;
/*     */   }
/*     */ 
/*     */   public void setCheckContent(String checkContent) {
/*  85 */     this.checkContent = checkContent;
/*     */   }
/*     */ 
/*     */   public BorrowRecord()
/*     */   {
/*     */   }
/*     */ 
/*     */   public BorrowRecord(Long in_recordId)
/*     */   {
/* 101 */     setRecordId(in_recordId);
/*     */   }
/*     */ 
/*     */   public AppUser getAppUser()
/*     */   {
/* 106 */     return this.appUser;
/*     */   }
/*     */ 
/*     */   public void setAppUser(AppUser in_appUser) {
/* 110 */     this.appUser = in_appUser;
/*     */   }
/*     */ 
/*     */   public Set getBorrowFileLists() {
/* 114 */     return this.borrowFileLists;
/*     */   }
/*     */ 
/*     */   public void setBorrowFileLists(Set in_borrowFileLists) {
/* 118 */     this.borrowFileLists = in_borrowFileLists;
/*     */   }
/*     */ 
/*     */   public Long getRecordId()
/*     */   {
/* 127 */     return this.recordId;
/*     */   }
/*     */ 
/*     */   public void setRecordId(Long aValue)
/*     */   {
/* 134 */     this.recordId = aValue;
/*     */   }
/*     */ 
/*     */   public Date getBorrowDate()
/*     */   {
/* 147 */     return this.borrowDate;
/*     */   }
/*     */ 
/*     */   public void setBorrowDate(Date aValue)
/*     */   {
/* 154 */     this.borrowDate = aValue;
/*     */   }
/*     */ 
/*     */   public String getBorrowType()
/*     */   {
/* 162 */     return this.borrowType;
/*     */   }
/*     */ 
/*     */   public void setBorrowType(String aValue)
/*     */   {
/* 169 */     this.borrowType = aValue;
/*     */   }
/*     */ 
/*     */   public String getBorrowReason()
/*     */   {
/* 177 */     return this.borrowReason;
/*     */   }
/*     */ 
/*     */   public void setBorrowReason(String aValue)
/*     */   {
/* 184 */     this.borrowReason = aValue;
/*     */   }
/*     */ 
/*     */   public Long getCheckUserId()
/*     */   {
/* 191 */     return getAppUser() == null ? null : getAppUser().getUserId();
/*     */   }
/*     */ 
/*     */   public void setCheckUserId(Long aValue)
/*     */   {
/* 198 */     if (aValue == null) {
/* 199 */       this.appUser = null;
/* 200 */     } else if (this.appUser == null) {
/* 201 */       this.appUser = new AppUser(aValue);
/* 202 */       this.appUser.setVersion(new Integer(0));
/*     */     } else {
/* 204 */       this.appUser.setUserId(aValue);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getCheckUserName()
/*     */   {
/* 213 */     return this.checkUserName;
/*     */   }
/*     */ 
/*     */   public void setCheckUserName(String aValue)
/*     */   {
/* 220 */     this.checkUserName = aValue;
/*     */   }
/*     */ 
/*     */   public Date getCheckDate()
/*     */   {
/* 228 */     return this.checkDate;
/*     */   }
/*     */ 
/*     */   public void setCheckDate(Date aValue)
/*     */   {
/* 235 */     this.checkDate = aValue;
/*     */   }
/*     */ 
/*     */   public Date getReturnDate()
/*     */   {
/* 243 */     return this.returnDate;
/*     */   }
/*     */ 
/*     */   public void setReturnDate(Date aValue)
/*     */   {
/* 250 */     this.returnDate = aValue;
/*     */   }
/*     */ 
/*     */   public Short getReturnStatus()
/*     */   {
/* 258 */     return this.returnStatus;
/*     */   }
/*     */ 
/*     */   public void setReturnStatus(Short aValue)
/*     */   {
/* 265 */     this.returnStatus = aValue;
/*     */   }
/*     */ 
/*     */   public String getBorrowNum()
/*     */   {
/* 273 */     return this.borrowNum;
/*     */   }
/*     */ 
/*     */   public void setBorrowNum(String aValue)
/*     */   {
/* 280 */     this.borrowNum = aValue;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 287 */     if (!(object instanceof BorrowRecord)) {
/* 288 */       return false;
/*     */     }
/* 290 */     BorrowRecord rhs = (BorrowRecord)object;
/* 291 */     return new EqualsBuilder()
/* 292 */       .append(this.recordId, rhs.recordId)
/* 294 */       .append(this.borrowRemark, rhs.borrowRemark)
/* 295 */       .append(this.checkId, rhs.checkId)
/* 296 */       .append(this.checkName, rhs.checkName)
/* 297 */       .append(this.checkContent, rhs.checkContent)
/* 299 */       .append(this.borrowDate, rhs.borrowDate)
/* 300 */       .append(this.borrowType, rhs.borrowType)
/* 301 */       .append(this.borrowReason, rhs.borrowReason)
/* 302 */       .append(this.checkUserName, rhs.checkUserName)
/* 303 */       .append(this.checkDate, rhs.checkDate)
/* 304 */       .append(this.returnDate, rhs.returnDate)
/* 305 */       .append(this.returnStatus, rhs.returnStatus)
/* 306 */       .append(this.borrowNum, rhs.borrowNum)
/* 307 */       .isEquals();
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 315 */     return new HashCodeBuilder(-82280557, -700257973)
/* 316 */       .append(this.recordId)
/* 318 */       .append(this.borrowRemark)
/* 319 */       .append(this.checkId)
/* 320 */       .append(this.checkName)
/* 321 */       .append(this.checkContent)
/* 323 */       .append(this.borrowDate)
/* 324 */       .append(this.borrowType)
/* 325 */       .append(this.borrowReason)
/* 326 */       .append(this.checkUserName)
/* 327 */       .append(this.checkDate)
/* 328 */       .append(this.returnDate)
/* 329 */       .append(this.returnStatus)
/* 330 */       .append(this.borrowNum)
/* 331 */       .toHashCode();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 339 */     return new ToStringBuilder(this)
/* 340 */       .append("recordId", this.recordId)
/* 342 */       .append("borrowRemark", this.borrowRemark)
/* 343 */       .append("checkId", this.checkId)
/* 344 */       .append("checkName", this.checkName)
/* 345 */       .append("checkContent", this.checkContent)
/* 347 */       .append("borrowDate", this.borrowDate)
/* 348 */       .append("borrowType", this.borrowType)
/* 349 */       .append("borrowReason", this.borrowReason)
/* 350 */       .append("checkUserName", this.checkUserName)
/* 351 */       .append("checkDate", this.checkDate)
/* 352 */       .append("returnDate", this.returnDate)
/* 353 */       .append("returnStatus", this.returnStatus)
/* 354 */       .append("borrowNum", this.borrowNum)
/* 355 */       .toString();
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.arch.BorrowRecord
 * JD-Core Version:    0.6.0
 */