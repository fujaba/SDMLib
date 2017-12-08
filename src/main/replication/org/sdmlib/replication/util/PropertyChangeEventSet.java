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

import java.beans.PropertyChangeEvent;
import java.util.Collection;

import de.uniks.networkparser.list.SimpleSet;

public class PropertyChangeEventSet extends SimpleSet<PropertyChangeEvent>
{

   public static final PropertyChangeEventSet EMPTY_SET = new PropertyChangeEventSet().withFlag(PropertyChangeEventSet.READONLY);


   public PropertyChangeEventPO hasPropertyChangeEventPO()
   {
      return new PropertyChangeEventPO(this.toArray(new PropertyChangeEvent[this.size()]));
   }


   public String getEntryType()
   {
      return "java.beans.PropertyChangeEvent";
   }


   @SuppressWarnings("unchecked")
   public PropertyChangeEventSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<PropertyChangeEvent>)value);
      }
      else if (value != null)
      {
         this.add((PropertyChangeEvent) value);
      }
      
      return this;
   }
   
   public PropertyChangeEventSet without(PropertyChangeEvent value)
   {
      this.remove(value);
      return this;
   }



   public PropertyChangeEventPO filterPropertyChangeEventPO()
   {
      return new PropertyChangeEventPO(this.toArray(new PropertyChangeEvent[this.size()]));
   }

   public PropertyChangeEventSet()
   {
      // empty
   }

   public PropertyChangeEventSet(PropertyChangeEvent... objects)
   {
      for (PropertyChangeEvent obj : objects)
      {
         this.add(obj);
      }
   }

   public PropertyChangeEventSet(Collection<PropertyChangeEvent> objects)
   {
      this.addAll(objects);
   }


   public PropertyChangeEventPO createPropertyChangeEventPO()
   {
      return new PropertyChangeEventPO(this.toArray(new PropertyChangeEvent[this.size()]));
   }


   @Override
   public PropertyChangeEventSet getNewList(boolean keyValue)
   {
      return new PropertyChangeEventSet();
   }
}