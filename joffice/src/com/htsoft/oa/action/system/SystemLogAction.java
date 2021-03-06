/*     */ package com.htsoft.oa.action.system;
/*     */ 
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.reflect.TypeToken;
/*     */ import com.htsoft.core.command.QueryFilter;
/*     */ import com.htsoft.core.web.action.BaseAction;
/*     */ import com.htsoft.core.web.paging.PagingBean;
/*     */ import com.htsoft.oa.model.system.SystemLog;
/*     */ import com.htsoft.oa.service.system.SystemLogService;
/*     */ import java.lang.reflect.Type;
/*     */ import java.util.List;
/*     */ import javax.annotation.Resource;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class SystemLogAction extends BaseAction
/*     */ {
/*     */ 
/*     */   @Resource
/*     */   private SystemLogService systemLogService;
/*     */   private SystemLog systemLog;
/*     */   private Long logId;
/*     */ 
/*     */   public Long getLogId()
/*     */   {
/*  32 */     return this.logId;
/*     */   }
/*     */ 
/*     */   public void setLogId(Long logId) {
/*  36 */     this.logId = logId;
/*     */   }
/*     */ 
/*     */   public SystemLog getSystemLog() {
/*  40 */     return this.systemLog;
/*     */   }
/*     */ 
/*     */   public void setSystemLog(SystemLog systemLog) {
/*  44 */     this.systemLog = systemLog;
/*     */   }
/*     */ 
/*     */   public String list()
/*     */   {
/*  52 */     QueryFilter filter = new QueryFilter(getRequest());
/*  53 */     List list = this.systemLogService.getAll(filter);
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
/*  76 */         this.systemLogService.remove(new Long(id));
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
/*  90 */     SystemLog systemLog = (SystemLog)this.systemLogService.get(this.logId);
/*     */ 
/*  92 */     Gson gson = new Gson();
/*     */ 
/*  94 */     StringBuffer sb = new StringBuffer("{success:true,data:");
/*  95 */     sb.append(gson.toJson(systemLog));
/*  96 */     sb.append("}");
/*  97 */     setJsonString(sb.toString());
/*     */ 
/*  99 */     return "success";
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/* 105 */     this.systemLogService.save(this.systemLog);
/* 106 */     setJsonString("{success:true}");
/* 107 */     return "success";
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.action.system.SystemLogAction
 * JD-Core Version:    0.6.0
 */