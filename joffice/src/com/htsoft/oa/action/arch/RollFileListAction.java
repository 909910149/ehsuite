 package com.htsoft.oa.action.arch;
 
 import com.google.gson.reflect.TypeToken;
 import com.htsoft.core.command.QueryFilter;
 import com.htsoft.core.util.BeanUtil;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.model.arch.RollFileList;
 import com.htsoft.oa.model.system.FileAttach;
 import com.htsoft.oa.service.arch.RollFileListService;
 import com.htsoft.oa.service.system.FileAttachService;
 import flexjson.JSONSerializer;
 import flexjson.transformer.DateTransformer;
 import java.lang.reflect.Type;
 import java.util.List;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 import org.apache.commons.logging.Log;
 
 public class RollFileListAction extends BaseAction
 {
 
   @Resource
   private RollFileListService rollFileListService;
   private RollFileList rollFileList;
 
   @Resource
   private FileAttachService fileAttachService;
   private Long listId;
 
   public Long getListId()
   {
     return this.listId;
   }
 
   public void setListId(Long listId) {
     this.listId = listId;
   }
 
   public RollFileList getRollFileList() {
     return this.rollFileList;
   }
 
   public void setRollFileList(RollFileList rollFileList) {
     this.rollFileList = rollFileList;
   }
 
   public String list()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     List list = this.rollFileListService.getAll(filter);
 
     Type type = new TypeToken() {
     }.getType();
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(filter.getPagingBean().getTotalItems()).append(
       ",result:");
 
     JSONSerializer serializer = new JSONSerializer();
     serializer
       .exclude(new String[] { "rollFile", "fileAttach.createTime" })
       .transform(new DateTransformer("yyyy-MM-dd"), 
       new String[] { "fileAttach.createTime", "createTime" });
     buff.append(serializer.serialize(list));
 
     buff.append("}");
 
     this.jsonString = buff.toString();
 
     return "success";
   }
 
   public String multiDel()
   {
     String[] listIds = getRequest().getParameterValues("listIds");
     FileAttach fileAttach;
     if ((listIds != null) && (listIds.length > 0)) {
       for (String id : listIds) {
         if ((id != null) && (!id.equals(""))) {
           this.rollFileList = ((RollFileList)this.rollFileListService.get(new Long(id)));
           fileAttach = this.rollFileList.getFileAttach();
 
           this.rollFileListService.remove(this.rollFileList);
           this.fileAttachService.removeByPath(fileAttach.getFilePath());
         }
       }
     }
     String[] fileIds = getRequest().getParameterValues("fileIds");
     if ((fileIds != null) && (fileIds.length > 0)) {
       for (String id : fileIds) {
         if ((id != null) && (!id.equals(""))) {
           fileAttach = (FileAttach)this.fileAttachService.get(new Long(id));
 
           this.fileAttachService.removeByPath(fileAttach.getFilePath());
         }
       }
     }
 
     this.jsonString = "{success:true}";
 
     return "success";
   }
 
   public String get()
   {
     RollFileList rollFileList = (RollFileList)this.rollFileListService.get(this.listId);
 
     StringBuffer sb = new StringBuffer("{success:true,data:");
 
     JSONSerializer serializer = new JSONSerializer();
     serializer.exclude(new String[] { "rollFile" }).transform(
       new DateTransformer("yyyy-MM-dd"), 
       new String[] { "fileAttach.createtime" });
     sb.append(serializer.serialize(rollFileList));
     sb.append("}");
     setJsonString(sb.toString());
 
     return "success";
   }
 
   public String save()
   {
     if (this.rollFileList.getListId() == null) {
       this.rollFileListService.save(this.rollFileList);
     } else {
       RollFileList orgRollFileList = (RollFileList)this.rollFileListService.get(this.rollFileList
         .getListId());
       try {
         BeanUtil.copyNotNullProperties(orgRollFileList, this.rollFileList);
         this.rollFileListService.save(orgRollFileList);
       } catch (Exception ex) {
         this.logger.error(ex.getMessage());
       }
     }
     setJsonString("{success:true}");
     return "success";
   }
 }

