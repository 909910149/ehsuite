/*    */ package com.htsoft.test.system;
/*    */ 
/*    */ import com.htsoft.oa.dao.system.DiaryDao;
/*    */ import com.htsoft.oa.model.system.Diary;
/*    */ import com.htsoft.test.BaseTestCase;
/*    */ import javax.annotation.Resource;
/*    */ import org.junit.Test;
/*    */ import org.springframework.test.annotation.Rollback;
/*    */ 
/*    */ public class DiaryDaoTestCase extends BaseTestCase
/*    */ {
/*    */ 
/*    */   @Resource
/*    */   private DiaryDao diaryDao;
/*    */ 
/*    */   @Test
/*    */   @Rollback(false)
/*    */   public void add()
/*    */   {
/* 19 */     Diary diary = new Diary();
/*    */ 
/* 22 */     this.diaryDao.save(diary);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.system.DiaryDaoTestCase
 * JD-Core Version:    0.6.0
 */