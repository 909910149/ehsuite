/*     */ package com.htsoft.oa.action.system;
/*     */ 
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.GsonBuilder;
/*     */ import com.google.gson.reflect.TypeToken;
/*     */ import com.htsoft.core.command.QueryFilter;
/*     */ import com.htsoft.core.util.AppUtil;
/*     */ import com.htsoft.core.util.BeanUtil;
/*     */ import com.htsoft.core.web.action.BaseAction;
/*     */ import com.htsoft.core.web.paging.PagingBean;
/*     */ import com.htsoft.oa.model.system.RelativeJob;
/*     */ import com.htsoft.oa.service.system.RelativeJobService;
/*     */ import java.lang.reflect.Type;
/*     */ import java.util.List;
/*     */ import javax.annotation.Resource;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.logging.Log;
/*     */ 
/*     */ public class RelativeJobAction extends BaseAction
/*     */ {
/*     */ 
/*     */   @Resource
/*     */   private RelativeJobService relativeJobService;
/*     */   private RelativeJob relativeJob;
/*     */   private Long reJobId;
/*     */ 
/*     */   public Long getReJobId()
/*     */   {
/*  39 */     return this.reJobId;
/*     */   }
/*     */ 
/*     */   public void setReJobId(Long reJobId) {
/*  43 */     this.reJobId = reJobId;
/*     */   }
/*     */ 
/*     */   public RelativeJob getRelativeJob() {
/*  47 */     return this.relativeJob;
/*     */   }
/*     */ 
/*     */   public void setRelativeJob(RelativeJob relativeJob) {
/*  51 */     this.relativeJob = relativeJob;
/*     */   }
/*     */ 
/*     */   public String list()
/*     */   {
/*  59 */     QueryFilter filter = new QueryFilter(getRequest());
/*  60 */     List list = this.relativeJobService.getAll(filter);
/*     */ 
/*  62 */     Type type = new TypeToken() {
/*  63 */     }.getType();
/*  64 */     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
/*  65 */       .append(filter.getPagingBean().getTotalItems()).append(
/*  66 */       ",result:");
/*     */ 
/*  68 */     Gson gson = new Gson();
/*  69 */     buff.append(gson.toJson(list, type));
/*  70 */     buff.append("}");
/*     */ 
/*  72 */     this.jsonString = buff.toString();
/*     */ 
/*  74 */     return "success";
/*     */   }
/*     */ 
/*     */   public String treeLoad()
/*     */   {
/*  81 */     StringBuffer sb = new StringBuffer("");
/*  82 */     sb.append("[{id:'0',text:'" + AppUtil.getCompanyName() + 
/*  83 */       "',expanded:true,children:[");
/*  84 */     List<RelativeJob> list = this.relativeJobService.findByParentId(new Long(0L));
/*  85 */     for (RelativeJob job : list) {
/*  86 */       sb.append("{id:'" + job.getReJobId() + "',text:'" + 
/*  87 */         job.getJobName() + "',");
/*  88 */       sb.append(findChildren(job.getReJobId()));
/*     */     }
/*  90 */     if (!list.isEmpty())
/*  91 */       sb.deleteCharAt(sb.length() - 1);
/*  92 */     sb.append("]}]");
/*  93 */     setJsonString(sb.toString());
/*  94 */     return "success";
/*     */   }
/*     */ 
/*     */   public String multiDel()
/*     */   {
/* 103 */     String[] ids = getRequest().getParameterValues("ids");
/* 104 */     if (ids != null)
/* 105 */       for (String id : ids)
/* 106 */         this.relativeJobService.remove(new Long(id));
/* 107 */     this.jsonString = "{success:true}";
/* 108 */     return "success";
/*     */   }
/*     */ 
/*     */   public String delete()
/*     */   {
/* 113 */     this.relativeJobService.remove(this.reJobId);
/* 114 */     this.jsonString = "{success:true}";
/* 115 */     return "success";
/*     */   }
/*     */ 
/*     */   public String get()
/*     */   {
/* 125 */     RelativeJob relativeJob = (RelativeJob)this.relativeJobService.get(this.reJobId);
/*     */ 
/* 127 */     Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
/*     */ 
/* 129 */     StringBuffer sb = new StringBuffer("{success:true,data:[");
/* 130 */     sb.append(gson.toJson(relativeJob));
/* 131 */     sb.append("]}");
/* 132 */     setJsonString(sb.toString());
/*     */ 
/* 134 */     return "success";
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/* 141 */     if (this.relativeJob.getReJobId() == null) {
/* 142 */       add();
/*     */     } else {
/* 144 */       RelativeJob orgRelativeJob = (RelativeJob)this.relativeJobService.get(this.relativeJob
/* 145 */         .getReJobId());
/*     */       try {
/* 147 */         BeanUtil.copyNotNullProperties(orgRelativeJob, this.relativeJob);
/* 148 */         this.relativeJobService.save(orgRelativeJob);
/*     */       } catch (Exception ex) {
/* 150 */         this.logger.error(ex.getMessage());
/*     */       }
/*     */     }
/* 153 */     setJsonString("{success:true}");
/* 154 */     return "success";
/*     */   }
/*     */ 
/*     */   private String add()
/*     */   {
/* 163 */     boolean isParent = this.relativeJob.getParent().longValue() < 1L;
/* 164 */     if (isParent) {
/* 165 */       this.relativeJob.setDepath(Integer.valueOf(2));
/*     */     } else {
/* 167 */       Integer parentDepath = ((RelativeJob)this.relativeJobService.get(
/* 168 */         this.relativeJob.getParent())).getDepath();
/* 169 */       this.relativeJob.setDepath(Integer.valueOf(parentDepath.intValue() + 1));
/*     */     }
/*     */ 
/* 172 */     RelativeJob newRelativeJob = (RelativeJob)this.relativeJobService.save(this.relativeJob);
/*     */ 
/* 174 */     if (isParent) {
/* 175 */       newRelativeJob.setPath("0." + newRelativeJob.getReJobId() + ".");
/*     */     }
/*     */     else {
/* 178 */       String parentPath = ((RelativeJob)this.relativeJobService.get(
/* 179 */         newRelativeJob.getParent())).getPath();
/* 180 */       newRelativeJob.setPath(parentPath + newRelativeJob.getReJobId() + 
/* 181 */         ".");
/*     */     }
/* 183 */     newRelativeJob.setJobCode("0");
/*     */ 
/* 185 */     this.relativeJobService.save(newRelativeJob);
/* 186 */     setJsonString("{success:true}");
/* 187 */     return "success";
/*     */   }
/*     */ 
/*     */   private String findChildren(Long childId)
/*     */   {
/* 195 */     StringBuffer sb = new StringBuffer("");
/* 196 */     List<RelativeJob> list = this.relativeJobService.findByParentId(childId);
/* 197 */     if ((list.isEmpty()) || (list.size() == 0)) {
/* 198 */       sb.append("leaf:true},");
/* 199 */       return sb.toString();
/*     */     }
/* 201 */     sb.append("children:[");
/* 202 */     for (RelativeJob job : list) {
/* 203 */       sb.append("{id:'" + job.getReJobId() + "',text:'" + 
/* 204 */         job.getJobName() + "',");
/* 205 */       sb.append(findChildren(job.getReJobId()));
/*     */     }
/* 207 */     sb.deleteCharAt(sb.length() - 1);
/* 208 */     sb.append("]},");
/* 209 */     return sb.toString();
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.action.system.RelativeJobAction
 * JD-Core Version:    0.6.0
 */