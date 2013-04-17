/*    */ package com.htsoft.oa.service.bpm.ILog.elements.impl;
/*    */ 
/*    */ import com.htsoft.oa.service.bpm.ILog.elements.BpmElementsManager;
/*    */ import com.htsoft.oa.service.bpm.ILog.helper.BpmHelper;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.Map.Entry;
/*    */ import org.dom4j.Document;
/*    */ import org.dom4j.Element;
/*    */ 
/*    */ public class BpmGatewayImpl extends BpmHelper
/*    */   implements BpmElementsManager
/*    */ {
/*    */   public BpmGatewayImpl(Document document)
/*    */   {
/* 26 */     super(document);
/*    */   }
/*    */ 
/*    */   public String getInfo(Element element)
/*    */   {
/* 37 */     StringBuffer sbf = new StringBuffer("<");
/* 38 */     String trigger = super.getNodeLabel(element, "gatewayType");
/* 39 */     String tg = "decision";
/* 40 */     if (trigger != null) {
/* 41 */       if (trigger.equals("AND"))
/* 42 */         tg = "join";
/* 43 */       else if (trigger.equals("OR")) {
/* 44 */         tg = "fork";
/*    */       }
/*    */     }
/* 47 */     sbf.append(tg);
/* 48 */     sbf.append(" " + super.getAttributes(element));
/* 49 */     String handlerString = attributeBaseTrigger(element, sbf, tg);
/* 50 */     sbf.append(">\n");
/* 51 */     super.addTransition(element, sbf);
/* 52 */     if ((tg != null) && (tg == "decision") && (!handlerString.equals("")))
/* 53 */       sbf.append("\t<handler class=\"" + handlerString + "\"/>\n");
/* 54 */     sbf.append("</" + tg + ">\n");
/* 55 */     return sbf.toString();
/*    */   }
/*    */ 
/*    */   private String attributeBaseTrigger(Element element, StringBuffer sbf, String tg)
/*    */   {
/* 60 */     String msg = "";
/* 61 */     Map map = new HashMap();
/*    */ 
/* 63 */     map = super.getNodeLabels(element, new String[] { "expr", "handler" });
///* 64 */     for (Map.Entry et : map.entrySet()) {
///* 65 */       if (((String)et.getKey()).equals("expr"))
///* 66 */         sbf.append(" expr=\"" + (String)et.getValue() + "\" ");
///* 67 */       else if (((String)et.getKey()).equals("handler")) {
///* 68 */         msg = (String)et.getValue();
///*    */       }
///*    */     }
/* 71 */     return msg;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.bpm.ILog.elements.impl.BpmGatewayImpl
 * JD-Core Version:    0.6.0
 */