/*     */ package com.htsoft.oa.action.system;
/*     */ 
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.reflect.TypeToken;
/*     */ import com.htsoft.core.command.QueryFilter;
/*     */ import com.htsoft.core.util.AppUtil;
/*     */ import com.htsoft.core.web.action.BaseAction;
/*     */ import com.htsoft.core.web.paging.PagingBean;
/*     */ import com.htsoft.oa.model.system.SysConfig;
/*     */ import com.htsoft.oa.service.system.SysConfigService;
/*     */ import flexjson.JSONSerializer;
/*     */ import java.lang.reflect.Type;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Resource;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class SysConfigAction extends BaseAction
/*     */ {
/*     */ 
/*     */   @Resource
/*     */   private SysConfigService sysConfigService;
/*     */   private SysConfig sysConfig;
/*     */   private Long configId;
/*     */ 
/*     */   public Long getConfigId()
/*     */   {
/*  37 */     return this.configId;
/*     */   }
/*     */ 
/*     */   public void setConfigId(Long configId) {
/*  41 */     this.configId = configId;
/*     */   }
/*     */ 
/*     */   public SysConfig getSysConfig() {
/*  45 */     return this.sysConfig;
/*     */   }
/*     */ 
/*     */   public void setSysConfig(SysConfig sysConfig) {
/*  49 */     this.sysConfig = sysConfig;
/*     */   }
/*     */ 
/*     */   public String list()
/*     */   {
/*  57 */     QueryFilter filter = new QueryFilter(getRequest());
/*  58 */     List list = this.sysConfigService.getAll(filter);
/*     */ 
/*  60 */     Type type = new TypeToken() {  }
/*  60 */     .getType();
/*  61 */     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
/*  62 */       .append(filter.getPagingBean().getTotalItems()).append(",result:");
/*     */ 
/*  64 */     Gson gson = new Gson();
/*  65 */     buff.append(gson.toJson(list, type));
/*  66 */     buff.append("}");
/*     */ 
/*  68 */     this.jsonString = buff.toString();
/*     */ 
/*  70 */     return "success";
/*     */   }
/*     */ 
/*     */   public String multiDel()
/*     */   {
/*  78 */     String[] ids = getRequest().getParameterValues("ids");
/*  79 */     if (ids != null) {
/*  80 */       for (String id : ids) {
/*  81 */         this.sysConfigService.remove(new Long(id));
/*     */       }
/*     */     }
/*     */ 
/*  85 */     this.jsonString = "{success:true}";
/*     */ 
/*  87 */     return "success";
/*     */   }
/*     */ 
/*     */   public String get()
/*     */   {
/*  95 */     SysConfig sysConfig = (SysConfig)this.sysConfigService.get(this.configId);
/*     */ 
/*  97 */     Gson gson = new Gson();
/*     */ 
/*  99 */     StringBuffer sb = new StringBuffer("{success:true,data:");
/* 100 */     sb.append(gson.toJson(sysConfig));
/* 101 */     sb.append("}");
/* 102 */     setJsonString(sb.toString());
/*     */ 
/* 104 */     return "success";
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/* 110 */     Map con = AppUtil.getSysConfig();
/* 111 */     Map map = getRequest().getParameterMap();
/* 112 */     Iterator it = map.entrySet().iterator();
/* 113 */     while (it.hasNext()) {
/* 114 */       Map.Entry entry = (Map.Entry)it.next();
/* 115 */       String key = (String)entry.getKey();
/* 116 */       SysConfig conf = this.sysConfigService.findByKey(key);
/* 117 */       String[] value = (String[])entry.getValue();
/* 118 */       conf.setDataValue(value[0]);
/* 119 */       this.sysConfigService.save(conf);
/* 120 */       con.remove(key);
/* 121 */       con.put(key, value[0]);
/*     */     }
/*     */ 
/* 125 */     AppUtil.reloadSysConfig();
/*     */ 
/* 127 */     setJsonString("{success:true}");
/* 128 */     return "success";
/*     */   }
/*     */ 
/*     */   public String load() {
/* 132 */     Map conf = this.sysConfigService.findByType();
/* 133 */     JSONSerializer json = new JSONSerializer();
/* 134 */     setJsonString("{success:true,data:" + json.deepSerialize(conf) + "}");
/* 135 */     return "success";
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.action.system.SysConfigAction
 * JD-Core Version:    0.6.0
 */