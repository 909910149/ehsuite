Ext.ns("htsoft.ux");
htsoft.ux.TreePanelEditor = Ext.extend(Ext.tree.TreePanel, {
			showContextMenu : true,
			url : null,
			contextMenu : null,
			contextMenuItems : null,
			selectedNode : null,
			constructor : function(b) {
				if (b == null) {
					b = {};
				}
				Ext.apply(this, b);
				htsoft.ux.TreePanelEditor.superclass.constructor.call(this, {
							tbar : new Ext.Toolbar({
										items : [{
													xtype : "button",
													iconCls : "btn-refresh",
													text : "刷新",
													scope : this,
													handler : function() {
														this.root.reload();
													}
												}, {
													xtype : "button",
													text : "展开",
													iconCls : "btn-expand",
													scope : this,
													handler : function() {
														this.expandAll();
													}
												}, {
													xtype : "button",
													text : "收起",
													iconCls : "btn-collapse",
													scope : this,
													handler : function() {
														this.collapseAll();
													}
												}]
									}),
							loader : new Ext.tree.TreeLoader({
										url : this.url
									}),
							root : new Ext.tree.AsyncTreeNode({
										expanded : true
									}),
							listeners : {
								"click" : function(a) {
									b.onclick.call(this, a);
								}
							},
							rootVisible : false
						});
				this.initContextMenu();
			},
			initContextMenu : function() {
				if (this.showContextMenu) {
					this.contextMenu = new Ext.menu.Menu({});
					if (this.contextMenuItems != null) {
						this.contextMenu.add(this.contextMenuItems);
					}
					this.on("contextmenu", this.contextHandler, this);
				}
			},
			contextHandler : function contextmenu(d, c) {
				this.selectedNode = new Ext.tree.TreeNode({
							id : d.id,
							text : d.text
						});
				this.contextMenu.showAt(c.getXY());
			}
		});
Ext.reg("treePanelEditor", htsoft.ux.TreePanelEditor);