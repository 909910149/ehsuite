/*    */ package com.htsoft.oa.service.flow.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.flow.FormFieldDao;
/*    */ import com.htsoft.oa.model.flow.FormField;
/*    */ import com.htsoft.oa.service.flow.FormFieldService;
/*    */ 
/*    */ public class FormFieldServiceImpl extends BaseServiceImpl<FormField>
/*    */   implements FormFieldService
/*    */ {
/*    */   private FormFieldDao dao;
/*    */ 
/*    */   public FormFieldServiceImpl(FormFieldDao dao)
/*    */   {
/* 16 */     super(dao);
/* 17 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   public FormField find(Long tableId, Short isFlowTitle)
/*    */   {
/* 27 */     return this.dao.find(tableId, isFlowTitle);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.flow.impl.FormFieldServiceImpl
 * JD-Core Version:    0.6.0
 */