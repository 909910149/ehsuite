/*    */ package com.htsoft.test.communicate;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import org.smslib.IOutboundMessageNotification;
/*    */ import org.smslib.Message.MessageEncodings;
/*    */ import org.smslib.OutboundMessage;
/*    */ import org.smslib.Service;
/*    */ import org.smslib.modem.SerialModemGateway;
/*    */ 
/*    */ public class smsTest
/*    */ {
/*    */   public void sendSMS(String mobilePhones, String content)
/*    */   {
/* 22 */     OutboundNotification outboundNotification = new OutboundNotification();
/* 23 */     Service srv = new Service();
/* 24 */     SerialModemGateway gateway = new SerialModemGateway("modem.com3", 
/* 25 */       "COM3", 9600, "wavecom", "");
/* 26 */     gateway.setInbound(true);
/* 27 */     gateway.setOutbound(true);
/* 28 */     gateway.setSimPin("0000");
/* 29 */     gateway.setOutboundNotification(outboundNotification);
/* 30 */     srv.addGateway(gateway);
/* 31 */     System.out.println("初始化成功，准备开启服务");
/*    */     try {
/* 33 */       srv.startService();
/* 34 */       System.out.println("服务启动成功");
/* 35 */       String[] phones = mobilePhones.split(",");
/* 36 */       for (int i = 0; i < phones.length; i++) {
/* 37 */         OutboundMessage msg = new OutboundMessage(phones[i], content);
///* 38 */         msg.setEncoding(Message.MessageEncodings.ENCUCS2);
/* 39 */         srv.sendMessage(msg);
/* 40 */         System.out.println("发送成功");
/*    */       }
/* 42 */       srv.stopService();
/*    */     } catch (Exception e) {
/* 44 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/*    */   public static void main(String[] args) {
/* 49 */     smsTest sendMessage = new smsTest();
/* 50 */     sendMessage.sendSMS("13560343147", "您要发送的内容！");
/* 51 */     System.out.println("发送结束");
/*    */   }
/*    */ 
/*    */   public class OutboundNotification
/*    */     implements IOutboundMessageNotification
/*    */   {
/*    */     public OutboundNotification()
/*    */     {
/*    */     }
/*    */ 
/*    */     public void process(String gatewayId, OutboundMessage msg)
/*    */     {
/* 12 */       System.out.println("Outbound handler called from Gateway: " + 
/* 13 */         gatewayId);
/* 14 */       System.out.println(msg);
/*    */     }
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.communicate.smsTest
 * JD-Core Version:    0.6.0
 */