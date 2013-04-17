/*    */ package com.htsoft.oa.service.customer.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.customer.ContractConfigDao;
/*    */ import com.htsoft.oa.model.customer.ContractConfig;
/*    */ import com.htsoft.oa.service.customer.ContractConfigService;
/*    */ 
/*    */ public class ContractConfigServiceImpl extends BaseServiceImpl<ContractConfig>
/*    */   implements ContractConfigService
/*    */ {
/*    */   private ContractConfigDao dao;
/*    */ 
/*    */   public ContractConfigServiceImpl(ContractConfigDao dao)
/*    */   {
/* 15 */     super(dao);
/* 16 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.customer.impl.ContractConfigServiceImpl
 * JD-Core Version:    0.6.0
 */