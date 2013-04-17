/*    */ package com.htsoft.oa.dao.flow.impl;
/*    */ 
/*    */ import com.htsoft.core.dao.impl.BaseDaoImpl;
/*    */ import com.htsoft.oa.dao.flow.ProDefRightsDao;
/*    */ import com.htsoft.oa.model.flow.ProDefRights;
/*    */ import java.util.List;
/*    */ 
/*    */ public class ProDefRightsDaoImpl extends BaseDaoImpl<ProDefRights>
/*    */   implements ProDefRightsDao
/*    */ {
/*    */   public ProDefRightsDaoImpl()
/*    */   {
/* 16 */     super(ProDefRights.class);
/*    */   }
/*    */ 
/*    */   public ProDefRights findByDefId(Long defId)
/*    */   {
/* 21 */     String hql = "from ProDefRights pd where pd.proDefinition.defId = ?";
/* 22 */     List list = findByHql(hql, new Object[] { defId });
/* 23 */     if (list.size() > 0) {
/* 24 */       return (ProDefRights)list.get(0);
/*    */     }
/* 26 */     return new ProDefRights();
/*    */   }
/*    */ 
/*    */   public ProDefRights findByTypeId(Long proTypeId)
/*    */   {
/* 32 */     String hql = "from ProDefRights pd where pd.globalType.proTypeId = ?";
/* 33 */     List list = findByHql(hql, new Object[] { proTypeId });
/* 34 */     if (list.size() > 0) {
/* 35 */       return (ProDefRights)list.get(0);
/*    */     }
/* 37 */     return new ProDefRights();
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.flow.impl.ProDefRightsDaoImpl
 * JD-Core Version:    0.6.0
 */