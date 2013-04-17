/*    */ package com.htsoft.oa.dao.task.impl;
/*    */ 
/*    */ import com.htsoft.core.dao.impl.BaseDaoImpl;
/*    */ import com.htsoft.core.web.paging.PagingBean;
/*    */ import com.htsoft.oa.dao.task.WorkPlanDao;
/*    */ import com.htsoft.oa.model.system.AppUser;
/*    */ import com.htsoft.oa.model.system.Department;
/*    */ import com.htsoft.oa.model.task.WorkPlan;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Date;
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ 
/*    */ public class WorkPlanDaoImpl extends BaseDaoImpl<WorkPlan>
/*    */   implements WorkPlanDao
/*    */ {
/*    */   public WorkPlanDaoImpl()
/*    */   {
/* 25 */     super(WorkPlan.class);
/*    */   }
/*    */ 
/*    */   public List<WorkPlan> findByDepartment(WorkPlan workPlan, AppUser user, PagingBean pb)
/*    */   {
/* 30 */     StringBuffer sb = new StringBuffer();
/* 31 */     ArrayList list = new ArrayList();
/* 32 */     if (!user.getRights().contains("__ALL")) {
/* 33 */       sb.append("select distinct wp from WorkPlan wp,PlanAttend pa where pa.workPlan=wp and wp.status=1 and wp.isPersonal=0 and ((pa.appUser.userId=? and pa.isDep=0)");
/* 34 */       Department dep = user.getDepartment();
/* 35 */       list.add(user.getUserId());
/* 36 */       if (dep != null) {
/* 37 */         String path = dep.getPath();
/* 38 */         if (StringUtils.isNotEmpty(path)) {
/* 39 */           StringBuffer buff = new StringBuffer(path.replace(".", ","));
/* 40 */           buff.deleteCharAt(buff.length() - 1);
/* 41 */           sb.append(" or (pa.department.depId in (" + buff.toString() + ") and pa.isDep=1)");
/*    */         }
/*    */       }
/* 44 */       sb.append(")");
/*    */     } else {
/* 46 */       sb.append("select wp from WorkPlan wp where wp.status=1 and wp.isPersonal=0");
/*    */     }
/* 48 */     if (workPlan != null) {
/* 49 */       if (StringUtils.isNotEmpty(workPlan.getPlanName())) {
/* 50 */         sb.append(" and wp.planName like ?");
/* 51 */         list.add("%" + workPlan.getPlanName() + "%");
/*    */       }
/* 53 */       if (StringUtils.isNotEmpty(workPlan.getPrincipal())) {
/* 54 */         sb.append(" and wp.principal like ?");
/* 55 */         list.add("%" + workPlan.getPrincipal() + "%");
/*    */       }
/* 57 */       if (workPlan.getTypeName() != null) {
/* 58 */         sb.append(" and wp.typeName like ?");
/* 59 */         list.add("%" + workPlan.getTypeName() + "%");
/*    */       }
/*    */ 
/*    */     }
/*    */ 
/* 64 */     return findByHql(sb.toString(), list.toArray(), pb);
/*    */   }
/*    */ 
/*    */   public List<WorkPlan> sendWorkPlanTime()
/*    */   {
/* 69 */     String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
/* 70 */     String hql = "select wp from WorkPlan wp where wp.isPersonal=1 and  wp.endTime <=" + dateTime;
/* 71 */     return findByHql(hql);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.task.impl.WorkPlanDaoImpl
 * JD-Core Version:    0.6.0
 */