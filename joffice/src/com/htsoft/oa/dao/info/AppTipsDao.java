package com.htsoft.oa.dao.info;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.info.AppTips;
import java.util.List;

public abstract interface AppTipsDao extends BaseDao<AppTips>
{
  public abstract List<AppTips> findByName(String paramString);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.info.AppTipsDao
 * JD-Core Version:    0.6.0
 */