/*    */ package com.htsoft.oa.service.bpm.ILog.elements.impl;
/*    */ 
/*    */ import com.htsoft.oa.service.bpm.ILog.elements.BpmElementsManager;
/*    */ import com.htsoft.oa.service.bpm.ILog.helper.BpmHelper;
/*    */ import org.dom4j.Document;
/*    */ import org.dom4j.Element;
/*    */ 
/*    */ public class BpmStartImpl extends BpmHelper
/*    */   implements BpmElementsManager
/*    */ {
/*    */   public BpmStartImpl(Document document)
/*    */   {
/* 23 */     super(document);
/*    */   }
/*    */ 
/*    */   public String getInfo(Element element)
/*    */   {
/* 34 */     StringBuffer sbf = new StringBuffer("<start ");
/* 35 */     sbf.append(super.getAttributes(element));
/* 36 */     sbf.append(">\n");
/* 37 */     super.addTransition(element, sbf);
/* 38 */     sbf.append("</start>\n");
/* 39 */     return sbf.toString();
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.bpm.ILog.elements.impl.BpmStartImpl
 * JD-Core Version:    0.6.0
 */