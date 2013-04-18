/*     */ package com.htsoft.oa.action.system;
/*     */ 
/*     */ import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.htsoft.core.util.BeanUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.model.system.RelativeJob;
import com.htsoft.oa.model.system.RelativeUser;
import com.htsoft.oa.service.system.AppUserService;
import com.htsoft.oa.service.system.RelativeJobService;
import com.htsoft.oa.service.system.RelativeUserService;

import flexjson.JSONSerializer;
/*     */ 
/*     */ public class RelativeUserAction extends BaseAction
/*     */ {
/*     */ 
/*     */   @Resource
/*     */   private RelativeUserService relativeUserService;
/*     */ 
/*     */   @Resource
/*     */   private AppUserService appUserService;
/*     */ 
/*     */   @Resource
/*     */   private RelativeJobService relativeJobService;
/*     */   private RelativeUser relativeUser;
/*     */   private Long relativeUserId;
/*     */   private Long userId;
/*     */   private Long reJobId;
/*     */ 
/*     */   public Long getRelativeUserId()
/*     */   {
/*  50 */     return this.relativeUserId;
/*     */   }
/*     */ 
/*     */   public void setRelativeUserId(Long relativeUserId) {
/*  54 */     this.relativeUserId = relativeUserId;
/*     */   }
/*     */ 
/*     */   public RelativeUser getRelativeUser() {
/*  58 */     return this.relativeUser;
/*     */   }
/*     */ 
/*     */   public void setRelativeUser(RelativeUser relativeUser) {
/*  62 */     this.relativeUser = relativeUser;
/*     */   }
/*     */ 
/*     */   public Long getUserId() {
/*  66 */     return this.userId;
/*     */   }
/*     */ 
/*     */   public void setUserId(Long userId) {
/*  70 */     this.userId = userId;
/*     */   }
/*     */ 
/*     */   public Long getReJobId() {
/*  74 */     return this.reJobId;
/*     */   }
/*     */ 
/*     */   public void setReJobId(Long reJobId) {
/*  78 */     this.reJobId = reJobId;
/*     */   }
/*     */ 
/*     */   public String list()
/*     */   {
/*  85 */     PagingBean pb = getInitPagingBean();
/*  86 */     List list = this.relativeUserService.list(this.userId, this.reJobId, pb);
/*  87 */     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
/*  88 */       .append(pb.getTotalItems()).append(",result:");
/*  89 */     JSONSerializer serializer = new JSONSerializer();
/*  90 */     buff.append(serializer.exclude(new String[] { "class" })
/*  91 */       .serialize(list));
/*  92 */     buff.append("}");
/*     */ 
/*  94 */     this.jsonString = buff.toString();
/*     */ 
/*  96 */     return "success";
/*     */   }
/*     */ 
/*     */   public String multiDel()
/*     */   {
/* 104 */     String[] ids = getRequest().getParameterValues("ids");
/* 105 */     if (ids != null) {
/* 106 */       for (String id : ids) {
/* 107 */         this.relativeUserService.remove(new Long(id));
/*     */       }
/*     */     }
/*     */ 
/* 111 */     this.jsonString = "{success:true}";
/*     */ 
/* 113 */     return "success";
/*     */   }
/*     */ 
/*     */   public String get()
/*     */   {
/* 120 */     RelativeUser relativeUser = (RelativeUser)this.relativeUserService.get(this.relativeUserId);
/*     */ 
/* 122 */     Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
/*     */ 
/* 124 */     StringBuffer sb = new StringBuffer("{success:true,data:");
/* 125 */     sb.append(gson.toJson(relativeUser));
/* 126 */     sb.append("}");
/* 127 */     setJsonString(sb.toString());
/*     */ 
/* 129 */     return "success";
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/* 136 */     if (this.relativeUser.getRelativeUserId() == null) {
/* 137 */       this.relativeUserService.save(this.relativeUser);
/*     */     } else {
/* 139 */       RelativeUser orgRelativeUser = (RelativeUser)this.relativeUserService.get(this.relativeUser
/* 140 */         .getRelativeUserId());
/*     */       try {
/* 142 */         BeanUtil.copyNotNullProperties(orgRelativeUser, this.relativeUser);
/* 143 */         this.relativeUserService.save(orgRelativeUser);
/*     */       } catch (Exception ex) {
/* 145 */         this.logger.error(ex.getMessage());
/*     */       }
/*     */     }
/* 148 */     setJsonString("{success:true}");
/* 149 */     return "success";
/*     */   }
/*     */ 
/*     */   public String mutliAdd()
/*     */   {
/* 156 */     boolean yesOrNoCurrentUser = false;
/* 157 */     ArrayList<Long> ex = new ArrayList();
/* 158 */     String noEx = "";
/* 159 */     String jobUserIds = getRequest().getParameter("jobUserIds");
/* 160 */     String userId = getRequest().getParameter("userId");
/* 161 */     AppUser appUser = (AppUser)this.appUserService.get(new Long(userId));
/*     */ 
/* 163 */     for (String uid : jobUserIds.split(",")) {
/* 164 */       if (uid.equals(userId)) {
/* 165 */         yesOrNoCurrentUser = true;
/*     */       } else {
/* 167 */         AppUser apu = this.relativeUserService.judge(new Long(userId), 
/* 168 */           new Long(uid));
/* 169 */         if (apu == null)
/* 170 */           ex.add(new Long(uid));
/*     */         else
/* 172 */           noEx = noEx + apu.getFullname() + ",";
/*     */       }
/*     */     }
/* 175 */     if (!noEx.equals("")) {
/* 176 */       noEx = noEx.substring(0, noEx.length() - 1);
/*     */     }
/* 178 */     String msg = "";
/* 179 */     if ((ex != null) && (ex.size() > 0)) {
/* 180 */       String exMsg = "";
/* 181 */       for (Iterator<Long> l = ex.iterator(); ((Iterator)l).hasNext(); ) {
				  Long uid = (Long)((Iterator)l).next();
/* 182 */         AppUser jobUser = (AppUser)this.appUserService.get(new Long(uid.longValue()));
/*     */ 
/* 184 */         RelativeUser ruSave = new RelativeUser();
/* 185 */         RelativeJob rJob = (RelativeJob)this.relativeJobService.get(this.reJobId);
/* 186 */         ruSave.setRelativeJob(rJob);
/* 187 */         ruSave.setJobUser(jobUser);
/* 188 */         ruSave.setAppUser(appUser);
/* 189 */         ruSave.setIsSuper(this.relativeUser.getIsSuper());
/*     */ 
/* 191 */         RelativeUser ru = (RelativeUser)this.relativeUserService.save(ruSave);
/* 192 */         exMsg = exMsg + ru.getJobUser().getFullname() + ",";
/*     */       }
/* 194 */       exMsg = exMsg.substring(0, exMsg.length() - 1);
/* 195 */       msg = "{success:true,msg:'成功添加[" + exMsg + "]用户";
/* 196 */       if ((noEx != null) && (!noEx.equals("")))
/* 197 */         msg = msg + "，其中[" + noEx + "]已经添加";
/* 198 */       if (yesOrNoCurrentUser)
/* 199 */         msg = msg + "，用户本身不能添加";
/* 200 */       msg = msg + "！'}";
/*     */     } else {
/* 202 */       msg = "{success:true,msg:'对不起，没有适合添加的用户，请重新选择！'}";
/* 203 */     }setJsonString(msg);
/* 204 */     return (String)"success";
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\estbpm-orcl\estbpm\WEB-INF\classes\
 * Qualified Name:     com.htsoft.est.action.system.RelativeUserAction
 * JD-Core Version:    0.6.0
 */