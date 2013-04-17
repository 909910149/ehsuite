/*     */ package com.htsoft.oa.action.hrm;
/*     */ 
/*     */ import com.htsoft.core.command.QueryFilter;
/*     */ import com.htsoft.core.util.AppUtil;
/*     */ import com.htsoft.core.web.action.BaseAction;
/*     */ import com.htsoft.core.web.paging.PagingBean;
/*     */ import com.htsoft.oa.model.hrm.Job;
/*     */ import com.htsoft.oa.service.hrm.JobService;
/*     */ import flexjson.JSONSerializer;
/*     */ import java.util.List;
/*     */ import javax.annotation.Resource;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class JobAction extends BaseAction
/*     */ {
/*     */ 
/*     */   @Resource
/*     */   private JobService jobService;
/*     */   private Job job;
/*     */   private Long jobId;
/*     */ 
/*     */   public Long getJobId()
/*     */   {
/*  35 */     return this.jobId;
/*     */   }
/*     */ 
/*     */   public void setJobId(Long jobId) {
/*  39 */     this.jobId = jobId;
/*     */   }
/*     */ 
/*     */   public Job getJob() {
/*  43 */     return this.job;
/*     */   }
/*     */ 
/*     */   public void setJob(Job job) {
/*  47 */     this.job = job;
/*     */   }
/*     */ 
/*     */   public String list()
/*     */   {
/*  54 */     QueryFilter filter = new QueryFilter(getRequest());
/*  55 */     List list = this.jobService.getAll(filter);
/*  56 */     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
/*  57 */       .append(filter.getPagingBean().getTotalItems()).append(
/*  58 */       ",result:");
/*  59 */     JSONSerializer serializer = new JSONSerializer();
/*  60 */     buff
/*  61 */       .append(serializer.exclude(
/*  62 */       new String[] { "class", "department.appUser" })
/*  63 */       .serialize(list));
/*  64 */     buff.append("}");
/*  65 */     this.jsonString = buff.toString();
/*  66 */     return "success";
/*     */   }
/*     */ 
/*     */   public String multiDel()
/*     */   {
/*  73 */     String[] ids = getRequest().getParameterValues("ids");
/*  74 */     if (ids != null) {
/*  75 */       for (String id : ids) {
/*  76 */         Job removeJob = (Job)this.jobService.get(new Long(id));
/*  77 */         removeJob.setDelFlag(Short.valueOf(Job.DELFLAG_HAD));
/*  78 */         this.jobService.save(removeJob);
/*     */       }
/*     */     }
/*  81 */     this.jsonString = "{success:true}";
/*  82 */     return "success";
/*     */   }
/*     */ 
/*     */   public String delete()
/*     */   {
/*  89 */     Job removeJob = (Job)this.jobService.get(this.jobId);
/*  90 */     removeJob.setDelFlag(Short.valueOf(Job.DELFLAG_HAD));
/*  91 */     this.jobService.save(removeJob);
/*  92 */     this.jsonString = "{success:true}";
/*  93 */     return "success";
/*     */   }
/*     */ 
/*     */   public String get()
/*     */   {
/* 100 */     Job job = (Job)this.jobService.get(this.jobId);
/*     */ 
/* 103 */     JSONSerializer json = new JSONSerializer();
/*     */ 
/* 105 */     StringBuffer sb = new StringBuffer("{success:true,data:");
/* 106 */     sb.append(json.exclude(new String[] { "class" }).serialize(job));
/* 107 */     sb.append("}");
/* 108 */     setJsonString(sb.toString());
/*     */ 
/* 110 */     return "success";
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/* 117 */     this.job.setDelFlag(Short.valueOf(Job.DELFLAG_NOT));
/* 118 */     this.jobService.save(this.job);
/* 119 */     setJsonString("{success:true}");
/* 120 */     return "success";
/*     */   }
/*     */ 
/*     */   public String combo()
/*     */   {
/* 127 */     String strDepId = getRequest().getParameter("depId");
/* 128 */     if (StringUtils.isNotEmpty(strDepId)) {
/* 129 */       List<Job> list = this.jobService.findByDep(new Long(strDepId));
/* 130 */       StringBuffer sb = new StringBuffer("[");
/* 131 */       for (Job job : list) {
/* 132 */         sb.append("['").append(job.getJobId()).append("','").append(
/* 133 */           job.getJobName()).append("'],");
/*     */       }
/* 135 */       if (list.size() > 0) {
/* 136 */         sb.deleteCharAt(sb.length() - 1);
/*     */       }
/* 138 */       sb.append("]");
/* 139 */       setJsonString(sb.toString());
/*     */     } else {
/* 141 */       setJsonString("{success:false}");
/*     */     }
/* 143 */     return "success";
/*     */   }
/*     */ 
/*     */   public String recovery()
/*     */   {
/* 150 */     String[] ids = getRequest().getParameterValues("ids");
/* 151 */     if (ids != null) {
/* 152 */       for (String id : ids) {
/* 153 */         Job deleteJob = (Job)this.jobService.get(new Long(id));
/* 154 */         deleteJob.setDelFlag(Short.valueOf(Job.DELFLAG_NOT));
/* 155 */         this.jobService.save(deleteJob);
/*     */       }
/*     */     }
/* 158 */     this.jsonString = "{success:true}";
/* 159 */     return "success";
/*     */   }
/*     */ 
/*     */   public String add()
/*     */   {
/* 166 */     this.job.setDelFlag(Short.valueOf(Job.DELFLAG_NOT));
/* 167 */     if ((this.job.getJobId() != null) && (this.job.getJobId().longValue() > 0L)) {
/* 168 */       this.jobService.edit(this.job);
/*     */     } else {
/* 170 */       boolean bo = this.job.getParentId().longValue() > 0L;
/*     */ 
/* 172 */       if (bo)
/*     */       {
/* 174 */         Job jb = (Job)this.jobService.get(this.job.getParentId());
/* 175 */         this.job.setDepth(Long.valueOf(jb.getDepth().longValue() + 1L));
/*     */       } else {
/* 177 */         this.job.setDepth(new Long(2L));
/*     */       }
/* 179 */       Job newJob = (Job)this.jobService.save(this.job);
/*     */ 
/* 181 */       if (bo) {
/* 182 */         Job jb = (Job)this.jobService.get(this.job.getParentId());
/* 183 */         newJob.setPath(jb.getPath() + newJob.getJobId() + ".");
/*     */       } else {
/* 185 */         newJob.setPath("0." + newJob.getJobId() + ".");
/*     */       }
/*     */ 
/* 188 */       this.jobService.save(newJob);
/*     */     }
/* 190 */     setJsonString("{success:true}");
/* 191 */     return "success";
/*     */   }
/*     */ 
/*     */   public String treeLoad()
/*     */   {
/* 200 */     StringBuffer sb = new StringBuffer("[{id:'0',text:'" + 
/* 201 */       AppUtil.getCompanyName() + "',expanded:true,children:[");
/*     */ 
/* 203 */     List<Job> list = this.jobService.findByCondition(new Long(0L));
/* 204 */     for (Job jb : list) {
/* 205 */       sb.append("{id:'" + jb.getJobId() + "',text:'" + jb.getJobName() + 
/* 206 */         "',");
/* 207 */       sb.append(findChildren(jb.getJobId()));
/*     */     }
/* 209 */     if (!list.isEmpty()) {
/* 210 */       sb.deleteCharAt(sb.length() - 1);
/*     */     }
/* 212 */     sb.append("]}]");
/* 213 */     this.jsonString = sb.toString();
/* 214 */     return "success";
/*     */   }
/*     */ 
/*     */   private String findChildren(Long parentId)
/*     */   {
/* 221 */     StringBuffer sb = new StringBuffer();
/* 222 */     List<Job> list = this.jobService.findByCondition(parentId);
/* 223 */     if ((list.isEmpty()) || (list.size() == 0)) {
/* 224 */       sb.append("leaf:true},");
/* 225 */       return sb.toString();
/*     */     }
/* 227 */     sb.append("expanded:true,children:[");
/* 228 */     for (Job j : list) {
/* 229 */       sb.append("{id:'" + j.getJobId() + "',text:'" + j.getJobName() + 
/* 230 */         "',");
/* 231 */       sb.append(findChildren(j.getJobId()));
/*     */     }
/* 233 */     sb.deleteCharAt(sb.length() - 1);
/* 234 */     sb.append("]},");
/* 235 */     return sb.toString();
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.action.hrm.JobAction
 * JD-Core Version:    0.6.0
 */