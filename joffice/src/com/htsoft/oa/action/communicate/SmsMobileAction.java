 package com.htsoft.oa.action.communicate;
 
 import com.google.gson.Gson;
 import com.google.gson.GsonBuilder;
 import com.google.gson.reflect.TypeToken;
 import com.htsoft.core.command.QueryFilter;
 import com.htsoft.core.util.ContextUtil;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.model.communicate.SmsMobile;
 import com.htsoft.oa.model.system.AppUser;
 import com.htsoft.oa.service.communicate.SmsMobileService;
 import com.htsoft.oa.service.system.AppUserService;
 import java.lang.reflect.Type;
 import java.util.Date;
 import java.util.List;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 import org.apache.commons.lang.StringUtils;
 
 public class SmsMobileAction extends BaseAction
 {
 
   @Resource
   private SmsMobileService smsMobileService;
 
   @Resource
   private AppUserService appUserService;
   private SmsMobile smsMobile;
   private Long smsId;
 
   public Long getSmsId()
   {
     return this.smsId;
   }
 
   public void setSmsId(Long smsId) {
     this.smsId = smsId;
   }
 
   public SmsMobile getSmsMobile() {
     return this.smsMobile;
   }
 
   public void setSmsMobile(SmsMobile smsMobile) {
     this.smsMobile = smsMobile;
   }
 
   public String list()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     List list = this.smsMobileService.getAll(filter);
 
     Type type = new TypeToken() {  }
     .getType();
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
         this.smsMobileService.remove(new Long(id));
       }
     }
 
     this.jsonString = "{success:true}";
 
     return "success";
   }
 
   public String get()
   {
     SmsMobile smsMobile = (SmsMobile)this.smsMobileService.get(this.smsId);
 
     Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
 
     StringBuffer sb = new StringBuffer("{success:true,data:");
     sb.append(gson.toJson(smsMobile));
     sb.append("}");
     setJsonString(sb.toString());
 
     return "success";
   }
 
   public String save()
   {
     StringBuffer msg = new StringBuffer("");
     String recipientIds = getRequest().getParameter("recipientIds");
     AppUser appUser;
     if (StringUtils.isNotEmpty(recipientIds))
     {
       String[] ids = recipientIds.split(",");
       for (String userId : ids) {
         appUser = (AppUser)this.appUserService.get(new Long(userId));
         if (appUser.getMobile() == null) {
           msg.append("用户<font color=\"red\">").append(appUser.getUsername()).append("</font>");
         } else {
           SmsMobile smsInner = new SmsMobile();
           smsInner.setPhoneNumber(appUser.getMobile());
           smsInner.setRecipients(appUser.getFullname());
           smsInner.setSendTime(new Date());
           smsInner.setSmsContent(this.smsMobile.getSmsContent());
           smsInner.setUserId(ContextUtil.getCurrentUserId());
           smsInner.setUserName(ContextUtil.getCurrentUser().getFullname());
           smsInner.setStatus(SmsMobile.STATUS_NOT_SENDED);
           this.smsMobileService.save(smsInner);
         }
       }
       if (msg.length() > 0)
         msg.append("未设置手机号码.");
     }
     else
     {
       String mobileNums = this.smsMobile.getPhoneNumber();
       if (StringUtils.isNotEmpty(mobileNums)) {
         String[] numbers = mobileNums.split(",");
         for (String num : numbers) {
           SmsMobile smsOutside = new SmsMobile();
           smsOutside.setPhoneNumber(num);
           smsOutside.setRecipients(num);
           smsOutside.setSendTime(new Date());
           smsOutside.setSmsContent(this.smsMobile.getSmsContent());
           smsOutside.setUserId(ContextUtil.getCurrentUserId());
           smsOutside.setUserName(ContextUtil.getCurrentUser().getFullname());
           smsOutside.setStatus(SmsMobile.STATUS_NOT_SENDED);
           this.smsMobileService.save(smsOutside);
         }
       }
     }
 
     setJsonString("{success:true,msg:'" + msg.toString() + "'}");
     return "success";
   }
 
   public String send()
   {
     this.smsMobile.setSendTime(new Date());
     this.smsMobileService.save(this.smsMobile);
     this.smsMobileService.sendOneSms(this.smsMobile);
     setJsonString("{success:true}");
     return "success";
   }
 }

