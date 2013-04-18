package com.htsoft.oa.dao.hrm;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.hrm.StandSalary;
import java.util.List;

public abstract interface StandSalaryDao extends BaseDao<StandSalary>
{
  public abstract boolean checkStandNo(String paramString);

  public abstract List<StandSalary> findByPassCheck();
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.hrm.StandSalaryDao
 * JD-Core Version:    0.6.0
 */