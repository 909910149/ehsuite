/*     */ package com.htsoft.oa.dao.system.impl;
/*     */ 
/*     */ import com.htsoft.core.dao.impl.BaseDaoImpl;
/*     */ import com.htsoft.core.util.ContextUtil;
/*     */ import com.htsoft.core.web.paging.PagingBean;
/*     */ import com.htsoft.oa.dao.system.FileAttachDao;
/*     */ import com.htsoft.oa.model.system.AppUser;
/*     */ import com.htsoft.oa.model.system.FileAttach;
/*     */ import java.io.File;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.hibernate.HibernateException;
/*     */ import org.hibernate.Query;
/*     */ import org.hibernate.Session;
/*     */ import org.springframework.orm.hibernate3.HibernateCallback;
/*     */ import org.springframework.orm.hibernate3.HibernateTemplate;
/*     */ 
/*     */ public class FileAttachDaoImpl extends BaseDaoImpl<FileAttach>
/*     */   implements FileAttachDao
/*     */ {
/*     */   public FileAttachDaoImpl()
/*     */   {
/*  28 */     super(FileAttach.class);
/*     */   }
/*     */ 
/*     */   public void removeByPath(final String filePath)
/*     */   {
/*  33 */     String hql = "delete from FileAttach fa where fa.filePath = ?";
/*  34 */     getHibernateTemplate().execute(new HibernateCallback()
/*     */     {
/*     */       public Object doInHibernate(Session session) throws HibernateException, SQLException
/*     */       {
/*  38 */         Query query = session.createQuery("delete from FileAttach fa where fa.filePath = ?");
/*  39 */         query.setString(0, filePath);
/*  40 */         return Integer.valueOf(query.executeUpdate());
/*     */       }
/*     */     });
/*     */   }
/*     */ 
/*     */   public FileAttach getByPath(String filePath)
/*     */   {
/*  51 */     String hql = "from FileAttach fa where fa.filePath = ?";
/*  52 */     return (FileAttach)findUnique(hql, new Object[] { filePath });
/*     */   }
/*     */ 
/*     */   public List<FileAttach> fileList(PagingBean pb, String fileType, boolean bo)
/*     */   {
/*  65 */     Long userId = ContextUtil.getCurrentUser().getUserId();
/*  66 */     ArrayList paramList = new ArrayList();
/*  67 */     paramList.add(userId);
/*  68 */     String hql = "select f from FileAttach f where (f.delFlag = 0 or f.delFlag is null) and f.creatorId = ? ";
/*  69 */     if ((fileType != null) && (!fileType.equals(""))) {
/*  70 */       hql = hql + " and f.fileType like ? ";
/*  71 */       paramList.add(fileType + "%");
/*     */     }
/*  73 */     if (!bo)
/*  74 */       hql = hql + "and f.ext in('jpg','gif','jpeg','png','bmp','JPG','GIF','JPEG','PNG','BPM') ";
/*  75 */     hql = hql + "order by f.createtime DESC ";
/*  76 */     this.logger.debug("FileAttach：" + hql);
/*  77 */     return findByHql(hql, paramList.toArray(), pb);
/*     */   }
/*     */ 
/*     */   public List<FileAttach> fileList(String fileType)
/*     */   {
/*  85 */     Long userId = ContextUtil.getCurrentUser().getUserId();
/*  86 */     ArrayList paramList = new ArrayList();
/*  87 */     String hql = "select f from FileAttach f where (f.delFlag =0 or f.delFlag is null) and f.creatorId = ? and ";
/*  88 */     paramList.add(userId);
/*  89 */     if (!fileType.isEmpty()) {
/*  90 */       hql = hql + "f.fileType like ? ";
/*  91 */       paramList.add(fileType);
/*     */     }
/*  93 */     hql = hql + "order by f.createtime DESC ";
/*  94 */     this.logger.debug(hql);
/*  95 */     return findByHql(hql, paramList.toArray());
/*     */   }
/*     */ 
/*     */   public void remove(Long arg0)
/*     */   {
/* 103 */     FileAttach fileAttach = (FileAttach)get(arg0);
/* 104 */     fileAttach.setDelFlag(Integer.valueOf(1));
/* 105 */     save(fileAttach);
/*     */ 
/* 107 */     File file = new File(fileAttach.getFilePath());
/* 108 */     if (file.exists())
/* 109 */       file.delete();
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.system.impl.FileAttachDaoImpl
 * JD-Core Version:    0.6.0
 */