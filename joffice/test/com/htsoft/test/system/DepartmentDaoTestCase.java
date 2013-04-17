/*    */ package com.htsoft.test.system;
/*    */ 
/*    */ import com.htsoft.oa.dao.system.DepartmentDao;
/*    */ import com.htsoft.oa.model.system.Department;
/*    */ import com.htsoft.test.BaseTestCase;
/*    */ import javax.annotation.Resource;
/*    */ import org.junit.Test;
/*    */ import org.springframework.test.annotation.Rollback;
/*    */ 
/*    */ public class DepartmentDaoTestCase extends BaseTestCase
/*    */ {
/*    */ 
/*    */   @Resource
/*    */   private DepartmentDao departmentDao;
/*    */ 
/*    */   @Test
/*    */   @Rollback(false)
/*    */   public void batchAdd()
/*    */   {
/* 21 */     for (int i = 1; i <= 100; i++) {
/* 22 */       Department dep = new Department();
/* 23 */       dep.setDepLevel(Integer.valueOf(1));
/* 24 */       dep.setDepName("局" + i);
/* 25 */       dep.setDepDesc("局" + i);
/* 26 */       dep.setParentId(new Long(0L));
/* 27 */       this.departmentDao.save(dep);
/*    */ 
/* 29 */       dep.setPath("0." + dep.getDepId() + ".");
/*    */ 
/* 31 */       this.departmentDao.save(dep);
/*    */ 
/* 33 */       for (int j = 1; j <= 5; j++) {
/* 34 */         Department subDep = new Department();
/* 35 */         subDep.setDepLevel(Integer.valueOf(2));
/* 36 */         subDep.setParentId(dep.getDepId());
/* 37 */         subDep.setDepName(dep.getDepName() + "-" + j);
/* 38 */         subDep.setDepDesc(dep.getDepName() + "-" + j);
/* 39 */         this.departmentDao.save(subDep);
/* 40 */         subDep.setPath(dep.getPath() + subDep.getDepId() + ".");
/*    */       }
/*    */     }
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.system.DepartmentDaoTestCase
 * JD-Core Version:    0.6.0
 */