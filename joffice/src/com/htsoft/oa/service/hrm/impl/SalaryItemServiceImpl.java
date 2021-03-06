/*    */ package com.htsoft.oa.service.hrm.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.core.web.paging.PagingBean;
/*    */ import com.htsoft.oa.dao.hrm.SalaryItemDao;
/*    */ import com.htsoft.oa.model.hrm.SalaryItem;
/*    */ import com.htsoft.oa.service.hrm.SalaryItemService;
/*    */ import java.util.List;
/*    */ 
/*    */ public class SalaryItemServiceImpl extends BaseServiceImpl<SalaryItem>
/*    */   implements SalaryItemService
/*    */ {
/*    */   private SalaryItemDao dao;
/*    */ 
/*    */   public SalaryItemServiceImpl(SalaryItemDao dao)
/*    */   {
/* 18 */     super(dao);
/* 19 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   public List<SalaryItem> getAllExcludeId(String excludeIds, PagingBean pb)
/*    */   {
/* 24 */     return this.dao.getAllExcludeId(excludeIds, pb);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.hrm.impl.SalaryItemServiceImpl
 * JD-Core Version:    0.6.0
 */