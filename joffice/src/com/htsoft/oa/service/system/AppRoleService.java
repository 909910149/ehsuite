package com.htsoft.oa.service.system;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.system.AppRole;
import java.util.HashMap;
import java.util.Set;
import javax.jws.WebService;

@WebService
public abstract interface AppRoleService extends BaseService<AppRole>
{
  public abstract AppRole getByRoleName(String paramString);

  public abstract HashMap<String, Set<String>> getSecurityDataSource();

  public abstract AppRole getById(Long paramLong);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.system.AppRoleService
 * JD-Core Version:    0.6.0
 */