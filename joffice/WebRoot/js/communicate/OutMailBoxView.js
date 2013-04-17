Ext.ns("OutMailFolder");var OutMailBoxView=function(){var q;var n=new OutMailView();var s=new Ext.tree.TreePanel({id:"leftOutMailBoxTree",border:false,region:"center",split:true,layout:"fit",tbar:new Ext.Toolbar({items:[{xtype:"button",iconCls:"btn-refresh",text:"刷新",handler:function(){s.root.reload();}},{xtype:"button",text:"展开",iconCls:"btn-expand",handler:function(){s.expandAll();}},{xtype:"button",text:"收起",iconCls:"btn-collapse",handler:function(){s.collapseAll();}}]}),loader:new Ext.tree.TreeLoader({url:__ctxPath+"/communicate/listOutMailFolder_.do"}),root:new Ext.tree.AsyncTreeNode({expanded:true}),rootVisible:false,listeners:{"click":function(d){if(d!=null){n.setFolderId(d.id);var e=Ext.getCmp("OutMailCenterView");var a=Ext.getCmp("OutMailView");a.folderId=d.id;Ext.getCmp("OutMailSearchForm").getForm().findField("Q_outMailFolder.folderId_L_EQ").setValue(d.id);var f=Ext.getCmp("ShowOutMailDetail");if(f!=null){e.remove("ShowOutMailDetail");a.show();e.doLayout();}if(d.id!=0){Ext.getCmp("OutMailView").setTitle("["+d.text+"]");var c=Ext.getCmp("OutMailGrid");var b=c.getStore();b.url=__ctxPath+"/communicate/listOutMail_.do";b.baseParams={folderId:d.id};b.reload({params:{start:0,limit:25}});}}}}});function t(b,a){q=new Ext.tree.TreeNode({id:b.id,text:b.text});p.showAt(a.getXY());}s.on("contextmenu",t,s);var p=new Ext.menu.Menu({id:"OutMailFolderTreeMenu",items:[{text:"新建目录",scope:this,iconCls:"btn-add",handler:l},{text:"修改目录",scope:this,iconCls:"btn-edit",handler:o},{text:"删除目录",scope:this,iconCls:"btn-delete",handler:r}]});function l(b){var a=q.id;new OutMailFolderForm(null,a);}function o(){var a=q.id;if(a>4){new OutMailFolderForm(a);}}function r(){var a=q.id;if(a>4){Ext.Ajax.request({url:__ctxPath+"/communicate/countOutMailFolder_.do",params:{folderId:a},method:"post",success:function(b,c){var d=Ext.util.JSON.decode(b.responseText);if(d.count==0){Ext.Ajax.request({url:__ctxPath+"/communicate/removeOutMailFolder_.do",params:{folderId:a},method:"post",success:function(f,e){Ext.ux.Toast.msg("操作信息","成功删除目录！");s.root.reload();},failure:function(f,e){Ext.MessageBox.show({title:"操作信息",msg:"信息保存出错，请联系管理员！",buttons:Ext.MessageBox.OK,icon:"ext-mb-error"});}});}else{Ext.Msg.confirm("警告信息","该文件夹及其子目录下还有"+d.count+"封邮件,确定要删除吗?",function(e){if(e=="yes"){Ext.Ajax.request({url:__ctxPath+"/communicate/removeOutMailFolder_.do",params:{folderId:a,count:d.count},method:"post",success:function(g,f){Ext.ux.Toast.msg("操作信息","成功删除目录！");s.root.reload();},failure:function(g,f){Ext.MessageBox.show({title:"操作信息",msg:"信息保存出错，请联系管理员！",buttons:Ext.MessageBox.OK,icon:"ext-mb-error"});}});}});}},failure:function(b,c){Ext.MessageBox.show({title:"操作信息",msg:"信息保存出错，请联系管理员！",buttons:Ext.MessageBox.OK,icon:"ext-mb-error"});}});}}var u=new Ext.Panel({collapsible:true,region:"west",layout:"border",title:"外部邮箱目录",width:160,autoScroll:false,items:[new Ext.Container({region:"north",layout:"table",height:23,layoutConfig:{columns:2},items:[new Ext.Button({width:78,text:"收邮件",iconCls:"btn-mail_receive",handler:function(){Ext.MessageBox.show({msg:"邮件收取中，请稍后...",width:300,wait:true,progress:true,closable:true,waitConfig:{interval:200},icon:Ext.Msg.INFO});Ext.Ajax.request({url:__ctxPath+"/communicate/fetchOutMail_.do?sid="+new Date(),timeout:12000000,success:function(a,d){Ext.MessageBox.hide();n.setFolderId(1);Ext.getCmp("OutMailView").setTitle("[收件箱]");var c=Ext.getCmp("OutMailGrid");var b=c.getStore();b.url=__ctxPath+"/communicate/listOutMail_.do";b.baseParams={folderId:1,isFetch:"Y"};b.reload({params:{start:0,limit:25}});},failure:function(b,a){Ext.MessageBox.updateText("邮件收取出错!!!...");Ext.MessageBox.hide();}});}}),new Ext.Button({width:79,text:"发邮件",iconCls:"btn-mail_send",handler:function(){var a=Ext.getCmp("centerTabPanel");var b=Ext.getCmp("OutMailForm");if(b==null){b=new OutMailForm("","","");a.add(b);a.activate(b);}else{a.remove(b);b=new OutMailForm("","","");a.add(b);a.activate(b);}}})]}),s]});var m=new Ext.Panel({id:"OutMailCenterView",region:"center",autoScroll:true,layout:"fit",items:[n.getView()]});var v=new Ext.Panel({title:"外部邮箱",iconCls:"menu-mail_box",layout:"border",id:"OutMailBoxView",autoScroll:false,items:[u,m]});return v;};