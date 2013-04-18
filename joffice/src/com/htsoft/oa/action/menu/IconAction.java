/*     */ package com.htsoft.oa.action.menu;
/*     */ 
/*     */ import com.htsoft.core.util.AppUtil;
/*     */ import com.htsoft.core.web.action.BaseAction;
/*     */ import java.io.File;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.dom4j.Attribute;
/*     */ import org.dom4j.Document;
/*     */ import org.dom4j.DocumentException;
/*     */ import org.dom4j.Element;
/*     */ import org.dom4j.io.SAXReader;
/*     */ 
/*     */ public class IconAction extends BaseAction
/*     */ {
/*  31 */   private static String xmlPath = AppUtil.getAppAbsolutePath()
/*  30 */     .replace("\\", 
/*  30 */     "/") + 
/*  31 */     "/css/icon.xml";
/*     */   private String id;
/*     */ 
/*     */   public String getId()
/*     */   {
/*  37 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id) {
/*  41 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public String list()
/*     */   {
/*  46 */     List list = null;
/*  47 */     Element element = getXMLDocument().getRootElement();
/*  48 */     if ((this.id != null) && (!this.id.trim().equals("")) && (!this.id.trim().equals("0"))) {
/*  49 */       List elist = element.selectNodes("//Items[@id='" + this.id + "']");
/*  50 */       if ((elist != null) && (elist.size() > 0))
/*  51 */         list = ((Element)elist.get(0)).elements("icon");
/*     */     } else {
/*  53 */       list = element.selectNodes("//icon");
/*  54 */     }StringBuffer dt = new StringBuffer(",icon:[");
/*  55 */     for (int i = 0; i < list.size(); i++) {
/*  56 */       Element ae = (Element)list.get(i);
/*  57 */       String text = ae.attributeValue("text");
/*  58 */       String url = ae.attributeValue("url");
/*  59 */       dt.append("{").append("'text':'" + text + "','url':'" + url + "'").append("}");
/*  60 */       if (i != list.size() - 1)
/*  61 */         dt.append(",");
/*     */     }
/*  63 */     dt.append("]");
/*  64 */     String msg = "{success:true" + dt.toString() + "}";
/*  65 */     setJsonString(msg);
/*  66 */     return "success";
/*     */   }
/*     */ 
/*     */   public String loadTree()
/*     */   {
/*  74 */     StringBuffer sbf = new StringBuffer("");
/*  75 */     sbf.append("[{id:'0',text:'" + AppUtil.getCompanyName() + 
/*  76 */       "',expanded:true,children:[");
/*  77 */     Document document = getXMLDocument();
/*  78 */     if (document != null) {
/*  79 */       Element root = document.getRootElement();
/*  80 */       List list = root.elements();
/*  81 */       Iterator it = list.iterator();
/*  82 */       int iCount = 0;
/*  83 */       while (it.hasNext()) {
/*  84 */         Element el = (Element)it.next();
/*  85 */         String ntn = el.getName();
/*  86 */         boolean bo = (ntn != null) && ("items".equalsIgnoreCase(ntn));
/*     */ 
/*  88 */         if (!bo)
/*     */           break;
/*  90 */         sbf.append(getElementValue(el));
/*  91 */         iCount++;
/*     */       }
/*  93 */       if ((list != null) && (list.size() == iCount))
/*  94 */         sbf.deleteCharAt(sbf.length() - 1);
/*     */     }
/*  96 */     sbf.append("]}]");
/*  97 */     setJsonString(sbf.toString());
/*  98 */     return "success";
/*     */   }
/*     */ 
/*     */   private String findChidNode(Element el)
/*     */   {
/* 106 */     List<Element> list = el.elements();
/* 107 */     StringBuffer bf = new StringBuffer("");
/* 108 */     if ((list == null) || (list.size() == 0)) {
/* 109 */       bf.append("leaf:true},");
/*     */     } else {
/* 111 */       int iCount = 0;
/* 112 */       StringBuffer subStr = new StringBuffer("children:[");
/* 113 */       for (Element e : list) {
/* 114 */         String ntn = e.getName();
/* 115 */         boolean bo = (ntn != null) && ("items".equalsIgnoreCase(ntn));
/*     */ 
/* 117 */         if (!bo)
/*     */           break;
/* 119 */         subStr.append(getElementValue(e));
/* 120 */         iCount++;
/*     */       }
/* 122 */       if ((list.size() == iCount) && (iCount != 0)) {
/* 123 */         subStr.deleteCharAt(subStr.length() - 1);
/* 124 */         subStr.append("]},");
/* 125 */         bf.append(subStr);
/*     */       } else {
/* 127 */         bf.append("leaf:true},");
/*     */       }
/*     */     }
/* 129 */     return bf.toString();
/*     */   }
/*     */ 
/*     */   private StringBuffer getElementValue(Element e)
/*     */   {
/* 136 */     StringBuffer subStr = new StringBuffer("");
/* 137 */     String id = e.attribute("id").getValue();
/* 138 */     String text = e.attribute("text") != null ? e.attribute("text")
/* 139 */       .getValue() : "";
/* 140 */     String iconCls = e.attribute("iconCls") != null ? e.attribute("iconCls")
/* 141 */       .getValue() : "";
/* 142 */     subStr.append("{id:'" + id + "',text:'" + text).append("',iconCls:'" + iconCls)
/* 143 */       .append("',");
/* 144 */     subStr.append(findChidNode(e));
/* 145 */     return subStr;
/*     */   }
/*     */ 
/*     */   private Document getXMLDocument()
/*     */   {
/* 152 */     Document document = null;
/*     */     try {
/* 154 */       SAXReader reader = new SAXReader();
/* 155 */       document = reader.read(new File(xmlPath));
/*     */     } catch (DocumentException e) {
/* 157 */       e.printStackTrace();
/*     */     }
/* 159 */     return document;
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.action.menu.IconAction
 * JD-Core Version:    0.6.0
 */