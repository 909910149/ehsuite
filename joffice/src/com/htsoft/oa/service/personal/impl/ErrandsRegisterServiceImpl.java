/*    */ package com.htsoft.oa.service.personal.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.core.util.BeanUtil;
/*    */ import com.htsoft.core.util.ContextUtil;
/*    */ import com.htsoft.oa.action.flow.FlowRunInfo;
/*    */ import com.htsoft.oa.dao.personal.ErrandsRegisterDao;
/*    */ import com.htsoft.oa.model.personal.ErrandsRegister;
/*    */ import com.htsoft.oa.model.system.AppUser;
/*    */ import com.htsoft.oa.service.personal.ErrandsRegisterService;
/*    */ import java.util.Map;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import org.apache.commons.logging.Log;
/*    */ 
/*    */ public class ErrandsRegisterServiceImpl extends BaseServiceImpl<ErrandsRegister>
/*    */   implements ErrandsRegisterService
/*    */ {
/*    */   private ErrandsRegisterDao dao;
/*    */ 
/*    */   public ErrandsRegisterServiceImpl(ErrandsRegisterDao dao)
/*    */   {
/* 19 */     super(dao);
/* 20 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   public Integer saveRegister(FlowRunInfo flowRunInfo)
/*    */   {
/* 29 */     AppUser curUser = ContextUtil.getCurrentUser();
/* 30 */     String comments = flowRunInfo.getRequest().getParameter("comments");
/*    */ 
/* 32 */     ErrandsRegister register = new ErrandsRegister();
/* 33 */     register.setUserId(curUser.getUserId());
/*    */     try
/*    */     {
/* 36 */       BeanUtil.populateEntity(flowRunInfo.getRequest(), register, "errandsRegister");
/*    */     } catch (Exception ex) {
/* 38 */       this.logger.error(ex.getMessage());
/* 39 */       return Integer.valueOf(0);
/*    */     }
/* 41 */     register.setApprovalOption(comments);
/* 42 */     if (register.getDateId() != null) {
/* 43 */       ErrandsRegister orgRegister = (ErrandsRegister)get(register.getDateId());
/*    */       try {
/* 45 */         BeanUtil.copyNotNullProperties(orgRegister, register);
/*    */ 
/* 47 */         orgRegister.setApprovalId(curUser.getUserId());
/* 48 */         orgRegister.setApprovalName(curUser.getFullname());
/* 49 */         String destName = flowRunInfo.getDestName();
/* 50 */         if (destName.equals("申请人查看结果"))
/* 51 */           orgRegister.setStatus(ErrandsRegister.STATUS_APPROVAL);
/*    */         else {
/* 53 */           orgRegister.setStatus(ErrandsRegister.STATUS_UNAPPROVAL);
/*    */         }
/* 55 */         this.dao.save(orgRegister);
/*    */       } catch (Exception ex) {
/* 57 */         this.logger.error(ex);
/*    */       }
/*    */     } else {
/* 60 */       this.dao.save(register);
/*    */     }
/*    */ 
/* 63 */     flowRunInfo.getVariables().put("dateId", register.getDateId());
/* 64 */     flowRunInfo.setFlowSubject(curUser.getFullname() + "提交请假申请");
/* 65 */     return Integer.valueOf(1);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.personal.impl.ErrandsRegisterServiceImpl
 * JD-Core Version:    0.6.0
 */