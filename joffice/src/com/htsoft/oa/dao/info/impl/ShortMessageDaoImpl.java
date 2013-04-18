/*    */ package com.htsoft.oa.dao.info.impl;
/*    */ 
/*    */ import com.htsoft.core.dao.impl.BaseDaoImpl;
/*    */ import com.htsoft.core.web.paging.PagingBean;
/*    */ import com.htsoft.oa.dao.info.ShortMessageDao;
/*    */ import com.htsoft.oa.model.info.ShortMessage;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Date;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ 
/*    */ public class ShortMessageDaoImpl extends BaseDaoImpl<ShortMessage>
/*    */   implements ShortMessageDao
/*    */ {
/*    */   public ShortMessageDaoImpl()
/*    */   {
/* 23 */     super(ShortMessage.class);
/*    */   }
/*    */ 
/*    */   public List<ShortMessage> findAll(Long userId, PagingBean pb)
/*    */   {
/* 28 */     String hql = "from ShortMessage vo where vo.senderId=?";
/* 29 */     Object[] objs = { userId };
/* 30 */     return findByHql(hql, objs, pb);
/*    */   }
/*    */ 
/*    */   public List<ShortMessage> findByUser(Long userId)
/*    */   {
/* 35 */     String hql = "from ShortMessage vo where vo.senderId=?";
/* 36 */     Object[] objs = { userId };
/* 37 */     return findByHql(hql, objs);
/*    */   }
/*    */ 
/*    */   public List searchShortMessage(Long userId, ShortMessage shortMessage, Date from, Date to, PagingBean pb, Short readFlag)
/*    */   {
/* 47 */     ArrayList paramList = new ArrayList();
/* 48 */     StringBuffer hql = new StringBuffer("select vo1,vo2 from InMessage vo1,ShortMessage vo2 where vo1.shortMessage=vo2 and vo1.delFlag=0 and vo1.userId=? ");
/* 49 */     paramList.add(userId);
/*    */ 
/* 51 */     if (readFlag != null) {
/* 52 */       hql.append("and vo1.readFlag=?");
/* 53 */       paramList.add(readFlag);
/*    */     }
/*    */ 
/* 56 */     if (shortMessage != null) {
/* 57 */       if (shortMessage.getMsgType() != null) {
/* 58 */         hql.append(" and vo2.msgType=?");
/* 59 */         paramList.add(shortMessage.getMsgType());
/*    */       }
/* 61 */       if (StringUtils.isNotEmpty(shortMessage.getSender())) {
/* 62 */         hql.append(" and vo2.sender=?");
/* 63 */         paramList.add(shortMessage.getSender());
/*    */       }
/*    */     }
/* 66 */     if (to != null) {
/* 67 */       hql.append("and vo2.sendTime <= ?");
/* 68 */       paramList.add(to);
/*    */     }
/* 70 */     if (from != null) {
/* 71 */       hql.append("and vo2.sendTime >= ?");
/* 72 */       paramList.add(from);
/*    */     }
/* 74 */     hql.append(" order by vo2.sendTime desc");
/* 75 */     return findByHql(hql.toString(), paramList.toArray(), pb);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.info.impl.ShortMessageDaoImpl
 * JD-Core Version:    0.6.0
 */