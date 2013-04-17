/*     */ package com.htsoft.oa.service.flow.impl;
/*     */ 
/*     */ import com.htsoft.core.command.QueryFilter;
/*     */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*     */ import com.htsoft.core.util.BeanUtil;
/*     */ import com.htsoft.core.util.XmlUtil;
/*     */ import com.htsoft.core.web.paging.PagingBean;
/*     */ import com.htsoft.oa.dao.flow.ProDefinitionDao;
/*     */ import com.htsoft.oa.model.flow.ProDefinition;
/*     */ import com.htsoft.oa.model.system.AppUser;
/*     */ import com.htsoft.oa.model.system.GlobalType;
/*     */ import com.htsoft.oa.service.bpm.ILog.factory.BpmFactory;
/*     */ import com.htsoft.oa.service.flow.JbpmService;
/*     */ import com.htsoft.oa.service.flow.ProDefinitionService;
/*     */ import com.htsoft.oa.service.system.GlobalTypeService;
/*     */ import java.util.Date;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.annotation.Resource;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.dom4j.Document;
/*     */ import org.dom4j.Element;
/*     */ 
/*     */ public class ProDefinitionServiceImpl extends BaseServiceImpl<ProDefinition>
/*     */   implements ProDefinitionService
/*     */ {
/*     */ 
/*     */   @Resource
/*     */   private GlobalTypeService globalTypeService;
/*     */ 
/*     */   @Resource
/*     */   private JbpmService jbpmService;
/*     */   private ProDefinitionDao dao;
/*     */ 
/*     */   public ProDefinitionServiceImpl(ProDefinitionDao dao)
/*     */   {
/*  39 */     super(dao);
/*  40 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */   public ProDefinition getByDeployId(String deployId) {
/*  44 */     return this.dao.getByDeployId(deployId);
/*     */   }
/*     */ 
/*     */   public ProDefinition getByName(String name) {
/*  48 */     return this.dao.getByName(name);
/*     */   }
/*     */ 
/*     */   public List<ProDefinition> getByRights(AppUser curUser, ProDefinition proDefinition, QueryFilter filter)
/*     */   {
/*  54 */     return this.dao.getByRights(curUser, proDefinition, filter);
/*     */   }
/*     */ 
/*     */   public boolean checkNameByVo(ProDefinition proDefinition)
/*     */   {
/*  59 */     return this.dao.checkNameByVo(proDefinition);
/*     */   }
/*     */ 
/*     */   public boolean checkProcessNameByVo(ProDefinition proDefinition)
/*     */   {
/*  64 */     return this.dao.checkProcessNameByVo(proDefinition);
/*     */   }
/*     */ 
/*     */   public List<ProDefinition> findRunningPro(ProDefinition proDefinition, Short runstate, PagingBean pb)
/*     */   {
/*  70 */     return this.dao.findRunningPro(proDefinition, runstate, pb);
/*     */   }
/*     */ 
/*     */   public String defSave(ProDefinition proDefinition, Boolean deploy)
/*     */   {
/*  78 */     this.logger.info("...eneter defSave......");
/*  79 */     String msg = "";
/*     */ 
/*  81 */     if ((proDefinition.getDrawDefXml() != null) && (!proDefinition.getDrawDefXml().equals(""))) {
/*  82 */       String text = change(proDefinition.getDrawDefXml(), proDefinition.getProcessName());
/*  83 */       proDefinition.setDefXml(text);
/*  84 */       this.logger.debug("解析的JBPM对应的xml文件：\n" + text);
/*     */     }
/*     */ 
/*  87 */     Long proTypeId = proDefinition.getProTypeId();
/*  88 */     if (proTypeId != null) {
/*  89 */       GlobalType proType = (GlobalType)this.globalTypeService.get(proTypeId);
/*  90 */       proDefinition.setProType(proType);
/*     */     }
/*     */ 
/*  94 */     if (!checkNameByVo(proDefinition))
/*     */     {
/*  96 */       msg = "流程名称(系统中使用)已存在,请重新填写.";
/*     */     }
/*  98 */     if (!checkProcessNameByVo(proDefinition))
/*     */     {
/* 100 */       msg = "流程名称(定义中使用)已存在,请重新填写.";
/*     */     }
/* 102 */     if (deploy.booleanValue()) {
/* 103 */       save(proDefinition, deploy.toString());
/* 104 */       msg = "true";
/* 105 */       this.logger.debug("flex流程设计【保存并发布】操作返回结果：" + msg);
/*     */     } else {
/* 107 */       proDefinition.setCreatetime(new Date());
/* 108 */       save(proDefinition);
/* 109 */       msg = "true";
/* 110 */       this.logger.debug("flex流程设计【保存】操作返回结果：" + msg);
/*     */     }
/* 112 */     return msg;
/*     */   }
/*     */ 
/*     */   private String change(String xml, String processName)
/*     */   {
/* 123 */     String text = "";
/* 124 */     if ((xml != null) && (!xml.equals(""))) {
/* 125 */       Document document = XmlUtil.stringToDocument(xml);
/* 126 */       Element element = document.getRootElement();
/* 127 */       BpmFactory factory = new BpmFactory(document);
/*     */ 
/* 129 */       Iterator it = element.elements().iterator();
/* 130 */       text = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n <process name=\"" + 
/* 131 */         processName + "\" xmlns=\"http://jbpm.org/4.4/jpdl\">";
/* 132 */       while (it.hasNext()) {
/* 133 */         Element el = (Element)it.next();
/* 134 */         String str = factory.getInfo(el, el.getName());
/* 135 */         text = text + str;
/*     */       }
/* 137 */       text = text + "</process>";
/*     */     }
/* 139 */     return text;
/*     */   }
/*     */ 
/*     */   private void save(ProDefinition proDefinition, String deploy)
/*     */   {
/* 146 */     if (this.logger.isDebugEnabled()) {
/* 147 */       this.logger.debug("---deploy---" + deploy);
/*     */     }
/* 149 */     if (proDefinition.getDefId() != null) {
/* 150 */       ProDefinition proDef = (ProDefinition)get(proDefinition.getDefId());
/*     */       try {
/* 152 */         BeanUtil.copyNotNullProperties(proDef, proDefinition);
/* 153 */         if ("true".equals(deploy)) {
/* 154 */           this.jbpmService.saveOrUpdateDeploy(proDef); return;
/*     */         }
/* 156 */         save(proDef);
/*     */       } catch (Exception ex) {
/* 158 */         this.logger.error(ex.getMessage());
/*     */       }
/*     */     } else {
/* 161 */       proDefinition.setCreatetime(new Date());
/*     */ 
/* 163 */       if (this.logger.isDebugEnabled()) {
/* 164 */         this.logger.debug("---start deploy---");
/*     */       }
/*     */ 
/* 167 */       if ("true".equals(deploy))
/* 168 */         this.jbpmService.saveOrUpdateDeploy(proDefinition);
/*     */       else
/* 170 */         save(proDefinition);
/*     */     }
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.flow.impl.ProDefinitionServiceImpl
 * JD-Core Version:    0.6.0
 */