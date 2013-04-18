/*    */ package com.htsoft.oa.workflow.event;
/*    */ 
/*    */ import bsh.EvalError;
/*    */ import bsh.Interpreter;
/*    */ import com.htsoft.core.util.AppUtil;
/*    */ import com.htsoft.oa.model.flow.ProHandleComp;
/*    */ import com.htsoft.oa.service.flow.ProHandleCompService;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import java.util.Map.Entry;
/*    */ import java.util.Set;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ import org.jbpm.api.ProcessDefinition;
/*    */ import org.jbpm.api.ProcessDefinitionQuery;
/*    */ import org.jbpm.api.ProcessEngine;
/*    */ import org.jbpm.api.RepositoryService;
/*    */ import org.jbpm.api.listener.EventListener;
/*    */ import org.jbpm.api.listener.EventListenerExecution;
/*    */ import org.jbpm.api.model.Activity;
/*    */ 
/*    */ public class NodeEventListener
/*    */   implements EventListener
/*    */ {
/* 31 */   private static final Log logger = LogFactory.getLog(NodeEventListener.class);
/*    */   private String eventType;
/*    */ 
/*    */   public String getEventType()
/*    */   {
/* 38 */     return this.eventType;
/*    */   }
/*    */ 
/*    */   public void setEventType(String eventType) {
/* 42 */     this.eventType = eventType;
/*    */   }
/*    */ 
/*    */   public void notify(EventListenerExecution execution)
/*    */     throws Exception
/*    */   {
/* 49 */     logger.debug("enter notify method of NodeEventListener...");
/*    */ 
/* 51 */     ProcessEngine processEngine = (ProcessEngine)AppUtil.getBean("processEngine");
/* 52 */     ProHandleCompService proHandleCompService = (ProHandleCompService)AppUtil.getBean("proHandleCompService");
/*    */ 
/* 54 */     String pdId = execution.getProcessDefinitionId();
/*    */ 
/* 56 */     ProcessDefinition processDefinition = processEngine.getRepositoryService().createProcessDefinitionQuery().processDefinitionId(pdId).uniqueResult();
/*    */ 
/* 58 */     String deployId = processDefinition.getDeploymentId();
/*    */ 
/* 60 */     Activity curActivity = execution.getActivity();
/*    */ 
/* 62 */     if (this.eventType == null) {
/* 63 */       this.eventType = "start";
/*    */     }
/*    */ 
/* 66 */     ProHandleComp proHandleComp = proHandleCompService.getProHandleComp(deployId, curActivity.getName(), this.eventType);
/*    */ 
/* 68 */     if (StringUtils.isNotEmpty(proHandleComp.getExeCode())) {
/* 69 */       logger.info("exeCode:" + proHandleComp.getExeCode());
/*    */ 
/* 71 */       Interpreter it = new Interpreter();
/*    */       try
/*    */       {
/* 74 */         Map vars = execution.getVariables();
/* 75 */         Iterator iterator = vars.entrySet().iterator();
/* 76 */         while (iterator.hasNext()) {
/* 77 */           Map.Entry entry = (Map.Entry)iterator.next();
/* 78 */           String key = (String)entry.getKey();
/* 79 */           Object val = entry.getValue();
/* 80 */           it.set(key.replace(".", "_"), val);
/*    */         }
/* 82 */         logger.info("dynamic execution code tranTo:" + proHandleComp.getExeCode());
/* 83 */         it.set("execution", execution);
/* 84 */         it.eval(proHandleComp.getExeCode());
/*    */       }
/*    */       catch (EvalError e) {
/* 87 */         e.printStackTrace();
/*    */       }
/*    */     }
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.workflow.event.NodeEventListener
 * JD-Core Version:    0.6.0
 */