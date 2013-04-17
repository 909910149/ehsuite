/*     */ package com.htsoft.test.jbpm;
/*     */ 
/*     */ import com.htsoft.core.util.XmlUtil;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.dom4j.Document;
/*     */ import org.dom4j.DocumentHelper;
/*     */ import org.dom4j.Element;
/*     */ import org.dom4j.Node;
/*     */ 
/*     */ public class XmlConvertTestCase
/*     */ {
/*     */   public static void main(String[] args)
/*     */   {
/*  20 */     Document jpdlDoc = DocumentHelper.createDocument();
/*  21 */     Element processEl = jpdlDoc.addElement("process");
/*     */ 
/*  23 */     processEl.addAttribute("name", "testProcess");
/*     */ 
/*  25 */     Set transitionSet = new HashSet();
/*     */ 
/*  27 */     Map nodeMap = parseDrawXml(transitionSet);
/*     */ 
/*  29 */     Map newNodeMap = new LinkedHashMap();
/*  30 */     Iterator ids = nodeMap.keySet().iterator();
/*     */ 
/*  32 */     while (ids.hasNext()) {
/*  33 */       String id = (String)ids.next();
/*  34 */       Element pNode = (Element)nodeMap.get(id);
/*     */ 
/*  36 */       Element curNewNode = processEl.addElement(pNode.getQualifiedName());
/*     */ 
/*  38 */       String x = pNode.attributeValue("x");
/*  39 */       String y = pNode.attributeValue("y");
/*     */ 
/*  41 */       String width = pNode.attributeValue("w");
/*  42 */       Integer intWidth = Integer.valueOf(new Integer(width).intValue() + 10);
/*     */ 
/*  44 */       String height = pNode.attributeValue("h");
/*  45 */       Integer intHeight = Integer.valueOf(new Integer(height).intValue() + 10);
/*     */ 
/*  47 */       curNewNode.addAttribute("name", pNode.attributeValue("name"));
/*  48 */       curNewNode.addAttribute("g", x + "," + y + "," + intWidth + "," + intHeight);
/*     */ 
/*  50 */       newNodeMap.put(id, curNewNode);
/*     */     }
/*     */ 
/*  53 */     Iterator tranIt = transitionSet.iterator();
/*     */ 
/*  55 */     while (tranIt.hasNext()) {
/*  56 */       Element tranEl = (Element)tranIt.next();
/*     */ 
/*  58 */       String g = tranEl.attributeValue("g");
/*  59 */       System.out.println("g:" + g);
/*  60 */       String name = tranEl.attributeValue("name");
/*     */ 
/*  63 */       Element startNode = (Element)tranEl.selectSingleNode("./startConnector/rConnector/Owner/*");
/*  64 */       Element endNode = (Element)tranEl.selectSingleNode("./endConnector/rConnector/Owner/*");
/*     */ 
/*  66 */       if ((startNode != null) && (endNode != null)) {
/*  67 */         String startRef = startNode.attributeValue("ref");
/*  68 */         String endRef = endNode.attributeValue("ref");
/*     */         Element endStartNode;
/*     */         Element newStartNode;
///*     */         Element endStartNode;
/*  72 */         if ((startRef != null) && (endRef != null)) {
/*  73 */           newStartNode = (Element)newNodeMap.get(startRef);
/*  74 */           endStartNode = (Element)newNodeMap.get(endRef);
/*     */         } else {
/*  76 */           String startId = startNode.attributeValue("id");
/*  77 */           String endId = startNode.attributeValue("id");
/*  78 */           newStartNode = (Element)newNodeMap.get(startId);
/*  79 */           endStartNode = (Element)newNodeMap.get(endId);
/*     */         }
/*  81 */         Element transitionEl = newStartNode.addElement("transition");
/*  82 */         transitionEl.addAttribute("name", name);
/*  83 */         transitionEl.addAttribute("to", endStartNode.attributeValue("name"));
/*  84 */         transitionEl.addAttribute("g", g);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static Map<String, Element> parseDrawXml(Set transitionSet)
/*     */   {
/*  93 */     Map map = new LinkedHashMap();
/*     */ 
/*  95 */     String path = "L:/devtools/workspace/joffice/test/com/htsoft/test/jbpm/NewFile1.xml";
/*     */ 
/*  97 */     Document drawDoc = XmlUtil.load(path);
/*  98 */     Element rootEl = drawDoc.getRootElement();
/*  99 */     List<Element> figures = rootEl.selectNodes("/drawing/figures/*");
/*     */ 
/* 101 */     for (Element el : figures) {
/* 102 */       String id = el.attributeValue("id");
/* 103 */       String ref = el.attributeValue("ref");
/* 104 */       if ("transition".equals(el.getQualifiedName())) {
/* 105 */         transitionSet.add(el);
/*     */       }
/* 107 */       else if (id != null) {
/* 108 */         map.put(id, el);
/* 109 */       } else if (ref != null) {
/* 110 */         Node figureNode = rootEl.selectSingleNode("/drawing/figures//*[@id='" + ref + "']");
/* 111 */         map.put(ref, (Element)figureNode);
/*     */       }
/*     */     }
/*     */ 
/* 115 */     return map;
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.jbpm.XmlConvertTestCase
 * JD-Core Version:    0.6.0
 */