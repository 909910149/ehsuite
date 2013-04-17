package com.htsoft.oa.service.flow;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.flow.RunData;
import java.util.Map;

public abstract interface RunDataService extends BaseService<RunData>
{
  public abstract RunData getByRunIdFieldName(Long paramLong, String paramString);

  public abstract void saveFlowVars(Long paramLong, Map<String, Object> paramMap);

  public abstract Map<String, Object> getMapByRunId(Long paramLong);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.flow.RunDataService
 * JD-Core Version:    0.6.0
 */