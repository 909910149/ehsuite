 package com.htsoft.oa.action.archive;
 
 import com.google.gson.Gson;
 import com.google.gson.reflect.TypeToken;
 import com.htsoft.core.command.QueryFilter;
 import com.htsoft.core.jbpm.jpdl.Node;
 import com.htsoft.core.util.AppUtil;
 import com.htsoft.core.util.XmlUtil;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.model.archive.ArchFlowConf;
 import com.htsoft.oa.model.flow.ProDefinition;
 import com.htsoft.oa.service.archive.ArchFlowConfService;
 import com.htsoft.oa.service.flow.JbpmService;
 import com.htsoft.oa.service.flow.ProDefinitionService;
 import java.lang.reflect.Type;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import java.util.List;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 import org.apache.commons.lang.StringUtils;
 import org.dom4j.Attribute;
 import org.dom4j.Document;
 import org.dom4j.Element;
 import org.jbpm.api.ProcessDefinition;
 
 public class ArchFlowConfAction extends BaseAction
 {
 
   @Resource
   private ArchFlowConfService archFlowConfService;
 
   @Resource
   private JbpmService jbpmService;
 
   @Resource
   private ProDefinitionService proDefinitionService;
   private ArchFlowConf archFlowConf;
   private Long configId;
 
   public Long getConfigId()
   {
     return this.configId;
   }
 
   public void setConfigId(Long configId) {
     this.configId = configId;
   }
 
   public ArchFlowConf getArchFlowConf() {
     return this.archFlowConf;
   }
 
   public void setArchFlowConf(ArchFlowConf archFlowConf) {
     this.archFlowConf = archFlowConf;
   }
 
   public String list()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     List list = this.archFlowConfService.getAll(filter);
 
     Type type = new TypeToken() {  }
     .getType();
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(filter.getPagingBean().getTotalItems()).append(",result:");
 
     Gson gson = new Gson();
     buff.append(gson.toJson(list, type));
     buff.append("}");
 
     this.jsonString = buff.toString();
 
     return "success";
   }
 
   public String multiDel()
   {
     String[] ids = getRequest().getParameterValues("ids");
     if (ids != null) {
       for (String id : ids) {
         this.archFlowConfService.remove(new Long(id));
       }
     }
 
     this.jsonString = "{success:true}";
 
     return "success";
   }
 
   public String get()
   {
     ArchFlowConf sendFlowConf = this.archFlowConfService.getByFlowType(ArchFlowConf.ARCH_SEND_TYPE);
     ArchFlowConf recFlowConf = this.archFlowConfService.getByFlowType(ArchFlowConf.ARCH_REC_TYPE);
     StringBuffer sb = new StringBuffer("{success:true,data:");
     if (sendFlowConf != null)
       sb.append("{'sendProcessId':'" + sendFlowConf.getDefId() + "','sendProcessName':'" + sendFlowConf.getProcessName() + "'");
     else {
       sb.append("{'sendProcessId':'','sendProcessName':''");
     }
     if (recFlowConf != null)
       sb.append(",'recProcessId':'" + recFlowConf.getDefId() + "','recProcessName':'" + recFlowConf.getProcessName() + "'}}");
     else {
       sb.append(",'recProcessId':'','recProcessName':''}}");
     }
     setJsonString(sb.toString());
     return "success";
   }
 
   public String save()
   {
     String sendId = getRequest().getParameter("sendProcessId");
     String sendName = getRequest().getParameter("sendProcessName");
     String recId = getRequest().getParameter("recProcessId");
     String recName = getRequest().getParameter("recProcessName");
     if ((StringUtils.isNotEmpty(sendId)) && (StringUtils.isNotEmpty(sendName))) {
       this.archFlowConf = this.archFlowConfService.getByFlowType(ArchFlowConf.ARCH_SEND_TYPE);
       if (this.archFlowConf == null) {
         this.archFlowConf = new ArchFlowConf();
         this.archFlowConf.setArchType(ArchFlowConf.ARCH_SEND_TYPE);
       }
       this.archFlowConf.setDefId(new Long(sendId));
       this.archFlowConf.setProcessName(sendName);
       this.archFlowConfService.save(this.archFlowConf);
     }
     if ((StringUtils.isNotEmpty(recId)) && (StringUtils.isNotEmpty(recName))) {
       this.archFlowConf = this.archFlowConfService.getByFlowType(ArchFlowConf.ARCH_REC_TYPE);
       if (this.archFlowConf == null) {
         this.archFlowConf = new ArchFlowConf();
         this.archFlowConf.setArchType(ArchFlowConf.ARCH_REC_TYPE);
       }
       this.archFlowConf.setDefId(new Long(recId));
       this.archFlowConf.setProcessName(recName);
       this.archFlowConfService.save(this.archFlowConf);
     }
     setJsonString("{success:true}");
     return "success";
   }
 
   public String getFlow()
   {
     String type = getRequest().getParameter("flowType");
     StringBuffer sb = new StringBuffer();
     if (type.equals(ArchFlowConf.ARCH_SEND_TYPE.toString())) {
       this.archFlowConf = this.archFlowConfService.getByFlowType(ArchFlowConf.ARCH_SEND_TYPE);
     }
     else {
       this.archFlowConf = this.archFlowConfService.getByFlowType(ArchFlowConf.ARCH_REC_TYPE);
     }
     if (this.archFlowConf != null)
       sb.append("{success:true,defId:").append(this.archFlowConf.getDefId()).append("}");
     else {
       sb.append("{success:false,'message':'你还没设定流程'}");
     }
     setJsonString(sb.toString());
     return "success";
   }
 
   public String setting()
   {
     String defId = getRequest().getParameter("defId");
 
     String settingType = getRequest().getParameter("settingType");
     Short archType = null;
     if (settingType.equals("send"))
       archType = ArchFlowConf.ARCH_SEND_TYPE;
     else {
       archType = ArchFlowConf.ARCH_REC_TYPE;
     }
     if (StringUtils.isNotEmpty(defId)) {
       ProDefinition proDefintion = (ProDefinition)this.proDefinitionService.get(new Long(defId));
       ProcessDefinition processDefinition = this.jbpmService.getProcessDefinitionByDefId(new Long(defId));
       List<Node> nodes = this.jbpmService.getTaskNodesByDefId(new Long(defId), false, true);
 
       if (archType.shortValue() == ArchFlowConf.ARCH_SEND_TYPE.shortValue())
         this.archFlowConf = this.archFlowConfService.getByFlowType(ArchFlowConf.ARCH_SEND_TYPE);
       else {
         this.archFlowConf = this.archFlowConfService.getByFlowType(ArchFlowConf.ARCH_REC_TYPE);
       }
 
       String archMenu = AppUtil.getAppAbsolutePath() + "/js/menu/menu-archives.xml";
       Document doc = XmlUtil.load(archMenu);
 
       Element menus = doc.getRootElement();
 
       Element firstItems = menus.element("Items");
 
       if (this.archFlowConf == null) {
         this.archFlowConf = new ArchFlowConf();
       } else {
         ProcessDefinition oldProDef = this.jbpmService.getProcessDefinitionByDefId(this.archFlowConf.getDefId());
         List<Element> list = firstItems.elements();
         for (Element el : list) {
           if (el.attribute("id").getData().equals(oldProDef.getKey())) {
             el.getParent().remove(el);
           }
         }
 
       }
 
       this.archFlowConf.setArchType(archType);
       this.archFlowConf.setDefId(new Long(defId));
       this.archFlowConf.setProcessName(proDefintion.getName());
       this.archFlowConfService.save(this.archFlowConf);
 
       Element dynamicItems = firstItems.addElement("Items");
       dynamicItems.addAttribute("id", processDefinition.getKey());
       dynamicItems.addAttribute("text", proDefintion.getName());
 
       SimpleDateFormat date = new SimpleDateFormat("yyyyMMddHHmmss");
       String idNo = date.format(new Date());
 
       Element start = dynamicItems.addElement("Item");
       start.addAttribute("id", processDefinition.getKey() + idNo);
       start.addAttribute("text", "流程启动");
       start.addAttribute("defId", defId);
       start.addAttribute("flowName", proDefintion.getName());
 
       if (archType.shortValue() == ArchFlowConf.ARCH_REC_TYPE.shortValue()) {
         StringBuffer params = new StringBuffer("{defId:");
         params.append(defId)
           .append(",flowName:'")
           .append(proDefintion.getName())
           .append("'}");
         Element sign = dynamicItems.addElement("Item");
         sign.addAttribute("id", "ArchivesSignView");
         sign.addAttribute("text", "公文签收待办");
         sign.addAttribute("iconCls", "menu-archive-sign");
         sign.addAttribute("params", params.toString());
       }
 
       int count = 1;
       for (Node node : nodes) {
         Element dynamicItem = dynamicItems.addElement("Item");
         dynamicItem.addAttribute("id", processDefinition.getKey() + "Node" + count);
         dynamicItem.addAttribute("text", node.getName());
         dynamicItem.addAttribute("flowNode", "true");
         count++;
       }
 
       XmlUtil.docToXmlFile(doc, archMenu);
     }
 
     return "success";
   }
 }

