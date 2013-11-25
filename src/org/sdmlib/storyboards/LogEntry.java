/*
   Copyright (c) 2012 Albert Zündorf

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

package org.sdmlib.storyboards;


import java.beans.PropertyChangeSupport;

import org.sdmlib.storyboards.creators.LogEntrySet;
import org.sdmlib.utils.PropertyChangeInterface;
import org.sdmlib.utils.StrUtil;
import java.beans.PropertyChangeListener;


// should have a creator class
public class LogEntry implements PropertyChangeInterface
{
   
   public LogEntry()
   {
      int i = 0;
      i = i + 1;
   }
   
   public static final LogEntrySet EMPTY_SET = new LogEntrySet();

   public static final String PROPERTY_DATE = "date";

   private String date;

   public void setDate(String value) {
      if ( StrUtil.stringCompare (this.date, value) != 0 )
      {
         String oldValue = this.date;
         this.date = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_DATE, oldValue, value);
      }
   }

   public String getDate() {
      return this.date;
   }
   public static final String PROPERTY_HOURS_SPEND = "hoursSpend";

   private double hoursSpend;

   public void setHoursSpend(double value) {
      if ( this.hoursSpend != value )
      {
         double oldValue = this.hoursSpend;
         this.hoursSpend = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_HOURS_SPEND, oldValue, value);
      }
   }

   public double getHoursSpend() {
      return this.hoursSpend;
   }
   public static final String PROPERTY_HOURS_REMAINING_IN_PHASE = "hoursRemainingInPhase";

   private double hoursRemainingInPhase;

   public void setHoursRemainingInPhase(double value) {
      if ( this.hoursRemainingInPhase != value )
      {
         double oldValue = this.hoursRemainingInPhase;
         this.hoursRemainingInPhase = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_HOURS_REMAINING_IN_PHASE, oldValue, value);
      }
   }

   public double getHoursRemainingInPhase() {
      return this.hoursRemainingInPhase;
   }
   public static final String PROPERTY_HOURS_REMAINING_IN_TOTAL = "hoursRemainingInTotal";

   private double hoursRemainingInTotal;

   public void setHoursRemainingInTotal(double value) {
      if ( this.hoursRemainingInTotal != value )
      {
         double oldValue = this.hoursRemainingInTotal;
         this.hoursRemainingInTotal = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_HOURS_REMAINING_IN_TOTAL, oldValue, value);
      }
   }

   public double getHoursRemainingInTotal() {
      return this.hoursRemainingInTotal;
   }
   public static final String PROPERTY_DEVELOPER = "developer";

   private String developer;

   public void setDeveloper(String value) {
      if ( StrUtil.stringCompare (this.developer, value) != 0 )
      {
         String oldValue = this.developer;
         this.developer = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_DEVELOPER, oldValue, value);
      }
   }

   public String getDeveloper() {
      return this.developer;
   }
   public static final String PROPERTY_PHASE = "phase";

   private String phase;

   public void setPhase(String value) {
      if ( StrUtil.stringCompare (this.phase, value) != 0 )
      {
         String oldValue = this.phase;
         this.phase = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_PHASE, oldValue, value);
      }
   }

   public String getPhase() {
      return this.phase;
   }
   public static final String PROPERTY_COMMENT = "comment";

   private String comment;

   public void setComment(String value) {
      if ( StrUtil.stringCompare (this.comment, value) != 0 )
      {
         String oldValue = this.comment;
         this.comment = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_COMMENT, oldValue, value);
      }
   }

   public String getComment() {
      return this.comment;
   }

   protected final PropertyChangeSupport listeners = new PropertyChangeSupport(this);

   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }

   public boolean set(String attrName, Object value) 
   {
      if (PROPERTY_DATE.equalsIgnoreCase(attrName)) 
      {
         setDate((String) value);
         return true;
      }

      if (PROPERTY_KANBANENTRY.equalsIgnoreCase(attrName))
      {
         setKanbanEntry((KanbanEntry) value);
         return true;
      }
      else   if (PROPERTY_HOURS_SPEND.equalsIgnoreCase(attrName)) 
      {
         setHoursSpend(Double.parseDouble(value.toString()));
         return true;
      }
      else   if (PROPERTY_HOURS_REMAINING_IN_PHASE.equalsIgnoreCase(attrName)) 
      {
         setHoursRemainingInPhase(Double.parseDouble(value.toString()));
         return true;
      }
      else   if (PROPERTY_HOURS_REMAINING_IN_TOTAL.equalsIgnoreCase(attrName)) 
      {
         setHoursRemainingInTotal(Double.parseDouble(value.toString()));
         return true;
      }
      else   if (PROPERTY_KANBANENTRY.equalsIgnoreCase(attrName)) 
      {
         setKanbanEntry((KanbanEntry) value);
         return true;
      }
      else   if (PROPERTY_DEVELOPER.equalsIgnoreCase(attrName)) 
      {
         setDeveloper((String) value);
         return true;
      }
      else   if (PROPERTY_PHASE.equalsIgnoreCase(attrName)) 
      {
         setPhase((String) value);
         return true;
      }
      else   if (PROPERTY_COMMENT.equalsIgnoreCase(attrName)) 
      {
         setComment((String) value);
         return true;
      }
      return false;
   }

   public Object get(String attrName) 
   {
      int pos=attrName.indexOf(".");
      String attribute = attrName;

      if(pos>0)
      {
         attribute=attrName.substring(0, pos);
      }

      if (PROPERTY_DATE.equalsIgnoreCase(attribute)) 
      {
         return getDate();
      }

      if (PROPERTY_KANBANENTRY.equalsIgnoreCase(attrName))
      {
         return getKanbanEntry();
      }
      else     if (PROPERTY_HOURS_SPEND.equalsIgnoreCase(attribute)) 
      {
         return getHoursSpend();
      }
      else     if (PROPERTY_HOURS_REMAINING_IN_PHASE.equalsIgnoreCase(attribute)) 
      {
         return getHoursRemainingInPhase();
      }
      else     if (PROPERTY_HOURS_REMAINING_IN_TOTAL.equalsIgnoreCase(attribute)) 
      {
         return getHoursRemainingInTotal();
      }
      else     if (PROPERTY_KANBANENTRY.equalsIgnoreCase(attribute)) 
      {
         return getKanbanEntry();
      }  
      else     if (PROPERTY_DEVELOPER.equalsIgnoreCase(attribute)) 
      {
         return getDeveloper();
      }
      else     if (PROPERTY_PHASE.equalsIgnoreCase(attribute)) 
      {
         return getPhase();
      }
      else     if (PROPERTY_COMMENT.equalsIgnoreCase(attribute)) 
      {
         return getComment();
      }
      return null;
   }

   public LogEntry withDate(String newValue)
   {
      this.setDate(newValue);
      return this;
   }


   public LogEntry withHoursSpend(double newValue)
   {
      this.setHoursSpend(newValue);
      return this;
   }


   public LogEntry withHoursRemainingInPhase(double newValue)
   {
      this.setHoursRemainingInPhase(newValue);
      return this;
   }


   public LogEntry withHoursRemainingInTotal(double newValue)
   {
      this.setHoursRemainingInTotal(newValue);
      return this;
   }


  public LogEntry withDeveloper(String newValue)
   {
      this.setDeveloper(newValue);
      return this;
   }


   public LogEntry withPhase(String newValue)
   {
      this.setPhase(newValue);
      return this;
   }


   public LogEntry withComment(String newValue)
   {
      this.setComment(newValue);
      return this;
   }

   
   /********************************************************************
    * <pre>
    *              many                       one
    * LogEntry ----------------------------------- KanbanEntry
    *              logEntries                   kanbanEntry
    * </pre>
    */
   
   public static final String PROPERTY_KANBANENTRY = "kanbanEntry";
   
   private KanbanEntry kanbanEntry = null;
   
   public KanbanEntry getKanbanEntry()
   {
      return this.kanbanEntry;
   }
   
   public boolean setKanbanEntry(KanbanEntry value)
   {
      boolean changed = false;
      
      if (this.kanbanEntry != value)
      {
         KanbanEntry oldValue = this.kanbanEntry;
         
         if (this.kanbanEntry != null)
         {
            this.kanbanEntry = null;
            oldValue.withoutLogEntries(this);
         }
         
         this.kanbanEntry = value;
         
         if (value != null)
         {
            value.withLogEntries(this);
         }
         
         // getPropertyChangeSupport().firePropertyChange(PROPERTY_KANBANENTRY, null, value);
         changed = true;
      }
      
      return changed;
   }
   
   public LogEntry withKanbanEntry(KanbanEntry value)
   {
      setKanbanEntry(value);
      return this;
   } 

   
   //==========================================================================
   
   public void removeYou()
   {
      setKanbanEntry(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }
}

