package org.sdmlib.models.classes;

public enum DataType
{
   VOID("void"), 
   INT("int"), 
   LONG("long"), 
   DOUBLE("double"), 
   STRING("String"), 
   BOOLEAN("boolean"), 
   OBJECT("Object"), 
   REF("");
   
   private String value;
   DataType(String value){
      this.setValue(value);
   }
   public String getValue()
   {
      return value;
   }
   public void setValue(String value)
   {
      this.value = value;
   }
   public DataType withValue(String value){
      this.value = value;
      return this;
   }
   
   public static DataType ref(String value){
      return REF.withValue(value);
   }
   public static DataType ref(Class<?> value){
      return REF.withValue(value.getName());
   }
   //TODO might be a bug when the user change the packagename or the name of clazz
   public static DataType ref(Clazz value){
      return REF.withValue(value.getFullName());
   }
}
