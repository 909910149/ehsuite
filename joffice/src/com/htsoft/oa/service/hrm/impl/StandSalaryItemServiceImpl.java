/*    */ package com.htsoft.oa.service.hrm.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.hrm.StandSalaryItemDao;
/*    */ import com.htsoft.oa.model.hrm.StandSalaryItem;
/*    */ import com.htsoft.oa.service.hrm.StandSalaryItemService;
/*    */ import java.util.List;
/*    */ 
/*    */ public class StandSalaryItemServiceImpl extends BaseServiceImpl<StandSalaryItem>
/*    */   implements StandSalaryItemService
/*    */ {
/*    */   private StandSalaryItemDao dao;
/*    */ 
/*    */   public StandSalaryItemServiceImpl(StandSalaryItemDao dao)
/*    */   {
/* 17 */     super(dao);
/* 18 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   public List<StandSalaryItem> getAllByStandardId(Long standardId)
/*    */   {
/* 23 */     return this.dao.getAllByStandardId(standardId);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.hrm.impl.StandSalaryItemServiceImpl
 * JD-Core Version:    0.6.0
 */