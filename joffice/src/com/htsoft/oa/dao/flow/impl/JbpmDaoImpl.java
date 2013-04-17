/*    */ package com.htsoft.oa.dao.flow.impl;
/*    */ 
/*    */ import com.htsoft.oa.dao.flow.JbpmDao;
/*    */ import java.io.ByteArrayOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.sql.PreparedStatement;
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.SQLException;
/*    */ import javax.annotation.Resource;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ import org.springframework.dao.DataAccessException;
/*    */ import org.springframework.jdbc.core.JdbcTemplate;
/*    */ import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
/*    */ import org.springframework.jdbc.core.support.AbstractLobStreamingResultSetExtractor;
/*    */ import org.springframework.jdbc.support.lob.DefaultLobHandler;
/*    */ import org.springframework.jdbc.support.lob.LobCreator;
/*    */ import org.springframework.jdbc.support.lob.LobHandler;
/*    */ import org.springframework.util.FileCopyUtils;
/*    */ 
/*    */ public class JbpmDaoImpl
/*    */   implements JbpmDao
/*    */ {
/* 40 */   private Log logger = LogFactory.getLog(JbpmDaoImpl.class);
/*    */ 
/*    */   @Resource
/*    */   private JdbcTemplate jdbcTemplate;
/*    */ 
/*    */   public String getDefXmlByDeployId(String deployId)
/*    */   {
/* 57 */     String sql = "select * from JBPM4_LOB where NAME_= ? and DEPLOYMENT_= ? ";
/*    */ 
/* 59 */     final LobHandler lobHandler = new DefaultLobHandler();
/* 60 */     final ByteArrayOutputStream contentOs = new ByteArrayOutputStream();
/* 61 */     String defXml = null;
/*    */     try {
/* 63 */       this.jdbcTemplate.query(
/* 64 */         sql, new Object[] { "process.jpdl.xml", deployId }, 
/* 65 */         new AbstractLobStreamingResultSetExtractor() {
/*    */         public void streamData(ResultSet rs) throws SQLException, IOException {
/* 67 */           FileCopyUtils.copy(lobHandler.getBlobAsBinaryStream(rs, "BLOB_VALUE_"), contentOs);
/*    */         }
/*    */       });
/* 71 */       defXml = new String(contentOs.toByteArray(), "UTF-8");
/*    */     } catch (Exception ex) {
/* 73 */       this.logger.debug(ex.getMessage());
/*    */     }
/* 75 */     return defXml;
/*    */   }
/*    */ 
/*    */   public void wirteDefXml(final String deployId,final String defXml)
/*    */   {
/*    */     try
/*    */     {
/* 84 */       LobHandler lobHandler = new DefaultLobHandler();
/* 85 */       final byte[] btyesXml = defXml.getBytes("UTF-8");
/* 86 */       String sql = "update JBPM4_LOB set BLOB_VALUE_=? where NAME_= ? and DEPLOYMENT_= ? ";
/* 87 */       this.jdbcTemplate.execute(sql, new AbstractLobCreatingPreparedStatementCallback(lobHandler)
/*    */       {
/*    */         protected void setValues(PreparedStatement ps, LobCreator lobCreator) throws SQLException, DataAccessException
/*    */         {
/* 91 */           lobCreator.setBlobAsBytes(ps, 1, btyesXml);
/* 92 */           ps.setString(2, "process.jpdl.xml");
/* 93 */           ps.setString(3, deployId);
/*    */         }
/*    */       });
/*    */     }
/*    */     catch (Exception localException)
/*    */     {
/*    */     }
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.flow.impl.JbpmDaoImpl
 * JD-Core Version:    0.6.0
 */