/*     */ package com.htsoft.oa.action.system;
/*     */ 
/*     */ import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.model.OnlineUser;
import com.htsoft.core.util.BeanUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.system.Dictionary;
import com.htsoft.oa.model.system.GlobalType;
import com.htsoft.oa.service.system.DictionaryService;
import com.htsoft.oa.service.system.GlobalTypeService;

import flexjson.JSONSerializer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DictionaryAction extends BaseAction
/*     */ {
/*     */ 
/*     */   @Resource
/*     */   private DictionaryService dictionaryService;
/*     */ 
/*     */   @Resource
/*     */   private GlobalTypeService globalTypeService;
/*     */   private Dictionary dictionary;
/*     */   private Long dicId;
/*     */   private String itemName;
/*     */ 
/*     */   public String getItemName()
/*     */   {
/*  42 */     return this.itemName;
/*     */   }
/*     */ 
/*     */   public void setItemName(String itemName) {
/*  46 */     this.itemName = itemName;
/*     */   }
/*     */ 
/*     */   public Long getDicId() {
/*  50 */     return this.dicId;
/*     */   }
/*     */ 
/*     */   public void setDicId(Long dicId) {
/*  54 */     this.dicId = dicId;
/*     */   }
/*     */ 
/*     */   public Dictionary getDictionary() {
/*  58 */     return this.dictionary;
/*     */   }
/*     */ 
/*     */   public void setDictionary(Dictionary dictionary) {
/*  62 */     this.dictionary = dictionary;
/*     */   }
/*     */ 
/*     */   public String mulSave() {
/*  66 */     String data = getRequest().getParameter("data");
/*     */ 
/*  68 */     if (StringUtils.isNotEmpty(data)) {
/*  69 */       Gson gson = new Gson();
/*  70 */       Dictionary[] dics = (Dictionary[])gson.fromJson(data, com.htsoft.oa.model.system.Dictionary[].class);
/*     */ 
/*  72 */       for (int i = 0; i < dics.length; i++) {
/*  73 */         Dictionary dic = (Dictionary)this.dictionaryService.get(dics[i].getDicId());
/*     */         try {
/*  75 */           BeanUtil.copyNotNullProperties(dic, dics[i]);
/*     */ 
/*  77 */           this.dictionaryService.save(dic);
/*     */         } catch (Exception ex) {
/*  79 */           this.logger.error(ex.getMessage());
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/*  85 */     this.jsonString = "{success:true}";
/*  86 */     return "success";
/*     */   }
/*     */ 
/*     */   public String list()
/*     */   {
/*  94 */     QueryFilter filter = new QueryFilter(getRequest());
/*  95 */     String sParentId = getRequest().getParameter("parentId");
/*  96 */     if ((StringUtils.isNotEmpty(sParentId)) && (!"0".equals(sParentId))) {
/*  97 */       GlobalType globalType = (GlobalType)this.globalTypeService.get(new Long(sParentId));
/*  98 */       filter.addFilter("Q_globalType.path_S_LFK", globalType.getPath());
/*     */     }
/* 100 */     List list = this.dictionaryService.getAll(filter);
/* 101 */     Type type = new TypeToken<List<OnlineUser>>() {
/* 102 */     }.getType();
/* 103 */     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
/* 104 */       .append(filter.getPagingBean().getTotalItems()).append(
/* 105 */       ",result:");
/*     */ 
/* 107 */     JSONSerializer json = new JSONSerializer();
/* 108 */     buff.append(json.serialize(list));
/*     */ 
/* 110 */     buff.append("}");
/*     */ 
/* 112 */     this.jsonString = buff.toString();
/*     */ 
/* 114 */     return "success";
/*     */   }
/*     */ 
/*     */   public String load()
/*     */   {
/* 123 */     List<String> list = this.dictionaryService.getAllByItemName(this.itemName);
/* 124 */     StringBuffer buff = new StringBuffer("[");
/* 125 */     for (String itemName : list) {
/* 126 */       buff.append("'").append(itemName).append("',");
/*     */     }
/* 128 */     if (list.size() > 0) {
/* 129 */       buff.deleteCharAt(buff.length() - 1);
/*     */     }
/* 131 */     buff.append("]");
/* 132 */     setJsonString(buff.toString());
/* 133 */     return "success";
/*     */   }
/*     */ 
/*     */   public String loadItem() {
/* 137 */     List<Dictionary> list = this.dictionaryService.getByItemName(this.itemName);
/* 138 */     StringBuffer buff = new StringBuffer("[");
/* 139 */     for (Dictionary dic : list) {
/* 140 */       buff.append("[").append(dic.getDicId()).append(",'")
/* 141 */         .append(dic.getItemValue()).append("'],");
/*     */     }
/*     */ 
/* 144 */     if (list.size() > 0) {
/* 145 */       buff.deleteCharAt(buff.length() - 1);
/*     */     }
/* 147 */     buff.append("]");
/* 148 */     setJsonString(buff.toString());
/* 149 */     return "success";
/*     */   }
/*     */ 
/*     */   public String loadItemRecord() {
/* 153 */     List list = this.dictionaryService.getByItemName(this.itemName);
/* 154 */     Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
/* 155 */       .create();
/*     */ 
/* 157 */     StringBuffer sb = new StringBuffer("{success:true,data:");
/* 158 */     sb.append(gson.toJson(list));
/* 159 */     sb.append("}");
/* 160 */     setJsonString(sb.toString());
/* 161 */     return "success";
/*     */   }
/*     */ 
/*     */   public String typeChange() {
/* 165 */     String dicIds = getRequest().getParameter("dicIds");
/* 166 */     String dicTypeId = getRequest().getParameter("dicTypeId");
/*     */ 
/* 168 */     if ((StringUtils.isNotEmpty(dicIds)) && (StringUtils.isNotEmpty(dicTypeId))) {
/* 169 */       GlobalType globalType = (GlobalType)this.globalTypeService.get(new Long(dicTypeId));
/*     */ 
/* 171 */       String[] ids = dicIds.split("[,]");
/* 172 */       if (ids != null) {
/* 173 */         for (String id : ids) {
/* 174 */           Dictionary dic = (Dictionary)this.dictionaryService.get(new Long(id));
/* 175 */           dic.setGlobalType(globalType);
/* 176 */           dic.setItemName(globalType.getTypeName());
/*     */ 
/* 178 */           this.dictionaryService.save(dic);
/*     */         }
/*     */       }
/*     */     }
/* 182 */     setJsonString("{success:true}");
/* 183 */     return "success";
/*     */   }
/*     */ 
/*     */   public String multiDel()
/*     */   {
/* 193 */     String[] ids = getRequest().getParameterValues("ids");
/* 194 */     if (ids != null) {
/* 195 */       for (String id : ids) {
/* 196 */         this.dictionaryService.remove(new Long(id));
/*     */       }
/*     */     }
/*     */ 
/* 200 */     this.jsonString = "{success:true}";
/*     */ 
/* 202 */     return "success";
/*     */   }
/*     */ 
/*     */   public String get()
/*     */   {
/* 211 */     Dictionary dictionary = (Dictionary)this.dictionaryService.get(this.dicId);
/*     */ 
/* 213 */     Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
/* 214 */       .create();
/*     */ 
/* 216 */     StringBuffer sb = new StringBuffer("{success:true,data:");
/* 217 */     sb.append(gson.toJson(dictionary));
/* 218 */     sb.append("}");
/* 219 */     setJsonString(sb.toString());
/*     */ 
/* 221 */     return "success";
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/* 229 */     if (this.dictionary.getDicId() != null) {
/* 230 */       Dictionary orgDic = (Dictionary)this.dictionaryService.get(this.dictionary.getDicId());
/*     */       try {
/* 232 */         BeanUtil.copyNotNullProperties(orgDic, this.dictionary);
/* 233 */         this.dictionaryService.save(orgDic);
/*     */       } catch (Exception ex) {
/* 235 */         this.logger.error(ex.getMessage());
/*     */       }
/*     */     } else {
/* 238 */       String parentId = getRequest().getParameter("parentId");
/* 239 */       if (StringUtils.isNotEmpty(parentId)) {
/* 240 */         GlobalType globalType = 
/* 241 */           (GlobalType)this.globalTypeService
/* 241 */           .get(new Long(parentId));
/* 242 */         this.dictionary.setGlobalType(globalType);
/*     */       }
/* 244 */       this.dictionaryService.save(this.dictionary);
/*     */     }
/* 246 */     setJsonString("{success:true}");
/* 247 */     return "success";
/*     */   }
/*     */ 
/*     */   public String items()
/*     */   {
/* 256 */     List<String> list = this.dictionaryService.getAllItems();
/* 257 */     StringBuffer buff = new StringBuffer("[");
/* 258 */     for (String str : list) {
/* 259 */       buff.append("'").append(str).append("',");
/*     */     }
/* 261 */     if (list.size() > 0) {
/* 262 */       buff.deleteCharAt(buff.length() - 1);
/*     */     }
/* 264 */     buff.append("]");
/* 265 */     setJsonString(buff.toString());
/* 266 */     return "success";
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.action.system.DictionaryAction
 * JD-Core Version:    0.6.0
 */