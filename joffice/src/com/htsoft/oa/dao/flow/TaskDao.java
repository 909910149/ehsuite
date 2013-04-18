package com.htsoft.oa.dao.flow;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.flow.JbpmTask;
import java.util.List;
import org.jbpm.api.Execution;
import org.jbpm.pvm.internal.model.ExecutionImpl;
import org.jbpm.pvm.internal.task.TaskImpl;

public abstract interface TaskDao extends BaseDao<TaskImpl>
{
  public abstract List<TaskImpl> getTasksByUserId(String paramString, PagingBean paramPagingBean);

  public abstract List<TaskImpl> getAllTasks(String paramString, PagingBean paramPagingBean);

  public abstract List<JbpmTask> getByActivityNameVarKeyLongVal(String paramString1, String paramString2, Long paramLong);

  public abstract List<Long> getGroupByTask(Long paramLong);

  public abstract List<Long> getUserIdByTask(Long paramLong);

  public abstract List<TaskImpl> getCandidateTasks(String paramString, PagingBean paramPagingBean);

  public abstract List<TaskImpl> getPersonTasks(String paramString, PagingBean paramPagingBean);

  public abstract Execution getExecutionByDbid(Long paramLong);

  public abstract void save(ExecutionImpl paramExecutionImpl);

  public abstract void removeExeByParentId(Long paramLong);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.flow.TaskDao
 * JD-Core Version:    0.6.0
 */