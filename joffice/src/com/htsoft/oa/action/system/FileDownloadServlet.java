/*     */ package com.htsoft.oa.action.system;
/*     */ 
/*     */ import com.htsoft.core.util.AppUtil;
/*     */ import com.htsoft.oa.model.system.FileAttach;
/*     */ import com.htsoft.oa.service.system.FileAttachService;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.net.URLEncoder;
/*     */ import javax.servlet.ServletContext;
/*     */ import javax.servlet.ServletException;
/*     */ import javax.servlet.ServletOutputStream;
/*     */ import javax.servlet.http.HttpServlet;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class FileDownloadServlet extends HttpServlet
/*     */ {
/*  25 */   private FileAttachService fileAttachService = (FileAttachService)AppUtil.getBean("fileAttachService");
/*     */ 
/*     */   protected void doGet(HttpServletRequest req, HttpServletResponse resp)
/*     */     throws ServletException, IOException
/*     */   {
/*  34 */     String fileId = req.getParameter("fileId");
/*  35 */     req.setCharacterEncoding("UTF-8");
/*  36 */     resp.setCharacterEncoding("UTF-8");
/*     */ 
/*  38 */     if (StringUtils.isNotEmpty(fileId)) {
/*  39 */       FileAttach fileAttach = (FileAttach)this.fileAttachService.get(new Long(fileId));
/*  40 */       String ext = fileAttach.getExt();
/*     */ 
/*  42 */       if (ext.toLowerCase().endsWith("zip"))
/*  43 */         resp.setContentType("application/x-zip-compressed");
/*  44 */       else if (ext.toLowerCase().endsWith("rar"))
/*  45 */         resp.setContentType("application/octet-stream");
/*  46 */       else if (ext.toLowerCase().endsWith("doc"))
/*  47 */         resp.setContentType("application/msword");
/*  48 */       else if ((ext.toLowerCase().endsWith("xls")) || 
/*  49 */         (ext.toLowerCase().endsWith("csv"))) {
/*  50 */         resp.setContentType("application/ms-excel ");
/*     */       }
/*  52 */       else if (ext.toLowerCase().endsWith("pdf"))
/*  53 */         resp.setContentType("application/pdf");
/*     */       else {
/*  55 */         resp.setContentType("application/x-msdownload");
/*     */       }
/*     */ 
/*  58 */       ServletOutputStream out = null;
/*     */       try
/*     */       {
/*  61 */         FileInputStream fileIn = new FileInputStream(
/*  62 */           getServletContext().getRealPath("/") + "/attachFiles/" + 
/*  63 */           fileAttach.getFilePath());
/*     */ 
/*  65 */         resp.setHeader("Content-Disposition", "attachment;filename=" + 
/*  66 */           URLEncoder.encode(fileAttach.getFileName(), "UTF-8"));
/*     */ 
/*  68 */         out = resp.getOutputStream();
/*     */ 
/*  70 */         byte[] buff = new byte[1024];
/*  71 */         int leng = fileIn.read(buff);
/*  72 */         while (leng > 0) {
/*  73 */           out.write(buff, 0, leng);
/*  74 */           leng = fileIn.read(buff);
/*     */         }
/*     */       } catch (Exception ex) {
/*  77 */         ex.printStackTrace();
/*     */ 
/*  79 */         if (out != null) {
/*     */           try {
/*  81 */             out.flush();
/*     */           } catch (IOException e) {
/*  83 */             e.printStackTrace();
/*     */           }
/*     */           try {
/*  86 */             out.close();
/*     */           } catch (IOException e) {
/*  88 */             e.printStackTrace();
/*     */           }
/*     */         }
/*     */       }
/*     */       finally
/*     */       {
/*  79 */         if (out != null) {
/*     */           try {
/*  81 */             out.flush();
/*     */           } catch (IOException e) {
/*  83 */             e.printStackTrace();
/*     */           }
/*     */           try {
/*  86 */             out.close();
/*     */           } catch (IOException e) {
/*  88 */             e.printStackTrace();
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void doPost(HttpServletRequest req, HttpServletResponse resp)
/*     */     throws ServletException, IOException
/*     */   {
/* 101 */     doGet(req, resp);
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.action.system.FileDownloadServlet
 * JD-Core Version:    0.6.0
 */