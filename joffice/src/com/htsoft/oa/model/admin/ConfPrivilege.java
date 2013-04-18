/*     */ package com.htsoft.oa.model.admin;
/*     */ 
/*     */ import com.htsoft.core.model.BaseModel;
/*     */ import org.apache.commons.lang.builder.EqualsBuilder;
/*     */ import org.apache.commons.lang.builder.HashCodeBuilder;
/*     */ import org.apache.commons.lang.builder.ToStringBuilder;
/*     */ 
/*     */ public class ConfPrivilege extends BaseModel
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   protected Integer privilegeId;
/*     */   protected Long userId;
/*     */   protected Long confId;
/*     */   protected String fullname;
/*     */   protected Short rights;
/*     */ 
/*     */   public ConfPrivilege()
/*     */   {
/*     */   }
/*     */ 
/*     */   public ConfPrivilege(Integer in_privilegeId)
/*     */   {
/*  38 */     setPrivilegeId(in_privilegeId);
/*     */   }
/*     */ 
/*     */   public Integer getPrivilegeId()
/*     */   {
/*  48 */     return this.privilegeId;
/*     */   }
/*     */ 
/*     */   public void setPrivilegeId(Integer aValue)
/*     */   {
/*  55 */     this.privilegeId = aValue;
/*     */   }
/*     */ 
/*     */   public Long getConfId()
/*     */   {
/*  65 */     return this.confId;
/*     */   }
/*     */ 
/*     */   public void setConfId(Long confId)
/*     */   {
/*  72 */     this.confId = confId;
/*     */   }
/*     */ 
/*     */   public Long getUserId()
/*     */   {
/*  82 */     return this.userId;
/*     */   }
/*     */ 
/*     */   public void setUserId(Long aValue)
/*     */   {
/*  91 */     this.userId = aValue;
/*     */   }
/*     */ 
/*     */   public String getFullname()
/*     */   {
/* 101 */     return this.fullname;
/*     */   }
/*     */ 
/*     */   public void setFullname(String aValue)
/*     */   {
/* 110 */     this.fullname = aValue;
/*     */   }
/*     */ 
/*     */   public Short getRights()
/*     */   {
/* 120 */     return this.rights;
/*     */   }
/*     */ 
/*     */   public void setRights(Short aValue)
/*     */   {
/* 129 */     this.rights = aValue;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 136 */     if (!(object instanceof ConfPrivilege)) {
/* 137 */       return false;
/*     */     }
/* 139 */     ConfPrivilege rhs = (ConfPrivilege)object;
/* 140 */     return new EqualsBuilder().append(this.privilegeId, rhs.privilegeId)
/* 141 */       .append(this.confId, rhs.confId)
/* 142 */       .append(this.userId, rhs.userId).append(this.fullname, 
/* 143 */       rhs.fullname).append(this.rights, rhs.rights)
/* 144 */       .isEquals();
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 151 */     return new HashCodeBuilder(-82280557, -700257973).append(
/* 152 */       this.privilegeId).append(this.confId).append(this.userId)
/* 153 */       .append(this.fullname).append(this.rights).toHashCode();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 160 */     return new ToStringBuilder(this)
/* 161 */       .append("privilegeId", this.privilegeId).append("confId", 
/* 162 */       this.confId).append("userId", this.userId).append(
/* 163 */       "fullname", this.fullname)
/* 164 */       .append("rights", this.rights).toString();
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.admin.ConfPrivilege
 * JD-Core Version:    0.6.0
 */