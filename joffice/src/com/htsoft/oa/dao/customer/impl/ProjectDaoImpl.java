/*    */ package com.htsoft.oa.dao.customer.impl;
/*    */ 
/*    */ import com.htsoft.core.dao.impl.BaseDaoImpl;
/*    */ import com.htsoft.oa.dao.customer.ProjectDao;
/*    */ import com.htsoft.oa.model.customer.Project;
/*    */ import java.sql.SQLException;
/*    */ import org.hibernate.HibernateException;
/*    */ import org.hibernate.Query;
/*    */ import org.hibernate.Session;
/*    */ import org.springframework.orm.hibernate3.HibernateCallback;
/*    */ import org.springframework.orm.hibernate3.HibernateTemplate;
/*    */ 
/*    */ public class ProjectDaoImpl extends BaseDaoImpl<Project>
/*    */   implements ProjectDao
/*    */ {
/*    */   public ProjectDaoImpl()
/*    */   {
/* 20 */     super(Project.class);
/*    */   }
/*    */ 
/*    */   public boolean checkProjectNo(final String projectNo)
/*    */   {
/* 25 */     String hql = "select count(*) from Project p where p.projectNo = ?";
/* 26 */     Long count = (Long)getHibernateTemplate().execute(new HibernateCallback()
/*    */     {
/*    */       public Object doInHibernate(Session session) throws HibernateException, SQLException
/*    */       {
/* 30 */         Query query = session.createQuery("select count(*) from Project p where p.projectNo = ?");
/* 31 */         query.setString(0, projectNo);
/* 32 */         return query.uniqueResult();
/*    */       }
/*    */     });
/* 35 */     return count.longValue() == 0L;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.customer.impl.ProjectDaoImpl
 * JD-Core Version:    0.6.0
 */