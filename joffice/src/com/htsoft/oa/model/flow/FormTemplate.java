/*     */ package com.htsoft.oa.model.flow;
/*     */ 
/*     */ import com.htsoft.core.model.BaseModel;
/*     */ import org.apache.commons.lang.builder.EqualsBuilder;
/*     */ import org.apache.commons.lang.builder.HashCodeBuilder;
/*     */ import org.apache.commons.lang.builder.ToStringBuilder;
/*     */ 
/*     */ public class FormTemplate extends BaseModel
/*     */ {
/*     */   protected Long templateId;
/*     */   protected String nodeName;
/*     */   protected String tempContent;
/*     */   protected String extDef;
/*     */   protected FormDefMapping formDefMapping;
/*     */ 
/*     */   public FormTemplate()
/*     */   {
/*     */   }
/*     */ 
/*     */   public FormTemplate(Long in_templateId)
/*     */   {
/*  39 */     setTemplateId(in_templateId);
/*     */   }
/*     */ 
/*     */   public FormDefMapping getFormDefMapping()
/*     */   {
/*  44 */     return this.formDefMapping;
/*     */   }
/*     */ 
/*     */   public void setFormDefMapping(FormDefMapping in_formDefMapping) {
/*  48 */     this.formDefMapping = in_formDefMapping;
/*     */   }
/*     */ 
/*     */   public Long getTemplateId()
/*     */   {
/*  57 */     return this.templateId;
/*     */   }
/*     */ 
/*     */   public void setTemplateId(Long aValue)
/*     */   {
/*  64 */     this.templateId = aValue;
/*     */   }
/*     */ 
/*     */   public Long getMappingId()
/*     */   {
/*  71 */     return getFormDefMapping() == null ? null : getFormDefMapping().getMappingId();
/*     */   }
/*     */ 
/*     */   public void setMappingId(Long aValue)
/*     */   {
/*  78 */     if (aValue == null) {
/*  79 */       this.formDefMapping = null;
/*  80 */     } else if (this.formDefMapping == null) {
/*  81 */       this.formDefMapping = new FormDefMapping(aValue);
/*  82 */       this.formDefMapping.setVersion(new Integer(0));
/*     */     }
/*     */     else {
/*  85 */       this.formDefMapping.setMappingId(aValue);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getNodeName()
/*     */   {
/*  94 */     return this.nodeName;
/*     */   }
/*     */ 
/*     */   public void setNodeName(String aValue)
/*     */   {
/* 102 */     this.nodeName = aValue;
/*     */   }
/*     */ 
/*     */   public String getTempContent()
/*     */   {
/* 110 */     return this.tempContent;
/*     */   }
/*     */ 
/*     */   public void setTempContent(String aValue)
/*     */   {
/* 117 */     this.tempContent = aValue;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 124 */     if (!(object instanceof FormTemplate)) {
/* 125 */       return false;
/*     */     }
/* 127 */     FormTemplate rhs = (FormTemplate)object;
/* 128 */     return new EqualsBuilder()
/* 129 */       .append(this.templateId, rhs.templateId)
/* 130 */       .append(this.nodeName, rhs.nodeName)
/* 131 */       .append(this.tempContent, rhs.tempContent)
/* 132 */       .isEquals();
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 139 */     return new HashCodeBuilder(-82280557, -700257973)
/* 140 */       .append(this.templateId)
/* 141 */       .append(this.nodeName)
/* 142 */       .append(this.tempContent)
/* 143 */       .toHashCode();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 150 */     return new ToStringBuilder(this)
/* 151 */       .append("templateId", this.templateId)
/* 152 */       .append("nodeName", this.nodeName)
/* 153 */       .append("tempContent", this.tempContent)
/* 154 */       .toString();
/*     */   }
/*     */ 
/*     */   public String getExtDef() {
/* 158 */     return this.extDef;
/*     */   }
/*     */ 
/*     */   public void setExtDef(String extDef) {
/* 162 */     this.extDef = extDef;
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.flow.FormTemplate
 * JD-Core Version:    0.6.0
 */