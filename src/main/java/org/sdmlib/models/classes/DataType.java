package org.sdmlib.models.classes;

public class DataType implements Type
{
   public static final DataType VOID = new DataType("void");
   public static final DataType INT = new DataType("int");
   public static final DataType LONG = new DataType("long");
   public static final DataType DOUBLE = new DataType("double");
   public static final DataType STRING = new DataType("String");
   public static final DataType BOOLEAN = new DataType("boolean");
   public static final DataType OBJECT = new DataType("java.lang.Object");
   public static final DataType CHAR = new DataType("char");

   protected Clazz clazzValue;

   DataType ( String value )
   {
      this.clazzValue = new Clazz(value);
   }
   
   DataType ( Clazz value )
   {
      this.clazzValue = value;
   }
   
   public Clazz getClazz() {
	   return clazzValue;
   }

   public String getValue() {
	   if(this.clazzValue==null) {
		   return null;
	   }
	   return this.clazzValue.getName();
   }


   public boolean equals(Object obj) {
	   if(!(obj instanceof DataType)) {
		   return false;
	   }
	   DataType other = (DataType)obj;
	   if(this.getValue()==null) {
		   return other.getValue()==null;
	   }
	   return getValue().equals(other.getValue());
   }

   public static DataType ref(String value)
   {
      return new DataType(value);
   }
   public static DataType ref(String value, boolean external) {
	   return new DataType(new Clazz(value).withExternal(external));
   }
   public static DataType ref(Class<?> value)
   {
      return new DataType(value.getName().replace("$", "."));
   }

   public static DataType ref(Class<?> value, boolean external) {
	   Clazz clazz = new Clazz(value.getName().replace("$", ".")).withExternal(external);
	   return new DataType(clazz);
   }
   
   public static DataType ref(Clazz value)
   {
      return new DataType(value);
   }

   public static DataType ref(Enumeration value)
   {
      return new DataType(value.getFullName());
   }

   public static DataType ref(Type value)
   {
      if (value instanceof Enumeration)
      {
         return ref((Enumeration) value);
      }
      else if (value instanceof Clazz)
      {
         return ref((Clazz) value);
      }
      else
      {
         return (DataType) value;
      }
   }

   @Override
   public String toString()
   {
      if ("void int long double String boolean Object".indexOf(this.getValue()) >= 0)
      {
         return "DataType." + this.getValue().toUpperCase();
      }
      else
      {
         return "DataType.ref(\"" + this.getValue() + "\")";
      }
   }
}
