/*    */ package com.htsoft.oa.dao.admin.impl;
/*    */ 
/*    */ import com.htsoft.core.dao.impl.BaseDaoImpl;
/*    */ import com.htsoft.oa.dao.admin.OfficeGoodsDao;
/*    */ import com.htsoft.oa.model.admin.OfficeGoods;
/*    */ import java.util.List;
/*    */ 
/*    */ public class OfficeGoodsDaoImpl extends BaseDaoImpl<OfficeGoods>
/*    */   implements OfficeGoodsDao
/*    */ {
/*    */   public OfficeGoodsDaoImpl()
/*    */   {
/* 15 */     super(OfficeGoods.class);
/*    */   }
/*    */ 
/*    */   public List<OfficeGoods> findByWarm()
/*    */   {
/* 20 */     String hql = "from OfficeGoods vo where ((vo.stockCounts<=vo.warnCounts and vo.isWarning=1) or (vo.stockCounts<=0))";
/* 21 */     return findByHql(hql);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.admin.impl.OfficeGoodsDaoImpl
 * JD-Core Version:    0.6.0
 */