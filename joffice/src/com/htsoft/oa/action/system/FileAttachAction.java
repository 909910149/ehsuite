 package com.htsoft.oa.action.system;
 
 import com.google.gson.Gson;
 import com.google.gson.GsonBuilder;
 import com.google.gson.reflect.TypeToken;
 import com.htsoft.core.command.QueryFilter;
 import com.htsoft.core.util.AppUtil;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.model.system.FileAttach;
 import com.htsoft.oa.service.system.FileAttachService;
 import java.io.File;
 import java.lang.reflect.Type;
 import java.util.List;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 import org.apache.commons.logging.Log;
 import org.dom4j.Attribute;
 import org.dom4j.Document;
 import org.dom4j.DocumentException;
 import org.dom4j.Element;
 import org.dom4j.io.SAXReader;
 
 public class FileAttachAction extends BaseAction
 {
 
   @Resource
   private FileAttachService fileAttachService;
   private FileAttach fileAttach;
   private Long fileId;
   private String filePath;
 
   public Long getFileId()
   {
     return this.fileId;
   }
 
   public void setFileId(Long fileId) {
     this.fileId = fileId;
   }
 
   public FileAttach getFileAttach() {
     return this.fileAttach;
   }
 
   public void setFileAttach(FileAttach fileAttach) {
     this.fileAttach = fileAttach;
   }
 
   public String getFilePath() {
     return this.filePath;
   }
 
   public void setFilePath(String filePath) {
     this.filePath = filePath;
   }
 
   public String list()
   {
     int start = new QueryFilter(getRequest()).getPagingBean().getStart().intValue();
     PagingBean pb = new PagingBean(start, 20);
     String imageOrOthersFile = getRequest().getParameter("type");
     boolean bo = true;
     if ((imageOrOthersFile != null) && 
       (imageOrOthersFile.toLowerCase().equals("image"))) {
       bo = false;
       pb = new PagingBean(start, 16);
     }
     String fileType = getRequest().getParameter("fileType");
     List list = this.fileAttachService.fileList(pb, fileType, bo);
     return listToJson(list, pb);
   }
 
   public String listAll() {
     QueryFilter filter = new QueryFilter(getRequest());
     filter.addSorted("fileType", "DESC");
     List<FileAttach> list = this.fileAttachService.getAll(filter);
     return listToJson(list, filter.getPagingBean());
   }
 
   public String multiDel()
   {
     String ids = getRequest().getParameter("ids");
     if (ids != null) {
       for (String id : ids.split(",")) {
         this.fileAttachService.remove(new Long(id));
       }
     }
     this.jsonString = "{success:true}";
     return "success";
   }
 
   public String get()
   {
     FileAttach fileAttach = (FileAttach)this.fileAttachService.get(this.fileId);
     Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
       .create();
 
     StringBuffer sb = new StringBuffer("{success:true,data:");
     sb.append(gson.toJson(fileAttach));
     sb.append("}");
     setJsonString(sb.toString());
 
     return "success";
   }
 
   public String save()
   {
     this.fileAttachService.save(this.fileAttach);
     setJsonString("{success:true}");
     return "success";
   }
 
   public String delete()
   {
     this.fileAttachService.removeByPath(this.filePath);
     return "success";
   }
 
   private String listToJson(List<FileAttach> list, PagingBean pb)
   {
     Type type = new TypeToken<List<FileAttach>>() {
     }.getType();
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(pb.getTotalItems()).append(",result:");
     Gson gson = new Gson();
     buff.append(gson.toJson(list, type));
     buff.append("}");
     this.jsonString = buff.toString();
     return "success";
   }
 
   public String treeLoad()
   {
     String xmlFilePath = AppUtil.getAppAbsolutePath().replace("\\", "/") + 
       "js/menu/file.xml";
     StringBuffer sbf = new StringBuffer("");
     sbf.append("[{id:'0',text:'" + AppUtil.getCompanyName() + 
       "',expanded:true,children:[");
     try {
       SAXReader reader = new SAXReader();
       Document document = reader.read(new File(xmlFilePath));
       if (document != null) {
         Element root = document.getRootElement();
         List<Element> list = root.elements();
         for (Element el : list) {
           String id = el.attribute("id").getValue();
           String text = el.attribute("text").getValue();
           sbf.append("{id:'" + id).append("',text:'" + text).append("',");
           sbf.append(findChild(el));
         }
         if ((list != null) && (list.size() > 0))
           sbf.deleteCharAt(sbf.length() - 1);
       }
     } catch (DocumentException e) {
       this.logger.debug("FileAttachAction中，加载xml文件失败！");
       e.printStackTrace();
     }
     sbf.append("]}]");
     setJsonString(sbf.toString());
     return "success";
   }
 
   private String findChild(Element el)
   {
     List<Element> list = el.elements();
     StringBuffer bf = new StringBuffer("");
     if ((list == null) || (list.size() == 0)) {
       bf.append("leaf:true},");
     } else {
       bf.append("children:[");
       for (Element e : list) {
         String id = e.attribute("id").getValue();
         String text = e.attribute("text").getValue();
         bf.append("{id:'" + id).append("',text:'" + text).append("',");
         bf.append(findChild(e));
       }
       bf.deleteCharAt(bf.length() - 1);
       bf.append("]},");
     }
     return bf.toString();
   }
 }

