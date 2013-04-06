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
import org.sdmlib.examples.replication.creators.ChatMsgSet;
import java.util.LinkedHashSet;
import org.sdmlib.serialization.json.JsonIdMap;

public class ChatRoot implements PropertyChangeInterface
{

   
   //==========================================================================
   
   public Object get(String attrName)
   {
      if (PROPERTY_MSGS.equalsIgnoreCase(attrName))
      {
         return getMsgs();
      }

      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_MSGS.equalsIgnoreCase(attrName))
      {
         addToMsgs((ChatMsg) value);
         return true;
      }
      
      if ((PROPERTY_MSGS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromMsgs((ChatMsg) value);
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
      removeAllFromMsgs();
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              one                       many
    * ChatRoot ----------------------------------- ChatMsg
    *              root                   msgs
    * </pre>
    */
   
   public static final String PROPERTY_MSGS = "msgs";
   
   private ChatMsgSet msgs = null;
   
   public ChatMsgSet getMsgs()
   {
      if (this.msgs == null)
      {
         return ChatMsg.EMPTY_SET;
      }
   
      return this.msgs;
   }
   
   public boolean addToMsgs(ChatMsg value)
   {
      boolean changed = false;
      
      if (value != null)
      {
         if (this.msgs == null)
         {
            this.msgs = new ChatMsgSet();
         }
         
         changed = this.msgs.add (value);
         
         if (changed)
         {
            value.withRoot(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_MSGS, null, value);
         }
      }
         
      return changed;   
   }
   
   public boolean removeFromMsgs(ChatMsg value)
   {
      boolean changed = false;
      
      if ((this.msgs != null) && (value != null))
      {
         changed = this.msgs.remove (value);
         
         if (changed)
         {
            value.setRoot(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_MSGS, value, null);
         }
      }
         
      return changed;   
   }
   
   public ChatRoot withMsgs(ChatMsg value)
   {
      addToMsgs(value);
      return this;
   } 
   
   public ChatRoot withoutMsgs(ChatMsg value)
   {
      removeFromMsgs(value);
      return this;
   } 
   
   public void removeAllFromMsgs()
   {
      LinkedHashSet<ChatMsg> tmpSet = new LinkedHashSet<ChatMsg>(this.getMsgs());
   
      for (ChatMsg value : tmpSet)
      {
         this.removeFromMsgs(value);
      }
   }
   
   public ChatMsg createMsgs()
   {
      ChatMsg value = new ChatMsg();
      withMsgs(value);
      return value;
   } 
}

