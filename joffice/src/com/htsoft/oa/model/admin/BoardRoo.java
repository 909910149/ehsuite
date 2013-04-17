/*    */ package com.htsoft.oa.model.admin;
/*    */ 
/*    */ import com.htsoft.core.model.BaseModel;
/*    */ import org.apache.commons.lang.builder.EqualsBuilder;
/*    */ import org.apache.commons.lang.builder.HashCodeBuilder;
/*    */ import org.apache.commons.lang.builder.ToStringBuilder;
/*    */ 
/*    */ public class BoardRoo extends BaseModel
/*    */ {
/*    */   private Long roomId;
/*    */   private String roomName;
/*    */   private String roomDesc;
/*    */   private Long containNum;
/*    */ 
/*    */   public Long getRoomId()
/*    */   {
/* 27 */     return this.roomId;
/*    */   }
/*    */ 
/*    */   public void setRoomId(Long roomId) {
/* 31 */     this.roomId = roomId;
/*    */   }
/*    */ 
/*    */   public String getRoomName() {
/* 35 */     return this.roomName;
/*    */   }
/*    */ 
/*    */   public void setRoomName(String roomName) {
/* 39 */     this.roomName = roomName;
/*    */   }
/*    */ 
/*    */   public String getRoomDesc() {
/* 43 */     return this.roomDesc;
/*    */   }
/*    */ 
/*    */   public void setRoomDesc(String roomDesc) {
/* 47 */     this.roomDesc = roomDesc;
/*    */   }
/*    */ 
/*    */   public Long getContainNum() {
/* 51 */     return this.containNum;
/*    */   }
/*    */ 
/*    */   public void setContainNum(Long containNum) {
/* 55 */     this.containNum = containNum;
/*    */   }
/*    */ 
/*    */   public boolean equals(Object obj)
/*    */   {
/* 60 */     if (!(obj instanceof BoardRoo)) {
/* 61 */       return false;
/*    */     }
/* 63 */     BoardRoo boardRoo = (BoardRoo)obj;
/* 64 */     return new EqualsBuilder().append(this.roomId, boardRoo.roomId).append(
/* 65 */       this.roomName, boardRoo.roomName).append(this.roomDesc, 
/* 66 */       boardRoo.roomDesc).append(this.containNum, boardRoo.containNum)
/* 67 */       .isEquals();
/*    */   }
/*    */ 
/*    */   public int hashCode()
/*    */   {
/* 72 */     return new HashCodeBuilder(-82280557, -700257973).append(this.roomId)
/* 73 */       .append(this.roomName).append(this.roomDesc).append(
/* 74 */       this.containNum).toHashCode();
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 79 */     return new ToStringBuilder(this).append("roomId", this.roomId).append(
/* 80 */       "roomName", this.roomName).append("roomDesc", this.roomDesc)
/* 81 */       .append("containNum", this.containNum).toString();
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.admin.BoardRoo
 * JD-Core Version:    0.6.0
 */