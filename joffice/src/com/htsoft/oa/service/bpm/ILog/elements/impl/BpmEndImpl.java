/*    */ package com.htsoft.oa.service.bpm.ILog.elements.impl;
/*    */ 
/*    */ import com.htsoft.oa.service.bpm.ILog.elements.BpmElementsManager;
/*    */ import com.htsoft.oa.service.bpm.ILog.helper.BpmHelper;
/*    */ import org.dom4j.Document;
/*    */ import org.dom4j.Element;
/*    */ 
/*    */ public class BpmEndImpl extends BpmHelper
/*    */   implements BpmElementsManager
/*    */ {
/*    */   public BpmEndImpl(Document document)
/*    */   {
/* 22 */     super(document);
/*    */   }
/*    */ 
/*    */   public String getInfo(Element element)
/*    */   {
/* 33 */     StringBuffer sbf = new StringBuffer("<");
/* 34 */     String trigger = super.getNodeLabel(element, "trigger");
/* 35 */     if (trigger != null) {
/* 36 */       if (trigger.equalsIgnoreCase("Error"))
/* 37 */         sbf.append("end-error");
/* 38 */       else if (trigger.equals("Cancel"))
/* 39 */         sbf.append("end-cancel");
/*    */       else
/* 41 */         sbf.append("end");
/*    */     }
/* 43 */     else sbf.append("end");
/* 44 */     sbf.append(" " + super.getAttributes(element));
/* 45 */     sbf.append(" />\n");
/* 46 */     return sbf.toString();
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.bpm.ILog.elements.impl.BpmEndImpl
 * JD-Core Version:    0.6.0
 */