package com.htsoft.oa.dao.system;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.model.system.UserJob;
import java.util.List;

public abstract interface UserJobDao extends BaseDao<UserJob>
{
  public abstract Boolean IsExistsjob(Long paramLong1, Long paramLong2);

  public abstract List<UserJob> findByUserIdJobs(Long paramLong);

  public abstract String add(UserJob paramUserJob);

  public abstract List<Long> getUserIdsByJobId(Long paramLong);

  public abstract List<AppUser> getUsersByJobId(Long paramLong);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.system.UserJobDao
 * JD-Core Version:    0.6.0
 */