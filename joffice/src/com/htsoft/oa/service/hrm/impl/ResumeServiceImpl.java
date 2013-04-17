/*    */ package com.htsoft.oa.service.hrm.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.hrm.ResumeDao;
/*    */ import com.htsoft.oa.model.hrm.Resume;
/*    */ import com.htsoft.oa.service.hrm.ResumeService;
/*    */ 
/*    */ public class ResumeServiceImpl extends BaseServiceImpl<Resume>
/*    */   implements ResumeService
/*    */ {
/*    */   private ResumeDao dao;
/*    */ 
/*    */   public ResumeServiceImpl(ResumeDao dao)
/*    */   {
/* 15 */     super(dao);
/* 16 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.hrm.impl.ResumeServiceImpl
 * JD-Core Version:    0.6.0
 */