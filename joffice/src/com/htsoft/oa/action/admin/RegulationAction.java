 package com.htsoft.oa.action.admin;
 
 import com.google.gson.Gson;
 import com.google.gson.GsonBuilder;
 import com.htsoft.core.command.QueryFilter;
 import com.htsoft.core.util.BeanUtil;
 import com.htsoft.core.util.ContextUtil;
 import com.htsoft.core.util.JsonUtil;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.model.admin.Regulation;
 import com.htsoft.oa.model.system.AppUser;
 import com.htsoft.oa.model.system.Department;
 import com.htsoft.oa.model.system.FileAttach;
 import com.htsoft.oa.model.system.GlobalType;
 import com.htsoft.oa.service.admin.RegulationService;
 import com.htsoft.oa.service.system.FileAttachService;
 import flexjson.JSONSerializer;
 import java.util.HashSet;
 import java.util.List;
 import java.util.Set;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 import org.apache.commons.lang.StringUtils;
 import org.apache.commons.logging.Log;
 
 public class RegulationAction extends BaseAction
 {
 
   @Resource
   private RegulationService regulationService;
 
   @Resource
   private FileAttachService fileAttachService;
   private Regulation regulation;
   private Long regId;
 
   public Long getRegId()
   {
     return this.regId;
   }
 
   public void setRegId(Long regId) {
     this.regId = regId;
   }
 
   public Regulation getRegulation() {
     return this.regulation;
   }
 
   public void setRegulation(Regulation regulation) {
     this.regulation = regulation;
   }
 
   public String list()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     List list = this.regulationService.getAll(filter);
 
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(filter.getPagingBean().getTotalItems()).append(",result:");
 
     JSONSerializer json = JsonUtil.getJSONSerializer(new String[] { "issueDate" });
     buff.append(json.exclude(new String[] { "content" }).serialize(list));
     buff.append("}");
 
     this.jsonString = buff.toString();
 
     return "success";
   }
 
   public String scan()
   {
     AppUser curUser = ContextUtil.getCurrentUser();
     Department dep = curUser.getDepartment();
 
     QueryFilter filter = new QueryFilter(getRequest());
     filter.setFilterName("GetRegulationWithRights");
 
     filter.addParamValue(Regulation.STATUS_EFFECT);
     if (dep != null)
       filter.addParamValue("%," + curUser.getDepartment().getDepId() + ",%");
     else {
       filter.addParamValue("%,0,%");
     }
 
     filter.addParamValue("%," + ContextUtil.getCurrentUserId() + ",%");
 
     List list = this.regulationService.getAll(filter);
 
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(filter.getPagingBean().getTotalItems()).append(",result:");
 
     JSONSerializer json = JsonUtil.getJSONSerializer(new String[] { "issueDate" });
     buff.append(json.exclude(new String[] { "content" }).serialize(list));
     buff.append("}");
 
     this.jsonString = buff.toString();
 
     return "success";
   }
 
   public String multiDel()
   {
     String[] ids = getRequest().getParameterValues("ids");
     if (ids != null) {
       for (String id : ids) {
         this.regulationService.remove(new Long(id));
       }
     }
 
     this.jsonString = "{success:true}";
 
     return "success";
   }
 
   public String get()
   {
     Regulation regulation = (Regulation)this.regulationService.get(this.regId);
 
     Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
 
     StringBuffer sb = new StringBuffer("{success:true,data:");
     sb.append(gson.toJson(regulation));
 
     GlobalType proType = regulation.getGlobalType();
     if (proType != null) {
       sb.deleteCharAt(sb.length() - 1);
       sb.append(",proTypeId:")
         .append(proType.getProTypeId())
         .append(",proTypeName:'")
         .append(proType.getTypeName())
         .append("'}");
     }
 
     sb.append("}");
     setJsonString(sb.toString());
 
     return "success";
   }
 
   public String save()
   {
     String fileIds = getRequest().getParameter("regAttachsFileIds");
 
     Set regAttachs = new HashSet();
 
     if (StringUtils.isNotEmpty(fileIds)) {
       String[] fIds = fileIds.split(",");
       for (int i = 0; i < fIds.length; i++) {
         FileAttach fileAttach = (FileAttach)this.fileAttachService.get(new Long(fIds[i]));
         regAttachs.add(fileAttach);
       }
 
     }
 
     String depIds = this.regulation.getRecDepIds();
     if (StringUtils.isNotEmpty(depIds)) {
       String[] dIds = depIds.split(",");
       StringBuffer newDepIds = new StringBuffer(",");
       for (String did : dIds) {
         newDepIds.append(did).append(",");
       }
       this.regulation.setRecDepIds(newDepIds.toString());
     }
 
     String userIds = this.regulation.getRecUserIds();
     if (StringUtils.isNotEmpty(userIds)) {
       String[] uIds = userIds.split(",");
       StringBuffer newUserIds = new StringBuffer(",");
       for (String uid : uIds) {
         newUserIds.append(uid).append(",");
       }
       this.regulation.setRecUserIds(newUserIds.toString());
     }
 
     if (this.regulation.getRegId() == null) {
       this.regulation.setRegAttachs(regAttachs);
       this.regulationService.save(this.regulation);
     } else {
       Regulation orgRegulation = (Regulation)this.regulationService.get(this.regulation.getRegId());
       try {
         BeanUtil.copyNotNullProperties(orgRegulation, this.regulation);
         orgRegulation.setRegAttachs(regAttachs);
         this.regulationService.save(orgRegulation);
       } catch (Exception ex) {
         this.logger.error(ex.getMessage());
       }
     }
     setJsonString("{success:true}");
     return "success";
   }
 }

