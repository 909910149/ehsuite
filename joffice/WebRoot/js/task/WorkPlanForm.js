var WorkPlanForm=function(f){this.planId=f;var e=this.setup();var d=new Ext.Window({id:"WorkPlanFormWin",title:"工作计划详细信息",width:880,height:450,modal:true,layout:"anchor",plain:true,bodyStyle:"padding:5px;",buttonAlign:"center",items:[this.setup()],buttons:[{text:"保存",iconCls:"btn-save",handler:function(){var a=Ext.getCmp("WorkPlanForm");if(a.getForm().isValid()){a.getForm().submit({method:"post",waitMsg:"正在提交数据...",success:function(c,b){Ext.ux.Toast.msg("操作信息","成功保存信息！");Ext.getCmp("WorkPlanGrid").getStore().reload();d.close();},failure:function(c,b){Ext.MessageBox.show({title:"操作信息",msg:"信息保存出错，请联系管理员！",buttons:Ext.MessageBox.OK,icon:"ext-mb-error"});d.close();}});}}},{text:"取消",iconCls:"btn-cancel",handler:function(){d.close();}}]});d.show();};WorkPlanForm.prototype.setup=function(){var b=new Ext.FormPanel({url:__ctxPath+"/task/saveWorkPlan.do",layout:"column",id:"WorkPlanForm",frame:true,defaults:{anchor:"98%,98%"},formId:"WorkPlanFormId",defaultType:"textfield",items:[{name:"workPlan.planId",id:"planId",xtype:"hidden",value:this.planId==null?"":this.planId},{name:"issueScopeIds",id:"issueScopeIds",xtype:"hidden"},{name:"participantsIds",id:"participantsIds",xtype:"hidden"},{name:"principalIds",id:"principalIds",xtype:"hidden"},{layout:"form",defaultType:"textfield",columnWidth:0.5,xtype:"container",labelWidth:60,items:[{fieldLabel:"计划名称",name:"workPlan.planName",width:348,id:"planName"},{xtype:"container",height:26,layout:"column",style:"padding-left:0px;",defaultType:"label",items:[{text:"时间范围:",width:61,style:"padding-left:0px;padding-top:3px;"},{text:"从",style:"padding-left:0px;padding-top:3px;"},{xtype:"datefield",width:149,format:"Y-m-d",editable:false,name:"workPlan.startTime",id:"startTime"},{text:"至",style:"padding-left:0px;padding-top:3px;"},{xtype:"datefield",width:149,format:"Y-m-d",editable:false,name:"workPlan.endTime",id:"endTime"}]},{fieldLabel:"计划内容",name:"workPlan.planContent",xtype:"htmleditor",height:250,width:348,id:"planContent"},{layout:"column",style:"padding-left:0px;",width:500,xtype:"container",items:[{columnWidth:0.7,style:"padding-left:0px;",layout:"form",items:[{fieldLabel:"附件",xtype:"panel",id:"planFilePanel",frame:true,height:80,autoScroll:true,html:""}]},{columnWidth:0.3,items:[{xtype:"button",text:"添加附件",handler:function(){var a=App.createUploadDialog({file_cat:"task",callback:function(g){var j=Ext.getCmp("planFileIds");var h=Ext.getCmp("planFilePanel");for(var i=0;i<g.length;i++){if(j.getValue()!=""){j.setValue(j.getValue()+",");}j.setValue(j.getValue()+g[i].fileId);Ext.DomHelper.append(h.body,'<span><a href="#" onclick="FileAttachDetail.show('+g[i].fileId+')">'+g[i].fileName+'</a> <img class="img-delete" src="'+__ctxPath+'/images/system/delete.gif" onclick="removePlanFile(this,'+g[i].fileId+')"/>&nbsp;|&nbsp;</span>');}}});a.show(this);}},{xtype:"button",text:"清除附件",handler:function(){var d=Ext.getCmp("planFileIds");var a=Ext.getCmp("planFilePanel");a.body.update("");d.setValue("");}},{xtype:"hidden",id:"planFileIds",name:"planFileIds"}]}]}]},{layout:"form",columnWidth:0.5,xtype:"container",items:[{fieldLabel:"计划类型",hiddenName:"workPlan.planType.typeId",xtype:"combo",width:273,editable:false,triggerAction:"all",id:"typeId",displayField:"name",valueField:"id",mode:"local",store:new Ext.data.SimpleStore({autoLoad:true,url:__ctxPath+"/task/comboPlanType.do",fields:["id","name"]})},{xtype:"container",style:"padding-left:0px;padding-bottom:3px;",layout:"column",items:[{xtype:"label",style:"padding-left:0px;",text:"发布范围:",width:101},{xtype:"textfield",name:"workPlan.issueScope",id:"issueScope",readOnly:true},{xtype:"button",text:"选择部门",iconCls:"btn-select",handler:function(){DepSelector.getView(function(d,a){Ext.getCmp("issueScope").setValue(a);Ext.getCmp("issueScopeIds").setValue(d);}).show();}},{xtype:"button",text:"清除记录"}]},{xtype:"container",layout:"column",style:"padding-left:0px;padding-bottom:3px;",items:[{xtype:"label",text:"参与人:",style:"padding-left:0px;",width:101},{xtype:"textfield",name:"workPlan.participants",id:"participants",readOnly:true},{xtype:"button",text:"选择人员",iconCls:"btn-select",handler:function(){UserSelector.getView(function(d,a){Ext.getCmp("participants").setValue(a);Ext.getCmp("participantsIds").setValue(d);}).show();}},{xtype:"button",text:"清除记录"}]},{xtype:"container",layout:"column",style:"padding-left:0px;padding-bottom:3px;",items:[{xtype:"label",text:"负责人:",style:"padding-left:0px;",width:101},{xtype:"textfield",name:"workPlan.principal",id:"principal",readOnly:true},{xtype:"button",text:"选择人员",iconCls:"btn-select",handler:function(){UserSelector.getView(function(d,a){Ext.getCmp("principal").setValue(a);Ext.getCmp("principalIds").setValue(d);}).show();}},{xtype:"button",text:"清除记录"}]},{xtype:"radiogroup",fieldLabel:"是否启用",autoHeight:true,columns:2,items:[{boxLabel:"是",name:"workPlan.status",inputValue:1,id:"status1",checked:true},{boxLabel:"否",name:"workPlan.status",inputValue:0,id:"status0"}]},{xtype:"radiogroup",fieldLabel:"是否为个人计划",autoHeight:true,columns:2,items:[{boxLabel:"个人",name:"workPlan.isPersonal",inputValue:0,id:"isPersonal0",checked:true},{boxLabel:"部门",name:"workPlan.isPersonal",inputValue:1,id:"isPersonal1"}]},{fieldLabel:"图标",hiddenName:"workPlan.icon",id:"icon",xtype:"iconcomb",mode:"local",anchor:"92.5%",allowBlank:false,editable:false,store:new Ext.data.SimpleStore({fields:["countryCode","countryName"],data:[["ux-flag-de","日常计划"],["ux-flag-us","重要计划"],["ux-flag-fr","特殊计划"]]}),valueField:"countryCode",displayField:"countryName",triggerAction:"all",mode:"local"},{fieldLabel:"备注",name:"workPlan.note",xtype:"textarea",id:"note",width:273,height:180}]}]});if(this.planId!=null&&this.planId!="undefined"){b.getForm().load({deferredRender:false,url:__ctxPath+"/task/getWorkPlan.do?planId="+this.planId,waitMsg:"正在载入数据...",success:function(u,s){var t=s.result.data.startTime;var r=s.result.data.status;var n=s.result.data.isPersonal;Ext.getCmp("status"+r).setValue(true);Ext.getCmp("isPersonal"+n).setValue(true);var p=s.result.data.endTime;var v=s.result.data.planType.typeId;Ext.getCmp("typeId").setValue(v);var i=s.result.data.planFiles;Ext.getCmp("startTime").setValue(new Date(getDateFromFormat(t,"yyyy-MM-dd H:mm:ss")));Ext.getCmp("endTime").setValue(new Date(getDateFromFormat(p,"yyyy-MM-dd H:mm:ss")));var o=Ext.getCmp("planFilePanel");var a=Ext.getCmp("planFileIds");for(var q=0;q<i.length;q++){if(a.getValue()!=""){a.setValue(a.getValue()+",");}a.setValue(a.getValue()+i[q].fileId);Ext.DomHelper.append(o.body,'<span><a href="#" onclick="FileAttachDetail.show('+i[q].fileId+')">'+i[q].fileName+'</a><img class="img-delete" src="'+__ctxPath+'/images/system/delete.gif" onclick="removePlanFile(this,'+i[q].fileId+')"/>&nbsp;|&nbsp;</span>');}},failure:function(a,d){Ext.ux.Toast.msg("编辑","载入失败");}});}return b;};function removePlanFile(h,g){var f=Ext.getCmp("planFileIds");var i=f.getValue();if(i.indexOf(",")<0){f.setValue("");}else{i=i.replace(","+g,"").replace(g+",","");f.setValue(i);}var j=Ext.get(h.parentNode);j.remove();}