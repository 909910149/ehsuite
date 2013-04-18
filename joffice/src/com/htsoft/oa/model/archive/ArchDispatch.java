/*     */ package com.htsoft.oa.model.archive;
/*     */ 
/*     */ import com.htsoft.core.model.BaseModel;
/*     */ import java.util.Date;
/*     */ import org.apache.commons.lang.builder.EqualsBuilder;
/*     */ import org.apache.commons.lang.builder.HashCodeBuilder;
/*     */ import org.apache.commons.lang.builder.ToStringBuilder;
/*     */ 
/*     */ public class ArchDispatch extends BaseModel
/*     */ {
/*  19 */   public static Short HAVE_READ = Short.valueOf((short)1);
/*  20 */   public static Short NOT_READ = Short.valueOf((short)0);
/*  21 */   public static Short IS_UNDERTAKE = Short.valueOf((short)1);
/*  22 */   public static Short IS_READER = Short.valueOf((short)0);
/*  23 */   public static Short IS_DISPATCH = Short.valueOf((short)2);
/*  24 */   public static Short IS_LEADER = Short.valueOf((short)0);
/*  25 */   public static Short NOT_LEADER = Short.valueOf((short)1);
/*  26 */   public static Short IS_OVER = Short.valueOf((short)2);
/*     */   protected Long dispatchId;
/*     */   protected Date dispatchTime;
/*     */   protected Long userId;
/*     */   protected String fullname;
/*     */   protected Short isRead;
/*     */   protected String subject;
/*     */   protected String readFeedback;
/*     */   protected Short archUserType;
/*     */   protected Long disRoleId;
/*     */   protected String disRoleName;
/*     */   protected Archives archives;
/*     */ 
/*     */   public ArchDispatch()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Long getDisRoleId()
/*     */   {
/*  51 */     return this.disRoleId;
/*     */   }
/*     */ 
/*     */   public void setDisRoleId(Long disRoleId)
/*     */   {
/*  58 */     this.disRoleId = disRoleId;
/*     */   }
/*     */ 
/*     */   public String getDisRoleName()
/*     */   {
/*  65 */     return this.disRoleName;
/*     */   }
/*     */ 
/*     */   public void setDisRoleName(String disRoleName)
/*     */   {
/*  72 */     this.disRoleName = disRoleName;
/*     */   }
/*     */ 
/*     */   public ArchDispatch(Long in_dispatchId)
/*     */   {
/*  84 */     setDispatchId(in_dispatchId);
/*     */   }
/*     */ 
/*     */   public Archives getArchives()
/*     */   {
/*  89 */     return this.archives;
/*     */   }
/*     */ 
/*     */   public void setArchives(Archives in_archives) {
/*  93 */     this.archives = in_archives;
/*     */   }
/*     */ 
/*     */   public Long getDispatchId()
/*     */   {
/* 102 */     return this.dispatchId;
/*     */   }
/*     */ 
/*     */   public void setDispatchId(Long aValue)
/*     */   {
/* 109 */     this.dispatchId = aValue;
/*     */   }
/*     */ 
/*     */   public Long getArchivesId()
/*     */   {
/* 116 */     return getArchives() == null ? null : getArchives().getArchivesId();
/*     */   }
/*     */ 
/*     */   public void setArchivesId(Long aValue)
/*     */   {
/* 123 */     if (aValue == null) {
/* 124 */       this.archives = null;
/* 125 */     } else if (this.archives == null) {
/* 126 */       this.archives = new Archives(aValue);
/* 127 */       this.archives.setVersion(new Integer(0));
/*     */     } else {
/* 129 */       this.archives.setArchivesId(aValue);
/*     */     }
/*     */   }
/*     */ 
/*     */   public Date getDispatchTime()
/*     */   {
/* 138 */     return this.dispatchTime;
/*     */   }
/*     */ 
/*     */   public void setDispatchTime(Date aValue)
/*     */   {
/* 146 */     this.dispatchTime = aValue;
/*     */   }
/*     */ 
/*     */   public Long getUserId()
/*     */   {
/* 154 */     return this.userId;
/*     */   }
/*     */ 
/*     */   public void setUserId(Long aValue)
/*     */   {
/* 162 */     this.userId = aValue;
/*     */   }
/*     */ 
/*     */   public String getFullname()
/*     */   {
/* 170 */     return this.fullname;
/*     */   }
/*     */ 
/*     */   public void setFullname(String aValue)
/*     */   {
/* 177 */     this.fullname = aValue;
/*     */   }
/*     */ 
/*     */   public Short getIsRead()
/*     */   {
/* 185 */     return this.isRead;
/*     */   }
/*     */ 
/*     */   public void setIsRead(Short aValue)
/*     */   {
/* 192 */     this.isRead = aValue;
/*     */   }
/*     */ 
/*     */   public String getSubject()
/*     */   {
/* 200 */     return this.subject;
/*     */   }
/*     */ 
/*     */   public void setSubject(String aValue)
/*     */   {
/* 207 */     this.subject = aValue;
/*     */   }
/*     */ 
/*     */   public String getReadFeedback()
/*     */   {
/* 215 */     return this.readFeedback;
/*     */   }
/*     */ 
/*     */   public void setReadFeedback(String aValue)
/*     */   {
/* 222 */     this.readFeedback = aValue;
/*     */   }
/*     */ 
/*     */   public Short getArchUserType()
/*     */   {
/* 230 */     return this.archUserType;
/*     */   }
/*     */ 
/*     */   public void setArchUserType(Short aValue)
/*     */   {
/* 237 */     this.archUserType = aValue;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 244 */     if (!(object instanceof ArchDispatch)) {
/* 245 */       return false;
/*     */     }
/* 247 */     ArchDispatch rhs = (ArchDispatch)object;
/* 248 */     return new EqualsBuilder()
/* 249 */       .append(this.dispatchId, rhs.dispatchId)
/* 250 */       .append(this.dispatchTime, rhs.dispatchTime)
/* 251 */       .append(this.userId, rhs.userId)
/* 252 */       .append(this.fullname, rhs.fullname)
/* 253 */       .append(this.isRead, rhs.isRead)
/* 254 */       .append(this.subject, rhs.subject)
/* 255 */       .append(this.readFeedback, rhs.readFeedback)
/* 256 */       .append(this.archUserType, rhs.archUserType)
/* 257 */       .append(this.disRoleId, rhs.disRoleId)
/* 258 */       .append(this.disRoleName, rhs.disRoleName)
/* 259 */       .isEquals();
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 266 */     return new HashCodeBuilder(-82280557, -700257973)
/* 267 */       .append(this.dispatchId)
/* 268 */       .append(this.dispatchTime)
/* 269 */       .append(this.userId)
/* 270 */       .append(this.fullname)
/* 271 */       .append(this.isRead)
/* 272 */       .append(this.subject)
/* 273 */       .append(this.readFeedback)
/* 274 */       .append(this.archUserType)
/* 275 */       .append(this.disRoleId)
/* 276 */       .append(this.disRoleName)
/* 277 */       .toHashCode();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 284 */     return new ToStringBuilder(this)
/* 285 */       .append("dispatchId", this.dispatchId)
/* 286 */       .append("dispatchTime", this.dispatchTime)
/* 287 */       .append("userId", this.userId)
/* 288 */       .append("fullname", this.fullname)
/* 289 */       .append("isRead", this.isRead)
/* 290 */       .append("subject", this.subject)
/* 291 */       .append("readFeedback", this.readFeedback)
/* 292 */       .append("isUndertake", this.archUserType)
/* 293 */       .append("disRoleId", this.disRoleId)
/* 294 */       .append("disRoleName", this.disRoleName)
/* 295 */       .toString();
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.archive.ArchDispatch
 * JD-Core Version:    0.6.0
 */