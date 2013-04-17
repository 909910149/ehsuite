package com.htsoft.oa.dao.flow;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.flow.RunData;
import java.util.List;

public abstract interface RunDataDao extends BaseDao<RunData>
{
  public abstract RunData getByRunIdFieldName(Long paramLong, String paramString);

  public abstract List<RunData> getByRunId(Long paramLong);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.flow.RunDataDao
 * JD-Core Version:    0.6.0
 */