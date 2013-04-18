package com.htsoft.oa.dao.flow;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.flow.ProDefRights;

public abstract interface ProDefRightsDao extends BaseDao<ProDefRights>
{
  public abstract ProDefRights findByDefId(Long paramLong);

  public abstract ProDefRights findByTypeId(Long paramLong);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.flow.ProDefRightsDao
 * JD-Core Version:    0.6.0
 */