/*    */ package com.htsoft.test.communicate;
/*    */ 
/*    */ import com.htsoft.oa.dao.communicate.PhoneBookDao;
/*    */ import com.htsoft.test.BaseTestCase;
/*    */ import flexjson.JSONSerializer;
/*    */ import java.util.List;
/*    */ import javax.annotation.Resource;
/*    */ import org.junit.Test;
/*    */ 
/*    */ public class PhoneBookDaoTestCase extends BaseTestCase
/*    */ {
/*    */ 
/*    */   @Resource
/*    */   private PhoneBookDao phoneBookDao;
/*    */ 
/*    */   @Test
/*    */   public void test()
/*    */   {
/* 36 */     List phoneBook = this.phoneBookDao.getAll();
/*    */ 
/* 38 */     JSONSerializer serializer = new JSONSerializer();
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.communicate.PhoneBookDaoTestCase
 * JD-Core Version:    0.6.0
 */