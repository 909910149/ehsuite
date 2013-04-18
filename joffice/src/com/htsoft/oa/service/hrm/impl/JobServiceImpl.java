/*    */ package com.htsoft.oa.service.hrm.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.hrm.JobDao;
/*    */ import com.htsoft.oa.model.hrm.Job;
/*    */ import com.htsoft.oa.service.hrm.JobService;
/*    */ import java.util.List;
/*    */ 
/*    */ public class JobServiceImpl extends BaseServiceImpl<Job>
/*    */   implements JobService
/*    */ {
/*    */   private JobDao dao;
/*    */ 
/*    */   public JobServiceImpl(JobDao dao)
/*    */   {
/* 26 */     super(dao);
/* 27 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   public List<Job> findByDep(Long jobId)
/*    */   {
/* 32 */     return this.dao.findByDep(jobId);
/*    */   }
/*    */ 
/*    */   public List<Job> findByCondition(Long parentId)
/*    */   {
/* 40 */     return this.dao.findByCondition(parentId);
/*    */   }
/*    */ 
/*    */   public void edit(Job job)
/*    */   {
/* 48 */     this.dao.edit(job);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.hrm.impl.JobServiceImpl
 * JD-Core Version:    0.6.0
 */