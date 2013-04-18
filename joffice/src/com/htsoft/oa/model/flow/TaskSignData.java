/*     */ package com.htsoft.oa.model.flow;
/*     */ 
/*     */ import com.htsoft.core.model.BaseModel;
/*     */ import java.util.Date;
/*     */ import org.apache.commons.lang.builder.EqualsBuilder;
/*     */ import org.apache.commons.lang.builder.HashCodeBuilder;
/*     */ import org.apache.commons.lang.builder.ToStringBuilder;
/*     */ 
/*     */ public class TaskSignData extends BaseModel
/*     */ {
/*  21 */   public static final Short IS_AGREE = Short.valueOf((short)1);
/*     */ 
/*  25 */   public static final Short IS_NOT_AGREE = Short.valueOf((short)2);
/*     */   protected Long dataId;
/*     */   protected Long voteId;
/*     */   protected String voteName;
/*     */   protected Date voteTime;
/*     */   protected String taskId;
/*     */   protected Short isAgree;
/*     */ 
/*     */   public TaskSignData()
/*     */   {
/*     */   }
/*     */ 
/*     */   public TaskSignData(Long in_dataId)
/*     */   {
/*  48 */     setDataId(in_dataId);
/*     */   }
/*     */ 
/*     */   public Long getDataId()
/*     */   {
/*  58 */     return this.dataId;
/*     */   }
/*     */ 
/*     */   public void setDataId(Long aValue)
/*     */   {
/*  65 */     this.dataId = aValue;
/*     */   }
/*     */ 
/*     */   public Long getVoteId()
/*     */   {
/*  73 */     return this.voteId;
/*     */   }
/*     */ 
/*     */   public void setVoteId(Long aValue)
/*     */   {
/*  81 */     this.voteId = aValue;
/*     */   }
/*     */ 
/*     */   public String getVoteName()
/*     */   {
/*  89 */     return this.voteName;
/*     */   }
/*     */ 
/*     */   public void setVoteName(String aValue)
/*     */   {
/*  96 */     this.voteName = aValue;
/*     */   }
/*     */ 
/*     */   public Date getVoteTime()
/*     */   {
/* 104 */     return this.voteTime;
/*     */   }
/*     */ 
/*     */   public void setVoteTime(Date aValue)
/*     */   {
/* 112 */     this.voteTime = aValue;
/*     */   }
/*     */ 
/*     */   public String getTaskId()
/*     */   {
/* 120 */     return this.taskId;
/*     */   }
/*     */ 
/*     */   public void setTaskId(String aValue)
/*     */   {
/* 128 */     this.taskId = aValue;
/*     */   }
/*     */ 
/*     */   public Short getIsAgree()
/*     */   {
/* 138 */     return this.isAgree;
/*     */   }
/*     */ 
/*     */   public void setIsAgree(Short aValue)
/*     */   {
/* 146 */     this.isAgree = aValue;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 153 */     if (!(object instanceof TaskSignData)) {
/* 154 */       return false;
/*     */     }
/* 156 */     TaskSignData rhs = (TaskSignData)object;
/* 157 */     return new EqualsBuilder()
/* 158 */       .append(this.dataId, rhs.dataId)
/* 159 */       .append(this.voteId, rhs.voteId)
/* 160 */       .append(this.voteName, rhs.voteName)
/* 161 */       .append(this.voteTime, rhs.voteTime)
/* 162 */       .append(this.taskId, rhs.taskId)
/* 163 */       .append(this.isAgree, rhs.isAgree)
/* 164 */       .isEquals();
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 171 */     return new HashCodeBuilder(-82280557, -700257973)
/* 172 */       .append(this.dataId)
/* 173 */       .append(this.voteId)
/* 174 */       .append(this.voteName)
/* 175 */       .append(this.voteTime)
/* 176 */       .append(this.taskId)
/* 177 */       .append(this.isAgree)
/* 178 */       .toHashCode();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 185 */     return new ToStringBuilder(this)
/* 186 */       .append("dataId", this.dataId)
/* 187 */       .append("voteId", this.voteId)
/* 188 */       .append("voteName", this.voteName)
/* 189 */       .append("voteTime", this.voteTime)
/* 190 */       .append("taskId", this.taskId)
/* 191 */       .append("isAgree", this.isAgree)
/* 192 */       .toString();
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.flow.TaskSignData
 * JD-Core Version:    0.6.0
 */