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

package org.sdmlib.storyboards;


import java.beans.PropertyChangeSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import org.sdmlib.StrUtil;
import org.sdmlib.serialization.PropertyChangeInterface;
import org.sdmlib.storyboards.util.LogEntryStoryboardSet;


// should have a creator class
public class LogEntryStoryBoard implements PropertyChangeInterface, Comparable<LogEntryStoryBoard>
{
   
   public LogEntryStoryBoard()
   {
      int i = 0;
      i = i + 1;
   }
   
   public static final LogEntryStoryboardSet EMPTY_SET = new LogEntryStoryboardSet();

   public static final String PROPERTY_DATE = "date";

   private String date;

   private Date parsedDate = null;
   
   public Date getParsedDate()
   {
      return parsedDate;
   }

   public void setDate(String value) {
      if ( StrUtil.stringCompare (this.date, value) != 0 )
      {
         String oldValue = this.date;
         this.date = value;
         
         DateFormat dateFormat = DateFormat.getInstance();
         try
         {
            this.parsedDate = dateFormat.parse(this.date);
         }
         catch (ParseException e)
         {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
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

   @Override
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }
   
   public LogEntryStoryBoard withDate(String newValue)
   {
      this.setDate(newValue);
      return this;
   }


   public LogEntryStoryBoard withHoursSpend(double newValue)
   {
      this.setHoursSpend(newValue);
      return this;
   }


   public LogEntryStoryBoard withHoursRemainingInTotal(double newValue)
   {
      this.setHoursRemainingInTotal(newValue);
      return this;
   }


  public LogEntryStoryBoard withDeveloper(String newValue)
   {
      this.setDeveloper(newValue);
      return this;
   }


   public LogEntryStoryBoard withPhase(String newValue)
   {
      this.setPhase(newValue);
      return this;
   }


   public LogEntryStoryBoard withComment(String newValue)
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
   
   public LogEntryStoryBoard withKanbanEntry(KanbanEntry value)
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

   @Override
   public int compareTo(LogEntryStoryBoard o)
   {
      return this.getParsedDate().compareTo(o.getParsedDate());
   }

   public KanbanEntry createKanbanEntry()
   {
      KanbanEntry value = new KanbanEntry();
      withKanbanEntry(value);
      return value;
   } 
}

