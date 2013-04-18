package com.htsoft.oa.service.system;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.system.RelativeJob;
import java.util.List;

public abstract interface RelativeJobService extends BaseService<RelativeJob>
{
  public abstract List<RelativeJob> findByParentId(Long paramLong);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.system.RelativeJobService
 * JD-Core Version:    0.6.0
 */