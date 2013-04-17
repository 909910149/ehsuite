/*    */ package com.htsoft.oa.dao.flow.impl;
/*    */ 
/*    */ import com.htsoft.core.dao.impl.BaseDaoImpl;
/*    */ import com.htsoft.oa.dao.flow.FieldRightsDao;
/*    */ import com.htsoft.oa.model.flow.FieldRights;
/*    */ import java.util.List;
/*    */ 
/*    */ public class FieldRightsDaoImpl extends BaseDaoImpl<FieldRights>
/*    */   implements FieldRightsDao
/*    */ {
/*    */   public FieldRightsDaoImpl()
/*    */   {
/* 16 */     super(FieldRights.class);
/*    */   }
/*    */ 
/*    */   public List<FieldRights> getByMappingFieldTaskName(Long mappingId, Long fieldId, String taskName)
/*    */   {
/* 22 */     String hql = "from FieldRights vo where vo.formField.fieldId=? and vo.mappingId=? and vo.taskName=?";
/* 23 */     return findByHql(hql, new Object[] { fieldId, mappingId, taskName });
/*    */   }
/*    */ 
/*    */   public List<FieldRights> getByMappingIdAndTaskName(Long mappingId, String taskName)
/*    */   {
/* 29 */     String hql = "from FieldRights vo where vo.mappingId=? and vo.taskName=?";
/* 30 */     return findByHql(hql, new Object[] { mappingId, taskName });
/*    */   }
/*    */ 
/*    */   public List<FieldRights> getByMappingId(Long mappingId)
/*    */   {
/* 35 */     String hql = "from FieldRights vo where vo.mappingId=?";
/* 36 */     return findByHql(hql, new Object[] { mappingId });
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.flow.impl.FieldRightsDaoImpl
 * JD-Core Version:    0.6.0
 */