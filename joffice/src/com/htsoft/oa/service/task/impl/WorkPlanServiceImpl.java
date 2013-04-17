/*    */ package com.htsoft.oa.service.task.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.core.util.ContextUtil;
/*    */ import com.htsoft.core.web.paging.PagingBean;
/*    */ import com.htsoft.oa.dao.task.WorkPlanDao;
/*    */ import com.htsoft.oa.model.info.ShortMessage;
/*    */ import com.htsoft.oa.model.system.AppUser;
/*    */ import com.htsoft.oa.model.task.WorkPlan;
/*    */ import com.htsoft.oa.service.info.ShortMessageService;
/*    */ import com.htsoft.oa.service.task.WorkPlanService;
/*    */ import java.util.List;
/*    */ import javax.annotation.Resource;
/*    */ import org.apache.commons.logging.Log;
/*    */ 
/*    */ public class WorkPlanServiceImpl extends BaseServiceImpl<WorkPlan>
/*    */   implements WorkPlanService
/*    */ {
/*    */   private WorkPlanDao dao;
/*    */ 
/*    */   @Resource
/*    */   private ShortMessageService shortMessageService;
/*    */ 
/*    */   public WorkPlanServiceImpl(WorkPlanDao dao)
/*    */   {
/* 27 */     super(dao);
/* 28 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   public List<WorkPlan> findByDepartment(WorkPlan workPlan, AppUser user, PagingBean pb)
/*    */   {
/* 33 */     return this.dao.findByDepartment(workPlan, user, pb);
/*    */   }
/*    */ 
/*    */   public void sendWorkPlanTime()
/*    */   {
/* 38 */     List<WorkPlan> list = this.dao.sendWorkPlanTime();
/* 39 */     if (list.size() > 0) {
/* 40 */       StringBuffer buff = new StringBuffer("工作计划时间");
/* 41 */       for (WorkPlan plan : list) {
/* 42 */         if (plan.getIsPersonal().shortValue() == 1)
/* 43 */           buff.append(plan.getPlanName() + "个人计划时间已经到" + plan.getEndTime() + "结束时间了.");
/*    */         else {
/* 45 */           buff.append(plan.getPlanName() + "部门计划时间已经到" + plan.getEndTime() + "结束时间了.");
/*    */         }
/*    */       }
/* 48 */       buff.append("请尽快完成您所定的计划!");
/* 49 */       AppUser user = ContextUtil.getCurrentUser();
/* 50 */       if (user != null) {
/* 51 */         this.shortMessageService.save(AppUser.SYSTEM_USER, user.getUserId().toString(), buff.toString(), ShortMessage.MSG_TYPE_SYS);
/* 52 */         this.logger.info("messages had sent to the manager!" + user.getUsername());
/*    */       } else {
/* 54 */         this.logger.info("can not find the user in the system.");
/*    */       }
/* 56 */       this.logger.info(buff.toString());
/*    */     } else {
/* 58 */       this.logger.info("没有任何计划.");
/*    */     }
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.task.impl.WorkPlanServiceImpl
 * JD-Core Version:    0.6.0
 */