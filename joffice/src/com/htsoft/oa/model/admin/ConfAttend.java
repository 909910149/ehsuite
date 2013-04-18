/*     */ package com.htsoft.oa.model.admin;
/*     */ 
/*     */ import com.htsoft.core.model.BaseModel;
/*     */ import org.apache.commons.lang.builder.EqualsBuilder;
/*     */ import org.apache.commons.lang.builder.HashCodeBuilder;
/*     */ import org.apache.commons.lang.builder.ToStringBuilder;
/*     */ 
/*     */ public class ConfAttend extends BaseModel
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   protected Conference confId;
/*     */   protected Integer attendId;
/*     */   protected Integer userId;
/*     */   protected Short userType;
/*     */   protected String fullname;
/*     */ 
/*     */   public ConfAttend()
/*     */   {
/*     */   }
/*     */ 
/*     */   public ConfAttend(Integer in_attendId)
/*     */   {
/*  39 */     setAttendId(in_attendId);
/*     */   }
/*     */ 
/*     */   public Integer getAttendId()
/*     */   {
/*  49 */     return this.attendId;
/*     */   }
/*     */ 
/*     */   public void setAttendId(Integer aValue)
/*     */   {
/*  56 */     this.attendId = aValue;
/*     */   }
/*     */ 
/*     */   public Conference getConfId()
/*     */   {
/*  66 */     return this.confId;
/*     */   }
/*     */ 
/*     */   public void setConfId(Conference conference)
/*     */   {
/*  73 */     this.confId = conference;
/*     */   }
/*     */ 
/*     */   public Integer getUserId()
/*     */   {
/*  83 */     return this.userId;
/*     */   }
/*     */ 
/*     */   public void setUserId(Integer aValue)
/*     */   {
/*  90 */     this.userId = aValue;
/*     */   }
/*     */ 
/*     */   public Short getUserType()
/*     */   {
/* 100 */     return this.userType;
/*     */   }
/*     */ 
/*     */   public void setUserType(Short aValue)
/*     */   {
/* 107 */     this.userType = aValue;
/*     */   }
/*     */ 
/*     */   public String getFullname()
/*     */   {
/* 117 */     return this.fullname;
/*     */   }
/*     */ 
/*     */   public void setFullname(String aValue)
/*     */   {
/* 124 */     this.fullname = aValue;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 131 */     if (!(object instanceof ConfAttend)) {
/* 132 */       return false;
/*     */     }
/* 134 */     ConfAttend rhs = (ConfAttend)object;
/* 135 */     return new EqualsBuilder().append(this.attendId, rhs.attendId).append(
/* 136 */       this.confId, rhs.confId).append(this.userId, rhs.userId)
/* 137 */       .append(this.userType, rhs.userType).append(this.fullname, 
/* 138 */       rhs.fullname).isEquals();
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 145 */     return new HashCodeBuilder(-82280557, -700257973).append(this.attendId)
/* 146 */       .append(this.confId).append(this.userId).append(this.userType)
/* 147 */       .append(this.fullname).toHashCode();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 154 */     return new ToStringBuilder(this).append("attendId", this.attendId)
/* 155 */       .append("confId", this.confId).append("userId", this.userId)
/* 156 */       .append("userType", this.userType).append("fullname", 
/* 157 */       this.fullname).toString();
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.admin.ConfAttend
 * JD-Core Version:    0.6.0
 */