Ext.ns("FormDefDetailForm");FormDefDetailForm.show=function(c){var d=new Ext.Window({title:"表单详细信息展示",iconCls:"menu-form",modal:true,width:600,height:380,layout:"form",region:"center",autoScroll:true,maximizable:true,autoLoad:{url:__ctxPath+"/pages/flow/formDefDetail.jsp?formDefId="+c},buttonAlign:"center",buttons:[{text:"取消",iconCls:"btn-cancel",handler:function(){d.close();}}]});d.show();};