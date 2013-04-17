/*     */ package com.htsoft.oa.action.system;
/*     */ 
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.reflect.TypeToken;
/*     */ import com.htsoft.core.command.QueryFilter;
/*     */ import com.htsoft.core.util.AppUtil;
/*     */ import com.htsoft.core.util.BeanUtil;
/*     */ import com.htsoft.core.web.action.BaseAction;
/*     */ import com.htsoft.core.web.paging.PagingBean;
/*     */ import com.htsoft.oa.model.system.ReportParam;
/*     */ import com.htsoft.oa.model.system.ReportTemplate;
/*     */ import com.htsoft.oa.service.system.ReportParamService;
/*     */ import com.htsoft.oa.service.system.ReportTemplateService;
/*     */ import flexjson.JSONSerializer;
/*     */ import java.io.File;
/*     */ import java.io.PrintStream;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Type;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Resource;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class ReportTemplateAction extends BaseAction
/*     */ {
/*  59 */   private String uploadPath = AppUtil.getAppAbsolutePath() + "/attachFiles/";
/*     */ 
/*     */   @Resource
/*     */   private ReportTemplateService reportTemplateService;
/*     */   private ReportTemplate reportTemplate;
/*     */ 
/*     */   @Resource
/*     */   private ReportParamService reportParamService;
/*     */   private Long reportId;
/*     */ 
/*  69 */   public Long getReportId() { return this.reportId; }
/*     */ 
/*     */   public void setReportId(Long reportId)
/*     */   {
/*  73 */     this.reportId = reportId;
/*     */   }
/*     */ 
/*     */   public ReportTemplate getReportTemplate() {
/*  77 */     return this.reportTemplate;
/*     */   }
/*     */ 
/*     */   public void setReportTemplate(ReportTemplate reportTemplate) {
/*  81 */     this.reportTemplate = reportTemplate;
/*     */   }
/*     */ 
/*     */   public String list()
/*     */   {
/*  89 */     QueryFilter filter = new QueryFilter(getRequest());
/*  90 */     List list = this.reportTemplateService.getAll(filter);
/*     */ 
/*  92 */     Type type = new TypeToken() {
/*  93 */     }.getType();
/*  94 */     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
/*  95 */       .append(filter.getPagingBean().getTotalItems()).append(
/*  96 */       ",result:");
/*     */ 
/*  98 */     Gson gson = new Gson();
/*  99 */     buff.append(gson.toJson(list, type));
/* 100 */     buff.append("}");
/*     */ 
/* 102 */     this.jsonString = buff.toString();
/*     */ 
/* 104 */     return "success";
/*     */   }
/*     */   public String checkKey() {
/* 107 */     String Q_reportKey_S_EQ = getRequest().getParameter("Q_reportKey_S_EQ");
/*     */ 
/* 109 */     QueryFilter filter = new QueryFilter(getRequest());
/* 110 */     List list = this.reportTemplateService.getAll(filter);
/*     */ 
/* 112 */     Type type = new TypeToken() {
/* 113 */     }.getType();
/* 114 */     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
/* 115 */       .append(filter.getPagingBean().getTotalItems()).append("}");
/*     */ 
/* 117 */     this.jsonString = buff.toString();
/*     */ 
/* 121 */     return "success";
/*     */   }
/*     */ 
/*     */   public String multiDel()
/*     */   {
/* 133 */     String[] ids = getRequest().getParameterValues("ids");
/* 134 */     if (ids != null) {
/* 135 */       for (String id : ids) {
/* 136 */         this.reportTemplate = ((ReportTemplate)this.reportTemplateService.get(new Long(id)));
/* 137 */         List<ReportParam> list = this.reportParamService
/* 138 */           .findByRepTemp(new Long(id));
/* 139 */         for (ReportParam rp : list) {
/* 140 */           this.reportParamService.remove(rp);
/*     */         }
/*     */ 
/* 143 */         File file = new File(this.uploadPath + 
/* 144 */           this.reportTemplate.getReportLocation());
/* 145 */         File parent = file.getParentFile();
/* 146 */         deleteFile(parent);
/*     */ 
/* 148 */         this.reportTemplateService.remove(new Long(id));
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 153 */     this.jsonString = "{success:true}";
/*     */ 
/* 155 */     return "success";
/*     */   }
/*     */ 
/*     */   public String get()
/*     */   {
/* 164 */     ReportTemplate reportTemplate = (ReportTemplate)this.reportTemplateService.get(this.reportId);
/*     */ 
/* 166 */     Gson gson = new Gson();
/*     */ 
/* 168 */     StringBuffer sb = new StringBuffer("{success:true,data:");
/* 169 */     sb.append(gson.toJson(reportTemplate));
/* 170 */     sb.append("}");
/* 171 */     setJsonString(sb.toString());
/*     */ 
/* 173 */     return "success";
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/* 181 */     if (this.reportTemplate.getReportId() == null) {
/* 182 */       this.reportTemplate.setCreatetime(new Date());
/* 183 */       this.reportTemplate.setUpdatetime(new Date());
/* 184 */       this.reportTemplateService.save(this.reportTemplate);
/*     */     }
/*     */     else {
/* 187 */       ReportTemplate old = (ReportTemplate)this.reportTemplateService.get(this.reportTemplate.getReportId());
/*     */ 
/* 189 */       if (!old.getReportLocation().toString().trim().equals(this.reportTemplate.getReportLocation().toString().trim())) {
/* 190 */         File file = new File(this.uploadPath + 
/* 191 */           old.getReportLocation());
/*     */ 
/* 193 */         deleteFile(file.getParentFile());
/*     */       }
/*     */ 
/*     */       try
/*     */       {
/* 198 */         BeanUtil.copyNotNullProperties(old, this.reportTemplate);
/*     */       }
/*     */       catch (IllegalAccessException e) {
/* 201 */         e.printStackTrace();
/*     */       }
/*     */       catch (InvocationTargetException e) {
/* 204 */         e.printStackTrace();
/*     */       }
/*     */ 
/* 207 */       old.setUpdatetime(new Date());
/*     */ 
/* 209 */       this.reportTemplateService.save(old);
/*     */     }
/*     */ 
/* 212 */     setJsonString("{success:true}");
/* 213 */     return "success";
/*     */   }
/*     */ 
/*     */   private void deleteFile(File file)
/*     */   {
/* 222 */     if (file.exists()) {
/* 223 */       if (file.isFile()) {
/* 224 */         file.delete();
/* 225 */       } else if (file.isDirectory()) {
/* 226 */         File[] files = file.listFiles();
/* 227 */         for (int i = 0; i < files.length; i++) {
/* 228 */           deleteFile(files[i]);
/*     */         }
/*     */       }
/* 231 */       file.delete();
/*     */     } else {
/* 233 */       System.out.println("所删除的文件不存在！\n");
/*     */     }
/*     */   }
/*     */ 
/*     */   public String load()
/*     */   {
/* 241 */     String strReportId = getRequest().getParameter("reportId");
/* 242 */     if (StringUtils.isNotEmpty(strReportId)) {
/* 243 */       List list = this.reportParamService.findByRepTemp(
/* 244 */         new Long(strReportId));
/* 245 */       JSONSerializer serializer = new JSONSerializer();
/*     */ 
/* 247 */       Gson gson = new Gson();
/* 248 */       StringBuffer sb = new StringBuffer();
/* 249 */       sb.append(gson.toJson(list));
/*     */ 
/* 251 */       setJsonString("{success:true,data:" + sb.toString() + "}");
/*     */     } else {
/* 253 */       setJsonString("{success:false}");
/*     */     }
/* 255 */     return "success";
/*     */   }
/*     */ 
/*     */   public String submit()
/*     */   {
/* 276 */     SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-mm-dd");
/*     */ 
/* 278 */     Map map = getRequest().getParameterMap();
/* 279 */     Iterator it = map.entrySet().iterator();
/* 280 */     StringBuffer sb = new StringBuffer();
/* 281 */     while (it.hasNext()) {
/* 282 */       Map.Entry entry = (Map.Entry)it.next();
/* 283 */       String key = (String)entry.getKey();
/* 284 */       String[] value = (String[])entry.getValue();
/* 285 */       String v = value[0];
/*     */ 
/* 288 */       if ((v == null) || (v.equals("")))
/* 289 */         v = "%";
/*     */       else {
/*     */         try {
/* 292 */           dateformat.parse(v.trim());
/*     */         } catch (ParseException e) {
/* 294 */           v = "%" + v.trim() + "%";
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 299 */       sb.append("&" + key + "=" + v);
/*     */     }
/* 301 */     setJsonString("{success:true,data:'" + sb.toString() + "'}");
/* 302 */     return "success";
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.action.system.ReportTemplateAction
 * JD-Core Version:    0.6.0
 */