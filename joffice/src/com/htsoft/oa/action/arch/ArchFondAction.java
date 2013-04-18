 package com.htsoft.oa.action.arch;
 
 import com.google.gson.reflect.TypeToken;
 import com.htsoft.core.command.QueryFilter;
 import com.htsoft.core.util.BeanUtil;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.model.arch.ArchFond;
 import com.htsoft.oa.model.arch.ArchRoll;
 import com.htsoft.oa.model.arch.BorrowFileList;
 import com.htsoft.oa.model.arch.BorrowRecord;
 import com.htsoft.oa.model.arch.RollFile;
 import com.htsoft.oa.model.arch.RollFileList;
 import com.htsoft.oa.model.system.FileAttach;
 import com.htsoft.oa.service.arch.ArchFondService;
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
 
 public class ArchFondAction extends BaseAction
 {
 
   @Resource
   private ArchFondService archFondService;
 
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
   private ArchFond archFond;
   private Long archFondId;
 
   public Long getArchFondId()
   {
     return this.archFondId;
   }
 
   public void setArchFondId(Long archFondId) {
     this.archFondId = archFondId;
   }
 
   public ArchFond getArchFond() {
     return this.archFond;
   }
 
   public void setArchFond(ArchFond archFond) {
     this.archFond = archFond;
   }
 
   public String list()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     List list = this.archFondService.getAll(filter);
 
     Type type = new TypeToken() {  }
     .getType();
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(filter.getPagingBean().getTotalItems()).append(",result:");
 
     JSONSerializer json = new JSONSerializer();
     json.transform(new DateTransformer("yyyy-MM-dd"), new String[] { "createTime", "updateTime" });
     buff.append(json.serialize(list));
     buff.append("}");
 
     this.jsonString = buff.toString();
 
     return "success";
   }
 
   public String listRollTree()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     List<ArchRoll> rollList = this.archRollService.getAll(filter);
     if ((rollList != null) && (rollList.size() > 0)) {
       ArchRoll archRoll = (ArchRoll)rollList.get(0);
 
       StringBuffer buff = new StringBuffer("[{id:'0',text:'" + archRoll.getAfNo() + "',expanded:true,children:[");
 
       if (rollList.size() > 0) {
         for (ArchRoll roll : rollList)
         {
           buff.append("{id:'" + roll.getRollNo()).append("',text:'" + roll.getRollNo()).append("',allowChildren:false,leaf :true},");
         }
         buff.deleteCharAt(buff.length() - 1);
       }
 
       buff.append("]}]");
       this.jsonString = buff.toString();
     }
 
     return "success";
   }
 
   public String multiDel()
   {
     String[] ids = getRequest().getParameterValues("ids");
     if (ids != null) {
       for (String id : ids) {
         this.archFond = ((ArchFond)this.archFondService.get(new Long(id)));
 
         Set borrowFileList_fond = this.archFond.getBorrowFileList();
         Iterator borrows_fond = borrowFileList_fond.iterator();
         while (borrows_fond.hasNext()) {
           BorrowFileList borr_fond = (BorrowFileList)borrows_fond.next();
           this.borrowFileListService.remove(borr_fond);
           this.borrowFileListService.flush();
 
           BorrowRecord record_fond = borr_fond.getBorrowRecord();
           Set list_fond = record_fond.getBorrowFileLists();
           if ((list_fond == null) || (list_fond.size() == 0)) {
             this.borrowRecordService.remove(record_fond);
           }
 
         }
 
         Set archRolls = this.archFond.getArchRolls();
         Iterator rolls = archRolls.iterator();
         while (rolls.hasNext()) {
           ArchRoll archRoll = (ArchRoll)rolls.next();
           Set borrowFileList_roll = archRoll.getBorrowFileList();
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
 
           Set rollFiles = archRoll.getRollFiles();
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
           this.archRollService.remove(archRoll);
           this.archRollService.flush();
         }
         this.archFondService.remove(this.archFond);
         this.archFondService.flush();
       }
     }
 
     this.jsonString = "{success:true}";
 
     return "success";
   }
 
   public String get()
   {
     ArchFond archFond = (ArchFond)this.archFondService.get(this.archFondId);
 
     StringBuffer sb = new StringBuffer("{success:true,data:");
 
     JSONSerializer json = new JSONSerializer();
     json.transform(new DateTransformer("yyyy-MM-dd"), new String[] { "createTime", "updateTime" });
     sb.append(json.serialize(archFond));
     sb.append("}");
     setJsonString(sb.toString());
 
     return "success";
   }
 
   public String save()
   {
     if (this.archFond.getArchFondId() == null) {
       this.archFondService.save(this.archFond);
     } else {
       ArchFond orgArchFond = (ArchFond)this.archFondService.get(this.archFond.getArchFondId());
       try {
         Set archRollSet = orgArchFond.getArchRolls();
         Set borrowFileList = orgArchFond.getBorrowFileList();
         BeanUtil.copyNotNullProperties(orgArchFond, this.archFond);
         orgArchFond.setArchRolls(archRollSet);
         orgArchFond.setBorrowFileList(borrowFileList);
         this.archFondService.save(orgArchFond);
       } catch (Exception ex) {
         this.logger.error(ex.getMessage());
       }
     }
     setJsonString("{success:true}");
     return "success";
   }
 }

