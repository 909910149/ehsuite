package com.htsoft.oa.service.flow;

import com.htsoft.core.model.DynaModel;
import com.htsoft.oa.action.flow.FlowRunInfo;

public abstract interface FlowFormService
{
  public abstract DynaModel doSaveData(FlowRunInfo paramFlowRunInfo);

  public abstract boolean deleteItems(String paramString, Long paramLong);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.flow.FlowFormService
 * JD-Core Version:    0.6.0
 */