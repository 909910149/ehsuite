 package com.htsoft.oa.action.communicate;
 
 import com.google.gson.Gson;
 import com.google.gson.GsonBuilder;
 import com.htsoft.core.command.QueryFilter;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.model.communicate.SmsHistory;
 import com.htsoft.oa.model.communicate.SmsMobile;
 import com.htsoft.oa.service.communicate.SmsHistoryService;
 import com.htsoft.oa.service.communicate.SmsMobileService;
 import java.util.List;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 import org.apache.commons.lang.StringUtils;
 
 public class SmsHistoryAction extends BaseAction
 {
 
   @Resource
   private SmsHistoryService smsHistoryService;
 
   @Resource
   private SmsMobileService smsMobileService;
   private SmsHistory smsHistory;
   private Long smsId;
 
   public Long getSmsId()
   {
     return this.smsId;
   }
 
   public void setSmsId(Long smsId) {
     this.smsId = smsId;
   }
 
   public SmsHistory getSmsHistory() {
     return this.smsHistory;
   }
 
   public void setSmsHistory(SmsHistory smsHistory) {
     this.smsHistory = smsHistory;
   }
 
   public String list()
   {
     String status = getRequest().getParameter("status");
     List list = null;
     QueryFilter filter = new QueryFilter(getRequest());
     if ((StringUtils.isNotEmpty(status)) && (status.equals(SmsMobile.STATUS_NOT_SENDED.toString())))
       list = this.smsMobileService.getAll(filter);
     else {
       list = this.smsHistoryService.getAll(filter);
     }
 
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(filter.getPagingBean().getTotalItems()).append(",result:");
 
     Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
     buff.append(gson.toJson(list));
     buff.append("}");
 
     this.jsonString = buff.toString();
 
     return "success";
   }
 
   public String multiDel()
   {
     String[] ids = getRequest().getParameterValues("ids");
     if (ids != null) {
       for (String id : ids) {
         this.smsHistoryService.remove(new Long(id));
       }
     }
 
     this.jsonString = "{success:true}";
 
     return "success";
   }
 
   public String get()
   {
     SmsHistory smsHistory = (SmsHistory)this.smsHistoryService.get(this.smsId);
 
     Gson gson = new Gson();
 
     StringBuffer sb = new StringBuffer("{success:true,data:");
     sb.append(gson.toJson(smsHistory));
     sb.append("}");
     setJsonString(sb.toString());
 
     return "success";
   }
 
   public String save()
   {
     this.smsHistoryService.save(this.smsHistory);
     setJsonString("{success:true}");
     return "success";
   }
 }

