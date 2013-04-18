/*    */ package com.htsoft.oa.service.customer.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.customer.ContractDao;
/*    */ import com.htsoft.oa.model.customer.Contract;
/*    */ import com.htsoft.oa.service.customer.ContractService;
/*    */ 
/*    */ public class ContractServiceImpl extends BaseServiceImpl<Contract>
/*    */   implements ContractService
/*    */ {
/*    */   private ContractDao dao;
/*    */ 
/*    */   public ContractServiceImpl(ContractDao dao)
/*    */   {
/* 15 */     super(dao);
/* 16 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.customer.impl.ContractServiceImpl
 * JD-Core Version:    0.6.0
 */