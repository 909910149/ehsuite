/*    */ package com.htsoft.oa.dao.flow.impl;
/*    */ 
/*    */ import com.htsoft.core.dao.impl.BaseDaoImpl;
/*    */ import com.htsoft.oa.dao.flow.FormDefMappingDao;
/*    */ import com.htsoft.oa.model.flow.FormDefMapping;
/*    */ import java.util.List;
/*    */ 
/*    */ public class FormDefMappingDaoImpl extends BaseDaoImpl<FormDefMapping>
/*    */   implements FormDefMappingDao
/*    */ {
/*    */   public FormDefMappingDaoImpl()
/*    */   {
/* 16 */     super(FormDefMapping.class);
/*    */   }
/*    */ 
/*    */   public FormDefMapping getByDeployId(String deployId)
/*    */   {
/* 23 */     String hql = "from FormDefMapping fdm where fdm.deployId=?";
/* 24 */     return (FormDefMapping)findUnique(hql, new Object[] { deployId });
/*    */   }
/*    */ 
/*    */   public FormDefMapping findByDefId(Long defId)
/*    */   {
/* 32 */     String hql = "select m from FormDefMapping m where m.proDefinition.defId = ? ";
/* 33 */     Object[] paramList = { defId };
/* 34 */     List list = findByHql(hql, paramList);
/* 35 */     return (list != null) && (list.size() > 0) ? (FormDefMapping)list.get(0) : null;
/*    */   }
/*    */ 
/*    */   public List<FormDefMapping> getByFormDef(Long formDefId) {
/* 39 */     String hql = "from FormDefMapping vo where vo.formDef.formDefId=?";
/* 40 */     return findByHql(hql, new Object[] { formDefId });
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.flow.impl.FormDefMappingDaoImpl
 * JD-Core Version:    0.6.0
 */