/*     */ package com.htsoft.oa.service.flow.impl;
/*     */ 
/*     */ import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.dom4j.Document;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.FileSystemResource;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.model.DynaModel;
import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.core.spring.SessionFactoryChangeEvent;
import com.htsoft.core.util.AppUtil;
import com.htsoft.core.util.XmlUtil;
import com.htsoft.oa.FlowConstants;
import com.htsoft.oa.dao.flow.FormFieldDao;
import com.htsoft.oa.dao.flow.FormTableDao;
import com.htsoft.oa.model.flow.FormField;
import com.htsoft.oa.model.flow.FormTable;
import com.htsoft.oa.service.flow.FormTableGenService;
import com.htsoft.oa.service.flow.FormTableService;
import com.htsoft.oa.util.FlowUtil;
/*     */ 
/*     */ public class FormTableGenServiceImpl extends BaseServiceImpl<FormTable>
/*     */   implements FormTableGenService, ApplicationContextAware
/*     */ {
/*  64 */   private String hibernateDialect = null;
/*     */   ApplicationContext context;
/*     */   private FormTableDao dao;
/*     */   private VelocityEngine velocityEngine;
/*     */ 
/*     */   @javax.annotation.Resource
/*     */   private FormFieldDao formFieldDao;
/*  96 */   private static Map<String, String> mapping = new HashMap();
/*     */   private static final int ORDER_1 = 1;
/*     */   private static final int ORDER_2 = 2;
/*     */ 
/*     */   public void setHibernateDialect(String hibernateDialect)
/*     */   {
/*  67 */     this.hibernateDialect = hibernateDialect;
/*     */   }
/*     */ 
/*     */   public void setApplicationContext(ApplicationContext applicationContext)
/*     */     throws BeansException
/*     */   {
/*  75 */     this.context = applicationContext;
/*     */   }
/*     */ 
/*     */   public void setVelocityEngine(VelocityEngine velocityEngine)
/*     */   {
/*  85 */     this.velocityEngine = velocityEngine;
/*     */   }
/*     */ 
/*     */   public FormTableGenServiceImpl(FormTableDao dao) {
/*  89 */     super(dao);
/*  90 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */   public boolean genBean(FormTable[] formTables)
/*     */   {
/* 100 */     mapping.clear();
/*     */     try {
/* 102 */       for (FormTable formTable : formTables) {
/* 103 */         createModelHbmFile(formTable);
/*     */       }
/* 105 */       if (createTable(mapping))
/*     */       {
/* 107 */         this.context.publishEvent(new SessionFactoryChangeEvent(mapping));
/*     */ 
/* 109 */         updateDynaModelMap(mapping);
/* 110 */         return true;
/*     */       }
/* 112 */       return false;
/*     */     }
/*     */     catch (Exception e) {
/* 115 */       e.printStackTrace();
/* 116 */     }return false;
/*     */   }
/*     */ 
/*     */   public void updateDynaModelMap(Map<String, String> mapping)
/*     */   {
/* 124 */     Collection<String> newMapCollect = mapping.keySet();
/* 125 */     for (String key : newMapCollect) {
/* 126 */       FormTableService formTableService = (FormTableService)AppUtil.getBean("formTableService");
/* 127 */       QueryFilter q = new QueryFilter(ServletActionContext.getRequest());
/* 128 */       int i = key.indexOf("_");
/* 129 */       String Q_key = key.substring(i + 1, key.length());
/* 130 */       q.addFilter("Q_tableKey_S_EQ", Q_key);
/* 131 */       List formTables = formTableService.getAll(q);
/* 132 */       if ((formTables != null) && (formTables.size() > 0)) {
/* 133 */         DynaModel dynaModel = new DynaModel((FormTable)formTables.get(0));
/* 134 */         FlowUtil.DynaModelMap.put(dynaModel.getEntityName(), dynaModel);
/* 135 */         if (this.logger.isInfoEnabled())
/* 136 */           this.logger.info("\t更新实体：" + key);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void deleteDynaModelMap(Map<String, String> mapping)
/*     */   {
/* 144 */     Collection<String> entitys = mapping.keySet();
/* 145 */     if ((entitys != null) && (entitys.size() > 0))
/* 146 */       for (String key : entitys) {
/* 147 */         String p = (String)mapping.get(key);
/* 148 */         File xmlFile = new File(p);
/* 149 */         if (this.logger.isErrorEnabled()) {
/* 150 */           Document xmlDoc = XmlUtil.load(xmlFile.getPath());
/* 151 */           this.logger.error(xmlFile.getPath() + ":" + XmlUtil.docToString(xmlDoc));
/*     */         }
/*     */ 
/* 154 */         if (xmlFile.delete()) {
/* 155 */           if (FlowUtil.DynaModelMap.containsKey(key)) {
/* 156 */             FlowUtil.DynaModelMap.remove(key);
/* 157 */             this.logger.info("\t删除实体:" + key);
/*     */           }
/* 159 */           this.logger.info("\t删除mxl文件" + xmlFile.getPath());
/*     */         }
/*     */         else {
/* 162 */           this.logger.info("\txml文件不存在:" + xmlFile.getPath());
/*     */         }
/*     */       }
/*     */   }
/*     */ 
/*     */   private boolean createTable(Map<String, String> mapping)
/*     */   {
/* 171 */     this.logger.info("create table start...");
/*     */ 
/* 173 */     Collection<String> xmlCollect = mapping.values();
/* 174 */     if ((xmlCollect != null) && (xmlCollect.size() > 0)) {
/* 175 */       DataSource dataSource = (DataSource)AppUtil.getBean("dataSource");
/* 176 */       LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
/* 177 */       factoryBean.setDataSource(dataSource);
/* 178 */       Properties prop = new Properties();
/* 179 */       prop.setProperty("connection.useUnicode", "true");
/* 180 */       prop.setProperty("connection.characterEncoding", "utf-8");
/*     */ 
/* 182 */       prop.setProperty("hibernate.dialect", this.hibernateDialect);
/*     */ 
/* 184 */       prop.setProperty("hibernate.show_sql", "true");
/* 185 */       prop.setProperty("hibernate.jdbc.batch_size", "20");
/* 186 */       prop.setProperty("hibernate.jdbc.fetch_size", "20");
/* 187 */       prop.setProperty("hibernate.cache.provider_class", 
/* 188 */         "org.hibernate.cache.EhCacheProvider");
/* 189 */       prop.setProperty("hibernate.cache.use_second_level_cache", "true");
/* 190 */       prop.setProperty("hibernate.hbm2ddl.auto", "update");
/* 191 */       factoryBean.setHibernateProperties(prop);
/*     */ 
/* 194 */       org.springframework.core.io.Resource[] mappingLocations = new FileSystemResource[xmlCollect.size()];
/* 195 */       int i = 0;
/* 196 */       for (String p : xmlCollect) {
/* 197 */         mappingLocations[(i++)] = new FileSystemResource(p);
/*     */       }
/* 199 */       factoryBean.setMappingLocations(mappingLocations);
/*     */       try
/*     */       {
/* 202 */         factoryBean.afterPropertiesSet();
/* 203 */         return true;
/*     */       } catch (Exception e) {
/* 205 */         this.logger.info("\t更新xml文件错误:" + e.getMessage());
/* 206 */         deleteDynaModelMap(mapping);
/* 207 */         return false;
/*     */       }
/*     */     }
/* 210 */     this.logger.info("\t没有要更新的xml文件!");
/* 211 */     return false;
/*     */   }
/*     */ 
/*     */   private void createModelHbmFile(FormTable formTable)
/*     */     throws ResourceNotFoundException, ParseErrorException, Exception
/*     */   {
/* 229 */     Template template = this.velocityEngine.getTemplate("codegen/Model.vm");
/*     */ 
/* 234 */     VelocityContext context = new VelocityContext();
/*     */ 
/* 236 */     String TableName = formTable.getEntityName();
/* 237 */     String EntityName = TableName;
/*     */ 
/* 239 */     context.put("EntityName", EntityName);
/* 240 */     context.put("TableName", TableName);
/*     */ 
/* 242 */     Iterator fieldIterator = formTable.getFormFields()
/* 243 */       .iterator();
/* 244 */     List list = new ArrayList();
/*     */ 
/* 246 */     boolean hadPrimary = false;
/*     */ 
/* 248 */     while (fieldIterator.hasNext()) {
/* 249 */       FormField formField = (FormField)fieldIterator.next();
/*     */       Map m;
/* 250 */       if ((StringUtils.isNotEmpty(formField.getForeignTable())) && 
/* 251 */         (StringUtils.isNotEmpty(formField.getForeignKey())))
/*     */       {
/* 253 */         String foreignTable = "WF_" + 
/* 254 */           formField.getForeignTable();
/* 255 */         String foreignEntity = foreignTable;
/*     */ 
/* 257 */         m = new HashMap();
/* 258 */         m.put("bag", "");
/* 259 */         m.put("order", Integer.valueOf(2));
/* 260 */         m.put("foreignEntity", foreignEntity);
/* 261 */         String foreignKey = formField.getForeignKey();
/* 262 */         m.put("foreignKey", foreignKey);
/*     */ 
/* 264 */         list.add(m);
/*     */       }
/*     */       else
/*     */       {
/* 268 */         if (formField.getIsPrimary().intValue() == 1) {
/* 269 */           hadPrimary = true;
/*     */ 
/* 271 */           List<FormField> setList = this.formFieldDao.getByForeignTableAndKey(formField.getFormTable().getTableKey(), formField.getFieldName());
/* 272 */           if ((setList != null) && (setList.size() > 0))
/*     */           {
/* 274 */             for (FormField f : setList) {
/* 275 */               FormTable ft = f.getFormTable();
/* 276 */               String table_Key = ft.getEntityName();
/* 277 */               String Entity_Name = table_Key;
/* 278 */               m = new HashMap();
/* 279 */               m.put("order", Integer.valueOf(2));
/* 280 */               m.put("isPrimary", FormField.NOT_PRIMARY_KEY);
/*     */ 
/* 282 */               m.put("foreignClass", "");
/* 283 */               m.put("foreignEntity", "");
/* 284 */               m.put("foreignKey", "");
/* 285 */               m.put("bag", "true");
/*     */ 
/* 287 */               m.put("bagTable", table_Key);
/*     */ 
/* 289 */               String bagName = Entity_Name + "s";
/* 290 */               m.put("bagName", bagName);
/*     */ 
/* 292 */               String fieldName = formField.getFieldName();
/* 293 */               m.put("bagColumn", fieldName);
/* 294 */               m.put("bagEntity", Entity_Name);
/* 295 */               list.add(m);
/*     */             }
/*     */           }
/*     */         }
/*     */ 
/* 300 */         m = new HashMap();
/*     */ 
/* 302 */         String fieldSize = "";
/* 303 */         if (formField.getFieldSize() != null) {
/* 304 */           fieldSize = formField.getFieldSize().toString();
/*     */         }
/* 306 */         m.put("fieldSize", fieldSize);
/* 307 */         m.put("isRequired", formField.getIsRequired());
/* 308 */         m.put("showFormat", formField.getShowFormat());
/* 309 */         m.put("fieldDscp", formField.getFieldDscp());
/*     */ 
/* 311 */         m.put("fieldType", ((Class)FlowConstants.FIELD_TYPE_MAP.get(formField.getFieldType())).getName());
/* 312 */         m.put("isPrimary", formField.getIsPrimary());
/*     */ 
/* 314 */         if (formField.getIsPrimary().shortValue() == FormField.PRIMARY_KEY.shortValue())
/* 315 */           m.put("order", Integer.valueOf(1));
/*     */         else {
/* 317 */           m.put("order", Integer.valueOf(2));
/*     */         }
/* 319 */         m.put("foreignClass", "");
/* 320 */         m.put("foreignEntity", "");
/* 321 */         m.put("foreignKey", "");
/* 322 */         m.put("bag", "");
/*     */ 
/* 324 */         String fieldName = formField.getFieldName();
/* 325 */         m.put("column", fieldName);
/* 326 */         m.put("property", fieldName);
/* 327 */         list.add(m);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 334 */     if (!hadPrimary) {
/* 335 */       Map m = new HashMap();
/* 336 */       m.put("fieldType", ((Class)FlowConstants.FIELD_TYPE_MAP.get("Long")).getName());
/* 337 */       m.put("isPrimary", FormField.PRIMARY_KEY);
/* 338 */       m.put("order", Integer.valueOf(1));
/* 339 */       m.put("foreignClass", "");
/* 340 */       m.put("foreignEntity", "");
/* 341 */       m.put("foreignKey", "");
/* 342 */       m.put("bag", "");
/* 343 */       String fieldName = EntityName + "Id";
/* 344 */       m.put("column", fieldName);
/* 345 */       m.put("property", fieldName);
/* 346 */       list.add(m);
/*     */     }
/*     */ 
/* 349 */     Map m = new HashMap();
/*     */ 
/* 351 */     m.put("fieldType", ((Class)FlowConstants.FIELD_TYPE_MAP.get("Long")).getName());
/* 352 */     m.put("isPrimary", FormField.NOT_PRIMARY_KEY);
/* 353 */     m.put("order", Integer.valueOf(2));
/* 354 */     m.put("foreignClass", "");
/* 355 */     m.put("foreignEntity", "");
/* 356 */     m.put("foreignKey", "");
/* 357 */     m.put("bag", "");
/* 358 */     m.put("column", "runId");
/* 359 */     m.put("property", "runId");
/* 360 */     list.add(m);
/* 361 */     context.put("fields", list);
/*     */ 
/* 363 */     Collections.sort(list, new Comparator() {
/*     */       public int compare(Object arg0, Object arg1) {
/* 365 */         Integer i1 = (Integer)((Map)arg0).get("order");
/* 366 */         Integer i2 = (Integer)((Map)arg1).get("order");
/* 367 */         return i1.compareTo(i2);
/*     */       }
/*     */     });
/* 372 */     StringWriter writer = new StringWriter();
/* 373 */     template.merge(context, writer);
/*     */ 
/* 375 */     String beanStr = writer.toString();
/*     */ 
/* 377 */     writeXmlToFile(EntityName, beanStr);
/*     */   }
/*     */ 
/*     */   private void writeXmlToFile(String EntityName, String beanXml)
/*     */     throws IOException
/*     */   {
/* 386 */     String rootPath = AppUtil.getAppAbsolutePath();
/* 387 */     String hbmdir = rootPath + "WEB-INF/classes/com/htsoft/oa/model/process";
/* 388 */     File fileSrc = new File(hbmdir);
/* 389 */     if ((!fileSrc.exists()) && 
/* 390 */       (!fileSrc.mkdirs())) {
/* 391 */       this.logger.info("创建目录失败:" + fileSrc.getPath());
/*     */     }
/*     */ 
/* 394 */     String xmlPath = hbmdir + "/" + EntityName + ".hbm.xml";
/*     */ 
/* 396 */     BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(xmlPath)), "UTF-8"));
/* 397 */     bw.write(beanXml);
/* 398 */     bw.close();
/*     */ 
/* 401 */     mapping.put(EntityName, hbmdir + "/" + EntityName + ".hbm.xml");
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\estbpm-orcl\estbpm\WEB-INF\classes\
 * Qualified Name:     com.htsoft.est.service.flow.impl.FormTableGenServiceImpl
 * JD-Core Version:    0.6.0
 */