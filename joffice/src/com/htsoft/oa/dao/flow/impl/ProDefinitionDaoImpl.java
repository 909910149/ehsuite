/*     */ package com.htsoft.oa.dao.flow.impl;
/*     */ 
/*     */ import com.htsoft.core.command.QueryFilter;
/*     */ import com.htsoft.core.dao.impl.BaseDaoImpl;
/*     */ import com.htsoft.core.web.paging.PagingBean;
/*     */ import com.htsoft.oa.dao.flow.ProDefinitionDao;
/*     */ import com.htsoft.oa.model.flow.ProDefinition;
/*     */ import com.htsoft.oa.model.system.AppRole;
/*     */ import com.htsoft.oa.model.system.AppUser;
/*     */ import com.htsoft.oa.model.system.Department;
/*     */ import com.htsoft.oa.service.system.GlobalTypeService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Resource;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class ProDefinitionDaoImpl extends BaseDaoImpl<ProDefinition>
/*     */   implements ProDefinitionDao
/*     */ {
/*     */ 
/*     */   @Resource
/*     */   private GlobalTypeService globalTypeService;
/*     */ 
/*     */   public ProDefinitionDaoImpl()
/*     */   {
/*  30 */     super(ProDefinition.class);
/*     */   }
/*     */ 
/*     */   public ProDefinition getByDeployId(String deployId) {
/*  34 */     String hql = "from ProDefinition pd where pd.deployId=?";
/*  35 */     return (ProDefinition)findUnique(hql, new Object[] { deployId });
/*     */   }
/*     */ 
/*     */   public ProDefinition getByName(String name) {
/*  39 */     String hql = "from ProDefinition pd where pd.name=?";
/*  40 */     return (ProDefinition)findUnique(hql, new Object[] { name });
/*     */   }
/*     */ 
/*     */   public List<ProDefinition> getByRights(AppUser curUser, ProDefinition proDefinition, QueryFilter filter)
/*     */   {
/*  45 */     String uIds = "%," + curUser.getUserId() + ",%";
/*  46 */     String dIds = "%," + curUser.getDepartment().getDepId() + ",%";
/*     */ 
/*  48 */     List params = new ArrayList();
/*  49 */     StringBuffer hql = new StringBuffer("select pd from ProDefRights pr right join pr.proDefinition pd  where 1=1 ");
/*     */ 
/*  51 */     if ((proDefinition != null) && (proDefinition.getName() != null)) {
/*  52 */       hql.append("and pd.name like = ?");
/*  53 */       params.add("%" + proDefinition.getName() + "%");
/*     */     }
/*     */ 
/*  56 */     hql.append("and (pr.userIds like ?  or pr.depIds like ? ");
/*  57 */     params.add(uIds);
/*  58 */     params.add(dIds);
/*     */ 
/*  60 */     Set roles = curUser.getRoles();
/*  61 */     for (Iterator it = roles.iterator(); it.hasNext(); ) {
/*  62 */       AppRole role = (AppRole)it.next();
/*  63 */       hql.append("or pr.roleIds like ? ");
/*  64 */       String rIds = "%," + role.getRoleId() + ",%";
/*  65 */       params.add(rIds);
/*     */     }
/*     */ 
/*  68 */     hql.append(")");
/*     */ 
/*  70 */     List result = findByHql(hql.toString(), params.toArray());
/*  71 */     filter.getPagingBean().setTotalItems(result.size());
/*  72 */     return result;
/*     */   }
/*     */ 
/*     */   public boolean checkNameByVo(ProDefinition proDefinition)
/*     */   {
/*  77 */     boolean idIsNull = false;
/*  78 */     if (proDefinition.getDefId() == null) {
/*  79 */       idIsNull = true;
/*     */     }
/*  81 */     StringBuffer hql = new StringBuffer("from ProDefinition pd where pd.name = ?");
/*  82 */     List params = new ArrayList();
/*  83 */     params.add(proDefinition.getName());
/*  84 */     if (!idIsNull) {
/*  85 */       hql.append("and pd.defId != ?");
/*  86 */       params.add(proDefinition.getDefId());
/*     */     }
/*  88 */     List result = findByHql(hql.toString(), params.toArray());
/*     */ 
/*  90 */     return result.size() <= 0;
/*     */   }
/*     */ 
/*     */   public boolean checkProcessNameByVo(ProDefinition proDefinition)
/*     */   {
/*  97 */     boolean idIsNull = false;
/*  98 */     if (proDefinition.getDefId() == null) {
/*  99 */       idIsNull = true;
/*     */     }
/* 101 */     StringBuffer hql = new StringBuffer("from ProDefinition pd where pd.processName = ?");
/* 102 */     List params = new ArrayList();
/* 103 */     params.add(proDefinition.getProcessName());
/* 104 */     if (!idIsNull) {
/* 105 */       hql.append("and pd.defId != ?");
/* 106 */       params.add(proDefinition.getDefId());
/*     */     }
/* 108 */     List result = findByHql(hql.toString(), params.toArray());
/*     */ 
/* 110 */     return result.size() <= 0;
/*     */   }
/*     */ 
/*     */   private List<ProDefinition> getByIds(List defIds)
/*     */   {
/* 122 */     String docHql = "from ProDefinition pd where pd.defId in (";
/* 123 */     StringBuffer sbIds = new StringBuffer();
/*     */ 
/* 125 */     for (int i = 0; i < defIds.size(); i++) {
/* 126 */       sbIds.append(defIds.get(i).toString()).append(",");
/*     */     }
/*     */ 
/* 129 */     if (defIds.size() > 0) {
/* 130 */       sbIds.deleteCharAt(sbIds.length() - 1);
/* 131 */       docHql = docHql + sbIds.toString() + ")";
/* 132 */       return findByHql(docHql);
/*     */     }
/* 134 */     return new ArrayList();
/*     */   }
/*     */ 
/*     */   public List<ProDefinition> findRunningPro(ProDefinition proDefinition, Short runstate, PagingBean pb)
/*     */   {
/* 140 */     StringBuffer hql = new StringBuffer("select distinct pd.defId from ProcessRun pr join pr.proDefinition pd  where pr.runStatus=?");
/* 141 */     List params = new ArrayList();
/* 142 */     params.add(runstate);
/* 143 */     if ((proDefinition != null) && (StringUtils.isNotEmpty(proDefinition.getName()))) {
/* 144 */       hql.append(" and pd.name like ?");
/* 145 */       params.add("%" + proDefinition.getName() + "%");
/*     */     }
/* 147 */     hql.append(" order by pd.defId desc");
/*     */ 
/* 149 */     List defIds = findByHql(hql.toString(), params.toArray(), pb);
/*     */ 
/* 151 */     return getByIds(defIds);
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.flow.impl.ProDefinitionDaoImpl
 * JD-Core Version:    0.6.0
 */