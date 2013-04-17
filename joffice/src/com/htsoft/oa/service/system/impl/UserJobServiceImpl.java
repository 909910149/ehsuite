/*    */ package com.htsoft.oa.service.system.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.system.UserJobDao;
/*    */ import com.htsoft.oa.model.system.AppUser;
/*    */ import com.htsoft.oa.model.system.UserJob;
/*    */ import com.htsoft.oa.service.system.UserJobService;
/*    */ import java.util.List;
/*    */ 
/*    */ public class UserJobServiceImpl extends BaseServiceImpl<UserJob>
/*    */   implements UserJobService
/*    */ {
/*    */   private UserJobDao dao;
/*    */ 
/*    */   public UserJobServiceImpl(UserJobDao dao)
/*    */   {
/* 29 */     super(dao);
/* 30 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   public Boolean IsExistsjob(Long userJobId, Long userId)
/*    */   {
/* 38 */     return this.dao.IsExistsjob(userJobId, userId);
/*    */   }
/*    */ 
/*    */   public List<UserJob> findByUserIdJobs(Long userId)
/*    */   {
/* 46 */     return this.dao.findByUserIdJobs(userId);
/*    */   }
/*    */ 
/*    */   public String add(UserJob userJob)
/*    */   {
/* 54 */     return this.dao.add(userJob);
/*    */   }
/*    */ 
/*    */   public List<Long> getUserIdsByJobId(Long jobId)
/*    */   {
/* 63 */     return this.dao.getUserIdsByJobId(jobId);
/*    */   }
/*    */ 
/*    */   public List<AppUser> getUsersByJobId(Long jobId)
/*    */   {
/* 72 */     return this.dao.getUsersByJobId(jobId);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.system.impl.UserJobServiceImpl
 * JD-Core Version:    0.6.0
 */