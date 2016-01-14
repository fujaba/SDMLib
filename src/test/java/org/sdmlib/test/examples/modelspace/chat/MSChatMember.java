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

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.sdmlib.StrUtil;
import org.sdmlib.serialization.PropertyChangeInterface;
import org.sdmlib.test.examples.modelspace.chat.util.MSChatChannelDescriptionSet;
   /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/modelspace/chat/ModelSpaceChatModel.java'>ModelSpaceChatModel.java</a>
*/
   public  class MSChatMember implements PropertyChangeInterface
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
      withoutChannels(this.getChannels().toArray(new MSChatChannelDescription[this.getChannels().size()]));
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
   
   public MSChatMember withName(String value)
   {
      setName(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getName());
      return result.substring(1);
   }


   
   /********************************************************************
    * <pre>
    *              many                       one
    * MSChatMember ----------------------------------- MSChatGroup
    *              members                   group
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
            oldValue.withoutMembers(this);
         }
         
         this.group = value;
         
         if (value != null)
         {
            value.withMembers(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_GROUP, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public MSChatMember withGroup(MSChatGroup value)
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
    * MSChatMember ----------------------------------- MSChatChannelDescription
    *              members                   channels
    * </pre>
    */
   
   public static final String PROPERTY_CHANNELS = "channels";

   private MSChatChannelDescriptionSet channels = null;
   
   public MSChatChannelDescriptionSet getChannels()
   {
      if (this.channels == null)
      {
         return MSChatChannelDescriptionSet.EMPTY_SET;
      }
   
      return this.channels;
   }

   public MSChatMember withChannels(MSChatChannelDescription... value)
   {
      if(value==null){
         return this;
      }
      for (MSChatChannelDescription item : value)
      {
         if (item != null)
         {
            if (this.channels == null)
            {
               this.channels = new MSChatChannelDescriptionSet();
            }
            
            boolean changed = this.channels.add (item);

            if (changed)
            {
               item.withMembers(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_CHANNELS, null, item);
            }
         }
      }
      return this;
   } 

   public MSChatMember withoutChannels(MSChatChannelDescription... value)
   {
      for (MSChatChannelDescription item : value)
      {
         if ((this.channels != null) && (item != null))
         {
            if (this.channels.remove(item))
            {
               item.withoutMembers(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_CHANNELS, item, null);
            }
         }
      }
      return this;
   }

   public MSChatChannelDescription createChannels()
   {
      MSChatChannelDescription value = new MSChatChannelDescription();
      withChannels(value);
      return value;
   } 
}
