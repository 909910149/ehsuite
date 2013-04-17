/*     */ package com.htsoft.oa.model.flow;
/*     */ 
/*     */ import com.htsoft.core.model.BaseModel;
/*     */ import java.util.Date;
/*     */ import org.apache.commons.lang.builder.EqualsBuilder;
/*     */ import org.apache.commons.lang.builder.HashCodeBuilder;
/*     */ import org.apache.commons.lang.builder.ToStringBuilder;
/*     */ 
/*     */ public class ProcessModule extends BaseModel
/*     */ {
/*     */   protected Long moduleid;
/*     */   protected String modulename;
/*     */   protected String modulekey;
/*     */   protected String descp;
/*     */   protected String processkey;
/*     */   protected String creator;
/*     */   protected Date createtime;
/*     */   protected ProDefinition proDefinition;
/*     */ 
/*     */   public ProcessModule()
/*     */   {
/*     */   }
/*     */ 
/*     */   public ProcessModule(Long in_moduleid)
/*     */   {
/*  42 */     setModuleid(in_moduleid);
/*     */   }
/*     */ 
/*     */   public ProDefinition getProDefinition()
/*     */   {
/*  47 */     return this.proDefinition;
/*     */   }
/*     */ 
/*     */   public void setProDefinition(ProDefinition in_proDefinition) {
/*  51 */     this.proDefinition = in_proDefinition;
/*     */   }
/*     */ 
/*     */   public Long getModuleid()
/*     */   {
/*  60 */     return this.moduleid;
/*     */   }
/*     */ 
/*     */   public void setModuleid(Long aValue)
/*     */   {
/*  67 */     this.moduleid = aValue;
/*     */   }
/*     */ 
/*     */   public String getModulename()
/*     */   {
/*  75 */     return this.modulename;
/*     */   }
/*     */ 
/*     */   public void setModulename(String aValue)
/*     */   {
/*  83 */     this.modulename = aValue;
/*     */   }
/*     */ 
/*     */   public String getModulekey()
/*     */   {
/*  91 */     return this.modulekey;
/*     */   }
/*     */ 
/*     */   public void setModulekey(String aValue)
/*     */   {
/*  99 */     this.modulekey = aValue;
/*     */   }
/*     */ 
/*     */   public String getDescp()
/*     */   {
/* 107 */     return this.descp;
/*     */   }
/*     */ 
/*     */   public void setDescp(String aValue)
/*     */   {
/* 114 */     this.descp = aValue;
/*     */   }
/*     */ 
/*     */   public Long getDefId()
/*     */   {
/* 121 */     return getProDefinition() == null ? null : getProDefinition().getDefId();
/*     */   }
/*     */ 
/*     */   public void setDefId(Long aValue)
/*     */   {
/* 128 */     if (aValue == null) {
/* 129 */       this.proDefinition = null;
/* 130 */     } else if (this.proDefinition == null) {
/* 131 */       this.proDefinition = new ProDefinition(aValue);
/* 132 */       this.proDefinition.setVersion(new Integer(0));
/*     */     }
/*     */     else {
/* 135 */       this.proDefinition.setDefId(aValue);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getProcesskey()
/*     */   {
/* 144 */     return this.processkey;
/*     */   }
/*     */ 
/*     */   public void setProcesskey(String aValue)
/*     */   {
/* 151 */     this.processkey = aValue;
/*     */   }
/*     */ 
/*     */   public String getCreator()
/*     */   {
/* 159 */     return this.creator;
/*     */   }
/*     */ 
/*     */   public void setCreator(String aValue)
/*     */   {
/* 166 */     this.creator = aValue;
/*     */   }
/*     */ 
/*     */   public Date getCreatetime()
/*     */   {
/* 174 */     return this.createtime;
/*     */   }
/*     */ 
/*     */   public void setCreatetime(Date aValue)
/*     */   {
/* 181 */     this.createtime = aValue;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 188 */     if (!(object instanceof ProcessModule)) {
/* 189 */       return false;
/*     */     }
/* 191 */     ProcessModule rhs = (ProcessModule)object;
/* 192 */     return new EqualsBuilder()
/* 193 */       .append(this.moduleid, rhs.moduleid)
/* 194 */       .append(this.modulename, rhs.modulename)
/* 195 */       .append(this.modulekey, rhs.modulekey)
/* 196 */       .append(this.descp, rhs.descp)
/* 197 */       .append(this.processkey, rhs.processkey)
/* 198 */       .append(this.creator, rhs.creator)
/* 199 */       .append(this.createtime, rhs.createtime)
/* 200 */       .isEquals();
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 207 */     return new HashCodeBuilder(-82280557, -700257973)
/* 208 */       .append(this.moduleid)
/* 209 */       .append(this.modulename)
/* 210 */       .append(this.modulekey)
/* 211 */       .append(this.descp)
/* 212 */       .append(this.processkey)
/* 213 */       .append(this.creator)
/* 214 */       .append(this.createtime)
/* 215 */       .toHashCode();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 222 */     return new ToStringBuilder(this)
/* 223 */       .append("moduleid", this.moduleid)
/* 224 */       .append("modulename", this.modulename)
/* 225 */       .append("modulekey", this.modulekey)
/* 226 */       .append("descp", this.descp)
/* 227 */       .append("processkey", this.processkey)
/* 228 */       .append("creator", this.creator)
/* 229 */       .append("createtime", this.createtime)
/* 230 */       .toString();
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.flow.ProcessModule
 * JD-Core Version:    0.6.0
 */