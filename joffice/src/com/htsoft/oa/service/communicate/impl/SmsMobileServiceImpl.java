/*     */ package com.htsoft.oa.service.communicate.impl;
/*     */ 
/*     */ import com.htsoft.core.jms.MobileMessageProducer;
/*     */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*     */ import com.htsoft.core.util.AppUtil;
/*     */ import com.htsoft.core.util.BeanUtil;
/*     */ import com.htsoft.oa.core.gms.GmsUtil;
/*     */ import com.htsoft.oa.dao.communicate.SmsMobileDao;
/*     */ import com.htsoft.oa.model.communicate.SmsHistory;
/*     */ import com.htsoft.oa.model.communicate.SmsMobile;
/*     */ import com.htsoft.oa.model.system.AppUser;
/*     */ import com.htsoft.oa.model.system.SysConfig;
/*     */ import com.htsoft.oa.service.communicate.SmsHistoryService;
/*     */ import com.htsoft.oa.service.communicate.SmsMobileService;
/*     */ import com.htsoft.oa.service.system.AppUserService;
/*     */ import com.htsoft.oa.service.system.SysConfigService;
/*     */ import com.sms.soap.SendStatus;
/*     */ import com.sms.soap.ServicesSoap;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import javax.annotation.Resource;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.codehaus.xfire.client.XFireProxyFactory;
/*     */ import org.codehaus.xfire.service.binding.ObjectServiceFactory;
/*     */ import org.smslib.Message.MessageEncodings;
/*     */ import org.smslib.OutboundMessage;
/*     */ 
/*     */ public class SmsMobileServiceImpl extends BaseServiceImpl<SmsMobile>
/*     */   implements SmsMobileService
/*     */ {
/*     */ 
/*     */   @Resource
/*     */   private MobileMessageProducer mobileMessageProducer;
/*     */ 
/*     */   @Resource
/*     */   private AppUserService appUserService;
/*     */ 
/*     */   @Resource
/*     */   private SmsMobileService smsMobileService;
/*     */ 
/*     */   @Resource
/*     */   private SmsHistoryService smsHistoryService;
/*     */ 
/*     */   @Resource
/*     */   private SysConfigService sysConfigService;
/*     */   private SmsMobileDao dao;
/*     */ 
/*     */   public SmsMobileServiceImpl(SmsMobileDao dao)
/*     */   {
/*  54 */     super(dao);
/*  55 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */   public SmsMobile save(SmsMobile entity)
/*     */   {
/*  60 */     this.dao.save(entity);
/*  61 */     this.mobileMessageProducer.send(entity);
/*  62 */     return entity;
/*     */   }
/*     */ 
/*     */   public void saveSms(String userIds, String content)
/*     */   {
/*  69 */     if (StringUtils.isNotEmpty(userIds)) {
/*  70 */       String[] ids = userIds.split(",");
/*  71 */       for (String id : ids) {
/*  72 */         AppUser recipients = (AppUser)this.appUserService.get(new Long(id));
/*     */ 
/*  74 */         SmsMobile smsMobile = new SmsMobile();
/*  75 */         smsMobile.setPhoneNumber(recipients.getMobile());
/*  76 */         smsMobile.setSendTime(new Date());
/*  77 */         smsMobile.setRecipients(recipients.getFullname());
/*  78 */         smsMobile.setSmsContent(content);
/*  79 */         smsMobile.setUserId(AppUser.SYSTEM_USER);
/*  80 */         smsMobile.setUserName("系统");
/*  81 */         smsMobile.setStatus(SmsMobile.STATUS_NOT_SENDED);
/*     */ 
/*  83 */         this.smsMobileService.save(smsMobile);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void sendSms()
/*     */   {
/*  91 */     List list = this.smsMobileService.getNeedToSend();
/*  92 */     send(list);
/*     */   }
/*     */ 
/*     */   public void send(List<SmsMobile> list)
/*     */   {
/*  97 */     boolean smsPort = AppUtil.getSmsPort();
/*     */     SysConfig pwd;
/*  99 */     if (smsPort) {
/* 100 */       SysConfig uri = this.sysConfigService.findByKey("smsPortUri");
/* 101 */       SysConfig userID = this.sysConfigService.findByKey("smsPortUserID");
/* 102 */       SysConfig account = this.sysConfigService.findByKey("smsPortAccount");
/* 103 */       pwd = this.sysConfigService.findByKey("smsPortPwd");
/* 104 */       SendStatus status = null;
/* 105 */       for (SmsMobile smsMobile : list) {
/* 106 */         status = DirectSend(uri.getDataValue(), 
/* 107 */           userID.getDataValue(), account.getDataValue(), pwd.getDataValue(), 
/* 108 */           smsMobile.getPhoneNumber(), smsMobile.getSmsContent(), "", 1, "", 1);
/* 109 */         if (status.getRetCode().equals("Sucess")) {
/* 110 */           smsMobile.setStatus(SmsMobile.STATUS_SENDED);
/*     */ 
/* 112 */           SmsHistory smsHistory = new SmsHistory();
/*     */           try {
/* 114 */             BeanUtil.copyNotNullProperties(smsHistory, smsMobile);
/* 115 */             smsHistory.setSmsId(null);
/* 116 */             this.smsHistoryService.merge(smsHistory);
/*     */ 
/* 118 */             this.smsMobileService.remove(smsMobile.getSmsId());
/*     */           } catch (Exception e) {
/* 120 */             this.logger.error(e);
/*     */           }
/*     */         }
/*     */         else {
/* 124 */           this.logger.debug("======Send sms failure!please check the setting of the sms port .===");
/*     */         }
/*     */       }
/*     */     }
/*     */     else {
/* 129 */       org.smslib.Service gmsService = GmsUtil.getGmsService();
/*     */ 
/* 131 */       if (list.size() > 0)
/*     */         try
/*     */         {
/* 134 */           gmsService.startService();
/*     */ 
/* 136 */           for (SmsMobile smsMobile : list) {
/* 137 */             OutboundMessage msg = new OutboundMessage(smsMobile.getPhoneNumber(), smsMobile.getSmsContent());
///* 138 */             msg.setEncoding(Message.MessageEncodings.ENCUCS2);
/* 139 */             gmsService.sendMessage(msg);
/* 140 */             smsMobile.setStatus(SmsMobile.STATUS_SENDED);
/*     */ 
/* 142 */             SmsHistory smsHistory = new SmsHistory();
/* 143 */             BeanUtil.copyNotNullProperties(smsHistory, smsMobile);
/* 144 */             smsHistory.setSmsId(null);
/* 145 */             this.smsHistoryService.merge(smsHistory);
/*     */ 
/* 147 */             this.smsMobileService.remove(smsMobile.getSmsId());
/*     */           }
/*     */ 
/* 151 */           gmsService.stopService();
/*     */         } catch (Exception e) {
/* 153 */           this.logger.error(e);
/*     */         }
/*     */     }
/*     */   }
/*     */ 
/*     */   public List<SmsMobile> getNeedToSend()
/*     */   {
/* 163 */     return this.dao.getNeedToSend();
/*     */   }
/*     */ 
/*     */   public void sendOneSms(SmsMobile smsMobile)
/*     */   {
/* 169 */     boolean smsPort = AppUtil.getSmsPort();
/*     */ 
/* 171 */     if (smsPort) {
/* 172 */       SysConfig uri = this.sysConfigService.findByKey("smsPortUri");
/* 173 */       SysConfig userID = this.sysConfigService.findByKey("smsPortUserID");
/* 174 */       SysConfig account = this.sysConfigService.findByKey("smsPortAccount");
/* 175 */       SysConfig pwd = this.sysConfigService.findByKey("smsPortPwd");
/* 176 */       SendStatus status = null;
/* 177 */       status = DirectSend(uri.getDataValue(), 
/* 178 */         userID.getDataValue(), account.getDataValue(), pwd.getDataValue(), 
/* 179 */         smsMobile.getPhoneNumber(), smsMobile.getSmsContent(), "", 1, "", 1);
/* 180 */       if (status.getRetCode().equals("success")) {
/* 181 */         smsMobile.setStatus(SmsMobile.STATUS_SENDED);
/*     */ 
/* 183 */         SmsHistory smsHistory = new SmsHistory();
/*     */         try {
/* 185 */           BeanUtil.copyNotNullProperties(smsHistory, smsMobile);
/* 186 */           smsHistory.setSmsId(null);
/* 187 */           this.smsHistoryService.merge(smsHistory);
/*     */ 
/* 189 */           this.smsMobileService.remove(smsMobile.getSmsId());
/*     */         } catch (Exception e) {
/* 191 */           this.logger.error(e);
/*     */         }
/*     */       }
/*     */       else {
/* 195 */         this.logger.debug("======Send sms failure!please check the setting of the sms port .===");
/*     */       }
/*     */     }
/*     */     else {
/*     */       try {
/* 200 */         org.smslib.Service gmsService = GmsUtil.getGmsService();
/* 201 */         gmsService.startService();
/*     */ 
/* 203 */         OutboundMessage msg = new OutboundMessage(smsMobile.getPhoneNumber(), smsMobile.getSmsContent());
///* 204 */         msg.setEncoding(Message.MessageEncodings.ENCUCS2);
/* 205 */         gmsService.sendMessage(msg);
/* 206 */         smsMobile.setStatus(SmsMobile.STATUS_SENDED);
/*     */ 
/* 208 */         SmsHistory smsHistory = new SmsHistory();
/* 209 */         BeanUtil.copyNotNullProperties(smsHistory, smsMobile);
/* 210 */         smsHistory.setSmsId(null);
/* 211 */         this.smsHistoryService.merge(smsHistory);
/*     */ 
/* 213 */         this.smsMobileService.remove(smsMobile.getSmsId());
/*     */ 
/* 215 */         gmsService.stopService();
/*     */       } catch (Exception e) {
/* 217 */         this.logger.error(e);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public SendStatus DirectSend(String url, String userID, String account, String password, String phones, String content, String sendTime, int sendType, String postFixNumber, int sign)
/*     */   {
/* 227 */     SendStatus status = null;
/* 228 */     org.codehaus.xfire.service.Service serviceModel = new ObjectServiceFactory().create(ServicesSoap.class);
/* 229 */     ServicesSoap service = null;
/*     */     try
/*     */     {
/* 232 */       service = (ServicesSoap)new XFireProxyFactory().create(serviceModel, url);
/* 233 */       status = service.directSend(userID, account, password, phones, content, sendTime, sendType, postFixNumber, sign);
/*     */     }
/*     */     catch (Exception e) {
/* 236 */       e.printStackTrace();
/* 237 */       throw new RuntimeException(e);
/*     */     }
/* 239 */     return status;
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.communicate.impl.SmsMobileServiceImpl
 * JD-Core Version:    0.6.0
 */