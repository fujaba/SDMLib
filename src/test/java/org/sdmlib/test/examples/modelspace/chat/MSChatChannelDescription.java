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
   
package org.sdmlib.test.examples.modelspace.chat;

import org.sdmlib.serialization.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import org.sdmlib.StrUtil;
import org.sdmlib.test.examples.modelspace.chat.util.MSChatMemberSet;

public  class MSChatChannelDescription implements PropertyChangeInterface
{

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }
   
   public void addPropertyChangeListener(PropertyChangeListener listener) 
   {
      getPropertyChangeSupport().addPropertyChangeListener(listener);
   }

   
   //==========================================================================
   
   
   public void removeYou()
   {
   
      setGroup(null);
      withoutMembers(this.getMembers().toArray(new MSChatMember[this.getMembers().size()]));
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_NAME = "name";
   
   private String name;

   public String getName()
   {
      return this.name;
   }
   
   public void setName(String value)
   {
      if ( ! StrUtil.stringEquals(this.name, value)) {
      
         String oldValue = this.name;
         this.name = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_NAME, oldValue, value);
      }
   }
   
   public MSChatChannelDescription withName(String value)
   {
      setName(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getName());
      result.append(" ").append(this.getLocation());
      return result.substring(1);
   }


   
   //==========================================================================
   
   public static final String PROPERTY_LOCATION = "location";
   
   private String location;

   public String getLocation()
   {
      return this.location;
   }
   
   public void setLocation(String value)
   {
      if ( ! StrUtil.stringEquals(this.location, value)) {
      
         String oldValue = this.location;
         this.location = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_LOCATION, oldValue, value);
      }
   }
   
   public MSChatChannelDescription withLocation(String value)
   {
      setLocation(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * MSChatChannelDescription ----------------------------------- MSChatGroup
    *              channels                   group
    * </pre>
    */
   
   public static final String PROPERTY_GROUP = "group";

   private MSChatGroup group = null;

   public MSChatGroup getGroup()
   {
      return this.group;
   }

   public boolean setGroup(MSChatGroup value)
   {
      boolean changed = false;
      
      if (this.group != value)
      {
         MSChatGroup oldValue = this.group;
         
         if (this.group != null)
         {
            this.group = null;
            oldValue.withoutChannels(this);
         }
         
         this.group = value;
         
         if (value != null)
         {
            value.withChannels(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_GROUP, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public MSChatChannelDescription withGroup(MSChatGroup value)
   {
      setGroup(value);
      return this;
   } 

   public MSChatGroup createGroup()
   {
      MSChatGroup value = new MSChatGroup();
      withGroup(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       many
    * MSChatChannelDescription ----------------------------------- MSChatMember
    *              channels                   members
    * </pre>
    */
   
   public static final String PROPERTY_MEMBERS = "members";

   private MSChatMemberSet members = null;
   
   public MSChatMemberSet getMembers()
   {
      if (this.members == null)
      {
         return MSChatMemberSet.EMPTY_SET;
      }
   
      return this.members;
   }

   public MSChatChannelDescription withMembers(MSChatMember... value)
   {
      if(value==null){
         return this;
      }
      for (MSChatMember item : value)
      {
         if (item != null)
         {
            if (this.members == null)
            {
               this.members = new MSChatMemberSet();
            }
            
            boolean changed = this.members.add (item);

            if (changed)
            {
               item.withChannels(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_MEMBERS, null, item);
            }
         }
      }
      return this;
   } 

   public MSChatChannelDescription withoutMembers(MSChatMember... value)
   {
      for (MSChatMember item : value)
      {
         if ((this.members != null) && (item != null))
         {
            if (this.members.remove(item))
            {
               item.withoutChannels(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_MEMBERS, item, null);
            }
         }
      }
      return this;
   }

   public MSChatMember createMembers()
   {
      MSChatMember value = new MSChatMember();
      withMembers(value);
      return value;
   } 
}
