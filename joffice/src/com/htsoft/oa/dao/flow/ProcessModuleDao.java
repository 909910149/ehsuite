package com.htsoft.oa.dao.flow;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.flow.ProcessModule;

public abstract interface ProcessModuleDao extends BaseDao<ProcessModule>
{
  public abstract ProcessModule getByKey(String paramString);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.flow.ProcessModuleDao
 * JD-Core Version:    0.6.0
 */