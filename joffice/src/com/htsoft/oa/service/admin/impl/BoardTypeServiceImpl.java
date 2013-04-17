/*    */ package com.htsoft.oa.service.admin.impl;
/*    */ 
/*    */ import com.htsoft.core.service.impl.BaseServiceImpl;
/*    */ import com.htsoft.oa.dao.admin.BoardTypeDao;
/*    */ import com.htsoft.oa.model.admin.BoardType;
/*    */ import com.htsoft.oa.service.admin.BoardTypeService;
/*    */ 
/*    */ public class BoardTypeServiceImpl extends BaseServiceImpl<BoardType>
/*    */   implements BoardTypeService
/*    */ {
/*    */   private BoardTypeDao dao;
/*    */ 
/*    */   public BoardTypeServiceImpl(BoardTypeDao dao)
/*    */   {
/* 21 */     super(dao);
/* 22 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.admin.impl.BoardTypeServiceImpl
 * JD-Core Version:    0.6.0
 */