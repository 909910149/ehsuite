package com.htsoft.oa.dao.task;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.task.PlanAttend;
import java.util.List;

public abstract interface PlanAttendDao extends BaseDao<PlanAttend>
{
  public abstract List<PlanAttend> FindPlanAttend(Long paramLong, Short paramShort1, Short paramShort2);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.task.PlanAttendDao
 * JD-Core Version:    0.6.0
 */