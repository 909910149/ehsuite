Ext.ns("App");
App.TreeLoader = Ext.extend(Ext.ux.tree.XmlTreeLoader, {
			processAttributes : function(b) {
				if (b.tagName == "Function") {
					b.leaf = true;
				} else {
					if (b.tagName == "Item") {
						b.loaded = true;
						b.expanded = true;
					} else {
						if (b.tagName == "Items") {
							b.loaded = true;
							b.expanded = true;
						}
					}
				}
			}
		});
var RoleGrantRightView = function(j, g) {
	var f = new Ext.ux.tree.CheckTreePanel({
				title : "为角色[" + g + "]授权",
				id : "roleGrantView",
				autoScroll : true,
				rootVisible : false,
				loader : new App.TreeLoader({
							dataUrl : __ctxPath + "/system/grantXmlAppRole.do"
						}),
				root : new Ext.tree.AsyncTreeNode({
							expanded : true
						}),
				tools : [{
							id : "refresh",
							qtip : "重新加载树",
							handler : function() {
								f.getRootNode().reload();
							}
						}]
			});
	f.on("load", function() {
				Ext.Ajax.request({
							url : __ctxPath + "/system/getAppRole.do",
							method : "POST",
							params : {
								roleId : j
							},
							success : function(c, a) {
								var b = Ext.util.JSON.decode(c.responseText);
								if (b.data.rights != null) {
									f.setValue(b.data.rights);
								}
							},
							failure : function(b, a) {
								Ext.ux.Toast.msg("操作信息", "加载权限出错！");
							},
							scope : this
						});
			});
	var h = new Ext.Toolbar({
				items : [{
							xtype : "button",
							text : "展开",
							iconCls : "btn-expand",
							scope : this,
							handler : function() {
								f.expandAll();
							}
						}, {
							xtype : "button",
							text : "收起",
							iconCls : "btn-collapse",
							scope : this,
							handler : function() {
								f.collapseAll();
							}
						}]
			});
	var i = new Ext.Window({
				id : "RoleGrantView",
				title : "角色授权设置",
				width : 600,
				tbar : h,
				height : 450,
				modal : true,
				layout : "fit",
				plain : true,
				bodyStyle : "padding:5px;",
				buttonAlign : "center",
				items : [f],
				buttons : [{
					text : "保存",
					iconCls : "btn-save",
					handler : function() {
						Ext.Ajax.request({
									url : __ctxPath + "/system/grantAppRole.do",
									method : "POST",
									params : {
										roleId : j,
										rights : f.getValue().toString()
									},
									success : function(b, a) {
										Ext.ux.Toast.msg("操作提示",
												"你已经成功为角色[<b>{0}</b>]进行了授权", g);
										i.close();
									},
									failure : function(b, a) {
										Ext.ux.Toast
												.msg("操作信息", "授权出错，请联系管理员！");
									},
									scope : this
								});
					}
				}, {
					text : "取消",
					iconCls : "btn-cancel",
					handler : function() {
						i.close();
					}
				}]
			});
	i.show();
};