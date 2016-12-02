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
   
package org.sdmlib.test.examples.modelspace.chat.util;

import java.util.Collection;
import java.util.Collections;

import org.sdmlib.test.examples.modelspace.chat.MSChatChannelDescription;
import org.sdmlib.test.examples.modelspace.chat.MSChatGroup;
import org.sdmlib.test.examples.modelspace.chat.MSChatMember;

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;
import de.uniks.networkparser.list.StringList;

public class MSChatMemberSet extends SimpleSet<MSChatMember>
{

   public static final MSChatMemberSet EMPTY_SET = new MSChatMemberSet().withFlag(MSChatMemberSet.READONLY);


   public MSChatMemberPO hasMSChatMemberPO()
   {
      return new MSChatMemberPO(this.toArray(new MSChatMember[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.modelspace.chat.MSChatMember";
   }


   @SuppressWarnings("unchecked")
   public MSChatMemberSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<MSChatMember>)value);
      }
      else if (value != null)
      {
         this.add((MSChatMember) value);
      }
      
      return this;
   }
   
   public MSChatMemberSet without(MSChatMember value)
   {
      this.remove(value);
      return this;
   }

   public StringList getName()
   {
      StringList result = new StringList();
      
      for (MSChatMember obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public MSChatMemberSet hasName(String value)
   {
      MSChatMemberSet result = new MSChatMemberSet();
      
      for (MSChatMember obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public MSChatMemberSet hasName(String lower, String upper)
   {
      MSChatMemberSet result = new MSChatMemberSet();
      
      for (MSChatMember obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public MSChatMemberSet withName(String value)
   {
      for (MSChatMember obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }

   public MSChatGroupSet getGroup()
   {
      MSChatGroupSet result = new MSChatGroupSet();
      
      for (MSChatMember obj : this)
      {
         result.add(obj.getGroup());
      }
      
      return result;
   }

   public MSChatMemberSet hasGroup(Object value)
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
      
      MSChatMemberSet answer = new MSChatMemberSet();
      
      for (MSChatMember obj : this)
      {
         if (neighbors.contains(obj.getGroup()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public MSChatMemberSet withGroup(MSChatGroup value)
   {
      for (MSChatMember obj : this)
      {
         obj.withGroup(value);
      }
      
      return this;
   }

   public MSChatChannelDescriptionSet getChannels()
   {
      MSChatChannelDescriptionSet result = new MSChatChannelDescriptionSet();
      
      for (MSChatMember obj : this)
      {
         result.addAll(obj.getChannels());
      }
      
      return result;
   }

   public MSChatMemberSet hasChannels(Object value)
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
      
      MSChatMemberSet answer = new MSChatMemberSet();
      
      for (MSChatMember obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getChannels()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public MSChatMemberSet withChannels(MSChatChannelDescription value)
   {
      for (MSChatMember obj : this)
      {
         obj.withChannels(value);
      }
      
      return this;
   }

   public MSChatMemberSet withoutChannels(MSChatChannelDescription value)
   {
      for (MSChatMember obj : this)
      {
         obj.withoutChannels(value);
      }
      
      return this;
   }



   public MSChatMemberPO filterMSChatMemberPO()
   {
      return new MSChatMemberPO(this.toArray(new MSChatMember[this.size()]));
   }

   /**
    * Loop through the current set of MSChatMember objects and collect those MSChatMember objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of MSChatMember objects that match the parameter
    */
   public MSChatMemberSet filterName(String value)
   {
      MSChatMemberSet result = new MSChatMemberSet();
      
      for (MSChatMember obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of MSChatMember objects and collect those MSChatMember objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of MSChatMember objects that match the parameter
    */
   public MSChatMemberSet filterName(String lower, String upper)
   {
      MSChatMemberSet result = new MSChatMemberSet();
      
      for (MSChatMember obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   public MSChatMemberSet()
   {
      // empty
   }

   public MSChatMemberSet(MSChatMember... objects)
   {
      for (MSChatMember obj : objects)
      {
         this.add(obj);
      }
   }

   public MSChatMemberSet(Collection<MSChatMember> objects)
   {
      this.addAll(objects);
   }
}
