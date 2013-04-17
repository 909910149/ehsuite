/*    */ package com.htsoft.oa.service.communicate.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.communicate.OutMailDao;
/*    */ import com.htsoft.oa.model.communicate.OutMail;
/*    */ import com.htsoft.oa.service.communicate.OutMailService;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class OutMailServiceImpl extends BaseServiceImpl<OutMail>
/*    */   implements OutMailService
/*    */ {
/*    */   private OutMailDao dao;
/*    */ 
/*    */   public OutMailServiceImpl(OutMailDao dao)
/*    */   {
/* 18 */     super(dao);
/* 19 */     this.dao = dao;
/*    */   }
/*    */   public List<OutMail> findByFolderId(Long folderId) {
/* 22 */     return this.dao.findByFolderId(folderId);
/*    */   }
/*    */ 
/*    */   public Long CountByFolderId(Long folderId) {
/* 26 */     return this.dao.CountByFolderId(folderId);
/*    */   }
/*    */   public Map getUidByUserId(Long userId) {
/* 29 */     return this.dao.getUidByUserId(userId);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.communicate.impl.OutMailServiceImpl
 * JD-Core Version:    0.6.0
 */