/*     */ package com.htsoft.oa.service.flow.impl;
/*     */ 
/*     */ import com.google.gson.Gson;
/*     */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*     */ import com.htsoft.core.util.BeanUtil;
/*     */ import com.htsoft.oa.dao.flow.FormDefDao;
/*     */ import com.htsoft.oa.model.flow.FormDef;
/*     */ import com.htsoft.oa.model.flow.FormField;
/*     */ import com.htsoft.oa.model.flow.FormTable;
/*     */ import com.htsoft.oa.service.flow.FormDefService;
/*     */ import com.htsoft.oa.service.flow.FormFieldService;
/*     */ import com.htsoft.oa.service.flow.FormTableService;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Resource;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.commons.logging.Log;
/*     */ 
/*     */ public class FormDefServiceImpl extends BaseServiceImpl<FormDef>
/*     */   implements FormDefService
/*     */ {
/*     */   private FormDefDao dao;
/*     */ 
/*     */   @Resource
/*     */   private FormTableService formTableService;
/*     */ 
/*     */   @Resource
/*     */   private FormFieldService formFieldService;
/*     */ 
/*     */   public FormDefServiceImpl(FormDefDao dao)
/*     */   {
/*  37 */     super(dao);
/*  38 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */   public List<FormDef> getByDeployId(String deployId)
/*     */   {
/*  46 */     return this.dao.getByDeployId(deployId);
/*     */   }
/*     */ 
/*     */   public FormDef getByDeployIdActivityName(String deployId, String activityName)
/*     */   {
/*  56 */     return this.dao.getByDeployIdActivityName(deployId, activityName);
/*     */   }
/*     */ 
/*     */   public FormDef saveFormDef(FormDef formDef, Map<FormTable, String> datas)
/*     */   {
/*  61 */     if (formDef.getFormDefId() == null) {
/*  62 */       this.dao.save(formDef);
/*     */     } else {
/*  64 */       FormDef orgFormDef = (FormDef)this.dao.get(formDef.getFormDefId());
/*  65 */       Set tables = orgFormDef.getFormTables();
/*  66 */       Set keys = datas.keySet();
/*  67 */       tables.retainAll(keys);
/*     */       try {
/*  69 */         BeanUtil.copyNotNullProperties(orgFormDef, formDef);
/*  70 */         orgFormDef.setFormTables(tables);
/*  71 */         formDef = (FormDef)this.dao.save(orgFormDef);
/*     */       } catch (Exception ex) {
/*  73 */         this.logger.error(ex.getMessage());
/*     */       }
/*     */     }
/*     */ 
/*  77 */     Iterator it = datas.entrySet().iterator();
/*     */ 
/*  79 */     while (it.hasNext()) {
/*  80 */       Map.Entry obj = (Map.Entry)it.next();
/*  81 */       FormTable formTable = (FormTable)obj.getKey();
/*  82 */       String data = (String)obj.getValue();
/*  83 */       if (StringUtils.isNotEmpty(data)) {
/*  84 */         Gson gson = new Gson();
/*  85 */         FormField[] formFields = (FormField[])gson.fromJson(data, com.htsoft.oa.model.flow.FormField[].class);
/*  86 */         Set fieldss = new HashSet();
/*  87 */         for (FormField field : formFields) {
/*  88 */           if (field.getFieldId() == null) {
/*  89 */             fieldss.add(field);
/*     */           } else {
/*  91 */             FormField orgForm = (FormField)this.formFieldService.get(field.getFieldId());
/*  92 */             Set rights = orgForm.getFieldRightss();
/*     */             try {
/*  94 */               BeanUtil.copyNotNullProperties(orgForm, field);
/*  95 */               orgForm.setFieldRightss(rights);
/*  96 */               fieldss.add(orgForm);
/*     */             }
/*     */             catch (Exception e) {
/*  99 */               e.printStackTrace();
/*     */             }
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/* 105 */         if (formTable.getTableId() == null) {
/* 106 */           formTable.setFormDef(formDef);
/* 107 */           formTable.setFormFields(fieldss);
/* 108 */           formTable = (FormTable)this.formTableService.save(formTable);
/*     */         } else {
/* 110 */           FormTable orgFormTable = (FormTable)this.formTableService.get(formTable.getTableId());
/* 111 */           Set fields = orgFormTable.getFormFields();
/*     */           try {
/* 113 */             BeanUtil.copyNotNullProperties(orgFormTable, formTable);
/*     */ 
/* 115 */             fields.retainAll(fieldss);
/* 116 */             fieldss.removeAll(fields);
/* 117 */             fields.addAll(fieldss);
/* 118 */             orgFormTable.setFormFields(fields);
/* 119 */             formTable = (FormTable)this.formTableService.save(orgFormTable);
/*     */           } catch (Exception ex) {
/* 121 */             this.logger.error(ex.getMessage());
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 127 */     return formDef;
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.flow.impl.FormDefServiceImpl
 * JD-Core Version:    0.6.0
 */