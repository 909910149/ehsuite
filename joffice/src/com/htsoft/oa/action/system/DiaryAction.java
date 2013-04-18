/*     */ package com.htsoft.oa.action.system;
/*     */ 
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.GsonBuilder;
/*     */ import com.google.gson.reflect.TypeToken;
/*     */ import com.htsoft.core.command.QueryFilter;
/*     */ import com.htsoft.core.util.ContextUtil;
/*     */ import com.htsoft.core.web.action.BaseAction;
/*     */ import com.htsoft.core.web.paging.PagingBean;
/*     */ import com.htsoft.oa.model.system.AppUser;
/*     */ import com.htsoft.oa.model.system.Diary;
/*     */ import com.htsoft.oa.service.system.AppUserService;
/*     */ import com.htsoft.oa.service.system.DiaryService;
/*     */ import flexjson.JSONSerializer;
/*     */ import flexjson.transformer.DateTransformer;
/*     */ import java.lang.reflect.Type;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import javax.annotation.Resource;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class DiaryAction extends BaseAction
/*     */ {
/*     */ 
/*     */   @Resource
/*     */   private DiaryService diaryService;
/*     */ 
/*     */   @Resource
/*     */   private AppUserService appUserService;
/*     */   private Diary diary;
/*     */   private Date from;
/*     */   private Date to;
/*     */   private Long diaryId;
/*     */ 
/*     */   public Date getFrom()
/*     */   {
/*  48 */     return this.from;
/*     */   }
/*     */ 
/*     */   public void setFrom(Date from) {
/*  52 */     this.from = from;
/*     */   }
/*     */ 
/*     */   public Date getTo() {
/*  56 */     return this.to;
/*     */   }
/*     */ 
/*     */   public void setTo(Date to) {
/*  60 */     this.to = to;
/*     */   }
/*     */ 
/*     */   public Long getDiaryId()
/*     */   {
/*  66 */     return this.diaryId;
/*     */   }
/*     */ 
/*     */   public void setDiaryId(Long diaryId) {
/*  70 */     this.diaryId = diaryId;
/*     */   }
/*     */ 
/*     */   public Diary getDiary() {
/*  74 */     return this.diary;
/*     */   }
/*     */ 
/*     */   public void setDiary(Diary diary) {
/*  78 */     this.diary = diary;
/*     */   }
/*     */ 
/*     */   public String list()
/*     */   {
/*  86 */     AppUser user = ContextUtil.getCurrentUser();
/*  87 */     QueryFilter filter = new QueryFilter(getRequest());
/*  88 */     filter.addFilter("Q_appUser.userId_L_EQ", user.getId().toString());
/*  89 */     List list = this.diaryService.getAll(filter);
/*  90 */     Type type = new TypeToken() {
/*  91 */     }.getType();
/*  92 */     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
/*  93 */       .append(filter.getPagingBean().getTotalItems()).append(
/*  94 */       ",result:");
/*  95 */     Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd")
/*  96 */       .excludeFieldsWithoutExposeAnnotation().create();
/*  97 */     buff.append(gson.toJson(list, type));
/*  98 */     buff.append("}");
/*  99 */     this.jsonString = buff.toString();
/* 100 */     return "success";
/*     */   }
/*     */ 
/*     */   public String subUser()
/*     */   {
/* 107 */     PagingBean pb = getInitPagingBean();
/* 108 */     String usrIds = getRequest().getParameter("userId");
/* 109 */     StringBuffer sb = new StringBuffer();
/* 110 */     if (StringUtils.isNotEmpty(usrIds)) {
/* 111 */       sb.append(usrIds);
/*     */     }
/*     */ 
/* 114 */     List<AppUser> list = this.appUserService.findRelativeUsersByUserId(
/* 115 */       ContextUtil.getCurrentUserId(), Short.valueOf((short) 0));
/* 116 */     if (list != null) {
/* 117 */       for (AppUser appUser : list) {
/* 118 */         sb.append(appUser.getUserId()).append(",");
/*     */       }
/* 120 */       if (list.size() > 0)
/* 121 */         sb.deleteCharAt(sb.length() - 1);
/*     */     }
/* 123 */     List diaryList = new ArrayList();
/*     */ 
/* 125 */     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':");
/*     */ 
/* 127 */     if (sb.length() > 0) {
/* 128 */       diaryList = this.diaryService.getSubDiary(sb.toString(), pb);
/* 129 */       buff.append(pb.getTotalItems());
/*     */     } else {
/* 131 */       buff.append("0");
/*     */     }
/* 133 */     buff.append(",result:");
/* 134 */     JSONSerializer serializer = new JSONSerializer();
/* 135 */     serializer.transform(new DateTransformer("yyyy-MM-dd"), new String[] { "dayTime" });
/* 136 */     buff.append(serializer.exclude(new String[] { "class" })
/* 137 */       .serialize(diaryList));
/* 138 */     buff.append("}");
/* 139 */     this.jsonString = buff.toString();
/* 140 */     return "success";
/*     */   }
/*     */ 
/*     */   public String search()
/*     */   {
/* 148 */     AppUser user = ContextUtil.getCurrentUser();
/* 149 */     QueryFilter filter = new QueryFilter(getRequest());
/*     */ 
/* 151 */     filter.addFilter("Q_appUser.userId_L_EQ", user.getId().toString());
/*     */ 
/* 153 */     if (getRequest().getParameter("from") != "") {
/* 154 */       filter.addFilter("Q_dayTime_D_GE", getRequest()
/* 155 */         .getParameter("from"));
/*     */     }
/*     */ 
/* 158 */     if (getRequest().getParameter("to") != "") {
/* 159 */       filter.addFilter("Q_dayTime_D_LE", getRequest().getParameter("to"));
/*     */     }
/*     */ 
/* 162 */     filter.addFilter("Q_content_S_LK", this.diary.getContent());
/*     */ 
/* 164 */     if (this.diary.getDiaryType() != null) {
/* 165 */       filter.addFilter("Q_diaryType_SN_EQ", this.diary.getDiaryType()
/* 166 */         .toString());
/*     */     }
/*     */ 
/* 169 */     List list = this.diaryService.getAll(filter);
/* 170 */     Type type = new TypeToken() {
/* 171 */     }.getType();
/* 172 */     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
/* 173 */       .append(filter.getPagingBean().getTotalItems()).append(
/* 174 */       ",result:");
/* 175 */     Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
/* 176 */     buff.append(gson.toJson(list, type));
/* 177 */     buff.append("}");
/* 178 */     this.jsonString = buff.toString();
/* 179 */     return "success";
/*     */   }
/*     */ 
/*     */   public String multiDel()
/*     */   {
/* 190 */     String[] ids = getRequest().getParameterValues("ids");
/* 191 */     if (ids != null) {
/* 192 */       for (String id : ids) {
/* 193 */         this.diaryService.remove(new Long(id));
/*     */       }
/*     */     }
/* 196 */     this.jsonString = "{success:true}";
/* 197 */     return "success";
/*     */   }
/*     */ 
/*     */   public String get()
/*     */   {
/* 206 */     Diary diary = (Diary)this.diaryService.get(this.diaryId);
/* 207 */     Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
/*     */ 
/* 209 */     StringBuffer sb = new StringBuffer("{success:true,data:");
/* 210 */     sb.append(gson.toJson(diary));
/* 211 */     sb.append("}");
/* 212 */     setJsonString(sb.toString());
/* 213 */     return "success";
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/* 220 */     AppUser user = ContextUtil.getCurrentUser();
/* 221 */     this.diary.setAppUser(user);
/* 222 */     this.diaryService.save(this.diary);
/* 223 */     setJsonString("{success:true}");
/* 224 */     return "success";
/*     */   }
/*     */ 
/*     */   public String check() {
/* 228 */     String strId = getRequest().getParameter("diaryId");
/* 229 */     if (StringUtils.isNotEmpty(strId)) {
/* 230 */       this.diary = ((Diary)this.diaryService.get(new Long(strId)));
/*     */     }
/* 232 */     return "check";
/*     */   }
/*     */ 
/*     */   public String display() {
/* 236 */     AppUser user = ContextUtil.getCurrentUser();
/* 237 */     QueryFilter filter = new QueryFilter(getRequest());
/* 238 */     filter.addFilter("Q_appUser.userId_L_EQ", user.getId().toString());
/* 239 */     filter.addSorted("diaryId", "desc");
/* 240 */     List list = this.diaryService.getAll(filter);
/* 241 */     getRequest().setAttribute("diaryList", list);
/* 242 */     return "display";
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.action.system.DiaryAction
 * JD-Core Version:    0.6.0
 */