/*    */ package com.htsoft.oa.service.bpm.ILog.factory;
/*    */ 
/*    */ import com.htsoft.oa.service.bpm.ILog.elements.BpmElementsManager;
/*    */ import com.htsoft.oa.service.bpm.ILog.elements.impl.BpmEndImpl;
/*    */ import com.htsoft.oa.service.bpm.ILog.elements.impl.BpmGatewayImpl;
/*    */ import com.htsoft.oa.service.bpm.ILog.elements.impl.BpmStartImpl;
/*    */ import com.htsoft.oa.service.bpm.ILog.elements.impl.BpmSubProcessImpl;
/*    */ import com.htsoft.oa.service.bpm.ILog.elements.impl.BpmTaskImpl;
/*    */ import org.dom4j.Document;
/*    */ import org.dom4j.Element;
/*    */ 
/*    */ public class BpmFactory
/*    */ {
/*    */   private Document getDocument;
/*    */   private String[] nodeType;
/*    */ 
/*    */   public BpmFactory(Document document)
/*    */   {
/* 34 */     this.nodeType = new String[] { "StartEvent", "Task", "Gateway", "EndEvent", 
/* 35 */       "SequenceFlow", "SubProcess" };
/* 36 */     this.getDocument = document;
/*    */   }
/*    */ 
/*    */   public String getInfo(Element element, String nodeName)
/*    */   {
/* 48 */     String str = "";
/* 49 */     BpmElementsManager bpmManager = null;
/* 50 */     if (nodeName.equalsIgnoreCase(this.nodeType[0]))
/* 51 */       bpmManager = new BpmStartImpl(this.getDocument);
/* 52 */     else if (nodeName.equalsIgnoreCase(this.nodeType[1]))
/* 53 */       bpmManager = new BpmTaskImpl(this.getDocument);
/* 54 */     else if (nodeName.equalsIgnoreCase(this.nodeType[2]))
/* 55 */       bpmManager = new BpmGatewayImpl(this.getDocument);
/* 56 */     else if (nodeName.equalsIgnoreCase(this.nodeType[3]))
/* 57 */       bpmManager = new BpmEndImpl(this.getDocument);
/* 58 */     else if (nodeName.equalsIgnoreCase(this.nodeType[5]))
/* 59 */       bpmManager = new BpmSubProcessImpl(this.getDocument);
/* 60 */     if (bpmManager != null)
/* 61 */       str = bpmManager.getInfo(element);
/* 62 */     return str;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.bpm.ILog.factory.BpmFactory
 * JD-Core Version:    0.6.0
 */