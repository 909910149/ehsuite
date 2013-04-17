var IndexPage = Ext.extend(Ext.Viewport, {
	top : new Ext.Panel({
				region : "north",
				id : "__nortPanel",
				contentEl : "app-header",
				height : 56
			}),
	center : null,
	west : new Ext.Panel({
				region : "west",
				id : "west-panel",
				title : "导航",
				iconCls : "menu-navigation",
				split : true,
				width : 180,
				layout : "accordion",
				collapsible : true,
				tools : [{
							id : "refresh",
							handler : function() {
								if (curUserInfo.username == "admin") {
									loadWestMenu("true");
								} else {
									Ext.ux.Toast.msg("操作提示", "仅对开发用户开放刷新菜单功能!");
								}
							}
						}],
				items : []
			}),
	south : new Ext.Panel({
		border : false,
		region : "south",
		height : 28,
		border : false,
		bbar : [
				{
					text : "在线用户",
					iconCls : "btn-onlineUser",
					handler : function() {
						OnlineUserSelector.getView().show();
					}
				},
				"-",
				{
					text : "意见箱",
					iconCls : "btn-feedback",
					handler : function() {
						App.clickTopTab("SuggestBoxView", {
									title : "我的意见箱",
									userId : curUserInfo.userId
								});
					}
				},
				"-",
				{
					text : "主页",
					iconCls : "btn-feedback",
					handler : function() {
						App.MyDesktopClick();
					}
				},
				"-",
				{
					id : "messageTip",
					xtype : "button",
					hidden : true,
					width : 50,
					height : 20,
					handler : function() {
						var d = Ext.getCmp("messageTip");
						var c = Ext.getCmp("win");
						if (c == null) {
							new MessageWin().show();
						}
						d.hide();
					}
				},
				"->",
				{
					xtype : "tbfill"
				},
				{
					xtype : "tbseparator"
				}, {
					pressed : false,
					text : "便签",
					iconCls : "tipsTile",
					handler : function() {
						App.clickTopTab("PersonalTipsView");
					}
				}, {
					xtype : "tbseparator"
				}, {
					pressed : false,
					iconCls : "contact",
					text : "联系我们",
					handler : function() {
						Ext.ux.Toast
								.msg("联系我们",
										"contact@ehsuite.com");
					}
				}, "-", {
					text : "收展",
					iconCls : "btn-expand",
					handler : function() {
						var b = Ext.getCmp("__nortPanel");
						if (b.collapsed) {
							b.expand(true);
						} else {
							b.collapse(true);
						}
					}
				}, "-", {
					xtype : "combo",
					mode : "local",
					editable : false,
					value : "切换皮肤",
					width : 100,
					triggerAction : "all",
					store : [["ext-all", "缺省浅蓝"], ["ext-all-css04", "灰白主题"],
							["ext-all-css05", "绿色主题"],
							["ext-all-css03", "粉红主题"], ["xtheme-tp", "灰色主题"],
							["xtheme-default2", "灰蓝主题"],
							["xtheme-default16", "绿色主题"],
							["xtheme-access", "Access风格"]],
					listeners : {
						scope : this,
						"select" : function(g, e, h) {
							if (g.value != "") {
								var f = new Date();
								f.setDate(f.getDate() + 300);
								setCookie("theme", g.value, f, __ctxPath);
								Ext.util.CSS.swapStyleSheet("theme", __ctxPath
												+ "/ext3/resources/css/"
												+ g.value + ".css");
							}
						}
					}
				}]
	}),
	constructor : function() {
		this.center = new Ext.TabPanel({
					id : "centerTabPanel",
					region : "center",
					deferredRender : true,
					enableTabScroll : true,
					activeTab : 0,
					defaults : {
						autoScroll : true,
						closable : true
					},
					items : [],
					plugins : new Ext.ux.TabCloseMenu(),
					listeners : {
						"add" : function(e, a, f) {
							//如果Tab超过8个,则自动移除最左侧Tab
							if (e.items.length > 8) {
								e.remove(e.items.get(0));
								e.doLayout();
							}
						}
					}
				});
		IndexPage.superclass.constructor.call(this, {
					border : false,
					layout : "border",
					items : [this.top, this.west, this.center, this.south]
				});
		var b = getCookie("_topNavId");
		if (b == null || b == undefined) {
			b = 0;
		}
		//顶部导航
		this.navTab = new Ext.TabPanel({
					//width : 620,
					width : 600,
					id : "appNavTabPanel",
					deferredRender : true,
					enableTabScroll : true,
					activeTab : b,
					frame : false,
					border : false,
					plain : true,
					height : 0,
					//EXTJS4为contentEl
					renderTo : "header-nav",
					tabMargin : 20,
					defaults : {
						autoScroll : false,
						closable : false,
						bodyStyle : "padding-bottom: 12px;"
					},
					listeners : {
						scope : this,
						"tabchange" : function(e, f) {
							var a = new Date();
							a.setDate(a.getDate() + 300);
							setCookie("_topNavId", f.getId(), a, __ctxPath);
							loadWestMenu();
						}
					},
					items : []
				});
		this.afterPropertySet();
	},
	afterPropertySet : function() {
		var e = this.center;
		var g = function(b) {
			var d = Ext.getCmp("messageTip");
			var a = Ext.getCmp("win");
			var c = Ext.getCmp("wind");
			if (b > 0 && a == null && c == null) {
				d
						.setText('<div style="height:25px;"><img src="'
								+ __ctxPath
								+ '/images/newpm.gif" style="height:12px;"/>你有<strong style="color: red;">'
								+ b + "</strong>信息</div>");
				d.show();
			} else {
				d.hide();
			}
		};
		var h = function() {
			Ext.Ajax.request({
						url : __ctxPath + "/info/countInMessage.do",
						method : "POST",
						success : function(b, a) {
							var c = Ext.util.JSON.decode(b.responseText);
							count = c.count;
							g(count);
							setTimeout(h, 1000 * 60);
						},
						failure : function(b, a) {
						},
						scope : this
					});
		};
		var f = this.navTab;
		//将用户拥有的菜单加载到顶部导航显示
		f.add(curUserInfo.topModules);
		setTimeout(function() {
					var a = getCookie("_topNavId");
					if (a) {
						f.activate(a);
					}
					if (f.getActiveTab() == null) {
						f.activate(f.items.get(0));
					}
					h();
				}, 1200);
		Ext.getCmp("SearchForm").render("searchFormDisplay");
	}
});
function loadWestMenu(isReload) {
	if (!isReload) {
		isReload = "";
	}
	var westPanel = Ext.getCmp("west-panel");
	var iconCls = Ext.getCmp("appNavTabPanel").getActiveTab().iconCls;
	var topMenuId = iconCls.split("-")[1];
	Ext.Ajax.request({
				url : __ctxPath + "/panelTreeMenu.do?topMenuId=" + topMenuId
						+ "&isReload=" + isReload,
				success : function(response, options) {
					var arr = eval(response.responseText);
					var __activedPanelId = getCookie("__activedPanelId");
					westPanel.removeAll();
					westPanel.doLayout();
					for (var i = 0; i < arr.length; i++) {
						var doc = strToDom(arr[i].subXml);
						var root = doc.documentElement || doc;
						var panel = new Ext.tree.TreePanel({
									id : arr[i].id,
									title : arr[i].text,
									iconCls : arr[i].iconCls,
									layout : "fit",
									animate : true,
									border : false,
									loader : new htsoft.ux.TreeXmlLoader({
												preloadChildren : true
											}),
									root : new Ext.tree.AsyncTreeNode({
												text : root.tagName,
												xmlNode : root
											}),
									listeners : {
										"click" : App.clickNode
									},
									rootVisible : false
								});
						westPanel.add(panel);
						panel.on("expand", function(p) {
									var expires = new Date();
									expires.setDate(expires.getDate() + 30);
									setCookie("__activedPanelId", p.id,
											expires, __ctxPath);
								});
						if (arr[i].id == __activedPanelId) {
							westPanel.layout.activeItem = panel;
						}
					}
					westPanel.doLayout();
				}
			});
}