 package com.htsoft.oa.action.flow;
 
 import com.google.gson.Gson;
 import com.google.gson.GsonBuilder;
 import com.google.gson.reflect.TypeToken;
 import com.htsoft.core.command.QueryFilter;
 import com.htsoft.core.util.BeanUtil;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.model.flow.FormField;
 import com.htsoft.oa.service.flow.FormFieldService;
 import java.lang.reflect.Type;
 import java.util.ArrayList;
 import java.util.List;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 import org.apache.commons.lang.StringUtils;
 import org.apache.commons.logging.Log;
 import org.htmlparser.NodeFilter;
 import org.htmlparser.Parser;
 import org.htmlparser.filters.NodeClassFilter;
 import org.htmlparser.filters.OrFilter;
 import org.htmlparser.tags.InputTag;
 import org.htmlparser.tags.SelectTag;
 import org.htmlparser.tags.TextareaTag;
 import org.htmlparser.util.NodeList;
 import org.htmlparser.util.ParserException;
 
 public class FormFieldAction extends BaseAction
 {
 
   @Resource
   private FormFieldService formFieldService;
   private FormField formField;
   private Long fieldId;
 
   public Long getFieldId()
   {
     return this.fieldId;
   }
 
   public void setFieldId(Long fieldId) {
     this.fieldId = fieldId;
   }
 
   public FormField getFormField() {
     return this.formField;
   }
 
   public void setFormField(FormField formField) {
     this.formField = formField;
   }
 
   public String list()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     List list = this.formFieldService.getAll(filter);
 
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
         this.formFieldService.remove(new Long(id));
       }
     }
 
     this.jsonString = "{success:true}";
 
     return "success";
   }
 
   public String get()
   {
     FormField formField = (FormField)this.formFieldService.get(this.fieldId);
 
     Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
 
     StringBuffer sb = new StringBuffer("{success:true,data:");
     sb.append(gson.toJson(formField));
     sb.append("}");
     setJsonString(sb.toString());
 
     return "success";
   }
 
   public String save()
   {
     if (this.formField.getFieldId() == null) {
       this.formFieldService.save(this.formField);
     } else {
       FormField orgFormField = (FormField)this.formFieldService.get(this.formField.getFieldId());
       try {
         BeanUtil.copyNotNullProperties(orgFormField, this.formField);
         this.formFieldService.save(orgFormField);
       } catch (Exception ex) {
         this.logger.error(ex.getMessage());
       }
     }
     setJsonString("{success:true}");
     return "success";
   }
 
   public String getFields()
   {
     String html = getRequest().getParameter("htmlCode");
     List flist = new ArrayList();
     try
     {
       NodeFilter inputFilter = new NodeClassFilter(InputTag.class);
       NodeFilter selectFilter = new NodeClassFilter(SelectTag.class);
       NodeFilter textareaFilter = new NodeClassFilter(TextareaTag.class);
       NodeList nodeList = null;
 
       Parser parser = new Parser();
       parser.setInputHTML(html);
 
       parser.setEncoding(parser.getEncoding());
       OrFilter lastFilter = new OrFilter();
       lastFilter.setPredicates(new NodeFilter[] { selectFilter, 
         inputFilter, textareaFilter });
       nodeList = parser.parse(lastFilter);
       for (int i = 0; i <= nodeList.size(); i++) {
         if ((nodeList.elementAt(i) instanceof InputTag)) {
           InputTag tag = (InputTag)nodeList.elementAt(i);
           if (!tag.getAttribute("type").toUpperCase().equals("BUTTON")) {
             FormField field = new FormField();
             field.setFieldDscp("");
             field.setFieldName(tag.getAttribute("name"));
             int size = 64;
             String size2 = tag.getAttribute("txtsize");
             if (StringUtils.isNotEmpty(size2)) {
               size = new Integer(size2).intValue();
             }
             field.setFieldSize(Integer.valueOf(size));
             field.setFieldType(tag.getAttribute("txtvalueType"));
             field.setIsRequired(new Short("1".equals(tag.getAttribute("txtisnotnull")) ? "1" : "0"));
             field.setIsPrimary(Short.valueOf((short) 0));
             String format = tag.getAttribute("dateFormat");
             if (StringUtils.isNotEmpty(format)) {
               field.setShowFormat(format);
             }
             field.setIsList(Short.valueOf((short)1));
             field.setIsQuery(Short.valueOf((short)1));
             field.setForeignTable("");
             flist.add(field);
           }
         }
         if ((nodeList.elementAt(i) instanceof SelectTag)) {
           SelectTag tag = (SelectTag)nodeList.elementAt(i);
           FormField field = new FormField();
           field.setFieldDscp("");
           field.setFieldName(tag.getAttribute("name"));
           int size = 128;
           String size2 = tag.getAttribute("txtsize");
           if (StringUtils.isNotEmpty(size2)) {
             size = new Integer(size2).intValue();
           }
           field.setFieldSize(Integer.valueOf(size));
           field.setFieldType(tag.getAttribute("txtvaluetype"));
           field.setIsList(Short.valueOf((short)1));
           field.setIsRequired(Short.valueOf((short)0));
           field.setIsPrimary(Short.valueOf((short)0));
           field.setIsQuery(Short.valueOf((short)1));
           field.setForeignTable("");
           flist.add(field);
         }
 
         if ((nodeList.elementAt(i) instanceof TextareaTag)) {
           TextareaTag tag = (TextareaTag)nodeList.elementAt(i);
           FormField field = new FormField();
           field.setFieldDscp("");
           field.setFieldName(tag.getAttribute("name"));
           int size = 128;
           String size2 = tag.getAttribute("txtsize");
           if (StringUtils.isNotEmpty(size2)) {
             size = new Integer(size2).intValue();
           }
           field.setFieldSize(Integer.valueOf(size));
           field.setFieldType(tag.getAttribute("txtvaluetype"));
           field.setIsList(Short.valueOf((short)1));
           field.setIsRequired(Short.valueOf((short)0));
           field.setIsPrimary(Short.valueOf((short)0));
           field.setIsQuery(Short.valueOf((short)1));
           field.setForeignTable("");
           flist.add(field);
         }
       }
     }
     catch (ParserException e)
     {
       e.printStackTrace();
     }
     Gson gson = new Gson();
     StringBuffer sb = new StringBuffer("{success:true,fields:");
     sb.append(gson.toJson(flist));
     sb.append("}");
     setJsonString(sb.toString());
 
     return "success";
   }
 }

