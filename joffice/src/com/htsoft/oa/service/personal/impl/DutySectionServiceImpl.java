/*    */ package com.htsoft.oa.service.personal.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.personal.DutySectionDao;
/*    */ import com.htsoft.oa.model.personal.DutySection;
/*    */ import com.htsoft.oa.service.personal.DutySectionService;
/*    */ 
/*    */ public class DutySectionServiceImpl extends BaseServiceImpl<DutySection>
/*    */   implements DutySectionService
/*    */ {
/*    */   private DutySectionDao dao;
/*    */ 
/*    */   public DutySectionServiceImpl(DutySectionDao dao)
/*    */   {
/* 15 */     super(dao);
/* 16 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.personal.impl.DutySectionServiceImpl
 * JD-Core Version:    0.6.0
 */