 package com.htsoft.oa.action.admin;
 
 import com.google.gson.Gson;
 import com.google.gson.GsonBuilder;
 import com.google.gson.reflect.TypeToken;
 import com.htsoft.core.command.QueryFilter;
 import com.htsoft.core.util.ContextUtil;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.model.admin.BoardRoo;
 import com.htsoft.oa.model.admin.BoardType;
 import com.htsoft.oa.model.admin.Conference;
 import com.htsoft.oa.service.admin.BoardRooService;
 import com.htsoft.oa.service.admin.BoardTypeService;
 import com.htsoft.oa.service.admin.ConferenceService;
 import java.lang.reflect.Type;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 
 public class ConferenceAction extends BaseAction
 {
   private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 
   @Resource
   private ConferenceService conferenceService;
 
   @Resource
   private BoardRooService boardRooService;
 
   @Resource
   private BoardTypeService boardTypeService;
   private Long confId;
   private String filePath;
   private String checkReason;
   private String updater;
   private Conference conference;
 
   public String getUpdater() { return this.updater; }
 
   public void setUpdater(String updater)
   {
     this.updater = updater;
   }
 
   public String getFilePath() {
     return this.filePath;
   }
 
   public void setFilePath(String filePath) {
     this.filePath = filePath;
   }
 
   public Long getConfId() {
     return this.confId;
   }
 
   public void setConfId(Long confId) {
     this.confId = confId;
   }
 
   public Conference getConference() {
     return this.conference;
   }
 
   public void setConference(Conference conference) {
     this.conference = conference;
   }
 
   public String getCheckReason() {
     return this.checkReason;
   }
 
   public void setCheckReason(String checkReason) {
     this.checkReason = checkReason;
   }
 
   public String displayMyconf()
   {
     if (this.conference == null)
       this.conference = new Conference();
     this.conference.setStatus(Short.valueOf((short) 1));
     PagingBean pb = getInitPagingBean();
     List list = this.conferenceService.myJoin(this.conference, Boolean.valueOf(false), pb);
     for (int i = 0; i < list.size(); i++) {
       if (i > 7) {
         for (int j = 7; j < list.size(); j++)
           list.remove(j);
       }
     }
     getRequest().setAttribute("myConferenceList", list);
     return "display";
   }
 
   public String zanCun()
   {
     QueryFilter filter = new QueryFilter(getRequest());
 
     filter.addFilter("Q_status_SN_EQ", "0");
     return filter(filter);
   }
 
   public String myJoin()
   {
     if (this.conference == null)
       this.conference = new Conference();
     this.conference.setStatus(Short.valueOf((short) 1));
     PagingBean pb = getInitPagingBean();
     List list = this.conferenceService.myJoin(this.conference, Boolean.valueOf(false), pb);
     return toJson(pb, list);
   }
 
   public String myJoined()
   {
     if (this.conference == null)
       this.conference = new Conference();
     this.conference.setStatus(Short.valueOf((short) 1));
     PagingBean pb = getInitPagingBean();
     List list = this.conferenceService.myJoin(this.conference, Boolean.valueOf(true), pb);
     return toJson(pb, list);
   }
 
   public String daiKai()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     filter.addFilter("Q_startTime_D_GE", this.sdf.format(new Date()));
     filter.addFilter("Q_status_SN_EQ", "1");
     return filter(filter);
   }
 
   public String yiKai()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     filter.addFilter("Q_endTime_D_LE", this.sdf.format(new Date()));
     filter.addFilter("Q_status_SN_EQ", "1");
     return filter(filter);
   }
 
   public String getConfTopic()
   {
     PagingBean pb = getInitPagingBean();
     String confTopic = getRequest().getParameter("Q_confTopic_S_LK");
     List list = this.conferenceService.getConfTopic(confTopic, pb);
 
     return toJson(pb, list);
   }
 
   public String send()
   {
     String rs = this.conferenceService.judgeBoardRoomNotUse(this.conference.getStartTime(), 
       this.conference.getEndTime(), this.conference.getRoomId());
     if (rs.equalsIgnoreCase("success")) {
       return customSave(Conference.Apply);
     }
     setJsonString("{failure:true,msg:'" + rs + "'}");
     return "success";
   }
 
   public String temp()
   {
     return customSave(Conference.TEMP);
   }
 
   public String list()
   {
     QueryFilter filter = new QueryFilter(getRequest());
 
     return filter(filter);
   }
 
   public String multiDel()
   {
     String[] ids = getRequest().getParameterValues("ids");
     if (ids != null) {
       for (String id : ids)
         this.conferenceService.remove(new Long(id));
     }
     this.jsonString = "{success:true}";
     return "success";
   }
 
   public String get()
   {
     Conference conference = (Conference)this.conferenceService.get(this.confId);
     Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
       .create();
 
     StringBuffer sb = new StringBuffer("{success:true,data:");
     sb.append(gson.toJson(conference));
     setJsonString("}");
     return "success";
   }
 
   public String save()
   {
     this.conferenceService.save(this.conference);
     setJsonString("{success:true}");
     return "success";
   }
 
   public String getBoardroo()
   {
     List<BoardRoo> list = this.boardRooService.getAll();
     StringBuffer bf = new StringBuffer("[");
     for (BoardRoo br : list) {
       bf.append("['").append(br.getRoomId()).append("','").append(
         br.getRoomName()).append("'],");
     }
     bf.deleteCharAt(bf.length() - 1).append("]");
     setJsonString(bf.toString());
     return "success";
   }
 
   public String getTypeAll()
   {
     List<BoardType> list = this.boardTypeService.getAll();
     StringBuffer bf = new StringBuffer("[");
     for (BoardType bt : list) {
       bf.append("['").append(bt.getTypeId()).append("','").append(
         bt.getTypeName()).append("'],");
     }
     bf.deleteCharAt(bf.length() - 1).append("]");
     setJsonString(bf.toString());
     return "success";
   }
 
   public String apply()
   {
     String msg = "{success:true}";
     String status = getRequest().getParameter("status");
     boolean bo = (status != null) && (status.equals("1"));
     if (bo) {
       String rs = judgeBoardRoomUse();
       if (rs.equalsIgnoreCase("success")) {
         this.conferenceService.apply(this.confId, this.checkReason, bo);
       } else {
         Conference cf = (Conference)this.conferenceService.get(this.confId);
         cf.setStatus(Conference.UNAPPLY);
         cf.setCheckReason("审核未通过," + rs);
         this.conferenceService.save(cf);
         msg = "{failure:true,msg:'" + rs + "'}";
       }
     } else {
       this.conferenceService.apply(this.confId, this.checkReason, bo);
     }setJsonString(msg);
     return "success";
   }
 
   public String daiConfApply()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     filter.addFilter("Q_checkUserId_L_EQ", ContextUtil.getCurrentUserId()
       .toString());
     filter.addFilter("Q_status_SN_EQ", "2");
     filter.addSorted("createtime", "DESC");
     return filter(filter);
   }
 
   public String unThrought()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     filter.addFilter("Q_status_SN_EQ", "3");
     filter.addSorted("createtime", "DESC");
     return filter(filter);
   }
 
   public String displyApply()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     filter.addFilter("Q_checkUserId_L_EQ", ContextUtil.getCurrentUserId()
       .toString());
     filter.addFilter("Q_status_SN_EQ", "2");
     filter.addFilter("Q_startTime_D_GE", this.sdf.format(new Date()));
     filter.addSorted("createtime", "DESC");
     List list = this.conferenceService.getAll(filter);
     if (list.size() > 8) {
       for (int i = 7; i < list.size(); i++) {
         list.remove(i);
       }
     }
     getRequest().setAttribute("applyConferenceList", list);
     return "displayApply";
   }
 
   private String toJson(PagingBean pb, List<Conference> list)
   {
     Type type = new TypeToken() {
     }.getType();
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(pb.getTotalItems()).append(",result:");
     Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss")
       .create();
     buff.append(gson.toJson(list, type));
     buff.append("}");
     setJsonString(buff.toString());
     return "success";
   }
 
   private String filter(QueryFilter filter)
   {
     filter.addSorted("startTime", "DESC");
     List list = this.conferenceService.getAll(filter);
 
     Type type = new TypeToken() {
     }.getType();
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(filter.getPagingBean().getTotalItems()).append(
       ",result:");
     Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm")
       .create();
     buff.append(gson.toJson(list, type));
     buff.append("}");
     setJsonString(buff.toString());
     return "success";
   }
 
   private String customSave(Short st)
   {
     if (this.conference.getIsEmail() == null)
       this.conference.setIsEmail(Conference.ISNOEMAIL);
     if (this.conference.getIsMobile() == null)
       this.conference.setIsMobile(Conference.ISNOMOBILE);
     this.conference.setStatus(st);
 
     String viewer = this.conference.getCompere() + "," + 
       this.conference.getRecorder() + "," + this.conference.getAttendUsers() + 
       "," + ContextUtil.getCurrentUserId() + "," + 
       this.conference.getCheckUserId();
     viewer = removeRepeatUserId(viewer);
 
     this.updater = (this.updater + "," + ContextUtil.getCurrentUserId());
     this.updater = removeRepeatUserId(this.updater);
 
     if (st == Conference.Apply)
       this.conferenceService.send(this.conference, viewer, this.updater, this.conference
         .getRecorder(), this.filePath);
     else {
       this.conferenceService.temp(this.conference, viewer, this.updater, this.conference
         .getRecorder(), this.filePath);
     }
     setJsonString("{success:true}");
     return "success";
   }
 
   private String judgeBoardRoomUse()
   {
     Conference cf = (Conference)this.conferenceService.get(this.confId);
     String msg = this.conferenceService.judgeBoardRoomNotUse(cf.getStartTime(), 
       cf.getEndTime(), cf.getRoomId());
     return msg;
   }
 
   private String removeRepeatUserId(String uIds)
   {
     String msg = "";
     Map map = new HashMap();
     for (String uId : uIds.split(",")) {
       if (!map.containsKey(uId)) {
         map.put(uId, uId);
         msg = msg + uId + ",";
       }
     }
     msg = msg.substring(0, msg.length() - 1);
     return msg;
   }
 }

