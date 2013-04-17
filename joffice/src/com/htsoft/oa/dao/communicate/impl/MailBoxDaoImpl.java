/*    */ package com.htsoft.oa.dao.communicate.impl;
/*    */ 
/*    */ import com.htsoft.core.Constants;
/*    */ import com.htsoft.core.dao.impl.BaseDaoImpl;
/*    */ import com.htsoft.core.util.ContextUtil;
/*    */ import com.htsoft.core.web.paging.PagingBean;
/*    */ import com.htsoft.oa.dao.communicate.MailBoxDao;
/*    */ import com.htsoft.oa.model.communicate.MailBox;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.hibernate.Query;
/*    */ import org.hibernate.Session;
/*    */ import org.springframework.orm.hibernate3.HibernateTemplate;
/*    */ 
/*    */ public class MailBoxDaoImpl extends BaseDaoImpl<MailBox>
/*    */   implements MailBoxDao
/*    */ {
/*    */   public MailBoxDaoImpl()
/*    */   {
/* 22 */     super(MailBox.class);
/*    */   }
/*    */ 
/*    */   public Long CountByFolderId(Long folderId)
/*    */   {
/* 27 */     String hql = "select count(*) from MailBox where folderId =" + folderId;
/*    */ 
/* 29 */     Query query = getSession().createQuery(hql);
/* 30 */     return (Long)getHibernateTemplate().find(hql).get(0);
/*    */   }
/*    */ 
/*    */   public List<MailBox> findByFolderId(Long folderId) {
/* 34 */     String hql = "from MailBox where folderId = ?";
/* 35 */     return findByHql(hql, new Object[] { folderId });
/*    */   }
/*    */ 
/*    */   public List<MailBox> findBySearch(String searchContent, PagingBean pb)
/*    */   {
/* 40 */     ArrayList params = new ArrayList();
/*    */ 
/* 42 */     StringBuffer hql = new StringBuffer("from MailBox mb where mb.delFlag = ? and mb.appUser.userId =?");
/* 43 */     params.add(Constants.FLAG_UNDELETED);
/* 44 */     params.add(ContextUtil.getCurrentUserId());
/*    */ 
/* 46 */     if (StringUtils.isNotEmpty(searchContent)) {
/* 47 */       hql.append(" and (mb.mail.subject like ? or mb.mail.content like ?)");
/* 48 */       params.add("%" + searchContent + "%");
/* 49 */       params.add("%" + searchContent + "%");
/*    */     }
/*    */ 
/* 52 */     hql.append(" order by mb.sendTime desc");
/* 53 */     return findByHql(hql.toString(), params.toArray(), pb);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.communicate.impl.MailBoxDaoImpl
 * JD-Core Version:    0.6.0
 */