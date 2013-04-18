package com.htsoft.oa.service.archive;

import com.htsoft.core.service.BaseService;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.action.flow.FlowRunInfo;
import com.htsoft.oa.model.archive.Archives;
import com.htsoft.oa.model.system.AppRole;
import java.util.List;
import java.util.Set;

public abstract interface ArchivesService extends BaseService<Archives>
{
  public abstract List<Archives> findByUserOrRole(Long paramLong, Set<AppRole> paramSet, PagingBean paramPagingBean);

  public abstract Integer startArchFlow(FlowRunInfo paramFlowRunInfo);

  public abstract Integer setRunId(FlowRunInfo paramFlowRunInfo);

  public abstract Integer saveStatus(FlowRunInfo paramFlowRunInfo);

  public abstract Integer endFlow(FlowRunInfo paramFlowRunInfo);

  public abstract Integer startRecFlow(FlowRunInfo paramFlowRunInfo);

  public abstract Integer endRecFlow(FlowRunInfo paramFlowRunInfo);

  public abstract Integer saveDispatch(FlowRunInfo paramFlowRunInfo);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.archive.ArchivesService
 * JD-Core Version:    0.6.0
 */