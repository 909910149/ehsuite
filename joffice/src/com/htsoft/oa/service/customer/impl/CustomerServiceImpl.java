/*    */ package com.htsoft.oa.service.customer.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.customer.CustomerDao;
/*    */ import com.htsoft.oa.model.customer.Customer;
/*    */ import com.htsoft.oa.service.customer.CustomerService;
/*    */ 
/*    */ public class CustomerServiceImpl extends BaseServiceImpl<Customer>
/*    */   implements CustomerService
/*    */ {
/*    */   private CustomerDao dao;
/*    */ 
/*    */   public CustomerServiceImpl(CustomerDao dao)
/*    */   {
/* 15 */     super(dao);
/* 16 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   public boolean checkCustomerNo(String customerNo)
/*    */   {
/* 21 */     return this.dao.checkCustomerNo(customerNo);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.customer.impl.CustomerServiceImpl
 * JD-Core Version:    0.6.0
 */