/*     */ package com.htsoft.oa.service.archive.impl;
/*     */ 
/*     */ import com.google.gson.Gson;
/*     */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*     */ import com.htsoft.core.util.BeanUtil;
/*     */ import com.htsoft.core.util.ContextUtil;
/*     */ import com.htsoft.core.web.paging.PagingBean;
/*     */ import com.htsoft.oa.action.flow.FlowRunInfo;
/*     */ import com.htsoft.oa.dao.archive.ArchivesDao;
/*     */ import com.htsoft.oa.model.archive.ArchDispatch;
/*     */ import com.htsoft.oa.model.archive.Archives;
/*     */ import com.htsoft.oa.model.archive.ArchivesDep;
/*     */ import com.htsoft.oa.model.archive.ArchivesDoc;
/*     */ import com.htsoft.oa.model.archive.DocHistory;
/*     */ import com.htsoft.oa.model.flow.ProcessRun;
/*     */ import com.htsoft.oa.model.system.AppRole;
/*     */ import com.htsoft.oa.model.system.AppUser;
/*     */ import com.htsoft.oa.model.system.Department;
/*     */ import com.htsoft.oa.model.system.FileAttach;
/*     */ import com.htsoft.oa.model.system.GlobalType;
/*     */ import com.htsoft.oa.service.archive.ArchDispatchService;
/*     */ import com.htsoft.oa.service.archive.ArchivesDepService;
/*     */ import com.htsoft.oa.service.archive.ArchivesDocService;
/*     */ import com.htsoft.oa.service.archive.ArchivesService;
/*     */ import com.htsoft.oa.service.archive.DocHistoryService;
/*     */ import com.htsoft.oa.service.system.DepartmentService;
/*     */ import com.htsoft.oa.service.system.FileAttachService;
/*     */ import com.htsoft.oa.service.system.GlobalTypeService;
/*     */ import java.util.Date;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Resource;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.commons.logging.Log;
/*     */ 
/*     */ public class ArchivesServiceImpl extends BaseServiceImpl<Archives>
/*     */   implements ArchivesService
/*     */ {
/*     */   private ArchivesDao dao;
/*     */ 
/*     */   @Resource
/*     */   private GlobalTypeService globalTypeService;
/*     */ 
/*     */   @Resource
/*     */   private FileAttachService fileAttachService;
/*     */ 
/*     */   @Resource
/*     */   private ArchivesDocService archivesDocService;
/*     */ 
/*     */   @Resource
/*     */   private DocHistoryService docHistoryService;
/*     */ 
/*     */   @Resource
/*     */   private DepartmentService departmentService;
/*     */ 
/*     */   @Resource
/*     */   private ArchivesDepService archivesDepService;
/*     */ 
/*     */   @Resource
/*     */   private ArchDispatchService archDispatchService;
/*     */ 
/*     */   public ArchivesServiceImpl(ArchivesDao dao)
/*     */   {
/*  58 */     super(dao);
/*  59 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */   public List<Archives> findByUserOrRole(Long userId, Set<AppRole> roles, PagingBean pb)
/*     */   {
/*  64 */     return this.dao.findByUserOrRole(userId, roles, pb);
/*     */   }
/*     */ 
/*     */   public Integer startArchFlow(FlowRunInfo flowRunInfo)
/*     */   {
/*  70 */     AppUser curUser = ContextUtil.getCurrentUser();
/*  71 */     Archives archives = new Archives();
/*     */     try {
/*  73 */       BeanUtil.populateEntity(flowRunInfo.getRequest(), archives, "archives");
/*     */     } catch (Exception ex) {
/*  75 */       this.logger.error(ex.getMessage());
/*  76 */       return Integer.valueOf(0);
/*     */     }
/*     */ 
/*  79 */     String docs = flowRunInfo.getRequest().getParameter("docs");
/*  80 */     Set archivesDocSet = new HashSet();
/*  81 */     if (StringUtils.isNotEmpty(docs)) {
/*  82 */       Gson gson = new Gson();
/*  83 */       ArchivesDoc[] archivesDocs = (ArchivesDoc[])gson.fromJson(docs, 
/*  84 */         com.htsoft.oa.model.archive.ArchivesDoc[].class);
/*  85 */       if (archivesDocs != null) {
/*  86 */         for (int i = 0; i < archivesDocs.length; i++) {
/*  87 */           if ((archivesDocs[i].getDocId() == null) || 
/*  88 */             (archivesDocs[i].getDocId().longValue() == 0L)) {
/*  89 */             archivesDocs[i].setDocId(null);
/*  90 */             archivesDocs[i].initUsers(curUser);
/*  91 */             archivesDocs[i].setDocStatus(Short.valueOf(ArchivesDoc.STATUS_MODIFY));
/*  92 */             archivesDocs[i].setUpdatetime(new Date());
/*  93 */             archivesDocs[i].setCreatetime(new Date());
/*  94 */             archivesDocs[i].setFileAttach(this.fileAttachService.getByPath(archivesDocs[i].getDocPath()));
/*  95 */             this.archivesDocService.save(archivesDocs[i]);
/*     */ 
/*  98 */             DocHistory newHistory = new DocHistory();
/*  99 */             newHistory.setArchivesDoc(archivesDocs[i]);
/* 100 */             newHistory.setFileAttach(archivesDocs[i].getFileAttach());
/* 101 */             newHistory.setDocName(archivesDocs[i].getDocName());
/* 102 */             newHistory.setPath(archivesDocs[i].getDocPath());
/* 103 */             newHistory.setVersion(Integer.valueOf(ArchivesDoc.ORI_VERSION));
/* 104 */             newHistory.setUpdatetime(new Date());
/* 105 */             newHistory.setMender(curUser.getFullname());
/* 106 */             this.docHistoryService.save(newHistory);
/*     */           } else {
/* 108 */             archivesDocs[i] = 
/* 109 */               ((ArchivesDoc)this.archivesDocService
/* 109 */               .get(archivesDocs[i].getDocId()));
/*     */           }
/* 111 */           archivesDocSet.add(archivesDocs[i]);
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 116 */     archives.setIssuer(curUser.getFullname());
/* 117 */     archives.setIssuerId(curUser.getUserId());
/*     */ 
/* 119 */     GlobalType archivesType = (GlobalType)this.globalTypeService.get(archives
/* 120 */       .getArchivesType().getProTypeId());
/* 121 */     archives.setArchivesType(archivesType);
/*     */ 
/* 123 */     archives.setArchType(Archives.ARCHIVE_TYPE_DISPATCH);
/*     */ 
/* 125 */     archives.setCreatetime(new Date());
/* 126 */     archives.setIssueDate(new Date());
/*     */ 
/* 129 */     archives.setFileCounts(Integer.valueOf(archivesDocSet.size()));
/* 130 */     archives.setArchivesDocs(archivesDocSet);
/*     */ 
/* 133 */     archives.setStatus(flowRunInfo.getDestName());
/*     */ 
/* 135 */     this.dao.save(archives);
/*     */ 
/* 138 */     flowRunInfo.getVariables().put("archivesId", archives.getArchivesId());
/* 139 */     flowRunInfo.setFlowSubject(archives.getSubject());
/* 140 */     return Integer.valueOf(1);
/*     */   }
/*     */ 
/*     */   public Integer setRunId(FlowRunInfo flowRunInfo)
/*     */   {
/* 148 */     Long archivesId = (Long)flowRunInfo.getVariables().get("archivesId");
/* 149 */     if (archivesId == null) {
/* 150 */       return Integer.valueOf(0);
/*     */     }
/* 152 */     Archives archives = (Archives)this.dao.get(archivesId);
/* 153 */     archives.setRunId(flowRunInfo.getProcessRun().getRunId());
/* 154 */     this.dao.save(archives);
/* 155 */     return Integer.valueOf(1);
/*     */   }
/*     */ 
/*     */   public Integer saveStatus(FlowRunInfo flowRunInfo)
/*     */   {
/* 162 */     String archivesId = flowRunInfo.getRequest().getParameter("archivesId");
/* 163 */     String status = flowRunInfo.getDestName();
/* 164 */     if (StringUtils.isEmpty(archivesId)) {
/* 165 */       return Integer.valueOf(0);
/*     */     }
/* 167 */     Archives archives = (Archives)this.dao.get(new Long(archivesId));
/* 168 */     archives.setStatus(status);
/* 169 */     archives.setArchStatus(Archives.END_FLOW_NONE);
/* 170 */     this.dao.save(archives);
/*     */ 
/* 172 */     return Integer.valueOf(1);
/*     */   }
/*     */ 
/*     */   public Integer endFlow(FlowRunInfo flowRunInfo)
/*     */   {
/* 181 */     String archivesId = flowRunInfo.getRequest().getParameter("archivesId");
/* 182 */     String status = flowRunInfo.getDestName();
/* 183 */     if (StringUtils.isEmpty(archivesId)) {
/* 184 */       return Integer.valueOf(0);
/*     */     }
/* 186 */     Archives archives = (Archives)this.dao.get(new Long(archivesId));
/* 187 */     archives.setStatus(status);
/* 188 */     archives.setArchStatus(Archives.END_FLOW);
/* 189 */     this.dao.save(archives);
/*     */ 
/* 192 */     String depIds = archives.getRecDepIds();
/* 193 */     if (StringUtils.isNotEmpty(depIds)) {
/* 194 */       String[] depIdArr = depIds.split("[,]");
/* 195 */       if (depIdArr != null)
/*     */       {
/* 197 */         StringBuffer recIds = new StringBuffer("");
/*     */ 
/* 199 */         for (int i = 0; i < depIdArr.length; i++) {
/* 200 */           Long depId = new Long(depIdArr[i]);
/* 201 */           Department department = (Department)this.departmentService.get(depId);
/*     */ 
/* 203 */           ArchivesDep archivesDep = new ArchivesDep();
/* 204 */           archivesDep.setSubject(archives.getSubject());
/* 205 */           archivesDep.setDepartment(department);
/* 206 */           archivesDep.setArchives(archives);
/* 207 */           archivesDep.setIsMain(ArchivesDep.RECEIVE_MAIN);
/* 208 */           archivesDep.setStatus(ArchivesDep.STATUS_UNSIGNED);
/*     */ 
/* 210 */           this.archivesDepService.save(archivesDep);
/*     */         }
/*     */       }
/*     */     }
/* 214 */     return Integer.valueOf(1);
/*     */   }
/*     */ 
/*     */   public Integer startRecFlow(FlowRunInfo flowRunInfo)
/*     */   {
/* 224 */     Archives archives = new Archives();
/*     */     try {
/* 226 */       BeanUtil.populateEntity(flowRunInfo.getRequest(), archives, "archives");
/*     */     } catch (Exception ex) {
/* 228 */       this.logger.error(ex.getMessage());
/* 229 */       return Integer.valueOf(0);
/*     */     }
/*     */ 
/* 232 */     String arcRecfileIds = flowRunInfo.getRequest().getParameter("archivesRecfileIds");
/* 233 */     String archDepId = flowRunInfo.getRequest().getParameter("archDepId");
/* 234 */     String handlerUids = flowRunInfo.getRequest().getParameter("signUserIds");
/* 235 */     String recTypeId = flowRunInfo.getRequest().getParameter("recTypeId");
/*     */ 
/* 237 */     AppUser appUser = ContextUtil.getCurrentUser();
/* 238 */     archives.setArchType(Archives.ARCHIVE_TYPE_RECEIVE);
/* 239 */     archives.setIssuerId(appUser.getUserId());
/* 240 */     archives.setIssuer(appUser.getFullname());
/* 241 */     archives.setHandlerUids(handlerUids);
/* 242 */     archives.setCreatetime(new Date());
/* 243 */     archives.setIssueDate(new Date());
/* 244 */     archives.setStatus(flowRunInfo.getDestName());
/* 245 */     archives.setArchivesRecType((GlobalType)this.globalTypeService.get(new Long(recTypeId)));
/* 246 */     if (StringUtils.isNotEmpty(arcRecfileIds)) {
/* 247 */       archives.setFileCounts(Integer.valueOf(arcRecfileIds.split(",").length));
/*     */     }
/* 249 */     this.dao.save(archives);
/* 250 */     if (StringUtils.isNotEmpty(arcRecfileIds)) {
/* 251 */       List<ArchivesDoc> list = this.archivesDocService.findByAid(archives
/* 252 */         .getArchivesId());
/* 253 */       for (ArchivesDoc archivesDoc : list) {
/* 254 */         this.archivesDocService.remove(archivesDoc);
/*     */       }
/* 256 */       String[] fileIds = arcRecfileIds.split(",");
/* 257 */       for (String id : fileIds) {
/* 258 */         FileAttach fileAttach = (FileAttach)this.fileAttachService.get(new Long(id));
/* 259 */         ArchivesDoc archivesDoc = new ArchivesDoc();
/* 260 */         archivesDoc.setArchives(archives);
/* 261 */         archivesDoc.setFileAttach(fileAttach);
/* 262 */         archivesDoc.setDocName(fileAttach.getFileName());
/* 263 */         archivesDoc.setDocStatus(Short.valueOf((short) 1));
/* 264 */         archivesDoc.setCurVersion(Integer.valueOf(1));
/* 265 */         archivesDoc.setDocPath(fileAttach.getFilePath());
/* 266 */         archivesDoc.setCreatetime(new Date());
/* 267 */         archivesDoc.setUpdatetime(new Date());
/* 268 */         this.archivesDocService.save(archivesDoc);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 273 */     if (StringUtils.isNotEmpty(archDepId)) {
/* 274 */       ArchivesDep archivesDep = (ArchivesDep)this.archivesDepService.get(new Long(archDepId));
/* 275 */       AppUser curUser = ContextUtil.getCurrentUser();
/* 276 */       archivesDep.setStatus(ArchivesDep.STATUS_SIGNED);
/* 277 */       archivesDep.setSignTime(new Date());
/* 278 */       archivesDep.setSignFullname(curUser.getFullname());
/* 279 */       archivesDep.setSignUserID(curUser.getUserId());
/* 280 */       this.archivesDepService.save(archivesDep);
/*     */     }
/*     */ 
/* 283 */     flowRunInfo.getVariables().put("archivesId", archives.getArchivesId());
/* 284 */     flowRunInfo.getVariables().put("archDepId", archDepId);
/* 285 */     flowRunInfo.setFlowSubject(archives.getSubject());
/* 286 */     return Integer.valueOf(1);
/*     */   }
/*     */ 
/*     */   public Integer endRecFlow(FlowRunInfo flowRunInfo)
/*     */   {
/* 295 */     String archivesId = flowRunInfo.getRequest().getParameter("archivesId");
/* 296 */     String status = flowRunInfo.getDestName();
/* 297 */     if (StringUtils.isEmpty(archivesId)) {
/* 298 */       return Integer.valueOf(0);
/*     */     }
/* 300 */     Archives archives = (Archives)this.dao.get(new Long(archivesId));
/* 301 */     archives.setStatus(status);
/* 302 */     archives.setArchStatus(Archives.END_FLOW);
/* 303 */     this.dao.save(archives);
/*     */ 
/* 306 */     String cruArchDepId = flowRunInfo.getRequest().getParameter("archDepId");
/* 307 */     String readFeedback = flowRunInfo.getRequest().getParameter("comments");
/* 308 */     if ((StringUtils.isNotEmpty(cruArchDepId)) && (cruArchDepId.indexOf("$") == -1)) {
/* 309 */       ArchivesDep archivesDep = (ArchivesDep)this.archivesDepService.get(new Long(cruArchDepId));
/* 310 */       archivesDep.setHandleFeedback(readFeedback);
/* 311 */       this.archivesDepService.save(archivesDep);
/*     */     }
/* 313 */     return Integer.valueOf(1);
/*     */   }
/*     */ 
/*     */   public Integer saveDispatch(FlowRunInfo flowRunInfo)
/*     */   {
/* 321 */     String archivesId = flowRunInfo.getRequest().getParameter("archivesId");
/* 322 */     String archUserType = flowRunInfo.getRequest().getParameter("archUserType");
/* 323 */     String readFeedback = flowRunInfo.getRequest().getParameter("comments");
/* 324 */     Archives archives = (Archives)this.dao.get(new Long(archivesId));
/* 325 */     if (archives != null) {
/* 326 */       ArchDispatch archDispatch = new ArchDispatch();
/* 327 */       AppUser user = ContextUtil.getCurrentUser();
/* 328 */       archDispatch.setArchives(archives);
/* 329 */       archDispatch.setArchUserType(new Short(archUserType));
/* 330 */       archDispatch.setUserId(user.getUserId());
/* 331 */       archDispatch.setFullname(user.getFullname());
/* 332 */       archDispatch.setDispatchTime(new Date());
/* 333 */       archDispatch.setSubject(archives.getSubject());
/* 334 */       archDispatch.setIsRead(ArchDispatch.HAVE_READ);
/* 335 */       archDispatch.setReadFeedback(readFeedback);
/* 336 */       this.archDispatchService.save(archDispatch);
/*     */     }
/* 338 */     return Integer.valueOf(1);
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.archive.impl.ArchivesServiceImpl
 * JD-Core Version:    0.6.0
 */