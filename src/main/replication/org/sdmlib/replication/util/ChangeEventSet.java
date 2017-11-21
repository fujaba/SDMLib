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

import org.sdmlib.replication.ChangeEvent;

import de.uniks.networkparser.list.SimpleSet;
import de.uniks.networkparser.list.StringList;
import de.uniks.networkparser.interfaces.Condition;
import de.uniks.networkparser.list.ObjectSet;

public class ChangeEventSet extends SimpleSet<ChangeEvent>
{

   public static final ChangeEventSet EMPTY_SET = new ChangeEventSet().withFlag(ChangeEventSet.READONLY);


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



   public ChangeEventPO filterChangeEventPO()
   {
      return new ChangeEventPO(this.toArray(new ChangeEvent[this.size()]));
   }

   /**
    * Loop through the current set of ChangeEvent objects and collect those ChangeEvent objects where the objectId attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of ChangeEvent objects that match the parameter
    */
   public ChangeEventSet filterObjectId(String value)
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


   /**
    * Loop through the current set of ChangeEvent objects and collect those ChangeEvent objects where the objectId attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of ChangeEvent objects that match the parameter
    */
   public ChangeEventSet filterObjectId(String lower, String upper)
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


   /**
    * Loop through the current set of ChangeEvent objects and collect those ChangeEvent objects where the objectType attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of ChangeEvent objects that match the parameter
    */
   public ChangeEventSet filterObjectType(String value)
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


   /**
    * Loop through the current set of ChangeEvent objects and collect those ChangeEvent objects where the objectType attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of ChangeEvent objects that match the parameter
    */
   public ChangeEventSet filterObjectType(String lower, String upper)
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


   /**
    * Loop through the current set of ChangeEvent objects and collect those ChangeEvent objects where the property attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of ChangeEvent objects that match the parameter
    */
   public ChangeEventSet filterProperty(String value)
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


   /**
    * Loop through the current set of ChangeEvent objects and collect those ChangeEvent objects where the property attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of ChangeEvent objects that match the parameter
    */
   public ChangeEventSet filterProperty(String lower, String upper)
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


   /**
    * Loop through the current set of ChangeEvent objects and collect those ChangeEvent objects where the newValue attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of ChangeEvent objects that match the parameter
    */
   public ChangeEventSet filterNewValue(String value)
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


   /**
    * Loop through the current set of ChangeEvent objects and collect those ChangeEvent objects where the newValue attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of ChangeEvent objects that match the parameter
    */
   public ChangeEventSet filterNewValue(String lower, String upper)
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


   /**
    * Loop through the current set of ChangeEvent objects and collect those ChangeEvent objects where the oldValue attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of ChangeEvent objects that match the parameter
    */
   public ChangeEventSet filterOldValue(String value)
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


   /**
    * Loop through the current set of ChangeEvent objects and collect those ChangeEvent objects where the oldValue attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of ChangeEvent objects that match the parameter
    */
   public ChangeEventSet filterOldValue(String lower, String upper)
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


   /**
    * Loop through the current set of ChangeEvent objects and collect those ChangeEvent objects where the valueType attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of ChangeEvent objects that match the parameter
    */
   public ChangeEventSet filterValueType(String value)
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


   /**
    * Loop through the current set of ChangeEvent objects and collect those ChangeEvent objects where the valueType attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of ChangeEvent objects that match the parameter
    */
   public ChangeEventSet filterValueType(String lower, String upper)
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


   /**
    * Loop through the current set of ChangeEvent objects and collect those ChangeEvent objects where the propertyKind attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of ChangeEvent objects that match the parameter
    */
   public ChangeEventSet filterPropertyKind(String value)
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


   /**
    * Loop through the current set of ChangeEvent objects and collect those ChangeEvent objects where the propertyKind attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of ChangeEvent objects that match the parameter
    */
   public ChangeEventSet filterPropertyKind(String lower, String upper)
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


   /**
    * Loop through the current set of ChangeEvent objects and collect those ChangeEvent objects where the changeNo attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of ChangeEvent objects that match the parameter
    */
   public ChangeEventSet filterChangeNo(String value)
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


   /**
    * Loop through the current set of ChangeEvent objects and collect those ChangeEvent objects where the changeNo attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of ChangeEvent objects that match the parameter
    */
   public ChangeEventSet filterChangeNo(String lower, String upper)
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


   /**
    * Loop through the current set of ChangeEvent objects and collect those ChangeEvent objects where the sessionId attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of ChangeEvent objects that match the parameter
    */
   public ChangeEventSet filterSessionId(String value)
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


   /**
    * Loop through the current set of ChangeEvent objects and collect those ChangeEvent objects where the sessionId attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of ChangeEvent objects that match the parameter
    */
   public ChangeEventSet filterSessionId(String lower, String upper)
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


   public ChangeEventSet()
   {
      // empty
   }

   public ChangeEventSet(ChangeEvent... objects)
   {
      for (ChangeEvent obj : objects)
      {
         this.add(obj);
      }
   }

   public ChangeEventSet(Collection<ChangeEvent> objects)
   {
      this.addAll(objects);
   }


   public ChangeEventPO createChangeEventPO()
   {
      return new ChangeEventPO(this.toArray(new ChangeEvent[this.size()]));
   }


   @Override
   public ChangeEventSet getNewList(boolean keyValue)
   {
      return new ChangeEventSet();
   }


   public ChangeEventSet filter(Condition<ChangeEvent> condition) {
      ChangeEventSet filterList = new ChangeEventSet();
      filterItems(filterList, condition);
      return filterList;
   }
   /**
    * Loop through the current set of ChangeEvent objects and collect those ChangeEvent objects where the changeNo attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of ChangeEvent objects that match the parameter
    */
   public ChangeEventSet createChangeNoCondition(String value)
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


   /**
    * Loop through the current set of ChangeEvent objects and collect those ChangeEvent objects where the changeNo attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of ChangeEvent objects that match the parameter
    */
   public ChangeEventSet createChangeNoCondition(String lower, String upper)
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


   /**
    * Loop through the current set of ChangeEvent objects and collect those ChangeEvent objects where the newValue attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of ChangeEvent objects that match the parameter
    */
   public ChangeEventSet createNewValueCondition(String value)
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


   /**
    * Loop through the current set of ChangeEvent objects and collect those ChangeEvent objects where the newValue attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of ChangeEvent objects that match the parameter
    */
   public ChangeEventSet createNewValueCondition(String lower, String upper)
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


   /**
    * Loop through the current set of ChangeEvent objects and collect those ChangeEvent objects where the objectId attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of ChangeEvent objects that match the parameter
    */
   public ChangeEventSet createObjectIdCondition(String value)
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


   /**
    * Loop through the current set of ChangeEvent objects and collect those ChangeEvent objects where the objectId attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of ChangeEvent objects that match the parameter
    */
   public ChangeEventSet createObjectIdCondition(String lower, String upper)
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


   /**
    * Loop through the current set of ChangeEvent objects and collect those ChangeEvent objects where the objectType attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of ChangeEvent objects that match the parameter
    */
   public ChangeEventSet createObjectTypeCondition(String value)
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


   /**
    * Loop through the current set of ChangeEvent objects and collect those ChangeEvent objects where the objectType attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of ChangeEvent objects that match the parameter
    */
   public ChangeEventSet createObjectTypeCondition(String lower, String upper)
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


   /**
    * Loop through the current set of ChangeEvent objects and collect those ChangeEvent objects where the oldValue attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of ChangeEvent objects that match the parameter
    */
   public ChangeEventSet createOldValueCondition(String value)
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


   /**
    * Loop through the current set of ChangeEvent objects and collect those ChangeEvent objects where the oldValue attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of ChangeEvent objects that match the parameter
    */
   public ChangeEventSet createOldValueCondition(String lower, String upper)
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


   /**
    * Loop through the current set of ChangeEvent objects and collect those ChangeEvent objects where the property attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of ChangeEvent objects that match the parameter
    */
   public ChangeEventSet createPropertyCondition(String value)
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


   /**
    * Loop through the current set of ChangeEvent objects and collect those ChangeEvent objects where the property attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of ChangeEvent objects that match the parameter
    */
   public ChangeEventSet createPropertyCondition(String lower, String upper)
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


   /**
    * Loop through the current set of ChangeEvent objects and collect those ChangeEvent objects where the propertyKind attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of ChangeEvent objects that match the parameter
    */
   public ChangeEventSet createPropertyKindCondition(String value)
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


   /**
    * Loop through the current set of ChangeEvent objects and collect those ChangeEvent objects where the propertyKind attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of ChangeEvent objects that match the parameter
    */
   public ChangeEventSet createPropertyKindCondition(String lower, String upper)
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


   /**
    * Loop through the current set of ChangeEvent objects and collect those ChangeEvent objects where the sessionId attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of ChangeEvent objects that match the parameter
    */
   public ChangeEventSet createSessionIdCondition(String value)
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


   /**
    * Loop through the current set of ChangeEvent objects and collect those ChangeEvent objects where the sessionId attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of ChangeEvent objects that match the parameter
    */
   public ChangeEventSet createSessionIdCondition(String lower, String upper)
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


   /**
    * Loop through the current set of ChangeEvent objects and collect those ChangeEvent objects where the valueType attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of ChangeEvent objects that match the parameter
    */
   public ChangeEventSet createValueTypeCondition(String value)
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


   /**
    * Loop through the current set of ChangeEvent objects and collect those ChangeEvent objects where the valueType attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of ChangeEvent objects that match the parameter
    */
   public ChangeEventSet createValueTypeCondition(String lower, String upper)
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

}
