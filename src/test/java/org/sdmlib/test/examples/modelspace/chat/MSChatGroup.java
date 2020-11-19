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
import org.sdmlib.test.examples.modelspace.chat.util.MSChatMemberSet;

import de.uniks.networkparser.interfaces.SendableEntity;
   /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/modelspace/chat/ModelSpaceChatModel.java'>ModelSpaceChatModel.java</a>
* @see org.sdmlib.test.examples.modelspace.chat.ModelSpaceChatModel#testModelSpaceChatModel
 */
   public  class MSChatGroup implements PropertyChangeInterface, SendableEntity
{

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }
   
   public boolean addPropertyChangeListener(PropertyChangeListener listener) 
   {
      getPropertyChangeSupport().addPropertyChangeListener(listener);
      return true;
   }
   
   public boolean addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
      getPropertyChangeSupport().addPropertyChangeListener(propertyName, listener);
      return true;
   }
   
	public boolean removePropertyChangeListener(PropertyChangeListener listener) {
		if (listeners != null) {
			listeners.removePropertyChangeListener(listener);
		}
		return true;
	}

	public boolean removePropertyChangeListener(String property, PropertyChangeListener listener) {
		if (listeners != null) {
			listeners.removePropertyChangeListener(property, listener);
		}
		return true;
	}

   
   //==========================================================================
   
   
   public void removeYou()
   {
   
      withoutMembers(this.getMembers().toArray(new MSChatMember[this.getMembers().size()]));
      withoutChannels(this.getChannels().toArray(new MSChatChannelDescription[this.getChannels().size()]));
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              one                       many
    * MSChatGroup ----------------------------------- MSChatMember
    *              group                   members
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

   public MSChatGroup withMembers(MSChatMember... value)
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
               item.withGroup(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_MEMBERS, null, item);
            }
         }
      }
      return this;
   } 

   public MSChatGroup withoutMembers(MSChatMember... value)
   {
      for (MSChatMember item : value)
      {
         if ((this.members != null) && (item != null))
         {
            if (this.members.remove(item))
            {
               item.setGroup(null);
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

   
   /********************************************************************
    * <pre>
    *              one                       many
    * MSChatGroup ----------------------------------- MSChatChannelDescription
    *              group                   channels
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

   public MSChatGroup withChannels(MSChatChannelDescription... value)
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
               item.withGroup(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_CHANNELS, null, item);
            }
         }
      }
      return this;
   } 

   public MSChatGroup withoutChannels(MSChatChannelDescription... value)
   {
      for (MSChatChannelDescription item : value)
      {
         if ((this.channels != null) && (item != null))
         {
            if (this.channels.remove(item))
            {
               item.setGroup(null);
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

   
   //==========================================================================
   
   public static final String PROPERTY_TASK = "task";
   
   private String task;

   public String getTask()
   {
      return this.task;
   }
   
   public void setTask(String value)
   {
      if ( ! StrUtil.stringEquals(this.task, value)) {
      
         String oldValue = this.task;
         this.task = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TASK, oldValue, value);
      }
   }
   
   public MSChatGroup withTask(String value)
   {
      setTask(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getTask());
      return result.substring(1);
   }


   public boolean firePropertyChange(String propertyName, Object oldValue, Object newValue)
   {
      if (listeners != null) {
   		listeners.firePropertyChange(propertyName, oldValue, newValue);
   		return true;
   	}
   	return false;
   }
   }
