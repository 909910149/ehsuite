package com.htsoft.oa.dao.hrm;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.hrm.Job;
import java.util.List;

public abstract interface JobDao extends BaseDao<Job>
{
  public abstract List<Job> findByDep(Long paramLong);

  public abstract List<Job> findByCondition(Long paramLong);

  public abstract void edit(Job paramJob);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.hrm.JobDao
 * JD-Core Version:    0.6.0
 */