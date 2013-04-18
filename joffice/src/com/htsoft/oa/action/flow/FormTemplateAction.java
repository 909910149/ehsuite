 package com.htsoft.oa.action.flow;
 
 import com.google.gson.Gson;
 import com.google.gson.GsonBuilder;
 import com.google.gson.reflect.TypeToken;
 import com.htsoft.core.command.QueryFilter;
 import com.htsoft.core.jbpm.jpdl.Node;
 import com.htsoft.core.util.AppUtil;
 import com.htsoft.core.util.FileUtil;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.model.flow.FormDefMapping;
 import com.htsoft.oa.model.flow.FormTemplate;
 import com.htsoft.oa.model.flow.ProDefinition;
 import com.htsoft.oa.service.flow.FormDefMappingService;
 import com.htsoft.oa.service.flow.FormTemplateService;
 import com.htsoft.oa.service.flow.JbpmService;
 import com.htsoft.oa.service.flow.ProDefinitionService;
 import java.io.File;
 import java.lang.reflect.Type;
 import java.util.List;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 import org.apache.commons.logging.Log;
 
 public class FormTemplateAction extends BaseAction
 {
 
   @Resource
   private FormTemplateService formTemplateService;
 
   @Resource
   private FormDefMappingService formDefMappingService;
 
   @Resource
   private ProDefinitionService proDefinitionService;
 
   @Resource
   private JbpmService jbpmService;
   private FormTemplate formTemplate;
   private Long templateId;
   private Long mappingId;
 
   public Long getTemplateId()
   {
     return this.templateId;
   }
 
   public void setTemplateId(Long templateId) {
     this.templateId = templateId;
   }
 
   public Long getMappingId()
   {
     return this.mappingId;
   }
 
   public void setMappingId(Long mappingId) {
     this.mappingId = mappingId;
   }
 
   public FormTemplate getFormTemplate() {
     return this.formTemplate;
   }
 
   public void setFormTemplate(FormTemplate formTemplate) {
     this.formTemplate = formTemplate;
   }
 
   public String mappings()
   {
     FormDefMapping formDefMapping = (FormDefMapping)this.formDefMappingService.get(this.mappingId);
 
     List<FormTemplate> formTemplateList = this.formTemplateService.getByMappingId(this.mappingId);
 
     List nodes = this.jbpmService.getFormNodesByDeployId(new Long(formDefMapping.getDeployId()));
 
     StringBuffer buff = new StringBuffer("{success:true,result:[");
 
     for (int i = 0; i < nodes.size(); i++) {
       String nodeName = ((Node)nodes.get(i)).getName();
       buff.append("{nodeName:'").append(nodeName).append("'");
       for (FormTemplate template : formTemplateList) {
         if (!nodeName.equals(template.getNodeName())) continue;
         buff.append(",mappingId:'").append(template.getMappingId())
           .append("',templateId:'").append(template.getTemplateId()).append("'");
         break;
       }
 
       buff.append("},");
     }
 
     if (nodes.size() > 0) {
       buff.deleteCharAt(buff.length() - 1);
     }
 
     buff.append("]}");
 
     this.jsonString = buff.toString();
 
     return "success";
   }
 
   public String list()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     List list = this.formTemplateService.getAll(filter);
 
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
         this.formTemplateService.remove(new Long(id));
       }
     }
 
     this.jsonString = "{success:true}";
 
     return "success";
   }
 
   public String get()
   {
     FormTemplate formTemplate = (FormTemplate)this.formTemplateService.get(this.templateId);
 
     Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
 
     StringBuffer sb = new StringBuffer("{success:true,data:");
     sb.append(gson.toJson(formTemplate));
     sb.append("}");
     setJsonString(sb.toString());
 
     return "success";
   }
 
   public String save()
   {
     String activityName = getRequest().getParameter("activityName");
     String extFormDef = getRequest().getParameter("extFormDef");
 
     FormTemplate formTemplate = (FormTemplate)this.formTemplateService.get(this.templateId);
     formTemplate.setTempContent(extFormDef);
 
     String extDef = getRequest().getParameter("extDef");
     formTemplate.setExtDef(extDef);
 
     this.formTemplateService.save(formTemplate);
 
     String formItemDef = getRequest().getParameter("formItemDef");
 
     this.logger.info("extFormDef:" + extFormDef);
     this.logger.info("formItemDef:" + formItemDef);
 
     ProDefinition proDefinition = this.proDefinitionService.getByDeployId(formTemplate.getFormDefMapping().getDeployId());
     String formPath = AppUtil.getAppAbsolutePath() + "/WEB-INF/FlowForm/" + proDefinition.getName() + "/" + formTemplate.getFormDefMapping().getVersionNo();
 
     File flowDirPath = new File(formPath);
     if (!flowDirPath.exists()) {
       flowDirPath.mkdirs();
     }
 
     String extFilePath = formPath + "/" + activityName + ".vm";
     FileUtil.writeFile(extFilePath, extFormDef);
 
     return "success";
   }
 }

