/*    */ package com.htsoft.oa.service.admin.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.admin.DepreRecordDao;
/*    */ import com.htsoft.oa.model.admin.DepreRecord;
/*    */ import com.htsoft.oa.service.admin.DepreRecordService;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class DepreRecordServiceImpl extends BaseServiceImpl<DepreRecord>
/*    */   implements DepreRecordService
/*    */ {
/*    */   private DepreRecordDao dao;
/*    */ 
/*    */   public DepreRecordServiceImpl(DepreRecordDao dao)
/*    */   {
/* 17 */     super(dao);
/* 18 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   public Date findMaxDate(Long assetsId)
/*    */   {
/* 23 */     return this.dao.findMaxDate(assetsId);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.admin.impl.DepreRecordServiceImpl
 * JD-Core Version:    0.6.0
 */