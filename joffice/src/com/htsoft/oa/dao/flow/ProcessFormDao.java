package com.htsoft.oa.dao.flow;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.flow.ProcessForm;
import java.util.List;

public abstract interface ProcessFormDao extends BaseDao<ProcessForm>
{
  public abstract List getByRunId(Long paramLong);

  public abstract ProcessForm getByRunIdActivityName(Long paramLong, String paramString);

  public abstract Long getActvityExeTimes(Long paramLong, String paramString);

  public abstract ProcessForm getByTaskId(String paramString);

  public abstract ProcessForm getByRunIdTaskName(Long paramLong, String paramString);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.flow.ProcessFormDao
 * JD-Core Version:    0.6.0
 */