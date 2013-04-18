/*     */ package com.htsoft.oa.model.info;
/*     */ 
/*     */ import com.htsoft.core.model.BaseModel;
/*     */ import java.util.Date;
/*     */ import org.apache.commons.lang.builder.EqualsBuilder;
/*     */ import org.apache.commons.lang.builder.HashCodeBuilder;
/*     */ import org.apache.commons.lang.builder.ToStringBuilder;
/*     */ 
/*     */ public class SuggestBox extends BaseModel
/*     */ {
/*  22 */   public static final Short STATUS_DRAFT = Short.valueOf((short)0);
/*     */ 
/*  27 */   public static final Short STATUS_SEND = Short.valueOf((short)1);
/*     */ 
/*  32 */   public static final Short STATUS_AUDIT = Short.valueOf((short)2);
/*     */   protected Long boxId;
/*     */   protected String subject;
/*     */   protected String content;
/*     */   protected Date createtime;
/*     */   protected Long recUid;
/*     */   protected String recFullname;
/*     */   protected Long senderId;
/*     */   protected String senderFullname;
/*     */   protected String senderIp;
/*     */   protected String phone;
/*     */   protected String email;
/*     */   protected Short isOpen;
/*     */   protected String replyContent;
/*     */   protected Date replyTime;
/*     */   protected Long replyId;
/*     */   protected String replyFullname;
/*     */   protected Short status;
/*     */   protected String queryPwd;
/*     */ 
/*     */   public SuggestBox()
/*     */   {
/*     */   }
/*     */ 
/*     */   public SuggestBox(Long in_boxId)
/*     */   {
/*  66 */     setBoxId(in_boxId);
/*     */   }
/*     */ 
/*     */   public Long getBoxId()
/*     */   {
/*  76 */     return this.boxId;
/*     */   }
/*     */ 
/*     */   public void setBoxId(Long aValue)
/*     */   {
/*  83 */     this.boxId = aValue;
/*     */   }
/*     */ 
/*     */   public String getSubject()
/*     */   {
/*  91 */     return this.subject;
/*     */   }
/*     */ 
/*     */   public void setSubject(String aValue)
/*     */   {
/*  99 */     this.subject = aValue;
/*     */   }
/*     */ 
/*     */   public String getContent()
/*     */   {
/* 107 */     return this.content;
/*     */   }
/*     */ 
/*     */   public void setContent(String aValue)
/*     */   {
/* 115 */     this.content = aValue;
/*     */   }
/*     */ 
/*     */   public Date getCreatetime()
/*     */   {
/* 123 */     return this.createtime;
/*     */   }
/*     */ 
/*     */   public void setCreatetime(Date aValue)
/*     */   {
/* 130 */     this.createtime = aValue;
/*     */   }
/*     */ 
/*     */   public Long getRecUid()
/*     */   {
/* 138 */     return this.recUid;
/*     */   }
/*     */ 
/*     */   public void setRecUid(Long aValue)
/*     */   {
/* 145 */     this.recUid = aValue;
/*     */   }
/*     */ 
/*     */   public String getRecFullname()
/*     */   {
/* 153 */     return this.recFullname;
/*     */   }
/*     */ 
/*     */   public void setRecFullname(String aValue)
/*     */   {
/* 160 */     this.recFullname = aValue;
/*     */   }
/*     */ 
/*     */   public Long getSenderId()
/*     */   {
/* 168 */     return this.senderId;
/*     */   }
/*     */ 
/*     */   public void setSenderId(Long aValue)
/*     */   {
/* 175 */     this.senderId = aValue;
/*     */   }
/*     */ 
/*     */   public String getSenderFullname()
/*     */   {
/* 183 */     return this.senderFullname;
/*     */   }
/*     */ 
/*     */   public void setSenderFullname(String aValue)
/*     */   {
/* 190 */     this.senderFullname = aValue;
/*     */   }
/*     */ 
/*     */   public String getSenderIp()
/*     */   {
/* 198 */     return this.senderIp;
/*     */   }
/*     */ 
/*     */   public void setSenderIp(String aValue)
/*     */   {
/* 205 */     this.senderIp = aValue;
/*     */   }
/*     */ 
/*     */   public String getPhone()
/*     */   {
/* 213 */     return this.phone;
/*     */   }
/*     */ 
/*     */   public void setPhone(String aValue)
/*     */   {
/* 220 */     this.phone = aValue;
/*     */   }
/*     */ 
/*     */   public String getEmail()
/*     */   {
/* 228 */     return this.email;
/*     */   }
/*     */ 
/*     */   public void setEmail(String aValue)
/*     */   {
/* 235 */     this.email = aValue;
/*     */   }
/*     */ 
/*     */   public Short getIsOpen()
/*     */   {
/* 243 */     return this.isOpen;
/*     */   }
/*     */ 
/*     */   public void setIsOpen(Short aValue)
/*     */   {
/* 250 */     this.isOpen = aValue;
/*     */   }
/*     */ 
/*     */   public String getReplyContent()
/*     */   {
/* 258 */     return this.replyContent;
/*     */   }
/*     */ 
/*     */   public void setReplyContent(String aValue)
/*     */   {
/* 265 */     this.replyContent = aValue;
/*     */   }
/*     */ 
/*     */   public Date getReplyTime()
/*     */   {
/* 273 */     return this.replyTime;
/*     */   }
/*     */ 
/*     */   public void setReplyTime(Date aValue)
/*     */   {
/* 280 */     this.replyTime = aValue;
/*     */   }
/*     */ 
/*     */   public Long getReplyId()
/*     */   {
/* 288 */     return this.replyId;
/*     */   }
/*     */ 
/*     */   public void setReplyId(Long aValue)
/*     */   {
/* 295 */     this.replyId = aValue;
/*     */   }
/*     */ 
/*     */   public String getReplyFullname()
/*     */   {
/* 303 */     return this.replyFullname;
/*     */   }
/*     */ 
/*     */   public void setReplyFullname(String aValue)
/*     */   {
/* 310 */     this.replyFullname = aValue;
/*     */   }
/*     */ 
/*     */   public Short getStatus()
/*     */   {
/* 318 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(Short aValue)
/*     */   {
/* 325 */     this.status = aValue;
/*     */   }
/*     */ 
/*     */   public String getQueryPwd()
/*     */   {
/* 333 */     return this.queryPwd;
/*     */   }
/*     */ 
/*     */   public void setQueryPwd(String aValue)
/*     */   {
/* 340 */     this.queryPwd = aValue;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 347 */     if (!(object instanceof SuggestBox)) {
/* 348 */       return false;
/*     */     }
/* 350 */     SuggestBox rhs = (SuggestBox)object;
/* 351 */     return new EqualsBuilder()
/* 352 */       .append(this.boxId, rhs.boxId)
/* 353 */       .append(this.subject, rhs.subject)
/* 354 */       .append(this.content, rhs.content)
/* 355 */       .append(this.createtime, rhs.createtime)
/* 356 */       .append(this.recUid, rhs.recUid)
/* 357 */       .append(this.recFullname, rhs.recFullname)
/* 358 */       .append(this.senderId, rhs.senderId)
/* 359 */       .append(this.senderFullname, rhs.senderFullname)
/* 360 */       .append(this.senderIp, rhs.senderIp)
/* 361 */       .append(this.phone, rhs.phone)
/* 362 */       .append(this.email, rhs.email)
/* 363 */       .append(this.isOpen, rhs.isOpen)
/* 364 */       .append(this.replyContent, rhs.replyContent)
/* 365 */       .append(this.replyTime, rhs.replyTime)
/* 366 */       .append(this.replyId, rhs.replyId)
/* 367 */       .append(this.replyFullname, rhs.replyFullname)
/* 368 */       .append(this.status, rhs.status)
/* 369 */       .append(this.queryPwd, rhs.queryPwd)
/* 370 */       .isEquals();
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 377 */     return new HashCodeBuilder(-82280557, -700257973)
/* 378 */       .append(this.boxId)
/* 379 */       .append(this.subject)
/* 380 */       .append(this.content)
/* 381 */       .append(this.createtime)
/* 382 */       .append(this.recUid)
/* 383 */       .append(this.recFullname)
/* 384 */       .append(this.senderId)
/* 385 */       .append(this.senderFullname)
/* 386 */       .append(this.senderIp)
/* 387 */       .append(this.phone)
/* 388 */       .append(this.email)
/* 389 */       .append(this.isOpen)
/* 390 */       .append(this.replyContent)
/* 391 */       .append(this.replyTime)
/* 392 */       .append(this.replyId)
/* 393 */       .append(this.replyFullname)
/* 394 */       .append(this.status)
/* 395 */       .append(this.queryPwd)
/* 396 */       .toHashCode();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 403 */     return new ToStringBuilder(this)
/* 404 */       .append("boxId", this.boxId)
/* 405 */       .append("subject", this.subject)
/* 406 */       .append("content", this.content)
/* 407 */       .append("createtime", this.createtime)
/* 408 */       .append("recUid", this.recUid)
/* 409 */       .append("recFullname", this.recFullname)
/* 410 */       .append("senderId", this.senderId)
/* 411 */       .append("senderFullname", this.senderFullname)
/* 412 */       .append("senderIp", this.senderIp)
/* 413 */       .append("phone", this.phone)
/* 414 */       .append("email", this.email)
/* 415 */       .append("isOpen", this.isOpen)
/* 416 */       .append("replyContent", this.replyContent)
/* 417 */       .append("replyTime", this.replyTime)
/* 418 */       .append("replyId", this.replyId)
/* 419 */       .append("replyFullname", this.replyFullname)
/* 420 */       .append("status", this.status)
/* 421 */       .append("queryPwd", this.queryPwd)
/* 422 */       .toString();
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.info.SuggestBox
 * JD-Core Version:    0.6.0
 */