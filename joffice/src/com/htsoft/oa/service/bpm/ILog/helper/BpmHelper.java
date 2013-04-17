/*     */ package com.htsoft.oa.service.bpm.ILog.helper;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.dom4j.Attribute;
/*     */ import org.dom4j.Document;
/*     */ import org.dom4j.Element;
/*     */ 
/*     */ public class BpmHelper extends AddTransition
/*     */ {
/*  27 */   private static int STARTLABELHEIGHT = 12;
/*     */   private Document getXMLDocument;
/*     */ 
/*     */   public BpmHelper(Document document)
/*     */   {
/*  36 */     setGetXMLDocument(document);
/*     */   }
/*     */ 
/*     */   public void setGetXMLDocument(Document getXMLDocument)
/*     */   {
/*  42 */     this.getXMLDocument = getXMLDocument;
/*     */   }
/*     */ 
/*     */   protected String getNodeLabel(Element element, String qName)
/*     */   {
/*  54 */     String str = "";
/*     */ 
/*  56 */     Iterator it = element.elements().iterator();
/*  57 */     while (it.hasNext()) {
/*  58 */       Element el = (Element)it.next();
/*  59 */       if (el.getName().equals(qName)) {
/*  60 */         str = el.getText();
/*  61 */         break;
/*     */       }
/*     */     }
/*  64 */     return str;
/*     */   }
/*     */ 
/*     */   protected Map<String, String> getNodeLabels(Element element, String[] qNames)
/*     */   {
/*  76 */     Map map = new HashMap();
/*     */ 
/*  78 */     Iterator it = element.elements().iterator();
/*  79 */     while (it.hasNext()) {
/*  80 */       Element el = (Element)it.next();
/*  81 */       for (int i = 0; i < qNames.length; i++) {
/*  82 */         String qName = qNames[i];
/*  83 */         if ((el.getName().equals(qName)) && (el.getTextTrim() != null)) {
/*  84 */           map.put(qName, el.getText());
/*     */         }
/*     */       }
/*     */     }
/*  88 */     return map;
/*     */   }
/*     */ 
/*     */   protected String getAttribute(Element el, String attributeName)
/*     */   {
/* 100 */     String str = "";
/* 101 */     Attribute obj = el.attribute(attributeName);
/* 102 */     if ((obj != null) && (obj.getValue() != ""))
/* 103 */       str = obj.getValue();
/* 104 */     return str;
/*     */   }
/*     */ 
/*     */   protected List<Element> baseSFAttributeSearch(String attributeName, String attributeValue)
/*     */   {
/* 117 */     List list = new ArrayList();
/*     */ 
/* 119 */     Iterator it = this.getXMLDocument.getRootElement()
/* 120 */       .elements("SequenceFlow").iterator();
/* 121 */     while (it.hasNext()) {
/* 122 */       Element el = (Element)it.next();
/* 123 */       String name = el.attributeValue(attributeName);
/* 124 */       if ((name != null) && (name.equals(attributeValue)))
/* 125 */         list.add(el);
/*     */     }
/* 127 */     return list;
/*     */   }
/*     */ 
/*     */   protected String getAttributes(Element el)
/*     */   {
/* 138 */     String str = "";
/* 139 */     int x = 0;
/* 140 */     int y = 0;
/* 141 */     String width = "48";
/* 142 */     String height = "48";
/*     */ 
/* 144 */     String xs = el.attributeValue("x");
/* 145 */     if ((xs != null) && (!xs.equals(""))) {
/* 146 */       x = Integer.valueOf(subString(xs)).intValue();
/*     */     }
/* 148 */     String ys = el.attributeValue("y");
/* 149 */     y = Integer.valueOf(subString(ys)).intValue();
/* 150 */     if (el.getName().equals("StartEvent"))
/* 151 */       y -= STARTLABELHEIGHT / 2;
/* 152 */     else if (el.getName().equals("EndEvent")) {
/* 153 */       y -= 2;
/*     */     }
/* 155 */     if ((el.getName().equals("Task")) || (el.getName().equals("SubProcess"))) {
/* 156 */       String widths = el.attributeValue("width");
/* 157 */       if ((widths != null) && (!widths.equals("")))
/* 158 */         width = subString(widths);
/* 159 */       String heights = el.attributeValue("height");
/* 160 */       if ((heights != null) && (!heights.equals(""))) {
/* 161 */         height = subString(heights);
/*     */       }
/*     */     }
/* 164 */     String name = getNodeLabel(el, "label");
/* 165 */     str = str + "name=\"" + name + "\"";
/* 166 */     if (el.getName().equalsIgnoreCase("SequenceFlow"))
/* 167 */       str = str + " g=\"-52,-22\"";
/*     */     else
/* 169 */       str = str + " g=\"" + x + "," + y + "," + width + "," + height + "\"";
/* 170 */     return str;
/*     */   }
/*     */ 
/*     */   protected String getSequenceFlowLabel(String startPort, String endPort)
/*     */   {
/* 184 */     Element element = null;
/*     */ 
/* 186 */     Iterator it = this.getXMLDocument.getRootElement()
/* 187 */       .elements("SequenceFlow").iterator();
/* 188 */     while (it.hasNext()) {
/* 189 */       Element el = (Element)it.next();
/* 190 */       String sp = el.attributeValue("startPort");
/* 191 */       String ep = el.attributeValue("endPort");
/* 192 */       if ((sp.equals(startPort)) && (ep != null) && (ep.equals(endPort))) {
/* 193 */         element = el;
/* 194 */         break;
/*     */       }
/*     */     }
/* 197 */     if (element != null) {
/* 198 */       return getNodeLabel(element, "label");
/*     */     }
/* 200 */     return "";
/*     */   }
/*     */ 
/*     */   protected List<String> getChildInfo(Element element)
/*     */   {
/* 210 */     List list = new ArrayList();
/*     */ 
/* 212 */     List subList = element.elements("ports");
/* 213 */     if ((subList != null) && (subList.size() > 0)) {
/* 214 */       Element el = (Element)subList.get(0);
/*     */ 
/* 216 */       Iterator it = el.elements("Port").iterator();
/* 217 */       while (it.hasNext()) {
/* 218 */         Element e = (Element)it.next();
/* 219 */         String x = e.attributeValue("x");
/* 220 */         String y = e.attributeValue("y");
/* 221 */         if ((x != null) || (y != null)) {
/* 222 */           list.add(e.attributeValue("id"));
/*     */         }
/*     */       }
/*     */     }
/* 226 */     return list;
/*     */   }
/*     */ 
/*     */   protected String baseStartPortGetEndPortId(Element element, String startPortId)
/*     */   {
/* 239 */     List list = baseSFAttributeSearch("startPort", startPortId);
/* 240 */     if ((list != null) && (list.size() > 0)) {
/* 241 */       Element el = (Element)list.get(0);
/* 242 */       String endPortId = getAttribute(el, "endPort");
/* 243 */       return endPortId;
/*     */     }
/* 245 */     return null;
/*     */   }
/*     */ 
/*     */   protected String baseStartPortSearchParentLabel(String startPort)
/*     */   {
/* 255 */     String str = "";
/*     */ 
/* 257 */     Iterator it = this.getXMLDocument.getRootElement().elements()
/* 258 */       .iterator();
/* 259 */     while (it.hasNext()) {
/* 260 */       Element element = (Element)it.next();
/* 261 */       Element ports = element.element("ports");
/* 262 */       if (ports == null)
/*     */         continue;
/* 264 */       Iterator subIt = ports.elementIterator();
/* 265 */       while (subIt.hasNext()) {
/* 266 */         String id = ((Element)subIt.next()).attributeValue("id");
/* 267 */         if ((id != null) && (id.equals(startPort))) {
/* 268 */           str = getNodeLabel(element, "label");
/*     */         }
/*     */       }
/*     */     }
/* 272 */     return str;
/*     */   }
/*     */ 
/*     */   private String subString(String str)
/*     */   {
/* 286 */     if ((str != null) && (!str.equals(""))) {
/* 287 */       int index = str.indexOf(".");
/* 288 */       if (index > 0)
/* 289 */         str = str.substring(0, index);
/* 290 */       return str;
/*     */     }
/* 292 */     return "";
/*     */   }
/*     */ 
/*     */   protected void addTransition(Element element, StringBuffer sbf)
/*     */   {
/* 302 */     List list = getChildInfo(element);
/* 303 */     Iterator it = list.iterator();
/* 304 */     while (it.hasNext())
/*     */     {
/* 306 */       String startPortId = (String)it.next();
/*     */ 
/* 309 */       String endPortId = baseStartPortGetEndPortId(element, startPortId);
/*     */ 
/* 312 */       String label = getSequenceFlowLabel(startPortId, endPortId);
/*     */ 
/* 315 */       String toName = baseStartPortSearchParentLabel(endPortId);
/*     */ 
/* 318 */       int[] startEle = getNodeAttributeValues(element);
/* 319 */       Element endElement = baseStartPortSearchParent(endPortId);
/* 320 */       int[] endEle = getNodeAttributeValues(endElement);
/*     */ 
/* 323 */       String endStr = getXOrYString(endElement, endPortId);
/* 324 */       String startStr = getXOrYString(element, startPortId);
/* 325 */       String pointString = "";
/*     */ 
/* 327 */       if ((endStr != null) && (!endStr.equals("")))
/* 328 */         pointString = super.getPointString(startStr, endStr, startEle, endEle, element.getName(), endElement.getName());
/* 329 */       if ((toName != null) && (!toName.equals(""))) {
/* 330 */         sbf.append("\t<transition g=\"");
/* 331 */         if ((pointString != null) && (!pointString.equals("")))
/* 332 */           sbf.append(pointString + ":");
/* 333 */         sbf.append("-50,-22\" name=\"" + label + "\" to=\"" + toName + "\" />\n");
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private int[] getNodeAttributeValues(Element element)
/*     */   {
/* 342 */     int[] values = { 70, 70, 52, 48 };
/* 343 */     if (element != null) {
/* 344 */       String xs = element.attributeValue("x");
/* 345 */       if ((xs != null) && (!xs.equals("")))
/* 346 */         values[0] = Integer.valueOf(subString(xs)).intValue();
/* 347 */       String ys = element.attributeValue("y");
/* 348 */       if ((ys != null) && (!ys.equals("")))
/* 349 */         values[1] = Integer.valueOf(subString(ys)).intValue();
/* 350 */       String ws = element.attributeValue("width");
/* 351 */       if ((ws != null) && (!ws.equals("")))
/* 352 */         values[2] = Integer.valueOf(subString(ws)).intValue();
/* 353 */       String hs = element.attributeValue("height");
/* 354 */       if ((hs != null) && (!hs.equals("")))
/* 355 */         values[3] = Integer.valueOf(subString(hs)).intValue();
/*     */     }
/* 357 */     return values;
/*     */   }
/*     */ 
/*     */   private Element baseStartPortSearchParent(String startPort)
/*     */   {
/* 364 */     Element element = null;
/*     */ 
/* 366 */     Iterator it = this.getXMLDocument.getRootElement().elements()
/* 367 */       .iterator();
/* 368 */     while (it.hasNext()) {
/* 369 */       Element el = (Element)it.next();
/* 370 */       Element ports = el.element("ports");
/* 371 */       if (ports == null)
/*     */         continue;
/* 373 */       Iterator subIt = ports.elementIterator();
/* 374 */       while (subIt.hasNext()) {
/* 375 */         String id = ((Element)subIt.next()).attributeValue("id");
/* 376 */         if ((id != null) && (id.equals(startPort))) {
/* 377 */           element = el;
/*     */         }
/*     */       }
/*     */     }
/* 381 */     return element;
/*     */   }
/*     */ 
/*     */   private String getXOrYString(Element element, String portId)
/*     */   {
/* 393 */     String str = "";
/* 394 */     if (element != null)
/*     */     {
/* 396 */       Iterator it = element.element("ports").elements()
/* 397 */         .iterator();
/* 398 */       while (it.hasNext()) {
/* 399 */         Element e = (Element)it.next();
/* 400 */         String id = e.attributeValue("id");
/* 401 */         String x = e.attributeValue("x");
/* 402 */         String y = e.attributeValue("y");
/* 403 */         if ((id != null) && (id.equals(portId)) && (x != null))
/* 404 */           return "x=" + x;
/* 405 */         if ((id != null) && (id.equals(portId)) && (y != null))
/* 406 */           return "y=" + y;
/*     */       }
/*     */     }
/* 409 */     return str;
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.bpm.ILog.helper.BpmHelper
 * JD-Core Version:    0.6.0
 */