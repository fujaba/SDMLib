/*   Copyright (c) 2012 zuendorf 

   Permission is hereby granted, free of charge, to any person obtaining a copy of this software 
   and associated documentation files (the "Software"), to deal in the Software without restriction, 
   including without limitation the rights to use, copy, modify, merge, publish, distribute, 
   sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is 
   furnished to do so, subject to the following conditions: 

   The above copyright notice and this permission notice shall be included in all copies or 
   substantial portions of the Software. 

   The Software shall be used for Good, not Evil. 

   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
   BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
   DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package org.sdmlib.models.classes;


import org.sdmlib.StrUtil;
import org.sdmlib.models.classes.logic.GenAttribute;

public class Attribute extends SDMLibClass
{
   public static final String SIMPLE = "simple";
   public static final String COMPLEX = "complex";
   public static final String PROPERTY_CLAZZ = "clazz";
   public static final String PROPERTY_INITIALIZATION = "initialization";
   public static final String PROPERTY_NAME = "name";
   public static final String PROPERTY_TYPE = "type";
   
   private Clazz clazz = null;
   private GenAttribute generator;
   private String initialization = null;
   private String name = null;
   private DataType type = null;
   
   public Clazz getClazz()
   {
      return this.clazz;
   }

   public boolean setClazz(Clazz value)
   {
      boolean changed = false;

      if (this.clazz != value)
      {
         Clazz oldValue = this.clazz;

         if (this.clazz != null)
         {
            this.clazz = null;
            oldValue.withoutAttributes(this);
         }

         this.clazz = value;

         if (value != null)
         {
            value.withAttributes(this);
         }

         getPropertyChangeSupport().firePropertyChange(PROPERTY_CLAZZ, oldValue, value);
         changed = true;
      }

      return changed;
   }

   public Attribute withClazz(Clazz value)
   {
      setClazz(value);
      return this;
   } 

   public void setGenerator(GenAttribute value)
   {
      if (this.generator != value)
      {
         GenAttribute oldValue = this.generator;
         if (this.generator != null)
         {
            this.generator = null;
            oldValue.setModel(null);
         }
         this.generator = value;
         if (value != null)
         {
            value.setModel(this);
         }
      }
   }
   
   public GenAttribute getGenerator(){
      if(generator==null){
         this.setGenerator(new GenAttribute());
      }
      return generator;
   }
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

   public Attribute withName(String string)
   {
      setName(string);
      return this;
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
   
   public Attribute withType(DataType value)
   {
      setType(value);
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
   public Attribute withInitialization(String value)
   {
      setInitialization(value);
      return this;
   }

   @Override
   public String toString()
   {
   	// StringBuilder _ = new StringBuilder();
   	// _.append(" ").append(this.getInitialization());
      // _.append(" ").append(this.getType());
      //_.append(" ").append(this.getName());
      return "" + name + " : " + type;
   }
   
   //==========================================================================

   public void removeYou()
   {
      setClazz(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   public Clazz createClazz()
   {
      Clazz value = new Clazz();
      withClazz(value);
      return value;
   } 
}

