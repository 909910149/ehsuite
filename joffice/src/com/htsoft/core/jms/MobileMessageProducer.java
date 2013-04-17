 package com.htsoft.core.jms;
 
 import com.htsoft.oa.model.communicate.SmsMobile;
 import javax.annotation.Resource;
 import javax.jms.Queue;
 import org.apache.commons.logging.Log;
 import org.apache.commons.logging.LogFactory;
 import org.springframework.jms.core.JmsTemplate;
 
 public class MobileMessageProducer
 {
   private static final Log logger = LogFactory.getLog(MobileMessageProducer.class);
 
   @Resource
   private JmsTemplate jmsTemplate;
 
   @Resource
   private Queue mobileQueue;
 
   public void send(SmsMobile smsMobile) { logger.debug("send the sms mobile" + smsMobile.getPhoneNumber());
     this.jmsTemplate.convertAndSend(this.mobileQueue, smsMobile);
   }
 }

