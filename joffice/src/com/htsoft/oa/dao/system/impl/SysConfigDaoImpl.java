/*    */ package com.htsoft.oa.dao.system.impl;
/*    */ 
/*    */ import com.htsoft.core.dao.impl.BaseDaoImpl;
/*    */ import com.htsoft.oa.dao.system.SysConfigDao;
/*    */ import com.htsoft.oa.model.system.SysConfig;
/*    */ import java.util.List;
/*    */ import org.hibernate.Query;
/*    */ import org.hibernate.Session;
/*    */ 
/*    */ public class SysConfigDaoImpl extends BaseDaoImpl<SysConfig>
/*    */   implements SysConfigDao
/*    */ {
/*    */   public SysConfigDaoImpl()
/*    */   {
/* 17 */     super(SysConfig.class);
/*    */   }
/*    */ 
/*    */   public SysConfig findByKey(String key)
/*    */   {
/* 22 */     String hql = "from SysConfig vo where vo.configKey=?";
/* 23 */     Object[] objs = { key };
/* 24 */     List list = findByHql(hql, objs);
/* 25 */     if (list.size() > 0)
/* 26 */       return (SysConfig)list.get(0);
/* 27 */     return null;
/*    */   }
/*    */ 
/*    */   public List<SysConfig> findConfigByTypeKey(String typeKey)
/*    */   {
/* 32 */     String hql = "from SysConfig vo where vo.typeKey=?";
/* 33 */     Object[] objs = { typeKey };
/* 34 */     return findByHql(hql, objs);
/*    */   }
/*    */ 
/*    */   public List findTypeKeys()
/*    */   {
/* 39 */     String sql = "select vo.typeKey from SysConfig vo group by vo.typeKey";
/* 40 */     Query query = getSession().createQuery(sql);
/* 41 */     return query.list();
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.system.impl.SysConfigDaoImpl
 * JD-Core Version:    0.6.0
 */