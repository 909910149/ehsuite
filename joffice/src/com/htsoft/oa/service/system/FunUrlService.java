package com.htsoft.oa.service.system;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.system.FunUrl;

public abstract interface FunUrlService extends BaseService<FunUrl>
{
  public abstract FunUrl getByPathFunId(String paramString, Long paramLong);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.system.FunUrlService
 * JD-Core Version:    0.6.0
 */