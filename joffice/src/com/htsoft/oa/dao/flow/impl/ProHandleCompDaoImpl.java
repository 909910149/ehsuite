/*    */ package com.htsoft.oa.dao.flow.impl;
/*    */ 
/*    */ import com.htsoft.core.dao.impl.BaseDaoImpl;
/*    */ import com.htsoft.oa.dao.flow.ProHandleCompDao;
/*    */ import com.htsoft.oa.model.flow.ProHandleComp;
/*    */ import java.util.List;
/*    */ 
/*    */ public class ProHandleCompDaoImpl extends BaseDaoImpl<ProHandleComp>
/*    */   implements ProHandleCompDao
/*    */ {
/*    */   public ProHandleCompDaoImpl()
/*    */   {
/* 16 */     super(ProHandleComp.class);
/*    */   }
/*    */ 
/*    */   public List<ProHandleComp> getByDeployIdActivityName(String deployId, String activityName)
/*    */   {
/* 23 */     String hql = "from ProHandleComp phc where phc.deployId=? and phc.activityName=?";
/* 24 */     return findByHql(hql, new Object[] { deployId, activityName });
/*    */   }
/*    */ 
/*    */   public List<ProHandleComp> getByDeployIdActivityNameHandleType(String deployId, String activityName, Short handleType)
/*    */   {
/* 31 */     String hql = "from ProHandleComp phc where phc.deployId=? and phc.activityName=? and phc.handleType=?";
/* 32 */     return findByHql(hql, new Object[] { deployId, activityName, handleType });
/*    */   }
/*    */ 
/*    */   public ProHandleComp getProHandleComp(String deployId, String activityName, String eventName) {
/* 36 */     String hql = "from ProHandleComp phc where phc.deployId=? and phc.activityName=? and eventName=? ";
/* 37 */     return (ProHandleComp)findUnique(hql, new Object[] { deployId, activityName, eventName });
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.flow.impl.ProHandleCompDaoImpl
 * JD-Core Version:    0.6.0
 */