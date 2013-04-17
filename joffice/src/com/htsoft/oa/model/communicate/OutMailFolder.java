/*     */ package com.htsoft.oa.model.communicate;
/*     */ 
/*     */ import com.htsoft.core.model.BaseModel;
/*     */ import com.htsoft.oa.model.system.AppUser;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang.builder.EqualsBuilder;
/*     */ import org.apache.commons.lang.builder.HashCodeBuilder;
/*     */ import org.apache.commons.lang.builder.ToStringBuilder;
/*     */ 
/*     */ public class OutMailFolder extends BaseModel
/*     */ {
/*     */   protected Long folderId;
/*     */   protected String folderName;
/*     */   protected Long parentId;
/*     */   protected Integer depLevel;
/*     */   protected String path;
/*     */   protected Short folderType;
/*     */   protected AppUser appUser;
/*  28 */   protected Set outMails = new HashSet();
/*     */ 
/*     */   public OutMailFolder()
/*     */   {
/*     */   }
/*     */ 
/*     */   public OutMailFolder(Long in_folderId)
/*     */   {
/*  43 */     setFolderId(in_folderId);
/*     */   }
/*     */ 
/*     */   public AppUser getAppUser()
/*     */   {
/*  48 */     return this.appUser;
/*     */   }
/*     */ 
/*     */   public void setAppUser(AppUser in_appUser) {
/*  52 */     this.appUser = in_appUser;
/*     */   }
/*     */ 
/*     */   public Set getOutMails() {
/*  56 */     return this.outMails;
/*     */   }
/*     */ 
/*     */   public void setOutMails(Set in_outMails) {
/*  60 */     this.outMails = in_outMails;
/*     */   }
/*     */ 
/*     */   public Long getFolderId()
/*     */   {
/*  69 */     return this.folderId;
/*     */   }
/*     */ 
/*     */   public void setFolderId(Long aValue)
/*     */   {
/*  76 */     this.folderId = aValue;
/*     */   }
/*     */ 
/*     */   public Long getUserId()
/*     */   {
/*  83 */     return getAppUser() == null ? null : getAppUser().getUserId();
/*     */   }
/*     */ 
/*     */   public void setUserId(Long aValue)
/*     */   {
/*  90 */     if (aValue == null) {
/*  91 */       this.appUser = null;
/*  92 */     } else if (this.appUser == null) {
/*  93 */       this.appUser = new AppUser(aValue);
/*  94 */       this.appUser.setVersion(new Integer(0));
/*     */     } else {
/*  96 */       this.appUser.setUserId(aValue);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getFolderName()
/*     */   {
/* 105 */     return this.folderName;
/*     */   }
/*     */ 
/*     */   public void setFolderName(String aValue)
/*     */   {
/* 113 */     this.folderName = aValue;
/*     */   }
/*     */ 
/*     */   public Long getParentId()
/*     */   {
/* 121 */     return this.parentId;
/*     */   }
/*     */ 
/*     */   public void setParentId(Long aValue)
/*     */   {
/* 128 */     this.parentId = aValue;
/*     */   }
/*     */ 
/*     */   public Integer getDepLevel()
/*     */   {
/* 136 */     return this.depLevel;
/*     */   }
/*     */ 
/*     */   public void setDepLevel(Integer aValue)
/*     */   {
/* 144 */     this.depLevel = aValue;
/*     */   }
/*     */ 
/*     */   public String getPath()
/*     */   {
/* 152 */     return this.path;
/*     */   }
/*     */ 
/*     */   public void setPath(String aValue)
/*     */   {
/* 159 */     this.path = aValue;
/*     */   }
/*     */ 
/*     */   public Short getFolderType()
/*     */   {
/* 179 */     return this.folderType;
/*     */   }
/*     */ 
/*     */   public void setFolderType(Short aValue)
/*     */   {
/* 187 */     this.folderType = aValue;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 194 */     if (!(object instanceof OutMailFolder)) {
/* 195 */       return false;
/*     */     }
/* 197 */     OutMailFolder rhs = (OutMailFolder)object;
/* 198 */     return new EqualsBuilder()
/* 199 */       .append(this.folderId, rhs.folderId)
/* 200 */       .append(this.folderName, rhs.folderName)
/* 201 */       .append(this.parentId, rhs.parentId)
/* 202 */       .append(this.depLevel, rhs.depLevel)
/* 203 */       .append(this.path, rhs.path)
/* 205 */       .append(this.folderType, rhs.folderType)
/* 206 */       .isEquals();
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 213 */     return new HashCodeBuilder(-82280557, -700257973)
/* 214 */       .append(this.folderId)
/* 215 */       .append(this.folderName)
/* 216 */       .append(this.parentId)
/* 217 */       .append(this.depLevel)
/* 218 */       .append(this.path)
/* 220 */       .append(this.folderType)
/* 221 */       .toHashCode();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 228 */     return new ToStringBuilder(this)
/* 229 */       .append("folderId", this.folderId)
/* 230 */       .append("folderName", this.folderName)
/* 231 */       .append("parentId", this.parentId)
/* 232 */       .append("depLevel", this.depLevel)
/* 233 */       .append("path", this.path)
/* 235 */       .append("folderType", this.folderType)
/* 236 */       .toString();
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.communicate.OutMailFolder
 * JD-Core Version:    0.6.0
 */