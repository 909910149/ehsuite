/*    */ package com.htsoft.oa.service.document.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.document.PaintTemplateDao;
/*    */ import com.htsoft.oa.model.document.PaintTemplate;
/*    */ import com.htsoft.oa.service.document.PaintTemplateService;
/*    */ 
/*    */ public class PaintTemplateServiceImpl extends BaseServiceImpl<PaintTemplate>
/*    */   implements PaintTemplateService
/*    */ {
/*    */   private PaintTemplateDao dao;
/*    */ 
/*    */   public PaintTemplateServiceImpl(PaintTemplateDao dao)
/*    */   {
/* 16 */     super(dao);
/* 17 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.document.impl.PaintTemplateServiceImpl
 * JD-Core Version:    0.6.0
 */