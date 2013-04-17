 package com.htsoft.oa.action.flow;
 
 import com.google.gson.Gson;
 import com.google.gson.GsonBuilder;
 import com.google.gson.reflect.TypeToken;
 import com.htsoft.core.command.QueryFilter;
 import com.htsoft.core.jbpm.jpdl.Node;
 import com.htsoft.core.util.BeanUtil;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.model.flow.FieldRights;
 import com.htsoft.oa.model.flow.FormDef;
 import com.htsoft.oa.model.flow.FormDefMapping;
 import com.htsoft.oa.model.flow.FormField;
 import com.htsoft.oa.model.flow.FormTable;
 import com.htsoft.oa.model.flow.ProDefinition;
 import com.htsoft.oa.service.flow.FieldRightsService;
 import com.htsoft.oa.service.flow.FormDefMappingService;
 import com.htsoft.oa.service.flow.JbpmService;
 import com.htsoft.oa.service.flow.ProDefinitionService;
 import java.lang.reflect.Type;
 import java.util.ArrayList;
 import java.util.Iterator;
 import java.util.List;
 import java.util.Set;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 import org.apache.commons.logging.Log;
 
 public class FieldRightsAction extends BaseAction
 {
 
   @Resource
   private FieldRightsService fieldRightsService;
 
   @Resource
   private ProDefinitionService proDefinitionService;
 
   @Resource
   private FormDefMappingService formDefMappingService;
 
   @Resource
   private JbpmService jbpmService;
   private FieldRights fieldRights;
   private Long rightId;
 
   public Long getRightId()
   {
     return this.rightId;
   }
 
   public void setRightId(Long rightId) {
     this.rightId = rightId;
   }
 
   public FieldRights getFieldRights() {
     return this.fieldRights;
   }
 
   public void setFieldRights(FieldRights fieldRights) {
     this.fieldRights = fieldRights;
   }
 
   public String list()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     List list = this.fieldRightsService.getAll(filter);
 
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
         this.fieldRightsService.remove(new Long(id));
       }
     }
 
     this.jsonString = "{success:true}";
 
     return "success";
   }
 
   public String get()
   {
     FieldRights fieldRights = (FieldRights)this.fieldRightsService.get(this.rightId);
 
     Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
 
     StringBuffer sb = new StringBuffer("{success:true,data:");
     sb.append(gson.toJson(fieldRights));
     sb.append("}");
     setJsonString(sb.toString());
 
     return "success";
   }
 
   public String save()
   {
     if (this.fieldRights.getRightId() == null) {
       this.fieldRightsService.save(this.fieldRights);
     } else {
       FieldRights orgFieldRights = (FieldRights)this.fieldRightsService.get(this.fieldRights.getRightId());
       try {
         BeanUtil.copyNotNullProperties(orgFieldRights, this.fieldRights);
         this.fieldRightsService.save(orgFieldRights);
       } catch (Exception ex) {
         this.logger.error(ex.getMessage());
       }
     }
     setJsonString("{success:true}");
     return "success";
   }
 
   public String nodes()
   {
     String defId = getRequest().getParameter("defId");
     ProDefinition proDefinition = (ProDefinition)this.proDefinitionService.get(new Long(defId));
     FormDefMapping fdm = this.formDefMappingService.getByDeployId(proDefinition.getDeployId());
     StringBuffer buff = new StringBuffer();
     if (fdm != null) {
       List nodes = this.jbpmService.getFormNodesByDeployId(new Long(proDefinition.getDeployId()));
       FormDef fd = fdm.getFormDef();
       List fields = new ArrayList();
       if (fd == null) {
         setJsonString("{success:false}");
         return "success";
       }
       Set tables = fd.getFormTables();
       Iterator it = tables.iterator();
       while (it.hasNext()) {
         Set fs = ((FormTable)it.next()).getFormFields();
         Iterator it2 = fs.iterator();
         while (it2.hasNext()) {
           FormField ff = (FormField)it2.next();
           if (FormField.IS_SHOW.compareTo(ff.getIsDesignShow()) == 0) {
             fields.add(ff);
           }
         }
       }
       buff.append("{success:true,result:[");
       Gson gson = new Gson();
       for (int i = 0; i < nodes.size(); i++) {
         String nodeName = ((Node)nodes.get(i)).getName();
         for (int k = 0; k < fields.size(); k++) {
           FormField field = (FormField)fields.get(k);
           buff.append("{taskName:'").append(nodeName).append("',mappingId:'" + fdm.getMappingId()).append("'");
           List list = this.fieldRightsService.getByMappingFieldTaskName(fdm.getMappingId(), field.getFieldId(), nodeName);
           FieldRights right = new FieldRights();
           if (list.size() > 0) {
             right = (FieldRights)list.get(0);
           }
           buff.append(",rightId:'").append(right.getRightId() == null ? "" : right.getRightId()).append("',readWrite:'").append(right.getRightId() == null ? 2 : right.getReadWrite().shortValue()).append("'");
           buff.append(",refieldId:'").append(field.getFieldId())
             .append("',fieldName:'").append(gson.toJson(field.getFieldName()).replace("\"", ""))
             .append("',fieldLabel:'").append(gson.toJson(field.getFieldLabel()).replace("\"", ""))
             .append("'");
           buff.append("},");
         }
       }
 
       if (!nodes.isEmpty()) {
         buff.deleteCharAt(buff.length() - 1);
       }
 
       buff.append("]}");
     } else {
       buff.append("{success:false}");
     }
     this.jsonString = buff.toString();
     return "success";
   }
 
   public String multSave()
   {
     String data = getRequest().getParameter("data");
     Gson gson = new Gson();
     FieldRights[] fieldRights = (FieldRights[])gson.fromJson(data, com.htsoft.oa.model.flow.FieldRights[].class);
     for (FieldRights right : fieldRights) {
       if (right.getRightId().longValue() == -1L) {
         right.setRightId(null);
       }
       right.setFieldId(right.getRefieldId());
       this.fieldRightsService.save(right);
     }
     this.jsonString = "{success:true}";
     return "success";
   }
 
   public String check() {
     String defId = getRequest().getParameter("defId");
     ProDefinition proDefinition = (ProDefinition)this.proDefinitionService.get(new Long(defId));
     FormDefMapping fdm = this.formDefMappingService.getByDeployId(proDefinition.getDeployId());
     if (fdm != null)
       this.jsonString = "{success:true}";
     else {
       this.jsonString = "{success:false,msg:'未绑定表单，请先绑定表单！'}";
     }
     return "success";
   }
 }

