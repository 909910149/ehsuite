/*    */ package com.htsoft.oa.service.flow.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.flow.ProTypeDao;
/*    */ import com.htsoft.oa.model.flow.ProType;
/*    */ import com.htsoft.oa.service.flow.ProTypeService;
/*    */ 
/*    */ public class ProTypeServiceImpl extends BaseServiceImpl<ProType>
/*    */   implements ProTypeService
/*    */ {
/*    */   private ProTypeDao dao;
/*    */ 
/*    */   public ProTypeServiceImpl(ProTypeDao dao)
/*    */   {
/* 15 */     super(dao);
/* 16 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.flow.impl.ProTypeServiceImpl
 * JD-Core Version:    0.6.0
 */