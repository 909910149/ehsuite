 package com.htsoft.oa.service.system.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.oa.dao.system.RelativeJobDao;
 import com.htsoft.oa.model.system.RelativeJob;
 import com.htsoft.oa.service.system.RelativeJobService;
 import java.util.List;
 
 public class RelativeJobServiceImpl extends BaseServiceImpl<RelativeJob>
   implements RelativeJobService
 {
   private RelativeJobDao dao;
 
   public RelativeJobServiceImpl(RelativeJobDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public List<RelativeJob> findByParentId(Long parentId)
   {
     return this.dao.findByParentId(parentId);
   }
 }

