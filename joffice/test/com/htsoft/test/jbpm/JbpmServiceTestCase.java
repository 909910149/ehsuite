/*    */ package com.htsoft.test.jbpm;
/*    */ 
/*    */ import com.htsoft.oa.service.flow.JbpmService;
/*    */ import com.htsoft.test.BaseTestCase;
/*    */ import java.io.PrintStream;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Date;
/*    */ import java.util.List;
/*    */ import javax.annotation.Resource;
/*    */ import org.jbpm.api.ExecutionService;
/*    */ import org.jbpm.api.ProcessEngine;
/*    */ import org.jbpm.api.ProcessInstance;
/*    */ import org.jbpm.api.TaskService;
/*    */ import org.jbpm.pvm.internal.env.EnvironmentFactory;
/*    */ import org.jbpm.pvm.internal.env.EnvironmentImpl;
/*    */ import org.jbpm.pvm.internal.model.ActivityImpl;
/*    */ import org.jbpm.pvm.internal.model.ExecutionImpl;
/*    */ import org.jbpm.pvm.internal.model.ProcessDefinitionImpl;
/*    */ import org.jbpm.pvm.internal.task.TaskDefinitionImpl;
/*    */ import org.jbpm.pvm.internal.task.TaskImpl;
/*    */ import org.junit.Test;
/*    */ import org.springframework.test.annotation.Rollback;
/*    */ 
/*    */ public class JbpmServiceTestCase extends BaseTestCase
/*    */ {
/*    */ 
/*    */   @Resource
/*    */   private ProcessEngine processEngine;
/*    */ 
/*    */   @Resource
/*    */   private JbpmService jbpmService;
/*    */ 
/*    */   @Resource
/*    */   private TaskService taskService;
/*    */ 
/*    */   @Resource
/*    */   private ExecutionService executionService;
/*    */ 
/*    */   @Test
/*    */   @Rollback(false)
/*    */   public void createTask()
/*    */   {
/* 39 */     EnvironmentImpl env = null;
/* 40 */     String taskId = "430018";
/* 41 */     String taskName = "开始";
/*    */     try {
/* 43 */       env = ((EnvironmentFactory)this.processEngine).openEnvironment();
/* 44 */       ProcessInstance pi = this.jbpmService.getProcessInstanceByTaskId(taskId);
/*    */ 
/* 46 */       ProcessDefinitionImpl pd = (ProcessDefinitionImpl)this.jbpmService.getProcessDefinitionByTaskId(taskId);
/*    */ 
/* 48 */       TaskDefinitionImpl taskDef = pd.getTaskDefinition(taskName);
/*    */ 
/* 50 */       if (taskDef == null)
/*    */       {
/* 53 */         TaskDefinitionImpl taskDefinition = new TaskDefinitionImpl();
/* 54 */         taskDefinition.setName(taskName);
/* 55 */         taskDefinition.setPriority(1);
/*    */ 
/* 57 */         taskDefinition.setProcessDefinition(pd);
/*    */ 
/* 59 */         ActivityImpl startActivityImpl = pd.findActivity(taskName);
/*    */ 
/* 61 */         ActivityImpl startTaskImpl = pd.createActivity();
/* 62 */         startTaskImpl.setName(taskName);
/* 63 */         List outTrans = new ArrayList();
/* 64 */         outTrans.addAll(startActivityImpl.getOutgoingTransitions());
/* 65 */         startTaskImpl.setOutgoingTransitions(outTrans);
/*    */       }
/*    */ 
/* 68 */       System.out.println("pi:" + pi);
/* 69 */       ExecutionImpl exeImpl = (ExecutionImpl)pi;
/*    */ 
/* 71 */       exeImpl.setActivity(pd.findActivity(taskName));
/*    */ 
/* 74 */       TaskImpl task = (TaskImpl)this.taskService.newTask();
/* 75 */       task.setDescription(taskName);
/*    */ 
/* 77 */       task.setProcessInstance((ExecutionImpl)pi);
/* 78 */       task.setName(taskName);
/* 79 */       task.setActivityName(taskName);
/* 80 */       task.setExecution(exeImpl.getExecution());
/* 81 */       task.setAssignee("1");
/* 82 */       task.setCreateTime(new Date());
/* 83 */       task.setNew(true);
/* 84 */       if (taskDef != null) {
/* 85 */         task.setTaskDefinition(taskDef);
/*    */       }
/* 87 */       this.taskService.saveTask(task);
/*    */     }
/*    */     finally
/*    */     {
/* 91 */       if (env != null) env.close();
/*    */     }
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.jbpm.JbpmServiceTestCase
 * JD-Core Version:    0.6.0
 */