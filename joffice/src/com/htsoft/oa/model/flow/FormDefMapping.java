/*     */ package com.htsoft.oa.model.flow;
/*     */ 
/*     */ import com.htsoft.core.model.BaseModel;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang.builder.EqualsBuilder;
/*     */ import org.apache.commons.lang.builder.HashCodeBuilder;
/*     */ import org.apache.commons.lang.builder.ToStringBuilder;
/*     */ 
/*     */ public class FormDefMapping extends BaseModel
/*     */ {
/*  24 */   public static final Short USE_TEMPLATE = Short.valueOf((short)1);
/*     */ 
/*  28 */   public static final Short NOT_USE_TEMPLATE = Short.valueOf((short)0);
/*     */   protected Long mappingId;
/*     */   protected Integer versionNo;
/*     */   protected ProDefinition proDefinition;
/*     */   protected FormDef formDef;
/*     */   protected String deployId;
/*     */   protected Short useTemplate;
/*  41 */   protected Set<FieldRights> fieldRightss = new HashSet();
/*     */ 
/*     */   public FormDefMapping()
/*     */   {
/*     */   }
/*     */ 
/*     */   public FormDefMapping(Long in_mappingId)
/*     */   {
/*  54 */     setMappingId(in_mappingId);
/*     */   }
/*     */ 
/*     */   public ProDefinition getProDefinition() {
/*  58 */     return this.proDefinition;
/*     */   }
/*     */ 
/*     */   public void setProDefinition(ProDefinition in_proDefinition)
/*     */   {
/*  63 */     this.proDefinition = in_proDefinition;
/*     */   }
/*     */ 
/*     */   public FormDef getFormDef() {
/*  67 */     return this.formDef;
/*     */   }
/*     */ 
/*     */   public void setFormDef(FormDef in_formDef) {
/*  71 */     this.formDef = in_formDef;
/*     */   }
/*     */ 
/*     */   public Set<FieldRights> getFieldRightss() {
/*  75 */     return this.fieldRightss;
/*     */   }
/*     */ 
/*     */   public void setFieldRightss(Set<FieldRights> in_fieldRightss) {
/*  79 */     this.fieldRightss = in_fieldRightss;
/*     */   }
/*     */ 
/*     */   public Long getMappingId()
/*     */   {
/*  89 */     return this.mappingId;
/*     */   }
/*     */ 
/*     */   public void setMappingId(Long aValue)
/*     */   {
/*  96 */     this.mappingId = aValue;
/*     */   }
/*     */ 
/*     */   public Long getFormDefId()
/*     */   {
/* 103 */     return getFormDef() == null ? null : getFormDef()
/* 104 */       .getFormDefId();
/*     */   }
/*     */ 
/*     */   public void setFormDefId(Long aValue)
/*     */   {
/* 111 */     if (aValue == null) {
/* 112 */       this.formDef = null;
/* 113 */     } else if (this.formDef == null) {
/* 114 */       this.formDef = new FormDef(aValue);
/* 115 */       this.formDef.setVersion(new Integer(0));
/*     */     }
/*     */     else
/*     */     {
/* 119 */       this.formDef.setFormDefId(aValue);
/*     */     }
/*     */   }
/*     */ 
/*     */   public Long getDefId()
/*     */   {
/* 127 */     return getProDefinition() == null ? null : getProDefinition()
/* 128 */       .getDefId();
/*     */   }
/*     */ 
/*     */   public void setDefId(Long aValue)
/*     */   {
/* 135 */     if (aValue == null) {
/* 136 */       this.proDefinition = null;
/* 137 */     } else if (this.proDefinition == null) {
/* 138 */       this.proDefinition = new ProDefinition(aValue);
/* 139 */       this.proDefinition.setVersion(new Integer(0));
/*     */     }
/*     */     else
/*     */     {
/* 143 */       this.proDefinition.setDefId(aValue);
/*     */     }
/*     */   }
/*     */ 
/*     */   public Integer getVersionNo()
/*     */   {
/* 154 */     return this.versionNo;
/*     */   }
/*     */ 
/*     */   public void setVersionNo(Integer aValue)
/*     */   {
/* 163 */     this.versionNo = aValue;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 170 */     if (!(object instanceof FormDefMapping)) {
/* 171 */       return false;
/*     */     }
/* 173 */     FormDefMapping rhs = (FormDefMapping)object;
/* 174 */     return new EqualsBuilder().append(this.mappingId, rhs.mappingId)
/* 175 */       .append(this.versionNo, rhs.versionNo).isEquals();
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 182 */     return new HashCodeBuilder(-82280557, -700257973)
/* 183 */       .append(this.mappingId).append(this.versionNo).toHashCode();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 190 */     return new ToStringBuilder(this).append("mappingId", this.mappingId)
/* 191 */       .append("versionNo", this.versionNo).toString();
/*     */   }
/*     */ 
/*     */   public String getDeployId() {
/* 195 */     return this.deployId;
/*     */   }
/*     */ 
/*     */   public void setDeployId(String deployId) {
/* 199 */     this.deployId = deployId;
/*     */   }
/*     */ 
/*     */   public Short getUseTemplate() {
/* 203 */     return this.useTemplate;
/*     */   }
/*     */ 
/*     */   public void setUseTemplate(Short useTemplate) {
/* 207 */     this.useTemplate = useTemplate;
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.flow.FormDefMapping
 * JD-Core Version:    0.6.0
 */