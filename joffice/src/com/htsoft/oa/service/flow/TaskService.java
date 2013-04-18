package com.htsoft.oa.service.flow;

import com.htsoft.core.jbpm.pv.TaskInfo;
import com.htsoft.core.service.BaseService;
import com.htsoft.core.web.paging.PagingBean;
import java.util.List;
import java.util.Set;
import org.jbpm.api.Execution;
import org.jbpm.pvm.internal.model.ExecutionImpl;
import org.jbpm.pvm.internal.task.TaskImpl;

public abstract interface TaskService extends BaseService<TaskImpl>
{
  public abstract List<TaskInfo> getAllTaskInfos(String paramString, PagingBean paramPagingBean);

  public abstract List<TaskImpl> getTasksByUserId(String paramString, PagingBean paramPagingBean);

  public abstract List<TaskInfo> getTaskInfosByUserId(String paramString, PagingBean paramPagingBean);

  public abstract Set<Long> getHastenByActivityNameVarKeyLongVal(String paramString1, String paramString2, Long paramLong);

  public abstract List<TaskImpl> getCandidateTasks(String paramString, PagingBean paramPagingBean);

  public abstract List<TaskImpl> getPersonTasks(String paramString, PagingBean paramPagingBean);

  public abstract Execution getExecutionByDbid(Long paramLong);

  public abstract void save(ExecutionImpl paramExecutionImpl);

  public abstract void removeExeByParentId(Long paramLong);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.flow.TaskService
 * JD-Core Version:    0.6.0
 */