/*    */ package com.htsoft.oa.service.flow.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.flow.RunDataDao;
/*    */ import com.htsoft.oa.model.flow.ProcessRun;
/*    */ import com.htsoft.oa.model.flow.RunData;
/*    */ import com.htsoft.oa.service.flow.ProcessRunService;
/*    */ import com.htsoft.oa.service.flow.RunDataService;
/*    */ import java.util.HashMap;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.Map.Entry;
/*    */ import java.util.Set;
/*    */ import javax.annotation.Resource;
/*    */ 
/*    */ public class RunDataServiceImpl extends BaseServiceImpl<RunData>
/*    */   implements RunDataService
/*    */ {
/*    */   private RunDataDao dao;
/*    */ 
/*    */   @Resource
/*    */   private ProcessRunService processRunService;
/*    */ 
/*    */   public RunDataServiceImpl(RunDataDao dao)
/*    */   {
/* 28 */     super(dao);
/* 29 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   public RunData getByRunIdFieldName(Long runId, String fieldName)
/*    */   {
/* 39 */     return this.dao.getByRunIdFieldName(runId, fieldName);
/*    */   }
/*    */ 
/*    */   public void saveFlowVars(Long runId, Map<String, Object> vars)
/*    */   {
/* 48 */     ProcessRun processRun = (ProcessRun)this.processRunService.get(runId);
/* 49 */     Iterator it = vars.entrySet().iterator();
/* 50 */     while (it.hasNext()) {
/* 51 */       Map.Entry entity = (Map.Entry)it.next();
/* 52 */       String fieldName = (String)entity.getKey();
/* 53 */       Object val = entity.getValue();
/*    */ 
/* 55 */       RunData runData = this.dao.getByRunIdFieldName(runId, fieldName);
/* 56 */       if (runData == null) {
/* 57 */         runData = new RunData();
/*    */       }
/* 59 */       runData.setFieldName(fieldName);
/* 60 */       runData.setFieldLabel(fieldName);
/* 61 */       runData.setProcessRun(processRun);
/* 62 */       runData.setRawValue(val);
/*    */ 
/* 64 */       save(runData);
/*    */     }
/*    */   }
/*    */ 
/*    */   public Map<String, Object> getMapByRunId(Long runId)
/*    */   {
/* 70 */     Map dataMap = new HashMap();
/* 71 */     List<RunData> list = this.dao.getByRunId(runId);
/* 72 */     for (RunData data : list) {
/* 73 */       dataMap.put(data.getFieldName(), data.getRawValue());
/*    */     }
/* 75 */     return dataMap;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.flow.impl.RunDataServiceImpl
 * JD-Core Version:    0.6.0
 */