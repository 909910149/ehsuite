var PersonalDocFolderSelector={getView:function(h){var f;var g=function(a){if(a!=null){if(!a.disabled){f=a;return f;}}};var i=new Ext.tree.TreePanel({id:"docFolderTreePanel",title:"目录列表 ",loader:new Ext.tree.TreeLoader({url:__ctxPath+"/document/listDocFolder.do"}),root:new Ext.tree.AsyncTreeNode({expanded:true}),rootVisible:false,listeners:{"click":g}});var j=new Ext.Window({title:"请选择目录",iconCls:"menu-mail_folder",width:440,height:420,layout:"fit",items:[i],modal:true,buttonAlign:"center",buttons:[{text:"确认",iconCls:"btn-ok",scope:"true",handler:function(){if(f!=null){if(h!=null){h.call(this,f.id,f.text);}j.close();}else{Ext.ux.Toast.msg("提示","你无权插文档入该目录！");}}},{text:"关闭",iconCls:"btn-cancel",handler:function(){j.close();}}]});return j;}};