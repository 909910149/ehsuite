/*     */ package com.htsoft.oa.model.flow;
/*     */ 
/*     */ import com.htsoft.core.model.BaseModel;
/*     */ import org.apache.commons.lang.builder.EqualsBuilder;
/*     */ import org.apache.commons.lang.builder.HashCodeBuilder;
/*     */ import org.apache.commons.lang.builder.ToStringBuilder;
/*     */ 
/*     */ public class FieldRights extends BaseModel
/*     */ {
/*     */   protected Long rightId;
/*     */   protected String taskName;
/*     */   protected Short readWrite;
/*     */   protected Long refieldId;
/*     */   protected Long mappingId;
/*     */   protected transient FormField formField;
/*     */ 
/*     */   public FieldRights()
/*     */   {
/*     */   }
/*     */ 
/*     */   public FieldRights(Long in_rightId)
/*     */   {
/*  42 */     setRightId(in_rightId);
/*     */   }
/*     */ 
/*     */   public FormField getFormField() {
/*  46 */     return this.formField;
/*     */   }
/*     */ 
/*     */   public void setFormField(FormField in_formField) {
/*  50 */     this.formField = in_formField;
/*     */   }
/*     */ 
/*     */   public Long getRightId()
/*     */   {
/*  69 */     return this.rightId;
/*     */   }
/*     */ 
/*     */   public void setRightId(Long aValue)
/*     */   {
/*  76 */     this.rightId = aValue;
/*     */   }
/*     */ 
/*     */   public Long getFieldId()
/*     */   {
/* 107 */     return getFormField() == null ? null : getFormField()
/* 108 */       .getFieldId();
/*     */   }
/*     */ 
/*     */   public void setFieldId(Long aValue)
/*     */   {
/* 115 */     if (aValue == null) {
/* 116 */       this.formField = null;
/* 117 */     } else if (this.formField == null) {
/* 118 */       this.formField = new FormField(aValue);
/* 119 */       this.formField.setVersion(new Integer(0));
/*     */     }
/*     */     else
/*     */     {
/* 123 */       this.formField.setFieldId(aValue);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getTaskName()
/*     */   {
/* 134 */     return this.taskName;
/*     */   }
/*     */ 
/*     */   public void setTaskName(String aValue)
/*     */   {
/* 143 */     this.taskName = aValue;
/*     */   }
/*     */ 
/*     */   public Short getReadWrite()
/*     */   {
/* 153 */     return this.readWrite;
/*     */   }
/*     */ 
/*     */   public void setReadWrite(Short aValue)
/*     */   {
/* 162 */     this.readWrite = aValue;
/*     */   }
/*     */ 
/*     */   public Long getRefieldId() {
/* 166 */     return this.refieldId;
/*     */   }
/*     */ 
/*     */   public void setRefieldId(Long refieldId) {
/* 170 */     this.refieldId = refieldId;
/*     */   }
/*     */ 
/*     */   public Long getMappingId()
/*     */   {
/* 182 */     return this.mappingId;
/*     */   }
/*     */ 
/*     */   public void setMappingId(Long mappingId) {
/* 186 */     this.mappingId = mappingId;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 193 */     if (!(object instanceof FieldRights)) {
/* 194 */       return false;
/*     */     }
/* 196 */     FieldRights rhs = (FieldRights)object;
/* 197 */     return new EqualsBuilder().append(this.rightId, rhs.rightId).append(
/* 198 */       this.taskName, rhs.taskName).append(this.readWrite, 
/* 199 */       rhs.readWrite).isEquals();
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 206 */     return new HashCodeBuilder(-82280557, -700257973).append(this.rightId)
/* 207 */       .append(this.taskName).append(this.readWrite).toHashCode();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 214 */     return new ToStringBuilder(this).append("rightId", this.rightId)
/* 215 */       .append("taskName", this.taskName).append("readWrite", 
/* 216 */       this.readWrite).toString();
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.flow.FieldRights
 * JD-Core Version:    0.6.0
 */