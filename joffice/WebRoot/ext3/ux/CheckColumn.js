Ext.ns("Ext.ux.grid");Ext.ux.grid.CheckColumn=function(b){Ext.apply(this,b);if(!this.id){this.id=Ext.id();}this.renderer=this.renderer.createDelegate(this);};Ext.ux.grid.CheckColumn.prototype={init:function(b){this.grid=b;this.grid.on("render",function(){var a=this.grid.getView();a.mainBody.on("mousedown",this.onMouseDown,this);},this);},onMouseDown:function(g,h){if(Ext.fly(h).hasClass(this.createId())){g.stopEvent();var e=this.grid.getView().findRowIndex(h);var f=this.grid.store.getAt(e);f.set(this.dataIndex,!f.data[this.dataIndex]);}},renderer:function(d,f,e){f.css+=" x-grid3-check-col-td";return String.format('<div class="x-grid3-check-col{0} {1}">&#160;</div>',d?"-on":"",this.createId());},createId:function(){return"x-grid3-cc-"+this.id;}};Ext.preg("checkcolumn",Ext.ux.grid.CheckColumn);Ext.grid.CheckColumn=Ext.ux.grid.CheckColumn;