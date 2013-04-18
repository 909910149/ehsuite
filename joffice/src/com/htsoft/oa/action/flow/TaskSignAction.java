 package com.htsoft.oa.action.flow;
 
 import com.google.gson.Gson;
 import com.google.gson.GsonBuilder;
 import com.google.gson.reflect.TypeToken;
 import com.htsoft.core.command.QueryFilter;
 import com.htsoft.core.util.BeanUtil;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.model.flow.ProUserAssign;
 import com.htsoft.oa.model.flow.TaskSign;
 import com.htsoft.oa.service.flow.ProUserAssignService;
 import com.htsoft.oa.service.flow.TaskSignService;
 import java.lang.reflect.Type;
 import java.util.List;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 import org.apache.commons.logging.Log;
 
 public class TaskSignAction extends BaseAction
 {
 
   @Resource
   private TaskSignService taskSignService;
 
   @Resource
   private ProUserAssignService proUserAssignService;
   private TaskSign taskSign;
   private Long signId;
   private Long assignId;
 
   public Long getSignId()
   {
     return this.signId;
   }
 
   public void setSignId(Long signId) {
     this.signId = signId;
   }
 
   public TaskSign getTaskSign() {
     return this.taskSign;
   }
 
   public void setTaskSign(TaskSign taskSign) {
     this.taskSign = taskSign;
   }
 
   public Long getAssignId() {
     return this.assignId;
   }
 
   public void setAssignId(Long assignId) {
     this.assignId = assignId;
   }
 
   public String list()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     List list = this.taskSignService.getAll(filter);
 
     Type type = new TypeToken() {
     }.getType();
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(filter.getPagingBean().getTotalItems()).append(
       ",result:");
 
     Gson gson = new Gson();
     buff.append(gson.toJson(list, type));
     buff.append("}");
 
     this.jsonString = buff.toString();
 
     return "success";
   }
 
   public String multiDel()
   {
     String[] ids = getRequest().getParameterValues("ids");
     if (ids != null) {
       for (String id : ids) {
         this.taskSignService.remove(new Long(id));
       }
     }
 
     this.jsonString = "{success:true}";
 
     return "success";
   }
 
   public String get()
   {
     TaskSign taskSign = (TaskSign)this.taskSignService.get(this.signId);
 
     Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
 
     StringBuffer sb = new StringBuffer("{success:true,data:");
     sb.append(gson.toJson(taskSign));
     sb.append("}");
     setJsonString(sb.toString());
 
     return "success";
   }
 
   public String find()
   {
     TaskSign ts = this.taskSignService.findByAssignId(this.assignId);
     Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
 
     StringBuffer sb = new StringBuffer("{success:true,data:");
     if (ts != null)
       sb.append(gson.toJson(ts));
     else
       sb.append("[]");
     sb.append("}");
     setJsonString(sb.toString());
     return "success";
   }
 
   public String save()
   {
     if (this.taskSign.getSignId() == null) {
       ProUserAssign pua = (ProUserAssign)this.proUserAssignService.get(this.assignId);
       this.taskSign.setProUserAssign(pua);
       this.taskSignService.save(this.taskSign);
     } else {
       TaskSign orgTaskSign = (TaskSign)this.taskSignService.get(this.taskSign.getSignId());
       try {
         BeanUtil.copyNotNullProperties(orgTaskSign, this.taskSign);
         this.taskSignService.save(orgTaskSign);
       } catch (Exception ex) {
         this.logger.error(ex.getMessage());
       }
     }
     setJsonString("{success:true}");
     return "success";
   }
 }

