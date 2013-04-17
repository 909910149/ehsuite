GlobalTypeManager=Ext.extend(Ext.Panel,{constructor:function(b){Ext.applyIf(this,b);this.initUIComponents();GlobalTypeManager.superclass.constructor.call(this,{id:"GlobalTypeManager",height:450,autoScroll:true,layout:"border",title:"系统分类树管理",items:[this.leftPanel,this.centerPanel]});},initUIComponents:function(){this.leftPanel=new Ext.Panel({region:"west",title:"分类管理",layout:"anchor",collapsible:true,split:true,width:200,tbar:new Ext.Toolbar({items:[{xtype:"button",text:"新建类",iconCls:"btn-add",handler:function(){new TypeKeyForm().show();}}]}),items:[{xtype:"panel",layout:"fit",items:[{xtype:"combo",editable:false,id:"comboGlobalType",hiddenName:"comboGlobalTypeValue",displayField:"name",valueField:"key",mode:"local",triggerAction:"all",store:new Ext.data.SimpleStore({autoLoad:true,url:__ctxPath+"/system/comboTypeKey.do",fields:["key","name"],listeners:{scope:this,"load":function(i){if(i.getCount()>0){var j=i.getAt(0);var b=j.data.key;Ext.getCmp("comboGlobalType").setValue(b);var a=Ext.getCmp("GlobalTypeTree");a.loader=new Ext.tree.TreeLoader({baseParams:{catKey:b},dataUrl:__ctxPath+"/system/treeGlobalType.do",requestMethod:"GET"});a.selectedNode=null;a.root.reload();var c=Ext.getCmp("globalTypeCenGrid");if(c!=null){var i=c.getStore();i.url=__ctxPath+"/system/subGlobalType.do";i.baseParams={parentId:0,catKey:b};i.reload();}}}}}),listeners:{scope:this,"select":function(a,n,l){var c=a.getValue();var b=Ext.getCmp("GlobalTypeTree");b.loader=new Ext.tree.TreeLoader({baseParams:{catKey:c},dataUrl:__ctxPath+"/system/treeGlobalType.do",requestMethod:"GET"});b.selectedNode=null;b.root.reload();var k=Ext.getCmp("globalTypeCenGrid");if(k!=null){var m=k.getStore();m.url=__ctxPath+"/system/subGlobalType.do";m.baseParams={parentId:0,catKey:c};m.reload();}}}}]},{xtype:"treePanelEditor",id:"GlobalTypeTree",title:"分类树",split:true,border:false,height:380,autoScroll:true,url:__ctxPath+"/system/treeGlobalType.do?catKey=DP",onclick:function(b){var a=b.id;var i=Ext.getCmp("globalTypeCenGrid");if(i!=null){var c=Ext.getCmp("comboGlobalType").getValue();var j=i.getStore();j.url=__ctxPath+"/system/subGlobalType.do";j.baseParams={parentId:a,catKey:c};j.reload();}},contextMenuItems:[{text:"新建分类",scope:this,iconCls:"btn-add",handler:function(){var h=Ext.getCmp("GlobalTypeTree");var a=h.selectedNode.id;var c=Ext.getCmp("comboGlobalType").getValue();var b=new GlobalTypeForm({parentId:a,catKey:c,callback:function(){Ext.getCmp("GlobalTypeTree").root.reload();Ext.getCmp("globalTypeCenGrid").getStore().reload();}});b.show();}},{text:"修改分类",scope:this,iconCls:"btn-edit",handler:function(){var b=Ext.getCmp("GlobalTypeTree");var c=b.selectedNode.id;var a=new GlobalTypeForm({proTypeId:c,callback:function(){Ext.getCmp("GlobalTypeTree").root.reload();Ext.getCmp("globalTypeCenGrid").getStore().reload();}});a.show();}}]}]});this.store=new Ext.data.JsonStore({url:__ctxPath+"/system/subGlobalType.do",baseParams:{parentId:0},root:"result",remoteSort:true,fields:[{name:"proTypeId",type:"int"},"typeName","nodeKey","sn"]});this.store.load();this.rowActions=new Ext.ux.grid.RowActions({header:"管理",width:80,actions:[{iconCls:"btn-last",qtip:"向下",style:"margin:0 3px 0 3px"},{iconCls:"btn-up",qtip:"向上",style:"margin:0 3px 0 3px"}]});this.rowActions.on("action",this.onRowAction,this);var f=new Ext.grid.CheckboxSelectionModel();var e=new Ext.grid.ColumnModel({columns:[f,new Ext.grid.RowNumberer(),{header:"proTypeId",dataIndex:"proTypeId",hidden:true},{header:"名称",dataIndex:"typeName",editor:new Ext.form.TextField({allowBlank:false})},{header:"节点Key",dataIndex:"nodeKey",editor:new Ext.form.TextField({allowBlank:false})},{header:"序号",dataIndex:"sn"},this.rowActions],defaults:{sortable:true,menuDisabled:false,width:100}});var d=new Ext.Toolbar({items:[{text:"新建分类",iconCls:"btn-add",handler:function(){var h=Ext.getCmp("GlobalTypeTree");var b=h.selectedNode;if(!b){Ext.ux.Toast.msg("操作信息","请选择左树中的分类！");return;}var c=Ext.getCmp("comboGlobalType").getValue();var a=new GlobalTypeForm({parentId:b.id,catKey:c,callback:function(){Ext.getCmp("GlobalTypeTree").root.reload();Ext.getCmp("globalTypeCenGrid").getStore().reload();}});a.show();}},"-",{xtype:"button",text:"保存",iconCls:"btn-save",scope:this,handler:function(){var b=this.centerPanel;var i=b.getStore();var a=[];for(var c=0;c<i.getCount();c+=1){var j=i.getAt(c);a.push(j.data);}Ext.Ajax.request({method:"post",url:__ctxPath+"/system/mulSaveGlobalType.do",params:{data:Ext.encode(a)},success:function(g){Ext.ux.Toast.msg("操作信息","成功设置");i.reload();b.getView().refresh();Ext.getCmp("GlobalTypeTree").root.reload();},failure:function(g){Ext.ux.Toast.msg("操作信息","设置出错，请联系管理员!");}});}},{text:"删除",iconCls:"btn-del",scope:this,handler:function(){var b=this.centerPanel;var h=b.getSelectionModel().getSelections();if(h.length==0){Ext.ux.Toast.msg("信息","请选择要删除的记录！");return;}var a=Array();for(var c=0;c<h.length;c++){a.push(h[c].data.proTypeId);}Ext.Msg.confirm("信息确认","您确认要删除所选记录吗？",function(g){if(g=="yes"){Ext.Ajax.request({url:__ctxPath+"/system/multiDelGlobalType.do",params:{ids:a},method:"POST",success:function(l,k){Ext.ux.Toast.msg("操作信息","成功删除该产品分类！");b.getStore().reload();Ext.getCmp("GlobalTypeTree").root.reload();},failure:function(l,k){Ext.ux.Toast.msg("操作信息","操作出错，请联系管理员！");}});}});}}]});this.centerPanel=new Ext.grid.EditorGridPanel({region:"center",title:"分类列表",tbar:d,clicksToEdit:1,id:"globalTypeCenGrid",store:this.store,plugins:this.rowActions,sm:f,cm:e,height:450});},onRowAction:function(n,o,p,j,q){var r=Ext.getCmp("globalTypeCenGrid");var l=r.getStore();switch(p){case"btn-up":if(j==0){Ext.ux.Toast.msg("操作信息","已经为第一条!");return;}var k=l.getAt(j-1);var m=l.getAt(j);l.removeAt(j);l.removeAt(j-1);l.insert(j-1,m);l.insert(j,k);break;case"btn-last":if(j==l.getCount()-1){Ext.ux.Toast.msg("操作信息","已经为最后一条!");return;}var k=l.getAt(j);var m=l.getAt(j+1);l.removeAt(j+1);l.removeAt(j);l.insert(j,m);l.insert(j+1,k);break;default:break;}}});