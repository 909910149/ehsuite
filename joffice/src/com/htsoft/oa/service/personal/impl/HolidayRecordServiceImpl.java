/*    */ package com.htsoft.oa.service.personal.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.personal.HolidayRecordDao;
/*    */ import com.htsoft.oa.model.personal.HolidayRecord;
/*    */ import com.htsoft.oa.service.personal.HolidayRecordService;
/*    */ 
/*    */ public class HolidayRecordServiceImpl extends BaseServiceImpl<HolidayRecord>
/*    */   implements HolidayRecordService
/*    */ {
/*    */   private HolidayRecordDao dao;
/*    */ 
/*    */   public HolidayRecordServiceImpl(HolidayRecordDao dao)
/*    */   {
/* 15 */     super(dao);
/* 16 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.personal.impl.HolidayRecordServiceImpl
 * JD-Core Version:    0.6.0
 */