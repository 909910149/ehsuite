Ext.ns("SearchDocument");
var SearchDocument = function(b) {
	return this.getView(b);
};
SearchDocument.prototype.getView = function(b) {
	return new Ext.Panel({
		id : "SearchDocument",
		title : "搜索文档",
		iconCls : "menu-document",
		border : false,
		style : "padding-bottom:10px;",
		autoScroll : true,
		items : [{
			region : "center",
			anchor : "100%",
			items : [new Ext.FormPanel({
						id : "ALLDocumentSearchForm",
						height : 40,
						frame : false,
						border : false,
						layout : "hbox",
						layoutConfig : {
							padding : "5",
							align : "middle"
						},
						defaults : {
							xtype : "label",
							margins : {
								top : 0,
								right : 4,
								bottom : 4,
								left : 4
							}
						},
						items : [{
									text : "请输入条件:"
								}, {
									xtype : "textfield",
									name : "content",
									width : 150
								}, {
									xtype : "button",
									text : "查询",
									iconCls : "search",
									handler : function() {
										var a = Ext
												.getCmp("ALLDocumentSearchForm");
										var d = Ext
												.getCmp("SearchDocumentGrid");
										if (a.getForm().isValid()) {
											$search({
														searchPanel : a,
														gridPanel : d
													});
										}
									}
								}, {
									xtype : "button",
									text : "重置",
									iconCls : "reset",
									handler : function() {
										var a = Ext
												.getCmp("ALLDocumentSearchForm");
										a.getForm().reset();
									}
								}]
					}), this.setup(b)]
		}]
	});
};
SearchDocument.prototype.setup = function(b) {
	return this.grid(b);
};
SearchDocument.prototype.grid = function(g) {
	var f = new Ext.grid.ColumnModel({
		columns : [new Ext.grid.RowNumberer(), {
					header : "docId",
					dataIndex : "docId",
					hidden : true
				}, {
					header : "文档名称",
					dataIndex : "docName",
					width : 120
				}, {
					header : "创建人",
					dataIndex : "fullname"
				}, {
					header : "创建时间",
					dataIndex : "createtime"
				}, {
					header : "属性",
					width : 40,
					dataIndex : "isShared",
					renderer : function(a, c, d) {
						var b = d.data.isPublic;
						if (a == 1) {
							return '<img src="'
									+ __ctxPath
									+ '/images/flag/shared.png" alt="共享" title="共享文档" />';
						} else {
							if (b == "0") {
								return '<img src="'
										+ __ctxPath
										+ '/images/flag/lock.png" alt="私有" title="私有文档" />';
							} else {
								return '<img src="'
										+ __ctxPath
										+ '/images/btn/flow/unlockTask.png" alt="公共" title="公共文档" />';
							}
						}
					}
				}, {
					header : "附件",
					dataIndex : "haveAttach",
					renderer : function(b, d, l) {
						if (b == "" || b == "0") {
							return "无附件";
						} else {
							var c = l.data.attachFiles;
							var a = "";
							for (var i = 0; i < c.length; i++) {
								a += '<a href="#" onclick="FileAttachDetail.show('
										+ c[i].fileId
										+ ');" class="attachment">'
										+ c[i].fileName + "</a>";
								a += "&nbsp;";
							}
							return a;
						}
					}
				}],
		defaults : {
			sortable : true,
			menuDisabled : false,
			width : 100
		}
	});
	var e = this.store();
	e.load({
				params : {
					start : 0,
					limit : 25,
					content : g
				}
			});
	var h = new Ext.grid.GridPanel({
				id : "SearchDocumentGrid",
				store : e,
				trackMouseOver : true,
				disableSelection : false,
				loadMask : true,
				autoHeight : true,
				maxHeight : 600,
				cm : f,
				viewConfig : {
					forceFit : true,
					enableRowBody : false,
					showPreview : false
				},
				bbar : new HT.PagingBar({
							store : e,
							pageSize : 12
						})
			});
	h.addListener("rowdblclick", function(b, c, a) {
				b.getSelectionModel().each(function(m) {
							var d = m.data.docId;
							var n = m.data.docName;
							var o = Ext.getCmp("centerTabPanel");
							var p = Ext.getCmp("PulicDocumentDetail");
							if (p == null) {
								p = new PublicDocumentDetail({
											docId : d,
											docName : n
										});
								Ext.getCmp("PublicDocumentTopBar").hide();
								o.add(p);
								o.activate(p);
							} else {
								o.remove("PulicDocumentDetail");
								p = new PublicDocumentDetail({
											docId : d,
											docName : n
										});
								Ext.getCmp("PublicDocumentTopBar").hide();
								o.add(p);
								o.activate(p);
							}
						});
			});
	return h;
};
SearchDocument.prototype.store = function() {
	var b = new Ext.data.Store({
				proxy : new Ext.data.HttpProxy({
							url : __ctxPath + "/document/searchDocument.do"
						}),
				reader : new Ext.data.JsonReader({
							root : "result",
							totalProperty : "totalCounts",
							id : "id",
							fields : [{
										name : "docId",
										type : "int"
									}, "docName", "fullname", {
										name : "isPublic",
										mapping : "docFolder.isShared"
									}, "content", "createtime", "haveAttach",
									"attachFiles", "isShared"]
						}),
				remoteSort : true
			});
	b.setDefaultSort("docId", "desc");
	return b;
};