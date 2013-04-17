/*    */ package com.htsoft.oa.service.communicate.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.communicate.OutMailFolderDao;
/*    */ import com.htsoft.oa.model.communicate.OutMailFolder;
/*    */ import com.htsoft.oa.service.communicate.OutMailFolderService;
/*    */ import java.util.List;
/*    */ 
/*    */ public class OutMailFolderServiceImpl extends BaseServiceImpl<OutMailFolder>
/*    */   implements OutMailFolderService
/*    */ {
/*    */   private OutMailFolderDao dao;
/*    */ 
/*    */   public OutMailFolderServiceImpl(OutMailFolderDao dao)
/*    */   {
/* 18 */     super(dao);
/* 19 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   public List<OutMailFolder> getAllUserFolderByParentId(Long userId, Long parentId)
/*    */   {
/* 24 */     return this.dao.getAllUserFolderByParentId(userId, parentId);
/*    */   }
/*    */ 
/*    */   public List<OutMailFolder> getUserFolderByParentId(Long userId, Long parentId) {
/* 28 */     return this.dao.getUserFolderByParentId(userId, parentId);
/*    */   }
/*    */ 
/*    */   public List<OutMailFolder> getFolderLikePath(String path) {
/* 32 */     return this.dao.getFolderLikePath(path);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.communicate.impl.OutMailFolderServiceImpl
 * JD-Core Version:    0.6.0
 */