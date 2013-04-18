package com.htsoft.oa.service.customer;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.customer.Project;

public abstract interface ProjectService extends BaseService<Project>
{
  public abstract boolean checkProjectNo(String paramString);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.customer.ProjectService
 * JD-Core Version:    0.6.0
 */