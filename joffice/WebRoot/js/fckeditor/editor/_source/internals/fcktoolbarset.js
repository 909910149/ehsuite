function FCKToolbarSet_Create(overhideLocation){var oToolbarSet;var sLocation=overhideLocation||FCKConfig.ToolbarLocation;switch(sLocation){case"In":document.getElementById("xToolbarRow").style.display="";oToolbarSet=new FCKToolbarSet(document);break;case"None":oToolbarSet=new FCKToolbarSet(document);break;default:FCK.Events.AttachEvent("OnBlur",FCK_OnBlur);FCK.Events.AttachEvent("OnFocus",FCK_OnFocus);var eToolbarTarget;var oOutMatch=sLocation.match(/^Out:(.+)\((\w+)\)$/);if(oOutMatch){if(FCKBrowserInfo.IsAIR){FCKAdobeAIR.ToolbarSet_GetOutElement(window,oOutMatch);}else{eToolbarTarget=eval("parent."+oOutMatch[1]).document.getElementById(oOutMatch[2]);}}else{oOutMatch=sLocation.match(/^Out:(\w+)$/);if(oOutMatch){eToolbarTarget=parent.document.getElementById(oOutMatch[1]);}}if(!eToolbarTarget){alert('Invalid value for "ToolbarLocation"');return arguments.callee("In");}oToolbarSet=eToolbarTarget.__FCKToolbarSet;if(oToolbarSet){break;}var eToolbarIFrame=FCKTools.GetElementDocument(eToolbarTarget).createElement("iframe");eToolbarIFrame.src="javascript:void(0)";eToolbarIFrame.frameBorder=0;eToolbarIFrame.width="100%";eToolbarIFrame.height="10";eToolbarTarget.appendChild(eToolbarIFrame);eToolbarIFrame.unselectable="on";var eTargetDocument=eToolbarIFrame.contentWindow.document;var sBase="";if(FCKBrowserInfo.IsSafari){sBase='<base href="'+window.document.location+'">';}eTargetDocument.open();eTargetDocument.write("<html><head>"+sBase+'<script type="text/javascript"> var adjust = function() { window.frameElement.height = document.body.scrollHeight ; }; '+"window.onresize = window.onload = "+"function(){"+"var timer = null;"+"var lastHeight = -1;"+"var lastChange = 0;"+"var poller = function(){"+"var currentHeight = document.body.scrollHeight || 0;"+"var currentTime = (new Date()).getTime();"+"if (currentHeight != lastHeight){"+"lastChange = currentTime;"+"adjust();"+"lastHeight = document.body.scrollHeight;"+"}"+"if (lastChange < currentTime - 1000) clearInterval(timer);"+"};"+"timer = setInterval(poller, 100);"+"}"+'<\/script></head><body style="overflow: hidden">'+document.getElementById("xToolbarSpace").innerHTML+"</body></html>");eTargetDocument.close();if(FCKBrowserInfo.IsAIR){FCKAdobeAIR.ToolbarSet_InitOutFrame(eTargetDocument);}FCKTools.AddEventListener(eTargetDocument,"contextmenu",FCKTools.CancelEvent);FCKTools.AppendStyleSheet(eTargetDocument,FCKConfig.SkinEditorCSS);oToolbarSet=eToolbarTarget.__FCKToolbarSet=new FCKToolbarSet(eTargetDocument);oToolbarSet._IFrame=eToolbarIFrame;if(FCK.IECleanup){FCK.IECleanup.AddItem(eToolbarTarget,FCKToolbarSet_Target_Cleanup);}}oToolbarSet.CurrentInstance=FCK;if(!oToolbarSet.ToolbarItems){oToolbarSet.ToolbarItems=FCKToolbarItems;}FCK.AttachToOnSelectionChange(oToolbarSet.RefreshItemsState);return oToolbarSet;}function FCK_OnBlur(d){var c=d.ToolbarSet;if(c.CurrentInstance==d){c.Disable();}}function FCK_OnFocus(d){var f=d.ToolbarSet;var e=d||FCK;f.CurrentInstance.FocusManager.RemoveWindow(f._IFrame.contentWindow);f.CurrentInstance=e;e.FocusManager.AddWindow(f._IFrame.contentWindow,true);f.Enable();}function FCKToolbarSet_Cleanup(){this._TargetElement=null;this._IFrame=null;}function FCKToolbarSet_Target_Cleanup(){this.__FCKToolbarSet=null;}var FCKToolbarSet=function(d){this._Document=d;this._TargetElement=d.getElementById("xToolbar");var e=d.getElementById("xExpandHandle");var f=d.getElementById("xCollapseHandle");e.title=FCKLang.ToolbarExpand;FCKTools.AddEventListener(e,"click",FCKToolbarSet_Expand_OnClick);f.title=FCKLang.ToolbarCollapse;FCKTools.AddEventListener(f,"click",FCKToolbarSet_Collapse_OnClick);if(!FCKConfig.ToolbarCanCollapse||FCKConfig.ToolbarStartExpanded){this.Expand();}else{this.Collapse();}f.style.display=FCKConfig.ToolbarCanCollapse?"":"none";if(FCKConfig.ToolbarCanCollapse){f.style.display="";}else{d.getElementById("xTBLeftBorder").style.display="";}this.Toolbars=new Array();this.IsLoaded=false;if(FCK.IECleanup){FCK.IECleanup.AddItem(this,FCKToolbarSet_Cleanup);}};function FCKToolbarSet_Expand_OnClick(){FCK.ToolbarSet.Expand();}function FCKToolbarSet_Collapse_OnClick(){FCK.ToolbarSet.Collapse();}FCKToolbarSet.prototype.Expand=function(){this._ChangeVisibility(false);};FCKToolbarSet.prototype.Collapse=function(){this._ChangeVisibility(true);};FCKToolbarSet.prototype._ChangeVisibility=function(b){this._Document.getElementById("xCollapsed").style.display=b?"":"none";this._Document.getElementById("xExpanded").style.display=b?"none":"";if(window.onresize){FCKTools.RunFunction(window.onresize);}};FCKToolbarSet.prototype.Load=function(p){this.Name=p;this.Items=new Array();this.ItemsWysiwygOnly=new Array();this.ItemsContextSensitive=new Array();this._TargetElement.innerHTML="";var k=FCKConfig.ToolbarSets[p];if(!k){alert(FCKLang.UnknownToolbarSet.replace(/%1/g,p));return;}this.Toolbars=new Array();for(var i=0;i<k.length;i++){var m=k[i];if(!m){continue;}var n;if(typeof(m)=="string"){if(m=="/"){n=new FCKToolbarBreak();}}else{n=new FCKToolbar();for(var o=0;o<m.length;o++){var j=m[o];if(j=="-"){n.AddSeparator();}else{var l=FCKToolbarItems.GetItem(j);if(l){n.AddItem(l);this.Items.push(l);if(!l.SourceView){this.ItemsWysiwygOnly.push(l);}if(l.ContextSensitive){this.ItemsContextSensitive.push(l);}}}}}n.Create(this._TargetElement);this.Toolbars[this.Toolbars.length]=n;}FCKTools.DisableSelection(this._Document.getElementById("xCollapseHandle").parentNode);if(FCK.Status!=FCK_STATUS_COMPLETE){FCK.Events.AttachEvent("OnStatusChange",this.RefreshModeState);}else{this.RefreshModeState();}this.IsLoaded=true;this.IsEnabled=true;FCKTools.RunFunction(this.OnLoad);};FCKToolbarSet.prototype.Enable=function(){if(this.IsEnabled){return;}this.IsEnabled=true;var d=this.Items;for(var c=0;c<d.length;c++){d[c].RefreshState();}};FCKToolbarSet.prototype.Disable=function(){if(!this.IsEnabled){return;}this.IsEnabled=false;var d=this.Items;for(var c=0;c<d.length;c++){d[c].Disable();}};FCKToolbarSet.prototype.RefreshModeState=function(i){if(FCK.Status!=FCK_STATUS_COMPLETE){return;}var h=i?i.ToolbarSet:this;var g=h.ItemsWysiwygOnly;if(FCK.EditMode==FCK_EDITMODE_WYSIWYG){for(var j=0;j<g.length;j++){g[j].Enable();}h.RefreshItemsState(i);}else{h.RefreshItemsState(i);for(var f=0;f<g.length;f++){g[f].Disable();}}};FCKToolbarSet.prototype.RefreshItemsState=function(f){var e=(f?f.ToolbarSet:this).ItemsContextSensitive;for(var d=0;d<e.length;d++){e[d].RefreshState();}};