/*     */ package com.htsoft.oa.model.flow;
/*     */ 
/*     */ import com.google.gson.annotations.Expose;
/*     */ import com.htsoft.core.model.BaseModel;
/*     */ import com.htsoft.oa.FlowConstants;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang.builder.EqualsBuilder;
/*     */ import org.apache.commons.lang.builder.HashCodeBuilder;
/*     */ import org.apache.commons.lang.builder.ToStringBuilder;
/*     */ 
/*     */ public class FormField extends BaseModel
/*     */ {
/*  24 */   public static final Short FLOW_TITLE = Short.valueOf((short)1);
/*     */ 
/*  28 */   public static final Short NOT_FLOW_TITLE = Short.valueOf((short)0);
/*     */ 
/*  33 */   public static final Short PRIMARY_KEY = Short.valueOf((short)1);
/*     */ 
/*  37 */   public static final Short NOT_PRIMARY_KEY = Short.valueOf((short)0);
/*     */ 
/*  41 */   public static final Short IS_SHOW = Short.valueOf((short)1);
/*     */ 
/*  45 */   public static final Short UN_SHOW = Short.valueOf((short)2);
/*     */ 
/*  49 */   public static final Short HAND_IN = Short.valueOf((short)3);
/*     */ 
/*     */   @Expose
/*     */   protected Long fieldId;
/*     */ 
/*     */   @Expose
/*     */   protected String fieldName;
/*     */ 
/*     */   @Expose
/*     */   protected String fieldLabel;
/*     */ 
/*     */   @Expose
/*     */   protected String fieldType;
/*     */ 
/*     */   @Expose
/*     */   protected Short isRequired;
/*     */ 
/*     */   @Expose
/*     */   protected Integer fieldSize;
/*     */ 
/*     */   @Expose
/*     */   protected String fieldDscp;
/*     */ 
/*     */   @Expose
/*     */   protected Short isPrimary;
/*     */ 
/*     */   @Expose
/*     */   protected String foreignKey;
/*     */ 
/*     */   @Expose
/*     */   protected String foreignTable;
/*     */ 
/*     */   @Expose
/*     */   protected Short isList;
/*     */ 
/*     */   @Expose
/*     */   protected Short isQuery;
/*     */ 
/*     */   @Expose
/*     */   protected String showFormat;
/*     */   protected transient FormTable formTable;
/*     */ 
/*     */   @Expose
/*     */   protected Short isFlowTitle;
/*     */ 
/*     */   @Expose
/*     */   protected Short isDesignShow;
/*  81 */   protected Set<FieldRights> fieldRightss = new HashSet();
/*     */ 
/*     */   public FormField()
/*     */   {
/*     */   }
/*     */ 
/*     */   public FormField(Long in_fieldId)
/*     */   {
/*  95 */     setFieldId(in_fieldId);
/*     */   }
/*     */ 
/*     */   public FormTable getFormTable()
/*     */   {
/* 100 */     return this.formTable;
/*     */   }
/*     */ 
/*     */   public void setFormTable(FormTable in_formTable) {
/* 104 */     this.formTable = in_formTable;
/*     */   }
/*     */ 
/*     */   public Long getFieldId()
/*     */   {
/* 113 */     return this.fieldId;
/*     */   }
/*     */ 
/*     */   public void setFieldId(Long aValue)
/*     */   {
/* 120 */     this.fieldId = aValue;
/*     */   }
/*     */ 
/*     */   public Long getTableId()
/*     */   {
/* 127 */     return getFormTable() == null ? null : getFormTable().getTableId();
/*     */   }
/*     */ 
/*     */   public void setTableId(Long aValue)
/*     */   {
/* 134 */     if (aValue == null) {
/* 135 */       this.formTable = null;
/* 136 */     } else if (this.formTable == null) {
/* 137 */       this.formTable = new FormTable(aValue);
/* 138 */       this.formTable.setVersion(new Integer(0));
/*     */     }
/*     */     else {
/* 141 */       this.formTable.setTableId(aValue);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getFieldName()
/*     */   {
/* 150 */     return this.fieldName;
/*     */   }
/*     */ 
/*     */   public void setFieldName(String aValue)
/*     */   {
/* 157 */     this.fieldName = aValue;
/*     */   }
/*     */ 
/*     */   public String getFieldType()
/*     */   {
/* 165 */     return this.fieldType;
/*     */   }
/*     */ 
/*     */   public void setFieldType(String aValue)
/*     */   {
/* 172 */     this.fieldType = aValue;
/*     */   }
/*     */ 
/*     */   public Short getIsRequired()
/*     */   {
/* 180 */     return this.isRequired;
/*     */   }
/*     */ 
/*     */   public void setIsRequired(Short aValue)
/*     */   {
/* 187 */     this.isRequired = aValue;
/*     */   }
/*     */ 
/*     */   public Integer getFieldSize()
/*     */   {
/* 195 */     return this.fieldSize;
/*     */   }
/*     */ 
/*     */   public void setFieldSize(Integer aValue)
/*     */   {
/* 202 */     this.fieldSize = aValue;
/*     */   }
/*     */ 
/*     */   public String getFieldDscp()
/*     */   {
/* 210 */     return this.fieldDscp;
/*     */   }
/*     */ 
/*     */   public void setFieldDscp(String aValue)
/*     */   {
/* 217 */     this.fieldDscp = aValue;
/*     */   }
/*     */ 
/*     */   public Short getIsPrimary()
/*     */   {
/* 225 */     return this.isPrimary;
/*     */   }
/*     */ 
/*     */   public void setIsPrimary(Short aValue)
/*     */   {
/* 232 */     this.isPrimary = aValue;
/*     */   }
/*     */ 
/*     */   public String getForeignKey()
/*     */   {
/* 240 */     return this.foreignKey;
/*     */   }
/*     */ 
/*     */   public void setForeignKey(String aValue)
/*     */   {
/* 247 */     this.foreignKey = aValue;
/*     */   }
/*     */ 
/*     */   public String getForeignTable()
/*     */   {
/* 255 */     return this.foreignTable;
/*     */   }
/*     */ 
/*     */   public void setForeignTable(String aValue)
/*     */   {
/* 262 */     this.foreignTable = aValue;
/*     */   }
/*     */ 
/*     */   public Short getIsList()
/*     */   {
/* 270 */     return this.isList;
/*     */   }
/*     */ 
/*     */   public void setIsList(Short aValue)
/*     */   {
/* 277 */     this.isList = aValue;
/*     */   }
/*     */ 
/*     */   public Short getIsQuery()
/*     */   {
/* 285 */     return this.isQuery;
/*     */   }
/*     */ 
/*     */   public void setIsQuery(Short aValue)
/*     */   {
/* 292 */     this.isQuery = aValue;
/*     */   }
/*     */ 
/*     */   public String getShowFormat() {
/* 296 */     return this.showFormat;
/*     */   }
/*     */ 
/*     */   public void setShowFormat(String showFormat) {
/* 300 */     this.showFormat = showFormat;
/*     */   }
/*     */ 
/*     */   public Short getIsFlowTitle() {
/* 304 */     return this.isFlowTitle;
/*     */   }
/*     */ 
/*     */   public void setIsFlowTitle(Short isFlowTitle) {
/* 308 */     this.isFlowTitle = isFlowTitle;
/*     */   }
/*     */ 
/*     */   public String getFieldLabel() {
/* 312 */     return this.fieldLabel;
/*     */   }
/*     */ 
/*     */   public void setFieldLabel(String fieldLabel) {
/* 316 */     this.fieldLabel = fieldLabel;
/*     */   }
/*     */ 
/*     */   public Short getIsDesignShow() {
/* 320 */     return this.isDesignShow;
/*     */   }
/*     */ 
/*     */   public void setIsDesignShow(Short isDesignShow) {
/* 324 */     this.isDesignShow = isDesignShow;
/*     */   }
/*     */ 
/*     */   public Class getFieldJavaClass()
/*     */   {
/* 332 */     return (Class)FlowConstants.FIELD_TYPE_MAP.get(this.fieldType);
/*     */   }
/*     */ 
/*     */   public Set<FieldRights> getFieldRightss() {
/* 336 */     return this.fieldRightss;
/*     */   }
/*     */ 
/*     */   public void setFieldRightss(Set<FieldRights> fieldRightss) {
/* 340 */     this.fieldRightss = fieldRightss;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 347 */     if (!(object instanceof FormField)) {
/* 348 */       return false;
/*     */     }
/* 350 */     FormField rhs = (FormField)object;
/* 351 */     return new EqualsBuilder()
/* 352 */       .append(this.fieldId, rhs.fieldId)
/* 353 */       .append(this.fieldName, rhs.fieldName)
/* 354 */       .append(this.fieldType, rhs.fieldType)
/* 355 */       .append(this.isRequired, rhs.isRequired)
/* 356 */       .append(this.fieldSize, rhs.fieldSize)
/* 357 */       .append(this.fieldDscp, rhs.fieldDscp)
/* 358 */       .append(this.isPrimary, rhs.isPrimary)
/* 359 */       .append(this.foreignKey, rhs.foreignKey)
/* 360 */       .append(this.foreignTable, rhs.foreignTable)
/* 361 */       .append(this.isList, rhs.isList)
/* 362 */       .append(this.isQuery, rhs.isQuery)
/* 363 */       .isEquals();
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 370 */     return new HashCodeBuilder(-82280557, -700257973)
/* 371 */       .append(this.fieldId)
/* 372 */       .append(this.fieldName)
/* 373 */       .append(this.fieldType)
/* 374 */       .append(this.isRequired)
/* 375 */       .append(this.fieldSize)
/* 376 */       .append(this.fieldDscp)
/* 377 */       .append(this.isPrimary)
/* 378 */       .append(this.foreignKey)
/* 379 */       .append(this.foreignTable)
/* 380 */       .append(this.isList)
/* 381 */       .append(this.isQuery)
/* 382 */       .toHashCode();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 389 */     return new ToStringBuilder(this)
/* 390 */       .append("fieldId", this.fieldId)
/* 391 */       .append("fieldName", this.fieldName)
/* 392 */       .append("fieldType", this.fieldType)
/* 393 */       .append("isRequired", this.isRequired)
/* 394 */       .append("fieldSize", this.fieldSize)
/* 395 */       .append("fieldDscp", this.fieldDscp)
/* 396 */       .append("isPrimary", this.isPrimary)
/* 397 */       .append("foreignKey", this.foreignKey)
/* 398 */       .append("foreignTable", this.foreignTable)
/* 399 */       .append("isList", this.isList)
/* 400 */       .append("isQuery", this.isQuery)
/* 401 */       .toString();
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.flow.FormField
 * JD-Core Version:    0.6.0
 */