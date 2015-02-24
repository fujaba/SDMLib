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
   
package org.sdmlib.replication.util;

import java.util.Collection;

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.booleanList;
import org.sdmlib.models.modelsets.longList;
import org.sdmlib.replication.ChangeHistory;
import org.sdmlib.replication.SeppelSpace;

public class SeppelSpaceSet extends SDMSet<SeppelSpace>
{

   public static final SeppelSpaceSet EMPTY_SET = new SeppelSpaceSet().withReadOnly(true);


   public SeppelSpacePO hasSeppelSpacePO()
   {
      return new SeppelSpacePO(this.toArray(new SeppelSpace[this.size()]));
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.replication.SeppelSpace";
   }


   @SuppressWarnings("unchecked")
   public SeppelSpaceSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<SeppelSpace>)value);
      }
      else if (value != null)
      {
         this.add((SeppelSpace) value);
      }
      
      return this;
   }
   
   public SeppelSpaceSet without(SeppelSpace value)
   {
      this.remove(value);
      return this;
   }

   public StringList getSpaceId()
   {
      StringList result = new StringList();
      
      for (SeppelSpace obj : this)
      {
         result.add(obj.getSpaceId());
      }
      
      return result;
   }

   public SeppelSpaceSet hasSpaceId(String value)
   {
      SeppelSpaceSet result = new SeppelSpaceSet();
      
      for (SeppelSpace obj : this)
      {
         if (value.equals(obj.getSpaceId()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public SeppelSpaceSet hasSpaceId(String lower, String upper)
   {
      SeppelSpaceSet result = new SeppelSpaceSet();
      
      for (SeppelSpace obj : this)
      {
         if (lower.compareTo(obj.getSpaceId()) <= 0 && obj.getSpaceId().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public SeppelSpaceSet withSpaceId(String value)
   {
      for (SeppelSpace obj : this)
      {
         obj.setSpaceId(value);
      }
      
      return this;
   }

   public ChangeHistorySet getHistory()
   {
      ChangeHistorySet result = new ChangeHistorySet();
      
      for (SeppelSpace obj : this)
      {
         result.add(obj.getHistory());
      }
      
      return result;
   }

   public SeppelSpaceSet hasHistory(ChangeHistory value)
   {
      SeppelSpaceSet result = new SeppelSpaceSet();
      
      for (SeppelSpace obj : this)
      {
         if (value == obj.getHistory())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public SeppelSpaceSet withHistory(ChangeHistory value)
   {
      for (SeppelSpace obj : this)
      {
         obj.setHistory(value);
      }
      
      return this;
   }

   public longList getLastChangeId()
   {
      longList result = new longList();
      
      for (SeppelSpace obj : this)
      {
         result.add(obj.getLastChangeId());
      }
      
      return result;
   }

   public SeppelSpaceSet hasLastChangeId(long value)
   {
      SeppelSpaceSet result = new SeppelSpaceSet();
      
      for (SeppelSpace obj : this)
      {
         if (value == obj.getLastChangeId())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public SeppelSpaceSet hasLastChangeId(long lower, long upper)
   {
      SeppelSpaceSet result = new SeppelSpaceSet();
      
      for (SeppelSpace obj : this)
      {
         if (lower <= obj.getLastChangeId() && obj.getLastChangeId() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public SeppelSpaceSet withLastChangeId(long value)
   {
      for (SeppelSpace obj : this)
      {
         obj.setLastChangeId(value);
      }
      
      return this;
   }

   public booleanList getJavaFXApplication()
   {
      booleanList result = new booleanList();
      
      for (SeppelSpace obj : this)
      {
         result.add(obj.isJavaFXApplication());
      }
      
      return result;
   }

   public SeppelSpaceSet hasJavaFXApplication(boolean value)
   {
      SeppelSpaceSet result = new SeppelSpaceSet();
      
      for (SeppelSpace obj : this)
      {
         if (value == obj.isJavaFXApplication())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public SeppelSpaceSet withJavaFXApplication(boolean value)
   {
      for (SeppelSpace obj : this)
      {
         obj.setJavaFXApplication(value);
      }
      
      return this;
   }

}
