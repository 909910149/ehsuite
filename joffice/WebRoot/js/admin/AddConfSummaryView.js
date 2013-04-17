Ext.ns("AddConfSummaryView");
AddConfSummaryView = Ext
		.extend(
				Ext.Panel,
				{
					topbar : null,
					form : null,
					constructor : function(b) {
						Ext.applyIf(this, b);
						this.initUIComponent();
						AddConfSummaryView.superclass.constructor.call(this, {
							id : "AddConfSummaryViewPanel",
							title : "新建会议纪要",
							iconCls : "menu-confSummary_add",
							header : true,
							region : "center",
							layout : "border",
							bodyStyle : "padding:5px 5px 5px 5px",
							items : [ this.form ]
						});
					},
					initUIComponent : function() {
						this.topbar = new Ext.Toolbar({
							id : "AddConfSummaryViewTool",
							heigth : 30,
							defaultType : "button",
							items : [ {
								iconCls : "btn-mail_send",
								text : "发送",
								handler : this.send
							}, "-", {
								iconCls : "btn-save",
								text : "保存",
								handler : this.save
							}, "-", {
								text : "取消",
								iconCls : "btn-cancel",
								handler : this.close,
								scope : this
							} ]
						});
						this.form = new Ext.form.FormPanel(
								{
									id : "AddConfSummaryViewForm",
									region : "center",
									layout : "form",
									tbar : this.topbar,
									bodyStyle : "padding:10px 10px 0 10px;",
									frame : false,
									border : true,
									defaultType : "texfield",
									items : [ {
										xtype : "fieldset",
										title : "新增会议纪要信息",
										width : "100%",
										anchor : "100%",
										layout : "form",
										buttonAlign : "center",
										defaults : {
											margins : {
												top : 5,
												left : 5,
												bottom : 5,
												right : 5
											}
										},
										items : [
												{
													xtype : "hidden",
													name : "confSummary.confId.confId"
												},
												{
													anchor : "99%",
													fieldLabel : "会议标题",
													xtype : "container",
													layout : "column",
													style : "margin-bottom:5px;",
													items : [
															{
																columnWidth : 1,
																anchor : "99%",
																xtype : "textfield",
																name : "confTopic",
																readOnly : true,
																allowBlank : false,
																maxLength : 128
															},
															{
																xtype : "button",
																text : "请选择",
																iconCls : "btn-user-sel",
																handler : function() {
																	ConferenceSelector
																			.getView(
																					function(
																							f,
																							e) {
																						var d = Ext
																								.getCmp("AddConfSummaryViewForm");
																						d
																								.getCmpByName(
																										"confSummary.confId.confId")
																								.setValue(
																										f);
																						d
																								.getCmpByName(
																										"confTopic")
																								.setValue(
																										e);
																					},
																					true)
																			.show();
																}
															} ]
												},
												{
													anchor : "99%",
													xtype : "htmleditor",
													fieldLabel : "纪要内容",
													id : "sumContent",
													name : "confSummary.sumContent",
													height : 200,
													allowBlank : false,
													maxLength : 4000
												},
												{
													xtype : "hidden",
													name : "fileIds"
												},
												{
													anchor : "99%",
													fieldLabel : "附件",
													xtype : "container",
													layout : "column",
													defaults : {
														border : false
													},
													border : false,
													items : [
															{
																columnWidth : 1,
																anchor : "100%",
																layout : "form",
																border : false,
																items : [ {
																	anchor : "100%",
																	xtype : "panel",
																	id : "filePathPanel",
																	frame : false,
																	border : true,
																	height : 50,
																	autoScroll : true,
																	html : ""
																} ]
															},
															{
																width : 75,
																defaultType : "button",
																items : [
																		{
																			text : "添加附件",
																			iconCls : "menu-attachment",
																			handler : this.upLoadFile
																		},
																		{
																			text : "清除附件",
																			iconCls : "reset",
																			handler : this.delLoadFile
																		} ]
															} ]
												} ],
										buttons : [ {
											text : "发送",
											iconCls : "btn-mail_send",
											handler : this.send
										}, {
											text : "保存",
											iconCls : "btn-save",
											handler : this.save
										}, {
											text : "取消",
											iconCls : "btn-cancel",
											handler : this.close,
											scope : this
										} ]
									} ]
								});
					},
					save : function() {
						var b = Ext.getCmp("AddConfSummaryViewForm");
						if (b.getForm().isValid()) {
							b
									.getForm()
									.submit(
											{
												url : __ctxPath
														+ "/admin/saveConfSummary.do",
												method : "post",
												success : function(f, e) {
													Ext.ux.Toast.msg("操作提示",
															"恭喜您，保存会议纪要成功！");
													b.getForm().reset();
													Ext.getCmp("filePathPanel")
															.update();
													var a = Ext
															.getCmp("centerTabPanel");
													a
															.remove("AddConfSummaryViewPanel");
													App
															.clickTopTab("ConfSummaryView");
													Ext.getCmp(
															"ConfSummaryGrid")
															.getStore()
															.reload();
												},
												failure : function(a, d) {
													Ext.ux.Toast.msg("操作提示",
															d.result.msg);
													Ext.getCmp("sumContent")
															.focus(true);
												}
											});
						}
					},
					send : function() {
						var b = Ext.getCmp("AddConfSummaryViewForm");
						if (b.getForm().isValid()) {
							b
									.getForm()
									.submit(
											{
												url : __ctxPath
														+ "/admin/sendConfSummary.do",
												method : "post",
												success : function() {
													Ext.ux.Toast.msg("操作提示",
															"恭喜您，会议纪要发送成功！");
													b.getForm().reset();
													Ext.getCmp("filePathPanel")
															.update();
													var a = Ext
															.getCmp("centerTabPanel");
													a
															.remove("AddConfSummaryViewPanel");
													App
															.clickTopTab("ConfSummaryView");
													Ext.getCmp(
															"ConfSummaryGrid")
															.getStore()
															.reload();
												},
												failure : function(a, d) {
													Ext.ux.Toast.msg("操作提示",
															d.result.msg);
													Ext.getCmp("sumContent")
															.focus(true);
												}
											});
						}
					},
					upLoadFile : function() {
						var b = App
								.createUploadDialog({
									url : __ctxPath + "/file-upload",
									file_cat : "admin/confSummary",
									callback : function(a) {
										var h = "";
										var k = "";
										for ( var l = 0; l < a.length; l++) {
											h += a[l].fileId + ",";
											k += '<span><a href="#" onclick="FileAttachDetail.show('
													+ a[l].fileId
													+ ')">'
													+ a[l].fileName
													+ '</a> <img class="img-delete" src="'
													+ __ctxPath
													+ '/images/system/delete.gif" onclick="removeContractAttach(this,'
													+ a[l].fileId
													+ ')"/>&nbsp;|&nbsp;</span>';
										}
										h = h.substring(0, h.length - 1);
										var j = Ext
												.getCmp("AddConfSummaryViewForm");
										j.getCmpByName("fileIds").setValue(h);
										var i = Ext.getCmp("filePathPanel");
										Ext.DomHelper.append(i.body, k);
									}
								});
						b.show("querybtn");
					},
					delLoadFile : function() {
						var c = Ext.getCmp("AddConfSummaryViewForm");
						var d = c.getCmpByName("fileIds").value;
						if (d != null && d != "" && d != "undefined") {
							Ext.Msg
									.confirm(
											"确认信息",
											"您真的要删除上传文件吗？",
											function(a) {
												if (a == "yes") {
													Ext.Ajax
															.request({
																url : __ctxPath
																		+ "/system/multiDelFileAttach.do",
																method : "post",
																params : {
																	ids : d
																},
																success : function() {
																	Ext.ux.Toast
																			.msg(
																					"操作提示",
																					"上传文件删除成功！");
																	c
																			.getCmpByName(
																					"fileIds")
																			.setValue(
																					"");
																	Ext
																			.getCmp(
																					"filePathPanel")
																			.update();
																},
																failure : function() {
																	Ext.ux.Toast
																			.msg(
																					"操作提示",
																					"对不起，您上传文件删除失败！");
																}
															});
												}
											});
						} else {
							Ext.ux.Toast.msg("操作提示", "对不起，你还没有上传文件！");
						}
					},
					close : function() {
						Ext.getCmp("centerTabPanel").remove(
								"AddConfSummaryViewPanel");
					}
				});
function removeContractAttach(h, g) {
	var f = Ext.getCmp("AddConfSummaryViewForm").getCmpByName("fileIds");
	var i = f.getValue();
	if (i.indexOf(",") < 0) {
		f.setValue("");
	} else {
		i = i.replace("," + g, "").replace(g + ",", "");
		f.setValue(i);
	}
	var j = Ext.get(h.parentNode);
	j.remove();
}