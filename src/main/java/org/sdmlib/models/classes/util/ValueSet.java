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
   
package org.sdmlib.models.classes.util;

import java.util.Collection;

import org.sdmlib.models.classes.DataType;
import org.sdmlib.models.classes.Value;
import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.models.modelsets.StringList;

public class ValueSet extends SDMSet<Value>
{
   @Override
   public String getEntryType()
   {
      return "org.sdmlib.models.classes.Value";
   }


   @SuppressWarnings("unchecked")
   public ValueSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Value>)value);
      }
      else if (value != null)
      {
         this.add((Value) value);
      }
      
      return this;
   }
   
   public ValueSet without(Value value)
   {
      this.remove(value);
      return this;
   }

   public StringList getInitialization()
   {
      StringList result = new StringList();
      
      for (Value obj : this)
      {
         result.add(obj.getInitialization());
      }
      
      return result;
   }

   public ValueSet hasInitialization(String value)
   {
      ValueSet result = new ValueSet();
      
      for (Value obj : this)
      {
         if (value.equals(obj.getInitialization()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ValueSet withInitialization(String value)
   {
      for (Value obj : this)
      {
         obj.setInitialization(value);
      }
      
      return this;
   }

   public DataTypeSet getType()
   {
      DataTypeSet result = new DataTypeSet();
      
      for (Value obj : this)
      {
         result.add(obj.getType());
      }
      
      return result;
   }

   public ValueSet hasType(DataType value)
   {
      ValueSet result = new ValueSet();
      
      for (Value obj : this)
      {
         if (value == obj.getType())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ValueSet withType(DataType value)
   {
      for (Value obj : this)
      {
         obj.setType(value);
      }
      
      return this;
   }

   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Value obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public ValueSet hasName(String value)
   {
      ValueSet result = new ValueSet();
      
      for (Value obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ValueSet withName(String value)
   {
      for (Value obj : this)
      {
         obj.withName(value);
      }
      
      return this;
   }

   public static final ValueSet EMPTY_SET = new ValueSet().withReadOnly(true);
   public ValueSet hasInitialization(String lower, String upper)
   {
      ValueSet result = new ValueSet();
      
      for (Value obj : this)
      {
         if (lower.compareTo(obj.getInitialization()) <= 0 && obj.getInitialization().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ValueSet hasName(String lower, String upper)
   {
      ValueSet result = new ValueSet();
      
      for (Value obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

}
