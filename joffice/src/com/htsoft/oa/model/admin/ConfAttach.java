/*    */ package com.htsoft.oa.model.admin;
/*    */ 
/*    */ import com.htsoft.core.model.BaseModel;
/*    */ import com.htsoft.oa.model.system.FileAttach;
/*    */ import org.apache.commons.lang.builder.EqualsBuilder;
/*    */ import org.apache.commons.lang.builder.HashCodeBuilder;
/*    */ import org.apache.commons.lang.builder.ToStringBuilder;
/*    */ 
/*    */ public class ConfAttach extends BaseModel
/*    */ {
/*    */   private Conference confId;
/*    */   private FileAttach fileId;
/*    */ 
/*    */   public Conference getConfId()
/*    */   {
/* 26 */     return this.confId;
/*    */   }
/*    */ 
/*    */   public void setConfId(Conference confId) {
/* 30 */     this.confId = confId;
/*    */   }
/*    */ 
/*    */   public FileAttach getFileId() {
/* 34 */     return this.fileId;
/*    */   }
/*    */ 
/*    */   public void setFileId(FileAttach fileId) {
/* 38 */     this.fileId = fileId;
/*    */   }
/*    */ 
/*    */   public boolean equals(Object obj) {
/* 42 */     if (!(obj instanceof ConfAttach)) {
/* 43 */       return false;
/*    */     }
/* 45 */     ConfAttach rhs = (ConfAttach)obj;
/* 46 */     return new EqualsBuilder().append(this.confId, rhs.confId).append(
/* 47 */       this.fileId, rhs.fileId).isEquals();
/*    */   }
/*    */ 
/*    */   public int hashCode() {
/* 51 */     return new HashCodeBuilder(-82280557, -700257973).append(this.confId)
/* 52 */       .append(this.fileId).hashCode();
/*    */   }
/*    */ 
/*    */   public String toString() {
/* 56 */     return new ToStringBuilder(this).append("confId", this.confId).append(
/* 57 */       "fileId", this.fileId).toString();
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.admin.ConfAttach
 * JD-Core Version:    0.6.0
 */