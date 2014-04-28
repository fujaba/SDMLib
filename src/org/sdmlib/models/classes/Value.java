package org.sdmlib.models.classes;

import org.sdmlib.StrUtil;

public abstract class Value extends SDMLibClass
{
   public static final String PROPERTY_INITIALIZATION = "initialization";
   public static final String PROPERTY_NAME = "name";
   public static final String PROPERTY_TYPE = "type";
   
   protected String initialization = null;
   protected String name = null;
   protected DataType type = null;
   
   //==========================================================================
   
   public void setName(String value)
   {
      if ( ! StrUtil.stringEquals(this.name, value))
      {
         String oldValue = this.name;
         this.name = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_NAME, oldValue, value);
      }
   }
   

   public String getName()
   {
      return name;
   }


   public void setType(DataType value)
   {
      if (( this.type==null && value!=null) || (this.type!=null && this.type!=value))
      {
         DataType oldValue = this.type;
         this.type = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TYPE, oldValue, value);
      }
   }
   public Value withName(String string)
   {
      setName(string);
      return this;
   }

   
   public Value withType(DataType value)
   {
      setType(value);
      return this;
   } 
   
   public Value withInitialization(String value)
   {
      setInitialization(value);
      return this;
   }

   public DataType getType()
   {
      return type;
   }

   public String getInitialization()
   {
      return this.initialization;
   }

   public void setInitialization(String value)
   {
      this.initialization = value;
   }
}

