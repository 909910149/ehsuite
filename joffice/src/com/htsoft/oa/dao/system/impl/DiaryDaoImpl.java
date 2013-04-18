/*    */ package com.htsoft.oa.dao.system.impl;
/*    */ 
/*    */ import com.htsoft.core.dao.impl.BaseDaoImpl;
/*    */ import com.htsoft.core.web.paging.PagingBean;
/*    */ import com.htsoft.oa.dao.system.DiaryDao;
/*    */ import com.htsoft.oa.model.system.Diary;
/*    */ import java.util.List;
/*    */ 
/*    */ public class DiaryDaoImpl extends BaseDaoImpl<Diary>
/*    */   implements DiaryDao
/*    */ {
/*    */   public DiaryDaoImpl()
/*    */   {
/* 26 */     super(Diary.class);
/*    */   }
/*    */ 
/*    */   public List<Diary> getSubDiary(String userIds, PagingBean pb)
/*    */   {
/* 31 */     String hql = "from Diary vo where vo.appUser.userId in (" + userIds + 
/* 32 */       ") and vo.diaryType=1";
/* 33 */     return findByHql(hql, null, pb);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.system.impl.DiaryDaoImpl
 * JD-Core Version:    0.6.0
 */