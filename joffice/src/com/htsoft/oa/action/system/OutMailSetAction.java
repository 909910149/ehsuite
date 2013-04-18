/*     */ package com.htsoft.oa.action.system;
/*     */ 
/*     */ import com.google.gson.Gson;
/*     */ import com.htsoft.core.util.ContextUtil;
/*     */ import com.htsoft.core.web.action.BaseAction;
/*     */ import com.htsoft.core.web.paging.PagingBean;
/*     */ import com.htsoft.oa.model.communicate.OutMailUserSeting;
/*     */ import com.htsoft.oa.model.system.AppUser;
/*     */ import com.htsoft.oa.service.communicate.OutMailUserSetingService;
/*     */ import com.htsoft.oa.service.system.AppUserService;
/*     */ import java.util.List;
/*     */ import javax.annotation.Resource;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class OutMailSetAction extends BaseAction
/*     */ {
/*     */ 
/*     */   @Resource
/*     */   private OutMailUserSetingService outMailUserSetingService;
/*     */ 
/*     */   @Resource
/*     */   private AppUserService appUserService;
/*     */   private AppUser appUser;
/*     */   private OutMailUserSeting outMailUserSeting;
/*     */   private Long id;
/*     */ 
/*     */   public AppUser getAppUser()
/*     */   {
/*  27 */     return this.appUser;
/*     */   }
/*     */ 
/*     */   public void setAppUser(AppUser appUser) {
/*  31 */     this.appUser = appUser;
/*     */   }
/*     */ 
/*     */   public OutMailUserSeting getOutMailUserSeting() {
/*  35 */     return this.outMailUserSeting;
/*     */   }
/*     */ 
/*     */   public void setOutMailUserSeting(OutMailUserSeting outMailUserSeting) {
/*  39 */     this.outMailUserSeting = outMailUserSeting;
/*     */   }
/*     */ 
/*     */   public Long getId()
/*     */   {
/*  45 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Long id) {
/*  49 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public String list()
/*     */   {
/*  57 */     PagingBean pb = new PagingBean(this.start.intValue(), this.limit.intValue());
/*  58 */     String fullname = getRequest().getParameter("userName");
/*     */ 
/*  60 */     if (StringUtils.isNotEmpty(fullname)) {
/*  61 */       List mail = this.outMailUserSetingService
/*  62 */         .findByUserAll(fullname, pb);
/*  63 */       StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
/*  64 */         .append(pb.getTotalItems()).append(",result:[");
/*  65 */       for (int i = 0; i < mail.size(); i++) {
/*  66 */         OutMailUserSeting set = (OutMailUserSeting)mail.get(i);
/*  67 */         if (set != null)
/*  68 */           buff.append("{'id':'" + set.getId() + "','userId':'" + 
/*  69 */             set.getUserId() + "'" + ",'userName':'" + 
/*  70 */             set.getUserName() + "'" + ",'mailAddress':'" + 
/*  71 */             set.getMailAddress() + "'" + ",'mailPass':'" + 
/*  72 */             set.getMailPass() + "','smtpHost':'" + 
/*  73 */             set.getSmtpHost() + "','smtpPort':'" + 
/*  74 */             set.getSmtpPort() + "'" + ",'mailAddress':'" + 
/*  75 */             set.getMailAddress() + "','mailAddress':'" + 
/*  76 */             set.getMailAddress() + "'" + ",'popHost':'" + 
/*  77 */             set.getPopHost() + "','popPort':'" + set.getPopPort() + 
/*  78 */             "'}");
/*     */         else {
/*  80 */           buff.append("{'id':'','userId':'','userName':'','mailAddress':'','mailAddress':'','mailPass':'','smtpHost':'','smtpPort':'','popHost':'','popPort':''}");
/*     */         }
/*     */       }
/*     */ 
/*  84 */       buff.append("]}");
/*  85 */       this.jsonString = buff.toString();
/*     */     }
/*     */     else {
/*  88 */       List list = this.outMailUserSetingService.findByUserAll();
/*  89 */       StringBuffer sb = new StringBuffer("{success:true,'totalCounts':");
/*  90 */       sb.append(list.size()).append(",result:[");
/*  91 */       for (int i = 0; i < list.size(); i++) {
/*  92 */         if (i > 0) {
/*  93 */           sb.append(",");
/*     */         }
/*  95 */         OutMailUserSeting set = (OutMailUserSeting)((java.lang.Object[])list.get(i))[0];
/*  96 */         AppUser user = (AppUser)((java.lang.Object[])list.get(i))[1];
/*  97 */         sb.append("{'userId':'" + user.getUserId() + "','userName':'" + 
/*  98 */           user.getFullname() + "',");
/*  99 */         if (set != null)
/* 100 */           sb.append("'id':'" + set.getId() + "','mailAddress':'" + 
/* 101 */             set.getMailAddress() + "'" + ",'mailPass':'" + 
/* 102 */             set.getMailPass() + "','smtpHost':'" + 
/* 103 */             set.getSmtpHost() + "','smtpPort':'" + 
/* 104 */             set.getSmtpPort() + "'" + ",'mailAddress':'" + 
/* 105 */             set.getMailAddress() + "','mailAddress':'" + 
/* 106 */             set.getMailAddress() + "'" + ",'popHost':'" + 
/* 107 */             set.getPopHost() + "','popPort':'" + 
/* 108 */             set.getPopPort() + "'}");
/*     */         else {
/* 110 */           sb.append("'id':'','mailAddress':'','mailAddress':'','mailPass':'','smtpHost':'','smtpPort':'','popHost':'','popPort':''}");
/*     */         }
/*     */       }
/*     */ 
/* 114 */       sb.append("]}");
/* 115 */       this.jsonString = sb.toString();
/*     */     }
/* 117 */     return "success";
/*     */   }
/*     */ 
/*     */   public String multiDel()
/*     */   {
/* 127 */     String[] ids = getRequest().getParameterValues("ids");
/* 128 */     if (ids != null) {
/* 129 */       for (String id : ids) {
/* 130 */         this.outMailUserSetingService.remove(new Long(id));
/*     */       }
/*     */     }
/*     */ 
/* 134 */     this.jsonString = "{success:true}";
/*     */ 
/* 136 */     return "success";
/*     */   }
/*     */ 
/*     */   public String get()
/*     */   {
/* 146 */     OutMailUserSeting outMailUserSeting = (OutMailUserSeting)this.outMailUserSetingService.get(this.id);
/* 147 */     Gson gson = new Gson();
/*     */ 
/* 149 */     StringBuffer sb = new StringBuffer("{success:true,data:");
/*     */ 
/* 151 */     if (outMailUserSeting != null)
/*     */     {
/* 153 */       sb.append(gson.toJson(outMailUserSeting));
/*     */     } else {
/* 155 */       outMailUserSeting = new OutMailUserSeting();
/* 156 */       outMailUserSeting.setUserId(ContextUtil.getCurrentUserId());
/* 157 */       outMailUserSeting.setUserName(ContextUtil.getCurrentUser()
/* 158 */         .getUsername());
/*     */ 
/* 160 */       sb.append(gson.toJson(outMailUserSeting));
/*     */     }
/*     */ 
/* 163 */     sb.append("}");
/* 164 */     setJsonString(sb.toString());
/*     */ 
/* 166 */     return "success";
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/* 173 */     String data = getRequest().getParameter("data");
/* 174 */     if (StringUtils.isNotEmpty(data)) {
/* 175 */       Gson gson = new Gson();
/* 176 */       OutMailUserSeting[] outSet = (OutMailUserSeting[])gson.fromJson(data, 
/* 177 */         com.htsoft.oa.model.communicate.OutMailUserSeting[].class);
/* 178 */       for (OutMailUserSeting setting : outSet) {
/* 179 */         if (setting.getId().longValue() == -1L) {
/* 180 */           setting.setId(null);
/*     */         }
/* 182 */         if ((setting.getReuserId() == null) || (!StringUtils.isNotEmpty(setting.getMailAddress())) || 
/* 183 */           (!StringUtils.isNotEmpty(setting.getPopHost())) || (!StringUtils.isNotEmpty(setting.getUserName())) || 
/* 184 */           (!StringUtils.isNotEmpty(setting.getSmtpPort())) || (!StringUtils.isNotEmpty(setting.getSmtpHost())) || 
/* 185 */           (!StringUtils.isNotEmpty(setting.getPopPort())) || (!StringUtils.isNotEmpty(setting.getMailPass())))
/*     */           continue;
/* 187 */         AppUser appUser = (AppUser)this.appUserService.get(setting.getReuserId());
/* 188 */         setting.setAppUser(appUser);
/* 189 */         this.outMailUserSetingService.save(setting);
/*     */       }
/*     */     }
/*     */ 
/* 193 */     setJsonString("{success:true}");
/* 194 */     return "success";
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.action.system.OutMailSetAction
 * JD-Core Version:    0.6.0
 */