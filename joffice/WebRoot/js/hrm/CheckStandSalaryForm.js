var CheckStandSalaryForm=function(f){this.standardId=f;var e=this.setup(f);var d=new Ext.Window({id:"CheckStandSalaryFormWin",title:"标准审核",iconCls:"menu-goods-apply",width:500,height:500,modal:true,layout:"fit",plain:true,bodyStyle:"padding:5px;",buttonAlign:"center",items:[this.setup(f)],buttons:[{text:"审核通过",iconCls:"btn-salaryPayoff-pass",handler:function(){var a=Ext.getCmp("CheckStandSalaryForm");if(a.getForm().isValid()){a.getForm().submit({method:"post",params:{status:"1"},waitMsg:"正在提交数据...",success:function(c,b){Ext.ux.Toast.msg("操作信息","成功保存信息！");Ext.getCmp("StandSalaryGrid").getStore().reload();d.close();},failure:function(c,b){Ext.MessageBox.show({title:"操作信息",msg:"信息保存出错，请联系管理员！",buttons:Ext.MessageBox.OK,icon:Ext.MessageBox.ERROR});d.close();}});}}},{text:"审核未通过",iconCls:"reset",handler:function(){var a=Ext.getCmp("CheckStandSalaryForm");if(a.getForm().isValid()){a.getForm().submit({method:"post",waitMsg:"正在提交数据...",success:function(c,b){Ext.ux.Toast.msg("操作信息","成功保存信息！");Ext.getCmp("StandSalaryGrid").getStore().reload();d.close();},failure:function(c,b){Ext.MessageBox.show({title:"操作信息",msg:"信息保存出错，请联系管理员！",buttons:Ext.MessageBox.OK,icon:Ext.MessageBox.ERROR});d.close();}});}}},{text:"取消",iconCls:"btn-cancel",handler:function(){d.close();}}]});d.show();};CheckStandSalaryForm.prototype.setup=function(f){var d=new CheckStandSalaryItemView(f);var e=new Ext.FormPanel({url:__ctxPath+"/hrm/checkStandSalary.do",layout:"form",id:"CheckStandSalaryForm",formId:"CheckStandSalaryFormId",bodyStyle:"padding:0px 5px 5px 5px;",defaultType:"textfield",border:false,items:[{name:"standSalary.standardId",id:"standSalary.standardId",xtype:"hidden",value:this.standardId==null?"":this.standardId},{xtype:"fieldset",title:"薪酬信息",layout:"form",anchor:"98%",autoWidth:true,autoHeight:true,labelWidth:70,defaultType:"textfield",items:[{xtype:"container",style:"padding-left:0px;",defaultType:"textfield",layout:"column",autoWidth:true,height:26,defaults:{width:150},items:[{xtype:"label",text:"标准编号:",style:"padding-left:0px;padding-top:3px;",width:71},{fieldLabel:"标准编号",readOnly:true,name:"standSalary.standardNo"},{xtype:"label",text:"标准名称",style:"padding-left:0px;padding-top:3px;",width:71},{fieldLabel:"标准名称",readOnly:true,name:"standSalary.standardName"}]},{xtype:"container",style:"padding-left:0px;",layout:"column",defaultType:"textfield",autoWidth:true,height:26,defaults:{width:150},items:[{xtype:"label",text:"薪资总额",style:"padding-left:0px;padding-top:3px;",width:71},{name:"standSalary.totalMoney",readOnly:true},{xtype:"label",text:"标准制定人",style:"padding-left:0px;padding-top:3px;",width:71},{name:"standSalary.framer",readOnly:true,value:curUserInfo.fullname}]},{fieldLabel:"备注",name:"standSalary.memo",readOnly:true,width:370,xtype:"textarea"},{fieldLabel:"审核意见",name:"standSalary.checkOpinion",allowBlank:false,blankText:"审核意见不可为空!",width:370,xtype:"textarea"}]},d]});if(this.standardId!=null&&this.standardId!="undefined"){e.loadData({preName:"standSalary",root:"data",url:__ctxPath+"/hrm/getStandSalary.do?standardId="+this.standardId,waitMsg:"正在载入数据...",success:function(b,a){},failure:function(b,a){}});}return e;};