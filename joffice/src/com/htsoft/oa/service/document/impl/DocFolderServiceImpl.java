/*    */ package com.htsoft.oa.service.document.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.document.DocFolderDao;
/*    */ import com.htsoft.oa.model.document.DocFolder;
/*    */ import com.htsoft.oa.service.document.DocFolderService;
/*    */ import java.util.List;
/*    */ 
/*    */ public class DocFolderServiceImpl extends BaseServiceImpl<DocFolder>
/*    */   implements DocFolderService
/*    */ {
/*    */   private DocFolderDao dao;
/*    */ 
/*    */   public DocFolderServiceImpl(DocFolderDao dao)
/*    */   {
/* 17 */     super(dao);
/* 18 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   public List<DocFolder> getUserFolderByParentId(Long userId, Long parentId) {
/* 22 */     return this.dao.getUserFolderByParentId(userId, parentId);
/*    */   }
/*    */ 
/*    */   public List<DocFolder> getFolderLikePath(String path)
/*    */   {
/* 31 */     return this.dao.getFolderLikePath(path);
/*    */   }
/*    */ 
/*    */   public List<DocFolder> getPublicFolderByParentId(Long parentId)
/*    */   {
/* 36 */     return this.dao.getPublicFolderByParentId(parentId);
/*    */   }
/*    */ 
/*    */   public List<DocFolder> findByParentId(Long parentId)
/*    */   {
/* 42 */     return this.dao.findByParentId(parentId);
/*    */   }
/*    */ 
/*    */   public List<DocFolder> findByUserAndName(Long userId, String foleName)
/*    */   {
/* 47 */     return this.dao.findByUserAndName(userId, foleName);
/*    */   }
/*    */ 
/*    */   public List<DocFolder> getOnlineFolderByParentId(Long parentId)
/*    */   {
/* 52 */     return this.dao.getOnlineFolderByParentId(parentId);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.document.impl.DocFolderServiceImpl
 * JD-Core Version:    0.6.0
 */