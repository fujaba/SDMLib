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

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.StringList;
import org.sdmlib.modelspace.ModelCloud;
import org.sdmlib.modelspace.ModelCloudProxy;
import org.sdmlib.modelspace.ModelSpaceProxy;

import de.uniks.networkparser.list.SimpleSet;
import org.sdmlib.modelspace.util.ModelCloudSet;
import org.sdmlib.modelspace.util.ModelCloudProxySet;

public class ModelSpaceProxySet extends SimpleSet<ModelSpaceProxy>
{

   public static final ModelSpaceProxySet EMPTY_SET = new ModelSpaceProxySet().withFlag(ModelSpaceProxySet.READONLY);


   public ModelSpaceProxyPO hasModelSpaceProxyPO()
   {
      return new ModelSpaceProxyPO(this.toArray(new ModelSpaceProxy[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.modelspace.ModelSpaceProxy";
   }


   @SuppressWarnings("unchecked")
   public ModelSpaceProxySet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<ModelSpaceProxy>)value);
      }
      else if (value != null)
      {
         this.add((ModelSpaceProxy) value);
      }
      
      return this;
   }
   
   public ModelSpaceProxySet without(ModelSpaceProxy value)
   {
      this.remove(value);
      return this;
   }

   public StringList getLocation()
   {
      StringList result = new StringList();
      
      for (ModelSpaceProxy obj : this)
      {
         result.add(obj.getLocation());
      }
      
      return result;
   }

   public ModelSpaceProxySet hasLocation(String value)
   {
      ModelSpaceProxySet result = new ModelSpaceProxySet();
      
      for (ModelSpaceProxy obj : this)
      {
         if (value.equals(obj.getLocation()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ModelSpaceProxySet hasLocation(String lower, String upper)
   {
      ModelSpaceProxySet result = new ModelSpaceProxySet();
      
      for (ModelSpaceProxy obj : this)
      {
         if (lower.compareTo(obj.getLocation()) <= 0 && obj.getLocation().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ModelSpaceProxySet withLocation(String value)
   {
      for (ModelSpaceProxy obj : this)
      {
         obj.setLocation(value);
      }
      
      return this;
   }

   public ModelCloudSet getCloud()
   {
      ModelCloudSet result = new ModelCloudSet();
      
      for (ModelSpaceProxy obj : this)
      {
         result.add(obj.getCloud());
      }
      
      return result;
   }

   public ModelSpaceProxySet hasCloud(Object value)
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
      
      ModelSpaceProxySet answer = new ModelSpaceProxySet();
      
      for (ModelSpaceProxy obj : this)
      {
         if (neighbors.contains(obj.getCloud()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public ModelSpaceProxySet withCloud(ModelCloud value)
   {
      for (ModelSpaceProxy obj : this)
      {
         obj.withCloud(value);
      }
      
      return this;
   }

   public ModelCloudProxySet getProvidingClouds()
   {
      ModelCloudProxySet result = new ModelCloudProxySet();
      
      for (ModelSpaceProxy obj : this)
      {
         result.addAll(obj.getProvidingClouds());
      }
      
      return result;
   }

   public ModelSpaceProxySet hasProvidingClouds(Object value)
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
      
      ModelSpaceProxySet answer = new ModelSpaceProxySet();
      
      for (ModelSpaceProxy obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getProvidingClouds()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public ModelSpaceProxySet withProvidingClouds(ModelCloudProxy value)
   {
      for (ModelSpaceProxy obj : this)
      {
         obj.withProvidingClouds(value);
      }
      
      return this;
   }

   public ModelSpaceProxySet withoutProvidingClouds(ModelCloudProxy value)
   {
      for (ModelSpaceProxy obj : this)
      {
         obj.withoutProvidingClouds(value);
      }
      
      return this;
   }



   public ModelSpaceProxyPO filterModelSpaceProxyPO()
   {
      return new ModelSpaceProxyPO(this.toArray(new ModelSpaceProxy[this.size()]));
   }

   /**
    * Loop through the current set of ModelSpaceProxy objects and collect those ModelSpaceProxy objects where the location attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of ModelSpaceProxy objects that match the parameter
    */
   public ModelSpaceProxySet filterLocation(String value)
   {
      ModelSpaceProxySet result = new ModelSpaceProxySet();
      
      for (ModelSpaceProxy obj : this)
      {
         if (value.equals(obj.getLocation()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of ModelSpaceProxy objects and collect those ModelSpaceProxy objects where the location attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of ModelSpaceProxy objects that match the parameter
    */
   public ModelSpaceProxySet filterLocation(String lower, String upper)
   {
      ModelSpaceProxySet result = new ModelSpaceProxySet();
      
      for (ModelSpaceProxy obj : this)
      {
         if (lower.compareTo(obj.getLocation()) <= 0 && obj.getLocation().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   public ModelSpaceProxySet()
   {
      // empty
   }

   public ModelSpaceProxySet(ModelSpaceProxy... objects)
   {
      for (ModelSpaceProxy obj : objects)
      {
         this.add(obj);
      }
   }

   public ModelSpaceProxySet(Collection<ModelSpaceProxy> objects)
   {
      this.addAll(objects);
   }
}
