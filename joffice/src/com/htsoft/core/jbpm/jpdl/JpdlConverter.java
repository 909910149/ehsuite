/*     */ package com.htsoft.core.jbpm.jpdl;
/*     */ 
/*     */ import com.htsoft.core.util.XmlUtil;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.dom4j.Document;
/*     */ import org.dom4j.DocumentHelper;
/*     */ import org.dom4j.Element;
/*     */ import org.dom4j.Node;
/*     */ 
/*     */ public class JpdlConverter
/*     */ {
/*  26 */   private static Log logger = LogFactory.getLog(JpdlConverter.class);
/*     */ 
/*     */   public static void main(String[] args) {
/*  29 */     String path = "D:/dev/workspace/joffice/web/file.xml";
/*     */ 
/*  31 */     String drawXml = XmlUtil.load(path).asXML();
/*     */ 
/*  33 */     String convertXml = JpdlGen(drawXml, "test");
/*  34 */     System.out.println("lastXML:" + convertXml);
/*     */   }
/*     */ 
/*     */   public static String JpdlGen(String drawXml, String processName)
/*     */   {
/*  51 */     if (logger.isDebugEnabled()) {
/*  52 */       logger.debug("drawXml:" + drawXml);
/*     */     }
/*     */ 
/*  55 */     Document jpdlDoc = DocumentHelper.createDocument();
/*     */ 
/*  57 */     jpdlDoc.setXMLEncoding(System.getProperty("file.encoding"));
/*     */ 
/*  59 */     Element processEl = jpdlDoc.addElement("process");
/*     */ 
/*  61 */     processEl.addAttribute("name", processName);
/*     */ 
/*  63 */     Document drawDoc = XmlUtil.stringToDocument(drawXml);
/*  64 */     Element orgRootEl = drawDoc.getRootElement();
/*     */ 
/*  67 */     Set transitionSet = new HashSet();
/*     */ 
/*  69 */     Map nodeMap = parseDrawXml(transitionSet, orgRootEl);
/*     */ 
/*  71 */     Iterator it = nodeMap.values().iterator();
/*     */ 
/*  73 */     while (it.hasNext()) {
/*  74 */       System.out.println(((Element)it.next()).asXML());
/*     */     }
/*     */ 
/*  77 */     Map newNodeMap = new LinkedHashMap();
/*  78 */     Iterator ids = nodeMap.keySet().iterator();
/*     */ 
/*  80 */     while (ids.hasNext()) {
/*  81 */       String id = (String)ids.next();
/*  82 */       Element pNode = (Element)nodeMap.get(id);
/*  83 */       Element curNewNode = processEl.addElement(pNode.getQualifiedName());
/*  84 */       String x = pNode.attributeValue("x");
/*  85 */       String y = pNode.attributeValue("y");
/*     */ 
/*  87 */       String width = pNode.attributeValue("w");
/*  88 */       Integer intWidth = Integer.valueOf(new Integer(width).intValue() + 10);
/*     */ 
/*  90 */       String height = pNode.attributeValue("h");
/*  91 */       Integer intHeight = Integer.valueOf(new Integer(height).intValue() + 10);
/*     */ 
/*  93 */       curNewNode.addAttribute("name", pNode.attributeValue("name"));
/*  94 */       curNewNode.addAttribute("g", x + "," + y + "," + intWidth + "," + intHeight);
/*     */ 
/*  96 */       newNodeMap.put(id, curNewNode);
/*     */     }
/*     */ 
/*  99 */     Iterator tranIt = transitionSet.iterator();
/*     */ 
/* 101 */     while (tranIt.hasNext()) {
/* 102 */       Element tranEl = (Element)tranIt.next();
/* 103 */       String g = tranEl.attributeValue("g");
/* 104 */       String name = tranEl.attributeValue("name");
/*     */ 
/* 107 */       Element startNode = (Element)tranEl.selectSingleNode("./startConnector/rConnector/Owner/*");
/* 108 */       Element endNode = (Element)tranEl.selectSingleNode("./endConnector/rConnector/Owner/*");
/*     */ 
/* 110 */       if ((startNode != null) && (endNode != null)) {
/* 111 */         String startRef = startNode.attributeValue("ref") != null ? startNode.attributeValue("ref") : startNode.attributeValue("id");
/* 112 */         String endRef = endNode.attributeValue("ref") != null ? endNode.attributeValue("ref") : endNode.attributeValue("id");
/*     */         Element newEndNode;
/*     */         Element newStartNode;
///*     */         Element newEndNode;
/* 117 */         if ((startRef != null) && (endRef != null)) {
/* 118 */           newStartNode = (Element)newNodeMap.get(startRef);
/* 119 */           newEndNode = (Element)newNodeMap.get(endRef);
/*     */         } else {
/* 121 */           String startId = startNode.attributeValue("id");
/* 122 */           String endId = startNode.attributeValue("id");
/* 123 */           newStartNode = (Element)newNodeMap.get(startId);
/* 124 */           newEndNode = (Element)newNodeMap.get(endId);
/*     */         }
/*     */ 
/* 127 */         Element transitionEl = newStartNode.addElement("transition");
/* 128 */         transitionEl.addAttribute("name", name);
/* 129 */         transitionEl.addAttribute("to", newEndNode.attributeValue("name"));
/* 130 */         transitionEl.addAttribute("g", g);
/*     */ 
/* 132 */         if ("decision".equals(newStartNode.getQualifiedName())) {
/* 133 */           Element conditionEl = (Element)orgRootEl.selectSingleNode("/drawing/figures//decision/conditions/condition[@to='" + name + "']");
/* 134 */           if (conditionEl != null) {
/* 135 */             Element newConditionEl = transitionEl.addElement("condition");
/* 136 */             newConditionEl.addAttribute("expr", conditionEl.attributeValue("expr"));
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 142 */     if (logger.isDebugEnabled()) {
/* 143 */       logger.debug("after convter jbpm xml:" + processEl.asXML());
/*     */     }
/*     */ 
/* 146 */     return jpdlDoc.asXML();
/*     */   }
/*     */ 
/*     */   private static Map<String, Element> parseDrawXml(Set transitionSet, Element rootEl)
/*     */   {
/* 152 */     Map map = new LinkedHashMap();
/*     */ 
/* 154 */     List<Element> figures = rootEl.selectNodes("/drawing/figures/*");
/*     */ 
/* 156 */     for (Element el : figures) {
/* 157 */       String id = el.attributeValue("id");
/* 158 */       String ref = el.attributeValue("ref");
/*     */ 
/* 160 */       if ("transition".equals(el.getQualifiedName()))
/*     */       {
/* 162 */         transitionSet.add(el);
/*     */ 
/* 164 */         List taskNodes = el.selectNodes("./*/rConnector/Owner/task");
/*     */ 
/* 166 */         for (int i = 0; i < taskNodes.size(); i++) {
/* 167 */           Element taskEl = (Element)taskNodes.get(i);
/* 168 */           String tId = taskEl.attributeValue("id");
/*     */ 
/* 170 */           if (tId != null) {
/* 171 */             map.put(tId, taskEl);
/*     */           }
/*     */         }
/*     */ 
/*     */       }
/* 176 */       else if (id != null) {
/* 177 */         map.put(id, el);
/* 178 */       } else if (ref != null) {
/* 179 */         Node figureNode = rootEl.selectSingleNode("/drawing/figures//*[@id='" + ref + "']");
/* 180 */         map.put(ref, (Element)figureNode);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 185 */     return map;
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\estbpm-orcl\estbpm\WEB-INF\classes\
 * Qualified Name:     com.htsoft.core.jbpm.jpdl.JpdlConverter
 * JD-Core Version:    0.6.0
 */