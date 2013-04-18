 package com.htsoft.oa.action.flow;
 
 import com.google.gson.Gson;
 import com.google.gson.GsonBuilder;
 import com.htsoft.core.command.QueryFilter;
 import com.htsoft.core.jbpm.pv.TaskInfo;
 import com.htsoft.core.util.ContextUtil;
 import com.htsoft.core.util.JsonUtil;
 import com.htsoft.core.util.StringUtil;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.model.flow.ProDefinition;
 import com.htsoft.oa.model.flow.ProcessRun;
 import com.htsoft.oa.model.system.AppUser;
 import com.htsoft.oa.service.flow.HistoryTaskService;
 import com.htsoft.oa.service.flow.JbpmService;
 import com.htsoft.oa.service.flow.ProcessRunService;
 import com.htsoft.oa.service.system.AppUserService;
 import flexjson.JSONSerializer;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Iterator;
 import java.util.List;
 import java.util.Set;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 import org.apache.commons.logging.Log;
 import org.jbpm.api.ProcessInstance;
 import org.jbpm.api.model.Activity;
 import org.jbpm.api.model.Transition;
 import org.jbpm.api.task.Task;
 import org.jbpm.pvm.internal.history.model.HistoryTaskInstanceImpl;
 import org.jbpm.pvm.internal.task.ParticipationImpl;
 import org.jbpm.pvm.internal.task.TaskImpl;
 
 public class ProcessRunAction extends BaseAction
 {
 
   @Resource
   private ProcessRunService processRunService;
   private ProcessRun processRun;
 
   @Resource
   private JbpmService jbpmService;
 
   @Resource
   private HistoryTaskService historyTaskService;
 
   @Resource
   private AppUserService appUserService;
   private Long runId;
 
   public Long getRunId()
   {
     return this.runId;
   }
 
   public void setRunId(Long runId) {
     this.runId = runId;
   }
 
   public ProcessRun getProcessRun() {
     return this.processRun;
   }
 
   public void setProcessRun(ProcessRun processRun) {
     this.processRun = processRun;
   }
 
   public String myRunning()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     filter.setFilterName("myRunning");
     filter.addParamValue(ContextUtil.getCurrentUserId());
     filter.addParamValue(ProcessRun.RUN_STATUS_RUNNING);
 
     List<ProcessRun> processRunList = this.processRunService.getAll(filter);
 
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(filter.getPagingBean().getTotalItems()).append(",result:[");
     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 
     for (ProcessRun run : processRunList) {
       buff.append("{runId:'").append(run.getRunId()).append("',subject:'")
         .append(run.getSubject()).append("',createtime:'").append(sdf.format(run.getCreatetime()))
         .append("',piId:'").append(run.getPiId()).append("',defId:'").append(run.getProDefinition().getDefId())
         .append("',runStatus:'").append(run.getRunStatus()).append("'");
 
       List<Task> listTask = this.jbpmService.getTasksByPiId(run.getPiId());
       if (listTask != null) {
         String tasks = "";
         String usernames = "";
         int i = 0;
         for (Task task : listTask) {
           if (i++ > 0) {
             tasks = tasks + ",";
             usernames = usernames + ",";
           }
           tasks = tasks + task.getName();
           if ((task.getAssignee() != null) && (StringUtil.isNumeric(task.getAssignee()))) {
             AppUser appUser = (AppUser)this.appUserService.get(new Long(task.getAssignee()));
             usernames = usernames + appUser.getFullname();
           } else {
             TaskImpl taskImpl = (TaskImpl)task;
             Iterator it = taskImpl.getParticipations().iterator();
             while (it.hasNext()) {
               ParticipationImpl part = (ParticipationImpl)it.next();
               if (part.getUserId() != null) {
                 if (StringUtil.isNumeric(part.getUserId())) {
                   AppUser appUser = (AppUser)this.appUserService.get(new Long(part.getUserId()));
                   usernames = usernames + appUser.getFullname();
                 }
               } else {
                 if ((part.getGroupId() == null) || 
                   (!StringUtil.isNumeric(part.getGroupId()))) continue;
                 List<AppUser> users = this.appUserService.getUsersByRoleId(new Long(part.getGroupId()));
                 for (AppUser user : users) {
                   usernames = usernames + user.getFullname();
                 }
               }
             }
           }
         }
 
         buff.append(",tasks:'").append(tasks).append("'");
         buff.append(",exeUsers:'").append(usernames).append("'");
       }
       buff.append("},");
     }
 
     if (processRunList.size() > 0) {
       buff.deleteCharAt(buff.length() - 1);
     }
     buff.append("]");
     buff.append("}");
 
     this.jsonString = buff.toString();
 
     return "success";
   }
 
   public String list()
   {
     QueryFilter filter = new QueryFilter(getRequest());
 
     filter.addFilter("Q_appUser.userId_L_EQ", ContextUtil.getCurrentUserId().toString());
 
     List list = this.processRunService.getAll(filter);
 
     this.jsonString = formatRunList(list, Integer.valueOf(filter.getPagingBean().getTotalItems()));
 
     return "success";
   }
 
   private String formatRunList(List<ProcessRun> processRunList, Integer totalItems)
   {
     Gson gson = JsonUtil.getGson();
 
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(totalItems).append(",result:[");
 
     for (ProcessRun run : processRunList) {
       buff.append("{runId:").append(gson.toJson(run.getRunId())).append(",subject:")
         .append(gson.toJson(run.getSubject())).append(",createtime:").append(gson.toJson(run.getCreatetime()))
         .append(",piId:").append(gson.toJson(run.getPiId())).append(",defId:").append(gson.toJson(run.getProDefinition().getDefId()))
         .append(",runStatus:").append(gson.toJson(run.getRunStatus())).append("},");
     }
 
     if (processRunList.size() > 0) {
       buff.deleteCharAt(buff.length() - 1);
     }
     buff.append("]");
     buff.append("}");
     return buff.toString();
   }
 
   public String my()
   {
     QueryFilter filter = new QueryFilter(getRequest());
 
     filter.setFilterName("MyAttendProcessRun");
 
     filter.addParamValue(ContextUtil.getCurrentUserId());
 
     List processRunList = this.processRunService.getAll(filter);
 
     this.jsonString = formatRunList(processRunList, Integer.valueOf(filter.getPagingBean().getTotalItems()));
     return "success";
   }
 
   public String multiDel()
   {
     String[] ids = getRequest().getParameterValues("ids");
     if (ids != null) {
       for (String id : ids) {
         this.processRunService.remove(new Long(id));
       }
     }
     this.jsonString = "{success:true}";
     return "success";
   }
 
   public String get()
   {
     ProcessRun processRun = (ProcessRun)this.processRunService.get(this.runId);
 
     Gson gson = new Gson();
 
     StringBuffer sb = new StringBuffer("{success:true,data:");
     sb.append(gson.toJson(processRun));
     sb.append("}");
     setJsonString(sb.toString());
 
     return "success";
   }
 
   public String save()
   {
     this.processRunService.save(this.processRun);
     setJsonString("{success:true}");
     return "success";
   }
 
   public String instance() {
     QueryFilter filter = new QueryFilter(getRequest());
 
     List list = this.processRunService.getAll(filter);
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(filter.getPagingBean().getTotalItems()).append(
       ",result:");
 
     JSONSerializer serializer = JsonUtil.getJSONSerializer(new String[] { "createtime" })
       .exclude(new String[] { 
       "appUser", "processForms" });
 
     buff.append(serializer.serialize(list));
     buff.append("}");
     this.jsonString = buff.toString();
 
     return "success";
   }
 
   public String tasks() {
     String runId = getRequest().getParameter("runId");
     ProcessRun processRun = (ProcessRun)this.processRunService.get(new Long(runId));
     String piId = processRun.getPiId();
     List<Task> tasks = this.jbpmService.getTasksByPiId(piId);
     List list = new ArrayList();
 
     for (Task task : tasks) {
       list.add((TaskImpl)task);
     }
     List infos = constructTaskInfos(list, processRun);
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(list.size()).append(
       ",result:");
     Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
     buff.append(gson.toJson(infos));
     buff.append("}");
     this.jsonString = buff.toString();
     return "success";
   }
 
   protected List<TaskInfo> constructTaskInfos(List<TaskImpl> taskImpls, ProcessRun processRun) {
     List taskInfoList = new ArrayList();
     for (TaskImpl taskImpl : taskImpls) {
       TaskInfo taskInfo = new TaskInfo(taskImpl);
       if ((taskImpl.getAssignee() != null) && (!taskImpl.getAssignee().trim().equalsIgnoreCase("null"))) {
         try {
           AppUser user = (AppUser)this.appUserService.get(new Long(taskImpl.getAssignee()));
           taskInfo.setAssignee(user.getFullname());
         } catch (Exception ex) {
           this.logger.error(ex);
         }
       }
       if (taskImpl.getSuperTask() != null) {
         taskImpl = taskImpl.getSuperTask();
       }
       if (processRun != null) {
         taskInfo.setTaskName(processRun.getSubject() + "--" + taskImpl.getActivityName());
         taskInfo.setActivityName(taskImpl.getActivityName());
       }
 
       taskInfoList.add(taskInfo);
     }
     return taskInfoList;
   }
 
   public String end() {
     String runId = getRequest().getParameter("runIds");
     String[] ids = runId.split(",");
     for (String id : ids) {
       ProcessRun processRun = (ProcessRun)this.processRunService.get(new Long(id));
       if (processRun != null) {
         String piId = processRun.getPiId();
         try {
           ProcessInstance pi = this.jbpmService.getProcessInstance(piId);
           if (pi != null) {
             this.jbpmService.endProcessInstance(piId);
           }
           processRun.setRunStatus(ProcessRun.RUN_STATUS_FINISHED);
           this.processRunService.save(processRun);
         } catch (Exception e) {
           e.printStackTrace();
           setJsonString("{success:false}");
           return "success";
         }
       }
     }
     setJsonString("{success:true}");
     return "success";
   }
 
   public String rollback()
   {
     String preTaskName = null;
 
     ProcessRun processRun = (ProcessRun)this.processRunService.get(this.runId);
 
     List<Task> tasks = this.jbpmService.getTasksByPiId(processRun.getPiId());
     String assignee = ContextUtil.getCurrentUserId().toString();
     label458: for (Task task : tasks) {
       List<Transition> trans = this.jbpmService.getInTransForTask(task.getId());
       for (Transition tran : trans) {
         String preType = tran.getSource().getType();
         this.logger.info("pre node type:" + preType);
         List preTrans = new ArrayList();
         int i = 0;
         if (("decision".equals(preType)) || ("fork".equals(preType))) {
           Activity source = tran.getSource();
           preTrans = source.getIncomingTransitions();
           i = 0;
         }while (true) { Transition tr = (Transition)preTrans.get(i);
           String outcome = tr.getName();
           String activityName = tr.getSource().getName();
           List list = this.historyTaskService.getByPiIdAssigneeOutcome(processRun.getPiId(), assignee, activityName, outcome);
           if (list.size() > 0) {
             HistoryTaskInstanceImpl impl = (HistoryTaskInstanceImpl)list.get(0);
             preTaskName = impl.getActivityName();
             this.logger.info("allow back 2:" + impl.getActivityName());
           }
           else
           {
//             i++; if (i < preTrans.size()) continue; break;
//             if (!"task".equals(preType)) break;
             outcome = tran.getName();
             activityName = tran.getSource().getName();
             list = this.historyTaskService.getByPiIdAssigneeOutcome(processRun.getPiId(), assignee, activityName, outcome);
             if (list.size() <= 0) break;
             HistoryTaskInstanceImpl impl = (HistoryTaskInstanceImpl)list.get(0);
             preTaskName = impl.getActivityName();
             this.logger.info("allow back :" + impl.getActivityName());
             break label458;
           }
         }
       }
 
     }
 
     if (preTaskName != null) {
       this.logger.info("prepared to jump previous task node");
       this.jbpmService.jumpToPreTask(processRun.getPiId(), assignee, preTaskName);
       this.jsonString = "{success:true}";
     } else {
       this.jsonString = "{success:false}";
     }
 
     return "success";
   }
 }

