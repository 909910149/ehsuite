/*    */ package com.htsoft.oa.service.admin.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.admin.CarDao;
/*    */ import com.htsoft.oa.model.admin.Car;
/*    */ import com.htsoft.oa.service.admin.CarService;
/*    */ 
/*    */ public class CarServiceImpl extends BaseServiceImpl<Car>
/*    */   implements CarService
/*    */ {
/*    */   private CarDao dao;
/*    */ 
/*    */   public CarServiceImpl(CarDao dao)
/*    */   {
/* 15 */     super(dao);
/* 16 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.admin.impl.CarServiceImpl
 * JD-Core Version:    0.6.0
 */