/*    */ package com.htsoft.test.system;
/*    */ 
/*    */ import com.htsoft.oa.dao.system.RegionDao;
/*    */ import com.htsoft.test.BaseTestCase;
/*    */ import java.io.PrintStream;
/*    */ import java.util.List;
/*    */ import javax.annotation.Resource;
/*    */ import org.junit.Test;
/*    */ import org.springframework.test.annotation.Rollback;
/*    */ 
/*    */ public class RegionDaoTestCase extends BaseTestCase
/*    */ {
/*    */ 
/*    */   @Resource
/*    */   private RegionDao regionDao;
/*    */ 
/*    */   @Test
/*    */   @Rollback(false)
/*    */   public void add()
/*    */   {
/* 23 */     System.out.println(this.regionDao.getProvince().size());
/* 24 */     System.out.println(this.regionDao.getCity(Long.valueOf(20L)).size());
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.system.RegionDaoTestCase
 * JD-Core Version:    0.6.0
 */