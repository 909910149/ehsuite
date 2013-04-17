/*    */ package com.htsoft.oa.service.info.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.info.AppTipsDao;
/*    */ import com.htsoft.oa.model.info.AppTips;
/*    */ import com.htsoft.oa.service.info.AppTipsService;
/*    */ import java.util.List;
/*    */ 
/*    */ public class AppTipsServiceImpl extends BaseServiceImpl<AppTips>
/*    */   implements AppTipsService
/*    */ {
/*    */   private AppTipsDao dao;
/*    */ 
/*    */   public AppTipsServiceImpl(AppTipsDao dao)
/*    */   {
/* 17 */     super(dao);
/* 18 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   public AppTips findByName(String name)
/*    */   {
/* 23 */     List list = this.dao.findByName(name);
/* 24 */     if (list.size() > 0) {
/* 25 */       return (AppTips)list.get(0);
/*    */     }
/* 27 */     return null;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.info.impl.AppTipsServiceImpl
 * JD-Core Version:    0.6.0
 */