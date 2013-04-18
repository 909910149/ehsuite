/*    */ package com.htsoft.oa.service.info.impl;
/*    */ 
/*    */ import com.htsoft.core.Constants;
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.core.web.paging.PagingBean;
/*    */ import com.htsoft.oa.dao.info.InMessageDao;
/*    */ import com.htsoft.oa.dao.info.ShortMessageDao;
/*    */ import com.htsoft.oa.dao.system.AppUserDao;
/*    */ import com.htsoft.oa.model.info.InMessage;
/*    */ import com.htsoft.oa.model.info.ShortMessage;
/*    */ import com.htsoft.oa.model.system.AppUser;
/*    */ import com.htsoft.oa.service.info.ShortMessageService;
/*    */ import java.util.Date;
/*    */ import java.util.List;
/*    */ import javax.annotation.Resource;
/*    */ 
/*    */ public class ShortMessageServiceImpl extends BaseServiceImpl<ShortMessage>
/*    */   implements ShortMessageService
/*    */ {
/*    */   private ShortMessageDao messageDao;
/*    */ 
/*    */   @Resource
/*    */   private InMessageDao inMessageDao;
/*    */ 
/*    */   @Resource
/*    */   private AppUserDao appUserDao;
/*    */ 
/*    */   public ShortMessageServiceImpl(ShortMessageDao dao)
/*    */   {
/* 32 */     super(dao);
/* 33 */     this.messageDao = dao;
/*    */   }
/*    */ 
/*    */   public List<ShortMessage> findAll(Long userId, PagingBean pb)
/*    */   {
/* 38 */     return this.messageDao.findAll(userId, pb);
/*    */   }
/*    */ 
/*    */   public List<ShortMessage> findByUser(Long userId)
/*    */   {
/* 43 */     return this.messageDao.findByUser(userId);
/*    */   }
/*    */ 
/*    */   public List<ShortMessage> searchShortMessage(Long userId, ShortMessage shortMessage, Date from, Date to, PagingBean pb, Short readFlag)
/*    */   {
/* 50 */     return this.messageDao.searchShortMessage(userId, shortMessage, from, to, 
/* 51 */       pb, readFlag);
/*    */   }
/*    */ 
/*    */   public ShortMessage save(Long senderId, String receiveIds, String content, Short msgType)
/*    */   {
/* 57 */     ShortMessage shortMessage = new ShortMessage();
/* 58 */     shortMessage.setContent(content);
/* 59 */     shortMessage.setMsgType(msgType);
/* 60 */     AppUser curUser = (AppUser)this.appUserDao.get(senderId);
/* 61 */     shortMessage.setSender(curUser.getFullname());
/* 62 */     shortMessage.setSenderId(curUser.getUserId());
/* 63 */     shortMessage.setSendTime(new Date());
/*    */ 
/* 65 */     this.messageDao.save(shortMessage);
/*    */ 
/* 67 */     String[] reIds = receiveIds.split("[,]");
/* 68 */     if (reIds != null)
/*    */     {
/* 70 */       for (String userId : reIds) {
/* 71 */         InMessage inMsg = new InMessage();
/* 72 */         inMsg.setDelFlag(Constants.FLAG_UNDELETED);
/* 73 */         inMsg.setReadFlag(InMessage.FLAG_UNREAD);
/* 74 */         inMsg.setShortMessage(shortMessage);
/* 75 */         AppUser receiveUser = (AppUser)this.appUserDao.get(new Long(userId));
/*    */ 
/* 77 */         inMsg.setUserId(receiveUser.getUserId());
/* 78 */         inMsg.setUserFullname(receiveUser.getFullname());
/* 79 */         this.inMessageDao.save(inMsg);
/*    */       }
/*    */     }
/*    */ 
/* 83 */     return shortMessage;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.info.impl.ShortMessageServiceImpl
 * JD-Core Version:    0.6.0
 */