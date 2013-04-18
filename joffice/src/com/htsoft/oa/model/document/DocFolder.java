/*     */ package com.htsoft.oa.model.document;
/*     */ 
/*     */ import com.google.gson.annotations.Expose;
/*     */ import com.htsoft.core.model.BaseModel;
/*     */ import com.htsoft.oa.model.system.AppUser;
/*     */ import org.apache.commons.lang.builder.EqualsBuilder;
/*     */ import org.apache.commons.lang.builder.HashCodeBuilder;
/*     */ import org.apache.commons.lang.builder.ToStringBuilder;
/*     */ 
/*     */ public class DocFolder extends BaseModel
/*     */ {
/*  22 */   public static final Short IS_SHARED = Short.valueOf((short)1);
/*  23 */   public static final Short IS_NOT_SHARED = Short.valueOf((short)0);
/*     */ 
/*     */   @Expose
/*     */   protected Long folderId;
/*     */ 
/*     */   @Expose
/*     */   protected String folderName;
/*     */ 
/*     */   @Expose
/*     */   protected Long parentId;
/*     */ 
/*     */   @Expose
/*     */   protected String path;
/*     */ 
/*     */   @Expose
/*     */   protected Short isShared;
/*     */ 
/*     */   @Expose
/*     */   protected String descp;
/*     */ 
/*     */   @Expose
/*     */   protected AppUser appUser;
/*     */ 
/*     */   public DocFolder() {  } 
/*  52 */   public DocFolder(Long in_folderId) { setFolderId(in_folderId);
/*     */   }
/*     */ 
/*     */   public AppUser getAppUser()
/*     */   {
/*  57 */     return this.appUser;
/*     */   }
/*     */ 
/*     */   public void setAppUser(AppUser in_appUser) {
/*  61 */     this.appUser = in_appUser;
/*     */   }
/*     */ 
/*     */   public Long getFolderId()
/*     */   {
/*  70 */     return this.folderId;
/*     */   }
/*     */ 
/*     */   public void setFolderId(Long aValue)
/*     */   {
/*  77 */     this.folderId = aValue;
/*     */   }
/*     */ 
/*     */   public Long getUserId()
/*     */   {
/*  84 */     return getAppUser() == null ? null : getAppUser().getUserId();
/*     */   }
/*     */ 
/*     */   public void setUserId(Long aValue)
/*     */   {
/*  91 */     if (aValue == null) {
/*  92 */       this.appUser = null;
/*  93 */     } else if (this.appUser == null) {
/*  94 */       this.appUser = new AppUser(aValue);
/*  95 */       this.appUser.setVersion(new Integer(0));
/*     */     } else {
/*  97 */       this.appUser.setUserId(aValue);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getFolderName()
/*     */   {
/* 117 */     return this.folderName;
/*     */   }
/*     */ 
/*     */   public void setFolderName(String aValue)
/*     */   {
/* 125 */     this.folderName = aValue;
/*     */   }
/*     */ 
/*     */   public Long getParentId()
/*     */   {
/* 133 */     return this.parentId;
/*     */   }
/*     */ 
/*     */   public void setParentId(Long aValue)
/*     */   {
/* 140 */     this.parentId = aValue;
/*     */   }
/*     */ 
/*     */   public String getPath()
/*     */   {
/* 148 */     return this.path;
/*     */   }
/*     */ 
/*     */   public void setPath(String aValue)
/*     */   {
/* 155 */     this.path = aValue;
/*     */   }
/*     */ 
/*     */   public Short getIsShared()
/*     */   {
/* 163 */     return this.isShared;
/*     */   }
/*     */ 
/*     */   public void setIsShared(Short aValue)
/*     */   {
/* 171 */     this.isShared = aValue;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 178 */     if (!(object instanceof DocFolder)) {
/* 179 */       return false;
/*     */     }
/* 181 */     DocFolder rhs = (DocFolder)object;
/* 182 */     return new EqualsBuilder()
/* 183 */       .append(this.folderId, rhs.folderId)
/* 184 */       .append(this.folderName, rhs.folderName)
/* 185 */       .append(this.parentId, rhs.parentId)
/* 186 */       .append(this.path, rhs.path)
/* 187 */       .append(this.isShared, rhs.isShared)
/* 188 */       .isEquals();
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 195 */     return new HashCodeBuilder(-82280557, -700257973)
/* 196 */       .append(this.folderId)
/* 197 */       .append(this.folderName)
/* 198 */       .append(this.parentId)
/* 199 */       .append(this.path)
/* 200 */       .append(this.isShared)
/* 201 */       .toHashCode();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 208 */     return new ToStringBuilder(this)
/* 209 */       .append("folderId", this.folderId)
/* 210 */       .append("folderName", this.folderName)
/* 211 */       .append("parentId", this.parentId)
/* 212 */       .append("path", this.path)
/* 213 */       .append("isShared", this.isShared)
/* 214 */       .toString();
/*     */   }
/*     */ 
/*     */   public String getFirstKeyColumnName()
/*     */   {
/* 221 */     return "folderId";
/*     */   }
/*     */ 
/*     */   public Long getId()
/*     */   {
/* 228 */     return this.folderId;
/*     */   }
/*     */ 
/*     */   public String getDescp() {
/* 232 */     return this.descp;
/*     */   }
/*     */ 
/*     */   public void setDescp(String descp) {
/* 236 */     this.descp = descp;
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.document.DocFolder
 * JD-Core Version:    0.6.0
 */