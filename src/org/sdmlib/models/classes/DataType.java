package org.sdmlib.models.classes;

public class DataType
{
   public static final DataType VOID = new DataType("void");
   public static final DataType INT = new DataType("int");
   public static final DataType LONG = new DataType("long");
   public static final DataType DOUBLE = new DataType("double");
   public static final DataType STRING = new DataType("String");
   public static final DataType BOOLEAN = new DataType("boolean");
   public static final DataType OBJECT = new DataType("Object");
   
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
      return new DataType(value);
   }
   public static DataType ref(Class<?> value){
      return new DataType(value.getName().replace("$", "."));
   }
   //TODO might be a bug when the user change the packagename or the name of clazz
   public static DataType ref(Clazz value){
      return new DataType(value.getFullName());
   }
   
   @Override
   public String toString()
   {
      return "DataType." + value.toUpperCase();
   }
}
