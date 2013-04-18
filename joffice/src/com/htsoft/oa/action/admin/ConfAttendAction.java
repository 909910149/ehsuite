 package com.htsoft.oa.action.admin;
 
 import com.google.gson.Gson;
 import com.google.gson.reflect.TypeToken;
 import com.htsoft.core.command.QueryFilter;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.model.admin.ConfAttend;
 import com.htsoft.oa.service.admin.ConfAttendService;
 import java.lang.reflect.Type;
 import java.util.List;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 
 public class ConfAttendAction extends BaseAction
 {
 
   @Resource
   private ConfAttendService confAttendService;
   private ConfAttend confAttend;
   private Long attendId;
 
   public Long getAttendId()
   {
     return this.attendId;
   }
 
   public void setAttendId(Long attendId) {
     this.attendId = attendId;
   }
 
   public ConfAttend getConfAttend() {
     return this.confAttend;
   }
 
   public void setConfAttend(ConfAttend confAttend) {
     this.confAttend = confAttend;
   }
 
   public String list()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     List list = this.confAttendService.getAll(filter);
 
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
         this.confAttendService.remove(new Long(id));
       }
     }
 
     this.jsonString = "{success:true}";
 
     return "success";
   }
 
   public String get()
   {
     ConfAttend confAttend = (ConfAttend)this.confAttendService.get(this.attendId);
 
     Gson gson = new Gson();
 
     StringBuffer sb = new StringBuffer("{success:true,data:");
     sb.append(gson.toJson(confAttend));
     sb.append("}");
     setJsonString(sb.toString());
 
     return "success";
   }
 
   public String save()
   {
     this.confAttendService.save(this.confAttend);
     setJsonString("{success:true}");
     return "success";
   }
 }

