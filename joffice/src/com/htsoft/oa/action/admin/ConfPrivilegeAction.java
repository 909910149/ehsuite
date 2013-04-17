 package com.htsoft.oa.action.admin;
 
 import com.google.gson.Gson;
 import com.google.gson.reflect.TypeToken;
 import com.htsoft.core.command.QueryFilter;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.model.admin.ConfPrivilege;
 import com.htsoft.oa.service.admin.ConfPrivilegeService;
 import java.lang.reflect.Type;
 import java.util.List;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 
 public class ConfPrivilegeAction extends BaseAction
 {
 
   @Resource
   private ConfPrivilegeService confPrivilegeService;
   private ConfPrivilege confPrivilege;
   private Long privilegeId;
 
   public Long getPrivilegeId()
   {
     return this.privilegeId;
   }
 
   public void setPrivilegeId(Long privilegeId) {
     this.privilegeId = privilegeId;
   }
 
   public ConfPrivilege getConfPrivilege() {
     return this.confPrivilege;
   }
 
   public void setConfPrivilege(ConfPrivilege confPrivilege) {
     this.confPrivilege = confPrivilege;
   }
 
   public String list()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     List list = this.confPrivilegeService.getAll(filter);
 
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
         this.confPrivilegeService.remove(new Long(id));
       }
     }
 
     this.jsonString = "{success:true}";
 
     return "success";
   }
 
   public String get()
   {
     ConfPrivilege confPrivilege = (ConfPrivilege)this.confPrivilegeService.get(this.privilegeId);
 
     Gson gson = new Gson();
 
     StringBuffer sb = new StringBuffer("{success:true,data:");
     sb.append(gson.toJson(confPrivilege));
     sb.append("}");
     setJsonString(sb.toString());
 
     return "success";
   }
 
   public String save()
   {
     this.confPrivilegeService.save(this.confPrivilege);
     setJsonString("{success:true}");
     return "success";
   }
 
   public String allowView()
   {
     String confId = getRequest().getParameter("confId");
     Short st = this.confPrivilegeService.getPrivilege(new Long(confId), 
       Short.valueOf((short) 1));
     if (st.shortValue() == 1)
       setJsonString("{success:true}");
     else
       setJsonString("{failure:true,msg:'对不起，您没有权限查看该会议内容，请原谅！'}");
     return "success";
   }
 
   public String allowUpdater()
   {
     String confId = getRequest().getParameter("confId");
     Short st = this.confPrivilegeService.getPrivilege(new Long(confId), 
       Short.valueOf((short) 2));
     if (st.shortValue() == 2)
       setJsonString("{success:true}");
     else
       setJsonString("{failure:true,msg:'对不起，您没有权限编辑该会议内容，请原谅！'}");
     return "success";
   }
 }

