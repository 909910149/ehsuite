/*    */ package com.htsoft.oa.dao.admin.impl;
/*    */ 
/*    */ import com.htsoft.core.dao.impl.BaseDaoImpl;
/*    */ import com.htsoft.oa.dao.admin.ConfSummaryDao;
/*    */ import com.htsoft.oa.dao.admin.ConferenceDao;
/*    */ import com.htsoft.oa.dao.system.FileAttachDao;
/*    */ import com.htsoft.oa.model.admin.ConfSummary;
/*    */ import com.htsoft.oa.model.admin.Conference;
/*    */ import com.htsoft.oa.model.info.ShortMessage;
/*    */ import com.htsoft.oa.model.system.AppUser;
/*    */ import com.htsoft.oa.model.system.FileAttach;
/*    */ import com.htsoft.oa.service.info.ShortMessageService;
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ import javax.annotation.Resource;
/*    */ 
/*    */ public class ConfSummaryDaoImpl extends BaseDaoImpl<ConfSummary>
/*    */   implements ConfSummaryDao
/*    */ {
/*    */ 
/*    */   @Resource
/*    */   private ConferenceDao confDao;
/*    */ 
/*    */   @Resource
/*    */   private FileAttachDao fileAttachDao;
/*    */ 
/*    */   @Resource
/*    */   private ShortMessageService shortMessageService;
/*    */ 
/*    */   public ConfSummaryDaoImpl()
/*    */   {
/* 41 */     super(ConfSummary.class);
/*    */   }
/*    */ 
/*    */   public ConfSummary send(ConfSummary cm, String fileIds)
/*    */   {
/* 49 */     Conference conf = (Conference)this.confDao.get(cm.getConfId().getConfId());
/* 50 */     String ids = conf.getCompere() + "," + conf.getRecorder() + "," + 
/* 51 */       conf.getAttendUsers();
/* 52 */     String msg = "请查看主题为【" + conf.getConfTopic() + "】的会议纪要信息！";
/* 53 */     this.shortMessageService.save(AppUser.SYSTEM_USER, ids, msg, 
/* 54 */       ShortMessage.MSG_TYPE_SYS);
/* 55 */     return save(cm, fileIds);
/*    */   }
/*    */ 
/*    */   public ConfSummary save(ConfSummary cm, String fileIds)
/*    */   {
/* 62 */     if ((fileIds != null) && (!fileIds.isEmpty())) {
/* 63 */       Set set = new HashSet();
/* 64 */       for (String s : fileIds.split(",")) {
/* 65 */         set.add((FileAttach)this.fileAttachDao.get(new Long(s)));
/*    */       }
/* 67 */       cm.setAttachFiles(set);
/*    */     }
/* 69 */     Conference cf = (Conference)this.confDao.get(cm.getConfId().getConfId());
/* 70 */     cm.setConfId(cf);
/* 71 */     return (ConfSummary)super.save(cm);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.admin.impl.ConfSummaryDaoImpl
 * JD-Core Version:    0.6.0
 */