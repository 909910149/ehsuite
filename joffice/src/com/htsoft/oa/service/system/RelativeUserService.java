package com.htsoft.oa.service.system;

import com.htsoft.core.service.BaseService;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.model.system.RelativeUser;
import java.util.List;
import java.util.Set;

public abstract interface RelativeUserService extends BaseService<RelativeUser>
{
  public abstract AppUser judge(Long paramLong1, Long paramLong2);

  public abstract List<AppUser> findByUserIdReJobId(Long paramLong1, Long paramLong2);

  public abstract List<RelativeUser> list(Long paramLong1, Long paramLong2, PagingBean paramPagingBean);

  public abstract List<Long> getReJobUserIds(Long paramLong1, Long paramLong2);

  public abstract Set<AppUser> getUpUser(Long paramLong);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.system.RelativeUserService
 * JD-Core Version:    0.6.0
 */