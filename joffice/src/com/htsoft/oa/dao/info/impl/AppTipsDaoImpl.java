/*    */ package com.htsoft.oa.dao.info.impl;
/*    */ 
/*    */ import com.htsoft.core.dao.impl.BaseDaoImpl;
/*    */ import com.htsoft.oa.dao.info.AppTipsDao;
/*    */ import com.htsoft.oa.model.info.AppTips;
/*    */ import java.util.List;
/*    */ 
/*    */ public class AppTipsDaoImpl extends BaseDaoImpl<AppTips>
/*    */   implements AppTipsDao
/*    */ {
/*    */   public AppTipsDaoImpl()
/*    */   {
/* 15 */     super(AppTips.class);
/*    */   }
/*    */ 
/*    */   public List<AppTips> findByName(String name)
/*    */   {
/* 20 */     String hql = "from AppTips vo where vo.tipsName=?";
/* 21 */     return findByHql(hql, new Object[] { name });
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.info.impl.AppTipsDaoImpl
 * JD-Core Version:    0.6.0
 */