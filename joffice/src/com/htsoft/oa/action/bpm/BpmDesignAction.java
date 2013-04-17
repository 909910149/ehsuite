 package com.htsoft.oa.action.bpm;
 
 import com.htsoft.core.util.ContextUtil;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.oa.model.flow.ProDefinition;
 import com.htsoft.oa.service.flow.ProDefinitionService;
 import com.htsoft.oa.service.system.GlobalTypeService;
 import java.util.ArrayList;
 import java.util.List;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 import org.apache.commons.lang.StringUtils;
 
 public class BpmDesignAction extends BaseAction
 {
 
   @Resource
   private ProDefinitionService proDefinitionService;
 
   @Resource
   private GlobalTypeService globalTypeService;
   private Long defId;
   private String name;
 
   public Long getDefId()
   {
     return this.defId;
   }
 
   public void setDefId(Long defId) {
     this.defId = defId;
   }
 
   public String getName() {
     return this.name;
   }
 
   public void setName(String name) {
     this.name = name;
   }
 
   public String execute() throws Exception
   {
     HttpServletRequest request = getRequest();
     String defId = request.getParameter("defId");
     if (StringUtils.isNotEmpty(defId)) {
       ProDefinition proDefintion = (ProDefinition)this.proDefinitionService.get(new Long(defId));
       request.setAttribute("proDefinition", proDefintion);
       request.setAttribute("title", proDefintion.getName());
     } else {
       request.setAttribute("title", "未命名");
     }
     Long uId = ContextUtil.getCurrentUserId();
     List record = new ArrayList();
     String catKey = "FLOW";
 
     record = this.globalTypeService.getByCatKeyUserId(ContextUtil.getCurrentUser(), catKey);
 
     request.setAttribute("record", record);
     request.setAttribute("uId", uId);
 
     return "success";
   }
 }

