/*     */ package com.htsoft.oa.dao.flow.impl;
/*     */ 
/*     */ import com.htsoft.core.dao.impl.BaseDaoImpl;
/*     */ import com.htsoft.core.web.paging.PagingBean;
/*     */ import com.htsoft.oa.dao.flow.FormTableDao;
/*     */ import com.htsoft.oa.model.flow.FormTable;
/*     */ import com.htsoft.oa.model.system.AppRole;
/*     */ import com.htsoft.oa.model.system.AppUser;
/*     */ import com.htsoft.oa.model.system.Department;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import org.hibernate.Hibernate;
/*     */ import org.hibernate.HibernateException;
/*     */ import org.hibernate.Query;
/*     */ import org.hibernate.Session;
/*     */ import org.springframework.orm.hibernate3.HibernateCallback;
/*     */ import org.springframework.orm.hibernate3.HibernateTemplate;
/*     */ 
/*     */ public class FormTableDaoImpl extends BaseDaoImpl<FormTable>
/*     */   implements FormTableDao
/*     */ {
/*     */   public FormTableDaoImpl()
/*     */   {
/*  36 */     super(FormTable.class);
/*     */   }
/*     */ 
/*     */   public List<FormTable> getListFromPro(String typeId, String tableName, AppUser curUser, PagingBean pb)
/*     */   {
/*  41 */     List params = new ArrayList();
/*  42 */     String hql = " select DISTINCT formTable from  FormTable formTable , FormDef formDef , FormDefMapping formDefMapping, ProDefinition proDefinition  where 1=1 and formTable.formDef=formDef  and formDef=formDefMapping.formDef  and formDefMapping.proDefinition.defId=proDefinition.defId  and formTable.tableName like ?";
/*     */ 
/*  54 */     tableName = "%" + tableName + "%";
/*  55 */     params.add(tableName);
/*     */ 
/*  57 */     Long tId = new Long(0L);
/*  58 */     if (typeId != null) {
/*  59 */       tId = Long.valueOf(Long.parseLong(typeId));
/*     */     }
/*  61 */     if (tId.longValue() != 0L) {
/*  62 */       hql = hql + " and  proDefinition.proType.proTypeId =?";
/*  63 */       params.add(tId);
/*     */     }
/*  65 */     if (curUser.isSupperManage()) {
/*  66 */       return findByHql(hql, params.toArray(), pb);
/*     */     }
/*     */ 
/*  70 */     String uIds = "'%," + curUser.getUserId() + ",%'";
/*  71 */     String dIds = "'%," + curUser.getDepartment().getDepId() + ",%'";
/*     */ 
/*  73 */     StringBuffer pHsql = new StringBuffer("select pd.defId from ProDefRights pr right join pr.proDefinition pd  where 1=1 ");
/*  74 */     pHsql.append("and (pr.userIds like " + uIds + "  or pr.depIds like " + dIds + " ");
/*     */ 
/*  77 */     Set roles = curUser.getRoles();
/*  78 */     for (Iterator it = roles.iterator(); it.hasNext(); ) {
/*  79 */       AppRole role = (AppRole)it.next();
/*  80 */       String rIds = "'%," + role.getRoleId() + ",%'";
/*  81 */       pHsql.append("or pr.roleIds like " + rIds + " ");
/*     */     }
/*     */ 
/*  85 */     pHsql.append(")");
/*  86 */     List<Long> result = getHibernateTemplate().find(pHsql.toString());
/*     */ 
/*  90 */     if ((result != null) && (result.size() > 0)) {
/*  91 */       String defIds = "";
/*  92 */       for (Long i : result) {
/*  93 */         defIds = defIds + i + ",";
/*     */       }
/*  95 */       defIds = defIds.substring(0, defIds.length() - 1);
/*     */ 
/*  97 */       hql = hql + " and proDefinition.defId in (" + defIds + ")";
/*     */     }
/*     */ 
/* 100 */     return findByHql(hql, params.toArray(), pb);
/*     */   }
/*     */ 
/*     */   public List<FormTable> getAllAndFields()
/*     */   {
/* 110 */     return (List)getHibernateTemplate().execute(new HibernateCallback()
/*     */     {
/*     */       public Object doInHibernate(Session session)
/*     */         throws HibernateException, SQLException
/*     */       {
/* 115 */         String hql = "from FormTable ft ";
/* 116 */         Query query = session.createQuery(hql);
/* 117 */         List fts = query.list();
/* 118 */         for (int i = 0; i < fts.size(); i++) {
/* 119 */           FormTable ft = (FormTable)fts.get(i);
/* 120 */           Hibernate.initialize(ft.getFormFields());
/*     */         }
/* 122 */         return fts;
/*     */       } } );
/*     */   }
/*     */ 
/*     */   public List<FormTable> findByTableKey(String tableKey) {
/* 128 */     String hql = "from FormTable ft where ft.tableKey=?";
/* 129 */     return findByHql(hql, new Object[] { tableKey });
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.flow.impl.FormTableDaoImpl
 * JD-Core Version:    0.6.0
 */