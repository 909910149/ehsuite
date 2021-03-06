/*    */ package com.htsoft.test.customer;
/*    */ 
/*    */ import com.htsoft.oa.dao.customer.CustomerDao;
/*    */ import com.htsoft.oa.model.customer.Customer;
/*    */ import com.htsoft.test.BaseTestCase;
/*    */ import javax.annotation.Resource;
/*    */ import org.junit.Test;
/*    */ import org.springframework.test.annotation.Rollback;
/*    */ 
/*    */ public class CustomerDaoTestCase extends BaseTestCase
/*    */ {
/*    */ 
/*    */   @Resource
/*    */   private CustomerDao customerDao;
/*    */ 
/*    */   @Test
/*    */   @Rollback(false)
/*    */   public void add()
/*    */   {
/* 19 */     Customer customer = new Customer();
/* 20 */     customer.setCustomerName("Customer1");
/* 21 */     this.customerDao.save(customer);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.customer.CustomerDaoTestCase
 * JD-Core Version:    0.6.0
 */