/*    */ package com.htsoft.oa.service.task.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.task.PlanTypeDao;
/*    */ import com.htsoft.oa.model.task.PlanType;
/*    */ import com.htsoft.oa.service.task.PlanTypeService;
/*    */ 
/*    */ public class PlanTypeServiceImpl extends BaseServiceImpl<PlanType>
/*    */   implements PlanTypeService
/*    */ {
/*    */   private PlanTypeDao dao;
/*    */ 
/*    */   public PlanTypeServiceImpl(PlanTypeDao dao)
/*    */   {
/* 15 */     super(dao);
/* 16 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.task.impl.PlanTypeServiceImpl
 * JD-Core Version:    0.6.0
 */