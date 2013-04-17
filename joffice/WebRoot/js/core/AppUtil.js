Ext.ns("App");
Ext.ns("AppUtil");
var jsCache = new Array();
function strToDom(e) {
	if (window.ActiveXObject) {
		var d = new ActiveXObject("Microsoft.XMLDOM");
		d.async = "false";
		d.loadXML(e);
		return d;
	} else {
		if (document.implementation && document.implementation.createDocument) {
			var f = new DOMParser();
			var d = f.parseFromString(e, "text/xml");
			return d;
		}
	}
}
function newView(viewName, params) {
	var str = "new " + viewName;
	if (params != null) {
		str += "(params);";
	} else {
		str += "();";
	}
	return eval(str);
}
function $ImportJs(viewName, callback, params) {
	var b = jsCache[viewName];
	if (b != null) {
		var view = newView(viewName, params);
		callback.call(this, view);
	} else {
		var jsArr = eval("App.importJs." + viewName);
		if (jsArr == undefined || jsArr.length == 0) {
			try {
				var view = newView(viewName, params);
				callback.call(this, view);
			} catch (e) {
			}
			return;
		}
		ScriptMgr.load({
					scripts : jsArr,
					callback : function() {
						jsCache[viewName] = 0;
						var view = newView(viewName, params);
						callback.call(this, view);
					}
				});
	}
}
function $ImportSimpleJs(e, f, d) {
	ScriptMgr.load({
				scripts : e,
				scope : d,
				callback : function() {
					if (f) {
						f.call(this);
					}
				}
			});
}
function $parseDate(c) {
	if (typeof c == "string") {
		var d = c.match(/^ *(\d{4})-(\d{1,2})-(\d{1,2}) *$/);
		if (d && d.length > 3) {
			return new Date(parseInt(d[1]), parseInt(d[2]) - 1, parseInt(d[3]));
		}
		d = c
				.match(/^ *(\d{4})-(\d{1,2})-(\d{1,2}) +(\d{1,2}):(\d{1,2}):(\d{1,2}) *$/);
		if (d && d.length > 6) {
			return new Date(parseInt(d[1]), parseInt(d[2]) - 1, parseInt(d[3]),
					parseInt(d[4]), parseInt(d[5]), parseInt(d[6]));
		}
		d = c
				.match(/^ *(\d{4})-(\d{1,2})-(\d{1,2}) +(\d{1,2}):(\d{1,2}):(\d{1,2})\.(\d{1,9}) *$/);
		if (d && d.length > 7) {
			return new Date(parseInt(d[1]), parseInt(d[2]) - 1, parseInt(d[3]),
					parseInt(d[4]), parseInt(d[5]), parseInt(d[6]),
					parseInt(d[7]));
		}
	}
	return null;
}
function $formatDate(d) {
	if (typeof d == "string") {
		d = parseDate(d);
	}
	if (d instanceof Date) {
		var i = d.getFullYear();
		var h = d.getMonth() + 1;
		var l = d.getDate();
		var m = d.getHours();
		var o = d.getMinutes();
		var n = d.getSeconds();
		var p = d.getMilliseconds();
		if (h < 10) {
			h = "0" + h;
		}
		if (l < 10) {
			l = "0" + l;
		}
		if (m < 10) {
			m = "0" + m;
		}
		if (o < 10) {
			o = "0" + o;
		}
		if (n < 10) {
			n = "0" + n;
		}
		if (p > 0) {
			return i + "-" + h + "-" + l + " " + m + ":" + o + ":" + n + "."
					+ p;
		}
		if (m > 0 || o > 0 || n > 0) {
			return i + "-" + h + "-" + l + " " + m + ":" + o + ":" + n;
		}
		return i + "-" + h + "-" + l;
	}
	return "";
}
function $convertTableToMap(i) {
	if (i.rows.length != 2) {
		return [];
	}
	var w = [];
	var s = i.rows[0];
	var t = i.rows[1];
	for (var z = 0; z < s.cells.length; z++) {
		var E = {};
		var j = t.cells[z];
		var A;
		for (var B = 0; B < j.childNodes.length; B++) {
			if (j.childNodes[B].getAttribute
					&& j.childNodes[B].getAttribute("name")) {
				A = j.childNodes[B];
				break;
			}
		}
		var F = A.getAttribute("name");
		var x = s.cells[z].innerHTML;
		var D = A.getAttribute("xtype");
		var u = A.getAttribute("dateformat");
		var C = A.getAttribute("txtitemname");
		var v = A.getAttribute("txtisnotnull");
		var y = A.getAttribute("issingle");
		E.name = F;
		E.header = x;
		E.xtype = D;
		if (u) {
			E.format = u;
		}
		if (C) {
			E.itemsName = C;
		}
		if (y) {
			E.issingle = y;
		}
		E.isnotnull = v;
		w.push(E);
	}
	return w;
}
function $getTableInputCmpName(j) {
	var m = [];
	for (var n = 0; n < j.rows.length; n++) {
		var i = j.rows[n];
		for (var p = 0; p < i.cells.length; p++) {
			var k = i.cells[p];
			var o;
			for (var q = 0; q < k.childNodes.length; q++) {
				if (k.childNodes[q].getAttribute
						&& k.childNodes[q].getAttribute("name")) {
					o = k.childNodes[q];
					if (o) {
						var r = o.getAttribute("name");
						m.push(r);
					}
				}
			}
		}
	}
	return m;
}
App.getContentPanel = function() {
	var b = Ext.getCmp("centerTabPanel");
	return b;
};
App.createUploadDialog = function(d) {
	var e = {
		file_cat : "others",
		url : __ctxPath + "/file-upload",
		reset_on_hide : false,
		upload_autostart : false,
		modal : true
	};
	Ext.apply(e, d);
	var f = new FileUploadManager(e);
	return f;
};
App.createUploadDialog2 = function(d) {
	var e = {
		file_cat : "others",
		url : __ctxPath + "/file-upload",
		reset_on_hide : false,
		upload_autostart : false,
		modal : true
	};
	Ext.apply(e, d);
	var f = new Ext.ux.UploadDialog.Dialog(e);
	return f;
};
function uniqueArray(f) {
	f = f || [];
	var a = {};
	for (var g = 0; g < f.length; g++) {
		var h = f[g];
		if (typeof(a[h]) == "undefined") {
			a[h] = 1;
		}
	}
	f.length = 0;
	for (var g in a) {
		f[f.length] = g;
	}
	return f;
}
function setCookie(g, k, h, i, l, j) {
	document.cookie = g + "=" + escape(k)
			+ ((h) ? "; expires=" + h.toGMTString() : "")
			+ ((i) ? "; path=" + i : "") + ((l) ? "; domain=" + l : "")
			+ ((j) ? "; secure" : "");
}
function getCookie(f) {
	var i = f + "=";
	var h = document.cookie.indexOf(i);
	if (h == -1) {
		return null;
	}
	var g = document.cookie.indexOf(";", h + i.length);
	if (g == -1) {
		g = document.cookie.length;
	}
	var j = document.cookie.substring(h + i.length, g);
	return unescape(j);
}
function deleteCookie(e, f, d) {
	if (getCookie(e)) {
		document.cookie = e + "=" + ((f) ? "; path=" + f : "")
				+ ((d) ? "; domain=" + d : "")
				+ "; expires=Thu, 01-Jan-70 00:00:01 GMT";
	}
}
String.prototype.trim = function() {
	return (this.replace(/^[\s\xA0]+/, "").replace(/[\s\xA0]+$/, ""));
};
function $request(b) {
	Ext.Ajax.request({
				url : b.url,
				params : b.params,
				method : b.method == null ? "POST" : b.method,
				success : function(a, d) {
					if (b.success != null) {
						b.success.call(this, a, d);
					}
				},
				failure : function(a, d) {
					Ext.ux.Toast.msg("操作信息", "操作出错，请联系管理员！");
					if (b.success != null) {
						b.failure.call(this, a, d);
					}
				}
			});
}
function asynReq() {
	var b = Ext.Ajax.getConnectionObject().conn;
	b.open("GET", url, false);
	b.send(null);
}
AppUtil.addPrintExport = function(b) {
};
AppUtil.removeTab = function(e) {
	var d = App.getContentPanel();
	var f = d.getItem(e);
	if (f != null) {
		d.remove(f, true);
	}
};
AppUtil.activateTab = function(d) {
	var c = App.getContentPanel();
	c.activate(d);
};
function $converCmp(x, g, s, q, e) {
	var p = "on";
	var v = this.formPanel;
	var z = new Ext.util.MixedCollection();
	var y = new Array();
	var w = [];
	Ext.each(x, function(X, o) {
		var a, V, m, T, S;
		var d = null;
		if (!X) {
			return;
		}
		a = X.name;
		V = X.type;
		if (s && s[a]) {
			d = s[a];
		}
		if (V == "button" || V == "hidden") {
			return;
		}
		T = X.getAttribute("xtype");
		if (g && g[a]) {
			if (V == "radio" || V == "checkbox") {
				var m = g[a];
				if (X.value) {
					var Z = X.value + "|", ad = m + "|";
					var c = ad.indexOf(Z) > -1;
					if (X.value == m || c) {
						X.checked = true;
					} else {
						X.checked = false;
					}
				}
				return;
			}
			X.value = g[a];
		}
		var i = X.parentNode;
		var n = X.getAttribute("width");
		var k = X.getAttribute("txtisnotnull");
		if (!n) {
			n = i.offsetWidth;
		}
		if (n < 300 && i.offsetWidth > 300) {
			n = 300;
		}
		if (d && (d == 0 || d == 1)
				&& (T != "officeeditor" || (T == "officeeditor" && d == 0))) {
			p = "un";
			X.style.display = "none";
			var R = document.createElement("span");
			R.setAttribute("style", "width:" + n + "px;");
			var ac = "";
			if (d == 0) {
				ac = '<font color="red">无权限</font>';
			} else {
				if (X.value) {
					ac = X.value;
				} else {
					ac = "";
				}
			}
			R.innerHTML = ac;
			i.appendChild(R);
			return;
		}
		if (T == "datefield") {
			var h = X.getAttribute("dateformat");
			var Q = X.getAttribute("txtistoday");
			var j = document.createElement("div");
			var Y = {
				parentNode : i,
				oldEl : X,
				newEl : j
			};
			w.push(Y);
			var U = document.createElement("div");
			U.setAttribute("style", "width:" + n + "px");
			j.appendChild(U);
			try {
				var ab;
				if (h == "yyyy-MM-dd HH:mm:ss") {
					ab = new Cls.form.DateTimeField({
								name : a,
								width : 200,
								autoWidth : false,
								boxMaxWidth : 200,
								format : "Y-m-d H:i:s",
								value : Q == 1 ? new Date() : "",
								allowBlank : k == 1 ? false : true
							});
				} else {
					ab = new Ext.form.DateField({
								name : a,
								height : 21,
								width : 100,
								boxMaxWidth : 100,
								autoWidth : false,
								format : "Y-m-d",
								value : Q == 1 ? new Date() : "",
								allowBlank : k == 1 ? false : true
							});
				}
				y.push("datefield" + o);
				z.add("datefield" + o, j);
				z.add("datefield" + o + "-cmp", ab);
				if (X.value) {
					ab.setValue($parseDate(X.value));
				}
			} catch (f) {
			}
		} else {
			if (T == "diccombo") {
				try {
					var W = X.getAttribute("txtitemname");
					var j = document.createElement("span");
					var Y = {
						parentNode : i,
						oldEl : X,
						newEl : j
					};
					w.push(Y);
					var U = document.createElement("div");
					j.appendChild(U);
					var ab = new DicCombo({
								name : a,
								itemName : W,
								displayField : "itemName",
								valueField : "itemName",
								width : n,
								allowBlank : k == 1 ? false : true
							});
					y.push("diccombo" + o);
					z.add("diccombo" + o, j);
					z.add("diccombo" + o + "-cmp", ab);
					if (X.value) {
						ab.setValue(X.value);
					}
				} catch (f) {
				}
			} else {
				if (T == "fckeditor") {
					S = i.offsetHeight;
					var j = document.createElement("div");
					var Y = {
						parentNode : i,
						oldEl : X,
						newEl : j
					};
					w.push(Y);
					var U = document.createElement("div");
					j.appendChild(U);
					var ab = new Ext.ux.form.FCKeditor({
								name : a,
								height : S,
								allowBlank : false
							});
					y.push("fckeditor" + o);
					z.add("fckeditor" + o, j);
					z.add("fckeditor" + o + "-cmp", ab);
					if (X.value) {
						ab.setValue(X.value);
					}
				} else {
					if (T == "officeeditor") {
						try {
							var j = document.createElement("div");
							S = i.offsetHeight;
							this.hiddenF = new Ext.form.Hidden({
										name : a
									});
							this.hiddenF.render(j);
							var Y = {
								parentNode : i,
								oldEl : X,
								newEl : j
							};
							w.push(Y);
							Ext.useShims = true;
							var ab = new NtkOfficePanel({
										showToolbar : d == 1 ? false : true,
										width : n,
										height : S,
										fileId : X.value,
										doctype : "doc",
										unshowMenuBar : false
									});
							if (d == 1) {
								ab.setReadOnly();
							}
							y.push("officeeditor" + o);
							z.add("officeeditor" + o, j);
							z.add("officeeditor" + o + "-cmp", ab.panel);
							if (X.value) {
								this.hiddenF.setValue(X.value);
								this.fileId = X.value;
							}
							this.officePanel = ab;
						} catch (f) {
						}
					} else {
						if (T == "userselector") {
							try {
								var j = document.createElement("div");
								var aa = new Ext.form.Hidden({
											value : curUserInfo.userId,
											name : a + "ids"
										});
								aa.render(j);
								var Y = {
									parentNode : i,
									oldEl : X,
									newEl : j
								};
								w.push(Y);
								var l = X.getAttribute("issingle");
								var b = new Ext.form.TextField({
											name : a,
											height : 21,
											readOnly : true,
											value : curUserInfo.fullname,
											allowBlank : k == 1 ? false : true,
											width : n ? (n - 90 > 0
													? n - 90
													: n) : n
										});
								if (l == 0) {
									b = new Ext.form.TextArea({
												name : a,
												readOnly : true,
												allowBlank : k == 1
														? false
														: true,
												value : curUserInfo.fullname,
												width : n ? (n - 90 > 0 ? n
														- 90 : n) : n
											});
								}
								var ab = new Ext.form.CompositeField({
											width : n,
											items : [b, {
												xtype : "button",
												width : 78,
												border : false,
												text : "选择人员",
												iconCls : "btn-sel",
												handler : function() {
													UserSelector.getView(
															function(B, A) {
																b.setValue(A);
															},
															l == 1
																	? true
																	: false)
															.show();
												}
											}]
										});
								y.push("userselector" + o);
								z.add("userselector" + o, j);
								z.add("userselector" + o + "-cmp", ab);
								if (X.value) {
									b.setValue(X.value);
								}
							} catch (f) {
							}
						} else {
							if (T == "depselector") {
								try {
									var j = document.createElement("div");
									var aa = new Ext.form.Hidden({
												value : curUserInfo.depId,
												name : a + "ids"
											});
									aa.render(j);
									var Y = {
										parentNode : i,
										oldEl : X,
										newEl : j
									};
									w.push(Y);
									var l = X.getAttribute("issingle");
									var b = new Ext.form.TextField({
												name : a,
												readOnly : true,
												value : curUserInfo.depName,
												height : 21,
												allowBlank : k == 1
														? false
														: true,
												width : n ? (n - 90 > 0 ? n
														- 90 : n) : n
											});
									if (l == 0) {
										b = new Ext.form.TextArea({
													name : a,
													readOnly : true,
													value : curUserInfo.depName,
													allowBlank : k == 1
															? false
															: true,
													width : n ? (n - 90 > 0 ? n
															- 90 : n) : n
												});
									}
									var ab = new Ext.form.CompositeField({
												width : n,
												items : [b, {
													xtype : "button",
													border : false,
													width : 78,
													text : "选择部门",
													iconCls : "btn-users",
													handler : function() {
														DepSelector
																.getView(
																		function(
																				B,
																				A) {
																			b
																					.setValue(A);
																		},
																		l == 1
																				? true
																				: false)
																.show();
													}
												}]
											});
									y.push("depselector" + o);
									z.add("depselector" + o, j);
									z.add("depselector" + o + "-cmp", ab);
									if (X.value) {
										b.setValue(X.value);
									}
								} catch (f) {
								}
							} else {
								if (T == "fileattach") {
									try {
										var j = document.createElement("div");
										var aa = new Ext.form.Hidden({
													name : a
												});
										aa.render(j);
										var Y = {
											parentNode : i,
											oldEl : X,
											newEl : j
										};
										w.push(Y);
										var b = new Ext.Panel({
													width : n ? (n - 90 > 0 ? n
															- 90 : n) : n,
													height : 60,
													autoScroll : true,
													html : ""
												});
										var ab = new Ext.form.CompositeField({
											width : n,
											items : [b, {
												xtype : "button",
												width : 78,
												text : "选择附件",
												iconCls : "menu-attachment",
												handler : function() {
													var A = App
															.createUploadDialog(
																	{
																		file_cat : "flow",
																		callback : function(
																				B) {
																			for (var C = 0; C < B.length; C++) {
																				if (aa
																						.getValue() != "") {
																					aa
																							.setValue(aa
																									.getValue()
																									+ ",");
																				}
																				aa
																						.setValue(aa
																								.getValue()
																								+ B[C].fileId
																								+ "|"
																								+ B[C].fileName);
																				Ext.DomHelper
																						.append(
																								b.body,
																								'<span><a href="#" onclick="FileAttachDetail.show('
																										+ B[C].fileId
																										+ ')">'
																										+ B[C].fileName
																										+ '</a> <img class="img-delete" src="'
																										+ __ctxPath
																										+ '/images/system/delete.gif" onclick="AppUtil.removeFile(this,'
																										+ B[C].fileId
																										+ ')"/>&nbsp;|&nbsp;</span>');
																			}
																		}
																	});
													A.show(this);
												}
											}]
										});
										y.push("fileattach" + o);
										z.add("fileattach" + o, j);
										z.add("fileattach" + o + "-cmp", ab);
										AppUtil.removeFile = function(B, F, A) {
											var E = aa;
											var C = E.getValue();
											if (C.indexOf(",") < 0) {
												E.setValue("");
											} else {
												C = C.replace(
														"," + F + "|" + A, "")
														.replace(
																F + "|" + A
																		+ ",",
																"");
												E.setValue(C);
											}
											var D = Ext.get(B.parentNode);
											D.remove();
										};
										ab.on("render", function() {
											if (X.value) {
												aa.setValue(X.value);
												var D = X.value.split(",");
												for (var B = 0; B < D.length; B++) {
													var C = D[B];
													var F = C.split("|");
													var E = F[0];
													var A = F[1];
													Ext.DomHelper
															.append(
																	b.body,
																	'<span><a href="#" onclick="FileAttachDetail.show('
																			+ E
																			+ ')">'
																			+ A
																			+ '</a> <img class="img-delete" src="'
																			+ __ctxPath
																			+ '/images/system/delete.gif" onclick="AppUtil.removeFile(this,'
																			+ E
																			+ ",'"
																			+ A
																			+ "')\"/>&nbsp;|&nbsp;</span>");
												}
											}
										}, this);
									} catch (f) {
									}
								}
							}
						}
					}
				}
			}
		}
		X.onblur = function() {
			$validField.call(this, X);
		};
	}, this);
	for (var t = 0; t < w.length; t++) {
		var u = w[t];
		try {
			u.parentNode.replaceChild(u.newEl, u.oldEl);
		} catch (r) {
			alert(r);
		}
	}
	if (y.length > 0 && z.length > 0) {
		Ext.each(y, function(d) {
					var b = z.get(d + "-cmp");
					var c = z.get(d);
					try {
						var f = document.createElement("div");
						var h = document.createElement("div");
						f.appendChild(h);
						b.render(h);
						c.appendChild(f);
						if (q != true) {
							v.add(b);
						} else {
							if (e) {
							}
						}
					} catch (a) {
						alert(a);
					}
				});
	}
	return p;
}
function $converDetail(ak, ag) {
	var ae = this.formPanel.getForm().getEl().dom;
	var Y = ae.getElementsByTagName("table");
	this.detailGrids = new Ext.util.MixedCollection();
	var ad = [];
	var av = [];
	this.formValidCmp = new Array();
	for (var X = 0; X < Y.length; X++) {
		var aq = Y[X].getAttribute("isdetail");
		var ap = Y[X].getAttribute("isgrid");
		var au = Y[X].getAttribute("txtname");
		if (aq != null && "true" == ap) {
			var az = Y[X].parentNode;
			var an = $convertTableToMap(Y[X]);
			var aD = [];
			var ay = [];
			if (this.taskId) {
				var ai = document
						.getElementById("WF_" + au + "_" + this.taskId);
				var aj = ai.value;
				aD.push(aj);
				ay.push({
							dataIndex : aj,
							header : aj,
							hidden : true
						});
			}
			var ah = 0;
			for (var V = 0; V < an.length; V++) {
				var Z = null;
				if (ag && ag[an[V].name]) {
					Z = ag[an[V].name];
				}
				if (Z != 0) {
					if (an[V].xtype != "datefield") {
						aD.push(an[V].name);
					}
					if (Z == 1) {
						ah--;
						if (an[V].xtype == "datefield") {
							aD.push({
										type : "date",
										name : an[V].name,
										dateFormat : an[V].format == "yyyy-MM-dd"
												? "Y-m-d"
												: "Y-m-d H:i:s"
									});
							ay.push({
										dataIndex : an[V].name,
										header : an[V].header,
										xtype : "datecolumn",
										format : an[V].format
									});
						} else {
							ay.push({
										dataIndex : an[V].name,
										header : an[V].header
									});
						}
					} else {
						ah++;
						var aE = new Ext.form.TextField();
						if (an[V].xtype == "datefield") {
							if (an[V].format == "yyyy-MM-dd") {
								aD.push({
											type : "date",
											name : an[V].name,
											dateFormat : "Y-m-d"
										});
								aE = new Ext.form.DateField({
											format : "Y-m-d",
											value : new Date(),
											allowBlank : false
										});
								ay.push({
											dataIndex : an[V].name,
											header : an[V].header,
											xtype : "datecolumn",
											format : "Y-m-d",
											editor : aE
										});
							} else {
								aD.push({
											type : "date",
											name : an[V].name,
											dateFormat : "Y-m-d H:i:s"
										});
								aE = new Cls.form.DateTimeField({
											format : "Y-m-d H:i:s",
											value : new Date(),
											allowBlank : false
										});
								ay.push({
											dataIndex : an[V].name,
											header : an[V].header,
											xtype : "datecolumn",
											format : "Y-m-d H:i:s",
											editor : aE
										});
							}
						} else {
							if (an[V].xtype == "diccombo") {
								ay.push({
											dataIndex : an[V].name,
											header : an[V].header,
											editor : new DicCombo({
														itemName : an[V].itemsName,
														isDisplayItemName : true
													})
										});
							} else {
								if (an[V].xtype == "userselector") {
									var ao = an[V].issingle;
									var aa = true;
									if (ao == 1) {
										aa = false;
									}
									var al = this;
									var ax = an[V].name;
									var f = new Ext.form.TriggerField({
										triggerClass : "x-form-browse-trigger",
										gridName : au,
										isSingle : ao,
										dataIndexName : ax,
										onTriggerClick : function(a) {
											var c = al.detailGrids
													.get(this.gridName);
											var b = this.dataIndexName;
											UserSelector.getView(
													function(g, d) {
														var h = c.getStore();
														var j = h
																.getAt(c.clickRow);
														j.set(b, d);
													}, this.isingle).show();
											c.stopEditing();
										}
									});
									ay.push({
												dataIndex : an[V].name,
												header : an[V].header,
												editor : f
											});
								} else {
									if (an[V].xtype == "depselector") {
										var ao = an[V].issingle;
										var aa = true;
										if (ao == 1) {
											aa = false;
										}
										var al = this;
										var ax = an[V].name;
										var aw = new Ext.form.TriggerField({
											triggerClass : "x-form-browse-trigger",
											gridName : au,
											isSingle : ao,
											dataIndexName : ax,
											onTriggerClick : function(a) {
												var c = al.detailGrids
														.get(this.gridName);
												var b = this.dataIndexName;
												DepSelector.getView(
														function(g, d) {
															var h = c
																	.getStore();
															var j = h
																	.getAt(c.clickRow);
															j.set(b, d);
														}, this.isingle).show();
												c.stopEditing();
											}
										});
										ay.push({
													dataIndex : an[V].name,
													header : an[V].header,
													editor : aw
												});
									} else {
										ay.push({
													dataIndex : an[V].name,
													header : an[V].header,
													editor : aE
												});
									}
								}
							}
						}
					}
				} else {
					ah--;
				}
			}
			var W = document.createElement("div");
			az.appendChild(W);
			var ab = new HT.EditorGridPanel({
				renderTo : W,
				tbar : new Ext.Toolbar({
					hidden : an.length == ah ? false : true,
					frame : true,
					items : [{
								text : "添加记录",
								iconCls : "btn-add",
								scope : this,
								gridName : au,
								handler : function(b) {
									var c = this.detailGrids.get(b.gridName);
									var a = c.getStore().recordType;
									c.getStore().add(new a());
								}
							}, {
								text : "删除记录",
								iconCls : "btn-del",
								scope : this,
								gridName : au,
								handler : function(a) {
									var c = this.detailGrids.get(a.gridName);
									var d = a.gridName;
									var b = this.taskId;
									Ext.Msg.confirm("信息确认", "您确认要删除所选记录吗？",
											function(n) {
												if (n == "yes") {
													var k = null;
													if (b) {
														k = document
																.getElementById(d
																		+ "_"
																		+ b).value;
													}
													var p = c.getStore();
													var g = c
															.getSelectionModel()
															.getSelections();
													var o = [];
													var m = [];
													var l = document
															.getElementById("WF_"
																	+ d
																	+ "_"
																	+ b);
													var q;
													if (l) {
														q = l.value;
													}
													for (var h = 0; h < g.length; h++) {
														if (g[h].data != null) {
															if (q) {
																var j = g[h].data[q];
																if (j) {
																	o.push(j);
																}
															}
															m.push(g[h]);
														}
													}
													if (o.length) {
														Ext.Ajax.request({
															url : __ctxPath
																	+ "/flow/delItemsProcessActivity.do",
															params : {
																tableId : k,
																ids : o
															},
															method : "POST",
															success : function(
																	s, r) {
																Ext.ux.Toast
																		.msg(
																				"操作信息",
																				"成功删除该记录！");
																p.remove(m);
															},
															failure : function(
																	s, r) {
																Ext.ux.Toast
																		.msg(
																				"操作信息",
																				"操作出错，请联系管理员！");
															}
														});
													} else {
														p.remove(m);
													}
												}
											});
								}
							}]
				}),
				clicksToEdit : 1,
				width : Y[X].offsetWidth,
				showPaging : false,
				autoHeight : true,
				fields : aD,
				columns : ay,
				listeners : {
					"rowclick" : function(b, c, a) {
						this.clickRow = c;
					}
				}
			});
			this.detailGrids.add(au, ab);
			if (ak) {
				var i = ak["WF_" + au + "s"];
				if (i) {
					ab.getStore().loadData({
								result : i
							});
				}
			}
			av.push(Y[X]);
		} else {
			if (aq != null && "false" == ap) {
				try {
					var U = $getTableInputCmpName(Y[X]);
					var aA;
					var af;
					if (this.taskId) {
						var at = document.getElementById("WF_" + au + "_"
								+ this.taskId);
						var e = at.value;
						if (ak) {
							aA = ak["WF_" + au + "s"];
							af = e;
							if (false && aA) {
								var aC = i[0];
								var ah = false;
								var w = aC[e];
								for (var ac = 0; ac < U.length; ac++) {
									if (U[ac] == e) {
										ah = true;
									}
									ak[U[ac]] = aC[U[ac]];
								}
							}
						}
					}
					var az = Y[X].parentNode;
					var am = az.innerHTML;
					var aC = {
						innerhtml : am,
						parentNode : az,
						gridName : au,
						elsName : U,
						jsonDatas : aA,
						pkName : af,
						rightJson : ag
					};
					ad.push(aC);
					av.push(Y[X]);
				} catch (T) {
					alert(T);
				}
			}
		}
	}
	for (var X = 0; X < av.length; X++) {
		var ar = av[X];
		var az = ar.parentNode;
		az.removeChild(ar);
	}
	var aB = ae.elements || (document.forms[ae] || Ext.getDom(ae)).elements;
	$converCmp.call(this, aB, ak, ag);
	$converFormDetail.call(this, ad);
}
function $converForm(u) {
	var A = u.innerhtml;
	var e = u.parentNode;
	var D = u.gridName;
	var w = u.rightJson;
	var E = document.createElement("div");
	e.appendChild(E);
	var y = u.jsonDatas;
	var z = u.pkName;
	var r = true;
	if (y && z) {
		for (var C = 0; C < y.length; C++) {
			var i = document.createElement("div");
			E.appendChild(i);
			i.setAttribute("class", "tipDiv");
			var G = document.createElement("form");
			G.setAttribute("belongName", D);
			G.setAttribute("pkName", z);
			G.setAttribute("pkValue", y[C][z]);
			i.appendChild($addDelButton(E, i, D, this.taskId, y[C][z]));
			i.appendChild(G);
			var H = document.createElement("div");
			H.innerHTML = A;
			G.appendChild(H);
			try {
				var F = $converCmp.call(this, G.elements, y[C], w, true);
				if (F == "un") {
					r = false;
				}
			} catch (v) {
				alert(v);
			}
		}
	} else {
		var i = document.createElement("div");
		E.appendChild(i);
		var G = document.createElement("form");
		G.setAttribute("belongName", D);
		i.appendChild($addDelButton(E, i, D, this.taskId, null));
		i.appendChild(G);
		var H = document.createElement("div");
		H.innerHTML = A;
		G.appendChild(H);
		try {
			var F = $converCmp.call(this, G.elements, null, w, true);
			if (F == "un") {
				r = false;
			}
		} catch (v) {
		}
	}
	if (r) {
		var x = document.createElement("div");
		E.appendChild(x);
		var B = new Ext.Button({
					renderTo : x,
					text : "添加",
					tableHtml : A,
					gridName : D,
					addButtonDiv : x,
					parentNode : E,
					iconCls : "btn-add",
					scope : this,
					handler : function(d) {
						var a = document.createElement("div");
						d.parentNode.insertBefore(a, d.addButtonDiv);
						var c = document.createElement("div");
						var b = document.createElement("form");
						b.setAttribute("belongName", D);
						a.appendChild(c);
						c.appendChild($addDelButton(a, c, D, null, null));
						c.appendChild(b);
						var f = document.createElement("div");
						f.innerHTML = A;
						b.appendChild(f);
						$converCmp.call(this, b.elements, null, null, true);
					}
				});
	}
}
function $converFormDetail(d) {
	for (var c = 0; c < d.length; c++) {
		$converForm.call(this, d[c]);
	}
}
function $addDelButton(n, h, i, l, m) {
	var k = document.createElement("span");
	k.setAttribute("style", " float:right;height:20px;");
	var j = document.createElement("button");
	j.setAttribute("class", "x-btn-text btn-del");
	j.setAttribute("style", "float:right;");
	j.qtip = "删除";
	j.owerDiv = n;
	j.removeDiv = h;
	j.gridName = i;
	j.taskId = l;
	j.pkValue = m;
	j.onclick = function() {
		try {
			var e = this.owerDiv;
			var b = this.removeDiv;
			var f = this.gridName;
			var c = this.taskId;
			var d = this.pkValue;
			Ext.Msg.confirm("信息确认", "您确认要删除所选记录吗？", function(p) {
				if (p == "yes") {
					if (d) {
						var g = null;
						if (c) {
							g = document.getElementById(f + "_" + c).value;
						}
						Ext.Ajax.request({
									url : __ctxPath
											+ "/flow/delItemsProcessActivity.do",
									params : {
										tableId : g,
										ids : d
									},
									method : "POST",
									success : function(r, o) {
										Ext.ux.Toast.msg("操作信息", "成功删除该记录！");
										e.removeChild(b);
									},
									failure : function(r, o) {
										Ext.ux.Toast
												.msg("操作信息", "操作出错，请联系管理员！");
									}
								});
					} else {
						e.removeChild(b);
					}
				}
			});
		} catch (a) {
			alert(a);
		}
	};
	k.appendChild(j);
	return k;
}
function $converDetailToRead(V) {
	var W = this.formPanel.getForm().getEl().dom;
	var S = W.getElementsByTagName("table");
	this.detailGrids = new Ext.util.MixedCollection();
	for (var F = 0; F < S.length; F++) {
		var O = S[F].getAttribute("isdetail");
		var w = S[F].getAttribute("isgrid");
		var f = S[F].getAttribute("txtname");
		if (O != null && "true" == w) {
			var R = S[F].parentNode;
			var M = $convertTableToMap(S[F]);
			var H = [];
			var X = [];
			if (this.runId) {
				var i = document.getElementById("WF_" + f + "_" + this.runId);
				var J = i.value;
				H.push(J);
				X.push({
							dataIndex : J,
							header : J,
							hidden : true
						});
			}
			var G = 0;
			for (var E = 0; E < M.length; E++) {
				H.push(M[E].name);
				X.push({
							dataIndex : M[E].name,
							header : M[E].header
						});
			}
			var K = document.createElement("div");
			R.appendChild(K);
			this.detailPanel = new HT.EditorGridPanel({
						renderTo : K,
						clicksToEdit : 1,
						width : S[F].offsetWidth,
						showPaging : false,
						autoHeight : true,
						fields : H,
						columns : X
					});
			if (V) {
				var I = V["WF_" + f + "s"];
				if (I) {
					this.detailPanel.getStore().loadData({
								result : I
							});
				}
			}
			R.removeChild(S[F]);
			break;
		} else {
			if (O != null && "false" == w) {
				var L = $getTableInputCmpName(S[F]);
				if (this.runId) {
					var T = document.getElementById("WF_" + f + "_"
							+ this.runId);
					var Y = T.value;
					if (V) {
						var I = V["WF_" + f + "s"];
						if (I) {
							var P = I[0];
							var G = false;
							var Z = P[Y];
							for (var Q = 0; Q < L.length; Q++) {
								if (L[Q] == Y) {
									G = true;
								}
								V[L[Q]] = P[L[Q]];
							}
							if (!G) {
								var aa = new Ext.form.Hidden({
											name : f + "_" + Y,
											value : Z
										});
								this.formPanel.add(aa);
								this.formPanel.doLayout(true);
							}
						}
					}
				}
			}
		}
	}
	var ab = W.elements || (document.forms[W] || Ext.getDom(W)).elements;
	var U = this.formPanel;
	var M = new Ext.util.MixedCollection();
	var N = new Array();
	Ext.each(ab, function(t, u) {
				var a, x, l, v, p;
				var e = null;
				if (!t) {
					return;
				}
				a = t.name;
				x = t.type;
				if (x == "button" || x == "hidden") {
					return;
				}
				if (t.id == "entity_" + this.runId) {
					return;
				}
				v = t.getAttribute("xtype");
				if (V && V[a]) {
					t.value = V[a];
				}
				var s = t.parentNode;
				var m = t.getAttribute("width");
				if (!m) {
					m = s.offsetWidth;
				}
				if (m < 300 && s.offsetWidth > 300) {
					m = 300;
				}
				if (v == "officeeditor") {
					var j = document.createElement("span");
					p = s.offsetHeight;
					this.hiddenF = new Ext.form.Hidden({
								name : a
							});
					this.hiddenF.render(j);
					try {
						s.replaceChild(j, t);
					} catch (g) {
						alert(a + "   error !!");
					}
					Ext.useShims = true;
					var r = new NtkOfficePanel({
								showToolbar : false,
								width : m,
								height : p,
								doctype : "doc",
								unshowMenuBar : false
							});
					r.setReadOnly();
					r.panel.render(j);
					if (t.value) {
						this.hiddenF.setValue(t.value);
						r.openDoc(t.value);
						this.fileId = t.value;
					}
					this.officePanel = r;
					U.add(r);
				} else {
					if (v == "fileattach") {
						var j = document.createElement("span");
						var y = new Ext.form.Hidden({
									name : a
								});
						y.render(j);
						try {
							s.replaceChild(j, t);
						} catch (g) {
							alert(a + "   error !!");
						}
						var b = new Ext.Panel({
									width : m ? (m - 90 > 0 ? m - 90 : m) : m,
									height : 60,
									autoScroll : true,
									html : ""
								});
						var r = new Ext.form.CompositeField({
									renderTo : j,
									width : m,
									items : [b]
								});
						if (t.value) {
							y.setValue(t.value);
							var n = t.value.split(",");
							for (var h = 0; h < n.length; h++) {
								var k = n[h];
								var q = k.split("|");
								var d = q[0];
								var c = q[1];
								Ext.DomHelper.append(b.body,
										'<span><a href="#" onclick="FileAttachDetail.show('
												+ d + ')">' + c
												+ "</a>&nbsp;|&nbsp;</span>");
							}
						}
					} else {
						p = s.offsetHeight;
						t.style.display = "none";
						try {
							var o = document.createElement("span");
							o.setAttribute("style", "width:" + m
											+ "px; height:" + p + "px;");
							o.innerHTML = t.value ? t.value : "";
							s.appendChild(o);
						} catch (g) {
						}
					}
				}
			}, this);
}
function $validField(z) {
	var s = z.getAttribute("txtisnotnull");
	var A = z.getAttribute("xtype");
	var t = z.getAttribute("txtsize");
	var x = z.getAttribute("dataformat");
	var q = true;
	var B;
	if (s == 1) {
		if (z.value == "") {
			B = "此选项为必填项";
			q = false;
		}
	}
	if (q && t && z.value.toString().length > t) {
		B = "此项内容不得超过" + t;
		q = false;
	}
	if (false && q) {
		var r = z.value;
		if (r != "") {
			var u = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
			q = u.test(r);
			B = "此项内容为邮件格式：XXX@XX.com";
		}
	}
	if (x && q) {
		var r = z.value;
		if (r != "") {
			var C = new RegExp(x);
			q = C.test(r);
			B = "此项内容的格式不正确";
		}
	}
	if (A == "numberfield" && q) {
		var r = z.value;
		var D = z.getAttribute("txtvaluetype");
		if (r != "") {
			if (D == "int" || D == "bigint" || D == "smallint") {
				var v = /^[-\+]?\d+$/;
				q = v.test(r);
				B = "此项内容应为整数";
			} else {
				var w = /^-?\d+\.?\d*$/;
				q = w.test(r);
				B = "此项内容应为数字";
			}
		}
	}
	var p = " x-form-invalid";
	var y = z.getAttribute("class");
	if (!q) {
		if (y) {
			if (y.indexOf(p) == -1) {
				y = y + p;
			}
		} else {
			y = p;
		}
		z.setAttribute("class", y);
		z.qtip = B;
		z.qclass = "x-form-invalid-tip";
		return false;
	} else {
		if (y) {
			z.setAttribute("class", y.replace(p, ""));
		}
		z.qtip = "";
		z.qclass = "";
		return true;
	}
}
function $validForm() {
	var l = this.formPanel.getForm().getEl().dom;
	var m = l.elements || (document.forms[l] || Ext.getDom(l)).elements;
	var j = true;
	Ext.each(m, function(a, b) {
				j = j && $validField.call(this, a);
			});
	var i = l.getElementsByTagName("form");
	for (var n = 0; n < i.length; n++) {
		var k = i[n];
		var f = k.elements;
		Ext.each(f, function(a, b) {
					j = j && $validField.call(this, a);
				});
	}
	if (j) {
		return true;
	} else {
		return false;
	}
}