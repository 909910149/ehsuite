 package com.htsoft.oa.action.document;
 
 import com.htsoft.core.command.QueryFilter;
 import com.htsoft.core.util.BeanUtil;
 import com.htsoft.core.util.JsonUtil;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.model.document.PaintTemplate;
 import com.htsoft.oa.service.document.PaintTemplateService;
 import flexjson.JSONSerializer;
 import java.util.List;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 import org.apache.commons.logging.Log;
 
 public class PaintTemplateAction extends BaseAction
 {
 
   @Resource
   private PaintTemplateService paintTemplateService;
   private PaintTemplate paintTemplate;
   private Long ptemplateId;
 
   public Long getPtemplateId()
   {
     return this.ptemplateId;
   }
 
   public void setPtemplateId(Long ptemplateId) {
     this.ptemplateId = ptemplateId;
   }
 
   public PaintTemplate getPaintTemplate() {
     return this.paintTemplate;
   }
 
   public void setPaintTemplate(PaintTemplate paintTemplate) {
     this.paintTemplate = paintTemplate;
   }
 
   public String list()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     List list = this.paintTemplateService.getAll(filter);
 
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(filter.getPagingBean().getTotalItems()).append(",result:");
 
     JSONSerializer json = JsonUtil.getJSONSerializer();
     json.exclude(new String[] { "class" });
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
         this.paintTemplateService.remove(new Long(id));
       }
     }
 
     this.jsonString = "{success:true}";
 
     return "success";
   }
 
   public String get()
   {
     PaintTemplate paintTemplate = (PaintTemplate)this.paintTemplateService.get(this.ptemplateId);
 
     StringBuffer sb = new StringBuffer("{success:true,data:");
     JSONSerializer json = JsonUtil.getJSONSerializer();
     json.exclude(new String[] { "class" });
     sb.append(json.serialize(paintTemplate));
 
     sb.append("}");
     setJsonString(sb.toString());
 
     return "success";
   }
 
   public String save()
   {
     if (this.paintTemplate.getPtemplateId() == null) {
       this.paintTemplateService.save(this.paintTemplate);
     } else {
       PaintTemplate orgPaintTemplate = (PaintTemplate)this.paintTemplateService.get(this.paintTemplate.getPtemplateId());
       try {
         BeanUtil.copyNotNullProperties(orgPaintTemplate, this.paintTemplate);
         this.paintTemplateService.save(orgPaintTemplate);
       } catch (Exception ex) {
         this.logger.error(ex.getMessage());
       }
     }
     setJsonString("{success:true}");
     return "success";
   }
 }

