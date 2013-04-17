/*    */ package com.htsoft.oa.dao.info.impl;
/*    */ 
/*    */ import com.htsoft.core.Constants;
/*    */ import com.htsoft.core.dao.impl.BaseDaoImpl;
/*    */ import com.htsoft.core.web.paging.PagingBean;
/*    */ import com.htsoft.oa.dao.info.NewsDao;
/*    */ import com.htsoft.oa.model.info.News;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ 
/*    */ public class NewsDaoImpl extends BaseDaoImpl<News>
/*    */   implements NewsDao
/*    */ {
/*    */   public NewsDaoImpl()
/*    */   {
/* 19 */     super(News.class);
/*    */   }
/*    */ 
/*    */   public List<News> findByTypeId(Long typeId, PagingBean pb)
/*    */   {
/* 24 */     String hql = "from News n where n.newsType.typeId=?";
/* 25 */     Object[] params = { typeId };
/* 26 */     return findByHql("from News n where n.newsType.typeId=?", params, pb);
/*    */   }
/*    */ 
/*    */   public List<News> findBySearch(Short isNotice, String searchContent, PagingBean pb)
/*    */   {
/* 31 */     ArrayList params = new ArrayList();
/* 32 */     StringBuffer hql = new StringBuffer("from News n where n.isNotice= ? and n.status = ?");
/* 33 */     params.add(isNotice);
/* 34 */     params.add(Constants.FLAG_ACTIVATION);
/* 35 */     if (StringUtils.isNotEmpty(searchContent)) {
/* 36 */       hql.append(" and (n.subject like ? or n.content like ?)");
/* 37 */       params.add("%" + searchContent + "%");
/* 38 */       params.add("%" + searchContent + "%");
/*    */     }
/* 40 */     hql.append(" order by n.updateTime desc");
/* 41 */     return findByHql(hql.toString(), params.toArray(), pb);
/*    */   }
/*    */ 
/*    */   public List<News> findImageNews(Long sectionId, PagingBean pb)
/*    */   {
/* 46 */     String hql = "from News vo where  vo.section.sectionId = ? order by vo.updateTime desc";
/* 47 */     return findByHql(hql, new Object[] { sectionId }, pb);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.info.impl.NewsDaoImpl
 * JD-Core Version:    0.6.0
 */