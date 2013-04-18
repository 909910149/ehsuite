 package com.htsoft.oa.action.bpm;
 
 import com.htsoft.core.util.XmlUtil;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.oa.service.bpm.ILog.factory.BpmFactory;
 import java.io.PrintStream;
 import java.util.Iterator;
 import java.util.List;
 import javax.servlet.http.HttpServletRequest;
 import org.dom4j.Document;
 import org.dom4j.Element;
 
 public class BpmXMLAction extends BaseAction
 {
   public String change()
   {
     String xml = getRequest().getParameter("xmlString");
     String text = "";
     if ((xml != null) && (!xml.equals(""))) {
       Document document = XmlUtil.stringToDocument(xml);
       Element element = document.getRootElement();
 
       BpmFactory factory = new BpmFactory(document);
 
       Iterator it = element.elements().iterator();
       text = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n <process name=\"test\" xmlns=\"http://jbpm.org/4.4/jpdl\">";
       while (it.hasNext()) {
         Element el = (Element)it.next();
         String str = factory.getInfo(el, el.getName());
         text = text + str;
       }
       text = text + "</process>";
       System.out.println(text);
     } else {
       System.out.println("没有执行这个方法");
     }
     setJsonString("{success:true,jbpmXML:'" + text + "'}");
     return "success";
   }
 }

