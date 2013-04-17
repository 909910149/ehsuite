/*     */ package com.htsoft.oa.action.info;
/*     */ 
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.GsonBuilder;
/*     */ import com.google.gson.reflect.TypeToken;
/*     */ import com.htsoft.core.command.QueryFilter;
/*     */ import com.htsoft.core.util.BeanUtil;
/*     */ import com.htsoft.core.util.ContextUtil;
/*     */ import com.htsoft.core.web.action.BaseAction;
/*     */ import com.htsoft.core.web.paging.PagingBean;
/*     */ import com.htsoft.oa.model.info.AppTips;
/*     */ import com.htsoft.oa.service.info.AppTipsService;
/*     */ import java.lang.reflect.Type;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import javax.annotation.Resource;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class AppTipsAction extends BaseAction
/*     */ {
/*     */ 
/*     */   @Resource
/*     */   private AppTipsService appTipsService;
/*     */   private AppTips appTips;
/*     */   private Long tipsId;
/*     */ 
/*     */   public Long getTipsId()
/*     */   {
/*  40 */     return this.tipsId;
/*     */   }
/*     */ 
/*     */   public void setTipsId(Long tipsId) {
/*  44 */     this.tipsId = tipsId;
/*     */   }
/*     */ 
/*     */   public AppTips getAppTips() {
/*  48 */     return this.appTips;
/*     */   }
/*     */ 
/*     */   public void setAppTips(AppTips appTips) {
/*  52 */     this.appTips = appTips;
/*     */   }
/*     */ 
/*     */   public String list()
/*     */   {
/*  60 */     QueryFilter filter = new QueryFilter(getRequest());
/*  61 */     filter.addFilter("Q_appUser.userId_L_EQ", ContextUtil.getCurrentUserId().toString());
/*  62 */     List list = this.appTipsService.getAll(filter);
/*     */ 
/*  64 */     Type type = new TypeToken() {  }
/*  64 */     .getType();
/*  65 */     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
/*  66 */       .append(filter.getPagingBean().getTotalItems()).append(",result:");
/*     */ 
/*  68 */     Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
/*  69 */     buff.append(gson.toJson(list, type));
/*  70 */     buff.append("}");
/*     */ 
/*  72 */     this.jsonString = buff.toString();
/*     */ 
/*  74 */     return "success";
/*     */   }
/*     */ 
/*     */   public String multiDel()
/*     */   {
/*  81 */     if (getRequest().getParameter("ids").equals("all")) {
/*  82 */       QueryFilter filter = new QueryFilter(getRequest());
/*  83 */       filter.addFilter("Q_appUser.userId_L_EQ", ContextUtil.getCurrentUserId().toString());
/*  84 */       List<AppTips> list = this.appTipsService.getAll(filter);
/*  85 */       for (AppTips tips : list)
/*  86 */         this.appTipsService.remove(tips);
/*     */     }
/*     */     else {
/*  89 */       String[] ids = getRequest().getParameterValues("ids");
/*  90 */       String[] names = getRequest().getParameterValues("names");
/*  91 */       if ((ids != null) && (names != null)) {
/*  92 */         for (String name : names) {
/*  93 */           AppTips appTips = this.appTipsService.findByName(name);
/*  94 */           if (appTips != null) {
/*  95 */             this.appTipsService.remove(appTips);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 100 */     this.jsonString = "{success:true}";
/*     */ 
/* 102 */     return "success";
/*     */   }
/*     */ 
/*     */   public String get()
/*     */   {
/* 110 */     AppTips appTips = (AppTips)this.appTipsService.get(this.tipsId);
/*     */ 
/* 112 */     Gson gson = new Gson();
/*     */ 
/* 114 */     StringBuffer sb = new StringBuffer("{success:true,data:");
/* 115 */     sb.append(gson.toJson(appTips));
/* 116 */     sb.append("}");
/* 117 */     setJsonString(sb.toString());
/*     */ 
/* 119 */     return "success";
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/* 125 */     String data = getRequest().getParameter("data");
/* 126 */     if (StringUtils.isNotEmpty(data)) {
/* 127 */       Gson gson = new Gson();
/* 128 */       AppTips[] tips = (AppTips[])gson.fromJson(data, com.htsoft.oa.model.info.AppTips[].class);
/* 129 */       for (AppTips tip : tips) {
/* 130 */         if (tip.getTipsId().longValue() == -1L) {
/* 131 */           tip.setTipsId(null);
/* 132 */           tip.setCreateTime(new Date());
/*     */ 
/* 136 */           tip.setDislevel(Integer.valueOf(1));
/* 137 */           tip.setAppUser(ContextUtil.getCurrentUser());
/* 138 */           this.appTipsService.save(tip);
/* 139 */         } else if (tip.getTipsId().longValue() == -2L) {
/* 140 */           AppTips orgTip = this.appTipsService.findByName(tip.getTipsName());
/* 141 */           if (orgTip != null) {
/* 142 */             tip.setTipsId(null);
/*     */             try {
/* 144 */               BeanUtil.copyNotNullProperties(orgTip, tip);
/*     */             } catch (Exception e) {
/* 146 */               e.printStackTrace();
/*     */             }
/* 148 */             orgTip.setDislevel(Integer.valueOf(1));
/* 149 */             orgTip.setAppUser(ContextUtil.getCurrentUser());
/* 150 */             this.appTipsService.save(orgTip);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 156 */     setJsonString("{success:true}");
/* 157 */     return "success";
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.action.info.AppTipsAction
 * JD-Core Version:    0.6.0
 */