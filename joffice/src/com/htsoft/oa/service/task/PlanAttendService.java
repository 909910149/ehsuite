package com.htsoft.oa.service.task;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.task.PlanAttend;

public abstract interface PlanAttendService extends BaseService<PlanAttend>
{
  public abstract boolean deletePlanAttend(Long paramLong, Short paramShort1, Short paramShort2);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.task.PlanAttendService
 * JD-Core Version:    0.6.0
 */