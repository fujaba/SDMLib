/*
   Copyright (c) 2014 christian 
   
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
   
package org.sdmlib.models.classes.util;

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.models.classes.EnumerationValue;
import java.util.Collection;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.models.classes.util.EnumerationSet;
import org.sdmlib.models.classes.Enumeration;

public class EnumerationValueSet extends SDMSet<EnumerationValue>
{


   public EnumerationValuePO hasEnumerationValuePO()
   {
      return new EnumerationValuePO(this.toArray(new EnumerationValue[this.size()]));
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.models.classes.EnumerationValue";
   }


   @SuppressWarnings("unchecked")
   public EnumerationValueSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<EnumerationValue>)value);
      }
      else if (value != null)
      {
         this.add((EnumerationValue) value);
      }
      
      return this;
   }
   
   public EnumerationValueSet without(EnumerationValue value)
   {
      this.remove(value);
      return this;
   }

   public StringList getValueName()
   {
      StringList result = new StringList();
      
      for (EnumerationValue obj : this)
      {
         result.add(obj.getValueName());
      }
      
      return result;
   }

   public EnumerationValueSet hasValueName(String value)
   {
      EnumerationValueSet result = new EnumerationValueSet();
      
      for (EnumerationValue obj : this)
      {
         if (value.equals(obj.getValueName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public EnumerationValueSet hasValueName(String lower, String upper)
   {
      EnumerationValueSet result = new EnumerationValueSet();
      
      for (EnumerationValue obj : this)
      {
         if (lower.compareTo(obj.getValueName()) <= 0 && obj.getValueName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public EnumerationValueSet withValueName(String value)
   {
      for (EnumerationValue obj : this)
      {
         obj.setValueName(value);
      }
      
      return this;
   }

   public StringList getName()
   {
      StringList result = new StringList();
      
      for (EnumerationValue obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public EnumerationValueSet hasName(String value)
   {
      EnumerationValueSet result = new EnumerationValueSet();
      
      for (EnumerationValue obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public EnumerationValueSet hasName(String lower, String upper)
   {
      EnumerationValueSet result = new EnumerationValueSet();
      
      for (EnumerationValue obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public EnumerationValueSet withName(String value)
   {
      for (EnumerationValue obj : this)
      {
         obj.withName(value);
      }
      
      return this;
   }

   public EnumerationSet getEnumeration()
   {
      EnumerationSet result = new EnumerationSet();
      
      for (EnumerationValue obj : this)
      {
         result.add(obj.getEnumeration());
      }
      
      return result;
   }

   public EnumerationValueSet hasEnumeration(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      EnumerationValueSet answer = new EnumerationValueSet();
      
      for (EnumerationValue obj : this)
      {
         if (neighbors.contains(obj.getEnumeration()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public EnumerationValueSet withEnumeration(Enumeration value)
   {
      for (EnumerationValue obj : this)
      {
         obj.withEnumeration(value);
      }
      
      return this;
   }

}
