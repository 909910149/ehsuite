/*     */ package com.htsoft.oa.model.flow;
/*     */ 
/*     */ import com.htsoft.core.model.BaseModel;
/*     */ import java.util.Date;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang.builder.EqualsBuilder;
/*     */ import org.apache.commons.lang.builder.HashCodeBuilder;
/*     */ import org.apache.commons.lang.builder.ToStringBuilder;
/*     */ 
/*     */ public class ProcessForm extends BaseModel
/*     */ {
/*  24 */   public static final Short STATUS_BACK = Short.valueOf((short)-1);
/*     */ 
/*  28 */   public static final Short STATUS_INIT = Short.valueOf((short)0);
/*     */ 
/*  32 */   public static final Short STATUS_PASS = Short.valueOf((short)1);
/*     */   protected Long formId;
/*     */   protected String activityName;
/*     */   protected Date createtime;
/*     */   protected Long creatorId;
/*     */   protected String creatorName;
/*     */   protected Date endtime;
/*     */   protected long durTimes;
/*     */   protected String fromTask;
/*     */   protected String fromTaskId;
/*     */   protected String taskId;
/*     */   protected String transTo;
/*     */   protected Short status;
/*     */   protected String comments;
/*     */   protected Long preFormId;
/*     */   protected ProcessRun processRun;
/*  57 */   protected Set formFiles = new HashSet();
/*     */ 
/*     */   public Date getCreatetime()
/*     */   {
/*  61 */     if (this.createtime == null) {
/*  62 */       this.createtime = new Date();
/*     */     }
/*  64 */     return this.createtime;
/*     */   }
/*     */ 
/*     */   public void setCreatetime(Date createtime) {
/*  68 */     this.createtime = createtime;
/*     */   }
/*     */ 
/*     */   public ProcessForm()
/*     */   {
/*     */   }
/*     */ 
/*     */   public ProcessForm(Long in_formId)
/*     */   {
/*  84 */     setFormId(in_formId);
/*     */   }
/*     */ 
/*     */   public ProcessRun getProcessRun()
/*     */   {
/*  89 */     return this.processRun;
/*     */   }
/*     */ 
/*     */   public void setProcessRun(ProcessRun in_processRun) {
/*  93 */     this.processRun = in_processRun;
/*     */   }
/*     */ 
/*     */   public Set getFormFiles()
/*     */   {
/* 105 */     return this.formFiles;
/*     */   }
/*     */ 
/*     */   public void setFormFiles(Set in_formFiles) {
/* 109 */     this.formFiles = in_formFiles;
/*     */   }
/*     */ 
/*     */   public Long getFormId()
/*     */   {
/* 118 */     return this.formId;
/*     */   }
/*     */ 
/*     */   public void setFormId(Long aValue)
/*     */   {
/* 125 */     this.formId = aValue;
/*     */   }
/*     */ 
/*     */   public Long getRunId()
/*     */   {
/* 132 */     return getProcessRun() == null ? null : getProcessRun().getRunId();
/*     */   }
/*     */ 
/*     */   public void setRunId(Long aValue)
/*     */   {
/* 139 */     if (aValue == null) {
/* 140 */       this.processRun = null;
/* 141 */     } else if (this.processRun == null) {
/* 142 */       this.processRun = new ProcessRun(aValue);
/* 143 */       this.processRun.setVersion(new Integer(0));
/*     */     } else {
/* 145 */       this.processRun.setRunId(aValue);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getActivityName()
/*     */   {
/* 154 */     return this.activityName;
/*     */   }
/*     */ 
/*     */   public void setActivityName(String aValue)
/*     */   {
/* 162 */     this.activityName = aValue;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 169 */     if (!(object instanceof ProcessForm)) {
/* 170 */       return false;
/*     */     }
/* 172 */     ProcessForm rhs = (ProcessForm)object;
/* 173 */     return new EqualsBuilder()
/* 174 */       .append(this.formId, rhs.formId)
/* 175 */       .append(this.activityName, rhs.activityName)
/* 176 */       .isEquals();
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 183 */     return new HashCodeBuilder(-82280557, -700257973)
/* 184 */       .append(this.formId)
/* 185 */       .append(this.activityName)
/* 186 */       .toHashCode();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 193 */     return new ToStringBuilder(this)
/* 194 */       .append("formId", this.formId)
/* 195 */       .append("activityName", this.activityName)
/* 196 */       .toString();
/*     */   }
/*     */ 
/*     */   public Long getCreatorId()
/*     */   {
/* 201 */     return this.creatorId;
/*     */   }
/*     */ 
/*     */   public void setCreatorId(Long creatorId) {
/* 205 */     this.creatorId = creatorId;
/*     */   }
/*     */ 
/*     */   public String getCreatorName() {
/* 209 */     return this.creatorName;
/*     */   }
/*     */ 
/*     */   public void setCreatorName(String creatorName) {
/* 213 */     this.creatorName = creatorName;
/*     */   }
/*     */ 
/*     */   public Date getEndtime() {
/* 217 */     return this.endtime;
/*     */   }
/*     */ 
/*     */   public void setEndtime(Date endtime) {
/* 221 */     this.endtime = endtime;
/*     */   }
/*     */ 
/*     */   public long getDurTimes() {
/* 225 */     return this.durTimes;
/*     */   }
/*     */ 
/*     */   public void setDurTimes(long durTimes) {
/* 229 */     this.durTimes = durTimes;
/*     */   }
/*     */ 
/*     */   public String getFromTask() {
/* 233 */     return this.fromTask;
/*     */   }
/*     */ 
/*     */   public void setFromTask(String fromTask) {
/* 237 */     this.fromTask = fromTask;
/*     */   }
/*     */ 
/*     */   public String getFromTaskId() {
/* 241 */     return this.fromTaskId;
/*     */   }
/*     */ 
/*     */   public void setFromTaskId(String fromTaskId) {
/* 245 */     this.fromTaskId = fromTaskId;
/*     */   }
/*     */ 
/*     */   public String getTaskId() {
/* 249 */     return this.taskId;
/*     */   }
/*     */ 
/*     */   public void setTaskId(String taskId) {
/* 253 */     this.taskId = taskId;
/*     */   }
/*     */ 
/*     */   public String getTransTo() {
/* 257 */     return this.transTo;
/*     */   }
/*     */ 
/*     */   public void setTransTo(String transTo) {
/* 261 */     this.transTo = transTo;
/*     */   }
/*     */ 
/*     */   public Short getStatus() {
/* 265 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(Short status) {
/* 269 */     this.status = status;
/*     */   }
/*     */ 
/*     */   public String getComments() {
/* 273 */     return this.comments;
/*     */   }
/*     */ 
/*     */   public void setComments(String comments) {
/* 277 */     this.comments = comments;
/*     */   }
/*     */ 
/*     */   public Long getPreFormId() {
/* 281 */     return this.preFormId;
/*     */   }
/*     */ 
/*     */   public void setPreFormId(Long preFormId) {
/* 285 */     this.preFormId = preFormId;
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.flow.ProcessForm
 * JD-Core Version:    0.6.0
 */