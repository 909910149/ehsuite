/*    */ package com.htsoft.oa.service.task.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.core.web.paging.PagingBean;
/*    */ import com.htsoft.oa.dao.task.AppointmentDao;
/*    */ import com.htsoft.oa.model.task.Appointment;
/*    */ import com.htsoft.oa.service.task.AppointmentService;
/*    */ import java.util.List;
/*    */ 
/*    */ public class AppointmentServiceImpl extends BaseServiceImpl<Appointment>
/*    */   implements AppointmentService
/*    */ {
/*    */   private AppointmentDao dao;
/*    */ 
/*    */   public AppointmentServiceImpl(AppointmentDao dao)
/*    */   {
/* 18 */     super(dao);
/* 19 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   public List showAppointmentByUserId(Long userId, PagingBean pb)
/*    */   {
/* 25 */     return this.dao.showAppointmentByUserId(userId, pb);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.task.impl.AppointmentServiceImpl
 * JD-Core Version:    0.6.0
 */