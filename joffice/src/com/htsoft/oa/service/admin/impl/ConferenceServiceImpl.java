/*    */ package com.htsoft.oa.service.admin.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.core.web.paging.PagingBean;
/*    */ import com.htsoft.oa.dao.admin.ConferenceDao;
/*    */ import com.htsoft.oa.model.admin.Conference;
/*    */ import com.htsoft.oa.service.admin.ConferenceService;
/*    */ import java.util.Date;
/*    */ import java.util.List;
/*    */ 
/*    */ public class ConferenceServiceImpl extends BaseServiceImpl<Conference>
/*    */   implements ConferenceService
/*    */ {
/*    */   private ConferenceDao dao;
/*    */ 
/*    */   public ConferenceServiceImpl(ConferenceDao dao)
/*    */   {
/* 27 */     super(dao);
/* 28 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   public List<Conference> getConfTopic(String confTopic, PagingBean pb)
/*    */   {
/* 36 */     return this.dao.getConfTopic(confTopic, pb);
/*    */   }
/*    */ 
/*    */   public String baseUserIdSearchFullName(String userIds)
/*    */   {
/* 46 */     return this.dao.baseUserIdSearchFullName(userIds);
/*    */   }
/*    */ 
/*    */   public Conference send(Conference conference, String view, String updater, String summary, String fileIds)
/*    */   {
/* 54 */     conference.setCreatetime(new Date());
/* 55 */     conference.setSendtime(new Date());
/* 56 */     return this.dao.send(conference, view, updater, summary, fileIds);
/*    */   }
/*    */ 
/*    */   public Conference temp(Conference conference, String view, String updater, String summary, String fileIds)
/*    */   {
/* 65 */     conference.setStatus(Short.valueOf((short) 0));
/* 66 */     conference.setCreatetime(new Date());
/* 67 */     return this.dao.temp(conference, view, updater, summary, fileIds);
/*    */   }
/*    */ 
/*    */   public String judgeBoardRoomNotUse(Date startTime, Date endTime, Long roomId)
/*    */   {
/* 75 */     return this.dao.judgeBoardRoomNotUse(startTime, endTime, roomId);
/*    */   }
/*    */ 
/*    */   public String apply(Long confId, String checkReason, boolean bo)
/*    */   {
/* 83 */     return this.dao.apply(confId, checkReason, bo);
/*    */   }
/*    */ 
/*    */   public List<Conference> myJoin(Conference conference, Boolean bo, PagingBean pb)
/*    */   {
/* 91 */     return this.dao.myJoin(conference, bo, pb);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.admin.impl.ConferenceServiceImpl
 * JD-Core Version:    0.6.0
 */