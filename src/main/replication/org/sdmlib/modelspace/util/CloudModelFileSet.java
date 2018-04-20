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

import org.sdmlib.models.modelsets.longList;
import org.sdmlib.modelspace.CloudModelDirectory;
import org.sdmlib.modelspace.CloudModelFile;

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;
import de.uniks.networkparser.list.StringList;
import de.uniks.networkparser.list.NumberList;
import org.sdmlib.modelspace.util.CloudModelDirectorySet;

public class CloudModelFileSet extends SimpleSet<CloudModelFile>
{

   public static final CloudModelFileSet EMPTY_SET = new CloudModelFileSet().withFlag(CloudModelFileSet.READONLY);


   public CloudModelFilePO hasCloudModelFilePO()
   {
      return new CloudModelFilePO(this.toArray(new CloudModelFile[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.modelspace.CloudModelFile";
   }


   @SuppressWarnings("unchecked")
   public CloudModelFileSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<CloudModelFile>)value);
      }
      else if (value != null)
      {
         this.add((CloudModelFile) value);
      }
      
      return this;
   }
   
   public CloudModelFileSet without(CloudModelFile value)
   {
      this.remove(value);
      return this;
   }

   public StringList getFileName()
   {
      StringList result = new StringList();
      
      for (CloudModelFile obj : this)
      {
         result.add(obj.getFileName());
      }
      
      return result;
   }

   public CloudModelFileSet hasFileName(String value)
   {
      CloudModelFileSet result = new CloudModelFileSet();
      
      for (CloudModelFile obj : this)
      {
         if (value.equals(obj.getFileName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public CloudModelFileSet hasFileName(String lower, String upper)
   {
      CloudModelFileSet result = new CloudModelFileSet();
      
      for (CloudModelFile obj : this)
      {
         if (lower.compareTo(obj.getFileName()) <= 0 && obj.getFileName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public CloudModelFileSet withFileName(String value)
   {
      for (CloudModelFile obj : this)
      {
         obj.setFileName(value);
      }
      
      return this;
   }

   public longList getLastModifiedTime()
   {
      longList result = new longList();
      
      for (CloudModelFile obj : this)
      {
         result.add(obj.getLastModifiedTime());
      }
      
      return result;
   }

   public CloudModelFileSet hasLastModifiedTime(long value)
   {
      CloudModelFileSet result = new CloudModelFileSet();
      
      for (CloudModelFile obj : this)
      {
         if (value == obj.getLastModifiedTime())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public CloudModelFileSet hasLastModifiedTime(long lower, long upper)
   {
      CloudModelFileSet result = new CloudModelFileSet();
      
      for (CloudModelFile obj : this)
      {
         if (lower <= obj.getLastModifiedTime() && obj.getLastModifiedTime() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public CloudModelFileSet withLastModifiedTime(long value)
   {
      for (CloudModelFile obj : this)
      {
         obj.setLastModifiedTime(value);
      }
      
      return this;
   }

   public CloudModelDirectorySet getDir()
   {
      CloudModelDirectorySet result = new CloudModelDirectorySet();
      
      for (CloudModelFile obj : this)
      {
         result.add(obj.getDir());
      }
      
      return result;
   }

   public CloudModelFileSet hasDir(Object value)
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
      
      CloudModelFileSet answer = new CloudModelFileSet();
      
      for (CloudModelFile obj : this)
      {
         if (neighbors.contains(obj.getDir()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public CloudModelFileSet withDir(CloudModelDirectory value)
   {
      for (CloudModelFile obj : this)
      {
         obj.withDir(value);
      }
      
      return this;
   }



   public CloudModelFilePO filterCloudModelFilePO()
   {
      return new CloudModelFilePO(this.toArray(new CloudModelFile[this.size()]));
   }

   /**
    * Loop through the current set of CloudModelFile objects and collect those CloudModelFile objects where the fileName attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of CloudModelFile objects that match the parameter
    */
   public CloudModelFileSet filterFileName(String value)
   {
      CloudModelFileSet result = new CloudModelFileSet();
      
      for (CloudModelFile obj : this)
      {
         if (value.equals(obj.getFileName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of CloudModelFile objects and collect those CloudModelFile objects where the fileName attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of CloudModelFile objects that match the parameter
    */
   public CloudModelFileSet filterFileName(String lower, String upper)
   {
      CloudModelFileSet result = new CloudModelFileSet();
      
      for (CloudModelFile obj : this)
      {
         if (lower.compareTo(obj.getFileName()) <= 0 && obj.getFileName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of CloudModelFile objects and collect those CloudModelFile objects where the lastModifiedTime attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of CloudModelFile objects that match the parameter
    */
   public CloudModelFileSet filterLastModifiedTime(long value)
   {
      CloudModelFileSet result = new CloudModelFileSet();
      
      for (CloudModelFile obj : this)
      {
         if (value == obj.getLastModifiedTime())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of CloudModelFile objects and collect those CloudModelFile objects where the lastModifiedTime attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of CloudModelFile objects that match the parameter
    */
   public CloudModelFileSet filterLastModifiedTime(long lower, long upper)
   {
      CloudModelFileSet result = new CloudModelFileSet();
      
      for (CloudModelFile obj : this)
      {
         if (lower <= obj.getLastModifiedTime() && obj.getLastModifiedTime() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   public CloudModelFileSet()
   {
      // empty
   }

   public CloudModelFileSet(CloudModelFile... objects)
   {
      for (CloudModelFile obj : objects)
      {
         this.add(obj);
      }
   }

   public CloudModelFileSet(Collection<CloudModelFile> objects)
   {
      this.addAll(objects);
   }


   public CloudModelFilePO createCloudModelFilePO()
   {
      return new CloudModelFilePO(this.toArray(new CloudModelFile[this.size()]));
   }


   @Override
   public CloudModelFileSet getNewList(boolean keyValue)
   {
      return new CloudModelFileSet();
   }

   /**
    * Loop through the current set of CloudModelFile objects and collect those CloudModelFile objects where the fileName attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of CloudModelFile objects that match the parameter
    */
   public CloudModelFileSet createFileNameCondition(String value)
   {
      CloudModelFileSet result = new CloudModelFileSet();
      
      for (CloudModelFile obj : this)
      {
         if (value.equals(obj.getFileName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of CloudModelFile objects and collect those CloudModelFile objects where the fileName attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of CloudModelFile objects that match the parameter
    */
   public CloudModelFileSet createFileNameCondition(String lower, String upper)
   {
      CloudModelFileSet result = new CloudModelFileSet();
      
      for (CloudModelFile obj : this)
      {
         if (lower.compareTo(obj.getFileName()) <= 0 && obj.getFileName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of CloudModelFile objects and collect those CloudModelFile objects where the lastModifiedTime attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of CloudModelFile objects that match the parameter
    */
   public CloudModelFileSet createLastModifiedTimeCondition(long value)
   {
      CloudModelFileSet result = new CloudModelFileSet();
      
      for (CloudModelFile obj : this)
      {
         if (value == obj.getLastModifiedTime())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of CloudModelFile objects and collect those CloudModelFile objects where the lastModifiedTime attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of CloudModelFile objects that match the parameter
    */
   public CloudModelFileSet createLastModifiedTimeCondition(long lower, long upper)
   {
      CloudModelFileSet result = new CloudModelFileSet();
      
      for (CloudModelFile obj : this)
      {
         if (lower <= obj.getLastModifiedTime() && obj.getLastModifiedTime() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

}
