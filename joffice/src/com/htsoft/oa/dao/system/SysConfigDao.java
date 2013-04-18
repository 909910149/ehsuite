package com.htsoft.oa.dao.system;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.system.SysConfig;
import java.util.List;

public abstract interface SysConfigDao extends BaseDao<SysConfig>
{
  public abstract SysConfig findByKey(String paramString);

  public abstract List<SysConfig> findConfigByTypeKey(String paramString);

  public abstract List findTypeKeys();
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.system.SysConfigDao
 * JD-Core Version:    0.6.0
 */