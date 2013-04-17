 package com.htsoft.core.jms;
 
 import com.htsoft.core.engine.MailEngine;
 import com.htsoft.core.model.MailModel;
 import javax.annotation.Resource;
 import org.apache.commons.logging.Log;
 import org.apache.commons.logging.LogFactory;
 
 public class MailMessageConsumer
 {
   private static final Log logger = LogFactory.getLog(MailMessageConsumer.class);
 
   @Resource
   MailEngine mailEngine;
 
   public void sendMail(MailModel mailModel) { logger.debug("send mail now " + mailModel.getSubject());
     this.mailEngine.sendTemplateMail(
       mailModel.getMailTemplate(), 
       mailModel.getMailData(), 
       mailModel.getSubject(), 
       null, 
       new String[] { mailModel.getTo() }, null, null, null, null, true);
   }
 }

