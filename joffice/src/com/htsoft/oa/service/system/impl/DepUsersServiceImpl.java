 package com.htsoft.oa.service.system.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.dao.system.DepUsersDao;
 import com.htsoft.oa.model.system.DepUsers;
 import com.htsoft.oa.service.system.DepUsersService;
 import java.util.List;
 
 public class DepUsersServiceImpl extends BaseServiceImpl<DepUsers>
   implements DepUsersService
 {
   private DepUsersDao dao;
 
   public DepUsersServiceImpl(DepUsersDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public List<DepUsers> findByDepartment(String path, PagingBean pb)
   {
     return this.dao.findByDepartment(path, pb);
   }
 
   public List<DepUsers> search(String path, DepUsers depUsers, PagingBean pb)
   {
     return this.dao.search(path, depUsers, pb);
   }
 
   public List<DepUsers> findByUserIdDep(Long userId)
   {
     return this.dao.findByUserIdDep(userId);
   }
 
   public Boolean existsDep(Long depUserId, Long userId)
   {
     return this.dao.existsDep(depUserId, userId);
   }
 
   public String add(DepUsers depUsers)
   {
     return this.dao.add(depUsers);
   }
 }

