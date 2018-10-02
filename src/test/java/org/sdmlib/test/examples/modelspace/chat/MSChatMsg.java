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
import java.text.SimpleDateFormat;
import java.util.Date;

import org.sdmlib.StrUtil;
import org.sdmlib.serialization.PropertyChangeInterface;

import de.uniks.networkparser.interfaces.SendableEntity;
   /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/modelspace/chat/ModelSpaceChatModel.java'>ModelSpaceChatModel.java</a>
* @see org.sdmlib.test.examples.modelspace.chat.ModelSpaceChatModel#testModelSpaceChatModel
 */
   public  class MSChatMsg implements PropertyChangeInterface, SendableEntity
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

	public boolean removePropertyChangeListener(String property,
			PropertyChangeListener listener) {
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
      if ( ! StrUtil.stringEquals(this.text, value)) {
      
         String oldValue = this.text;
         this.text = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TEXT, oldValue, value);
      }
   }
   
   public MSChatMsg withText(String value)
   {
      setText(value);
      return this;
   } 

   private SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      
      result.append(dateFormat.format(new Date(this.getTime())));
      result.append(" ").append(this.getSender());
      result.append(": ").append(this.getText());
      return result.toString();
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
      if (this.time != value) {
      
         long oldValue = this.time;
         this.time = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TIME, oldValue, value);
      }
   }
   
   public MSChatMsg withTime(long value)
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
      if ( ! StrUtil.stringEquals(this.sender, value)) {
      
         String oldValue = this.sender;
         this.sender = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_SENDER, oldValue, value);
      }
   }
   
   public MSChatMsg withSender(String value)
   {
      setSender(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * MSChatMsg ----------------------------------- MSChatChannel
    *              msgs                   channel
    * </pre>
    */
   
   public static final String PROPERTY_CHANNEL = "channel";

   private MSChatChannel channel = null;

   public MSChatChannel getChannel()
   {
      return this.channel;
   }

   public boolean setChannel(MSChatChannel value)
   {
      boolean changed = false;
      
      if (this.channel != value)
      {
         MSChatChannel oldValue = this.channel;
         
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

   public MSChatMsg withChannel(MSChatChannel value)
   {
      setChannel(value);
      return this;
   } 

   public MSChatChannel createChannel()
   {
      MSChatChannel value = new MSChatChannel();
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
