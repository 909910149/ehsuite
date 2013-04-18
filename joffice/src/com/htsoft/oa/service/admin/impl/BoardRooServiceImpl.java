/*    */ package com.htsoft.oa.service.admin.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.admin.BoardRooDao;
/*    */ import com.htsoft.oa.model.admin.BoardRoo;
/*    */ import com.htsoft.oa.service.admin.BoardRooService;
/*    */ 
/*    */ public class BoardRooServiceImpl extends BaseServiceImpl<BoardRoo>
/*    */   implements BoardRooService
/*    */ {
/*    */   private BoardRooDao dao;
/*    */ 
/*    */   public BoardRooServiceImpl(BoardRooDao dao)
/*    */   {
/* 21 */     super(dao);
/* 22 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.admin.impl.BoardRooServiceImpl
 * JD-Core Version:    0.6.0
 */