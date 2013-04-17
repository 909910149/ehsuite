package com.htsoft.oa.dao.hrm;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.hrm.EmpProfile;

public abstract interface EmpProfileDao extends BaseDao<EmpProfile>
{
  public abstract boolean checkProfileNo(String paramString);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.hrm.EmpProfileDao
 * JD-Core Version:    0.6.0
 */