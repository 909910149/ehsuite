/*    */ package com.htsoft.oa.dao.hrm.impl;
/*    */ 
/*    */ import com.htsoft.core.dao.impl.BaseDaoImpl;
/*    */ import com.htsoft.oa.dao.hrm.EmpProfileDao;
/*    */ import com.htsoft.oa.model.hrm.EmpProfile;
/*    */ import java.sql.SQLException;
/*    */ import org.hibernate.HibernateException;
/*    */ import org.hibernate.Query;
/*    */ import org.hibernate.Session;
/*    */ import org.springframework.orm.hibernate3.HibernateCallback;
/*    */ import org.springframework.orm.hibernate3.HibernateTemplate;
/*    */ 
/*    */ public class EmpProfileDaoImpl extends BaseDaoImpl<EmpProfile>
/*    */   implements EmpProfileDao
/*    */ {
/*    */   public EmpProfileDaoImpl()
/*    */   {
/* 20 */     super(EmpProfile.class);
/*    */   }
/*    */ 
/*    */   public boolean checkProfileNo(final String profileNo)
/*    */   {
/* 25 */     String hql = "select count(*) from EmpProfile ep where ep.profileNo = ?";
/* 26 */     Long count = (Long)getHibernateTemplate().execute(new HibernateCallback()
/*    */     {
/*    */       public Object doInHibernate(Session session) throws HibernateException, SQLException
/*    */       {
/* 30 */         Query query = session.createQuery("select count(*) from EmpProfile ep where ep.profileNo = ?");
/* 31 */         query.setString(0, profileNo);
/* 32 */         return query.uniqueResult();
/*    */       }
/*    */     });
/* 35 */     return count.longValue() == 0L;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.hrm.impl.EmpProfileDaoImpl
 * JD-Core Version:    0.6.0
 */