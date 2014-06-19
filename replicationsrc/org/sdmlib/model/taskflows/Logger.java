/*
   Copyright (c) 2014 zuendorf 
   
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

import org.sdmlib.model.taskflows.TaskFlow;
import org.sdmlib.model.taskflows.PeerProxy;
import org.sdmlib.model.taskflows.util.LogEntrySet;

public class Logger extends TaskFlow
{

   
   //==========================================================================
   
   @Override
   public void removeYou()
   {
      super.removeYou();

      setSubFlow(null);
      setParent(null);
      withoutEntries(this.getEntries().toArray(new LogEntry[this.getEntries().size()]));
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_STARTPEER = "startPeer";
   
   private PeerProxy startPeer;

   public PeerProxy getStartPeer()
   {
      return this.startPeer;
   }
   
   public void setStartPeer(PeerProxy value)
   {
      if (this.startPeer != value)
      {
         PeerProxy oldValue = this.startPeer;
         this.startPeer = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_STARTPEER, oldValue, value);
      }
   }
   
   public Logger withStartPeer(PeerProxy value)
   {
      setStartPeer(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getTaskNo());
      return _.substring(1);
   }


   
   /********************************************************************
    * <pre>
    *              one                       many
    * Logger ----------------------------------- LogEntry
    *              logger                   entries
    * </pre>
    */
   
   public static final String PROPERTY_ENTRIES = "entries";

   private LogEntrySet entries = null;
   
   public LogEntrySet getEntries()
   {
      if (this.entries == null)
      {
         return LogEntry.EMPTY_SET;
      }
   
      return this.entries;
   }

   public Logger withEntries(LogEntry... value)
   {
      if(value==null){
         return this;
      }
      for (LogEntry item : value)
      {
         if (item != null)
         {
            if (this.entries == null)
            {
               this.entries = new LogEntrySet();
            }
            
            boolean changed = this.entries.add (item);

            if (changed)
            {
               item.withLogger(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_ENTRIES, null, item);
            }
         }
      }
      return this;
   } 

   public Logger withoutEntries(LogEntry... value)
   {
      for (LogEntry item : value)
      {
         if ((this.entries != null) && (item != null))
         {
            if (this.entries.remove(item))
            {
               item.setLogger(null);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_ENTRIES, item, null);
            }
         }
         withoutEntries(item);
      }
      return this;
   }

   public LogEntry createEntries()
   {
      LogEntry value = new LogEntry();
      withEntries(value);
      return value;
   } 
}
