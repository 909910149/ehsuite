AppRoleForm = Ext.extend(Ext.Window, {
	formPanel : null,
	constructor : function(b) {
		Ext.applyIf(this, b);
		this.initUIComponents();
		AppRoleForm.superclass.constructor.call(this, {
					layout : "fit",
					items : this.formPanel,
					modal : true,
					id : "AppRoleFormWin",
					title : "角色详细信息",
					iconCls : "menu-role",
					width : 370,
					height : 220,
					buttonAlign : "center",
					buttons : this.buttons
				});
	},
	initUIComponents : function() {
		this.formPanel = new Ext.FormPanel({
					url : __ctxPath + "/system/saveAppRole.do?isCopy="
							+ this.isCopy,
					layout : "form",
					id : "AppRoleForm",
					border : false,
					bodyStyle : "padding:5px;",
					defaults : {
						anchor : "100%,100%"
					},
					formId : "AppRoleFormId",
					defaultType : "textfield",
					items : [{
								name : "appRole.roleId",
								xtype : "hidden",
								value : this.roleId == null ? "" : this.roleId
							}, {
								fieldLabel : "角色名称",
								name : "appRole.roleName"
							}, {
								fieldLabel : "角色描述",
								xtype : "textarea",
								name : "appRole.roleDesc"
							}, {
								fieldLabel : "状态",
								hiddenName : "appRole.status",
								xtype : "combo",
								mode : "local",
								editable : true,
								triggerAction : "all",
								store : [["0", "禁用"], ["1", "可用"]],
								value : 1
							}]
				});
		if (this.roleId != null && this.roleId != "undefined") {
			this.formPanel.loadData({
				url : __ctxPath + "/system/getAppRole.do?roleId=" + this.roleId,
				preName : "appRole",
				root : "data"
			});
		}
		this.buttons = [{
					text : "保存",
					iconCls : "btn-save",
					scope : this,
					handler : this.save.createCallback(this)
				}, {
					text : "取消",
					iconCls : "btn-cancel",
					scope : this,
					handler : function() {
						this.close();
					}
				}];
	},
	save : function() {
		var c = Ext.getCmp("AppRoleForm");
		if (this.isCopy == 1) {
			var d = Ext.getCmp("roleName").getValue();
			Ext.Ajax.request({
						url : __ctxPath + "/system/checkAppRole.do",
						params : {
							roleName : d
						},
						method : "post",
						success : function(a) {
							var b = Ext.util.JSON.decode(a.responseText);
							if (b.success) {
								if (c.getForm().isValid()) {
									c.getForm().submit({
										method : "post",
										waitMsg : "正在提交数据...",
										success : function(h, g) {
											Ext.ux.Toast.msg("操作信息", "成功信息保存！");
											Ext.getCmp("AppRoleGrid")
													.getStore().reload();
											Ext.getCmp("AppRoleFormWin")
													.close();
										},
										failure : function(h, g) {
											Ext.MessageBox.show({
														title : "操作信息",
														msg : "信息保存出错，请联系管理员！",
														buttons : Ext.MessageBox.OK,
														icon : "ext-mb-error"
													});
											Ext.getCmp("AppRoleFormWin")
													.close();
										}
									});
								}
							} else {
								Ext.ux.Toast.msg("提示信息", "该角色名字已经存在，请更改！");
							}
						},
						failure : function() {
						}
					});
		} else {
			if (c.getForm().isValid()) {
				c.getForm().submit({
							method : "post",
							waitMsg : "正在提交数据...",
							success : function(b, a) {
								Ext.ux.Toast.msg("操作信息", "成功信息保存！");
								Ext.getCmp("AppRoleGrid").getStore().reload();
								Ext.getCmp("AppRoleFormWin").close();
							},
							failure : function(b, a) {
								Ext.MessageBox.show({
											title : "操作信息",
											msg : "信息保存出错，请联系管理员！",
											buttons : Ext.MessageBox.OK,
											icon : "ext-mb-error"
										});
								Ext.getCmp("AppRoleFormWin").close();
							}
						});
			}
		}
	}
});