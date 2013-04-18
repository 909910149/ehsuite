/*    */ package com.htsoft.oa.dao.flow.impl;
/*    */ 
/*    */ import com.htsoft.core.dao.impl.BaseDaoImpl;
/*    */ import com.htsoft.oa.dao.flow.ProcessModuleDao;
/*    */ import com.htsoft.oa.model.flow.ProcessModule;
/*    */ import java.util.List;
/*    */ 
/*    */ public class ProcessModuleDaoImpl extends BaseDaoImpl<ProcessModule>
/*    */   implements ProcessModuleDao
/*    */ {
/*    */   public ProcessModuleDaoImpl()
/*    */   {
/* 16 */     super(ProcessModule.class);
/*    */   }
/*    */ 
/*    */   public ProcessModule getByKey(String string)
/*    */   {
/* 21 */     String hql = "from ProcessModule pm where pm.modulekey=?";
/* 22 */     List list = findByHql(hql, new Object[] { string });
/* 23 */     if (list.size() > 0) {
/* 24 */       return (ProcessModule)list.get(0);
/*    */     }
/* 26 */     return null;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.flow.impl.ProcessModuleDaoImpl
 * JD-Core Version:    0.6.0
 */