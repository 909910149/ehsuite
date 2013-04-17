/*    */ package com.htsoft.oa.dao.customer.impl;
/*    */ 
/*    */ import com.htsoft.core.dao.impl.BaseDaoImpl;
/*    */ import com.htsoft.oa.dao.customer.CustomerDao;
/*    */ import com.htsoft.oa.model.customer.Customer;
/*    */ import java.sql.SQLException;
/*    */ import org.hibernate.HibernateException;
/*    */ import org.hibernate.Query;
/*    */ import org.hibernate.Session;
/*    */ import org.springframework.orm.hibernate3.HibernateCallback;
/*    */ import org.springframework.orm.hibernate3.HibernateTemplate;
/*    */ 
/*    */ public class CustomerDaoImpl extends BaseDaoImpl<Customer>
/*    */   implements CustomerDao
/*    */ {
/*    */   public CustomerDaoImpl()
/*    */   {
/* 20 */     super(Customer.class);
/*    */   }
/*    */ 
/*    */   public boolean checkCustomerNo(final String customerNo)
/*    */   {
/* 25 */     String hql = "select count(*) from Customer c where c.customerNo = ?";
/* 26 */     Long count = (Long)getHibernateTemplate().execute(new HibernateCallback()
/*    */     {
/*    */       public Object doInHibernate(Session session)
/*    */         throws HibernateException, SQLException
/*    */       {
/* 31 */         Query query = session.createQuery("select count(*) from Customer c where c.customerNo = ?");
/* 32 */         query.setString(0, customerNo);
/* 33 */         return query.uniqueResult();
/*    */       }
/*    */     });
/* 36 */     return count.longValue() == 0L;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.customer.impl.CustomerDaoImpl
 * JD-Core Version:    0.6.0
 */