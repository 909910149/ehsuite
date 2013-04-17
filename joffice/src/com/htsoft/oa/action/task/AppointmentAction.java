/*     */ package com.htsoft.oa.action.task;
/*     */ 
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.GsonBuilder;
/*     */ import com.google.gson.reflect.TypeToken;
/*     */ import com.htsoft.core.command.QueryFilter;
/*     */ import com.htsoft.core.jms.MailMessageProducer;
/*     */ import com.htsoft.core.jms.MobileMessageProducer;
/*     */ import com.htsoft.core.model.MailModel;
/*     */ import com.htsoft.core.util.AppUtil;
/*     */ import com.htsoft.core.util.ContextUtil;
/*     */ import com.htsoft.core.web.action.BaseAction;
/*     */ import com.htsoft.core.web.paging.PagingBean;
/*     */ import com.htsoft.oa.model.communicate.SmsMobile;
/*     */ import com.htsoft.oa.model.info.ShortMessage;
/*     */ import com.htsoft.oa.model.system.AppUser;
/*     */ import com.htsoft.oa.model.task.Appointment;
/*     */ import com.htsoft.oa.service.communicate.SmsMobileService;
/*     */ import com.htsoft.oa.service.info.ShortMessageService;
/*     */ import com.htsoft.oa.service.task.AppointmentService;
/*     */ import java.lang.reflect.Type;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.annotation.Resource;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.commons.logging.Log;
/*     */ 
/*     */ public class AppointmentAction extends BaseAction
/*     */ {
/*     */ 
/*     */   @Resource
/*     */   private AppointmentService appointmentService;
/*     */ 
/*     */   @Resource
/*     */   private ShortMessageService shortMessageService;
/*     */ 
/*     */   @Resource
/*     */   private MailMessageProducer mailMessageProducer;
/*     */ 
/*     */   @Resource
/*     */   private MobileMessageProducer mobileMessageProducer;
/*     */ 
/*     */   @Resource
/*     */   private SmsMobileService smsMobileService;
/*     */   private Appointment appointment;
/*     */   private String sendMessage;
/*     */   private Long appointId;
/*     */   private List<Appointment> list;
/*     */ 
/*     */   public String getSendMessage()
/*     */   {
/*  61 */     return this.sendMessage;
/*     */   }
/*     */ 
/*     */   public void setSendMessage(String sendMessage) {
/*  65 */     this.sendMessage = sendMessage;
/*     */   }
/*     */ 
/*     */   public List<Appointment> getList() {
/*  69 */     return this.list;
/*     */   }
/*     */ 
/*     */   public void setList(List<Appointment> list) {
/*  73 */     this.list = list;
/*     */   }
/*     */ 
/*     */   public Long getAppointId() {
/*  77 */     return this.appointId;
/*     */   }
/*     */ 
/*     */   public void setAppointId(Long appointId) {
/*  81 */     this.appointId = appointId;
/*     */   }
/*     */ 
/*     */   public Appointment getAppointment() {
/*  85 */     return this.appointment;
/*     */   }
/*     */ 
/*     */   public void setAppointment(Appointment appointment) {
/*  89 */     this.appointment = appointment;
/*     */   }
/*     */ 
/*     */   public String list()
/*     */   {
/*  97 */     QueryFilter filter = new QueryFilter(getRequest());
/*  98 */     filter.addFilter("Q_appUser.userId_L_EQ", ContextUtil.getCurrentUserId().toString());
/*  99 */     List list = this.appointmentService.getAll(filter);
/*     */ 
/* 101 */     Type type = new TypeToken() {  }
/* 101 */     .getType();
/* 102 */     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
/* 103 */       .append(filter.getPagingBean().getTotalItems()).append(",result:");
/*     */ 
/* 105 */     Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").excludeFieldsWithoutExposeAnnotation().create();
/* 106 */     buff.append(gson.toJson(list, type));
/* 107 */     buff.append("}");
/*     */ 
/* 109 */     this.jsonString = buff.toString();
/*     */ 
/* 111 */     return "success";
/*     */   }
/*     */ 
/*     */   public String multiDel()
/*     */   {
/* 119 */     String[] ids = getRequest().getParameterValues("ids");
/* 120 */     if (ids != null) {
/* 121 */       for (String id : ids) {
/* 122 */         this.appointmentService.remove(new Long(id));
/*     */       }
/*     */     }
/*     */ 
/* 126 */     this.jsonString = "{success:true}";
/*     */ 
/* 128 */     return "success";
/*     */   }
/*     */ 
/*     */   public String get()
/*     */   {
/* 136 */     Appointment appointment = (Appointment)this.appointmentService.get(this.appointId);
/*     */ 
/* 138 */     Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
/*     */ 
/* 140 */     StringBuffer sb = new StringBuffer("{success:true,data:");
/* 141 */     sb.append(gson.toJson(appointment));
/* 142 */     sb.append("}");
/* 143 */     setJsonString(sb.toString());
/*     */ 
/* 145 */     return "success";
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/* 151 */     AppUser appUser = ContextUtil.getCurrentUser();
/* 152 */     this.appointment.setAppUser(appUser);
/* 153 */     String userId = ContextUtil.getCurrentUserId().toString();
/* 154 */     String isMsg = getRequest().getParameter("appointment.sendMessage");
/* 155 */     String isMail = getRequest().getParameter("appointment.sendMail");
/*     */ 
/* 157 */     StringBuffer msgContent = new StringBuffer("您的约会主题为:<font color=\"green\">");
/* 158 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
/* 159 */     msgContent.append(this.appointment.getSubject())
/* 160 */       .append(",地点：").append(this.appointment.getLocation())
/* 161 */       .append("</font>")
/* 162 */       .append(",请在<font color=\"red\">")
/* 163 */       .append(sdf.format(this.appointment.getStartTime()))
/* 164 */       .append(" 至 ")
/* 165 */       .append(sdf.format(this.appointment.getEndTime()))
/* 166 */       .append("</font>")
/* 167 */       .append("这时间段中,准时参加您的约会！");
/*     */ 
/* 169 */     if (StringUtils.isNotEmpty(isMsg))
/*     */     {
/* 171 */       if (appUser.getMobile() != null) {
/* 172 */         if (this.logger.isDebugEnabled()) {
/* 173 */           this.logger.info("Notice " + appUser.getFullname() + " by mobile:" + appUser.getMobile());
/*     */         }
/* 175 */         SmsMobile smsMobile = new SmsMobile();
/* 176 */         smsMobile.setPhoneNumber(appUser.getMobile());
/* 177 */         smsMobile.setSmsContent(msgContent.toString());
/* 178 */         smsMobile.setSendTime(new Date());
/* 179 */         smsMobile.setUserId(Long.valueOf(-1L));
/* 180 */         smsMobile.setUserName("system user");
/* 181 */         smsMobile.setStatus(SmsMobile.STATUS_NOT_SENDED);
/*     */ 
/* 183 */         this.smsMobileService.save(smsMobile);
/*     */ 
/* 185 */         this.mobileMessageProducer.send(smsMobile);
/*     */       }
/*     */     }
/* 188 */     Date curDate = new Date();
/* 189 */     String curDateStr = sdf.format(curDate);
/* 190 */     if (StringUtils.isNotEmpty(isMail))
/*     */     {
/* 192 */       if (appUser.getEmail() != null) {
/* 193 */         if (this.logger.isDebugEnabled()) {
/* 194 */           this.logger.info("Notice " + appUser.getFullname() + " by mail:" + appUser.getEmail());
/*     */         }
/* 196 */         String tempPath = "mail/flowMail.vm";
/* 197 */         Map model = new HashMap();
/* 198 */         model.put("curDateStr", curDateStr);
/* 199 */         model.put("appUser", appUser);
/* 200 */         model.put("appointment", this.appointment);
/* 201 */         String subject = "来自" + AppUtil.getCompanyName() + "我的约会(" + this.appointment.getSubject() + "--地址:" + this.appointment.getLocation() + ")提醒";
/* 202 */         MailModel mailModel = new MailModel();
/* 203 */         mailModel.setContent(msgContent.toString());
/* 204 */         mailModel.setSubject(subject);
/*     */ 
/* 206 */         this.mailMessageProducer.send(mailModel);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 211 */     this.shortMessageService.save(AppUser.SYSTEM_USER, userId, msgContent.toString(), ShortMessage.MSG_TYPE_SYS);
/* 212 */     this.appointmentService.save(this.appointment);
/* 213 */     setJsonString("{success:true}");
/* 214 */     return "success";
/*     */   }
/*     */ 
/*     */   public String display()
/*     */   {
/* 223 */     QueryFilter filter = new QueryFilter(getRequest());
/* 224 */     filter.addFilter("Q_appUser.userId_L_EQ", ContextUtil.getCurrentUserId().toString());
/* 225 */     filter.addSorted("appointId", "desc");
/* 226 */     List list = this.appointmentService.getAll(filter);
/* 227 */     getRequest().setAttribute("appointmentList", list);
/* 228 */     return "display";
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.action.task.AppointmentAction
 * JD-Core Version:    0.6.0
 */