/*
   Copyright (c) 2014 zuendorf 
   
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
   
package org.sdmlib.models.objects.util;

import java.util.Collection;

import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.objects.GenericAttribute;
import org.sdmlib.models.objects.GenericObject;

import de.uniks.networkparser.list.SimpleSet;

public class GenericAttributeSet extends SimpleSet<GenericAttribute>
{
   public StringList getName()
   {
      StringList result = new StringList();
      
      for (GenericAttribute obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public GenericAttributeSet withName(String value)
   {
      for (GenericAttribute obj : this)
      {
         obj.withName(value);
      }
      
      return this;
   }

   public StringList getValue()
   {
      StringList result = new StringList();
      
      for (GenericAttribute obj : this)
      {
         result.add(obj.getValue());
      }
      
      return result;
   }

   public GenericAttributeSet withValue(String value)
   {
      for (GenericAttribute obj : this)
      {
         obj.withValue(value);
      }
      
      return this;
   }

   public GenericObjectSet getOwner()
   {
      GenericObjectSet result = new GenericObjectSet();
      
      for (GenericAttribute obj : this)
      {
         result.add(obj.getOwner());
      }
      
      return result;
   }
   public GenericAttributeSet withOwner(GenericObject value)
   {
      for (GenericAttribute obj : this)
      {
         obj.withOwner(value);
      }
      
      return this;
   }



   public String toString()
   {
      StringList stringList = new StringList();
      
      for (GenericAttribute elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }




   public String getEntryType()
   {
      return "org.sdmlib.models.objects.GenericAttribute";
   }


   public GenericAttributePO startModelPattern()
   {
      return new GenericAttributePO(this.toArray(new GenericAttribute[this.size()]));
   }


   public GenericAttributeSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.withList((Collection<?>)value);
      }
      else if (value != null)
      {
         this.add((GenericAttribute) value);
      }
      
      return this;
   }
   
   public GenericAttributeSet without(GenericAttribute value)
   {
      this.remove(value);
      return this;
   }



   public GenericAttributePO hasGenericAttributePO()
   {
      return new GenericAttributePO(this.toArray(new GenericAttribute[this.size()]));
   }

   public static final GenericAttributeSet EMPTY_SET = new GenericAttributeSet().withFlag(GenericAttributeSet.READONLY);
   public GenericAttributeSet hasName(String value)
   {
      GenericAttributeSet result = new GenericAttributeSet();
      
      for (GenericAttribute obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public GenericAttributeSet hasName(String lower, String upper)
   {
      GenericAttributeSet result = new GenericAttributeSet();
      
      for (GenericAttribute obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public GenericAttributeSet hasValue(String value)
   {
      GenericAttributeSet result = new GenericAttributeSet();
      
      for (GenericAttribute obj : this)
      {
         if (value.equals(obj.getValue()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public GenericAttributeSet hasValue(String lower, String upper)
   {
      GenericAttributeSet result = new GenericAttributeSet();
      
      for (GenericAttribute obj : this)
      {
         if (lower.compareTo(obj.getValue()) <= 0 && obj.getValue().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

}















