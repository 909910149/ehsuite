package com.htsoft.oa.service.communicate;

import com.htsoft.core.service.BaseService;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.communicate.OutMailUserSeting;
import java.util.List;

public abstract interface OutMailUserSetingService extends BaseService<OutMailUserSeting>
{
  public abstract OutMailUserSeting getByLoginId(Long paramLong);

  public abstract List findByUserAll();

  public abstract List<OutMailUserSeting> findByUserAll(String paramString, PagingBean paramPagingBean);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.communicate.OutMailUserSetingService
 * JD-Core Version:    0.6.0
 */