/*    */ package com.htsoft.oa.model.admin;
/*    */ 
/*    */ import com.htsoft.core.model.BaseModel;
/*    */ import org.apache.commons.lang.builder.EqualsBuilder;
/*    */ import org.apache.commons.lang.builder.HashCodeBuilder;
/*    */ import org.apache.commons.lang.builder.ToStringBuilder;
/*    */ 
/*    */ public class BoardType extends BaseModel
/*    */ {
/*    */   private Long typeId;
/*    */   private String typeName;
/*    */   private String typeDesc;
/*    */ 
/*    */   public BoardType()
/*    */   {
/*    */   }
/*    */ 
/*    */   public BoardType(Long in_typeId)
/*    */   {
/* 27 */     setTypeId(in_typeId);
/*    */   }
/*    */ 
/*    */   public Long getTypeId() {
/* 31 */     return this.typeId;
/*    */   }
/*    */ 
/*    */   public void setTypeId(Long typeId) {
/* 35 */     this.typeId = typeId;
/*    */   }
/*    */ 
/*    */   public String getTypeName() {
/* 39 */     return this.typeName;
/*    */   }
/*    */ 
/*    */   public void setTypeName(String typeName) {
/* 43 */     this.typeName = typeName;
/*    */   }
/*    */ 
/*    */   public String getTypeDesc() {
/* 47 */     return this.typeDesc;
/*    */   }
/*    */ 
/*    */   public void setTypeDesc(String typeDesc) {
/* 51 */     this.typeDesc = typeDesc;
/*    */   }
/*    */ 
/*    */   public boolean equals(Object obj)
/*    */   {
/* 61 */     if (!(obj instanceof BoardType)) {
/* 62 */       return false;
/*    */     }
/* 64 */     BoardType rhs = (BoardType)obj;
/* 65 */     return new EqualsBuilder().append(this.typeId, rhs.typeId).append(
/* 66 */       this.typeName, rhs.typeName)
/* 67 */       .append(this.typeDesc, rhs.typeDesc).isEquals();
/*    */   }
/*    */ 
/*    */   public int hashCode()
/*    */   {
/* 75 */     return new HashCodeBuilder(-82280557, -700257973).append(this.typeId)
/* 76 */       .append(this.typeName).append(this.typeDesc).hashCode();
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 84 */     return new ToStringBuilder(this).append("typeId", this.typeId).append(
/* 85 */       "typeName", this.typeName).append("typeDesc", this.typeDesc)
/* 86 */       .toString();
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.admin.BoardType
 * JD-Core Version:    0.6.0
 */