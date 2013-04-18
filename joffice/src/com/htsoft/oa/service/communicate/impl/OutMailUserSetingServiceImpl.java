/*    */ package com.htsoft.oa.service.communicate.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.core.web.paging.PagingBean;
/*    */ import com.htsoft.oa.dao.communicate.OutMailUserSetingDao;
/*    */ import com.htsoft.oa.model.communicate.OutMailUserSeting;
/*    */ import com.htsoft.oa.service.communicate.OutMailUserSetingService;
/*    */ import java.util.List;
/*    */ 
/*    */ public class OutMailUserSetingServiceImpl extends BaseServiceImpl<OutMailUserSeting>
/*    */   implements OutMailUserSetingService
/*    */ {
/*    */   private OutMailUserSetingDao dao;
/*    */ 
/*    */   public OutMailUserSetingServiceImpl(OutMailUserSetingDao dao)
/*    */   {
/* 26 */     super(dao);
/* 27 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   public OutMailUserSeting getByLoginId(Long loginid) {
/* 31 */     return this.dao.getByLoginId(loginid);
/*    */   }
/*    */ 
/*    */   public List findByUserAll()
/*    */   {
/* 36 */     return this.dao.findByUserAll();
/*    */   }
/*    */ 
/*    */   public List<OutMailUserSeting> findByUserAll(String userName, PagingBean pb)
/*    */   {
/* 41 */     return this.dao.findByUserAll(userName, pb);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.communicate.impl.OutMailUserSetingServiceImpl
 * JD-Core Version:    0.6.0
 */