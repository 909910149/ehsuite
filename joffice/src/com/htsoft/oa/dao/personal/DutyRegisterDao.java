package com.htsoft.oa.dao.personal;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.personal.DutyRegister;

public abstract interface DutyRegisterDao extends BaseDao<DutyRegister>
{
  public abstract DutyRegister getTodayUserRegister(Long paramLong1, Short paramShort, Long paramLong2);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.personal.DutyRegisterDao
 * JD-Core Version:    0.6.0
 */