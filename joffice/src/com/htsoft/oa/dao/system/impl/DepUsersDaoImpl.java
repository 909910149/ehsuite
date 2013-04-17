/*     */ package com.htsoft.oa.dao.system.impl;
/*     */ 
/*     */ import com.htsoft.core.Constants;
/*     */ import com.htsoft.core.dao.impl.BaseDaoImpl;
/*     */ import com.htsoft.core.web.paging.PagingBean;
/*     */ import com.htsoft.oa.dao.system.DepUsersDao;
/*     */ import com.htsoft.oa.model.system.AppUser;
/*     */ import com.htsoft.oa.model.system.DepUsers;
/*     */ import com.htsoft.oa.model.system.Department;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.commons.logging.Log;
/*     */ 
/*     */ public class DepUsersDaoImpl extends BaseDaoImpl<DepUsers>
/*     */   implements DepUsersDao
/*     */ {
/*     */   public DepUsersDaoImpl()
/*     */   {
/*  31 */     super(DepUsers.class);
/*     */   }
/*     */ 
/*     */   public List<DepUsers> findByDepartment(String path, PagingBean pb)
/*     */   {
/*  39 */     List list = new ArrayList();
/*  40 */     String hql = new String();
/*  41 */     if ("0.".equals(path)) {
/*  42 */       hql = "select DISTINCT vo3 from Department vo1,DepUsers vo3,AppUser vo2 where 1=1 and vo3.appUser=vo2 and vo3.department=vo1 and vo2.delFlag = ? order by  vo3.sn,vo1.depId";
/*     */ 
/*  46 */       list.add(Constants.FLAG_UNDELETED);
/*     */     } else {
/*  48 */       hql = "select DISTINCT vo3 from Department vo1,AppUser vo2,DepUsers vo3 where 1=1 and vo3.appUser=vo2 and vo3.department=vo1 and vo1.path like ? and vo2.delFlag = ? order by  vo3.sn,vo1.depId";
/*     */ 
/*  52 */       list.add(path + "%");
/*  53 */       list.add(Constants.FLAG_UNDELETED);
/*     */     }
/*  55 */     return findByHql(hql, list.toArray(), pb);
/*     */   }
/*     */ 
/*     */   public List<DepUsers> search(String path, DepUsers depUsers, PagingBean pb)
/*     */   {
/*  60 */     List list = new ArrayList();
/*  61 */     StringBuffer hql = new StringBuffer();
/*  62 */     if ("0.".equals(path)) {
/*  63 */       hql
/*  64 */         .append("select DISTINCT vo3 from Department vo1,DepUsers vo3,AppUser vo2 where 1=1 and vo3.appUser=vo2 and vo3.department=vo1 and vo2.delFlag = ? ");
/*     */ 
/*  66 */       list.add(Constants.FLAG_UNDELETED);
/*     */     } else {
/*  68 */       hql
/*  69 */         .append("select DISTINCT vo3 from Department vo1,AppUser vo2,DepUsers vo3 where 1=1 and vo3.appUser=vo2 and vo3.department=vo1 and vo1.path like ? and vo2.delFlag = ? ");
/*     */ 
/*  71 */       list.add(path + "%");
/*  72 */       list.add(Constants.FLAG_UNDELETED);
/*     */     }
/*  74 */     if ((depUsers != null) && (depUsers.getAppUser() != null))
/*     */     {
/*  76 */       String username = depUsers.getAppUser().getUsername();
/*  77 */       if ((username != null) && (!username.equals(""))) {
/*  78 */         hql.append("and vo2.username like ? ");
/*  79 */         list.add(username + "%");
/*     */       }
/*     */ 
/*  82 */       String fullname = depUsers.getAppUser().getFullname();
/*  83 */       if ((fullname != null) && (!fullname.equals(""))) {
/*  84 */         hql.append("and vo2.fullname like ? ");
/*  85 */         list.add(fullname + "%");
/*     */       }
/*     */ 
/*  88 */       Short isMain = depUsers.getIsMain();
/*  89 */       if ((isMain != null) && (!isMain.equals(""))) {
/*  90 */         hql.append("and vo3.isMain >= ? ");
/*  91 */         list.add(isMain);
/*     */       }
/*     */     }
/*  94 */     hql.append("order by  vo3.sn,vo1.depId");
/*  95 */     this.logger.debug("自定义DepUserDaoImpl:" + hql.toString());
/*  96 */     return findByHql(hql.toString(), list.toArray(), pb);
/*     */   }
/*     */ 
/*     */   public List<DepUsers> findByUserIdDep(Long userId)
/*     */   {
/* 104 */     String hql = "select d from DepUsers d where d.appUser.userId = " + 
/* 105 */       userId;
/* 106 */     return findByHql(hql);
/*     */   }
/*     */ 
/*     */   public Boolean existsDep(Long depUserId, Long userId)
/*     */   {
/* 114 */     String hql = "select d from DepUsers d where d.appUser.userId = ? and d.isMain = 1 and d.depUserId not in(?) ";
/* 115 */     Object[] paramList = { userId, depUserId };
/* 116 */     List list = findByHql(hql, paramList);
/* 117 */     return Boolean.valueOf((list != null) && (list.size() > 0));
/*     */   }
/*     */ 
/*     */   public String add(DepUsers depUsers)
/*     */   {
/* 125 */     String msg = "{success:true,msg:'操作数据成功！'}";
/* 126 */     String hql = "select d from DepUsers d where d.appUser.userId = ? and d.department.depId = ? ";
/* 127 */     Object[] paramList = { depUsers.getAppUser().getUserId(), 
/* 128 */       depUsers.getDepartment().getDepId() };
/* 129 */     List list = findByHql(hql, paramList);
/* 130 */     if ((list != null) && (list.size() > 0))
/* 131 */       msg = "{failure:true,msg:'对不起，该用户[" + 
/* 132 */         depUsers.getAppUser().getUsername() + "]已经加入该部门[" + 
/* 133 */         depUsers.getDepartment().getDepName() + "]！'}";
/*     */     else
/* 135 */       save(depUsers);
/* 136 */     return msg;
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.system.impl.DepUsersDaoImpl
 * JD-Core Version:    0.6.0
 */