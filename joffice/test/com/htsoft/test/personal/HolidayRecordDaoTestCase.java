/*    */ package com.htsoft.test.personal;
/*    */ 
/*    */ import com.htsoft.oa.dao.personal.HolidayRecordDao;
/*    */ import com.htsoft.oa.model.personal.HolidayRecord;
/*    */ import com.htsoft.test.BaseTestCase;
/*    */ import javax.annotation.Resource;
/*    */ import org.junit.Test;
/*    */ import org.springframework.test.annotation.Rollback;
/*    */ 
/*    */ public class HolidayRecordDaoTestCase extends BaseTestCase
/*    */ {
/*    */ 
/*    */   @Resource
/*    */   private HolidayRecordDao holidayRecordDao;
/*    */ 
/*    */   @Test
/*    */   @Rollback(false)
/*    */   public void add()
/*    */   {
/* 19 */     HolidayRecord holidayRecord = new HolidayRecord();
/*    */ 
/* 22 */     this.holidayRecordDao.save(holidayRecord);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.personal.HolidayRecordDaoTestCase
 * JD-Core Version:    0.6.0
 */