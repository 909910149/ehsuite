/*     */ package com.htsoft.oa.model.flow;
/*     */ 
/*     */ import com.google.gson.annotations.Expose;
/*     */ import com.htsoft.core.model.BaseModel;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang.builder.EqualsBuilder;
/*     */ import org.apache.commons.lang.builder.HashCodeBuilder;
/*     */ import org.apache.commons.lang.builder.ToStringBuilder;
/*     */ 
/*     */ public class FormTable extends BaseModel
/*     */ {
/*     */   public static final String TABLE_PRE_NAME = "WF_";
/*  31 */   public static final Short MAIN_TABLE = Short.valueOf((short)1);
/*     */ 
/*  35 */   public static final Short NOT_MAIN_TABLE = Short.valueOf((short)0);
/*     */ 
/*     */   @Expose
/*     */   protected Long tableId;
/*     */ 
/*     */   @Expose
/*     */   protected String tableName;
/*     */ 
/*     */   @Expose
/*     */   protected String tableKey;
/*     */ 
/*     */   @Expose
/*     */   protected Short isMain;
/*     */   protected transient FormDef formDef;
/*     */ 
/*     */   @Expose
/*  47 */   protected Set<FormField> formFields = new HashSet();
/*     */ 
/*     */   public FormField getPrimaryField()
/*     */   {
/*  55 */     Iterator it = this.formFields.iterator();
/*  56 */     while (it.hasNext()) {
/*  57 */       FormField formField = (FormField)it.next();
/*  58 */       if (FormField.PRIMARY_KEY.equals(formField.getIsPrimary())) {
/*  59 */         return formField;
/*     */       }
/*     */     }
/*  62 */     return null;
/*     */   }
/*     */ 
/*     */   public FormField getFlowTitleField()
/*     */   {
/*  71 */     Iterator it = this.formFields.iterator();
/*  72 */     while (it.hasNext()) {
/*  73 */       FormField formField = (FormField)it.next();
/*  74 */       if (FormField.FLOW_TITLE.equals(formField.getIsFlowTitle())) {
/*  75 */         return formField;
/*     */       }
/*     */     }
/*  78 */     return null;
/*     */   }
/*     */ 
/*     */   public String getEntityName()
/*     */   {
/*  87 */     return "WF_" + this.tableKey;
/*     */   }
/*     */ 
/*     */   public FormTable()
/*     */   {
/*     */   }
/*     */ 
/*     */   public FormTable(Long in_tableId)
/*     */   {
/* 105 */     setTableId(in_tableId);
/*     */   }
/*     */ 
/*     */   public FormDef getFormDef() {
/* 109 */     return this.formDef;
/*     */   }
/*     */ 
/*     */   public void setFormDef(FormDef in_formDef) {
/* 113 */     this.formDef = in_formDef;
/*     */   }
/*     */ 
/*     */   public Set<FormField> getFormFields() {
/* 117 */     return this.formFields;
/*     */   }
/*     */ 
/*     */   public void setFormFields(Set<FormField> in_formFields) {
/* 121 */     this.formFields = in_formFields;
/*     */   }
/*     */ 
/*     */   public Long getTableId()
/*     */   {
/* 131 */     return this.tableId;
/*     */   }
/*     */ 
/*     */   public void setTableId(Long aValue)
/*     */   {
/* 138 */     this.tableId = aValue;
/*     */   }
/*     */ 
/*     */   public Long getFormDefId()
/*     */   {
/* 145 */     return getFormDef() == null ? null : getFormDef()
/* 146 */       .getFormDefId();
/*     */   }
/*     */ 
/*     */   public void setFormDefId(Long aValue)
/*     */   {
/* 153 */     if (aValue == null) {
/* 154 */       this.formDef = null;
/* 155 */     } else if (this.formDef == null) {
/* 156 */       this.formDef = new FormDef(aValue);
/* 157 */       this.formDef.setVersion(new Integer(0));
/*     */     }
/*     */     else
/*     */     {
/* 161 */       this.formDef.setFormDefId(aValue);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getTableName()
/*     */   {
/* 172 */     return this.tableName;
/*     */   }
/*     */ 
/*     */   public void setTableName(String aValue)
/*     */   {
/* 181 */     this.tableName = aValue;
/*     */   }
/*     */ 
/*     */   public String getTableKey()
/*     */   {
/* 191 */     return this.tableKey;
/*     */   }
/*     */ 
/*     */   public void setTableKey(String aValue)
/*     */   {
/* 200 */     this.tableKey = aValue;
/*     */   }
/*     */ 
/*     */   public Short getIsMain() {
/* 204 */     return this.isMain;
/*     */   }
/*     */ 
/*     */   public void setIsMain(Short isMain) {
/* 208 */     this.isMain = isMain;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 215 */     if (!(object instanceof FormTable)) {
/* 216 */       return false;
/*     */     }
/* 218 */     FormTable rhs = (FormTable)object;
/* 219 */     return new EqualsBuilder().append(this.tableId, rhs.tableId).append(
/* 220 */       this.tableName, rhs.tableName).append(this.tableKey, 
/* 221 */       rhs.tableKey).isEquals();
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 228 */     return new HashCodeBuilder(-82280557, -700257973).append(this.tableId)
/* 229 */       .append(this.tableName).append(this.tableKey).toHashCode();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 236 */     return new ToStringBuilder(this).append("tableId", this.tableId)
/* 237 */       .append("tableName", this.tableName).append("tableKey", 
/* 238 */       this.tableKey).toString();
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.flow.FormTable
 * JD-Core Version:    0.6.0
 */