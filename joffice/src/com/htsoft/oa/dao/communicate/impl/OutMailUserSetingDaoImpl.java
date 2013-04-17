/*    */ package com.htsoft.oa.dao.communicate.impl;
/*    */ 
/*    */ import com.htsoft.core.dao.impl.BaseDaoImpl;
/*    */ import com.htsoft.core.web.paging.PagingBean;
/*    */ import com.htsoft.oa.dao.communicate.OutMailUserSetingDao;
/*    */ import com.htsoft.oa.model.communicate.OutMailUserSeting;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.springframework.orm.hibernate3.HibernateTemplate;
/*    */ 
/*    */ public class OutMailUserSetingDaoImpl extends BaseDaoImpl<OutMailUserSeting>
/*    */   implements OutMailUserSetingDao
/*    */ {
/*    */   public OutMailUserSetingDaoImpl()
/*    */   {
/* 28 */     super(OutMailUserSeting.class);
/*    */   }
/*    */ 
/*    */   public OutMailUserSeting getByLoginId(Long loginid)
/*    */   {
/* 36 */     String hql = "select a from OutMailUserSeting a where a.appUser.userId =" + 
/* 37 */       loginid;
/* 38 */     List loginList = getHibernateTemplate().find(hql);
/* 39 */     return (loginList != null) && (loginList.size() > 0) ? 
/* 40 */       (OutMailUserSeting)loginList
/* 40 */       .get(0) : 
/* 41 */       null;
/*    */   }
/*    */ 
/*    */   public List findByUserAll()
/*    */   {
/* 46 */     String hql = "select au,vo from OutMailUserSeting au right join au.appUser vo where vo.delFlag = 0";
/* 47 */     return findByHql(hql);
/*    */   }
/*    */ 
/*    */   public List<OutMailUserSeting> findByUserAll(String userName, PagingBean pb)
/*    */   {
/* 52 */     List params = new ArrayList();
/* 53 */     String hql = "select au from OutMailUserSeting au right join au.appUser vo where vo.delFlag = 0";
/* 54 */     if (StringUtils.isNotEmpty(userName)) {
/* 55 */       hql = hql + "and vo.fullname like ?";
/* 56 */       params.add("%" + userName + "%");
/*    */     }
/* 58 */     return findByHql(hql, params.toArray(), pb);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.communicate.impl.OutMailUserSetingDaoImpl
 * JD-Core Version:    0.6.0
 */