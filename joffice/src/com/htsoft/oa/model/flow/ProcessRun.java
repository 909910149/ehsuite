/*     */ package com.htsoft.oa.model.flow;
/*     */ 
/*     */ import com.google.gson.annotations.Expose;
/*     */ import com.htsoft.core.model.BaseModel;
/*     */ import com.htsoft.oa.model.system.AppUser;
/*     */ import java.util.Date;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang.builder.EqualsBuilder;
/*     */ import org.apache.commons.lang.builder.HashCodeBuilder;
/*     */ import org.apache.commons.lang.builder.ToStringBuilder;
/*     */ 
/*     */ public class ProcessRun extends BaseModel
/*     */ {
/*  26 */   public static final Short RUN_STATUS_INIT = Short.valueOf((short)0);
/*     */ 
/*  30 */   public static final Short RUN_STATUS_RUNNING = Short.valueOf((short)1);
/*     */ 
/*  34 */   public static final Short RUN_STATUS_FINISHED = Short.valueOf((short)2);
/*     */   protected Long runId;
/*     */ 
/*     */   @Expose
/*     */   protected String subject;
/*     */ 
/*     */   @Expose
/*     */   protected String creator;
/*     */ 
/*     */   @Expose
/*     */   protected Date createtime;
/*     */ 
/*     */   @Expose
/*     */   protected ProDefinition proDefinition;
/*     */ 
/*     */   @Expose
/*     */   protected String piId;
/*     */ 
/*     */   @Expose
/*     */   protected String busDesc;
/*     */ 
/*     */   @Expose
/*     */   protected String entityName;
/*     */ 
/*     */   @Expose
/*     */   protected String entityId;
/*     */ 
/*     */   @Expose
/*     */   protected Long formDefId;
/*     */ 
/*     */   @Expose
/*  57 */   protected Short runStatus = RUN_STATUS_INIT;
/*     */   protected AppUser appUser;
/*  61 */   protected Set processForms = new HashSet();
/*     */ 
/*     */   public ProcessRun()
/*     */   {
/*     */   }
/*     */ 
/*     */   public ProcessRun(Long in_runId)
/*     */   {
/*  76 */     setRunId(in_runId);
/*     */   }
/*     */ 
/*     */   public ProDefinition getProDefinition()
/*     */   {
/*  81 */     return this.proDefinition;
/*     */   }
/*     */ 
/*     */   public void setProDefinition(ProDefinition proDefinition) {
/*  85 */     this.proDefinition = proDefinition;
/*     */   }
/*     */ 
/*     */   public AppUser getAppUser() {
/*  89 */     return this.appUser;
/*     */   }
/*     */ 
/*     */   public void setAppUser(AppUser in_appUser) {
/*  93 */     this.appUser = in_appUser;
/*     */   }
/*     */ 
/*     */   public Set getProcessForms() {
/*  97 */     return this.processForms;
/*     */   }
/*     */ 
/*     */   public void setProcessForms(Set in_processForms) {
/* 101 */     this.processForms = in_processForms;
/*     */   }
/*     */ 
/*     */   public Long getRunId()
/*     */   {
/* 110 */     return this.runId;
/*     */   }
/*     */ 
/*     */   public void setRunId(Long aValue)
/*     */   {
/* 117 */     this.runId = aValue;
/*     */   }
/*     */ 
/*     */   public String getSubject()
/*     */   {
/* 126 */     return this.subject;
/*     */   }
/*     */ 
/*     */   public void setSubject(String aValue)
/*     */   {
/* 134 */     this.subject = aValue;
/*     */   }
/*     */ 
/*     */   public String getCreator()
/*     */   {
/* 142 */     return this.creator;
/*     */   }
/*     */ 
/*     */   public void setCreator(String aValue)
/*     */   {
/* 149 */     this.creator = aValue;
/*     */   }
/*     */ 
/*     */   public Long getUserId()
/*     */   {
/* 156 */     return getAppUser() == null ? null : getAppUser().getUserId();
/*     */   }
/*     */ 
/*     */   public void setUserId(Long aValue)
/*     */   {
/* 163 */     if (aValue == null) {
/* 164 */       this.appUser = null;
/* 165 */     } else if (this.appUser == null) {
/* 166 */       this.appUser = new AppUser(aValue);
/* 167 */       this.appUser.setVersion(new Integer(0));
/*     */     } else {
/* 169 */       this.appUser.setUserId(aValue);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getPiId()
/*     */   {
/* 179 */     return this.piId;
/*     */   }
/*     */ 
/*     */   public void setPiId(String aValue)
/*     */   {
/* 186 */     this.piId = aValue;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 193 */     if (!(object instanceof ProcessRun)) {
/* 194 */       return false;
/*     */     }
/* 196 */     ProcessRun rhs = (ProcessRun)object;
/* 197 */     return new EqualsBuilder()
/* 198 */       .append(this.runId, rhs.runId)
/* 199 */       .append(this.subject, rhs.subject)
/* 200 */       .append(this.creator, rhs.creator)
/* 201 */       .append(this.piId, rhs.piId)
/* 202 */       .isEquals();
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 209 */     return new HashCodeBuilder(-82280557, -700257973)
/* 210 */       .append(this.runId)
/* 211 */       .append(this.subject)
/* 212 */       .append(this.creator)
/* 213 */       .append(this.piId)
/* 214 */       .toHashCode();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 221 */     return new ToStringBuilder(this)
/* 222 */       .append("runId", this.runId)
/* 223 */       .append("subject", this.subject)
/* 224 */       .append("creator", this.creator)
/* 225 */       .append("piId", this.piId)
/* 226 */       .toString();
/*     */   }
/*     */ 
/*     */   public Date getCreatetime() {
/* 230 */     return this.createtime;
/*     */   }
/*     */ 
/*     */   public void setCreatetime(Date createtime) {
/* 234 */     this.createtime = createtime;
/*     */   }
/*     */ 
/*     */   public Short getRunStatus() {
/* 238 */     return this.runStatus;
/*     */   }
/*     */ 
/*     */   public void setRunStatus(Short runStatus) {
/* 242 */     this.runStatus = runStatus;
/*     */   }
/*     */ 
/*     */   public String getBusDesc() {
/* 246 */     return this.busDesc;
/*     */   }
/*     */ 
/*     */   public void setBusDesc(String busDesc) {
/* 250 */     this.busDesc = busDesc;
/*     */   }
/*     */ 
/*     */   public String getEntityName() {
/* 254 */     return this.entityName;
/*     */   }
/*     */ 
/*     */   public void setEntityName(String entityName) {
/* 258 */     this.entityName = entityName;
/*     */   }
/*     */ 
/*     */   public String getEntityId() {
/* 262 */     return this.entityId;
/*     */   }
/*     */ 
/*     */   public void setEntityId(String entityId) {
/* 266 */     this.entityId = entityId;
/*     */   }
/*     */ 
/*     */   public Long getFormDefId() {
/* 270 */     return this.formDefId;
/*     */   }
/*     */ 
/*     */   public void setFormDefId(Long formDefId) {
/* 274 */     this.formDefId = formDefId;
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.flow.ProcessRun
 * JD-Core Version:    0.6.0
 */