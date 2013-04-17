/*    */ package com.htsoft.oa.dao.system.impl;
/*    */ 
/*    */ import com.htsoft.core.dao.impl.BaseDaoImpl;
/*    */ import com.htsoft.oa.dao.system.UserJobDao;
/*    */ import com.htsoft.oa.model.hrm.Job;
/*    */ import com.htsoft.oa.model.system.AppUser;
/*    */ import com.htsoft.oa.model.system.UserJob;
/*    */ import java.util.List;
/*    */ import org.apache.commons.logging.Log;
/*    */ 
/*    */ public class UserJobDaoImpl extends BaseDaoImpl<UserJob>
/*    */   implements UserJobDao
/*    */ {
/*    */   public UserJobDaoImpl()
/*    */   {
/* 28 */     super(UserJob.class);
/*    */   }
/*    */ 
/*    */   public Boolean IsExistsjob(Long userJobId, Long userId)
/*    */   {
/* 36 */     StringBuffer hql = new StringBuffer(
/* 37 */       "select u from UserJob u where u.isMain = 1 and u.appUser.userId = ? ");
/* 38 */     if ((userJobId != null) && (!userJobId.equals("")))
/* 39 */       hql.append("and u.userJobId not in(" + userJobId + ") ");
/* 40 */     Object[] paramList = { userId };
/* 41 */     List list = findByHql(hql.toString(), paramList);
/* 42 */     this.logger.debug("自定义[UserJobImpl]:" + hql);
/* 43 */     return Boolean.valueOf((list != null) && (list.size() > 0));
/*    */   }
/*    */ 
/*    */   public List<UserJob> findByUserIdJobs(Long userId)
/*    */   {
/* 51 */     String hql = "select u from UserJob u where u.appUser.userId = ? ";
/* 52 */     Object[] paramList = { userId };
/* 53 */     return findByHql(hql, paramList);
/*    */   }
/*    */ 
/*    */   public String add(UserJob userJob)
/*    */   {
/* 61 */     String msg = "{success:true,msg:'数据操作成功！'}";
/* 62 */     String hql = "select u from UserJob u where u.appUser.userId = ? and u.job.jobId = ? ";
/* 63 */     Object[] paramList = { userJob.getAppUser().getUserId(), 
/* 64 */       userJob.getJob().getJobId() };
/* 65 */     List list = findByHql(hql, paramList);
/* 66 */     if ((list != null) && (list.size() > 0))
/* 67 */       msg = "{failure:true,msg:'对不起，该用户[" + 
/* 68 */         userJob.getAppUser().getUsername() + "]已经添加了该职位[" + 
/* 69 */         userJob.getJob().getJobName() + "]！'}";
/*    */     else
/* 71 */       save(userJob);
/* 72 */     return msg;
/*    */   }
/*    */ 
/*    */   public List<Long> getUserIdsByJobId(Long jobId)
/*    */   {
/* 82 */     String hql = "select u.appUser.userId from UserJob u where u.job.jobId=?";
/* 83 */     List userIds = findByHql(hql, new Object[] { jobId });
/* 84 */     return userIds;
/*    */   }
/*    */ 
/*    */   public List<AppUser> getUsersByJobId(Long jobId)
/*    */   {
/* 93 */     String hql = "select u.appUser from UserJob u where u.job.jobId=?";
/* 94 */     List list = findByHql(hql, new Object[] { jobId });
/* 95 */     return list;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.system.impl.UserJobDaoImpl
 * JD-Core Version:    0.6.0
 */