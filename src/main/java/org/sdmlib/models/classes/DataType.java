package org.sdmlib.models.classes;

import java.util.concurrent.ConcurrentHashMap;

public class DataType
{

   private static ConcurrentHashMap<String, DataType> instances = new ConcurrentHashMap<>();

   public static synchronized DataType getInstance(String key)
   {
      DataType result = instances.get(key);

      if (result == null)
      {
         result = new DataType(key);
         instances.put(key, result);

      }
      return result;

   }

   public static final DataType VOID = getInstance("void");
   public static final DataType INT = getInstance("int");
   public static final DataType LONG = getInstance("long");
   public static final DataType DOUBLE = getInstance("double");
   public static final DataType STRING = getInstance("String");
   public static final DataType BOOLEAN = getInstance("boolean");
   public static final DataType OBJECT = getInstance("Object");

   private String value;

   private DataType ( String value )
   {
      this.value = value;
   }

   public String getValue()
   {
      return value;
   }

   public static DataType ref(String value)
   {
      return getInstance(value);
   }

   public static DataType ref(Class<?> value)
   {
      return getInstance(value.getName().replace("$", "."));
   }

   public static DataType ref(Clazz value)
   {
      return getInstance(value.getFullName());
   }

   public static DataType ref(Enumeration value)
   {
      return getInstance(value.getFullName());
   }

   @Override
   public String toString()
   {
      if ("void int long double String boolean Object".indexOf(this.value) >= 0)
      {
         return "DataType." + value.toUpperCase();
      }
      else
      {
         return "DataType.ref(\"" + value + "\")";
      }
   }

}
