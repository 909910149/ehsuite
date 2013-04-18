/*    */ package com.htsoft.oa.service.flow.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.flow.FormTemplateDao;
/*    */ import com.htsoft.oa.model.flow.FormDefMapping;
/*    */ import com.htsoft.oa.model.flow.FormTemplate;
/*    */ import com.htsoft.oa.service.flow.FormTemplateService;
/*    */ import java.util.List;
/*    */ 
/*    */ public class FormTemplateServiceImpl extends BaseServiceImpl<FormTemplate>
/*    */   implements FormTemplateService
/*    */ {
/*    */   private FormTemplateDao dao;
/*    */ 
/*    */   public FormTemplateServiceImpl(FormTemplateDao dao)
/*    */   {
/* 19 */     super(dao);
/* 20 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   public List<FormTemplate> getByMappingId(Long mappingId)
/*    */   {
/* 29 */     return this.dao.getByMappingId(mappingId);
/*    */   }
/*    */ 
/*    */   public void batchAddDefault(List<String> nodeNames, FormDefMapping fdm)
/*    */   {
/* 34 */     for (String nodeName : nodeNames) {
/* 35 */       FormTemplate formTemplate = new FormTemplate();
/* 36 */       formTemplate.setFormDefMapping(fdm);
/* 37 */       formTemplate.setNodeName(nodeName);
/* 38 */       save(formTemplate);
/*    */     }
/*    */   }
/*    */ 
/*    */   public FormTemplate getByMappingIdNodeName(Long mappingId, String nodeName)
/*    */   {
/* 50 */     return this.dao.getByMappingIdNodeName(mappingId, nodeName);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.flow.impl.FormTemplateServiceImpl
 * JD-Core Version:    0.6.0
 */