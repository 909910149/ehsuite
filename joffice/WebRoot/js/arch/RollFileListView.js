RollFileListView=Ext.extend(Ext.grid.EditorGridPanel,{viewConfig:null,startIndex:0,constructor:function(b){Ext.applyIf(this,b);this.initUIComponents();RollFileListView.superclass.constructor.call(this,{id:"RollFileListView",tbar:this.topbar,store:this.store,height:200,plugins:this.rowActions,trackMouseOver:true,autoScroll:true,disableSelection:false,loadMask:true,clicksToEdit:1,frame:false,cm:this.cm,sm:this.sm,autoExpandColumn:"shortDesc",viewConfig:{forceFit:true,enableRowBody:false,showPreview:false},listeners:{"rowdblclick":this.rowClick}});},initUIComponents:function(){this.topbar=new Ext.Toolbar({items:[{xtype:"tbtext",text:"附件"},{xtype:"tbseparator"},{iconCls:"btn-add",text:"添加",xtype:"button",scope:this,handler:this.createRs},{iconCls:"btn-del",text:"删除",xtype:"button",scope:this,handler:this.removeSelRs}]});this.store=new Ext.data.Store({proxy:new Ext.data.HttpProxy({url:__ctxPath+"/arch/listRollFileList.do"}),autoLoad:false,reader:new Ext.data.JsonReader({root:"result",totalProperty:"totalCounts",id:"listId",fields:[{name:"listId",type:"int"},"downLoads","sn","shortDesc","rollFileId","fileAttach"]}),remoteSort:true});this.store.setDefaultSort("sn","asc");this.store.on("load",function(c,d){this.viewConfig=[];Ext.each(d,function(a){this.viewConfig.push({fileName:a.data.fileAttach.fileName,filePath:a.data.fileAttach.filePath});},this);},this);this.rowActions=new Ext.ux.grid.RowActions({header:"管理",width:15,actions:[{iconCls:"btn-readdocument",qtip:"预览",style:"margin:0 3px 0 3px"},{iconCls:"btn-downLoad",qtip:"下载",style:"margin:0 3px 0 3px"},{iconCls:"btn-up",qtip:"向上",style:"margin:0 3px 0 3px"},{iconCls:"btn-last",qtip:"向下",style:"margin:0 3px 0 3px"}]});this.rowActions.on("action",this.onRowAction,this);this.sm=new Ext.grid.CheckboxSelectionModel();this.cm=new Ext.grid.ColumnModel({columns:[this.sm,{header:"listId",dataIndex:"listId",hidden:true},{header:"序号",width:5,dataIndex:"sn",renderer:function(k,g,h,j,l,m){h.data["sn"]=j+1;m.commitChanges();return h.data["sn"];}},{header:"文件名称",width:20,dataIndex:"fileAttach",renderer:function(b){return b.fileName;}},{header:"概要",dataIndex:"shortDesc",editor:{allowBlank:false,xtype:"textarea"}},{header:"下载次数",width:10,dataIndex:"downLoads"},this.rowActions],defaults:{sortable:true,menuDisabled:false,width:40}});},rowClick:function(d,e,f){},createRs:function(){App.createUploadDialog({file_cat:"arch/upload",callback:function(m){var o=Ext.getCmp("RollFileListView");var j=o.getStore();o.stopEditing();for(i=0;i<m.length;i++){var k=m[i].fileId;var l=m[i].filename;var p=m[i].filepath;var n=new j.recordType();n.data={};n.data["downLoads"]=0;n.data["shortDesc"]="";n.data[""]=this.rollFileId==null?"":this.rollFileId;var q={};Ext.applyIf(q,{fileId:k,fileName:l,filePath:p});n.data["fileAttach"]=q;j.insert(j.getCount(),n);n.markDirty();}j.commitChanges();o.getView().refresh();o.startEditing(0,0);}}).show();},removeByIds:function(c,d){Ext.Ajax.request({url:__ctxPath+"/arch/multiDelRollFileList.do",params:{listIds:c,fileIds:d},method:"POST",success:function(b,a){Ext.ux.Toast.msg("操作信息","成功删除该明细！");Ext.getCmp("RollFileListView").getStore().reload();},failure:function(b,a){Ext.ux.Toast.msg("操作信息","操作出错，请联系管理员！");}});},removeSelRs:function(){Ext.Msg.confirm("信息确认","您确认要删除所选记录吗？",function(l){if(l=="yes"){var m=Ext.getCmp("RollFileListView");var h=m.getStore();var j=m.getSelectionModel().getSelections();if(j.length==0){Ext.ux.Toast.msg("信息","请选择要删除的记录！");return;}var k=Array();var o=Array();for(var n=0;n<j.length;n++){if(j[n].data.listId!=""&&j[n].data.listId!=null){k.push(j[n].data.listId);}else{o.push(j[n].data.fileAttach.fileId);}}this.removeByIds(k,o);}},this);},editRs:function(d){var c=Ext.getCmp("RollFileListView");var d=c.getSelectionModel().getSelections();new RollFileListForm({listId:d[0].data.listId}).show();},saveSelRs:function(){},downLoad:function(b){window.open(__ctxPath+"/attachFiles/"+b.data.fileAttach.filePath);b.data.downLoads=b.data.downLoads+1;Ext.getCmp("RollFileFormWin").save();},sn:function(o,l,n){var j=o.getStore();var k=j.getAt(l);var p=j.getAt(n);if(k&&p){var m=k.get("sn");var q=p.get("sn");k.data.sn=q;p.data.sn=m;Ext.getCmp("RollFileFormWin").save("sn");o.getView().refresh();}},viewFile:function(b){new ViewFileWindow({viewConfig:this.viewConfig,startIndex:b}).show();},onRowAction:function(k,g,j,h,f){switch(j){case"btn-readdocument":this.viewFile.call(this,h);break;case"btn-downLoad":this.downLoad.call(this,g);break;case"btn-up":this.sn.call(this,k,h,h-1);break;case"btn-last":this.sn.call(this,k,h,h+1);break;default:break;}}});