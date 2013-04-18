 package com.htsoft.oa.action.flow;
 
 import com.google.gson.Gson;
 import com.google.gson.GsonBuilder;
 import com.google.gson.reflect.TypeToken;
 import com.htsoft.core.command.QueryFilter;
 import com.htsoft.core.util.BeanUtil;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.model.flow.FormDefMapping;
 import com.htsoft.oa.service.flow.FormDefMappingService;
 import java.lang.reflect.Type;
 import java.util.List;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 import org.apache.commons.logging.Log;
 
 public class FormDefMappingAction extends BaseAction
 {
 
   @Resource
   private FormDefMappingService formDefMappingService;
   private FormDefMapping formDefMapping;
   private Long mappingId;
 
   public Long getMappingId()
   {
     return this.mappingId;
   }
 
   public void setMappingId(Long mappingId) {
     this.mappingId = mappingId;
   }
 
   public FormDefMapping getFormDefMapping() {
     return this.formDefMapping;
   }
 
   public void setFormDefMapping(FormDefMapping formDefMapping) {
     this.formDefMapping = formDefMapping;
   }
 
   public String list()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     List list = this.formDefMappingService.getAll(filter);
 
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
         this.formDefMappingService.remove(new Long(id));
       }
     }
 
     this.jsonString = "{success:true}";
 
     return "success";
   }
 
   public String get()
   {
     FormDefMapping formDefMapping = (FormDefMapping)this.formDefMappingService.get(this.mappingId);
 
     Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
 
     StringBuffer sb = new StringBuffer("{success:true,data:");
     sb.append(gson.toJson(formDefMapping));
     sb.append("}");
     setJsonString(sb.toString());
 
     return "success";
   }
 
   public String save()
   {
     if (this.formDefMapping.getMappingId() == null) {
       this.formDefMappingService.save(this.formDefMapping);
     } else {
       FormDefMapping orgFormDefMapping = (FormDefMapping)this.formDefMappingService.get(this.formDefMapping.getMappingId());
       try {
         BeanUtil.copyNotNullProperties(orgFormDefMapping, this.formDefMapping);
         this.formDefMappingService.save(orgFormDefMapping);
       } catch (Exception ex) {
         this.logger.error(ex.getMessage());
       }
     }
     setJsonString("{success:true}");
     return "success";
   }
 }

