/*    */ package com.htsoft.oa.action.mobile.flow;
/*    */ 
/*    */ import com.htsoft.core.web.action.BaseAction;
/*    */ import com.htsoft.oa.service.flow.ProDefinitionService;
/*    */ import java.util.List;
/*    */ import javax.annotation.Resource;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ 
/*    */ public class MobileProDefAction extends BaseAction
/*    */ {
/*    */ 
/*    */   @Resource
/*    */   private ProDefinitionService proDefinitionService;
/*    */ 
/*    */   public String list()
/*    */   {
/* 16 */     List proDefList = this.proDefinitionService.getAll(getInitPagingBean());
/* 17 */     getRequest().setAttribute("proDefList", proDefList);
/* 18 */     return "success";
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.action.mobile.flow.MobileProDefAction
 * JD-Core Version:    0.6.0
 */