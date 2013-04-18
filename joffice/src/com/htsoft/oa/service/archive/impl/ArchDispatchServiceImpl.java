/*    */ package com.htsoft.oa.service.archive.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.core.web.paging.PagingBean;
/*    */ import com.htsoft.oa.dao.archive.ArchDispatchDao;
/*    */ import com.htsoft.oa.model.archive.ArchDispatch;
/*    */ import com.htsoft.oa.model.system.AppUser;
/*    */ import com.htsoft.oa.service.archive.ArchDispatchService;
/*    */ import java.util.List;
/*    */ 
/*    */ public class ArchDispatchServiceImpl extends BaseServiceImpl<ArchDispatch>
/*    */   implements ArchDispatchService
/*    */ {
/*    */   private ArchDispatchDao dao;
/*    */ 
/*    */   public ArchDispatchServiceImpl(ArchDispatchDao dao)
/*    */   {
/* 19 */     super(dao);
/* 20 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   public List<ArchDispatch> findByUser(AppUser user, PagingBean pb)
/*    */   {
/* 25 */     return this.dao.findByUser(user, pb);
/*    */   }
/*    */ 
/*    */   public int countArchDispatch(Long archivesId)
/*    */   {
/* 30 */     return this.dao.findRecordByArc(archivesId).size();
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.archive.impl.ArchDispatchServiceImpl
 * JD-Core Version:    0.6.0
 */