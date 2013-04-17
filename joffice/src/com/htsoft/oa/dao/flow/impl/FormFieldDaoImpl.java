/*    */ package com.htsoft.oa.dao.flow.impl;
/*    */ 
/*    */ import com.htsoft.core.dao.impl.BaseDaoImpl;
/*    */ import com.htsoft.oa.dao.flow.FormFieldDao;
/*    */ import com.htsoft.oa.model.flow.FormField;
/*    */ import java.util.List;
/*    */ 
/*    */ public class FormFieldDaoImpl extends BaseDaoImpl<FormField>
/*    */   implements FormFieldDao
/*    */ {
/*    */   public FormFieldDaoImpl()
/*    */   {
/* 16 */     super(FormField.class);
/*    */   }
/*    */ 
/*    */   public FormField find(Long tableId, Short isFlowTitle)
/*    */   {
/* 25 */     String hql = "from FormField ff where ff.formTable.tableId=? and ff.isFlowTitle=? ";
/* 26 */     return (FormField)findUnique(hql, new Object[] { 
/* 27 */       tableId, isFlowTitle });
/*    */   }
/*    */ 
/*    */   public List<FormField> getByForeignTableAndKey(String foreignTable, String foreignKey)
/*    */   {
/* 35 */     String hql = "select formField from FormField formField where formField.foreignTable=? and formField.foreignKey=?";
/* 36 */     Object[] objs = { foreignTable, foreignKey };
/* 37 */     List setList = findByHql(hql, objs);
/* 38 */     return setList;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.flow.impl.FormFieldDaoImpl
 * JD-Core Version:    0.6.0
 */