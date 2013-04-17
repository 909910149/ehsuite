/*     */ package com.htsoft.oa.dao.admin.impl;
/*     */ 
/*     */ import com.htsoft.core.dao.impl.BaseDaoImpl;
/*     */ import com.htsoft.core.util.ContextUtil;
/*     */ import com.htsoft.core.web.paging.PagingBean;
/*     */ import com.htsoft.oa.dao.admin.ConfPrivilegeDao;
/*     */ import com.htsoft.oa.dao.admin.ConferenceDao;
/*     */ import com.htsoft.oa.dao.system.AppUserDao;
/*     */ import com.htsoft.oa.dao.system.FileAttachDao;
/*     */ import com.htsoft.oa.model.admin.ConfPrivilege;
/*     */ import com.htsoft.oa.model.admin.Conference;
/*     */ import com.htsoft.oa.model.info.ShortMessage;
/*     */ import com.htsoft.oa.model.system.AppUser;
/*     */ import com.htsoft.oa.model.system.FileAttach;
/*     */ import com.htsoft.oa.service.info.ShortMessageService;
/*     */ import java.io.PrintStream;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Resource;
/*     */ import org.apache.commons.logging.Log;
/*     */ 
/*     */ public class ConferenceDaoImpl extends BaseDaoImpl<Conference>
/*     */   implements ConferenceDao
/*     */ {
/*  41 */   private static SimpleDateFormat sdfc = new SimpleDateFormat(
/*  42 */     "yyyy年MM月dd日 HH时mm分");
/*     */ 
/*     */   @Resource
/*     */   private FileAttachDao fileAttachDao;
/*     */ 
/*     */   @Resource
/*     */   private AppUserDao appUserDao;
/*     */ 
/*     */   @Resource
/*     */   private ShortMessageService shortMessageService;
/*     */ 
/*     */   @Resource
/*     */   private ConfPrivilegeDao confPrivilegeDao;
/*     */ 
/*  55 */   public ConferenceDaoImpl() { super(Conference.class);
/*     */   }
/*     */ 
/*     */   public List<Conference> getConfTopic(String confTopic, PagingBean pb)
/*     */   {
/*  63 */     Long userId = ContextUtil.getCurrentUserId();
/*  64 */     ArrayList paramList = new ArrayList();
/*  65 */     StringBuffer bf = new StringBuffer(
/*  66 */       "select c from Conference c,ConfPrivilege p where c.endTime < ? and c.confId=p.confId ");
/*  67 */     bf.append("and p.rights=3 and p.userId=" + userId);
/*  68 */     paramList.add(new Date());
/*  69 */     if ((confTopic != null) && (!confTopic.isEmpty())) {
/*  70 */       bf.append(" and c.confTopic like ? ");
/*  71 */       paramList.add("%" + confTopic + "%");
/*     */     }
/*  73 */     this.logger.debug("可创建会议纪要的HQL：" + bf.toString());
/*  74 */     return findByHql(bf.toString(), paramList.toArray(), pb);
/*     */   }
/*     */ 
/*     */   public String baseUserIdSearchFullName(String userIds)
/*     */   {
/*  81 */     String fullNames = "";
/*  82 */     for (String userId : userIds.split(",")) {
/*  83 */       fullNames = fullNames + ((AppUser)this.appUserDao.get(new Long(userId))).getFullname() + ",";
/*     */     }
/*  85 */     return fullNames.substring(0, fullNames.length() - 1);
/*     */   }
/*     */ 
/*     */   public Conference send(Conference conference, String view, String updater, String summary, String fileIds)
/*     */   {
/*  94 */     String sMsg = "请审核主题为【" + conference.getConfTopic() + "】的会议信息！";
/*  95 */     this.shortMessageService.save(AppUser.SYSTEM_USER, conference
/*  96 */       .getCheckUserId().toString(), sMsg, ShortMessage.MSG_TYPE_SYS);
/*  97 */     return temp(conference, view, updater, summary, fileIds);
/*     */   }
/*     */ 
/*     */   public Conference temp(Conference conference, String view, String updater, String summary, String fileIds)
/*     */   {
/* 106 */     if ((fileIds != null) && (!fileIds.isEmpty())) {
/* 107 */       Set set = new HashSet();
/* 108 */       for (String f : fileIds.split(",")) {
/* 109 */         FileAttach fa = (FileAttach)this.fileAttachDao.get(new Long(f));
/* 110 */         set.add(fa);
/*     */       }
/* 112 */       conference.setAttachFiles(set);
/*     */     }
/* 114 */     Conference cf = (Conference)super.save(conference);
/*     */ 
/* 116 */     Set sett = new HashSet();
/*     */ 
/* 118 */     setConfPrivilege(cf.getConfId(), view, 1, sett);
/*     */ 
/* 120 */     setConfPrivilege(cf.getConfId(), updater, 2, sett);
/*     */ 
/* 122 */     setConfPrivilege(cf.getConfId(), summary, 3, sett);
/*     */ 
/* 124 */     this.confPrivilegeDao.delete(cf.getConfId());
/*     */ 
/* 126 */     cf.setConfPrivilege(sett);
/* 127 */     return (Conference)super.save(cf);
/*     */   }
/*     */ 
/*     */   public String judgeBoardRoomNotUse(Date startTime, Date endTime, Long roomId)
/*     */   {
/* 135 */     ArrayList paramList = new ArrayList();
/* 136 */     String msg = "success";
/* 137 */     String hql = "select t from Conference t where t.roomId = ? and t.status=1 and ";
/* 138 */     hql = hql + "((t.startTime < ? and t.endTime > ?) ";
/* 139 */     hql = hql + "or (t.startTime < ? and t.endTime > ?) ";
/* 140 */     hql = hql + "or (t.startTime > ? and t.endTime <?))";
/* 141 */     paramList.add(roomId);
/* 142 */     paramList.add(startTime);
/* 143 */     paramList.add(startTime);
/* 144 */     paramList.add(endTime);
/* 145 */     paramList.add(endTime);
/* 146 */     paramList.add(startTime);
/* 147 */     paramList.add(endTime);
/* 148 */     List list = findByHql(hql, paramList.toArray());
/* 149 */     if ((list != null) && (list.size() > 0)) {
/* 150 */       Conference conference = (Conference)list.get(0);
/* 151 */       msg = "会议室【" + conference.getRoomName() + "】，在【" + 
/* 152 */         sdfc.format(conference.getStartTime()) + " 至 " + 
/* 153 */         sdfc.format(conference.getEndTime()) + 
/* 154 */         "】这段时间不可使用，请选择其他时间段或者会议室！";
/*     */     } else {
/* 156 */       msg = "success";
/*     */     }
/* 158 */     this.logger.debug("Conference中判断会议室是否可用：" + hql);
/* 159 */     return msg;
/*     */   }
/*     */ 
/*     */   public String apply(Long confId, String checkReason, boolean bo)
/*     */   {
/* 167 */     int status = bo ? 1 : 3;
/* 168 */     Conference conference = (Conference)get(confId);
/* 169 */     if (status == 1)
/*     */     {
/* 171 */       String cMsg = "请于【" + sdfc.format(conference.getStartTime()) + "-" + 
/* 172 */         sdfc.format(conference.getEndTime()) + "】到【" + 
/* 173 */         conference.getRoomName() + "】去主持主题为【" + 
/* 174 */         conference.getConfTopic() + "】的会议！\n\t地址：" + 
/* 175 */         conference.getRoomLocation();
/* 176 */       this.shortMessageService.save(AppUser.SYSTEM_USER, 
/* 177 */         conference.getCompere(), cMsg, ShortMessage.MSG_TYPE_SYS);
/*     */ 
/* 179 */       String rMsg = "请于【" + sdfc.format(conference.getStartTime()) + "-" + 
/* 180 */         sdfc.format(conference.getEndTime()) + "】到【" + 
/* 181 */         conference.getRoomName() + "】去记录主题为【" + 
/* 182 */         conference.getConfTopic() + "】的会议内容！\n\t地址：" + 
/* 183 */         conference.getRoomLocation();
/* 184 */       this.shortMessageService.save(AppUser.SYSTEM_USER, 
/* 185 */         conference.getRecorder(), rMsg, ShortMessage.MSG_TYPE_SYS);
/*     */ 
/* 187 */       String aMsg = "请于【" + sdfc.format(conference.getStartTime()) + "-" + 
/* 188 */         sdfc.format(conference.getEndTime()) + "】到【" + 
/* 189 */         conference.getRoomName() + "】去参加主题为【" + 
/* 190 */         conference.getConfTopic() + "】的会议！\n\t地址：" + 
/* 191 */         conference.getRoomLocation();
/* 192 */       this.shortMessageService.save(AppUser.SYSTEM_USER, 
/* 193 */         conference.getAttendUsers(), aMsg, 
/* 194 */         ShortMessage.MSG_TYPE_SYS);
/*     */ 
/* 197 */       if (conference.getIsEmail() != null) conference.getIsEmail().shortValue();
/*     */ 
/* 207 */       if (conference.getIsMobile() != null) {
/* 208 */         conference.getIsMobile().shortValue();
/*     */       }
/*     */     }
/*     */ 
/* 212 */     conference.setStatus(Short.valueOf((short)status));
/* 213 */     conference.setCheckReason(checkReason);
/* 214 */     return "success";
/*     */   }
/*     */ 
/*     */   public List<Conference> myJoin(Conference conference, Boolean bo, PagingBean pb)
/*     */   {
/* 222 */     ArrayList paramList = new ArrayList();
/* 223 */     StringBuffer bf = new StringBuffer("select c from Conference c where 1=1");
/* 224 */     bf.append(" and (c.compereName like ? or c.recorderName like ? or c.attendUsersName like ?)");
/* 225 */     String fullName = ContextUtil.getCurrentUser().getFullname();
/* 226 */     System.out.println(fullName);
/* 227 */     paramList.add("%" + fullName + "%");
/* 228 */     paramList.add("%" + fullName + "%");
/* 229 */     paramList.add("%" + fullName + "%");
/*     */ 
/* 231 */     if (bo != null) {
/* 232 */       if (!bo.booleanValue()) {
/* 233 */         bf.append(" and c.startTime >= ?");
/* 234 */         paramList.add(new Date());
/*     */       } else {
/* 236 */         bf.append(" and c.endTime < ? ");
/* 237 */         paramList.add(new Date());
/*     */       }
/*     */     }
/*     */ 
/* 241 */     if (conference != null)
/*     */     {
/* 243 */       if ((conference.getConfTopic() != null) && (!conference.getConfTopic().trim().equals(""))) {
/* 244 */         bf.append(" and c.confTopic like ?");
/* 245 */         paramList.add("%" + conference.getConfTopic() + "%");
/*     */       }
/*     */ 
/* 248 */       if ((conference.getConfProperty() != null) && (!conference.getConfProperty().trim().equals(""))) {
/* 249 */         bf.append(" and c.confProperty = ?");
/* 250 */         paramList.add(conference.getConfProperty());
/*     */       }
/*     */ 
/* 253 */       if ((conference.getConfContent() != null) && (!conference.getConfContent().trim().equals(""))) {
/* 254 */         bf.append(" and c.confContent like ?");
/* 255 */         paramList.add("%" + conference.getConfContent() + "%");
/*     */       }
/*     */ 
/* 258 */       if ((conference.getRoomName() != null) && (!conference.getRoomName().trim().equals(""))) {
/* 259 */         bf.append(" and c.roomName like ?");
/* 260 */         paramList.add("%" + conference.getRoomName() + "%");
/*     */       }
/*     */ 
/* 263 */       if (conference.getRoomId() != null) {
/* 264 */         bf.append(" and c.roomId = ?");
/* 265 */         paramList.add(conference.getRoomId());
/*     */       }
/*     */ 
/* 268 */       if (conference.getStartTime() != null) {
/* 269 */         bf.append(" and c.startTime = ?");
/* 270 */         paramList.add(conference.getStartTime());
/*     */       }
/*     */ 
/* 273 */       if (conference.getEndTime() != null) {
/* 274 */         bf.append(" and c.endTime = ?");
/* 275 */         paramList.add(conference.getEndTime());
/*     */       }
/*     */ 
/* 278 */       if (conference.getStatus() != null) {
/* 279 */         bf.append(" and c.status = ?");
/* 280 */         paramList.add(conference.getStatus());
/*     */       }
/*     */     }
/* 283 */     bf.append(" order by confId DESC");
/* 284 */     this.logger.debug("与我相关会议查询：" + bf.toString());
/* 285 */     return findByHql(bf.toString(), paramList.toArray(), pb);
/*     */   }
/*     */ 
/*     */   private void setConfPrivilege(Long confId, String ids, int type, Set<ConfPrivilege> set)
/*     */   {
/* 296 */     for (String id : ids.split(",")) {
/* 297 */       AppUser appUser = (AppUser)this.appUserDao.get(new Long(id));
/* 298 */       ConfPrivilege cp = new ConfPrivilege();
/* 299 */       cp.setConfId(confId);
/* 300 */       cp.setUserId(appUser.getUserId());
/* 301 */       cp.setFullname(appUser.getFullname());
/* 302 */       cp.setRights(Short.valueOf((short)type));
/* 303 */       set.add(cp);
/*     */     }
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.admin.impl.ConferenceDaoImpl
 * JD-Core Version:    0.6.0
 */