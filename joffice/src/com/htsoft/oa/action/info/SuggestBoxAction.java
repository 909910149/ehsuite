/*     */ package com.htsoft.oa.action.info;
/*     */ 
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.reflect.TypeToken;
/*     */ import com.htsoft.core.command.QueryFilter;
/*     */ import com.htsoft.core.web.action.BaseAction;
/*     */ import com.htsoft.core.web.paging.PagingBean;
/*     */ import com.htsoft.oa.model.info.SuggestBox;
/*     */ import com.htsoft.oa.model.system.AppUser;
/*     */ import com.htsoft.oa.model.system.SysConfig;
/*     */ import com.htsoft.oa.service.info.SuggestBoxService;
/*     */ import com.htsoft.oa.service.system.AppUserService;
/*     */ import com.htsoft.oa.service.system.SysConfigService;
/*     */ import java.lang.reflect.Type;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import javax.annotation.Resource;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class SuggestBoxAction extends BaseAction
/*     */ {
/*     */ 
/*     */   @Resource
/*     */   private SuggestBoxService suggestBoxService;
/*     */ 
/*     */   @Resource
/*     */   private SysConfigService sysConfigService;
/*     */ 
/*     */   @Resource
/*     */   private AppUserService appUserService;
/*     */   private SuggestBox suggestBox;
/*     */   private Long boxId;
/*     */ 
/*     */   public Long getBoxId()
/*     */   {
/*  40 */     return this.boxId;
/*     */   }
/*     */ 
/*     */   public void setBoxId(Long boxId) {
/*  44 */     this.boxId = boxId;
/*     */   }
/*     */ 
/*     */   public SuggestBox getSuggestBox() {
/*  48 */     return this.suggestBox;
/*     */   }
/*     */ 
/*     */   public void setSuggestBox(SuggestBox suggestBox) {
/*  52 */     this.suggestBox = suggestBox;
/*     */   }
/*     */ 
/*     */   public String list()
/*     */   {
/*  60 */     QueryFilter filter = new QueryFilter(getRequest());
/*  61 */     List list = this.suggestBoxService.getAll(filter);
/*     */ 
/*  63 */     Type type = new TypeToken() {  }
/*  63 */     .getType();
/*  64 */     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
/*  65 */       .append(filter.getPagingBean().getTotalItems()).append(",result:");
/*     */ 
/*  67 */     Gson gson = new Gson();
/*  68 */     buff.append(gson.toJson(list, type));
/*  69 */     buff.append("}");
/*     */ 
/*  71 */     this.jsonString = buff.toString();
/*     */ 
/*  73 */     return "success";
/*     */   }
/*     */ 
/*     */   public String multiDel()
/*     */   {
/*  81 */     String[] ids = getRequest().getParameterValues("ids");
/*  82 */     if (ids != null) {
/*  83 */       for (String id : ids) {
/*  84 */         this.suggestBoxService.remove(new Long(id));
/*     */       }
/*     */     }
/*     */ 
/*  88 */     this.jsonString = "{success:true}";
/*     */ 
/*  90 */     return "success";
/*     */   }
/*     */ 
/*     */   public String get()
/*     */   {
/*  98 */     SuggestBox suggestBox = (SuggestBox)this.suggestBoxService.get(this.boxId);
/*     */ 
/* 100 */     Gson gson = new Gson();
/*     */ 
/* 102 */     StringBuffer sb = new StringBuffer("{success:true,data:");
/* 103 */     sb.append(gson.toJson(suggestBox));
/* 104 */     sb.append("}");
/* 105 */     setJsonString(sb.toString());
/*     */ 
/* 107 */     return "success";
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/* 114 */     this.suggestBox.setCreatetime(new Date());
/*     */ 
/* 116 */     this.suggestBox.setSenderIp(getRequest().getRemoteAddr());
/*     */ 
/* 118 */     SysConfig suggestId = this.sysConfigService.findByKey("suggestId");
/* 119 */     AppUser suggestManager = (AppUser)this.appUserService.get(new Long(suggestId.getDataValue()));
/*     */ 
/* 121 */     if (suggestManager != null) {
/* 122 */       this.suggestBox.setRecFullname(suggestManager.getFullname());
/* 123 */       this.suggestBox.setRecUid(suggestManager.getUserId());
/*     */     }
/*     */ 
/* 129 */     this.suggestBoxService.save(this.suggestBox);
/* 130 */     setJsonString("{success:true}");
/* 131 */     return "success";
/*     */   }
/*     */ 
/*     */   public String reply()
/*     */   {
/* 139 */     SuggestBox orgSuggest = (SuggestBox)this.suggestBoxService.get(this.suggestBox.getBoxId());
/* 140 */     AppUser curUser = (AppUser)this.appUserService.get(new Long(this.sysConfigService.findByKey("suggestId").getDataValue()));
/* 141 */     orgSuggest.setReplyId(curUser.getUserId());
/* 142 */     orgSuggest.setIsOpen(this.suggestBox.getIsOpen());
/* 143 */     orgSuggest.setReplyFullname(curUser.getFullname());
/* 144 */     orgSuggest.setReplyTime(new Date());
/* 145 */     orgSuggest.setStatus(SuggestBox.STATUS_AUDIT);
/* 146 */     orgSuggest.setReplyContent(this.suggestBox.getReplyContent());
/* 147 */     this.suggestBoxService.save(orgSuggest);
/* 148 */     setJsonString("{success:true}");
/* 149 */     return "success";
/*     */   }
/*     */ 
/*     */   public String match()
/*     */   {
/* 156 */     SuggestBox orgSuggest = (SuggestBox)this.suggestBoxService.get(this.suggestBox.getBoxId());
/* 157 */     if (orgSuggest.getQueryPwd().equals(this.suggestBox.getQueryPwd()))
/* 158 */       setJsonString("{success:true}");
/*     */     else {
/* 160 */       setJsonString("{failure:true}");
/*     */     }
/* 162 */     return "success";
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.action.info.SuggestBoxAction
 * JD-Core Version:    0.6.0
 */