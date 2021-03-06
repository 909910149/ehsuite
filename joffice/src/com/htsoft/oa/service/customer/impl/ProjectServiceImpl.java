/*    */ package com.htsoft.oa.service.customer.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.customer.ProjectDao;
/*    */ import com.htsoft.oa.model.customer.Project;
/*    */ import com.htsoft.oa.service.customer.ProjectService;
/*    */ 
/*    */ public class ProjectServiceImpl extends BaseServiceImpl<Project>
/*    */   implements ProjectService
/*    */ {
/*    */   private ProjectDao dao;
/*    */ 
/*    */   public ProjectServiceImpl(ProjectDao dao)
/*    */   {
/* 15 */     super(dao);
/* 16 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   public boolean checkProjectNo(String projectNo)
/*    */   {
/* 21 */     return this.dao.checkProjectNo(projectNo);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.customer.impl.ProjectServiceImpl
 * JD-Core Version:    0.6.0
 */