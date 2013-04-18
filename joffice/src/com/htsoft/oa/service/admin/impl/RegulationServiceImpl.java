/*    */ package com.htsoft.oa.service.admin.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.admin.RegulationDao;
/*    */ import com.htsoft.oa.model.admin.Regulation;
/*    */ import com.htsoft.oa.service.admin.RegulationService;
/*    */ 
/*    */ public class RegulationServiceImpl extends BaseServiceImpl<Regulation>
/*    */   implements RegulationService
/*    */ {
/*    */   private RegulationDao dao;
/*    */ 
/*    */   public RegulationServiceImpl(RegulationDao dao)
/*    */   {
/* 16 */     super(dao);
/* 17 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.admin.impl.RegulationServiceImpl
 * JD-Core Version:    0.6.0
 */