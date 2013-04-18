package com.htsoft.oa.service.flow;

import com.htsoft.core.service.BaseService;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.action.flow.FlowRunInfo;
import com.htsoft.oa.model.flow.ProDefinition;
import com.htsoft.oa.model.flow.ProcessRun;
import java.util.List;

public abstract interface ProcessRunService extends BaseService<ProcessRun>
{
  public abstract ProcessRun getInitNewProcessRun(ProDefinition paramProDefinition);

  public abstract ProcessRun getInitFromFlowRunInfo(FlowRunInfo paramFlowRunInfo);

  public abstract ProcessRun getByExeId(String paramString);

  public abstract ProcessRun getByTaskId(String paramString);

  public abstract ProcessRun getByPiId(String paramString);

  public abstract void removeByDefId(Long paramLong);

  public abstract List<ProcessRun> getByUserIdSubject(Long paramLong, String paramString, PagingBean paramPagingBean);

  public abstract Boolean checkRun(Long paramLong);

  public abstract Integer countRunningProcess(Long paramLong);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.flow.ProcessRunService
 * JD-Core Version:    0.6.0
 */