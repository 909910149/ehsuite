var FCKDomTools={MoveChildren:function(h,g,e){if(h==g){return;}var f;if(e){while((f=h.lastChild)){g.insertBefore(h.removeChild(f),g.firstChild);}}else{while((f=h.firstChild)){g.appendChild(h.removeChild(f));}}},MoveNode:function(d,f,e){if(e){f.insertBefore(FCKDomTools.RemoveNode(d),f.firstChild);}else{f.appendChild(FCKDomTools.RemoveNode(d));}},TrimNode:function(b){this.LTrimNode(b);this.RTrimNode(b);},LTrimNode:function(h){var g;while((g=h.firstChild)){if(g.nodeType==3){var e=g.nodeValue.LTrim();var f=g.nodeValue.length;if(e.length==0){h.removeChild(g);continue;}else{if(e.length<f){g.splitText(f-e.length);h.removeChild(h.firstChild);}}}break;}},RTrimNode:function(h){var g;while((g=h.lastChild)){if(g.nodeType==3){var e=g.nodeValue.RTrim();var f=g.nodeValue.length;if(e.length==0){g.parentNode.removeChild(g);continue;}else{if(e.length<f){g.splitText(e.length);h.lastChild.parentNode.removeChild(h.lastChild);}}}break;}if(!FCKBrowserInfo.IsIE&&!FCKBrowserInfo.IsOpera){g=h.lastChild;if(g&&g.nodeType==1&&g.nodeName.toLowerCase()=="br"){g.parentNode.removeChild(g);}}},RemoveNode:function(d,f){if(f){var e;while((e=d.firstChild)){d.parentNode.insertBefore(d.removeChild(e),d);}}return d.parentNode.removeChild(d);},GetFirstChild:function(f,e){if(typeof(e)=="string"){e=[e];}var d=f.firstChild;while(d){if(d.nodeType==1&&d.tagName.Equals.apply(d.tagName,e)){return d;}d=d.nextSibling;}return null;},GetLastChild:function(f,e){if(typeof(e)=="string"){e=[e];}var d=f.lastChild;while(d){if(d.nodeType==1&&(!e||d.tagName.Equals(e))){return d;}d=d.previousSibling;}return null;},GetPreviousSourceElement:function(h,g,f,e){if(!h){return null;}if(f&&h.nodeType==1&&h.nodeName.IEquals(f)){return null;}if(h.previousSibling){h=h.previousSibling;}else{return this.GetPreviousSourceElement(h.parentNode,g,f,e);}while(h){if(h.nodeType==1){if(f&&h.nodeName.IEquals(f)){break;}if(!e||!h.nodeName.IEquals(e)){return h;}}else{if(g&&h.nodeType==3&&h.nodeValue.RTrim().length>0){break;}}if(h.lastChild){h=h.lastChild;}else{return this.GetPreviousSourceElement(h,g,f,e);}}return null;},GetNextSourceElement:function(i,h,f,j,g){while((i=this.GetNextSourceNode(i,g))){if(i.nodeType==1){if(f&&i.nodeName.IEquals(f)){break;}if(j&&i.nodeName.IEquals(j)){return this.GetNextSourceElement(i,h,f,j);}return i;}else{if(h&&i.nodeType==3&&i.nodeValue.RTrim().length>0){break;}}}return null;},GetNextSourceNode:function(j,g,f,i){if(!j){return null;}var h;if(!g&&j.firstChild){h=j.firstChild;}else{if(i&&j==i){return null;}h=j.nextSibling;if(!h&&(!i||i!=j.parentNode)){return this.GetNextSourceNode(j.parentNode,true,f,i);}}if(f&&h&&h.nodeType!=f){return this.GetNextSourceNode(h,false,f,i);}return h;},GetPreviousSourceNode:function(j,g,f,i){if(!j){return null;}var h;if(!g&&j.lastChild){h=j.lastChild;}else{if(i&&j==i){return null;}h=j.previousSibling;if(!h&&(!i||i!=j.parentNode)){return this.GetPreviousSourceNode(j.parentNode,true,f,i);}}if(f&&h&&h.nodeType!=f){return this.GetPreviousSourceNode(h,false,f,i);}return h;},InsertAfterNode:function(c,d){return c.parentNode.insertBefore(d,c.nextSibling);},GetParents:function(c){var d=new Array();while(c){d.unshift(c);c=c.parentNode;}return d;},GetCommonParents:function(l,g){var i=this.GetParents(l);var j=this.GetParents(g);var h=[];for(var k=0;k<i.length;k++){if(i[k]==j[k]){h.push(i[k]);}}return h;},GetCommonParentNode:function(g,h,j){var i={};if(!j.pop){j=[j];}while(j.length>0){i[j.pop().toLowerCase()]=1;}var k=this.GetCommonParents(g,h);var l=null;while((l=k.pop())){if(i[l.nodeName.toLowerCase()]){return l;}}return null;},GetIndexOf:function(f){var d=f.parentNode?f.parentNode.firstChild:null;var e=-1;while(d){e++;if(d==f){return e;}d=d.nextSibling;}return -1;},PaddingNode:null,EnforcePaddingNode:function(g,f){try{if(!g||!g.body){return;}}catch(h){return;}this.CheckAndRemovePaddingNode(g,f,true);try{if(g.body.lastChild&&(g.body.lastChild.nodeType!=1||g.body.lastChild.tagName.toLowerCase()==f.toLowerCase())){return;}}catch(h){return;}var e=g.createElement(f);if(FCKBrowserInfo.IsGecko&&FCKListsLib.NonEmptyBlockElements[f]){FCKTools.AppendBogusBr(e);}this.PaddingNode=e;if(g.body.childNodes.length==1&&g.body.firstChild.nodeType==1&&g.body.firstChild.tagName.toLowerCase()=="br"&&(g.body.firstChild.getAttribute("_moz_dirty")!=null||g.body.firstChild.getAttribute("type")=="_moz")){g.body.replaceChild(e,g.body.firstChild);}else{g.body.appendChild(e);}},CheckAndRemovePaddingNode:function(h,j,e){var g=this.PaddingNode;if(!g){return;}try{if(g.parentNode!=h.body||g.tagName.toLowerCase()!=j||(g.childNodes.length>1)||(g.firstChild&&g.firstChild.nodeValue!="\xa0"&&String(g.firstChild.tagName).toLowerCase()!="br")){this.PaddingNode=null;return;}}catch(i){this.PaddingNode=null;return;}if(!e){if(g.parentNode.childNodes.length>1){g.parentNode.removeChild(g);}this.PaddingNode=null;}},HasAttribute:function(f,d){if(f.hasAttribute){return f.hasAttribute(d);}else{var e=f.attributes[d];return(e!=undefined&&e.specified);}},HasAttributes:function(h){var f=h.attributes;for(var e=0;e<f.length;e++){if(FCKBrowserInfo.IsIE){var g=f[e].nodeName;if(g.StartsWith("_fck")){continue;}if(g=="class"){if(h.className.length>0){return true;}continue;}}if(f[e].specified){return true;}}return false;},RemoveAttribute:function(c,d){if(FCKBrowserInfo.IsIE&&d.toLowerCase()=="class"){d="className";}return c.removeAttribute(d,0);},RemoveAttributes:function(f,e){for(var d=0;d<e.length;d++){this.RemoveAttribute(f,e[d]);}},GetAttributeValue:function(d,e){var f=e;if(typeof e=="string"){e=d.attributes[e];}else{f=e.nodeName;}if(e&&e.specified){if(f=="style"){return d.style.cssText;}else{if(f=="class"||f.indexOf("on")==0){return e.nodeValue;}else{return d.getAttribute(f,2);}}}return null;},Contains:function(c,d){if(c.contains&&d.nodeType==1){return c.contains(d);}while((d=d.parentNode)){if(d==c){return true;}}return false;},BreakParent:function(j,h,i){var g=i||new FCKDomRange(FCKTools.GetElementWindow(j));g.SetStart(j,4);g.SetEnd(h,4);var f=g.ExtractContents();g.InsertNode(j.parentNode.removeChild(j));f.InsertAfterNode(j);g.Release(!!i);},GetNodeAddress:function(k,j){var n=[];while(k&&k!=FCKTools.GetElementDocument(k).documentElement){var i=k.parentNode;var h=-1;for(var m=0;m<i.childNodes.length;m++){var l=i.childNodes[m];if(j===true&&l.nodeType==3&&l.previousSibling&&l.previousSibling.nodeType==3){continue;}h++;if(i.childNodes[m]==k){break;}}n.unshift(h);k=k.parentNode;}return n;},GetNodeFromAddress:function(m,n,l){var j=m.documentElement;for(var q=0;q<n.length;q++){var p=n[q];if(!l){j=j.childNodes[p];continue;}var o=-1;for(var r=0;r<j.childNodes.length;r++){var i=j.childNodes[r];if(l===true&&i.nodeType==3&&i.previousSibling&&i.previousSibling.nodeType==3){continue;}o++;if(o==p){j=i;break;}}}return j;},CloneElement:function(b){b=b.cloneNode(false);b.removeAttribute("id",false);return b;},ClearElementJSProperty:function(c,d){if(FCKBrowserInfo.IsIE){c.removeAttribute(d);}else{delete c[d];}},SetElementMarker:function(g,j,f,i){var h=String(parseInt(Math.random()*4294967295,10));j._FCKMarkerId=h;j[f]=i;if(!g[h]){g[h]={"element":j,"markers":{}};}g[h]["markers"][f]=i;},ClearElementMarkers:function(g,i,j){var h=i._FCKMarkerId;if(!h){return;}this.ClearElementJSProperty(i,"_FCKMarkerId");for(var f in g[h]["markers"]){this.ClearElementJSProperty(i,f);}if(j){delete g[h];}},ClearAllMarkers:function(d){for(var c in d){this.ClearElementMarkers(d,d[c]["element"],true);}},ListToArray:function(r,q,j,m,i){if(!r.nodeName.IEquals(["ul","ol"])){return[];}if(!m){m=0;}if(!j){j=[];}for(var o=0;o<r.childNodes.length;o++){var t=r.childNodes[o];if(!t.nodeName.IEquals("li")){continue;}var n={"parent":r,"indent":m,"contents":[]};if(!i){n.grandparent=r.parentNode;if(n.grandparent&&n.grandparent.nodeName.IEquals("li")){n.grandparent=n.grandparent.parentNode;}}else{n.grandparent=i;}if(q){this.SetElementMarker(q,t,"_FCK_ListArray_Index",j.length);}j.push(n);for(var p=0;p<t.childNodes.length;p++){var s=t.childNodes[p];if(s.nodeName.IEquals(["ul","ol"])){this.ListToArray(s,q,j,m+1,n.grandparent);}else{n.contents.push(s);}}}return j;},ArrayToList:function(z,y,s){if(s==undefined){s=0;}if(!z||z.length<s+1){return null;}var q=FCKTools.GetElementDocument(z[s].parent);var B=q.createDocumentFragment();var A=null;var t=s;var p=Math.max(z[s].indent,0);var r=null;while(true){var i=z[t];if(i.indent==p){if(!A||z[t].parent.nodeName!=A.nodeName){A=z[t].parent.cloneNode(false);B.appendChild(A);}r=q.createElement("li");A.appendChild(r);for(var v=0;v<i.contents.length;v++){r.appendChild(i.contents[v].cloneNode(true));}t++;}else{if(i.indent==Math.max(p,0)+1){var u=this.ArrayToList(z,null,t);r.appendChild(u.listNode);t=u.nextIndex;}else{if(i.indent==-1&&s==0&&i.grandparent){var r;if(i.grandparent.nodeName.IEquals(["ul","ol"])){r=q.createElement("li");}else{if(FCKConfig.EnterMode.IEquals(["div","p"])&&!i.grandparent.nodeName.IEquals("td")){r=q.createElement(FCKConfig.EnterMode);}else{r=q.createDocumentFragment();}}for(var v=0;v<i.contents.length;v++){r.appendChild(i.contents[v].cloneNode(true));}if(r.nodeType==11){if(r.lastChild&&r.lastChild.getAttribute&&r.lastChild.getAttribute("type")=="_moz"){r.removeChild(r.lastChild);}r.appendChild(q.createElement("br"));}if(r.nodeName.IEquals(FCKConfig.EnterMode)&&r.firstChild){this.TrimNode(r);if(FCKListsLib.BlockBoundaries[r.firstChild.nodeName.toLowerCase()]){var x=q.createDocumentFragment();while(r.firstChild){x.appendChild(r.removeChild(r.firstChild));}r=x;}}if(FCKBrowserInfo.IsGeckoLike&&r.nodeName.IEquals(["div","p"])){FCKTools.AppendBogusBr(r);}B.appendChild(r);A=null;t++;}else{return null;}}}if(z.length<=t||Math.max(z[t].indent,0)<p){break;}}if(y){var C=B.firstChild;while(C){if(C.nodeType==1){this.ClearElementMarkers(y,C);}C=this.GetNextSourceNode(C);}}return{"listNode":B,"nextIndex":t};},GetNextSibling:function(c,d){c=c.nextSibling;while(c&&!d&&c.nodeType!=1&&(c.nodeType!=3||c.nodeValue.length==0)){c=c.nextSibling;}return c;},GetPreviousSibling:function(c,d){c=c.previousSibling;while(c&&!d&&c.nodeType!=1&&(c.nodeType!=3||c.nodeValue.length==0)){c=c.previousSibling;}return c;},CheckIsEmptyElement:function(h,e){var g=h.firstChild;var f;while(g){if(g.nodeType==1){if(f||!FCKListsLib.InlineNonEmptyElements[g.nodeName.toLowerCase()]){return false;}if(!e||e(g)===true){f=g;}}else{if(g.nodeType==3&&g.nodeValue.length>0){return false;}}g=g.nextSibling;}return f?this.CheckIsEmptyElement(f,e):true;},SetElementStyles:function(h,f){var g=h.style;for(var e in f){g[e]=f[e];}},SetOpacity:function(c,d){if(FCKBrowserInfo.IsIE){d=Math.round(d*100);c.style.filter=(d>100?"":"progid:DXImageTransform.Microsoft.Alpha(opacity="+d+")");}else{c.style.opacity=d;}},GetCurrentElementStyle:function(c,d){if(FCKBrowserInfo.IsIE){return c.currentStyle[d];}else{return c.ownerDocument.defaultView.getComputedStyle(c,"").getPropertyValue(d);}},GetPositionedAncestor:function(c){var d=c;while(d!=FCKTools.GetElementDocument(d).documentElement){if(this.GetCurrentElementStyle(d,"position")!="static"){return d;}if(d==FCKTools.GetElementDocument(d).documentElement&&currentWindow!=w){d=currentWindow.frameElement;}else{d=d.parentNode;}}return null;},ScrollIntoView:function(h,l){var m=FCKTools.GetElementWindow(h);var j=FCKTools.GetViewPaneSize(m).Height;var k=j*-1;if(l===false){k+=h.offsetHeight||0;k+=parseInt(this.GetCurrentElementStyle(h,"marginBottom")||0,10)||0;}var n=FCKTools.GetDocumentPosition(m,h);k+=n.y;var i=FCKTools.GetScrollPosition(m).Y;if(k>0&&(k>i||k<i-j)){m.scrollTo(0,k);}},CheckIsEditable:function(e){var f=e.nodeName.toLowerCase();var d=FCK.DTD[f]||FCK.DTD.span;return(d["#"]&&!FCKListsLib.NonEditableElements[f]);},GetSelectedDivContainers:function(){var k=[];var h=new FCKDomRange(FCK.EditorWindow);h.MoveToSelection();var m=h.GetTouchedStartNode();var i=h.GetTouchedEndNode();var l=m;if(m==i){while(i.nodeType==1&&i.lastChild){i=i.lastChild;}i=FCKDomTools.GetNextSourceNode(i);}while(l&&l!=i){if(l.nodeType!=3||!/^[ \t\n]*$/.test(l.nodeValue)){var j=new FCKElementPath(l);var n=j.BlockLimit;if(n&&n.nodeName.IEquals("div")&&k.IndexOf(n)==-1){k.push(n);}}l=FCKDomTools.GetNextSourceNode(l);}return k;}};