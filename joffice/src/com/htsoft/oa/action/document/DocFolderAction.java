 package com.htsoft.oa.action.document;
 
 import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.log.Action;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.document.DocFolder;
import com.htsoft.oa.model.document.DocPrivilege;
import com.htsoft.oa.model.document.Document;
import com.htsoft.oa.model.document.DocumentFile;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.model.system.FileAttach;
import com.htsoft.oa.service.document.DocFolderService;
import com.htsoft.oa.service.document.DocPrivilegeService;
import com.htsoft.oa.service.document.DocumentService;
 
 public class DocFolderAction extends BaseAction
 {
 
   @Resource
   private DocFolderService docFolderService;
 
   @Resource
   private DocPrivilegeService docPrivilegeService;
 
   @Resource
   private DocumentService documentService;
   private DocFolder docFolder;
   private Integer folderNum = Integer.valueOf(0);
   private Integer documentNum = Integer.valueOf(0);
   private Integer attachsNum = Integer.valueOf(0);
   private double filesize = 0.0D;
   private Long folderId;
   private static Integer ALL_RIGHT = Integer.valueOf(7);
   private static Integer NOT_RIGHT = Integer.valueOf(0);
   private static Long ISPARENT = Long.valueOf(0L);
 
   public Long getFolderId() {
     return this.folderId;
   }
 
   public void setFolderId(Long folderId) {
     this.folderId = folderId;
   }
 
   public DocFolder getDocFolder() {
     return this.docFolder;
   }
 
   public void setDocFolder(DocFolder docFolder) {
     this.docFolder = docFolder;
   }
 
   public String list()
   {
     String method = getRequest().getParameter("method");
     StringBuffer buff = new StringBuffer();
     boolean flag = false;
     if (StringUtils.isNotEmpty(method)) {
       buff.append("[");
       flag = true;
     } else {
       buff.append("[{id:'0',text:'我的文件夹',expanded:true,children:[");
     }
     Long curUserId = ContextUtil.getCurrentUserId();
     List<DocFolder> docList = this.docFolderService.getUserFolderByParentId(curUserId, Long.valueOf(0L));
     for (DocFolder folder : docList) {
       buff.append("{id:'" + folder.getFolderId()).append("',expanded:true,text:'" + folder.getFolderName()).append("',");
       buff.append(findChildsFolder(curUserId, folder.getFolderId()));
     }
     if (!docList.isEmpty()) {
       buff.deleteCharAt(buff.length() - 1);
     }
     if (flag)
       buff.append("]");
     else {
       buff.append("]}]");
     }
     setJsonString(buff.toString());
     this.logger.info("tree json:" + buff.toString());
     return "success";
   }
 
   public String tree()
   {
     StringBuffer buff = new StringBuffer("[{id:'0',text:'知识目录',expanded:true,children:[");
     List<DocFolder> docList = this.docFolderService.getPublicFolderByParentId(Long.valueOf(0L));
     for (DocFolder folder : docList) {
       buff.append("{id:'" + folder.getFolderId()).append("',text:'" + folder.getFolderName()).append("',expanded:true,");
       buff.append(findChildsFolder(folder.getFolderId()));
     }
     if (!docList.isEmpty()) {
       buff.deleteCharAt(buff.length() - 1);
     }
     buff.append("]}]");
     setJsonString(buff.toString());
 
     this.logger.info("tree json:" + buff.toString());
     return "success";
   }
 
   public String select()
   {
     AppUser appUser = ContextUtil.getCurrentUser();
     StringBuffer buff = new StringBuffer("[{id:'0',text:'公共文件夹',expanded:true,children:[");
     List<DocFolder> docList = this.docFolderService.getPublicFolderByParentId(Long.valueOf(0L));
     for (DocFolder docFolder : docList) {
       List<Integer> rights = this.docPrivilegeService.getRightsByFolder(appUser, docFolder.getFolderId());
       Integer right = NOT_RIGHT;
       for (Integer in : rights) {
         right = Integer.valueOf(right.intValue() | in.intValue());
       }
       Set roleRight = appUser.getRights();
       if (roleRight.contains("__ALL")) {
         right = ALL_RIGHT;
       }
       if (right == NOT_RIGHT) {
         buff.append("{id:'" + docFolder.getFolderId()).append("',disabled:true,text:'" + docFolder.getFolderName()).append("',expanded:true,");
         buff.append(findChildsFolderByRight(docFolder.getFolderId(), right, false));
       } else {
         buff.append("{id:'" + docFolder.getFolderId()).append("',text:'" + docFolder.getFolderName()).append("',expanded:true,");
         if (right == ALL_RIGHT)
           buff.append(findChildsFolderByRight(docFolder.getFolderId(), right, true));
         else {
           buff.append(findChildsFolderByRight(docFolder.getFolderId(), right, false));
         }
       }
     }
     if (!docList.isEmpty()) {
       buff.deleteCharAt(buff.length() - 1);
     }
     buff.append("]}]");
     setJsonString(buff.toString());
     return "success";
   }
 
   public String share()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     filter.addFilter("Q_isShared_SN_EQ", "1");
     List list = this.docFolderService.getAll(filter);
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
 
   public String findChildsFolder(Long userId, Long parentId)
   {
     StringBuffer sb = new StringBuffer();
     List<DocFolder> list = this.docFolderService.getUserFolderByParentId(userId, parentId);
     if (list.size() == 0) {
       sb.append("children:[],expanded:true},");
       return sb.toString();
     }
     sb.append("children:[");
     for (DocFolder folder : list) {
       sb.append("{id:'" + folder.getFolderId() + "',text:'" + folder.getFolderName() + "',expanded:true,");
       sb.append(findChildsFolder(userId, folder.getFolderId()));
     }
     sb.deleteCharAt(sb.length() - 1);
     sb.append("]},");
     return sb.toString();
   }
 
   public String findChildsFolder(Long parentId)
   {
     StringBuffer sb = new StringBuffer();
     List<DocFolder> list = this.docFolderService.getPublicFolderByParentId(parentId);
     if (list.size() == 0) {
       sb.append("leaf:true,expanded:true},");
       return sb.toString();
     }
     sb.append("children:[");
     for (DocFolder folder : list) {
       sb.append("{id:'" + folder.getFolderId() + "',text:'" + folder.getFolderName() + "',expanded:true,");
       sb.append(findChildsFolder(folder.getFolderId()));
     }
     sb.deleteCharAt(sb.length() - 1);
     sb.append("]},");
     return sb.toString();
   }
 
   public String findChildsFolderByRight(Long parentId, Integer right, boolean isAllRight)
   {
     StringBuffer sb = new StringBuffer();
     List<DocFolder> list = this.docFolderService.getPublicFolderByParentId(parentId);
     if (list.size() == 0) {
       sb.append("leaf:true,expanded:true},");
       return sb.toString();
     }
     sb.append("children:[");
     for (DocFolder folder : list) {
       Integer in = right;
       if (isAllRight) {
         in = ALL_RIGHT;
       }
       else if (in != NOT_RIGHT) {
         in = NOT_RIGHT;
         AppUser appUser = ContextUtil.getCurrentUser();
         List<Integer> rights = this.docPrivilegeService.getRightsByFolder(appUser, folder.getFolderId());
         for (Integer inte : rights) {
           in = Integer.valueOf(in.intValue() | inte.intValue());
         }
       }
 
       if (in == NOT_RIGHT) {
         sb.append("{id:'" + folder.getFolderId() + "',disabled:true,text:'" + folder.getFolderName() + "',expanded:true,");
         sb.append(findChildsFolderByRight(folder.getFolderId(), in, isAllRight));
       } else {
         sb.append("{id:'" + folder.getFolderId() + "',text:'" + folder.getFolderName() + "',expanded:true,");
         sb.append(findChildsFolderByRight(folder.getFolderId(), in, isAllRight));
       }
     }
     sb.deleteCharAt(sb.length() - 1);
     sb.append("]},");
     return sb.toString();
   }
 
   public String multiDel()
   {
     String[] ids = getRequest().getParameterValues("ids");
     if (ids != null) {
       for (String id : ids) {
         this.docFolderService.remove(new Long(id));
       }
     }
 
     this.jsonString = "{success:true}";
 
     return "success";
   }
 
   public String remove()
   {
     String folderId = getRequest().getParameter("folderId");
     if (StringUtils.isNotEmpty(folderId)) {
       DocFolder tmpFolder = (DocFolder)this.docFolderService.get(new Long(folderId));
       List<DocFolder> docFolderList = this.docFolderService.getFolderLikePath(tmpFolder.getPath());
 
       for (DocFolder folder : docFolderList) {
         List list = this.documentService.findByFolder(folder.getPath());
         if (list.size() > 0) {
           this.jsonString = "{success:false,message:'该目录下还有文档，请把文件删除后删除该目录'}";
           return "success";
         }
         QueryFilter filter = new QueryFilter(getRequest());
         filter.addFilter("Q_docFolder.folderId_L_EQ", folder.getFolderId().toString());
         List<DocPrivilege> priList = this.docPrivilegeService.getAll(filter);
         for (DocPrivilege dp : priList) {
           this.docPrivilegeService.remove(dp);
         }
         this.docFolderService.remove(folder.getFolderId());
       }
     }
 
     this.jsonString = "{success:true}";
     return "success";
   }
 
   public String get()
   {
     DocFolder docFolder = (DocFolder)this.docFolderService.get(this.folderId);
 
     Gson gson = new Gson();
 
     StringBuffer sb = new StringBuffer("{success:true,data:");
     sb.append(gson.toJson(docFolder));
     sb.append("}");
     setJsonString(sb.toString());
 
     return "success";
   }
 
   public String save()
   {
     if (this.docFolder.getFolderId() == null) {
       if (this.docFolder.getIsShared() == null) {
         this.docFolder.setAppUser(ContextUtil.getCurrentUser());
         this.docFolder.setIsShared(DocFolder.IS_NOT_SHARED);
       }
       this.docFolderService.save(this.docFolder);
 
       if ((this.docFolder.getParentId() == null) || (this.docFolder.getParentId().longValue() == 0L)) {
         this.docFolder.setPath(this.docFolder.getFolderId() + ".");
       } else {
         DocFolder pFolder = (DocFolder)this.docFolderService.get(this.docFolder.getParentId());
         if (pFolder != null) {
           this.docFolder.setPath(pFolder.getPath() + this.docFolder.getFolderId() + ".");
         }
       }
 
       this.docFolderService.save(this.docFolder);
     } else {
       DocFolder df = (DocFolder)this.docFolderService.get(this.docFolder.getFolderId());
       df.setDescp(this.docFolder.getDescp());
       df.setFolderName(this.docFolder.getFolderName());
       this.docFolderService.save(df);
     }
 
     setJsonString("{success:true}");
     return "success";
   }
 
   public String move()
   {
     String strFolderIdOld = getRequest().getParameter("folderIdOld");
     String strFolderIdNew = getRequest().getParameter("folderIdNew");
     if ((StringUtils.isNotEmpty(strFolderIdOld)) && (StringUtils.isNotEmpty(strFolderIdNew))) {
       Long folderIdOld = new Long(strFolderIdOld);
       Long folderIdNew = new Long(strFolderIdNew);
       String newPath = null;
       DocFolder folderOld = (DocFolder)this.docFolderService.get(folderIdOld);
       DocFolder folderNew = new DocFolder();
       if (folderIdNew.longValue() > 0L) {
         folderNew = (DocFolder)this.docFolderService.get(folderIdNew);
         newPath = folderNew.getPath() + folderIdOld.toString() + ".";
         boolean flag = Pattern.compile(folderOld.getPath()).matcher(folderNew.getPath()).find();
         if (flag) {
           setJsonString("{success:false,msg:'不能移到子文件夹下！'}");
           return "success";
         }
       } else {
         folderIdNew = ISPARENT;
         newPath = folderIdOld.toString() + ".";
       }
       String oldPath = folderOld.getPath();
       folderOld.setParentId(folderIdNew);
       folderOld.setPath(newPath);
       List<DocFolder> list = this.docFolderService.getFolderLikePath(oldPath);
       for (DocFolder folder : list) {
         folder.setPath(folder.getPath().replaceFirst(oldPath, newPath));
         this.docFolderService.save(folder);
       }
       this.docFolderService.save(folderOld);
       setJsonString("{success:true}");
     } else {
       setJsonString("{success:false,msg:'请联系系统管理员！'}");
     }
     return "success";
   }
 
   @Action(description="显示文件")
   public String folder() {
     String isUp = getRequest().getParameter("isUp");
     if (this.folderId == null) {
       this.folderId = Long.valueOf(0L);
     }
     else if ((StringUtils.isNotEmpty(isUp)) && ("true".equals(isUp))) {
       DocFolder folder = (DocFolder)this.docFolderService.get(this.folderId);
       if (folder != null) {
         this.folderId = folder.getParentId();
       }
     }
 
     List lists = new ArrayList();
     String isSearch = getRequest().getParameter("isSearch");
     List<DocFolder> list = new ArrayList();
     List<Document> documents = new ArrayList();
     Long userId;
     if ((StringUtils.isNotEmpty(isSearch)) && ("true".equals(isSearch))) {
       String fileName = getRequest().getParameter("fileName");
       userId = ContextUtil.getCurrentUserId();
       list = this.docFolderService.findByUserAndName(userId, fileName);
       Document doc = new Document();
       doc.setDocName(fileName);
       documents = this.documentService.findByPersonal(userId, doc, null, null, null);
     } else {
       list = this.docFolderService.getUserFolderByParentId(ContextUtil.getCurrentUserId(), this.folderId);
       documents = this.documentService.findByFolder(this.folderId);
     }
     for (DocFolder folder : list) {
       DocumentFile file = new DocumentFile();
       file.setFileId(folder.getFolderId());
       file.setFileName(folder.getFolderName());
       file.setFileSize("0 bytes");
       file.setFileType("目录");
       file.setParentId(folder.getParentId());
       DocFolder fol = (DocFolder)this.docFolderService.get(folder.getParentId());
       if (fol != null) {
         file.setParentName(fol.getFolderName());
       }
       file.setIsFolder(DocumentFile.IS_FOLDER);
       lists.add(file);
     }
     for (Document doc : documents) {
       DocumentFile file = new DocumentFile();
       file.setFileId(doc.getDocId());
       file.setFileName(doc.getDocName());
       file.setFileSize(doc.getContent().getBytes() + " bytes");
       file.setFileType("文档");
       file.setIsFolder(DocumentFile.NOT_FOLDER);
       file.setIsShared(doc.getIsShared());
       file.setAuthor(doc.getAuthor());
       file.setUpdateTime(doc.getUpdatetime());
       file.setKeywords(doc.getKeywords());
       lists.add(file);
     }
 
     Gson gson = new Gson();
     Type type = new TypeToken() {  }
     .getType();
     StringBuffer sb = new StringBuffer("{success:true,result:");
     sb.append(gson.toJson(lists, type));
     sb.append("}");
     setJsonString(sb.toString());
     return "success";
   }
 
   public String detail() {
     String fileId = getRequest().getParameter("fileId");
     String isPersonal = getRequest().getParameter("isPersonal");
     String isFolder = getRequest().getParameter("isFolder");
     StringBuffer buff = new StringBuffer("{success:true,");
     if ((StringUtils.isNotEmpty(fileId)) && (StringUtils.isNotEmpty(isFolder))) {
       if ("true".equals(isFolder)) {
         if ("0".equals(fileId)) {
           String path = "/";
           buff.append("fileId:").append(0).append(",fileName:'").append("我的文件夹").append("'");
           buff.append(",descp:'").append("根目录").append("'");
           buff.append(",path:'" + path + "'");
           buff.append(",fileType:'目录'");
           DocFolder docFolder = new DocFolder();
           docFolder.setFolderId(Long.valueOf(0L));
           boolean isPer = false;
           if ((StringUtils.isNotEmpty(isPersonal)) && ("true".equals(isPersonal))) {
             isPer = true;
           }
           sumNum(docFolder, isPer);
         } else {
           DocFolder docFolder = (DocFolder)this.docFolderService.get(new Long(fileId));
           String path = "";
           if ((docFolder.getParentId() != null) && (docFolder.getParentId().longValue() != 0L)) {
             path = findPath(docFolder.getParentId(), path);
           }
           buff.append("fileId:").append(docFolder.getFolderId()).append(",fileName:'").append(docFolder.getFolderName()).append("'");
           buff.append(",descp:'").append(docFolder.getDescp()).append("'");
           buff.append(",path:'" + path + "'");
           buff.append(",fileType:'目录'");
 
           sumNum(docFolder, false);
         }
       }
       else {
         Document document = (Document)this.documentService.get(new Long(fileId));
         String path = "";
         path = findPath(document.getFolderId(), path);
         buff.append("fileId:").append(document.getDocId()).append(",fileName:'").append(document.getDocName()).append("'");
         buff.append(",fileType:'" + document.getDocType() + "'").append(",author:'").append(document.getAuthor())
           .append("',keywords:'").append(document.getKeywords()).append("'")
           .append(",path:'").append(path).append("'");
         Set fileAttachs = document.getAttachFiles();
         this.attachsNum = Integer.valueOf(this.attachsNum.intValue() + fileAttachs.size());
         this.filesize += fileSize(document);
       }
     }
 
     this.logger.info("folderNum:" + this.folderNum + "documentNum:" + this.documentNum + "attachsNum:" + this.attachsNum);
     buff.append(",folderNum:" + this.folderNum + ",documentNum:" + this.documentNum + ",attachsNum:" + this.attachsNum + ",docFileSize:'" + getStrFileSize(this.filesize) + "'");
     buff.append("}");
     setJsonString(buff.toString());
     this.logger.info(buff.toString());
     return "success";
   }
 
   private String findPath(Long folderId, String path) {
     DocFolder df = (DocFolder)this.docFolderService.get(folderId);
     if (df != null) {
       path = "/" + df.getFolderName() + path;
       return findPath(df.getParentId(), path);
     }
     return path;
   }
 
   private void sumNum(DocFolder docFolder, boolean isPer)
   {
     List<Document> list = this.documentService.findByFolder(docFolder.getFolderId());
     Set fileAttachs;
     for (Document doc : list) {
       fileAttachs = doc.getAttachFiles();
       this.attachsNum = Integer.valueOf(this.attachsNum.intValue() + fileAttachs.size());
       this.documentNum = Integer.valueOf(this.documentNum.intValue() + 1);
       this.filesize += fileSize(doc);
     }
     List<DocFolder> folders = new ArrayList();
     if (!isPer)
       folders = this.docFolderService.findByParentId(docFolder.getFolderId());
     else {
       folders = this.docFolderService.getUserFolderByParentId(ContextUtil.getCurrentUserId(), docFolder.getFolderId());
     }
     for (DocFolder folder : folders) {
       this.folderNum = Integer.valueOf(this.folderNum.intValue() + 1);
       sumNum(folder, false);
     }
   }
 
   public void childList(List<DocumentFile> lists, List<DocFolder> folders, AppUser appUser, boolean isEmpty, String fileName) {
     for (DocFolder folder : folders) {
       if ((isEmpty) || ((StringUtils.isNotEmpty(fileName)) && (folder.getFolderName().indexOf(fileName) != -1))) {
         DocumentFile file = new DocumentFile();
         file.setFileId(folder.getFolderId());
         file.setFileName(folder.getFolderName());
         file.setFileType("目录");
         file.setParentId(folder.getParentId());
         DocFolder fol = (DocFolder)this.docFolderService.get(folder.getParentId());
         if (fol != null) {
           file.setParentName(fol.getFolderName());
         }
         file.setIsFolder(DocumentFile.IS_FOLDER);
         lists.add(file);
       }
 
       List list = this.docFolderService.getPublicFolderByParentId(folder.getFolderId());
       if (list.size() > 0)
         childList(lists, list, appUser, isEmpty, fileName);
     }
   }
 
   public String knowledge()
   {
     String isSearch = getRequest().getParameter("isSearch");
     AppUser appUser = ContextUtil.getCurrentUser();
     Set roleRight = appUser.getRights();
     boolean isSuperUser = false;
     if (roleRight.contains("__ALL"))
       isSuperUser = true;
     Object doc1;
     short rightM = 0;
     Gson gson = new Gson();
     if ((StringUtils.isNotEmpty(isSearch)) && ("true".equals(isSearch))) {
       String fileName = getRequest().getParameter("fileName");
       String author = getRequest().getParameter("author");
       String keywords = getRequest().getParameter("keywords");
       List lists = new ArrayList();
       List<DocFolder> list = this.docFolderService.getPublicFolderByParentId(Long.valueOf(0L));
       Integer right = NOT_RIGHT;
       for (DocFolder folder : list) {
         right = NOT_RIGHT;
         if (isSuperUser) {
           right = ALL_RIGHT;
         } else {
           List<Integer> rights = this.docPrivilegeService.getRightsByFolder(appUser, folder.getFolderId());
           for (Integer in : rights) {
             right = Integer.valueOf(right.intValue() | in.intValue());
           }
         }
         if (right != NOT_RIGHT) {
           boolean isEmpty = (StringUtils.isEmpty(fileName)) && (StringUtils.isEmpty(author)) && (StringUtils.isEmpty(keywords));
           if ((isEmpty) || ((StringUtils.isNotEmpty(fileName)) && (folder.getFolderName().indexOf(fileName) != -1))) {
             DocumentFile file = new DocumentFile();
             file.setFileId(folder.getFolderId());
             file.setFileName(folder.getFolderName());
             file.setFileType("目录");
             file.setParentId(folder.getParentId());
             DocFolder fol = (DocFolder)this.docFolderService.get(folder.getParentId());
             if (fol != null) {
               file.setParentName(((DocFolder)fol).getFolderName());
             }
             file.setIsFolder(DocumentFile.IS_FOLDER);
             lists.add(file);
           }
           List listss = this.docFolderService.getPublicFolderByParentId(folder.getFolderId());
           if (listss.size() > 0) {
             childList(lists, listss, appUser, isEmpty, fileName);
           }
         }
       }
       PagingBean pb = getInitPagingBean();
       pb.setPageSize(10000);
       doc1 = new Document();
       ((Document)doc1).setDocName(fileName);
       ((Document)doc1).setAuthor(author);
       ((Document)doc1).setKeywords(keywords);
       List docs = this.documentService.findByPublic(null, (Document)doc1, null, null, appUser, pb);
 
       for (Object fol = docs.iterator(); ((Iterator)fol).hasNext(); ) { Document doc = (Document)((Iterator)fol).next();
         short rightD = 0;
         rightM = 0;
         short rightR = 0;
         if (isSuperUser) {
           rightD = 1;
           rightM = 1;
           rightR = 1;
         } else {
           Long folderId = doc.getFolderId();
           right = NOT_RIGHT;
           if (folderId.longValue() != 0L) {
             List<Integer> folderrights = this.docPrivilegeService.getRightsByFolder(appUser, folderId);
             for (Integer in : folderrights) {
               right = Integer.valueOf(right.intValue() | in.intValue());
             }
             right = rightOfFolder(appUser, right, folderId);
           }
           if (right != NOT_RIGHT) {
             String strRight = Integer.toBinaryString(right.intValue());
             char[] cc = strRight.toCharArray();
             if ((cc.length == 2) && 
               (cc[0] == '1')) {
               rightM = 1;
             }
 
             if (cc.length == 3) {
               if (cc[0] == '1') {
                 rightD = 1;
               }
               if (cc[1] == '1') {
                 rightM = 1;
               }
             }
             rightR = 1;
           }
         }
         if (rightR > 0) {
           DocumentFile file = new DocumentFile();
           file.setFileId(doc.getDocId());
           file.setFileName(doc.getDocName());
           file.setFileSize(getStrFileSize(fileSize(doc)));
           file.setFileType("文档");
           file.setIsFolder(DocumentFile.NOT_FOLDER);
           file.setIsShared(doc.getIsShared());
           file.setRightRead(Short.valueOf(rightR));
           file.setRightMod(Short.valueOf(rightM));
           file.setAuthor(doc.getAuthor());
           file.setKeywords(doc.getKeywords());
           file.setUpdateTime(doc.getUpdatetime());
           file.setRightDel(Short.valueOf(rightD));
           lists.add(file);
         }
       }
//       gson = new Gson();
       Type type = new TypeToken() {  }
       .getType();
       StringBuffer sb = new StringBuffer("{success:true,result:");
       sb.append(gson.toJson(lists, type));
       sb.append("}");
       setJsonString(sb.toString());
       return "success";
     }
     String isUp = getRequest().getParameter("isUp");
 
     Integer right = NOT_RIGHT;
     if (this.folderId == null) {
       this.folderId = Long.valueOf(0L);
     }
     else if ((StringUtils.isNotEmpty(isUp)) && ("true".equals(isUp))) {
       DocFolder folder = (DocFolder)this.docFolderService.get(this.folderId);
       if (folder != null) {
         this.folderId = folder.getParentId();
       }
 
     }
 
     short rightD = 0;
//     short rightM = 0;
     short rightR = 0;
     if (isSuperUser) {
       rightD = 1;
       rightM = 1;
       rightR = 1;
     }
     else {
       if (this.folderId.longValue() != 0L) {
         List folderrights = this.docPrivilegeService.getRightsByFolder(appUser, this.folderId);
         for (doc1 = folderrights.iterator(); ((Iterator)doc1).hasNext(); ) { Integer in = (Integer)((Iterator)doc1).next();
           right = Integer.valueOf(right.intValue() | in.intValue());
         }
         right = rightOfFolder(appUser, right, this.folderId);
       }
       if (right != NOT_RIGHT) {
         String strRight = Integer.toBinaryString(right.intValue());
         char[] cc = strRight.toCharArray();
         if ((cc.length == 2) && 
           (cc[0] == '1')) {
           rightM = 1;
         }
 
         if (cc.length == 3) {
           if (cc[0] == '1') {
             rightD = 1;
           }
           if (cc[1] == '1') {
             rightM = 1;
           }
         }
         rightR = 1;
       }
     }
     List lists = new ArrayList();
     List<DocFolder> list = this.docFolderService.getPublicFolderByParentId(this.folderId);
     List<Document> documents = this.documentService.findByFolder(this.folderId);
     for (DocFolder folder : list) {
       if (folder.getParentId().longValue() == 0L) {
         right = NOT_RIGHT;
         if (isSuperUser) {
           right = ALL_RIGHT;
         } else {
           List<Integer> rights = this.docPrivilegeService.getRightsByFolder(appUser, folder.getFolderId());
           for (Integer in : rights)
             right = Integer.valueOf(right.intValue() | in.intValue());
         }
       }
       else {
         right = ALL_RIGHT;
       }
       if (right != NOT_RIGHT) {
         DocumentFile file = new DocumentFile();
         file.setFileId(folder.getFolderId());
         file.setFileName(folder.getFolderName());
         file.setFileType("目录");
         file.setParentId(folder.getParentId());
         DocFolder fol = (DocFolder)this.docFolderService.get(folder.getParentId());
         if (fol != null) {
           file.setParentName(fol.getFolderName());
         }
         file.setIsFolder(DocumentFile.IS_FOLDER);
         lists.add(file);
       }
     }
     for (Document doc : documents) {
       DocumentFile file = new DocumentFile();
       file.setFileId(doc.getDocId());
       file.setFileName(doc.getDocName());
       file.setFileSize(getStrFileSize(fileSize(doc)));
       file.setFileType("文档");
       file.setIsFolder(DocumentFile.NOT_FOLDER);
       file.setIsShared(doc.getIsShared());
       file.setRightRead(Short.valueOf(rightR));
       file.setRightMod(Short.valueOf(rightM));
       file.setAuthor(doc.getAuthor());
       file.setKeywords(doc.getKeywords());
       file.setUpdateTime(doc.getUpdatetime());
       file.setRightDel(Short.valueOf(rightD));
       lists.add(file);
     }
 
     gson = new Gson();
     Type type = new TypeToken() {  }
     .getType();
     StringBuffer sb = new StringBuffer("{success:true,result:");
     sb.append(gson.toJson(lists, type));
     sb.append("}");
     setJsonString(sb.toString());
     return (String)(String)"success";
   }
 
   private String getStrFileSize(double size) {
     DecimalFormat df = new DecimalFormat("0.00");
     if (size > 1048576.0D) {
       double ss = size / 1048576.0D;
       return df.format(ss) + " M";
     }if (size > 1024.0D) {
       double ss = size / 1024.0D;
       return df.format(ss) + " KB";
     }
     return size + " bytes";
   }
 
   private double fileSize(Document doc)
   {
     Set files = doc.getAttachFiles();
     Iterator it = files.iterator();
     double filesize = 0.0D;
     while (it.hasNext()) {
       FileAttach file = (FileAttach)it.next();
       if ((file != null) && (file.getTotalBytes() != null)) {
         filesize += file.getTotalBytes().longValue();
       }
     }
     String content = doc.getContent();
     if (StringUtils.isNotEmpty(content)) {
       int size = content.getBytes().length;
       filesize += size;
     }
     return filesize;
   }
 
   public Integer rightOfFolder(AppUser appUser, Integer right, Long folderId) {
     DocFolder docFolder = (DocFolder)this.docFolderService.get(folderId);
     if (docFolder != null) {
       List<Integer> folderrights = this.docPrivilegeService.getRightsByFolder(appUser, docFolder.getParentId());
       for (Integer in : folderrights) {
         right = Integer.valueOf(right.intValue() | in.intValue());
       }
       Integer rights = rightOfFolder(appUser, right, docFolder.getParentId());
       right = Integer.valueOf(right.intValue() | rights.intValue());
     }
     return right;
   }
 
   public String knowledgeTree() {
     AppUser appUser = ContextUtil.getCurrentUser();
     StringBuffer buff = new StringBuffer("[{id:'0',text:'知识目录',expanded:true,children:[");
     List<DocFolder> docList = this.docFolderService.getPublicFolderByParentId(Long.valueOf(0L));
     boolean isFlag = false;
     for (DocFolder docFolder : docList) {
       List<Integer> rights = this.docPrivilegeService.getRightsByFolder(appUser, docFolder.getFolderId());
       Integer right = NOT_RIGHT;
       for (Integer in : rights) {
         right = Integer.valueOf(right.intValue() | in.intValue());
       }
       Set roleRight = appUser.getRights();
       if (roleRight.contains("__ALL")) {
         right = ALL_RIGHT;
       }
       if (right != NOT_RIGHT) {
         isFlag = true;
         buff.append("{id:'" + docFolder.getFolderId()).append("',text:'" + docFolder.getFolderName()).append("',expanded:true,");
 
         buff.append(findChildsFolder(docFolder.getFolderId()));
       }
 
     }
 
     if (isFlag) {
       buff.deleteCharAt(buff.length() - 1);
     }
     buff.append("]}]");
     setJsonString(buff.toString());
     return "success";
   }
 
   public String findChildsFolderByRights(Long parentId, Integer right, boolean isAllRight) {
     StringBuffer sb = new StringBuffer();
     List<DocFolder> list = this.docFolderService.getPublicFolderByParentId(parentId);
     if (list.size() == 0) {
       sb.append("leaf:true},");
       return sb.toString();
     }
     sb.append("children:[");
     boolean flag = false;
     for (DocFolder folder : list) {
       Integer in = right;
       if (isAllRight) {
         in = ALL_RIGHT;
       }
       else if (in != NOT_RIGHT) {
         in = NOT_RIGHT;
         AppUser appUser = ContextUtil.getCurrentUser();
         List<Integer> rights = this.docPrivilegeService.getRightsByFolder(appUser, folder.getFolderId());
         for (Integer inte : rights) {
           in = Integer.valueOf(in.intValue() | inte.intValue());
         }
       }
 
       if (in != NOT_RIGHT) {
         flag = true;
         sb.append("{id:'" + folder.getFolderId() + "',text:'" + folder.getFolderName() + "',expanded:true,");
         sb.append(findChildsFolderByRights(folder.getFolderId(), in, isAllRight));
       }
     }
     if (flag) {
       sb.deleteCharAt(sb.length() - 1);
     }
     sb.append("]},");
     return sb.toString();
   }
 
   public String onlineTree()
   {
     AppUser appUser = ContextUtil.getCurrentUser();
     StringBuffer buff = new StringBuffer("[{id:'0',text:'在线文档目录',expanded:true,children:[");
     List<DocFolder> docList = this.docFolderService.getOnlineFolderByParentId(Long.valueOf(0L));
     boolean isFlag = false;
     for (DocFolder docFolder : docList) {
       List<Integer> rights = this.docPrivilegeService.getRightsByFolder(appUser, docFolder.getFolderId());
       Integer right = NOT_RIGHT;
       for (Integer in : rights) {
         right = Integer.valueOf(right.intValue() | in.intValue());
       }
       Set roleRight = appUser.getRights();
       if (roleRight.contains("__ALL")) {
         right = ALL_RIGHT;
       }
       if (right != NOT_RIGHT) {
         isFlag = true;
         buff.append("{id:'" + docFolder.getFolderId()).append("',text:'" + docFolder.getFolderName()).append("',expanded:true,");
         buff.append(findOnlineChildsFolder(docFolder.getFolderId()));
       }
     }
     if (isFlag) {
       buff.deleteCharAt(buff.length() - 1);
     }
     buff.append("]}]");
     setJsonString(buff.toString());
 
     return "success";
   }
 
   public String findOnlineChildsFolder(Long parentId)
   {
     StringBuffer sb = new StringBuffer();
     List<DocFolder> list = this.docFolderService.getOnlineFolderByParentId(parentId);
     if (list.size() == 0) {
       sb.append("leaf:true,expanded:true},");
       return sb.toString();
     }
     sb.append("children:[");
     for (DocFolder folder : list) {
       sb.append("{id:'" + folder.getFolderId() + "',text:'" + folder.getFolderName() + "',expanded:true,");
       sb.append(findOnlineChildsFolder(folder.getFolderId()));
     }
     sb.deleteCharAt(sb.length() - 1);
     sb.append("]},");
     return sb.toString();
   }
 
   public String onlineList()
   {
     AppUser appUser = ContextUtil.getCurrentUser();
     Set roleRight = appUser.getRights();
     boolean isSuperUser = false;
     if (roleRight.contains("__ALL")) {
       isSuperUser = true;
     }
     String isSearch = getRequest().getParameter("isSearch");
     short rightM;
     String strRight;
     Type type;
     if ((StringUtils.isNotEmpty(isSearch)) && ("true".equals(isSearch))) {
       String fileName = getRequest().getParameter("fileName");
       List lists = new ArrayList();
       List<DocFolder> list = this.docFolderService.getOnlineFolderByParentId(Long.valueOf(0L));
       Integer right = NOT_RIGHT;
       List listss;
       for (DocFolder folder : list) {
         right = NOT_RIGHT;
         if (isSuperUser) {
           right = ALL_RIGHT;
         } else {
           List<Integer> rights = this.docPrivilegeService.getRightsByFolder(appUser, folder.getFolderId());
           for (Integer in : rights) {
             right = Integer.valueOf(right.intValue() | in.intValue());
           }
         }
         if (right != NOT_RIGHT) {
           boolean isEmpty = StringUtils.isEmpty(fileName);
           if ((isEmpty) || ((StringUtils.isNotEmpty(fileName)) && (folder.getFolderName().indexOf(fileName) != -1))) {
             DocumentFile file = new DocumentFile();
             file.setFileId(folder.getFolderId());
             file.setFileName(folder.getFolderName());
             file.setFileType("目录");
             file.setParentId(folder.getParentId());
             DocFolder fol = (DocFolder)this.docFolderService.get(folder.getParentId());
             if (fol != null) {
               file.setParentName(fol.getFolderName());
             }
             file.setIsFolder(DocumentFile.IS_FOLDER);
             lists.add(file);
           }
           listss = this.docFolderService.getOnlineFolderByParentId(folder.getFolderId());
           if (listss.size() > 0) {
             childOnlineList(lists, listss, appUser, isEmpty, fileName);
           }
         }
       }
       Document doc1 = new Document();
       doc1.setDocName(fileName);
       List<Document> docs = this.documentService.findByOnline(doc1, null, null, appUser);
 
       for (Document doc : docs) {
         short rightD = 0;
         rightM = 0;
         short rightR = 0;
         if (isSuperUser) {
           rightD = 1;
           rightM = 1;
           rightR = 1;
         } else {
           Long folderId = doc.getFolderId();
           right = NOT_RIGHT;
           if (folderId.longValue() != 0L) {
             List<Integer> folderrights = this.docPrivilegeService.getRightsByFolder(appUser, folderId);
             for (Integer in : folderrights) {
               right = Integer.valueOf(right.intValue() | in.intValue());
             }
             right = rightOfFolder(appUser, right, folderId);
           }
           if (right != NOT_RIGHT) {
             strRight = Integer.toBinaryString(right.intValue());
             char[] cc = strRight.toCharArray();
             if ((cc.length == 2) && 
               (cc[0] == '1')) {
               rightM = 1;
             }
 
             if (cc.length == 3) {
               if (cc[0] == '1') {
                 rightD = 1;
               }
               if (cc[1] == '1') {
                 rightM = 1;
               }
             }
             rightR = 1;
           }
         }
         if (rightR > 0) {
           DocumentFile file = new DocumentFile();
           file.setFileId(doc.getDocId());
           file.setFileName(doc.getDocName());
           file.setFileSize(getStrFileSize(fileSize(doc)));
           file.setFileType(doc.getDocType());
           file.setIsFolder(DocumentFile.NOT_FOLDER);
           file.setIsShared(doc.getIsShared());
           file.setRightRead(Short.valueOf(rightR));
           file.setRightMod(Short.valueOf(rightM));
           file.setAuthor(doc.getAuthor());
           file.setKeywords(doc.getKeywords());
           file.setUpdateTime(doc.getUpdatetime());
           file.setRightDel(Short.valueOf(rightD));
           lists.add(file);
         }
       }
       Gson gson = new Gson();
       type = new TypeToken() {  }
       .getType();
       StringBuffer sb = new StringBuffer("{success:true,result:");
       sb.append(gson.toJson(lists, type));
       sb.append("}");
       setJsonString(sb.toString());
       return "success";
     }
 
     String isUp = getRequest().getParameter("isUp");
 
     Integer right = NOT_RIGHT;
     if (this.folderId == null) {
       this.folderId = Long.valueOf(0L);
     }
     else if ((StringUtils.isNotEmpty(isUp)) && ("true".equals(isUp))) {
       DocFolder folder = (DocFolder)this.docFolderService.get(this.folderId);
       if (folder != null) {
         this.folderId = folder.getParentId();
       }
 
     }
 
     short rightD = 0;
     rightM = 0;
     short rightR = 0;
     if (isSuperUser) {
       rightD = 1;
       rightM = 1;
       rightR = 1;
     }
     else {
       if (this.folderId.longValue() != 0L) {
         List<Integer> folderrights = this.docPrivilegeService.getRightsByFolder(appUser, this.folderId);
         for (Integer in : folderrights) {
           right = Integer.valueOf(right.intValue() | in.intValue());
         }
         right = rightOfFolder(appUser, right, this.folderId);
       }
       if (right != NOT_RIGHT) {
         strRight = Integer.toBinaryString(right.intValue());
         char[] cc = strRight.toCharArray();
         if ((cc.length == 2) && 
           (cc[0] == '1')) {
           rightM = 1;
         }
 
         if (cc.length == 3) {
           if (cc[0] == '1') {
             rightD = 1;
           }
           if (cc[1] == '1') {
             rightM = 1;
           }
         }
         rightR = 1;
       }
     }
     Object lists = new ArrayList();
     List<DocFolder> list = this.docFolderService.getOnlineFolderByParentId(this.folderId);
     List<Document> documents = this.documentService.findByFolder(this.folderId);
     for (DocFolder folder : list) {
       if (folder.getParentId().longValue() == 0L) {
         right = NOT_RIGHT;
         if (isSuperUser) {
           right = ALL_RIGHT;
         } else {
           List<Integer> rights = this.docPrivilegeService.getRightsByFolder(appUser, folder.getFolderId());
           for (Integer in : rights)
             right = Integer.valueOf(right.intValue() | in.intValue());
         }
       }
       else {
         right = ALL_RIGHT;
       }
       if (right != NOT_RIGHT) {
         DocumentFile file = new DocumentFile();
         file.setFileId(folder.getFolderId());
         file.setFileName(folder.getFolderName());
         file.setFileType("目录");
         file.setParentId(folder.getParentId());
         DocFolder fol = (DocFolder)this.docFolderService.get(folder.getParentId());
         if (fol != null) {
           file.setParentName(fol.getFolderName());
         }
         file.setIsFolder(DocumentFile.IS_FOLDER);
         ((List)lists).add(file);
       }
     }
     for (Document doc : documents) {
       DocumentFile file = new DocumentFile();
       file.setFileId(doc.getDocId());
       file.setFileName(doc.getDocName());
       file.setFileSize(getStrFileSize(fileSize(doc)));
       file.setFileType(doc.getDocType());
       file.setIsFolder(DocumentFile.NOT_FOLDER);
       file.setIsShared(doc.getIsShared());
       file.setRightRead(Short.valueOf(rightR));
       file.setRightMod(Short.valueOf(rightM));
       file.setAuthor(doc.getAuthor());
       file.setKeywords(doc.getKeywords());
       file.setUpdateTime(doc.getUpdatetime());
       file.setRightDel(Short.valueOf(rightD));
       ((List)lists).add(file);
     }
 
     Gson gson = new Gson();
     type = new TypeToken() {  }
     .getType();
     StringBuffer sb = new StringBuffer("{success:true,result:");
     sb.append(gson.toJson(lists, type));
     sb.append("}");
     setJsonString(sb.toString());
 
     return (String)"success";
   }
 
   public void childOnlineList(List<DocumentFile> lists, List<DocFolder> folders, AppUser appUser, boolean isEmpty, String fileName) {
     for (DocFolder folder : folders) {
       if ((isEmpty) || ((StringUtils.isNotEmpty(fileName)) && (folder.getFolderName().indexOf(fileName) != -1))) {
         DocumentFile file = new DocumentFile();
         file.setFileId(folder.getFolderId());
         file.setFileName(folder.getFolderName());
         file.setFileType("目录");
         file.setParentId(folder.getParentId());
         DocFolder fol = (DocFolder)this.docFolderService.get(folder.getParentId());
         if (fol != null) {
           file.setParentName(fol.getFolderName());
         }
         file.setIsFolder(DocumentFile.IS_FOLDER);
         lists.add(file);
       }
 
       List list = this.docFolderService.getOnlineFolderByParentId(folder.getFolderId());
       if (list.size() > 0)
         childOnlineList(lists, list, appUser, isEmpty, fileName);
     }
   }
 }

