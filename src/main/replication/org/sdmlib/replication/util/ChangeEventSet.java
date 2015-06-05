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

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.replication.ChangeEvent;
import java.util.Collection;
import org.sdmlib.models.modelsets.StringList;

public class ChangeEventSet extends SDMSet<ChangeEvent>
{

   public static final ChangeEventSet EMPTY_SET = new ChangeEventSet().withReadOnly(true);


   public ChangeEventPO hasChangeEventPO()
   {
      return new ChangeEventPO(this.toArray(new ChangeEvent[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.replication.ChangeEvent";
   }


   @SuppressWarnings("unchecked")
   public ChangeEventSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<ChangeEvent>)value);
      }
      else if (value != null)
      {
         this.add((ChangeEvent) value);
      }
      
      return this;
   }
   
   public ChangeEventSet without(ChangeEvent value)
   {
      this.remove(value);
      return this;
   }

   public StringList getObjectId()
   {
      StringList result = new StringList();
      
      for (ChangeEvent obj : this)
      {
         result.add(obj.getObjectId());
      }
      
      return result;
   }

   public ChangeEventSet hasObjectId(String value)
   {
      ChangeEventSet result = new ChangeEventSet();
      
      for (ChangeEvent obj : this)
      {
         if (value.equals(obj.getObjectId()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ChangeEventSet hasObjectId(String lower, String upper)
   {
      ChangeEventSet result = new ChangeEventSet();
      
      for (ChangeEvent obj : this)
      {
         if (lower.compareTo(obj.getObjectId()) <= 0 && obj.getObjectId().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ChangeEventSet withObjectId(String value)
   {
      for (ChangeEvent obj : this)
      {
         obj.setObjectId(value);
      }
      
      return this;
   }

   public StringList getObjectType()
   {
      StringList result = new StringList();
      
      for (ChangeEvent obj : this)
      {
         result.add(obj.getObjectType());
      }
      
      return result;
   }

   public ChangeEventSet hasObjectType(String value)
   {
      ChangeEventSet result = new ChangeEventSet();
      
      for (ChangeEvent obj : this)
      {
         if (value.equals(obj.getObjectType()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ChangeEventSet hasObjectType(String lower, String upper)
   {
      ChangeEventSet result = new ChangeEventSet();
      
      for (ChangeEvent obj : this)
      {
         if (lower.compareTo(obj.getObjectType()) <= 0 && obj.getObjectType().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ChangeEventSet withObjectType(String value)
   {
      for (ChangeEvent obj : this)
      {
         obj.setObjectType(value);
      }
      
      return this;
   }

   public StringList getProperty()
   {
      StringList result = new StringList();
      
      for (ChangeEvent obj : this)
      {
         result.add(obj.getProperty());
      }
      
      return result;
   }

   public ChangeEventSet hasProperty(String value)
   {
      ChangeEventSet result = new ChangeEventSet();
      
      for (ChangeEvent obj : this)
      {
         if (value.equals(obj.getProperty()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ChangeEventSet hasProperty(String lower, String upper)
   {
      ChangeEventSet result = new ChangeEventSet();
      
      for (ChangeEvent obj : this)
      {
         if (lower.compareTo(obj.getProperty()) <= 0 && obj.getProperty().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ChangeEventSet withProperty(String value)
   {
      for (ChangeEvent obj : this)
      {
         obj.setProperty(value);
      }
      
      return this;
   }

   public StringList getNewValue()
   {
      StringList result = new StringList();
      
      for (ChangeEvent obj : this)
      {
         result.add(obj.getNewValue());
      }
      
      return result;
   }

   public ChangeEventSet hasNewValue(String value)
   {
      ChangeEventSet result = new ChangeEventSet();
      
      for (ChangeEvent obj : this)
      {
         if (value.equals(obj.getNewValue()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ChangeEventSet hasNewValue(String lower, String upper)
   {
      ChangeEventSet result = new ChangeEventSet();
      
      for (ChangeEvent obj : this)
      {
         if (lower.compareTo(obj.getNewValue()) <= 0 && obj.getNewValue().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ChangeEventSet withNewValue(String value)
   {
      for (ChangeEvent obj : this)
      {
         obj.setNewValue(value);
      }
      
      return this;
   }

   public StringList getOldValue()
   {
      StringList result = new StringList();
      
      for (ChangeEvent obj : this)
      {
         result.add(obj.getOldValue());
      }
      
      return result;
   }

   public ChangeEventSet hasOldValue(String value)
   {
      ChangeEventSet result = new ChangeEventSet();
      
      for (ChangeEvent obj : this)
      {
         if (value.equals(obj.getOldValue()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ChangeEventSet hasOldValue(String lower, String upper)
   {
      ChangeEventSet result = new ChangeEventSet();
      
      for (ChangeEvent obj : this)
      {
         if (lower.compareTo(obj.getOldValue()) <= 0 && obj.getOldValue().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ChangeEventSet withOldValue(String value)
   {
      for (ChangeEvent obj : this)
      {
         obj.setOldValue(value);
      }
      
      return this;
   }

   public StringList getValueType()
   {
      StringList result = new StringList();
      
      for (ChangeEvent obj : this)
      {
         result.add(obj.getValueType());
      }
      
      return result;
   }

   public ChangeEventSet hasValueType(String value)
   {
      ChangeEventSet result = new ChangeEventSet();
      
      for (ChangeEvent obj : this)
      {
         if (value.equals(obj.getValueType()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ChangeEventSet hasValueType(String lower, String upper)
   {
      ChangeEventSet result = new ChangeEventSet();
      
      for (ChangeEvent obj : this)
      {
         if (lower.compareTo(obj.getValueType()) <= 0 && obj.getValueType().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ChangeEventSet withValueType(String value)
   {
      for (ChangeEvent obj : this)
      {
         obj.setValueType(value);
      }
      
      return this;
   }

   public StringList getChangeNo()
   {
      StringList result = new StringList();
      
      for (ChangeEvent obj : this)
      {
         result.add(obj.getChangeNo());
      }
      
      return result;
   }

   public ChangeEventSet hasChangeNo(String value)
   {
      ChangeEventSet result = new ChangeEventSet();
      
      for (ChangeEvent obj : this)
      {
         if (value.equals(obj.getChangeNo()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ChangeEventSet hasChangeNo(String lower, String upper)
   {
      ChangeEventSet result = new ChangeEventSet();
      
      for (ChangeEvent obj : this)
      {
         if (lower.compareTo(obj.getChangeNo()) <= 0 && obj.getChangeNo().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ChangeEventSet withChangeNo(String value)
   {
      for (ChangeEvent obj : this)
      {
         obj.setChangeNo(value);
      }
      
      return this;
   }

   public StringList getSessionId()
   {
      StringList result = new StringList();
      
      for (ChangeEvent obj : this)
      {
         result.add(obj.getSessionId());
      }
      
      return result;
   }

   public ChangeEventSet hasSessionId(String value)
   {
      ChangeEventSet result = new ChangeEventSet();
      
      for (ChangeEvent obj : this)
      {
         if (value.equals(obj.getSessionId()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ChangeEventSet hasSessionId(String lower, String upper)
   {
      ChangeEventSet result = new ChangeEventSet();
      
      for (ChangeEvent obj : this)
      {
         if (lower.compareTo(obj.getSessionId()) <= 0 && obj.getSessionId().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ChangeEventSet withSessionId(String value)
   {
      for (ChangeEvent obj : this)
      {
         obj.setSessionId(value);
      }
      
      return this;
   }

   public StringList getPropertyKind()
   {
      StringList result = new StringList();
      
      for (ChangeEvent obj : this)
      {
         result.add(obj.getPropertyKind());
      }
      
      return result;
   }

   public ChangeEventSet hasPropertyKind(String value)
   {
      ChangeEventSet result = new ChangeEventSet();
      
      for (ChangeEvent obj : this)
      {
         if (value.equals(obj.getPropertyKind()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ChangeEventSet hasPropertyKind(String lower, String upper)
   {
      ChangeEventSet result = new ChangeEventSet();
      
      for (ChangeEvent obj : this)
      {
         if (lower.compareTo(obj.getPropertyKind()) <= 0 && obj.getPropertyKind().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ChangeEventSet withPropertyKind(String value)
   {
      for (ChangeEvent obj : this)
      {
         obj.setPropertyKind(value);
      }
      
      return this;
   }

}
