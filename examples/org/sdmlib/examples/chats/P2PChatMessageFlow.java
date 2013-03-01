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
   
package org.sdmlib.examples.chats;

import java.beans.PropertyChangeSupport;

import org.sdmlib.model.taskflows.TaskFlow;
import org.sdmlib.utils.PropertyChangeInterface;
import org.sdmlib.utils.StrUtil;

public class P2PChatMessageFlow extends TaskFlow implements PropertyChangeInterface
{
   enum TaskNames 
   {
      Start, AtOtherPeer
   }

   @Override
   public Object[] getTaskNames()
   {
      return TaskNames.values();
   }

   
   //==========================================================================
   
   public void run(  )
   {
      PeerToPeerChat gui;
      
      switch (TaskNames.values()[taskNo])
      {
      case Start:
         // get the new message
         gui = (PeerToPeerChat) getIdMap().getObject(PeerToPeerChat.MY_GUI);
         
         msg = gui.getNameLabel().getText() + ": " + gui.getNewMessageText().getText() + "\n";
         
         gui.getNewMessageText().setText("");
         
         pos = gui.getAllMessages().getText().length();
         
         // spread the word
         switchTo(TaskNames.AtOtherPeer.ordinal(), gui.getPeer(), gui.getProxies());
         break;
         
      case AtOtherPeer:
         gui = (PeerToPeerChat) getIdMap().getObject(PeerToPeerChat.MY_GUI); 
         
         // if I already know that msg do nothing
         int foundPos = gui.getAllMessages().getText().indexOf(msg.trim(), pos);
         if ( foundPos < 0 )
         {
            // new msg. Add it and spread the word.
            gui.getAllMessages().append(msg);
            
            switchTo(TaskNames.AtOtherPeer.ordinal(), gui.getPeer(), gui.getProxies());
         }
         break;
         
      default:
         break;
      }

   }

   
   //==========================================================================
   
   public Object get(String attrName)
   {
      if (PROPERTY_MSG.equalsIgnoreCase(attrName))
      {
         return getMsg();
      }

      if (PROPERTY_POS.equalsIgnoreCase(attrName))
      {
         return getPos();
      }

      if (PROPERTY_TASKNO.equalsIgnoreCase(attrName))
      {
         return getTaskNo();
      }

      if (PROPERTY_IDMAP.equalsIgnoreCase(attrName))
      {
         return getIdMap();
      }

      if (PROPERTY_SUBFLOW.equalsIgnoreCase(attrName))
      {
         return getSubFlow();
      }

      if (PROPERTY_PARENT.equalsIgnoreCase(attrName))
      {
         return getParent();
      }

      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_MSG.equalsIgnoreCase(attrName))
      {
         setMsg((String) value);
         return true;
      }

      if (PROPERTY_POS.equalsIgnoreCase(attrName))
      {
         setPos(Integer.parseInt(value.toString()));
         return true;
      }

      if (PROPERTY_TASKNO.equalsIgnoreCase(attrName))
      {
         setTaskNo(Integer.parseInt(value.toString()));
         return true;
      }

      if (PROPERTY_IDMAP.equalsIgnoreCase(attrName))
      {
         setIdMap((org.sdmlib.serialization.json.SDMLibJsonIdMap) value);
         return true;
      }

      if (PROPERTY_SUBFLOW.equalsIgnoreCase(attrName))
      {
         setSubFlow((TaskFlow) value);
         return true;
      }

      if (PROPERTY_PARENT.equalsIgnoreCase(attrName))
      {
         setParent((TaskFlow) value);
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
      setSubFlow(null);
      setParent(null);
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
   
   public P2PChatMessageFlow withMsg(String value)
   {
      setMsg(value);
      return this;
   } 

   public String toString()
   {
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getMsg());
      _.append(" ").append(this.getPos());
      _.append(" ").append(this.getTaskNo());
      return _.substring(1);
   }


   
   //==========================================================================
   
   public static final String PROPERTY_POS = "pos";
   
   private int pos;

   public int getPos()
   {
      return this.pos;
   }
   
   public void setPos(int value)
   {
      if (this.pos != value)
      {
         int oldValue = this.pos;
         this.pos = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_POS, oldValue, value);
      }
   }
   
   public P2PChatMessageFlow withPos(int value)
   {
      setPos(value);
      return this;
   } 
}

