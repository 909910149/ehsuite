package com.htsoft.oa.dao.system;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.system.Region;
import java.util.List;

public abstract interface RegionDao extends BaseDao<Region>
{
  public abstract List<Region> getProvince();

  public abstract List<Region> getCity(Long paramLong);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.system.RegionDao
 * JD-Core Version:    0.6.0
 */