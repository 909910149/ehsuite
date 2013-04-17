/*    */ package com.htsoft.test.admin;
/*    */ 
/*    */ import com.htsoft.oa.dao.admin.GoodsApplyDao;
/*    */ import com.htsoft.oa.model.admin.GoodsApply;
/*    */ import com.htsoft.test.BaseTestCase;
/*    */ import javax.annotation.Resource;
/*    */ import org.junit.Test;
/*    */ import org.springframework.test.annotation.Rollback;
/*    */ 
/*    */ public class GoodsApplyDaoTestCase extends BaseTestCase
/*    */ {
/*    */ 
/*    */   @Resource
/*    */   private GoodsApplyDao goodsApplyDao;
/*    */ 
/*    */   @Test
/*    */   @Rollback(false)
/*    */   public void add()
/*    */   {
/* 19 */     GoodsApply goodsApply = new GoodsApply();
/*    */ 
/* 22 */     this.goodsApplyDao.save(goodsApply);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.admin.GoodsApplyDaoTestCase
 * JD-Core Version:    0.6.0
 */