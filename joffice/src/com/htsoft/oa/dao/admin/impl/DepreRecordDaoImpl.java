/*    */ package com.htsoft.oa.dao.admin.impl;
/*    */ 
/*    */ import com.htsoft.core.dao.impl.BaseDaoImpl;
/*    */ import com.htsoft.oa.dao.admin.DepreRecordDao;
/*    */ import com.htsoft.oa.model.admin.DepreRecord;
/*    */ import java.util.Date;
/*    */ import java.util.List;
/*    */ import org.hibernate.Query;
/*    */ import org.hibernate.Session;
/*    */ 
/*    */ public class DepreRecordDaoImpl extends BaseDaoImpl<DepreRecord>
/*    */   implements DepreRecordDao
/*    */ {
/*    */   public DepreRecordDaoImpl()
/*    */   {
/* 19 */     super(DepreRecord.class);
/*    */   }
/*    */ 
/*    */   public Date findMaxDate(Long assetsId)
/*    */   {
/* 24 */     String hql = "select max(vo.calTime) from DepreRecord vo where vo.fixedAssets.assetsId=?";
/* 25 */     Query query = getSession().createQuery(hql);
/* 26 */     query.setLong(0, assetsId.longValue());
/* 27 */     Date date = (Date)query.list().get(0);
/* 28 */     return date;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.admin.impl.DepreRecordDaoImpl
 * JD-Core Version:    0.6.0
 */