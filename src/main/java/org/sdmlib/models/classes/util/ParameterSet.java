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
import org.sdmlib.models.classes.Parameter;
import java.util.Collection;
import de.uniks.networkparser.interfaces.Condition;
import org.sdmlib.models.modelsets.StringList;
import de.uniks.networkparser.graph.DataType;
import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.models.classes.util.MethodSet;
import org.sdmlib.models.classes.Method;

public class ParameterSet extends SDMSet<Parameter>
{

   public static final ParameterSet EMPTY_SET = new ParameterSet().withFlag(ParameterSet.READONLY);


   public String getEntryType()
   {
      return "org.sdmlib.models.classes.Parameter";
   }


   @SuppressWarnings("unchecked")
   public ParameterSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Parameter>)value);
      }
      else if (value != null)
      {
         this.add((Parameter) value);
      }
      
      return this;
   }
   
   public ParameterSet without(Parameter value)
   {
      this.remove(value);
      return this;
   }

   @Override
   public ParameterSet filter(Condition<Parameter> newValue) {
      ParameterSet filterList = new ParameterSet();
      filterItems(filterList, newValue);
      return filterList;
   }

   /**
    * Loop through the current set of Parameter objects and collect a list of the initialization attribute values. 
    * 
    * @return List of String objects reachable via initialization attribute
    */
   public StringList getInitialization()
   {
      StringList result = new StringList();
      
      for (Parameter obj : this)
      {
         result.add(obj.getInitialization());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Parameter objects and collect those Parameter objects where the initialization attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Parameter objects that match the parameter
    */
   public ParameterSet filterInitialization(String value)
   {
      ParameterSet result = new ParameterSet();
      
      for (Parameter obj : this)
      {
         if (value.equals(obj.getInitialization()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Parameter objects and collect those Parameter objects where the initialization attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Parameter objects that match the parameter
    */
   public ParameterSet filterInitialization(String lower, String upper)
   {
      ParameterSet result = new ParameterSet();
      
      for (Parameter obj : this)
      {
         if (lower.compareTo(obj.getInitialization()) <= 0 && obj.getInitialization().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Parameter objects and assign value to the initialization attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Parameter objects now with new attribute values.
    */
   public ParameterSet withInitialization(String value)
   {
      for (Parameter obj : this)
      {
         obj.setInitialization(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of Parameter objects and collect a list of the type attribute values. 
    * 
    * @return List of de.uniks.networkparser.graph.DataType objects reachable via type attribute
    */
   public DataTypeSet getType()
   {
      DataTypeSet result = new DataTypeSet();
      
      for (Parameter obj : this)
      {
         result.add(obj.getType());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Parameter objects and collect those Parameter objects where the type attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Parameter objects that match the parameter
    */
   public ParameterSet filterType(DataType value)
   {
      ParameterSet result = new ParameterSet();
      
      for (Parameter obj : this)
      {
         if (value == obj.getType())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Parameter objects and assign value to the type attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Parameter objects now with new attribute values.
    */
   public ParameterSet withType(DataType value)
   {
      for (Parameter obj : this)
      {
         obj.setType(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of Parameter objects and collect a list of the name attribute values. 
    * 
    * @return List of String objects reachable via name attribute
    */
   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Parameter obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Parameter objects and collect those Parameter objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Parameter objects that match the parameter
    */
   public ParameterSet filterName(String value)
   {
      ParameterSet result = new ParameterSet();
      
      for (Parameter obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Parameter objects and collect those Parameter objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Parameter objects that match the parameter
    */
   public ParameterSet filterName(String lower, String upper)
   {
      ParameterSet result = new ParameterSet();
      
      for (Parameter obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Parameter objects and assign value to the name attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Parameter objects now with new attribute values.
    */
   public ParameterSet withName(String value)
   {
      for (Parameter obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Parameter objects and collect a set of the Method objects reached via method. 
    * 
    * @return Set of Method objects reachable via method
    */
   public MethodSet getMethod()
   {
      MethodSet result = new MethodSet();
      
      for (Parameter obj : this)
      {
         result.with(obj.getMethod());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Parameter objects and collect all contained objects with reference method pointing to the object passed as parameter. 
    * 
    * @param value The object required as method neighbor of the collected results. 
    * 
    * @return Set of Method objects referring to value via method
    */
   public ParameterSet filterMethod(Object value)
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
      
      ParameterSet answer = new ParameterSet();
      
      for (Parameter obj : this)
      {
         if (neighbors.contains(obj.getMethod()) || (neighbors.isEmpty() && obj.getMethod() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Parameter object passed as parameter to the Method attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Method attributes.
    */
   public ParameterSet withMethod(Method value)
   {
      for (Parameter obj : this)
      {
         obj.withMethod(value);
      }
      
      return this;
   }

}
