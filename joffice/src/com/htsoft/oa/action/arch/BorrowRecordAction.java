 package com.htsoft.oa.action.arch;
 
 import com.google.gson.Gson;
 import com.google.gson.GsonBuilder;
 import com.google.gson.reflect.TypeToken;
 import com.htsoft.core.command.QueryFilter;
 import com.htsoft.core.util.BeanUtil;
 import com.htsoft.core.util.ContextUtil;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.model.arch.BorrowFileList;
 import com.htsoft.oa.model.arch.BorrowRecord;
 import com.htsoft.oa.model.system.AppUser;
 import com.htsoft.oa.service.arch.BorrowFileListService;
 import com.htsoft.oa.service.arch.BorrowRecordService;
 import java.lang.reflect.Type;
 import java.util.List;
 import java.util.Set;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 import org.apache.commons.lang.StringUtils;
 import org.apache.commons.logging.Log;
 
 public class BorrowRecordAction extends BaseAction
 {
 
   @Resource
   private BorrowRecordService borrowRecordService;
   private BorrowRecord borrowRecord;
 
   @Resource
   private BorrowFileListService borrowFileListService;
   private Long recordId;
 
   public Long getRecordId()
   {
     return this.recordId;
   }
 
   public void setRecordId(Long recordId) {
     this.recordId = recordId;
   }
 
   public BorrowRecord getBorrowRecord() {
     return this.borrowRecord;
   }
 
   public void setBorrowRecord(BorrowRecord borrowRecord) {
     this.borrowRecord = borrowRecord;
   }
 
   public String list()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     List list = this.borrowRecordService.getAll(filter);
 
     Type type = new TypeToken() {  }
     .getType();
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(filter.getPagingBean().getTotalItems()).append(",result:");
 
     Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd").create();
 
     buff.append(gson.toJson(list, type));
     buff.append("}");
 
     this.jsonString = buff.toString();
 
     return "success";
   }
 
   public String multiDel()
   {
     String[] ids = getRequest().getParameterValues("ids");
     if (ids != null) {
       for (String id : ids) {
         this.borrowRecordService.remove(new Long(id));
       }
     }
 
     this.jsonString = "{success:true}";
 
     return "success";
   }
 
   public String get()
   {
     BorrowRecord borrowRecord = (BorrowRecord)this.borrowRecordService.get(this.recordId);
 
     Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd").create();
 
     StringBuffer sb = new StringBuffer("{success:true,data:");
     sb.append(gson.toJson(borrowRecord));
     sb.append("}");
     setJsonString(sb.toString());
     this.logger.debug("sb:" + sb.toString());
     return "success";
   }
 
   public String save()
   {
     if (this.borrowRecord.getRecordId() == null) {
       this.borrowRecordService.save(this.borrowRecord);
     } else {
       BorrowRecord orgBorrowRecord = (BorrowRecord)this.borrowRecordService.get(this.borrowRecord.getRecordId());
       try {
         Set borrowFileLists = orgBorrowRecord.getBorrowFileLists();
         BeanUtil.copyNotNullProperties(orgBorrowRecord, this.borrowRecord);
         orgBorrowRecord.setBorrowFileLists(borrowFileLists);
         this.borrowRecordService.save(orgBorrowRecord);
       } catch (Exception ex) {
         this.logger.error(ex.getMessage());
       }
 
     }
 
     String params = getRequest().getParameter("params");
     if (StringUtils.isNotEmpty(params)) {
       Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
       BorrowFileList[] bfl = (BorrowFileList[])gson.fromJson(params, com.htsoft.oa.model.arch.BorrowFileList[].class);
       if ((bfl != null) && (bfl.length > 0)) {
         for (BorrowFileList l : bfl)
         {
           l.setRecordId(this.borrowRecord.getRecordId());
 
           this.borrowFileListService.save(l);
         }
 
       }
 
     }
 
     setJsonString("{success:true,recordId:" + this.borrowRecord.getRecordId() + 
       "}");
 
     return "success";
   }
 
   public String check() {
     BorrowRecord r = (BorrowRecord)this.borrowRecordService.get(this.borrowRecord.getRecordId());
     r.setReturnStatus(this.borrowRecord.getReturnStatus());
     r.setCheckId(ContextUtil.getCurrentUserId());
     r.setCheckName(ContextUtil.getCurrentUser().getUsername());
     this.borrowRecordService.save(r);
 
     setJsonString("{success:true,recordId:" + r.getRecordId() + 
       "}");
 
     return "success";
   }
 }

