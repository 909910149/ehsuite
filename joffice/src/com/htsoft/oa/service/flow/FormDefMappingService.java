package com.htsoft.oa.service.flow;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.flow.FormDefMapping;

public abstract interface FormDefMappingService extends BaseService<FormDefMapping>
{
  public abstract FormDefMapping getByDeployId(String paramString);

  public abstract FormDefMapping findByDefId(Long paramLong);

  public abstract boolean formDefHadMapping(Long paramLong);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.flow.FormDefMappingService
 * JD-Core Version:    0.6.0
 */