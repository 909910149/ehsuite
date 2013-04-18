/*     */ package com.htsoft.oa.model.document;
/*     */ 
/*     */ import com.htsoft.core.model.BaseModel;
/*     */ import com.htsoft.oa.model.system.FileAttach;
/*     */ import org.apache.commons.lang.builder.EqualsBuilder;
/*     */ import org.apache.commons.lang.builder.HashCodeBuilder;
/*     */ import org.apache.commons.lang.builder.ToStringBuilder;
/*     */ 
/*     */ public class Seal extends BaseModel
/*     */ {
/*     */   protected Long sealId;
/*     */   protected String sealName;
/*     */   protected String sealPath;
/*     */   protected Long belongId;
/*     */   protected String belongName;
/*     */   protected FileAttach fileAttach;
/*     */ 
/*     */   public Seal()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Seal(Long in_sealId)
/*     */   {
/*  40 */     setSealId(in_sealId);
/*     */   }
/*     */ 
/*     */   public FileAttach getFileAttach()
/*     */   {
/*  45 */     return this.fileAttach;
/*     */   }
/*     */ 
/*     */   public void setFileAttach(FileAttach in_fileAttach) {
/*  49 */     this.fileAttach = in_fileAttach;
/*     */   }
/*     */ 
/*     */   public Long getSealId()
/*     */   {
/*  58 */     return this.sealId;
/*     */   }
/*     */ 
/*     */   public void setSealId(Long aValue)
/*     */   {
/*  65 */     this.sealId = aValue;
/*     */   }
/*     */ 
/*     */   public Long getFileId()
/*     */   {
/*  72 */     return getFileAttach() == null ? null : getFileAttach().getFileId();
/*     */   }
/*     */ 
/*     */   public void setFileId(Long aValue)
/*     */   {
/*  79 */     if (aValue == null) {
/*  80 */       this.fileAttach = null;
/*  81 */     } else if (this.fileAttach == null) {
/*  82 */       this.fileAttach = new FileAttach(aValue);
/*  83 */       this.fileAttach.setVersion(new Integer(0));
/*     */     } else {
/*  85 */       this.fileAttach.setFileId(aValue);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getSealName()
/*     */   {
/*  94 */     return this.sealName;
/*     */   }
/*     */ 
/*     */   public void setSealName(String aValue)
/*     */   {
/* 102 */     this.sealName = aValue;
/*     */   }
/*     */ 
/*     */   public String getSealPath()
/*     */   {
/* 110 */     return this.sealPath;
/*     */   }
/*     */ 
/*     */   public void setSealPath(String aValue)
/*     */   {
/* 118 */     this.sealPath = aValue;
/*     */   }
/*     */ 
/*     */   public Long getBelongId()
/*     */   {
/* 126 */     return this.belongId;
/*     */   }
/*     */ 
/*     */   public void setBelongId(Long aValue)
/*     */   {
/* 134 */     this.belongId = aValue;
/*     */   }
/*     */ 
/*     */   public String getBelongName()
/*     */   {
/* 142 */     return this.belongName;
/*     */   }
/*     */ 
/*     */   public void setBelongName(String aValue)
/*     */   {
/* 150 */     this.belongName = aValue;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 157 */     if (!(object instanceof Seal)) {
/* 158 */       return false;
/*     */     }
/* 160 */     Seal rhs = (Seal)object;
/* 161 */     return new EqualsBuilder()
/* 162 */       .append(this.sealId, rhs.sealId)
/* 163 */       .append(this.sealName, rhs.sealName)
/* 164 */       .append(this.sealPath, rhs.sealPath)
/* 165 */       .append(this.belongId, rhs.belongId)
/* 166 */       .append(this.belongName, rhs.belongName)
/* 167 */       .isEquals();
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 174 */     return new HashCodeBuilder(-82280557, -700257973)
/* 175 */       .append(this.sealId)
/* 176 */       .append(this.sealName)
/* 177 */       .append(this.sealPath)
/* 178 */       .append(this.belongId)
/* 179 */       .append(this.belongName)
/* 180 */       .toHashCode();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 187 */     return new ToStringBuilder(this)
/* 188 */       .append("sealId", this.sealId)
/* 189 */       .append("sealName", this.sealName)
/* 190 */       .append("sealPath", this.sealPath)
/* 191 */       .append("belongId", this.belongId)
/* 192 */       .append("belongName", this.belongName)
/* 193 */       .toString();
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.document.Seal
 * JD-Core Version:    0.6.0
 */