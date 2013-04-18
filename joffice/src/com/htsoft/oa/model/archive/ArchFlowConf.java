/*     */ package com.htsoft.oa.model.archive;
/*     */ 
/*     */ import com.htsoft.core.model.BaseModel;
/*     */ import org.apache.commons.lang.builder.EqualsBuilder;
/*     */ import org.apache.commons.lang.builder.HashCodeBuilder;
/*     */ import org.apache.commons.lang.builder.ToStringBuilder;
/*     */ 
/*     */ public class ArchFlowConf extends BaseModel
/*     */ {
/*  19 */   public static Short ARCH_SEND_TYPE = Short.valueOf((short)0);
/*  20 */   public static Short ARCH_REC_TYPE = Short.valueOf((short)1);
/*     */   protected Long configId;
/*     */   protected String processName;
/*     */   protected Long defId;
/*     */   protected Short archType;
/*     */   protected Long depId;
/*     */ 
/*     */   public ArchFlowConf()
/*     */   {
/*     */   }
/*     */ 
/*     */   public ArchFlowConf(Long in_configId)
/*     */   {
/*  41 */     setConfigId(in_configId);
/*     */   }
/*     */ 
/*     */   public Long getConfigId()
/*     */   {
/*  51 */     return this.configId;
/*     */   }
/*     */ 
/*     */   public void setConfigId(Long aValue)
/*     */   {
/*  58 */     this.configId = aValue;
/*     */   }
/*     */ 
/*     */   public String getProcessName()
/*     */   {
/*  66 */     return this.processName;
/*     */   }
/*     */ 
/*     */   public void setProcessName(String aValue)
/*     */   {
/*  74 */     this.processName = aValue;
/*     */   }
/*     */ 
/*     */   public Long getDefId()
/*     */   {
/*  82 */     return this.defId;
/*     */   }
/*     */ 
/*     */   public void setDefId(Long aValue)
/*     */   {
/*  89 */     this.defId = aValue;
/*     */   }
/*     */ 
/*     */   public Long getDepId() {
/*  93 */     return this.depId;
/*     */   }
/*     */ 
/*     */   public void setDepId(Long depId) {
/*  97 */     this.depId = depId;
/*     */   }
/*     */ 
/*     */   public Short getArchType()
/*     */   {
/* 105 */     return this.archType;
/*     */   }
/*     */ 
/*     */   public void setArchType(Short aValue)
/*     */   {
/* 112 */     this.archType = aValue;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 119 */     if (!(object instanceof ArchFlowConf)) {
/* 120 */       return false;
/*     */     }
/* 122 */     ArchFlowConf rhs = (ArchFlowConf)object;
/* 123 */     return new EqualsBuilder()
/* 124 */       .append(this.configId, rhs.configId)
/* 125 */       .append(this.processName, rhs.processName)
/* 126 */       .append(this.defId, rhs.defId)
/* 127 */       .append(this.archType, rhs.archType)
/* 128 */       .isEquals();
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 135 */     return new HashCodeBuilder(-82280557, -700257973)
/* 136 */       .append(this.configId)
/* 137 */       .append(this.processName)
/* 138 */       .append(this.defId)
/* 139 */       .append(this.archType)
/* 140 */       .toHashCode();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 147 */     return new ToStringBuilder(this)
/* 148 */       .append("configId", this.configId)
/* 149 */       .append("processName", this.processName)
/* 150 */       .append("processDefId", this.defId)
/* 151 */       .append("archType", this.archType)
/* 152 */       .toString();
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.archive.ArchFlowConf
 * JD-Core Version:    0.6.0
 */