 package com.htsoft.oa.action.flow;
 
 import com.google.gson.Gson;
 import com.google.gson.GsonBuilder;
 import com.google.gson.reflect.TypeToken;
 import com.htsoft.core.command.QueryFilter;
 import com.htsoft.core.util.BeanUtil;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.model.flow.FormTable;
 import com.htsoft.oa.service.flow.FormTableGenService;
 import com.htsoft.oa.service.flow.FormTableService;
 import flexjson.JSONSerializer;
 import java.lang.reflect.Type;
 import java.util.List;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 import org.apache.commons.logging.Log;
 
 public class FormTableAction extends BaseAction
 {
 
   @Resource
   private FormTableService formTableService;
   private FormTable formTable;
 
   @Resource
   private FormTableGenService formTableGenService;
   private Long tableId;
 
   public Long getTableId()
   {
     return this.tableId;
   }
 
   public void setTableId(Long tableId) {
     this.tableId = tableId;
   }
 
   public FormTable getFormTable() {
     return this.formTable;
   }
 
   public void setFormTable(FormTable formTable) {
     this.formTable = formTable;
   }
 
   public String list()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     List list = this.formTableService.getAll(filter);
     Type type = new TypeToken() {  }
     .getType();
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(filter.getPagingBean().getTotalItems()).append(",result:");
 
     JSONSerializer serializer = new JSONSerializer();
     buff.append(serializer.serialize(list));
 
     buff.append("}");
 
     this.jsonString = buff.toString();
 
     return "success";
   }
 
   public String multiDel()
   {
     String[] ids = getRequest().getParameterValues("ids");
     if (ids != null) {
       for (String id : ids) {
         this.formTableService.remove(new Long(id));
       }
     }
 
     this.jsonString = "{success:true}";
 
     return "success";
   }
 
   public String get()
   {
     FormTable formTable = (FormTable)this.formTableService.get(this.tableId);
 
     Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
 
     StringBuffer sb = new StringBuffer("{success:true,data:");
     sb.append(gson.toJson(formTable));
     sb.append("}");
     setJsonString(sb.toString());
 
     return "success";
   }
 
   public String save()
   {
     if (this.formTable.getTableId() == null) {
       this.formTableService.save(this.formTable);
     } else {
       FormTable orgFormTable = (FormTable)this.formTableService.get(this.formTable.getTableId());
       try {
         BeanUtil.copyNotNullProperties(orgFormTable, this.formTable);
         this.formTableService.save(orgFormTable);
       } catch (Exception ex) {
         this.logger.error(ex.getMessage());
       }
     }
     setJsonString("{success:true}");
     return "success";
   }
 }

