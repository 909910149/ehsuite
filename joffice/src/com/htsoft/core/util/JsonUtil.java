 package com.htsoft.core.util;
 
 import com.google.gson.Gson;
 import com.google.gson.GsonBuilder;
 import com.htsoft.core.json.SqlTimestampConverter;
 import com.htsoft.core.model.DynaModel;
 import com.htsoft.oa.util.FlowUtil;
 import flexjson.JSONSerializer;
 import flexjson.transformer.DateTransformer;
 import java.sql.Timestamp;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import java.util.Iterator;
 import java.util.List;
 import java.util.Map;
 import java.util.Map.Entry;
 import java.util.Set;
 import org.hibernate.collection.PersistentBag;
 import org.hibernate.proxy.map.MapProxy;
 
 public class JsonUtil
 {
   public static JSONSerializer getJSONSerializer(String[] dateFields)
   {
     JSONSerializer serializer = new JSONSerializer();
     serializer.exclude(new String[] { "*.class" });
     serializer.transform(new DateTransformer("yyyy-MM-dd HH:mm:ss"), dateFields);
     return serializer;
   }
 
   public static JSONSerializer getJSONSerializer() {
     JSONSerializer serializer = new JSONSerializer();
     serializer.exclude(new String[] { "*.class" });
     serializer.transform(new DateTransformer("yyyy-MM-dd HH:mm:ss"), new Class[] { Date.class });
     return serializer;
   }
 
   public static String listEntity2Json(List<Map<String, Object>> list, String entityName)
   {
     StringBuffer sb = new StringBuffer("[");
     if ((list != null) && (list.size() > 0)) {
       for (int i = 0; i < list.size(); i++) {
         Map m = (Map)list.get(i);
         String entStr = mapEntity2Json(m, entityName);
         sb.append(entStr).append(",");
       }
       sb.deleteCharAt(sb.length() - 1);
     }
     sb.append("]");
 
     return sb.toString();
   }
 
   public static String mapEntity2Json(Map<String, Object> mapData, String entityName)
   {
     StringBuffer sb = new StringBuffer("{");
     Gson gson = new GsonBuilder().serializeNulls().create();
     DynaModel dynaModel = (DynaModel)FlowUtil.DynaModelMap.get(entityName);
     Iterator entryIt = mapData.entrySet().iterator();
     int i = 0;
     while (entryIt.hasNext()) {
       Map.Entry entry = (Map.Entry)entryIt.next();
       String key = (String)entry.getKey();
       Object val = entry.getValue();
 
       if ((key.equals(entityName)) || 
         ((val instanceof MapProxy)) || 
         ((val instanceof Map)))
         continue;
       if ((val instanceof PersistentBag)) {
         int j = 0;
 
         String subEntityName = key.substring(0, key.length() - 1);
         sb.append(key).append(":[");
         Iterator bagIt = ((PersistentBag)val).iterator();
         while (bagIt.hasNext()) {
           if (j++ > 0) sb.append(",");
           Map map = (Map)bagIt.next();
           sb.append(mapEntity2Json(map, subEntityName));
         }
         sb.append("],");
       } else if ((val instanceof Date)) {
         String formatStyle = dynaModel.getFormat(key);
         if (formatStyle == null) {
           formatStyle = "yyyy-MM-dd HH:mm:ss";
         }
         SimpleDateFormat sdf = new SimpleDateFormat(formatStyle);
         String result = sdf.format((Date)val);
         sb.append(key).append(":").append(gson.toJson(result)).append(",");
       }
       else {
         sb.append(key).append(":").append(gson.toJson(val)).append(",");
       }
       i++;
     }
     if (i > 0) {
       sb.deleteCharAt(sb.length() - 1);
     }
     sb.append("}");
     return sb.toString();
   }
 
   public static Gson getGson()
   {
     GsonBuilder builder = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss");
     builder.registerTypeAdapter(Timestamp.class, new SqlTimestampConverter());
     Gson gson = builder.create();
     return gson;
   }
 }

