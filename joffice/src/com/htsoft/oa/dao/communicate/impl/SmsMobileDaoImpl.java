/*    */ package com.htsoft.oa.dao.communicate.impl;
/*    */ 
/*    */ import com.htsoft.core.dao.impl.BaseDaoImpl;
/*    */ import com.htsoft.oa.dao.communicate.SmsMobileDao;
/*    */ import com.htsoft.oa.model.communicate.SmsMobile;
/*    */ import java.util.List;
/*    */ 
/*    */ public class SmsMobileDaoImpl extends BaseDaoImpl<SmsMobile>
/*    */   implements SmsMobileDao
/*    */ {
/*    */   public SmsMobileDaoImpl()
/*    */   {
/* 15 */     super(SmsMobile.class);
/*    */   }
/*    */ 
/*    */   public List<SmsMobile> getNeedToSend()
/*    */   {
/* 20 */     String hql = "from SmsMobile sm where sm.status = ? order by sm.sendTime desc";
/* 21 */     Object[] params = { SmsMobile.STATUS_NOT_SENDED };
/* 22 */     return findByHql(hql, params);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.communicate.impl.SmsMobileDaoImpl
 * JD-Core Version:    0.6.0
 */