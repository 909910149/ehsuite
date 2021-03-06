/*    */ package com.htsoft.oa.dao.system.impl;
/*    */ 
/*    */ import com.htsoft.core.dao.impl.BaseDaoImpl;
/*    */ import com.htsoft.oa.dao.system.AppRoleDao;
/*    */ import com.htsoft.oa.model.system.AppFunction;
/*    */ import com.htsoft.oa.model.system.AppRole;
/*    */ import com.htsoft.oa.model.system.FunUrl;
/*    */ import java.sql.SQLException;
/*    */ import java.util.HashMap;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ import java.util.TreeSet;
/*    */ import org.hibernate.HibernateException;
/*    */ import org.hibernate.Query;
/*    */ import org.hibernate.Session;
/*    */ import org.springframework.orm.hibernate3.HibernateCallback;
/*    */ import org.springframework.orm.hibernate3.HibernateTemplate;
/*    */ 
/*    */ public class AppRoleDaoImpl extends BaseDaoImpl<AppRole>
/*    */   implements AppRoleDao
/*    */ {
/*    */   public AppRoleDaoImpl()
/*    */   {
/* 27 */     super(AppRole.class);
/*    */   }
/*    */ 
/*    */   public AppRole getByRoleName(String roleName) {
/* 31 */     String hql = "from AppRole ar where ar.roleName=?";
/* 32 */     return (AppRole)findUnique(hql, new Object[] { roleName });
/*    */   }
/*    */ 
/*    */   public HashMap<String, Set<String>> getSecurityDataSource()
/*    */   {
/* 40 */     final HashMap source = new HashMap();
/*    */ 
/* 44 */     getHibernateTemplate().execute(new HibernateCallback()
/*    */     {
/*    */       public Object doInHibernate(Session session) throws HibernateException, SQLException
/*    */       {
/* 48 */         String hql = "from AppRole";
/* 49 */         Query query = session.createQuery(hql);
/* 50 */         List<AppRole> roleList = query.list();
/*    */ 
/* 52 */         for (AppRole role : roleList) {
/* 53 */           TreeSet urlSet = new TreeSet();
/*    */ 
/* 55 */           Iterator functions = role.getFunctions().iterator();
/*    */ 
/* 57 */           while (functions.hasNext()) {
/* 58 */             AppFunction fun = (AppFunction)functions.next();
/* 59 */             Iterator urlIt = fun.getFunUrls().iterator();
/* 60 */             while (urlIt.hasNext()) {
/* 61 */               urlSet.add(((FunUrl)urlIt.next()).getUrlPath());
/*    */             }
/*    */           }
/*    */ 
/* 65 */           source.put(role.getName(), urlSet);
/*    */         }
/* 67 */         return null;
/*    */       }
/*    */     });
/* 70 */     return source;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.system.impl.AppRoleDaoImpl
 * JD-Core Version:    0.6.0
 */