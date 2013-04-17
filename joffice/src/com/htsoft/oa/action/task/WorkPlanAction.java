/*     */ package com.htsoft.oa.action.task;
/*     */ 
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.GsonBuilder;
/*     */ import com.htsoft.core.command.QueryFilter;
/*     */ import com.htsoft.core.util.ContextUtil;
/*     */ import com.htsoft.core.util.JsonUtil;
/*     */ import com.htsoft.core.web.action.BaseAction;
/*     */ import com.htsoft.core.web.paging.PagingBean;
/*     */ import com.htsoft.oa.model.system.AppUser;
/*     */ import com.htsoft.oa.model.system.Department;
/*     */ import com.htsoft.oa.model.system.FileAttach;
/*     */ import com.htsoft.oa.model.task.PlanAttend;
/*     */ import com.htsoft.oa.model.task.WorkPlan;
/*     */ import com.htsoft.oa.service.system.AppUserService;
/*     */ import com.htsoft.oa.service.system.DepartmentService;
/*     */ import com.htsoft.oa.service.system.FileAttachService;
/*     */ import com.htsoft.oa.service.task.PlanAttendService;
/*     */ import com.htsoft.oa.service.task.WorkPlanService;
/*     */ import flexjson.JSONSerializer;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Resource;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class WorkPlanAction extends BaseAction
/*     */ {
/*     */ 
/*     */   @Resource
/*     */   private WorkPlanService workPlanService;
/*     */   private WorkPlan workPlan;
/*     */ 
/*     */   @Resource
/*     */   private FileAttachService fileAttachService;
/*     */ 
/*     */   @Resource
/*     */   private DepartmentService departmentService;
/*     */ 
/*     */   @Resource
/*     */   private PlanAttendService planAttendService;
/*     */ 
/*     */   @Resource
/*     */   private AppUserService appUserService;
/*     */   private Long planId;
/*  52 */   static short ISDEPARTMENT = 1;
/*  53 */   static short NOTDEPARTMENT = 0;
/*  54 */   static short ISPRIMARY = 1;
/*  55 */   static short NOTPRIMARY = 0;
/*     */ 
/*     */   public Long getPlanId() {
/*  58 */     return this.planId;
/*     */   }
/*     */ 
/*     */   public void setPlanId(Long planId) {
/*  62 */     this.planId = planId;
/*     */   }
/*     */ 
/*     */   public WorkPlan getWorkPlan() {
/*  66 */     return this.workPlan;
/*     */   }
/*     */ 
/*     */   public void setWorkPlan(WorkPlan workPlan) {
/*  70 */     this.workPlan = workPlan;
/*     */   }
/*     */ 
/*     */   public String list()
/*     */   {
/*  77 */     Long userId = ContextUtil.getCurrentUserId();
/*  78 */     QueryFilter filter = new QueryFilter(getRequest());
/*  79 */     filter.addFilter("Q_appUser.userId_L_EQ", userId.toString());
/*  80 */     List list = this.workPlanService.getAll(filter);
/*  81 */     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
/*  82 */       .append(filter.getPagingBean().getTotalItems()).append(",result:");
/*  83 */     JSONSerializer serializer = JsonUtil.getJSONSerializer(new String[] { "startTime", "endTime" });
/*  84 */     buff.append(serializer.exclude(new String[] { "class", "appUser.password" }).serialize(
/*  85 */       list));
/*  86 */     buff.append("}");
/*     */ 
/*  88 */     this.jsonString = buff.toString();
/*     */ 
/*  90 */     return "success";
/*     */   }
/*     */ 
/*     */   public String personal()
/*     */   {
/*  98 */     QueryFilter filter = new QueryFilter(getRequest());
/*  99 */     Long userId = ContextUtil.getCurrentUserId();
/* 100 */     filter.addFilter("Q_appUser.userId_L_EQ", userId.toString());
/* 101 */     filter.addFilter("Q_isPersonal_SN_EQ", "1");
/* 102 */     filter.addFilter("Q_status_SN_EQ", "1");
/* 103 */     List list = this.workPlanService.getAll(filter);
/* 104 */     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
/* 105 */       .append(filter.getPagingBean().getTotalItems()).append(",result:");
/* 106 */     JSONSerializer serializer = JsonUtil.getJSONSerializer(new String[] { "startTime", "endTime" });
/* 107 */     buff.append(serializer.exclude(new String[] { "class", "appUser.password", "department" }).serialize(
/* 108 */       list));
/* 109 */     buff.append("}");
/* 110 */     this.jsonString = buff.toString();
/* 111 */     return "success";
/*     */   }
/*     */ 
/*     */   public String department()
/*     */   {
/* 118 */     PagingBean pb = getInitPagingBean();
/* 119 */     AppUser user = ContextUtil.getCurrentUser();
/* 120 */     List list = this.workPlanService.findByDepartment(this.workPlan, user, pb);
/* 121 */     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
/* 122 */       .append(pb.getTotalItems()).append(",result:");
/* 123 */     JSONSerializer serializer = JsonUtil.getJSONSerializer(new String[] { "startTime", "endTime" });
/* 124 */     buff.append(serializer.exclude(new String[] { "class", "appUser.password", "department" }).serialize(
/* 125 */       list));
/* 126 */     buff.append("}");
/* 127 */     this.jsonString = buff.toString();
/* 128 */     return "success";
/*     */   }
/*     */ 
/*     */   public String multiDel()
/*     */   {
/* 136 */     String[] ids = getRequest().getParameterValues("ids");
/* 137 */     if (ids != null) {
/* 138 */       for (String id : ids) {
/* 139 */         this.workPlanService.remove(new Long(id));
/*     */       }
/*     */     }
/* 142 */     this.jsonString = "{success:true}";
/* 143 */     return "success";
/*     */   }
/*     */ 
/*     */   public String get()
/*     */   {
/* 151 */     WorkPlan workPlan = (WorkPlan)this.workPlanService.get(this.planId);
/* 152 */     Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
/*     */ 
/* 154 */     StringBuffer sb = new StringBuffer("{success:true,data:");
/* 155 */     sb.append(gson.toJson(workPlan));
/* 156 */     sb.append(",proTypeId:" + workPlan.getProTypeId());
/* 157 */     sb.append("}");
/* 158 */     setJsonString(sb.toString());
/* 159 */     return "success";
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/* 165 */     String issueScopeIds = getRequest().getParameter("issueScopeIds");
/* 166 */     String participantsIds = getRequest().getParameter("participantsIds");
/* 167 */     String principalIds = getRequest().getParameter("principalIds");
/* 168 */     String fileIds = getRequest().getParameter("planFileIds");
/* 169 */     short isPersonal = this.workPlan.getIsPersonal().shortValue();
/* 170 */     this.workPlan.getPlanFiles().clear();
/* 171 */     if (StringUtils.isNotEmpty(fileIds)) {
/* 172 */       String[] fIds = fileIds.split(",");
/* 173 */       for (int i = 0; i < fIds.length; i++) {
/* 174 */         FileAttach fileAttach = (FileAttach)this.fileAttachService.get(new Long(fIds[i]));
/* 175 */         this.workPlan.getPlanFiles().add(fileAttach);
/*     */       }
/*     */     }
/* 178 */     this.workPlan.setPlanFiles(this.workPlan.getPlanFiles());
/* 179 */     AppUser appUser = ContextUtil.getCurrentUser();
/* 180 */     this.workPlan.setAppUser(appUser);
/* 181 */     if (isPersonal == 1) {
/* 182 */       this.workPlan.setPrincipal(appUser.getFullname());
/*     */     }
/* 184 */     this.workPlanService.save(this.workPlan);
/* 185 */     if (isPersonal != 1) {
/* 186 */       if (StringUtils.isNotEmpty(issueScopeIds)) {
/* 187 */         boolean b = this.planAttendService.deletePlanAttend(this.workPlan.getPlanId(), Short.valueOf(ISDEPARTMENT), Short.valueOf(NOTPRIMARY));
/* 188 */         if (b) {
/* 189 */           String[] strIssueScopeId = issueScopeIds.split(",");
/* 190 */           for (int i = 0; i < strIssueScopeId.length; i++) {
/* 191 */             if (StringUtils.isNotEmpty(strIssueScopeId[i])) {
/* 192 */               Long depId = new Long(strIssueScopeId[i]);
/* 193 */               PlanAttend pa = new PlanAttend();
/* 194 */               pa.setDepartment((Department)this.departmentService.get(depId));
/* 195 */               pa.setWorkPlan(this.workPlan);
/* 196 */               pa.setIsDep(Short.valueOf(ISDEPARTMENT));
/* 197 */               pa.setIsPrimary(Short.valueOf(NOTPRIMARY));
/* 198 */               this.planAttendService.save(pa);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/* 203 */       if (StringUtils.isNotEmpty(participantsIds)) {
/* 204 */         boolean b = this.planAttendService.deletePlanAttend(this.workPlan.getPlanId(), Short.valueOf(NOTDEPARTMENT), Short.valueOf(NOTPRIMARY));
/* 205 */         if (b) {
/* 206 */           String[] strParticipantsId = participantsIds.split(",");
/* 207 */           for (int i = 0; i < strParticipantsId.length; i++) {
/* 208 */             if (StringUtils.isNotEmpty(strParticipantsId[i])) {
/* 209 */               Long userId = new Long(strParticipantsId[i]);
/* 210 */               PlanAttend pa = new PlanAttend();
/* 211 */               pa.setAppUser((AppUser)this.appUserService.get(userId));
/* 212 */               pa.setIsDep(Short.valueOf(NOTDEPARTMENT));
/* 213 */               pa.setWorkPlan(this.workPlan);
/* 214 */               pa.setIsPrimary(Short.valueOf(NOTPRIMARY));
/* 215 */               this.planAttendService.save(pa);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/* 220 */       if (StringUtils.isNotEmpty(principalIds)) {
/* 221 */         boolean b = this.planAttendService.deletePlanAttend(this.workPlan.getPlanId(), Short.valueOf(NOTDEPARTMENT), Short.valueOf(ISPRIMARY));
/* 222 */         if (b) {
/* 223 */           String[] strPrincipalId = principalIds.split(",");
/* 224 */           for (int i = 0; i < strPrincipalId.length; i++) {
/* 225 */             if (StringUtils.isNotEmpty(strPrincipalId[i])) {
/* 226 */               Long userId = new Long(strPrincipalId[i]);
/* 227 */               PlanAttend pa = new PlanAttend();
/* 228 */               pa.setAppUser((AppUser)this.appUserService.get(userId));
/* 229 */               pa.setIsDep(Short.valueOf(NOTDEPARTMENT));
/* 230 */               pa.setWorkPlan(this.workPlan);
/* 231 */               pa.setIsPrimary(Short.valueOf(ISPRIMARY));
/* 232 */               this.planAttendService.save(pa);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 238 */     setJsonString("{success:true}");
/* 239 */     return "success";
/*     */   }
/*     */ 
/*     */   public String show()
/*     */   {
/* 246 */     String strPlanId = getRequest().getParameter("planId");
/* 247 */     if (StringUtils.isNotEmpty(strPlanId)) {
/* 248 */       Long planId = new Long(strPlanId);
/* 249 */       this.workPlan = ((WorkPlan)this.workPlanService.get(planId));
/*     */     }
/* 251 */     return "show";
/*     */   }
/*     */ 
/*     */   public String display()
/*     */   {
/* 259 */     QueryFilter filter = new QueryFilter(getRequest());
/* 260 */     Long userId = ContextUtil.getCurrentUserId();
/* 261 */     filter.addFilter("Q_appUser.userId_L_EQ", userId.toString());
/* 262 */     filter.addFilter("Q_isPersonal_SN_EQ", "1");
/* 263 */     filter.addFilter("Q_status_SN_EQ", "1");
/* 264 */     filter.addSorted("planId", "desc");
/* 265 */     List list = this.workPlanService.getAll(filter);
/* 266 */     getRequest().setAttribute("planList", list);
/* 267 */     return "display";
/*     */   }
/*     */ 
/*     */   public String displayDep()
/*     */   {
/* 275 */     PagingBean pb = new PagingBean(0, 8);
/* 276 */     AppUser user = ContextUtil.getCurrentUser();
/* 277 */     List list = this.workPlanService.findByDepartment(this.workPlan, user, pb);
/* 278 */     getRequest().setAttribute("planList", list);
/* 279 */     return "displayDep";
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.action.task.WorkPlanAction
 * JD-Core Version:    0.6.0
 */