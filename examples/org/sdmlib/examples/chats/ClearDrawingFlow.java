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

import org.sdmlib.model.taskflows.TaskFlow;
import org.sdmlib.utils.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;

public class ClearDrawingFlow extends TaskFlow implements PropertyChangeInterface
{
   enum TaskNames
   {
      HandelClearButtonClick, ClearImage
   }
   
	public String getCurrentTaskName()
	{
		return TaskNames.values()[taskNo].toString();
	}
   
   //==========================================================================
   
   public void run()
   {
      switch (TaskNames.values()[taskNo])
      {
      case HandelClearButtonClick:
         switchToThisAnd(gui.getPeer());
         break;

      case ClearImage:
         gui.clearImage();
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

      if (PROPERTY_GUI.equalsIgnoreCase(attribute))
      {
         return getGui();
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
      if (PROPERTY_GUI.equalsIgnoreCase(attrName))
      {
         setGui((org.sdmlib.examples.chats.PeerToPeerChat) value);
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
      super.removeYou();
   }

   
   //==========================================================================
   
   public static final String PROPERTY_GUI = "gui";
   
   private org.sdmlib.examples.chats.PeerToPeerChat gui;

   public org.sdmlib.examples.chats.PeerToPeerChat getGui()
   {
      return this.gui;
   }
   
   public void setGui(org.sdmlib.examples.chats.PeerToPeerChat value)
   {
      if (this.gui != value)
      {
         org.sdmlib.examples.chats.PeerToPeerChat oldValue = this.gui;
         this.gui = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_GUI, oldValue, value);
      }
   }
   
   public ClearDrawingFlow withGui(org.sdmlib.examples.chats.PeerToPeerChat value)
   {
      setGui(value);
      return this;
   } 
}

