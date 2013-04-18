package com.htsoft.oa.dao.admin;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.admin.ConfPrivilege;

public abstract interface ConfPrivilegeDao extends BaseDao<ConfPrivilege>
{
  public abstract Short getPrivilege(Long paramLong1, Long paramLong2, Short paramShort);

  public abstract void delete(Long paramLong);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.admin.ConfPrivilegeDao
 * JD-Core Version:    0.6.0
 */