var RoleSelector={getView:function(g,h){var f=this.initGridPanel(h);var e=new Ext.Window({title:"角色选择",width:630,height:380,layout:"fit",border:false,items:[f],modal:true,buttonAlign:"center",buttons:[{iconCls:"btn-ok",text:"确定",handler:function(){var c=Ext.getCmp("RoleSelectorGrid");var b=c.getSelectionModel().getSelections();var a="";var i="";for(var d=0;d<b.length;d++){if(d>0){a+=",";i+=",";}a+=b[d].data.roleId;i+=b[d].data.roleName;}if(g!=null){g.call(this,a,i);}e.close();}},{text:"取消",iconCls:"btn-cancel",handler:function(){e.close();}}]});return e;},initGridPanel:function(j){var i=null;if(j){var i=new Ext.grid.CheckboxSelectionModel({singleSelect:true});}else{i=new Ext.grid.CheckboxSelectionModel();}var h=new Ext.grid.ColumnModel({columns:[i,new Ext.grid.RowNumberer(),{header:"roleId",dataIndex:"roleId",hidden:true},{header:"角色名称",dataIndex:"roleName",width:60},{header:"角色描述",dataIndex:"roleDesc",width:60}]});var g=new Ext.data.Store({proxy:new Ext.data.HttpProxy({url:__ctxPath+"/system/listAppRole.do"}),reader:new Ext.data.JsonReader({root:"result",totalProperty:"totalCounts",id:"id",fields:[{name:"roleId",type:"int"},"roleName","roleDesc"]})});g.load({params:{start:0,limit:25}});var k=new Ext.Toolbar({id:"AppRoleFootBar",height:30,items:["角色名称：",{name:"Q_roleName_S_LK",xtype:"textfield",id:"Q_roleName_S_LK",width:200}," ",{xtype:"button",iconCls:"btn-search",text:"查询",handler:function(){var a=Ext.getCmp("Q_roleName_S_LK").getValue();Ext.Ajax.request({url:__ctxPath+"/system/listAppRole.do",params:{Q_roleName_S_LK:a},method:"post",success:function(e,c){var b=Ext.util.JSON.decode(e.responseText);var d=Ext.getCmp("RoleSelectorGrid");d.getStore().loadData(b);},failure:function(c,b){}});}}]});var l=new Ext.grid.GridPanel({id:"RoleSelectorGrid",tbar:k,store:g,trackMouseOver:true,disableSelection:false,loadMask:true,height:360,cm:h,sm:i,viewConfig:{forceFit:true,enableRowBody:false,showPreview:false},bbar:new HT.PagingBar({store:g})});return l;}};