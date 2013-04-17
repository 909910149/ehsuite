Ext.ns("MessageRecView");MessageRecView=Ext.extend(Ext.Panel,{gridPanel:null,searchPanel:null,receiveStore:null,constructor:function(b){Ext.applyIf(this,b);this.initUIComponents();MessageRecView.superclass.constructor.call(this,{id:"MessageRecView",flex:1,layout:"border",items:[this.searchPanel,this.gridPanel]});},initUIComponents:function(){this.searchPanel=new Ext.FormPanel({region:"north",height:60,title:"已收信息显示",id:"receiveSearchForm",frame:false,border:false,url:__ctxPath+"/info/listShortMessage.do",layout:"hbox",layoutConfig:{padding:"5",align:"middle"},defaults:{xtype:"label",margins:{top:0,right:4,bottom:4,left:4}},items:[{text:"类型",width:30},{hiddenName:"shortMessage.msgType",xtype:"combo",mode:"local",width:100,editable:false,triggerAction:"all",store:[["1","个人信息"],["2","日程安排"],["3","计划任务"],["4","系统消息"]]},{text:"发送人",width:40},{xtype:"textfield",name:"shortMessage.sender"},{text:"从",width:20},{xtype:"datefield",format:"Y-m-d",name:"from",editable:false},{text:"到",width:20},{xtype:"datefield",format:"Y-m-d",name:"to",editable:false},{text:"查询",xtype:"button",iconCls:"search",handler:this.search.createCallback(this)},{xtype:"button",text:"重置",iconCls:"reset",handler:this.reset.createCallback(this)},{xtype:"hidden",name:"start",value:0},{xtype:"hidden",name:"limit",value:11}]});this.receiveStore=new Ext.data.Store({proxy:new Ext.data.HttpProxy({url:__ctxPath+"/info/listShortMessage.do"}),reader:new Ext.data.JsonReader({root:"result",totalProperty:"totalCounts",id:"id",fields:[{name:"receiveId",type:"int"},{name:"messageId",mapping:"shortMessage.messageId",type:"int"},{name:"msgType",mapping:"shortMessage.msgType",type:"int"},{name:"senderId",mapping:"shortMessage.senderId",type:"int"},{name:"sender",mapping:"shortMessage.sender"},{name:"content",mapping:"shortMessage.content"},{name:"sendTime",mapping:"shortMessage.sendTime"},{name:"readFlag"}]}),remoteSort:true});this.receiveStore.setDefaultSort("id","desc");this.receiveStore.load({params:{start:0,limit:11}});var c=new Ext.grid.CheckboxSelectionModel();var d=new Ext.grid.ColumnModel({columns:[c,new Ext.grid.RowNumberer(),{header:"状态",dataIndex:"readFlag",width:60,renderer:function(a){return a=="1"?"<img src='"+__ctxPath+"/images/btn/info/email_open.png'/>":"<img src='"+__ctxPath+"/images/btn/info/email.png'/>";}},{header:"类别",dataIndex:"msgType",width:60,renderer:function(a){if(a=="1"){return"<p style='color:green;'>个人信息</p>";}else{if(a=="2"){return"<p style='color:green;'>日程安排</p>";}else{if(a=="3"){return"<p style='color:green;'>计划任务</p>";}else{if(a=="4"){return"<p style='color:green;'>代办任务提醒</p>";}else{if(a=="5"){return"<p style='color:green;'>系统提醒</p>";}else{return"<p style='color:green;'>其他</p>";}}}}}}},{header:"发送人",dataIndex:"sender",width:40},{header:"内容",dataIndex:"content",width:300},{header:"发送时间",dataIndex:"sendTime",width:90},{header:"操作",dataIndex:"receiveId",width:120,renderer:function(k,l,n,a,m){var p=n.data.receiveId;var o=n.data.msgType;var b='<button title="删除" value=" " class="btn-del" onclick="MessageRecView.removeReceiveMessage('+p+')">&nbsp;</button>';if(o=="1"){b+='&nbsp;<button title="回复" value=" " class="btn-update" onclick="MessageRecView. reply('+p+')">&nbsp;</button>';}return b;}}],defaults:{sortable:true,menuDisabled:true,width:100},listeners:{hiddenchange:function(f,b,a){saveConfig(b,a);}}});this.gridPanel=new Ext.grid.GridPanel({id:"ReceiveMessage",border:false,region:"center",tbar:new Ext.Toolbar({items:[{xtype:"button",text:"标记为已读",iconCls:"ux-flag-blue",handler:this.setReadFlag.createCallback(this)},{iconCls:"btn-del",text:"删除信息",xtype:"button",handler:this.mutDel}]}),store:this.receiveStore,shim:true,trackMouseOver:true,disableSelection:false,autoScroll:true,loadMask:true,cm:d,sm:c,viewConfig:{forceFit:true,enableRowBody:false,showPreview:false},bbar:new HT.PagingBar({pageSize:11,store:this.receiveStore})});},search:function(d){var e=d.gridPanel;var f=d.searchPanel;f.getForm().submit({waitMsg:"正在提交查询信息",success:function(c,b){var a=e.getStore();var i=b.response.responseText.replace("success:true,","");var j=Ext.util.JSON.decode(i);a.loadData(j,false);},failure:function(b,a){}});},reset:function(b){b.searchPanel.getForm().reset();},setReadFlag:function(f){var i=Ext.getCmp("ReceiveMessage");var g=i.getSelectionModel().getSelections();if(g.length==0){Ext.ux.Toast.msg("信息","请选择信息！");return;}var h=Array();for(var j=0;j<g.length;j++){h.push(g[j].data.receiveId);}Ext.Ajax.request({url:__ctxPath+"/info/multiReadInMessage.do",params:{ids:h},method:"post",success:function(){Ext.ux.Toast.msg("操作信息","成功标记所选信息为已读！");i.getStore().reload();}});},mutDel:function(){var h=Ext.getCmp("ReceiveMessage");var f=h.getSelectionModel().getSelections();if(f.length==0){Ext.ux.Toast.msg("信息","请选择要删除的记录！");return;}var g=Array();for(var e=0;e<f.length;e++){g.push(f[e].data.receiveId);}MessageRecView.removeReceiveMessage(g);}});MessageRecView.removeReceiveMessage=function(d){var c=Ext.getCmp("ReceiveMessage");Ext.Msg.confirm("删除操作","你确定要删除该信息吗?",function(a){if(a=="yes"){Ext.Ajax.request({url:__ctxPath+"/info/multiRemoveInMessage.do",params:{ids:d},method:"post",success:function(){Ext.ux.Toast.msg("操作信息","删除信息成功！");c.getStore().reload();}});}});};MessageRecView.reply=function(f){var g=Ext.getCmp("MessageManageView");g.removeAll(true);var h=new MessageForm();g.add(h);var e=Ext.getCmp("mFormPanel");g.doLayout();e.form.load({url:__ctxPath+"/info/replyInMessage.do",params:{receiveId:f},method:"post",deferredRender:true,layoutOnTabChange:true,success:function(){Ext.Ajax.request({url:__ctxPath+"/info/knowInMessage.do",method:"POST",params:{receiveId:f},success:function(b,a){},failure:function(b,a){},scope:this});},failure:function(){}});};MessageSendView=Ext.extend(Ext.Panel,{searchPanel:null,gridPanel:null,store:null,constructor:function(b){Ext.applyIf(this,b);this.initUIComponents();MessageSendView.superclass.constructor.call(this,{id:"MessageSendView",flex:1,layout:"border",items:[this.searchPanel,this.gridPanel]});},initUIComponents:function(){this.searchPanel=new Ext.FormPanel({region:"north",height:60,title:"已发信息显示",id:"sendSearchForm",url:__ctxPath+"/info/listInMessage.do",frame:false,border:false,layout:"hbox",layoutConfig:{padding:"5",align:"middle"},defaults:{xtype:"label",margins:{top:0,right:4,bottom:4,left:4}},items:[{text:"收信人",width:60},{xtype:"textfield",name:"inMessage.userFullname"},{text:"从",width:20},{xtype:"datefield",format:"Y-m-d",name:"from",editable:false},{text:"到",width:20},{xtype:"datefield",format:"Y-m-d",name:"to",editable:false},{text:"查询",xtype:"button",iconCls:"search",handler:this.search.createCallback(this)},{text:"重置",xtype:"button",iconCls:"reset",handler:this.reset.createCallback(this)},{xtype:"hidden",name:"start",value:0},{xtype:"hidden",name:"limit",value:11}]});this.store=new Ext.data.Store({proxy:new Ext.data.HttpProxy({url:__ctxPath+"/info/listInMessage.do"}),reader:new Ext.data.JsonReader({root:"result",totalProperty:"totalCounts",id:"id",fields:[{name:"receiveId",type:"int"},{name:"messageId",mapping:"shortMessage.messageId",type:"int"},{name:"msgType",mapping:"shortMessage.msgType",type:"int"},{name:"content",mapping:"shortMessage.content"},{name:"userId",type:"int"},"userFullname",{name:"sendTime",mapping:"shortMessage.sendTime"}]}),remoteSort:true});this.store.setDefaultSort("id","desc");this.store.load({params:{start:0,limit:11}});this.rowActions=new Ext.ux.grid.RowActions({header:"管理",width:120,actions:[{iconCls:"btn-update",qtip:"重发",style:"margin:0 3px 0 3px"}]});var c=new Ext.grid.CheckboxSelectionModel();var d=new Ext.grid.ColumnModel({columns:[c,new Ext.grid.RowNumberer(),{header:"收信人",dataIndex:"userFullname",width:100},{header:"内容",dataIndex:"content",width:250},{header:"发送时间",dataIndex:"sendTime",width:90},this.rowActions],defaults:{sortable:true,menuDisabled:true,width:100},listeners:{hiddenchange:function(f,b,a){saveConfig(b,a);}}});this.gridPanel=new Ext.grid.GridPanel({id:"sendMessage",region:"center",height:330,border:false,tbar:new Ext.Toolbar({height:27}),store:this.store,shim:true,trackMouseOver:true,disableSelection:false,autoScroll:true,loadMask:true,cm:d,sm:c,plugins:this.rowActions,viewConfig:{forceFit:true,enableRowBody:false,showPreview:false},bbar:new HT.PagingBar({store:this.store,pageSize:11})});this.rowActions.on("action",this.onRowAction,this);},search:function(d){var f=d.gridPanel;var e=d.searchPanel;e.getForm().submit({waitMsg:"正在提交查询信息",success:function(c,a){var b=f.getStore();var i=a.response.responseText.replace("success:true,","");var j=Ext.util.JSON.decode(i);b.loadData(j,false);}});},reset:function(b){b.searchPanel.getForm().reset();},reSend:function(d){var c=Ext.getCmp("sendMessage");Ext.Ajax.request({url:__ctxPath+"/info/sendShortMessage.do",params:{userId:d.data.userId+",",content:d.data.content},method:"post",success:function(){Ext.ux.Toast.msg("操作信息","重发成功！");c.getStore().reload();}});},onRowAction:function(j,g,i,h,f){switch(i){case"btn-update":this.reSend(g);break;default:break;}}});MessageManageView=Ext.extend(Ext.Panel,{constructor:function(b){Ext.applyIf(this,b);this.initUIComponents();MessageManageView.superclass.constructor.call(this,{id:"MessageManageView",region:"center",iconCls:"menu-message",title:"我的消息",layout:"vbox",layoutConfig:{align:"stretch"},tbar:this.toolbar,items:[]});this.addRecPanel(this);},initUIComponents:function(){this.toolbar=new Ext.Toolbar({height:30,layout:"column",items:[new Ext.Button({text:"发送信息",iconCls:"btn-sendM",handler:this.addSendFormPanel.createCallback(this)}),{xtype:"button",text:"已发信息",iconCls:"btn-sendMessage",handler:this.addSendPanel.createCallback(this)},{text:"已收信息",iconCls:"btn-receiveMessage",handler:this.addRecPanel.createCallback(this)}]});},addRecPanel:function(d){d.removeAll(true);var c=new MessageRecView();d.add(c);d.doLayout();},addSendPanel:function(d){d.removeAll(true);var c=new MessageSendView();d.add(c);d.doLayout();},addSendFormPanel:function(c){c.removeAll(true);var d=new MessageForm();c.add(d);c.doLayout();}});