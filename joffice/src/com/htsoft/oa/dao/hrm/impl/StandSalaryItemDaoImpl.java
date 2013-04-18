/*    */ package com.htsoft.oa.dao.hrm.impl;
/*    */ 
/*    */ import com.htsoft.core.dao.impl.BaseDaoImpl;
/*    */ import com.htsoft.oa.dao.hrm.StandSalaryItemDao;
/*    */ import com.htsoft.oa.model.hrm.StandSalaryItem;
/*    */ import java.util.List;
/*    */ 
/*    */ public class StandSalaryItemDaoImpl extends BaseDaoImpl<StandSalaryItem>
/*    */   implements StandSalaryItemDao
/*    */ {
/*    */   public StandSalaryItemDaoImpl()
/*    */   {
/* 15 */     super(StandSalaryItem.class);
/*    */   }
/*    */ 
/*    */   public List<StandSalaryItem> getAllByStandardId(Long standardId)
/*    */   {
/* 20 */     String hql = "from StandSalaryItem ssi where ssi.standSalary.standardId=?";
/* 21 */     return findByHql(hql, new Object[] { standardId });
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.hrm.impl.StandSalaryItemDaoImpl
 * JD-Core Version:    0.6.0
 */