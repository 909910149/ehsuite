 package com.htsoft.oa.action.flow;
 
 import com.htsoft.core.service.DynamicService;
 import com.htsoft.core.util.BeanUtil;
 import com.htsoft.core.util.StringUtil;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.oa.model.flow.ProcessRun;
 import com.htsoft.oa.service.flow.JbpmService;
 import com.htsoft.oa.service.flow.ProcessFormService;
 import com.htsoft.oa.service.flow.ProcessRunService;
 import java.io.Serializable;
 import java.util.List;
 import java.util.Map;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 import org.jbpm.pvm.internal.model.ExecutionImpl;
 
 public class ProcessRunDetailAction extends BaseAction
 {
 
   @Resource
   private ProcessRunService processRunService;
 
   @Resource
   private ProcessFormService processFormService;
 
   @Resource
   private JbpmService jbpmService;
   private Long runId;
   private Long taskId;
 
   public Long getRunId()
   {
     return this.runId;
   }
 
   public void setRunId(Long runId) {
     this.runId = runId;
   }
 
   public Long getTaskId()
   {
     return this.taskId;
   }
 
   public void setTaskId(Long taskId) {
     this.taskId = taskId;
   }
 
   public String execute() throws Exception
   {
     ProcessRun processRun = null;
     if (this.runId == null) {
       ExecutionImpl pis = (ExecutionImpl)this.jbpmService.getProcessInstanceByTaskId(this.taskId.toString());
       String piId = pis.getId();
       if (pis.getSuperProcessExecution() != null) {
         piId = pis.getSuperProcessExecution().getId();
       }
       processRun = this.processRunService.getByPiId(piId);
       getRequest().setAttribute("processRun", processRun);
       this.runId = processRun.getRunId();
     } else {
       processRun = (ProcessRun)this.processRunService.get(this.runId);
     }
 
     Serializable pkValue = processRun.getEntityId();
     String entityName = processRun.getEntityName();
 
     if ((pkValue != null) && (entityName != null))
     {
       if (StringUtil.isNumeric(pkValue.toString())) {
         pkValue = new Long(pkValue.toString());
       }
 
       DynamicService dynamicService = BeanUtil.getDynamicServiceBean(entityName);
 
       Object entity = dynamicService.get(pkValue);
 
       if (entity != null) {
         getRequest().setAttribute("entity", entity);
         getRequest().setAttribute("entityHtml", BeanUtil.mapEntity2Html((Map)entity, entityName));
       }
 
     }
 
     List pfList = this.processFormService.getByRunId(this.runId);
 
     getRequest().setAttribute("pfList", pfList);
 
     return "success";
   }
 }

