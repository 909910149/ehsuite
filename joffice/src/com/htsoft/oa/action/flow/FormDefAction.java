 package com.htsoft.oa.action.flow;
 
 import com.google.gson.Gson;
 import com.google.gson.GsonBuilder;
 import com.google.gson.reflect.TypeToken;
 import com.htsoft.core.command.QueryFilter;
 import com.htsoft.core.util.AppUtil;
 import com.htsoft.core.util.FileUtil;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.model.flow.FieldRights;
 import com.htsoft.oa.model.flow.FormDef;
 import com.htsoft.oa.model.flow.FormDefMapping;
 import com.htsoft.oa.model.flow.FormTable;
 import com.htsoft.oa.model.flow.FormTableItem;
 import com.htsoft.oa.model.flow.ProDefinition;
 import com.htsoft.oa.service.flow.FieldRightsService;
 import com.htsoft.oa.service.flow.FormDefMappingService;
 import com.htsoft.oa.service.flow.FormDefService;
 import com.htsoft.oa.service.flow.FormTableGenService;
 import com.htsoft.oa.service.flow.FormTableService;
 import com.htsoft.oa.service.flow.ProDefinitionService;
 import java.lang.reflect.Type;
 import java.util.HashMap;
 import java.util.Iterator;
 import java.util.List;
 import java.util.Map;
 import java.util.Set;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 import org.apache.commons.lang.StringUtils;
 
 public class FormDefAction extends BaseAction
 {
 
   @Resource
   private FormDefService formDefService;
 
   @Resource
   private FormTableGenService formTableGenService;
 
   @Resource
   private FormTableService formTableService;
 
   @Resource
   private ProDefinitionService proDefinitionService;
 
   @Resource
   private FormDefMappingService formDefMappingService;
 
   @Resource
   private FieldRightsService fieldRightsService;
   private FormDef formDef;
   private FormTable formTable;
   private FormTable formTable1;
   private Long formDefId;
   private Long defId;
 
   public Long getFormDefId()
   {
     return this.formDefId;
   }
 
   public void setFormDefId(Long formDefId) {
     this.formDefId = formDefId;
   }
 
   public FormDef getFormDef() {
     return this.formDef;
   }
 
   public void setFormDef(FormDef formDef) {
     this.formDef = formDef;
   }
 
   public FormTable getFormTable() {
     return this.formTable;
   }
 
   public void setFormTable(FormTable formTable) {
     this.formTable = formTable;
   }
 
   public FormTable getFormTable1() {
     return this.formTable1;
   }
 
   public void setFormTable1(FormTable formTable1) {
     this.formTable1 = formTable1;
   }
 
   public Long getDefId() {
     return this.defId;
   }
 
   public void setDefId(Long defId) {
     this.defId = defId;
   }
 
   public String list()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     List<FormDef> list = this.formDefService.getAll(filter);
 
     Type type = new TypeToken() {
     }.getType();
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(filter.getPagingBean().getTotalItems()).append(
       ",result:");
 
     Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
       .create();
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
         this.formDefService.remove(new Long(id));
       }
     }
 
     this.jsonString = "{success:true}";
 
     return "success";
   }
 
   public String get()
   {
     FormDef formDef = (FormDef)this.formDefService.get(this.formDefId);
 
     Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
 
     StringBuffer sb = new StringBuffer("{success:true,data:");
     sb.append(gson.toJson(formDef));
     sb.append("}");
     setJsonString(sb.toString());
 
     return "success";
   }
 
   public String gen()
   {
     FormDef formDef = (FormDef)this.formDefService.get(this.formDefId);
     Set tables = formDef.getFormTables();
     if (tables.size() > 0) {
       FormTable[] formTables = new FormTable[tables.size()];
       Iterator it = tables.iterator();
       int i = 0;
       while (it.hasNext()) {
         formTables[(i++)] = ((FormTable)it.next());
       }
       if (this.formTableGenService.genBean(formTables))
         setJsonString("{success:true}");
       else {
         setJsonString("{failure:true}");
       }
       formDef.setIsGen(FormDef.HAS_GEN);
       this.formDefService.save(formDef);
     }
 
     return "success";
   }
 
   public String genAll()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     filter.addFilter("Q_formDef.status_SN_EQ", FormDef.HAS_Pub.toString());
     List tables = this.formTableService.getAll(filter);
     if (tables.size() > 0) {
       FormTable[] formTables = new FormTable[tables.size()];
       Iterator it = tables.iterator();
       int i = 0;
       while (it.hasNext()) {
         formTables[(i++)] = ((FormTable)it.next());
       }
       if (this.formTableGenService.genBean(formTables))
         setJsonString("{success:true}");
       else {
         setJsonString("{failure:true}");
       }
 
     }
 
     return "success";
   }
 
   public String save()
   {
     Map datas = new HashMap();
     String tablesReq = getRequest().getParameter("objs");
     if (StringUtils.isNotEmpty(tablesReq)) {
       Gson gson = new Gson();
       FormTableItem[] tables = (FormTableItem[])gson.fromJson(tablesReq, com.htsoft.oa.model.flow.FormTableItem[].class);
       for (FormTableItem formTableItem : tables) {
         FormTable formTable = new FormTable();
         formTable.setTableId(formTableItem.getTableId());
         formTable.setTableKey(formTableItem.getTableKey());
         formTable.setTableName(formTableItem.getTableName());
         formTable.setIsMain(formTableItem.getIsMain());
         datas.put(formTable, formTableItem.getFields());
 
         List formTables = this.formTableService.findByTableKey(formTableItem.getTableKey());
         if ((formTables.size() > 2) || ((formTables.size() == 1) && (formTableItem.getTableId() == null))) {
           setJsonString("{success:false,msg:'" + formTableItem.getTableKey() + "'}");
           return "success";
         }
 
       }
 
     }
 
     this.formDef = this.formDefService.saveFormDef(this.formDef, datas);
 
     setJsonString("{success:true}");
     return "success";
   }
 
   public String add()
   {
     String msg = "{success:true}";
 
     ProDefinition pd = (ProDefinition)this.proDefinitionService.get(this.defId);
 
     FormDefMapping fm = this.formDefMappingService.getByDeployId(pd.getDeployId());
     FormDef fd = (FormDef)this.formDefService.get(this.formDefId);
     if ((fm != null) && (fm.getFormDefId() == this.formDefId)) {
       msg = "{failure:true,msg:'对不起，该表单信息已经添加，请选择其他表单信息！'}";
     } else if (fm == null)
     {
       ProDefinition pdf = (ProDefinition)this.proDefinitionService.get(this.defId);
 
       FormDefMapping fdm = new FormDefMapping();
       fdm.setDeployId(pdf.getDeployId());
       fdm.setDefId(this.defId);
       fdm.setFormDef(fd);
       fdm.setVersionNo(pdf.getNewVersion());
 
       this.formDefMappingService.save(fdm);
     }
     else
     {
       List<FieldRights> rights = this.fieldRightsService.getByMappingId(fm.getMappingId());
       if (rights.size() > 0) {
         for (FieldRights right : rights) {
           this.fieldRightsService.remove(right);
         }
       }
       fm.setFormDef(fd);
 
       this.formDefMappingService.save(fm);
     }
     setJsonString(msg);
     return "success";
   }
 
   public String check() {
     boolean isHas = this.formDefMappingService.formDefHadMapping(this.formDefId);
     if (isHas)
       setJsonString("{success:true,msg:'该表单已经同相应的流程关联了！'}");
     else {
       setJsonString("{success:false}");
     }
     return "success";
   }
 
   public String saveVmXml()
   {
     String defId = getRequest().getParameter("defId");
     String activityName = getRequest().getParameter("activityName");
 
     String vmSources = getRequest().getParameter("vmSources");
 
     ProDefinition proDefinition = (ProDefinition)this.proDefinitionService.get(new Long(defId));
     String filePath = AppUtil.getAppAbsolutePath() + "/WEB-INF/FlowForm/" + proDefinition.getName() + "/" + proDefinition.getNewVersion() + activityName;
 
     String vmFilePath = filePath + ".vm";
 
     FileUtil.writeFile(vmFilePath, vmSources);
 
     setJsonString("{success:true}");
 
     return "success";
   }
 
   public String getVmXml()
   {
     String defId = getRequest().getParameter("defId");
     String activityName = getRequest().getParameter("activityName");
 
     ProDefinition proDefinition = (ProDefinition)this.proDefinitionService.get(new Long(defId));
     String filePath = AppUtil.getAppAbsolutePath() + "/WEB-INF/FlowForm/" + proDefinition.getName() + "/" + proDefinition.getNewVersion() + "/" + activityName;
     String vmFilePath = filePath + ".vm";
     String vmSources = FileUtil.readFile(vmFilePath);
 
     Gson gson = new Gson();
 
     setJsonString("{success:true,vmSources:" + gson.toJson(vmSources) + "}");
 
     return "success";
   }
 }

