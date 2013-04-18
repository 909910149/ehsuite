package com.htsoft.oa.service.flow;

import com.htsoft.core.jbpm.jpdl.Node;
import com.htsoft.oa.action.flow.FlowRunInfo;
import com.htsoft.oa.model.flow.ProDefinition;
import com.htsoft.oa.model.flow.ProUserAssign;
import com.htsoft.oa.model.flow.ProcessRun;
import com.htsoft.oa.model.system.AppUser;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.model.Transition;
import org.jbpm.api.task.Task;

public abstract interface JbpmService
{
  public abstract ProcessInstance getProcessInstanceByExeId(String paramString);

  public abstract List<Node> getTaskNodesByDefId(Long paramLong);

  public abstract List<Node> getTaskNodesByDefId(Long paramLong, boolean paramBoolean1, boolean paramBoolean2);

  public abstract Task getTaskById(String paramString);

  public abstract void assignTask(String paramString1, String paramString2);

  public abstract List<Node> getFormNodesByDeployId(Long paramLong);

  public abstract ProDefinition getProDefinitionByKey(String paramString);

  public abstract ProcessDefinition getProcessDefinitionByTaskId(String paramString);

  public abstract ProcessDefinition getProcessDefinitionByKey(String paramString);

  public abstract ProcessDefinition getProcessDefinitionByDefId(Long paramLong);

  public abstract String getDefinitionXmlByDefId(Long paramLong);

  public abstract String getDefinitionXmlByDpId(String paramString);

  public abstract String getDefinitionXmlByPiId(String paramString);

  public abstract String getDefinitionXmlByExeId(String paramString);

  public abstract ProcessInstance getProcessInstance(String paramString);

  public abstract ProcessInstance getProcessInstanceByTaskId(String paramString);

  public abstract void doUnDeployProDefinition(Long paramLong);

  public abstract ProDefinition saveOrUpdateDeploy(ProDefinition paramProDefinition);

  public abstract ProcessRun doStartProcess(FlowRunInfo paramFlowRunInfo);

  public abstract ProcessRun doNextStep(FlowRunInfo paramFlowRunInfo);

  public abstract List<Transition> getTransitionsForSignalProcess(String paramString);

  public abstract List<Transition> getTransitionsByTaskId(String paramString);

  public abstract List<Transition> getFreeTransitionsByTaskId(String paramString);

  public abstract String getNodeType(String paramString1, String paramString2);

  public abstract String getStartNodeName(ProDefinition paramProDefinition);

  public abstract String getProcessDefintionXMLByPiId(String paramString);

  public abstract List<Task> getTasksByPiId(String paramString);

  public abstract void completeTask(String paramString1, String paramString2, String paramString3, Map paramMap);

  public abstract void signalProcess(String paramString1, String paramString2, Map<String, Object> paramMap);

  public abstract void newSubTask(String paramString, Long[] paramArrayOfLong);

  public abstract void endProcessInstance(String paramString);

  public abstract void addAssignHandler(ProUserAssign paramProUserAssign);

  public abstract boolean isAllownBack(String paramString);

  public abstract Long getFlowStartUserId(String paramString);

  public abstract boolean isSignTask(String paramString);

  public abstract Set<AppUser> getNodeHandlerUsers(ProcessDefinition paramProcessDefinition, String paramString, Long paramLong);

  public abstract Set<AppUser> getNodeHandlerUsers(String paramString1, String paramString2);

  public abstract Set<AppUser> getNodeHandlerUsers(Long paramLong, String paramString);

  public abstract Object getVarByTaskIdVarName(String paramString1, String paramString2);

  public abstract List<Transition> getNodeOuterTrans(ProcessDefinition paramProcessDefinition, String paramString);

  public abstract List<String> getAssigneeByTaskId(String paramString);

  public abstract Task getParentTask(String paramString);

  public abstract List<Transition> getStartOutTransByDeployId(String paramString);

  public abstract Map<String, Object> getVarsByTaskId(String paramString);

  public abstract List<Transition> getInTransForTask(String paramString);

  public abstract void jumpToPreTask(String paramString1, String paramString2, String paramString3);

  public abstract void wirteDefXml(String paramString1, String paramString2);

  public abstract String getPreTask(String paramString);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.flow.JbpmService
 * JD-Core Version:    0.6.0
 */