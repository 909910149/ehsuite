ReportTemplateMenu=function(b){Ext.applyIf(this,b);this.reportId=getReportIdByKey(this.reportKey);return ReportTemplatePreview(this.reportId,this.title);};var getReportIdByKey=function(c){var d="";Ext.Ajax.request({url:__ctxPath+"/system/listReportTemplate.do",method:"POST",async:false,success:function(f,b){var a=Ext.decode(f.responseText);d=a.result[0].reportId;},failure:function(b,a){},params:{Q_reportKey_S_EQ:c}});return d;};