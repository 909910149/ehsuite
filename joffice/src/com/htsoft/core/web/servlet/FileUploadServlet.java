 package com.htsoft.core.web.servlet;
 
 import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.htsoft.core.util.AppUtil;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.util.FileUtil;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.model.system.FileAttach;
import com.htsoft.oa.service.system.FileAttachService;
 
 public class FileUploadServlet extends HttpServlet
 {
   private Log logger = LogFactory.getLog(FileUploadServlet.class);
 
   private ServletConfig servletConfig = null;
 
   private FileAttachService fileAttachService = (FileAttachService)AppUtil.getBean("fileAttachService");
 
   private String uploadPath = "";
   private String tempPath = "";
 
   private String fileCat = "others";
 
   protected void doPost(HttpServletRequest req, HttpServletResponse resp)
     throws ServletException, IOException
   {
     String filePath = "";
     String fileId = "";
 
     req.setCharacterEncoding("UTF-8");
     resp.setCharacterEncoding("UTF-8");
     try
     {
       DiskFileItemFactory factory = new DiskFileItemFactory();
 
       factory.setSizeThreshold(4096);
       factory.setRepository(new File(this.tempPath));
       ServletFileUpload fu = new ServletFileUpload(factory);
 
       List<FileItem> fileItems = fu.parseRequest(req);
 
       for (FileItem fi : fileItems) {
         if ("file_cat".equals(fi.getFieldName())) {
           this.fileCat = fi.getString();
         }
 
         if ("file_path".equals(fi.getFieldName())) {
           filePath = fi.getString();
         }
         if ("fileId".equals(fi.getFieldName())) {
           fileId = fi.getString();
         }
       }
       this.logger.info("fileId:" + fileId);
       Iterator i = fileItems.iterator();
 
       while (i.hasNext())
       {
         FileItem fi = (FileItem)i.next();
 
         if (fi.getContentType() == null)
         {
           continue;
         }
 
         String path = fi.getName();
 
         int start = path.lastIndexOf("\\");
 
         String fileName = path.substring(start + 1);
 
         String relativeFullPath = null;
 
         if (!"".equals(filePath)) {
           relativeFullPath = filePath;
         } else if (!"".equals(fileId)) {
           FileAttach fileAttach = (FileAttach)this.fileAttachService.get(new Long(fileId));
           relativeFullPath = fileAttach.getFilePath();
           this.logger.info("exist filePath:" + relativeFullPath);
         } else {
           relativeFullPath = this.fileCat + "/" + FileUtil.generateFilename(fileName);
         }
 
         int index = relativeFullPath.lastIndexOf("/");
 
         File dirPath = new File(this.uploadPath + "/" + relativeFullPath.substring(0, index + 1));
 
         if (!dirPath.exists()) {
           dirPath.mkdirs();
         }
 
         fi.write(new File(this.uploadPath + "/" + relativeFullPath));
         FileAttach file = null;
 
         if (!"".equals(filePath)) {
           file = this.fileAttachService.getByPath(filePath);
           file.setTotalBytes(Long.valueOf(fi.getSize()));
           file.setNote(getStrFileSize(fi.getSize()));
           this.fileAttachService.save(file);
         }
         if (!"".equals(fileId)) {
           file = (FileAttach)this.fileAttachService.get(new Long(fileId));
           file.setTotalBytes(Long.valueOf(fi.getSize()));
           file.setNote(getStrFileSize(fi.getSize()));
           this.fileAttachService.save(file);
         }
         if (file == null)
         {
           file = new FileAttach();
           file.setCreatetime(new Date());
           AppUser curUser = ContextUtil.getCurrentUser();
           if (curUser != null) {
             file.setCreator(curUser.getFullname());
             file.setCreatorId(curUser.getUserId());
           }
           else {
             file.setCreator("UNKown");
           }
           int dotIndex = fileName.lastIndexOf(".");
           file.setExt(fileName.substring(dotIndex + 1));
           file.setFileName(fileName);
           file.setFilePath(relativeFullPath);
           file.setFileType(this.fileCat);
           file.setTotalBytes(Long.valueOf(fi.getSize()));
           file.setNote(getStrFileSize(fi.getSize()));
           file.setCreatorId(ContextUtil.getCurrentUserId());
           file.setDelFlag(FileAttach.FLAG_NOT_DEL);
           this.fileAttachService.save(file);
         }
 
         StringBuffer sb = new StringBuffer("{success:true");
         sb.append(",fileId:").append(file.getFileId())
           .append(",fileName:'").append(file.getFileName())
           .append("',filePath:'").append(file.getFilePath()).append("',message:'upload file success.(" + fi.getSize() + " bytes)'");
         sb.append("}");
         resp.setContentType("text/html;charset=UTF-8");
         PrintWriter writer = resp.getWriter();
 
         writer.println(sb.toString());
       }
     }
     catch (Exception e)
     {
       resp.getWriter().write("{'success':false,'message':'error..." + e.getMessage() + "'}");
     }
   }
 
   public void init(ServletConfig config) throws ServletException
   {
     super.init(config);
     this.servletConfig = config;
   }
 
   public void init()
     throws ServletException
   {
     this.uploadPath = getServletContext().getRealPath("/attachFiles/");
 
     File uploadPathFile = new File(this.uploadPath);
     if (!uploadPathFile.exists()) {
       uploadPathFile.mkdirs();
     }
     this.tempPath = (this.uploadPath + "/temp");
 
     File tempPathFile = new File(this.tempPath);
     if (!tempPathFile.exists())
       tempPathFile.mkdirs();
   }
 
   public boolean saveFileToDisk(String officefileNameDisk)
   {
     File officeFileUpload = null;
     FileItem officeFileItem = null;
 
     boolean result = true;
     try
     {
       if (("".equalsIgnoreCase(officefileNameDisk)) || (officeFileItem == null));
     }
     catch (Exception e)
     {
       e.printStackTrace();
       result = false;
     }
     return result;
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
 }

