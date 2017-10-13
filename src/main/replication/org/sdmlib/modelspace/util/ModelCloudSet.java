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
import org.sdmlib.modelspace.util.ModelCloudProxySet;

public class ModelCloudSet extends SimpleSet<ModelCloud>
{

   public static final ModelCloudSet EMPTY_SET = new ModelCloudSet().withFlag(ModelCloudSet.READONLY);


   public ModelCloudPO hasModelCloudPO()
   {
      return new ModelCloudPO(this.toArray(new ModelCloud[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.modelspace.ModelCloud";
   }


   @SuppressWarnings("unchecked")
   public ModelCloudSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<ModelCloud>)value);
      }
      else if (value != null)
      {
         this.add((ModelCloud) value);
      }
      
      return this;
   }
   
   public ModelCloudSet without(ModelCloud value)
   {
      this.remove(value);
      return this;
   }

   public intList getAcceptPort()
   {
      intList result = new intList();
      
      for (ModelCloud obj : this)
      {
         result.add(obj.getAcceptPort());
      }
      
      return result;
   }

   public ModelCloudSet hasAcceptPort(int value)
   {
      ModelCloudSet result = new ModelCloudSet();
      
      for (ModelCloud obj : this)
      {
         if (value == obj.getAcceptPort())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ModelCloudSet hasAcceptPort(int lower, int upper)
   {
      ModelCloudSet result = new ModelCloudSet();
      
      for (ModelCloud obj : this)
      {
         if (lower <= obj.getAcceptPort() && obj.getAcceptPort() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ModelCloudSet withAcceptPort(int value)
   {
      for (ModelCloud obj : this)
      {
         obj.setAcceptPort(value);
      }
      
      return this;
   }

   public ModelCloudProxySet getServers()
   {
      ModelCloudProxySet result = new ModelCloudProxySet();
      
      for (ModelCloud obj : this)
      {
         result.addAll(obj.getServers());
      }
      
      return result;
   }

   public ModelCloudSet hasServers(Object value)
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
      
      ModelCloudSet answer = new ModelCloudSet();
      
      for (ModelCloud obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getServers()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public ModelCloudSet withServers(ModelCloudProxy value)
   {
      for (ModelCloud obj : this)
      {
         obj.withServers(value);
      }
      
      return this;
   }

   public ModelCloudSet withoutServers(ModelCloudProxy value)
   {
      for (ModelCloud obj : this)
      {
         obj.withoutServers(value);
      }
      
      return this;
   }

   public ModelSpaceProxySet getModelSpaces()
   {
      ModelSpaceProxySet result = new ModelSpaceProxySet();
      
      for (ModelCloud obj : this)
      {
         result.addAll(obj.getModelSpaces());
      }
      
      return result;
   }

   public ModelCloudSet hasModelSpaces(Object value)
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
      
      ModelCloudSet answer = new ModelCloudSet();
      
      for (ModelCloud obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getModelSpaces()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public ModelCloudSet withModelSpaces(ModelSpaceProxy value)
   {
      for (ModelCloud obj : this)
      {
         obj.withModelSpaces(value);
      }
      
      return this;
   }

   public ModelCloudSet withoutModelSpaces(ModelSpaceProxy value)
   {
      for (ModelCloud obj : this)
      {
         obj.withoutModelSpaces(value);
      }
      
      return this;
   }

   public StringList getHostName()
   {
      StringList result = new StringList();
      
      for (ModelCloud obj : this)
      {
         result.add(obj.getHostName());
      }
      
      return result;
   }

   public ModelCloudSet hasHostName(String value)
   {
      ModelCloudSet result = new ModelCloudSet();
      
      for (ModelCloud obj : this)
      {
         if (value.equals(obj.getHostName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ModelCloudSet hasHostName(String lower, String upper)
   {
      ModelCloudSet result = new ModelCloudSet();
      
      for (ModelCloud obj : this)
      {
         if (lower.compareTo(obj.getHostName()) <= 0 && obj.getHostName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ModelCloudSet withHostName(String value)
   {
      for (ModelCloud obj : this)
      {
         obj.setHostName(value);
      }
      
      return this;
   }



   public ModelCloudPO filterModelCloudPO()
   {
      return new ModelCloudPO(this.toArray(new ModelCloud[this.size()]));
   }

   /**
    * Loop through the current set of ModelCloud objects and collect those ModelCloud objects where the hostName attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of ModelCloud objects that match the parameter
    */
   public ModelCloudSet filterHostName(String value)
   {
      ModelCloudSet result = new ModelCloudSet();
      
      for (ModelCloud obj : this)
      {
         if (value.equals(obj.getHostName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of ModelCloud objects and collect those ModelCloud objects where the hostName attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of ModelCloud objects that match the parameter
    */
   public ModelCloudSet filterHostName(String lower, String upper)
   {
      ModelCloudSet result = new ModelCloudSet();
      
      for (ModelCloud obj : this)
      {
         if (lower.compareTo(obj.getHostName()) <= 0 && obj.getHostName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of ModelCloud objects and collect those ModelCloud objects where the acceptPort attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of ModelCloud objects that match the parameter
    */
   public ModelCloudSet filterAcceptPort(int value)
   {
      ModelCloudSet result = new ModelCloudSet();
      
      for (ModelCloud obj : this)
      {
         if (value == obj.getAcceptPort())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of ModelCloud objects and collect those ModelCloud objects where the acceptPort attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of ModelCloud objects that match the parameter
    */
   public ModelCloudSet filterAcceptPort(int lower, int upper)
   {
      ModelCloudSet result = new ModelCloudSet();
      
      for (ModelCloud obj : this)
      {
         if (lower <= obj.getAcceptPort() && obj.getAcceptPort() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   public ModelCloudSet()
   {
      // empty
   }

   public ModelCloudSet(ModelCloud... objects)
   {
      for (ModelCloud obj : objects)
      {
         this.add(obj);
      }
   }

   public ModelCloudSet(Collection<ModelCloud> objects)
   {
      this.addAll(objects);
   }


   public ModelCloudPO createModelCloudPO()
   {
      return new ModelCloudPO(this.toArray(new ModelCloud[this.size()]));
   }


   @Override
   public ModelCloudSet getNewList(boolean keyValue)
   {
      return new ModelCloudSet();
   }


   public ModelCloudSet filter(Condition<ModelCloud> condition) {
      ModelCloudSet filterList = new ModelCloudSet();
      filterItems(filterList, condition);
      return filterList;
   }
   /**
    * Loop through the current set of ModelCloud objects and collect those ModelCloud objects where the acceptPort attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of ModelCloud objects that match the parameter
    */
   public ModelCloudSet createAcceptPortCondition(int value)
   {
      ModelCloudSet result = new ModelCloudSet();
      
      for (ModelCloud obj : this)
      {
         if (value == obj.getAcceptPort())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of ModelCloud objects and collect those ModelCloud objects where the acceptPort attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of ModelCloud objects that match the parameter
    */
   public ModelCloudSet createAcceptPortCondition(int lower, int upper)
   {
      ModelCloudSet result = new ModelCloudSet();
      
      for (ModelCloud obj : this)
      {
         if (lower <= obj.getAcceptPort() && obj.getAcceptPort() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of ModelCloud objects and collect those ModelCloud objects where the hostName attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of ModelCloud objects that match the parameter
    */
   public ModelCloudSet createHostNameCondition(String value)
   {
      ModelCloudSet result = new ModelCloudSet();
      
      for (ModelCloud obj : this)
      {
         if (value.equals(obj.getHostName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of ModelCloud objects and collect those ModelCloud objects where the hostName attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of ModelCloud objects that match the parameter
    */
   public ModelCloudSet createHostNameCondition(String lower, String upper)
   {
      ModelCloudSet result = new ModelCloudSet();
      
      for (ModelCloud obj : this)
      {
         if (lower.compareTo(obj.getHostName()) <= 0 && obj.getHostName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

}
