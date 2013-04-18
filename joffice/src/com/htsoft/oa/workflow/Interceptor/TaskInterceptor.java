/*    */ package com.htsoft.oa.workflow.Interceptor;
/*    */ 
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ import org.aspectj.lang.ProceedingJoinPoint;
/*    */ 
/*    */ public class TaskInterceptor
/*    */ {
/* 24 */   private Log logger = LogFactory.getLog(TaskInterceptor.class);
/*    */ 
/*    */   public void assignTaskUser(ProceedingJoinPoint pjp)
/*    */     throws Throwable
/*    */   {
/* 39 */     this.logger.debug("before the taskactvity execution...");
/* 40 */     pjp.proceed();
/* 41 */     this.logger.debug("after the taskactvity execution...");
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.workflow.Interceptor.TaskInterceptor
 * JD-Core Version:    0.6.0
 */