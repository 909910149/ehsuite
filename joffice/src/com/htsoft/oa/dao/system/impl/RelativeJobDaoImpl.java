/*    */ package com.htsoft.oa.dao.system.impl;
/*    */ 
/*    */ import com.htsoft.core.dao.impl.BaseDaoImpl;
/*    */ import com.htsoft.oa.dao.system.RelativeJobDao;
/*    */ import com.htsoft.oa.model.system.RelativeJob;
/*    */ import java.util.List;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.hibernate.Query;
/*    */ import org.hibernate.Session;
/*    */ 
/*    */ public class RelativeJobDaoImpl extends BaseDaoImpl<RelativeJob>
/*    */   implements RelativeJobDao
/*    */ {
/*    */   public RelativeJobDaoImpl()
/*    */   {
/* 26 */     super(RelativeJob.class);
/*    */   }
/*    */ 
/*    */   public void remove(Long id)
/*    */   {
/* 34 */     String hql = "delete from RelativeJob r where r.path like ? ";
/* 35 */     Query query = getSession().createQuery(hql);
/* 36 */     query.setString(0, ((RelativeJob)get(id)).getPath() + "%");
/* 37 */     this.logger.debug(hql);
/* 38 */     query.executeUpdate();
/*    */   }
/*    */ 
/*    */   public List<RelativeJob> findByParentId(Long parentId)
/*    */   {
/* 46 */     String hql = "select r from RelativeJob r where r.parent = ? ";
/* 47 */     Object[] paramList = { parentId };
/* 48 */     return findByHql(hql, paramList);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.system.impl.RelativeJobDaoImpl
 * JD-Core Version:    0.6.0
 */