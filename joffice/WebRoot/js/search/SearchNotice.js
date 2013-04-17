Ext.ns("SearchNotice");var SearchNotice=function(b){return this.getView(b);};SearchNotice.prototype.getView=function(b){return new Ext.Panel({id:"SearchNotice",title:"搜索公告",iconCls:"menu-news",border:false,style:"padding-bottom:10px;",autoScroll:true,items:[{region:"center",anchor:"100%",items:[new Ext.FormPanel({id:"ALLNewsSearchForm",height:40,frame:false,border:false,layout:"hbox",layoutConfig:{padding:"5",align:"middle"},defaults:{xtype:"label",margins:{top:0,right:4,bottom:4,left:4}},items:[{text:"请输入条件:"},{xtype:"textfield",name:"searchContent",width:150},{xtype:"button",text:"查询",iconCls:"search",handler:function(){var a=Ext.getCmp("ALLNewsSearchForm");var d=Ext.getCmp("SearchNoticeGrid");if(a.getForm().isValid()){$search({searchPanel:a,gridPanel:d});}}},{xtype:"button",text:"重置",iconCls:"reset",handler:function(){var a=Ext.getCmp("ALLNewsSearchForm");a.getForm().reset();}},{name:"isNotice",value:1,xtype:"hidden"}]}),this.setup(b)]}]});};SearchNotice.prototype.setup=function(b){return this.grid(b);};SearchNotice.prototype.grid=function(g){var f=new Ext.grid.ColumnModel({columns:[new Ext.grid.RowNumberer(),{header:"newsId",dataIndex:"newsId",hidden:true},{header:"新闻标题",width:120,dataIndex:"subject"},{header:"作者",width:120,dataIndex:"author"},{header:"创建时间",dataIndex:"createtime",renderer:function(a){if(a!=null){return a.substring(0,10);}}},{header:"回复次数",width:120,dataIndex:"replyCounts"},{header:"浏览数",width:120,dataIndex:"viewCounts"},{header:"状态",width:120,dataIndex:"status",renderer:function(a){if(a!=null&&a==0){return'<font color="red">禁用</font>';}else{if(a==1){return'<font color="green">激活</font>';}}}}],defaults:{sortable:true,menuDisabled:false,width:100}});var e=this.store();e.load({params:{start:0,limit:7,searchContent:g}});var h=new Ext.grid.GridPanel({id:"SearchNoticeGrid",store:e,trackMouseOver:true,disableSelection:false,autoScroll:true,loadMask:true,autoHeight:true,sortable:false,cm:f,viewConfig:{forceFit:true,enableRowBody:false,showPreview:false},bbar:new HT.PagingBar({store:e,pageSize:7})});h.addListener("rowdblclick",function(b,c,a){b.getSelectionModel().each(function(d){App.clickTopTab("NewsDetail",d.data.newsId,function(){AppUtil.removeTab("NewsDetail");});});});return h;};SearchNotice.prototype.store=function(){var b=new Ext.data.Store({baseParams:{"isNotice":1},proxy:new Ext.data.HttpProxy({url:__ctxPath+"/info/searchNews.do"}),reader:new Ext.data.JsonReader({root:"result",totalProperty:"totalCounts",id:"id",fields:[{name:"newsId",type:"int"},"sectionId","subjectIcon","subject","author","createtime","expTime","replyCounts","viewCounts","issuer","content","updateTime","status","isDeskImage","isNotice","sn","section"]}),remoteSort:true});b.setDefaultSort("newsId","desc");return b;};