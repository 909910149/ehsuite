/*    */ package com.htsoft.oa.service.flow.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.flow.ProDefRightsDao;
/*    */ import com.htsoft.oa.model.flow.ProDefRights;
/*    */ import com.htsoft.oa.service.flow.ProDefRightsService;
/*    */ 
/*    */ public class ProDefRightsServiceImpl extends BaseServiceImpl<ProDefRights>
/*    */   implements ProDefRightsService
/*    */ {
/*    */   private ProDefRightsDao dao;
/*    */ 
/*    */   public ProDefRightsServiceImpl(ProDefRightsDao dao)
/*    */   {
/* 16 */     super(dao);
/* 17 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   public ProDefRights findByDefId(Long defId)
/*    */   {
/* 22 */     return this.dao.findByDefId(defId);
/*    */   }
/*    */ 
/*    */   public ProDefRights findByTypeId(Long proTypeId)
/*    */   {
/* 27 */     return this.dao.findByTypeId(proTypeId);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.flow.impl.ProDefRightsServiceImpl
 * JD-Core Version:    0.6.0
 */