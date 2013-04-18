package com.htsoft.oa.dao.system;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.system.FunUrl;

public abstract interface FunUrlDao extends BaseDao<FunUrl>
{
  public abstract FunUrl getByPathFunId(String paramString, Long paramLong);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.system.FunUrlDao
 * JD-Core Version:    0.6.0
 */