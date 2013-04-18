/*     */ package com.htsoft.oa.action.system;
/*     */ 
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.GsonBuilder;
/*     */ import com.htsoft.core.command.QueryFilter;
/*     */ import com.htsoft.core.util.BeanUtil;
/*     */ import com.htsoft.core.util.JsonUtil;
/*     */ import com.htsoft.core.web.action.BaseAction;
/*     */ import com.htsoft.core.web.paging.PagingBean;
/*     */ import com.htsoft.oa.model.system.AppUser;
/*     */ import com.htsoft.oa.model.system.DepUsers;
/*     */ import com.htsoft.oa.model.system.Department;
/*     */ import com.htsoft.oa.service.system.DepUsersService;
/*     */ import com.htsoft.oa.service.system.DepartmentService;
/*     */ import flexjson.JSONSerializer;
/*     */ import flexjson.transformer.DateTransformer;
/*     */ import java.util.List;
/*     */ import javax.annotation.Resource;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.commons.logging.Log;
/*     */ 
/*     */ public class DepUsersAction extends BaseAction
/*     */ {
/*     */ 
/*     */   @Resource
/*     */   private DepUsersService depUsersService;
/*     */ 
/*     */   @Resource
/*     */   private DepartmentService departmentService;
/*     */   private DepUsers depUsers;
/*     */   private Long depUserId;
/*     */ 
/*     */   public Long getDepUserId()
/*     */   {
/*  47 */     return this.depUserId;
/*     */   }
/*     */ 
/*     */   public void setDepUserId(Long depUserId) {
/*  51 */     this.depUserId = depUserId;
/*     */   }
/*     */ 
/*     */   public DepUsers getDepUsers() {
/*  55 */     return this.depUsers;
/*     */   }
/*     */ 
/*     */   public void setDepUsers(DepUsers depUsers) {
/*  59 */     this.depUsers = depUsers;
/*     */   }
/*     */ 
/*     */   public String list()
/*     */   {
/*  66 */     QueryFilter filter = new QueryFilter(getRequest());
/*  67 */     String strDepId = getRequest().getParameter("depId");
/*     */ 
/*  69 */     String path = "0.";
/*  70 */     if (StringUtils.isNotEmpty(strDepId)) {
/*  71 */       Long depId = Long.valueOf(Long.parseLong(strDepId));
/*  72 */       Department dep = (Department)this.departmentService.get(depId);
/*  73 */       if (dep != null) {
/*  74 */         path = dep.getPath();
/*  75 */         filter.addFilter("Q_department.path_S_LK", path);
/*     */       }
/*     */     }
/*  78 */     List list = this.depUsersService.getAll(filter);
/*  79 */     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
/*  80 */       .append(filter.getPagingBean().getTotalItems()).append(
/*  81 */       ",result:");
/*  82 */     JSONSerializer serializer = new JSONSerializer();
/*  83 */     serializer.transform(new DateTransformer("yyyy-MM-dd"), 
/*  84 */       new String[] { "appUser.accessionTime" });
/*  85 */     buff.append(serializer.serialize(list));
/*     */ 
/*  87 */     buff.append("}");
/*  88 */     this.jsonString = buff.toString();
/*  89 */     return "success";
/*     */   }
/*     */ 
/*     */   public String multiDel()
/*     */   {
/*  99 */     String[] ids = getRequest().getParameterValues("ids");
/* 100 */     if (ids != null) {
/* 101 */       for (String id : ids)
/* 102 */         this.depUsersService.remove(new Long(id));
/*     */     }
/* 104 */     this.jsonString = "{success:true}";
/* 105 */     return "success";
/*     */   }
/*     */ 
/*     */   public String get()
/*     */   {
/* 112 */     DepUsers depUsers = (DepUsers)this.depUsersService.get(this.depUserId);
/* 113 */     StringBuffer sb = new StringBuffer("{success:true,data:");
/* 114 */     sb.append(JsonUtil.getJSONSerializer(new String[] { "accessionTime" })
/* 115 */       .serialize(depUsers));
/* 116 */     sb.append("}");
/* 117 */     setJsonString(sb.toString());
/* 118 */     return "success";
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/* 125 */     String msg = "{success:true,msg:'数据操作成功！'}";
/*     */ 
/* 127 */     boolean isAdd = (this.depUsers != null) && (this.depUsers.getDepUserId() == null);
/*     */ 
/* 129 */     Long userId = this.depUsers.getAppUser().getUserId();
/* 130 */     if ((this.depUsers.getIsMain().equals(DepUsers.ISMAIN)) && 
/* 131 */       (this.depUsersService.existsDep(this.depUsers.getDepUserId(), userId).booleanValue())) {
/* 132 */       msg = "{failure:true,msg:'对不起，该用户已经添加了主部门，请选择添加副部门！'}";
/* 133 */       setJsonString(msg);
/* 134 */       return "success";
/*     */     }
/*     */ 
/* 137 */     QueryFilter filter = new QueryFilter(getRequest());
/* 138 */     filter.addSorted("sn", "DESC");
/* 139 */     filter.getPagingBean().setPageSize(1);
/* 140 */     filter.getPagingBean().setStart(Integer.valueOf(0));
/* 141 */     List list = this.depUsersService.getAll(filter);
/* 142 */     Integer sn = Integer.valueOf(0);
/* 143 */     if ((list != null) && (list.size() > 0))
/* 144 */       sn = Integer.valueOf(((DepUsers)list.get(0)).getSn().intValue() + 1);
/* 145 */     this.depUsers.setSn(sn);
/*     */ 
/* 147 */     if (isAdd)
/* 148 */       msg = this.depUsersService.add(this.depUsers);
/*     */     else
/* 150 */       this.depUsersService.save(this.depUsers);
/* 151 */     setJsonString(msg);
/* 152 */     return "success";
/*     */   }
/*     */ 
/*     */   public String sn()
/*     */   {
/* 186 */     String depParams = getRequest().getParameter("depParams");
/* 187 */     if (StringUtils.isNotEmpty(depParams)) {
/* 188 */       Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
/* 189 */       DepUsers[] dus = (DepUsers[])gson.fromJson(depParams, com.htsoft.oa.model.system.DepUsers[].class);
/* 190 */       if ((dus != null) && (dus.length > 0)) {
/* 191 */         for (DepUsers du : dus)
/*     */         {
/* 193 */           if (du.getDepUserId() != null) {
/* 194 */             DepUsers orgDepUsers = (DepUsers)this.depUsersService.get(du
/* 195 */               .getDepUserId());
/*     */             try
/*     */             {
/* 198 */               BeanUtil.copyNotNullProperties(orgDepUsers, du);
/* 199 */               this.depUsersService.save(orgDepUsers);
/*     */             } catch (Exception ex) {
/* 201 */               this.logger.error(ex.getMessage());
/*     */             }
/*     */           } else {
/* 204 */             this.depUsersService.save(du);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 209 */     setJsonString("{success:true}");
/* 210 */     return "success";
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.action.system.DepUsersAction
 * JD-Core Version:    0.6.0
 */