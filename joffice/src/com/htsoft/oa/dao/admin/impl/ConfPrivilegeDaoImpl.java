/*    */ package com.htsoft.oa.dao.admin.impl;
/*    */ 
/*    */ import com.htsoft.core.dao.impl.BaseDaoImpl;
/*    */ import com.htsoft.oa.dao.admin.ConfPrivilegeDao;
/*    */ import com.htsoft.oa.model.admin.ConfPrivilege;
/*    */ import java.io.PrintStream;
/*    */ import java.sql.SQLException;
/*    */ import java.util.List;
/*    */ import org.hibernate.HibernateException;
/*    */ import org.hibernate.Query;
/*    */ import org.hibernate.Session;
/*    */ import org.springframework.orm.hibernate3.HibernateCallback;
/*    */ import org.springframework.orm.hibernate3.HibernateTemplate;
/*    */ 
/*    */ public class ConfPrivilegeDaoImpl extends BaseDaoImpl<ConfPrivilege>
/*    */   implements ConfPrivilegeDao
/*    */ {
/*    */   public ConfPrivilegeDaoImpl()
/*    */   {
/* 30 */     super(ConfPrivilege.class);
/*    */   }
/*    */ 
/*    */   public Short getPrivilege(Long userId, Long confId, Short s)
/*    */   {
/* 39 */     Short st = Short.valueOf((short) 0);
/* 40 */     String hql = "select p from ConfPrivilege p where p.userId =" + userId + 
/* 41 */       " and p.confId = " + confId + " and p.rights=" + s;
/* 42 */     List list = findByHql(hql);
/* 43 */     if ((list != null) && (list.size() > 0)) {
/* 44 */       ConfPrivilege cp = (ConfPrivilege)list.get(0);
/* 45 */       st = cp.getRights();
/*    */     }
/* 47 */     return st;
/*    */   }
/*    */ 
/*    */   public void delete(final Long confId)
/*    */   {
/* 56 */     String hql = "delete ConfPrivilege c where c.confId = ?";
/* 57 */     Object count = getHibernateTemplate().execute(new HibernateCallback()
/*    */     {
/*    */       public Object doInHibernate(Session session) throws HibernateException, SQLException
/*    */       {
/* 61 */         Query query = session.createQuery("delete ConfPrivilege c where c.confId = ?");
/* 62 */         query.setLong(0, confId.longValue());
/* 63 */         return Integer.valueOf(query.executeUpdate());
/*    */       }
/*    */     });
/* 66 */     System.out.println("删除数据条数：" + count);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.admin.impl.ConfPrivilegeDaoImpl
 * JD-Core Version:    0.6.0
 */