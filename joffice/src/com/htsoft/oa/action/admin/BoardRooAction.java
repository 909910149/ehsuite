 package com.htsoft.oa.action.admin;
 
 import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.admin.BoardRoo;
import com.htsoft.oa.service.admin.BoardRooService;
 
 public class BoardRooAction extends BaseAction
 {
 
   @Resource
   private BoardRooService boardRooService;
   private BoardRoo boardRoo;
   private Long roomId;
 
   public Long getRoomId()
   {
     return this.roomId;
   }
 
   public void setRoomId(Long roomId) {
     this.roomId = roomId;
   }
 
   public BoardRoo getBoardRoo() {
     return this.boardRoo;
   }
 
   public void setBoardRoo(BoardRoo boardRoo) {
     this.boardRoo = boardRoo;
   }
 
   public String list()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     filter.addSorted("roomId", "DESC");
     List<BoardRoo> list = this.boardRooService.getAll(filter);
     Type type = new TypeToken<List<BoardRoo>>() {
     }.getType();
     StringBuffer bf = new StringBuffer("{success:true,'totalCounts':")
       .append(filter.getPagingBean().getTotalItems()).append(
       ",result:");
     Gson gson = new Gson();
     bf.append(gson.toJson(list, type)).append("}");
     setJsonString(bf.toString());
     return "success";
   }
 
   public String multiDel()
   {
     String[] ids = getRequest().getParameterValues("ids");
     if (ids != null) {
       for (String id : ids) {
         this.boardRooService.remove(Long.valueOf(id));
       }
     }
     this.jsonString = "{success:true}";
     return "success";
   }
 
   public String del()
   {
     this.boardRooService.remove(this.roomId);
     this.jsonString = "{success:true}";
     return "success";
   }
 
   public String save()
   {
     this.boardRooService.save(this.boardRoo);
     this.jsonString = "{success:true,msg:'保存成功'}";
     return "success";
   }
 
   public String get()
   {
     BoardRoo boardRoo = (BoardRoo)this.boardRooService.get(this.roomId);
     Gson gson = new Gson();
     StringBuffer bf = new StringBuffer("{success:true,data:");
     bf.append(gson.toJson(boardRoo)).append("}");
     setJsonString(bf.toString());
     return "success";
   }
 }

