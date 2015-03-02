/*
   Copyright (c) 2013 zuendorf 
   
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

import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.DataType;
import org.sdmlib.models.classes.Enumeration;
import org.sdmlib.models.classes.Method;
import org.sdmlib.models.classes.Parameter;
import org.sdmlib.models.modelsets.DataTypeSet;
import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.models.modelsets.StringList;

public class MethodSet extends SDMSet<Method> implements org.sdmlib.models.modelsets.ModelSet
{
   public MethodSet withParameter(Parameter value)
   {
      for (Method obj : this)
      {
         obj.with(value);
      }
      
      return this;
   }

   public DataTypeSet getReturnType()
   {
      DataTypeSet result = new DataTypeSet();
      
      for (Method obj : this)
      {
         result.add(obj.getReturnType());
      }
      
      return result;
   }

   public MethodSet withReturnType(DataType value)
   {
      for (Method obj : this)
      {
         obj.setReturnType(value);
      }
      
      return this;
   }

   public ClazzSet getClazz()
   {
      ClazzSet result = new ClazzSet();
      
      for (Method obj : this)
      {
         result.add(obj.getClazz());
      }
      
      return result;
   }
   public MethodSet withClazz(Clazz value)
   {
      for (Method obj : this)
      {
         obj.with(value);
      }
      
      return this;
   }



   @Override
   public String toString()
   {
      StringList stringList = new StringList();
      
      for (Method elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.models.classes.Method";
   }


   public StringList getBody()
   {
      StringList result = new StringList();
      
      for (Method obj : this)
      {
         result.add(obj.getBody());
      }
      
      return result;
   }

   public MethodSet withBody(String value)
   {
      for (Method obj : this)
      {
         obj.setBody(value);
      }
      
      return this;
   }



   public MethodPO startModelPattern()
   {
      return new MethodPO(this.toArray(new Method[this.size()]));
   }


   @SuppressWarnings("unchecked")
   public MethodSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Method>)value);
      }
      else if (value != null)
      {
         this.add((Method) value);
      }
      
      return this;
   }
   
   public MethodSet without(Method value)
   {
      this.remove(value);
      return this;
   }

   public MethodPO hasMethodPO()
   {
      return new MethodPO(this.toArray(new Method[this.size()]));
   }
   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Method obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public MethodSet hasName(String value)
   {
      MethodSet result = new MethodSet();
      
      for (Method obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public MethodSet withName(String value)
   {
      for (Method obj : this)
      {
         obj.withName(value);
      }
      
      return this;
   }

   public ParameterSet getParameter()
   {
      ParameterSet result = new ParameterSet();
      
      for (Method obj : this)
      {
         result.addAll(obj.getParameter());
      }
      
      return result;
   }

   public MethodSet hasParameter(Object value)
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
      
      MethodSet answer = new MethodSet();
      
      for (Method obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getParameter()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public MethodSet withoutParameter(Parameter value)
   {
      for (Method obj : this)
      {
         obj.without(value);
      }
      
      return this;
   }
   @Override
   public MethodSet getNewInstance()
   {
      return new MethodSet();
   }
   public EnumerationSet getEnumeration()
   {
      EnumerationSet result = new EnumerationSet();
      
      for (Method obj : this)
      {
         result.add(obj.getEnumeration());
      }
      
      return result;
   }

   public MethodSet hasEnumeration(Object value)
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
      
      MethodSet answer = new MethodSet();
      
      for (Method obj : this)
      {
         if (neighbors.contains(obj.getEnumeration()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public MethodSet withEnumeration(Enumeration value)
   {
      for (Method obj : this)
      {
         obj.withEnumeration(value);
      }
      
      return this;
   }


   public static final MethodSet EMPTY_SET = new MethodSet().withReadOnly(true);
}




