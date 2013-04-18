/*    */ package com.htsoft.oa.service.flow.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.flow.FormDefMappingDao;
/*    */ import com.htsoft.oa.model.flow.FormDefMapping;
/*    */ import com.htsoft.oa.service.flow.FormDefMappingService;
/*    */ import java.util.List;
/*    */ 
/*    */ public class FormDefMappingServiceImpl extends BaseServiceImpl<FormDefMapping>
/*    */   implements FormDefMappingService
/*    */ {
/*    */   private FormDefMappingDao dao;
/*    */ 
/*    */   public FormDefMappingServiceImpl(FormDefMappingDao dao)
/*    */   {
/* 20 */     super(dao);
/* 21 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   public FormDefMapping getByDeployId(String deployId)
/*    */   {
/* 31 */     return this.dao.getByDeployId(deployId);
/*    */   }
/*    */ 
/*    */   public FormDefMapping findByDefId(Long defId)
/*    */   {
/* 37 */     return this.dao.findByDefId(defId);
/*    */   }
/*    */ 
/*    */   public boolean formDefHadMapping(Long formDefId)
/*    */   {
/* 42 */     List mps = this.dao.getByFormDef(formDefId);
/*    */ 
/* 44 */     return mps.size() > 0;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.flow.impl.FormDefMappingServiceImpl
 * JD-Core Version:    0.6.0
 */