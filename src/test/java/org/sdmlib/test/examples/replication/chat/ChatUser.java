/*
   Copyright (c) 2014 zuendorf 
   
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
   
package org.sdmlib.test.examples.replication.chat;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.sdmlib.StrUtil;
import org.sdmlib.serialization.PropertyChangeInterface;
import org.sdmlib.test.examples.replication.chat.util.ChatChannelSet;

import de.uniks.networkparser.interfaces.SendableEntity;
import org.sdmlib.test.examples.replication.chat.ChatChannel;
import org.sdmlib.test.examples.replication.chat.ChatRoot;
   /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/replication/chat/ReplicationChatModel.java'>ReplicationChatModel.java</a>
* @see org.sdmlib.test.examples.replication.chat.ReplicationChatModel#main
 */
   public class ChatUser implements PropertyChangeInterface, SendableEntity
{

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   @Override
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
      setChatRoot(null);
      withoutChannels(this.getChannels().toArray(new ChatChannel[this.getChannels().size()]));
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              many                       one
    * ChatUser ----------------------------------- ChatRoot
    *              users                   chatRoot
    * </pre>
    */
   
   public static final String PROPERTY_CHATROOT = "chatRoot";

   private ChatRoot chatRoot = null;

   public ChatRoot getChatRoot()
   {
      return this.chatRoot;
   }

   public boolean setChatRoot(ChatRoot value)
   {
      boolean changed = false;
      
      if (this.chatRoot != value)
      {
         ChatRoot oldValue = this.chatRoot;
         
         if (this.chatRoot != null)
         {
            this.chatRoot = null;
            oldValue.withoutUsers(this);
         }
         
         this.chatRoot = value;
         
         if (value != null)
         {
            value.withUsers(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_CHATROOT, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public ChatUser withChatRoot(ChatRoot value)
   {
      setChatRoot(value);
      return this;
   } 

   public ChatRoot createChatRoot()
   {
      ChatRoot value = new ChatRoot();
      withChatRoot(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       many
    * ChatUser ----------------------------------- ChatChannel
    *              users                   channels
    * </pre>
    */
   
   public static final String PROPERTY_CHANNELS = "channels";

   private ChatChannelSet channels = null;
   
   public ChatChannelSet getChannels()
   {
      if (this.channels == null)
      {
         return ChatChannelSet.EMPTY_SET;
      }
   
      return this.channels;
   }

   public ChatUser withChannels(ChatChannel... value)
   {
      if(value==null){
         return this;
      }
      for (ChatChannel item : value)
      {
         if (item != null)
         {
            if (this.channels == null)
            {
               this.channels = new ChatChannelSet();
            }
            
            boolean changed = this.channels.add (item);

            if (changed)
            {
               item.withUsers(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_CHANNELS, null, item);
            }
         }
      }
      return this;
   } 

   public ChatUser withoutChannels(ChatChannel... value)
   {
      for (ChatChannel item : value)
      {
         if ((this.channels != null) && (item != null))
         {
            if (this.channels.remove(item))
            {
               item.withoutUsers(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_CHANNELS, item, null);
            }
         }
      }
      return this;
   }

   public ChatChannel createChannels()
   {
      ChatChannel value = new ChatChannel();
      withChannels(value);
      return value;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_USERNAME = "userName";
   
   private String userName;

   public String getUserName()
   {
      return this.userName;
   }
   
   public void setUserName(String value)
   {
      if ( ! StrUtil.stringEquals(this.userName, value))
      {
         String oldValue = this.userName;
         this.userName = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_USERNAME, oldValue, value);
      }
   }
   
   public ChatUser withUserName(String value)
   {
      setUserName(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getUserName());
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
