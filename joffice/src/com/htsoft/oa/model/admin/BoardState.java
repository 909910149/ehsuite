/*    */ package com.htsoft.oa.model.admin;
/*    */ 
/*    */ import com.htsoft.core.model.BaseModel;
/*    */ import org.apache.commons.lang.builder.EqualsBuilder;
/*    */ import org.apache.commons.lang.builder.HashCodeBuilder;
/*    */ import org.apache.commons.lang.builder.ToStringBuilder;
/*    */ 
/*    */ public class BoardState extends BaseModel
/*    */ {
/*    */   private Integer stateId;
/*    */   private String stateName;
/*    */ 
/*    */   public BoardState()
/*    */   {
/*    */   }
/*    */ 
/*    */   public BoardState(Integer in_stateId)
/*    */   {
/* 26 */     this.stateId = in_stateId;
/*    */   }
/*    */ 
/*    */   public Integer getStateId() {
/* 30 */     return this.stateId;
/*    */   }
/*    */ 
/*    */   public void setStateId(Integer stateId) {
/* 34 */     this.stateId = stateId;
/*    */   }
/*    */ 
/*    */   public String getStateName() {
/* 38 */     return this.stateName;
/*    */   }
/*    */ 
/*    */   public void setStateName(String stateName) {
/* 42 */     this.stateName = stateName;
/*    */   }
/*    */ 
/*    */   public int hashCode()
/*    */   {
/* 47 */     return new HashCodeBuilder(-82280557, -700257973).append(this.stateId)
/* 48 */       .append(this.stateName).hashCode();
/*    */   }
/*    */ 
/*    */   public boolean equals(Object obj)
/*    */   {
/* 53 */     if (!(obj instanceof BoardType)) {
/* 54 */       return false;
/*    */     }
/* 56 */     BoardState bs = (BoardState)obj;
/* 57 */     return new EqualsBuilder().append(this.stateId, bs.stateId).append(
/* 58 */       this.stateName, bs.stateName).isEquals();
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 63 */     return new ToStringBuilder(this).append("stateId", this.stateId)
/* 64 */       .append("stateName", this.stateName).toString();
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.admin.BoardState
 * JD-Core Version:    0.6.0
 */