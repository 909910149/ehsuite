/*    */ package com.htsoft.oa.service.info.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.core.web.paging.PagingBean;
/*    */ import com.htsoft.oa.dao.info.NewsDao;
/*    */ import com.htsoft.oa.model.info.News;
/*    */ import com.htsoft.oa.service.info.NewsService;
/*    */ import java.util.List;
/*    */ 
/*    */ public class NewsServiceImpl extends BaseServiceImpl<News>
/*    */   implements NewsService
/*    */ {
/*    */   private NewsDao newsDao;
/*    */ 
/*    */   public NewsServiceImpl(NewsDao dao)
/*    */   {
/* 19 */     super(dao);
/* 20 */     this.newsDao = dao;
/*    */   }
/*    */ 
/*    */   public List<News> findByTypeId(Long typeId, PagingBean pb)
/*    */   {
/* 25 */     return this.newsDao.findByTypeId(typeId, pb);
/*    */   }
/*    */ 
/*    */   public List<News> findBySearch(Short isNotice, String searchContent, PagingBean pb)
/*    */   {
/* 30 */     return this.newsDao.findBySearch(isNotice, searchContent, pb);
/*    */   }
/*    */ 
/*    */   public List<News> findImageNews(Long sectionId, PagingBean pb)
/*    */   {
/* 35 */     return this.newsDao.findImageNews(sectionId, pb);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.info.impl.NewsServiceImpl
 * JD-Core Version:    0.6.0
 */