/*     */ package com.htsoft.oa.util;
/*     */ 
/*     */ import com.htsoft.core.model.DynaModel;
/*     */ import com.htsoft.core.util.AppUtil;
/*     */ import com.htsoft.core.util.XmlUtil;
/*     */ import com.htsoft.oa.FlowConstants;
/*     */ import com.htsoft.oa.model.flow.FormTable;
/*     */ import com.htsoft.oa.service.flow.FormTableService;
/*     */ import java.io.File;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.dom4j.Document;
/*     */ import org.dom4j.Element;
/*     */ 
/*     */ public class FlowUtil
/*     */ {
/*  22 */   public static Map<String, DynaModel> DynaModelMap = new HashMap();
/*     */ 
/*     */   public static String getPrimaryKeyByEntity(String EntityName)
/*     */   {
/*  26 */     String rootPath = AppUtil.getAppAbsolutePath();
/*     */ 
/*  28 */     String hbmDirPath = rootPath + "WEB-INF/classes/com/htsoft/oa/model/process";
/*  29 */     String hbmFile = hbmDirPath + "/" + EntityName + ".hbm.xml";
/*  30 */     Document doc = XmlUtil.load(hbmFile);
/*  31 */     Element root = doc.getRootElement();
/*  32 */     Element idNode = (Element)root.selectSingleNode("class/id");
/*  33 */     String primaryKey = idNode.attributeValue("name");
/*  34 */     return primaryKey;
/*     */   }
/*     */ 
/*     */   public static void initDynModel()
/*     */   {
/*  41 */     FormTableService formTableService = (FormTableService)AppUtil.getBean("formTableService");
/*     */ 
/*  43 */     List<FormTable> formTables = formTableService.getAllAndFields();
/*     */ 
/*  45 */     for (FormTable formTable : formTables) {
/*  46 */       DynaModel dynaModel = new DynaModel(formTable);
/*  47 */       DynaModelMap.put(formTable.getEntityName(), dynaModel);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static DynaModel getInitDynModel(File entityHbmFile)
/*     */   {
/*  55 */     String entityName = entityHbmFile.getName();
/*  56 */     String[] entitySplit = entityName.split("\\.");
/*  57 */     entityName = entitySplit[0];
/*  58 */     DynaModel model = new DynaModel(entityName);
/*  59 */     Document doc = XmlUtil.load(entityHbmFile);
/*  60 */     if (doc != null) {
/*  61 */       Element classRoot = doc.getRootElement().element("class");
/*  62 */       Iterator it = classRoot.elementIterator();
/*     */ 
/*  64 */       while (it.hasNext()) {
/*  65 */         Element fieldEl = (Element)it.next();
/*  66 */         String name = fieldEl.attributeValue("name");
/*  67 */         if ("id".equals(fieldEl.getName())) {
/*  68 */           String type = fieldEl.attributeValue("type");
/*  69 */           model.setType(name, (Class)FlowConstants.FIELD_TYPE_MAP.get(type));
/*  70 */           model.setPrimaryFieldName(name);
/*  71 */         } else if ("property".equals(fieldEl.getName())) {
/*  72 */           String type = fieldEl.attributeValue("type");
/*  73 */           model.setType(name, (Class)FlowConstants.FIELD_TYPE_MAP.get(type));
/*  74 */         } else if ("bag".equals(fieldEl.getName())) {
/*  75 */           model.setType(name, Collection.class);
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/*  80 */     return model;
/*     */   }
/*     */ 
/*     */   public static DynaModel getInitDynModel(String entityName)
/*     */   {
/*  86 */     DynaModel model = new DynaModel(entityName);
/*     */ 
/*  88 */     String entityHbmFile = AppUtil.getAppAbsolutePath() + "/WEB-INF/classes/com/htsoft/oa/model/process/" + entityName + ".hbm.xml";
/*  89 */     Document doc = XmlUtil.load(entityHbmFile);
/*     */ 
/*  91 */     if (doc != null) {
/*  92 */       Element classRoot = doc.getRootElement().element("class");
/*  93 */       Iterator it = classRoot.elementIterator();
/*     */ 
/*  95 */       while (it.hasNext()) {
/*  96 */         Element fieldEl = (Element)it.next();
/*  97 */         String name = fieldEl.attributeValue("name");
/*  98 */         if ("id".equals(fieldEl.getName())) {
/*  99 */           String type = fieldEl.attributeValue("type");
/* 100 */           model.setType(name, (Class)FlowConstants.FIELD_TYPE_MAP.get(type));
/* 101 */           model.setPrimaryFieldName(name);
/* 102 */         } else if ("property".equals(fieldEl.getName())) {
/* 103 */           String type = fieldEl.attributeValue("type");
/* 104 */           model.setType(name, (Class)FlowConstants.FIELD_TYPE_MAP.get(type)); } else {
/* 105 */           if (!"bag".equals(fieldEl.getName())) {
/*     */             continue;
/*     */           }
/* 108 */           model.setType(name, Collection.class);
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 113 */     return model;
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.util.FlowUtil
 * JD-Core Version:    0.6.0
 */