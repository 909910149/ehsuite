/*    */ package com.htsoft.oa.dao.flow.impl;
/*    */ 
/*    */ import com.htsoft.core.dao.impl.BaseDaoImpl;
/*    */ import com.htsoft.core.web.paging.PagingBean;
/*    */ import com.htsoft.oa.dao.flow.ProcessRunDao;
/*    */ import com.htsoft.oa.model.flow.ProcessRun;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ 
/*    */ public class ProcessRunDaoImpl extends BaseDaoImpl<ProcessRun>
/*    */   implements ProcessRunDao
/*    */ {
/*    */   public ProcessRunDaoImpl()
/*    */   {
/* 31 */     super(ProcessRun.class);
/*    */   }
/*    */ 
/*    */   public ProcessRun getByPiId(String piId)
/*    */   {
/* 41 */     String hql = "from ProcessRun pr where pr.piId=?";
/* 42 */     return (ProcessRun)findUnique(hql, new Object[] { piId });
/*    */   }
/*    */ 
/*    */   public List<ProcessRun> getByDefId(Long defId, PagingBean pb)
/*    */   {
/* 53 */     String hql = " from ProcessRun pr where pr.proDefinition.defId=? ";
/* 54 */     return findByHql(hql, new Object[] { defId }, pb);
/*    */   }
/*    */ 
/*    */   public List<ProcessRun> getByUserIdSubject(Long userId, String subject, PagingBean pb)
/*    */   {
/* 68 */     ArrayList params = new ArrayList();
/* 69 */     String hql = "select pr from ProcessRun as pr join pr.processForms as pf where pf.creatorId=? group by pr.runId order by pr.createtime desc";
/* 70 */     params.add(userId);
/* 71 */     if (StringUtils.isNotEmpty(subject)) {
/* 72 */       hql = hql + " and pr.subject like ?";
/* 73 */       params.add("%" + subject + "%");
/*    */     }
/*    */ 
/* 76 */     return findByHql(hql, params.toArray(), pb);
/*    */   }
/*    */ 
/*    */   public boolean checkRun(Long defId)
/*    */   {
/* 84 */     String hql = "select r from ProcessRun r where r.proDefinition.defId = ?";
/* 85 */     Object[] paramList = { defId };
/* 86 */     List list = findByHql(hql, paramList);
/* 87 */     return (list != null) && (list.size() > 0);
/*    */   }
/*    */ 
/*    */   public List<ProcessRun> getProcessRunning(Long defId)
/*    */   {
/* 92 */     String hql = "from ProcessRun r where r.proDefinition.defId = ? and r.runStatus=1";
/* 93 */     Object[] paramList = { defId };
/* 94 */     return findByHql(hql, paramList);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.flow.impl.ProcessRunDaoImpl
 * JD-Core Version:    0.6.0
 */