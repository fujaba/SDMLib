package org.sdmlib.models.classes;

import java.beans.PropertyChangeSupport;

import org.sdmlib.StrUtil;
import org.sdmlib.serialization.PropertyChangeInterface;

public class DataType implements PropertyChangeInterface
{
   public static final String PROPERTY_VALUE = "value";

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
   public boolean setValue(String value)
   {
      if ( ! StrUtil.stringEquals(this.value, value))
      {
         String oldValue = this.value;
         this.value = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_VALUE, oldValue, value);
         return true;
      }
      return false;
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
   public static DataType ref(Clazz value){
      return new DataType(value.getFullName());
   }
   
   @Override
   public String toString()
   {
      return "DataType." + value.toUpperCase();
   }
   
   protected final PropertyChangeSupport listeners = new PropertyChangeSupport(this);

   @Override
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }
   
}
