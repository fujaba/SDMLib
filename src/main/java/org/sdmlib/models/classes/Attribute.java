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

import org.sdmlib.models.classes.util.AttributeSet;
import java.util.LinkedHashSet;

public class Attribute extends Value
{
   public static final String PROPERTY_CLAZZ = "clazz";
   public static final AttributeSet EMPTY_SET = new AttributeSet().withReadonly(true);

   private Clazz clazz = null;
   private Visibility visibility = Visibility.PRIVATE;
   
   public Attribute(String name, DataType type)
   {
      this.name = name; 
      this.type = type;
   }

   public Clazz getClazz()
   {
      return this.clazz;
   }

   public boolean setClazz(Clazz value)
   {
      if (this.clazz != value)
      {
         Clazz oldValue = this.clazz;

         if (this.clazz != null)
         {
            this.clazz = null;
            oldValue.without(this);
         }

         this.clazz = value;

         if (value != null)
         {
            value.with(this);
         }

         getPropertyChangeSupport().firePropertyChange(PROPERTY_CLAZZ, oldValue, value);
         return true;
      }

      return false;
   }

   public Attribute with(Clazz value)
   {
      setClazz(value);
      return this;
   } 


   @Override
   public String toString()
   {
      //      StringBuilder _ = new StringBuilder();
      //      s.append(" ").append(this.getInitialization());
      //      s.append(" ").append(this.getName());
      return "" + name + " : " + type;
   }
   
   //==========================================================================
   @Override
   public void removeYou()
   {
      super.removeYou();
      setClazz(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }
   
   // OVERRIDE
   @Override
   public Attribute withName(String string)
   {
      setName(string);
      return this;
   }

   
   @Override
   public Attribute with(DataType value)
   {
      setType(value);
      return this;
   } 
   
   @Override
   public Attribute withInitialization(String value){
      setInitialization(value);
      return this;
   }

   public Visibility getVisibility()
   {
      return visibility;
   }

   public Attribute with(Visibility... visibility)
   {
      if(visibility==null){
         return this;
      }
      
      if(visibility.length==1){
         this.visibility = visibility[0];
      }
      this.visibility = Visibility.ref(visibility);
      return this;
   }

   Attribute withClazz(Clazz value)
   {
      setClazz(value);
      return this;
   } 

   Clazz createClazz()
   {
      Clazz value = new Clazz(null);
      withClazz(value);
      return value;
   } 
}

