/*    */ package com.htsoft.oa.service.flow.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.flow.ProHandleCompDao;
/*    */ import com.htsoft.oa.model.flow.ProHandleComp;
/*    */ import com.htsoft.oa.service.flow.ProHandleCompService;
/*    */ import java.util.List;
/*    */ 
/*    */ public class ProHandleCompServiceImpl extends BaseServiceImpl<ProHandleComp>
/*    */   implements ProHandleCompService
/*    */ {
/*    */   private ProHandleCompDao dao;
/*    */ 
/*    */   public ProHandleCompServiceImpl(ProHandleCompDao dao)
/*    */   {
/* 18 */     super(dao);
/* 19 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   public List<ProHandleComp> getByDeployIdActivityName(String deployId, String activityName)
/*    */   {
/* 25 */     return this.dao.getByDeployIdActivityName(deployId, activityName);
/*    */   }
/*    */ 
/*    */   public List<ProHandleComp> getByDeployIdActivityNameHandleType(String deployId, String activityName, Short handleType)
/*    */   {
/* 31 */     return this.dao.getByDeployIdActivityNameHandleType(deployId, activityName, handleType);
/*    */   }
/*    */ 
/*    */   public ProHandleComp getProHandleComp(String deployId, String activityName, String eventName)
/*    */   {
/* 42 */     return this.dao.getProHandleComp(deployId, activityName, eventName);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.flow.impl.ProHandleCompServiceImpl
 * JD-Core Version:    0.6.0
 */