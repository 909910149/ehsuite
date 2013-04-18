/*    */ package com.htsoft.oa.service.admin.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.admin.CarApplyDao;
/*    */ import com.htsoft.oa.model.admin.CarApply;
/*    */ import com.htsoft.oa.service.admin.CarApplyService;
/*    */ 
/*    */ public class CarApplyServiceImpl extends BaseServiceImpl<CarApply>
/*    */   implements CarApplyService
/*    */ {
/*    */   private CarApplyDao dao;
/*    */ 
/*    */   public CarApplyServiceImpl(CarApplyDao dao)
/*    */   {
/* 15 */     super(dao);
/* 16 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.admin.impl.CarApplyServiceImpl
 * JD-Core Version:    0.6.0
 */