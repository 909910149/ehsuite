/*     */ package com.htsoft.oa.model.info;
/*     */ 
/*     */ import com.google.gson.annotations.Expose;
/*     */ import com.htsoft.core.model.BaseModel;
/*     */ import java.util.Date;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang.builder.EqualsBuilder;
/*     */ import org.apache.commons.lang.builder.HashCodeBuilder;
/*     */ import org.apache.commons.lang.builder.ToStringBuilder;
/*     */ 
/*     */ public class Section extends BaseModel
/*     */ {
/*  24 */   public static final Short STATUS_ENABLE = Short.valueOf((short)1);
/*     */ 
/*  28 */   public static final Short STATUS_DISABLE = Short.valueOf((short)0);
/*     */ 
/*  32 */   public static final Integer COLUMN_ONE = Integer.valueOf(1);
/*     */ 
/*     */   @Expose
/*     */   protected Long sectionId;
/*     */ 
/*     */   @Expose
/*     */   protected String sectionName;
/*     */ 
/*     */   @Expose
/*     */   protected String sectionDesc;
/*     */ 
/*     */   @Expose
/*     */   protected Date createtime;
/*     */ 
/*     */   @Expose
/*     */   protected Short sectionType;
/*     */ 
/*     */   @Expose
/*     */   protected String username;
/*     */ 
/*     */   @Expose
/*     */   protected Long userId;
/*     */ 
/*     */   @Expose
/*     */   protected Integer colNumber;
/*     */ 
/*     */   @Expose
/*     */   protected Integer rowNumber;
/*     */ 
/*     */   @Expose
/*     */   protected Short status;
/*  54 */   protected Set newss = new HashSet();
/*     */ 
/*     */   public Section()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Section(Long in_sectionId)
/*     */   {
/*  69 */     setSectionId(in_sectionId);
/*     */   }
/*     */ 
/*     */   public Set getNewss()
/*     */   {
/*  74 */     return this.newss;
/*     */   }
/*     */ 
/*     */   public void setNewss(Set in_newss) {
/*  78 */     this.newss = in_newss;
/*     */   }
/*     */ 
/*     */   public Long getSectionId()
/*     */   {
/*  87 */     return this.sectionId;
/*     */   }
/*     */ 
/*     */   public void setSectionId(Long aValue)
/*     */   {
/*  94 */     this.sectionId = aValue;
/*     */   }
/*     */ 
/*     */   public String getSectionName()
/*     */   {
/* 102 */     return this.sectionName;
/*     */   }
/*     */ 
/*     */   public void setSectionName(String aValue)
/*     */   {
/* 110 */     this.sectionName = aValue;
/*     */   }
/*     */ 
/*     */   public String getSectionDesc()
/*     */   {
/* 118 */     return this.sectionDesc;
/*     */   }
/*     */ 
/*     */   public void setSectionDesc(String aValue)
/*     */   {
/* 125 */     this.sectionDesc = aValue;
/*     */   }
/*     */ 
/*     */   public Date getCreatetime()
/*     */   {
/* 133 */     return this.createtime;
/*     */   }
/*     */ 
/*     */   public void setCreatetime(Date aValue)
/*     */   {
/* 141 */     this.createtime = aValue;
/*     */   }
/*     */ 
/*     */   public Short getSectionType()
/*     */   {
/* 149 */     return this.sectionType;
/*     */   }
/*     */ 
/*     */   public void setSectionType(Short aValue)
/*     */   {
/* 157 */     this.sectionType = aValue;
/*     */   }
/*     */ 
/*     */   public String getUsername()
/*     */   {
/* 165 */     return this.username;
/*     */   }
/*     */ 
/*     */   public void setUsername(String aValue)
/*     */   {
/* 172 */     this.username = aValue;
/*     */   }
/*     */ 
/*     */   public Long getUserId()
/*     */   {
/* 180 */     return this.userId;
/*     */   }
/*     */ 
/*     */   public void setUserId(Long aValue)
/*     */   {
/* 187 */     this.userId = aValue;
/*     */   }
/*     */ 
/*     */   public Integer getColNumber()
/*     */   {
/* 195 */     return this.colNumber;
/*     */   }
/*     */ 
/*     */   public void setColNumber(Integer aValue)
/*     */   {
/* 202 */     this.colNumber = aValue;
/*     */   }
/*     */ 
/*     */   public Integer getRowNumber()
/*     */   {
/* 210 */     return this.rowNumber;
/*     */   }
/*     */ 
/*     */   public void setRowNumber(Integer aValue)
/*     */   {
/* 217 */     this.rowNumber = aValue;
/*     */   }
/*     */ 
/*     */   public Short getStatus()
/*     */   {
/* 225 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(Short aValue)
/*     */   {
/* 233 */     this.status = aValue;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 240 */     if (!(object instanceof Section)) {
/* 241 */       return false;
/*     */     }
/* 243 */     Section rhs = (Section)object;
/* 244 */     return new EqualsBuilder()
/* 245 */       .append(this.sectionId, rhs.sectionId)
/* 246 */       .append(this.sectionName, rhs.sectionName)
/* 247 */       .append(this.sectionDesc, rhs.sectionDesc)
/* 248 */       .append(this.createtime, rhs.createtime)
/* 249 */       .append(this.sectionType, rhs.sectionType)
/* 250 */       .append(this.username, rhs.username)
/* 251 */       .append(this.userId, rhs.userId)
/* 252 */       .append(this.colNumber, rhs.colNumber)
/* 253 */       .append(this.rowNumber, rhs.rowNumber)
/* 254 */       .append(this.status, rhs.status)
/* 255 */       .isEquals();
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 262 */     return new HashCodeBuilder(-82280557, -700257973)
/* 263 */       .append(this.sectionId)
/* 264 */       .append(this.sectionName)
/* 265 */       .append(this.sectionDesc)
/* 266 */       .append(this.createtime)
/* 267 */       .append(this.sectionType)
/* 268 */       .append(this.username)
/* 269 */       .append(this.userId)
/* 270 */       .append(this.colNumber)
/* 271 */       .append(this.rowNumber)
/* 272 */       .append(this.status)
/* 273 */       .toHashCode();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 280 */     return new ToStringBuilder(this)
/* 281 */       .append("sectionId", this.sectionId)
/* 282 */       .append("sectionName", this.sectionName)
/* 283 */       .append("sectionDesc", this.sectionDesc)
/* 284 */       .append("createtime", this.createtime)
/* 285 */       .append("sectionType", this.sectionType)
/* 286 */       .append("username", this.username)
/* 287 */       .append("userId", this.userId)
/* 288 */       .append("colNumber", this.colNumber)
/* 289 */       .append("rowNumber", this.rowNumber)
/* 290 */       .append("status", this.status)
/* 291 */       .toString();
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.info.Section
 * JD-Core Version:    0.6.0
 */