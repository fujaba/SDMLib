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
import org.sdmlib.test.examples.replication.chat.util.ChatMsgSet;
import de.uniks.networkparser.interfaces.SendableEntity;
import org.sdmlib.test.examples.replication.chat.ChatChannel;
   /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/replication/chat/ReplicationChatModel.java'>ReplicationChatModel.java</a>
*/
   public class ChatMsg implements PropertyChangeInterface, SendableEntity
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
      setChannel(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_TEXT = "text";
   
   private String text;

   public String getText()
   {
      return this.text;
   }
   
   public void setText(String value)
   {
      if ( ! StrUtil.stringEquals(this.text, value))
      {
         String oldValue = this.text;
         this.text = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TEXT, oldValue, value);
      }
   }
   
   public ChatMsg withText(String value)
   {
      setText(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getText());
      result.append(" ").append(this.getSender());
      return result.substring(1);
   }


   
   //==========================================================================
   
   public static final String PROPERTY_TIME = "time";
   
   private long time;

   public long getTime()
   {
      return this.time;
   }
   
   public void setTime(long value)
   {
      if (this.time != value)
      {
         long oldValue = this.time;
         this.time = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TIME, oldValue, value);
      }
   }
   
   public ChatMsg withTime(long value)
   {
      setTime(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_SENDER = "sender";
   
   private String sender;

   public String getSender()
   {
      return this.sender;
   }
   
   public void setSender(String value)
   {
      if ( ! StrUtil.stringEquals(this.sender, value))
      {
         String oldValue = this.sender;
         this.sender = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_SENDER, oldValue, value);
      }
   }
   
   public ChatMsg withSender(String value)
   {
      setSender(value);
      return this;
   } 

   
   public static final ChatMsgSet EMPTY_SET = new ChatMsgSet().withFlag(ChatMsgSet.READONLY);

  

   
   /********************************************************************
    * <pre>
    *              many                       one
    * ChatMsg ----------------------------------- ChatChannel
    *              msgs                   channel
    * </pre>
    */
   
   public static final String PROPERTY_CHANNEL = "channel";

   private ChatChannel channel = null;

   public ChatChannel getChannel()
   {
      return this.channel;
   }

   public boolean setChannel(ChatChannel value)
   {
      boolean changed = false;
      
      if (this.channel != value)
      {
         ChatChannel oldValue = this.channel;
         
         if (this.channel != null)
         {
            this.channel = null;
            oldValue.withoutMsgs(this);
         }
         
         this.channel = value;
         
         if (value != null)
         {
            value.withMsgs(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_CHANNEL, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public ChatMsg withChannel(ChatChannel value)
   {
      setChannel(value);
      return this;
   } 

   public ChatChannel createChannel()
   {
      ChatChannel value = new ChatChannel();
      withChannel(value);
      return value;
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
