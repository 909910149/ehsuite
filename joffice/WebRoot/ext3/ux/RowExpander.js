Ext.ns("Ext.ux.grid");Ext.ux.grid.RowExpander=Ext.extend(Ext.util.Observable,{expandOnEnter:true,expandOnDblClick:true,header:"",width:20,sortable:false,fixed:true,menuDisabled:true,dataIndex:"",id:"expander",lazyRender:true,enableCaching:true,constructor:function(b){Ext.apply(this,b);this.addEvents({beforeexpand:true,expand:true,beforecollapse:true,collapse:true});Ext.ux.grid.RowExpander.superclass.constructor.call(this);if(this.tpl){if(typeof this.tpl=="string"){this.tpl=new Ext.Template(this.tpl);}this.tpl.compile();}this.state={};this.bodyContent={};},getRowClass:function(g,h,i,j){i.cols=i.cols-1;var f=this.bodyContent[g.id];if(!f&&!this.lazyRender){f=this.getBodyContent(g,h);}if(f){i.body=f;}return this.state[g.id]?"x-grid3-row-expanded":"x-grid3-row-collapsed";},init:function(c){this.grid=c;var d=c.getView();d.getRowClass=this.getRowClass.createDelegate(this);d.enableRowBody=true;c.on("render",this.onRender,this);c.on("destroy",this.onDestroy,this);},onRender:function(){var d=this.grid;var c=d.getView().mainBody;c.on("mousedown",this.onMouseDown,this,{delegate:".x-grid3-row-expander"});if(this.expandOnEnter){this.keyNav=new Ext.KeyNav(this.grid.getGridEl(),{"enter":this.onEnter,scope:this});}if(this.expandOnDblClick){d.on("rowdblclick",this.onRowDblClick,this);}},onDestroy:function(){if(this.keyNav){this.keyNav.disable();delete this.keyNav;}var b=this.grid.getView().mainBody;if(b){b.un("mousedown",this.onMouseDown,this);}},onRowDblClick:function(e,d,f){this.toggleRow(d);},onEnter:function(k){var l=this.grid;var i=l.getSelectionModel();var e=i.getSelections();for(var n=0,g=e.length;n<g;n++){var m=l.getStore().indexOf(e[n]);this.toggleRow(m);}},getBodyContent:function(e,d){if(!this.enableCaching){return this.tpl.apply(e.data);}var f=this.bodyContent[e.id];if(!f){f=this.tpl.apply(e.data);this.bodyContent[e.id]=f;}return f;},onMouseDown:function(d,e){d.stopEvent();var f=d.getTarget(".x-grid3-row");this.toggleRow(f);},renderer:function(d,f,e){f.cellAttr='rowspan="2"';return'<div class="x-grid3-row-expander">&#160;</div>';},beforeExpand:function(d,e,f){if(this.fireEvent("beforeexpand",this,d,e,f)!==false){if(this.tpl&&this.lazyRender){e.innerHTML=this.getBodyContent(d,f);}return true;}else{return false;}},toggleRow:function(b){if(typeof b=="number"){b=this.grid.view.getRow(b);}this[Ext.fly(b).hasClass("x-grid3-row-collapsed")?"expandRow":"collapseRow"](b);},expandRow:function(f){if(typeof f=="number"){f=this.grid.view.getRow(f);}var d=this.grid.store.getAt(f.rowIndex);var e=Ext.DomQuery.selectNode("tr:nth(2) div.x-grid3-row-body",f);if(this.beforeExpand(d,e,f.rowIndex)){this.state[d.id]=true;Ext.fly(f).replaceClass("x-grid3-row-collapsed","x-grid3-row-expanded");this.fireEvent("expand",this,d,e,f.rowIndex);}},collapseRow:function(f){if(typeof f=="number"){f=this.grid.view.getRow(f);}var d=this.grid.store.getAt(f.rowIndex);var e=Ext.fly(f).child("tr:nth(1) div.x-grid3-row-body",true);if(this.fireEvent("beforecollapse",this,d,e,f.rowIndex)!==false){this.state[d.id]=false;Ext.fly(f).replaceClass("x-grid3-row-expanded","x-grid3-row-collapsed");this.fireEvent("collapse",this,d,e,f.rowIndex);}}});Ext.preg("rowexpander",Ext.ux.grid.RowExpander);Ext.grid.RowExpander=Ext.ux.grid.RowExpander;