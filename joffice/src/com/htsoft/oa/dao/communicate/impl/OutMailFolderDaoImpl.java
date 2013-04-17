/*    */ package com.htsoft.oa.dao.communicate.impl;
/*    */ 
/*    */ import com.htsoft.core.dao.impl.BaseDaoImpl;
/*    */ import com.htsoft.oa.dao.communicate.OutMailFolderDao;
/*    */ import com.htsoft.oa.model.communicate.OutMailFolder;
/*    */ import java.util.List;
/*    */ 
/*    */ public class OutMailFolderDaoImpl extends BaseDaoImpl<OutMailFolder>
/*    */   implements OutMailFolderDao
/*    */ {
/*    */   public OutMailFolderDaoImpl()
/*    */   {
/* 16 */     super(OutMailFolder.class);
/*    */   }
/*    */ 
/*    */   public List<OutMailFolder> getAllUserFolderByParentId(Long userId, Long parentId)
/*    */   {
/* 24 */     String hql = "from OutMailFolder mf where mf.appUser.userId=? and parentId=? or userId is null";
/* 25 */     return findByHql(hql, new Object[] { userId, parentId });
/*    */   }
/*    */ 
/*    */   public List<OutMailFolder> getUserFolderByParentId(Long userId, Long parentId)
/*    */   {
/* 34 */     String hql = "from OutMailFolder mf where mf.appUser.userId=? and parentId=?";
/* 35 */     return findByHql(hql, new Object[] { userId, parentId });
/*    */   }
/*    */ 
/*    */   public List<OutMailFolder> getFolderLikePath(String path)
/*    */   {
/* 45 */     String hql = "from OutMailFolder mf where mf.path like ?";
/* 46 */     return findByHql(hql, new Object[] { path + '%' });
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.communicate.impl.OutMailFolderDaoImpl
 * JD-Core Version:    0.6.0
 */