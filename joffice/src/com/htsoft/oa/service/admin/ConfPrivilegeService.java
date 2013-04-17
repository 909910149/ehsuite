package com.htsoft.oa.service.admin;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.admin.ConfPrivilege;

public abstract interface ConfPrivilegeService extends BaseService<ConfPrivilege>
{
  public abstract Short getPrivilege(Long paramLong, Short paramShort);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.admin.ConfPrivilegeService
 * JD-Core Version:    0.6.0
 */