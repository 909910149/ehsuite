/*     */ package com.htsoft.oa.model.arch;
/*     */ 
/*     */ import com.google.gson.annotations.Expose;
/*     */ import com.htsoft.core.model.BaseModel;
/*     */ import org.apache.commons.lang.builder.EqualsBuilder;
/*     */ import org.apache.commons.lang.builder.HashCodeBuilder;
/*     */ import org.apache.commons.lang.builder.ToStringBuilder;
/*     */ 
/*     */ public class BorrowFileList extends BaseModel
/*     */ {
/*     */ 
/*     */   @Expose
/*     */   protected Long listId;
/*     */ 
/*     */   @Expose
/*     */   protected String listType;
/*     */ 
/*     */   @Expose
/*     */   protected String afNo;
/*     */ 
/*     */   @Expose
/*     */   protected String afName;
/*     */ 
/*     */   @Expose
/*     */   protected String rollNo;
/*     */ 
/*     */   @Expose
/*     */   protected String rolllName;
/*     */ 
/*     */   @Expose
/*     */   protected String fileNo;
/*     */ 
/*     */   @Expose
/*     */   protected String fileName;
/*     */ 
/*     */   @Expose
/*     */   protected RollFile rollFile;
/*     */ 
/*     */   @Expose
/*     */   protected ArchRoll archRoll;
/*     */ 
/*     */   @Expose
/*     */   protected ArchFond archFond;
/*     */ 
/*     */   @Expose
/*     */   protected BorrowRecord borrowRecord;
/*     */ 
/*     */   public BorrowFileList()
/*     */   {
/*     */   }
/*     */ 
/*     */   public BorrowFileList(Long in_listId)
/*     */   {
/*  59 */     setListId(in_listId);
/*     */   }
/*     */ 
/*     */   public RollFile getRollFile()
/*     */   {
/*  64 */     return this.rollFile;
/*     */   }
/*     */ 
/*     */   public void setRollFile(RollFile in_rollFile) {
/*  68 */     this.rollFile = in_rollFile;
/*     */   }
/*     */ 
/*     */   public ArchRoll getArchRoll() {
/*  72 */     return this.archRoll;
/*     */   }
/*     */ 
/*     */   public void setArchRoll(ArchRoll in_archRoll) {
/*  76 */     this.archRoll = in_archRoll;
/*     */   }
/*     */ 
/*     */   public ArchFond getArchFond() {
/*  80 */     return this.archFond;
/*     */   }
/*     */ 
/*     */   public void setArchFond(ArchFond in_archFond) {
/*  84 */     this.archFond = in_archFond;
/*     */   }
/*     */ 
/*     */   public BorrowRecord getBorrowRecord() {
/*  88 */     return this.borrowRecord;
/*     */   }
/*     */ 
/*     */   public void setBorrowRecord(BorrowRecord in_borrowRecord) {
/*  92 */     this.borrowRecord = in_borrowRecord;
/*     */   }
/*     */ 
/*     */   public Long getListId()
/*     */   {
/* 101 */     return this.listId;
/*     */   }
/*     */ 
/*     */   public void setListId(Long aValue)
/*     */   {
/* 108 */     this.listId = aValue;
/*     */   }
/*     */ 
/*     */   public Long getRecordId()
/*     */   {
/* 115 */     return getBorrowRecord() == null ? null : getBorrowRecord().getRecordId();
/*     */   }
/*     */ 
/*     */   public void setRecordId(Long aValue)
/*     */   {
/* 122 */     if (aValue == null) {
/* 123 */       this.borrowRecord = null;
/* 124 */     } else if (this.borrowRecord == null) {
/* 125 */       this.borrowRecord = new BorrowRecord(aValue);
/* 126 */       this.borrowRecord.setVersion(new Integer(0));
/*     */     } else {
/* 128 */       this.borrowRecord.setRecordId(aValue);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getListType()
/*     */   {
/* 139 */     return this.listType;
/*     */   }
/*     */ 
/*     */   public void setListType(String aValue)
/*     */   {
/* 146 */     this.listType = aValue;
/*     */   }
/*     */ 
/*     */   public Long getArchFondId()
/*     */   {
/* 153 */     return getArchFond() == null ? null : getArchFond().getArchFondId();
/*     */   }
/*     */ 
/*     */   public void setArchFondId(Long aValue)
/*     */   {
/* 160 */     if (aValue == null) {
/* 161 */       this.archFond = null;
/* 162 */     } else if (this.archFond == null) {
/* 163 */       this.archFond = new ArchFond(aValue);
/* 164 */       this.archFond.setVersion(new Integer(0));
/*     */     } else {
/* 166 */       this.archFond.setArchFondId(aValue);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getAfNo()
/*     */   {
/* 175 */     return this.afNo;
/*     */   }
/*     */ 
/*     */   public void setAfNo(String aValue)
/*     */   {
/* 182 */     this.afNo = aValue;
/*     */   }
/*     */ 
/*     */   public String getAfName()
/*     */   {
/* 190 */     return this.afName;
/*     */   }
/*     */ 
/*     */   public void setAfName(String aValue)
/*     */   {
/* 197 */     this.afName = aValue;
/*     */   }
/*     */ 
/*     */   public Long getRollId()
/*     */   {
/* 204 */     return getArchRoll() == null ? null : getArchRoll().getRollId();
/*     */   }
/*     */ 
/*     */   public void setRollId(Long aValue)
/*     */   {
/* 211 */     if (aValue == null) {
/* 212 */       this.archRoll = null;
/* 213 */     } else if (this.archRoll == null) {
/* 214 */       this.archRoll = new ArchRoll(aValue);
/* 215 */       this.archRoll.setVersion(new Integer(0));
/*     */     } else {
/* 217 */       this.archRoll.setRollId(aValue);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getRollNo()
/*     */   {
/* 226 */     return this.rollNo;
/*     */   }
/*     */ 
/*     */   public void setRollNo(String aValue)
/*     */   {
/* 233 */     this.rollNo = aValue;
/*     */   }
/*     */ 
/*     */   public String getRolllName()
/*     */   {
/* 241 */     return this.rolllName;
/*     */   }
/*     */ 
/*     */   public void setRolllName(String aValue)
/*     */   {
/* 248 */     this.rolllName = aValue;
/*     */   }
/*     */ 
/*     */   public Long getRollFileId()
/*     */   {
/* 255 */     return getRollFile() == null ? null : getRollFile().getRollFileId();
/*     */   }
/*     */ 
/*     */   public void setRollFileId(Long aValue)
/*     */   {
/* 262 */     if (aValue == null) {
/* 263 */       this.rollFile = null;
/* 264 */     } else if (this.rollFile == null) {
/* 265 */       this.rollFile = new RollFile(aValue);
/* 266 */       this.rollFile.setVersion(new Integer(0));
/*     */     } else {
/* 268 */       this.rollFile.setRollFileId(aValue);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getFileNo()
/*     */   {
/* 277 */     return this.fileNo;
/*     */   }
/*     */ 
/*     */   public void setFileNo(String aValue)
/*     */   {
/* 284 */     this.fileNo = aValue;
/*     */   }
/*     */ 
/*     */   public String getFileName()
/*     */   {
/* 292 */     return this.fileName;
/*     */   }
/*     */ 
/*     */   public void setFileName(String aValue)
/*     */   {
/* 299 */     this.fileName = aValue;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 306 */     if (!(object instanceof BorrowFileList)) {
/* 307 */       return false;
/*     */     }
/* 309 */     BorrowFileList rhs = (BorrowFileList)object;
/* 310 */     return new EqualsBuilder()
/* 311 */       .append(this.listId, rhs.listId)
/* 312 */       .append(this.listType, rhs.listType)
/* 313 */       .append(this.afNo, rhs.afNo)
/* 314 */       .append(this.afName, rhs.afName)
/* 315 */       .append(this.rollNo, rhs.rollNo)
/* 316 */       .append(this.rolllName, rhs.rolllName)
/* 317 */       .append(this.fileNo, rhs.fileNo)
/* 318 */       .append(this.fileName, rhs.fileName)
/* 319 */       .isEquals();
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 326 */     return new HashCodeBuilder(-82280557, -700257973)
/* 327 */       .append(this.listId)
/* 328 */       .append(this.listType)
/* 329 */       .append(this.afNo)
/* 330 */       .append(this.afName)
/* 331 */       .append(this.rollNo)
/* 332 */       .append(this.rolllName)
/* 333 */       .append(this.fileNo)
/* 334 */       .append(this.fileName)
/* 335 */       .toHashCode();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 342 */     return new ToStringBuilder(this)
/* 343 */       .append("listId", this.listId)
/* 344 */       .append("listType", this.listType)
/* 345 */       .append("afNo", this.afNo)
/* 346 */       .append("afName", this.afName)
/* 347 */       .append("rollNo", this.rollNo)
/* 348 */       .append("rolllName", this.rolllName)
/* 349 */       .append("fileNo", this.fileNo)
/* 350 */       .append("fileName", this.fileName)
/* 351 */       .toString();
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.arch.BorrowFileList
 * JD-Core Version:    0.6.0
 */