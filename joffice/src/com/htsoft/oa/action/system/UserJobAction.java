/*     */ package com.htsoft.oa.action.system;
/*     */ 
/*     */ import com.htsoft.core.command.QueryFilter;
/*     */ import com.htsoft.core.util.JsonUtil;
/*     */ import com.htsoft.core.web.action.BaseAction;
/*     */ import com.htsoft.core.web.paging.PagingBean;
/*     */ import com.htsoft.oa.model.hrm.Job;
/*     */ import com.htsoft.oa.model.system.AppUser;
/*     */ import com.htsoft.oa.model.system.UserJob;
/*     */ import com.htsoft.oa.service.hrm.JobService;
/*     */ import com.htsoft.oa.service.system.UserJobService;
/*     */ import flexjson.JSONSerializer;
/*     */ import flexjson.transformer.DateTransformer;
/*     */ import java.util.List;
/*     */ import javax.annotation.Resource;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class UserJobAction extends BaseAction
/*     */ {
/*     */ 
/*     */   @Resource
/*     */   private UserJobService userJobService;
/*     */ 
/*     */   @Resource
/*     */   private JobService jobService;
/*     */   private UserJob userJob;
/*     */   private Long userJobId;
/*     */ 
/*     */   public Long getUserJobId()
/*     */   {
/*  44 */     return this.userJobId;
/*     */   }
/*     */ 
/*     */   public void setUserJobId(Long userJobId) {
/*  48 */     this.userJobId = userJobId;
/*     */   }
/*     */ 
/*     */   public UserJob getUserJob() {
/*  52 */     return this.userJob;
/*     */   }
/*     */ 
/*     */   public void setUserJob(UserJob userJob) {
/*  56 */     this.userJob = userJob;
/*     */   }
/*     */ 
/*     */   public String list()
/*     */   {
/*  63 */     QueryFilter filter = new QueryFilter(getRequest());
/*  64 */     if ((this.userJob != null) && (this.userJob.getJob() != null) && 
/*  65 */       (this.userJob.getJob().getJobId().longValue() > 0L)) {
/*  66 */       Job job = (Job)this.jobService.get(this.userJob.getJob().getJobId());
/*  67 */       filter.addFilter("Q_job.path_S_LK", job.getPath());
/*     */     }
/*  69 */     List list = this.userJobService.getAll(filter);
/*  70 */     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
/*  71 */       .append(filter.getPagingBean().getTotalItems()).append(
/*  72 */       ",result:");
/*  73 */     JSONSerializer serializer = new JSONSerializer();
/*  74 */     serializer.transform(new DateTransformer("yyyy-MM-dd"), 
/*  75 */       new String[] { "appUser.accessionTime" });
/*  76 */     buff.append(serializer.serialize(list));
/*  77 */     buff.append("}");
/*  78 */     this.jsonString = buff.toString();
/*     */ 
/*  80 */     return "success";
/*     */   }
/*     */ 
/*     */   public String multiDel()
/*     */   {
/*  90 */     String[] ids = getRequest().getParameterValues("ids");
/*  91 */     if (ids != null) {
/*  92 */       for (String id : ids) {
/*  93 */         this.userJobService.remove(new Long(id));
/*     */       }
/*     */     }
/*     */ 
/*  97 */     this.jsonString = "{success:true}";
/*     */ 
/*  99 */     return "success";
/*     */   }
/*     */ 
/*     */   public String get()
/*     */   {
/* 106 */     UserJob userJob = (UserJob)this.userJobService.get(this.userJobId);
/*     */ 
/* 108 */     StringBuffer sb = new StringBuffer("{success:true,data:");
/* 109 */     sb.append(JsonUtil.getJSONSerializer(new String[] { "accessionTime" })
/* 110 */       .serialize(userJob));
/* 111 */     sb.append("}");
/* 112 */     setJsonString(sb.toString());
/* 113 */     return "success";
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/* 120 */     String msg = "{success:true,msg:'数据操作成功！'}";
/* 121 */     Long userId = this.userJob.getAppUser().getUserId();
/* 122 */     if ((this.userJob.getIsMain().equals(UserJob.ISMIAN)) && 
/* 123 */       (this.userJobService.IsExistsjob(this.userJob.getUserJobId(), userId).booleanValue())) {
/* 124 */       msg = "{failure:true,msg:'对不起，该用户已经存在主职位，请添加副职位！'}";
/* 125 */       setJsonString(msg);
/* 126 */       return "success";
/*     */     }
/*     */ 
/* 129 */     boolean isAdd = (this.userJob != null) && (this.userJob.getUserJobId() != null);
/*     */ 
/* 131 */     if (!isAdd)
/* 132 */       msg = this.userJobService.add(this.userJob);
/*     */     else
/* 134 */       this.userJobService.save(this.userJob);
/* 135 */     setJsonString(msg);
/* 136 */     return "success";
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.action.system.UserJobAction
 * JD-Core Version:    0.6.0
 */