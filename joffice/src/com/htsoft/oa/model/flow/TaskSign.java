/*     */ package com.htsoft.oa.model.flow;
/*     */ 
/*     */ import com.htsoft.core.model.BaseModel;
/*     */ import org.apache.commons.lang.builder.EqualsBuilder;
/*     */ import org.apache.commons.lang.builder.HashCodeBuilder;
/*     */ import org.apache.commons.lang.builder.ToStringBuilder;
/*     */ 
/*     */ public class TaskSign extends BaseModel
/*     */ {
/*  21 */   public static final Short DECIDE_TYPE_PASS = Short.valueOf((short) 1);
/*     */ 
/*  23 */   public static final Short DECIDE_TYPE_REFUSE = Short.valueOf((short) 0);
/*     */   protected Long signId;
/*     */   protected Integer voteCounts;
/*     */   protected Integer votePercents;
/*     */   protected Short decideType;
/*     */   protected ProUserAssign proUserAssign;
/*     */ 
/*     */   public TaskSign()
/*     */   {
/*     */   }
/*     */ 
/*     */   public TaskSign(Long in_signId)
/*     */   {
/*  42 */     setSignId(in_signId);
/*     */   }
/*     */ 
/*     */   public ProUserAssign getProUserAssign() {
/*  46 */     return this.proUserAssign;
/*     */   }
/*     */ 
/*     */   public void setProUserAssign(ProUserAssign in_proUserAssign)
/*     */   {
/*  51 */     this.proUserAssign = in_proUserAssign;
/*     */   }
/*     */ 
/*     */   public Long getSignId()
/*     */   {
/*  61 */     return this.signId;
/*     */   }
/*     */ 
/*     */   public void setSignId(Long aValue)
/*     */   {
/*  68 */     this.signId = aValue;
/*     */   }
/*     */ 
/*     */   public Long getAssignId()
/*     */   {
/*  75 */     return getProUserAssign() == null ? null : getProUserAssign()
/*  76 */       .getAssignId();
/*     */   }
/*     */ 
/*     */   public void setAssignId(Long aValue)
/*     */   {
/*  83 */     if (aValue == null) {
/*  84 */       this.proUserAssign = null;
/*  85 */     } else if (this.proUserAssign == null) {
/*  86 */       this.proUserAssign = new ProUserAssign(aValue);
/*  87 */       this.proUserAssign.setVersion(new Integer(0));
/*     */     }
/*     */     else
/*     */     {
/*  91 */       this.proUserAssign.setAssignId(aValue);
/*     */     }
/*     */   }
/*     */ 
/*     */   public Integer getVoteCounts()
/*     */   {
/* 102 */     return this.voteCounts;
/*     */   }
/*     */ 
/*     */   public void setVoteCounts(Integer aValue)
/*     */   {
/* 109 */     this.voteCounts = aValue;
/*     */   }
/*     */ 
/*     */   public Integer getVotePercents()
/*     */   {
/* 119 */     return this.votePercents;
/*     */   }
/*     */ 
/*     */   public void setVotePercents(Integer aValue)
/*     */   {
/* 126 */     this.votePercents = aValue;
/*     */   }
/*     */ 
/*     */   public Short getDecideType()
/*     */   {
/* 136 */     return this.decideType;
/*     */   }
/*     */ 
/*     */   public void setDecideType(Short aValue)
/*     */   {
/* 145 */     this.decideType = aValue;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 152 */     if (!(object instanceof TaskSign)) {
/* 153 */       return false;
/*     */     }
/* 155 */     TaskSign rhs = (TaskSign)object;
/* 156 */     return new EqualsBuilder().append(this.signId, rhs.signId).append(
/* 157 */       this.voteCounts, rhs.voteCounts).append(this.votePercents, 
/* 158 */       rhs.votePercents).append(this.decideType, rhs.decideType)
/* 159 */       .isEquals();
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 166 */     return new HashCodeBuilder(-82280557, -700257973).append(this.signId)
/* 167 */       .append(this.voteCounts).append(this.votePercents).append(
/* 168 */       this.decideType).toHashCode();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 175 */     return new ToStringBuilder(this).append("signId", this.signId).append(
/* 176 */       "voteCounts", this.voteCounts).append("votePercents", 
/* 177 */       this.votePercents).append("decideType", this.decideType)
/* 178 */       .toString();
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.flow.TaskSign
 * JD-Core Version:    0.6.0
 */