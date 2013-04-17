/*    */ package com.htsoft.oa.dao.system.impl;
/*    */ 
/*    */ import com.htsoft.core.dao.impl.BaseDaoImpl;
/*    */ import com.htsoft.oa.dao.system.RegionDao;
/*    */ import com.htsoft.oa.model.system.Region;
/*    */ import java.util.List;
/*    */ 
/*    */ public class RegionDaoImpl extends BaseDaoImpl<Region>
/*    */   implements RegionDao
/*    */ {
/*    */   public RegionDaoImpl()
/*    */   {
/* 15 */     super(Region.class);
/*    */   }
/*    */ 
/*    */   public List<Region> getProvince()
/*    */   {
/* 23 */     Long parentId = Long.valueOf(0L);
/* 24 */     String hql = "from Region r where r.parentId = ?";
/* 25 */     return findByHql(hql, new Object[] { parentId });
/*    */   }
/*    */ 
/*    */   public List<Region> getCity(Long regionId)
/*    */   {
/* 33 */     String hql = "from Region r where r.parentId = ?";
/* 34 */     return findByHql(hql, new Object[] { regionId });
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.system.impl.RegionDaoImpl
 * JD-Core Version:    0.6.0
 */