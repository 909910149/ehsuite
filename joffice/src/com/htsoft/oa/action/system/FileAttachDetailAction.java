/*    */ package com.htsoft.oa.action.system;
/*    */ 
/*    */ import com.htsoft.core.web.action.BaseAction;
/*    */ import com.htsoft.oa.model.system.FileAttach;
/*    */ import com.htsoft.oa.service.system.FileAttachService;
/*    */ import javax.annotation.Resource;
/*    */ 
/*    */ public class FileAttachDetailAction extends BaseAction
/*    */ {
/*    */ 
/*    */   @Resource
/*    */   private FileAttachService fileAttachService;
/*    */   private Long fileId;
/*    */   private FileAttach fileAttach;
/*    */ 
/*    */   public Long getFileId()
/*    */   {
/* 24 */     return this.fileId;
/*    */   }
/*    */ 
/*    */   public void setFileId(Long fileId) {
/* 28 */     this.fileId = fileId;
/*    */   }
/*    */ 
/*    */   public FileAttach getFileAttach() {
/* 32 */     return this.fileAttach;
/*    */   }
/*    */ 
/*    */   public void setFileAttach(FileAttach fileAttach) {
/* 36 */     this.fileAttach = fileAttach;
/*    */   }
/*    */ 
/*    */   public String execute() throws Exception
/*    */   {
/* 41 */     this.fileAttach = ((FileAttach)this.fileAttachService.get(this.fileId));
/* 42 */     return "success";
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.action.system.FileAttachDetailAction
 * JD-Core Version:    0.6.0
 */