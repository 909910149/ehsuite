/*     */ package com.htsoft.oa.model.flow;
/*     */ 
/*     */ import com.htsoft.core.model.BaseModel;
/*     */ import com.htsoft.oa.model.system.GlobalType;
/*     */ import flexjson.JSON;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class ProDefinition extends BaseModel
/*     */ {
/*  22 */   public static final Short STATUS_ENABLE = Short.valueOf((short)1);
/*     */ 
/*  26 */   public static final Short STATUS_DISABLE = Short.valueOf((short)0);
/*     */ 
/*  28 */   public static final Short IS_DEFAULT = Short.valueOf((short)1);
/*  29 */   public static final Short IS_NOT_DEFAULT = Short.valueOf((short)0);
/*     */   protected Long defId;
/*     */   protected String processName;
/*     */   protected String name;
/*     */   protected String description;
/*     */   protected Date createtime;
/*     */   protected String deployId;
/*     */   protected String defXml;
/*     */   protected Integer newVersion;
/*     */   protected String drawDefXml;
/*     */   protected Short status;
/*  43 */   protected Short isDefault = IS_NOT_DEFAULT;
/*     */   protected GlobalType proType;
/*     */ 
/*     */   @JSON
/*     */   public String getDefXml()
/*     */   {
/*  49 */     return this.defXml;
/*     */   }
/*     */ 
/*     */   public void setDefXml(String defXml) {
/*  53 */     this.defXml = defXml;
/*     */   }
/*     */ 
/*     */   public ProDefinition()
/*     */   {
/*     */   }
/*     */ 
/*     */   public ProDefinition(Long in_defId)
/*     */   {
/*  69 */     setDefId(in_defId);
/*     */   }
/*     */ 
/*     */   public GlobalType getProType()
/*     */   {
/*  74 */     return this.proType;
/*     */   }
/*     */ 
/*     */   public void setProType(GlobalType in_proType) {
/*  78 */     this.proType = in_proType;
/*     */   }
/*     */ 
/*     */   public void setProTypeId(Long proTypeId) {
/*  82 */     if (this.proType == null) {
/*  83 */       this.proType = new GlobalType();
/*     */     }
/*  85 */     this.proType.setProTypeId(proTypeId);
/*     */   }
/*     */ 
/*     */   public Long getProTypeId() {
/*  89 */     return this.proType == null ? null : this.proType.getProTypeId();
/*     */   }
/*     */ 
/*     */   public Long getDefId()
/*     */   {
/*  97 */     return this.defId;
/*     */   }
/*     */ 
/*     */   public void setDefId(Long aValue)
/*     */   {
/* 104 */     this.defId = aValue;
/*     */   }
/*     */ 
/*     */   public Long getTypeId()
/*     */   {
/* 111 */     return getProType() == null ? null : getProType().getProTypeId();
/*     */   }
/*     */ 
/*     */   public void setTypeId(Long aValue)
/*     */   {
/* 118 */     if (aValue == null)
/* 119 */       this.proType = null;
/* 120 */     else if (this.proType == null) {
/* 121 */       this.proType = new GlobalType(aValue);
/*     */     }
/*     */     else
/* 124 */       this.proType.setProTypeId(aValue);
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 133 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String aValue)
/*     */   {
/* 141 */     this.name = aValue;
/*     */   }
/*     */ 
/*     */   public String getDescription()
/*     */   {
/* 149 */     return this.description;
/*     */   }
/*     */ 
/*     */   public void setDescription(String aValue)
/*     */   {
/* 156 */     this.description = aValue;
/*     */   }
/*     */ 
/*     */   public Date getCreatetime()
/*     */   {
/* 164 */     return this.createtime;
/*     */   }
/*     */ 
/*     */   public void setCreatetime(Date aValue)
/*     */   {
/* 171 */     this.createtime = aValue;
/*     */   }
/*     */ 
/*     */   public String getDeployId()
/*     */   {
/* 179 */     return this.deployId;
/*     */   }
/*     */ 
/*     */   public void setDeployId(String aValue)
/*     */   {
/* 187 */     this.deployId = aValue;
/*     */   }
/*     */ 
/*     */   public String getDrawDefXml()
/*     */   {
/* 192 */     return this.drawDefXml;
/*     */   }
/*     */ 
/*     */   public void setDrawDefXml(String drawDefXml) {
/* 196 */     this.drawDefXml = drawDefXml;
/*     */   }
/*     */ 
/*     */   public Short getIsDefault()
/*     */   {
/* 202 */     return this.isDefault;
/*     */   }
/*     */ 
/*     */   public String getProcessName() {
/* 206 */     return this.processName;
/*     */   }
/*     */ 
/*     */   public void setProcessName(String processName) {
/* 210 */     this.processName = processName;
/*     */   }
/*     */ 
/*     */   public Integer getNewVersion() {
/* 214 */     return this.newVersion;
/*     */   }
/*     */ 
/*     */   public void setNewVersion(Integer newVersion) {
/* 218 */     this.newVersion = newVersion;
/*     */   }
/*     */ 
/*     */   public void setIsDefault(Short isDefault) {
/* 222 */     this.isDefault = isDefault;
/*     */   }
/*     */ 
/*     */   public Short getStatus() {
/* 226 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(Short status) {
/* 230 */     this.status = status;
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.flow.ProDefinition
 * JD-Core Version:    0.6.0
 */