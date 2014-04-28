package org.sdmlib.serialization.graph;

public class Attribute
{
   private String key;
   private String className;
   private String value;

   public String getKey()
   {
      return key;
   }

   public Attribute withKey(String key)
   {
      this.key = key;
      return this;
   }

   public String getClazz()
   {
      return className;
   }

   public Attribute withClazz(String clazz)
   {
      this.className = clazz;
      return this;
   }

   public String getValue()
   {
      return value;
   }

   public Attribute withValue(String value)
   {
      this.value = value;
      return this;
   }

   public String getValue(String typ, boolean shortName)
   {
      if (typ.equals(GraphIdMap.CLASS))
      {
         if (!shortName || className == null || className.lastIndexOf(".") < 0)
         {
            return className;
         }
         return className.substring(className.lastIndexOf(".") + 1);
      }
      return value;
   }
}
