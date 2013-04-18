package com.htsoft.oa.dao.personal;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.personal.Duty;
import java.util.Date;
import java.util.List;

public abstract interface DutyDao extends BaseDao<Duty>
{
  public abstract List<Duty> getUserDutyByTime(Long paramLong, Date paramDate1, Date paramDate2);

  public abstract List<Duty> getCurUserDuty(Long paramLong);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.personal.DutyDao
 * JD-Core Version:    0.6.0
 */