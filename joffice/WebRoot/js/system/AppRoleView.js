Ext.ns("AppRoleView");
AppRoleView = Ext.extend(Ext.Panel, {
	searchPanel : null,
	gridPanel : null,
	store : null,
	topbar : null,
	constructor : function(b) {
		Ext.applyIf(this, b);
		this.initUIComponents();
		AppRoleView.superclass.constructor.call(this, {
					id : "AppRoleView",
					title : "角色列表",
					iconCls : "menu-role",
					region : "center",
					layout : "border",
					items : [this.searchPanel, this.gridPanel]
				});
	},
	initUIComponents : function() {
		this.searchPanel = new Ext.FormPanel({
					height : 35,
					region : "north",
					frame : false,
					layoutConfig : {
						padding : "5",
						align : "middle"
					},
					id : "AppRoleSearchForm",
					layout : "hbox",
					defaults : {
						xtype : "label",
						border : false,
						margins : {
							top : 2,
							right : 4,
							bottom : 2,
							left : 4
						}
					},
					items : [{
								text : "角色名称"
							}, {
								xtype : "textfield",
								name : "Q_roleName_S_LK"
							}, {
								text : "角色描述"
							}, {
								xtype : "textfield",
								name : "Q_roleDesc_S_LK"
							}, {
								xtype : "button",
								text : "查询",
								iconCls : "search",
								handler : function() {
									var b = Ext.getCmp("AppRoleSearchForm");
									var a = Ext.getCmp("AppRoleGrid");
									if (b.getForm().isValid()) {
										$search({
													searchPanel : b,
													gridPanel : a
												});
									}
								}
							}]
				});
		this.store = new Ext.data.Store({
					proxy : new Ext.data.HttpProxy({
								url : __ctxPath + "/system/listAppRole.do"
							}),
					reader : new Ext.data.JsonReader({
								root : "result",
								totalProperty : "totalCounts",
								id : "id",
								fields : [{
											name : "roleId",
											type : "int"
										}, "roleName", "roleDesc", {
											name : "status",
											type : "int"
										}, "isDefaultIn"]
							}),
					remoteSort : true
				});
		this.store.setDefaultSort("roleId", "desc");
		this.store.load({
					params : {
						start : 0,
						limit : 25
					}
				});
		var c = new Ext.grid.CheckboxSelectionModel();
		var d = new Ext.grid.ColumnModel({
			columns : [c, new Ext.grid.RowNumberer(), {
						header : "roleId",
						dataIndex : "roleId",
						hidden : true
					}, {
						header : "状态",
						dataIndex : "status",
						width : 30,
						renderer : function(b) {
							var a = "";
							if (b == "1") {
								a += '<img title="激活" src="'
										+ __ctxPath
										+ '/images/flag/customer/effective.png"/>';
							} else {
								a += '<img title="禁用" src="'
										+ __ctxPath
										+ '/images/flag/customer/invalid.png"/>';
							}
							return a;
						}
					}, {
						header : "角色名称",
						dataIndex : "roleName",
						width : 200
					}, {
						header : "角色描述",
						dataIndex : "roleDesc",
						width : 400
					}, {
						header : "管理",
						dataIndex : "roleId",
						width : 80,
						renderer : function(b, l, p, n, a) {
							var r = p.data.roleId;
							var q = p.data.roleName;
							var m = p.data.isDefaultIn;
							var o = "";
							if (r != -1) {
								if (m == "0") {
									if (isGranted("_AppRoleDel")) {
										o = '<button title="删除" value=" " class="btn-del" onclick="AppRoleView.remove('
												+ r + ')"></button>';
									}
									if (isGranted("_AppRoleEdit")) {
										o += '&nbsp;<button title="编辑" value=" " class="btn-edit" onclick="AppRoleView.edit('
												+ r + ')"></button>';
									}
									if (isGranted("_AppRoleGrant")) {
										o += '&nbsp;<button title="授权" value=" " class="btn-grant" onclick="AppRoleView.grant('
												+ r
												+ ",'"
												+ q
												+ "')\">&nbsp;</button>";
									}
								} else {
									o = '<button title="复制" value=" " class="btn-copyrole" onclick="AppRoleView.copy('
											+ r + ')"></button>';
								}
							}
							return o;
						}
					}],
			defaults : {
				sortable : true,
				menuDisabled : false,
				width : 100
			}
		});
		this.gridPanel = new Ext.grid.GridPanel({
					id : "AppRoleGrid",
					region : "center",
					tbar : this.topbar(),
					store : this.store,
					trackMouseOver : true,
					disableSelection : false,
					loadMask : true,
					cm : d,
					sm : c,
					viewConfig : {
						forceFit : true,
						enableRowBody : false,
						showPreview : false
					},
					bbar : new HT.PagingBar({
								store : this.store
							})
				});
		this.gridPanel.addListener("rowdblclick", function(b, e, a) {
					b.getSelectionModel().each(function(f) {
								if (f.data.isDefaultIn == "0"
										&& f.data.roleId != -1) {
									AppRoleView.edit(f.data.roleId);
								}
							});
				});
	}
});
AppRoleView.prototype.topbar = function() {
	var b = new Ext.Toolbar({
				id : "AppRoleFootBar",
				height : 30,
				bodyStyle : "text-align:left",
				items : []
			});
	if (isGranted("_AppRoleAdd")) {
		b.add(new Ext.Button({
					iconCls : "btn-add",
					text : "添加角色",
					handler : function() {
						new AppRoleForm().show();
					}
				}));
	}
	if (isGranted("_AppRoleDel")) {
		b.add(new Ext.Button({
			iconCls : "btn-del",
			text : "删除角色",
			handler : function() {
				var i = Ext.getCmp("AppRoleGrid");
				var a = i.getSelectionModel().getSelections();
				if (a.length == 0) {
					Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
					return;
				}
				var h = Array();
				var g = "";
				for (var j = 0; j < a.length; j++) {
					if (a[j].data.isDefaultIn == "0" && a[j].data.roleId != -1) {
						h.push(a[j].data.roleId);
					} else {
						g += a[j].data.roleName + ",";
					}
				}
				if (g == "") {
					AppRoleView.remove(h);
				} else {
					Ext.ux.Toast.msg("信息", g + "不能被删除！");
				}
			}
		}));
	}
	return b;
};
AppRoleView.remove = function(c) {
	var d = Ext.getCmp("AppRoleGrid");
	Ext.Msg.confirm("信息确认", "您确认要删除该记录吗？", function(a) {
				if (a == "yes") {
					Ext.Ajax.request({
								url : __ctxPath + "/system/multiDelAppRole.do",
								params : {
									ids : c
								},
								method : "post",
								success : function() {
									Ext.ux.Toast.msg("信息", "成功删除所选记录！");
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
AppRoleView.edit = function(b) {
	new AppRoleForm({
				roleId : b,
				isCopy : 0
			}).show();
};
AppRoleView.grant = function(c, d) {
	new RoleGrantRightView(c, d);
};
AppRoleView.copy = function(b) {
	new AppRoleForm({
				roleId : b,
				isCopy : 1
			}).show();
};