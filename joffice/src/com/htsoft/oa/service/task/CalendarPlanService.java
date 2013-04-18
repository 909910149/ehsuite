package com.htsoft.oa.service.task;

import com.htsoft.core.service.BaseService;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.task.CalendarPlan;
import java.util.Date;
import java.util.List;

public abstract interface CalendarPlanService extends BaseService<CalendarPlan>
{
  public abstract List<CalendarPlan> getTodayPlans(Long paramLong, PagingBean paramPagingBean);

  public abstract List<CalendarPlan> getByPeriod(Long paramLong, Date paramDate1, Date paramDate2);

  public abstract List showCalendarPlanByUserId(Long paramLong, PagingBean paramPagingBean);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.task.CalendarPlanService
 * JD-Core Version:    0.6.0
 */