 package com.htsoft.core.dao.impl;
 
 import com.htsoft.core.command.CriteriaCommand;
 import com.htsoft.core.command.QueryFilter;
 import com.htsoft.core.command.SortCommandImpl;
 import com.htsoft.core.dao.DynamicDao;
 import com.htsoft.core.web.paging.PagingBean;
 import java.io.Serializable;
 import java.sql.SQLException;
 import java.util.Collections;
 import java.util.List;
 import java.util.Set;
 import org.hibernate.Criteria;
 import org.hibernate.HibernateException;
 import org.hibernate.Query;
 import org.hibernate.SQLQuery;
 import org.hibernate.Session;
 import org.hibernate.SessionFactory;
 import org.hibernate.criterion.Projections;
 import org.hibernate.engine.SessionFactoryImplementor;
 import org.hibernate.hql.ast.QueryTranslatorImpl;
 import org.springframework.orm.hibernate3.HibernateCallback;
 import org.springframework.orm.hibernate3.HibernateTemplate;
 
 public class DynamicDaoImpl
   implements DynamicDao
 {
   private String entityClassName;
   private SessionFactory sessionFactory;
   private HibernateTemplate hibernateTemplate;
 
   public DynamicDaoImpl(String entityClassName)
   {
     this.entityClassName = entityClassName;
   }
 
   public DynamicDaoImpl()
   {
   }
 
   public String getEntityClassName()
   {
     return this.entityClassName;
   }
 
   public void setEntityClassName(String entityClassName) {
     this.entityClassName = entityClassName;
   }
 
   public SessionFactory getSessionFactory()
   {
     return this.sessionFactory;
   }
 
   public void setSessionFactory(SessionFactory sessionFactory) {
     this.sessionFactory = sessionFactory;
   }
 
   public HibernateTemplate getHibernateTemplate()
   {
     if (this.hibernateTemplate == null) {
       this.hibernateTemplate = new HibernateTemplate(this.sessionFactory);
     }
     return this.hibernateTemplate;
   }
 
   public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
     this.hibernateTemplate = hibernateTemplate;
   }
 
   public Object save(Object entity)
   {
     getHibernateTemplate().save(this.entityClassName, entity);
     return entity;
   }
 
   public Object merge(Object entity)
   {
     getHibernateTemplate().merge(this.entityClassName, entity);
     return entity;
   }
 
   public Object get(Serializable id)
   {
     return getHibernateTemplate().load(this.entityClassName, id);
   }
 
   public void remove(Serializable id)
   {
     getHibernateTemplate().delete(this.entityClassName, get(id));
   }
 
   public void remove(Object entity)
   {
     getHibernateTemplate().delete(this.entityClassName, entity);
   }
 
   public void evict(Object entity)
   {
     getHibernateTemplate().evict(entity);
   }
 
   public Long getTotalItems(String queryString, final Object[] values)
   {
     int orderByIndex = queryString.toUpperCase().indexOf(" ORDER BY ");
 
     if (orderByIndex != -1) {
       queryString = queryString.substring(0, orderByIndex);
     }
 
     QueryTranslatorImpl queryTranslator = new QueryTranslatorImpl(queryString, queryString, 
       Collections.EMPTY_MAP, (SessionFactoryImplementor)getSessionFactory());
     queryTranslator.compile(Collections.EMPTY_MAP, false);
     final String sql = "select count(1) from (" + queryTranslator.getSQLString() + ") tmp_count_t";
 
     Object reVal = getHibernateTemplate().execute(new HibernateCallback()
     {
       public Object doInHibernate(Session session) throws HibernateException, SQLException {
         SQLQuery query = session.createSQLQuery(sql);
         if (values != null) {
           for (int i = 0; i < values.length; i++) {
             query.setParameter(i, values[i]);
           }
         }
         return query.uniqueResult();
       }
     });
     return new Long(reVal.toString());
   }
 
   public List<Object> getAll()
   {
     return (List)getHibernateTemplate().execute(new HibernateCallback()
     {
       public Object doInHibernate(Session session) throws HibernateException, SQLException
       {
         String hql = "from " + DynamicDaoImpl.this.entityClassName;
         Query query = session.createQuery(hql);
         return query.list();
       }
     });
   }
 
   public List<Object> getAll(final PagingBean pb)
   {
     final String hql = "from " + this.entityClassName;
     int totalItems = getTotalItems(hql, null).intValue();
     pb.setTotalItems(totalItems);
     return (List)getHibernateTemplate().execute(new HibernateCallback()
     {
       public Object doInHibernate(Session session) throws HibernateException, SQLException {
         Query query = session.createQuery(hql);
         query.setFirstResult(pb.getFirstResult()).setFetchSize(pb.getPageSize().intValue());
         query.setMaxResults(pb.getPageSize().intValue());
         return query.list();
       }
     });
   }
 
   public List<Object> getAll(final QueryFilter queryFilter)
   {
     int totalCounts = getCountByFilter(queryFilter);
 
     queryFilter.getPagingBean().setTotalItems(totalCounts);
 
     List resultList = (List)getHibernateTemplate().execute(new HibernateCallback() {
       public Object doInHibernate(Session session) throws HibernateException, SQLException {
         Criteria criteria = session.createCriteria(DynamicDaoImpl.this.entityClassName);
         queryFilter.getAliasSet().clear();
         DynamicDaoImpl.this.setCriteriaByQueryFilter(criteria, queryFilter);
         return criteria.list();
       }
     });
     return resultList;
   }
 
   protected int getCountByFilter(final QueryFilter filter) {
     Object count = getHibernateTemplate().execute(new HibernateCallback()
     {
       public Object doInHibernate(Session session) throws HibernateException, SQLException {
         Criteria criteria = session.createCriteria(DynamicDaoImpl.this.entityClassName);
         for (int i = 0; i < filter.getCommands().size(); i++) {
           CriteriaCommand command = (CriteriaCommand)filter.getCommands().get(i);
           if (!(command instanceof SortCommandImpl)) {
             criteria = command.execute(criteria);
           }
         }
         criteria.setProjection(Projections.rowCount());
         return criteria.uniqueResult();
       }
     });
     if (count == null) return new Integer(0).intValue();
     return new Integer(count.toString()).intValue();
   }
 
   private Criteria setCriteriaByQueryFilter(Criteria criteria, QueryFilter filter) {
     for (int i = 0; i < filter.getCommands().size(); i++) {
       criteria = ((CriteriaCommand)filter.getCommands().get(i)).execute(criteria);
     }
 
     criteria.setFirstResult(filter.getPagingBean().getFirstResult());
     criteria.setMaxResults(filter.getPagingBean().getPageSize().intValue());
 
     return criteria;
   }
 }

