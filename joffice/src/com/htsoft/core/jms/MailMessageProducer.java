 package com.htsoft.core.jms;
 
 import com.htsoft.core.model.MailModel;
 import javax.annotation.Resource;
 import javax.jms.Queue;
 import org.apache.commons.logging.Log;
 import org.apache.commons.logging.LogFactory;
 import org.springframework.jms.core.JmsTemplate;
 
 public class MailMessageProducer
 {
   private static final Log logger = LogFactory.getLog(MailMessageProducer.class);
 
   @Resource
   private Queue mailQueue;
 
   @Resource
   private JmsTemplate jmsTemplate;
 
   public void send(MailModel mailModel) { logger.debug("procduce the mail message");
 
     this.jmsTemplate.convertAndSend(this.mailQueue, mailModel);
   }
 }

