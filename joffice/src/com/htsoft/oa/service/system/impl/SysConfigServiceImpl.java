/*    */ package com.htsoft.oa.service.system.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.system.SysConfigDao;
/*    */ import com.htsoft.oa.model.system.SysConfig;
/*    */ import com.htsoft.oa.service.system.SysConfigService;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class SysConfigServiceImpl extends BaseServiceImpl<SysConfig>
/*    */   implements SysConfigService
/*    */ {
/*    */   private SysConfigDao dao;
/*    */ 
/*    */   public SysConfigServiceImpl(SysConfigDao dao)
/*    */   {
/* 20 */     super(dao);
/* 21 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   public SysConfig findByKey(String key)
/*    */   {
/* 26 */     return this.dao.findByKey(key);
/*    */   }
/*    */ 
/*    */   public Map findByType()
/*    */   {
/* 31 */     List<String> list = this.dao.findTypeKeys();
/* 32 */     Map cList = new HashMap();
/* 33 */     for (String typeKey : list) {
/* 34 */       List confList = this.dao.findConfigByTypeKey(typeKey);
/* 35 */       cList.put(typeKey, confList);
/*    */     }
/* 37 */     return cList;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.system.impl.SysConfigServiceImpl
 * JD-Core Version:    0.6.0
 */