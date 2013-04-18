/*    */ package com.htsoft.oa.dao.system.impl;
/*    */ 
/*    */ import com.htsoft.core.dao.impl.BaseDaoImpl;
/*    */ import com.htsoft.oa.dao.system.CompanyDao;
/*    */ import com.htsoft.oa.model.system.Company;
/*    */ import java.util.List;
/*    */ 
/*    */ public class CompanyDaoImpl extends BaseDaoImpl<Company>
/*    */   implements CompanyDao
/*    */ {
/*    */   public CompanyDaoImpl()
/*    */   {
/* 15 */     super(Company.class);
/*    */   }
/*    */ 
/*    */   public List<Company> findCompany() {
/* 19 */     String hql = "from Company c";
/* 20 */     return findByHql(hql);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.system.impl.CompanyDaoImpl
 * JD-Core Version:    0.6.0
 */