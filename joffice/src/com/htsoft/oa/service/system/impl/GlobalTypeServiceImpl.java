 package com.htsoft.oa.service.system.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.oa.dao.system.GlobalTypeDao;
 import com.htsoft.oa.model.system.AppUser;
 import com.htsoft.oa.model.system.GlobalType;
 import com.htsoft.oa.service.system.GlobalTypeService;
 import java.io.PrintStream;
 import java.util.ArrayList;
 import java.util.List;
 
 public class GlobalTypeServiceImpl extends BaseServiceImpl<GlobalType>
   implements GlobalTypeService
 {
   private GlobalTypeDao dao;
 
   public GlobalTypeServiceImpl(GlobalTypeDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public List<GlobalType> getByParentIdCatKey(Long parentId, String catKey)
   {
     return this.dao.getByParentIdCatKey(parentId, catKey);
   }
 
   public Integer getCountsByParentId(Long parentId)
   {
     return this.dao.getCountsByParentId(parentId);
   }
 
   public void mulDel(Long proTypeId)
   {
     GlobalType globalType = (GlobalType)get(proTypeId);
     this.dao.evict(globalType);
 
     List<GlobalType> subList = this.dao.getByPath(globalType.getPath());
 
     for (GlobalType gt : subList)
       this.dao.remove(gt);
   }
 
   public List<GlobalType> getByParentIdCatKeyUserId(Long parentId, String catKey, Long userId)
   {
     return this.dao.getByParentIdCatKeyUserId(parentId, catKey, userId);
   }
 
   public List<GlobalType> getByRightsCatKey(AppUser curUser, String catKey)
   {
     return this.dao.getByRightsCatKey(curUser, catKey);
   }
 
   public List<GlobalType> getByCatKeyUserId(AppUser curUser, String catKey)
   {
     List<GlobalType> typeList = null;
     if (curUser.isSupperManage())
       typeList = getByParentIdCatKey(new Long(0L), catKey);
     else
       typeList = getByRightsCatKey(curUser, catKey);
     List record = new ArrayList();
     for (GlobalType type : typeList) {
       if (!record.contains(type)) {
         String str = "";
         for (int i = 0; i < type.getDepth().intValue(); i++) {
           str = str + "—";
         }
         type.setTypeName(str + type.getTypeName());
         record.add(type);
         getTypeByRights(type.getProTypeId(), catKey, record);
       }
     }
     return record;
   }
 
   private void getTypeByRights(Long parentId, String catKey, List<GlobalType> record)
   {
     List<GlobalType> typeList = getByParentIdCatKey(parentId, catKey);
     for (GlobalType type : typeList)
       if (!record.contains(type)) {
         String str = "";
         for (int i = 0; i < type.getDepth().intValue(); i++) {
           str = str + "—";
         }
         type.setTypeName(str + type.getTypeName());
         record.add(type);
         getTypeByRights(type.getProTypeId(), catKey, record);
       } else {
         System.out.print("已经存在" + type.getTypeName());
       }
   }
 }

