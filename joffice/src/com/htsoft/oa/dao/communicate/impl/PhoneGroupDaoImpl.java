/*    */ package com.htsoft.oa.dao.communicate.impl;
/*    */ 
/*    */ import com.htsoft.core.dao.impl.BaseDaoImpl;
/*    */ import com.htsoft.oa.dao.communicate.PhoneGroupDao;
/*    */ import com.htsoft.oa.model.communicate.PhoneGroup;
/*    */ import java.util.List;
/*    */ 
/*    */ public class PhoneGroupDaoImpl extends BaseDaoImpl<PhoneGroup>
/*    */   implements PhoneGroupDao
/*    */ {
/*    */   public PhoneGroupDaoImpl()
/*    */   {
/* 15 */     super(PhoneGroup.class);
/*    */   }
/*    */ 
/*    */   public Integer findLastSn(Long userId)
/*    */   {
/* 20 */     String hql = "select max(sn) from PhoneGroup vo where vo.isPublic=0 and vo.appUser.userId=?";
/* 21 */     Object[] object = { userId };
/* 22 */     List list = findByHql(hql, object);
/* 23 */     return (Integer)list.get(0);
/*    */   }
/*    */ 
/*    */   public PhoneGroup findBySn(Integer sn, Long userId)
/*    */   {
/* 28 */     String hql = "select vo from PhoneGroup vo where vo.isPublic=0 and vo.appUser.userId=? and vo.sn=?";
/* 29 */     Object[] object = { userId, sn };
/* 30 */     List list = findByHql(hql, object);
/* 31 */     return (PhoneGroup)list.get(0);
/*    */   }
/*    */ 
/*    */   public List<PhoneGroup> findBySnUp(Integer sn, Long userId)
/*    */   {
/* 36 */     String hql = "from PhoneGroup vo where vo.isPublic=0 and vo.appUser.userId=? and vo.sn<?";
/* 37 */     Object[] object = { userId, sn };
/* 38 */     return findByHql(hql, object);
/*    */   }
/*    */ 
/*    */   public List<PhoneGroup> findBySnDown(Integer sn, Long userId)
/*    */   {
/* 43 */     String hql = "from PhoneGroup vo where vo.isPublic=0 and vo.appUser.userId=? and vo.sn>?";
/* 44 */     Object[] object = { userId, sn };
/* 45 */     return findByHql(hql, object);
/*    */   }
/*    */ 
/*    */   public List<PhoneGroup> getAll(Long userId)
/*    */   {
/* 50 */     String hql = "from PhoneGroup vo where vo.isPublic=0 and vo.appUser.userId=? order by vo.sn asc";
/* 51 */     Object[] object = { userId };
/* 52 */     return findByHql(hql, object);
/*    */   }
/*    */ 
/*    */   public PhoneGroup findPublicBySn(Integer sn)
/*    */   {
/* 57 */     String hql = "select vo from PhoneGroup vo where vo.isPublic=1 and vo.sn=?";
/* 58 */     Object[] object = { sn };
/* 59 */     List list = findByHql(hql, object);
/* 60 */     return (PhoneGroup)list.get(0);
/*    */   }
/*    */ 
/*    */   public List<PhoneGroup> findPublicBySnDown(Integer sn)
/*    */   {
/* 65 */     String hql = "from PhoneGroup vo where vo.isPublic=1 and vo.sn>?";
/* 66 */     Object[] object = { sn };
/* 67 */     return findByHql(hql, object);
/*    */   }
/*    */ 
/*    */   public List<PhoneGroup> findPublicBySnUp(Integer sn)
/*    */   {
/* 72 */     String hql = "from PhoneGroup vo where vo.isPublic=1 and vo.sn<?";
/* 73 */     Object[] object = { sn };
/* 74 */     return findByHql(hql, object);
/*    */   }
/*    */ 
/*    */   public Integer findPublicLastSn()
/*    */   {
/* 79 */     String hql = "select max(sn) from PhoneGroup vo where vo.isPublic=1";
/* 80 */     List list = findByHql(hql);
/* 81 */     return (Integer)list.get(0);
/*    */   }
/*    */ 
/*    */   public List<PhoneGroup> getPublicAll()
/*    */   {
/* 86 */     String hql = "from PhoneGroup vo where vo.isPublic=1 order by vo.sn asc";
/* 87 */     return findByHql(hql);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.communicate.impl.PhoneGroupDaoImpl
 * JD-Core Version:    0.6.0
 */