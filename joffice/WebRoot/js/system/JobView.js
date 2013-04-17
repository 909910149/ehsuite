JobView = Ext.extend(Ext.Panel, {
	constructor : function(b) {
		Ext.applyIf(this, b);
		this.initUIComponents();
		JobView.superclass.constructor.call(this, {
					id : "JobView",
					title : "职位人员管理",
					region : "center",
					layout : "border",
					border : false,
					iconCls : "menu-job",
					items : [this.gridPanel, this.searchPanel,
							this.jobTreePanel]
				});
	},
	initUIComponents : function() {
		this.topbar = new Ext.Toolbar({
					defaultType : "button",
					items : [{
						text : "添加",
						iconCls : "add-user",
						scope : this,
						handler : function() {
							var a = Ext.getCmp("jobViewJobTreePanel")
									.getSelectionModel().getSelectedNode();
							if (a != null && a.id > 0) {
								new UserJobForm({
											jobId : a.id,
											jobName : a.text
										}).show();
							} else {
								new UserJobForm({}).show();
							}
						}
					}, "-", {
						text : "删除",
						iconCls : "btn-del",
						scope : this,
						handler : this.removeByIds
					}]
				});
		this.searchPanel = new Ext.FormPanel({
					id : "JobViewSearchPanel",
					region : "north",
					layout : "hbox",
					border : false,
					height : 40,
					frame : false,
					keys : {
						key : Ext.EventObject.ENTER,
						scope : this,
						fn : this.search
					},
					layoutConfig : {
						padding : "5px",
						align : "middle"
					},
					defaults : {
						xtype : "label",
						border : false,
						margins : {
							top : 0,
							left : 5,
							right : 5,
							bottom : 0
						}
					},
					items : [{
								text : "用户账号："
							}, {
								xtype : "textfield",
								name : "Q_appUser.username_S_LK",
								maxLength : 256
							}, {
								text : "用户名称："
							}, {
								xtype : "textfield",
								name : "Q_appUser.fullname_S_LK",
								maxLength : 256
							}, {
								text : "主职位(是/否)："
							}, {
								xtype : "combo",
								hiddenName : "Q_isMain_SN_EQ",
								mode : "local",
								triggerAction : "all",
								editable : false,
								store : [["0", "否"], ["1", "是"]],
								emptyText : "全部"
							}, {
								xtype : "button",
								text : "搜索",
								iconCls : "search",
								scope : this,
								handler : this.search
							}, {
								xtype : "button",
								text : "清空",
								iconCls : "reset",
								scope : this,
								handler : this.reset
							}]
				});
		this.gridPanel = new HT.GridPanel({
					id : "jobUserGrid",
					title : "职位人员列表",
					region : "center",
					tbar : this.topbar,
					rowActions : true,
					url : __ctxPath + "/system/listUserJob.do",
					trackMouseOver : true,
					disableSelection : false,
					loadMask : true,
					viewConfig : {
						forceFit : true,
						enableRowBody : false,
						showPreview : false
					},
					fields : [{
								name : "userJobId",
								type : "int"
							}, "job", "appUser", "isMain"],
					columns : [{
								header : "userJobId",
								dataIndex : "userJobId",
								hidden : true
							}, {
								header : "员工账号",
								dataIndex : "appUser",
								renderer : function(a) {
									return a.userId;
								}
							}, {
								header : "员工名称",
								dataIndex : "appUser",
								renderer : function(a) {
									return a.username;
								}
							}, {
								header : "职位",
								dataIndex : "job",
								renderer : function(a) {
									return a.jobName;
								}
							}, {
								header : "主职位(是/否)",
								dataIndex : "isMain",
								renderer : function(a) {
									return a == 1 ? "是" : "否";
								}
							}, new Ext.ux.grid.RowActions({
										header : "管理",
										width : 100,
										actions : [{
													iconCls : "btn-del",
													qtip : "删除",
													style : "margin : 0 3px 0 3px"
												}, {
													iconCls : "btn-edit",
													qtip : "编辑",
													style : "margin : 0 3px 0 3px"
												}, {
													iconCls : "btn-showDetail",
													qtip : "查看职位信息",
													style : "margin : 0 3px 0 3px"
												}],
										listeners : {
											scope : this,
											"action" : this.onRowActions
										}
									})]
				});
		this.gridPanel.addListener("rowdblclick", function(c, a, b) {
					c.getSelectionModel().each(function(d) {
								new UserJobForm({
											userJobId : d.data.userJobId,
											jobName : d.data.job.jobName
										}).show();
							});
				});
		this.jobTreePanel = new Ext.tree.TreePanel({
			id : "jobViewJobTreePanel",
			region : "west",
			border : false,
			width : 200,
			collapsible : false,
			autoScroll : true,
			split : true,
			title : "职位信息列表",
			tbar : new Ext.Toolbar({
				defaultType : "button",
				items : [{
							text : "刷新",
							iconCls : "btn-refresh",
							handler : function() {
								Ext.getCmp("jobViewJobTreePanel").root.reload();
							}
						}, {
							text : "展开",
							iconCls : "btn-expand",
							handler : function() {
								Ext.getCmp("jobViewJobTreePanel").expandAll();
							}
						}, {
							text : "收起",
							iconCls : "btn-collapse",
							handler : function() {
								Ext.getCmp("jobViewJobTreePanel").collapseAll();
							}
						}]
			}),
			loader : new Ext.tree.TreeLoader({
						url : __ctxPath + "/hrm/treeLoadJob.do"
					}),
			root : new Ext.tree.AsyncTreeNode({
						expanded : true
					}),
			rootVisible : false,
			listeners : {
				"click" : this.clickNode
			}
		});
		this.jobTreePanel.on("contextmenu", f, this.jobTreePanel);
		var j = new Ext.menu.Menu({
					id : "jobTreeMenu",
					items : [{
								text : "新增职位信息",
								iconCls : "btn-add",
								scope : this,
								handler : h
							}, {
								text : "修改职位信息",
								iconCls : "btn-edit",
								scope : this,
								handler : g
							}, {
								text : "删除职位信息",
								iconCls : "btn-del",
								scope : this,
								handler : i
							}]
				});
		function f(b, a) {
			jobSelected = new Ext.tree.TreeNode({
						id : b.id,
						text : b.text
					});
			j.showAt(a.getXY());
		}
		function h() {
			var a = jobSelected.id;
			if (a > 0) {
				new JobForm({
							parentId : a
						}).show();
			} else {
				new JobForm({
							parentId : 0
						}).show();
			}
		}
		function g() {
			var a = jobSelected.id;
			if (a > 0) {
				new JobForm({
							jobId : a
						}).show();
			} else {
				Ext.ux.Toast.msg("操作提示", "对不起，公司名称不能修改！");
			}
		}
		function i() {
			var a = jobSelected.id;
			if (a > 0) {
				Ext.Msg.confirm("操作提示", "你真的要删除该职位信息吗？", function(c) {
							if (c == "yes") {
								var b = Ext.getCmp("jobViewJobTreePanel");
								Ext.Ajax.request({
											url : __ctxPath
													+ "/hrm/deleteJob.do?jobId="
													+ a,
											method : "post",
											waitMsg : "数据正在提交，请稍后...",
											success : function(l, d) {
												var e = Ext.util.JSON
														.decode(l.responseText);
												if (e.success) {
													Ext.ux.Toast.msg("操作提示",
															"删除该职位信息操作成功！");
													b.root.reload();
												} else {
													Ext.ux.Toast.msg("操作提示",
															"对不起，删除该职位信息操作失败！");
												}
											}
										});
							}
						});
			} else {
				Ext.ux.Toast.msg("操作提示", "对不起，公司名称不能删除！");
			}
		}
	},
	onRowActions : function(j, g, i, h, f) {
		switch (i) {
			case "btn-showDetail" :
				this.showDetail.call(this, g);
				break;
			case "btn-edit" :
				this.editById.call(this, g);
				break;
			case "btn-del" :
				this.removeById.call(this, g.data.userJobId);
				break;
			default :
				break;
		}
	},
	reset : function() {
		var b = Ext.getCmp("JobViewSearchPanel");
		b.getForm().reset();
	},
	search : function() {
		var l = Ext.getCmp("JobViewSearchPanel");
		var p = l.getCmpByName("Q_appUser.username_S_LK").getValue();
		var k = l.getCmpByName("Q_appUser.fullname_S_LK").getValue();
		var i = l.getCmpByName("Q_isMain_SN_EQ").getValue();
		var n = Ext.getCmp("jobUserGrid");
		var j = Ext.getCmp("jobViewJobTreePanel");
		var m = j.getSelectionModel().getSelectedNode();
		var o = n.getStore();
		if (m != null && m.id > 0) {
			o.baseParams = {
				"Q_job.jobId_L_EQ" : m.id,
				"Q_appUser.username_S_LK" : p,
				"Q_appUser.fullname_S_LK" : k,
				"Q_isMain_SN_LK" : i
			};
		} else {
			o.baseParams = {
				"Q_appUser.username_S_LK" : p,
				"Q_appUser.fullname_S_LK" : k,
				"Q_isMain_SN_EQ" : i
			};
		}
		if (l.getForm().isValid()) {
			o.reload({
						params : {
							start : 0,
							limit : 25
						}
					});
		}
	},
	editById : function(b) {
		new UserJobForm({
					userJobId : b.data.userJobId,
					jobName : b.data.job.jobName
				}).show();
	},
	showDetail : function(d) {
		var c = d.data.appUser;
		UserJobDetailForm.show(c.userId, c.username);
	},
	removeById : function(b) {
		Ext.Msg.confirm("操作提示", "你真的要删除所选数据吗？", function(a) {
					if (a == "yes") {
						Ext.Ajax.request({
									url : __ctxPath
											+ "/system/multiDelUserJob.do",
									params : {
										ids : b
									},
									method : "post",
									waitMsg : "数据正在提交，请稍后...",
									success : function(f, e) {
										Ext.ux.Toast.msg("操作提示", "数据提交成功！");
										Ext.getCmp("jobUserGrid").getStore()
												.reload();
									},
									failure : function() {
										Ext.ux.Toast.msg("操作提示", "对不起，数据提交失败！");
									}
								});
					}
				});
	},
	removeByIds : function() {
		var g = Ext.getCmp("jobUserGrid");
		var f = g.getSelectionModel().getSelections();
		if (f.length > 0) {
			var h = new Array();
			for (var e = 0; e < f.length; e++) {
				h.push(f[e].data.userJobId);
			}
			this.removeById(h);
		} else {
			Ext.ux.Toast.msg("操作提示", "对不起，请选择要删除的员工信息！");
		}
	},
	clickNode : function(j) {
		if (j != null) {
			var k = Ext.getCmp("JobViewSearchPanel");
			var g = k.getCmpByName("Q_appUser.username_S_LK").getValue();
			var i = k.getCmpByName("Q_appUser.fullname_S_LK").getValue();
			var h = k.getCmpByName("Q_isMain_SN_EQ").getValue();
			var l = Ext.getCmp("jobUserGrid").getStore();
			if (j != null && j.id > 0) {
				l.baseParams = {
					"Q_job.jobId_L_EQ" : j.id,
					"Q_appUser.username_S_LK" : g,
					"Q_appUser.fullname_S_LK" : i,
					"Q_isMain_SN_EQ" : h
				};
			} else {
				l.baseParams = {
					"Q_appUser.username_S_LK" : g,
					"Q_appUser.fullname_S_LK" : i,
					"Q_isMain_SN_EQ" : h
				};
			}
			l.reload({
						params : {
							start : 0,
							limit : 25
						}
					});
		}
	}
});