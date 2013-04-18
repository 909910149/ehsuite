/*    */ package com.htsoft.oa.dao.customer.impl;
/*    */ 
/*    */ import com.htsoft.core.dao.impl.BaseDaoImpl;
/*    */ import com.htsoft.oa.dao.customer.CusLinkmanDao;
/*    */ import com.htsoft.oa.model.customer.CusLinkman;
/*    */ import java.sql.SQLException;
/*    */ import org.hibernate.HibernateException;
/*    */ import org.hibernate.Query;
/*    */ import org.hibernate.Session;
/*    */ import org.springframework.orm.hibernate3.HibernateCallback;
/*    */ import org.springframework.orm.hibernate3.HibernateTemplate;
/*    */ 
/*    */ public class CusLinkmanDaoImpl extends BaseDaoImpl<CusLinkman>
/*    */   implements CusLinkmanDao
/*    */ {
/*    */   public CusLinkmanDaoImpl()
/*    */   {
/* 20 */     super(CusLinkman.class);
/*    */   }
/*    */ 
/*    */   public boolean checkMainCusLinkman(final Long customerId, final Long linkmanId)
/*    */   {
/* 25 */     final StringBuffer hql = new StringBuffer("select count(*) from CusLinkman  cl where cl.isPrimary = 1 and cl.customer.customerId =? ");
/* 26 */     if (linkmanId != null) {
/* 27 */       hql.append("and cl.linkmanId != ? ");
/*    */     }
/* 29 */     Long count = (Long)getHibernateTemplate().execute(new HibernateCallback()
/*    */     {
/*    */       public Object doInHibernate(Session session)
/*    */         throws HibernateException, SQLException
/*    */       {
/* 34 */         Query query = session.createQuery(hql.toString());
/* 35 */         query.setLong(0, customerId.longValue());
/* 36 */         if (linkmanId != null) {
/* 37 */           query.setLong(1, linkmanId.longValue());
/*    */         }
/* 39 */         return query.uniqueResult();
/*    */       }
/*    */     });
/* 43 */     return count.longValue() == 0L;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.customer.impl.CusLinkmanDaoImpl
 * JD-Core Version:    0.6.0
 */