/*
   Copyright (c) 2016 deeptought
   
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
   
package org.sdmlib.modelcouch.util;

import de.uniks.networkparser.list.SDMSet;
import org.sdmlib.modelcouch.ModelCouch;
import java.util.Collection;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.intList;

public class ModelCouchSet extends SDMSet<ModelCouch>
{

   public static final ModelCouchSet EMPTY_SET = new ModelCouchSet().withReadOnly();


   public ModelCouchPO hasModelCouchPO()
   {
      return new ModelCouchPO(this.toArray(new ModelCouch[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.modelcouch.ModelCouch";
   }


   @SuppressWarnings("unchecked")
   public ModelCouchSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<ModelCouch>)value);
      }
      else if (value != null)
      {
         this.add((ModelCouch) value);
      }
      
      return this;
   }
   
   public ModelCouchSet without(ModelCouch value)
   {
      this.remove(value);
      return this;
   }


   /**
    * Loop through the current set of ModelCouch objects and collect a list of the hostName attribute values. 
    * 
    * @return List of String objects reachable via hostName attribute
    */
   public StringList getHostName()
   {
      StringList result = new StringList();
      
      for (ModelCouch obj : this)
      {
         result.add(obj.getHostName());
      }
      
      return result;
   }


   /**
    * Loop through the current set of ModelCouch objects and collect those ModelCouch objects where the hostName attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of ModelCouch objects that match the parameter
    */
   public ModelCouchSet hasHostName(String value)
   {
      ModelCouchSet result = new ModelCouchSet();
      
      for (ModelCouch obj : this)
      {
         if (value.equals(obj.getHostName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of ModelCouch objects and collect those ModelCouch objects where the hostName attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of ModelCouch objects that match the parameter
    */
   public ModelCouchSet hasHostName(String lower, String upper)
   {
      ModelCouchSet result = new ModelCouchSet();
      
      for (ModelCouch obj : this)
      {
         if (lower.compareTo(obj.getHostName()) <= 0 && obj.getHostName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of ModelCouch objects and assign value to the hostName attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of ModelCouch objects now with new attribute values.
    */
   public ModelCouchSet withHostName(String value)
   {
      for (ModelCouch obj : this)
      {
         obj.setHostName(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of ModelCouch objects and collect a list of the port attribute values. 
    * 
    * @return List of int objects reachable via port attribute
    */
   public intList getPort()
   {
      intList result = new intList();
      
      for (ModelCouch obj : this)
      {
         result.add(obj.getPort());
      }
      
      return result;
   }


   /**
    * Loop through the current set of ModelCouch objects and collect those ModelCouch objects where the port attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of ModelCouch objects that match the parameter
    */
   public ModelCouchSet hasPort(int value)
   {
      ModelCouchSet result = new ModelCouchSet();
      
      for (ModelCouch obj : this)
      {
         if (value == obj.getPort())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of ModelCouch objects and collect those ModelCouch objects where the port attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of ModelCouch objects that match the parameter
    */
   public ModelCouchSet hasPort(int lower, int upper)
   {
      ModelCouchSet result = new ModelCouchSet();
      
      for (ModelCouch obj : this)
      {
         if (lower <= obj.getPort() && obj.getPort() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of ModelCouch objects and assign value to the port attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of ModelCouch objects now with new attribute values.
    */
   public ModelCouchSet withPort(int value)
   {
      for (ModelCouch obj : this)
      {
         obj.setPort(value);
      }
      
      return this;
   }

}
