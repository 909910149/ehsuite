Ext.ns("htsoft.ux");
htsoft.ux.TreeXmlLoader = Ext.extend(Ext.tree.TreeLoader, {
			load : function(d, c) {
				if (this.clearOnLoad) {
					while (d.firstChild) {
						d.removeChild(d.firstChild);
					}
				}
				if (this.doPreload(d)) {
					if (typeof c == "function") {
						c();
					}
				} else {
					this.loadXml(d, c);
				}
			},
			doPreload : function(i) {
				if (i.attributes.children) {
					if (i.childNodes.length < 1) {
						var j = i.attributes.children;
						i.beginUpdate();
						for (var f = 0, g = j.length; f < g; f++) {
							var h = i.appendChild(this.createNode(j[f]));
							if (this.preloadChildren) {
								this.doPreload(h);
							}
						}
						i.endUpdate();
					}
					return true;
				} else {
					return false;
				}
			},
			loadXml : function(c, h) {
				var j = c.attributes.xmlNode;
				if (j && ((j.nodeType == 1) || (j.nodeType == 9))) {
					childNodes = j.childNodes, l = j.childNodes.length;
					for (var g = 0; g < l; g++) {
						var i = j.childNodes[g];
						if (i.nodeType == 1) {
							c.appendChild(this.createNode({
										id : i.getAttribute("id"),
										iconCls : i.getAttribute("iconCls"),
										text : i.getAttribute("text"),
										iframe : i.getAttribute("iframe"),
										model : i.getAttribute("model"),
										url : i.getAttribute("url"),
										defId : i.getAttribute("defId"),
										flowName : i.getAttribute("flowName"),
										flowNode : i.getAttribute("flowNode"),
										params : i.getAttribute("params"),
										xmlNode : i,
										expanded : true,
										leaf : ((i.childNodes.length) == 0)
									}));
						} else {
							if ((i.nodeType == 3)
									&& (i.data.trim().length != 0)) {
								c.appendChild(this.createNode({
											expanded : true,
											text : i.data,
											leaf : true
										}));
							}
						}
					}
				}
				h(this, c);
			},
			createNode : function(attr) {
				if (this.baseAttrs) {
					Ext.applyIf(attr, this.baseAttrs);
				}
				if (this.applyLoader !== false) {
					attr.loader = this;
				}
				if (typeof attr.uiProvider == "string") {
					attr.uiProvider = this.uiProviders[attr.uiProvider]
							|| eval(attr.uiProvider);
				}
				return (attr.leaf
						? new Ext.tree.TreeNode(attr)
						: new Ext.tree.AsyncTreeNode(attr));
			}
		});