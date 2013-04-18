/*     */ package com.htsoft.oa.dao.system.impl;
/*     */ 
/*     */ import com.htsoft.core.dao.impl.BaseDaoImpl;
/*     */ import com.htsoft.core.web.paging.PagingBean;
/*     */ import com.htsoft.oa.dao.system.RelativeUserDao;
/*     */ import com.htsoft.oa.model.system.AppUser;
/*     */ import com.htsoft.oa.model.system.RelativeUser;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.logging.Log;
/*     */ 
/*     */ public class RelativeUserDaoImpl extends BaseDaoImpl<RelativeUser>
/*     */   implements RelativeUserDao
/*     */ {
/*     */   public RelativeUserDaoImpl()
/*     */   {
/*  29 */     super(RelativeUser.class);
/*     */   }
/*     */ 
/*     */   public AppUser judge(Long userId, Long jobUserId)
/*     */   {
/*  37 */     String hql = "select r from RelativeUser r where r.appUser.userId = ? and r.jobUser.userId = ? ";
/*  38 */     Object[] paramList = { userId, jobUserId };
/*  39 */     List list = findByHql(hql, paramList);
/*  40 */     this.logger.debug(hql);
/*  41 */     return (list != null) && (list.size() > 0) ? ((RelativeUser)list.get(0)).getJobUser() : 
/*  42 */       null;
/*     */   }
/*     */ 
/*     */   public List<AppUser> findByUserIdReJobId(Long userId, Long reJobId)
/*     */   {
/*  53 */     String hql = "select ru.jobUser from RelativeUser ru where ru.appUser.userId=? and ru.relativeJob.reJobId=? ";
/*  54 */     Object result = findByHql(hql, new Object[] { userId, reJobId });
/*  55 */     return (List)result;
/*     */   }
/*     */ 
/*     */   public List<RelativeUser> list(Long appUserId, Long reJobId, PagingBean pb)
/*     */   {
/*  60 */     ArrayList paramList = new ArrayList();
/*  61 */     StringBuffer hql = new StringBuffer(
/*  62 */       "select r from RelativeUser r where 1=1 ");
/*  63 */     if ((appUserId != null) && (appUserId.longValue() > 0L)) {
/*  64 */       hql.append("and r.appUser.userId = ? ");
/*  65 */       paramList.add(appUserId);
/*     */     }
/*  67 */     if ((reJobId != null) && (reJobId.longValue() > 0L)) {
/*  68 */       hql.append("and r.relativeJob.reJobId = ? ");
/*  69 */       paramList.add(reJobId);
/*     */     }
/*  71 */     this.logger.debug("自定义：RelativeUserDaoImpl:" + hql.toString());
/*  72 */     return (ArrayList)findByHql(hql.toString(), paramList
/*  73 */       .toArray(), pb);
/*     */   }
/*     */ 
/*     */   public List<Long> getReJobUserIds(Long userId, Long reJobId)
/*     */   {
/*  86 */     String hql = "select jobUser.userId from RelativeUser ru where ru.appUser.userId=? and relativeJob.reJobId=? ";
/*  87 */     List list = findByHql(hql, new Object[] { userId, reJobId });
/*  88 */     return list;
/*     */   }
/*     */ 
/*     */   public Set<AppUser> getUpUser(Long userId)
/*     */   {
/*  97 */     String hql1 = "select  ru.jobUser from RelativeUser ru where ru.appUser.userId =? and ru.isSuper =?";
/*  98 */     List list1 = findByHql(hql1, new Object[] { userId, RelativeUser.SUPER_FLAG_TRUE });
/*     */ 
/* 101 */     String hql2 = "select  ru.appUser from RelativeUser ru where  ru.jobUser.userId = ? and ru.isSuper = ?";
/* 102 */     List list2 = findByHql(hql2, new Object[] { userId, RelativeUser.SUPER_FLAG_FALSE });
/*     */ 
/* 104 */     Set result = new HashSet();
/* 105 */     result.addAll(list1);
/* 106 */     result.addAll(list2);
/*     */ 
/* 108 */     return result;
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.system.impl.RelativeUserDaoImpl
 * JD-Core Version:    0.6.0
 */