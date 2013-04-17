/*    */ package com.htsoft.test.system;
/*    */ 
/*    */ import java.io.FileInputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.PrintStream;
/*    */ import org.apache.poi.hssf.usermodel.HSSFCell;
/*    */ import org.apache.poi.hssf.usermodel.HSSFRichTextString;
/*    */ import org.apache.poi.hssf.usermodel.HSSFRow;
/*    */ import org.apache.poi.hssf.usermodel.HSSFSheet;
/*    */ import org.apache.poi.hssf.usermodel.HSSFWorkbook;
/*    */ 
/*    */ public class AppUserTest
/*    */ {
/*    */   public static void main(String[] args)
/*    */     throws IOException
/*    */   {
/* 23 */     String userFile = "C:/dev/workspace/joffice/metedata/data/users.xls";
/* 24 */     InputStream myxls = new FileInputStream(userFile);
/*    */ 
/* 26 */     HSSFWorkbook workbook = new HSSFWorkbook(myxls);
/*    */ 
/* 28 */     HSSFSheet sheet = workbook.getSheetAt(0);
/*    */ 
/* 30 */     HSSFRow row = null;
/* 31 */     int i = 1;
/*    */     while (true) {
/* 33 */       row = sheet.getRow(i++);
/* 34 */       if (row == null) {
/* 35 */         System.out.println("row==null");
/* 36 */         break;
/*    */       }
/* 38 */       HSSFCell fullnameCell = row.getCell(0);
/* 39 */       HSSFCell accountCell = row.getCell(1);
/* 40 */       HSSFCell emailCell = row.getCell(2);
/*    */ 
/* 42 */       if (accountCell == null) {
/*    */         continue;
/*    */       }
/* 45 */       String fullname = fullnameCell.getRichStringCellValue().toString();
/* 46 */       String account = accountCell.getRichStringCellValue().toString();
/* 47 */       String email = "";
/*    */ 
/* 49 */       if (emailCell != null) {
/* 50 */         email = emailCell.getRichStringCellValue().toString();
/* 51 */         String[] mails = email.split("[ ]");
/* 52 */         email = mails[0];
/*    */       } else {
/* 54 */         email = account + "@unkown.com";
/*    */       }
/*    */ 
/* 59 */       System.out.println(fullname + " :" + account + " :" + email);
/*    */     }
/*    */ 
/* 63 */     myxls.close();
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.system.AppUserTest
 * JD-Core Version:    0.6.0
 */