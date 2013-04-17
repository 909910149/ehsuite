/*     */ package com.htsoft.oa.service.flow.impl;
/*     */ 
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.reflect.TypeToken;
/*     */ import com.htsoft.core.model.DynaModel;
/*     */ import com.htsoft.core.service.DynamicService;
/*     */ import com.htsoft.core.util.BeanUtil;
/*     */ import com.htsoft.oa.action.flow.FlowRunInfo;
/*     */ import com.htsoft.oa.model.flow.FormDef;
/*     */ import com.htsoft.oa.model.flow.FormField;
/*     */ import com.htsoft.oa.model.flow.FormTable;
/*     */ import com.htsoft.oa.service.flow.FlowFormService;
/*     */ import com.htsoft.oa.service.flow.FormDefService;
/*     */ import com.htsoft.oa.service.flow.FormFieldService;
/*     */ import com.htsoft.oa.service.flow.FormTableService;
/*     */ import java.io.Serializable;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.annotation.Resource;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.beanutils.ConvertUtilsBean;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ public class FlowFormServiceImpl
/*     */   implements FlowFormService
/*     */ {
/*  47 */   private Log logger = LogFactory.getLog(FlowFormServiceImpl.class);
/*     */ 
/*     */   @Resource
/*     */   private FormDefService formDefService;
/*     */ 
/*     */   @Resource
/*     */   private FormFieldService formFieldService;
/*     */ 
/*     */   @Resource
/*     */   private FormTableService formTableService;
/*     */ 
/*  62 */   public DynaModel doSaveData(FlowRunInfo flowRunInfo) { FormDef formDef = (FormDef)this.formDefService.get(new Long(flowRunInfo.getFormDefId()));
/*     */ 
/*  64 */     if (formDef != null)
/*     */     {
/*  66 */       FormTable mainTable = formDef.getMainTable();
/*     */ 
/*  75 */       List<FormTable> subTables = formDef.getSubTables();
/*     */ 
/*  77 */       if (mainTable != null)
/*     */         try
/*     */         {
/*  80 */           DynaModel mainDynaModel = new DynaModel(mainTable);
/*     */ 
/*  82 */           DynamicService mainDynamicService = BeanUtil.getDynamicServiceBean(mainTable.getEntityName());
/*     */ 
/*  85 */           Map mainData = BeanUtil.populateEntity(flowRunInfo.getRequest(), mainDynaModel);
/*  86 */           String pkFieldName = mainDynaModel.getPrimaryFieldName();
/*  87 */           Serializable entityPKValue = (Serializable)mainData.get(pkFieldName);
/*     */ 
/*  89 */           if (entityPKValue != null) {
/*  90 */             Map orgEntity = (Map)mainDynamicService.get(entityPKValue);
/*     */ 
/*  92 */             orgEntity.putAll(mainData);
/*     */ 
/*  94 */             mainDynamicService.save(orgEntity);
/*     */ 
/*  96 */             mainDynaModel.getDatas().putAll(orgEntity);
/*     */           }
/*     */           else
/*     */           {
/* 100 */             mainDynamicService.save(mainData);
/*     */ 
/* 102 */             entityPKValue = (Serializable)mainData.get(pkFieldName);
/*     */ 
/* 104 */             mainDynaModel.set("entityName", mainTable.getEntityName());
/*     */ 
/* 106 */             mainDynaModel.setDatas(mainData);
/*     */ 
/* 108 */             flowRunInfo.setEntityPK(entityPKValue);
/*     */           }
/* 110 */           if ((subTables != null) && (subTables.size() > 0)) {
/* 111 */             for (FormTable subTable : subTables) {
/* 112 */               String details = flowRunInfo.getRequest().getParameter(subTable.getTableKey() + "details");
/* 113 */               if (StringUtils.isNotEmpty(details)) {
/* 114 */                 DynaModel subDynaModel = new DynaModel(subTable);
/* 115 */                 DynamicService subDynamicService = BeanUtil.getDynamicServiceBean(subTable.getEntityName());
/* 116 */                 String subPk = subDynaModel.getPrimaryFieldName();
/* 117 */                 Gson gson = new Gson();
/* 118 */                 String[] detailArr = (String[])gson.fromJson(details, java.lang.String[].class);
/* 119 */                 for (String rowArr : detailArr) {
/* 120 */                   HashMap rowMap = (HashMap)gson.fromJson(rowArr, new TypeToken() {  }
/* 120 */                   .getType());
/*     */ 
/* 122 */                   Map datas = BeanUtil.populateEntity(rowMap, subDynaModel);
/*     */ 
/* 124 */                   Object subPkVal = datas.get(subPk);
/* 125 */                   if (subPkVal != null) {
/* 126 */                     Map orgEntity = (Map)subDynamicService.get((Serializable)subPkVal);
/* 127 */                     orgEntity.putAll(datas);
/* 128 */                     subDynamicService.save(orgEntity);
/*     */                   } else {
/* 130 */                     datas.put(mainTable.getEntityName(), mainDynaModel.getDatas());
/* 131 */                     subDynamicService.save(datas);
/*     */                   }
/*     */                 }
/*     */               }
/*     */               else {
/* 136 */                 DynaModel subDynaModel = new DynaModel(subTable);
/* 137 */                 DynamicService subDynamicService = BeanUtil.getDynamicServiceBean(subTable.getEntityName());
/*     */ 
/* 139 */                 Map subData = BeanUtil.populateEntity(flowRunInfo.getRequest(), subDynaModel);
/* 140 */                 String subpkFieldName = subDynaModel.getPrimaryFieldName();
/*     */ 
/* 142 */                 String strsubEntityPKValue = flowRunInfo.getRequest().getParameter(subTable.getTableKey() + "_" + subpkFieldName);
/* 143 */                 Object subEntityPKValue = null;
/* 144 */                 if (StringUtils.isNotEmpty(strsubEntityPKValue)) {
/* 145 */                   subEntityPKValue = BeanUtil.convertValue(new ConvertUtilsBean(), strsubEntityPKValue, subDynaModel.getType(subpkFieldName));
/*     */                 }
/* 147 */                 if (subEntityPKValue != null) {
/* 148 */                   Map orgSubEntity = (Map)subDynamicService.get((Serializable)subEntityPKValue);
/*     */ 
/* 150 */                   orgSubEntity.putAll(subData);
/*     */ 
/* 152 */                   subDynamicService.save(orgSubEntity);
/*     */ 
/* 154 */                   subDynaModel.getDatas().putAll(orgSubEntity);
/*     */                 }
/*     */                 else {
/* 157 */                   subData.put(mainTable.getEntityName(), mainDynaModel.getDatas());
/*     */ 
/* 159 */                   subDynamicService.save(subData);
/*     */                 }
/*     */ 
/*     */               }
/*     */ 
/*     */             }
/*     */ 
/*     */           }
/*     */ 
/* 197 */           FormField titleField = this.formFieldService.find(mainTable.getTableId(), FormField.FLOW_TITLE);
/* 198 */           if (titleField != null) {
/*     */             try
/*     */             {
/* 201 */               String flowSubject = (String)mainDynaModel.get(titleField.getFieldName());
/* 202 */               flowRunInfo.setFlowSubject(flowSubject);
/*     */             } catch (Exception ex) {
/* 204 */               this.logger.error(ex.getMessage());
/*     */             }
/*     */           }
/*     */ 
/* 208 */           flowRunInfo.setEntityName(mainTable.getEntityName());
/*     */ 
/* 211 */           flowRunInfo.getVariables().putAll(mainDynaModel.getDatas());
/*     */ 
/* 213 */           return mainDynaModel;
/*     */         }
/*     */         catch (Exception ex) {
/* 216 */           ex.printStackTrace();
/* 217 */           this.logger.error("error:" + ex.getMessage());
/*     */         }
/*     */       else
/* 220 */         this.logger.debug("main table is not exist");
/*     */     }
/*     */     else {
/* 223 */       this.logger.debug("formdef is not exist " + flowRunInfo.getFormDefId());
/*     */     }
/* 225 */     return null;
/*     */   }
/*     */ 
/*     */   public boolean deleteItems(String strIds, Long tableId)
/*     */   {
/* 230 */     FormTable formTable = (FormTable)this.formTableService.get(tableId);
/* 231 */     DynaModel subDynaModel = new DynaModel(formTable);
/* 232 */     DynamicService subDynamicService = BeanUtil.getDynamicServiceBean(subDynaModel.getEntityName());
/* 233 */     String[] ids = strIds.split(",");
/* 234 */     String subpkFieldName = subDynaModel.getPrimaryFieldName();
/*     */     try {
/* 236 */       for (String id : ids) {
/* 237 */         Object subEntityPKValue = null;
/* 238 */         if (StringUtils.isNotEmpty(id)) {
/* 239 */           subEntityPKValue = BeanUtil.convertValue(new ConvertUtilsBean(), id, subDynaModel.getType(subpkFieldName));
/* 240 */           if (subEntityPKValue != null)
/* 241 */             subDynamicService.remove((Serializable)subEntityPKValue);
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 246 */       return false;
/*     */     }
/* 248 */     return true;
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.flow.impl.FlowFormServiceImpl
 * JD-Core Version:    0.6.0
 */