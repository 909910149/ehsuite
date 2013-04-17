/*     */ package com.htsoft.oa.model.document;
/*     */ 
/*     */ import com.htsoft.core.model.BaseModel;
/*     */ import com.htsoft.oa.model.system.FileAttach;
/*     */ import org.apache.commons.lang.builder.EqualsBuilder;
/*     */ import org.apache.commons.lang.builder.HashCodeBuilder;
/*     */ import org.apache.commons.lang.builder.ToStringBuilder;
/*     */ 
/*     */ public class PaintTemplate extends BaseModel
/*     */ {
/*     */   protected Long ptemplateId;
/*     */   protected String templateName;
/*     */   protected String path;
/*     */   protected Short isActivate;
/*     */   protected FileAttach fileAttach;
/*     */ 
/*     */   public PaintTemplate()
/*     */   {
/*     */   }
/*     */ 
/*     */   public PaintTemplate(Long in_ptemplateId)
/*     */   {
/*  39 */     setPtemplateId(in_ptemplateId);
/*     */   }
/*     */ 
/*     */   public FileAttach getFileAttach()
/*     */   {
/*  44 */     return this.fileAttach;
/*     */   }
/*     */ 
/*     */   public void setFileAttach(FileAttach in_fileAttach) {
/*  48 */     this.fileAttach = in_fileAttach;
/*     */   }
/*     */ 
/*     */   public Long getPtemplateId()
/*     */   {
/*  57 */     return this.ptemplateId;
/*     */   }
/*     */ 
/*     */   public void setPtemplateId(Long aValue)
/*     */   {
/*  64 */     this.ptemplateId = aValue;
/*     */   }
/*     */ 
/*     */   public Long getFileId()
/*     */   {
/*  71 */     return getFileAttach() == null ? null : getFileAttach().getFileId();
/*     */   }
/*     */ 
/*     */   public void setFileId(Long aValue)
/*     */   {
/*  78 */     if (aValue == null) {
/*  79 */       this.fileAttach = null;
/*  80 */     } else if (this.fileAttach == null) {
/*  81 */       this.fileAttach = new FileAttach(aValue);
/*  82 */       this.fileAttach.setVersion(new Integer(0));
/*     */     } else {
/*  84 */       this.fileAttach.setFileId(aValue);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getTemplateName()
/*     */   {
/*  93 */     return this.templateName;
/*     */   }
/*     */ 
/*     */   public void setTemplateName(String aValue)
/*     */   {
/* 101 */     this.templateName = aValue;
/*     */   }
/*     */ 
/*     */   public String getPath()
/*     */   {
/* 109 */     return this.path;
/*     */   }
/*     */ 
/*     */   public void setPath(String aValue)
/*     */   {
/* 117 */     this.path = aValue;
/*     */   }
/*     */ 
/*     */   public Short getIsActivate()
/*     */   {
/* 125 */     return this.isActivate;
/*     */   }
/*     */ 
/*     */   public void setIsActivate(Short aValue)
/*     */   {
/* 133 */     this.isActivate = aValue;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 140 */     if (!(object instanceof PaintTemplate)) {
/* 141 */       return false;
/*     */     }
/* 143 */     PaintTemplate rhs = (PaintTemplate)object;
/* 144 */     return new EqualsBuilder()
/* 145 */       .append(this.ptemplateId, rhs.ptemplateId)
/* 146 */       .append(this.templateName, rhs.templateName)
/* 147 */       .append(this.path, rhs.path)
/* 148 */       .append(this.isActivate, rhs.isActivate)
/* 149 */       .isEquals();
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 156 */     return new HashCodeBuilder(-82280557, -700257973)
/* 157 */       .append(this.ptemplateId)
/* 158 */       .append(this.templateName)
/* 159 */       .append(this.path)
/* 160 */       .append(this.isActivate)
/* 161 */       .toHashCode();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 168 */     return new ToStringBuilder(this)
/* 169 */       .append("ptemplateId", this.ptemplateId)
/* 170 */       .append("templateName", this.templateName)
/* 171 */       .append("path", this.path)
/* 172 */       .append("isActivate", this.isActivate)
/* 173 */       .toString();
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.document.PaintTemplate
 * JD-Core Version:    0.6.0
 */