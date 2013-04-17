/*    */ package com.htsoft.oa.dao.system.impl;
/*    */ 
/*    */ import com.htsoft.core.dao.impl.BaseDaoImpl;
/*    */ import com.htsoft.oa.dao.system.DictionaryDao;
/*    */ import com.htsoft.oa.model.system.Dictionary;
/*    */ import java.sql.SQLException;
/*    */ import java.util.List;
/*    */ import org.hibernate.HibernateException;
/*    */ import org.hibernate.Query;
/*    */ import org.hibernate.Session;
/*    */ import org.springframework.orm.hibernate3.HibernateCallback;
/*    */ import org.springframework.orm.hibernate3.HibernateTemplate;
/*    */ 
/*    */ public class DictionaryDaoImpl extends BaseDaoImpl<Dictionary>
/*    */   implements DictionaryDao
/*    */ {
/*    */   public DictionaryDaoImpl()
/*    */   {
/* 19 */     super(Dictionary.class);
/*    */   }
/*    */ 
/*    */   public List<String> getAllItems()
/*    */   {
/* 24 */     String hql = "select itemName from Dictionary group by itemName";
/* 25 */     return (List)getHibernateTemplate().execute(
/* 26 */       new HibernateCallback()
/*    */     {
/*    */       public Object doInHibernate(Session session) throws HibernateException, SQLException
/*    */       {
/* 30 */         Query query = session.createQuery("select itemName from Dictionary group by itemName");
/* 31 */         return query.list();
/*    */       }
/*    */     });
/*    */   }
/*    */ 
/*    */   public List<String> getAllByItemName(final String itemName) {
/* 38 */     String hql = "select itemValue from Dictionary where itemName=?";
/* 39 */     return (List)getHibernateTemplate().execute(
/* 40 */       new HibernateCallback()
/*    */     {
/*    */       public Object doInHibernate(Session session) throws HibernateException, SQLException
/*    */       {
/* 44 */         Query query = session.createQuery("select itemValue from Dictionary where itemName=?");
/* 45 */         query.setString(0, itemName);
/* 46 */         return query.list();
/*    */       }
/*    */     });
/*    */   }
/*    */ 
/*    */   public List<Dictionary> getByItemName(final String itemName) {
/* 53 */     String hql = " from Dictionary where itemName=?";
/* 54 */     return (List)getHibernateTemplate().execute(
/* 55 */       new HibernateCallback()
/*    */     {
/*    */       public Object doInHibernate(Session session) throws HibernateException, SQLException
/*    */       {
/* 59 */         Query query = session.createQuery(" from Dictionary where itemName=?");
/* 60 */         query.setString(0,itemName);
/* 61 */         return query.list();
/*    */       }
/*    */     });
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.system.impl.DictionaryDaoImpl
 * JD-Core Version:    0.6.0
 */