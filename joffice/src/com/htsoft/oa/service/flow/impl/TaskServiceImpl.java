/*     */ package com.htsoft.oa.service.flow.impl;
/*     */ 
/*     */ import com.htsoft.core.jbpm.pv.TaskInfo;
/*     */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*     */ import com.htsoft.core.web.paging.PagingBean;
/*     */ import com.htsoft.oa.dao.flow.TaskDao;
/*     */ import com.htsoft.oa.model.flow.JbpmTask;
/*     */ import com.htsoft.oa.model.flow.ProcessRun;
/*     */ import com.htsoft.oa.model.system.AppUser;
/*     */ import com.htsoft.oa.service.flow.ProcessRunService;
/*     */ import com.htsoft.oa.service.flow.TaskService;
/*     */ import com.htsoft.oa.service.system.AppUserService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Resource;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.jbpm.api.Execution;
/*     */ import org.jbpm.pvm.internal.model.ExecutionImpl;
/*     */ import org.jbpm.pvm.internal.task.TaskImpl;
/*     */ 
/*     */ public class TaskServiceImpl extends BaseServiceImpl<TaskImpl>
/*     */   implements TaskService
/*     */ {
/*     */ 
/*     */   @Resource
/*     */   private ProcessRunService processRunService;
/*     */   private TaskDao dao;
/*     */ 
/*     */   @Resource
/*     */   private AppUserService appUserService;
/*     */ 
/*     */   public TaskServiceImpl(TaskDao dao)
/*     */   {
/*  34 */     super(dao);
/*  35 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */   public List<TaskImpl> getTasksByUserId(String userId, PagingBean pb)
/*     */   {
/*  42 */     return this.dao.getTasksByUserId(userId, pb);
/*     */   }
/*     */ 
/*     */   public List<TaskInfo> getAllTaskInfos(String taskName, PagingBean pb)
/*     */   {
/*  51 */     List list = this.dao.getAllTasks(taskName, pb);
/*  52 */     List taskInfoList = constructTaskInfos(list);
/*  53 */     return taskInfoList;
/*     */   }
/*     */ 
/*     */   protected List<TaskInfo> constructTaskInfos(List<TaskImpl> taskImpls)
/*     */   {
/*  58 */     List taskInfoList = new ArrayList();
/*  59 */     for (TaskImpl taskImpl : taskImpls) {
/*  60 */       TaskInfo taskInfo = new TaskInfo(taskImpl);
/*  61 */       if ((taskImpl.getAssignee() != null) && (!taskImpl.getAssignee().trim().equalsIgnoreCase("null"))) {
/*     */         try {
/*  63 */           AppUser user = (AppUser)this.appUserService.get(new Long(taskImpl.getAssignee()));
/*  64 */           taskInfo.setAssignee(user.getFullname());
/*     */         } catch (Exception ex) {
/*  66 */           this.logger.error(ex);
/*     */         }
/*     */       }
/*  69 */       if (taskImpl.getSuperTask() != null) {
/*  70 */         taskImpl = taskImpl.getSuperTask();
/*     */       }
/*  72 */       ProcessRun processRun = this.processRunService.getByPiId(taskInfo.getPiId());
/*  73 */       if (processRun != null) {
/*  74 */         taskInfo.setTaskName(processRun.getSubject() + "--" + taskImpl.getActivityName());
/*  75 */         taskInfo.setActivityName(taskImpl.getActivityName());
/*     */       }
/*     */ 
/*  78 */       taskInfoList.add(taskInfo);
/*     */     }
/*  80 */     return taskInfoList;
/*     */   }
/*     */ 
/*     */   public List<TaskInfo> getTaskInfosByUserId(String userId, PagingBean pb)
/*     */   {
/*  86 */     List list = getTasksByUserId(userId, pb);
/*     */ 
/* 104 */     return constructTaskInfos(list);
/*     */   }
/*     */ 
/*     */   public Set<Long> getHastenByActivityNameVarKeyLongVal(String activityName, String varKey, Long value)
/*     */   {
/* 112 */     List<JbpmTask> jtasks = this.dao.getByActivityNameVarKeyLongVal(activityName, varKey, value);
/* 113 */     Set userIds = new HashSet();
/* 114 */     for (JbpmTask jtask : jtasks) {
/* 115 */       if (jtask.getAssignee() == null) {
/* 116 */         List userlist = this.dao.getUserIdByTask(jtask.getTaskId());
/* 117 */         userIds.addAll(userlist);
/* 118 */         List<Long> groupList = this.dao.getGroupByTask(jtask.getTaskId());
/* 119 */         for (Long l : groupList) {
/* 120 */           List<AppUser> uList = this.appUserService.findByRoleId(l);
/* 121 */           List idList = new ArrayList();
/* 122 */           for (AppUser appUser : uList) {
/* 123 */             idList.add(appUser.getUserId());
/*     */           }
/* 125 */           userIds.addAll(idList);
/*     */         }
/*     */       } else {
/* 128 */         userIds.add(new Long(jtask.getAssignee()));
/*     */       }
/*     */     }
/* 131 */     return userIds;
/*     */   }
/*     */ 
/*     */   public List<TaskImpl> getCandidateTasks(String userId, PagingBean pb) {
/* 135 */     return this.dao.getCandidateTasks(userId, pb);
/*     */   }
/*     */ 
/*     */   public List<TaskImpl> getPersonTasks(String userId, PagingBean pb)
/*     */   {
/* 140 */     return this.dao.getPersonTasks(userId, pb);
/*     */   }
/*     */ 
/*     */   public Execution getExecutionByDbid(Long dbid)
/*     */   {
/* 149 */     return this.dao.getExecutionByDbid(dbid);
/*     */   }
/*     */ 
/*     */   public void save(ExecutionImpl executionImpl)
/*     */   {
/* 157 */     this.dao.save(executionImpl);
/*     */   }
/*     */ 
/*     */   public void removeExeByParentId(Long parentDbid)
/*     */   {
/* 165 */     this.dao.removeExeByParentId(parentDbid);
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.flow.impl.TaskServiceImpl
 * JD-Core Version:    0.6.0
 */