/*     */ package com.htsoft.oa.model.flow;
/*     */ 
/*     */ import com.htsoft.core.jbpm.pv.ParamField;
/*     */ import com.htsoft.core.model.BaseModel;
/*     */ import java.math.BigDecimal;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import org.apache.commons.lang.builder.EqualsBuilder;
/*     */ import org.apache.commons.lang.builder.HashCodeBuilder;
/*     */ import org.apache.commons.lang.builder.ToStringBuilder;
/*     */ import org.apache.commons.lang.time.DateUtils;
/*     */ import org.apache.commons.logging.Log;
/*     */ 
/*     */ public class RunData extends BaseModel
/*     */ {
/*  28 */   public static final Short SHOWED = Short.valueOf((short)1);
/*     */ 
/*  30 */   public static final Short UNSHOWED = Short.valueOf((short)0);
/*     */   protected Long dataId;
/*     */   protected String fieldLabel;
/*     */   protected String fieldName;
/*     */   protected Integer intValue;
/*     */   protected Long longValue;
/*     */   protected BigDecimal decValue;
/*     */   protected Date dateValue;
/*     */   protected String strValue;
/*     */   protected String blobValue;
/*     */   protected Short boolValue;
/*     */   protected String textValue;
/*  44 */   protected Short isShowed = Short.valueOf((short)1);
/*     */   protected String fieldType;
/*     */   protected ProcessRun processRun;
/*     */ 
/*     */   public RunData(ParamField pf)
/*     */   {
/*  50 */     copyValue(pf);
/*     */   }
/*     */ 
/*     */   public void setRawValue(Object val)
/*     */   {
/*  58 */     if (val == null) return;
/*  59 */     if ((val instanceof String)) {
/*  60 */       if (val.toString().length() < 4000)
/*  61 */         this.strValue = ((String)val);
/*     */       else
/*  63 */         this.textValue = ((String)val);
/*     */     }
/*  65 */     else if ((val instanceof Integer))
/*  66 */       this.intValue = ((Integer)val);
/*  67 */     else if ((val instanceof Long))
/*  68 */       this.longValue = ((Long)val);
/*  69 */     else if ((val instanceof BigDecimal))
/*  70 */       this.decValue = ((BigDecimal)val);
/*  71 */     else if ((val instanceof Date))
/*  72 */       this.dateValue = ((Date)val);
/*  73 */     else if ((val instanceof Short))
/*  74 */       this.boolValue = ((Short)val);
/*     */     else
/*  76 */       this.strValue = val.toString();
/*     */   }
/*     */ 
/*     */   public Object getRawValue()
/*     */   {
/*  84 */     if (this.strValue != null) return this.strValue;
/*  85 */     if (this.intValue != null) return this.intValue;
/*  86 */     if (this.longValue != null) return this.longValue;
/*  87 */     if (this.decValue != null) return this.decValue;
/*  88 */     if (this.dateValue != null) return this.dateValue;
/*  89 */     if (this.boolValue != null) return this.boolValue;
/*  90 */     if (this.textValue != null) return this.textValue;
/*  91 */     return this.strValue;
/*     */   }
/*     */ 
/*     */   public void copyValue(ParamField pf) {
/*  95 */     this.fieldLabel = pf.getLabel();
/*  96 */     this.fieldName = pf.getName();
/*  97 */     this.fieldType = pf.getType();
/*  98 */     this.isShowed = pf.getIsShowed();
/*     */ 
/* 100 */     setValue(pf.getValue(), pf.getType());
/*     */   }
/*     */ 
/*     */   public Object getValue()
/*     */   {
/* 105 */     if (this.strValue != null) return this.strValue;
/* 106 */     if (this.intValue != null) return this.intValue;
/* 107 */     if (this.longValue != null) return this.longValue;
/* 108 */     if (this.decValue != null) return this.decValue;
/* 109 */     if (this.dateValue != null) return this.dateValue;
/* 110 */     if (this.boolValue != null) return this.boolValue;
/* 111 */     if (this.textValue != null) return this.textValue;
/* 112 */     return null;
/*     */   }
/*     */ 
/*     */   public String getVal()
/*     */   {
/* 121 */     if ("varchar".equals(this.fieldType)) {
/* 122 */       return this.strValue;
/*     */     }
/*     */ 
/* 125 */     if ("date".equals(this.fieldType)) {
/* 126 */       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/* 127 */       return this.dateValue == null ? null : sdf.format(this.dateValue);
/*     */     }
/*     */ 
/* 130 */     if ("datetime".equals(this.fieldType)) {
/* 131 */       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/* 132 */       return this.dateValue == null ? null : sdf.format(this.dateValue);
/*     */     }
/*     */ 
/* 135 */     if ("int".equals(this.fieldType)) {
/* 136 */       return this.intValue == null ? null : this.intValue.toString();
/*     */     }
/*     */ 
/* 139 */     if ("long".equals(this.fieldType)) {
/* 140 */       return this.longValue == null ? null : this.longValue.toString();
/*     */     }
/*     */ 
/* 143 */     if ("decimal".equals(this.fieldType)) {
/* 144 */       return this.decValue == null ? null : this.decValue.toString();
/*     */     }
/*     */ 
/* 147 */     if ("text".equals(this.fieldType)) {
/* 148 */       return this.textValue;
/*     */     }
/*     */ 
/* 151 */     if ("file".equals(this.fieldType)) {
/* 152 */       return this.strValue;
/*     */     }
/*     */ 
/* 155 */     if ("bool".equals(this.fieldType)) {
/* 156 */       return this.boolValue.shortValue() == 1 ? "是" : "否";
/*     */     }
/*     */ 
/* 159 */     return null;
/*     */   }
/*     */ 
/*     */   public void setValue(String val, String type)
/*     */   {
/* 164 */     if (val == null) return;
/*     */     try
/*     */     {
/* 167 */       if ("varchar".equals(type))
/* 168 */         this.strValue = val;
/* 169 */       else if ("bool".equals(type))
/* 170 */         this.boolValue = Short.valueOf((short) ("1".equals(val) ? 1 : 0));
/* 171 */       else if (("date".equals(type)) || ("datetime".equals(type)))
/* 172 */         this.dateValue = DateUtils.parseDate(val, new String[] { "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd" });
/* 173 */       else if ("decimal".equals(type))
/* 174 */         this.decValue = new BigDecimal(val);
/* 175 */       else if ("int".equals(type))
/* 176 */         this.intValue = new Integer(val);
/* 177 */       else if ("long".equals(type))
/* 178 */         this.longValue = new Long(val);
/* 179 */       else if ("text".equals(type))
/* 180 */         this.textValue = val;
/* 181 */       else if ("file".equals(type))
/* 182 */         this.strValue = val;
/*     */     }
/*     */     catch (Exception ex) {
/* 185 */       this.logger.warn("setValue error:" + ex.getMessage());
/*     */     }
/*     */   }
/*     */ 
/*     */   public RunData()
/*     */   {
/*     */   }
/*     */ 
/*     */   public RunData(Long in_dataId)
/*     */   {
/* 203 */     setDataId(in_dataId);
/*     */   }
/*     */ 
/*     */   public ProcessRun getProcessRun() {
/* 207 */     return this.processRun;
/*     */   }
/*     */ 
/*     */   public void setProcessRun(ProcessRun processRun) {
/* 211 */     this.processRun = processRun;
/*     */   }
/*     */ 
/*     */   public Long getDataId()
/*     */   {
/* 219 */     return this.dataId;
/*     */   }
/*     */ 
/*     */   public void setDataId(Long aValue)
/*     */   {
/* 226 */     this.dataId = aValue;
/*     */   }
/*     */   public Short getBoolValue() {
/* 229 */     return this.boolValue;
/*     */   }
/*     */ 
/*     */   public void setBoolValue(Short boolValue) {
/* 233 */     this.boolValue = boolValue;
/*     */   }
/*     */ 
/*     */   public String getFieldLabel()
/*     */   {
/* 241 */     return this.fieldLabel;
/*     */   }
/*     */ 
/*     */   public void setFieldLabel(String aValue)
/*     */   {
/* 249 */     this.fieldLabel = aValue;
/*     */   }
/*     */ 
/*     */   public String getFieldName()
/*     */   {
/* 257 */     return this.fieldName;
/*     */   }
/*     */ 
/*     */   public void setFieldName(String aValue)
/*     */   {
/* 265 */     this.fieldName = aValue;
/*     */   }
/*     */ 
/*     */   public Integer getIntValue()
/*     */   {
/* 273 */     return this.intValue;
/*     */   }
/*     */ 
/*     */   public void setIntValue(Integer aValue)
/*     */   {
/* 280 */     this.intValue = aValue;
/*     */   }
/*     */ 
/*     */   public BigDecimal getDecValue()
/*     */   {
/* 288 */     return this.decValue;
/*     */   }
/*     */ 
/*     */   public void setDecValue(BigDecimal aValue)
/*     */   {
/* 295 */     this.decValue = aValue;
/*     */   }
/*     */ 
/*     */   public Date getDateValue()
/*     */   {
/* 303 */     return this.dateValue;
/*     */   }
/*     */ 
/*     */   public void setDateValue(Date aValue)
/*     */   {
/* 310 */     this.dateValue = aValue;
/*     */   }
/*     */ 
/*     */   public String getStrValue()
/*     */   {
/* 318 */     return this.strValue;
/*     */   }
/*     */ 
/*     */   public void setStrValue(String aValue)
/*     */   {
/* 325 */     this.strValue = aValue;
/*     */   }
/*     */ 
/*     */   public String getBlobValue()
/*     */   {
/* 333 */     return this.blobValue;
/*     */   }
/*     */ 
/*     */   public void setBlobValue(String aValue)
/*     */   {
/* 340 */     this.blobValue = aValue;
/*     */   }
/*     */ 
/*     */   public Short getIsShowed()
/*     */   {
/* 350 */     return this.isShowed;
/*     */   }
/*     */ 
/*     */   public void setIsShowed(Short aValue)
/*     */   {
/* 358 */     this.isShowed = aValue;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 365 */     if (!(object instanceof RunData)) {
/* 366 */       return false;
/*     */     }
/* 368 */     RunData rhs = (RunData)object;
/* 369 */     return new EqualsBuilder()
/* 370 */       .append(this.dataId, rhs.dataId)
/* 371 */       .append(this.fieldLabel, rhs.fieldLabel)
/* 372 */       .append(this.fieldName, rhs.fieldName)
/* 373 */       .append(this.intValue, rhs.intValue)
/* 374 */       .append(this.decValue, rhs.decValue)
/* 375 */       .append(this.dateValue, rhs.dateValue)
/* 376 */       .append(this.strValue, rhs.strValue)
/* 377 */       .append(this.blobValue, rhs.blobValue)
/* 378 */       .append(this.isShowed, rhs.isShowed)
/* 379 */       .isEquals();
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 386 */     return new HashCodeBuilder(-82280557, -700257973)
/* 387 */       .append(this.dataId)
/* 388 */       .append(this.fieldLabel)
/* 389 */       .append(this.fieldName)
/* 390 */       .append(this.intValue)
/* 391 */       .append(this.decValue)
/* 392 */       .append(this.dateValue)
/* 393 */       .append(this.strValue)
/* 394 */       .append(this.blobValue)
/* 395 */       .append(this.isShowed)
/* 396 */       .toHashCode();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 403 */     return new ToStringBuilder(this)
/* 404 */       .append("dataId", this.dataId)
/* 405 */       .append("fieldLabel", this.fieldLabel)
/* 406 */       .append("fieldName", this.fieldName)
/* 407 */       .append("intValue", this.intValue)
/* 408 */       .append("decValue", this.decValue)
/* 409 */       .append("dateValue", this.dateValue)
/* 410 */       .append("strValue", this.strValue)
/* 411 */       .append("blobValue", this.blobValue)
/* 412 */       .append("isShowed", this.isShowed)
/* 413 */       .toString();
/*     */   }
/*     */ 
/*     */   public Long getLongValue() {
/* 417 */     return this.longValue;
/*     */   }
/*     */ 
/*     */   public void setLongValue(Long longValue) {
/* 421 */     this.longValue = longValue;
/*     */   }
/*     */ 
/*     */   public String getTextValue() {
/* 425 */     return this.textValue;
/*     */   }
/*     */ 
/*     */   public void setTextValue(String textValue) {
/* 429 */     this.textValue = textValue;
/*     */   }
/*     */ 
/*     */   public String getFieldType() {
/* 433 */     return this.fieldType;
/*     */   }
/*     */ 
/*     */   public void setFieldType(String fieldType) {
/* 437 */     this.fieldType = fieldType;
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.flow.RunData
 * JD-Core Version:    0.6.0
 */