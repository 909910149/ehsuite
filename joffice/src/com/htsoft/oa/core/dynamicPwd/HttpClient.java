/*    */ package com.htsoft.oa.core.dynamicPwd;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStreamReader;
/*    */ import java.io.UnsupportedEncodingException;
/*    */ import java.net.HttpURLConnection;
/*    */ import java.net.URI;
/*    */ import java.net.URL;
/*    */ import java.net.URLEncoder;
/*    */ import java.util.Iterator;
/*    */ import java.util.LinkedList;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class HttpClient
/*    */ {
/*    */   private URI base_uri;
/* 18 */   private String encoding = "UTF8";
/*    */ 
/*    */   public HttpClient(URI base_uri) {
/* 21 */     this.base_uri = base_uri;
/*    */   }
/*    */ 
/*    */   public String getEncoding() {
/* 25 */     return this.encoding;
/*    */   }
/*    */ 
/*    */   public void setEncoding(String encoding) {
/* 29 */     this.encoding = encoding;
/*    */   }
/*    */ 
/*    */   protected String makeQueryString(Map<String, String> vars_dict) throws UnsupportedEncodingException {
/* 33 */     StringBuffer out = new StringBuffer();
/* 34 */     Iterator i = vars_dict.keySet().iterator();
/* 35 */     boolean first = true;
/* 36 */     while (i.hasNext()) {
/* 37 */       if (first)
/* 38 */         first = false;
/*    */       else {
/* 40 */         out.append("&");
/*    */       }
/* 42 */       String name = (String)i.next();
/* 43 */       String value = (String)vars_dict.get(name);
/* 44 */       out.append(
/* 45 */         URLEncoder.encode(name, getEncoding()) + 
/* 46 */         "=" + 
/* 47 */         URLEncoder.encode(value, getEncoding()));
/*    */     }
/* 49 */     return out.toString();
/*    */   }
/*    */   public YooeResponse call_api(String cmd_name, Map<String, String> vars_dict) throws IOException, Exception {
/* 52 */     String query_string = makeQueryString(vars_dict);
/* 53 */     URI full_uri = this.base_uri.resolve(URI.create(cmd_name + "/?" + query_string));
/* 54 */     URL url = full_uri.toURL();
/* 55 */     HttpURLConnection conn = (HttpURLConnection)url.openConnection();
/* 56 */     int status_code = conn.getResponseCode();
/* 57 */     if (status_code != 200) {
/* 58 */       String msg = "Error:" + status_code;
/* 59 */       throw new Exception(msg);
/*    */     }
/*    */ 
/* 62 */     BufferedReader buf = new BufferedReader(
/* 63 */       new InputStreamReader(conn.getInputStream()));
/* 64 */     List relines = new LinkedList();
/*    */     String in;
/* 66 */     while ((in = buf.readLine()) != null)
/*    */     {
/* 67 */       String line = in.trim();
/* 68 */       if (line.isEmpty()) continue; relines.add(line);
/*    */     }
/*    */ 
/* 71 */     return new YooeResponse(relines);
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.core.dynamicPwd.HttpClient
 * JD-Core Version:    0.6.0
 */