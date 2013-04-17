 package com.htsoft.oa.action.flow;
 
 import com.htsoft.core.command.QueryFilter;
 import com.htsoft.core.jbpm.jpdl.Node;
 import com.htsoft.core.util.BeanUtil;
 import com.htsoft.core.util.ContextUtil;
 import com.htsoft.core.util.JsonUtil;
 import com.htsoft.core.util.XmlUtil;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.model.flow.FormDefMapping;
 import com.htsoft.oa.model.flow.ProDefinition;
 import com.htsoft.oa.model.flow.ProcessRun;
 import com.htsoft.oa.model.system.AppUser;
 import com.htsoft.oa.model.system.GlobalType;
 import com.htsoft.oa.service.bpm.ILog.factory.BpmFactory;
 import com.htsoft.oa.service.flow.FormDefMappingService;
 import com.htsoft.oa.service.flow.FormTemplateService;
 import com.htsoft.oa.service.flow.JbpmService;
 import com.htsoft.oa.service.flow.ProDefinitionService;
 import com.htsoft.oa.service.flow.ProcessRunService;
 import com.htsoft.oa.service.system.GlobalTypeService;
 import flexjson.JSONSerializer;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.Iterator;
 import java.util.List;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 import org.apache.commons.lang.StringUtils;
 import org.apache.commons.logging.Log;
 import org.dom4j.Document;
 import org.dom4j.Element;
 
 public class ProDefinitionAction extends BaseAction
 {
 
   @Resource
   private ProDefinitionService proDefinitionService;
 
   @Resource
   private GlobalTypeService globalTypeService;
 
   @Resource
   private JbpmService jbpmService;
 
   @Resource
   private FormDefMappingService formDefMappingService;
 
   @Resource
   private FormTemplateService formTemplateService;
 
   @Resource
   private ProcessRunService processRunService;
   private ProDefinition proDefinition;
   private Long defId;
 
   public Long getDefId()
   {
     return this.defId;
   }
 
   public void setDefId(Long defId) {
     this.defId = defId;
   }
 
   public ProDefinition getProDefinition() {
     return this.proDefinition;
   }
 
   public void setProDefinition(ProDefinition proDefinition) {
     this.proDefinition = proDefinition;
   }
 
   public String list()
   {
     QueryFilter filter = new QueryFilter(getRequest());
 
     String typeId = getRequest().getParameter("typeId");
 
     List list = null;
     if ((StringUtils.isNotEmpty(typeId)) && (!"0".equals(typeId)))
     {
       GlobalType proType = (GlobalType)this.globalTypeService.get(new Long(typeId));
       filter.addFilter("Q_proType.path_S_LK", proType.getPath());
       list = this.proDefinitionService.getAll(filter);
     } else {
       AppUser curUser = ContextUtil.getCurrentUser();
       if (curUser.isSupperManage())
       {
         list = this.proDefinitionService.getAll(filter);
       }
       else {
         list = this.proDefinitionService.getByRights(curUser, this.proDefinition, 
           filter);
       }
     }
 
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(filter.getPagingBean().getTotalItems()).append(
       ",result:");
 
     JSONSerializer serializer = JsonUtil.getJSONSerializer(new String[] { "createtime" })
       .exclude(new String[] { 
       "defXml" });
 
     buff.append(serializer.serialize(list));
     buff.append("}");
 
     this.jsonString = buff.toString();
 
     return "success";
   }
 
   public String multiDel()
   {
     String[] ids = getRequest().getParameterValues("ids");
     if (ids != null) {
       for (String id : ids)
       {
         this.jbpmService.doUnDeployProDefinition(new Long(id));
       }
     }
 
     this.jsonString = "{success:true}";
 
     return "success";
   }
 
   public String get()
   {
     if (this.defId != null) {
       this.proDefinition = ((ProDefinition)this.proDefinitionService.get(this.defId));
     } else {
       this.proDefinition = new ProDefinition();
       String proTypeId = getRequest().getParameter("proTypeId");
       if (StringUtils.isNotEmpty(proTypeId)) {
         GlobalType proType = (GlobalType)this.globalTypeService.get(new Long(proTypeId));
         this.proDefinition.setProType(proType);
       }
 
     }
 
     JSONSerializer serializer = JsonUtil.getJSONSerializer(new String[] { "createtime" });
 
     StringBuffer sb = new StringBuffer("{success:true,data:");
     sb.append(serializer.serialize(this.proDefinition));
     sb.append("}");
     setJsonString(sb.toString());
 
     return "success";
   }
 
   public String flexGet() {
     if (this.defId != null) {
       this.proDefinition = ((ProDefinition)this.proDefinitionService.get(this.defId));
     } else {
       this.proDefinition = new ProDefinition();
       String proTypeId = getRequest().getParameter("proTypeId");
       if (StringUtils.isNotEmpty(proTypeId)) {
         GlobalType proType = (GlobalType)this.globalTypeService.get(new Long(proTypeId));
         this.proDefinition.setProType(proType);
       }
     }
     StringBuffer msg = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?><Result>");
 
     msg.append("<defId>" + this.proDefinition.getDefId() + "</defId>");
 
     msg.append("<drawDefXml>" + this.proDefinition.getDrawDefXml() + "</drawDefXml>");
 
     if (this.proDefinition.getProType() != null)
     {
       msg.append("<proTypeName>" + this.proDefinition.getProType().getTypeName() + "</proTypeName>");
 
       msg.append("<proTypeId>" + this.proDefinition.getProType().getProTypeId() + "</proTypeId>");
     }
 
     msg.append("<name>" + this.proDefinition.getName() + "</name>");
 
     msg.append("<processName>" + this.proDefinition.getProcessName() + "</processName>");
 
     msg.append("<status>" + this.proDefinition.getStatus() + "</status>");
 
     msg.append("<description>" + this.proDefinition.getDescription() + "</description>");
 
     msg.append("<newVersion>" + this.proDefinition.getNewVersion() + "</newVersion>");
     msg.append("</Result>");
     setJsonString(msg.toString());
     return "success";
   }
 
   public String flexDefSave()
   {
     this.logger.info("...eneter defSave......");
     boolean deploy = Boolean.parseBoolean(getRequest().getParameter("deploy"));
 
     if ((this.proDefinition.getDrawDefXml() != null) && (!this.proDefinition.getDrawDefXml().equals(""))) {
       String text = convertFlexXmlToJbpmXml(this.proDefinition.getDrawDefXml(), this.proDefinition.getProcessName());
       this.proDefinition.setDefXml(text);
       this.logger.debug("解析的JBPM对应的xml文件：\n" + text);
     }
 
     if (!this.proDefinitionService.checkNameByVo(this.proDefinition))
     {
       setJsonString("流程名称(系统中使用)已存在,请重新填写.");
       return "success";
     }
     if (!this.proDefinitionService.checkProcessNameByVo(this.proDefinition))
     {
       setJsonString("流程名称(定义中使用)已存在,请重新填写.");
       return "success";
     }
     if (deploy) {
       save();
     } else {
       Long proTypeId = this.proDefinition.getProTypeId();
       if (proTypeId != null) {
         GlobalType proType = (GlobalType)this.globalTypeService.get(proTypeId);
         this.proDefinition.setProType(proType);
       }
       this.proDefinition.setCreatetime(new Date());
       this.proDefinitionService.save(this.proDefinition);
       setJsonString("true");
     }
 
     return "success";
   }
 
   public String defSave() {
     return custSave();
   }
 
   private String custSave() {
     this.logger.info("...eneter defSave......");
     boolean deploy = Boolean.parseBoolean(getRequest().getParameter("deploy"));
 
     if ((this.proDefinition.getDrawDefXml() != null) && (!this.proDefinition.getDrawDefXml().equals(""))) {
       String text = convertFlexXmlToJbpmXml(this.proDefinition.getDrawDefXml(), this.proDefinition.getProcessName());
       this.proDefinition.setDefXml(text);
       this.logger.debug("解析的JBPM对应的xml文件：\n" + text);
     }
 
     if (!this.proDefinitionService.checkNameByVo(this.proDefinition))
     {
       setJsonString("{success:false,msg:'流程名称(系统中使用)已存在,请重新填写.'}");
       return "success";
     }
     if (!this.proDefinitionService.checkProcessNameByVo(this.proDefinition))
     {
       setJsonString("{success:false,msg:'流程名称(定义中使用)已存在,请重新填写.'}");
       return "success";
     }
     if (deploy) {
       save();
     } else {
       Long proTypeId = this.proDefinition.getProTypeId();
       if (proTypeId != null) {
         GlobalType proType = (GlobalType)this.globalTypeService.get(proTypeId);
         this.proDefinition.setProType(proType);
       }
       this.proDefinition.setCreatetime(new Date());
       this.proDefinitionService.save(this.proDefinition);
       setJsonString("{success:true}");
     }
     return "success";
   }
 
   private String convertFlexXmlToJbpmXml(String xml, String processName)
   {
     String text = "";
     if ((xml != null) && (!xml.equals(""))) {
       Document document = XmlUtil.stringToDocument(xml);
       Element element = document.getRootElement();
       BpmFactory factory = new BpmFactory(document);
 
       Iterator it = element.elements().iterator();
       text = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n <process name=\"" + processName + "\" xmlns=\"http://jbpm.org/4.4/jpdl\">";
       while (it.hasNext()) {
         Element el = (Element)it.next();
         String str = factory.getInfo(el, el.getName());
         text = text + str;
       }
       text = text + "</process>";
     }
     return text;
   }
 
   public String save()
   {
     Long proTypeId = this.proDefinition.getProTypeId();
     if (proTypeId != null) {
       GlobalType proType = (GlobalType)this.globalTypeService.get(proTypeId);
       this.proDefinition.setProType(proType);
     }
 
     String deploy = getRequest().getParameter("deploy");
     if (this.logger.isDebugEnabled()) {
       this.logger.debug("---deploy---" + deploy);
     }
 
     if (this.proDefinition.getDefId() != null) {
       ProDefinition proDef = (ProDefinition)this.proDefinitionService.get(this.proDefinition
         .getDefId());
       try {
         BeanUtil.copyNotNullProperties(proDef, this.proDefinition);
         if ("true".equals(deploy)) {
           this.jbpmService.saveOrUpdateDeploy(proDef); 
//					break label259;
         }
         this.proDefinitionService.save(proDef);
       }
       catch (Exception ex) {
         this.logger.error(ex.getMessage());
       }
     } else {
       this.proDefinition.setCreatetime(new Date());
 
       if (this.logger.isDebugEnabled()) {
         this.logger.debug("---start deploy---");
       }
 
       if ("true".equals(deploy))
         this.jbpmService.saveOrUpdateDeploy(this.proDefinition);
       else {
         this.proDefinitionService.save(this.proDefinition);
       }
     }
     label259: setJsonString("{success:true}");
     return "success";
   }
 
   public String formTemp()
   {
     StringBuffer sb = new StringBuffer("{success:true");
     Short isUseTemplate = FormDefMapping.NOT_USE_TEMPLATE;
 
     ProDefinition proDefinition = (ProDefinition)this.proDefinitionService.get(this.defId);
 
     if ((proDefinition != null) && (proDefinition.getDeployId() != null)) {
       FormDefMapping fdm = this.formDefMappingService.getByDeployId(proDefinition.getDeployId());
       if (fdm != null) {
         isUseTemplate = fdm.getUseTemplate();
         sb.append(",mappingId:").append(fdm.getMappingId());
       }
     }
     sb.append(",isUseTemplate:" + isUseTemplate + "}");
     setJsonString(sb.toString());
     return "success";
   }
 
   public String saveFm()
   {
     ProDefinition pd = (ProDefinition)this.proDefinitionService.get(this.defId);
     String useTemplate = getRequest().getParameter("useTemplate");
 
     if ((pd != null) && (pd.getDeployId() != null)) {
       FormDefMapping mapping = this.formDefMappingService.getByDeployId(pd.getDeployId());
       Short isUseTemplate = FormDefMapping.NOT_USE_TEMPLATE;
       if ("true".equals(useTemplate)) {
         isUseTemplate = FormDefMapping.USE_TEMPLATE;
       }
       if (mapping != null) {
         mapping.setUseTemplate(isUseTemplate);
       } else {
         mapping = new FormDefMapping();
         mapping.setUseTemplate(isUseTemplate);
         mapping.setDefId(pd.getDefId());
         mapping.setDeployId(pd.getDeployId());
         mapping.setVersionNo(pd.getNewVersion());
       }
       this.formDefMappingService.save(mapping);
 
       if ("true".equals(useTemplate)) {
         List formTemplateList = this.formTemplateService.getByMappingId(mapping.getMappingId());
         if (formTemplateList.size() == 0)
         {
           List<Node> nodes = this.jbpmService.getFormNodesByDeployId(new Long(pd.getDeployId()));
           List nodeNames = new ArrayList();
           for (Node node : nodes) {
             nodeNames.add(node.getName());
           }
           this.formTemplateService.batchAddDefault(nodeNames, mapping);
         }
       }
       setJsonString("{success:true,mappingId:" + mapping.getMappingId() + "}");
     }
 
     return "success";
   }
 
   public String getDecision()
   {
     this.jsonString = "{success:true,data:{decisionExpr:''}}";
     return "success";
   }
 
   public String saveDecision()
   {
     this.jsonString = "{success:true}";
     return "success";
   }
 
   public String getFormMapping()
   {
     ProDefinition pd = (ProDefinition)this.proDefinitionService.get(this.defId);
     StringBuffer sb = new StringBuffer();
     Short isUseTemplate = FormDefMapping.NOT_USE_TEMPLATE;
     if ((pd != null) && (pd.getDeployId() != null)) {
       FormDefMapping localFormDefMapping = this.formDefMappingService.getByDeployId(pd.getDeployId());
     }
 
     return "success";
   }
 
   public String update()
   {
     ProDefinition orgProDefinition = (ProDefinition)this.proDefinitionService.get(this.proDefinition
       .getDefId());
     orgProDefinition.setStatus(this.proDefinition.getStatus());
     this.proDefinitionService.save(orgProDefinition);
 
     setJsonString("{success:true}");
     return "success";
   }
 
   public String checkRun()
   {
     boolean isRun = this.processRunService.checkRun(this.defId).booleanValue();
     String msg = "{success:true}";
     if (isRun)
       msg = "{failure:true,msg:'对不起，该流程正在运行不能设置，请谅解！'}";
     setJsonString(msg);
     return "success";
   }
 
   public String inList()
   {
     PagingBean pb = getInitPagingBean();
 
     List<ProDefinition> list = this.proDefinitionService.findRunningPro(this.proDefinition, ProcessRun.RUN_STATUS_RUNNING, pb);
 
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(pb.getTotalItems()).append(
       ",result:[");
     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
     for (ProDefinition pd : list) {
       buff.append("{defId:'").append(pd.getDefId())
         .append("',subTotal:").append(this.processRunService.countRunningProcess(pd.getDefId()))
         .append(",typeName:'").append(pd.getProType() == null ? "" : pd.getProType().getTypeName())
         .append("',name:'").append(pd.getName())
         .append("',createtime:'").append(sdf.format(pd.getCreatetime()))
         .append("',deployId:'").append(pd.getDeployId())
         .append("',status:").append(pd.getStatus()).append("},");
     }
     if (list.size() > 0) {
       buff.deleteCharAt(buff.length() - 1);
     }
     buff.append("]}");
     this.jsonString = buff.toString();
     return "success";
   }
 }

