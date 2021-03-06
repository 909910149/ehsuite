/*    */ package com.htsoft.oa.dao.flow.impl;
/*    */ 
/*    */ import com.htsoft.core.dao.impl.BaseDaoImpl;
/*    */ import com.htsoft.oa.dao.flow.FormDefDao;
/*    */ import com.htsoft.oa.model.flow.FormDef;
/*    */ import java.util.List;
/*    */ 
/*    */ public class FormDefDaoImpl extends BaseDaoImpl<FormDef>
/*    */   implements FormDefDao
/*    */ {
/*    */   public FormDefDaoImpl()
/*    */   {
/* 15 */     super(FormDef.class);
/*    */   }
/*    */ 
/*    */   public List<FormDef> getByDeployId(String deployId)
/*    */   {
/* 20 */     String hql = "from FormDef fd where deployId=?";
/* 21 */     return findByHql(hql, new Object[] { deployId });
/*    */   }
/*    */ 
/*    */   public FormDef getByDeployIdActivityName(String deployId, String activityName)
/*    */   {
/* 31 */     String hql = "from FormDef fd where fd.deployId=? and fd.activityName=?";
/* 32 */     return (FormDef)findUnique(hql, new Object[] { deployId, activityName });
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.flow.impl.FormDefDaoImpl
 * JD-Core Version:    0.6.0
 */