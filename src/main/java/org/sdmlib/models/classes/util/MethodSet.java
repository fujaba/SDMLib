/*
   Copyright (c) 2016 zuendorf
   
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
import org.sdmlib.models.classes.Method;
import java.util.Collection;
import de.uniks.networkparser.interfaces.Condition;
import de.uniks.networkparser.graph.DataType;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.ObjectSet;
import java.util.Collections;
import org.sdmlib.models.classes.util.ParameterSet;
import org.sdmlib.models.classes.Parameter;

public class MethodSet extends SDMSet<Method>
{

   public static final MethodSet EMPTY_SET = new MethodSet().withFlag(MethodSet.READONLY);


   public String getEntryType()
   {
      return "org.sdmlib.models.classes.Method";
   }


   @SuppressWarnings("unchecked")
   public MethodSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
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

   @Override
   public MethodSet filter(Condition<Method> newValue) {
      MethodSet filterList = new MethodSet();
      filterItems(filterList, newValue);
      return filterList;
   }

   /**
    * Loop through the current set of Method objects and collect a list of the returnType attribute values. 
    * 
    * @return List of de.uniks.networkparser.graph.DataType objects reachable via returnType attribute
    */
   public DataTypeSet getReturnType()
   {
      DataTypeSet result = new DataTypeSet();
      
      for (Method obj : this)
      {
         result.add(obj.getReturnType());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Method objects and collect those Method objects where the returnType attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Method objects that match the parameter
    */
   public MethodSet filterReturnType(DataType value)
   {
      MethodSet result = new MethodSet();
      
      for (Method obj : this)
      {
         if (value == obj.getReturnType())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Method objects and assign value to the returnType attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Method objects now with new attribute values.
    */
   public MethodSet withReturnType(DataType value)
   {
      for (Method obj : this)
      {
         obj.setReturnType(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of Method objects and collect a list of the body attribute values. 
    * 
    * @return List of String objects reachable via body attribute
    */
   public StringList getBody()
   {
      StringList result = new StringList();
      
      for (Method obj : this)
      {
         result.add(obj.getBody());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Method objects and collect those Method objects where the body attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Method objects that match the parameter
    */
   public MethodSet filterBody(String value)
   {
      MethodSet result = new MethodSet();
      
      for (Method obj : this)
      {
         if (value.equals(obj.getBody()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Method objects and collect those Method objects where the body attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Method objects that match the parameter
    */
   public MethodSet filterBody(String lower, String upper)
   {
      MethodSet result = new MethodSet();
      
      for (Method obj : this)
      {
         if (lower.compareTo(obj.getBody()) <= 0 && obj.getBody().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Method objects and assign value to the body attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Method objects now with new attribute values.
    */
   public MethodSet withBody(String value)
   {
      for (Method obj : this)
      {
         obj.setBody(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of Method objects and collect a list of the name attribute values. 
    * 
    * @return List of String objects reachable via name attribute
    */
   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Method obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Method objects and collect those Method objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Method objects that match the parameter
    */
   public MethodSet filterName(String value)
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


   /**
    * Loop through the current set of Method objects and collect those Method objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Method objects that match the parameter
    */
   public MethodSet filterName(String lower, String upper)
   {
      MethodSet result = new MethodSet();
      
      for (Method obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Method objects and assign value to the name attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Method objects now with new attribute values.
    */
   public MethodSet withName(String value)
   {
      for (Method obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Method objects and collect a set of the Parameter objects reached via parameter. 
    * 
    * @return Set of Parameter objects reachable via parameter
    */
   public ParameterSet getParameter()
   {
      ParameterSet result = new ParameterSet();
      
      for (Method obj : this)
      {
         result.with(obj.getParameter());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Method objects and collect all contained objects with reference parameter pointing to the object passed as parameter. 
    * 
    * @param value The object required as parameter neighbor of the collected results. 
    * 
    * @return Set of Parameter objects referring to value via parameter
    */
   public MethodSet filterParameter(Object value)
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

   /**
    * Loop through current set of ModelType objects and attach the Method object passed as parameter to the Parameter attribute of each of it. 
    * 
    * @param value parameter
    * @return The original set of ModelType objects now with the new neighbor attached to their Parameter attributes.
    */
   public MethodSet withParameter(Parameter value)
   {
      for (Method obj : this)
      {
         obj.withParameter(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Method object passed as parameter from the Parameter attribute of each of it. 
    * 
    * @param value parameter
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public MethodSet withoutParameter(Parameter value)
   {
      for (Method obj : this)
      {
         obj.withoutParameter(value);
      }
      
      return this;
   }

}
