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

import org.sdmlib.modelspace.CloudModelDirectory;
import org.sdmlib.modelspace.CloudModelFile;

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;
import de.uniks.networkparser.interfaces.Condition;
import org.sdmlib.modelspace.util.CloudModelFileSet;

public class CloudModelDirectorySet extends SimpleSet<CloudModelDirectory>
{

   public static final CloudModelDirectorySet EMPTY_SET = new CloudModelDirectorySet().withFlag(CloudModelDirectorySet.READONLY);


   public CloudModelDirectoryPO hasCloudModelDirectoryPO()
   {
      return new CloudModelDirectoryPO(this.toArray(new CloudModelDirectory[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.modelspace.CloudModelDirectory";
   }


   @SuppressWarnings("unchecked")
   public CloudModelDirectorySet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<CloudModelDirectory>)value);
      }
      else if (value != null)
      {
         this.add((CloudModelDirectory) value);
      }
      
      return this;
   }
   
   public CloudModelDirectorySet without(CloudModelDirectory value)
   {
      this.remove(value);
      return this;
   }

   public CloudModelFileSet getFiles()
   {
      CloudModelFileSet result = new CloudModelFileSet();
      
      for (CloudModelDirectory obj : this)
      {
         result.addAll(obj.getFiles());
      }
      
      return result;
   }

   public CloudModelDirectorySet hasFiles(Object value)
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
      
      CloudModelDirectorySet answer = new CloudModelDirectorySet();
      
      for (CloudModelDirectory obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getFiles()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public CloudModelDirectorySet withFiles(CloudModelFile value)
   {
      for (CloudModelDirectory obj : this)
      {
         obj.withFiles(value);
      }
      
      return this;
   }

   public CloudModelDirectorySet withoutFiles(CloudModelFile value)
   {
      for (CloudModelDirectory obj : this)
      {
         obj.withoutFiles(value);
      }
      
      return this;
   }



   public CloudModelDirectoryPO filterCloudModelDirectoryPO()
   {
      return new CloudModelDirectoryPO(this.toArray(new CloudModelDirectory[this.size()]));
   }

   public CloudModelDirectorySet()
   {
      // empty
   }

   public CloudModelDirectorySet(CloudModelDirectory... objects)
   {
      for (CloudModelDirectory obj : objects)
      {
         this.add(obj);
      }
   }

   public CloudModelDirectorySet(Collection<CloudModelDirectory> objects)
   {
      this.addAll(objects);
   }


   public CloudModelDirectoryPO createCloudModelDirectoryPO()
   {
      return new CloudModelDirectoryPO(this.toArray(new CloudModelDirectory[this.size()]));
   }


   @Override
   public CloudModelDirectorySet getNewList(boolean keyValue)
   {
      return new CloudModelDirectorySet();
   }


   public CloudModelDirectorySet filter(Condition<CloudModelDirectory> condition) {
      CloudModelDirectorySet filterList = new CloudModelDirectorySet();
      filterItems(filterList, condition);
      return filterList;
   }}
