/*    */ package com.htsoft.oa.service.info.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.info.SectionDao;
/*    */ import com.htsoft.oa.model.info.Section;
/*    */ import com.htsoft.oa.service.info.SectionService;
/*    */ 
/*    */ public class SectionServiceImpl extends BaseServiceImpl<Section>
/*    */   implements SectionService
/*    */ {
/*    */   private SectionDao dao;
/*    */ 
/*    */   public SectionServiceImpl(SectionDao dao)
/*    */   {
/* 16 */     super(dao);
/* 17 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   public Integer getLastColumn()
/*    */   {
/* 22 */     return this.dao.getLastColumn();
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.info.impl.SectionServiceImpl
 * JD-Core Version:    0.6.0
 */