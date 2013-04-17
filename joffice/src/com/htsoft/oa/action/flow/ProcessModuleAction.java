 package com.htsoft.oa.action.flow;
 
 import com.htsoft.core.command.QueryFilter;
 import com.htsoft.core.util.BeanUtil;
 import com.htsoft.core.util.JsonUtil;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.model.flow.ProcessModule;
 import com.htsoft.oa.service.flow.ProcessModuleService;
 import flexjson.JSONSerializer;
 import java.util.Date;
 import java.util.List;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 import org.apache.commons.logging.Log;
 
 public class ProcessModuleAction extends BaseAction
 {
 
   @Resource
   private ProcessModuleService processModuleService;
   private ProcessModule processModule;
   private Long moduleid;
 
   public Long getModuleid()
   {
     return this.moduleid;
   }
 
   public void setModuleid(Long moduleid) {
     this.moduleid = moduleid;
   }
 
   public ProcessModule getProcessModule() {
     return this.processModule;
   }
 
   public void setProcessModule(ProcessModule processModule) {
     this.processModule = processModule;
   }
 
   public String list()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     List list = this.processModuleService.getAll(filter);
 
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(filter.getPagingBean().getTotalItems()).append(",result:");
 
     JSONSerializer json = JsonUtil.getJSONSerializer(new String[] { "createtime" });
     buff.append(json.serialize(list));
     buff.append("}");
 
     this.jsonString = buff.toString();
 
     return "success";
   }
 
   public String multiDel()
   {
     String[] ids = getRequest().getParameterValues("ids");
     if (ids != null) {
       for (String id : ids) {
         this.processModuleService.remove(new Long(id));
       }
     }
 
     this.jsonString = "{success:true}";
 
     return "success";
   }
 
   public String get()
   {
     ProcessModule processModule = (ProcessModule)this.processModuleService.get(this.moduleid);
 
     JSONSerializer json = JsonUtil.getJSONSerializer(new String[] { "createtime" });
 
     StringBuffer sb = new StringBuffer("{success:true,data:");
     sb.append(json.serialize(processModule));
     sb.append("}");
     setJsonString(sb.toString());
 
     return "success";
   }
 
   public String save()
   {
     if (this.processModule.getModuleid() == null) {
       this.processModule.setCreatetime(new Date());
       this.processModuleService.save(this.processModule);
     } else {
       ProcessModule orgProcessModule = (ProcessModule)this.processModuleService.get(this.processModule.getModuleid());
       try {
         BeanUtil.copyNotNullProperties(orgProcessModule, this.processModule);
         this.processModuleService.save(orgProcessModule);
       } catch (Exception ex) {
         this.logger.error(ex.getMessage());
       }
     }
     setJsonString("{success:true}");
     return "success";
   }
 }

