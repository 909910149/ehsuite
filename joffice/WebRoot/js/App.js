Ext.ns("App");
var PortalItem = function(e, d, f) {
	this.panelId = e;
	this.column = d;
	this.row = f;
};
var SectionItem = function(f, d, e) {
	this.sectionId = f;
	this.colNumber = d;
	this.rowNumber = e;
};
var UserInfo = function(b) {
	this.userId = b.userId;
	this.username = b.username;
	this.fullname = b.fullname;
	this.depId = b.depId;
	this.depName = b.depName;
	this.rights = b.rights;
	this.portalConfig = b.items;
	this.topModules = b.topModules;
};
var SysConfig = function(b) {
	this.dynamicPwd = b.dynamicPwd;
};
var curUserInfo = null;
var sysConfigInfo = null;
function isGranted(b) {
	if (curUserInfo.rights.indexOf("__ALL") != -1) {
		return true;
	}
	if (curUserInfo.rights.indexOf(b) != -1) {
		return true;
	}
	return false;
}
App.init = function() {
	Ext.QuickTips.init();
	Ext.BLANK_IMAGE_URL = __ctxPath + "/ext3/resources/images/default/s.gif";
	setTimeout(function() {
				Ext.get("loading").remove();
				Ext.get("loading-mask").fadeOut({
							remove : true
						});
				document.getElementById("app-header").style.display = "block";
			}, 1000);
	Ext.util.Observable.observeClass(Ext.data.Connection);
	Ext.data.Connection.on("requestcomplete", function(b, a, c) {
				if (a && a.getResponseHeader) {
					if (a.getResponseHeader("__timeout")) {
						Ext.ux.Toast.msg("操作提示：", "操作已经超时，请重新登录!");
						window.location.href = __ctxPath + "/index.jsp?randId="
								+ parseInt(1000 * Math.random());
					} else {
						if (a.getResponseHeader("__403_error")) {
							Ext.ux.Toast.msg("系统访问权限提示：", "你目前没有权限访问：{0}",
									c.url);
						}
					}
				}
			});
	Ext.data.Connection.on("requestexception", function(b, a, c) {
				if (a && a.getResponseHeader) {
					if (a.getResponseHeader("__500_error")) {
						Ext.ux.Toast.msg("后台出错", "您访问的URL:{0}出错了，具体原因请联系管理员。",
								c.url);
					} else {
						if (a.getResponseHeader("__404_error")) {
							Ext.ux.Toast.msg("后台出错",
									"您访问的URL:{0}对应的页面不存在，具体原因请联系管理员。", c.url);
						}
					}
				}
			});
	var j = Ext.util.JSON.decode(userInfo);
	var f = j.user;
	//alert('joffice user topmodules : '+f.topModules);
	var k = f.items;
	curUserInfo = new UserInfo(f);
	//alert('rights of curUserInfo : '+curUserInfo.rights);
	var h = j.sysConfigs;
	sysConfigInfo = new SysConfig(h);
	var g = new IndexPage();
	App.clickTopTab("ComIndexPage");
};
App.clickTopTab = function(j, m, h, k) {
	if (h != null) {
		h.call(this);
	}
	var g = Ext.getCmp("centerTabPanel");
	var l = g.getItem(j);
	if (l == null) {
		$ImportJs(j, function(a) {
					l = g.add(a);
					g.activate(l);
				}, m);
	} else {
		if (k != null) {
			k.call(this);
		}
		g.activate(l);
	}
};
App.clickTopTabIframe = function(d) {
	if (d.id == null || d.id == "" || d.id.indexOf("xnode") != -1) {
		return;
	}
	var e = Ext.getCmp("centerTabPanel");
	var f = e.getItem(d.id);
	if (f == null) {
		f = e.add({
					xtype : "iframepanel",
					title : d.text,
					id : d.id,
					loadMask : {
						msg : "正在加载...,请稍等..."
					},
					iconCls : d.attributes.iconCls,
					defaultSrc : __ctxPath + "/pages/iframe/"
							+ d.attributes.model + "/" + d.id + ".jsp?id="
							+ Math.random(),
					listeners : {
						domready : function(a) {
						}
					}
				});
	}
	e.activate(f);
};
App.clickTopTabUrl = function(h) {
	if (h.id == null || h.id == "" || h.id.indexOf("xnode") != -1) {
		return;
	}
	var e = Ext.getCmp("centerTabPanel");
	var f = h.attributes.url;
	if (!f.substring(0, 5) == "http:") {
		f = __ctxPath + f;
	}
	var g = e.getItem(h.id);
	if (g == null) {
		g = e.add({
					xtype : "iframepanel",
					title : h.text,
					id : h.id,
					loadMask : {
						msg : "正在加载...,请稍等..."
					},
					iconCls : h.attributes.iconCls,
					defaultSrc : f,
					listeners : {
						domready : function(a) {
						}
					}
				});
	}
	e.activate(g);
};
App.clickStartFlow = function(d) {
	var f = App.getContentPanel();
	var e = f.getItem("ProcessRunStart" + d.attributes.defId);
	if (e == null) {
		e = new ProcessRunStart({
					id : "ProcessRunStart" + d.attributes.defId,
					defId : d.attributes.defId,
					flowName : d.attributes.flowName
				});
		f.add(e);
	}
	f.activate(e);
};
App.clickFLowNode = function(e) {
	var f = [__ctxPath + "/js/archive/ArchivesDetailWin.js",
			__ctxPath + "/js/archive/ArchHastenForm.js",
			__ctxPath + "/js/flow/ProcessNextForm.js",
			__ctxPath + "/js/flow/ProcessRunDetail.js"];
	$ImportSimpleJs(f, null);
	var g = App.getContentPanel();
	var h = g.getItem(e.id);
	if (h == null) {
		h = new ArchivesNode({
					id : "ProcessRunStart" + e.id,
					title : e.attributes.text
				});
		g.add(h);
	}
	g.activate(h);
};
App.clickNode = function(r) {
	if (r.id == null || r.id == "" || r.id.indexOf("xnode") != -1) {
		return;
	}
	var t = r.id;
	var o = r.text;
	if (t.indexOf("?") > 0) {
		var p = t.split("?");
		var w = "";
		if (p.length > 0) {
			t = p[0];
			var n = p[1];
			var v = n.split("&");
			for (i = 0; i < v.length; i++) {
				var q = v[i];
				var s = q.split("=");
				var u = s[0];
				var m = s[1];
				w += u + ":'" + m + "',";
			}
			w += "title:'" + o + "'";
			w = "{" + w + "}";
		}
		if (r.attributes.url) {
			App.clickTopTabUrl(r);
		} else {
			if (r.attributes.iframe) {
				App.clickTopTabIframe(r);
			} else {
				if (r.attributes.defId) {
					App.clickStartFlow(r);
				} else {
					if (r.attributes.flowNode) {
						App.clickFLowNode(r);
					} else {
						App.clickTopTab(t, Ext.decode(w));
					}
				}
			}
		}
	} else {
		if (r.attributes.url) {
			App.clickTopTabUrl(r);
		} else {
			if (r.attributes.iframe) {
				App.clickTopTabIframe(r);
			} else {
				if (r.attributes.defId) {
					App.clickStartFlow(r);
				} else {
					if (r.attributes.flowNode) {
						App.clickFLowNode(r);
					} else {
						App.clickTopTab(r.id, Ext.decode(r.attributes.params));
					}
				}
			}
		}
	}
};
App.MyDesktopClick = function() {
	var b = Ext.getCmp("MyDesktop");
	if (b != null) {
		b.expand(true);
	}
	App.clickTopTab("ComIndexPage");
};
App.MyDesktopClickTopTab = function(id, params, precall, callback) {
	if (precall != null) {
		precall.call(this);
	}
	var tabs = Ext.getCmp("centerTabPanel");
	var tabItem = tabs.getItem(id);
	if (tabItem == null) {
		$ImportJs(id, function(view) {
					tabItem = tabs.add(view);
					tabs.activate(tabItem);
				}, params);
	} else {
		tabs.remove(tabItem);
		var str = "new " + id;
		if (params != null) {
			str += "(params);";
		} else {
			str += "();";
		}
		var view = eval(str);
		tabItem = tabs.add(view);
		tabs.activate(tabItem);
	}
};
App.Logout = function() {
	Ext.Ajax.request({
				url : __ctxPath + "/j_logout.do",
				success : function() {
					deleteCookie("jforumSSOCookieNameUser", "/", 0);
					window.location.href = __ctxPath + "/login.jsp";
				}
			});
};
Ext.onReady(App.init);