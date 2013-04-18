package com.htsoft.oa.service.system;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.system.UserSub;
import java.util.List;
import java.util.Set;

public abstract interface UserSubService extends BaseService<UserSub>
{
  public abstract Set<Long> findAllUpUser(Long paramLong);

  public abstract List<Long> subUsers(Long paramLong);

  public abstract List<Long> upUser(Long paramLong);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.system.UserSubService
 * JD-Core Version:    0.6.0
 */