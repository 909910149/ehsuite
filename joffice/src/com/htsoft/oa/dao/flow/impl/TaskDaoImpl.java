/*     */ package com.htsoft.oa.dao.flow.impl;
/*     */ 
/*     */ import com.htsoft.core.dao.impl.BaseDaoImpl;
/*     */ import com.htsoft.core.web.paging.PagingBean;
/*     */ import com.htsoft.oa.dao.flow.TaskDao;
/*     */ import com.htsoft.oa.model.flow.JbpmTask;
/*     */ import com.htsoft.oa.model.system.AppRole;
/*     */ import com.htsoft.oa.model.system.AppUser;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.jbpm.api.Execution;
/*     */ import org.jbpm.pvm.internal.model.ExecutionImpl;
/*     */ import org.jbpm.pvm.internal.task.TaskImpl;
/*     */ import org.springframework.jdbc.core.JdbcTemplate;
/*     */ import org.springframework.jdbc.core.RowMapper;
/*     */ import org.springframework.orm.hibernate3.HibernateTemplate;
/*     */ 
/*     */ public class TaskDaoImpl extends BaseDaoImpl<TaskImpl>
/*     */   implements TaskDao
/*     */ {
/*     */   public TaskDaoImpl()
/*     */   {
/*  28 */     super(TaskImpl.class);
/*     */   }
/*     */ 
/*     */   public List<TaskImpl> getPersonTasks(String userId, PagingBean pb)
/*     */   {
/*  39 */     StringBuffer hqlSb = new StringBuffer();
/*  40 */     hqlSb.append("select task from org.jbpm.pvm.internal.task.TaskImpl task  where task.assignee=?");
/*  41 */     hqlSb.append(" order by task.createTime desc");
/*     */ 
/*  43 */     return findByHql(hqlSb.toString(), new Object[] { userId }, pb);
/*     */   }
/*     */ 
/*     */   public List<TaskImpl> getAllTasks(String taskName, PagingBean pb)
/*     */   {
/*  53 */     List params = new ArrayList();
/*  54 */     String hql = "from org.jbpm.pvm.internal.task.TaskImpl task where 1=1";
/*  55 */     if (StringUtils.isNotEmpty(taskName)) {
/*  56 */       hql = hql + " and task.name like ?";
/*  57 */       params.add("%" + taskName + "%");
/*     */     }
/*  59 */     hql = hql + " order by task.createTime desc";
/*  60 */     return findByHql(hql, params.toArray(), pb);
/*     */   }
/*     */ 
/*     */   public List<TaskImpl> getCandidateTasks(String userId, PagingBean pb)
/*     */   {
/*  70 */     AppUser user = (AppUser)getHibernateTemplate().load(AppUser.class, new Long(userId));
/*  71 */     Iterator rolesIt = user.getRoles().iterator();
/*  72 */     StringBuffer groupIds = new StringBuffer();
/*  73 */     int i = 0;
/*  74 */     while (rolesIt.hasNext()) {
/*  75 */       if (i++ > 0) groupIds.append(",");
/*  76 */       groupIds.append("'" + ((AppRole)rolesIt.next()).getRoleId().toString() + "'");
/*     */     }
/*  78 */     StringBuffer hqlSb = new StringBuffer();
/*  79 */     hqlSb.append("select distinct task from org.jbpm.pvm.internal.task.TaskImpl task left join task.participations pt ");
/*  80 */     hqlSb.append(" where task.assignee is null and pt.type = 'candidate' and ( pt.userId=? ");
/*     */ 
/*  82 */     if (user.getRoles().size() > 0) {
/*  83 */       hqlSb.append(" or pt.groupId in (" + groupIds.toString() + ")");
/*     */     }
/*  85 */     hqlSb.append(")");
/*  86 */     hqlSb.append(" order by task.createTime desc");
/*     */ 
/*  88 */     return findByHql(hqlSb.toString(), new Object[] { userId, userId }, pb);
/*     */   }
/*     */ 
/*     */   public List<TaskImpl> getTasksByUserId(String userId, PagingBean pb)
/*     */   {
/*  97 */     AppUser user = (AppUser)getHibernateTemplate().load(AppUser.class, new Long(userId));
/*  98 */     Iterator rolesIt = user.getRoles().iterator();
/*  99 */     StringBuffer groupIds = new StringBuffer();
/* 100 */     int i = 0;
/* 101 */     while (rolesIt.hasNext()) {
/* 102 */       if (i++ > 0) groupIds.append(",");
/* 103 */       groupIds.append("'" + ((AppRole)rolesIt.next()).getRoleId().toString() + "'");
/*     */     }
/* 105 */     StringBuffer hqlSb = new StringBuffer();
/* 106 */     hqlSb.append("select distinct task from org.jbpm.pvm.internal.task.TaskImpl task left join task.participations pt where task.assignee=?");
/* 107 */     hqlSb.append(" or ( task.assignee is null and pt.type = 'candidate' and  ( pt.userId = ? ");
/*     */ 
/* 109 */     if (user.getRoles().size() > 0) {
/* 110 */       hqlSb.append(" or pt.groupId in (" + groupIds.toString() + ")");
/*     */     }
/* 112 */     hqlSb.append("))");
/* 113 */     hqlSb.append(" order by task.createTime desc");
/*     */ 
/* 115 */     return findByHql(hqlSb.toString(), new Object[] { userId, userId }, pb);
/*     */   }
/*     */ 
/*     */   public List<JbpmTask> getByActivityNameVarKeyLongVal(String activityName, String varKey, Long value)
/*     */   {
/* 126 */     String sql = "select task.DBID_ taskId, task.ASSIGNEE_ assignee from jbpm4_task task join jbpm4_variable var on task.EXECUTION_=var.EXECUTION_ where  task.ACTIVITY_NAME_=? and var.KEY_=? and var.LONG_VALUE_=?";
/* 127 */     Collection jbpmtask = this.jdbcTemplate.query(sql, new Object[] { activityName, varKey, value }, 
/* 128 */       new RowMapper()
/*     */     {
/*     */       public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
/* 131 */         JbpmTask task = new JbpmTask();
/* 132 */         Long taskId = Long.valueOf(rs.getLong("taskId"));
/* 133 */         String assignee = rs.getString("assignee");
/* 134 */         task.setAssignee(assignee);
/* 135 */         task.setTaskId(taskId);
/* 136 */         return task;
/*     */       }
/*     */     });
/* 140 */     return new ArrayList(jbpmtask);
/*     */   }
/*     */ 
/*     */   public List<Long> getGroupByTask(Long taskId) {
/* 144 */     String sql = "select pa.GROUPID_ groupId from jbpm4_participation pa  where pa.TYPE_ = 'candidate'and pa.TASK_=?";
/* 145 */     Collection groupIds = this.jdbcTemplate.query(sql, new Object[] { taskId }, 
/* 146 */       new RowMapper()
/*     */     {
/*     */       public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
/* 149 */         String groupId = rs.getString("groupId");
/* 150 */         return groupId;
/*     */       }
/*     */     });
/* 154 */     return new ArrayList(groupIds);
/*     */   }
/*     */ 
/*     */   public List<Long> getUserIdByTask(Long taskId) {
/* 158 */     String hql = "from org.jbpm.pvm.internal.task.TaskImpl task where task.superTask.id=?";
/* 159 */     Object[] objs = { taskId };
/* 160 */     List<TaskImpl> taskList = findByHql(hql, objs);
/* 161 */     List list = new ArrayList();
/* 162 */     for (TaskImpl task : taskList) {
/* 163 */       list.add(new Long(task.getAssignee()));
/*     */     }
/* 165 */     return list;
/*     */   }
/*     */ 
/*     */   public void removeExeByParentId(Long parentDbid)
/*     */   {
/* 175 */     String delExeHql = "delete from ExecutionImpl exi where exi.parent.dbid=? ";
/*     */ 
/* 178 */     update(delExeHql, new Object[] { parentDbid });
/*     */   }
/*     */ 
/*     */   public Execution getExecutionByDbid(Long dbid)
/*     */   {
/* 187 */     String hql = "from ExecutionImpl exi where exi.dbid=?";
/* 188 */     return (Execution)findUnique(hql, new Object[] { dbid });
/*     */   }
/*     */ 
/*     */   public void save(ExecutionImpl executionImpl)
/*     */   {
/* 196 */     getHibernateTemplate().save(executionImpl);
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.flow.impl.TaskDaoImpl
 * JD-Core Version:    0.6.0
 */