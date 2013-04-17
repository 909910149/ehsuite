package com.htsoft.oa.service.system;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.system.SysConfig;
import java.util.Map;

public abstract interface SysConfigService extends BaseService<SysConfig>
{
  public abstract SysConfig findByKey(String paramString);

  public abstract Map findByType();
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.system.SysConfigService
 * JD-Core Version:    0.6.0
 */