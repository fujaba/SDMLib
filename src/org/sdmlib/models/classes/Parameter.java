package org.sdmlib.models.classes;

public class Parameter extends Value
{
   public static final String PROPERTY_METHOD = "method";
   private Method method = null;
   
   public Parameter()
   {
      
   }
   
   public Parameter(DataType type)
   {
      this.type = type;
   }

   public Method getMethod()
   {
      return this.method;
   }

   public boolean setMethod(Method value)
   {
      boolean changed = false;

      if (this.method != value)
      {
         Method oldValue = this.method;

         if (this.method != null)
         {
            this.method = null;
            oldValue.withoutParameter(this);
         }

         this.method = value;

         if (value != null)
         {
            value.withParameter(this);
         }

         getPropertyChangeSupport().firePropertyChange(PROPERTY_METHOD, oldValue, value);
         changed = true;
      }

      return changed;
   }

   public Parameter withClazz(Method value)
   {
      setMethod(value);
      return this;
   } 


   @Override
   public String toString()
   {
      return "" + name + " : " + type;
   }
   
   //==========================================================================
   public void removeYou()
   {
      setMethod(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }
   
   // OVERRIDE
   @Override
   public Parameter withName(String string)
   {
      setName(string);
      return this;
   }

   
   @Override
   public Parameter withType(DataType value)
   {
      setType(value);
      return this;
   } 
   
   @Override
   public Parameter withInitialization(String value){
      setInitialization(value);
      return this;
   }

}
