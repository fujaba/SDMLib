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

import org.sdmlib.model.taskflows.TaskFlow;
import org.sdmlib.utils.PropertyChangeInterface;
import org.sdmlib.utils.StrUtil;

public class CSChatMessageFlow extends TaskFlow implements PropertyChangeInterface
{
   enum TaskNames
   {
      HandleClick, ServerHandleMessage, ClientHandleMessage, ServerReceiveRoger, ClientReceiveRoger,
   }
   
   public Object[] getTaskNames()
	{
		return TaskNames.values();
	}
   
   int noOfRogers = 0;
   //==========================================================================
   
   public void run(  )
   {
      switch (TaskNames.values()[taskNo])
      {
      case HandleClick:
         PeerToPeerChat gui = (PeerToPeerChat) getIdMap().getObject(PeerToPeerChat.MY_GUI);
         msg =  ": " + gui.getNewMessageText().getText();
         
         gui.getNewMessageText().setText("");
         
         switchTo(gui.getPeer());
         break;

      case ServerHandleMessage:
         ChatServer chatServer = (ChatServer) getIdMap().getObject(ChatServer.CHAT_SERVER);
         
         // append message to local buffer
         chatServer.setAllMessages(msg + "\n");
         
         // resend to all clients
         switchTo(chatServer.getAllPeers());
         break;
      
      case ClientHandleMessage:
         gui = (PeerToPeerChat) getIdMap().getObject(PeerToPeerChat.MY_GUI);
         if (gui == null)
         {
            System.out.println("Should not happen");
         }
         gui.getAllMessages().append(msg + "\n");
         
         switchTo(gui.getPeer());
         break;
         
      case ServerReceiveRoger:
         chatServer = (ChatServer) getIdMap().getObject(ChatServer.CHAT_SERVER);
         noOfRogers++;
         
         if (noOfRogers >= chatServer.getAllPeers().size())
         {
            // got all acknowledges
            msg = "OK";
            switchTo(chatServer.getAllPeers());
         }
         break;
         
      case ClientReceiveRoger:
         System.out.println(msg);
         
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

      if (PROPERTY_TASKNO.equalsIgnoreCase(attribute))
      {
         return getTaskNo();
      }

      if (PROPERTY_MSG.equalsIgnoreCase(attribute))
      {
         return getMsg();
      }

      if (PROPERTY_IDMAP.equalsIgnoreCase(attribute))
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
      if (PROPERTY_TASKNO.equalsIgnoreCase(attrName))
      {
         setTaskNo(Integer.parseInt(value.toString()));
         return true;
      }

      if (PROPERTY_MSG.equalsIgnoreCase(attrName))
      {
         setMsg((String) value);
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
   
   public CSChatMessageFlow withMsg(String value)
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

