 package com.htsoft.oa.action.flow;
 
 import com.google.gson.Gson;
 import com.google.gson.GsonBuilder;
 import com.google.gson.reflect.TypeToken;
 import com.htsoft.core.command.QueryFilter;
 import com.htsoft.core.util.BeanUtil;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.model.flow.ProDefRights;
 import com.htsoft.oa.service.flow.ProDefRightsService;
 import java.lang.reflect.Type;
 import java.util.List;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 import org.apache.commons.lang.StringUtils;
 import org.apache.commons.logging.Log;
 
 public class ProDefRightsAction extends BaseAction
 {
 
   @Resource
   private ProDefRightsService proDefRightsService;
   private ProDefRights proDefRights;
   private Long rightsId;
 
   public Long getRightsId()
   {
     return this.rightsId;
   }
 
   public void setRightsId(Long rightsId) {
     this.rightsId = rightsId;
   }
 
   public ProDefRights getProDefRights() {
     return this.proDefRights;
   }
 
   public void setProDefRights(ProDefRights proDefRights) {
     this.proDefRights = proDefRights;
   }
 
   public String list()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     List list = this.proDefRightsService.getAll(filter);
 
     Type type = new TypeToken() {  }
     .getType();
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(filter.getPagingBean().getTotalItems()).append(",result:");
 
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
         this.proDefRightsService.remove(new Long(id));
       }
     }
 
     this.jsonString = "{success:true}";
 
     return "success";
   }
 
   public String get()
   {
     String proTypeId = getRequest().getParameter("proTypeId");
     if (StringUtils.isNotEmpty(proTypeId)) {
       this.proDefRights = this.proDefRightsService.findByTypeId(new Long(proTypeId));
     } else {
       String defId = getRequest().getParameter("defId");
       if (StringUtils.isNotEmpty(defId)) {
         this.proDefRights = this.proDefRightsService.findByDefId(new Long(defId));
       }
     }
 
     Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
 
     StringBuffer sb = new StringBuffer("{success:true,data:");
     sb.append(gson.toJson(this.proDefRights));
     sb.append("}");
     setJsonString(sb.toString());
 
     return "success";
   }
 
   public String save()
   {
     this.proDefRights.setUserIds(splitIds(this.proDefRights.getUserIds()));
     this.proDefRights.setRoleIds(splitIds(this.proDefRights.getRoleIds()));
     this.proDefRights.setDepIds(splitIds(this.proDefRights.getDepIds()));
 
     if (this.proDefRights.getRightsId() == null) {
       this.proDefRightsService.save(this.proDefRights);
     } else {
       ProDefRights orgProDefRights = (ProDefRights)this.proDefRightsService.get(this.proDefRights.getRightsId());
       try {
         BeanUtil.copyNotNullProperties(orgProDefRights, this.proDefRights);
         this.proDefRightsService.save(orgProDefRights);
       } catch (Exception ex) {
         this.logger.error(ex.getMessage());
       }
     }
     setJsonString("{success:true}");
     return "success";
   }
 
   private String splitIds(String Ids)
   {
     if (StringUtils.isNotEmpty(Ids)) {
       String[] ids = Ids.split(",");
       StringBuffer newIds = new StringBuffer(",");
       for (String id : ids) {
         if (StringUtils.isNotEmpty(id)) {
           newIds.append(id).append(",");
         }
       }
 
       return newIds.toString();
     }
     return "";
   }
 }

