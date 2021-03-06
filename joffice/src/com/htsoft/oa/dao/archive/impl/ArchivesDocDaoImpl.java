/*    */ package com.htsoft.oa.dao.archive.impl;
/*    */ 
/*    */ import com.htsoft.core.dao.impl.BaseDaoImpl;
/*    */ import com.htsoft.oa.dao.archive.ArchivesDocDao;
/*    */ import com.htsoft.oa.model.archive.ArchivesDoc;
/*    */ import java.util.List;
/*    */ 
/*    */ public class ArchivesDocDaoImpl extends BaseDaoImpl<ArchivesDoc>
/*    */   implements ArchivesDocDao
/*    */ {
/*    */   public ArchivesDocDaoImpl()
/*    */   {
/* 15 */     super(ArchivesDoc.class);
/*    */   }
/*    */ 
/*    */   public List<ArchivesDoc> findByAid(Long archivesId)
/*    */   {
/* 20 */     String hql = "from ArchivesDoc vo where vo.archives.archivesId=?";
/* 21 */     Object[] objs = { archivesId };
/* 22 */     return findByHql(hql, objs);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.archive.impl.ArchivesDocDaoImpl
 * JD-Core Version:    0.6.0
 */