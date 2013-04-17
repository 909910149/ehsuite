Ext.ns("DepartmentView");
var DepartmentView = function() {
	return this.setup();
};
DepartmentView.prototype.setup = function() {
	var v;
	var q = this.initData();
	var w = new Ext.grid.CheckboxSelectionModel();
	var p = new Ext.grid.ColumnModel({
		columns : [w, {
					header : "userId",
					dataIndex : "userId",
					hidden : true
				}, {
					header : "",
					sortable : true,
					width : 5,
					dataIndex : "sn",
					renderer : function(b, e, f, a, c, d) {
						f.data["sn"] = a + 1;
						d.commitChanges();
						return f.data["sn"];
					}
				}, {
					header : "状态",
					sortable : true,
					dataIndex : "appUser",
					width : 30,
					renderer : function(a) {
						if (a) {
							var c = a.status;
							var b = "";
							if (c == "1") {
								b += '<img title="激活" src="'
										+ __ctxPath
										+ '/images/flag/customer/effective.png"/>';
							} else {
								b += '<img title="禁用" src="'
										+ __ctxPath
										+ '/images/flag/customer/invalid.png"/>';
							}
							return b;
						}
					}
				}, {
					header : "账号",
					sortable : true,
					dataIndex : "appUser",
					width : 60,
					renderer : function(a) {
						return a == null ? "" : a.username;
					}
				}, {
					header : "用户名",
					sortable : true,
					dataIndex : "appUser",
					width : 60,
					renderer : function(a) {
						return a != null ? a.fullname : "";
					}
				}, {
					header : "所属部门",
					sortable : true,
					dataIndex : "department",
					width : 60,
					renderer : function(a) {
						return a.depName == null ? "" : a.depName;
					}
				}, {
					header : "主部门(是/否)",
					sortable : true,
					dataIndex : "isMain",
					width : 60,
					renderer : function(a) {
						return a == "1" ? "是" : "否";
					}
				}, {
					header : "管理",
					dataIndex : "appUser",
					sortable : true,
					width : 100,
					renderer : function(e, j, c, k, g, h) {
						if (e) {
							var d = e.userId;
							var b = e.username;
							var f = e.department.depId;
							var a = "";
							if (isGranted("_AppUserDel") && d != 1) {
								a += '<button title="删除" value=" " class="btn-del" onclick="DepartmentView.remove('
										+ c.data.depUserId + ')"></button>';
							} else {
								a += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
							}
							if (isGranted("_AppUserEdit") && d != 1) {
								a += '&nbsp;<button title="编辑" value=" " class="btn-edit" onclick="DepartmentView.edit('
										+ c.data.appUser.userId
										+ ",'"
										+ c.data.appUser.username
										+ "')\"></button>";
							} else {
								a += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
							}
							a += '&nbsp;<button title="查看部门信息" value=" " class="btn-showDetail" onclick="DepartmentView.show('
									+ c.data.appUser.userId
									+ ",'"
									+ c.data.appUser.username
									+ "')\"></button>";
							a += '&nbsp;<button title="添加上下级" value=" " class="btn-relativeJob" onclick="DepartmentView.addRelativeJob('
									+ d
									+ ",'"
									+ b
									+ "','"
									+ f
									+ "')\"></button>";
							return a;
						}
					}
				}],
		defaults : {
			sortable : true,
			menuDisabled : true,
			width : 100
		},
		listeners : {
			hiddenchange : function(c, b, a) {
				saveConfig(b, a);
			}
		}
	});
	var y = new Ext.grid.GridPanel({
				region : "center",
				id : "DepUsersView",
				tbar : new Ext.Toolbar({
							defaultType : "button",
							items : [{
										text : "添加",
										iconCls : "add-user",
										handler : function() {
											DepartmentView.add();
										}
									}, "-", {
										text : "删除",
										iconCls : "btn-del",
										handler : function() {
											DepartmentView.multiRemove();
										}
									}]
						}),
				height : 800,
				title : "科室人员列表",
				store : q,
				shim : true,
				trackMouseOver : true,
				disableSelection : false,
				loadMask : true,
				cm : p,
				sm : w,
				viewConfig : {
					forceFit : true,
					enableRowBody : false,
					showPreview : false
				},
				bbar : new HT.PagingBar({
							store : q
						})
			});
	y.addListener("rowdblclick", t);
	function t(b, c, a) {
		b.getSelectionModel().each(function(d) {
			DepartmentView.edit(d.data.appUser.userId, d.data.appUser.username);
		});
	}
	q.load({
				params : {
					start : 0,
					limit : 25
				}
			});
	var u = new Ext.tree.TreePanel({
				region : "west",
				id : "departmentTreePanel",
				title : "部门信息列表",
				collapsible : true,
				autoScroll : true,
				split : true,
				height : 800,
				width : 180,
				tbar : new Ext.Toolbar({
							items : [{
										xtype : "button",
										iconCls : "btn-refresh",
										text : "刷新",
										handler : function() {
											u.root.reload();
										}
									}, "-", {
										xtype : "button",
										text : "展开",
										iconCls : "btn-expand",
										handler : function() {
											u.expandAll();
										}
									}, "-", {
										xtype : "button",
										text : "收起",
										iconCls : "btn-collapse",
										handler : function() {
											u.collapseAll();
										}
									}]
						}),
				loader : new Ext.tree.TreeLoader({
							url : __ctxPath + "/system/listDepartment.do"
						}),
				root : new Ext.tree.AsyncTreeNode({
							expanded : true
						}),
				rootVisible : false,
				listeners : {
					"click" : DepartmentView.clickNode
				}
			});
	if (isGranted("_DepartmentAdd") || isGranted("_DepartmentEdit")
			|| isGranted("_DepartmentDel")) {
		u.on("contextmenu", z, u);
	}
	function z(c, b) {
		v = new Ext.tree.TreeNode({
					id : c.id,
					text : c.text
				});
		var a = new Ext.menu.Menu({
					items : []
				});
		a.clearMons();
		if (isGranted("_DepartmentAdd")) {
			a.add({
						text : "新建部门",
						iconCls : "btn-add",
						scope : this,
						handler : o
					});
		}
		if (isGranted("_DepartmentEdit")) {
			a.add({
						text : "修改部门信息",
						iconCls : "btn-edit",
						scope : this,
						handler : s
					});
		}
		if (isGranted("_DepartmentDel")) {
			a.add({
						text : "删除部门",
						iconCls : "btn-delete",
						scope : this,
						handler : r
					});
		}
		a.showAt(b.getXY());
	}
	function o() {
		var b = v.id;
		var a = Ext.getCmp("departmentForm");
		if (a == null) {
			if (b > 0) {
				new DepartmentForm({
							nodeId : b
						}).show();
			} else {
				new DepartmentForm({
							nodeId : 0
						}).show();
			}
			Ext.getCmp("departmentTreePanel").root.reload();
		}
	}
	function r() {
		var a = v.id;
		if (a > 0) {
			Ext.Msg.confirm("删除操作", "你确定删除部门?", function(b) {
						if (b == "yes") {
							Ext.Ajax.request({
										url : __ctxPath
												+ "/system/removeDepartment.do?depId="
												+ a,
										success : function(e, c) {
											var d = Ext.util.JSON
													.decode(e.responseText);
											if (d.success == false) {
												Ext.ux.Toast.msg("操作信息",
														d.message);
											} else {
												Ext.ux.Toast.msg("操作信息",
														"删除成功!");
											}
											Ext.getCmp("departmentTreePanel").root
													.reload();
										},
										failure : function(d, c) {
										}
									});
						}
					});
		} else {
			Ext.ux.Toast.msg("警告", "总公司不能被删除");
		}
	}
	function s() {
		var b = v.id;
		if (b > 0) {
			var a = Ext.getCmp("departmentForm");
			if (a == null) {
				new DepartmentForm().show();
				a = Ext.getCmp("departmentForm");
			}
			a.form.load({
						url : __ctxPath + "/system/detailDepartment.do",
						params : {
							depId : b
						},
						method : "post",
						deferredRender : true,
						layoutOnTabChange : true,
						success : function() {
							Ext.getCmp("departmentTreePanel").root.reload();
						},
						failure : function() {
							Ext.ux.Toast.msg("编辑", "载入失败");
						}
					});
		} else {
			Ext.ux.Toast.msg("警告", "总公司不能修改！");
		}
	}
	var x = new Ext.FormPanel({
				id : "departmentViewSearchPanel",
				height : 40,
				frame : false,
				border : false,
				region : "north",
				layout : "form",
				layoutConfig : {
					padding : "5px",
					align : "middle"
				},
				items : [{
							xtype : "container",
							layout : "column",
							border : false,
							fieldLabel : "请输入查询条件",
							style : "margin-top:8px;",
							defaults : {
								xtype : "label",
								border : false,
								height : 25
							},
							layoutConfig : {
								align : "middle"
							},
							items : [{
										style : "margin:5px 5px 5px 5px;",
										text : "用户账号"
									}, {
										columnWidth : 0.2,
										xtype : "textfield",
										name : "Q_appUser.username_S_LK",
										maxLength : 256
									}, {
										style : "margin:5px 5px 5px 5px",
										text : "用户名称"
									}, {
										columnWidth : 0.2,
										xtype : "textfield",
										name : "Q_appUser.fullname_S_LK",
										maxLength : 256
									}, {
										style : "margin: 5px 5px 5px 5px;",
										text : "主部门(是/否)"
									}, {
										columnWidth : 0.2,
										xtype : "combo",
										hiddenName : "Q_isMain_SN_EQ",
										displayField : "name",
										valueField : "id",
										mode : "local",
										triggerAction : "all",
										editable : false,
										store : [["0", "否"], ["1", "是"]],
										emptyText : "全部"
									}, {
										style : "margin-left: 5px;",
										xtype : "button",
										text : "搜索",
										scope : this,
										iconCls : "search",
										handler : DepartmentView.search
									}, {
										xtype : "button",
										text : "清空",
										scope : this,
										iconCls : "reset",
										handler : DepartmentView.reset
									}]
						}]
			});
	var A = new Ext.Panel({
				id : "DepartmentView",
				title : "部门人员信息",
				closable : true,
				iconCls : "menu-department",
				layout : "border",
				items : [u, x, y],
				keys : [{
							key : Ext.EventObject.ESC,
							fn : DepartmentView.reset,
							scope : this
						}, {
							key : Ext.EventObject.ENTER,
							fn : DepartmentView.search,
							scope : this
						}]
			});
	return A;
};
DepartmentView.prototype.initData = function() {
	var b = new Ext.data.Store({
				proxy : new Ext.data.HttpProxy({
							url : __ctxPath + "/system/listDepUsers.do"
						}),
				reader : new Ext.data.JsonReader({
							root : "result",
							totalProperty : "totalCounts",
							fields : [{
										name : "depUserId",
										type : "int"
									}, {
										name : "isMain",
										type : "int"
									}, {
										name : "sn",
										type : "int"
									}, "department", "appUser"]
						}),
				remoteSort : true
			});
	b.setDefaultSort("id", "desc");
	return b;
};
DepartmentView.multiRemove = function() {
	var e = Ext.getCmp("DepUsersView");
	var g = e.getSelectionModel().getSelections();
	if (g != null && g.length > 0) {
		var h = new Array();
		for (var f = 0; f < g.length; f++) {
			h.push(g[f].data.depUserId);
		}
		DepartmentView.remove(h);
	} else {
		Ext.ux.Toast.msg("操作提示", "对不起，请选择你要删除的数据！");
	}
}, DepartmentView.remove = function(b) {
	Ext.Msg.confirm("删除操作", "你确定要删除该用户吗?", function(a) {
				if (a == "yes") {
					Ext.Ajax.request({
								url : __ctxPath + "/system/multiDelDepUsers.do",
								method : "post",
								params : {
									ids : b
								},
								success : function(d) {
									Ext.ux.Toast.msg("操作信息", "用户删除成功");
									Ext.getCmp("DepUsersView").getStore()
											.reload();
								},
								failure : function() {
									Ext.ux.Toast.msg("操作信息", "用户删除失败");
								}
							});
				}
			});
};
DepartmentView.clickNode = function(b) {
	DepartmentView.select(b);
};
DepartmentView.reset = function() {
	var b = Ext.getCmp("departmentViewSearchPanel");
	b.getForm().reset();
};
DepartmentView.search = function() {
	DepartmentView.select(null);
};
DepartmentView.select = function(n) {
	var o = Ext.getCmp("departmentViewSearchPanel");
	var l = o.getCmpByName("Q_appUser.username_S_LK").getValue();
	var j = o.getCmpByName("Q_appUser.fullname_S_LK").getValue();
	var k = o.getCmpByName("Q_isMain_SN_EQ").getValue();
	var m = Ext.getCmp("DepUsersView");
	var q = m.getStore();
	q.url = __ctxPath + "/system/listDepUsers.do";
	var p = {
		start : 0,
		limit : 25,
		"Q_appUser.username_S_LK" : l,
		"Q_appUser.fullname_S_LK" : j
	};
	if (k == "0") {
		p["Q_isMain_SN_EQ"] = "";
	} else {
		if (k == "1") {
			p["Q_isMain_SN_EQ"] = "1";
		}
	}
	if (n != null && n.id > 0) {
		p["depId"] = n.id;
	}
	q.reload({
				params : p
			});
};
DepartmentView.add = function() {
	var j = Ext.getCmp("departmentTreePanel").getSelectionModel()
			.getSelectedNode();
	var g = Ext.getCmp("centerTabPanel");
	var f = Ext.getCmp("AppUserForm");
	var h, k;
	if (j != null && j.id > 0) {
		h = j.id;
		k = j.text;
	}
	if (f == null) {
		f = new AppUserForm("增加员工", null, h, k);
		g.add(f);
	} else {
		g.remove(f);
		f = new AppUserForm("增加员工", null, h, k);
		g.add(f);
	}
	g.activate(f);
};
DepartmentView.edit = function(e, f) {
	var d = Ext.getCmp("departmentTreePanel").getSelectionModel()
			.getSelectedNode();
	AppUserView.edit(e, f);
};
DepartmentView.show = function(d, c) {
	DepUsersDetailForm.show(d, c);
};
DepartmentView.addRelativeJob = function(d, f, e) {
	new RelativeUserView({
				userId : d,
				username : f,
				depId : e
			}).show();
}, DepartmentView.sn = function(p, t) {
	var u = Ext.getCmp("DepUsersView");
	var n = u.getStore();
	var w = p;
	var v = p + t;
	var r = n.getAt(w);
	var o = n.getAt(v);
	var q = r.get("sn");
	var x = o.get("sn");
	r.data.sn = x;
	o.data.sn = q;
	var s = new Array();
	for (i = 0; i < n.getCount(); i++) {
		var y = n.getAt(i);
		s.push(y.data);
	}
	Ext.Ajax.request({
				url : __ctxPath + "/system/snDepUsers.do",
				method : "POST",
				success : function(b, a) {
					n.reload();
				},
				failure : function(b, a) {
				},
				params : {
					depParams : Ext.encode(s)
				}
			});
};