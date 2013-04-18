/*    */ package com.htsoft.oa.action.mobile;
/*    */ 
/*    */ import com.htsoft.core.util.StringUtil;
/*    */ import com.htsoft.core.web.action.BaseAction;
/*    */ import com.htsoft.oa.model.system.AppUser;
/*    */ import com.htsoft.oa.service.system.AppUserService;
/*    */ import javax.annotation.Resource;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.springframework.security.AuthenticationManager;
/*    */ import org.springframework.security.context.SecurityContext;
/*    */ import org.springframework.security.context.SecurityContextHolder;
/*    */ import org.springframework.security.providers.UsernamePasswordAuthenticationToken;
/*    */ 
/*    */ public class SignInAction extends BaseAction
/*    */ {
/*    */ 
/*    */   @Resource
/*    */   private AppUserService userService;
/*    */ 
/*    */   @Resource(name="authenticationManager")
/* 21 */   private AuthenticationManager authenticationManager = null;
/*    */   private String username;
/*    */   private String password;
/*    */ 
/*    */   public String getUsername()
/*    */   {
/* 27 */     return this.username;
/*    */   }
/*    */ 
/*    */   public void setUsername(String username) {
/* 31 */     this.username = username;
/*    */   }
/*    */ 
/*    */   public String getPassword() {
/* 35 */     return this.password;
/*    */   }
/*    */ 
/*    */   public void setPassword(String password) {
/* 39 */     this.password = password;
/*    */   }
/*    */ 
/*    */   public String execute() throws Exception
/*    */   {
/* 44 */     if ((StringUtils.isNotEmpty(this.username)) && (StringUtils.isNotEmpty(this.password))) {
/* 45 */       AppUser user = this.userService.findByUserName(this.username);
/* 46 */       if (user != null) {
/* 47 */         String enPassword = StringUtil.encryptSha256(this.password);
/* 48 */         if (enPassword.equals(user.getPassword()))
/*    */         {
/* 50 */           UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(this.username, this.password);
/* 51 */           SecurityContext securityContext = SecurityContextHolder.getContext();
/* 52 */           securityContext.setAuthentication(this.authenticationManager.authenticate(authRequest));
/* 53 */           SecurityContextHolder.setContext(securityContext);
/*    */ 
/* 55 */           return "success";
/*    */         }
/*    */       }
/*    */     }
/*    */ 
/* 60 */     return "input";
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.action.mobile.SignInAction
 * JD-Core Version:    0.6.0
 */