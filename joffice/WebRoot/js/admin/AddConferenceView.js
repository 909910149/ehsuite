AddConferenceView = Ext
		.extend(
				Ext.Panel,
				{
					store : null,
					gridPanel : null,
					formPanel : null,
					basePanel : null,
					contextPanel : null,
					jonerPanel : null,
					grantPanel : null,
					filePanel : null,
					constructor : function(b) {
						Ext.applyIf(this, b);
						this.initUIComponents();
						AddConferenceView.superclass.constructor.call(this, {
							id : "AddConferenceViewWin",
							layout : "form",
							region : "center",
							title : "会议申请",
							iconCls : "menu-conference_add",
							tbar : new Ext.Toolbar({
								heigth : 30,
								defaultType : "button",
								items : [ {
									text : "发送会议通知",
									iconCls : "btn-mail_send",
									handler : this.send.createCallback(this)
								}, "-", {
									text : "暂存会议",
									iconCls : "temp",
									handler : this.save.createCallback(this)
								}, "-", {
									text : "清空",
									iconCls : "reset",
									handler : this.reset
								}, "-", {
									text : "取消",
									iconCls : "btn-cancel",
									handler : this.close
								} ]
							}),
							modal : true,
							maximizable : true,
							bodyStyle : "padding : 5px 5px 5px 5px",
							autoScroll : false,
							items : this.formPanel,
							keys : {
								key : Ext.EventObject.ENTER,
								fn : this.send.createCallback(this),
								scope : this
							}
						});
					},
					initUIComponents : function() {
						this.basePanel = new Ext.form.FieldSet(
								{
									id : "addConference.basePanel",
									title : "会议信息",
									layout : "form",
									border : true,
									items : [ {
										layout : "column",
										columnWidth : 1,
										border : false,
										defaults : {
											border : false
										},
										items : [
												{
													layout : "form",
													columnWidth : 0.5,
													defaults : {
														width : "80%"
													},
													items : [
															{
																xtype : "hidden",
																name : "conference.confId",
																value : this.confId != null ? this.confId
																		: ""
															},
															{
																anchor : "99%",
																xtype : "textfield",
																name : "conference.confTopic",
																fieldLabel : "会议标题",
																allowBlank : false,
																maxLength : 128
															},
															{
																xtype : "hidden",
																name : "conference.typeId"
															},
															{
																anchor : "99%",
																xtype : "combo",
																name : "conference.confProperty",
																fieldLabel : "会议类型",
																valueField : "typeId",
																displayField : "typeName",
																mode : "local",
																editable : false,
																emptyText : "--请选择会议类型--",
																triggerAction : "all",
																forceSelection : true,
																allowBlank : false,
																store : new Ext.data.SimpleStore(
																		{
																			url : __ctxPath
																					+ "/admin/getTypeAllConference.do",
																			autoLoad : true,
																			fields : [
																					"typeId",
																					"typeName" ]
																		}),
																listeners : {
																	select : function(
																			g,
																			f,
																			e) {
																		var h = Ext
																				.getCmp("addConference.basePanel");
																		h
																				.getCmpByName(
																						"conference.typeId")
																				.setValue(
																						g.value);
																	}
																}
															},
															{
																anchor : "99%",
																name : "conference.feeBudget",
																fieldLabel : "经费(元)",
																text : "0",
																xtype : "numberfield",
																maxLength : 21
															},
															{
																anchor : "99%",
																fieldLabel : "留言方式",
																xtype : "container",
																layout : "column",
																defaults : {
																	xtype : "checkbox",
																	margins : "0 20 0 10"
																},
																items : [
																		{
																			columnWidth : 0.5,
																			anchor : "99%",
																			boxLabel : "普通留言",
																			name : "conference.isEmail",
																			inputValue : 1,
																			checked : true
																		},
																		{
																			columnWidth : 0.5,
																			anchor : "99%",
																			boxLabel : "手机留言",
																			name : "conference.isMobile",
																			inputValue : 1,
																			width : 100
																		} ]
															} ]
												},
												{
													anchor : "99%",
													layout : "form",
													columnWidth : 0.5,
													items : [
															{
																anchor : "99%",
																xtype : "combo",
																hiddenName : "conference.roomId",
																fieldLabel : "会议室名称",
																valueField : "roomId",
																displayField : "roomName",
																mode : "local",
																editable : false,
																emptyText : "--请选择会议室--",
																triggerAction : "all",
																forceSelection : true,
																allowBlank : false,
																store : new Ext.data.SimpleStore(
																		{
																			url : __ctxPath
																					+ "/admin/getBoardrooConference.do",
																			autoLoad : true,
																			fields : [
																					"roomId",
																					"roomName" ]
																		}),
																listeners : {
																	select : function(
																			g,
																			f,
																			e) {
																		var h = Ext
																				.getCmp("addConference.basePanel");
																		h
																				.getCmpByName(
																						"conference.roomName")
																				.setValue(
																						g
																								.getRawValue());
																		h
																				.getCmpByName(
																						"conference.roomLocation")
																				.setValue(
																						g
																								.getRawValue());
																	}
																}
															},
															{
																anchor : "99%",
																xtype : "textfield",
																fieldLabel : "会议室",
																name : "conference.roomName"
															},
															{
																anchor : "99%",
																xtype : "textfield",
																fieldLabel : "地址",
																name : "conference.roomLocation",
																allowBlank : false,
																maxLength : "128"
															},
															{
																anchor : "99%",
																xtype : "radiogroup",
																fieldLabel : "重要级别",
																border : false,
																items : [
																		{
																			boxLabel : "普通",
																			name : "conference.importLevel",
																			inputValue : 0,
																			checked : true
																		},
																		{
																			boxLabel : "重要",
																			name : "conference.importLevel",
																			inputValue : 1
																		} ]
															} ]
												} ]
									} ]
								});
						this.jonerPanel = new Ext.form.FieldSet(
								{
									id : "addConference.jonerPanel",
									title : "参加人员",
									layout : "form",
									autoScroll : true,
									border : true,
									items : [
											{
												anchor : "99%",
												fieldLabel : "主持人",
												xtype : "container",
												layout : "column",
												border : false,
												defaults : {
													border : false
												},
												items : [
														{
															xtype : "hidden",
															name : "conference.compere"
														},
														{
															columnWidth : 1,
															anchor : "90%",
															xtype : "textfield",
															name : "conference.compereName",
															readOnly : true,
															allowBlank : false,
															maxLength : 256
														},
														{
															width : 75,
															xtype : "button",
															text : "请选择",
															iconCls : "btn-user-sel",
															handler : function() {
																UserSelector
																		.getView(
																				function(
																						e,
																						f) {
																					var d = Ext
																							.getCmp("addConference.jonerPanel");
																					d
																							.getCmpByName(
																									"conference.compere")
																							.setValue(
																									e);
																					d
																							.getCmpByName(
																									"conference.compereName")
																							.setValue(
																									f);
																				},
																				false)
																		.show();
															}
														} ]
											},
											{
												anchor : "99%",
												fieldLabel : "记录人",
												xtype : "container",
												layout : "column",
												border : false,
												defaults : {
													border : false
												},
												items : [
														{
															xtype : "hidden",
															name : "conference.recorder"
														},
														{
															columnWidth : 1,
															anchor : "90%",
															xtype : "textfield",
															name : "conference.recorderName",
															readOnly : true,
															allowBlank : false,
															maxLength : 256
														},
														{
															width : 75,
															xtype : "button",
															text : "请选择",
															iconCls : "btn-user-sel",
															handler : function() {
																UserSelector
																		.getView(
																				function(
																						e,
																						f) {
																					var d = Ext
																							.getCmp("addConference.jonerPanel");
																					d
																							.getCmpByName(
																									"conference.recorder")
																							.setValue(
																									e);
																					d
																							.getCmpByName(
																									"conference.recorderName")
																							.setValue(
																									f);
																				},
																				false)
																		.show();
															}
														} ]
											},
											{
												anchor : "99%",
												fieldLabel : "参加人",
												xtype : "container",
												layout : "column",
												border : false,
												items : [
														{
															xtype : "hidden",
															name : "conference.attendUsers"
														},
														{
															columnWidth : 1,
															anchor : "90%",
															xtype : "textfield",
															name : "conference.attendUsersName",
															readOnly : true,
															allowBlank : false,
															maxLength : 4000
														},
														{
															width : 75,
															xtype : "button",
															text : "请选择",
															iconCls : "btn-user-sel",
															handler : function() {
																UserSelector
																		.getView(
																				function(
																						e,
																						f) {
																					var d = Ext
																							.getCmp("addConference.jonerPanel");
																					d
																							.getCmpByName(
																									"conference.attendUsers")
																							.setValue(
																									e);
																					d
																							.getCmpByName(
																									"conference.attendUsersName")
																							.setValue(
																									f);
																				},
																				false)
																		.show();
															}
														} ]
											} ]
								});
						this.grantPanel = new Ext.form.FieldSet(
								{
									id : "addConference.grantPanel",
									title : "权限设置",
									region : "center",
									layout : "form",
									border : true,
									items : [
											{
												anchor : "99%",
												xtype : "container",
												fieldLabel : "修改人",
												layout : "column",
												border : false,
												items : [
														{
															xtype : "hidden",
															name : "updater"
														},
														{
															columnWidth : 1,
															anchor : "90%",
															xtype : "textfield",
															name : "updaters",
															readOnly : true,
															allowBlank : false,
															maxLength : 256
														},
														{
															width : 75,
															xtype : "button",
															text : "请选择",
															iconCls : "btn-user-sel",
															handler : function() {
																UserSelector
																		.getView(
																				function(
																						e,
																						f) {
																					var d = Ext
																							.getCmp("addConference.grantPanel");
																					d
																							.getCmpByName(
																									"updater")
																							.setValue(
																									e);
																					d
																							.getCmpByName(
																									"updaters")
																							.setValue(
																									f);
																				},
																				false)
																		.show();
															}
														} ]
											},
											{
												anchor : "99%",
												fieldLabel : "审核人",
												xtype : "container",
												layout : "column",
												defaults : {
													border : false
												},
												border : false,
												items : [
														{
															xtype : "hidden",
															name : "conference.checkUserId"
														},
														{
															columnWidth : 1,
															width : "90%",
															xtype : "textfield",
															name : "conference.checkName",
															readOnly : true,
															allowBlank : false,
															maxLength : 64
														},
														{
															width : 75,
															xtype : "button",
															text : "请选择",
															iconCls : "btn-user-sel",
															handler : function() {
																UserSelector
																		.getView(
																				function(
																						e,
																						f) {
																					var d = Ext
																							.getCmp("addConference.grantPanel");
																					d
																							.getCmpByName(
																									"conference.checkUserId")
																							.setValue(
																									e);
																					d
																							.getCmpByName(
																									"conference.checkName")
																							.setValue(
																									f);
																				},
																				true)
																		.show();
															}
														} ]
											}, {
												layout : "column",
												height : 26,
												border : false
											} ]
								});
						this.contextPanel = new Ext.form.FieldSet({
							title : "时间和内容设置",
							layout : "column",
							columnWidth : 1,
							border : true,
							items : [ {
								columnWidth : 0.5,
								anchor : "99%",
								layout : "form",
								border : false,
								items : [ {
									anchor : "99%",
									xtype : "datetimefield",
									format : "Y-m-d H:i:s",
									editable : false,
									name : "conference.startTime",
									minValue : new Date(),
									fieldLabel : "开始时间",
									allowBlank : false
								} ]
							}, {
								columnWidth : 0.5,
								anchor : "99%",
								layout : "form",
								border : false,
								items : [ {
									anchor : "99%",
									name : "conference.endTime",
									xtype : "datetimefield",
									format : "Y-m-d H:i:s",
									minValue : new Date(),
									editable : false,
									fieldLabel : "结束时间",
									allowBlank : false
								} ]
							}, {
								columnWidth : 1,
								anchor : "100%",
								layout : "form",
								height : 100,
								border : false,
								items : [ {
									anchor : "100%",
									name : "conference.confContent",
									xtype : "htmleditor",
									height : 150,
									fieldLabel : "会议内容",
									allowBlank : false,
									maxLength : 4000
								} ]
							} ]
						});
						this.filePanel = new Ext.form.FieldSet({
							id : "addConferenceView.filePath",
							layout : "form",
							region : "center",
							title : "附件信息",
							bodyStyle : "padding : 5px 5px 5px 5px",
							items : [ {
								xtype : "hidden",
								name : "filePath"
							}, {
								fieldLabel : "附件信息",
								xtype : "container",
								columnWidth : 1,
								layout : "column",
								border : false,
								items : [ {
									columnWidth : 1,
									anchor : "99%",
									xtype : "panel",
									id : "resumeFilePanel",
									height : 50,
									border : true,
									autoScroll : true,
									html : ""
								}, {
									width : 75,
									defaultType : "button",
									border : false,
									items : [ {
										iconCls : "menu-attachment",
										text : "上传附件",
										handler : this.upLoadFile
									}, {
										text : "清除附件",
										iconCls : "reset",
										handler : this.delLoadFile
									} ]
								} ]
							} ]
						});
						this.formPanel = new Ext.FormPanel({
							id : "AddConferenceViewPanel",
							autoScroll : true,
							layout : "form",
							region : "center",
							bodyStyle : "padding : 10px 10px 10px 10px;",
							border : false,
							defaults : {
								readOnly : true
							},
							items : [ {
								id : "addConferenceViewAllfieldset",
								xtype : "fieldset",
								title : "会议申请",
								bodyStyle : "padding:5px 5px 5px 5px",
								layout : "form",
								buttonAlign : "center",
								items : [ this.basePanel, this.contextPanel, {
									layout : "column",
									border : false,
									columnWidth : 1,
									defaults : {
										border : false
									},
									items : [ {
										columnWidth : 0.5,
										layout : "form",
										items : [ this.jonerPanel ]
									}, {
										columnWidth : 0.5,
										layout : "form",
										items : [ this.grantPanel ]
									} ]
								}, this.filePanel ],
								buttons : [ {
									text : "发送会议通知",
									iconCls : "btn-mail_send",
									handler : this.send.createCallback(this)
								}, {
									text : "暂存会议",
									iconCls : "temp",
									handler : this.save.createCallback(this)
								}, {
									text : "清空",
									iconCls : "reset",
									handler : this.reset
								} ]
							} ]
						});
					},
					reset : function() {
						Ext.getCmp("AddConferenceViewPanel").getForm().reset();
						Ext.getCmp("resumeFilePanel").body.update("");
					},
					save : function(d) {
						var e = Ext.getCmp("AddConferenceViewPanel");
						if (e.getForm().isValid()) {
							var f = new Date().format("Y-m-d H:i:s");
							if (f > e.getCmpByName("conference.startTime").value) {
								Ext.ux.Toast.msg("操作提示",
										"对不起，开会时间必须在当前时间之前，请重新输入！");
								e.getCmpByName("conference.startTime").focus(
										true);
								return;
							}
							if (e.getCmpByName("conference.startTime").value >= e
									.getCmpByName("conference.endTime").value) {
								Ext.ux.Toast.msg("操作提示", "对不起，开会时间有误，请重新输入！");
								e.getCmpByName("conference.startTime").focus(
										true);
								return;
							}
							if (!AddConferenceView.validateCompereAndRecorder()) {
								return;
							}
							e
									.getForm()
									.submit(
											{
												url : __ctxPath
														+ "/admin/tempConference.do",
												method : "post",
												waitMsg : "数据正在保存，请稍后...",
												success : function(c, a) {
													Ext.ux.Toast.msg("操作信息",
															"成功保存信息！");
													Ext
															.getCmp(
																	"AddConferenceViewPanel")
															.getForm().reset();
													Ext
															.getCmp("resumeFilePanel").body
															.update("");
													var b = Ext
															.getCmp("centerTabPanel");
													b
															.remove("AddConferenceViewWin");
													App
															.clickTopTab("ZanCunConferenceView");
												},
												failure : function(b, a) {
													Ext.MessageBox
															.show({
																title : "操作信息",
																msg : "对不起，会议申请失败！",
																buttons : Ext.MessageBox.OK,
																icon : "ext-mb-error"
															});
												}
											});
						}
					},
					send : function(d) {
						var e = Ext.getCmp("AddConferenceViewPanel");
						if (e.getForm().isValid()) {
							var f = new Date().format("Y-m-d H:i:s");
							if (f > e.getCmpByName("conference.startTime").value) {
								Ext.ux.Toast.msg("操作提示",
										"对不起，开会时间必须在当前时间之前，请重新输入！");
								e.getCmpByName("conference.startTime").focus(
										true);
								return;
							}
							if (e.getCmpByName("conference.startTime").value >= e
									.getCmpByName("conference.endTime").value) {
								Ext.ux.Toast.msg("操作提示", "对不起，开会时间有误，请重新输入！");
								e.getCmpByName("conference.startTime").focus(
										true);
								return;
							}
							if (!AddConferenceView.validateCompereAndRecorder()) {
								return;
							}
							e
									.getForm()
									.submit(
											{
												url : __ctxPath
														+ "/admin/sendConference.do",
												method : "post",
												waitMsg : "数据正在发送，请稍后...",
												success : function(c, a) {
													Ext.ux.Toast.msg("操作信息",
															"成功发送会议申请信息,等待审批！");
													Ext
															.getCmp("resumeFilePanel").body
															.update("");
													Ext
															.getCmp(
																	"AddConferenceViewPanel")
															.getForm().reset();
													var b = Ext
															.getCmp("centerTabPanel");
													b
															.remove("AddConferenceViewWin");
													App
															.clickTopTab("DaiConfApplyView");
												},
												failure : function(c, a) {
													var b = Ext.util.JSON
															.decode(a.response.responseText);
													Ext.MessageBox
															.show({
																title : "操作信息",
																msg : b.msg,
																buttons : Ext.MessageBox.OK,
																icon : "ext-mb-error"
															});
												}
											});
						}
					},
					upLoadFile : function() {
						var b = App
								.createUploadDialog({
									file_cat : "admin/conference",
									callback : function(a) {
										var h = "";
										var f = Ext.getCmp("resumeFilePanel");
										for ( var g = 0; g < a.length; g++) {
											h += a[g].fileId + ",";
											Ext.DomHelper
													.append(
															f.body,
															'<span><a href="#" onclick="FileAttachDetail.show('
																	+ a[g].fileId
																	+ ')">'
																	+ a[g].fileName
																	+ '</a><img class="img-delete" src="'
																	+ __ctxPath
																	+ '/images/system/delete.gif" onclick="removeResumeFile(this,'
																	+ a[g].fileId
																	+ ')"/>&nbsp;|&nbsp;</span>');
										}
										Ext
												.getCmp(
														"addConferenceView.filePath")
												.getCmpByName("filePath")
												.setValue(
														h.substring(0,
																h.length - 1));
									}
								});
						b.show("querybtn");
					},
					delLoadFile : function() {
						var c = Ext.getCmp("addConferenceView.filePath");
						var d = c.getCmpByName("filePath").value;
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
																					"filePath")
																			.setValue(
																					"");
																	Ext
																			.getCmp(
																					"resumeFilePanel")
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
								"AddConferenceViewWin");
					}
				});
AddConferenceView.validateCompereAndRecorder = function() {
	var i = Ext.getCmp("addConference.jonerPanel");
	var f = i.getCmpByName("conference.compere").value.split(",");
	var g = i.getCmpByName("conference.recorder").value.split(",");
	var h = true;
	if (f.length == 1 && g.length == 1) {
		if (f[0].search(g) >= 0) {
			h = false;
		}
	} else {
		if (f.length == 1 && g.length != 1) {
			for ( var j = 0; j < g.length; j++) {
				if (g[j].search(f) >= 0) {
					h = false;
				}
			}
		} else {
			if (f.length != 1 && g.length == 1) {
				for ( var j = 0; j < f.length; j++) {
					if (f[j].search(g) >= 0) {
						h = false;
					}
				}
			}
		}
	}
	if (h == false) {
		i.getCmpByName("conference.compereName").focus(true);
		Ext.ux.Toast.msg("操作提示", "对不起，会议主持人和记录人不能出现重复，请重新选择！");
	}
	return h;
};
function removeResumeFile(h, g) {
	var f = Ext.getCmp("addConferenceView.filePath").getCmpByName("filePath");
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