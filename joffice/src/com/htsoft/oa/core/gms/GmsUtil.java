/*    */ package com.htsoft.oa.core.gms;
/*    */ 
/*    */ import com.htsoft.core.util.AppUtil;
/*    */ import java.io.PrintStream;
/*    */ import java.util.Map;
/*    */ import org.smslib.IOutboundMessageNotification;
/*    */ import org.smslib.OutboundMessage;
/*    */ import org.smslib.Service;
/*    */ import org.smslib.modem.SerialModemGateway;
/*    */ 
/*    */ public class GmsUtil
/*    */ {
/* 12 */   private static Object sign = new Object();
/* 13 */   private static Service service = null;
/*    */ 
/*    */   private static void init() {
/* 16 */     service = new Service();
/* 17 */     IOutboundMessageNotification outboundNotification = new IOutboundMessageNotification()
/*    */     {
/*    */       public void process(String gatewayId, OutboundMessage msg) {
/* 20 */         System.out.println("Outbound handler called from Gateway: " + 
/* 21 */           gatewayId);
/* 22 */         System.out.println(msg);
/*    */       }
/*    */     };
/* 25 */     Map sysConfig = AppUtil.getSysConfig();
/* 26 */     String deviceName = (String)sysConfig.get("deviceName");
/* 27 */     Integer baudRate = Integer.valueOf(Integer.parseInt((String)sysConfig.get("baudRate")));
/* 28 */     SerialModemGateway gateway = new SerialModemGateway("modem.com3", deviceName, baudRate.intValue(), "wavecom", "");
/* 29 */     gateway.setInbound(true);
/* 30 */     gateway.setOutbound(true);
/* 31 */     gateway.setSimPin("0000");
/* 32 */     gateway.setOutboundNotification(outboundNotification);
/* 33 */     service.addGateway(gateway);
/*    */   }
/*    */ 
/*    */   public static Service getGmsService() {
/* 37 */     if (service == null) {
/* 38 */       synchronized (sign) {
/* 39 */         init();
/*    */       }
/*    */     }
/* 42 */     return service;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.core.gms.GmsUtil
 * JD-Core Version:    0.6.0
 */