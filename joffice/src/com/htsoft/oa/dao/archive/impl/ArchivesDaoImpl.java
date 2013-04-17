/*    */ package com.htsoft.oa.dao.archive.impl;
/*    */ 
/*    */ import com.htsoft.core.dao.impl.BaseDaoImpl;
/*    */ import com.htsoft.core.web.paging.PagingBean;
/*    */ import com.htsoft.oa.dao.archive.ArchivesDao;
/*    */ import com.htsoft.oa.model.archive.Archives;
/*    */ import com.htsoft.oa.model.system.AppRole;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class ArchivesDaoImpl extends BaseDaoImpl<Archives>
/*    */   implements ArchivesDao
/*    */ {
/*    */   public ArchivesDaoImpl()
/*    */   {
/* 20 */     super(Archives.class);
/*    */   }
/*    */ 
/*    */   public List<Archives> findByUserOrRole(Long userId, Set<AppRole> roles, PagingBean pb)
/*    */   {
/* 26 */     Iterator it = roles.iterator();
/* 27 */     StringBuffer sb = new StringBuffer();
/* 28 */     while (it.hasNext()) {
/* 29 */       if (sb.length() > 0) {
/* 30 */         sb.append(",");
/*    */       }
/* 32 */       sb.append(((AppRole)it.next()).getRoleId().toString());
/*    */     }
/* 34 */     StringBuffer hql = new StringBuffer("select distinct vo1.archivesId from Archives vo1,ArchDispatch vo2 where vo2.archives=vo1 and vo2.archUserType=2 and (vo2.userId=?");
/* 35 */     if (sb.length() > 0) {
/* 36 */       hql.append(" or vo2.disRoleId in (" + sb + ")");
/*    */     }
/* 38 */     hql.append(")");
/* 39 */     Object[] objs = { userId };
/* 40 */     List ids2 = findByHql(hql.toString(), objs, pb);
/* 41 */     return findByIds(ids2);
/*    */   }
/*    */ 
/*    */   private List<Archives> findByIds(List ids) {
/* 45 */     String hql = "from Document doc where doc.docId in (";
/* 46 */     StringBuffer ids2 = new StringBuffer();
/*    */ 
/* 48 */     for (int i = 0; i < ids.size(); i++) {
/* 49 */       ids2.append(ids.get(i).toString()).append(",");
/*    */     }
/* 51 */     if (ids.size() > 0) {
/* 52 */       ids2.deleteCharAt(ids2.length() - 1);
/* 53 */       hql = hql + ids2.toString() + ")";
/* 54 */       return findByHql(hql);
/*    */     }
/* 56 */     return new ArrayList();
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.archive.impl.ArchivesDaoImpl
 * JD-Core Version:    0.6.0
 */