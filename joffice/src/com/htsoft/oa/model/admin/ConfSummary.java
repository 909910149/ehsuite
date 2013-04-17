/*     */ package com.htsoft.oa.model.admin;
/*     */ 
/*     */ import com.htsoft.core.model.BaseModel;
/*     */ import com.htsoft.oa.model.system.FileAttach;
/*     */ import java.util.Date;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang.builder.EqualsBuilder;
/*     */ import org.apache.commons.lang.builder.HashCodeBuilder;
/*     */ import org.apache.commons.lang.builder.ToStringBuilder;
/*     */ 
/*     */ public class ConfSummary extends BaseModel
/*     */ {
/*     */   protected Conference confId;
/*     */   protected Long sumId;
/*     */   protected Date createtime;
/*     */   protected String creator;
/*     */   protected String sumContent;
/*     */   protected Short status;
/*  32 */   protected Set<FileAttach> attachFiles = new HashSet();
/*     */ 
/*     */   public Set<FileAttach> getAttachFiles() {
/*  35 */     return this.attachFiles;
/*     */   }
/*     */ 
/*     */   public void setAttachFiles(Set<FileAttach> attachFiles) {
/*  39 */     this.attachFiles = attachFiles;
/*     */   }
/*     */ 
/*     */   public ConfSummary()
/*     */   {
/*     */   }
/*     */ 
/*     */   public ConfSummary(Long in_sumId)
/*     */   {
/*  53 */     setSumId(in_sumId);
/*     */   }
/*     */ 
/*     */   public Long getSumId()
/*     */   {
/*  63 */     return this.sumId;
/*     */   }
/*     */ 
/*     */   public void setSumId(Long aValue)
/*     */   {
/*  70 */     this.sumId = aValue;
/*     */   }
/*     */ 
/*     */   public Conference getConfId()
/*     */   {
/*  80 */     return this.confId;
/*     */   }
/*     */ 
/*     */   public void setConfId(Conference conference)
/*     */   {
/*  87 */     this.confId = conference;
/*     */   }
/*     */ 
/*     */   public Date getCreatetime()
/*     */   {
/*  97 */     return this.createtime;
/*     */   }
/*     */ 
/*     */   public void setCreatetime(Date aValue)
/*     */   {
/* 106 */     this.createtime = aValue;
/*     */   }
/*     */ 
/*     */   public String getCreator()
/*     */   {
/* 116 */     return this.creator;
/*     */   }
/*     */ 
/*     */   public void setCreator(String aValue)
/*     */   {
/* 125 */     this.creator = aValue;
/*     */   }
/*     */ 
/*     */   public String getSumContent()
/*     */   {
/* 135 */     return this.sumContent;
/*     */   }
/*     */ 
/*     */   public void setSumContent(String aValue)
/*     */   {
/* 144 */     this.sumContent = aValue;
/*     */   }
/*     */ 
/*     */   public Short getStatus()
/*     */   {
/* 154 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(Short aValue)
/*     */   {
/* 161 */     this.status = aValue;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 168 */     if (!(object instanceof ConfSummary)) {
/* 169 */       return false;
/*     */     }
/* 171 */     ConfSummary rhs = (ConfSummary)object;
/* 172 */     return new EqualsBuilder().append(this.sumId, rhs.sumId).append(
/* 173 */       this.confId, rhs.confId)
/* 174 */       .append(this.createtime, rhs.createtime).append(this.creator, 
/* 175 */       rhs.creator).append(this.sumContent, rhs.sumContent)
/* 176 */       .append(this.status, rhs.status).isEquals();
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 183 */     return new HashCodeBuilder(-82280557, -700257973).append(this.sumId)
/* 184 */       .append(this.confId).append(this.createtime).append(
/* 185 */       this.creator).append(this.sumContent).append(
/* 186 */       this.status).toHashCode();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 193 */     return new ToStringBuilder(this).append("sumId", this.sumId).append(
/* 194 */       "confId", this.confId).append("createtime", this.createtime)
/* 195 */       .append("creator", this.creator).append("sumContent", 
/* 196 */       this.sumContent).append("status", this.status)
/* 197 */       .toString();
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.admin.ConfSummary
 * JD-Core Version:    0.6.0
 */