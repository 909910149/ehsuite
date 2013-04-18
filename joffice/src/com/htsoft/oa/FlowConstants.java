/*    */ package com.htsoft.oa;
/*    */ 
/*    */ import java.util.Date;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class FlowConstants
/*    */ {
/* 10 */   public static Map<Object, Class> FIELD_TYPE_MAP = new HashMap();
/*    */   public static final String SIGN_VOTE_TYPE = "signVoteType";
/*    */ 
/*    */   static
/*    */   {
/* 12 */     FIELD_TYPE_MAP.put("java.lang.String", String.class);
/* 13 */     FIELD_TYPE_MAP.put("java.lang.Long", Long.class);
/* 14 */     FIELD_TYPE_MAP.put("java.lang.Integer", Integer.class);
/* 15 */     FIELD_TYPE_MAP.put("java.lang.Short", Short.class);
/* 16 */     FIELD_TYPE_MAP.put("java.lang.Float", Float.class);
/* 17 */     FIELD_TYPE_MAP.put("java.lang.Double", Double.class);
/* 18 */     FIELD_TYPE_MAP.put("java.util.Date", Date.class);
/*    */ 
/* 20 */     FIELD_TYPE_MAP.put("String", String.class);
/* 21 */     FIELD_TYPE_MAP.put("Long", Long.class);
/* 22 */     FIELD_TYPE_MAP.put("Integer", Integer.class);
/* 23 */     FIELD_TYPE_MAP.put("Short", Short.class);
/* 24 */     FIELD_TYPE_MAP.put("Float", Float.class);
/* 25 */     FIELD_TYPE_MAP.put("Double", Double.class);
/* 26 */     FIELD_TYPE_MAP.put("Date", Date.class);
/*    */ 
/* 28 */     FIELD_TYPE_MAP.put("String", String.class);
/* 29 */     FIELD_TYPE_MAP.put("long", Long.class);
/* 30 */     FIELD_TYPE_MAP.put("int", Integer.class);
/* 31 */     FIELD_TYPE_MAP.put("short", Short.class);
/* 32 */     FIELD_TYPE_MAP.put("float", Float.class);
/* 33 */     FIELD_TYPE_MAP.put("double", Double.class);
/*    */ 
/* 35 */     FIELD_TYPE_MAP.put("tinyint", Integer.class);
/* 36 */     FIELD_TYPE_MAP.put("smallint", Integer.class);
/* 37 */     FIELD_TYPE_MAP.put("mediumint", Integer.class);
/* 38 */     FIELD_TYPE_MAP.put("int", Integer.class);
/* 39 */     FIELD_TYPE_MAP.put("bigint", Long.class);
/*    */ 
/* 41 */     FIELD_TYPE_MAP.put("float", Float.class);
/* 42 */     FIELD_TYPE_MAP.put("double", Double.class);
/*    */ 
/* 44 */     FIELD_TYPE_MAP.put("char", String.class);
/* 45 */     FIELD_TYPE_MAP.put("varchar", String.class);
/* 46 */     FIELD_TYPE_MAP.put("tinytext ", String.class);
/* 47 */     FIELD_TYPE_MAP.put("text", String.class);
/* 48 */     FIELD_TYPE_MAP.put("mediumtext", String.class);
/* 49 */     FIELD_TYPE_MAP.put("longtext", String.class);
/*    */ 
/* 51 */     FIELD_TYPE_MAP.put("blob", String.class);
/* 52 */     FIELD_TYPE_MAP.put("longblob", String.class);
/*    */ 
/* 54 */     FIELD_TYPE_MAP.put("date", Date.class);
/* 55 */     FIELD_TYPE_MAP.put("time", Date.class);
/* 56 */     FIELD_TYPE_MAP.put("datetime", Date.class);
/* 57 */     FIELD_TYPE_MAP.put("timestamp", Date.class);
/*    */ 
/* 59 */     FIELD_TYPE_MAP.put("TINYINT", Integer.class);
/* 60 */     FIELD_TYPE_MAP.put("SMALLINT", Integer.class);
/* 61 */     FIELD_TYPE_MAP.put("MEDIUMINT", Integer.class);
/* 62 */     FIELD_TYPE_MAP.put("INT", Integer.class);
/* 63 */     FIELD_TYPE_MAP.put("BIGINT", Long.class);
/*    */ 
/* 65 */     FIELD_TYPE_MAP.put("FLOAT", Float.class);
/* 66 */     FIELD_TYPE_MAP.put("DOUBLE", Double.class);
/*    */ 
/* 68 */     FIELD_TYPE_MAP.put("CHAR", String.class);
/* 69 */     FIELD_TYPE_MAP.put("VARCHAR", String.class);
/* 70 */     FIELD_TYPE_MAP.put("TINYTEXT ", String.class);
/* 71 */     FIELD_TYPE_MAP.put("TEXT", String.class);
/* 72 */     FIELD_TYPE_MAP.put("MEDIUMTEXT", String.class);
/* 73 */     FIELD_TYPE_MAP.put("LONGTEXT", String.class);
/*    */ 
/* 75 */     FIELD_TYPE_MAP.put("BLOB", String.class);
/* 76 */     FIELD_TYPE_MAP.put("LONGBLOB", String.class);
/*    */ 
/* 78 */     FIELD_TYPE_MAP.put("DATE", Date.class);
/* 79 */     FIELD_TYPE_MAP.put("TIME", Date.class);
/* 80 */     FIELD_TYPE_MAP.put("DATETIME", Date.class);
/* 81 */     FIELD_TYPE_MAP.put("TIMESTAMP", Date.class);
/*    */ 
/* 83 */     FIELD_TYPE_MAP.put("decimal", Double.class);
/* 84 */     FIELD_TYPE_MAP.put("DECIMAL", Double.class);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.FlowConstants
 * JD-Core Version:    0.6.0
 */