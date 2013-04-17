/*    */ package com.htsoft.oa.service.bpm.ILog.elements.impl;
/*    */ 
/*    */ import com.htsoft.oa.service.bpm.ILog.elements.BpmElementsManager;
/*    */ import com.htsoft.oa.service.bpm.ILog.helper.BpmHelper;
/*    */ import java.util.Map;
/*    */ import org.dom4j.Document;
/*    */ import org.dom4j.Element;
/*    */ 
/*    */ public class BpmTaskImpl extends BpmHelper
/*    */   implements BpmElementsManager
/*    */ {
/*    */   public BpmTaskImpl(Document document)
/*    */   {
/* 24 */     super(document);
/*    */   }
/*    */ 
/*    */   public String getInfo(Element element)
/*    */   {
/* 35 */     StringBuffer sbf = new StringBuffer("<task ");
/* 36 */     sbf.append(super.getAttributes(element));
/* 37 */     Map map = getNodeLabels(element, new String[] { "type", 
/* 38 */       "expression" });
/* 39 */     String msg = "";
/* 40 */     if ((map.size() == 2) && 
/* 41 */       (map.get("type") != null) && (!((String)map.get("type")).equals("none"))) {
/* 42 */       msg = " " + (String)map.get("type") + "=\"" + (String)map.get("expression") + 
/* 43 */         "\"";
/*    */     }
/*    */ 
/* 46 */     sbf.append(msg + " ");
/* 47 */     sbf.append(">\n");
/* 48 */     super.addTransition(element, sbf);
/* 49 */     sbf.append("</task>\n");
/* 50 */     return sbf.toString();
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.bpm.ILog.elements.impl.BpmTaskImpl
 * JD-Core Version:    0.6.0
 */