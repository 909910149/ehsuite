 package com.htsoft.core.service.impl;
 
 import com.htsoft.core.command.QueryFilter;
 import com.htsoft.core.dao.DynamicDao;
 import com.htsoft.core.service.DynamicService;
 import com.htsoft.core.web.paging.PagingBean;
 import java.io.Serializable;
 import java.util.List;
 
 public class DynamicServiceImpl
   implements DynamicService
 {
   private DynamicDao dynamicDao;
 
   public DynamicServiceImpl()
   {
   }
 
   public DynamicServiceImpl(DynamicDao dao)
   {
     this.dynamicDao = dao;
   }
 
   public Object save(Object entity)
   {
     return this.dynamicDao.save(entity);
   }
 
   public Object merge(Object entity)
   {
     return this.dynamicDao.merge(entity);
   }
 
   public Object get(Serializable id)
   {
     return this.dynamicDao.get(id);
   }
 
   public void remove(Serializable id)
   {
     this.dynamicDao.remove(id);
   }
 
   public void remove(Object entity)
   {
     this.dynamicDao.remove(entity);
   }
 
   public void evict(Object entity)
   {
     this.dynamicDao.evict(entity);
   }
 
   public List<Object> getAll()
   {
     return this.dynamicDao.getAll();
   }
 
   public List<Object> getAll(PagingBean pb)
   {
     return this.dynamicDao.getAll(pb);
   }
 
   public List<Object> getAll(QueryFilter filter)
   {
     return this.dynamicDao.getAll(filter);
   }
 }

