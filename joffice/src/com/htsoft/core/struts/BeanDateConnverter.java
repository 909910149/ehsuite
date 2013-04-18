 package com.htsoft.core.struts;
 
 import org.apache.commons.beanutils.Converter;
 import org.apache.commons.lang.time.DateUtils;
 import org.apache.commons.logging.Log;
 import org.apache.commons.logging.LogFactory;
 
 public class BeanDateConnverter
   implements Converter
 {
   private static final Log logger = LogFactory.getLog(DateConverter.class);
   public static final String[] ACCEPT_DATE_FORMATS = { 
     "yyyy-MM-dd HH:mm:ss", 
     "yyyy-MM-dd" };
 
   public Object convert(Class arg0, Object value)
   {
     logger.debug("conver " + value + " to date object");
     String dateStr = value.toString();
     dateStr = dateStr.replace("T", " ");
     try {
       return DateUtils.parseDate(dateStr, ACCEPT_DATE_FORMATS);
     } catch (Exception ex) {
       logger.debug("parse date error:" + ex.getMessage());
     }
     return null;
   }
 }

