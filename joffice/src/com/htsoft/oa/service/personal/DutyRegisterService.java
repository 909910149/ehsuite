package com.htsoft.oa.service.personal;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.personal.DutyRegister;
import com.htsoft.oa.model.system.AppUser;
import java.util.Date;

public abstract interface DutyRegisterService extends BaseService<DutyRegister>
{
  public abstract void signInOff(Long paramLong, Short paramShort, AppUser paramAppUser, Date paramDate);

  public abstract DutyRegister getTodayUserRegister(Long paramLong1, Short paramShort, Long paramLong2);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.personal.DutyRegisterService
 * JD-Core Version:    0.6.0
 */