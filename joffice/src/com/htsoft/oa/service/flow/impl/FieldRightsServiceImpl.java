/*    */ package com.htsoft.oa.service.flow.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.flow.FieldRightsDao;
/*    */ import com.htsoft.oa.model.flow.FieldRights;
/*    */ import com.htsoft.oa.service.flow.FieldRightsService;
/*    */ import java.util.List;
/*    */ 
/*    */ public class FieldRightsServiceImpl extends BaseServiceImpl<FieldRights>
/*    */   implements FieldRightsService
/*    */ {
/*    */   private FieldRightsDao dao;
/*    */ 
/*    */   public FieldRightsServiceImpl(FieldRightsDao dao)
/*    */   {
/* 18 */     super(dao);
/* 19 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   public List<FieldRights> getByMappingFieldTaskName(Long mappingId, Long fieldId, String taskName)
/*    */   {
/* 25 */     return this.dao.getByMappingFieldTaskName(mappingId, fieldId, taskName);
/*    */   }
/*    */ 
/*    */   public List<FieldRights> getByMappingIdAndTaskName(Long mappingId, String taskName)
/*    */   {
/* 31 */     return this.dao.getByMappingIdAndTaskName(mappingId, taskName);
/*    */   }
/*    */ 
/*    */   public List<FieldRights> getByMappingId(Long mappingId)
/*    */   {
/* 36 */     return this.dao.getByMappingId(mappingId);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.flow.impl.FieldRightsServiceImpl
 * JD-Core Version:    0.6.0
 */