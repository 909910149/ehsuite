/*    */ package com.htsoft.oa.dao.document.impl;
/*    */ 
/*    */ import com.htsoft.core.dao.impl.BaseDaoImpl;
/*    */ import com.htsoft.oa.dao.document.DocFolderDao;
/*    */ import com.htsoft.oa.model.document.DocFolder;
/*    */ import java.util.List;
/*    */ 
/*    */ public class DocFolderDaoImpl extends BaseDaoImpl<DocFolder>
/*    */   implements DocFolderDao
/*    */ {
/*    */   public DocFolderDaoImpl()
/*    */   {
/* 14 */     super(DocFolder.class);
/*    */   }
/*    */ 
/*    */   public List<DocFolder> getUserFolderByParentId(Long userId, Long parentId)
/*    */   {
/* 25 */     String hql = "from DocFolder df where df.isShared=0 and df.appUser.userId=? and parentId=?";
/* 26 */     return findByHql(hql, new Object[] { userId, parentId });
/*    */   }
/*    */ 
/*    */   public List<DocFolder> getFolderLikePath(String path)
/*    */   {
/* 34 */     String hql = "from DocFolder df where df.path like ?";
/* 35 */     return findByHql(hql, new Object[] { path + '%' });
/*    */   }
/*    */ 
/*    */   public List<DocFolder> getPublicFolderByParentId(Long parentId)
/*    */   {
/* 40 */     String hql = "from DocFolder df where df.isShared=1 and df.parentId=? ";
/* 41 */     return findByHql(hql, new Object[] { parentId });
/*    */   }
/*    */ 
/*    */   public List<DocFolder> findByParentId(Long parentId)
/*    */   {
/* 46 */     String hql = "from DocFolder df where df.parentId=?";
/* 47 */     return findByHql(hql, new Object[] { parentId });
/*    */   }
/*    */ 
/*    */   public List<DocFolder> findByUserAndName(Long userId, String foleName)
/*    */   {
/* 52 */     String hql = "from DocFolder df where df.isShared=0 and df.appUser.userId=? and df.folderName like ?";
/* 53 */     return findByHql(hql, new Object[] { userId, "%" + foleName + "%" });
/*    */   }
/*    */ 
/*    */   public List<DocFolder> getOnlineFolderByParentId(Long parentId)
/*    */   {
/* 58 */     String hql = "from DocFolder df where df.isShared=2 and df.parentId=? ";
/* 59 */     return findByHql(hql, new Object[] { parentId });
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.document.impl.DocFolderDaoImpl
 * JD-Core Version:    0.6.0
 */