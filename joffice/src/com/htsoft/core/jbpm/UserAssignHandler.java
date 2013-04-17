 package com.htsoft.core.jbpm;
 
 import org.apache.commons.lang.StringUtils;
 import org.apache.commons.logging.Log;
 import org.apache.commons.logging.LogFactory;
 import org.jbpm.api.model.OpenExecution;
 import org.jbpm.api.task.Assignable;
 import org.jbpm.api.task.AssignmentHandler;
 
 public class UserAssignHandler
   implements AssignmentHandler
 {
   private Log logger = LogFactory.getLog(UserAssignHandler.class);
   String userIds;
   String groupIds;
 
   public void assign(Assignable assignable, OpenExecution execution)
     throws Exception
   {
     String assignId = (String)execution.getVariable("flowAssignId");
 
     this.logger.info("assignId:===========>" + assignId);
 
     if (StringUtils.isNotEmpty(assignId)) {
       assignable.setAssignee(assignId);
       return;
     }
 
     String signUserIds = (String)execution.getVariable("signUserIds");
 
     this.logger.debug("Enter UserAssignHandler assign method~~~~");
 
     if (this.userIds != null) {
       String[] uIds = this.userIds.split("[,]");
       if ((uIds != null) && (uIds.length > 1)) {
         for (String uId : uIds)
           assignable.addCandidateUser(uId);
       }
       else {
         assignable.setAssignee(this.userIds);
       }
     }
 
     if (this.groupIds != null) {
       String[] gIds = this.userIds.split("[,]");
       if ((gIds != null) && (gIds.length > 1)) {
         for (String gId : gIds)
           assignable.addCandidateGroup(gId);
       }
       else
         assignable.addCandidateGroup(this.groupIds);
     }
   }
 }

