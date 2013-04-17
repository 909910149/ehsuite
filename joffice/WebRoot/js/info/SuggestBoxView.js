SuggestBoxView=Ext.extend(Ext.Panel,{searchPanel:null,gridPanel:null,store:null,topbar:null,constructor:function(b){Ext.applyIf(this,b);this.initUIComponents();SuggestBoxView.superclass.constructor.call(this,{id:"SuggestBoxView",title:this.title?this.title:"意见箱管理",region:"center",iconCls:"menu-suggestbox",layout:"border",height:600,items:[this.searchPanel,this.gridPanel]});},initUIComponents:function(){this.searchPanel=new Ext.FormPanel({height:35,region:"north",frame:false,border:false,layout:"hbox",layoutConfig:{padding:"5",align:"middle"},defaults:{style:"padding:0px 5px 0px 5px;",border:false,anchor:"98%,98%",labelWidth:75,xtype:"label"},items:[{text:"标题"},{name:"Q_subject_S_LK",xtype:"textfield"},{text:"日期  从"},{name:"Q_createtime_D_GE",xtype:"datefield",format:"Y-m-d"},{text:"至"},{name:"Q_createtime_D_LE",xtype:"datefield",format:"Y-m-d"},{text:"发送人"},{name:"Q_senderFullname_S_LK",xtype:"textfield"},{text:"发送人IP"},{name:"Q_senderIp_S_LK",xtype:"textfield"},{text:"是否公开"},{hiddenName:"Q_isOpen_SN_EQ",xtype:"combo",editable:false,width:80,mode:"local",triggerAction:"all",store:[["","全部"],["0","公开"],["1","未公开"]]},{xtype:"button",text:"查询",iconCls:"search",handler:this.search.createCallback(this)},{name:"Q_senderId_SN_EQ",id:"SuggestBoxViewSearchSenderId",xtype:"hidden"}]});this.store=new Ext.data.JsonStore({url:__ctxPath+"/info/listSuggestBox.do",root:"result",baseParams:{"Q_senderId_L_EQ":this.userId},totalProperty:"totalCounts",remoteSort:true,fields:[{name:"boxId",type:"int"},"subject","content","createtime","recUid","recFullname","senderId","senderFullname","senderIp","phone","email","isOpen","replyContent","replyTime","replyId","replyFullname","status","queryPwd"]});this.store.setDefaultSort("boxId","desc");this.store.load({params:{start:0,limit:25}});var f=new Ext.grid.CheckboxSelectionModel();var d=this.isOutSide;var e=new Ext.grid.ColumnModel({columns:[f,new Ext.grid.RowNumberer(),{header:"boxId",dataIndex:"boxId",hidden:true},{header:"是否公开",width:60,dataIndex:"isOpen",renderer:function(a){if(a==0){return'<font color="green">公开</font>';}else{return'<font color="red">未公开</font>';}}},{header:"状态",width:45,dataIndex:"status",renderer:function(a){if(a==0){return'<font color="blue">草稿</font>';}else{if(a==1){return'<font color="red">提交</font>';}else{return'<font color="green">已回复</font>';}}}},{header:"查询密码",wdith:70,dataIndex:"queryPwd",renderer:function(a,b,h){var c=h.data.isOpen;if(a!=null&&c==1){return'<font color="red">需要密码</font>';}else{return'<font color="green">无密码</font>';}}},{header:"意见标题",width:300,dataIndex:"subject"},{header:"创建日期",dataIndex:"createtime",renderer:function(a){return a.substring(0,10);}},{header:"发送人",dataIndex:"senderFullname",renderer:function(a){if(a!=null&&a!=""){return a;}else{return"匿名";}}},{header:"发送人IP",dataIndex:"senderIp"},{header:"管理",dataIndex:"newsId",width:210,renderer:function(b,c,q,o,a){var r=q.data.status;var v=q.data.isOpen;var t=q.data.queryPwd;var u=false;if(v==1&&t!=null&&t!=""){u=true;}var s=q.data.boxId;var p="";if(!d){p='<button title="删除" value=" " class="btn-del" onclick="SuggestBoxView.delByIds('+s+')">&nbsp</button>';if(r==0){p+='<button title="编辑" value=" " class="btn-edit" onclick="SuggestBoxView.editRecord('+s+')">&nbsp</button>';}else{if(r==1&&this.userId==null){p+='<button title="回复" value=" " class="btn-suggest-reply" onclick="SuggestBoxView.reply('+s+')">&nbsp</button>';}}}p+='<button title="浏览" value=" " class="btn-suggest-scan" onclick="SuggestBoxView.scan('+s+","+u+')">&nbsp</button>';return p;}}],defaults:{sortable:true,menuDisabled:false,width:100}});this.topbar=new Ext.Toolbar({height:30,bodyStyle:"text-align:left",items:[{iconCls:"btn-add",text:"添加",xtype:"button",handler:this.createRecord}]});if(!this.isOutSide){this.topbar.add({iconCls:"btn-del",text:"删除",xtype:"button",handler:this.delRecords,scope:this});}this.gridPanel=new Ext.grid.GridPanel({id:"SuggestBoxGrid",region:"center",stripeRows:true,tbar:this.topbar,store:this.store,trackMouseOver:true,disableSelection:false,loadMask:true,cm:e,sm:f,viewConfig:{forceFit:true,autoFill:true},bbar:new Ext.PagingToolbar({pageSize:25,store:this.store,displayInfo:true,displayMsg:"当前页记录索引{0}-{1}， 共{2}条记录",emptyMsg:"当前没有记录"})});this.gridPanel.addListener("rowdblclick",function(b,c,a){b.getSelectionModel().each(function(g){var o=g.data.status;var m=g.data.isOpen;var l=g.data.queryPwd;var n=false;if(m==1&&l!=null&&l!=""){n=true;}if(o==0){new SuggestBoxForm({boxId:g.data.boxId}).show();}else{SuggestBoxView.scan(g.data.boxId,n);}});});},search:function(b){if(b.searchPanel.getForm().isValid()){$search({searchPanel:b.searchPanel,gridPanel:b.gridPanel});}},createRecord:function(){new SuggestBoxForm().show();},delRecords:function(){var h=Ext.getCmp("SuggestBoxGrid");var f=h.getSelectionModel().getSelections();if(f.length==0){Ext.ux.Toast.msg("信息","请选择要删除的记录！");return;}var g=Array();for(var e=0;e<f.length;e++){g.push(f[e].data.boxId);}SuggestBoxView.delByIds(g);}});SuggestBoxView.delByIds=function(b){Ext.Msg.confirm("信息确认","您确认要删除所选记录吗？",function(a){if(a=="yes"){Ext.Ajax.request({url:__ctxPath+"/info/multiDelSuggestBox.do",params:{ids:b},method:"POST",success:function(f,e){Ext.ux.Toast.msg("操作信息","成功删除该[SuggestBox]！");Ext.getCmp("SuggestBoxGrid").getStore().reload();},failure:function(f,e){Ext.ux.Toast.msg("操作信息","操作出错，请联系管理员！");}});}});};SuggestBoxView.editRecord=function(b){new SuggestBoxForm({boxId:b}).show();};SuggestBoxView.reply=function(b){new SuggestBoxReplyForm({boxId:b}).show();};SuggestBoxView.scan=function(g,f){if(f){var e=new Ext.FormPanel({layout:"form",bodyStyle:"padding:10px",border:false,url:__ctxPath+"/info/matchSuggestBox.do",defaults:{anchor:"98%,98%"},items:[{fieldLabel:"访问密码",name:"suggestBox.queryPwd",id:"queryPwd",xtype:"textfield",inputType:"password"},{name:"suggestBox.boxId",value:g,xtype:"hidden"}]});var h=new Ext.Window({layout:"fit",iconCls:"btn-add",items:e,modal:true,minHeight:149,minWidth:499,height:150,width:500,maximizable:true,title:"访问密码",buttonAlign:"center",buttons:[{text:"确定",iconCls:"btn-save",handler:function(){if(e.getForm().isValid()){e.getForm().submit({method:"POST",waitMsg:"正在提交数据...",success:function(b,a){h.close();new SuggestBoxDisplay({boxId:g}).show();},failure:function(){Ext.ux.Toast.msg("操作信息","访问密码不正确！");}});}}},{text:"取消",iconCls:"btn-cancel",handler:function(){h.close();}}]});h.show();}else{new SuggestBoxDisplay({boxId:g}).show();}};