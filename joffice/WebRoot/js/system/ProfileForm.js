var ProfileForm=function(b){return this.setup(b);};ProfileForm.prototype.setup=function(d){var f=this.initFooterToolbar(d);var e=new Ext.form.FormPanel({id:"ProfileForm",title:"个人资料",closable:true,iconCls:"menu-customer",border:false,autoScroll:true,labelAlign:"right",layout:"fit",tbar:f,defaultType:"textfield",url:__ctxPath+"/system/profileAppUser.do",reader:new Ext.data.JsonReader({root:"data"},[{name:"appUser.userId",mapping:"userId"},{name:"appUser.username",mapping:"username"},{name:"appUser.fullname",mapping:"fullname"},{name:"appUser.email",mapping:"email"},{name:"profileTitle",mapping:"title"},{name:"appUser.position",mapping:"position"},{name:"appUser.phone",mapping:"phone"},{name:"appUser.mobile",mapping:"mobile"},{name:"appUser.fax",mapping:"fax"},{name:"appUser.address",mapping:"address"},{name:"appUser.zip",mapping:"zip"},{name:"appUser.photo",mapping:"photo"}]),items:[{xtype:"panel",frame:false,autoWidth:true,autoHeight:true,border:false,layout:"table",bodyStyle:"margin-top:5px;margin-left: 17%; background-color: transparent;",layoutConfig:{columns:2},items:[{id:"displayProfilePhoto",xtype:"panel",width:230,rowspan:2,style:"padding:3px 4px 25px 0px;",height:290,title:"个人照片",html:'<img src="'+__ctxPath+'/images/default_image_male.jpg"/>',tbar:new Ext.Toolbar({height:30,items:[{text:"上传",iconCls:"btn-upload",handler:function(){ProfileForm.uploadPhotoBtn(d);}},{text:"删除",iconCls:"btn-delete",handler:function(){ProfileForm.deleteUserPhoto(d);}}]})},{xtype:"panel",id:"ProfileMustInfo",width:305,height:290,title:"个人资料",layout:"form",style:"padding:3px 4px 25px 0px;",defaultType:"textfield",defaults:{width:203},labelWidth:55,labelAlign:"right",hideLabels:false,items:[{xtype:"hidden",fieldLabel:"员工ID",name:"appUser.userId",id:"profile.userId"},{fieldLabel:"登录账号",name:"appUser.username",id:"profile.username",readOnly:true},{fieldLabel:"员工姓名",name:"appUser.fullname",id:"profile.fullname",allowBlank:false,blankText:"员工姓名不能为空!"},{fieldLabel:"E-mail",name:"appUser.email",id:"profile.email",vtype:"email",allowBlank:false,blankText:"邮箱不能为空!",vtypeText:"邮箱格式不正确!"},{fieldLabel:"性别",xtype:"combo",hiddenName:"appUser.title",id:"profileTitle",mode:"local",editable:false,triggerAction:"all",store:[["1","先生"],["0","女士"]],value:"1",listeners:{select:function(a,j,c){var i=Ext.getCmp("profile.photo");if(i.value==""||i.value=="undefined"||i.value==null){var b=Ext.getCmp("displayProfilePhoto");if(a.value=="0"){b.body.update('<img src="'+__ctxPath+'/images/default_image_female.jpg" />');}else{b.body.update('<img src="'+__ctxPath+'/images/default_image_male.jpg" />');}}}}},{fieldLabel:"家庭电话",name:"appUser.phone",id:"profile.phone"},{fieldLabel:"移动电话",xtype:"numberfield",name:"appUser.mobile",id:"profile.mobile"},{fieldLabel:"传真",name:"appUser.fax",id:"profile.fax"},{fieldLabel:"家庭住址",name:"appUser.address",id:"profile.address"},{fieldLabel:"邮编",xtype:"numberfield",name:"appUser.zip",id:"profile.zip"},{filedLabel:"照片",xtype:"hidden",id:"profile.photo",name:"appUser.photo"}]}]}]});e.getForm().load({deferredRender:false,url:__ctxPath+"/system/getAppUser.do",waitMsg:"正在载入数据...",success:function(c,b){var j=Ext.getCmp("profile.photo");var a=Ext.getCmp("displayProfilePhoto");var i=Ext.getCmp("profileTitle");if(j.value!=""&&j.value!=null&&j.value!="undefined"){a.body.update('<img src="'+__ctxPath+"/attachFiles/"+j.value+'" width="100%" height="100%"/>');}else{if(i.value=="0"){a.body.update('<img src="'+__ctxPath+'/images/default_image_female.jpg" />');}}},failure:function(b,a){Ext.ux.Toast.msg("编辑","载入失败");}});return e;};ProfileForm.prototype.initFooterToolbar=function(d){var c=new Ext.Toolbar({id:"ProfileFormToolbar",height:30,items:[{text:"保存",iconCls:"btn-save",handler:function(){var a=Ext.getCmp("ProfileForm");a.getForm().submit({waitMsg:"正在提交用户信息",success:function(h,b){Ext.ux.Toast.msg("操作信息","保存成功！");var g=Ext.getCmp("AppUserGrid");if(g!=null){g.getStore().reload({start:0,limit:25});}}});}},{text:"取消",iconCls:"reset",handler:function(){var a=Ext.getCmp("centerTabPanel");a.remove("ProfileForm");}},{text:"修改密码",iconCls:"btn-password",handler:function(){new ResetPasswordForm(d);}}]});return c;};ProfileForm.uploadPhotoBtn=function(h){var f=Ext.getCmp("profile.photo");var e=App.createUploadDialog({file_cat:"myDesktop/profile",callback:uploadUserPhoto,permitted_extensions:["jpg"]});if(f.value!=""&&f.value!=null&&f.value!="undefined"){var g="再次上传需要先删除原有图片,";Ext.Msg.confirm("信息确认",g+"是否删除？",function(a){if(a=="yes"){Ext.Ajax.request({url:__ctxPath+"/system/deleteFileAttach.do",method:"post",params:{filePath:f.value},success:function(){if(h!=""&&h!=null&&h!="undefined"){Ext.Ajax.request({url:__ctxPath+"/system/photoAppUser.do",method:"post",params:{userId:h},success:function(){f.setValue("");var j=Ext.getCmp("profileTitle");var d=Ext.getCmp("displayProfilePhoto");if(j.value==1){d.body.update('<img src="'+__ctxPath+'/images/default_image_male.jpg" />');}else{d.body.update('<img src="'+__ctxPath+'/images/default_image_female.jpg" />');}e.show("queryBtn");}});}else{f.setValue("");var b=Ext.getCmp("profileTitle");var c=Ext.getCmp("displayProfilePhoto");if(b.value==1){c.body.update('<img src="'+__ctxPath+'/images/default_image_male.jpg" />');}else{c.body.update('<img src="'+__ctxPath+'/images/default_image_female.jpg" />');}e.show("queryBtn");}}});}});}else{e.show("queryBtn");}};ProfileForm.deleteUserPhoto=function(d){var e=Ext.getCmp("profile.photo");if(e.value!=null&&e.value!=""&&e.value!="undefined"){var f="照片一旦删除将不可恢复,";Ext.Msg.confirm("确认信息",f+"是否删除?",function(a){if(a=="yes"){Ext.Ajax.request({url:__ctxPath+"/system/deleteFileAttach.do",method:"post",params:{filePath:e.value},success:function(){if(d!=""&&d!=null&&d!="undefined"){Ext.Ajax.request({url:__ctxPath+"/system/photoAppUser.do",method:"post",params:{userId:d},success:function(){e.setValue("");var i=Ext.getCmp("profileTitle");var j=Ext.getCmp("displayProfilePhoto");if(i.value==1){j.body.update('<img src="'+__ctxPath+'/images/default_image_male.jpg" />');}else{j.body.update('<img src="'+__ctxPath+'/images/default_image_female.jpg" />');}}});}else{e.setValue("");var b=Ext.getCmp("profileTitle");var c=Ext.getCmp("displayProfilePhoto");if(b.value==1){c.body.update('<img src="'+__ctxPath+'/images/default_image_male.jpg" />');}else{c.body.update('<img src="'+__ctxPath+'/images/default_image_female.jpg" />');}}}});}});}else{Ext.ux.Toast.msg("提示信息","您还未增加照片.");}};function uploadUserPhoto(d){var e=Ext.getCmp("profile.photo");var f=Ext.getCmp("displayProfilePhoto");e.setValue(d[0].filePath);f.body.update('<img src="'+__ctxPath+"/attachFiles/"+d[0].filePath+'"  width="100%" height="100%"/>');}