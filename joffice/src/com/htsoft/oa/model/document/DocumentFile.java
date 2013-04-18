/*     */ package com.htsoft.oa.model.document;
/*     */ 
/*     */ import java.util.Date;
/*     */ 
/*     */ public class DocumentFile
/*     */ {
/*   6 */   public static Short IS_FOLDER = Short.valueOf((short)1);
/*   7 */   public static Short NOT_FOLDER = Short.valueOf((short)0);
/*     */   private Long fileId;
/*     */   private String fileName;
/*     */   private String fileType;
/*     */   private String fileSize;
/*     */   private Short isFolder;
/*     */   private Short isShared;
/*     */   private String author;
/*     */   private String keywords;
/*     */   private Date updateTime;
/*     */   private Long parentId;
/*     */   private String parentName;
/*     */   private Short rightRead;
/*     */   private Short rightMod;
/*     */   private Short rightDel;
/*     */ 
/*     */   public Long getFileId()
/*     */   {
/*  26 */     return this.fileId;
/*     */   }
/*     */   public void setFileId(Long fileId) {
/*  29 */     this.fileId = fileId;
/*     */   }
/*     */   public String getFileName() {
/*  32 */     return this.fileName;
/*     */   }
/*     */   public void setFileName(String fileName) {
/*  35 */     this.fileName = fileName;
/*     */   }
/*     */   public String getFileType() {
/*  38 */     return this.fileType;
/*     */   }
/*     */   public void setFileType(String fileType) {
/*  41 */     this.fileType = fileType;
/*     */   }
/*     */   public String getFileSize() {
/*  44 */     return this.fileSize;
/*     */   }
/*     */   public void setFileSize(String fileSize) {
/*  47 */     this.fileSize = fileSize;
/*     */   }
/*     */   public Short getIsFolder() {
/*  50 */     return this.isFolder;
/*     */   }
/*     */   public void setIsFolder(Short isFolder) {
/*  53 */     this.isFolder = isFolder;
/*     */   }
/*     */   public Long getParentId() {
/*  56 */     return this.parentId;
/*     */   }
/*     */   public void setParentId(Long parentId) {
/*  59 */     this.parentId = parentId;
/*     */   }
/*     */   public Short getIsShared() {
/*  62 */     return this.isShared;
/*     */   }
/*     */   public void setIsShared(Short isShared) {
/*  65 */     this.isShared = isShared;
/*     */   }
/*     */   public String getParentName() {
/*  68 */     return this.parentName;
/*     */   }
/*     */   public void setParentName(String parentName) {
/*  71 */     this.parentName = parentName;
/*     */   }
/*     */   public Short getRightRead() {
/*  74 */     return this.rightRead;
/*     */   }
/*     */   public void setRightRead(Short rightRead) {
/*  77 */     this.rightRead = rightRead;
/*     */   }
/*     */   public Short getRightMod() {
/*  80 */     return this.rightMod;
/*     */   }
/*     */   public void setRightMod(Short rightMod) {
/*  83 */     this.rightMod = rightMod;
/*     */   }
/*     */   public Short getRightDel() {
/*  86 */     return this.rightDel;
/*     */   }
/*     */   public void setRightDel(Short rightDel) {
/*  89 */     this.rightDel = rightDel;
/*     */   }
/*     */   public String getAuthor() {
/*  92 */     return this.author;
/*     */   }
/*     */   public void setAuthor(String author) {
/*  95 */     this.author = author;
/*     */   }
/*     */   public String getKeywords() {
/*  98 */     return this.keywords;
/*     */   }
/*     */   public void setKeywords(String keywords) {
/* 101 */     this.keywords = keywords;
/*     */   }
/*     */   public Date getUpdateTime() {
/* 104 */     return this.updateTime;
/*     */   }
/*     */   public void setUpdateTime(Date updateTime) {
/* 107 */     this.updateTime = updateTime;
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.document.DocumentFile
 * JD-Core Version:    0.6.0
 */