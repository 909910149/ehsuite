/*     */ package com.htsoft.oa.model.communicate;
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
/*     */ public class OutMail extends BaseModel
/*     */ {
/*     */   protected String uid;
/*     */   protected String title;
/*     */   protected String content;
/*     */   protected String senderAddresses;
/*     */   protected String senderName;
/*     */   protected String receiverAddresses;
/*     */   protected String receiverNames;
/*     */   protected String cCAddresses;
/*     */   protected String cCNames;
/*     */   protected String bCCAddresses;
/*     */   protected String bCCAnames;
/*     */   protected Date mailDate;
/*     */   protected String fileIds;
/*     */   protected String fileNames;
/*     */   protected Short readFlag;
/*     */   protected Short replyFlag;
/*     */   protected OutMailFolder outMailFolder;
/*  41 */   protected Set<FileAttach> outMailFiles = new HashSet();
/*     */   protected Long userId;
/*     */   protected Long mailId;
/*     */ 
/*     */   public String getUid()
/*     */   {
/*  48 */     return this.uid;
/*     */   }
/*     */ 
/*     */   public void setUid(String uid) {
/*  52 */     this.uid = uid;
/*     */   }
/*     */ 
/*     */   public Set<FileAttach> getOutMailFiles()
/*     */   {
/*  59 */     return this.outMailFiles;
/*     */   }
/*     */ 
/*     */   public void setOutMailFiles(Set<FileAttach> outMailFiles)
/*     */   {
/*  64 */     this.outMailFiles = outMailFiles;
/*     */   }
/*     */ 
/*     */   public String getcCAddresses()
/*     */   {
/*  70 */     return this.cCAddresses;
/*     */   }
/*     */ 
/*     */   public void setcCAddresses(String cCAddresses) {
/*  74 */     this.cCAddresses = cCAddresses;
/*     */   }
/*     */ 
/*     */   public String getcCNames() {
/*  78 */     return this.cCNames;
/*     */   }
/*     */ 
/*     */   public void setcCNames(String cCNames) {
/*  82 */     this.cCNames = cCNames;
/*     */   }
/*     */ 
/*     */   public String getbCCAddresses() {
/*  86 */     return this.bCCAddresses;
/*     */   }
/*     */ 
/*     */   public void setbCCAddresses(String bCCAddresses) {
/*  90 */     this.bCCAddresses = bCCAddresses;
/*     */   }
/*     */ 
/*     */   public String getbCCAnames() {
/*  94 */     return this.bCCAnames;
/*     */   }
/*     */ 
/*     */   public void setbCCAnames(String bCCAnames) {
/*  98 */     this.bCCAnames = bCCAnames;
/*     */   }
/*     */ 
/*     */   public OutMail()
/*     */   {
/*     */   }
/*     */ 
/*     */   public OutMail(Long in_mailId)
/*     */   {
/* 118 */     setMailId(in_mailId);
/*     */   }
/*     */ 
/*     */   public OutMailFolder getOutMailFolder()
/*     */   {
/* 123 */     return this.outMailFolder;
/*     */   }
/*     */ 
/*     */   public void setOutMailFolder(OutMailFolder in_outMailFolder) {
/* 127 */     this.outMailFolder = in_outMailFolder;
/*     */   }
/*     */ 
/*     */   public Long getFolderId()
/*     */   {
/* 148 */     return getOutMailFolder() == null ? null : getOutMailFolder().getFolderId();
/*     */   }
/*     */ 
/*     */   public void setFolderId(Long aValue)
/*     */   {
/* 155 */     if (aValue == null) {
/* 156 */       this.outMailFolder = null;
/* 157 */     } else if (this.outMailFolder == null) {
/* 158 */       this.outMailFolder = new OutMailFolder(aValue);
/* 159 */       this.outMailFolder.setVersion(new Integer(0));
/*     */     } else {
/* 161 */       this.outMailFolder.setFolderId(aValue);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getTitle()
/*     */   {
/* 170 */     return this.title;
/*     */   }
/*     */ 
/*     */   public void setTitle(String aValue)
/*     */   {
/* 177 */     this.title = aValue;
/*     */   }
/*     */ 
/*     */   public String getContent()
/*     */   {
/* 185 */     return this.content;
/*     */   }
/*     */ 
/*     */   public void setContent(String aValue)
/*     */   {
/* 192 */     this.content = aValue;
/*     */   }
/*     */ 
/*     */   public String getSenderAddresses()
/*     */   {
/* 200 */     return this.senderAddresses;
/*     */   }
/*     */ 
/*     */   public void setSenderAddresses(String aValue)
/*     */   {
/* 208 */     this.senderAddresses = aValue;
/*     */   }
/*     */ 
/*     */   public String getSenderName()
/*     */   {
/* 216 */     return this.senderName;
/*     */   }
/*     */ 
/*     */   public void setSenderName(String aValue)
/*     */   {
/* 223 */     this.senderName = aValue;
/*     */   }
/*     */ 
/*     */   public String getReceiverAddresses()
/*     */   {
/* 231 */     return this.receiverAddresses;
/*     */   }
/*     */ 
/*     */   public void setReceiverAddresses(String aValue)
/*     */   {
/* 239 */     this.receiverAddresses = aValue;
/*     */   }
/*     */ 
/*     */   public String getReceiverNames()
/*     */   {
/* 247 */     return this.receiverNames;
/*     */   }
/*     */ 
/*     */   public void setReceiverNames(String aValue)
/*     */   {
/* 254 */     this.receiverNames = aValue;
/*     */   }
/*     */ 
/*     */   public String getCCAddresses()
/*     */   {
/* 262 */     return this.cCAddresses;
/*     */   }
/*     */ 
/*     */   public void setCCAddresses(String aValue)
/*     */   {
/* 269 */     this.cCAddresses = aValue;
/*     */   }
/*     */ 
/*     */   public String getCCNames()
/*     */   {
/* 277 */     return this.cCNames;
/*     */   }
/*     */ 
/*     */   public void setCCNames(String aValue)
/*     */   {
/* 284 */     this.cCNames = aValue;
/*     */   }
/*     */ 
/*     */   public String getBCCAddresses()
/*     */   {
/* 292 */     return this.bCCAddresses;
/*     */   }
/*     */ 
/*     */   public void setBCCAddresses(String aValue)
/*     */   {
/* 299 */     this.bCCAddresses = aValue;
/*     */   }
/*     */ 
/*     */   public String getBCCAnames()
/*     */   {
/* 307 */     return this.bCCAnames;
/*     */   }
/*     */ 
/*     */   public void setBCCAnames(String aValue)
/*     */   {
/* 314 */     this.bCCAnames = aValue;
/*     */   }
/*     */ 
/*     */   public Date getMailDate()
/*     */   {
/* 322 */     return this.mailDate;
/*     */   }
/*     */ 
/*     */   public void setMailDate(Date aValue)
/*     */   {
/* 330 */     this.mailDate = aValue;
/*     */   }
/*     */ 
/*     */   public String getFileIds()
/*     */   {
/* 338 */     return this.fileIds;
/*     */   }
/*     */ 
/*     */   public void setFileIds(String aValue)
/*     */   {
/* 345 */     this.fileIds = aValue;
/*     */   }
/*     */ 
/*     */   public String getFileNames()
/*     */   {
/* 353 */     return this.fileNames;
/*     */   }
/*     */ 
/*     */   public void setFileNames(String aValue)
/*     */   {
/* 360 */     this.fileNames = aValue;
/*     */   }
/*     */ 
/*     */   public Short getReadFlag()
/*     */   {
/* 369 */     return this.readFlag;
/*     */   }
/*     */ 
/*     */   public void setReadFlag(Short aValue)
/*     */   {
/* 377 */     this.readFlag = aValue;
/*     */   }
/*     */ 
/*     */   public Short getReplyFlag()
/*     */   {
/* 386 */     return this.replyFlag;
/*     */   }
/*     */ 
/*     */   public void setReplyFlag(Short aValue)
/*     */   {
/* 394 */     this.replyFlag = aValue;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 401 */     if (!(object instanceof OutMail)) {
/* 402 */       return false;
/*     */     }
/* 404 */     OutMail rhs = (OutMail)object;
/* 405 */     return new EqualsBuilder()
/* 406 */       .append(this.mailId, rhs.mailId)
/* 407 */       .append(this.title, rhs.title)
/* 408 */       .append(this.content, rhs.content)
/* 409 */       .append(this.senderAddresses, rhs.senderAddresses)
/* 410 */       .append(this.senderName, rhs.senderName)
/* 411 */       .append(this.receiverAddresses, rhs.receiverAddresses)
/* 412 */       .append(this.receiverNames, rhs.receiverNames)
/* 413 */       .append(this.cCAddresses, rhs.cCAddresses)
/* 414 */       .append(this.cCNames, rhs.cCNames)
/* 415 */       .append(this.bCCAddresses, rhs.bCCAddresses)
/* 416 */       .append(this.bCCAnames, rhs.bCCAnames)
/* 417 */       .append(this.mailDate, rhs.mailDate)
/* 418 */       .append(this.fileIds, rhs.fileIds)
/* 419 */       .append(this.fileNames, rhs.fileNames)
/* 420 */       .append(this.readFlag, rhs.readFlag)
/* 421 */       .append(this.replyFlag, rhs.replyFlag)
/* 422 */       .isEquals();
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 429 */     return new HashCodeBuilder(-82280557, -700257973)
/* 430 */       .append(this.mailId)
/* 431 */       .append(this.title)
/* 432 */       .append(this.content)
/* 433 */       .append(this.senderAddresses)
/* 434 */       .append(this.senderName)
/* 435 */       .append(this.receiverAddresses)
/* 436 */       .append(this.receiverNames)
/* 437 */       .append(this.cCAddresses)
/* 438 */       .append(this.cCNames)
/* 439 */       .append(this.bCCAddresses)
/* 440 */       .append(this.bCCAnames)
/* 441 */       .append(this.mailDate)
/* 442 */       .append(this.fileIds)
/* 443 */       .append(this.fileNames)
/* 444 */       .append(this.readFlag)
/* 445 */       .append(this.replyFlag)
/* 446 */       .toHashCode();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 453 */     return new ToStringBuilder(this)
/* 454 */       .append("mailId", this.mailId)
/* 455 */       .append("title", this.title)
/* 456 */       .append("content", this.content)
/* 457 */       .append("senderAddresses", this.senderAddresses)
/* 458 */       .append("senderName", this.senderName)
/* 459 */       .append("receiverAddresses", this.receiverAddresses)
/* 460 */       .append("receiverNames", this.receiverNames)
/* 461 */       .append("cCAddresses", this.cCAddresses)
/* 462 */       .append("cCNames", this.cCNames)
/* 463 */       .append("bCCAddresses", this.bCCAddresses)
/* 464 */       .append("bCCAnames", this.bCCAnames)
/* 465 */       .append("mailDate", this.mailDate)
/* 466 */       .append("fileIds", this.fileIds)
/* 467 */       .append("fileNames", this.fileNames)
/* 468 */       .append("readFlag", this.readFlag)
/* 469 */       .append("replyFlag", this.replyFlag)
/* 470 */       .toString();
/*     */   }
/*     */   public Long getUserId() {
/* 473 */     return this.userId;
/*     */   }
/*     */ 
/*     */   public void setUserId(Long userId) {
/* 477 */     this.userId = userId;
/*     */   }
/*     */ 
/*     */   public Long getMailId()
/*     */   {
/* 484 */     return this.mailId;
/*     */   }
/*     */ 
/*     */   public void setMailId(Long aValue)
/*     */   {
/* 491 */     this.mailId = aValue;
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.communicate.OutMail
 * JD-Core Version:    0.6.0
 */