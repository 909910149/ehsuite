package com.htsoft.oa.service.flow;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.flow.ProcessModule;

public abstract interface ProcessModuleService extends BaseService<ProcessModule>
{
  public abstract ProcessModule getByKey(String paramString);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.flow.ProcessModuleService
 * JD-Core Version:    0.6.0
 */