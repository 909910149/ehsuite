/*     */ package com.htsoft.oa.action.system;
/*     */ 
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.GsonBuilder;
/*     */ import com.google.gson.reflect.TypeToken;
/*     */ import com.htsoft.core.command.QueryFilter;
/*     */ import com.htsoft.core.util.AppUtil;
/*     */ import com.htsoft.core.web.action.BaseAction;
/*     */ import com.htsoft.core.web.paging.PagingBean;
/*     */ import com.htsoft.oa.model.system.Department;
/*     */ import com.htsoft.oa.service.system.AppUserService;
/*     */ import com.htsoft.oa.service.system.DepartmentService;
/*     */ import java.lang.reflect.Type;
/*     */ import java.util.List;
/*     */ import javax.annotation.Resource;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class DepartmentAction extends BaseAction
/*     */ {
/*     */   private Department department;
/*     */ 
/*     */   @Resource
/*     */   private DepartmentService departmentService;
/*     */ 
/*     */   @Resource
/*     */   private AppUserService appUserService;
/*     */ 
/*     */   public Department getDepartment()
/*     */   {
/*  30 */     return this.department;
/*     */   }
/*     */ 
/*     */   public void setDepartment(Department department) {
/*  34 */     this.department = department;
/*     */   }
/*     */ 
/*     */   public String select()
/*     */   {
/*  49 */     String depId = getRequest().getParameter("depId");
/*  50 */     QueryFilter filter = new QueryFilter(getRequest());
/*  51 */     if ((StringUtils.isNotEmpty(depId)) && (!"0".equals(depId))) {
/*  52 */       this.department = ((Department)this.departmentService.get(new Long(depId)));
/*  53 */       filter.addFilter("Q_path_S_LFK", this.department.getPath());
/*     */     }
/*     */ 
/*  56 */     filter.addSorted("path", "asc");
/*  57 */     List list = this.departmentService.getAll(filter);
/*  58 */     Type type = new TypeToken() {  }
/*  58 */     .getType();
/*  59 */     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':").append(filter.getPagingBean().getTotalItems()).append(",result:");
/*  60 */     Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
/*  61 */     buff.append(gson.toJson(list, type));
/*  62 */     buff.append("}");
/*  63 */     this.jsonString = buff.toString();
/*     */ 
/*  65 */     return "success";
/*     */   }
/*     */ 
/*     */   public String list() {
/*  69 */     String opt = getRequest().getParameter("opt");
/*  70 */     StringBuffer buff = new StringBuffer();
/*  71 */     if (StringUtils.isNotEmpty(opt))
/*  72 */       buff.append("[");
/*     */     else {
/*  74 */       buff.append("[{id:'0',text:'" + AppUtil.getCompanyName() + "',expanded:true,children:[");
/*     */     }
/*     */ 
/*  77 */     List<Department> listParent = this.departmentService.findByParentId(new Long(0L));
/*  78 */     for (Department dep : listParent) {
/*  79 */       buff.append("{id:'" + dep.getDepId() + "',text:'" + dep.getDepName() + "',");
/*  80 */       buff.append(findChild(dep.getDepId()));
/*     */     }
/*  82 */     if (!listParent.isEmpty()) {
/*  83 */       buff.deleteCharAt(buff.length() - 1);
/*     */     }
/*  85 */     if (StringUtils.isNotEmpty(opt))
/*  86 */       buff.append("]");
/*     */     else {
/*  88 */       buff.append("]}]");
/*     */     }
/*  90 */     setJsonString(buff.toString());
/*  91 */     return "success";
/*     */   }
/*     */ 
/*     */   public String findChild(Long depId)
/*     */   {
/*  97 */     StringBuffer buff1 = new StringBuffer("");
/*  98 */     List<Department> list = this.departmentService.findByParentId(depId);
/*  99 */     if (list.size() == 0) {
/* 100 */       buff1.append("leaf:true},");
/* 101 */       return buff1.toString();
/*     */     }
/* 103 */     buff1.append("children:[");
/* 104 */     for (Department dep2 : list) {
/* 105 */       buff1.append("{id:'" + dep2.getDepId() + "',text:'" + dep2.getDepName() + "',");
/* 106 */       buff1.append(findChild(dep2.getDepId()));
/*     */     }
/* 108 */     buff1.deleteCharAt(buff1.length() - 1);
/* 109 */     buff1.append("]},");
/* 110 */     return buff1.toString();
/*     */   }
/*     */ 
/*     */   public String add()
/*     */   {
/* 115 */     Long parentId = this.department.getParentId();
/* 116 */     String depPath = "";
/* 117 */     int level = 0;
/* 118 */     if (parentId.longValue() < 1L) {
/* 119 */       parentId = new Long(0L);
/* 120 */       depPath = "0.";
/*     */     } else {
/* 122 */       depPath = ((Department)this.departmentService.get(parentId)).getPath();
/* 123 */       level = ((Department)this.departmentService.get(parentId)).getDepLevel().intValue();
/*     */     }
/* 125 */     if (level < 1) {
/* 126 */       level = 1;
/*     */     }
/* 128 */     this.department.setDepLevel(Integer.valueOf(level + 1));
/* 129 */     this.departmentService.save(this.department);
/* 130 */     if (this.department != null) {
/* 131 */       depPath = depPath + this.department.getDepId().toString() + ".";
/* 132 */       this.department.setPath(depPath);
/* 133 */       this.departmentService.save(this.department);
/* 134 */       setJsonString("{success:true}");
/*     */     } else {
/* 136 */       setJsonString("{success:false}");
/*     */     }
/* 138 */     return "success";
/*     */   }
/*     */ 
/*     */   public String remove() {
/* 142 */     PagingBean pb = getInitPagingBean();
/* 143 */     Long depId = Long.valueOf(Long.parseLong(getRequest().getParameter("depId")));
/* 144 */     Department department = (Department)this.departmentService.get(depId);
/* 145 */     List userList = this.appUserService.findByDepartment(department.getPath(), pb);
/* 146 */     if (userList.size() > 0) {
/* 147 */       setJsonString("{success:false,message:'该部门还有人员，请将人员转移后再删除部门!'}");
/* 148 */       return "success";
/*     */     }
/* 150 */     this.departmentService.remove(depId);
/* 151 */     List<Department> list = this.departmentService.findByParentId(depId);
/* 152 */     for (Department dep : list) {
/* 153 */       List users = this.appUserService.findByDepartment(dep.getPath(), pb);
/* 154 */       if (users.size() > 0) {
/* 155 */         setJsonString("{success:false,message:'该部门还有人员，请将人员转移后再删除部门!'}");
/* 156 */         return "success";
/*     */       }
/* 158 */       this.departmentService.remove(dep.getDepId());
/*     */     }
/* 160 */     setJsonString("{success:true}");
/* 161 */     return "success";
/*     */   }
/*     */ 
/*     */   public String detail() {
/* 165 */     Long depId = Long.valueOf(Long.parseLong(getRequest().getParameter("depId")));
/* 166 */     setDepartment((Department)this.departmentService.get(depId));
/* 167 */     Gson gson = new Gson();
/* 168 */     StringBuffer sb = new StringBuffer("{success:true,data:[");
/* 169 */     sb.append(gson.toJson(this.department));
/* 170 */     sb.append("]}");
/* 171 */     setJsonString(sb.toString());
/* 172 */     return "success";
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.action.system.DepartmentAction
 * JD-Core Version:    0.6.0
 */