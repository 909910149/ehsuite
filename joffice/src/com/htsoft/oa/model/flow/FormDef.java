/*     */ package com.htsoft.oa.model.flow;
/*     */ 
/*     */ import com.google.gson.annotations.Expose;
/*     */ import com.htsoft.core.model.BaseModel;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang.builder.EqualsBuilder;
/*     */ import org.apache.commons.lang.builder.HashCodeBuilder;
/*     */ import org.apache.commons.lang.builder.ToStringBuilder;
/*     */ 
/*     */ public class FormDef extends BaseModel
/*     */ {
/*  27 */   public static final Long DEFAULT_FLOW_FORMID = Long.valueOf(1L);
/*     */ 
/*  31 */   public static final Short NOT_GEN = Short.valueOf((short)0);
/*     */ 
/*  35 */   public static final Short HAS_GEN = Short.valueOf((short)1);
/*     */ 
/*  39 */   public static final Short HAS_Pub = Short.valueOf((short)1);
/*     */ 
/*  43 */   public static final Short NOT_Pub = Short.valueOf((short)0);
/*     */ 
/*     */   @Expose
/*     */   protected Long formDefId;
/*     */ 
/*     */   @Expose
/*     */   protected String formTitle;
/*     */ 
/*     */   @Expose
/*     */   protected String formDesp;
/*     */ 
/*     */   @Expose
/*     */   protected String defHtml;
/*     */ 
/*     */   @Expose
/*     */   protected Short status;
/*     */ 
/*     */   @Expose
/*     */   protected Short formType;
/*     */ 
/*     */   @Expose
/*     */   protected Short isDefault;
/*     */ 
/*     */   @Expose
/*     */   protected Short isGen;
/*  64 */   protected Set<FormTable> formTables = new HashSet();
/*     */ 
/*     */   public FormTable getMainTable()
/*     */   {
/*  71 */     Iterator it = this.formTables.iterator();
/*  72 */     while (it.hasNext()) {
/*  73 */       FormTable formTable = (FormTable)it.next();
/*  74 */       if (FormTable.MAIN_TABLE.equals(formTable.getIsMain())) {
/*  75 */         return formTable;
/*     */       }
/*     */     }
/*  78 */     return null;
/*     */   }
/*     */ 
/*     */   public FormTable getSubTable()
/*     */   {
/*  86 */     Iterator it = this.formTables.iterator();
/*  87 */     while (it.hasNext()) {
/*  88 */       FormTable formTable = (FormTable)it.next();
/*  89 */       if (!FormTable.MAIN_TABLE.equals(formTable.getIsMain())) {
/*  90 */         return formTable;
/*     */       }
/*     */     }
/*  93 */     return null;
/*     */   }
/*     */ 
/*     */   public List<FormTable> getSubTables()
/*     */   {
/*  99 */     Iterator it = this.formTables.iterator();
/* 100 */     List formTables = new ArrayList();
/* 101 */     while (it.hasNext()) {
/* 102 */       FormTable formTable = (FormTable)it.next();
/* 103 */       if (!FormTable.MAIN_TABLE.equals(formTable.getIsMain())) {
/* 104 */         formTables.add(formTable);
/*     */       }
/*     */     }
/* 107 */     return formTables;
/*     */   }
/*     */ 
/*     */   public FormDef()
/*     */   {
/*     */   }
/*     */ 
/*     */   public FormDef(Long in_formDefId)
/*     */   {
/* 122 */     setFormDefId(in_formDefId);
/*     */   }
/*     */ 
/*     */   public Set getFormTables()
/*     */   {
/* 127 */     return this.formTables;
/*     */   }
/*     */ 
/*     */   public void setFormTables(Set in_formTables) {
/* 131 */     this.formTables = in_formTables;
/*     */   }
/*     */ 
/*     */   public Long getFormDefId()
/*     */   {
/* 140 */     return this.formDefId;
/*     */   }
/*     */ 
/*     */   public void setFormDefId(Long aValue)
/*     */   {
/* 147 */     this.formDefId = aValue;
/*     */   }
/*     */ 
/*     */   public String getFormTitle()
/*     */   {
/* 155 */     return this.formTitle;
/*     */   }
/*     */ 
/*     */   public void setFormTitle(String aValue)
/*     */   {
/* 163 */     this.formTitle = aValue;
/*     */   }
/*     */ 
/*     */   public String getFormDesp()
/*     */   {
/* 171 */     return this.formDesp;
/*     */   }
/*     */ 
/*     */   public void setFormDesp(String aValue)
/*     */   {
/* 178 */     this.formDesp = aValue;
/*     */   }
/*     */ 
/*     */   public String getDefHtml()
/*     */   {
/* 186 */     return this.defHtml;
/*     */   }
/*     */ 
/*     */   public void setDefHtml(String aValue)
/*     */   {
/* 193 */     this.defHtml = aValue;
/*     */   }
/*     */ 
/*     */   public Short getStatus()
/*     */   {
/* 202 */     return this.status;
/*     */   }
/*     */ 
/*     */   public Short getFormType() {
/* 206 */     return this.formType;
/*     */   }
/*     */ 
/*     */   public void setFormType(Short formType) {
/* 210 */     this.formType = formType;
/*     */   }
/*     */ 
/*     */   public Short getIsDefault() {
/* 214 */     return this.isDefault;
/*     */   }
/*     */ 
/*     */   public void setIsDefault(Short isDefault) {
/* 218 */     this.isDefault = isDefault;
/*     */   }
/*     */ 
/*     */   public void setStatus(Short aValue)
/*     */   {
/* 226 */     this.status = aValue;
/*     */   }
/*     */   public Short getIsGen() {
/* 229 */     return this.isGen;
/*     */   }
/*     */   public void setIsGen(Short isGen) {
/* 232 */     this.isGen = isGen;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 238 */     if (!(object instanceof FormDef)) {
/* 239 */       return false;
/*     */     }
/* 241 */     FormDef rhs = (FormDef)object;
/* 242 */     return new EqualsBuilder()
/* 243 */       .append(this.formDefId, rhs.formDefId)
/* 244 */       .append(this.formTitle, rhs.formTitle)
/* 245 */       .append(this.formDesp, rhs.formDesp)
/* 246 */       .append(this.defHtml, rhs.defHtml)
/* 247 */       .append(this.status, rhs.status)
/* 248 */       .isEquals();
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 255 */     return new HashCodeBuilder(-82280557, -700257973)
/* 256 */       .append(this.formDefId)
/* 257 */       .append(this.formTitle)
/* 258 */       .append(this.formDesp)
/* 259 */       .append(this.defHtml)
/* 260 */       .append(this.status)
/* 261 */       .toHashCode();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 268 */     return new ToStringBuilder(this)
/* 269 */       .append("formDefId", this.formDefId)
/* 270 */       .append("formTitle", this.formTitle)
/* 271 */       .append("formDesp", this.formDesp)
/* 272 */       .append("defHtml", this.defHtml)
/* 273 */       .append("status", this.status)
/* 274 */       .toString();
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.flow.FormDef
 * JD-Core Version:    0.6.0
 */