package com.htsoft.oa.service.flow;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.flow.TaskSignData;
import java.util.List;

public abstract interface TaskSignDataService extends BaseService<TaskSignData>
{
  public abstract void addVote(String paramString, Short paramShort);

  public abstract Long getVoteCounts(String paramString, Short paramShort);

  public abstract List<TaskSignData> getByTaskId(String paramString);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.flow.TaskSignDataService
 * JD-Core Version:    0.6.0
 */