/*
   Copyright (c) 2014 NeTH 
   
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
import java.util.Collections;

import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Enumeration;
import org.sdmlib.models.classes.Method;
import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.models.modelsets.StringList;

public class EnumerationSet extends SDMSet<Enumeration>
{


   public EnumerationPO hasEnumerationPO()
   {
      return new EnumerationPO(this.toArray(new Enumeration[this.size()]));
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.models.classes.Enumeration";
   }


   @SuppressWarnings("unchecked")
   public EnumerationSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Enumeration>)value);
      }
      else if (value != null)
      {
         this.add((Enumeration) value);
      }
      
      return this;
   }
   
   public EnumerationSet without(Enumeration value)
   {
      this.remove(value);
      return this;
   }

   public ArrayListSet getValueNames()
   {
      ArrayListSet result = new ArrayListSet();
      
      for (Enumeration obj : this)
      {
         result.addAll(obj.getValueNames());
      }
      
      return result;
   }

   public EnumerationSet hasValueNames(ArrayListSet value)
   {
      EnumerationSet result = new EnumerationSet();
      
      for (Enumeration obj : this)
      {
         if (value == obj.getValueNames())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public EnumerationSet withValueNames(ArrayListSet value)
   {
      for (Enumeration obj : this)
      {
         obj.setValueNames(value);
      }
      
      return this;
   }

   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Enumeration obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public EnumerationSet hasName(String value)
   {
      EnumerationSet result = new EnumerationSet();
      
      for (Enumeration obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public EnumerationSet hasName(String lower, String upper)
   {
      EnumerationSet result = new EnumerationSet();
      
      for (Enumeration obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public EnumerationSet withName(String value)
   {
      for (Enumeration obj : this)
      {
         obj.withName(value);
      }
      
      return this;
   }

   public ClassModelSet getClassModel()
   {
      ClassModelSet result = new ClassModelSet();
      
      for (Enumeration obj : this)
      {
         result.add(obj.getClassModel());
      }
      
      return result;
   }

   public EnumerationSet hasClassModel(Object value)
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
      
      EnumerationSet answer = new EnumerationSet();
      
      for (Enumeration obj : this)
      {
         if (neighbors.contains(obj.getClassModel()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public EnumerationSet withClassModel(ClassModel value)
   {
      for (Enumeration obj : this)
      {
         obj.withClassModel(value);
      }
      
      return this;
   }

   public MethodSet getMethods()
   {
      MethodSet result = new MethodSet();
      
      for (Enumeration obj : this)
      {
         result.addAll(obj.getMethods());
      }
      
      return result;
   }

   public EnumerationSet hasMethods(Object value)
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
      
      EnumerationSet answer = new EnumerationSet();
      
      for (Enumeration obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getMethods()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public EnumerationSet withMethods(Method value)
   {
      for (Enumeration obj : this)
      {
         obj.withMethods(value);
      }
      
      return this;
   }

   public EnumerationSet withoutMethods(Method value)
   {
      for (Enumeration obj : this)
      {
         obj.withoutMethods(value);
      }
      
      return this;
   }


   public static final EnumerationSet EMPTY_SET = new EnumerationSet().withReadOnly(true);
}
