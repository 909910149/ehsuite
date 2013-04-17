 package com.htsoft.core.jms;
 
 import com.htsoft.oa.model.communicate.SmsMobile;
 import com.htsoft.oa.service.communicate.SmsMobileService;
 import javax.annotation.Resource;
 import org.apache.commons.logging.Log;
 import org.apache.commons.logging.LogFactory;
 
 public class MobileMessageConsumer
 {
   private static final Log logger = LogFactory.getLog(MobileMessageConsumer.class);
 
   @Resource
   private SmsMobileService smsMobileService;
 
   public void sendMobileMsg(SmsMobile smsMobile)
   {
     logger.debug("send the sms mobile message now for :" + smsMobile.getPhoneNumber());
 
     this.smsMobileService.sendOneSms(smsMobile);
   }
 }

