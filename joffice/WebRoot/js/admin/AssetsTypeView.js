Ext.ns("AssetsTypeView");
var AssetsTypeView = function() {
	return new Ext.Panel({
		id : "AssetsTypeView",
		title : "[AssetsType]列表",
		layout : "border",
		autoScroll : true,
		items : [new Ext.FormPanel({
							region : "north",
							id : "AssetsTypeSearchForm",
							height : 40,
							frame : false,
							border : false,
							layout : "hbox",
							layoutConfig : {
								padding : "5",
								align : "center"
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
										text : "请输入查询条件:"
									}, {
										text : "分类名称"
									}, {
										xtype : "textfield",
										name : "Q_typeName_S_LK"
									}, {
										xtype : "button",
										text : "查询",
										iconCls : "search",
										handler : function() {
											var d = Ext
													.getCmp("AssetsTypeSearchForm");
											var c = Ext
													.getCmp("AssetsTypeGrid");
											if (d.getForm().isValid()) {
												$search({
															searchPanel : d,
															gridPanel : c
														});
											}
										}
									}]
						}), this.setup()]
	});
};
AssetsTypeView.prototype.setup = function() {
	return this.grid();
};
AssetsTypeView.prototype.grid = function() {
	var g = new Ext.grid.CheckboxSelectionModel();
	var f = new Ext.grid.ColumnModel({
		columns : [g, new Ext.grid.RowNumberer(), {
					header : "assetsTypeId",
					dataIndex : "assetsTypeId",
					hidden : true
				}, {
					header : "分类名称",
					dataIndex : "typeName"
				}, {
					header : "管理",
					dataIndex : "assetsTypeId",
					width : 50,
					sortable : false,
					renderer : function(d, l, n, a, m) {
						var b = n.data.assetsTypeId;
						var c = '<button title="删除" value=" " class="btn-del" onclick="AssetsTypeView.remove('
								+ b + ')">&nbsp;</button>';
						c += '&nbsp;<button title="编辑" value=" " class="btn-edit" onclick="AssetsTypeView.edit('
								+ b + ')">&nbsp;</button>';
						return c;
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
					limit : 25
				}
			});
	var h = new Ext.grid.GridPanel({
				id : "AssetsTypeGrid",
				tbar : this.topbar(),
				store : e,
				region : "center",
				trackMouseOver : true,
				disableSelection : false,
				loadMask : true,
				cm : f,
				sm : g,
				viewConfig : {
					forceFit : true,
					enableRowBody : false,
					showPreview : false
				},
				bbar : new HT.PagingBar({
							store : e
						})
			});
	h.addListener("rowdblclick", function(b, c, a) {
				b.getSelectionModel().each(function(d) {
							AssetsTypeView.edit(d.data.assetsTypeId);
						});
			});
	return h;
};
AssetsTypeView.prototype.store = function() {
	var b = new Ext.data.Store({
				proxy : new Ext.data.HttpProxy({
							url : __ctxPath + "/admin/listAssetsType.do"
						}),
				reader : new Ext.data.JsonReader({
							root : "result",
							totalProperty : "totalCounts",
							id : "id",
							fields : [{
										name : "assetsTypeId",
										type : "int"
									}, "typeName"]
						}),
				remoteSort : true
			});
	b.setDefaultSort("assetsTypeId", "desc");
	return b;
};
AssetsTypeView.prototype.topbar = function() {
	var b = new Ext.Toolbar({
				id : "AssetsTypeFootBar",
				height : 30,
				bodyStyle : "text-align:left",
				items : [{
							iconCls : "btn-add",
							text : "添加[AssetsType]",
							xtype : "button",
							handler : function() {
								new AssetsTypeForm();
							}
						}, {
							iconCls : "btn-del",
							text : "删除[AssetsType]",
							xtype : "button",
							handler : function() {
								var g = Ext.getCmp("AssetsTypeGrid");
								var a = g.getSelectionModel().getSelections();
								if (a.length == 0) {
									Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
									return;
								}
								var f = Array();
								for (var h = 0; h < a.length; h++) {
									f.push(a[h].data.assetsTypeId);
								}
								AssetsTypeView.remove(f);
							}
						}]
			});
	return b;
};
AssetsTypeView.remove = function(c) {
	var d = Ext.getCmp("AssetsTypeGrid");
	Ext.Msg.confirm("信息确认", "您确认要删除该记录吗？", function(a) {
				if (a == "yes") {
					Ext.Ajax.request({
								url : __ctxPath
										+ "/admin/multiDelAssetsType.do",
								params : {
									ids : c
								},
								method : "post",
								success : function() {
									Ext.ux.Toast.msg("信息提示", "成功删除所选记录！");
									d.getStore().reload({
												params : {
													start : 0,
													limit : 25
												}
											});
								}
							});
				}
			});
};
AssetsTypeView.edit = function(b) {
	new AssetsTypeForm(b);
};