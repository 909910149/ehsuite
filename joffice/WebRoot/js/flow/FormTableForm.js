FormTableForm=Ext.extend(Ext.Window,{constructor:function(b){Ext.applyIf(this,b);this.initUIComponents();FormTableForm.superclass.constructor.call(this,{id:"FormTableFormWin",layout:"fit",items:this.formPanel,modal:true,height:400,width:500,maximizable:true,title:"[FormTable]详细信息",buttonAlign:"center",buttons:[{text:"保存",iconCls:"btn-save",scope:this,handler:this.save},{text:"重置",iconCls:"btn-reset",scope:this,handler:this.reset},{text:"取消",iconCls:"btn-cancel",scope:this,handler:this.cancel}]});},initUIComponents:function(){this.formPanel=new Ext.FormPanel({layout:"form",bodyStyle:"padding:10px",border:false,autoScroll:true,defaults:{anchor:"96%,96%"},defaultType:"textfield",items:[{name:"formTable.tableId",xtype:"hidden",value:this.tableId==null?"":this.tableId},{fieldLabel:"表单ID",name:"formTable.formDefId",xtype:"numberfield"},{fieldLabel:"",name:"formTable.tableName",allowBlank:false,maxLength:128},{fieldLabel:"",name:"formTable.tableKey",allowBlank:false,maxLength:128}]});if(this.tableId!=null&&this.tableId!="undefined"){this.formPanel.loadData({url:__ctxPath+"/arch/getFormTable.do?tableId="+this.tableId,root:"data",preName:"formTable"});}},reset:function(){this.formPanel.getForm().reset();},cancel:function(){this.close();},save:function(){$postForm({formPanel:this.formPanel,scope:this,url:__ctxPath+"/flow/saveFormTable.do",callback:function(e,f){var d=Ext.getCmp("FormTableGrid");if(d!=null){d.getStore().reload();}this.close();}});}});