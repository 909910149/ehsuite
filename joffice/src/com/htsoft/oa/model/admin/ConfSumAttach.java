/*    */ package com.htsoft.oa.model.admin;
/*    */ 
/*    */ import com.htsoft.core.model.BaseModel;
/*    */ import org.apache.commons.lang.builder.EqualsBuilder;
/*    */ import org.apache.commons.lang.builder.HashCodeBuilder;
/*    */ import org.apache.commons.lang.builder.ToStringBuilder;
/*    */ 
/*    */ public class ConfSumAttach extends BaseModel
/*    */ {
/*    */   protected ConfSummary sumId;
/*    */   private static final long serialVersionUID = 1L;
/*    */   protected Integer fileId;
/*    */ 
/*    */   public ConfSummary getSumId()
/*    */   {
/* 38 */     return this.sumId;
/*    */   }
/*    */ 
/*    */   public void setSumId(ConfSummary confSummary)
/*    */   {
/* 45 */     this.sumId = confSummary;
/*    */   }
/*    */ 
/*    */   public Integer getFileId()
/*    */   {
/* 55 */     return this.fileId;
/*    */   }
/*    */ 
/*    */   public void setFileId(Integer aValue)
/*    */   {
/* 62 */     this.fileId = aValue;
/*    */   }
/*    */ 
/*    */   public boolean equals(Object object)
/*    */   {
/* 69 */     if (!(object instanceof ConfSumAttach)) {
/* 70 */       return false;
/*    */     }
/* 72 */     ConfSumAttach rhs = (ConfSumAttach)object;
/* 73 */     return new EqualsBuilder().append(this.sumId, rhs.sumId).append(
/* 74 */       this.fileId, rhs.fileId).isEquals();
/*    */   }
/*    */ 
/*    */   public int hashCode()
/*    */   {
/* 81 */     return new HashCodeBuilder(-82280557, -700257973).append(this.sumId)
/* 82 */       .append(this.fileId).toHashCode();
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 89 */     return new ToStringBuilder(this).append("sumId", this.sumId).append(
/* 90 */       "fileId", this.fileId).toString();
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.admin.ConfSumAttach
 * JD-Core Version:    0.6.0
 */