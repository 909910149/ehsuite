package com.htsoft.oa.action.communicate;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Security;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.annotation.Resource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.FetchProfile;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.UIDFolder;
import javax.mail.URLName;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.util.AppUtil;
import com.htsoft.core.util.BeanUtil;
import com.htsoft.core.util.CertUtil;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.util.FileUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.communicate.OutMail;
import com.htsoft.oa.model.communicate.OutMailFolder;
import com.htsoft.oa.model.communicate.OutMailUserSeting;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.model.system.FileAttach;
import com.htsoft.oa.service.communicate.OutMailFolderService;
import com.htsoft.oa.service.communicate.OutMailService;
import com.htsoft.oa.service.communicate.OutMailUserSetingService;
import com.htsoft.oa.service.system.AppUserService;
import com.htsoft.oa.service.system.FileAttachService;
import com.sun.mail.pop3.POP3Folder;
import com.sun.net.ssl.internal.ssl.Provider;
/*      */ 
/*      */ public class OutMailAction extends BaseAction
/*      */ {
/*   78 */   static long FOLDER_ID_RECEIVE = 1L;
/*   79 */   static long FOLDER_ID_SEND = 2L;
/*   80 */   static long FOLDER_ID_DRAFT = 3L;
/*   81 */   static long FOLDER_ID_DELETE = 4L;
/*   82 */   static long FOLDER_TYPE_OTHER = 10L;
/*   83 */   static short OTHER_FOLDER_TYPE = 10;
/*      */ 
/*   85 */   static int FIRST_LEVEL = 1;
/*   86 */   static long FIRST_PARENTID = 0L;
/*   87 */   static short HAVE_DELETE = 1;
/*   88 */   static short NOT_DELETE = 0;
/*   89 */   static short HAVE_READ = 1;
/*   90 */   static short NOT_READ = 0;
/*   91 */   static Short HAVE_REPLY = Short.valueOf((short) 1);
/*   92 */   static short NOT_REPLY = 0;
/*   93 */   static String sendType = "smtp";
/*      */ 
/*   95 */   static String FILE_PATH_ROOT = AppUtil.getAppAbsolutePath() + 
/*   95 */     "attachFiles/";
/*   96 */   static String OUT_MAIL_TEMP = FILE_PATH_ROOT + "outMailTemp/";
/*      */ 
/*      */   @Resource
/*      */   private OutMailService outMailService;
/*      */   private OutMail outMail;
/*      */ 
/*      */   @Resource
/*      */   private FileAttachService fileAttachService;
/*      */ 
/*      */   @Resource
/*      */   private OutMailUserSetingService outMailUserSetingService;
/*      */   private OutMailUserSeting outMailUserSeting;
/*      */ 
/*      */   @Resource
/*      */   private AppUserService appUserService;
/*      */   private AppUser appUser;
/*      */ 
/*      */   @Resource
/*      */   private OutMailFolderService outMailFolderService;
/*      */   private OutMailFolder outMailFolder;
/*      */   private Long mailId;
/*      */   private String outMailIds;
/*      */   private Long fileId;
/*      */   private Long folderId;
/*      */ 
/*  119 */   public OutMailUserSeting getOutMailUserSeting() { return this.outMailUserSeting; }
/*      */ 
/*      */   public void setOutMailUserSeting(OutMailUserSeting outMailUserSeting)
/*      */   {
/*  123 */     this.outMailUserSeting = outMailUserSeting;
/*      */   }
/*      */ 
/*      */   public AppUser getAppUser() {
/*  127 */     return this.appUser;
/*      */   }
/*      */ 
/*      */   public void setAppUser(AppUser appUser) {
/*  131 */     this.appUser = appUser;
/*      */   }
/*      */ 
/*      */   public Long getFileId() {
/*  135 */     return this.fileId;
/*      */   }
/*      */ 
/*      */   public void setFileId(Long fileId) {
/*  139 */     this.fileId = fileId;
/*      */   }
/*      */ 
/*      */   public String getOutMailIds() {
/*  143 */     return this.outMailIds;
/*      */   }
/*      */ 
/*      */   public void setOutMailIds(String outMailIds) {
/*  147 */     this.outMailIds = outMailIds;
/*      */   }
/*      */ 
/*      */   public Long getFolderId() {
/*  151 */     return this.folderId;
/*      */   }
/*      */ 
/*      */   public void setFolderId(Long folderId) {
/*  155 */     this.folderId = folderId;
/*      */   }
/*      */ 
/*      */   public Long getMailId() {
/*  159 */     return this.mailId;
/*      */   }
/*      */ 
/*      */   public void setMailId(Long mailId) {
/*  163 */     this.mailId = mailId;
/*      */   }
/*      */ 
/*      */   public OutMail getOutMail() {
/*  167 */     return this.outMail;
/*      */   }
/*      */ 
/*      */   public void setOutMail(OutMail outMail) {
/*  171 */     this.outMail = outMail;
/*      */   }
/*      */ 
/*      */   public String list()
/*      */   {
/*  232 */     if ((this.folderId == null) || (this.folderId.longValue() == FOLDER_ID_RECEIVE)) {
/*  233 */       setFolderId(new Long(FOLDER_ID_RECEIVE));
/*      */     }
/*      */ 
/*  236 */     QueryFilter filter = new QueryFilter(getRequest());
/*      */ 
/*  238 */     filter.addFilter("Q_userId_L_EQ", ContextUtil.getCurrentUserId()
/*  239 */       .toString());
/*  240 */     filter.addFilter("Q_outMailFolder.folderId_L_EQ", this.folderId.toString());
/*      */ 
/*  242 */     filter.addSorted("mailId", "desc");
/*  243 */     List list = this.outMailService.getAll(filter);
/*      */ 
/*  245 */     getOutMailJsonStr(list, filter.getPagingBean().getTotalItems());
/*      */ 
/*  247 */     return "success";
/*      */   }
/*      */ 
/*      */   public String multiDel()
/*      */   {
/*  256 */     OutMailFolder deleteFolder = (OutMailFolder)this.outMailFolderService.get(Long.valueOf(FOLDER_ID_DELETE));
/*      */ 
/*  258 */     String[] ids = getRequest().getParameterValues("ids");
/*      */ 
/*  260 */     if (ids != null) {
/*  261 */       if (getFolderId().longValue() == FOLDER_ID_DELETE) {
/*  262 */         for (String id : ids) {
/*  263 */           this.outMail = ((OutMail)this.outMailService.get(new Long(id)));
/*      */ 
/*  265 */           if (this.outMail == null)
/*      */             continue;
/*  267 */           Set<FileAttach> outMailFiles = this.outMail
/*  268 */             .getOutMailFiles();
/*  269 */           this.outMailService.remove(this.outMail);
/*      */ 
/*  271 */           if ((outMailFiles != null) && (outMailFiles.size() > 0)) {
/*  272 */             for (FileAttach f : outMailFiles) {
/*  273 */               delPhysicalFile(f);
/*  274 */               this.fileAttachService.remove(f);
/*      */             }
/*      */           }
/*      */         }
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*  282 */         for (String id : ids)
/*      */         {
/*  284 */           this.outMail = ((OutMail)this.outMailService.get(new Long(id)));
/*      */ 
/*  286 */           this.outMail.setOutMailFolder(deleteFolder);
/*  287 */           this.outMailService.save(this.outMail);
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/*  292 */     this.jsonString = "{success:true}";
/*      */ 
/*  294 */     return "success";
/*      */   }
/*      */ 
/*      */   public String get()
/*      */   {
/*  303 */     String opt = getRequest().getParameter("opt");
/*      */ 
/*  305 */     if ((opt == null) || ("".equals(opt)))
/*      */     {
/*  307 */       this.outMail = ((OutMail)this.outMailService.get(this.mailId));
/*  308 */       getRequest().setAttribute("__haveNextOutMailFlag", "");
/*      */     }
/*      */     else {
/*  311 */       String folderId = getRequest().getParameter("folderId");
/*  312 */       if ((folderId == null) || ("".equals(folderId))) {
/*  313 */         folderId = String.valueOf(FOLDER_ID_RECEIVE);
/*      */       }
/*      */ 
/*  317 */       List list = null;
/*      */ 
/*  319 */       QueryFilter filter = new QueryFilter(getRequest());
/*  320 */       filter.getPagingBean().setPageSize(1);
/*      */ 
/*  322 */       filter.addFilter("Q_userId_L_EQ", ContextUtil.getCurrentUserId()
/*  323 */         .toString());
/*      */ 
/*  325 */       filter.addFilter("Q_outMailFolder.folderId_L_EQ", folderId);
/*      */ 
/*  327 */       if (opt.equals("_next"))
/*      */       {
/*  329 */         filter.addFilter("Q_mailId_L_GT", this.mailId.toString());
/*  330 */         list = this.outMailService.getAll(filter);
/*  331 */         if (filter.getPagingBean().getStart().intValue() + 1 == filter
/*  332 */           .getPagingBean().getTotalItems())
/*  333 */           getRequest().setAttribute("__haveNextOutMailFlag", 
/*  334 */             "endNext");
/*      */       }
/*  336 */       else if (opt.equals("_pre"))
/*      */       {
/*  338 */         filter.addFilter("Q_mailId_L_LT", this.mailId.toString());
/*  339 */         filter.addSorted("mailId", "desc");
/*  340 */         list = this.outMailService.getAll(filter);
/*  341 */         if (filter.getPagingBean().getStart().intValue() + 1 == filter
/*  342 */           .getPagingBean().getTotalItems()) {
/*  343 */           getRequest()
/*  344 */             .setAttribute("__haveNextOutMailFlag", "endPre");
/*      */         }
/*      */       }
/*      */ 
/*  348 */       if (list.size() > 0)
/*  349 */         this.outMail = ((OutMail)list.get(0));
/*      */       else {
/*  351 */         this.outMail = ((OutMail)this.outMailService.get(this.mailId));
/*      */       }
/*      */     }
/*      */ 
/*  355 */     setOutMail(this.outMail);
/*  356 */     this.outMail.setReadFlag(Short.valueOf(HAVE_READ));
/*  357 */     this.outMailService.save(this.outMail);
/*      */ 
/*  359 */     if (this.outMail.getFolderId().longValue() == FOLDER_ID_DRAFT) {
/*  360 */       List list = new ArrayList();
/*  361 */       list.add(this.outMail);
/*  362 */       setJsonString(getOutMailJsonStr(list, 1));
/*  363 */       return "success";
/*      */     }
/*  365 */     if ((this.outMail.getReceiverNames() == null) || 
/*  366 */       (this.outMail.getReceiverNames().equals("null"))) {
/*  367 */       this.outMail.setReceiverNames("(收信人未写)");
/*      */     }
/*  369 */     getRequest().setAttribute("outMail_view", this.outMail);
/*  370 */     getRequest()
/*  371 */       .setAttribute("outMailFiles", this.outMail.getOutMailFiles());
/*  372 */     return "detail";
/*      */   }
/*      */ 
/*      */   public String save()
/*      */   {
/*  381 */     this.logger.debug("save start...");
/*      */ 
/*  383 */     setJsonString("{success:true}");
/*      */ 
/*  385 */     String opt = getRequest().getParameter("opt");
/*      */ 
/*  387 */     this.appUser = ((AppUser)this.appUserService.get(new Long(ContextUtil.getCurrentUserId().longValue())));
/*  388 */     this.outMailUserSeting = this.outMailUserSetingService.getByLoginId(
/*  389 */       ContextUtil.getCurrentUserId());
/*  390 */     this.outMailUserSeting.setAppUser(this.appUser);
/*  391 */     this.outMail.setUserId(this.outMailUserSeting.getAppUser().getUserId());
/*  392 */     this.outMail.setSenderAddresses(this.outMailUserSeting.getMailAddress());
/*  393 */     this.outMail.setSenderName(this.outMailUserSeting.getUserName());
/*  394 */     this.outMail.setMailDate(new Date());
/*  395 */     this.outMail.setReceiverAddresses(getAddressesToStr(this.outMail
/*  396 */       .getReceiverNames()));
/*  397 */     this.outMail
/*  398 */       .setReceiverNames(
/*  399 */       getNamesToStr(this.outMail.getReceiverNames()));
/*  400 */     this.outMail.setcCAddresses(getAddressesToStr(this.outMail.getcCNames()));
/*  401 */     this.outMail.setcCNames(getNamesToStr(this.outMail.getcCNames()));
/*  402 */     this.outMail.setReadFlag(new Short("0"));
/*  403 */     this.outMail.setReplyFlag(new Short("0"));
/*      */ 
/*  406 */     Set outMailFiles = new HashSet();
/*  407 */     String[] fileIds = this.outMail.getFileIds().split(",");
/*  408 */     for (String id : fileIds) {
/*  409 */       if ((!id.equals("")) && (!id.equals("null"))) {
/*  410 */         outMailFiles.add((FileAttach)this.fileAttachService.get(new Long(id)));
/*      */       }
/*      */     }
/*  413 */     this.outMail.setOutMailFiles(outMailFiles);
/*      */     try
/*      */     {
/*  417 */       if ((opt != null) && (opt.trim().equals("attach"))) {
/*  418 */         this.outMailFolder = ((OutMailFolder)this.outMailFolderService.get(Long.valueOf(FOLDER_ID_DRAFT)));
/*  419 */         this.outMail.setOutMailFolder(this.outMailFolder);
/*  420 */         this.outMailService.save(this.outMail);
/*      */       }
/*      */       else {
/*  423 */         OutMail newOutMail = new OutMail();
/*  424 */         BeanUtil.copyNotNullProperties(newOutMail, this.outMail);
/*  425 */         if ((newOutMail.getContent() == null) || 
/*  426 */           (newOutMail.getContent().equals(""))) {
/*  427 */           newOutMail.setContent("\t\t");
/*      */         }
/*      */ 
/*  430 */         newOutMail.setMailId(null);
/*  431 */         this.outMailFolder = ((OutMailFolder)this.outMailFolderService.get(Long.valueOf(FOLDER_ID_SEND)));
/*  432 */         newOutMail.setOutMailFolder(this.outMailFolder);
/*      */ 
/*  435 */         List reviceList = getEMailStrToList(
/*  436 */           newOutMail.getReceiverAddresses(), newOutMail
/*  437 */           .getReceiverNames());
/*  438 */         List ccList = null;
/*      */ 
/*  440 */         if ((newOutMail.getcCAddresses() != null) && 
/*  441 */           (!newOutMail.getcCAddresses().trim().equals("")) && 
/*  442 */           (!newOutMail.getcCAddresses().trim().equals("null")) && 
/*  443 */           (newOutMail.getcCAddresses().length() > 2))
/*      */         {
/*  445 */           ccList = getEMailStrToList(
/*  446 */             newOutMail.getcCAddresses(), newOutMail
/*  447 */             .getcCNames());
/*      */         }
/*      */ 
/*  450 */         send(newOutMail, reviceList, ccList, this.outMailUserSeting);
/*  451 */         this.outMailService.save(newOutMail);
/*      */       }
/*      */     }
/*      */     catch (Exception e)
/*      */     {
/*  456 */       e.printStackTrace();
/*  457 */       setJsonString("{failure:true,msg:'发送邮件失败!请检查书写的邮件格式是否正确!!'}");
/*  458 */       return "success";
/*      */     }
/*  460 */     this.logger.debug("save end...");
/*  461 */     return "success";
/*      */   }
/*      */ 
/*      */   public String attach()
/*      */   {
/*  471 */     String fileIds = getRequest().getParameter("fileIds");
/*  472 */     String filenames = getRequest().getParameter("filenames");
/*  473 */     setOutMail((OutMail)this.outMailService.get(this.mailId));
/*  474 */     Set mailAttachs = this.outMail.getOutMailFiles();
/*  475 */     FileAttach remove = (FileAttach)this.fileAttachService.get(this.fileId);
/*  476 */     delPhysicalFile(remove);
/*  477 */     mailAttachs.remove(remove);
/*  478 */     this.outMail.setOutMailFiles(mailAttachs);
/*  479 */     this.outMail.setFileIds(fileIds);
/*  480 */     this.outMail.setFileNames(filenames);
/*  481 */     this.outMailService.save(this.outMail);
/*  482 */     this.fileAttachService.remove(this.fileId);
/*  483 */     return "success";
/*      */   }
/*      */ 
/*      */   public String move()
/*      */   {
/*  491 */     StringBuffer msg = new StringBuffer("{");
/*  492 */     if ((this.outMailIds != null) && (this.outMailIds.length() > 0) && (this.folderId != null)) {
/*  493 */       OutMailFolder moveToFolder = (OutMailFolder)this.outMailFolderService.get(
/*  494 */         new Long(this.folderId.longValue()));
/*      */ 
/*  497 */       String[] ids = this.outMailIds.split(",");
/*  498 */       boolean moveSuccess = true;
/*  499 */       if ((ids != null) && (ids.length > 0))
/*      */       {
/*  501 */         if (moveSuccess)
/*      */         {
/*  503 */           for (String id : ids) {
/*  504 */             if (!"".equals(id)) {
/*  505 */               this.outMail = ((OutMail)this.outMailService.get(new Long(id)));
/*  506 */               this.outMail.setOutMailFolder(moveToFolder);
/*  507 */               this.outMailService.save(this.outMail);
/*      */             }
/*      */           }
/*      */ 
/*  511 */           msg.append("success:true}");
/*  512 */           setJsonString(msg.toString());
/*      */         }
/*      */         else {
/*  515 */           msg.append("failure:true}");
/*  516 */           setJsonString(msg.toString());
/*      */         }
/*      */       }
/*      */     } else {
/*  520 */       msg.append("msg:'请选择要移动的邮件及文件夹!'");
/*  521 */       msg.append(",failure:true}");
/*  522 */       setJsonString(msg.toString());
/*      */     }
/*      */ 
/*  525 */     return "success";
/*      */   }
/*      */ 
/*      */   public String opt()
/*      */   {
/*  532 */     setOutMail((OutMail)this.outMailService.get(this.mailId));
/*  533 */     String opt = getRequest().getParameter("opt");
/*      */ 
/*  535 */     this.outMail.setReadFlag(Short.valueOf(HAVE_READ));
/*  536 */     if ((opt != null) && (opt.trim().equals("回复")))
/*      */     {
/*  538 */       this.outMail.setReplyFlag(HAVE_REPLY);
/*      */     }
/*      */ 
/*  541 */     this.outMailService.save(this.outMail);
/*      */ 
/*  543 */     StringBuffer newSubject = new StringBuffer(
/*  544 */       "<br><br><br><br><br><br><br><hr>");
/*  545 */     newSubject.append("<br>----<strong>" + opt + "邮件</strong>----");
/*  546 */     newSubject
/*  547 */       .append("<br><strong>发件人</strong>:" + this.outMail.getSenderName());
/*  548 */     newSubject.append("<br><strong>发送时间</strong>:" + this.outMail.getMailDate());
/*  549 */     newSubject.append("<br><strong>收件人</strong>:" + 
/*  550 */       this.outMail.getReceiverNames());
/*  551 */     String copyToNames = this.outMail.getcCNames();
/*  552 */     if ((!"".equals(copyToNames)) && (copyToNames != null)) {
/*  553 */       newSubject.append("<br><strong>抄送人</strong>:" + copyToNames);
/*      */     }
/*  555 */     newSubject.append("<br><strong>主题</strong>:" + this.outMail.getTitle());
/*  556 */     newSubject.append("<br><strong>内容</strong>:<br><br>" + 
/*  557 */       this.outMail.getContent());
/*  558 */     this.outMail.setContent(newSubject.toString());
/*  559 */     this.outMail.setTitle(opt + ":" + this.outMail.getTitle());
/*      */ 
/*  561 */     List list = new ArrayList();
/*  562 */     list.add(this.outMail);
/*      */ 
/*  564 */     setJsonString(getOutMailJsonStr(list, 1));
/*  565 */     return "success";
/*      */   }
/*      */ 
/*      */   protected String getOutMailJsonStr(List<OutMail> list, int totalCounts)
/*      */   {
/*  576 */     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
/*  577 */     StringBuffer buff = new StringBuffer("{success:true,\"totalCounts\":")
/*  578 */       .append(totalCounts).append(",result:[");
/*  579 */     for (OutMail mailTemp : list)
/*      */     {
/*  581 */       buff.append("{")
/*  583 */         .append("\"mailId\":").append(mailTemp.getMailId())
/*  585 */         .append(",\"title\":").append(
/*  586 */         "\"" + changSpecialCharacters(mailTemp.getTitle()) + "\"")
/*  588 */         .append(",\"content\":")
/*  589 */         .append(
/*  590 */         "\"" + 
/*  591 */         changSpecialCharacters(mailTemp
/*  592 */         .getContent()) + "\"")
/*  594 */         .append(",\"senderAddresses\":").append(
/*  595 */         "\"" + mailTemp.getSenderAddresses() + "\"")
/*  597 */         .append(",\"receiverAddresses\":").append(
/*  598 */         "\"" + mailTemp.getReceiverAddresses() + "\"")
/*  600 */         .append(",\"cCAddresses\":").append(
/*  601 */         "\"" + mailTemp.getcCAddresses() + "\"")
/*  603 */         .append(",\"cCNames\":").append(
/*  604 */         "\"" + 
/*  605 */         changSpecialCharacters(mailTemp
/*  606 */         .getcCNames()) + "\"")
/*  608 */         .append(",\"receiverNames\":").append(
/*  609 */         "\"" + 
/*  610 */         changSpecialCharacters(mailTemp
/*  611 */         .getReceiverNames()) + "\"")
/*  613 */         .append(",\"senderName\":").append(
/*  614 */         "\"" + 
/*  615 */         changSpecialCharacters(mailTemp
/*  616 */         .getSenderName()) + "\"")
/*  618 */         .append(",\"mailDate\":").append(
/*  619 */         "\"" + df.format(mailTemp.getMailDate()) + "\"")
/*  621 */         .append(",\"readFlag\":").append(mailTemp.getReadFlag())
/*  623 */         .append(",\"replyFlag\":").append(mailTemp.getReplyFlag())
/*  625 */         .append(",\"fileIds\":").append(
/*  626 */         "\"" + mailTemp.getFileIds() + "\"")
/*  628 */         .append(",\"fileNames\":").append(
/*  629 */         "\"" + 
/*  630 */         changSpecialCharacters(mailTemp
/*  631 */         .getFileNames()) + "\"");
/*      */ 
/*  633 */       buff.append("},");
/*      */     }
/*  635 */     if (list.size() > 0) {
/*  636 */       buff.deleteCharAt(buff.length() - 1);
/*      */     }
/*  638 */     buff.append("]}");
/*      */ 
/*  640 */     this.jsonString = buff.toString();
/*  641 */     return this.jsonString;
/*      */   }
/*      */ 
/*      */   protected static String changSpecialCharacters(String special)
/*      */   {
/*  651 */     if (special == null) {
/*  652 */       return "";
/*      */     }
/*      */ 
/*  655 */     special = special.replace("\"", "'").replace("\t", "\\t").replace(
/*  656 */       "\n", "\\n").replace(":", "\\:").replace("[", "\\[")
/*  657 */       .replace("]", "\\]").replace("{", "\\{")
/*  658 */       .replace("}", "\\}").replace(",", "\\,").replace("\r", 
/*  659 */       "\\r").replace("null", "");
/*      */ 
/*  662 */     return special;
/*      */   }
/*      */ 
/*      */   protected void send(OutMail outMail_, List reviceList, List ccList, OutMailUserSeting _outMailUserSeting) throws Exception
/*      */   {
/*  667 */     this.logger.debug("send start...");
/*      */ 
/*  669 */     String user = _outMailUserSeting.getMailAddress();
/*  670 */     String pass = _outMailUserSeting.getMailPass();
/*  671 */     String smtpHost = _outMailUserSeting.getSmtpHost();
/*  672 */     String smtpPort = _outMailUserSeting.getSmtpPort();
/*  673 */     String smtpAuth = "true";
/*  674 */     this.logger.debug(_outMailUserSeting);
/*      */ 
/*  676 */     Properties props = new Properties();
/*  677 */     props.setProperty("mail.smtp.host", smtpHost);
/*  678 */     props.setProperty("mail.smtp.port", smtpPort);
/*  679 */     props.put("mail.smtp.auth", smtpAuth);
/*      */ 
/*  681 */     props.setProperty("mail.smtp.socketFactory.fallback", "false");
/*  682 */     props.setProperty("mail.smtp.socketFactory.port", smtpPort);
/*      */ 
/*  684 */     File cert = null;
/*      */ 
/*  686 */     cert = CertUtil.get(smtpHost, Integer.parseInt(smtpPort));
/*      */ 
/*  688 */     if (cert != null) {
/*  689 */       this.logger.debug("ssl connection...");
/*  690 */       Security.addProvider(new Provider());
/*  691 */       String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
/*  692 */       props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
/*  693 */       System.setProperty("javax.net.ssl.trustStore", cert
/*  694 */         .getAbsolutePath());
/*  695 */       props.put("javax.net.ssl.trustStore", cert.getAbsolutePath());
/*      */     } else {
/*  697 */       this.logger.debug("plaintext connection or tls connection...");
/*  698 */       props.put("mail.smtp.starttls.enable", "true");
/*      */     }
/*      */ 
/*  701 */     final String username = user;
/*  702 */     final String password = pass;
/*      */ 
/*  704 */     Session session = Session.getInstance(props, new Authenticator() {
/*      */       protected PasswordAuthentication getPasswordAuthentication() {
/*  706 */         return new PasswordAuthentication(username, password);
/*      */       }
/*      */     });
/*  710 */     this.logger.debug("connetion session:" + session);
/*  711 */     EmailAddress sender = new EmailAddress(_outMailUserSeting
/*  712 */       .getMailAddress(), _outMailUserSeting.getUserName());
/*  713 */     BodyPart contentPart = new MimeBodyPart();
/*      */ 
/*  715 */     contentPart.setHeader("Content-Transfer-Encoding", "base64");
/*  716 */     contentPart
/*  717 */       .setContent(outMail_.getContent(), "text/html;charset=utf-8");
/*      */ 
/*  719 */     MimeMessage message = new MimeMessage(session);
/*  720 */     Multipart multipart = new MimeMultipart();
/*  721 */     message.setSubject(outMail_.getTitle(), "utf-8");
/*      */ 
/*  723 */     message.setText("utf-8", "utf-8");
/*  724 */     message.setSentDate(outMail_.getMailDate() == null ? new Date() : 
/*  725 */       outMail_.getMailDate());
/*      */ 
/*  727 */     multipart.addBodyPart(contentPart);
/*      */ 
/*  730 */     message.setFrom(sender.toInternetAddress());
/*      */ 
/*  733 */     InternetAddress[] address = getAddressByType(reviceList);
/*  734 */     if (address != null) {
/*  735 */       message.addRecipients(Message.RecipientType.TO, address);
/*      */     }
/*  737 */     if ((ccList != null) && (ccList.size() > 0)) {
/*  738 */       address = getAddressByType(ccList);
/*  739 */       if (address != null) {
/*  740 */         message.addRecipients(Message.RecipientType.CC, address);
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  748 */     if ((outMail_.getFileIds() != null) && (outMail_.getFileIds().length() > 0)) {
/*  749 */       String fileids = outMail_.getFileIds();
/*  750 */       String[] fileid_s = fileids.split(",");
/*  751 */       for (int i = 0; i < fileid_s.length; i++) {
/*  752 */         if ((!fileid_s[i].equals("")) && (!fileid_s[i].equals("null"))) {
/*  753 */           FileAttach f__attach = (FileAttach)this.fileAttachService.get(
/*  754 */             new Long(fileid_s[i]));
/*  755 */           if (f__attach == null)
/*      */             continue;
/*  757 */           File file = new File(FILE_PATH_ROOT + 
/*  758 */             f__attach.getFilePath());
/*      */ 
/*  760 */           BodyPart messageBodyPart = new MimeBodyPart();
/*  761 */           DataSource source = new FileDataSource(file);
/*  762 */           messageBodyPart.setDataHandler(new DataHandler(source));
/*  763 */           messageBodyPart.setFileName(MimeUtility.encodeWord(
/*  764 */             f__attach.getFileName(), "UTF-8", "Q"));
/*  765 */           multipart.addBodyPart(messageBodyPart);
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  773 */     message.setContent(multipart);
/*  774 */     if (sendType == null) {
/*  775 */       sendType = "smtp";
/*      */     }
/*      */ 
/*  778 */     Transport transport = session.getTransport(sendType);
/*  779 */     transport.connect(_outMailUserSeting.getSmtpHost().toString(), 
/*  780 */       username, password);
/*  781 */     Transport.send(message);
/*  782 */     transport.close();
/*  783 */     this.logger.debug("send end");
/*      */   }
/*      */ 
/*      */   protected static InternetAddress[] getAddressByType(List<EmailAddress> list)
/*      */     throws Exception
/*      */   {
/*  792 */     if (list != null) {
/*  793 */       InternetAddress[] address = new InternetAddress[list.size()];
/*  794 */       for (int i = 0; i < list.size(); i++)
/*      */       {
/*  796 */         if (((EmailAddress)list.get(i)).toInternetAddress() != null) {
/*  797 */           address[i] = ((EmailAddress)list.get(i)).toInternetAddress();
/*      */         }
/*      */       }
/*      */ 
/*  801 */       return address;
/*      */     }
/*  803 */     return null;
/*      */   }
/*      */ 
/*      */   protected static String getAddressesToStr(String str)
/*      */   {
/*  810 */     String address = "";
/*  811 */     if ((str != null) && (str.length() > 0) && (str.indexOf(";") >= 0)) {
/*  812 */       String[] emails = str.split(";");
/*  813 */       for (int i = 0; i < emails.length; i++)
/*      */       {
/*  815 */         if ((emails[i].length() > 1) && (emails[i].indexOf("<") >= 0) && 
/*  816 */           (emails[i].indexOf(">") > 0)) {
/*  817 */           String[] email = emails[i].split("<");
/*      */ 
/*  819 */           address = address + email[1].substring(0, email[1].length() - 1) + 
/*  820 */             ",";
/*      */         }
/*      */         else {
/*  823 */           address = address + emails[i] + ",";
/*      */         }
/*      */       }
/*      */ 
/*      */     }
/*  828 */     else if ((str != null) && (str.indexOf("<") >= 0) && (str.indexOf(">") > 0)) {
/*  829 */       String[] email = str.split("<");
/*      */ 
/*  831 */       address = address + email[1].substring(0, email[1].length() - 1) + ",";
/*      */     }
/*      */     else {
/*  834 */       address = str + ",";
/*      */     }
/*      */ 
/*  838 */     if ((address != null) && (address.length() > 1)) {
/*  839 */       address = address.substring(0, address.length() - 1);
/*      */     }
/*  841 */     return address;
/*      */   }
/*      */ 
/*      */   protected static String getNamesToStr(String str)
/*      */   {
/*  849 */     String name = "";
/*  850 */     if ((str != null) && (str.length() > 0) && (str.indexOf(";") >= 0)) {
/*  851 */       String[] emails = str.split(";");
/*  852 */       for (int i = 0; i < emails.length; i++) {
/*  853 */         if ((emails[i].indexOf("<") >= 0) && (emails[i].indexOf(">") > 0)) {
/*  854 */           String[] email = emails[i].split("<");
/*  855 */           name = name + email[0] + ",";
/*      */         }
/*      */         else {
/*  858 */           name = name + emails[i] + ",";
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*      */     }
/*  865 */     else if ((str != null) && (str.indexOf("<") >= 0) && (str.indexOf(">") > 0)) {
/*  866 */       String[] email = str.split("<");
/*  867 */       name = name + email[0] + ",";
/*      */     }
/*      */     else {
/*  870 */       name = str + ",";
/*      */     }
/*      */ 
/*  875 */     if ((name != null) && (name.length() > 1)) {
/*  876 */       name = name.substring(0, name.length() - 1);
/*      */     }
/*  878 */     if (name == null)
/*  879 */       name = " ";
/*  880 */     return name;
/*      */   }
/*      */ 
/*      */   protected List<EmailAddress> getEMailStrToList(String addresses, String names)
/*      */   {
/*  890 */     List list = new ArrayList();
/*      */ 
/*  893 */     if ((addresses != null) && (addresses.length() > 1)) {
/*  894 */       String[] revice_a = addresses.split(",");
/*  895 */       if ((names != null) && (names.length() > 1)) {
/*  896 */         String[] revice_n = names.split(",");
/*      */ 
/*  898 */         for (int i = 0; i < revice_a.length; i++)
/*  899 */           if ((revice_a[i] != null) && (revice_a[i].length() > 1) && 
/*  900 */             (revice_a[i].indexOf("@") > 0)) {
/*  901 */             if (revice_n.length > i) {
/*  902 */               EmailAddress add = new EmailAddress(revice_a[i]
/*  903 */                 .trim(), revice_n[i]);
/*  904 */               list.add(add);
/*      */             } else {
/*  906 */               EmailAddress add = new EmailAddress(revice_a[i]
/*  907 */                 .trim(), revice_a[i].trim());
/*  908 */               list.add(add);
/*      */             }
/*      */           } else {
/*  911 */             setJsonString("{failure:true,msg:'收件人地址有误!'}");
/*  912 */             return null;
/*      */           }
/*      */       }
/*      */       else
/*      */       {
/*  917 */         for (int i = 0; i < revice_a.length; i++) {
/*  918 */           if ((revice_a[i] != null) && (revice_a[i].length() > 1) && 
/*  919 */             (revice_a[i].indexOf("@") > 0)) {
/*  920 */             EmailAddress add = new EmailAddress(revice_a[i].trim(), 
/*  921 */               revice_a[i].trim());
/*  922 */             list.add(add);
/*      */           } else {
/*  924 */             return null;
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     else
/*      */     {
/*  931 */       setJsonString("{failure:true,msg:'收件人不能为空!'}");
/*  932 */       return null;
/*      */     }
/*      */ 
/*  935 */     return list;
/*      */   }
/*      */ 
/*      */   public String fetch()
/*      */   {
/*  947 */     this.logger.debug("fectch start...");
/*  948 */     this.outMailUserSeting = this.outMailUserSetingService.getByLoginId(
/*  949 */       ContextUtil.getCurrentUserId());
/*      */ 
/*  951 */     if (this.outMailUserSeting != null)
/*      */     {
/*  953 */       this.logger.debug(this.outMailUserSeting);
/*      */ 
/*  955 */       Properties props = new Properties();
/*      */ 
/*  957 */       props.setProperty("mail.pop3.socketFactory.fallback", "false");
/*  958 */       props.setProperty("mail.pop3.port", this.outMailUserSeting.getPopPort());
/*  959 */       props.setProperty("mail.pop3.socketFactory.port", this.outMailUserSeting
/*  960 */         .getPopPort());
/*      */ 
/*  963 */       File cert = null;
/*      */ 
/*  965 */       cert = CertUtil.get(this.outMailUserSeting.getPopHost(), 
/*  966 */         Integer.parseInt(this.outMailUserSeting.getPopPort()));
/*      */ 
/*  968 */       if (cert != null)
/*      */       {
/*  970 */         Security.addProvider(new Provider());
/*  971 */         this.logger.debug("ssl connection...");
/*  972 */         String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
/*  973 */         props.setProperty("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
/*  974 */         System.setProperty("javax.net.ssl.trustStore", cert
/*  975 */           .getAbsolutePath());
/*  976 */         props.put("javax.net.ssl.trustStore", cert.getAbsolutePath());
/*      */       } else {
/*  978 */         this.logger.debug("plaintext connection or tls connection...");
/*  979 */         props.put("mail.smtp.starttls.enable", "true");
/*      */       }
/*      */ 
/*  982 */       Session session = Session.getInstance(props, null);
/*      */ 
/*  984 */       URLName urln = new URLName("pop3", this.outMailUserSeting.getPopHost(), 
/*  985 */         Integer.parseInt(this.outMailUserSeting.getPopPort()), null, 
/*  986 */         this.outMailUserSeting.getMailAddress(), this.outMailUserSeting
/*  987 */         .getMailPass());
/*      */ 
/*  989 */       Store store = null;
/*  990 */       POP3Folder inbox = null;
/*      */ 
/*  992 */       OutMailFolder fectchFolder = 
/*  993 */         (OutMailFolder)this.outMailFolderService
/*  993 */         .get(Long.valueOf(FOLDER_ID_RECEIVE));
/*      */       try
/*      */       {
/*  996 */         store = session.getStore(urln);
/*  997 */         store.connect();
/*  998 */         inbox = (POP3Folder)store.getFolder("INBOX");
/*  999 */         inbox.open(1);
/* 1000 */         FetchProfile profile = new FetchProfile();
/*      */ 
/* 1002 */         profile.add(UIDFolder.FetchProfileItem.UID);
/* 1003 */         Message[] messages = inbox.getMessages();
/* 1004 */         inbox.fetch(messages, profile);
/*      */ 
/* 1007 */         Map uidMail = this.outMailService.getUidByUserId(
/* 1008 */           ContextUtil.getCurrentUserId());
/*      */ 
/* 1012 */         int count = messages.length;
/* 1013 */         this.logger.debug("mail counts :\t" + count);
/*      */ 
/* 1015 */         for (int i = 0; i < count; i++) {
/*      */           try {
/* 1017 */             if (uidMail.get(inbox.getUID(messages[i])) != null) continue;
/*      */             try {
/* 1019 */               this.logger.debug("");
/* 1020 */               this.logger.debug("开始接收邮件:\t" + 
/* 1021 */                 messages[i].getSubject());
/* 1022 */               OutMail fetchOutMail = new OutMail();
/*      */ 
/* 1024 */               fetchOutMail.setUid(inbox.getUID(messages[i]));
/*      */ 
/* 1027 */               String from = messages[i].getFrom()[0]
/* 1028 */                 .toString();
/* 1029 */               InternetAddress ia = new InternetAddress(from);
/* 1030 */               String senerAddress = ia.getAddress();
/* 1031 */               if ((senerAddress == null) || 
/* 1032 */                 (senerAddress.equals(""))) {
/* 1033 */                 senerAddress = "\t";
/*      */               }
/* 1035 */               fetchOutMail.setSenderAddresses(senerAddress);
/* 1036 */               fetchOutMail.setSenderName(ia.getPersonal());
/*      */ 
/* 1039 */               InternetAddress[] ia_re = (InternetAddress[])null;
/*      */               try {
/* 1041 */                 ia_re = (InternetAddress[])messages[i]
/* 1042 */                   .getRecipients(Message.RecipientType.TO);
/*      */               } catch (AddressException e) {
/* 1044 */                 e.printStackTrace();
/*      */               }
/* 1046 */               String re_a = "\t\t";
/* 1047 */               String re_n = "\t\t";
/*      */ 
/* 1049 */               if ((ia_re != null) && (ia_re.length > 0))
/*      */               {
/* 1051 */                 for (int k = 0; k < ia_re.length; k++) {
/* 1052 */                   re_a = re_a + ia_re[k].getAddress() + ",";
/* 1053 */                   if (ia_re[k].getPersonal() != null)
/*      */                   {
/* 1055 */                     if (!ia_re[k].getPersonal()
/* 1055 */                       .equals("")) {
/* 1056 */                       re_n = re_n + ia_re[k].getPersonal() + 
/* 1057 */                         ",";
/*      */ 
/* 1056 */                       continue;
/*      */                     }
/*      */                   }
/* 1059 */                   re_n = re_n + ia_re[k].getAddress() + ",";
/*      */                 }
/*      */ 
/* 1063 */                 if ((re_a != null) && (re_a.length() > 1)) {
/* 1064 */                   re_a = re_a.substring(0, 
/* 1065 */                     re_a.length() - 1);
/*      */                 }
/* 1067 */                 if ((re_n != null) && (re_n.length() > 1)) {
/* 1068 */                   re_n = re_n.substring(0, 
/* 1069 */                     re_n.length() - 1);
/*      */                 }
/*      */ 
/*      */               }
/*      */ 
/* 1074 */               fetchOutMail.setReceiverAddresses(re_a);
/* 1075 */               fetchOutMail.setReceiverNames(re_n);
/*      */ 
/* 1078 */               InternetAddress[] ia_cc = (InternetAddress[])null;
/*      */               try {
/* 1080 */                 ia_cc = (InternetAddress[])messages[i]
/* 1081 */                   .getRecipients(Message.RecipientType.CC);
/*      */               } catch (AddressException e) {
/* 1083 */                 e.printStackTrace();
/*      */               }
/* 1085 */               String cc_a = "\t";
/* 1086 */               String cc_n = "\t";
/* 1087 */               if ((ia_cc != null) && (ia_cc.length > 0))
/*      */               {
/* 1089 */                 for (int k = 0; k < ia_cc.length; k++) {
/* 1090 */                   cc_a = cc_a + ia_cc[k].getAddress() + ",";
/* 1091 */                   cc_n = cc_n + ia_cc[k].getPersonal() + ",";
/*      */                 }
/* 1093 */                 if ((cc_a != null) && (cc_a.length() > 1)) {
/* 1094 */                   cc_a = cc_a.substring(0, 
/* 1095 */                     cc_a.length() - 1);
/*      */                 }
/* 1097 */                 if ((cc_n != null) && (cc_n.length() > 1)) {
/* 1098 */                   cc_n = cc_n.substring(0, 
/* 1099 */                     cc_n.length() - 1);
/*      */                 }
/*      */ 
/*      */               }
/*      */ 
/* 1104 */               fetchOutMail.setcCAddresses(cc_a);
/* 1105 */               fetchOutMail.setcCNames(cc_n);
/*      */ 
/* 1108 */               InternetAddress[] ia_bcc = (InternetAddress[])null;
/*      */               try {
/* 1110 */                 ia_bcc = (InternetAddress[])messages[i]
/* 1111 */                   .getRecipients(Message.RecipientType.BCC);
/*      */               } catch (AddressException e) {
/* 1113 */                 e.printStackTrace();
/*      */               }
/* 1115 */               String bcc_a = "\t";
/* 1116 */               String bcc_n = "\t";
/* 1117 */               if ((ia_bcc != null) && (ia_bcc.length > 0))
/*      */               {
/* 1119 */                 for (int k = 0; k < ia_bcc.length; k++) {
/* 1120 */                   bcc_a = bcc_a + ia_bcc[k].getAddress() + ",";
/* 1121 */                   bcc_n = bcc_n + ia_bcc[k].getPersonal() + ",";
/*      */                 }
/* 1123 */                 if ((bcc_a != null) && (bcc_a.length() > 1)) {
/* 1124 */                   bcc_a = bcc_a.substring(0, bcc_a
/* 1125 */                     .length() - 1);
/*      */                 }
/* 1127 */                 if ((bcc_n != null) && (bcc_n.length() > 1)) {
/* 1128 */                   bcc_n = bcc_n.substring(0, bcc_n
/* 1129 */                     .length() - 1);
/*      */                 }
/*      */ 
/*      */               }
/*      */ 
/* 1134 */               fetchOutMail.setbCCAddresses(bcc_a);
/* 1135 */               fetchOutMail.setbCCAnames(bcc_n);
/*      */ 
/* 1138 */               fetchOutMail
/* 1139 */                 .setTitle(messages[i].getSubject());
/*      */ 
/* 1142 */               Date sentDate = null;
/* 1143 */               if (messages[i].getSentDate() != null)
/* 1144 */                 sentDate = messages[i].getSentDate();
/*      */               else {
/* 1146 */                 sentDate = new Date();
/*      */               }
/* 1148 */               fetchOutMail.setMailDate(sentDate);
/*      */ 
/* 1152 */               String content = getMailContent(messages[i]);
/* 1153 */               if ((content == null) || (content.equals(""))) {
/* 1154 */                 content = "\t";
/*      */               }
/*      */ 
/* 1157 */               fetchOutMail.setContent(content);
/*      */ 
/* 1161 */               Set outMailFiles = saveFileForFetch(messages[i]);
/* 1162 */               fetchOutMail.setOutMailFiles(outMailFiles);
/*      */ 
/* 1166 */               fetchOutMail.setReadFlag(new Short("0"));
/* 1167 */               fetchOutMail.setReplyFlag(new Short("0"));
/*      */ 
/* 1170 */               fetchOutMail.setOutMailFolder(fectchFolder);
/*      */ 
/* 1172 */               fetchOutMail.setUserId(
/* 1173 */                 ContextUtil.getCurrentUserId());
/*      */ 
/* 1176 */               this.outMailService.save(fetchOutMail);
/*      */ 
/* 1178 */               this.outMail = ((OutMail)this.outMailService.get(fetchOutMail
/* 1179 */                 .getMailId()));
/*      */ 
/* 1181 */               Set<FileAttach> outMailFiles_new = this.outMail
/* 1182 */                 .getOutMailFiles();
/*      */ 
/* 1185 */               String f_id = "";
/* 1186 */               String f_name = "";
/* 1187 */               if ((outMailFiles_new != null) && 
/* 1188 */                 (outMailFiles_new.size() > 0)) {
/* 1189 */                 for (FileAttach f : outMailFiles_new) {
/* 1190 */                   f_id = f_id + f.getFileId().toString() + ",";
/* 1191 */                   f_name = f_name + f.getFileName().toString() + 
/* 1192 */                     ",";
/*      */                 }
/* 1194 */                 if (f_id.length() > 1) {
/* 1195 */                   f_id = f_id.substring(0, 
/* 1196 */                     f_id.length() - 1);
/*      */                 }
/*      */ 
/* 1199 */                 if (f_name.length() > 1) {
/* 1200 */                   f_name = f_name.substring(0, f_name
/* 1201 */                     .length() - 1);
/*      */                 }
/*      */               }
/*      */ 
/* 1205 */               this.outMail.setFileIds(f_id);
/* 1206 */               this.outMail.setFileNames(f_name);
/* 1207 */               this.outMailService.save(this.outMail);
/* 1208 */               this.logger.debug("接收邮件成功:\t" + 
/* 1209 */                 messages[i].getSubject());
/*      */             }
/*      */             catch (IOException e) {
/* 1212 */               e.printStackTrace();
/*      */             } finally {
/* 1214 */               System.gc();
/*      */             }
/*      */ 
/*      */           }
/*      */           catch (MessagingException e1)
/*      */           {
/* 1220 */             e1.printStackTrace();
/*      */           }
/*      */         }
/*      */ 
/*      */       }
/*      */       catch (NoSuchProviderException e)
/*      */       {
/* 1227 */         e.printStackTrace();
/*      */         try
/*      */         {
/* 1234 */           inbox.close(false);
/*      */         } catch (Exception ex) {
/* 1236 */           ex.printStackTrace();
/*      */         }
/*      */         try {
/* 1239 */           store.close();
/*      */         } catch (Exception ex) {
/* 1241 */           ex.printStackTrace();
/*      */         }
/*      */       }
/*      */       catch (MessagingException e)
/*      */       {
/* 1230 */         e.printStackTrace();
/*      */         try
/*      */         {
/* 1234 */           inbox.close(false);
/*      */         } catch (Exception ex) {
/* 1236 */           e.printStackTrace();
/*      */         }
/*      */         try {
/* 1239 */           store.close();
/*      */         } catch (Exception ex) {
/* 1241 */           e.printStackTrace();
/*      */         }
/*      */       }
/*      */       finally
/*      */       {
/*      */         try
/*      */         {
/* 1234 */           inbox.close(false);
/*      */         } catch (Exception e) {
/* 1236 */           e.printStackTrace();
/*      */         }
/*      */         try {
/* 1239 */           store.close();
/*      */         } catch (Exception e) {
/* 1241 */           e.printStackTrace();
/*      */         }
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1247 */     setJsonString("{success:true,msg:'收取邮件完成！'}");
/* 1248 */     this.logger.debug("fectch end...");
/* 1249 */     return "success";
/*      */   }
/*      */ 
/*      */   protected boolean delPhysicalFile(FileAttach fileAttach)
/*      */   {
/* 1257 */     String fp_p = FILE_PATH_ROOT;
/* 1258 */     String fp_full = fp_p + fileAttach.getFilePath();
/* 1259 */     File del_f = new File(fp_full);
/* 1260 */     if (del_f.delete())
/* 1261 */       this.logger.info("删除文件：" + fp_full);
/*      */     else {
/* 1263 */       this.logger.info("文件不存在：" + fp_full);
/*      */     }
/* 1265 */     return true;
/*      */   }
/*      */ 
/*      */   protected static String decodeText(String text)
/*      */   {
/*      */     try
/*      */     {
/* 1278 */       if (text == null)
/* 1279 */         return "";
/* 1280 */       if ((text.startsWith("=?GB")) || (text.startsWith("=?gb")))
/* 1281 */         text = MimeUtility.decodeText(text);
/* 1282 */       else if ((text.startsWith("=?ISO-8859-1")) || 
/* 1283 */         (text.startsWith("=?iso-8859-1")))
/* 1284 */         text = MimeUtility.decodeText(text);
/*      */       else
/* 1286 */         text = new String(text.getBytes("ISO8859_1"));
/*      */     } catch (Exception e) {
/* 1288 */       System.out.println("转换字附编号出错!");
/* 1289 */       e.printStackTrace();
/*      */     }
/* 1291 */     return text;
/*      */   }
/*      */ 
/*      */   protected String getMailContent(Part part)
/*      */   {
/* 1308 */     String userName = ContextUtil.getCurrentUser().getUsername();
/* 1309 */     StringBuffer sb = new StringBuffer();
/* 1310 */     sb.append(new String(""));
/*      */     try
/*      */     {
/* 1316 */       if ((part.isMimeType("text/plain")) || (part.isMimeType("text/html"))) {
/* 1317 */         sb.append(part.getContent());
/* 1318 */       } else if (part.isMimeType("multipart/*"))
/*      */       {
/* 1322 */         if (part.isMimeType("multipart/alternative")) {
/* 1323 */           Multipart mp = (Multipart)part.getContent();
/* 1324 */           int index = 0;
/* 1325 */           if (mp.getCount() > 1) {
/* 1326 */             index = 1;
/*      */           }
/*      */ 
/* 1331 */           Part tmp = mp.getBodyPart(index);
/* 1332 */           sb.append(tmp.getContent()); } else {
/* 1333 */           if (part.isMimeType("multipart/related"))
/*      */           {
/* 1337 */             Multipart mp = (Multipart)part.getContent();
/* 1338 */             Part tmp = mp.getBodyPart(0);
/* 1339 */             String body = getMailContent(tmp);
/* 1340 */             int count = mp.getCount();
/*      */ 
/* 1344 */             for (int k = 1; (count > 1) && (k < count); k++) {
/* 1345 */               Part att = mp.getBodyPart(k);
/* 1346 */               String attname = att.getFileName();
/* 1347 */               if (attname != null)
/* 1348 */                 attname = MimeUtility.decodeText(attname);
/*      */               else
/* 1350 */                 attname = "\t";
/*      */               try
/*      */               {
/* 1353 */                 File attFile = new File(FILE_PATH_ROOT, userName
/* 1354 */                   .concat(attname));
/*      */ 
/* 1356 */                 attFile.createNewFile();
/* 1357 */                 FileOutputStream fileoutput = new FileOutputStream(
/* 1358 */                   attFile);
/* 1359 */                 InputStream is = att.getInputStream();
/* 1360 */                 BufferedOutputStream outs = new BufferedOutputStream(
/* 1361 */                   fileoutput);
/* 1362 */                 byte[] b = new byte[att.getSize()];
/* 1363 */                 is.read(b);
/* 1364 */                 outs.write(b);
/* 1365 */                 outs.close();
/*      */               } catch (Exception e) {
/* 1367 */                 this.logger
/* 1368 */                   .error("Error occurred when to get the photos from server");
/*      */               }
/*      */ 
/* 1371 */               String[] Content_ID = att.getHeader("Content-ID");
/* 1372 */               if ((Content_ID != null) && (Content_ID.length > 0)) {
/* 1373 */                 String cid_name = Content_ID[0].replaceAll("<", "")
/* 1374 */                   .replaceAll(">", "");
/* 1375 */                 body = body.replaceAll("cid:" + cid_name, 
/* 1376 */                   FILE_PATH_ROOT.concat("/").concat(
/* 1377 */                   userName.concat(attname)));
/*      */               }
/*      */             }
/*      */ 
/* 1381 */             sb.append(body);
/* 1382 */             return sb.toString();
/*      */           }
/*      */ 
/* 1387 */           Part tmp = ((Multipart)part.getContent()).getBodyPart(0);
/* 1388 */           return getMailContent(tmp);
/*      */         }
/*      */       } else {
/* 1390 */         if (part.isMimeType("message/rfc822")) {
/* 1391 */           return getMailContent((Message)part.getContent());
/*      */         }
/*      */ 
/* 1396 */         Object obj = part.getContent();
/* 1397 */         if ((obj instanceof String)) {
/* 1398 */           sb.append(obj);
/*      */         } else {
/* 1400 */           Multipart mp = (Multipart)obj;
/* 1401 */           Part tmp = mp.getBodyPart(0);
/* 1402 */           return getMailContent(tmp);
/*      */         }
/*      */       }
/*      */     }
/*      */     catch (Exception e) {
/* 1407 */       e.printStackTrace();
/* 1408 */       return "解析正文错误!";
/*      */     }
/* 1410 */     return sb.toString();
/*      */   }
/*      */ 
/*      */   protected Set<FileAttach> saveFileForFetch(Message msg)
/*      */     throws IOException, MessagingException
/*      */   {
/* 1423 */     String contentType = msg.getContentType();
/*      */ 
/* 1425 */     if (contentType.startsWith("multipart/mixed")) {
/* 1426 */       SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
/*      */ 
/* 1428 */       Set outMailFiles = new HashSet();
/*      */ 
/* 1430 */       Multipart multipart = (Multipart)msg.getContent();
/*      */ 
/* 1434 */       int j = 0; for (int n = multipart.getCount(); j < n; j++)
/*      */       {
/* 1436 */         Part part = multipart.getBodyPart(j);
/*      */ 
/* 1443 */         String disposition = part.getDisposition();
/* 1444 */         if (disposition != null) {
/* 1445 */           FileAttach fileAttach = new FileAttach();
/* 1446 */           fileAttach.setCreatetime(new Date());
/* 1447 */           fileAttach.setCreator(ContextUtil.getCurrentUser()
/* 1448 */             .getFullname());
/* 1449 */           String fileName = part.getFileName();
/* 1450 */           if (fileName != null)
/* 1451 */             fileName = MimeUtility.decodeText(fileName);
/*      */           else {
/* 1453 */             fileName = "\t";
/*      */           }
/* 1455 */           String[] ext = fileName.split("\\.");
/* 1456 */           fileAttach.setFileName(fileName);
/* 1457 */           fileAttach.setExt(ext[(ext.length - 1)]);
/* 1458 */           fileAttach.setFileType("communicate/outmail/download");
/* 1459 */           fileAttach.setNote(String.valueOf(part.getSize()) + 
/* 1460 */             " bytes");
/*      */ 
/* 1462 */           String ym = df.format(new Date());
/* 1463 */           InputStream in = part.getInputStream();
/*      */ 
/* 1465 */           String fp_p = FILE_PATH_ROOT;
/* 1466 */           String fp_c = "communicate\\outmail\\download\\" + 
/* 1467 */             ContextUtil.getCurrentUser().getUsername() + "\\" + 
/* 1468 */             ym + "\\";
/* 1469 */           fp_p = fp_p.replace("\\", "/");
/* 1470 */           fp_c = fp_c.replace("\\", "/");
/*      */ 
/* 1472 */           String filePath = FileUtil.generateFilename(fileAttach
/* 1473 */             .getFileName());
/* 1474 */           filePath = filePath.substring(filePath.indexOf("/") + 1, 
/* 1475 */             filePath.length());
/*      */ 
/* 1477 */           fileAttach.setFilePath(fp_c + filePath.trim());
/* 1478 */           File f = new File(fp_p, fp_c);
/* 1479 */           if ((!f.exists()) && 
/* 1480 */             (!f.mkdirs())) {
/* 1481 */             this.logger.info("目录不存在，创建失败！");
/*      */           }
/*      */ 
/* 1484 */           String f_full_p = fp_p + fileAttach.getFilePath();
/* 1485 */           f_full_p = f_full_p.replace("\\", "/");
/*      */ 
/* 1487 */           FileOutputStream out = new FileOutputStream(
/* 1488 */             f_full_p);
/*      */           int data;
/* 1490 */           while ((data = in.read()) != -1)
/*      */           {
///*      */             int data;
/* 1491 */             out.write(data);
/*      */           }
/* 1493 */           in.close();
/* 1494 */           out.close();
/* 1495 */           outMailFiles.add(fileAttach);
/* 1496 */           this.logger.debug("附件:" + fileAttach.getFileName() + "," + fileAttach.getFilePath());
/*      */         }
/*      */       }
/*      */ 
/* 1500 */       return outMailFiles;
/*      */     }
/* 1502 */     return null;
/*      */   }
/*      */ 
/*      */   protected class EmailAddress
/*      */   {
/*      */     protected String address;
/*      */     protected String name;
/*      */ 
/*      */     public String getAddress()
/*      */     {
/*  185 */       return this.address;
/*      */     }
/*      */ 
/*      */     public void setAddress(String address) {
/*  189 */       this.address = address;
/*      */     }
/*      */ 
/*      */     public String getName() {
/*  193 */       return this.name;
/*      */     }
/*      */ 
/*      */     public void setName(String name) {
/*  197 */       this.name = name;
/*      */     }
/*      */ 
/*      */     public EmailAddress()
/*      */     {
/*      */     }
/*      */ 
/*      */     public EmailAddress(String address, String name) {
/*  205 */       this.address = address;
/*  206 */       this.name = name;
/*      */     }
/*      */ 
/*      */     public InternetAddress toInternetAddress() throws Exception {
/*  210 */       if ((this.name != null) && (!this.name.trim().equals(""))) {
/*  211 */         return new InternetAddress(this.address, MimeUtility.encodeWord(
/*  212 */           this.name, "utf-8", "Q"));
/*      */       }
/*  214 */       return new InternetAddress(this.address);
/*      */     }
/*      */   }
/*      */ }

/* Location:           E:\Workspace\Template Projects\estbpm-orcl\estbpm\WEB-INF\classes\
 * Qualified Name:     com.htsoft.est.action.communicate.OutMailAction
 * JD-Core Version:    0.6.0
 */