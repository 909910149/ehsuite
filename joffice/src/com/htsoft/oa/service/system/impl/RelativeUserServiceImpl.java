 package com.htsoft.oa.service.system.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.dao.system.RelativeUserDao;
 import com.htsoft.oa.model.system.AppUser;
 import com.htsoft.oa.model.system.RelativeUser;
 import com.htsoft.oa.service.system.RelativeUserService;
 import java.util.List;
 import java.util.Set;
 
 public class RelativeUserServiceImpl extends BaseServiceImpl<RelativeUser>
   implements RelativeUserService
 {
   private RelativeUserDao dao;
 
   public RelativeUserServiceImpl(RelativeUserDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public AppUser judge(Long userId, Long jobUserId)
   {
     return this.dao.judge(userId, jobUserId);
   }
 
   public List<AppUser> findByUserIdReJobId(Long userId, Long reJobId)
   {
     return this.dao.findByUserIdReJobId(userId, reJobId);
   }
 
   public List<RelativeUser> list(Long appUserId, Long reJobId, PagingBean pb)
   {
     return this.dao.list(appUserId, reJobId, pb);
   }
 
   public List<Long> getReJobUserIds(Long userId, Long reJobId)
   {
     return this.dao.getReJobUserIds(userId, reJobId);
   }
 
   public Set<AppUser> getUpUser(Long userId)
   {
     return this.dao.getUpUser(userId);
   }
 }

