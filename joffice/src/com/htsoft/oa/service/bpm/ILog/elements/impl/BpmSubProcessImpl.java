/*    */ package com.htsoft.oa.service.bpm.ILog.elements.impl;
/*    */ 
/*    */ import com.htsoft.oa.service.bpm.ILog.elements.BpmElementsManager;
/*    */ import com.htsoft.oa.service.bpm.ILog.helper.BpmHelper;
/*    */ import java.util.Map;
/*    */ import java.util.Map.Entry;
/*    */ import org.dom4j.Document;
/*    */ import org.dom4j.Element;
/*    */ 
/*    */ public class BpmSubProcessImpl extends BpmHelper
/*    */   implements BpmElementsManager
/*    */ {
/*    */   public BpmSubProcessImpl(Document document)
/*    */   {
/* 26 */     super(document);
/*    */   }
/*    */ 
/*    */   public String getInfo(Element element)
/*    */   {
/* 37 */     StringBuffer sbf = new StringBuffer("<sub-process ");
/* 38 */     sbf.append(super.getAttributes(element));
/* 39 */     Map map = super.getNodeLabels(element, new String[] { 
/* 40 */       "sub_process_key", "outcome" });
/* 41 */     String msg = "";
///* 42 */     for (Map.Entry et : map.entrySet()) {
///* 43 */       if ("sub_process_key".equals(et.getKey()))
///* 44 */         msg = msg + " sub-process-key=\"" + (String)et.getValue() + "\" ";
///*    */       else {
///* 46 */         msg = msg + (String)et.getKey() + "=\"" + (String)et.getValue() + "\" ";
///*    */       }
///*    */     }
/* 49 */     sbf.append(" " + msg);
/* 50 */     sbf.append(">\n");
/* 51 */     super.addTransition(element, sbf);
/* 52 */     sbf.append("</sub-process>\n");
/* 53 */     return sbf.toString();
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.bpm.ILog.elements.impl.BpmSubProcessImpl
 * JD-Core Version:    0.6.0
 */