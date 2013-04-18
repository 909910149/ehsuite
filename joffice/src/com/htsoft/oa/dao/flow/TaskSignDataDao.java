package com.htsoft.oa.dao.flow;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.flow.TaskSignData;
import java.util.List;

public abstract interface TaskSignDataDao extends BaseDao<TaskSignData>
{
  public abstract Long getVoteCounts(String paramString, Short paramShort);

  public abstract List<TaskSignData> getByTaskId(String paramString);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.flow.TaskSignDataDao
 * JD-Core Version:    0.6.0
 */