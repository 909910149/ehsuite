/*    */ package com.htsoft.oa.service.hrm.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.hrm.HireIssueDao;
/*    */ import com.htsoft.oa.model.hrm.HireIssue;
/*    */ import com.htsoft.oa.service.hrm.HireIssueService;
/*    */ 
/*    */ public class HireIssueServiceImpl extends BaseServiceImpl<HireIssue>
/*    */   implements HireIssueService
/*    */ {
/*    */   private HireIssueDao dao;
/*    */ 
/*    */   public HireIssueServiceImpl(HireIssueDao dao)
/*    */   {
/* 15 */     super(dao);
/* 16 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.hrm.impl.HireIssueServiceImpl
 * JD-Core Version:    0.6.0
 */