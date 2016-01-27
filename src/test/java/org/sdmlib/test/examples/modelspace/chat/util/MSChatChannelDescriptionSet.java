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

import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.test.examples.modelspace.chat.MSChatChannelDescription;
import org.sdmlib.test.examples.modelspace.chat.MSChatGroup;
import org.sdmlib.test.examples.modelspace.chat.MSChatMember;

import de.uniks.networkparser.list.SimpleSet;

public class MSChatChannelDescriptionSet extends SimpleSet<MSChatChannelDescription>
{

   public static final MSChatChannelDescriptionSet EMPTY_SET = new MSChatChannelDescriptionSet().withFlag(MSChatChannelDescriptionSet.READONLY);


   public MSChatChannelDescriptionPO hasMSChatChannelDescriptionPO()
   {
      return new MSChatChannelDescriptionPO(this.toArray(new MSChatChannelDescription[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.modelspace.chat.MSChatChannelDescription";
   }


   @SuppressWarnings("unchecked")
   public MSChatChannelDescriptionSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<MSChatChannelDescription>)value);
      }
      else if (value != null)
      {
         this.add((MSChatChannelDescription) value);
      }
      
      return this;
   }
   
   public MSChatChannelDescriptionSet without(MSChatChannelDescription value)
   {
      this.remove(value);
      return this;
   }

   public StringList getName()
   {
      StringList result = new StringList();
      
      for (MSChatChannelDescription obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public MSChatChannelDescriptionSet hasName(String value)
   {
      MSChatChannelDescriptionSet result = new MSChatChannelDescriptionSet();
      
      for (MSChatChannelDescription obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public MSChatChannelDescriptionSet hasName(String lower, String upper)
   {
      MSChatChannelDescriptionSet result = new MSChatChannelDescriptionSet();
      
      for (MSChatChannelDescription obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public MSChatChannelDescriptionSet withName(String value)
   {
      for (MSChatChannelDescription obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }

   public StringList getLocation()
   {
      StringList result = new StringList();
      
      for (MSChatChannelDescription obj : this)
      {
         result.add(obj.getLocation());
      }
      
      return result;
   }

   public MSChatChannelDescriptionSet hasLocation(String value)
   {
      MSChatChannelDescriptionSet result = new MSChatChannelDescriptionSet();
      
      for (MSChatChannelDescription obj : this)
      {
         if (value.equals(obj.getLocation()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public MSChatChannelDescriptionSet hasLocation(String lower, String upper)
   {
      MSChatChannelDescriptionSet result = new MSChatChannelDescriptionSet();
      
      for (MSChatChannelDescription obj : this)
      {
         if (lower.compareTo(obj.getLocation()) <= 0 && obj.getLocation().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public MSChatChannelDescriptionSet withLocation(String value)
   {
      for (MSChatChannelDescription obj : this)
      {
         obj.setLocation(value);
      }
      
      return this;
   }

   public MSChatGroupSet getGroup()
   {
      MSChatGroupSet result = new MSChatGroupSet();
      
      for (MSChatChannelDescription obj : this)
      {
         result.add(obj.getGroup());
      }
      
      return result;
   }

   public MSChatChannelDescriptionSet hasGroup(Object value)
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
      
      MSChatChannelDescriptionSet answer = new MSChatChannelDescriptionSet();
      
      for (MSChatChannelDescription obj : this)
      {
         if (neighbors.contains(obj.getGroup()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public MSChatChannelDescriptionSet withGroup(MSChatGroup value)
   {
      for (MSChatChannelDescription obj : this)
      {
         obj.withGroup(value);
      }
      
      return this;
   }

   public MSChatMemberSet getMembers()
   {
      MSChatMemberSet result = new MSChatMemberSet();
      
      for (MSChatChannelDescription obj : this)
      {
         result.addAll(obj.getMembers());
      }
      
      return result;
   }

   public MSChatChannelDescriptionSet hasMembers(Object value)
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
      
      MSChatChannelDescriptionSet answer = new MSChatChannelDescriptionSet();
      
      for (MSChatChannelDescription obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getMembers()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public MSChatChannelDescriptionSet withMembers(MSChatMember value)
   {
      for (MSChatChannelDescription obj : this)
      {
         obj.withMembers(value);
      }
      
      return this;
   }

   public MSChatChannelDescriptionSet withoutMembers(MSChatMember value)
   {
      for (MSChatChannelDescription obj : this)
      {
         obj.withoutMembers(value);
      }
      
      return this;
   }

}
