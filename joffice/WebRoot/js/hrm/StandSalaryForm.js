var StandSalaryForm=function(b){this.standardId=b;return new Ext.Panel({id:"StandSalaryForm",iconCls:"menu-development",tbar:this.initToolbar(),border:false,title:"薪酬标准详细信息",items:[this.setup(b)]});};StandSalaryForm.prototype.setup=function(f){var d=new StandSalaryItemView(f);var e=new Ext.FormPanel({id:"StandSalaryFormPanel",url:__ctxPath+"/hrm/saveStandSalary.do",layout:"form",bodyStyle:"padding:5px 10px 10px 10px;",formId:"StandSalaryFormId",defaultType:"textfield",border:false,items:[{name:"standSalary.standardId",id:"standSalaryForm.standardId",xtype:"hidden",value:this.standardId==null?"":this.standardId},{name:"standSalary.setdownTime",id:"standSalaryForm.setdownTime",xtype:"hidden"},{name:"standSalary.framer",id:"standSalaryForm.framer",xtype:"hidden"},{name:"standSalary.checkName",id:"standSalaryForm.checkName",xtype:"hidden"},{name:"standSalary.checkTime",id:"standSalaryForm.checkTime",xtype:"hidden"},{name:"standSalary.checkOpinion",id:"standSalaryForm.checkOpinion",xtype:"hidden"},{name:"deleteItemIds",id:"deleteItemIds",xtype:"hidden"},{xtype:"fieldset",title:"薪酬信息",anchor:"100%",layout:"form",items:[{xtype:"container",layout:"column",style:"padding-left:0px;",items:[{xtype:"container",defaultType:"textfield",style:"padding-left:0px;",columnWidth:0.5,defaults:{anchor:"100%,100%"},layout:"form",items:[{fieldLabel:"标准编号",allowBlank:false,blankText:"标准编号不能为空!",name:"standSalary.standardNo",id:"standSalaryForm.standardNo"},{fieldLabel:"标准名称",xtype:"textfield",name:"standSalary.standardName",allowBlank:false,blankText:"标准名称不能为空!",id:"standSalaryForm.standardName"}]},{xtype:"container",columnWidth:0.5,style:"padding-left:0px;",defaults:{anchor:"100%,100%"},layout:"form",items:[{id:"getStandardNoButton",xtype:"button",autoWidth:true,text:"系统生成",iconCls:"btn-system-setting",handler:function(){Ext.Ajax.request({url:__ctxPath+"/hrm/numberStandSalary.do",success:function(a){var b=Ext.util.JSON.decode(a.responseText);Ext.getCmp("standSalaryForm.standardNo").setValue(b.standardNo);}});}},{fieldLabel:"薪资总额",name:"standSalary.totalMoney",id:"standSalaryForm.totalMoney",xtype:"textfield",readOnly:true,anchor:"100%"}]}]},{fieldLabel:"备注",name:"standSalary.memo",id:"standSalaryForm.memo",xtype:"textarea",anchor:"99%"}]},d]});if(this.standardId!=null&&this.standardId!="undefined"){e.loadData({preName:"standSalary",root:"data",url:__ctxPath+"/hrm/getStandSalary.do?standardId="+this.standardId,waitMsg:"正在载入数据...",success:function(b,a){Ext.getCmp("getStandardNoButton").disable();Ext.getCmp("standSalaryForm.standardNo").getEl().dom.readOnly=true;},failure:function(b,a){}});}return e;};StandSalaryForm.prototype.initToolbar=function(){var b=new Ext.Toolbar({width:"100%",height:30,items:[{text:"保存",iconCls:"btn-save",handler:function(){StandSalaryForm.saveStandSalary();}},{text:"取消",iconCls:"btn-cancel",handler:function(){var a=Ext.getCmp("centerTabPanel");a.remove("StandSalaryForm");}}]});return b;};StandSalaryForm.saveStandSalary=function(){StandSalaryItemView.onCalcTotalMoney();var h=Ext.getCmp("StandSalaryFormPanel");var e=Ext.getCmp("StandSalaryItemGrid").getStore();var g=[];for(i=0,cnt=e.getCount();i<cnt;i+=1){var f=e.getAt(i);if(f.data.itemId==""||f.data.itemId==null){f.set("itemId",-1);}if(f.dirty){g.push(f.data);}}if(h.getForm().isValid()){h.getForm().submit({method:"post",params:{data:Ext.encode(g)},waitMsg:"正在提交数据...",success:function(b,a){Ext.ux.Toast.msg("操作信息","成功保存信息！");},failure:function(b,a){Ext.MessageBox.show({title:"操作信息",msg:a.result.msg,buttons:Ext.MessageBox.OK,icon:"ext-mb-error"});Ext.getCmp("standSalaryForm.standardNo").setValue("");}});}};