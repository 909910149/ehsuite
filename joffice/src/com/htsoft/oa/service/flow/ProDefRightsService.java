package com.htsoft.oa.service.flow;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.flow.ProDefRights;

public abstract interface ProDefRightsService extends BaseService<ProDefRights>
{
  public abstract ProDefRights findByTypeId(Long paramLong);

  public abstract ProDefRights findByDefId(Long paramLong);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.flow.ProDefRightsService
 * JD-Core Version:    0.6.0
 */