/*    */ package com.htsoft.oa.dao.info.impl;
/*    */ 
/*    */ import com.htsoft.core.dao.impl.BaseDaoImpl;
/*    */ import com.htsoft.oa.dao.info.SectionDao;
/*    */ import com.htsoft.oa.model.info.Section;
/*    */ import java.sql.SQLException;
/*    */ import org.hibernate.HibernateException;
/*    */ import org.hibernate.Query;
/*    */ import org.hibernate.Session;
/*    */ import org.springframework.orm.hibernate3.HibernateCallback;
/*    */ import org.springframework.orm.hibernate3.HibernateTemplate;
/*    */ 
/*    */ public class SectionDaoImpl extends BaseDaoImpl<Section>
/*    */   implements SectionDao
/*    */ {
/*    */   public SectionDaoImpl()
/*    */   {
/* 21 */     super(Section.class);
/*    */   }
/*    */ 
/*    */   public Integer getLastColumn()
/*    */   {
/* 26 */     String hql = "select max(st.rowNumber) from Section st where st.colNumber = ? ";
/*    */ 
/* 28 */     Integer maxRow = (Integer)getHibernateTemplate().execute(new HibernateCallback()
/*    */     {
/*    */       public Object doInHibernate(Session session)
/*    */         throws HibernateException, SQLException
/*    */       {
/* 33 */         Query query = session.createQuery("select max(st.rowNumber) from Section st where st.colNumber = ? ");
/* 34 */         query.setInteger(0, Section.COLUMN_ONE.intValue());
/* 35 */         return query.uniqueResult();
/*    */       }
/*    */     });
/* 38 */     if (maxRow == null) {
/* 39 */       maxRow = Integer.valueOf(1);
/*    */     }
/* 41 */     return maxRow;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.info.impl.SectionDaoImpl
 * JD-Core Version:    0.6.0
 */