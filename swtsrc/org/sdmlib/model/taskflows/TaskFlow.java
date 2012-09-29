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
   
package org.sdmlib.model.taskflows;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Display;
import org.sdmlib.model.taskflows.creators.PeerProxySet;
import org.sdmlib.utils.PropertyChangeInterface;

import com.sun.corba.se.pept.transport.Selector;

import java.beans.PropertyChangeSupport;
import java.util.Timer;
import java.util.TimerTask;

public abstract class TaskFlow extends TimerTask implements PropertyChangeInterface, SelectionListener
{
   public void switchTo(PeerProxy peer)
   {
      taskNo++;
      
      peer.transferTaskFlow(this);
   }
   
   public void switchToThisAnd(PeerProxy peer)
   {
      taskNo++;
      
      peer.transferTaskFlow(this);
      
      this.run();
   }
   
   public void switchTo(PeerProxySet peers)
   {
      taskNo++;
      
      for (PeerProxy peer : peers)
      {
         peer.transferTaskFlow(this);
      }
   }
   
   public void switchTo(Display display)
   {
      taskNo++;
      
      display.asyncExec(this);
   }
   
   public void switchTo(Timer timer)
   {
      taskNo++;
      
      timer.schedule(this, 0);
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

      if (PROPERTY_TASKNO.equalsIgnoreCase(attribute))
      {
         return getTaskNo();
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

      if (PROPERTY_TASKNO.equalsIgnoreCase(attrName))
      {
         setTaskNo(Integer.parseInt(value.toString()));
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
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }


   @Override
   public void widgetSelected(SelectionEvent e)
   {
      run();
   }


   @Override
   public void widgetDefaultSelected(SelectionEvent e)
   {
      run();
   }

   

   
   //==========================================================================
   
   public static final String PROPERTY_TASKNO = "taskNo";
   
   protected int taskNo;

   public int getTaskNo()
   {
      return this.taskNo;
   }
   
   public void setTaskNo(int value)
   {
      if (this.taskNo != value)
      {
         int oldValue = this.taskNo;
         this.taskNo = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TASKNO, oldValue, value);
      }
   }
   
   public TaskFlow withTaskNo(int value)
   {
      setTaskNo(value);
      return this;
   } 
}

