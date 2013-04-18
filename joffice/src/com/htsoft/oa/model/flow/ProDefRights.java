/*     */ package com.htsoft.oa.model.flow;
/*     */ 
/*     */ import com.htsoft.core.model.BaseModel;
/*     */ import com.htsoft.oa.model.system.GlobalType;
/*     */ import org.apache.commons.lang.builder.EqualsBuilder;
/*     */ import org.apache.commons.lang.builder.HashCodeBuilder;
/*     */ import org.apache.commons.lang.builder.ToStringBuilder;
/*     */ 
/*     */ public class ProDefRights extends BaseModel
/*     */ {
/*     */   protected Long rightsId;
/*     */   protected String roleNames;
/*     */   protected String depNames;
/*     */   protected String userNames;
/*     */   protected String userIds;
/*     */   protected String roleIds;
/*     */   protected String depIds;
/*     */   protected ProDefinition proDefinition;
/*     */   protected GlobalType globalType;
/*     */ 
/*     */   public ProDefRights()
/*     */   {
/*     */   }
/*     */ 
/*     */   public ProDefRights(Long in_rightsId)
/*     */   {
/*  43 */     setRightsId(in_rightsId);
/*     */   }
/*     */ 
/*     */   public ProDefinition getProDefinition()
/*     */   {
/*  48 */     return this.proDefinition;
/*     */   }
/*     */ 
/*     */   public void setProDefinition(ProDefinition in_proDefinition) {
/*  52 */     this.proDefinition = in_proDefinition;
/*     */   }
/*     */ 
/*     */   public GlobalType getGlobalType() {
/*  56 */     return this.globalType;
/*     */   }
/*     */ 
/*     */   public void setGlobalType(GlobalType in_globalType) {
/*  60 */     this.globalType = in_globalType;
/*     */   }
/*     */ 
/*     */   public Long getRightsId()
/*     */   {
/*  69 */     return this.rightsId;
/*     */   }
/*     */ 
/*     */   public void setRightsId(Long aValue)
/*     */   {
/*  76 */     this.rightsId = aValue;
/*     */   }
/*     */ 
/*     */   public Long getProTypeId()
/*     */   {
/*  83 */     return getGlobalType() == null ? null : getGlobalType().getProTypeId();
/*     */   }
/*     */ 
/*     */   public void setProTypeId(Long aValue)
/*     */   {
/*  90 */     if (aValue == null) {
/*  91 */       this.globalType = null;
/*  92 */     } else if (this.globalType == null) {
/*  93 */       this.globalType = new GlobalType(aValue);
/*  94 */       this.globalType.setVersion(new Integer(0));
/*     */     } else {
/*  96 */       this.globalType.setProTypeId(aValue);
/*     */     }
/*     */   }
/*     */ 
/*     */   public Long getDefId()
/*     */   {
/* 104 */     return getProDefinition() == null ? null : getProDefinition().getDefId();
/*     */   }
/*     */ 
/*     */   public void setDefId(Long aValue)
/*     */   {
/* 111 */     if (aValue == null) {
/* 112 */       this.proDefinition = null;
/* 113 */     } else if (this.proDefinition == null) {
/* 114 */       this.proDefinition = new ProDefinition(aValue);
/* 115 */       this.proDefinition.setVersion(new Integer(0));
/*     */     } else {
/* 117 */       this.proDefinition.setDefId(aValue);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getRoleNames()
/*     */   {
/* 126 */     return this.roleNames;
/*     */   }
/*     */ 
/*     */   public void setRoleNames(String aValue)
/*     */   {
/* 133 */     this.roleNames = aValue;
/*     */   }
/*     */ 
/*     */   public String getDepNames()
/*     */   {
/* 141 */     return this.depNames;
/*     */   }
/*     */ 
/*     */   public void setDepNames(String aValue)
/*     */   {
/* 148 */     this.depNames = aValue;
/*     */   }
/*     */ 
/*     */   public String getUserNames()
/*     */   {
/* 156 */     return this.userNames;
/*     */   }
/*     */ 
/*     */   public void setUserNames(String aValue)
/*     */   {
/* 163 */     this.userNames = aValue;
/*     */   }
/*     */ 
/*     */   public String getUserIds()
/*     */   {
/* 173 */     return this.userIds;
/*     */   }
/*     */ 
/*     */   public void setUserIds(String aValue)
/*     */   {
/* 180 */     this.userIds = aValue;
/*     */   }
/*     */ 
/*     */   public String getRoleIds()
/*     */   {
/* 190 */     return this.roleIds;
/*     */   }
/*     */ 
/*     */   public void setRoleIds(String aValue)
/*     */   {
/* 197 */     this.roleIds = aValue;
/*     */   }
/*     */ 
/*     */   public String getDepIds()
/*     */   {
/* 207 */     return this.depIds;
/*     */   }
/*     */ 
/*     */   public void setDepIds(String aValue)
/*     */   {
/* 214 */     this.depIds = aValue;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 221 */     if (!(object instanceof ProDefRights)) {
/* 222 */       return false;
/*     */     }
/* 224 */     ProDefRights rhs = (ProDefRights)object;
/* 225 */     return new EqualsBuilder()
/* 226 */       .append(this.rightsId, rhs.rightsId)
/* 227 */       .append(this.roleNames, rhs.roleNames)
/* 228 */       .append(this.depNames, rhs.depNames)
/* 229 */       .append(this.userNames, rhs.userNames)
/* 230 */       .append(this.userIds, rhs.userIds)
/* 231 */       .append(this.roleIds, rhs.roleIds)
/* 232 */       .append(this.depIds, rhs.depIds)
/* 233 */       .isEquals();
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 240 */     return new HashCodeBuilder(-82280557, -700257973)
/* 241 */       .append(this.rightsId)
/* 242 */       .append(this.roleNames)
/* 243 */       .append(this.depNames)
/* 244 */       .append(this.userNames)
/* 245 */       .append(this.userIds)
/* 246 */       .append(this.roleIds)
/* 247 */       .append(this.depIds)
/* 248 */       .toHashCode();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 255 */     return new ToStringBuilder(this)
/* 256 */       .append("rightsId", this.rightsId)
/* 257 */       .append("roleNames", this.roleNames)
/* 258 */       .append("depNames", this.depNames)
/* 259 */       .append("userNames", this.userNames)
/* 260 */       .append("userIds", this.userIds)
/* 261 */       .append("roleIds", this.roleIds)
/* 262 */       .append("depIds", this.depIds)
/* 263 */       .toString();
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.flow.ProDefRights
 * JD-Core Version:    0.6.0
 */