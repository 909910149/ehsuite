/*     */ package com.htsoft.oa.model.archive;
/*     */ 
/*     */ import com.htsoft.core.model.BaseModel;
/*     */ import com.htsoft.oa.model.system.FileAttach;
/*     */ import com.htsoft.oa.model.system.GlobalType;
/*     */ import org.apache.commons.lang.builder.EqualsBuilder;
/*     */ import org.apache.commons.lang.builder.HashCodeBuilder;
/*     */ import org.apache.commons.lang.builder.ToStringBuilder;
/*     */ 
/*     */ public class ArchTemplate extends BaseModel
/*     */ {
/*     */   protected Long templateId;
/*     */   protected String tempName;
/*     */   protected String tempPath;
/*     */   protected FileAttach fileAttach;
/*     */   protected GlobalType archivesType;
/*     */ 
/*     */   public ArchTemplate()
/*     */   {
/*     */   }
/*     */ 
/*     */   public ArchTemplate(Long in_templateId)
/*     */   {
/*  41 */     setTemplateId(in_templateId);
/*     */   }
/*     */ 
/*     */   public FileAttach getFileAttach()
/*     */   {
/*  46 */     return this.fileAttach;
/*     */   }
/*     */ 
/*     */   public void setFileAttach(FileAttach in_fileAttach) {
/*  50 */     this.fileAttach = in_fileAttach;
/*     */   }
/*     */ 
/*     */   public GlobalType getArchivesType() {
/*  54 */     return this.archivesType;
/*     */   }
/*     */ 
/*     */   public void setArchivesType(GlobalType in_archivesType) {
/*  58 */     this.archivesType = in_archivesType;
/*     */   }
/*     */ 
/*     */   public Long getTemplateId()
/*     */   {
/*  67 */     return this.templateId;
/*     */   }
/*     */ 
/*     */   public void setTemplateId(Long aValue)
/*     */   {
/*  74 */     this.templateId = aValue;
/*     */   }
/*     */ 
/*     */   public Long getTypeId()
/*     */   {
/*  81 */     return getArchivesType() == null ? null : getArchivesType().getProTypeId();
/*     */   }
/*     */ 
/*     */   public void setTypeId(Long aValue)
/*     */   {
/*  88 */     if (aValue == null) {
/*  89 */       this.archivesType = null;
/*  90 */     } else if (this.archivesType == null) {
/*  91 */       this.archivesType = new GlobalType(aValue);
/*  92 */       this.archivesType.setVersion(new Integer(0));
/*     */     } else {
/*  94 */       this.archivesType.setProTypeId(aValue);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getTempName()
/*     */   {
/* 103 */     return this.tempName;
/*     */   }
/*     */ 
/*     */   public void setTempName(String aValue)
/*     */   {
/* 111 */     this.tempName = aValue;
/*     */   }
/*     */ 
/*     */   public String getTempPath()
/*     */   {
/* 119 */     return this.tempPath;
/*     */   }
/*     */ 
/*     */   public void setTempPath(String aValue)
/*     */   {
/* 127 */     this.tempPath = aValue;
/*     */   }
/*     */ 
/*     */   public Long getFileId()
/*     */   {
/* 134 */     return getFileAttach() == null ? null : getFileAttach().getFileId();
/*     */   }
/*     */ 
/*     */   public void setFileId(Long aValue)
/*     */   {
/* 141 */     if (aValue == null) {
/* 142 */       this.fileAttach = null;
/* 143 */     } else if (this.fileAttach == null) {
/* 144 */       this.fileAttach = new FileAttach(aValue);
/* 145 */       this.fileAttach.setVersion(new Integer(0));
/*     */     } else {
/* 147 */       this.fileAttach.setFileId(aValue);
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 155 */     if (!(object instanceof ArchTemplate)) {
/* 156 */       return false;
/*     */     }
/* 158 */     ArchTemplate rhs = (ArchTemplate)object;
/* 159 */     return new EqualsBuilder()
/* 160 */       .append(this.templateId, rhs.templateId)
/* 161 */       .append(this.tempName, rhs.tempName)
/* 162 */       .append(this.tempPath, rhs.tempPath)
/* 163 */       .isEquals();
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 170 */     return new HashCodeBuilder(-82280557, -700257973)
/* 171 */       .append(this.templateId)
/* 172 */       .append(this.tempName)
/* 173 */       .append(this.tempPath)
/* 174 */       .toHashCode();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 181 */     return new ToStringBuilder(this)
/* 182 */       .append("templateId", this.templateId)
/* 183 */       .append("tempName", this.tempName)
/* 184 */       .append("tempPath", this.tempPath)
/* 185 */       .toString();
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.archive.ArchTemplate
 * JD-Core Version:    0.6.0
 */