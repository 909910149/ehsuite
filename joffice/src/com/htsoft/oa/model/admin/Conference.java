/*     */ package com.htsoft.oa.model.admin;
/*     */ 
/*     */ import com.htsoft.core.model.BaseModel;
/*     */ import com.htsoft.oa.model.system.FileAttach;
/*     */ import java.util.Date;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang.builder.EqualsBuilder;
/*     */ import org.apache.commons.lang.builder.HashCodeBuilder;
/*     */ import org.apache.commons.lang.builder.ToStringBuilder;
/*     */ 
/*     */ public class Conference extends BaseModel
/*     */ {
/*  30 */   public static final Short ISNOEMAIL = Short.valueOf((short) 0);
/*     */ 
/*  34 */   public static final Short ISNOMOBILE = Short.valueOf((short)0);
/*     */ 
/*  39 */   public static final Short SEND = Short.valueOf((short)1);
/*     */ 
/*  44 */   public static final Short Apply = Short.valueOf((short)2);
/*     */ 
/*  49 */   public static final Short UNAPPLY = Short.valueOf((short)3);
/*     */ 
/*  54 */   public static final Short TEMP = Short.valueOf((short)0);
/*     */   protected Long confId;
/*     */   protected String confTopic;
/*     */   protected String confProperty;
/*     */   protected Short importLevel;
/*     */   protected Double feeBudget;
/*     */   protected String compere;
/*     */   protected String recorder;
/*     */   protected String attendUsers;
/*     */   protected Short status;
/*     */   protected Short isEmail;
/*     */   protected Short isMobile;
/*     */   protected Date startTime;
/*     */   protected Date endTime;
/*     */   protected Long roomId;
/*     */   protected String roomName;
/*     */   protected String roomLocation;
/*     */   protected String confContent;
/*     */   protected String compereName;
/*     */   protected String recorderName;
/*     */   protected String attendUsersName;
/*     */   protected Long checkUserId;
/*     */   protected String checkName;
/*     */   protected String checkReason;
/*     */   protected Date createtime;
/*     */   protected Date sendtime;
/*     */   protected Long typeId;
/*  84 */   protected Set<ConfPrivilege> confPrivilege = new HashSet();
/*     */ 
/*  86 */   protected Set<FileAttach> attachFiles = new HashSet();
/*     */ 
/*     */   public Conference()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Conference(Long in_confId)
/*     */   {
/*  99 */     setConfId(in_confId);
/*     */   }
/*     */ 
/*     */   public Long getConfId()
/*     */   {
/* 109 */     return this.confId;
/*     */   }
/*     */ 
/*     */   public void setConfId(Long aValue)
/*     */   {
/* 116 */     this.confId = aValue;
/*     */   }
/*     */ 
/*     */   public String getConfTopic()
/*     */   {
/* 126 */     return this.confTopic;
/*     */   }
/*     */ 
/*     */   public void setConfTopic(String aValue)
/*     */   {
/* 135 */     this.confTopic = aValue;
/*     */   }
/*     */ 
/*     */   public String getConfProperty()
/*     */   {
/* 145 */     return this.confProperty;
/*     */   }
/*     */ 
/*     */   public void setConfProperty(String confProperty)
/*     */   {
/* 152 */     this.confProperty = confProperty;
/*     */   }
/*     */ 
/*     */   public Short getImportLevel()
/*     */   {
/* 162 */     return this.importLevel;
/*     */   }
/*     */ 
/*     */   public void setImportLevel(Short aValue)
/*     */   {
/* 171 */     this.importLevel = aValue;
/*     */   }
/*     */ 
/*     */   public Double getFeeBudget()
/*     */   {
/* 181 */     return this.feeBudget;
/*     */   }
/*     */ 
/*     */   public void setFeeBudget(Double aValue)
/*     */   {
/* 188 */     this.feeBudget = aValue;
/*     */   }
/*     */ 
/*     */   public String getCompere()
/*     */   {
/* 198 */     return this.compere;
/*     */   }
/*     */ 
/*     */   public void setCompere(String aValue)
/*     */   {
/* 205 */     this.compere = aValue;
/*     */   }
/*     */ 
/*     */   public String getRecorder()
/*     */   {
/* 215 */     return this.recorder;
/*     */   }
/*     */ 
/*     */   public void setRecorder(String aValue)
/*     */   {
/* 222 */     this.recorder = aValue;
/*     */   }
/*     */ 
/*     */   public String getAttendUsers()
/*     */   {
/* 232 */     return this.attendUsers;
/*     */   }
/*     */ 
/*     */   public void setAttendUsers(String aValue)
/*     */   {
/* 239 */     this.attendUsers = aValue;
/*     */   }
/*     */ 
/*     */   public Short getStatus()
/*     */   {
/* 249 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(Short aValue)
/*     */   {
/* 258 */     this.status = aValue;
/*     */   }
/*     */ 
/*     */   public Short getIsEmail()
/*     */   {
/* 268 */     return this.isEmail;
/*     */   }
/*     */ 
/*     */   public void setIsEmail(Short aValue)
/*     */   {
/* 275 */     this.isEmail = aValue;
/*     */   }
/*     */ 
/*     */   public Short getIsMobile()
/*     */   {
/* 285 */     return this.isMobile;
/*     */   }
/*     */ 
/*     */   public void setIsMobile(Short aValue)
/*     */   {
/* 292 */     this.isMobile = aValue;
/*     */   }
/*     */ 
/*     */   public Date getStartTime()
/*     */   {
/* 302 */     return this.startTime;
/*     */   }
/*     */ 
/*     */   public void setStartTime(Date aValue)
/*     */   {
/* 311 */     this.startTime = aValue;
/*     */   }
/*     */ 
/*     */   public Date getEndTime()
/*     */   {
/* 321 */     return this.endTime;
/*     */   }
/*     */ 
/*     */   public void setEndTime(Date aValue)
/*     */   {
/* 330 */     this.endTime = aValue;
/*     */   }
/*     */ 
/*     */   public Long getRoomId()
/*     */   {
/* 340 */     return this.roomId;
/*     */   }
/*     */ 
/*     */   public void setRoomId(Long roomId)
/*     */   {
/* 347 */     this.roomId = roomId;
/*     */   }
/*     */ 
/*     */   public String getRoomName()
/*     */   {
/* 357 */     return this.roomName;
/*     */   }
/*     */ 
/*     */   public void setRoomName(String aValue)
/*     */   {
/* 364 */     this.roomName = aValue;
/*     */   }
/*     */ 
/*     */   public String getRoomLocation()
/*     */   {
/* 374 */     return this.roomLocation;
/*     */   }
/*     */ 
/*     */   public void setRoomLocation(String aValue)
/*     */   {
/* 381 */     this.roomLocation = aValue;
/*     */   }
/*     */ 
/*     */   public String getConfContent()
/*     */   {
/* 391 */     return this.confContent;
/*     */   }
/*     */ 
/*     */   public void setConfContent(String aValue)
/*     */   {
/* 398 */     this.confContent = aValue;
/*     */   }
/*     */ 
/*     */   public String getCompereName() {
/* 402 */     return this.compereName;
/*     */   }
/*     */ 
/*     */   public void setCompereName(String compereName) {
/* 406 */     this.compereName = compereName;
/*     */   }
/*     */ 
/*     */   public String getRecorderName() {
/* 410 */     return this.recorderName;
/*     */   }
/*     */ 
/*     */   public void setRecorderName(String recorderName) {
/* 414 */     this.recorderName = recorderName;
/*     */   }
/*     */ 
/*     */   public String getAttendUsersName() {
/* 418 */     return this.attendUsersName;
/*     */   }
/*     */ 
/*     */   public void setAttendUsersName(String attendUsersName) {
/* 422 */     this.attendUsersName = attendUsersName;
/*     */   }
/*     */ 
/*     */   public Date getCreatetime()
/*     */   {
/* 432 */     return this.createtime;
/*     */   }
/*     */ 
/*     */   public void setCreatetime(Date aValue)
/*     */   {
/* 439 */     this.createtime = aValue;
/*     */   }
/*     */ 
/*     */   public Date getSendtime()
/*     */   {
/* 449 */     return this.sendtime;
/*     */   }
/*     */ 
/*     */   public void setSendtime(Date aValue)
/*     */   {
/* 456 */     this.sendtime = aValue;
/*     */   }
/*     */ 
/*     */   public Set<FileAttach> getAttachFiles() {
/* 460 */     return this.attachFiles;
/*     */   }
/*     */ 
/*     */   public void setAttachFiles(Set<FileAttach> attachFiles) {
/* 464 */     this.attachFiles = attachFiles;
/*     */   }
/*     */ 
/*     */   public Set<ConfPrivilege> getConfPrivilege() {
/* 468 */     return this.confPrivilege;
/*     */   }
/*     */ 
/*     */   public void setConfPrivilege(Set<ConfPrivilege> confPrivilege) {
/* 472 */     this.confPrivilege = confPrivilege;
/*     */   }
/*     */ 
/*     */   public Long getCheckUserId() {
/* 476 */     return this.checkUserId;
/*     */   }
/*     */ 
/*     */   public void setCheckUserId(Long checkUserId) {
/* 480 */     this.checkUserId = checkUserId;
/*     */   }
/*     */ 
/*     */   public String getCheckName() {
/* 484 */     return this.checkName;
/*     */   }
/*     */ 
/*     */   public void setCheckName(String checkName) {
/* 488 */     this.checkName = checkName;
/*     */   }
/*     */ 
/*     */   public String getCheckReason() {
/* 492 */     return this.checkReason;
/*     */   }
/*     */ 
/*     */   public void setCheckReason(String checkReason) {
/* 496 */     this.checkReason = checkReason;
/*     */   }
/*     */ 
/*     */   public Long getTypeId() {
/* 500 */     return this.typeId;
/*     */   }
/*     */ 
/*     */   public void setTypeId(Long typeId) {
/* 504 */     this.typeId = typeId;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 511 */     if (!(object instanceof Conference)) {
/* 512 */       return false;
/*     */     }
/* 514 */     Conference rhs = (Conference)object;
/* 515 */     return new EqualsBuilder().append(this.confId, rhs.confId).append(
/* 516 */       this.confTopic, rhs.confTopic).append(this.confProperty, 
/* 517 */       rhs.confProperty).append(this.importLevel, rhs.importLevel)
/* 518 */       .append(this.feeBudget, rhs.feeBudget).append(this.compere, 
/* 519 */       rhs.compere).append(this.recorder, rhs.recorder)
/* 520 */       .append(this.attendUsers, rhs.attendUsers).append(this.status, 
/* 521 */       rhs.status).append(this.isEmail, rhs.isEmail).append(
/* 522 */       this.isMobile, rhs.isMobile).append(this.startTime, 
/* 523 */       rhs.startTime).append(this.endTime, rhs.endTime)
/* 524 */       .append(this.roomId, rhs.roomId).append(this.roomName, 
/* 525 */       rhs.roomName).append(this.roomLocation, 
/* 526 */       rhs.roomLocation).append(this.confContent, 
/* 527 */       rhs.confContent).append(this.compereName, 
/* 528 */       rhs.compereName).append(this.recorderName, 
/* 529 */       rhs.recorderName).append(this.attendUsersName, 
/* 530 */       rhs.attendUsersName).append(this.checkUserId, 
/* 531 */       rhs.checkUserId).append(this.checkName, rhs.checkName)
/* 532 */       .append(this.checkReason, rhs.checkReason).append(
/* 533 */       this.createtime, rhs.createtime).append(this.sendtime, 
/* 534 */       rhs.sendtime).append(this.typeId, rhs.typeId)
/* 535 */       .isEquals();
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 542 */     return new HashCodeBuilder(-82280557, -700257973).append(this.confId)
/* 543 */       .append(this.confTopic).append(this.confProperty).append(
/* 544 */       this.importLevel).append(this.feeBudget).append(
/* 545 */       this.compere).append(this.recorder).append(
/* 546 */       this.attendUsers).append(this.status).append(
/* 547 */       this.isEmail).append(this.isMobile).append(
/* 548 */       this.startTime).append(this.endTime)
/* 549 */       .append(this.roomId).append(this.roomName).append(
/* 550 */       this.roomLocation).append(this.confContent).append(
/* 551 */       this.compereName).append(this.recorderName).append(
/* 552 */       this.attendUsersName).append(this.checkUserId).append(
/* 553 */       this.checkName).append(this.checkReason).append(
/* 554 */       this.createtime).append(this.sendtime).append(
/* 555 */       this.typeId).toHashCode();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 562 */     return new ToStringBuilder(this).append("confId", this.confId).append(
/* 563 */       "confTopic", this.confTopic).append("confProperty", 
/* 564 */       this.confProperty).append("importLevel", this.importLevel)
/* 565 */       .append("feeBudget", this.feeBudget).append("compere", 
/* 566 */       this.compere).append("recorder", this.recorder).append(
/* 567 */       "attendUsers", this.attendUsers).append("status", 
/* 568 */       this.status).append("isEmail", this.isEmail).append(
/* 569 */       "isMobile", this.isMobile).append("startTime", 
/* 570 */       this.startTime).append("endTime", this.endTime).append(
/* 571 */       "roomId", this.roomId)
/* 572 */       .append("roomName", this.roomName).append("roomLocation", 
/* 573 */       this.roomLocation).append("confContent", 
/* 574 */       this.confContent).append("compereName", 
/* 575 */       this.compereName).append("recorderName", 
/* 576 */       this.recorderName).append("attendUsersName", 
/* 577 */       this.attendUsersName).append("checkUserId", 
/* 578 */       this.checkUserId).append("checkName", this.checkName)
/* 579 */       .append("checkReason", this.checkReason).append("createtime", 
/* 580 */       this.createtime).append("sendtime", this.sendtime)
/* 581 */       .append("typeId", this.typeId).toString();
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.admin.Conference
 * JD-Core Version:    0.6.0
 */