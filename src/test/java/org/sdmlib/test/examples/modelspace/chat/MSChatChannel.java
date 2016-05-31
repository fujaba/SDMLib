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
import org.sdmlib.test.examples.modelspace.chat.util.MSChatMsgSet;
import de.uniks.networkparser.interfaces.SendableEntity;
import org.sdmlib.test.examples.modelspace.chat.MSChatMsg;

/**
 * 
 * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/modelspace/chat/MSChatClientTest.java'>MSChatClientTest.java</a>
 * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/modelspace/chat/ModelSpaceChatModel.java'>ModelSpaceChatModel.java</a>
*/
public  class MSChatChannel implements PropertyChangeInterface, SendableEntity
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
      getPropertyChangeSupport().removePropertyChangeListener(listener);
      return true;
   }

   
   //==========================================================================
   
   
   public void removeYou()
   {
   
      withoutMsgs(this.getMsgs().toArray(new MSChatMsg[this.getMsgs().size()]));
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              one                       many
    * MSChatChannel ----------------------------------- MSChatMsg
    *              channel                   msgs
    * </pre>
    */
   
   public static final String PROPERTY_MSGS = "msgs";

   private MSChatMsgSet msgs = null;
   
   public MSChatMsgSet getMsgs()
   {
      if (this.msgs == null)
      {
         return MSChatMsgSet.EMPTY_SET;
      }
   
      return this.msgs;
   }

   public MSChatChannel withMsgs(MSChatMsg... value)
   {
      if(value==null){
         return this;
      }
      for (MSChatMsg item : value)
      {
         if (item != null)
         {
            if (this.msgs == null)
            {
               this.msgs = new MSChatMsgSet();
            }
            
            boolean changed = this.msgs.add (item);

            if (changed)
            {
               item.withChannel(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_MSGS, null, item);
            }
         }
      }
      return this;
   } 

   public MSChatChannel withoutMsgs(MSChatMsg... value)
   {
      for (MSChatMsg item : value)
      {
         if ((this.msgs != null) && (item != null))
         {
            if (this.msgs.remove(item))
            {
               item.setChannel(null);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_MSGS, item, null);
            }
         }
      }
      return this;
   }

   public MSChatMsg createMsgs()
   {
      MSChatMsg value = new MSChatMsg();
      withMsgs(value);
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
   
   public MSChatChannel withTask(String value)
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
