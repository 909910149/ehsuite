package com.htsoft.oa.dao.system;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.system.AppFunction;

public abstract interface AppFunctionDao extends BaseDao<AppFunction>
{
  public abstract AppFunction getByKey(String paramString);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.system.AppFunctionDao
 * JD-Core Version:    0.6.0
 */