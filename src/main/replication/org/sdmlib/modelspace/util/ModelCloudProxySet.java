/*
   Copyright (c) 2015 zuendorf
   
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
   
package org.sdmlib.modelspace.util;

import java.util.Collection;
import java.util.Collections;

import org.sdmlib.models.modelsets.intList;
import org.sdmlib.modelspace.ModelCloud;
import org.sdmlib.modelspace.ModelCloudProxy;
import org.sdmlib.modelspace.ModelSpaceProxy;

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;
import de.uniks.networkparser.list.StringList;
import de.uniks.networkparser.interfaces.Condition;
import de.uniks.networkparser.list.NumberList;
import org.sdmlib.modelspace.util.ModelSpaceProxySet;
import org.sdmlib.modelspace.util.ModelCloudSet;

public class ModelCloudProxySet extends SimpleSet<ModelCloudProxy>
{

   public static final ModelCloudProxySet EMPTY_SET = new ModelCloudProxySet().withFlag(ModelCloudProxySet.READONLY);


   public ModelCloudProxyPO hasModelCloudProxyPO()
   {
      return new ModelCloudProxyPO(this.toArray(new ModelCloudProxy[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.modelspace.ModelCloudProxy";
   }


   @SuppressWarnings("unchecked")
   public ModelCloudProxySet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<ModelCloudProxy>)value);
      }
      else if (value != null)
      {
         this.add((ModelCloudProxy) value);
      }
      
      return this;
   }
   
   public ModelCloudProxySet without(ModelCloudProxy value)
   {
      this.remove(value);
      return this;
   }

   public StringList getHostName()
   {
      StringList result = new StringList();
      
      for (ModelCloudProxy obj : this)
      {
         result.add(obj.getHostName());
      }
      
      return result;
   }

   public ModelCloudProxySet hasHostName(String value)
   {
      ModelCloudProxySet result = new ModelCloudProxySet();
      
      for (ModelCloudProxy obj : this)
      {
         if (value.equals(obj.getHostName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ModelCloudProxySet hasHostName(String lower, String upper)
   {
      ModelCloudProxySet result = new ModelCloudProxySet();
      
      for (ModelCloudProxy obj : this)
      {
         if (lower.compareTo(obj.getHostName()) <= 0 && obj.getHostName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ModelCloudProxySet withHostName(String value)
   {
      for (ModelCloudProxy obj : this)
      {
         obj.setHostName(value);
      }
      
      return this;
   }

   public intList getPortNo()
   {
      intList result = new intList();
      
      for (ModelCloudProxy obj : this)
      {
         result.add(obj.getPortNo());
      }
      
      return result;
   }

   public ModelCloudProxySet hasPortNo(int value)
   {
      ModelCloudProxySet result = new ModelCloudProxySet();
      
      for (ModelCloudProxy obj : this)
      {
         if (value == obj.getPortNo())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ModelCloudProxySet hasPortNo(int lower, int upper)
   {
      ModelCloudProxySet result = new ModelCloudProxySet();
      
      for (ModelCloudProxy obj : this)
      {
         if (lower <= obj.getPortNo() && obj.getPortNo() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ModelCloudProxySet withPortNo(int value)
   {
      for (ModelCloudProxy obj : this)
      {
         obj.setPortNo(value);
      }
      
      return this;
   }

   public ModelCloudSet getRoot()
   {
      ModelCloudSet result = new ModelCloudSet();
      
      for (ModelCloudProxy obj : this)
      {
         result.add(obj.getRoot());
      }
      
      return result;
   }

   public ModelCloudProxySet hasRoot(Object value)
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
      
      ModelCloudProxySet answer = new ModelCloudProxySet();
      
      for (ModelCloudProxy obj : this)
      {
         if (neighbors.contains(obj.getRoot()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public ModelCloudProxySet withRoot(ModelCloud value)
   {
      for (ModelCloudProxy obj : this)
      {
         obj.withRoot(value);
      }
      
      return this;
   }

   public ModelSpaceProxySet getProvidedSpaces()
   {
      ModelSpaceProxySet result = new ModelSpaceProxySet();
      
      for (ModelCloudProxy obj : this)
      {
         result.addAll(obj.getProvidedSpaces());
      }
      
      return result;
   }

   public ModelCloudProxySet hasProvidedSpaces(Object value)
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
      
      ModelCloudProxySet answer = new ModelCloudProxySet();
      
      for (ModelCloudProxy obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getProvidedSpaces()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public ModelCloudProxySet withProvidedSpaces(ModelSpaceProxy value)
   {
      for (ModelCloudProxy obj : this)
      {
         obj.withProvidedSpaces(value);
      }
      
      return this;
   }

   public ModelCloudProxySet withoutProvidedSpaces(ModelSpaceProxy value)
   {
      for (ModelCloudProxy obj : this)
      {
         obj.withoutProvidedSpaces(value);
      }
      
      return this;
   }



   public ModelCloudProxyPO filterModelCloudProxyPO()
   {
      return new ModelCloudProxyPO(this.toArray(new ModelCloudProxy[this.size()]));
   }

   /**
    * Loop through the current set of ModelCloudProxy objects and collect those ModelCloudProxy objects where the hostName attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of ModelCloudProxy objects that match the parameter
    */
   public ModelCloudProxySet filterHostName(String value)
   {
      ModelCloudProxySet result = new ModelCloudProxySet();
      
      for (ModelCloudProxy obj : this)
      {
         if (value.equals(obj.getHostName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of ModelCloudProxy objects and collect those ModelCloudProxy objects where the hostName attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of ModelCloudProxy objects that match the parameter
    */
   public ModelCloudProxySet filterHostName(String lower, String upper)
   {
      ModelCloudProxySet result = new ModelCloudProxySet();
      
      for (ModelCloudProxy obj : this)
      {
         if (lower.compareTo(obj.getHostName()) <= 0 && obj.getHostName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of ModelCloudProxy objects and collect those ModelCloudProxy objects where the portNo attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of ModelCloudProxy objects that match the parameter
    */
   public ModelCloudProxySet filterPortNo(int value)
   {
      ModelCloudProxySet result = new ModelCloudProxySet();
      
      for (ModelCloudProxy obj : this)
      {
         if (value == obj.getPortNo())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of ModelCloudProxy objects and collect those ModelCloudProxy objects where the portNo attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of ModelCloudProxy objects that match the parameter
    */
   public ModelCloudProxySet filterPortNo(int lower, int upper)
   {
      ModelCloudProxySet result = new ModelCloudProxySet();
      
      for (ModelCloudProxy obj : this)
      {
         if (lower <= obj.getPortNo() && obj.getPortNo() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   public ModelCloudProxySet()
   {
      // empty
   }

   public ModelCloudProxySet(ModelCloudProxy... objects)
   {
      for (ModelCloudProxy obj : objects)
      {
         this.add(obj);
      }
   }

   public ModelCloudProxySet(Collection<ModelCloudProxy> objects)
   {
      this.addAll(objects);
   }


   public ModelCloudProxyPO createModelCloudProxyPO()
   {
      return new ModelCloudProxyPO(this.toArray(new ModelCloudProxy[this.size()]));
   }


   @Override
   public ModelCloudProxySet getNewList(boolean keyValue)
   {
      return new ModelCloudProxySet();
   }


   public ModelCloudProxySet filter(Condition<ModelCloudProxy> condition) {
      ModelCloudProxySet filterList = new ModelCloudProxySet();
      filterItems(filterList, condition);
      return filterList;
   }
   /**
    * Loop through the current set of ModelCloudProxy objects and collect those ModelCloudProxy objects where the hostName attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of ModelCloudProxy objects that match the parameter
    */
   public ModelCloudProxySet createHostNameCondition(String value)
   {
      ModelCloudProxySet result = new ModelCloudProxySet();
      
      for (ModelCloudProxy obj : this)
      {
         if (value.equals(obj.getHostName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of ModelCloudProxy objects and collect those ModelCloudProxy objects where the hostName attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of ModelCloudProxy objects that match the parameter
    */
   public ModelCloudProxySet createHostNameCondition(String lower, String upper)
   {
      ModelCloudProxySet result = new ModelCloudProxySet();
      
      for (ModelCloudProxy obj : this)
      {
         if (lower.compareTo(obj.getHostName()) <= 0 && obj.getHostName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of ModelCloudProxy objects and collect those ModelCloudProxy objects where the portNo attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of ModelCloudProxy objects that match the parameter
    */
   public ModelCloudProxySet createPortNoCondition(int value)
   {
      ModelCloudProxySet result = new ModelCloudProxySet();
      
      for (ModelCloudProxy obj : this)
      {
         if (value == obj.getPortNo())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of ModelCloudProxy objects and collect those ModelCloudProxy objects where the portNo attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of ModelCloudProxy objects that match the parameter
    */
   public ModelCloudProxySet createPortNoCondition(int lower, int upper)
   {
      ModelCloudProxySet result = new ModelCloudProxySet();
      
      for (ModelCloudProxy obj : this)
      {
         if (lower <= obj.getPortNo() && obj.getPortNo() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

}
