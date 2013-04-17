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
 import com.htsoft.oa.model.arch.RollFile;
 import com.htsoft.oa.model.arch.RollFileList;
 import com.htsoft.oa.model.system.AppUser;
 import com.htsoft.oa.model.system.FileAttach;
 import com.htsoft.oa.service.arch.BorrowFileListService;
 import com.htsoft.oa.service.arch.BorrowRecordService;
 import com.htsoft.oa.service.arch.RollFileListService;
 import com.htsoft.oa.service.arch.RollFileService;
 import com.htsoft.oa.service.system.FileAttachService;
 import flexjson.JSONSerializer;
 import flexjson.transformer.DateTransformer;
 import java.lang.reflect.Type;
 import java.util.Date;
 import java.util.Iterator;
 import java.util.List;
 import java.util.Set;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 import org.apache.commons.lang.StringUtils;
 import org.apache.commons.logging.Log;
 
 public class RollFileAction extends BaseAction
 {
 
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
   private RollFile rollFile;
   private Long rollFileId;
 
   public Long getRollFileId()
   {
     return this.rollFileId;
   }
 
   public void setRollFileId(Long rollFileId) {
     this.rollFileId = rollFileId;
   }
 
   public RollFile getRollFile() {
     return this.rollFile;
   }
 
   public void setRollFile(RollFile rollFile) {
     this.rollFile = rollFile;
   }
 
   public String list()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     List list = this.rollFileService.getAll(filter);
 
     Type type = new TypeToken() {
     }.getType();
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(filter.getPagingBean().getTotalItems()).append(
       ",result:");
 
     JSONSerializer json = new JSONSerializer();
     json.transform(new DateTransformer("yyyy-MM-dd"), new String[] { 
       "fileTime", 
       "createTime", 
       "archFond.createTime", 
       "archFond.updateTime", 
       "archRoll.archFond.createTime", 
       "archRoll.archFond.updateTime", 
       "rollFile.archRoll.archFond.createTime", 
       "rollFile.archRoll.archFond.updateTime", 
       "archRoll.startTime", 
       "archRoll.endTime", 
       "archRoll.setupTime", 
       "archRoll.createTime", 
       "fileAttach.createtime" });
 
     buff.append(json.serialize(list));
     buff.append("}");
 
     this.jsonString = buff.toString();
     this.logger.debug(this.jsonString);
 
     return "success";
   }
 
   public String multiDel()
   {
     String[] ids = getRequest().getParameterValues("ids");
     if (ids != null) {
       for (String id : ids)
       {
         RollFile file = (RollFile)this.rollFileService.get(new Long(id));
 
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
 
     }
 
     this.jsonString = "{success:true}";
 
     return "success";
   }
 
   public String get()
   {
     RollFile rollFile = (RollFile)this.rollFileService.get(this.rollFileId);
 
     StringBuffer sb = new StringBuffer("{success:true,data:");
 
     JSONSerializer json = new JSONSerializer();
     json.transform(new DateTransformer("yyyy-MM-dd"), new String[] { 
       "createTime", "updateTime", "fileTime" });
     sb.append(json.serialize(rollFile));
     sb.append("}");
     setJsonString(sb.toString());
 
     return "success";
   }
 
   public String save()
   {
     Long rid = null;
     if (this.rollFile.getRollFileId() == null) {
       this.rollFileService.save(this.rollFile);
       rid = this.rollFile.getRollFileId();
     } else {
       RollFile orgRollFile = (RollFile)this.rollFileService.get(this.rollFile.getRollFileId());
       try {
         Set rollFileList = orgRollFile.getRollFileLists();
         Set borrowFileList = orgRollFile.getBorrowFileList();
         BeanUtil.copyNotNullProperties(orgRollFile, this.rollFile);
         orgRollFile.setRollFileLists(rollFileList);
         orgRollFile.setBorrowFileList(borrowFileList);
         this.rollFileService.save(orgRollFile);
         rid = orgRollFile.getRollFileId();
       } catch (Exception ex) {
         this.logger.error(ex.getMessage());
       }
 
     }
 
     String params = getRequest().getParameter("params");
     if (StringUtils.isNotEmpty(params)) {
       Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
       RollFileList[] rls = (RollFileList[])gson.fromJson(params, com.htsoft.oa.model.arch.RollFileList[].class);
       if ((rls != null) && (rls.length > 0)) {
         for (RollFileList rl : rls) {
           rl.setRollFileId(rid);
           this.rollFileListService.save(rl);
         }
 
       }
 
     }
 
     setJsonString("{success:true,rollFileId:" + this.rollFile.getRollFileId() + 
       "}");
     return "success";
   }
 
   public String updateDownLoad()
   {
     String params = getRequest().getParameter("params");
     this.logger.debug("params=" + params);
     if (StringUtils.isNotEmpty(params)) {
       Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
       RollFileList[] rls = (RollFileList[])gson.fromJson(params, com.htsoft.oa.model.arch.RollFileList[].class);
       if ((rls != null) && (rls.length > 0)) {
         for (RollFileList rl : rls)
         {
           this.rollFileListService.save(rl);
         }
 
       }
 
     }
 
     setJsonString("{success:true}");
     return "success";
   }
 
   public String tidy()
   {
     Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd")
       .create();
     String params = getRequest().getParameter("params");
 
     if (StringUtils.isNotEmpty(params)) {
       RollFile[] rfs = (RollFile[])gson.fromJson(params, com.htsoft.oa.model.arch.RollFile[].class);
 
       if ((rfs != null) && (rfs.length > 0)) {
         for (RollFile rollFile : rfs) {
           RollFile orgRollFile = (RollFile)this.rollFileService.get(rollFile.getRollFileId());
           try {
             Set rollFileList = orgRollFile.getRollFileLists();
             Set borrowFileList = orgRollFile.getBorrowFileList();
             BeanUtil.copyNotNullProperties(orgRollFile, rollFile);
             orgRollFile.setRollFileLists(rollFileList);
             orgRollFile.setBorrowFileList(borrowFileList);
             orgRollFile.setTidyName(ContextUtil.getCurrentUser().getFullname());
             orgRollFile.setTidyTime(new Date());
             this.rollFileService.save(orgRollFile);
           }
           catch (Exception ex) {
             this.logger.error(ex.getMessage());
           }
         }
       }
 
     }
 
     setJsonString("{success:true}");
     return "success";
   }
 }

