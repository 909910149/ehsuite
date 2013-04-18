 package com.htsoft.oa.action.document;
 
 import com.htsoft.core.command.QueryFilter;
 import com.htsoft.core.util.BeanUtil;
 import com.htsoft.core.util.ContextUtil;
 import com.htsoft.core.util.JsonUtil;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.model.document.Seal;
 import com.htsoft.oa.service.document.SealService;
 import flexjson.JSONSerializer;
 import java.util.List;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 import org.apache.commons.logging.Log;
 
 public class SealAction extends BaseAction
 {
 
   @Resource
   private SealService sealService;
   private Seal seal;
   private Long sealId;
 
   public Long getSealId()
   {
     return this.sealId;
   }
 
   public void setSealId(Long sealId) {
     this.sealId = sealId;
   }
 
   public Seal getSeal() {
     return this.seal;
   }
 
   public void setSeal(Seal seal) {
     this.seal = seal;
   }
 
   public String list()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     List list = this.sealService.getAll(filter);
 
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(filter.getPagingBean().getTotalItems()).append(",result:");
 
     JSONSerializer json = JsonUtil.getJSONSerializer();
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
         this.sealService.remove(new Long(id));
       }
     }
 
     this.jsonString = "{success:true}";
 
     return "success";
   }
 
   public String get()
   {
     Seal seal = (Seal)this.sealService.get(this.sealId);
 
     StringBuffer sb = new StringBuffer("{success:true,data:");
     JSONSerializer json = JsonUtil.getJSONSerializer();
     json.exclude(new String[] { "class" });
     sb.append(json.serialize(seal));
 
     sb.append("}");
     setJsonString(sb.toString());
 
     return "success";
   }
 
   public String save()
   {
     this.seal.setBelongId(ContextUtil.getCurrentUserId());
     if (this.seal.getSealId() == null) {
       this.sealService.save(this.seal);
     } else {
       Seal orgSeal = (Seal)this.sealService.get(this.seal.getSealId());
       try {
         BeanUtil.copyNotNullProperties(orgSeal, this.seal);
         this.sealService.save(orgSeal);
       } catch (Exception ex) {
         this.logger.error(ex.getMessage());
       }
     }
     setJsonString("{success:true}");
     return "success";
   }
 }

