/*     */ package com.htsoft.oa.action.system;
/*     */ 
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.reflect.TypeToken;
/*     */ import com.htsoft.core.command.QueryFilter;
/*     */ import com.htsoft.core.util.BeanUtil;
/*     */ import com.htsoft.core.util.ContextUtil;
/*     */ import com.htsoft.core.web.action.BaseAction;
/*     */ import com.htsoft.core.web.paging.PagingBean;
/*     */ import com.htsoft.oa.model.system.AppUser;
/*     */ import com.htsoft.oa.model.system.GlobalType;
/*     */ import com.htsoft.oa.service.system.GlobalTypeService;
/*     */ import java.io.PrintStream;
/*     */ import java.lang.reflect.Type;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Resource;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.commons.logging.Log;
/*     */ 
/*     */ public class GlobalTypeAction extends BaseAction
/*     */ {
/*     */ 
/*     */   @Resource
/*     */   private GlobalTypeService globalTypeService;
/*     */   private GlobalType globalType;
/*     */   private Long proTypeId;
/*  36 */   private String catKey = "PT";
/*     */ 
/*     */   public String getCatKey() {
/*  39 */     return this.catKey;
/*     */   }
/*     */ 
/*     */   public void setCatKey(String catKey) {
/*  43 */     this.catKey = catKey;
/*     */   }
/*     */ 
/*     */   public Long getProTypeId() {
/*  47 */     return this.proTypeId;
/*     */   }
/*     */ 
/*     */   public void setProTypeId(Long proTypeId) {
/*  51 */     this.proTypeId = proTypeId;
/*     */   }
/*     */ 
/*     */   public GlobalType getGlobalType() {
/*  55 */     return this.globalType;
/*     */   }
/*     */ 
/*     */   public void setGlobalType(GlobalType globalType) {
/*  59 */     this.globalType = globalType;
/*     */   }
/*     */ 
/*     */   public String sub()
/*     */   {
/*  67 */     Long parentId = null;
/*  68 */     String sParentId = getRequest().getParameter("parentId");
/*  69 */     if (StringUtils.isNotEmpty(sParentId)) {
/*  70 */       parentId = new Long(sParentId);
/*     */     }
/*  72 */     List typeList = this.globalTypeService.getByParentIdCatKey(parentId, this.catKey);
/*     */ 
/*  74 */     Type type = new TypeToken() {  }
/*  74 */     .getType();
/*  75 */     StringBuffer buff = new StringBuffer("{success:true,result:");
/*     */ 
/*  77 */     Gson gson = new Gson();
/*  78 */     buff.append(gson.toJson(typeList, type));
/*  79 */     buff.append("}");
/*     */ 
/*  81 */     this.jsonString = buff.toString();
/*     */ 
/*  83 */     return "success";
/*     */   }
/*     */ 
/*     */   public String mulSave() {
/*  87 */     String data = getRequest().getParameter("data");
/*     */ 
/*  89 */     this.logger.info("data:" + data);
/*     */ 
/*  91 */     if (StringUtils.isNotEmpty(data)) {
/*  92 */       Gson gson = new Gson();
/*  93 */       GlobalType[] types = (GlobalType[])gson.fromJson(data, com.htsoft.oa.model.system.GlobalType[].class);
/*     */ 
/*  95 */       for (int i = 0; i < types.length; i++) {
/*  96 */         GlobalType newType = (GlobalType)this.globalTypeService.get(types[i].getProTypeId());
/*     */         try {
/*  98 */           BeanUtil.copyNotNullProperties(newType, types[i]);
/*  99 */           newType.setSn(Integer.valueOf(i + 1));
/* 100 */           this.globalTypeService.save(newType);
/*     */         } catch (Exception ex) {
/* 102 */           this.logger.error(ex.getMessage());
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 107 */     this.jsonString = "{success:true}";
/* 108 */     return "success";
/*     */   }
/*     */ 
/*     */   public String tree()
/*     */   {
/* 116 */     StringBuffer buff = new StringBuffer("[{id:'0',text:'总分类',expanded:true,children:[");
/* 117 */     List<GlobalType> typeList = this.globalTypeService.getByParentIdCatKey(new Long(0L), this.catKey);
/* 118 */     for (GlobalType type : typeList) {
/* 119 */       buff.append("{id:'" + type.getProTypeId()).append("',text:'" + type.getTypeName()).append("',");
/* 120 */       buff.append(getChildType(type.getProTypeId(), this.catKey));
/*     */     }
/* 122 */     if (!typeList.isEmpty()) {
/* 123 */       buff.deleteCharAt(buff.length() - 1);
/*     */     }
/* 125 */     buff.append("]}]");
/* 126 */     setJsonString(buff.toString());
/*     */ 
/* 128 */     this.logger.info("tree json:" + buff.toString());
/* 129 */     return "success";
/*     */   }
/*     */ 
/*     */   public String proTree()
/*     */   {
/* 137 */     StringBuffer buff = new StringBuffer("[{id:'0',text:'总分类',expanded:true,children:[");
/* 138 */     List<GlobalType> typeList = this.globalTypeService.getByParentIdCatKey(new Long(0L), this.catKey);
/* 139 */     for (GlobalType type : typeList) {
/* 140 */       buff.append("{id:'" + type.getProTypeId()).append("',text:'" + type.getTypeName()).append("',");
/* 141 */       buff.append("leaf:true,expanded:true},");
/*     */     }
/* 143 */     if (!typeList.isEmpty()) {
/* 144 */       buff.deleteCharAt(buff.length() - 1);
/*     */     }
/* 146 */     buff.append("]}]");
/* 147 */     setJsonString(buff.toString());
/*     */ 
/* 149 */     this.logger.info("tree json:" + buff.toString());
/* 150 */     return "success";
/*     */   }
/*     */ 
/*     */   public String pwTree()
/*     */   {
/* 159 */     StringBuffer buff = new StringBuffer("[{id:'0',text:'总分类',expanded:true,children:[");
/* 160 */     List<GlobalType> typeList = this.globalTypeService.getByParentIdCatKeyUserId(new Long(0L), this.catKey, ContextUtil.getCurrentUserId());
/* 161 */     for (GlobalType type : typeList) {
/* 162 */       buff.append("{id:'" + type.getProTypeId()).append("',text:'" + type.getTypeName()).append("',");
/* 163 */       buff.append("leaf:true,expanded:true},");
/*     */     }
/* 165 */     if (!typeList.isEmpty()) {
/* 166 */       buff.deleteCharAt(buff.length() - 1);
/*     */     }
/* 168 */     buff.append("]}]");
/* 169 */     setJsonString(buff.toString());
/* 170 */     this.logger.info("tree json:" + buff.toString());
/* 171 */     return "success";
/*     */   }
/*     */ 
/*     */   public String combo()
/*     */   {
/* 179 */     StringBuffer sb = new StringBuffer();
/* 180 */     List<GlobalType> typeList = this.globalTypeService.getByParentIdCatKeyUserId(new Long(0L), this.catKey, ContextUtil.getCurrentUserId());
/* 181 */     sb.append("[");
/* 182 */     for (GlobalType globalType : typeList) {
/* 183 */       sb.append("['").append(globalType.getProTypeId()).append("','").append(globalType.getTypeName()).append("'],");
/*     */     }
/* 185 */     if (typeList.size() > 0) {
/* 186 */       sb.deleteCharAt(sb.length() - 1);
/*     */     }
/* 188 */     sb.append("]");
/* 189 */     setJsonString(sb.toString());
/* 190 */     return "success";
/*     */   }
/*     */ 
/*     */   public String getChildType(Long parentId, String catKey) {
/* 194 */     StringBuffer buff = new StringBuffer();
/* 195 */     List<GlobalType> typeList = this.globalTypeService.getByParentIdCatKey(parentId, catKey);
/* 196 */     if (typeList.size() == 0) {
/* 197 */       buff.append("leaf:true,expanded:true},");
/* 198 */       return buff.toString();
/*     */     }
/* 200 */     buff.append("expanded:true,children:[");
/* 201 */     for (GlobalType type : typeList) {
/* 202 */       buff.append("{id:'" + type.getProTypeId()).append("',text:'" + type.getTypeName()).append("',");
/* 203 */       buff.append(getChildType(type.getProTypeId(), catKey));
/*     */     }
/* 205 */     buff.deleteCharAt(buff.length() - 1);
/* 206 */     buff.append("]},");
/* 207 */     return buff.toString();
/*     */   }
/*     */ 
/*     */   public String list()
/*     */   {
/* 216 */     QueryFilter filter = new QueryFilter(getRequest());
/* 217 */     List list = this.globalTypeService.getAll(filter);
/*     */ 
/* 219 */     Type type = new TypeToken() {  }
/* 219 */     .getType();
/* 220 */     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
/* 221 */       .append(filter.getPagingBean().getTotalItems()).append(",result:");
/*     */ 
/* 223 */     Gson gson = new Gson();
/* 224 */     buff.append(gson.toJson(list, type));
/* 225 */     buff.append("}");
/*     */ 
/* 227 */     this.jsonString = buff.toString();
/*     */ 
/* 229 */     return "success";
/*     */   }
/*     */ 
/*     */   public String multiDel()
/*     */   {
/* 237 */     String[] ids = getRequest().getParameterValues("ids");
/* 238 */     if (ids != null) {
/* 239 */       for (String id : ids)
/*     */       {
/* 241 */         this.globalTypeService.mulDel(new Long(id));
/*     */       }
/*     */     }
/*     */ 
/* 245 */     this.jsonString = "{success:true}";
/*     */ 
/* 247 */     return "success";
/*     */   }
/*     */ 
/*     */   public String get()
/*     */   {
/* 255 */     GlobalType globalType = (GlobalType)this.globalTypeService.get(this.proTypeId);
/*     */ 
/* 257 */     Gson gson = new Gson();
/*     */ 
/* 259 */     StringBuffer sb = new StringBuffer("{success:true,data:");
/* 260 */     sb.append(gson.toJson(globalType));
/* 261 */     sb.append("}");
/* 262 */     setJsonString(sb.toString());
/*     */ 
/* 264 */     return "success";
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/* 270 */     if ((this.globalType != null) && (this.globalType.getProTypeId() != null)) {
/* 271 */       GlobalType orgGlobalType = (GlobalType)this.globalTypeService.get(this.globalType.getProTypeId());
/*     */       try {
/* 273 */         BeanUtil.copyNotNullProperties(orgGlobalType, this.globalType);
/* 274 */         this.globalTypeService.save(orgGlobalType);
/*     */       } catch (Exception ex) {
/* 276 */         this.logger.error(ex.getMessage());
/*     */       }
/*     */     }
/*     */     else {
/* 280 */       String parentPath = "0.";
/* 281 */       int level = 1;
/* 282 */       if ((this.globalType != null) && (this.globalType.getParentId() != null) && (this.globalType.getParentId().longValue() != 0L)) {
/* 283 */         GlobalType parentType = (GlobalType)this.globalTypeService.get(this.globalType.getParentId());
/* 284 */         if (parentType != null) {
/* 285 */           parentPath = parentType.getPath();
/* 286 */           level = parentType.getDepth().intValue() + 1;
/*     */         }
/*     */       }
/* 289 */       this.globalType.setDepth(Integer.valueOf(level));
/*     */ 
/* 293 */       Integer sn = this.globalTypeService.getCountsByParentId(this.globalType.getParentId());
/* 294 */       this.globalType.setSn(Integer.valueOf(sn.intValue() + 1));
/* 295 */       this.globalTypeService.save(this.globalType);
/*     */ 
/* 297 */       this.globalType.setPath(parentPath + this.globalType.getProTypeId() + ".");
/*     */ 
/* 299 */       this.globalTypeService.save(this.globalType);
/*     */     }
/*     */ 
/* 302 */     setJsonString("{success:true}");
/* 303 */     return "success";
/*     */   }
/*     */ 
/*     */   public String flowTree()
/*     */   {
/* 311 */     StringBuffer buff = new StringBuffer("[{id:'0',text:'总分类',expanded:true,children:[");
/* 312 */     AppUser curUser = ContextUtil.getCurrentUser();
/* 313 */     List<GlobalType> typeList = null;
/* 314 */     if (curUser.isSupperManage())
/* 315 */       typeList = this.globalTypeService.getByParentIdCatKey(new Long(0L), this.catKey);
/*     */     else {
/* 317 */       typeList = this.globalTypeService.getByRightsCatKey(curUser, this.catKey);
/*     */     }
/* 319 */     Set record = new HashSet();
/* 320 */     for (GlobalType type : typeList) {
/* 321 */       if (!record.contains(type)) {
/* 322 */         record.add(type);
/* 323 */         buff.append("{id:'" + type.getProTypeId()).append("',text:'" + type.getTypeName()).append("',");
/* 324 */         buff.append(getTypeByRights(type.getProTypeId(), this.catKey, record));
/*     */       }
/*     */     }
/* 327 */     if (!typeList.isEmpty()) {
/* 328 */       buff.deleteCharAt(buff.length() - 1);
/*     */     }
/* 330 */     buff.append("]}]");
/* 331 */     setJsonString(buff.toString());
/*     */ 
/* 333 */     this.logger.info("tree json:" + buff.toString());
/* 334 */     return "success";
/*     */   }
/*     */ 
/*     */   private String getTypeByRights(Long parentId, String catKey, Set<GlobalType> record) {
/* 338 */     StringBuffer buff = new StringBuffer();
/* 339 */     List<GlobalType> typeList = this.globalTypeService.getByParentIdCatKey(parentId, catKey);
/*     */ 
/* 341 */     if (typeList.size() == 0) {
/* 342 */       buff.append("leaf:true,expanded:true},");
/* 343 */       return buff.toString();
/*     */     }
/* 345 */     buff.append("expanded:true,children:[");
/* 346 */     for (GlobalType type : typeList) {
/* 347 */       if (!record.contains(type)) {
/* 348 */         record.add(type);
/* 349 */         System.out.println(type.getPath());
/* 350 */         buff.append("{id:'" + type.getProTypeId()).append("',text:'" + type.getTypeName()).append("',");
/* 351 */         buff.append(getTypeByRights(type.getProTypeId(), catKey, record));
/*     */       } else {
/* 353 */         System.out.print("已经存在" + type.getTypeName());
/*     */       }
/*     */     }
/*     */ 
/* 357 */     buff.deleteCharAt(buff.length() - 1);
/* 358 */     buff.append("]},");
/* 359 */     return buff.toString();
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.action.system.GlobalTypeAction
 * JD-Core Version:    0.6.0
 */