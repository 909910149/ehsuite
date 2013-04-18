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
/*     */ public class News extends BaseModel
/*     */ {
/*     */ 
/*     */   @Expose
/*     */   protected Long newsId;
/*     */ 
/*     */   @Expose
/*     */   protected String subjectIcon;
/*     */ 
/*     */   @Expose
/*     */   protected String subject;
/*     */ 
/*     */   @Expose
/*     */   protected String author;
/*     */ 
/*     */   @Expose
/*     */   protected Date createtime;
/*     */ 
/*     */   @Expose
/*     */   protected Date expTime;
/*     */ 
/*     */   @Expose
/*     */   protected Integer replyCounts;
/*     */ 
/*     */   @Expose
/*     */   protected Integer viewCounts;
/*     */ 
/*     */   @Expose
/*     */   protected String issuer;
/*     */ 
/*     */   @Expose
/*     */   protected String content;
/*     */ 
/*     */   @Expose
/*     */   protected Date updateTime;
/*     */ 
/*     */   @Expose
/*     */   protected Short status;
/*     */ 
/*     */   @Expose
/*     */   protected Short isDeskImage;
/*     */ 
/*     */   @Expose
/*     */   protected Short isNotice;
/*     */ 
/*     */   @Expose
/*     */   protected Integer sn;
/*     */ 
/*     */   @Expose
/*     */   protected Section section;
/*     */ 
/*     */   @Expose
/*  54 */   protected Set newsComments = new HashSet();
/*     */ 
/*     */   public News()
/*     */   {
/*     */   }
/*     */ 
/*     */   public News(Long in_newsId)
/*     */   {
/*  69 */     setNewsId(in_newsId);
/*     */   }
/*     */ 
/*     */   public Section getSection()
/*     */   {
/*  74 */     return this.section;
/*     */   }
/*     */ 
/*     */   public void setSection(Section in_section) {
/*  78 */     this.section = in_section;
/*     */   }
/*     */ 
/*     */   public Set getNewsComments() {
/*  82 */     return this.newsComments;
/*     */   }
/*     */ 
/*     */   public void setNewsComments(Set in_newsComments) {
/*  86 */     this.newsComments = in_newsComments;
/*     */   }
/*     */ 
/*     */   public Long getNewsId()
/*     */   {
/*  95 */     return this.newsId;
/*     */   }
/*     */ 
/*     */   public void setNewsId(Long aValue)
/*     */   {
/* 102 */     this.newsId = aValue;
/*     */   }
/*     */ 
/*     */   public Long getSectionId()
/*     */   {
/* 109 */     return getSection() == null ? null : getSection().getSectionId();
/*     */   }
/*     */ 
/*     */   public void setSectionId(Long aValue)
/*     */   {
/* 116 */     if (aValue == null) {
/* 117 */       this.section = null;
/* 118 */     } else if (this.section == null) {
/* 119 */       this.section = new Section(aValue);
/* 120 */       this.section.setVersion(new Integer(0));
/*     */     } else {
/* 122 */       this.section.setSectionId(aValue);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getSubjectIcon()
/*     */   {
/* 131 */     return this.subjectIcon;
/*     */   }
/*     */ 
/*     */   public void setSubjectIcon(String aValue)
/*     */   {
/* 138 */     this.subjectIcon = aValue;
/*     */   }
/*     */ 
/*     */   public String getSubject()
/*     */   {
/* 146 */     return this.subject;
/*     */   }
/*     */ 
/*     */   public void setSubject(String aValue)
/*     */   {
/* 154 */     this.subject = aValue;
/*     */   }
/*     */ 
/*     */   public String getAuthor()
/*     */   {
/* 162 */     return this.author;
/*     */   }
/*     */ 
/*     */   public void setAuthor(String aValue)
/*     */   {
/* 170 */     this.author = aValue;
/*     */   }
/*     */ 
/*     */   public Date getCreatetime()
/*     */   {
/* 178 */     return this.createtime;
/*     */   }
/*     */ 
/*     */   public void setCreatetime(Date aValue)
/*     */   {
/* 186 */     this.createtime = aValue;
/*     */   }
/*     */ 
/*     */   public Date getExpTime()
/*     */   {
/* 194 */     return this.expTime;
/*     */   }
/*     */ 
/*     */   public void setExpTime(Date aValue)
/*     */   {
/* 201 */     this.expTime = aValue;
/*     */   }
/*     */ 
/*     */   public Integer getReplyCounts()
/*     */   {
/* 209 */     return this.replyCounts;
/*     */   }
/*     */ 
/*     */   public void setReplyCounts(Integer aValue)
/*     */   {
/* 216 */     this.replyCounts = aValue;
/*     */   }
/*     */ 
/*     */   public Integer getViewCounts()
/*     */   {
/* 224 */     return this.viewCounts;
/*     */   }
/*     */ 
/*     */   public void setViewCounts(Integer aValue)
/*     */   {
/* 231 */     this.viewCounts = aValue;
/*     */   }
/*     */ 
/*     */   public String getIssuer()
/*     */   {
/* 239 */     return this.issuer;
/*     */   }
/*     */ 
/*     */   public void setIssuer(String aValue)
/*     */   {
/* 247 */     this.issuer = aValue;
/*     */   }
/*     */ 
/*     */   public String getContent()
/*     */   {
/* 255 */     return this.content;
/*     */   }
/*     */ 
/*     */   public void setContent(String aValue)
/*     */   {
/* 263 */     this.content = aValue;
/*     */   }
/*     */ 
/*     */   public Date getUpdateTime()
/*     */   {
/* 271 */     return this.updateTime;
/*     */   }
/*     */ 
/*     */   public void setUpdateTime(Date aValue)
/*     */   {
/* 278 */     this.updateTime = aValue;
/*     */   }
/*     */ 
/*     */   public Short getStatus()
/*     */   {
/* 288 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(Short aValue)
/*     */   {
/* 296 */     this.status = aValue;
/*     */   }
/*     */ 
/*     */   public Short getIsDeskImage()
/*     */   {
/* 304 */     return this.isDeskImage;
/*     */   }
/*     */ 
/*     */   public void setIsDeskImage(Short aValue)
/*     */   {
/* 311 */     this.isDeskImage = aValue;
/*     */   }
/*     */ 
/*     */   public Short getIsNotice()
/*     */   {
/* 319 */     return this.isNotice;
/*     */   }
/*     */ 
/*     */   public void setIsNotice(Short aValue)
/*     */   {
/* 326 */     this.isNotice = aValue;
/*     */   }
/*     */ 
/*     */   public Integer getSn()
/*     */   {
/* 334 */     return this.sn;
/*     */   }
/*     */ 
/*     */   public void setSn(Integer aValue)
/*     */   {
/* 341 */     this.sn = aValue;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 348 */     if (!(object instanceof News)) {
/* 349 */       return false;
/*     */     }
/* 351 */     News rhs = (News)object;
/* 352 */     return new EqualsBuilder()
/* 353 */       .append(this.newsId, rhs.newsId)
/* 354 */       .append(this.subjectIcon, rhs.subjectIcon)
/* 355 */       .append(this.subject, rhs.subject)
/* 356 */       .append(this.author, rhs.author)
/* 357 */       .append(this.createtime, rhs.createtime)
/* 358 */       .append(this.expTime, rhs.expTime)
/* 359 */       .append(this.replyCounts, rhs.replyCounts)
/* 360 */       .append(this.viewCounts, rhs.viewCounts)
/* 361 */       .append(this.issuer, rhs.issuer)
/* 362 */       .append(this.content, rhs.content)
/* 363 */       .append(this.updateTime, rhs.updateTime)
/* 364 */       .append(this.status, rhs.status)
/* 365 */       .append(this.isDeskImage, rhs.isDeskImage)
/* 366 */       .append(this.isNotice, rhs.isNotice)
/* 367 */       .append(this.sn, rhs.sn)
/* 368 */       .isEquals();
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 375 */     return new HashCodeBuilder(-82280557, -700257973)
/* 376 */       .append(this.newsId)
/* 377 */       .append(this.subjectIcon)
/* 378 */       .append(this.subject)
/* 379 */       .append(this.author)
/* 380 */       .append(this.createtime)
/* 381 */       .append(this.expTime)
/* 382 */       .append(this.replyCounts)
/* 383 */       .append(this.viewCounts)
/* 384 */       .append(this.issuer)
/* 385 */       .append(this.content)
/* 386 */       .append(this.updateTime)
/* 387 */       .append(this.status)
/* 388 */       .append(this.isDeskImage)
/* 389 */       .append(this.isNotice)
/* 390 */       .append(this.sn)
/* 391 */       .toHashCode();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 398 */     return new ToStringBuilder(this)
/* 399 */       .append("newsId", this.newsId)
/* 400 */       .append("subjectIcon", this.subjectIcon)
/* 401 */       .append("subject", this.subject)
/* 402 */       .append("author", this.author)
/* 403 */       .append("createtime", this.createtime)
/* 404 */       .append("expTime", this.expTime)
/* 405 */       .append("replyCounts", this.replyCounts)
/* 406 */       .append("viewCounts", this.viewCounts)
/* 407 */       .append("issuer", this.issuer)
/* 408 */       .append("content", this.content)
/* 409 */       .append("updateTime", this.updateTime)
/* 410 */       .append("status", this.status)
/* 411 */       .append("isDeskImage", this.isDeskImage)
/* 412 */       .append("isNotice", this.isNotice)
/* 413 */       .append("sn", this.sn)
/* 414 */       .toString();
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.info.News
 * JD-Core Version:    0.6.0
 */