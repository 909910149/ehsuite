/*     */ package com.htsoft.oa.dao.system.impl;
/*     */ 
/*     */ import com.htsoft.core.Constants;
/*     */ import com.htsoft.core.dao.impl.BaseDaoImpl;
/*     */ import com.htsoft.core.menu.TopModule;
/*     */ import com.htsoft.core.util.AppUtil;
/*     */ import com.htsoft.core.web.paging.PagingBean;
/*     */ import com.htsoft.oa.dao.system.AppUserDao;
/*     */ import com.htsoft.oa.model.system.AppRole;
/*     */ import com.htsoft.oa.model.system.AppUser;
/*     */ import com.htsoft.oa.model.system.Department;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.hibernate.Hibernate;
/*     */ import org.hibernate.HibernateException;
/*     */ import org.hibernate.Query;
/*     */ import org.hibernate.Session;
/*     */ import org.springframework.dao.DataAccessException;
/*     */ import org.springframework.orm.hibernate3.HibernateCallback;
/*     */ import org.springframework.orm.hibernate3.HibernateTemplate;
/*     */ import org.springframework.security.userdetails.UserDetails;
/*     */ import org.springframework.security.userdetails.UserDetailsService;
/*     */ import org.springframework.security.userdetails.UsernameNotFoundException;
/*     */ 
/*     */ public class AppUserDaoImpl extends BaseDaoImpl<AppUser>
/*     */   implements AppUserDao, UserDetailsService
/*     */ {
/*     */   public AppUserDaoImpl()
/*     */   {
/*  46 */     super(AppUser.class);
/*     */   }
/*     */ 
/*     */   public AppUser findByUserName(String username)
/*     */   {
/*  51 */     String hql = "from AppUser au where au.username=?";
/*  52 */     Object[] params = { username };
/*  53 */     List list = findByHql(hql, params);
/*  54 */     AppUser user = null;
/*  55 */     if (list.size() != 0) {
/*  56 */       user = (AppUser)list.get(0);
/*     */     }
/*     */ 
/*  60 */     return user;
/*     */   }
/*     */ 
/*     */   public UserDetails loadUserByUsername(final String username)
/*     */     throws UsernameNotFoundException, DataAccessException
/*     */   {
/*  66 */     AppUser user = (AppUser)getHibernateTemplate().execute(
/*  67 */       new HibernateCallback()
/*     */     {
/*     */       public Object doInHibernate(Session session) throws HibernateException, SQLException
/*     */       {
/*  71 */         String hql = "from AppUser ap where ap.username=? and ap.delFlag = ?";
/*  72 */         Query query = session.createQuery(hql);
/*  73 */         query.setString(0,username);
/*  74 */         query.setShort(1, Constants.FLAG_UNDELETED.shortValue());
/*  75 */         AppUser user = null;
/*     */ 
/*  78 */         user = (AppUser)query.uniqueResult();
/*     */ 
/*  80 */         if (user != null) {
/*  81 */           Hibernate.initialize(user.getRoles());
/*  82 */           Hibernate.initialize(user.getDepartment());
/*     */ 
/*  85 */           Set roleSet = user.getRoles();
/*  86 */           Iterator it = roleSet.iterator();
/*     */ 
/*  88 */           while (it.hasNext()) {
/*  89 */             AppRole role = (AppRole)it.next();
/*  90 */             if (role.getRoleId().equals(
/*  91 */               AppRole.SUPER_ROLEID)) {
/*  92 */               user.getRights().clear();
/*  93 */               user.getTopModules().clear();
/*  94 */               user.getRights().add("__ALL");
/*  95 */               user.getTopModules().putAll(
/*  96 */                 AppUtil.getAllTopModels());
/*  97 */               break;
/*     */             }
/*  99 */             if (StringUtils.isNotEmpty(role.getRights())) {
/* 100 */               String[] items = role.getRights()
/* 101 */                 .split("[,]");
/* 102 */               for (int i = 0; i < items.length; i++) {
/* 103 */                 String item = items[i];
/*     */ 
/* 105 */                 if (item.startsWith("Mod_"))
/*     */                 {
/* 107 */                   if (user.getTopModules()
/* 107 */                     .containsKey(item)) continue;
/* 108 */                   user.getTopModules()
/* 109 */                     .put(items[i], 
/* 111 */                     (TopModule)AppUtil.getAllTopModels()
/* 111 */                     .get(item));
/*     */                 }
/* 116 */                 else if (!user.getRights()
/* 116 */                   .contains(item)) {
/* 117 */                   user.getRights().add(item);
/*     */                 }
/*     */ 
/*     */               }
/*     */ 
/*     */             }
/*     */ 
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/* 130 */         return user;
/*     */       }
/*     */     });
/* 134 */     return user;
/*     */   }
/*     */ 
/*     */   public List findByDepartment(String path, PagingBean pb)
/*     */   {
/* 142 */     List list = new ArrayList();
/* 143 */     String hql = new String();
/* 144 */     if ("0.".equals(path)) {
/* 145 */       hql = "from AppUser vo2 where vo2.delFlag = ?";
/* 146 */       list.add(Constants.FLAG_UNDELETED);
/*     */     }
/*     */     else {
/* 149 */       hql = "select distinct au from AppUser au where au.department.path like ? and au.delFlag=? ";
/*     */ 
/* 155 */       list.add(path + "%");
/* 156 */       list.add(Constants.FLAG_UNDELETED);
/*     */     }
/* 158 */     return findByHql(hql, list.toArray(), pb);
/*     */   }
/*     */ 
/*     */   public List findByDepartment(Department department)
/*     */   {
/* 163 */     String hql = "select vo2 from Department vo1,AppUser vo2 where vo1=vo2.department and vo1.path like ? and vo2.delFlag = ?";
/* 164 */     Object[] params = { department.getPath() + "%", 
/* 165 */       Constants.FLAG_UNDELETED };
/* 166 */     return findByHql(hql, params);
/*     */   }
/*     */ 
/*     */   public List findByRole(Long roleId)
/*     */   {
/* 171 */     String hql = "select vo from AppUser vo join vo.roles roles where roles.roleId=? and vo.delFlag = ?";
/* 172 */     Object[] objs = { roleId, Constants.FLAG_UNDELETED };
/* 173 */     return findByHql(hql, objs);
/*     */   }
/*     */ 
/*     */   public List findByRole(Long roleId, PagingBean pb)
/*     */   {
/* 178 */     String hql = "select vo from AppUser vo join vo.roles roles where roles.roleId=? and vo.delFlag = ?";
/* 179 */     Object[] objs = { roleId, Constants.FLAG_UNDELETED };
/* 180 */     return findByHql(hql, objs, pb);
/*     */   }
/*     */ 
/*     */   public List<AppUser> findByDepartment(String path)
/*     */   {
/* 185 */     String hql = "select vo2 from Department vo1,AppUser vo2 where vo1.depId=vo2.depId and vo1.path like ? and vo2.delFlag =?";
/* 186 */     Object[] params = { path + "%", Constants.FLAG_UNDELETED };
/* 187 */     return findByHql(hql, params);
/*     */   }
/*     */ 
/*     */   public List findByRoleId(Long roleId) {
/* 191 */     String hql = "select vo from AppUser vo join vo.roles as roles where roles.roleId=? and vo.delFlag =?";
/* 192 */     return findByHql(hql, new Object[] { roleId, Constants.FLAG_UNDELETED });
/*     */   }
/*     */ 
/*     */   public List findByUserIds(Long[] userIds) {
/* 196 */     String hql = "select vo from AppUser vo where vo.delFlag=? ";
/*     */ 
/* 198 */     if ((userIds == null) || (userIds.length == 0))
/* 199 */       return null;
/* 200 */     hql = hql + " where vo.userId in (";
/* 201 */     int i = 0;
/*     */ 
/* 203 */     for (Long userId : userIds) {
/* 204 */       if (i++ > 0) {
/* 205 */         hql = hql + ",";
/*     */       }
/* 207 */       hql = hql + "?";
/*     */     }
/* 209 */     hql = hql + " )";
/*     */ 
/* 211 */     return findByHql(hql, 
/* 212 */       new Object[] { Constants.FLAG_UNDELETED, userIds });
/*     */   }
/*     */ 
/*     */   public List<AppUser> findSubAppUser(String path, Set<Long> userIds, PagingBean pb)
/*     */   {
/* 218 */     String st = "";
/* 219 */     if (userIds.size() > 0) {
/* 220 */       Iterator it = userIds.iterator();
/* 221 */       StringBuffer sb = new StringBuffer();
/* 222 */       while (it.hasNext()) {
/* 223 */         sb.append(((Long)it.next()).toString() + ",");
/*     */       }
/* 225 */       sb.deleteCharAt(sb.length() - 1);
/* 226 */       st = sb.toString();
/*     */     }
/*     */ 
/* 229 */     List list = new ArrayList();
/* 230 */     StringBuffer hql = new StringBuffer();
/* 231 */     if (path != null) {
/* 232 */       hql.append("select vo2 from Department vo1,AppUser vo2 where vo1=vo2.department ");
/* 233 */       hql.append(" and vo1.path like ?");
/* 234 */       list.add(path + "%");
/*     */     } else {
/* 236 */       hql.append("from AppUser vo2 where 1=1 ");
/*     */     }
/* 238 */     if (st != "") {
/* 239 */       hql.append(" and vo2.userId not in (" + st + ")");
/*     */     }
/* 241 */     hql.append(" and vo2.delFlag = ?");
/* 242 */     list.add(Constants.FLAG_UNDELETED);
/* 243 */     return findByHql(hql.toString(), list.toArray(), pb);
/*     */   }
/*     */ 
/*     */   public List<AppUser> findSubAppUserByRole(Long roleId, Set<Long> userIds, PagingBean pb)
/*     */   {
/* 249 */     String st = "";
/* 250 */     if (userIds.size() > 0) {
/* 251 */       Iterator it = userIds.iterator();
/* 252 */       StringBuffer sb = new StringBuffer();
/* 253 */       while (it.hasNext()) {
/* 254 */         sb.append(((Long)it.next()).toString() + ",");
/*     */       }
/* 256 */       sb.deleteCharAt(sb.length() - 1);
/* 257 */       st = sb.toString();
/*     */     }
/* 259 */     StringBuffer hql = new StringBuffer(
/* 260 */       "select vo from AppUser vo join vo.roles roles where roles.roleId=?");
/* 261 */     List list = new ArrayList();
/* 262 */     list.add(roleId);
/* 263 */     if (st != "") {
/* 264 */       hql.append(" and vo.userId not in (" + st + ")");
/*     */     }
/* 266 */     hql.append(" and vo.delFlag =?");
/* 267 */     list.add(Constants.FLAG_UNDELETED);
/* 268 */     return findByHql(hql.toString(), list.toArray(), pb);
/*     */   }
/*     */ 
/*     */   public List<AppUser> findByDepId(Long depId)
/*     */   {
/* 273 */     String hql = "from AppUser vo where vo.delFlag=0 and vo.department.depId=?";
/* 274 */     Object[] objs = { depId };
/* 275 */     return findByHql(hql, objs);
/*     */   }
/*     */ 
/*     */   public List<AppUser> findUsersByRoleIds(String roleIds)
/*     */   {
/* 285 */     if (StringUtils.isEmpty(roleIds)) {
/* 286 */       return new ArrayList();
/*     */     }
/* 288 */     String hql = "select distinct au from AppUser as au join au.roles as roles where roles.roleId in (" + 
/* 289 */       roleIds + ") and au.delFlag =?";
/*     */ 
/* 291 */     return findByHql(hql, new Object[] { Constants.FLAG_UNDELETED });
/*     */   }
/*     */ 
/*     */   public List<AppUser> findRelativeUsersByUserId(Long userId, Short level)
/*     */   {
/* 306 */     StringBuffer sb = new StringBuffer(
/* 307 */       "select u.jobUser from RelativeUser u where u.appUser.userId = ? ");
/* 308 */     boolean bo = (level != null) && (level.shortValue() < 3);
/* 309 */     if (bo)
/* 310 */       sb.append("and u.isSuper = ? ");
/* 311 */     Query query = getSession().createQuery(sb.toString());
/* 312 */     query.setLong(0, userId.longValue());
/* 313 */     if (bo)
/* 314 */       query.setShort(1, level.shortValue());
/* 315 */     this.logger.debug("我的下属查询：" + sb.toString());
/* 316 */     return query.list();
/*     */   }
/*     */ 
/*     */   public List<AppUser> findByDepartment(String path, String userIds, PagingBean pb)
/*     */   {
/* 322 */     List list = new ArrayList();
/* 323 */     StringBuffer hql = new StringBuffer("");
/* 324 */     if ("0.".equals(path)) {
/* 325 */       hql.append("from AppUser vo2 where vo2.delFlag = ? ");
/* 326 */       list.add(Constants.FLAG_UNDELETED);
/*     */     } else {
/* 328 */       hql.append("select DISTINCT vo2 from Department vo1,AppUser vo2,DepUsers vo3 where 1=1 and vo3.appUser=vo2 and vo3.department=vo1 and vo1.path like ? and vo2.delFlag = ? ");
/*     */ 
/* 332 */       list.add(path + "%");
/* 333 */       list.add(Constants.FLAG_UNDELETED);
/*     */     }
/*     */ 
/* 336 */     if ((userIds != null) && (!userIds.equals(""))) {
/* 337 */       hql.append("and vo2.userId in (?) ");
/* 338 */       list.add(userIds);
/*     */     }
/* 340 */     hql.append("order by vo3.sn ");
/* 341 */     this.logger.debug("自定义AppUserDaoImpl : " + hql.toString());
/* 342 */     return findByHql(hql.toString(), list.toArray(), pb);
/*     */   }
/*     */ 
/*     */   public List<AppUser> getUsersByRoleId(Long roleId)
/*     */   {
/* 352 */     String hql = "from AppUser au join au.roles as role where role.roleId=?";
/* 353 */     return findByHql(hql, new Object[] { roleId });
/*     */   }
/*     */ 
/*     */   public List<AppUser> findByfullName(String fullname)
/*     */   {
/* 365 */     String hql = "from AppUser u where u.fullname= ? ";
/* 366 */     return findByHql(hql, new Object[] { fullname });
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.system.impl.AppUserDaoImpl
 * JD-Core Version:    0.6.0
 */