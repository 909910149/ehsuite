package com.htsoft.oa.service.hrm;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.hrm.Job;
import java.util.List;

public abstract interface JobService extends BaseService<Job>
{
  public abstract List<Job> findByDep(Long paramLong);

  public abstract List<Job> findByCondition(Long paramLong);

  public abstract void edit(Job paramJob);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.hrm.JobService
 * JD-Core Version:    0.6.0
 */