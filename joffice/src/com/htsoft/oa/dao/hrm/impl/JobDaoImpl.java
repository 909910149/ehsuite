/*    */ package com.htsoft.oa.dao.hrm.impl;
/*    */ 
/*    */ import com.htsoft.core.dao.impl.BaseDaoImpl;
/*    */ import com.htsoft.oa.dao.hrm.JobDao;
/*    */ import com.htsoft.oa.model.hrm.Job;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.hibernate.Query;
/*    */ import org.hibernate.Session;
/*    */ 
/*    */ public class JobDaoImpl extends BaseDaoImpl<Job>
/*    */   implements JobDao
/*    */ {
/*    */   public JobDaoImpl()
/*    */   {
/* 28 */     super(Job.class);
/*    */   }
/*    */ 
/*    */   public List<Job> findByDep(Long jobId)
/*    */   {
/* 33 */     String hql = "from Job vo where vo.jobId=?";
/* 34 */     Object[] objs = { jobId };
/* 35 */     return findByHql(hql, objs);
/*    */   }
/*    */ 
/*    */   public List<Job> findByCondition(Long parentId)
/*    */   {
/* 43 */     StringBuffer sb = new StringBuffer(
/* 44 */       "select j from Job j where j.delFlag = 0 and j.parentId = ? ");
/* 45 */     ArrayList paramList = new ArrayList();
/* 46 */     paramList.add(parentId);
/* 47 */     return findByHql(sb.toString(), paramList.toArray());
/*    */   }
/*    */ 
/*    */   public void edit(Job job)
/*    */   {
/* 55 */     String hql = "update Job set jobName=?,memo=?,delFlag=? where jobId = ?";
/* 56 */     Query query = getSession().createQuery(hql);
/* 57 */     query.setString(0, job.getJobName());
/* 58 */     query.setString(1, job.getMemo());
/* 59 */     query.setShort(2, job.getDelFlag().shortValue());
/* 60 */     query.setLong(3, job.getJobId().longValue());
/* 61 */     this.logger.debug("JobDao中[修改岗位信息jobName,memo,delFlag]：" + hql);
/* 62 */     query.executeUpdate();
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.hrm.impl.JobDaoImpl
 * JD-Core Version:    0.6.0
 */