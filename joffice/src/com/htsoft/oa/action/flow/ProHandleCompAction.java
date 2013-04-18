 package com.htsoft.oa.action.flow;
 
 import com.google.gson.Gson;
 import com.google.gson.GsonBuilder;
 import com.google.gson.reflect.TypeToken;
 import com.htsoft.core.command.QueryFilter;
 import com.htsoft.core.util.BeanUtil;
 import com.htsoft.core.util.XmlUtil;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.model.flow.ProDefinition;
 import com.htsoft.oa.model.flow.ProHandleComp;
 import com.htsoft.oa.service.flow.JbpmService;
 import com.htsoft.oa.service.flow.ProDefinitionService;
 import com.htsoft.oa.service.flow.ProHandleCompService;
 import java.io.PrintStream;
 import java.lang.reflect.Type;
 import java.util.Iterator;
 import java.util.List;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 import org.apache.commons.lang.StringUtils;
 import org.apache.commons.logging.Log;
 import org.dom4j.Attribute;
 import org.dom4j.Document;
 import org.dom4j.Element;
 
 public class ProHandleCompAction extends BaseAction
 {
 
   @Resource
   private ProHandleCompService proHandleCompService;
 
   @Resource
   private ProDefinitionService proDefinitionService;
 
   @Resource
   private JbpmService jbpmService;
   private ProHandleComp proHandleComp;
   private Long handleId;
 
   public Long getHandleId()
   {
     return this.handleId;
   }
 
   public void setHandleId(Long handleId) {
     this.handleId = handleId;
   }
 
   public ProHandleComp getProHandleComp() {
     return this.proHandleComp;
   }
 
   public void setProHandleComp(ProHandleComp proHandleComp) {
     this.proHandleComp = proHandleComp;
   }
 
   public String getDecision()
   {
     String defId = getRequest().getParameter("defId");
     String activityName = getRequest().getParameter("activityName");
     ProDefinition proDefinition = (ProDefinition)this.proDefinitionService.get(new Long(defId));
     List proHandleComps = this.proHandleCompService.getByDeployIdActivityNameHandleType(proDefinition.getDeployId(), activityName, ProHandleComp.HANDLE_TYPE_HANDLER);
     String exeCode = "";
     if (proHandleComps.size() > 0) {
       ProHandleComp proHandleComp = (ProHandleComp)proHandleComps.get(0);
       exeCode = proHandleComp.getExeCode();
     }
     Gson gson = new Gson();
     this.jsonString = ("{success:true,data:{exeCode:" + gson.toJson(exeCode) + "}}");
 
     return "success";
   }
 
   public String getCode() {
     HttpServletRequest request = getRequest();
     String activityName = request.getParameter("activityName");
     String defId = request.getParameter("defId");
     String includeDecision = request.getParameter("includeDecision");
 
     String startExeCode = "";
     String endExeCode = "";
     String decisonExeCode = "";
     ProDefinition proDefinition = (ProDefinition)this.proDefinitionService.get(new Long(defId));
 
     ProHandleComp startEventHandle = this.proHandleCompService.getProHandleComp(proDefinition.getDeployId(), activityName, "start");
     ProHandleComp endEventHandle = this.proHandleCompService.getProHandleComp(proDefinition.getDeployId(), activityName, "end");
 
     if ((startEventHandle != null) && (StringUtils.isNotEmpty(startEventHandle.getExeCode()))) {
       startExeCode = startEventHandle.getExeCode();
     }
 
     if ((endEventHandle != null) && (StringUtils.isNotEmpty(endEventHandle.getExeCode()))) {
       endExeCode = endEventHandle.getExeCode();
     }
 
     if ("true".equals(includeDecision)) {
       List proHandleCompDecisons = this.proHandleCompService.getByDeployIdActivityNameHandleType(proDefinition.getDeployId(), activityName, ProHandleComp.HANDLE_TYPE_HANDLER);
       if (proHandleCompDecisons.size() > 0) {
         ProHandleComp proHandleComp = (ProHandleComp)proHandleCompDecisons.get(0);
         if (StringUtils.isNotEmpty(proHandleComp.getExeCode())) {
           decisonExeCode = proHandleComp.getExeCode();
         }
       }
     }
 
     Gson gson = new Gson();
     this.jsonString = ("{success:true,startExeCode:" + gson.toJson(startExeCode) + ",endExeCode:" + gson.toJson(endExeCode) + ",decisionExeCode:" + gson.toJson(decisonExeCode) + "}");
 
     return "success";
   }
 
   public String saveCode()
   {
     HttpServletRequest request = getRequest();
 
     String startExeCode = request.getParameter("startExeCode");
     String endExeCode = request.getParameter("endExeCode");
     String decisionExeCode = request.getParameter("decisionExeCode");
 
     String activityName = request.getParameter("activityName");
     String defId = request.getParameter("defId");
 
     ProDefinition proDefinition = (ProDefinition)this.proDefinitionService.get(new Long(defId));
 
     String defXml = this.jbpmService.getDefinitionXmlByDpId(proDefinition.getDeployId());
 
     Document document = XmlUtil.stringToDocument(defXml);
 
     ProHandleComp startEventHandle = this.proHandleCompService.getProHandleComp(proDefinition.getDeployId(), activityName, "start");
     if (startEventHandle == null) {
       startEventHandle = new ProHandleComp();
       startEventHandle.setActivityName(activityName);
       startEventHandle.setDeployId(proDefinition.getDeployId());
       startEventHandle.setEventName("start");
       startEventHandle.setHandleType(ProHandleComp.HANDLE_TYPE_LISTENER);
     }
     startEventHandle.setExeCode(startExeCode);
 
     this.proHandleCompService.save(startEventHandle);
     if (StringUtils.isNotEmpty(startExeCode)) {
       writeXml(document, activityName, "start");
     }
 
     ProHandleComp endEventHandle = this.proHandleCompService.getProHandleComp(proDefinition.getDeployId(), activityName, "end");
     if (endEventHandle == null) {
       endEventHandle = new ProHandleComp();
       endEventHandle.setActivityName(activityName);
       endEventHandle.setDeployId(proDefinition.getDeployId());
       endEventHandle.setEventName("end");
       endEventHandle.setHandleType(ProHandleComp.HANDLE_TYPE_LISTENER);
     }
     endEventHandle.setExeCode(endExeCode);
 
     this.proHandleCompService.save(endEventHandle);
     if (StringUtils.isNotEmpty(endExeCode)) {
       writeXml(document, activityName, "end");
     }
 
     List proHandleCompDecisons = this.proHandleCompService.getByDeployIdActivityNameHandleType(proDefinition.getDeployId(), activityName, ProHandleComp.HANDLE_TYPE_HANDLER);
     ProHandleComp decisionHandle = null;
     if (proHandleCompDecisons.size() > 0) {
       decisionHandle = (ProHandleComp)proHandleCompDecisons.get(0);
     } else {
       decisionHandle = new ProHandleComp();
       decisionHandle.setActivityName(activityName);
       decisionHandle.setDeployId(proDefinition.getDeployId());
       decisionHandle.setHandleType(ProHandleComp.HANDLE_TYPE_HANDLER);
     }
 
     decisionHandle.setExeCode(decisionExeCode);
     this.proHandleCompService.save(decisionHandle);
     if (StringUtils.isNotEmpty(decisionExeCode)) {
       writeXml(document, activityName, "decision");
     }
 
     defXml = XmlUtil.docToString(document);
 
     this.jbpmService.wirteDefXml(proDefinition.getDeployId(), defXml);
     proDefinition.setDefXml(defXml);
     this.proDefinitionService.save(proDefinition);
 
     return "success";
   }
 
   public String saveDecision() {
     String exeCode = getRequest().getParameter("exeCode");
     String defId = getRequest().getParameter("defId");
     String activityName = getRequest().getParameter("activityName");
     ProDefinition proDefinition = (ProDefinition)this.proDefinitionService.get(new Long(defId));
     List proHandleComps = this.proHandleCompService.getByDeployIdActivityNameHandleType(proDefinition.getDeployId(), activityName, ProHandleComp.HANDLE_TYPE_HANDLER);
     ProHandleComp proHandleComp = null;
     if (proHandleComps.size() > 0) {
       proHandleComp = (ProHandleComp)proHandleComps.get(0);
     } else {
       proHandleComp = new ProHandleComp();
       proHandleComp.setActivityName(activityName);
       proHandleComp.setDeployId(proDefinition.getDeployId());
       proHandleComp.setHandleType(ProHandleComp.HANDLE_TYPE_HANDLER);
     }
 
     proHandleComp.setExeCode(exeCode);
 
     this.proHandleCompService.save(proHandleComp);
 
     String defXml = this.jbpmService.getDefinitionXmlByDpId(proHandleComp.getDeployId());
 
     Document document = XmlUtil.stringToDocument(defXml);
     Element rootEl = document.getRootElement();
 
     Iterator it = rootEl.elementIterator();
     while (it.hasNext()) {
       Element el = (Element)it.next();
       if ("decision".equals(el.getName())) {
         String name = el.attributeValue("name");
         if (name.equals(activityName)) {
           Attribute exprAttr = el.attribute("expr");
           if (exprAttr != null) {
             el.remove(exprAttr);
           }
           Element handlerEl = (Element)el.selectSingleNode("./handler");
           if (handlerEl != null) break;
           handlerEl = el.addElement("handler");
           handlerEl.addAttribute("class", "com.htsoft.oa.workflow.handler.DecisionHandlerImpl");
 
           break;
         }
       }
     }
 
     defXml = document.asXML();
     this.logger.info("xml:" + defXml);
     try {
       defXml = new String(defXml.getBytes(), "UTF-8");
     } catch (Exception ex) {
       this.logger.error(ex);
     }
     this.logger.debug("lastXml:" + defXml);
 
     this.jbpmService.wirteDefXml(proHandleComp.getDeployId(), defXml);
     proDefinition.setDefXml(defXml);
     this.proDefinitionService.save(proDefinition);
 
     return "success";
   }
 
   private void writeXml(Document document, String activityName, String handleType) {
     Element rootEl = document.getRootElement();
     Iterator it = rootEl.elementIterator();
     Element el;
     while (it.hasNext()) {
       el = (Element)it.next();
       if ("decision".equals(handleType)) {
         if ("decision".equals(el.getName())) {
           String name = el.attributeValue("name");
           if (name.equals(activityName)) {
             Attribute exprAttr = el.attribute("expr");
             if (exprAttr != null) {
               el.remove(exprAttr);
             }
             boolean isHandlerExist = false;
             Iterator subIt = el.elementIterator();
             while (subIt.hasNext()) {
               Element subEl = (Element)subIt.next();
               if ("handler".equals(subEl.getName())) {
                 isHandlerExist = true;
                 break;
               }
             }
             if (isHandlerExist) break;
             Element handlerEl = el.addElement("handler");
             handlerEl.addAttribute("class", "com.htsoft.oa.workflow.handler.DecisionHandlerImpl");
 
             break;
           }
         }
       } else if ("start".equals(handleType)) {
         String name = el.attributeValue("name");
         if (name.equals(activityName)) {
           boolean isStartEventExist = false;
           Iterator subIt = el.elementIterator();
           while (subIt.hasNext()) {
             Element subEl = (Element)subIt.next();
             if ((!"on".equals(subEl.getName())) || 
               (!"start".equals(subEl.attributeValue("event")))) continue;
             isStartEventExist = true;
             break;
           }
 
           if (isStartEventExist) break;
           Element startEvent = el.addElement("on");
           Element onEl = startEvent.addAttribute("event", "start");
           Element listEl = onEl.addElement("event-listener");
           listEl.addAttribute("class", "com.htsoft.oa.workflow.event.NodeEventListener");
           Element fieldEl = listEl.addElement("field");
           fieldEl.addAttribute("name", "eventType");
           Element stringEl = fieldEl.addElement("string");
           stringEl.addAttribute("value", "start");
 
           break;
         }
       } else if ("end".equals(handleType)) {
         String name = el.attributeValue("name");
         if (name.equals(activityName)) {
           boolean isEndEventExist = false;
           Iterator subIt = el.elementIterator();
           while (subIt.hasNext()) {
             Element subEl = (Element)subIt.next();
             if ((!"on".equals(subEl.getName())) || 
               (!"end".equals(subEl.attributeValue("event")))) continue;
             isEndEventExist = true;
             break;
           }
 
           if (isEndEventExist) break;
           Element eventEvent = el.addElement("on");
           Element onEl = eventEvent.addAttribute("event", "end");
           Element listEl = onEl.addElement("event-listener");
           listEl.addAttribute("class", "com.htsoft.oa.workflow.event.NodeEventListener");
           Element fieldEl = listEl.addElement("field");
           fieldEl.addAttribute("name", "eventType");
           Element stringEl = fieldEl.addElement("string");
           stringEl.addAttribute("value", "end");
 
           break;
         }
       }
     }
   }
 
   public String list()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     List list = this.proHandleCompService.getAll(filter);
 
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
         this.proHandleCompService.remove(new Long(id));
       }
     }
 
     this.jsonString = "{success:true}";
 
     return "success";
   }
 
   public String get()
   {
     ProHandleComp proHandleComp = (ProHandleComp)this.proHandleCompService.get(this.handleId);
 
     Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
 
     StringBuffer sb = new StringBuffer("{success:true,data:");
     sb.append(gson.toJson(proHandleComp));
     sb.append("}");
     setJsonString(sb.toString());
 
     return "success";
   }
 
   public String save()
   {
     if (this.proHandleComp.getHandleId() == null) {
       this.proHandleCompService.save(this.proHandleComp);
     } else {
       ProHandleComp orgProHandleComp = (ProHandleComp)this.proHandleCompService.get(this.proHandleComp.getHandleId());
       try {
         BeanUtil.copyNotNullProperties(orgProHandleComp, this.proHandleComp);
         this.proHandleCompService.save(orgProHandleComp);
       } catch (Exception ex) {
         this.logger.error(ex.getMessage());
       }
     }
     setJsonString("{success:true}");
     return "success";
   }
 
   public static void main(String[] args)
   {
     String file = "D:/tools/jbpm/jbpm-4.4/jbpm-4.4/examples/src/org/jbpm/examples/decision/expression/process.jpdl.xml";
     Document document = XmlUtil.load(file);
     System.out.println("xml:" + document.asXML());
     Element rootEl = document.getRootElement();
 
     Iterator it = rootEl.elementIterator();
 
     while (it.hasNext()) {
       Element el = (Element)it.next();
       System.out.println("el:" + el.getName());
       if ("decision".equals(el.getName())) {
         String name = el.attributeValue("name");
         if (!"evaluate document".equals(name))
           continue;
         Attribute exprAttr = el.attribute("expr");
         if (exprAttr != null) {
           el.remove(exprAttr);
         }
         Element handlerEl = (Element)el.selectSingleNode("./handler");
         if (handlerEl != null) break;
         handlerEl = el.addElement("handler");
         handlerEl.addAttribute("class", "com.htsoft.oa.workflow.handler.DecisionHandlerImpl");
 
         break;
       }
 
     }
 
     System.out.println("xml:" + document.asXML());
   }
 }

