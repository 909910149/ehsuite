var FCKElementPath=function(k){var h=null;var l=null;var i=new Array();var j=k;while(j){if(j.nodeType==1){if(!this.LastElement){this.LastElement=j;}var e=j.nodeName.toLowerCase();if(FCKBrowserInfo.IsIE&&j.scopeName!="HTML"){e=j.scopeName.toLowerCase()+":"+e;}if(!l){if(!h&&FCKListsLib.PathBlockElements[e]!=null){h=j;}if(FCKListsLib.PathBlockLimitElements[e]!=null){if(!h&&e=="div"&&!FCKElementPath._CheckHasBlock(j)){h=j;}else{l=j;}}}i.push(j);if(e=="body"){break;}}j=j.parentNode;}this.Block=h;this.BlockLimit=l;this.Elements=i;};FCKElementPath._CheckHasBlock=function(f){var i=f.childNodes;for(var g=0,j=i.length;g<j;g++){var h=i[g];if(h.nodeType==1&&FCKListsLib.BlockElements[h.nodeName.toLowerCase()]){return true;}}return false;};