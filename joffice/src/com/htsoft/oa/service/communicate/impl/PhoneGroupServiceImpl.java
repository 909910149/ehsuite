/*    */ package com.htsoft.oa.service.communicate.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.communicate.PhoneGroupDao;
/*    */ import com.htsoft.oa.model.communicate.PhoneGroup;
/*    */ import com.htsoft.oa.service.communicate.PhoneGroupService;
/*    */ import java.util.List;
/*    */ 
/*    */ public class PhoneGroupServiceImpl extends BaseServiceImpl<PhoneGroup>
/*    */   implements PhoneGroupService
/*    */ {
/*    */   private PhoneGroupDao dao;
/*    */ 
/*    */   public PhoneGroupServiceImpl(PhoneGroupDao dao)
/*    */   {
/* 17 */     super(dao);
/* 18 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   public Integer findLastSn(Long userId)
/*    */   {
/* 23 */     return this.dao.findLastSn(userId);
/*    */   }
/*    */ 
/*    */   public PhoneGroup findBySn(Integer sn, Long userId)
/*    */   {
/* 28 */     return this.dao.findBySn(sn, userId);
/*    */   }
/*    */ 
/*    */   public List<PhoneGroup> findBySnUp(Integer sn, Long userId)
/*    */   {
/* 33 */     return this.dao.findBySnUp(sn, userId);
/*    */   }
/*    */ 
/*    */   public List<PhoneGroup> findBySnDown(Integer sn, Long userId)
/*    */   {
/* 38 */     return this.dao.findBySnDown(sn, userId);
/*    */   }
/*    */ 
/*    */   public List<PhoneGroup> getAll(Long userId)
/*    */   {
/* 43 */     return this.dao.getAll(userId);
/*    */   }
/*    */ 
/*    */   public PhoneGroup findPublicBySn(Integer sn)
/*    */   {
/* 48 */     return this.dao.findPublicBySn(sn);
/*    */   }
/*    */ 
/*    */   public List<PhoneGroup> findPublicBySnDown(Integer sn)
/*    */   {
/* 53 */     return this.dao.findPublicBySnDown(sn);
/*    */   }
/*    */ 
/*    */   public List<PhoneGroup> findPublicBySnUp(Integer sn)
/*    */   {
/* 58 */     return this.dao.findPublicBySnUp(sn);
/*    */   }
/*    */ 
/*    */   public Integer findPublicLastSn()
/*    */   {
/* 63 */     return this.dao.findPublicLastSn();
/*    */   }
/*    */ 
/*    */   public List<PhoneGroup> getPublicAll()
/*    */   {
/* 68 */     return this.dao.getPublicAll();
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.communicate.impl.PhoneGroupServiceImpl
 * JD-Core Version:    0.6.0
 */