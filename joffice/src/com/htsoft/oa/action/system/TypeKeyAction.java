/*     */ package com.htsoft.oa.action.system;
/*     */ 
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.reflect.TypeToken;
/*     */ import com.htsoft.core.command.QueryFilter;
/*     */ import com.htsoft.core.web.action.BaseAction;
/*     */ import com.htsoft.core.web.paging.PagingBean;
/*     */ import com.htsoft.oa.model.system.TypeKey;
/*     */ import com.htsoft.oa.service.system.TypeKeyService;
/*     */ import java.lang.reflect.Type;
/*     */ import java.util.List;
/*     */ import javax.annotation.Resource;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class TypeKeyAction extends BaseAction
/*     */ {
/*     */ 
/*     */   @Resource
/*     */   private TypeKeyService typeKeyService;
/*     */   private TypeKey typeKey;
/*     */   private Long typekeyId;
/*     */ 
/*     */   public Long getTypekeyId()
/*     */   {
/*  32 */     return this.typekeyId;
/*     */   }
/*     */ 
/*     */   public void setTypekeyId(Long typekeyId) {
/*  36 */     this.typekeyId = typekeyId;
/*     */   }
/*     */ 
/*     */   public TypeKey getTypeKey() {
/*  40 */     return this.typeKey;
/*     */   }
/*     */ 
/*     */   public void setTypeKey(TypeKey typeKey) {
/*  44 */     this.typeKey = typeKey;
/*     */   }
/*     */ 
/*     */   public String list()
/*     */   {
/*  52 */     QueryFilter filter = new QueryFilter(getRequest());
/*  53 */     List list = this.typeKeyService.getAll(filter);
/*     */ 
/*  55 */     Type type = new TypeToken() {  }
/*  55 */     .getType();
/*  56 */     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
/*  57 */       .append(filter.getPagingBean().getTotalItems()).append(",result:");
/*     */ 
/*  59 */     Gson gson = new Gson();
/*  60 */     buff.append(gson.toJson(list, type));
/*  61 */     buff.append("}");
/*     */ 
/*  63 */     this.jsonString = buff.toString();
/*     */ 
/*  65 */     return "success";
/*     */   }
/*     */ 
/*     */   public String multiDel()
/*     */   {
/*  73 */     String[] ids = getRequest().getParameterValues("ids");
/*  74 */     if (ids != null) {
/*  75 */       for (String id : ids) {
/*  76 */         this.typeKeyService.remove(new Long(id));
/*     */       }
/*     */     }
/*     */ 
/*  80 */     this.jsonString = "{success:true}";
/*     */ 
/*  82 */     return "success";
/*     */   }
/*     */ 
/*     */   public String get()
/*     */   {
/*  90 */     TypeKey typeKey = (TypeKey)this.typeKeyService.get(this.typekeyId);
/*     */ 
/*  92 */     Gson gson = new Gson();
/*     */ 
/*  94 */     StringBuffer sb = new StringBuffer("{success:true,data:");
/*  95 */     sb.append(gson.toJson(typeKey));
/*  96 */     sb.append("}");
/*  97 */     setJsonString(sb.toString());
/*     */ 
/*  99 */     return "success";
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/* 105 */     this.typeKeyService.save(this.typeKey);
/* 106 */     setJsonString("{success:true}");
/* 107 */     return "success";
/*     */   }
/*     */ 
/*     */   public String combo() {
/* 111 */     List<TypeKey> list = this.typeKeyService.getAll();
/* 112 */     StringBuffer sb = new StringBuffer("[");
/* 113 */     for (TypeKey typeKey : list) {
/* 114 */       if (sb.length() > 1) {
/* 115 */         sb.append(",");
/*     */       }
/* 117 */       sb.append("['").append(typeKey.getTypeKey()).append("','").append(typeKey.getTypeName()).append("(").append(typeKey.getTypeKey()).append(")").append("']");
/*     */     }
/* 119 */     sb.append("]");
/* 120 */     setJsonString(sb.toString());
/* 121 */     return "success";
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.action.system.TypeKeyAction
 * JD-Core Version:    0.6.0
 */