package com.htsoft.oa.dao.flow;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.flow.TaskSign;

public abstract interface TaskSignDao extends BaseDao<TaskSign>
{
  public abstract TaskSign findByAssignId(Long paramLong);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.flow.TaskSignDao
 * JD-Core Version:    0.6.0
 */