/*    */ package com.htsoft.test.document;
/*    */ 
/*    */ import com.htsoft.core.web.paging.PagingBean;
/*    */ import com.htsoft.oa.dao.document.DocumentDao;
/*    */ import com.htsoft.oa.dao.system.AppUserDao;
/*    */ import com.htsoft.oa.model.document.Document;
/*    */ import com.htsoft.oa.model.system.AppUser;
/*    */ import com.htsoft.test.BaseTestCase;
/*    */ import java.io.PrintStream;
/*    */ import java.util.List;
/*    */ import javax.annotation.Resource;
/*    */ import org.junit.Test;
/*    */ 
/*    */ public class DocumentDaoTestCase extends BaseTestCase
/*    */ {
/*    */ 
/*    */   @Resource
/*    */   private DocumentDao documentDao;
/*    */ 
/*    */   @Resource
/*    */   private AppUserDao appUserDao;
/*    */ 
/*    */   @Test
/*    */   public void tesss()
/*    */   {
/* 51 */     AppUser user = (AppUser)this.appUserDao.get(Long.valueOf(2L));
/*    */ 
/* 85 */     PagingBean pb = new PagingBean(0, 6);
/* 86 */     Document document = (Document)this.documentDao.get(Long.valueOf(6L));
/* 87 */     List docs = this.documentDao.findByPersonal(Long.valueOf(2L), null, null, null, null, pb);
/* 88 */     System.out.println("size:" + docs.size());
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.document.DocumentDaoTestCase
 * JD-Core Version:    0.6.0
 */