/*     */ package com.htsoft.oa.model.flow;
/*     */ 
/*     */ import com.htsoft.core.model.BaseModel;
/*     */ import org.apache.commons.lang.builder.EqualsBuilder;
/*     */ import org.apache.commons.lang.builder.HashCodeBuilder;
/*     */ import org.apache.commons.lang.builder.ToStringBuilder;
/*     */ 
/*     */ public class ProHandleComp extends BaseModel
/*     */ {
/*  21 */   public static final Short HANDLE_TYPE_LISTENER = Short.valueOf((short)1);
/*     */ 
/*  25 */   public static final Short HANDLE_TYPE_HANDLER = Short.valueOf((short)2);
/*     */ 
/*  30 */   public static final Short EVENT_LEVEL_PROCESS = Short.valueOf((short)1);
/*     */ 
/*  34 */   public static final Short EVENT_LEVEL_NODE = Short.valueOf((short)1);
/*     */ 
/*  38 */   public static final Short EVENT_LEVEL_TRANSITION = Short.valueOf((short)1);
/*     */   public static final String EVENT_START = "start";
/*     */   public static final String EVENT_END = "end";
/*     */   protected Long handleId;
/*     */   protected String deployId;
/*     */   protected String activityName;
/*     */   protected String tranName;
/*     */   protected String eventName;
/*     */   protected Short eventLevel;
/*     */   protected String exeCode;
/*     */   protected Short handleType;
/*     */ 
/*     */   public ProHandleComp()
/*     */   {
/*     */   }
/*     */ 
/*     */   public ProHandleComp(Long in_handleId)
/*     */   {
/*  73 */     setHandleId(in_handleId);
/*     */   }
/*     */ 
/*     */   public Long getHandleId()
/*     */   {
/*  83 */     return this.handleId;
/*     */   }
/*     */ 
/*     */   public void setHandleId(Long aValue)
/*     */   {
/*  90 */     this.handleId = aValue;
/*     */   }
/*     */ 
/*     */   public String getDeployId()
/*     */   {
/*  98 */     return this.deployId;
/*     */   }
/*     */ 
/*     */   public void setDeployId(String aValue)
/*     */   {
/* 106 */     this.deployId = aValue;
/*     */   }
/*     */ 
/*     */   public String getActivityName()
/*     */   {
/* 114 */     return this.activityName;
/*     */   }
/*     */ 
/*     */   public void setActivityName(String aValue)
/*     */   {
/* 121 */     this.activityName = aValue;
/*     */   }
/*     */ 
/*     */   public String getTranName()
/*     */   {
/* 129 */     return this.tranName;
/*     */   }
/*     */ 
/*     */   public void setTranName(String aValue)
/*     */   {
/* 136 */     this.tranName = aValue;
/*     */   }
/*     */ 
/*     */   public String getEventName()
/*     */   {
/* 144 */     return this.eventName;
/*     */   }
/*     */ 
/*     */   public void setEventName(String aValue)
/*     */   {
/* 151 */     this.eventName = aValue;
/*     */   }
/*     */ 
/*     */   public Short getEventLevel()
/*     */   {
/* 159 */     return this.eventLevel;
/*     */   }
/*     */ 
/*     */   public void setEventLevel(Short aValue)
/*     */   {
/* 166 */     this.eventLevel = aValue;
/*     */   }
/*     */ 
/*     */   public String getExeCode()
/*     */   {
/* 174 */     return this.exeCode;
/*     */   }
/*     */ 
/*     */   public void setExeCode(String aValue)
/*     */   {
/* 181 */     this.exeCode = aValue;
/*     */   }
/*     */ 
/*     */   public Short getHandleType()
/*     */   {
/* 189 */     return this.handleType;
/*     */   }
/*     */ 
/*     */   public void setHandleType(Short aValue)
/*     */   {
/* 196 */     this.handleType = aValue;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 203 */     if (!(object instanceof ProHandleComp)) {
/* 204 */       return false;
/*     */     }
/* 206 */     ProHandleComp rhs = (ProHandleComp)object;
/* 207 */     return new EqualsBuilder()
/* 208 */       .append(this.handleId, rhs.handleId)
/* 209 */       .append(this.deployId, rhs.deployId)
/* 210 */       .append(this.activityName, rhs.activityName)
/* 211 */       .append(this.tranName, rhs.tranName)
/* 212 */       .append(this.eventName, rhs.eventName)
/* 213 */       .append(this.eventLevel, rhs.eventLevel)
/* 214 */       .append(this.exeCode, rhs.exeCode)
/* 215 */       .append(this.handleType, rhs.handleType)
/* 216 */       .isEquals();
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 223 */     return new HashCodeBuilder(-82280557, -700257973)
/* 224 */       .append(this.handleId)
/* 225 */       .append(this.deployId)
/* 226 */       .append(this.activityName)
/* 227 */       .append(this.tranName)
/* 228 */       .append(this.eventName)
/* 229 */       .append(this.eventLevel)
/* 230 */       .append(this.exeCode)
/* 231 */       .append(this.handleType)
/* 232 */       .toHashCode();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 239 */     return new ToStringBuilder(this)
/* 240 */       .append("handleId", this.handleId)
/* 241 */       .append("deployId", this.deployId)
/* 242 */       .append("activityName", this.activityName)
/* 243 */       .append("tranName", this.tranName)
/* 244 */       .append("eventName", this.eventName)
/* 245 */       .append("eventLevel", this.eventLevel)
/* 246 */       .append("exeCode", this.exeCode)
/* 247 */       .append("handleType", this.handleType)
/* 248 */       .toString();
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.flow.ProHandleComp
 * JD-Core Version:    0.6.0
 */