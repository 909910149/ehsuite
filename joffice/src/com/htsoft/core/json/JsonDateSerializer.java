 package com.htsoft.core.json;
 
 import com.google.gson.JsonElement;
 import com.google.gson.JsonPrimitive;
 import com.google.gson.JsonSerializationContext;
 import com.google.gson.JsonSerializer;
 import java.lang.reflect.Type;
 import java.util.Date;
 
 public class JsonDateSerializer
   implements JsonSerializer<Date>
 {
   public JsonElement serialize(Date date, Type type, JsonSerializationContext context)
   {
     return new JsonPrimitive(Long.valueOf(date.getTime()));
   }
 }

