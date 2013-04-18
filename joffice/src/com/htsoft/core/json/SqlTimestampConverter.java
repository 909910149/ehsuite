 package com.htsoft.core.json;
 
 import com.google.gson.JsonElement;
 import com.google.gson.JsonPrimitive;
 import com.google.gson.JsonSerializationContext;
 import com.google.gson.JsonSerializer;
 import java.lang.reflect.Type;
 import java.sql.Timestamp;
 import java.text.SimpleDateFormat;
 
 public class SqlTimestampConverter
   implements JsonSerializer<Timestamp>
 {
   private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 
   public JsonElement serialize(Timestamp src, Type type, JsonSerializationContext context)
   {
     return new JsonPrimitive(sdf.format(src));
   }
 }

