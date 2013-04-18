/*     */ package com.htsoft.oa.model.document;
/*     */ 
/*     */ import com.google.gson.annotations.Expose;
/*     */ import com.htsoft.core.model.BaseModel;
/*     */ import com.htsoft.oa.model.system.AppUser;
/*     */ import com.htsoft.oa.model.system.FileAttach;
/*     */ import java.util.Date;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang.builder.EqualsBuilder;
/*     */ import org.apache.commons.lang.builder.HashCodeBuilder;
/*     */ import org.apache.commons.lang.builder.ToStringBuilder;
/*     */ 
/*     */ public class Document extends BaseModel
/*     */ {
/*  26 */   public static final Short SHARED = Short.valueOf((short)1);
/*  27 */   public static final Short NOT_SHARED = Short.valueOf((short)0);
/*     */ 
/*  29 */   public static final Short ONLINE_DOCUMENT = Short.valueOf((short)2);
/*     */ 
/*  31 */   public static final Short HAVE_ATTACH = Short.valueOf((short)1);
/*  32 */   public static final Short NOT_HAVE_ATTACH = Short.valueOf((short)0);
/*     */ 
/*     */   @Expose
/*     */   protected Long docId;
/*     */ 
/*     */   @Expose
/*     */   protected String docName;
/*     */ 
/*     */   @Expose
/*     */   protected String content;
/*     */ 
/*     */   @Expose
/*     */   protected Date createtime;
/*     */ 
/*     */   @Expose
/*     */   protected Date updatetime;
/*     */ 
/*     */   @Expose
/*     */   protected Short haveAttach;
/*     */ 
/*     */   @Expose
/*     */   protected String sharedUserIds;
/*     */ 
/*     */   @Expose
/*     */   protected String sharedUserNames;
/*     */ 
/*     */   @Expose
/*     */   protected String sharedDepIds;
/*     */ 
/*     */   @Expose
/*     */   protected String sharedDepNames;
/*     */ 
/*     */   @Expose
/*     */   protected String sharedRoleIds;
/*     */ 
/*     */   @Expose
/*     */   protected String sharedRoleNames;
/*     */ 
/*     */   @Expose
/*     */   protected Short isShared;
/*     */ 
/*     */   @Expose
/*     */   protected String fullname;
/*     */ 
/*     */   @Expose
/*     */   protected String author;
/*     */ 
/*     */   @Expose
/*     */   protected String keywords;
/*     */ 
/*     */   @Expose
/*     */   protected String docType;
/*     */ 
/*     */   @Expose
/*     */   protected DocFolder docFolder;
/*     */   protected AppUser appUser;
/*     */ 
/*     */   @Expose
/*  71 */   protected Set<FileAttach> attachFiles = new HashSet();
/*     */ 
/*     */   public Document()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Document(Long in_docId)
/*     */   {
/*  86 */     setDocId(in_docId);
/*     */   }
/*     */ 
/*     */   public String getFullname() {
/*  90 */     return this.fullname;
/*     */   }
/*     */ 
/*     */   public void setFullname(String fullname) {
/*  94 */     this.fullname = fullname;
/*     */   }
/*     */ 
/*     */   public DocFolder getDocFolder() {
/*  98 */     return this.docFolder;
/*     */   }
/*     */ 
/*     */   public void setDocFolder(DocFolder in_docFolder) {
/* 102 */     this.docFolder = in_docFolder;
/*     */   }
/*     */ 
/*     */   public AppUser getAppUser() {
/* 106 */     return this.appUser;
/*     */   }
/*     */ 
/*     */   public void setAppUser(AppUser in_appUser) {
/* 110 */     this.appUser = in_appUser;
/*     */   }
/*     */ 
/*     */   public Long getDocId()
/*     */   {
/* 119 */     return this.docId;
/*     */   }
/*     */ 
/*     */   public void setDocId(Long aValue)
/*     */   {
/* 126 */     this.docId = aValue;
/*     */   }
/*     */ 
/*     */   public String getDocName()
/*     */   {
/* 134 */     return this.docName;
/*     */   }
/*     */ 
/*     */   public void setDocName(String aValue)
/*     */   {
/* 142 */     this.docName = aValue;
/*     */   }
/*     */ 
/*     */   public String getContent()
/*     */   {
/* 150 */     return this.content;
/*     */   }
/*     */ 
/*     */   public void setContent(String aValue)
/*     */   {
/* 157 */     this.content = aValue;
/*     */   }
/*     */ 
/*     */   public Date getCreatetime()
/*     */   {
/* 165 */     return this.createtime;
/*     */   }
/*     */ 
/*     */   public void setCreatetime(Date aValue)
/*     */   {
/* 173 */     this.createtime = aValue;
/*     */   }
/*     */ 
/*     */   public Date getUpdatetime()
/*     */   {
/* 181 */     return this.updatetime;
/*     */   }
/*     */ 
/*     */   public void setUpdatetime(Date aValue)
/*     */   {
/* 188 */     this.updatetime = aValue;
/*     */   }
/*     */ 
/*     */   public Long getFolderId()
/*     */   {
/* 195 */     return getDocFolder() == null ? null : getDocFolder().getFolderId();
/*     */   }
/*     */ 
/*     */   public void setFolderId(Long aValue)
/*     */   {
/* 202 */     if (aValue == null) {
/* 203 */       this.docFolder = null;
/* 204 */     } else if (this.docFolder == null) {
/* 205 */       this.docFolder = new DocFolder(aValue);
/* 206 */       this.docFolder.setVersion(new Integer(0));
/*     */     } else {
/* 208 */       this.docFolder.setFolderId(aValue);
/*     */     }
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
/*     */   public Short getHaveAttach()
/*     */   {
/* 238 */     return this.haveAttach;
/*     */   }
/*     */ 
/*     */   public void setHaveAttach(Short aValue)
/*     */   {
/* 245 */     this.haveAttach = aValue;
/*     */   }
/*     */ 
/*     */   public String getSharedUserIds()
/*     */   {
/* 253 */     return this.sharedUserIds;
/*     */   }
/*     */ 
/*     */   public void setSharedUserIds(String aValue)
/*     */   {
/* 260 */     this.sharedUserIds = aValue;
/*     */   }
/*     */ 
/*     */   public String getSharedDepIds()
/*     */   {
/* 268 */     return this.sharedDepIds;
/*     */   }
/*     */ 
/*     */   public void setSharedDepIds(String aValue)
/*     */   {
/* 275 */     this.sharedDepIds = aValue;
/*     */   }
/*     */ 
/*     */   public String getSharedRoleIds()
/*     */   {
/* 283 */     return this.sharedRoleIds;
/*     */   }
/*     */ 
/*     */   public void setSharedRoleIds(String aValue)
/*     */   {
/* 290 */     this.sharedRoleIds = aValue;
/*     */   }
/*     */ 
/*     */   public Short getIsShared()
/*     */   {
/* 298 */     return this.isShared;
/*     */   }
/*     */ 
/*     */   public void setIsShared(Short aValue)
/*     */   {
/* 306 */     this.isShared = aValue;
/*     */   }
/*     */ 
/*     */   public String getDocType() {
/* 310 */     return this.docType;
/*     */   }
/*     */ 
/*     */   public void setDocType(String docType) {
/* 314 */     this.docType = docType;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 321 */     if (!(object instanceof Document)) {
/* 322 */       return false;
/*     */     }
/* 324 */     Document rhs = (Document)object;
/* 325 */     return new EqualsBuilder()
/* 326 */       .append(this.docId, rhs.docId)
/* 327 */       .append(this.docName, rhs.docName)
/* 328 */       .append(this.fullname, rhs.fullname)
/* 329 */       .append(this.content, rhs.content)
/* 330 */       .append(this.createtime, rhs.createtime)
/* 331 */       .append(this.updatetime, rhs.updatetime)
/* 332 */       .append(this.haveAttach, rhs.haveAttach)
/* 333 */       .append(this.sharedUserIds, rhs.sharedUserIds)
/* 334 */       .append(this.sharedDepIds, rhs.sharedDepIds)
/* 335 */       .append(this.sharedRoleIds, rhs.sharedRoleIds)
/* 336 */       .append(this.isShared, rhs.isShared)
/* 337 */       .isEquals();
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 344 */     return new HashCodeBuilder(-82280557, -700257973)
/* 345 */       .append(this.docId)
/* 346 */       .append(this.docName)
/* 347 */       .append(this.content)
/* 348 */       .append(this.createtime)
/* 349 */       .append(this.updatetime)
/* 350 */       .append(this.fullname)
/* 351 */       .append(this.haveAttach)
/* 352 */       .append(this.sharedUserIds)
/* 353 */       .append(this.sharedDepIds)
/* 354 */       .append(this.sharedRoleIds)
/* 355 */       .append(this.isShared)
/* 356 */       .toHashCode();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 363 */     return new ToStringBuilder(this)
/* 364 */       .append("docId", this.docId)
/* 365 */       .append("docName", this.docName)
/* 366 */       .append("content", this.content)
/* 367 */       .append("fullname", this.fullname)
/* 368 */       .append("createtime", this.createtime)
/* 369 */       .append("updatetime", this.updatetime)
/* 370 */       .append("haveAttach", this.haveAttach)
/* 371 */       .append("sharedUserIds", this.sharedUserIds)
/* 372 */       .append("sharedDepIds", this.sharedDepIds)
/* 373 */       .append("sharedRoleIds", this.sharedRoleIds)
/* 374 */       .append("isShared", this.isShared)
/* 375 */       .toString();
/*     */   }
/*     */ 
/*     */   public String getFirstKeyColumnName()
/*     */   {
/* 382 */     return "docId";
/*     */   }
/*     */ 
/*     */   public Long getId()
/*     */   {
/* 389 */     return this.docId;
/*     */   }
/*     */ 
/*     */   public Set<FileAttach> getAttachFiles() {
/* 393 */     return this.attachFiles;
/*     */   }
/*     */ 
/*     */   public void setAttachFiles(Set<FileAttach> attachFiles) {
/* 397 */     this.attachFiles = attachFiles;
/*     */   }
/*     */ 
/*     */   public String getSharedUserNames() {
/* 401 */     return this.sharedUserNames;
/*     */   }
/*     */ 
/*     */   public void setSharedUserNames(String sharedUserNames) {
/* 405 */     this.sharedUserNames = sharedUserNames;
/*     */   }
/*     */ 
/*     */   public String getSharedDepNames() {
/* 409 */     return this.sharedDepNames;
/*     */   }
/*     */ 
/*     */   public void setSharedDepNames(String sharedDepNames) {
/* 413 */     this.sharedDepNames = sharedDepNames;
/*     */   }
/*     */ 
/*     */   public String getSharedRoleNames() {
/* 417 */     return this.sharedRoleNames;
/*     */   }
/*     */ 
/*     */   public void setSharedRoleNames(String sharedRoleNames) {
/* 421 */     this.sharedRoleNames = sharedRoleNames;
/*     */   }
/*     */ 
/*     */   public String getAuthor() {
/* 425 */     return this.author;
/*     */   }
/*     */ 
/*     */   public void setAuthor(String author) {
/* 429 */     this.author = author;
/*     */   }
/*     */ 
/*     */   public String getKeywords() {
/* 433 */     return this.keywords;
/*     */   }
/*     */ 
/*     */   public void setKeywords(String keywords) {
/* 437 */     this.keywords = keywords;
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.document.Document
 * JD-Core Version:    0.6.0
 */