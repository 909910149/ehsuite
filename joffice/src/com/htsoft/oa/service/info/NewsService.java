package com.htsoft.oa.service.info;

import com.htsoft.core.service.BaseService;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.info.News;
import java.util.List;

public abstract interface NewsService extends BaseService<News>
{
  public abstract List<News> findByTypeId(Long paramLong, PagingBean paramPagingBean);

  public abstract List<News> findBySearch(Short paramShort, String paramString, PagingBean paramPagingBean);

  public abstract List<News> findImageNews(Long paramLong, PagingBean paramPagingBean);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.info.NewsService
 * JD-Core Version:    0.6.0
 */