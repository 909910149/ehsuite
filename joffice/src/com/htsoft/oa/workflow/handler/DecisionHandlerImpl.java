/*    */ package com.htsoft.oa.workflow.handler;
/*    */ 
/*    */ import bsh.EvalError;
/*    */ import bsh.Interpreter;
/*    */ import com.htsoft.core.util.AppUtil;
/*    */ import com.htsoft.oa.model.flow.ProHandleComp;
/*    */ import com.htsoft.oa.service.flow.ProHandleCompService;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.Map.Entry;
/*    */ import java.util.Set;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ import org.jbpm.api.ProcessDefinition;
/*    */ import org.jbpm.api.ProcessDefinitionQuery;
/*    */ import org.jbpm.api.ProcessEngine;
/*    */ import org.jbpm.api.RepositoryService;
/*    */ import org.jbpm.api.jpdl.DecisionHandler;
/*    */ import org.jbpm.api.model.Activity;
/*    */ import org.jbpm.api.model.OpenExecution;
/*    */ 
/*    */ public class DecisionHandlerImpl
/*    */   implements DecisionHandler
/*    */ {
/* 29 */   private static final Log logger = LogFactory.getLog(DecisionHandlerImpl.class);
/*    */ 
/*    */   public String decide(OpenExecution execution)
/*    */   {
/* 33 */     logger.debug("enter decision handler....");
/*    */ 
/* 35 */     ProcessEngine processEngine = (ProcessEngine)AppUtil.getBean("processEngine");
/*    */ 
/* 37 */     ProHandleCompService proHandleCompService = (ProHandleCompService)AppUtil.getBean("proHandleCompService");
/*    */ 
/* 39 */     String pdId = execution.getProcessDefinitionId();
/*    */ 
/* 41 */     ProcessDefinition processDefinition = processEngine.getRepositoryService().createProcessDefinitionQuery().processDefinitionId(pdId).uniqueResult();
/*    */ 
/* 43 */     String deployId = processDefinition.getDeploymentId();
/*    */ 
/* 45 */     Activity curActivity = execution.getActivity();
/*    */ 
/* 47 */     List list = proHandleCompService.getByDeployIdActivityNameHandleType(deployId, curActivity.getName(), ProHandleComp.HANDLE_TYPE_HANDLER);
/*    */ 
/* 49 */     if (list.size() > 0) {
/* 50 */       ProHandleComp proHandleComp = (ProHandleComp)list.get(0);
/* 51 */       logger.info("exeCode:" + proHandleComp.getExeCode());
/*    */ 
/* 53 */       Interpreter it = new Interpreter();
/*    */       try
/*    */       {
/* 57 */         Map vars = execution.getVariables();
/* 58 */         Iterator iterator = vars.entrySet().iterator();
/* 59 */         while (iterator.hasNext()) {
/* 60 */           Map.Entry entry = (Map.Entry)iterator.next();
/* 61 */           String key = (String)entry.getKey();
/* 62 */           Object val = entry.getValue();
/* 63 */           it.set(key.replace(".", "_"), val);
/*    */         }
/* 65 */         logger.info("dynamic execution code tranTo:" + proHandleComp.getExeCode());
/* 66 */         it.set("execution", execution);
/* 67 */         it.eval(proHandleComp.getExeCode());
/* 68 */         String tran = (String)it.get("tranTo");
/* 69 */         logger.info("return tranTo:" + tran);
/* 70 */         return tran;
/*    */       } catch (EvalError e) {
/* 72 */         e.printStackTrace();
/*    */       }
/*    */     }
/*    */ 
/* 76 */     return null;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.workflow.handler.DecisionHandlerImpl
 * JD-Core Version:    0.6.0
 */