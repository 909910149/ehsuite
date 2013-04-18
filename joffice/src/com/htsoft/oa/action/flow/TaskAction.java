 package com.htsoft.oa.action.flow;
 
 import com.htsoft.core.util.ContextUtil;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.model.info.ShortMessage;
 import com.htsoft.oa.model.system.AppUser;
 import com.htsoft.oa.service.flow.JbpmService;
 import com.htsoft.oa.service.info.ShortMessageService;
 import java.util.Date;
 import java.util.Iterator;
 import java.util.List;
 import java.util.Set;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 import org.apache.commons.lang.StringUtils;
 import org.apache.commons.lang.time.DateUtils;
 import org.apache.commons.logging.Log;
 import org.jbpm.api.task.Task;
 
 public class TaskAction extends BaseAction
 {
 
   @Resource(name="flowTaskService")
   private com.htsoft.oa.service.flow.TaskService flowTaskService;
 
   @Resource
   private org.jbpm.api.TaskService taskService;
 
   @Resource
   private ShortMessageService shortMessageService;
 
   @Resource
   private JbpmService jbpmService;
 
   public String all()
   {
     String taskName = getRequest().getParameter("taskName");
     PagingBean pb = new PagingBean(this.start.intValue(), this.limit.intValue());
     List tasks = this.flowTaskService.getAllTaskInfos(taskName, pb);
     setJsonString(gsonFormat(tasks, pb.getTotalItems()));
     return "success";
   }
 
   public String users()
   {
     String taskId = getRequest().getParameter("taskId");
     String activityName = getRequest().getParameter("activityName");
     Set users = this.jbpmService.getNodeHandlerUsers(taskId, activityName);
     StringBuffer uIds = new StringBuffer();
     StringBuffer uNames = new StringBuffer();
     Iterator it = users.iterator();
     int i = 0;
     while (it.hasNext()) {
       AppUser user = (AppUser)it.next();
       if (i > 0) {
         uIds.append(",");
         uNames.append(",");
       }
       uIds.append(user.getUserId());
       uNames.append(user.getFullname());
       i++;
     }
 
     this.jsonString = ("{success:true,userIds:'" + uIds.toString() + "',userNames:'" + uNames.toString() + "'}");
 
     return "success";
   }
 
   public String due()
   {
     String taskIds = getRequest().getParameter("taskIds");
     String dueDateStr = getRequest().getParameter("dueDate");
     if (this.logger.isDebugEnabled()) {
       this.logger.debug("taskIds:" + taskIds + " dueDate:" + dueDateStr);
     }
     if (StringUtils.isNotEmpty(taskIds)) {
       String[] taskIdArr = taskIds.split("[,]");
       try {
         Date dueDate = DateUtils.parseDate(dueDateStr, new String[] { "yyyy-MM-dd HH:mm:ss" });
         for (String taskId : taskIdArr) {
           Task task = this.taskService.getTask(taskId);
           task.setDuedate(dueDate);
           this.taskService.saveTask(task);
         }
       } catch (Exception ex) {
         this.logger.error(ex.getMessage());
       }
     }
 
     return "success";
   }
 
   public String handler() {
     String taskIds = getRequest().getParameter("taskIds");
     String userId = getRequest().getParameter("userId");
 
     if (this.logger.isDebugEnabled()) {
       this.logger.debug("taskIds:" + taskIds + " userId:" + userId);
     }
     if (StringUtils.isNotEmpty(taskIds)) {
       String[] taskIdArr = taskIds.split("[,]");
       for (String taskId : taskIdArr) {
         this.taskService.assignTask(taskId, userId);
       }
     }
 
     return "success";
   }
 
   public String list()
   {
     PagingBean pb = new PagingBean(this.start.intValue(), this.limit.intValue());
     List tasks = this.flowTaskService.getTaskInfosByUserId(ContextUtil.getCurrentUserId().toString(), pb);
     setJsonString(gsonFormat(tasks, pb.getTotalItems()));
     return "success";
   }
 
   public String change() {
     HttpServletRequest request = getRequest();
     String taskId = request.getParameter("taskId");
     String userId = request.getParameter("userId");
     String curUserId = ContextUtil.getCurrentUserId().toString();
     Task task = this.taskService.getTask(taskId);
     if ((task != null) && (curUserId.equals(task.getAssignee()))) {
       this.taskService.assignTask(taskId, userId);
       String msg = request.getParameter("msg");
       if (StringUtils.isNotEmpty(msg))
       {
         this.shortMessageService.save(AppUser.SYSTEM_USER, userId, msg, ShortMessage.MSG_TYPE_TASK);
       }
     }
     setJsonString("{success:true}");
     return "success";
   }
 
   public String unlock()
   {
     String taskId = getRequest().getParameter("taskId");
     Task task = this.taskService.getTask(taskId);
 
     String curUserId = ContextUtil.getCurrentUserId().toString();
 
     if ((task != null) && (curUserId.equals(task.getAssignee()))) {
       this.taskService.assignTask(task.getId(), null);
       setJsonString("{success:true,unlocked:true}");
     } else {
       setJsonString("{success:true,unlocked:false}");
     }
 
     return "success";
   }
 
   public String lock()
   {
     String taskId = getRequest().getParameter("taskId");
     Task task = this.taskService.getTask(taskId);
 
     if ((task != null) && (task.getAssignee() == null)) {
       this.taskService.assignTask(task.getId(), ContextUtil.getCurrentUserId().toString());
       setJsonString("{success:true,hasAssigned:false}");
     } else {
       setJsonString("{success:true,hasAssigned:true}");
     }
 
     return "success";
   }
 
   public String display()
   {
     PagingBean pb = new PagingBean(0, 8);
     List tasks = this.flowTaskService.getTaskInfosByUserId(ContextUtil.getCurrentUserId().toString(), pb);
     getRequest().setAttribute("taskList", tasks);
     return "display";
   }
 
   public String check()
   {
     String taskId = getRequest().getParameter("taskId");
     Task task = this.taskService.getTask(taskId);
     String cruUserId = ContextUtil.getCurrentUserId().toString();
     if ((task != null) && (task.getAssignee() != null) && (task.getAssignee().equals(cruUserId))) {
       setJsonString("{success:true}");
     } else if ((task != null) && (task.getAssignee() == null)) {
       this.taskService.assignTask(task.getId(), cruUserId);
       setJsonString("{success:true,assigned:true}");
     } else {
       setJsonString("{success:true,assigned:false}");
     }
     return "success";
   }
 }

