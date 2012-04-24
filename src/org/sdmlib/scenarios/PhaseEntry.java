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

package org.sdmlib.scenarios;


import java.beans.PropertyChangeSupport;
import java.util.LinkedHashSet;
import java.util.Set;

import org.sdmlib.utils.PropertyChangeInterface;
import org.sdmlib.utils.StrUtil;


// should have a creator class
public class PhaseEntry implements PropertyChangeInterface
{
   public static final String PROPERTY_PHASE = "phase";

   private String phase;

   public void setPhase(String value) 
   {
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
   public static final String PROPERTY_PLANNED_START = "plannedStart";

   private String plannedStart;

   public void setPlannedStart(String value) {
      if ( StrUtil.stringCompare (this.plannedStart, value) != 0 )
      {
         String oldValue = this.plannedStart;
         this.plannedStart = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_PLANNED_START, oldValue, value);
      }
   }

   public String getPlannedStart() {
      return this.plannedStart;
   }
   public static final String PROPERTY_PLANNED_END = "plannedEnd";

   private String plannedEnd;

   public void setPlannedEnd(String value) {
      if ( StrUtil.stringCompare (this.plannedEnd, value) != 0 )
      {
         String oldValue = this.plannedEnd;
         this.plannedEnd = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_PLANNED_END, oldValue, value);
      }
   }

   public String getPlannedEnd() {
      return this.plannedEnd;
   }
   public static final String PROPERTY_ACTUAL_START = "actualStart";

   private String actualStart;

   public void setActualStart(String value) {
      if ( StrUtil.stringCompare (this.actualStart, value) != 0 )
      {
         String oldValue = this.actualStart;
         this.actualStart = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_ACTUAL_START, oldValue, value);
      }
   }

   public String getActualStart() {
      return this.actualStart;
   }
   public static final String PROPERTY_ACTUAL_END = "actualEnd";

   private String actualEnd;

   public void setActualEnd(String value) {
      if ( StrUtil.stringCompare (this.actualEnd, value) != 0 )
      {
         String oldValue = this.actualEnd;
         this.actualEnd = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_ACTUAL_END, oldValue, value);
      }
   }

   public String getActualEnd() {
      return this.actualEnd;
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
   public static final String PROPERTY_HOURS_PLANNED = "hoursPlanned";

   private double hoursPlanned;

   public void setHoursPlanned(double value) 
   {
      if ( this.hoursPlanned != value )
      {
         double oldValue = this.hoursPlanned;
         this.hoursPlanned = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_HOURS_PLANNED, oldValue, value);
      }
   }

   public double getHoursPlanned() 
   {
      return this.hoursPlanned;
   }
   
   /**
    * <pre>
    *           0..n     1..1
    * PhaseEntry ------------------------- KanbanEntry
    *           phaseEntries        &lt;       kanbanEntry
    * </pre>
    */

   public static final String PROPERTY_KANBAN_ENTRY = "kanbanEntry";
   private KanbanEntry kanbanEntry;

   public boolean setKanbanEntry (KanbanEntry value)		
   {
      boolean changed = false;

      if (this.kanbanEntry != value)
      {
         KanbanEntry oldValue = this.kanbanEntry;
         if (this.kanbanEntry != null)
         {
            this.kanbanEntry = null;
            oldValue.removeFromPhaseEntries(this);
         }
         this.kanbanEntry = value;

         if (value != null)
         {
            value.addToPhaseEntries(this);
         }
         getPropertyChangeSupport().firePropertyChange(PROPERTY_KANBAN_ENTRY, oldValue, value);
         changed = true;
      }
      return changed;
   }

   public KanbanEntry getKanbanEntry ()	
   {
      return this.kanbanEntry;
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

   public static final Set<PhaseEntry> EMPTY_SET = new LinkedHashSet<PhaseEntry>();


   protected final PropertyChangeSupport listeners = new PropertyChangeSupport(this);


   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }

   public boolean set(String attrName, Object value) 
   {
      if (PROPERTY_PHASE.equalsIgnoreCase(attrName)) 
      {
         setPhase((String) value);
         return true;
      }
      else   if (PROPERTY_DEVELOPER.equalsIgnoreCase(attrName)) 
      {
         setDeveloper((String) value);
         return true;
      }
      else   if (PROPERTY_PLANNED_START.equalsIgnoreCase(attrName)) 
      {
         setPlannedStart((String) value);
         return true;
      }
      else   if (PROPERTY_PLANNED_END.equalsIgnoreCase(attrName)) 
      {
         setPlannedEnd((String) value);
         return true;
      }
      else   if (PROPERTY_ACTUAL_START.equalsIgnoreCase(attrName)) 
      {
         setActualStart((String) value);
         return true;
      }
      else   if (PROPERTY_ACTUAL_END.equalsIgnoreCase(attrName)) 
      {
         setActualEnd((String) value);
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
      else   if (PROPERTY_HOURS_PLANNED.equalsIgnoreCase(attrName)) 
      {
         setHoursPlanned(Double.parseDouble(value.toString()));
         return true;
      }
      else   if (PROPERTY_KANBAN_ENTRY.equalsIgnoreCase(attrName)) 
      {
         setKanbanEntry((KanbanEntry) value);
         return true;
      }
      else   if (PROPERTY_HOURS_REMAINING_IN_TOTAL.equalsIgnoreCase(attrName)) 
      {
         setHoursRemainingInTotal(Double.parseDouble(value.toString()));
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

      if (PROPERTY_PHASE.equalsIgnoreCase(attribute)) 
      {
         return getPhase();
      }
      else     if (PROPERTY_DEVELOPER.equalsIgnoreCase(attribute)) 
      {
         return getDeveloper();
      }
      else     if (PROPERTY_PLANNED_START.equalsIgnoreCase(attribute)) 
      {
         return getPlannedStart();
      }
      else     if (PROPERTY_PLANNED_END.equalsIgnoreCase(attribute)) 
      {
         return getPlannedEnd();
      }
      else     if (PROPERTY_ACTUAL_START.equalsIgnoreCase(attribute)) 
      {
         return getActualStart();
      }
      else     if (PROPERTY_ACTUAL_END.equalsIgnoreCase(attribute)) 
      {
         return getActualEnd();
      }
      else     if (PROPERTY_HOURS_SPEND.equalsIgnoreCase(attribute)) 
      {
         return getHoursSpend();
      }
      else     if (PROPERTY_HOURS_REMAINING_IN_PHASE.equalsIgnoreCase(attribute)) 
      {
         return getHoursRemainingInPhase();
      }
      else     if (PROPERTY_HOURS_PLANNED.equalsIgnoreCase(attribute)) 
      {
         return getHoursPlanned();
      }
      else     if (PROPERTY_KANBAN_ENTRY.equalsIgnoreCase(attribute)) 
      {
         return getKanbanEntry();
      }        else     if (PROPERTY_HOURS_REMAINING_IN_TOTAL.equalsIgnoreCase(attribute)) 
      {
         return getHoursRemainingInTotal();
      }
      return null;
   }

   public PhaseEntry withPhase(String newValue)
   {
      this.setPhase(newValue);
      return this;
   }


   public PhaseEntry withDeveloper(String newValue)
   {
      this.setDeveloper(newValue);
      return this;
   }


   public PhaseEntry withPlannedStart(String newValue)
   {
      this.setPlannedStart(newValue);
      return this;
   }


   public PhaseEntry withPlannedEnd(String newValue)
   {
      this.setPlannedEnd(newValue);
      return this;
   }


   public PhaseEntry withActualStart(String newValue)
   {
      this.setActualStart(newValue);
      return this;
   }


   public PhaseEntry withActualEnd(String newValue)
   {
      this.setActualEnd(newValue);
      return this;
   }


   public PhaseEntry withHoursSpend(double newValue)
   {
      this.setHoursSpend(newValue);
      return this;
   }


   public PhaseEntry withHoursRemainingInPhase(double newValue)
   {
      this.setHoursRemainingInPhase(newValue);
      return this;
   }


   public PhaseEntry withHoursPlanned(double newValue)
   {
      this.setHoursPlanned(newValue);
      return this;
   }


   public PhaseEntry withKanbanEntry(KanbanEntry newValue)
   {
      this.setKanbanEntry(newValue);
      return this;
   }


   public PhaseEntry withHoursRemainingInTotal(double newValue)
   {
      this.setHoursRemainingInTotal(newValue);
      return this;
   }
}
