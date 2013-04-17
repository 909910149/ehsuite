package com.htsoft.oa.dao.system;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.model.system.Department;
import java.util.List;
import java.util.Set;

public abstract interface AppUserDao extends BaseDao<AppUser>
{
  public abstract AppUser findByUserName(String paramString);

  public abstract List<AppUser> findByDepartment(String paramString, PagingBean paramPagingBean);

  public abstract List<AppUser> findByDepartment(String paramString1, String paramString2, PagingBean paramPagingBean);

  public abstract List<AppUser> findByDepartment(String paramString);

  public abstract List<AppUser> findByDepartment(Department paramDepartment);

  public abstract List<AppUser> findByRole(Long paramLong);

  public abstract List<AppUser> findByRole(Long paramLong, PagingBean paramPagingBean);

  public abstract List<AppUser> findByRoleId(Long paramLong);

  public abstract List<AppUser> findSubAppUser(String paramString, Set<Long> paramSet, PagingBean paramPagingBean);

  public abstract List<AppUser> findSubAppUserByRole(Long paramLong, Set<Long> paramSet, PagingBean paramPagingBean);

  public abstract List<AppUser> findByDepId(Long paramLong);

  public abstract List<AppUser> findUsersByRoleIds(String paramString);

  public abstract List<AppUser> findRelativeUsersByUserId(Long paramLong, Short paramShort);

  public abstract List<AppUser> getUsersByRoleId(Long paramLong);

  public abstract List<AppUser> findByfullName(String paramString);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.system.AppUserDao
 * JD-Core Version:    0.6.0
 */