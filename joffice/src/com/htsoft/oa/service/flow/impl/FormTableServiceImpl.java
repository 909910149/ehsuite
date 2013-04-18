/*    */ package com.htsoft.oa.service.flow.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.core.web.paging.PagingBean;
/*    */ import com.htsoft.oa.dao.flow.FormTableDao;
/*    */ import com.htsoft.oa.model.flow.FormTable;
/*    */ import com.htsoft.oa.model.system.AppUser;
/*    */ import com.htsoft.oa.service.flow.FormTableService;
/*    */ import java.util.List;
/*    */ 
/*    */ public class FormTableServiceImpl extends BaseServiceImpl<FormTable>
/*    */   implements FormTableService
/*    */ {
/*    */   private FormTableDao dao;
/*    */ 
/*    */   public FormTableServiceImpl(FormTableDao dao)
/*    */   {
/* 20 */     super(dao);
/* 21 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   public List<FormTable> getListFromPro(String typeId, String tableName, AppUser curUser, PagingBean pb) {
/* 25 */     return this.dao.getListFromPro(typeId, tableName, curUser, pb);
/*    */   }
/*    */ 
/*    */   public List<FormTable> getAllAndFields()
/*    */   {
/* 33 */     return this.dao.getAllAndFields();
/*    */   }
/*    */ 
/*    */   public List<FormTable> findByTableKey(String tableKey) {
/* 37 */     return this.dao.findByTableKey(tableKey);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.flow.impl.FormTableServiceImpl
 * JD-Core Version:    0.6.0
 */