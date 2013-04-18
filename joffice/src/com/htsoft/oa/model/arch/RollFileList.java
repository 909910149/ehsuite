/*     */ package com.htsoft.oa.model.arch;
/*     */ 
/*     */ import com.htsoft.core.model.BaseModel;
/*     */ import com.htsoft.oa.model.system.FileAttach;
/*     */ import org.apache.commons.lang.builder.EqualsBuilder;
/*     */ import org.apache.commons.lang.builder.HashCodeBuilder;
/*     */ import org.apache.commons.lang.builder.ToStringBuilder;
/*     */ 
/*     */ public class RollFileList extends BaseModel
/*     */ {
/*     */   protected Long listId;
/*     */   protected Integer downLoads;
/*     */   protected Integer sn;
/*     */   protected String shortDesc;
/*     */   protected RollFile rollFile;
/*     */   protected FileAttach fileAttach;
/*     */ 
/*     */   public Long getFileId()
/*     */   {
/*  31 */     return getFileAttach().getFileId();
/*     */   }
/*     */ 
/*     */   public void setFileId(Long aValue) {
/*  35 */     if (aValue == null) {
/*  36 */       this.fileAttach = null;
/*  37 */     } else if (this.fileAttach == null) {
/*  38 */       this.fileAttach = new FileAttach(aValue);
/*  39 */       this.fileAttach.setVersion(new Integer(0));
/*     */     } else {
/*  41 */       this.fileAttach.setFileId(aValue);
/*     */     }
/*     */   }
/*     */ 
/*     */   public FileAttach getFileAttach()
/*     */   {
/*  48 */     return this.fileAttach;
/*     */   }
/*     */ 
/*     */   public void setFileAttach(FileAttach fileAttach) {
/*  52 */     this.fileAttach = fileAttach;
/*     */   }
/*     */ 
/*     */   public Long getListId() {
/*  56 */     return this.listId;
/*     */   }
/*     */ 
/*     */   public void setListId(Long listId) {
/*  60 */     this.listId = listId;
/*     */   }
/*     */   public Integer getDownLoads() {
/*  63 */     return this.downLoads;
/*     */   }
/*     */ 
/*     */   public void setDownLoads(Integer downLoads) {
/*  67 */     this.downLoads = downLoads;
/*     */   }
/*     */ 
/*     */   public RollFileList()
/*     */   {
/*     */   }
/*     */ 
/*     */   public RollFileList(Long in_fileId)
/*     */   {
/*  91 */     setListId(in_fileId);
/*     */   }
/*     */ 
/*     */   public RollFile getRollFile()
/*     */   {
/*  96 */     return this.rollFile;
/*     */   }
/*     */ 
/*     */   public void setRollFile(RollFile in_rollFile) {
/* 100 */     this.rollFile = in_rollFile;
/*     */   }
/*     */ 
/*     */   public Long getRollFileId()
/*     */   {
/* 114 */     return getRollFile() == null ? null : getRollFile().getRollFileId();
/*     */   }
/*     */ 
/*     */   public void setRollFileId(Long aValue)
/*     */   {
/* 121 */     if (aValue == null) {
/* 122 */       this.rollFile = null;
/* 123 */     } else if (this.rollFile == null) {
/* 124 */       this.rollFile = new RollFile(aValue);
/* 125 */       this.rollFile.setVersion(new Integer(0));
/*     */     } else {
/* 127 */       this.rollFile.setRollFileId(aValue);
/*     */     }
/*     */   }
/*     */ 
/*     */   public Integer getSn()
/*     */   {
/* 176 */     return this.sn;
/*     */   }
/*     */ 
/*     */   public void setSn(Integer aValue)
/*     */   {
/* 183 */     this.sn = aValue;
/*     */   }
/*     */ 
/*     */   public String getShortDesc()
/*     */   {
/* 191 */     return this.shortDesc;
/*     */   }
/*     */ 
/*     */   public void setShortDesc(String aValue)
/*     */   {
/* 198 */     this.shortDesc = aValue;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 205 */     if (!(object instanceof RollFileList)) {
/* 206 */       return false;
/*     */     }
/* 208 */     RollFileList rhs = (RollFileList)object;
/* 209 */     return new EqualsBuilder()
/* 214 */       .append(this.downLoads, rhs.downLoads)
/* 216 */       .append(this.sn, rhs.sn)
/* 217 */       .append(this.shortDesc, rhs.shortDesc)
/* 218 */       .isEquals();
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 225 */     return new HashCodeBuilder(-82280557, -700257973)
/* 229 */       .append(this.downLoads)
/* 232 */       .append(this.sn)
/* 233 */       .append(this.shortDesc)
/* 234 */       .toHashCode();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 241 */     return new ToStringBuilder(this)
/* 246 */       .append("downLoads", this.downLoads)
/* 248 */       .append("sn", this.sn)
/* 249 */       .append("shortDesc", this.shortDesc)
/* 250 */       .toString();
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.arch.RollFileList
 * JD-Core Version:    0.6.0
 */