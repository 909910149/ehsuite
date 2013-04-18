 package com.htsoft.oa.action.arch;
 
 import com.google.gson.Gson;
 import com.google.gson.reflect.TypeToken;
 import com.htsoft.core.command.QueryFilter;
 import com.htsoft.core.util.BeanUtil;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.model.system.GlobalType;
 import com.htsoft.oa.service.system.GlobalTypeService;
 import java.lang.reflect.Type;
 import java.util.List;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 import org.apache.commons.lang.StringUtils;
 import org.apache.commons.logging.Log;
 
 public class ArchTypeAction extends BaseAction
 {
 
   @Resource
   private GlobalTypeService globalTypeService;
   private GlobalType globalType;
   private Long proTypeId;
   private String catKey = "AR_FD";
 
   public String getCatKey() {
     return this.catKey;
   }
 
   public void setCatKey(String catKey) {
     this.catKey = catKey;
   }
 
   public Long getProTypeId() {
     return this.proTypeId;
   }
 
   public void setProTypeId(Long proTypeId) {
     this.proTypeId = proTypeId;
   }
 
   public GlobalType getGlobalType() {
     return this.globalType;
   }
 
   public void setGlobalType(GlobalType globalType) {
     this.globalType = globalType;
   }
 
   public String sub()
   {
     Long parentId = null;
     String sParentId = getRequest().getParameter("parentId");
     if (StringUtils.isNotEmpty(sParentId)) {
       parentId = new Long(sParentId);
     }
     List typeList = this.globalTypeService.getByParentIdCatKey(parentId, this.catKey);
 
     Type type = new TypeToken() {  }
     .getType();
     StringBuffer buff = new StringBuffer("{success:true,result:");
 
     Gson gson = new Gson();
     buff.append(gson.toJson(typeList, type));
     buff.append("}");
 
     this.jsonString = buff.toString();
 
     return "success";
   }
 
   public String mulSave() {
     String data = getRequest().getParameter("data");
 
     this.logger.info("data:" + data);
 
     if (StringUtils.isNotEmpty(data)) {
       Gson gson = new Gson();
       GlobalType[] types = (GlobalType[])gson.fromJson(data,com.htsoft.oa.model.system.GlobalType[].class);
 
       for (int i = 0; i < types.length; i++) {
         GlobalType newType = (GlobalType)this.globalTypeService.get(types[i].getProTypeId());
         try {
           BeanUtil.copyNotNullProperties(newType, types[i]);
           newType.setSn(Integer.valueOf(i + 1));
           this.globalTypeService.save(newType);
         } catch (Exception ex) {
           this.logger.error(ex.getMessage());
         }
       }
     }
 
     this.jsonString = "{success:true}";
     return "success";
   }
 
   public String tree()
   {
     Long rootId = new Long(getRequest().getParameter("rootId"));
     GlobalType globalType = (GlobalType)this.globalTypeService.get(rootId);
     StringBuffer buff = new StringBuffer("[{id:'" + globalType.getProTypeId() + "',text:'" + globalType.getTypeName() + "',expanded:true,children:[");
     List<GlobalType> typeList = this.globalTypeService.getByParentIdCatKey(rootId, this.catKey);
     for (GlobalType type : typeList) {
       buff.append("{id:'" + type.getProTypeId()).append("',text:'" + type.getTypeName()).append("',");
       buff.append(getChildType(type.getProTypeId(), this.catKey));
     }
     if (!typeList.isEmpty()) {
       buff.deleteCharAt(buff.length() - 1);
     }
     buff.append("]}]");
     setJsonString(buff.toString());
 
     this.logger.info("tree json:" + buff.toString());
     return "success";
   }
 
   public String proTree()
   {
     StringBuffer buff = new StringBuffer("[{id:'0',text:'总分类',expanded:true,children:[");
     List<GlobalType> typeList = this.globalTypeService.getByParentIdCatKey(new Long(0L), this.catKey);
     for (GlobalType type : typeList) {
       buff.append("{id:'" + type.getProTypeId()).append("',text:'" + type.getTypeName()).append("',");
       buff.append("leaf:true,expanded:true},");
     }
     if (!typeList.isEmpty()) {
       buff.deleteCharAt(buff.length() - 1);
     }
     buff.append("]}]");
     setJsonString(buff.toString());
 
     this.logger.info("tree json:" + buff.toString());
     return "success";
   }
   public String getChildType(Long parentId, String catKey) {
     StringBuffer buff = new StringBuffer();
     List<GlobalType> typeList = this.globalTypeService.getByParentIdCatKey(parentId, catKey);
     if (typeList.size() == 0) {
       buff.append("leaf:true,expanded:true},");
       return buff.toString();
     }
     buff.append("expanded:true,children:[");
     for (GlobalType type : typeList) {
       buff.append("{id:'" + type.getProTypeId()).append("',text:'" + type.getTypeName()).append("',");
       buff.append(getChildType(type.getProTypeId(), catKey));
     }
     buff.deleteCharAt(buff.length() - 1);
     buff.append("]},");
     return buff.toString();
   }
 
   public String list()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     List list = this.globalTypeService.getAll(filter);
 
     Type type = new TypeToken() {  }
     .getType();
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(filter.getPagingBean().getTotalItems()).append(",result:");
 
     Gson gson = new Gson();
     buff.append(gson.toJson(list, type));
     buff.append("}");
 
     this.jsonString = buff.toString();
 
     return "success";
   }
 
   public String multiDel()
   {
     String[] ids = getRequest().getParameterValues("ids");
     if (ids != null) {
       for (String id : ids)
       {
         this.globalTypeService.mulDel(new Long(id));
       }
     }
 
     this.jsonString = "{success:true}";
 
     return "success";
   }
 
   public String get()
   {
     GlobalType globalType = (GlobalType)this.globalTypeService.get(this.proTypeId);
 
     Gson gson = new Gson();
 
     StringBuffer sb = new StringBuffer("{success:true,data:");
     sb.append(gson.toJson(globalType));
     sb.append("}");
     setJsonString(sb.toString());
 
     return "success";
   }
 
   public String save()
   {
     if (this.globalType.getProTypeId() != null) {
       GlobalType orgGlobalType = (GlobalType)this.globalTypeService.get(this.globalType.getProTypeId());
       try {
         BeanUtil.copyNotNullProperties(orgGlobalType, this.globalType);
         this.globalTypeService.save(orgGlobalType);
       } catch (Exception ex) {
         this.logger.error(ex.getMessage());
       }
     }
     else {
       String parentPath = "0.";
       int depth = 1;
       if ((this.globalType.getParentId() != null) && (this.globalType.getParentId().longValue() != 0L)) {
         GlobalType parentType = (GlobalType)this.globalTypeService.get(this.globalType.getParentId());
         if (parentType != null) {
           parentPath = parentType.getPath();
           depth = parentType.getDepth().intValue() + 1;
         }
       }
       this.globalType.setDepth(Integer.valueOf(depth));
 
       Integer sn = this.globalTypeService.getCountsByParentId(this.globalType.getParentId());
       this.globalType.setSn(Integer.valueOf(sn.intValue() + 1));
       this.globalTypeService.save(this.globalType);
 
       this.globalType.setPath(parentPath + this.globalType.getProTypeId() + ".");
 
       this.globalTypeService.save(this.globalType);
     }
 
     setJsonString("{success:true}");
     return "success";
   }
 }

