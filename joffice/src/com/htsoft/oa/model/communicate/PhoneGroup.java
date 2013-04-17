/*     */ package com.htsoft.oa.model.communicate;
/*     */ 
/*     */ import com.google.gson.annotations.Expose;
/*     */ import com.htsoft.core.model.BaseModel;
/*     */ import com.htsoft.oa.model.system.AppUser;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang.builder.EqualsBuilder;
/*     */ import org.apache.commons.lang.builder.HashCodeBuilder;
/*     */ import org.apache.commons.lang.builder.ToStringBuilder;
/*     */ 
/*     */ public class PhoneGroup extends BaseModel
/*     */ {
/*  25 */   public static Short IS_PUBLIC = Short.valueOf((short)1);
/*     */ 
/*     */   @Expose
/*     */   protected Long groupId;
/*     */ 
/*     */   @Expose
/*     */   protected String groupName;
/*     */ 
/*     */   @Expose
/*     */   protected Short isShared;
/*     */ 
/*     */   @Expose
/*     */   protected Integer sn;
/*     */ 
/*     */   @Expose
/*     */   protected Short isPublic;
/*     */   protected AppUser appUser;
/*  37 */   protected Set<PhoneGroup> phoneBooks = new HashSet();
/*     */ 
/*     */   public Set<PhoneGroup> getPhoneBooks() {
/*  40 */     return this.phoneBooks;
/*     */   }
/*     */ 
/*     */   public void setPhoneBooks(Set<PhoneGroup> phoneBooks)
/*     */   {
/*  45 */     this.phoneBooks = phoneBooks;
/*     */   }
/*     */ 
/*     */   public PhoneGroup()
/*     */   {
/*     */   }
/*     */ 
/*     */   public PhoneGroup(Long in_groupId)
/*     */   {
/*  61 */     setGroupId(in_groupId);
/*     */   }
/*     */ 
/*     */   public AppUser getAppUser()
/*     */   {
/*  66 */     return this.appUser;
/*     */   }
/*     */ 
/*     */   public void setAppUser(AppUser in_appUser) {
/*  70 */     this.appUser = in_appUser;
/*     */   }
/*     */ 
/*     */   public Long getGroupId()
/*     */   {
/*  80 */     return this.groupId;
/*     */   }
/*     */ 
/*     */   public void setGroupId(Long aValue)
/*     */   {
/*  87 */     this.groupId = aValue;
/*     */   }
/*     */ 
/*     */   public String getGroupName()
/*     */   {
/*  95 */     return this.groupName;
/*     */   }
/*     */ 
/*     */   public void setGroupName(String aValue)
/*     */   {
/* 103 */     this.groupName = aValue;
/*     */   }
/*     */ 
/*     */   public Short getIsShared()
/*     */   {
/* 112 */     return this.isShared;
/*     */   }
/*     */ 
/*     */   public void setIsShared(Short aValue)
/*     */   {
/* 120 */     this.isShared = aValue;
/*     */   }
/*     */ 
/*     */   public Integer getSn()
/*     */   {
/* 128 */     return this.sn;
/*     */   }
/*     */ 
/*     */   public void setSn(Integer aValue)
/*     */   {
/* 136 */     this.sn = aValue;
/*     */   }
/*     */ 
/*     */   public Long getUserId()
/*     */   {
/* 143 */     return getAppUser() == null ? null : getAppUser().getUserId();
/*     */   }
/*     */ 
/*     */   public void setUserId(Long aValue)
/*     */   {
/* 150 */     if (aValue == null) {
/* 151 */       this.appUser = null;
/* 152 */     } else if (this.appUser == null) {
/* 153 */       this.appUser = new AppUser(aValue);
/* 154 */       this.appUser.setVersion(new Integer(0));
/*     */     } else {
/* 156 */       this.appUser.setUserId(aValue);
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 164 */     if (!(object instanceof PhoneGroup)) {
/* 165 */       return false;
/*     */     }
/* 167 */     PhoneGroup rhs = (PhoneGroup)object;
/* 168 */     return new EqualsBuilder()
/* 169 */       .append(this.groupId, rhs.groupId)
/* 170 */       .append(this.groupName, rhs.groupName)
/* 171 */       .append(this.isShared, rhs.isShared)
/* 172 */       .append(this.sn, rhs.sn)
/* 173 */       .isEquals();
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 180 */     return new HashCodeBuilder(-82280557, -700257973)
/* 181 */       .append(this.groupId)
/* 182 */       .append(this.groupName)
/* 183 */       .append(this.isShared)
/* 184 */       .append(this.sn)
/* 185 */       .toHashCode();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 192 */     return new ToStringBuilder(this)
/* 193 */       .append("groupId", this.groupId)
/* 194 */       .append("groupName", this.groupName)
/* 195 */       .append("isShared", this.isShared)
/* 196 */       .append("sn", this.sn)
/* 197 */       .toString();
/*     */   }
/*     */ 
/*     */   public String getFirstKeyColumnName()
/*     */   {
/* 204 */     return "groupId";
/*     */   }
/*     */ 
/*     */   public Long getId()
/*     */   {
/* 211 */     return this.groupId;
/*     */   }
/*     */ 
/*     */   public Short getIsPublic() {
/* 215 */     return this.isPublic;
/*     */   }
/*     */ 
/*     */   public void setIsPublic(Short isPublic) {
/* 219 */     this.isPublic = isPublic;
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.communicate.PhoneGroup
 * JD-Core Version:    0.6.0
 */