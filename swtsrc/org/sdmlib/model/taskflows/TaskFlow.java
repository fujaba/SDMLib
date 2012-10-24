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

import java.beans.PropertyChangeSupport;
import java.util.Timer;
import java.util.TimerTask;

public abstract class TaskFlow extends TimerTask implements PropertyChangeInterface, SelectionListener
{
	public abstract String getCurrentTaskName();
	
	public void switchTo(PeerProxy peer)
   {
      taskNo++;
      
      if(getLogger() != null)
      {
    	  peer.transferTaskFlow(getLogger());
      }
      else
      {
		peer.transferTaskFlow(this);
      }
   }
   
   public void switchToThisAnd(PeerProxy peer)
   {
      taskNo++;
      
      if(getLogger() != null)
      {
    	  peer.transferTaskFlow(getLogger());
    	  
    	  getLogger().run();
      }
      else
      {
		peer.transferTaskFlow(this);
		
		this.run();
      }
   }
   
   public void switchTo(PeerProxySet peers)
   {
      taskNo++;
      
      for (PeerProxy peer : peers)
      {
    	  if(getLogger() != null)
          {
        	  peer.transferTaskFlow(getLogger());
          }
          else
          {
    		peer.transferTaskFlow(this);
          }
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

      if (PROPERTY_IDMAP.equalsIgnoreCase(attribute))
      {
         return getIdMap();
      }

      if (PROPERTY_LOGGER.equalsIgnoreCase(attrName))
      {
         return getLogger();
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

      if (PROPERTY_IDMAP.equalsIgnoreCase(attrName))
      {
         setIdMap((org.sdmlib.serialization.json.SDMLibJsonIdMap) value);
         return true;
      }

      if (PROPERTY_LOGGER.equalsIgnoreCase(attrName))
      {
         setLogger((Logger) value);
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
      setLogger(null);
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
   
   public static final String PROPERTY_IDMAP = "idMap";
   
   protected org.sdmlib.serialization.json.SDMLibJsonIdMap idMap;

   public org.sdmlib.serialization.json.SDMLibJsonIdMap getIdMap()
   {
      return this.idMap;
   }
   
   public void setIdMap(org.sdmlib.serialization.json.SDMLibJsonIdMap value)
   {
      if (this.idMap != value)
      {
         org.sdmlib.serialization.json.SDMLibJsonIdMap oldValue = this.idMap;
         this.idMap = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_IDMAP, oldValue, value);
      }
   }
   
   public TaskFlow withIdMap(org.sdmlib.serialization.json.SDMLibJsonIdMap value)
   {
      setIdMap(value);
      return this;
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

   
   /********************************************************************
    * <pre>
    *              one                       one
    * TaskFlow ----------------------------------- Logger
    *              targetTaskFlow                   logger
    * </pre>
    */
   
   public static final String PROPERTY_LOGGER = "logger";
   
   private Logger logger = null;
   
   public Logger getLogger()
   {
      return this.logger;
   }
   
   public boolean setLogger(Logger value)
   {
      boolean changed = false;
      
      if (this.logger != value)
      {
         Logger oldValue = this.logger;
         
         if (this.logger != null)
         {
            this.logger = null;
            oldValue.setTargetTaskFlow(null);
         }
         
         this.logger = value;
         
         if (value != null)
         {
            value.withTargetTaskFlow(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_LOGGER, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public TaskFlow withLogger(Logger value)
   {
      setLogger(value);
      return this;
   } 
   
   public Logger createLogger()
   {
      Logger value = new Logger();
      withLogger(value);
      return value;
   } 
}

