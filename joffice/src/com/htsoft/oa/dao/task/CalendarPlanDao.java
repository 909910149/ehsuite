package com.htsoft.oa.dao.task;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.task.CalendarPlan;
import java.util.Date;
import java.util.List;

public abstract interface CalendarPlanDao extends BaseDao<CalendarPlan>
{
  public abstract List<CalendarPlan> getTodayPlans(Long paramLong, PagingBean paramPagingBean);

  public abstract List<CalendarPlan> getByPeriod(Long paramLong, Date paramDate1, Date paramDate2);

  public abstract List showCalendarPlanByUserId(Long paramLong, PagingBean paramPagingBean);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.task.CalendarPlanDao
 * JD-Core Version:    0.6.0
 */