var FCKeditor=function(f,i,g,j,h){this.InstanceName=f;this.Width=i||"100%";this.Height=g||"200";this.ToolbarSet=j||"Default";this.Value=h||"";this.BasePath=FCKeditor.BasePath;this.CheckBrowser=true;this.DisplayErrors=true;this.Config=new Object();this.OnError=null;};FCKeditor.BasePath="/fckeditor/";FCKeditor.MinHeight=200;FCKeditor.MinWidth=750;FCKeditor.prototype.Version="2.6.6";FCKeditor.prototype.VersionBuild="25427";FCKeditor.prototype.Create=function(){document.write(this.CreateHtml());};FCKeditor.prototype.CreateHtml=function(){if(!this.InstanceName||this.InstanceName.length==0){this._ThrowError(701,"You must specify an instance name.");return"";}var f="";if(!this.CheckBrowser||this._IsCompatibleBrowser()){f+='<input type="hidden" id="'+this.InstanceName+'" name="'+this.InstanceName+'" value="'+this._HTMLEncode(this.Value)+'" style="display:none" />';f+=this._GetConfigHtml();f+=this._GetIFrameHtml();}else{var d=this.Width.toString().indexOf("%")>0?this.Width:this.Width+"px";var e=this.Height.toString().indexOf("%")>0?this.Height:this.Height+"px";f+='<textarea name="'+this.InstanceName+'" rows="4" cols="40" style="width:'+d+";height:"+e;if(this.TabIndex){f+='" tabindex="'+this.TabIndex;}f+='">'+this._HTMLEncode(this.Value)+"</textarea>";}return f;};FCKeditor.prototype.ReplaceTextarea=function(){if(document.getElementById(this.InstanceName+"___Frame")){return;}if(!this.CheckBrowser||this._IsCompatibleBrowser()){var f=document.getElementById(this.InstanceName);var d=document.getElementsByName(this.InstanceName);var e=0;while(f||e==0){if(f&&f.tagName.toLowerCase()=="textarea"){break;}f=d[e++];}if(!f){alert('Error: The TEXTAREA with id or name set to "'+this.InstanceName+'" was not found');return;}f.style.display="none";if(f.tabIndex){this.TabIndex=f.tabIndex;}this._InsertHtmlBefore(this._GetConfigHtml(),f);this._InsertHtmlBefore(this._GetIFrameHtml(),f);}};FCKeditor.prototype._InsertHtmlBefore=function(j,e){if(e.insertAdjacentHTML){try{e.parentNode.insertAdjacentHTML("afterEnd",j);}catch(i){e.insertAdjacentHTML("afterEnd",j);}}else{var h=document.createRange();h.setStartBefore(e);var g=h.createContextualFragment(j);e.parentNode.insertBefore(g,e);}};FCKeditor.prototype._GetConfigHtml=function(){var d="";for(var c in this.Config){if(d.length>0){d+="&amp;";}d+=encodeURIComponent(c)+"="+encodeURIComponent(this.Config[c]);}return'<input type="hidden" id="'+this.InstanceName+'___Config" value="'+d+'" style="display:none" />';};FCKeditor.prototype._GetIFrameHtml=function(){var g="fckeditor.html";try{if((/fcksource=true/i).test(window.top.location.search)){g="fckeditor.original.html";}}catch(h){}var f=this.BasePath+"editor/"+g+"?InstanceName="+encodeURIComponent(this.InstanceName);if(this.ToolbarSet){f+="&amp;Toolbar="+this.ToolbarSet;}var e='<iframe id="'+this.InstanceName+'___Frame" src="'+f+'" width="'+this.Width+'"  height="'+this.Height;if(this.TabIndex){e+='" tabindex="'+this.TabIndex;}e+='" frameborder="0" scrolling="no"></iframe>';return e;};FCKeditor.prototype._IsCompatibleBrowser=function(){return FCKeditor_IsCompatibleBrowser();};FCKeditor.prototype._ThrowError=function(c,d){this.ErrorNumber=c;this.ErrorDescription=d;if(this.DisplayErrors){document.write('<div style="COLOR: #ff0000">');document.write("[ FCKeditor Error "+this.ErrorNumber+": "+this.ErrorDescription+" ]");document.write("</div>");}if(typeof(this.OnError)=="function"){this.OnError(this,c,d);}};FCKeditor.prototype._HTMLEncode=function(b){if(typeof(b)!="string"){b=b.toString();}b=b.replace(/&/g,"&amp;").replace(/"/g,"&quot;").replace(/</g,"&lt;").replace(/>/g,"&gt;");return b;};(function(){var b=function(a){var d=new FCKeditor(a.name);d.Width=Math.max(a.offsetWidth,FCKeditor.MinWidth);d.Height=Math.max(a.offsetHeight,FCKeditor.MinHeight);return d;};FCKeditor.ReplaceAllTextareas=function(){var a=document.getElementsByTagName("textarea");for(var j=0;j<a.length;j++){var i=null;var l=a[j];var k=l.name;if(!k||k.length==0){continue;}if(typeof arguments[0]=="string"){var h=new RegExp("(?:^| )"+arguments[0]+"(?:$| )");if(!h.test(l.className)){continue;}}else{if(typeof arguments[0]=="function"){i=b(l);if(arguments[0](l,i)===false){continue;}}}if(!i){i=b(l);}i.ReplaceTextarea();}};})();function FCKeditor_IsCompatibleBrowser(){var sAgent=navigator.userAgent.toLowerCase();if(
/*@cc_on!@*/
false&&sAgent.indexOf("mac")==-1){var sBrowserVersion=navigator.appVersion.match(/MSIE (.\..)/)[1];return(sBrowserVersion>=5.5);}if(navigator.product=="Gecko"&&navigator.productSub>=20030210&&!(typeof(opera)=="object"&&opera.postError)){return true;}if(window.opera&&window.opera.version&&parseFloat(window.opera.version())>=9.5){return true;}if(sAgent.indexOf(" adobeair/")!=-1){return(sAgent.match(/ adobeair\/(\d+)/)[1]>=1);}if(sAgent.indexOf(" applewebkit/")!=-1){return(sAgent.match(/ applewebkit\/(\d+)/)[1]>=522);}return false;}