package com.htsoft.oa.dao.customer;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.customer.Project;

public abstract interface ProjectDao extends BaseDao<Project>
{
  public abstract boolean checkProjectNo(String paramString);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.customer.ProjectDao
 * JD-Core Version:    0.6.0
 */