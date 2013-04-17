/*     */ package com.htsoft.oa.dao.system.impl;
/*     */ 
/*     */ import com.htsoft.core.dao.impl.BaseDaoImpl;
/*     */ import com.htsoft.oa.dao.system.GlobalTypeDao;
/*     */ import com.htsoft.oa.model.system.AppRole;
/*     */ import com.htsoft.oa.model.system.AppUser;
/*     */ import com.htsoft.oa.model.system.Department;
/*     */ import com.htsoft.oa.model.system.GlobalType;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class GlobalTypeDaoImpl extends BaseDaoImpl<GlobalType>
/*     */   implements GlobalTypeDao
/*     */ {
/*     */   public GlobalTypeDaoImpl()
/*     */   {
/*  20 */     super(GlobalType.class);
/*     */   }
/*     */ 
/*     */   public List<GlobalType> getByParentIdCatKey(Long parentId, String catKey)
/*     */   {
/*  27 */     String hql = " from GlobalType gt where gt.parentId = ? and gt.catKey = ? order by gt.sn asc";
/*  28 */     return findByHql(hql, new Object[] { parentId, catKey });
/*     */   }
/*     */ 
/*     */   public Integer getCountsByParentId(Long parentId) {
/*  32 */     ArrayList param = new ArrayList();
/*  33 */     String hql = " select count(proTypeId) from GlobalType gt ";
/*  34 */     if ((parentId != null) && (parentId.longValue() != 0L)) {
/*  35 */       hql = hql + " where gt.parentId=?";
/*  36 */       param.add(parentId);
/*     */     } else {
/*  38 */       hql = hql + " where gt.parentId is null";
/*     */     }
/*     */ 
/*  41 */     Object obj = findUnique(hql, param.toArray());
/*  42 */     return new Integer(obj.toString());
/*     */   }
/*     */ 
/*     */   public List<GlobalType> getByParentId(Long parentId)
/*     */   {
/*  47 */     ArrayList param = new ArrayList();
/*  48 */     String hql = " from GlobalType gt ";
/*  49 */     if ((parentId != null) && (parentId.longValue() != 0L)) {
/*  50 */       hql = hql + " where gt.parentId=?";
/*  51 */       param.add(parentId);
/*     */     } else {
/*  53 */       hql = hql + " where gt.parentId is null";
/*     */     }
/*     */ 
/*  56 */     return findByHql(hql, param.toArray());
/*     */   }
/*     */ 
/*     */   public List<GlobalType> getByPath(String path)
/*     */   {
/*  65 */     String hql = " from GlobalType gt where gt.path like ?";
/*  66 */     return findByHql(hql, new Object[] { path + "%" });
/*     */   }
/*     */ 
/*     */   public GlobalType findByTypeName(String typeName) {
/*  70 */     String hql = " from GlobalType gt where gt.typeName = ?";
/*  71 */     List list = findByHql(hql, new Object[] { typeName });
/*  72 */     if (list.size() > 0) {
/*  73 */       return (GlobalType)list.get(0);
/*     */     }
/*  75 */     return null;
/*     */   }
/*     */ 
/*     */   public List<GlobalType> getByParentIdCatKeyUserId(Long parentId, String catKey, Long userId)
/*     */   {
/*  82 */     String hql = " from GlobalType gt where gt.parentId = ? and gt.catKey = ? and gt.userId=?";
/*  83 */     return findByHql(hql, new Object[] { parentId, catKey, userId });
/*     */   }
/*     */ 
/*     */   public List<GlobalType> getByRightsCatKey(AppUser curUser, String catKey) {
/*  87 */     String uIds = "%," + curUser.getUserId() + ",%";
/*  88 */     String dIds = "%," + curUser.getDepartment().getDepId() + ",%";
/*  89 */     List params = new ArrayList();
/*  90 */     StringBuffer hql = new StringBuffer("select gt from ProDefRights pr right join pr.globalType gt  where gt.catKey = ? and (pr.userIds like ?  or pr.depIds like ? ");
/*  91 */     params.add(catKey);
/*  92 */     params.add(uIds);
/*  93 */     params.add(dIds);
/*     */ 
/*  95 */     Set roles = curUser.getRoles();
/*  96 */     for (Iterator it = roles.iterator(); it.hasNext(); ) {
/*  97 */       AppRole role = (AppRole)it.next();
/*  98 */       hql.append("or pr.roleIds like ? ");
/*  99 */       String rIds = "%," + role.getRoleId() + ",%";
/* 100 */       params.add(rIds);
/*     */     }
/*     */ 
/* 103 */     hql.append(")");
/*     */ 
/* 105 */     return findByHql(hql.toString(), params.toArray());
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.system.impl.GlobalTypeDaoImpl
 * JD-Core Version:    0.6.0
 */