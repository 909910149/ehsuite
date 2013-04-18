package com.htsoft.oa.dao.system;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.system.DepUsers;
import java.util.List;

public abstract interface DepUsersDao extends BaseDao<DepUsers>
{
  public abstract List<DepUsers> findByDepartment(String paramString, PagingBean paramPagingBean);

  public abstract List<DepUsers> search(String paramString, DepUsers paramDepUsers, PagingBean paramPagingBean);

  public abstract List<DepUsers> findByUserIdDep(Long paramLong);

  public abstract Boolean existsDep(Long paramLong1, Long paramLong2);

  public abstract String add(DepUsers paramDepUsers);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.system.DepUsersDao
 * JD-Core Version:    0.6.0
 */