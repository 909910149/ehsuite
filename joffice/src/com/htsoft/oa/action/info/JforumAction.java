/*    */ package com.htsoft.oa.action.info;
/*    */ 
/*    */ import com.htsoft.core.util.ContextUtil;
/*    */ import com.htsoft.core.web.action.BaseAction;
/*    */ import com.htsoft.oa.model.system.AppUser;
/*    */ import com.htsoft.oa.service.system.AppUserService;
/*    */ import javax.annotation.Resource;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ 
/*    */ public class JforumAction extends BaseAction
/*    */ {
/*    */ 
/*    */   @Resource
/*    */   private AppUserService appUserService;
/*    */   private Long userId;
/*    */ 
/*    */   public Long getUserId()
/*    */   {
/* 16 */     return this.userId;
/*    */   }
/*    */ 
/*    */   public void setUserId(Long userId) {
/* 20 */     this.userId = userId;
/*    */   }
/*    */ 
/*    */   public String execute()
/*    */   {
/* 25 */     AppUser appUser = null;
/* 26 */     String userId = getRequest().getParameter("userId");
/* 27 */     if (userId == null)
/* 28 */       appUser = (AppUser)this.appUserService.get(new Long(userId));
/*    */     else {
/* 30 */       appUser = (AppUser)this.appUserService.get(ContextUtil.getCurrentUserId());
/*    */     }
/* 32 */     getRequest().setAttribute("appUser", appUser);
/* 33 */     return "success";
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.action.info.JforumAction
 * JD-Core Version:    0.6.0
 */