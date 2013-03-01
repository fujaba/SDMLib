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

import org.sdmlib.model.taskflows.PeerProxy;
import org.sdmlib.model.taskflows.TaskFlow;
import org.sdmlib.utils.PropertyChangeInterface;

public class CSVisitAllClientsFlow extends TaskFlow implements PropertyChangeInterface
{
   enum TaskNames
   {
      HandleClick, ServerHandleMessage, ClientHandleMessage, 
   }
   
   public Object[] getTaskNames()
	{
		return TaskNames.values();
	}
   
   //==========================================================================
   
   public void run(  )
   {
      switch (TaskNames.values()[taskNo])
      {
      case HandleClick:
         PeerToPeerChat gui = (PeerToPeerChat) idMap.getObject(PeerToPeerChat.MY_GUI);
         
         switchTo(gui.getPeer());
         break;

      case ServerHandleMessage:
         ChatServer chatServer = (ChatServer) idMap.getObject(ChatServer.CHAT_SERVER);
         
         // resend to all clients
         for (PeerProxy peer : chatServer.getAllPeers())
         {
            switchTo(peer);
            taskNo--;
         }
         break;
      
      case ClientHandleMessage:
         getTgtTask().run();
         break;
         
      default:
         break;
      }
      
   }

   
   //==========================================================================
   
   public Object get(String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (PROPERTY_IDMAP.equalsIgnoreCase(attribute))
      {
         return getIdMap();
      }

      if (PROPERTY_TASKNO.equalsIgnoreCase(attribute))
      {
         return getTaskNo();
      }

      if (PROPERTY_TGTTASK.equalsIgnoreCase(attrName))
      {
         return getTgtTask();
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
      if (PROPERTY_IDMAP.equalsIgnoreCase(attrName))
      {
         setIdMap((org.sdmlib.serialization.json.SDMLibJsonIdMap) value);
         return true;
      }

      if (PROPERTY_TASKNO.equalsIgnoreCase(attrName))
      {
         setTaskNo(Integer.parseInt(value.toString()));
         return true;
      }

      if (PROPERTY_TGTTASK.equalsIgnoreCase(attrName))
      {
         setTgtTask((CSClientTask) value);
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
      setTgtTask(null);
      setSubFlow(null);
      setParent(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
      super.removeYou();
   }

   
   /********************************************************************
    * <pre>
    *              one                       one
    * CSVisitAllClientsFlow ----------------------------------- CSClientTask
    *              parent                   tgtTask
    * </pre>
    */
   
   public static final String PROPERTY_TGTTASK = "tgtTask";
   
   private CSClientTask tgtTask = null;
   
   public CSClientTask getTgtTask()
   {
      return this.tgtTask;
   }
   
   public boolean setTgtTask(CSClientTask value)
   {
      boolean changed = false;
      
      if (this.tgtTask != value)
      {
         CSClientTask oldValue = this.tgtTask;
         
         if (this.tgtTask != null)
         {
            this.tgtTask = null;
            oldValue.setParent(null);
         }
         
         this.tgtTask = value;
         
         if (value != null)
         {
            value.withParent(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TGTTASK, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public CSVisitAllClientsFlow withTgtTask(CSClientTask value)
   {
      setTgtTask(value);
      return this;
   } 
   
   public CTDrawPoint createTgtTaskCTDrawPoint()
   {
      CTDrawPoint value = new CTDrawPoint();
      withTgtTask(value);
      return value;
   } 
   
   public CTClearDrawing createTgtTaskCTClearDrawing()
   {
      CTClearDrawing value = new CTClearDrawing();
      withTgtTask(value);
      return value;
   } 

   public String toString()
   {
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getTaskNo());
      return _.substring(1);
   }

}

