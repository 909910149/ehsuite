/*     */ package com.htsoft.oa.model.flow;
/*     */ 
/*     */ import com.htsoft.core.model.BaseModel;
/*     */ import org.apache.commons.lang.builder.EqualsBuilder;
/*     */ import org.apache.commons.lang.builder.HashCodeBuilder;
/*     */ import org.apache.commons.lang.builder.ToStringBuilder;
/*     */ 
/*     */ public class ProUserAssign extends BaseModel
/*     */ {
/*  26 */   public static final Short IS_SIGNED_TASK = Short.valueOf((short)1);
/*     */ 
/*  30 */   public static final Short IS_NOT_SIGNED_TASK = Short.valueOf((short)1);
/*     */   protected Long assignId;
/*     */   protected String deployId;
/*     */   protected String activityName;
/*     */   protected String roleId;
/*     */   protected String roleName;
/*     */   protected String userId;
/*     */   protected String username;
/*     */   protected Short isSigned;
/*     */   protected String jobId;
/*     */   protected String jobName;
/*     */   protected String reJobId;
/*     */   protected String reJobName;
/*     */ 
/*     */   public ProUserAssign()
/*     */   {
/*     */   }
/*     */ 
/*     */   public ProUserAssign(Long in_assignId)
/*     */   {
/*  57 */     setAssignId(in_assignId);
/*     */   }
/*     */ 
/*     */   public Short getIsSigned() {
/*  61 */     return this.isSigned;
/*     */   }
/*     */ 
/*     */   public void setIsSigned(Short isSigned) {
/*  65 */     this.isSigned = isSigned;
/*     */   }
/*     */ 
/*     */   public Long getAssignId()
/*     */   {
/*  75 */     return this.assignId;
/*     */   }
/*     */ 
/*     */   public void setAssignId(Long aValue)
/*     */   {
/*  82 */     this.assignId = aValue;
/*     */   }
/*     */ 
/*     */   public String getDeployId()
/*     */   {
/*  92 */     return this.deployId;
/*     */   }
/*     */ 
/*     */   public void setDeployId(String aValue)
/*     */   {
/* 101 */     this.deployId = aValue;
/*     */   }
/*     */ 
/*     */   public String getActivityName()
/*     */   {
/* 111 */     return this.activityName;
/*     */   }
/*     */ 
/*     */   public void setActivityName(String aValue)
/*     */   {
/* 120 */     this.activityName = aValue;
/*     */   }
/*     */ 
/*     */   public String getRoleId()
/*     */   {
/* 130 */     return this.roleId;
/*     */   }
/*     */ 
/*     */   public void setRoleId(String aValue)
/*     */   {
/* 137 */     this.roleId = aValue;
/*     */   }
/*     */ 
/*     */   public String getUserId()
/*     */   {
/* 147 */     return this.userId;
/*     */   }
/*     */ 
/*     */   public void setUserId(String aValue)
/*     */   {
/* 154 */     this.userId = aValue;
/*     */   }
/*     */ 
/*     */   public String getJobId() {
/* 158 */     return this.jobId;
/*     */   }
/*     */ 
/*     */   public void setJobId(String jobId) {
/* 162 */     this.jobId = jobId;
/*     */   }
/*     */ 
/*     */   public String getJobName() {
/* 166 */     return this.jobName;
/*     */   }
/*     */ 
/*     */   public void setJobName(String jobName) {
/* 170 */     this.jobName = jobName;
/*     */   }
/*     */ 
/*     */   public String getReJobId() {
/* 174 */     return this.reJobId;
/*     */   }
/*     */ 
/*     */   public void setReJobId(String reJobId) {
/* 178 */     this.reJobId = reJobId;
/*     */   }
/*     */ 
/*     */   public String getReJobName() {
/* 182 */     return this.reJobName;
/*     */   }
/*     */ 
/*     */   public void setReJobName(String reJobName) {
/* 186 */     this.reJobName = reJobName;
/*     */   }
/*     */ 
/*     */   public String getFirstKeyColumnName()
/*     */   {
/* 193 */     return "assignId";
/*     */   }
/*     */ 
/*     */   public Long getId()
/*     */   {
/* 200 */     return this.assignId;
/*     */   }
/*     */ 
/*     */   public String getRoleName() {
/* 204 */     return this.roleName;
/*     */   }
/*     */ 
/*     */   public void setRoleName(String roleName) {
/* 208 */     this.roleName = roleName;
/*     */   }
/*     */ 
/*     */   public String getUsername() {
/* 212 */     return this.username;
/*     */   }
/*     */ 
/*     */   public void setUsername(String username) {
/* 216 */     this.username = username;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 223 */     if (!(object instanceof ProUserAssign)) {
/* 224 */       return false;
/*     */     }
/* 226 */     ProUserAssign rhs = (ProUserAssign)object;
/* 227 */     return new EqualsBuilder().append(this.assignId, rhs.assignId).append(
/* 228 */       this.deployId, rhs.deployId).append(this.activityName, 
/* 229 */       rhs.activityName).append(this.roleId, rhs.roleId).append(
/* 230 */       this.userId, rhs.userId).append(this.jobId, rhs.jobId).append(
/* 231 */       this.jobName, rhs.jobName).append(this.reJobId, rhs.reJobId)
/* 232 */       .append(this.reJobName, rhs.reJobName).append(this.isSigned, 
/* 233 */       rhs.isSigned).isEquals();
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 240 */     return new HashCodeBuilder(-82280557, -700257973).append(this.assignId)
/* 241 */       .append(this.deployId).append(this.activityName).append(
/* 242 */       this.roleId).append(this.userId).append(this.jobId)
/* 243 */       .append(this.jobName).append(this.reJobId).append(
/* 244 */       this.reJobName).append(this.isSigned).toHashCode();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 251 */     return new ToStringBuilder(this).append("assignId", this.assignId)
/* 252 */       .append("deployId", this.deployId).append("activityName", 
/* 253 */       this.activityName).append("roleId", this.roleId)
/* 254 */       .append("userId", this.userId).append("jobId", this.jobId)
/* 255 */       .append("jobName", this.jobName)
/* 256 */       .append("reJobId", this.reJobId).append("reJobName", 
/* 257 */       this.reJobName).append("isSigned", this.isSigned)
/* 258 */       .toString();
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.flow.ProUserAssign
 * JD-Core Version:    0.6.0
 */