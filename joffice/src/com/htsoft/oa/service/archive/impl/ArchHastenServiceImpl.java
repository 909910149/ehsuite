/*    */ package com.htsoft.oa.service.archive.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.archive.ArchHastenDao;
/*    */ import com.htsoft.oa.model.archive.ArchHasten;
/*    */ import com.htsoft.oa.service.archive.ArchHastenService;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class ArchHastenServiceImpl extends BaseServiceImpl<ArchHasten>
/*    */   implements ArchHastenService
/*    */ {
/*    */   private ArchHastenDao dao;
/*    */ 
/*    */   public ArchHastenServiceImpl(ArchHastenDao dao)
/*    */   {
/* 17 */     super(dao);
/* 18 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   public Date getLeastRecordByUser(Long archivesId)
/*    */   {
/* 23 */     return this.dao.getLeastRecordByUser(archivesId);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.archive.impl.ArchHastenServiceImpl
 * JD-Core Version:    0.6.0
 */