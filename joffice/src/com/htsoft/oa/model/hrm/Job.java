/*     */ package com.htsoft.oa.model.hrm;
/*     */ 
/*     */ import com.htsoft.core.model.BaseModel;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang.builder.EqualsBuilder;
/*     */ import org.apache.commons.lang.builder.HashCodeBuilder;
/*     */ import org.apache.commons.lang.builder.ToStringBuilder;
/*     */ 
/*     */ public class Job extends BaseModel
/*     */ {
/*  25 */   public static short DELFLAG_NOT = 0;
/*  26 */   public static short DELFLAG_HAD = 1;
/*     */   protected Long jobId;
/*     */   protected String jobName;
/*     */   protected String memo;
/*     */   protected Short delFlag;
/*     */   protected Long parentId;
/*     */   protected String path;
/*     */   protected Long depth;
/*  37 */   protected Set empProfiles = new HashSet();
/*     */ 
/*     */   public Job()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Job(Long in_jobId)
/*     */   {
/*  50 */     setJobId(in_jobId);
/*     */   }
/*     */ 
/*     */   public Set getEmpProfiles()
/*     */   {
/*  55 */     return this.empProfiles;
/*     */   }
/*     */ 
/*     */   public void setEmpProfiles(Set in_empProfiles)
/*     */   {
/*  60 */     this.empProfiles = in_empProfiles;
/*     */   }
/*     */ 
/*     */   public Long getJobId()
/*     */   {
/*  70 */     return this.jobId;
/*     */   }
/*     */ 
/*     */   public void setJobId(Long aValue)
/*     */   {
/*  77 */     this.jobId = aValue;
/*     */   }
/*     */ 
/*     */   public String getJobName()
/*     */   {
/*  87 */     return this.jobName;
/*     */   }
/*     */ 
/*     */   public void setJobName(String aValue)
/*     */   {
/*  96 */     this.jobName = aValue;
/*     */   }
/*     */ 
/*     */   public String getMemo()
/*     */   {
/* 106 */     return this.memo;
/*     */   }
/*     */ 
/*     */   public void setMemo(String aValue)
/*     */   {
/* 113 */     this.memo = aValue;
/*     */   }
/*     */ 
/*     */   public Short getDelFlag() {
/* 117 */     return this.delFlag;
/*     */   }
/*     */ 
/*     */   public void setDelFlag(Short delFlag) {
/* 121 */     this.delFlag = delFlag;
/*     */   }
/*     */ 
/*     */   public Long getParentId() {
/* 125 */     return this.parentId;
/*     */   }
/*     */ 
/*     */   public void setParentId(Long parentId) {
/* 129 */     this.parentId = parentId;
/*     */   }
/*     */ 
/*     */   public String getPath() {
/* 133 */     return this.path;
/*     */   }
/*     */ 
/*     */   public void setPath(String path) {
/* 137 */     this.path = path;
/*     */   }
/*     */ 
/*     */   public Long getDepth() {
/* 141 */     return this.depth;
/*     */   }
/*     */ 
/*     */   public void setDepth(Long depth) {
/* 145 */     this.depth = depth;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 152 */     if (!(object instanceof Job))
/* 153 */       return false;
/* 154 */     Job rhs = (Job)object;
/* 155 */     return new EqualsBuilder().append(this.jobId, rhs.jobId).append(
/* 156 */       this.jobName, rhs.jobName).append(this.memo, rhs.memo).append(
/* 157 */       this.delFlag, rhs.delFlag).append(this.parentId, rhs.parentId)
/* 158 */       .append(this.path, rhs.path).append(this.depth, rhs.depth)
/* 159 */       .isEquals();
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 166 */     return new HashCodeBuilder(-82280557, -700257973).append(this.jobId)
/* 167 */       .append(this.jobName).append(this.memo).append(this.delFlag)
/* 168 */       .append(this.parentId).append(this.path).append(this.depth)
/* 169 */       .toHashCode();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 176 */     return new ToStringBuilder(this).append("jobId", this.jobId).append(
/* 177 */       "jobName", this.jobName).append("memo", this.memo).append(
/* 178 */       "delFlag", this.delFlag).append("parentId", this.parentId)
/* 179 */       .append("path", this.path).append("depth", this.depth)
/* 180 */       .toString();
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.hrm.Job
 * JD-Core Version:    0.6.0
 */