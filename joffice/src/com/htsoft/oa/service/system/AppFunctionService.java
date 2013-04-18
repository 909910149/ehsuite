package com.htsoft.oa.service.system;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.system.AppFunction;

public abstract interface AppFunctionService extends BaseService<AppFunction>
{
  public abstract AppFunction getByKey(String paramString);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.system.AppFunctionService
 * JD-Core Version:    0.6.0
 */