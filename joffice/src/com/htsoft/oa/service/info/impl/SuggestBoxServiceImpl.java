/*    */ package com.htsoft.oa.service.info.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.info.SuggestBoxDao;
/*    */ import com.htsoft.oa.model.info.SuggestBox;
/*    */ import com.htsoft.oa.service.info.SuggestBoxService;
/*    */ 
/*    */ public class SuggestBoxServiceImpl extends BaseServiceImpl<SuggestBox>
/*    */   implements SuggestBoxService
/*    */ {
/*    */   private SuggestBoxDao dao;
/*    */ 
/*    */   public SuggestBoxServiceImpl(SuggestBoxDao dao)
/*    */   {
/* 15 */     super(dao);
/* 16 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.info.impl.SuggestBoxServiceImpl
 * JD-Core Version:    0.6.0
 */