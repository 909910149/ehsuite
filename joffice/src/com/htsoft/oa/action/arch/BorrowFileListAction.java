 package com.htsoft.oa.action.arch;
 
 import com.google.gson.Gson;
 import com.google.gson.reflect.TypeToken;
 import com.htsoft.core.command.QueryFilter;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.model.arch.BorrowFileList;
 import com.htsoft.oa.service.arch.BorrowFileListService;
 import flexjson.JSONSerializer;
 import flexjson.transformer.DateTransformer;
 import java.lang.reflect.Type;
 import java.util.List;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 
 public class BorrowFileListAction extends BaseAction
 {
 
   @Resource
   private BorrowFileListService borrowFileListService;
   private BorrowFileList borrowFileList;
   private Long listId;
 
   public Long getListId()
   {
     return this.listId;
   }
 
   public void setListId(Long listId) {
     this.listId = listId;
   }
 
   public BorrowFileList getBorrowFileList() {
     return this.borrowFileList;
   }
 
   public void setBorrowFileList(BorrowFileList borrowFileList) {
     this.borrowFileList = borrowFileList;
   }
 
   public String list()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     List list = this.borrowFileListService.getAll(filter);
 
     Type type = new TypeToken() {
     }.getType();
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(list.size()).append(",result:");
 
     JSONSerializer serializer = new JSONSerializer();
     serializer.exclude(new String[] { "*.class" }).transform(
       new DateTransformer("yyyy-MM-dd"), 
       new String[] { "createtime", 
       "archFond.createTime", "archFond.updateTime", 
       "archRoll.archFond.createTime", 
       "archRoll.archFond.updateTime", 
       "rollFile.archRoll.archFond.createTime", 
       "rollFile.archRoll.archFond.updateTime", 
       "archRoll.startTime", "archRoll.endTime", 
       "archRoll.setupTime", "archRoll.createTime", 
       "rollFile.archRoll.startTime", 
       "rollFile.archRoll.endTime", 
       "rollFile.archRoll.setupTime", 
       "rollFile.archRoll.createTime", 
       "rollFile.createTime", "rollFile.fileTime", 
       "borrowRecord.borrowDate", "borrowRecord.checkDate", 
       "borrowRecord.returnDate", 
       "borrowRecord.appUser.accessionTime" });
 
     buff.append(serializer.serialize(list));
     buff.append("}");
 
     this.jsonString = buff.toString();
 
     return "success";
   }
 
   public String listCheck()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     List list = this.borrowFileListService.getAll(filter);
 
     Type type = new TypeToken() {
     }.getType();
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(filter.getPagingBean().getTotalItems()).append(
       ",result:");
 
     JSONSerializer serializer = new JSONSerializer();
     serializer.exclude(new String[] { "*.class" }).transform(
       new DateTransformer("yyyy-MM-dd"), 
       new String[] { "createtime", 
       "archFond.createTime", "archFond.updateTime", 
       "archRoll.archFond.createTime", 
       "archRoll.archFond.updateTime", 
       "rollFile.archRoll.archFond.createTime", 
       "rollFile.archRoll.archFond.updateTime", 
       "archRoll.startTime", "archRoll.endTime", 
       "archRoll.setupTime", "archRoll.createTime", 
       "rollFile.archRoll.startTime", 
       "rollFile.archRoll.endTime", 
       "rollFile.archRoll.setupTime", 
       "rollFile.archRoll.createTime", 
       "rollFile.createTime", "rollFile.fileTime", 
       "borrowRecord.borrowDate", "borrowRecord.checkDate", 
       "borrowRecord.returnDate", 
       "borrowRecord.appUser.accessionTime" });
 
     buff.append(serializer.serialize(list));
     buff.append("}");
 
     this.jsonString = buff.toString();
 
     return "success";
   }
 
   public String multiDel()
   {
     String[] ids = getRequest().getParameterValues("ids");
     if (ids != null) {
       for (String id : ids) {
         this.borrowFileListService.remove(new Long(id));
       }
     }
 
     this.jsonString = "{success:true}";
 
     return "success";
   }
 
   public String get()
   {
     BorrowFileList borrowFileList = (BorrowFileList)this.borrowFileListService.get(this.listId);
 
     Gson gson = new Gson();
 
     StringBuffer sb = new StringBuffer("{success:true,data:");
     sb.append(gson.toJson(borrowFileList));
     sb.append("}");
     setJsonString(sb.toString());
 
     return "success";
   }
 
   public String save()
   {
     this.borrowFileListService.save(this.borrowFileList);
     setJsonString("{success:true}");
     return "success";
   }
 }

