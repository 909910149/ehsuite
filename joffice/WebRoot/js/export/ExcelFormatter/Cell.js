Ext.ux.Exporter.ExcelFormatter.Cell=Ext.extend(Object,{constructor:function(b){Ext.applyIf(b,{type:"String"});Ext.apply(this,b);Ext.ux.Exporter.ExcelFormatter.Cell.superclass.constructor.apply(this,arguments);},render:function(){return this.tpl.apply(this);},tpl:new Ext.XTemplate('<ss:Cell ss:StyleID="{style}">','<ss:Data ss:Type="{type}">{value}</ss:Data>',"</ss:Cell>")});