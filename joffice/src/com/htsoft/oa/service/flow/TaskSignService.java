package com.htsoft.oa.service.flow;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.flow.TaskSign;

public abstract interface TaskSignService extends BaseService<TaskSign>
{
  public abstract TaskSign findByAssignId(Long paramLong);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.flow.TaskSignService
 * JD-Core Version:    0.6.0
 */