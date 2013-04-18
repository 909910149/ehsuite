/*     */ package com.htsoft.oa.dao.document.impl;
/*     */ 
/*     */ import com.htsoft.core.dao.impl.BaseDaoImpl;
/*     */ import com.htsoft.core.web.paging.PagingBean;
/*     */ import com.htsoft.oa.dao.document.DocumentDao;
/*     */ import com.htsoft.oa.model.document.Document;
/*     */ import com.htsoft.oa.model.system.AppRole;
/*     */ import com.htsoft.oa.model.system.AppUser;
/*     */ import com.htsoft.oa.model.system.Department;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.commons.logging.Log;
/*     */ 
/*     */ public class DocumentDaoImpl extends BaseDaoImpl<Document>
/*     */   implements DocumentDao
/*     */ {
/*     */   public DocumentDaoImpl()
/*     */   {
/*  24 */     super(Document.class);
/*     */   }
/*     */ 
/*     */   public List<Document> findByIsShared(Document document, Date from, Date to, Long userId, ArrayList<Long> roleIds, Long depId, PagingBean pb)
/*     */   {
/*  30 */     ArrayList list = new ArrayList();
/*  31 */     StringBuffer buff = new StringBuffer();
/*  32 */     if (roleIds.contains(AppRole.SUPER_ROLEID)) {
/*  33 */       buff.append("select distinct vo.docId from Document vo where vo.isShared=1");
/*     */     } else {
/*  35 */       buff.append("select distinct vo.docId from Document vo where vo.isShared=1 and ( 0=1 ");
/*  36 */       if (depId != null) {
/*  37 */         buff.append(" or vo.sharedDepIds like ? ");
/*  38 */         list.add("%," + depId + ",%");
/*     */       }
/*     */ 
/*  41 */       for (Long roleId : roleIds) {
/*  42 */         buff.append(" or vo.sharedRoleIds like ?");
/*  43 */         list.add("%," + roleId + ",%");
/*     */       }
/*     */ 
/*  46 */       if (userId != null) {
/*  47 */         buff.append(" or vo.sharedUserIds like ?");
/*  48 */         list.add("%," + userId + ",%");
/*     */       }
/*  50 */       buff.append(")");
/*     */     }
/*  52 */     if (document != null) {
/*  53 */       if (StringUtils.isNotEmpty(document.getDocName())) {
/*  54 */         buff.append(" and vo.docName like ?");
/*  55 */         list.add("%" + document.getDocName() + "%");
/*     */       }
/*  57 */       if (StringUtils.isNotEmpty(document.getFullname())) {
/*  58 */         buff.append(" and vo.fullname=?");
/*  59 */         list.add("," + document.getFullname() + ",");
/*     */       }
/*     */     }
/*  62 */     if (to != null) {
/*  63 */       buff.append(" and vo.createtime <= ?");
/*  64 */       list.add(to);
/*     */     }
/*  66 */     if (from != null) {
/*  67 */       buff.append(" and vo.createtime >= ?");
/*  68 */       list.add(from);
/*     */     }
/*  70 */     buff.append(" order by vo desc");
/*  71 */     List docIds = findByHql(buff.toString(), list.toArray(), pb);
/*  72 */     return getByIds(docIds);
/*     */   }
/*     */ 
/*     */   private List<Document> getByIds(List docIds)
/*     */   {
/*  82 */     String docHql = "from Document doc where doc.docId in (";
/*  83 */     StringBuffer sbIds = new StringBuffer();
/*     */ 
/*  85 */     for (int i = 0; i < docIds.size(); i++) {
/*  86 */       sbIds.append(docIds.get(i).toString()).append(",");
/*     */     }
/*     */ 
/*  89 */     if (docIds.size() > 0) {
/*  90 */       sbIds.deleteCharAt(sbIds.length() - 1);
/*  91 */       docHql = docHql + sbIds.toString() + ")";
/*  92 */       return findByHql(docHql);
/*     */     }
/*  94 */     return new ArrayList();
/*     */   }
/*     */ 
/*     */   public List<Document> findByPublic(String path, Document document, Date from, Date to, AppUser appUser, PagingBean pb)
/*     */   {
/* 104 */     StringBuffer sb = new StringBuffer();
/* 105 */     List list = new ArrayList();
/* 106 */     if (!appUser.getRights().contains("__ALL")) {
/* 107 */       sb.append("select distinct doc.docId from Document doc,DocFolder docF,DocPrivilege pr where doc.docFolder=docF and pr.docFolder=docF and docF.isShared=1 ");
/* 108 */       Set roles = appUser.getRoles();
/* 109 */       StringBuffer buff = new StringBuffer();
/* 110 */       if (roles != null) {
/* 111 */         Iterator it = roles.iterator();
/* 112 */         while (it.hasNext()) {
/* 113 */           Long roleId = ((AppRole)it.next()).getRoleId();
/* 114 */           buff.append(roleId.toString() + ',');
/*     */         }
/* 116 */         if (roles.size() > 0) {
/* 117 */           buff.deleteCharAt(buff.length() - 1);
/*     */         }
/*     */       }
/* 120 */       sb.append(" and pr.rights>0 and ((pr.udrId=? and pr.flag=1)");
/* 121 */       Integer userId = Integer.valueOf(Integer.parseInt(appUser.getUserId().toString()));
/* 122 */       list.add(userId);
/* 123 */       if (appUser.getDepartment() != null) {
/* 124 */         Integer depId = Integer.valueOf(Integer.parseInt(appUser.getDepartment().getDepId().toString()));
/* 125 */         sb.append(" or (pr.udrId=? and pr.flag=2)");
/* 126 */         list.add(depId);
/*     */       }
/* 128 */       if ((buff.toString() != null) && (buff.length() > 0)) {
/* 129 */         sb.append(" or (pr.udrId in (" + buff.toString() + ") and pr.flag=3)");
/*     */       }
/* 131 */       sb.append(")");
/*     */     } else {
/* 133 */       sb.append("select distinct doc.docId from Document doc,DocFolder docF where doc.docFolder=docF and docF.isShared=1");
/*     */     }
/* 135 */     if (path != null) {
/* 136 */       sb.append(" and docF.path like ?");
/* 137 */       list.add(path + "%");
/*     */     }
/* 139 */     if (document != null) {
/* 140 */       if (document.getDocName() != null) {
/* 141 */         sb.append(" and doc.docName like ?");
/* 142 */         list.add("%" + document.getDocName() + "%");
/*     */       }
/* 144 */       if (document.getAuthor() != null) {
/* 145 */         sb.append(" and doc.author like ?");
/* 146 */         list.add("%" + document.getAuthor() + "%");
/*     */       }
/* 148 */       if (document.getKeywords() != null) {
/* 149 */         sb.append(" and doc.keywords like ?");
/* 150 */         list.add("%" + document.getKeywords() + "%");
/*     */       }
/*     */     }
/* 153 */     if (to != null) {
/* 154 */       sb.append(" and doc.createtime <= ?");
/* 155 */       list.add(to);
/*     */     }
/* 157 */     if (from != null) {
/* 158 */       sb.append(" and doc.createtime >= ?");
/* 159 */       list.add(from);
/*     */     }
/* 161 */     sb.append(" order by doc desc");
/* 162 */     List docIds = findByHql(sb.toString(), list.toArray(), pb);
/* 163 */     List docList = getByIds(docIds);
/* 164 */     return docList;
/*     */   }
/*     */ 
/*     */   public List<Document> findByPersonal(Long userId, Document document, Date from, Date to, String path, PagingBean pb)
/*     */   {
/* 169 */     StringBuffer sb = new StringBuffer();
/* 170 */     ArrayList list = new ArrayList();
/* 171 */     sb.append("select distinct doc.docId from Document doc,DocFolder docFolder where doc.docFolder=docFolder and docFolder.appUser.userId is not Null");
/* 172 */     if (path != null) {
/* 173 */       sb.append(" and docFolder.path like ?");
/* 174 */       list.add(path + "%");
/*     */     }
/* 176 */     if (userId != null) {
/* 177 */       sb.append(" and doc.appUser.userId=?");
/* 178 */       list.add(userId);
/*     */     }
/* 180 */     if ((document != null) && 
/* 181 */       (document.getDocName() != null)) {
/* 182 */       sb.append(" and doc.docName like ?");
/* 183 */       list.add("%" + document.getDocName() + "%");
/*     */     }
/*     */ 
/* 186 */     if (to != null) {
/* 187 */       sb.append(" and vo.createtime <= ?");
/* 188 */       list.add(to);
/*     */     }
/* 190 */     if (from != null) {
/* 191 */       sb.append(" and vo.createtime >= ?");
/* 192 */       list.add(from);
/*     */     }
/* 194 */     List docIds = findByHql(sb.toString(), list.toArray(), pb);
/* 195 */     return getByIds(docIds);
/*     */   }
/*     */ 
/*     */   public List<Document> findByFolder(String path)
/*     */   {
/* 200 */     String hql = "select doc from Document doc where doc.docFolder.path like ?";
/* 201 */     List list = new ArrayList();
/* 202 */     list.add(path + "%");
/* 203 */     return findByHql(hql, list.toArray());
/*     */   }
/*     */ 
/*     */   public List<Document> searchDocument(AppUser appUser, String content, boolean isHaveData, PagingBean pb)
/*     */   {
/* 209 */     StringBuffer buff = new StringBuffer(
/* 210 */       "select distinct doc.docId from Document doc,DocFolder docF ");
/* 211 */     if (isHaveData) {
/* 212 */       buff.append(" ,DocPrivilege pr");
/*     */     }
/* 214 */     buff.append(" where ");
/* 215 */     Set roles = appUser.getRoles();
/* 216 */     List list = new ArrayList();
/* 217 */     StringBuffer sb = new StringBuffer();
/* 218 */     Iterator it = roles.iterator();
/* 219 */     if (roles.size() > 0) {
/* 220 */       while (it.hasNext()) {
/* 221 */         Long roleId = ((AppRole)it.next()).getRoleId();
/* 222 */         sb.append(roleId.toString() + ',');
/*     */       }
/* 224 */       if (roles.size() > 0) {
/* 225 */         sb.deleteCharAt(sb.length() - 1);
/*     */       }
/*     */     }
/* 228 */     buff.append(" ((doc.isShared=1 ");
/* 229 */     if (!appUser.getRights().contains("__ALL")) {
/* 230 */       buff.append(" and (0=1");
/* 231 */       if (appUser.getDepartment() != null) {
/* 232 */         buff.append(" or doc.sharedDepIds like ? ");
/* 233 */         list.add("%," + appUser.getDepartment().getDepId() + ",%");
/*     */       }
/* 235 */       while (it.hasNext()) {
/* 236 */         Long roleId = ((AppRole)it.next()).getRoleId();
/* 237 */         buff.append(" or doc.sharedRoleIds like ?");
/* 238 */         list.add("%," + roleId.toString() + ",%");
/*     */       }
/* 240 */       if (appUser.getUserId() != null) {
/* 241 */         buff.append(" or doc.sharedUserIds like ?");
/* 242 */         list.add("%," + appUser.getUserId() + ",%");
/*     */       }
/* 244 */       buff.append(")");
/*     */     }
/* 246 */     buff.append(") or (doc.isShared=0 and doc.docFolder=docF and docF.appUser.userId is not Null and doc.appUser.userId=? )");
/* 247 */     list.add(appUser.getUserId());
/* 248 */     buff.append(" or (doc.docFolder=docF and docF.isShared=1");
/* 249 */     if ((isHaveData) && 
/* 250 */       (!appUser.getRights().contains("__ALL"))) {
/* 251 */       buff.append(" and pr.docFolder=docF");
/* 252 */       buff.append(" and pr.rights>0 and ((pr.udrId=? and pr.flag=1)");
/* 253 */       Integer userId = Integer.valueOf(Integer.parseInt(appUser.getUserId()
/* 254 */         .toString()));
/* 255 */       list.add(userId);
/* 256 */       if (appUser.getDepartment() != null) {
/* 257 */         Integer depId = Integer.valueOf(Integer.parseInt(appUser.getDepartment()
/* 258 */           .getDepId().toString()));
/* 259 */         buff.append(" or (pr.udrId=? and pr.flag=2)");
/* 260 */         list.add(depId);
/*     */       }
/* 262 */       if ((sb.toString() != null) && (sb.length() > 1)) {
/* 263 */         buff.append(" or (pr.udrId in (" + sb.toString() + 
/* 264 */           ") and pr.flag=3)");
/*     */       }
/* 266 */       buff.append(")");
/*     */     }
/*     */ 
/* 269 */     buff.append(")");
/*     */ 
/* 271 */     buff.append(")");
/* 272 */     if (StringUtils.isNotEmpty(content)) {
/* 273 */       buff.append(" and (doc.content like ? or doc.docName like ?)");
/* 274 */       list.add("%" + content + "%");
/* 275 */       list.add("%" + content + "%");
/*     */     }
/* 277 */     buff.append("  order by doc desc");
/* 278 */     this.logger.info("HQL:" + buff.toString());
/* 279 */     List docIds = findByHql(buff.toString(), list.toArray(), pb);
/* 280 */     return getByIds(docIds);
/*     */   }
/*     */ 
/*     */   public List<Document> findByFolder(Long folderId)
/*     */   {
/* 285 */     return findByHql("from Document doc where doc.docFolder.folderId=?", new Object[] { folderId });
/*     */   }
/*     */ 
/*     */   public List<Document> findByPersonal(Long userId, Document document, Date from, Date to, String path)
/*     */   {
/* 291 */     PagingBean pb = new PagingBean(0, 10000);
/* 292 */     StringBuffer sb = new StringBuffer();
/* 293 */     ArrayList list = new ArrayList();
/* 294 */     sb.append("select distinct doc.docId from Document doc join doc.docFolder df where df.appUser.userId is not Null");
/* 295 */     if (path != null) {
/* 296 */       sb.append(" and df.path like ?");
/* 297 */       list.add(path + "%");
/*     */     }
/* 299 */     if (userId != null) {
/* 300 */       sb.append(" and doc.appUser.userId=?");
/* 301 */       list.add(userId);
/*     */     }
/* 303 */     if ((document != null) && 
/* 304 */       (document.getDocName() != null)) {
/* 305 */       sb.append(" and doc.docName like ?");
/* 306 */       list.add("%" + document.getDocName() + "%");
/*     */     }
/*     */ 
/* 309 */     if (to != null) {
/* 310 */       sb.append(" and vo.createtime <= ?");
/* 311 */       list.add(to);
/*     */     }
/* 313 */     if (from != null) {
/* 314 */       sb.append(" and vo.createtime >= ?");
/* 315 */       list.add(from);
/*     */     }
/* 317 */     sb.append(" order by doc desc");
/* 318 */     List docIds = findByHql(sb.toString(), list.toArray(), pb);
/* 319 */     return getByIds(docIds);
/*     */   }
/*     */ 
/*     */   public List<Document> findByOnline(Document document, Date from, Date to, AppUser appUser)
/*     */   {
/* 324 */     PagingBean pb = new PagingBean(0, 10000);
/* 325 */     StringBuffer sb = new StringBuffer();
/* 326 */     List list = new ArrayList();
/* 327 */     if (!appUser.getRights().contains("__ALL")) {
/* 328 */       sb.append("select distinct doc.docId from Document doc,DocFolder docF,DocPrivilege pr where doc.docFolder=docF and pr.docFolder=docF and docF.isShared=2 ");
/* 329 */       Set roles = appUser.getRoles();
/* 330 */       StringBuffer buff = new StringBuffer();
/* 331 */       if (roles != null) {
/* 332 */         Iterator it = roles.iterator();
/* 333 */         while (it.hasNext()) {
/* 334 */           Long roleId = ((AppRole)it.next()).getRoleId();
/* 335 */           buff.append(roleId.toString() + ',');
/*     */         }
/* 337 */         if (roles.size() > 0) {
/* 338 */           buff.deleteCharAt(buff.length() - 1);
/*     */         }
/*     */       }
/* 341 */       sb.append(" and pr.rights>0 and ((pr.udrId=? and pr.flag=1)");
/* 342 */       Integer userId = Integer.valueOf(Integer.parseInt(appUser.getUserId().toString()));
/* 343 */       list.add(userId);
/* 344 */       if (appUser.getDepartment() != null) {
/* 345 */         Integer depId = Integer.valueOf(Integer.parseInt(appUser.getDepartment().getDepId().toString()));
/* 346 */         sb.append(" or (pr.udrId=? and pr.flag=2)");
/* 347 */         list.add(depId);
/*     */       }
/* 349 */       if ((buff.toString() != null) && (buff.length() > 0)) {
/* 350 */         sb.append(" or (pr.udrId in (" + buff.toString() + ") and pr.flag=3)");
/*     */       }
/* 352 */       sb.append(")");
/*     */     } else {
/* 354 */       sb.append("select distinct doc.docId from Document doc,DocFolder docF where doc.docFolder=docF and docF.isShared=2");
/*     */     }
/* 356 */     if ((document != null) && 
/* 357 */       (document.getDocName() != null)) {
/* 358 */       sb.append(" and doc.docName like ?");
/* 359 */       list.add("%" + document.getDocName() + "%");
/*     */     }
/*     */ 
/* 362 */     if (to != null) {
/* 363 */       sb.append(" and doc.createtime <= ?");
/* 364 */       list.add(to);
/*     */     }
/* 366 */     if (from != null) {
/* 367 */       sb.append(" and doc.createtime >= ?");
/* 368 */       list.add(from);
/*     */     }
/* 370 */     sb.append(" order by doc desc");
/* 371 */     List docIds = findByHql(sb.toString(), list.toArray(), pb);
/* 372 */     List docList = getByIds(docIds);
/* 373 */     return docList;
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.document.impl.DocumentDaoImpl
 * JD-Core Version:    0.6.0
 */