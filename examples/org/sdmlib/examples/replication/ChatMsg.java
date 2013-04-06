/*
   Copyright (c) 2013 zuendorf 
   
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
   
package org.sdmlib.examples.replication;

import org.sdmlib.utils.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import org.sdmlib.utils.StrUtil;
import org.sdmlib.examples.replication.creators.ChatMsgSet;

public class ChatMsg implements PropertyChangeInterface
{

   
   //==========================================================================
   
   public Object get(String attrName)
   {
      if (PROPERTY_TEXT.equalsIgnoreCase(attrName))
      {
         return getText();
      }

      if (PROPERTY_TIME.equalsIgnoreCase(attrName))
      {
         return getTime();
      }

      if (PROPERTY_SENDER.equalsIgnoreCase(attrName))
      {
         return getSender();
      }

      if (PROPERTY_ROOT.equalsIgnoreCase(attrName))
      {
         return getRoot();
      }

      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_TEXT.equalsIgnoreCase(attrName))
      {
         setText((String) value);
         return true;
      }

      if (PROPERTY_TIME.equalsIgnoreCase(attrName))
      {
         setTime(Long.parseLong(value.toString()));
         return true;
      }

      if (PROPERTY_SENDER.equalsIgnoreCase(attrName))
      {
         setSender((String) value);
         return true;
      }

      if (PROPERTY_ROOT.equalsIgnoreCase(attrName))
      {
         setRoot((ChatRoot) value);
         return true;
      }

      return false;
   }

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }

   
   //==========================================================================
   
   public void removeYou()
   {
      setRoot(null);
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

   public String toString()
   {
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getText());
      _.append(" ").append(this.getSender());
      return _.substring(1);
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

   
   public static final ChatMsgSet EMPTY_SET = new ChatMsgSet();

   
   /********************************************************************
    * <pre>
    *              many                       one
    * ChatMsg ----------------------------------- ChatRoot
    *              msgs                   root
    * </pre>
    */
   
   public static final String PROPERTY_ROOT = "root";
   
   private ChatRoot root = null;
   
   public ChatRoot getRoot()
   {
      return this.root;
   }
   
   public boolean setRoot(ChatRoot value)
   {
      boolean changed = false;
      
      if (this.root != value)
      {
         ChatRoot oldValue = this.root;
         
         if (this.root != null)
         {
            this.root = null;
            oldValue.withoutMsgs(this);
         }
         
         this.root = value;
         
         if (value != null)
         {
            value.withMsgs(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_ROOT, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public ChatMsg withRoot(ChatRoot value)
   {
      setRoot(value);
      return this;
   } 
   
   public ChatRoot createRoot()
   {
      ChatRoot value = new ChatRoot();
      withRoot(value);
      return value;
   } 
}

