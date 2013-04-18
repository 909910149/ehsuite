/*    */ package com.htsoft.oa.dao.flow.impl;
/*    */ 
/*    */ import com.htsoft.core.dao.impl.BaseDaoImpl;
/*    */ import com.htsoft.oa.dao.flow.FormTemplateDao;
/*    */ import com.htsoft.oa.model.flow.FormTemplate;
/*    */ import java.util.List;
/*    */ 
/*    */ public class FormTemplateDaoImpl extends BaseDaoImpl<FormTemplate>
/*    */   implements FormTemplateDao
/*    */ {
/*    */   public FormTemplateDaoImpl()
/*    */   {
/* 16 */     super(FormTemplate.class);
/*    */   }
/*    */ 
/*    */   public List<FormTemplate> getByMappingId(Long mappingId)
/*    */   {
/* 24 */     String hql = "from FormTemplate ft where ft.formDefMapping.mappingId=?";
/* 25 */     return findByHql(hql, new Object[] { mappingId });
/*    */   }
/*    */ 
/*    */   public FormTemplate getByMappingIdNodeName(Long mappingId, String nodeName)
/*    */   {
/* 34 */     String hql = "from FormTemplate ft where ft.formDefMapping.mappingId=? and ft.nodeName=?";
/* 35 */     return (FormTemplate)findUnique(hql, new Object[] { mappingId, nodeName });
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.flow.impl.FormTemplateDaoImpl
 * JD-Core Version:    0.6.0
 */