/*
   Copyright (c) 2012 zuendorf 

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

package org.sdmlib.examples.chats;

import java.beans.PropertyChangeSupport;

import org.sdmlib.model.taskflows.Logger;
import org.sdmlib.model.taskflows.PeerProxy;
import org.sdmlib.model.taskflows.TaskFlow;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;
import org.sdmlib.utils.PropertyChangeInterface;
import org.sdmlib.utils.StrUtil;

public class TestChatMessageFlow extends TaskFlow implements PropertyChangeInterface
{
   //==========================================================================

   enum TaskNames 
   {
      Start, Click, Exec
   }

   @Override
   public Object[] getTaskNames()
   {
      return TaskNames.values();
   }

   @Override
   public void run()
   {
      switch (TaskNames.values()[taskNo])
      {
      case Start:
         switchTo(new PeerProxy("localhost", 11113, idMap));
         break;

      case Click:
         // prepare gui text field
         PeerToPeerChat gui = (PeerToPeerChat) getIdMap().getObject(PeerToPeerChat.MY_GUI); 
         gui.getNewMessageText().setText(msg);

         // call click handler
         this.withSubFlow(
            ((Logger) new Logger()
            .withSubFlow( new CSChatMessageFlow().withIdMap((SDMLibJsonIdMap) idMap)))
            .withStartPeer(new PeerProxy("localhost", 42424, idMap)));

         taskNo++;
         this.run();

         break;

      case Exec:
         boolean lastAction = getSubFlow().getTaskNo() + 1 >= getSubFlow().getTaskNames().length;

         getSubFlow().run();

         if (lastAction)
         {
            synchronized (idMap)
            {
               idMap.notifyAll();
            }
         }
         
         break;
      
      default:
         break;
      }

   }

   private PeerProxy testTarget = null;

   public TestChatMessageFlow withTestTarget(PeerProxy target)
   {
      this.testTarget = target; 
      return this;
   }

   //==========================================================================

   public Object get(String attrName)
   {
      if (PROPERTY_MSG.equalsIgnoreCase(attrName))
      {
         return getMsg();
      }

      if (PROPERTY_TASKNO.equalsIgnoreCase(attrName))
      {
         return getTaskNo();
      }
      
      return super.get(attrName);
   }


   //==========================================================================

   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_MSG.equalsIgnoreCase(attrName))
      {
         setMsg((String) value);
         return true;
      }

      if (PROPERTY_TASKNO.equalsIgnoreCase(attrName))
      {
         setTaskNo(Integer.parseInt(value.toString()));
         return true;
      }

      return super.set(attrName, value);
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
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
      super.removeYou();
   }


   //==========================================================================

   public static final String PROPERTY_MSG = "msg";

   private String msg;

   public String getMsg()
   {
      return this.msg;
   }

   public void setMsg(String value)
   {
      if ( ! StrUtil.stringEquals(this.msg, value))
      {
         String oldValue = this.msg;
         this.msg = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_MSG, oldValue, value);
      }
   }

   public TestChatMessageFlow withMsg(String value)
   {
      setMsg(value);
      return this;
   } 

   public String toString()
   {
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getMsg());
      _.append(" ").append(this.getTaskNo());
      return _.substring(1);
   }

}

