package com.htsoft.oa.service.personal;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.personal.Duty;
import java.util.Date;

public abstract interface DutyService extends BaseService<Duty>
{
  public abstract boolean isExistDutyForUser(Long paramLong, Date paramDate1, Date paramDate2);

  public abstract Duty getCurUserDuty(Long paramLong);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.personal.DutyService
 * JD-Core Version:    0.6.0
 */