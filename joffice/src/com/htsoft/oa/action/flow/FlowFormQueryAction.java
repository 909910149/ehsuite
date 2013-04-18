 package com.htsoft.oa.action.flow;
 
 import com.google.gson.Gson;
 import com.htsoft.core.command.QueryFilter;
 import com.htsoft.core.service.DynamicService;
 import com.htsoft.core.util.BeanUtil;
 import com.htsoft.core.util.ContextUtil;
 import com.htsoft.core.util.JsonUtil;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.model.flow.FormField;
 import com.htsoft.oa.model.flow.FormTable;
 import com.htsoft.oa.service.flow.FormFieldService;
 import com.htsoft.oa.service.flow.FormTableService;
 import flexjson.JSONSerializer;
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.Iterator;
 import java.util.List;
 import java.util.Map;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 import org.apache.commons.lang.StringUtils;
 import org.apache.commons.logging.Log;
 
 public class FlowFormQueryAction extends BaseAction
 {
 
   @Resource
   private FormTableService formTableService;
   private FormTable formTable;
 
   @Resource
   private FormFieldService formFieldService;
   private static final String packageStr = "com.htsoft.oa.entity.";
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
 
   public String queryForms()
   {
     String typeId = getRequest().getParameter("typeId");
     String Q_tableName_S_LK = getRequest().getParameter("Q_tableName_S_LK");
 
     PagingBean pb = getInitPagingBean();
     String s = getRequest().getParameter("start");
     String l = getRequest().getParameter("limit");
     if ((s != null) && (!s.equals("")))
       pb.setStart(Integer.valueOf(Integer.parseInt(s)));
     if ((l != null) && (!l.equals(""))) {
       pb.setPageSize(Integer.parseInt(l));
     }
     String sort = getRequest().getParameter("sort");
     String dir = getRequest().getParameter("dir");
 
     List form_table_list = 
       this.formTableService.getListFromPro(typeId, Q_tableName_S_LK, ContextUtil.getCurrentUser(), pb);
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(pb.getTotalItems()).append(
       ",result:");
 
     JSONSerializer serializer = new JSONSerializer();
     buff.append(serializer.serialize(form_table_list));
     buff.append("}");
     this.jsonString = buff.toString();
     return "success";
   }
 
   public String queryEntity() {
     String tableKey = getRequest().getParameter("tableKey");
 
     String ClassName = "WF_" + tableKey;
     try {
       DynamicService dynamicService = 
         BeanUtil.getDynamicServiceBean(ClassName);
       QueryFilter filter = new QueryFilter(getRequest());
       List list = dynamicService.getAll(filter);
       StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
         .append(filter.getPagingBean().getTotalItems()).append(
         ",result:");
       buff.append(JsonUtil.listEntity2Json(list, ClassName));
       buff.append("}");
 
       this.jsonString = buff.toString();
     }
     catch (Exception e)
     {
       e.printStackTrace();
     }
 
     this.logger.debug(this.jsonString);
 
     return "success";
   }
 
   public String fieldList()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     List fieldList = this.formFieldService.getAll(filter);
 
     Iterator fieldIterator = fieldList.iterator();
     List list = new ArrayList();
 
     while (fieldIterator.hasNext()) {
       FormField ff = (FormField)fieldIterator.next();
 
       if ((StringUtils.isNotEmpty(ff.getForeignTable())) && 
         (StringUtils.isNotEmpty(ff.getForeignKey())))
       {
         continue;
       }
 
       Map m = new HashMap();
       m.put("fieldSize", ff.getFieldSize());
       m.put("showFormat", ff.getShowFormat());
       if (ff.getFieldLabel() != null)
         m.put("fieldDscp", ff.getFieldLabel());
       else {
         m.put("fieldDscp", ff.getFieldName());
       }
       m.put("fieldType", ff.getFieldType().trim());
       m.put("isList", ff.getIsList());
       m.put("isQuery", ff.getIsQuery());
       String fieldName = ff.getFieldName();
       m.put("property", fieldName);
       list.add(m);
     }
 
     if ((list != null) && (list.size() > 0)) {
       Map m = new HashMap();
       m.put("fieldDscp", "任务ID");
       m.put("fieldType", "bigint");
       m.put("isList", Integer.valueOf(1));
       m.put("isQuery", Integer.valueOf(0));
       m.put("property", "runId");
       list.add(m);
     }
 
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(filter.getPagingBean().getTotalItems()).append(
       ",result:");
 
     Gson gson = new Gson();
     buff.append(gson.toJson(list));
     buff.append("}");
 
     this.jsonString = buff.toString();
 
     return "success";
   }
 }

