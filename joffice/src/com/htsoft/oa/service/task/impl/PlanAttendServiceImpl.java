/*    */ package com.htsoft.oa.service.task.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.task.PlanAttendDao;
/*    */ import com.htsoft.oa.model.task.PlanAttend;
/*    */ import com.htsoft.oa.service.task.PlanAttendService;
/*    */ import java.util.List;
/*    */ 
/*    */ public class PlanAttendServiceImpl extends BaseServiceImpl<PlanAttend>
/*    */   implements PlanAttendService
/*    */ {
/*    */   private PlanAttendDao dao;
/*    */ 
/*    */   public PlanAttendServiceImpl(PlanAttendDao dao)
/*    */   {
/* 17 */     super(dao);
/* 18 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   public boolean deletePlanAttend(Long planId, Short isDep, Short isPrimary)
/*    */   {
/* 23 */     List<PlanAttend> list = this.dao.FindPlanAttend(planId, isDep, isPrimary);
/* 24 */     for (PlanAttend pa : list) {
/* 25 */       this.dao.remove(pa.getAttendId());
/*    */     }
/* 27 */     return true;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.task.impl.PlanAttendServiceImpl
 * JD-Core Version:    0.6.0
 */