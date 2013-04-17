var ProDefinitionForm=function(e,d){this.defId=e;this.typeId=d;this.setup();var f=new Ext.Window({id:"ProDefinitionFormWin",title:"流程定义详细信息",iconCls:"menu-flowNew",width:500,height:420,modal:true,layout:"anchor",plain:true,bodyStyle:"padding:5px;",buttonAlign:"center",items:[this.formPanel],buttons:[{text:"保存并发布",iconCls:"btn-save",handler:function(){var a=Ext.getCmp("ProDefinitionForm");if(a.getForm().isValid()){a.getForm().submit({method:"post",params:{deploy:true},waitMsg:"正在提交数据...",success:function(c,b){Ext.ux.Toast.msg("操作信息","成功信息保存！");Ext.getCmp("ProDefinitionGrid").getStore().reload();f.close();},failure:function(c,b){Ext.MessageBox.show({title:"操作信息",msg:"信息保存出错，请联系管理员！",buttons:Ext.MessageBox.OK,icon:"ext-mb-error"});}});}}},{iconCls:"btn-cancel",text:"取消",handler:function(){f.close();}}]});f.show();};ProDefinitionForm.prototype.setup=function(){var f=this.typeId;var d=__ctxPath+"/flow/listProType.do";this.formPanel=new Ext.FormPanel({url:__ctxPath+"/flow/saveProDefinition.do",layout:"form",id:"ProDefinitionForm",frame:true,defaults:{widht:400,anchor:"100%,100%"},formId:"ProDefinitionFormId",defaultType:"textfield",items:[{name:"proDefinition.defId",xtype:"hidden",value:this.defId==null?"":this.defId},{xtype:"compositefield",fieldLabel:"流程类型",items:[{name:"proDefinition.proTypeName",xtype:"textfield",width:250,readOnly:true,allowBlank:false},{xtype:"button",text:"选择类型",iconCls:"btn-select",scope:this,handler:function(){var a=this.formPanel;new GlobalTypeSelector({catKey:"FLOW",isSingle:true,callback:function(b,c){a.getCmpByName("proDefinition.proTypeId").setValue(b);a.getCmpByName("proDefinition.proTypeName").setValue(c);}}).show();}}]},{fieldLabel:"流程状态",hiddenName:"proDefinition.status",xtype:"combo",allowBlank:false,editable:false,mode:"local",triggerAction:"all",store:[["0","禁用"],["1","激活"]],value:1},{xtype:"hidden",id:"proTypeId",name:"proDefinition.proTypeId"},{fieldLabel:"流程的名称",name:"proDefinition.name"},{fieldLabel:"描述",xtype:"textarea",name:"proDefinition.description"},{fieldLabel:"流程定义的XML",name:"proDefinition.defXml",xtype:"textarea",height:200}]});var e=(this.defId==null||this.defId=="undefined")?"":this.defId;var f=(this.typeId==null||this.typeId=="undefined")?"":this.typeId;this.formPanel.loadData({url:__ctxPath+"/flow/getProDefinition.do?defId="+e+"&proTypeId="+f,root:"data",preName:"proDefinition",scope:this,success:function(i,a){var j=Ext.util.JSON.decode(i.responseText);if(j){var b=j.data.proType;if(b!=null){this.formPanel.getCmpByName("proDefinition.proTypeName").setValue(b.typeName);this.formPanel.getCmpByName("proDefinition.proTypeId").setValue(b.proTypeId);}var c=j.data.deployId;if(c){this.formPanel.getCmpByName("proDefinition.name").getEl().dom.readOnly=true;}}}});return this.formPanel;};