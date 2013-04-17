/*     */ package com.htsoft.oa.action.info;
/*     */ 
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.GsonBuilder;
/*     */ import com.google.gson.reflect.TypeToken;
/*     */ import com.htsoft.core.command.QueryFilter;
/*     */ import com.htsoft.core.util.BeanUtil;
/*     */ import com.htsoft.core.util.JsonUtil;
/*     */ import com.htsoft.core.web.action.BaseAction;
/*     */ import com.htsoft.core.web.paging.PagingBean;
/*     */ import com.htsoft.oa.model.info.News;
/*     */ import com.htsoft.oa.service.info.NewsService;
/*     */ import flexjson.JSONSerializer;
/*     */ import java.lang.reflect.Type;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import javax.annotation.Resource;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.logging.Log;
/*     */ 
/*     */ public class NewsAction extends BaseAction
/*     */ {
/*     */ 
/*     */   @Resource
/*     */   private NewsService newsService;
/*     */   private News news;
/*     */   private Long newsId;
/*     */ 
/*     */   public Long getNewsId()
/*     */   {
/*  37 */     return this.newsId;
/*     */   }
/*     */ 
/*     */   public void setNewsId(Long newsId) {
/*  41 */     this.newsId = newsId;
/*     */   }
/*     */ 
/*     */   public News getNews() {
/*  45 */     return this.news;
/*     */   }
/*     */ 
/*     */   public void setNews(News news) {
/*  49 */     this.news = news;
/*     */   }
/*     */ 
/*     */   public String list()
/*     */   {
/*  57 */     QueryFilter filter = new QueryFilter(getRequest());
/*  58 */     List list = this.newsService.getAll(filter);
/*     */ 
/*  61 */     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
/*  62 */       .append(filter.getPagingBean().getTotalItems()).append(",result:");
/*  63 */     JSONSerializer json = JsonUtil.getJSONSerializer(new String[] { "createtime", "expTime", "updateTime" });
/*  64 */     buff.append(json.serialize(list));
/*  65 */     buff.append("}");
/*     */ 
/*  67 */     this.jsonString = buff.toString();
/*     */ 
/*  69 */     return "success";
/*     */   }
/*     */ 
/*     */   public String multiDel()
/*     */   {
/*  77 */     String[] ids = getRequest().getParameterValues("ids");
/*  78 */     if (ids != null) {
/*  79 */       for (String id : ids) {
/*  80 */         this.newsService.remove(new Long(id));
/*     */       }
/*     */     }
/*     */ 
/*  84 */     this.jsonString = "{success:true}";
/*     */ 
/*  86 */     return "success";
/*     */   }
/*     */ 
/*     */   public String get()
/*     */   {
/*  94 */     News news = (News)this.newsService.get(this.newsId);
/*     */ 
/*  96 */     JSONSerializer json = JsonUtil.getJSONSerializer(new String[] { "createtime", "expTime", "updateTime" });
/*     */ 
/*  98 */     StringBuffer sb = new StringBuffer("{success:true,data:");
/*  99 */     sb.append(json.serialize(news));
/* 100 */     sb.append("}");
/* 101 */     setJsonString(sb.toString());
/*     */ 
/* 103 */     return "success";
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/* 109 */     this.news.setUpdateTime(new Date());
/* 110 */     if (this.news.getNewsId() == null) {
/* 111 */       this.news.setViewCounts(Integer.valueOf(0));
/* 112 */       this.news.setReplyCounts(Integer.valueOf(0));
/* 113 */       this.newsService.save(this.news);
/*     */     } else {
/* 115 */       News orgNews = (News)this.newsService.get(this.news.getNewsId());
/*     */       try {
/* 117 */         BeanUtil.copyNotNullProperties(orgNews, this.news);
/* 118 */         this.newsService.save(orgNews);
/*     */       } catch (Exception ex) {
/* 120 */         this.logger.error(ex.getMessage());
/*     */       }
/*     */     }
/* 123 */     setJsonString("{success:true}");
/* 124 */     return "success";
/*     */   }
/*     */ 
/*     */   public String icon()
/*     */   {
/* 132 */     setNews((News)this.newsService.get(getNewsId()));
/* 133 */     this.news.setSubjectIcon("");
/* 134 */     this.newsService.save(this.news);
/* 135 */     return "success";
/*     */   }
/*     */ 
/*     */   public String search()
/*     */   {
/* 142 */     PagingBean pb = getInitPagingBean();
/* 143 */     String searchContent = getRequest().getParameter("searchContent");
/* 144 */     String isNotice = getRequest().getParameter("isNotice");
/* 145 */     List list = this.newsService.findBySearch(new Short(isNotice), searchContent, pb);
/*     */ 
/* 147 */     Type type = new TypeToken() {  }
/* 147 */     .getType();
/* 148 */     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
/* 149 */       .append(pb.getTotalItems()).append(",result:");
/*     */ 
/* 151 */     Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
/* 152 */     buff.append(gson.toJson(list, type));
/* 153 */     buff.append("}");
/*     */ 
/* 155 */     this.jsonString = buff.toString();
/* 156 */     return "success";
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.action.info.NewsAction
 * JD-Core Version:    0.6.0
 */