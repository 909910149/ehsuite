/*     */ package com.htsoft.oa.dao.info.impl;
/*     */ 
/*     */ import com.htsoft.core.dao.impl.BaseDaoImpl;
/*     */ import com.htsoft.core.web.paging.PagingBean;
/*     */ import com.htsoft.oa.dao.info.InMessageDao;
/*     */ import com.htsoft.oa.model.info.InMessage;
/*     */ import com.htsoft.oa.model.info.ShortMessage;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.hibernate.Query;
/*     */ import org.hibernate.Session;
/*     */ 
/*     */ public class InMessageDaoImpl extends BaseDaoImpl<InMessage>
/*     */   implements InMessageDao
/*     */ {
/*     */   public InMessageDaoImpl()
/*     */   {
/*  22 */     super(InMessage.class);
/*     */   }
/*     */ 
/*     */   public InMessage findByRead(Long userId)
/*     */   {
/*  30 */     String hql = "from InMessage vo where vo.readFlag=0 and vo.delFlag=0 and vo.userId=?";
/*  31 */     Object[] objs = { userId };
/*  32 */     List list = findByHql(hql, objs);
/*  33 */     if (list.size() > 0) {
/*  34 */       return (InMessage)list.get(list.size() - 1);
/*     */     }
/*  36 */     return null;
/*     */   }
/*     */ 
/*     */   public Integer findByReadFlag(Long userId)
/*     */   {
/*  43 */     String sql = "select count(*) from InMessage vo where vo.readFlag=0 and vo.delFlag=0 and vo.userId=" + userId;
/*  44 */     Query query = getSession().createQuery(sql);
/*  45 */     return Integer.valueOf(Integer.parseInt(query.list().iterator().next().toString()));
/*     */   }
/*     */ 
/*     */   public List<InMessage> findAll(Long userId, PagingBean pb)
/*     */   {
/*  50 */     String hql = "from InMessage vo where vo.userId=?";
/*  51 */     Object[] objs = { userId };
/*  52 */     return findByHql(hql, objs, pb);
/*     */   }
/*     */ 
/*     */   public List<InMessage> findByShortMessage(ShortMessage shortMessage, PagingBean pb)
/*     */   {
/*  57 */     String hql = "from InMessage vo1,ShortMessage vo2 where vo1.shortMessage=?";
/*  58 */     Object[] objs = { shortMessage };
/*  59 */     return findByHql(hql, objs, pb);
/*     */   }
/*     */ 
/*     */   public List findByUser(Long userId, PagingBean pb)
/*     */   {
/*  64 */     String hql = "select vo1,vo2 from InMessage vo1,ShortMessage vo2 where vo1.shortMessage=vo2 and vo2.msgType=1 and vo2.senderId=? order by vo2.sendTime desc";
/*  65 */     Object[] objs = { userId };
/*  66 */     return findByHql(hql, objs, pb);
/*     */   }
/*     */ 
/*     */   public List findByUser(Long userId)
/*     */   {
/*  71 */     String hql = "select vo1,vo2 from InMessage vo1,ShortMessage vo2 where vo1.shortMessage=vo2 and vo2.senderId=?";
/*  72 */     Object[] objs = { userId };
/*  73 */     return findByHql(hql, objs);
/*     */   }
/*     */ 
/*     */   public List searchInMessage(Long userId, InMessage inMessage, ShortMessage shortMessage, Date from, Date to, PagingBean pb)
/*     */   {
/*  84 */     StringBuffer hql = new StringBuffer("select vo1 ,vo2 from InMessage vo1,ShortMessage vo2 where vo1.shortMessage=vo2 and vo2.msgType=1 and vo2.senderId=?");
/*  85 */     ArrayList paramList = new ArrayList();
/*  86 */     paramList.add(userId);
/*  87 */     if (to != null) {
/*  88 */       hql.append("and vo2.sendTime <= ?");
/*  89 */       paramList.add(to);
/*     */     }
/*  91 */     if (from != null) {
/*  92 */       hql.append("and vo2.sendTime >= ?");
/*  93 */       paramList.add(from);
/*     */     }
/*  95 */     if ((shortMessage != null) && 
/*  96 */       (shortMessage.getMsgType() != null)) {
/*  97 */       hql.append(" and vo2.msgType=?");
/*  98 */       paramList.add(shortMessage.getMsgType());
/*     */     }
/*     */ 
/* 101 */     if ((inMessage != null) && 
/* 102 */       (StringUtils.isNotEmpty(inMessage.getUserFullname()))) {
/* 103 */       hql.append(" and vo1.userFullname=?");
/* 104 */       paramList.add(inMessage.getUserFullname());
/*     */     }
/*     */ 
/* 107 */     hql.append(" order by vo2.sendTime desc");
/*     */ 
/* 109 */     return findByHql(hql.toString(), paramList.toArray(), pb);
/*     */   }
/*     */ 
/*     */   public InMessage findLatest(Long userId)
/*     */   {
/* 114 */     String hql = "from InMessage vo where vo.delFlag=0 and vo.userId=?";
/* 115 */     Object[] objs = { userId };
/* 116 */     List list = findByHql(hql, objs);
/* 117 */     if (list.size() > 0) {
/* 118 */       return (InMessage)list.get(list.size() - 1);
/*     */     }
/* 120 */     return null;
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.info.impl.InMessageDaoImpl
 * JD-Core Version:    0.6.0
 */