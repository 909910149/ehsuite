/*    */ package com.htsoft.oa.dao.hrm.impl;
/*    */ 
/*    */ import com.htsoft.core.dao.impl.BaseDaoImpl;
/*    */ import com.htsoft.core.web.paging.PagingBean;
/*    */ import com.htsoft.oa.dao.hrm.SalaryItemDao;
/*    */ import com.htsoft.oa.model.hrm.SalaryItem;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ 
/*    */ public class SalaryItemDaoImpl extends BaseDaoImpl<SalaryItem>
/*    */   implements SalaryItemDao
/*    */ {
/*    */   public SalaryItemDaoImpl()
/*    */   {
/* 20 */     super(SalaryItem.class);
/*    */   }
/*    */ 
/*    */   public List<SalaryItem> getAllExcludeId(String excludeIds, PagingBean pb)
/*    */   {
/* 25 */     String hql = "from SalaryItem ";
/* 26 */     if (StringUtils.isNotEmpty(excludeIds)) {
/* 27 */       hql = hql + "where salaryItemId not in(" + excludeIds + ")";
/*    */     }
/* 29 */     return findByHql(hql, null, pb);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.hrm.impl.SalaryItemDaoImpl
 * JD-Core Version:    0.6.0
 */