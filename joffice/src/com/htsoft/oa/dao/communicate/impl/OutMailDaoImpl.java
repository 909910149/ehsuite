/*    */ package com.htsoft.oa.dao.communicate.impl;
/*    */ 
/*    */ import com.htsoft.core.dao.impl.BaseDaoImpl;
/*    */ import com.htsoft.oa.dao.communicate.OutMailDao;
/*    */ import com.htsoft.oa.model.communicate.OutMail;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.springframework.orm.hibernate3.HibernateTemplate;
/*    */ 
/*    */ public class OutMailDaoImpl extends BaseDaoImpl<OutMail>
/*    */   implements OutMailDao
/*    */ {
/*    */   public OutMailDaoImpl()
/*    */   {
/* 20 */     super(OutMail.class);
/*    */   }
/*    */   public List<OutMail> findByFolderId(Long folderId) {
/* 23 */     String hql = "from OutMail where folderId = ?";
/* 24 */     return findByHql(hql, new Object[] { folderId });
/*    */   }
/*    */ 
/*    */   public Long CountByFolderId(Long folderId)
/*    */   {
/* 29 */     String hql = "select count(*) from OutMail where folderId =" + folderId;
/* 30 */     return (Long)getHibernateTemplate().find(hql).get(0);
/*    */   }
/*    */ 
/*    */   public Map getUidByUserId(Long userId) {
/* 34 */     String hql = "select om.uid from OutMail om where om.userId =" + userId;
/* 35 */     List<String> uidList = getHibernateTemplate().find(hql);
/* 36 */     Map uidMap = new HashMap();
/* 37 */     for (String uid : uidList) {
/* 38 */       uidMap.put(uid, "Y");
/*    */     }
/* 40 */     return uidMap;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.communicate.impl.OutMailDaoImpl
 * JD-Core Version:    0.6.0
 */