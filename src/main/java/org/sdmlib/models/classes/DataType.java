package org.sdmlib.models.classes;

import java.util.concurrent.ConcurrentHashMap;

public class DataType
{

   private static ConcurrentHashMap<Clazz, DataType> instances = new ConcurrentHashMap<>();

   public static synchronized DataType getInstance(String key)
   {
      DataType result = instances.get(key);

      if (result == null)
      {
         result = new DataType(key);
         instances.put(new Clazz(key), result);

      }
      return result;

   }
   
   public static synchronized DataType getInstance(Clazz key)
   {
	   return getInstance(key.getFullName());   
   }

   public static final DataType VOID = getInstance("void");
   public static final DataType INT = getInstance("int");
   public static final DataType LONG = getInstance("long");
   public static final DataType DOUBLE = getInstance("double");
   public static final DataType STRING = getInstance("String");
   public static final DataType BOOLEAN = getInstance("boolean");
   public static final DataType OBJECT = getInstance("Object");
   public static final DataType CHAR = getInstance("char");

   private String value;
   private Clazz clazzValue;

   private DataType ( String value )
   {
      this.value = value;
   }
   
   private DataType ( Clazz value )
   {
      this.clazzValue = value;
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
      return getInstance(value);
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
