 package com.htsoft.oa.action.arch;
 
 import com.google.gson.reflect.TypeToken;
 import com.htsoft.core.command.QueryFilter;
 import com.htsoft.core.util.BeanUtil;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.model.arch.ArchRoll;
 import com.htsoft.oa.model.arch.BorrowFileList;
 import com.htsoft.oa.model.arch.BorrowRecord;
 import com.htsoft.oa.model.arch.RollFile;
 import com.htsoft.oa.model.arch.RollFileList;
 import com.htsoft.oa.model.system.FileAttach;
 import com.htsoft.oa.service.arch.ArchRollService;
 import com.htsoft.oa.service.arch.BorrowFileListService;
 import com.htsoft.oa.service.arch.BorrowRecordService;
 import com.htsoft.oa.service.arch.RollFileListService;
 import com.htsoft.oa.service.arch.RollFileService;
 import com.htsoft.oa.service.system.FileAttachService;
 import flexjson.JSONSerializer;
 import flexjson.transformer.DateTransformer;
 import java.lang.reflect.Type;
 import java.util.Iterator;
 import java.util.List;
 import java.util.Set;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 import org.apache.commons.logging.Log;
 
 public class ArchRollAction extends BaseAction
 {
 
   @Resource
   private ArchRollService archRollService;
 
   @Resource
   private RollFileService rollFileService;
 
   @Resource
   private RollFileListService rollFileListService;
 
   @Resource
   private FileAttachService fileAttachService;
 
   @Resource
   private BorrowRecordService borrowRecordService;
 
   @Resource
   private BorrowFileListService borrowFileListService;
   private ArchRoll archRoll;
   private Long rollId;
 
   public Long getRollId()
   {
     return this.rollId;
   }
 
   public void setRollId(Long rollId) {
     this.rollId = rollId;
   }
 
   public ArchRoll getArchRoll() {
     return this.archRoll;
   }
 
   public void setArchRoll(ArchRoll archRoll) {
     this.archRoll = archRoll;
   }
 
   public String list()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     List list = this.archRollService.getAll(filter);
 
     Type type = new TypeToken() {
     }.getType();
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(filter.getPagingBean().getTotalItems()).append(
       ",result:");
 
     JSONSerializer json = new JSONSerializer();
     json.transform(new DateTransformer("yyyy-MM-dd"), new String[] { 
       "createTime", "updateTime", "setupTime", "endTime", "startTime" });
     buff.append(json.serialize(list));
     buff.append("}");
 
     this.jsonString = buff.toString();
 
     return "success";
   }
 
   public String multiDel()
   {
     String[] ids = getRequest().getParameterValues("ids");
     if (ids != null) {
       for (String id : ids) {
         this.archRoll = ((ArchRoll)this.archRollService.get(new Long(id)));
         Set borrowFileList_roll = this.archRoll.getBorrowFileList();
         Iterator borrows_roll = borrowFileList_roll.iterator();
         while (borrows_roll.hasNext()) {
           BorrowFileList borr_roll = (BorrowFileList)borrows_roll.next();
           this.borrowFileListService.remove(borr_roll);
           this.borrowFileListService.flush();
 
           BorrowRecord record_roll = borr_roll.getBorrowRecord();
           Set list_roll = record_roll.getBorrowFileLists();
           if ((list_roll == null) || (list_roll.size() == 0)) {
             this.borrowRecordService.remove(record_roll);
           }
 
         }
 
         Set rollFiles = this.archRoll.getRollFiles();
         Iterator files = rollFiles.iterator();
         while (files.hasNext()) {
           RollFile file = (RollFile)files.next();
 
           Set rollFileLists = file.getRollFileLists();
           Iterator lists = rollFileLists.iterator();
           while (lists.hasNext()) {
             RollFileList list = (RollFileList)lists.next();
             FileAttach fileAttach = list.getFileAttach();
             this.rollFileListService.remove(list);
             this.rollFileListService.flush();
             this.fileAttachService.removeByPath(fileAttach.getFilePath());
           }
 
           Set borrowFileList_file = file.getBorrowFileList();
           Iterator borrows_file = borrowFileList_file.iterator();
           while (borrows_file.hasNext()) {
             BorrowFileList borr_file = (BorrowFileList)borrows_file.next();
             this.borrowFileListService.remove(borr_file);
             this.borrowFileListService.flush();
 
             BorrowRecord record_file = borr_file.getBorrowRecord();
             Set list_file = record_file.getBorrowFileLists();
             if ((list_file == null) || (list_file.size() == 0)) {
               this.borrowRecordService.remove(record_file);
             }
 
           }
 
           this.rollFileService.remove(file);
           this.rollFileService.flush();
         }
         this.archRollService.remove(this.archRoll);
         this.archRollService.flush();
       }
 
     }
 
     this.jsonString = "{success:true}";
 
     return "success";
   }
 
   public String get()
   {
     ArchRoll archRoll = (ArchRoll)this.archRollService.get(this.rollId);
 
     StringBuffer sb = new StringBuffer("{success:true,data:");
 
     JSONSerializer json = new JSONSerializer();
     json.transform(new DateTransformer("yyyy-MM-dd"), new String[] { 
       "createTime", "updateTime", "setupTime", "endTime", "startTime" });
 
     sb.append(json.serialize(archRoll));
 
     sb.append("}");
     setJsonString(sb.toString());
 
     return "success";
   }
 
   public String save()
   {
     if (this.archRoll.getRollId() == null) {
       this.archRollService.save(this.archRoll);
     } else {
       ArchRoll orgArchRoll = (ArchRoll)this.archRollService.get(this.archRoll.getRollId());
       try {
         Set rollFileSet = orgArchRoll.getRollFiles();
         Set borrowFileList = orgArchRoll.getBorrowFileList();
         BeanUtil.copyNotNullProperties(orgArchRoll, this.archRoll);
         orgArchRoll.setRollFiles(rollFileSet);
         orgArchRoll.setBorrowFileList(borrowFileList);
         this.archRollService.save(orgArchRoll);
       } catch (Exception ex) {
         this.logger.error(ex.getMessage());
       }
     }
     setJsonString("{success:true}");
     return "success";
   }
 }

