/*     */ package com.htsoft.test.system;
/*     */ 
/*     */ import com.htsoft.oa.dao.system.AppUserDao;
/*     */ import com.htsoft.oa.dao.system.DepartmentDao;
/*     */ import com.htsoft.oa.model.system.AppUser;
/*     */ import com.htsoft.oa.model.system.Department;
/*     */ import com.htsoft.test.BaseTestCase;
/*     */ import java.text.DecimalFormat;
/*     */ import java.util.Date;
/*     */ import javax.annotation.Resource;
/*     */ 
/*     */ public class AppUserDaoTestCase extends BaseTestCase
/*     */ {
/*     */ 
/*     */   @Resource
/*     */   private AppUserDao appUserDao;
/*     */ 
/*     */   @Resource
/*     */   private DepartmentDao departmentDao;
/*     */ 
/*     */   // ERROR //
/*     */   @org.junit.Test
/*     */   @org.springframework.test.annotation.Rollback(false)
/*     */   public void batchAdd()
/*     */     throws java.io.IOException
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield 28	com/htsoft/test/system/AppUserDaoTestCase:departmentDao	Lcom/htsoft/oa/dao/system/DepartmentDao;
/*     */     //   4: new 30	java/lang/Long
/*     */     //   7: dup
/*     */     //   8: lconst_1
/*     */     //   9: invokespecial 32	java/lang/Long:<init>	(J)V
/*     */     //   12: invokeinterface 35 2 0
/*     */     //   17: checkcast 41	com/htsoft/oa/model/system/Department
/*     */     //   20: astore_1
/*     */     //   21: aconst_null
/*     */     //   22: astore_2
/*     */     //   23: ldc 43
/*     */     //   25: astore_3
/*     */     //   26: new 45	java/io/FileInputStream
/*     */     //   29: dup
/*     */     //   30: aload_3
/*     */     //   31: invokespecial 47	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
/*     */     //   34: astore_2
/*     */     //   35: new 50	org/apache/poi/hssf/usermodel/HSSFWorkbook
/*     */     //   38: dup
/*     */     //   39: aload_2
/*     */     //   40: invokespecial 52	org/apache/poi/hssf/usermodel/HSSFWorkbook:<init>	(Ljava/io/InputStream;)V
/*     */     //   43: astore 4
/*     */     //   45: aload 4
/*     */     //   47: iconst_0
/*     */     //   48: invokevirtual 55	org/apache/poi/hssf/usermodel/HSSFWorkbook:getSheetAt	(I)Lorg/apache/poi/hssf/usermodel/HSSFSheet;
/*     */     //   51: astore 5
/*     */     //   53: aconst_null
/*     */     //   54: astore 6
/*     */     //   56: iconst_1
/*     */     //   57: istore 7
/*     */     //   59: ldc2_w 59
/*     */     //   62: invokestatic 61	java/lang/Long:valueOf	(J)Ljava/lang/Long;
/*     */     //   65: astore 8
/*     */     //   67: aload 5
/*     */     //   69: iload 7
/*     */     //   71: iinc 7 1
/*     */     //   74: invokevirtual 65	org/apache/poi/hssf/usermodel/HSSFSheet:getRow	(I)Lorg/apache/poi/hssf/usermodel/HSSFRow;
/*     */     //   77: astore 6
/*     */     //   79: aload 6
/*     */     //   81: ifnonnull +14 -> 95
/*     */     //   84: getstatic 71	java/lang/System:out	Ljava/io/PrintStream;
/*     */     //   87: ldc 77
/*     */     //   89: invokevirtual 79	java/io/PrintStream:println	(Ljava/lang/String;)V
/*     */     //   92: goto +306 -> 398
/*     */     //   95: aload 6
/*     */     //   97: iconst_0
/*     */     //   98: invokevirtual 84	org/apache/poi/hssf/usermodel/HSSFRow:getCell	(I)Lorg/apache/poi/hssf/usermodel/HSSFCell;
/*     */     //   101: astore 9
/*     */     //   103: aload 6
/*     */     //   105: iconst_1
/*     */     //   106: invokevirtual 84	org/apache/poi/hssf/usermodel/HSSFRow:getCell	(I)Lorg/apache/poi/hssf/usermodel/HSSFCell;
/*     */     //   109: astore 10
/*     */     //   111: aload 6
/*     */     //   113: iconst_2
/*     */     //   114: invokevirtual 84	org/apache/poi/hssf/usermodel/HSSFRow:getCell	(I)Lorg/apache/poi/hssf/usermodel/HSSFCell;
/*     */     //   117: astore 11
/*     */     //   119: aload 10
/*     */     //   121: ifnonnull +6 -> 127
/*     */     //   124: goto -57 -> 67
/*     */     //   127: aload 9
/*     */     //   129: invokevirtual 90	org/apache/poi/hssf/usermodel/HSSFCell:getRichStringCellValue	()Lorg/apache/poi/hssf/usermodel/HSSFRichTextString;
/*     */     //   132: invokevirtual 96	org/apache/poi/hssf/usermodel/HSSFRichTextString:toString	()Ljava/lang/String;
/*     */     //   135: astore 12
/*     */     //   137: aload 10
/*     */     //   139: invokevirtual 90	org/apache/poi/hssf/usermodel/HSSFCell:getRichStringCellValue	()Lorg/apache/poi/hssf/usermodel/HSSFRichTextString;
/*     */     //   142: invokevirtual 96	org/apache/poi/hssf/usermodel/HSSFRichTextString:toString	()Ljava/lang/String;
/*     */     //   145: astore 13
/*     */     //   147: ldc 102
/*     */     //   149: astore 14
/*     */     //   151: aload 11
/*     */     //   153: ifnull +31 -> 184
/*     */     //   156: aload 11
/*     */     //   158: invokevirtual 90	org/apache/poi/hssf/usermodel/HSSFCell:getRichStringCellValue	()Lorg/apache/poi/hssf/usermodel/HSSFRichTextString;
/*     */     //   161: invokevirtual 96	org/apache/poi/hssf/usermodel/HSSFRichTextString:toString	()Ljava/lang/String;
/*     */     //   164: astore 14
/*     */     //   166: aload 14
/*     */     //   168: ldc 104
/*     */     //   170: invokevirtual 106	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
/*     */     //   173: astore 15
/*     */     //   175: aload 15
/*     */     //   177: iconst_0
/*     */     //   178: aaload
/*     */     //   179: astore 14
/*     */     //   181: goto +25 -> 206
/*     */     //   184: new 112	java/lang/StringBuilder
/*     */     //   187: dup
/*     */     //   188: aload 13
/*     */     //   190: invokestatic 114	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
/*     */     //   193: invokespecial 117	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*     */     //   196: ldc 118
/*     */     //   198: invokevirtual 120	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   201: invokevirtual 124	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*     */     //   204: astore 14
/*     */     //   206: getstatic 71	java/lang/System:out	Ljava/io/PrintStream;
/*     */     //   209: new 112	java/lang/StringBuilder
/*     */     //   212: dup
/*     */     //   213: aload 12
/*     */     //   215: invokestatic 114	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
/*     */     //   218: invokespecial 117	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*     */     //   221: ldc 125
/*     */     //   223: invokevirtual 120	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   226: aload 13
/*     */     //   228: invokevirtual 120	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   231: ldc 125
/*     */     //   233: invokevirtual 120	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   236: aload 14
/*     */     //   238: invokevirtual 120	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   241: invokevirtual 124	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*     */     //   244: invokevirtual 79	java/io/PrintStream:println	(Ljava/lang/String;)V
/*     */     //   247: new 127	com/htsoft/oa/model/system/AppUser
/*     */     //   250: dup
/*     */     //   251: invokespecial 129	com/htsoft/oa/model/system/AppUser:<init>	()V
/*     */     //   254: astore 15
/*     */     //   256: aload 15
/*     */     //   258: aload 8
/*     */     //   260: dup
/*     */     //   261: invokevirtual 130	java/lang/Long:longValue	()J
/*     */     //   264: lconst_1
/*     */     //   265: ladd
/*     */     //   266: invokestatic 61	java/lang/Long:valueOf	(J)Ljava/lang/Long;
/*     */     //   269: astore 8
/*     */     //   271: invokevirtual 134	com/htsoft/oa/model/system/AppUser:setUserId	(Ljava/lang/Long;)V
/*     */     //   274: aload 15
/*     */     //   276: aload 13
/*     */     //   278: invokevirtual 138	com/htsoft/oa/model/system/AppUser:setUsername	(Ljava/lang/String;)V
/*     */     //   281: aload 15
/*     */     //   283: aload 12
/*     */     //   285: invokevirtual 141	com/htsoft/oa/model/system/AppUser:setFullname	(Ljava/lang/String;)V
/*     */     //   288: aload 15
/*     */     //   290: ldc 144
/*     */     //   292: invokevirtual 146	com/htsoft/oa/model/system/AppUser:setPassword	(Ljava/lang/String;)V
/*     */     //   295: aload 15
/*     */     //   297: aload 14
/*     */     //   299: invokevirtual 149	com/htsoft/oa/model/system/AppUser:setEmail	(Ljava/lang/String;)V
/*     */     //   302: aload 15
/*     */     //   304: new 152	java/lang/Short
/*     */     //   307: dup
/*     */     //   308: ldc 154
/*     */     //   310: invokespecial 156	java/lang/Short:<init>	(Ljava/lang/String;)V
/*     */     //   313: invokevirtual 157	com/htsoft/oa/model/system/AppUser:setTitle	(Ljava/lang/Short;)V
/*     */     //   316: aload 15
/*     */     //   318: new 152	java/lang/Short
/*     */     //   321: dup
/*     */     //   322: ldc 161
/*     */     //   324: invokespecial 156	java/lang/Short:<init>	(Ljava/lang/String;)V
/*     */     //   327: invokevirtual 163	com/htsoft/oa/model/system/AppUser:setDelFlag	(Ljava/lang/Short;)V
/*     */     //   330: aload 15
/*     */     //   332: aload_1
/*     */     //   333: invokevirtual 166	com/htsoft/oa/model/system/AppUser:setDepartment	(Lcom/htsoft/oa/model/system/Department;)V
/*     */     //   336: aload 15
/*     */     //   338: new 152	java/lang/Short
/*     */     //   341: dup
/*     */     //   342: ldc 154
/*     */     //   344: invokespecial 156	java/lang/Short:<init>	(Ljava/lang/String;)V
/*     */     //   347: invokevirtual 170	com/htsoft/oa/model/system/AppUser:setStatus	(Ljava/lang/Short;)V
/*     */     //   350: aload 15
/*     */     //   352: new 173	java/util/Date
/*     */     //   355: dup
/*     */     //   356: invokespecial 175	java/util/Date:<init>	()V
/*     */     //   359: invokevirtual 176	com/htsoft/oa/model/system/AppUser:setAccessionTime	(Ljava/util/Date;)V
/*     */     //   362: aload_0
/*     */     //   363: getfield 180	com/htsoft/test/system/AppUserDaoTestCase:appUserDao	Lcom/htsoft/oa/dao/system/AppUserDao;
/*     */     //   366: aload 15
/*     */     //   368: invokeinterface 182 2 0
/*     */     //   373: pop
/*     */     //   374: goto -307 -> 67
/*     */     //   377: astore_3
/*     */     //   378: aload_3
/*     */     //   379: invokevirtual 188	java/lang/Exception:printStackTrace	()V
/*     */     //   382: aload_2
/*     */     //   383: invokevirtual 193	java/io/InputStream:close	()V
/*     */     //   386: goto +16 -> 402
/*     */     //   389: astore 16
/*     */     //   391: aload_2
/*     */     //   392: invokevirtual 193	java/io/InputStream:close	()V
/*     */     //   395: aload 16
/*     */     //   397: athrow
/*     */     //   398: aload_2
/*     */     //   399: invokevirtual 193	java/io/InputStream:close	()V
/*     */     //   402: return
/*     */     //
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   23	377	377	java/lang/Exception
/*     */     //   23	382	389	finally
/*     */   }
/*     */ 
/*     */   public void add()
/*     */   {
/* 104 */     Department department = (Department)this.departmentDao.get(new Long(1L));
/* 105 */     DecimalFormat df = new DecimalFormat("000");
/* 106 */     for (int i = 20; i <= 120; i++) {
/* 107 */       AppUser appUser = new AppUser();
/* 108 */       appUser.setTitle(new Short("1"));
/* 109 */       appUser.setDelFlag(new Short("0"));
/* 110 */       appUser.setEmail("user" + i + "@jsoft.com");
/* 111 */       appUser.setUsername("user" + i);
/* 112 */       appUser.setEducation("大学本科");
/* 113 */       appUser.setMobile("135803XX" + df.format(new Double(i)));
/* 114 */       appUser.setPassword("9uCh4qxBlFqap/+KiqoM68EqO8yYGpKa1c+BCgkOEa4=");
/* 115 */       appUser.setDepartment(department);
/* 116 */       appUser.setStatus(new Short("1"));
/* 117 */       appUser.setAccessionTime(new Date());
/* 118 */       appUser.setFullname("张三" + i);
/*     */ 
/* 121 */       this.appUserDao.save(appUser);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void addDep()
/*     */   {
/* 136 */     Department dep = new Department();
/* 137 */     dep.setDepName("Root Dep");
/* 138 */     dep.setDepLevel(Integer.valueOf(1));
/*     */ 
/* 140 */     this.departmentDao.save(dep);
/*     */   }
/*     */ 
/*     */   public void bacthAdd()
/*     */   {
/* 145 */     Department dep = (Department)this.departmentDao.get(Long.valueOf(1L));
/* 146 */     for (int i = 101; i < 102; i++) {
/* 147 */       AppUser au = new AppUser();
/*     */ 
/* 149 */       au.setTitle(Short.valueOf((short) 1));
/* 150 */       au.setUsername("user" + i);
/* 151 */       au.setPassword("1");
/* 152 */       au.setFullname("李海" + i);
/* 153 */       au.setAddress("testAddress");
/* 154 */       au.setEducation("test");
/* 155 */       au.setEmail("user" + i + "@htsoft.com");
/* 156 */       au.setAccessionTime(new Date());
/* 157 */       au.setPhoto("photo");
/* 158 */       au.setZip("00003");
/* 159 */       au.setStatus(Short.valueOf((short) 1));
/* 160 */       au.setFax("020-003034034");
/*     */ 
/* 164 */       this.appUserDao.save(au);
/*     */     }
/*     */   }
/*     */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.system.AppUserDaoTestCase
 * JD-Core Version:    0.6.0
 */