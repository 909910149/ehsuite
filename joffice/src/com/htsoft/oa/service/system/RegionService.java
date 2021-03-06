package com.htsoft.oa.service.system;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.system.Region;
import java.util.List;

public abstract interface RegionService extends BaseService<Region>
{
  public abstract List<Region> getProvince();

  public abstract List<Region> getCity(Long paramLong);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.system.RegionService
 * JD-Core Version:    0.6.0
 */