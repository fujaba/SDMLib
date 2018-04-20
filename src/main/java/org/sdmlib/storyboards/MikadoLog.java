/*
   Copyright (c) 2018 zuendorf
   
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

import de.uniks.networkparser.interfaces.SendableEntity;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.sdmlib.CGUtil;
import org.sdmlib.storyboards.util.LogEntrySet;
import org.sdmlib.storyboards.LogEntry;
import org.sdmlib.storyboards.Goal;


public  class MikadoLog implements SendableEntity
{
   public String burnDownChart()
   {
      StringBuilder buf = new StringBuilder();

      buf     .append("<canvas id=\"myChart\" width=\"880\" height=\"550\"></canvas>\n")
              .append("<script>\n")
              .append("var ctx = document.getElementById(\"myChart\").getContext('2d');")
              .append("var myLineChart = new Chart(ctx, {\n")
              .append("    type: 'line',\n")
              .append("    data: {\n" +
                      "        datasets: [\n" +
                      "            {\n" +
                      "                label: \"Burn Down\",\n" +
                      "                data: [\n" +
                      "                    burndowndatapoints" +
                      "                ]\n" +
                      "            }\n" +
                      "        ]\n" +
                      "    },\n" +
                      "    ")
              .append("options: {\n" +
                      "        animation: false,\n" +
                      "        responsive: false,\n" +
                      "         animation: {\n" +
                      "            onComplete: function(animation) {\n" +
                      "                    java.screendump(\"42\");\n"+
                      "                    java.close();\n"+
                      "                }\n"+
                      "         },\n"+
                      "        scales: {\n" +
                      "            xAxes: [{\n" +
                      "                type: \"time\",\n" +
                      "                time: {\n" +
                      "                    displayFormats: {\n" +
                      "                       'millisecond': 'DD MMM hh:mm',\n" +
                      "                       'second': 'DD MMM hh:mm',\n" +
                      "                       'minute': 'DD MMM hh:mm',\n" +
                      "                       'hour': 'DD MMM hh:mm',\n" +
                      "                       'day': 'DD MMM',\n" +
                      "                       'week': 'DD MMM',\n" +
                      "                       'month': 'MMM YYYY',\n" +
                      "                       'quarter': 'MMM YYYY',\n" +
                      "                       'year': 'YYYY',\n" +
                      "                    }\n" +
                      "                },\n" +
                      "                display: true,\n" +
                      "                scaleLabel: {\n" +
                      "                    display: true,\n" +
                      "                    labelString: 'Date'\n" +
                      "                },\n" +
                      "                ticks: {\n" +
                      "                    major: {\n" +
                      "                        fontStyle: \"bold\",\n" +
                      "                        fontColor: \"#FF0000\"\n" +
                      "                    }\n" +
                      "                }\n" +
                      "            }],\n" +
                      "            yAxes: [{\n" +
                      "                display: true,\n" +
                      "                scaleLabel: {\n" +
                      "                    display: true,\n" +
                      "                    labelString: 'hours'\n" +
                      "                },\n" +
                      "                ticks: {\n" +
                      "                    beginAtZero: true\n" +
                      "                }\n" +
                      "            }]\n" +
                      "        }\n" +
                      "    }")
              .append("});\n")
              .append("</script>\n")
      ;

      // collect hours remaining
      double totalHours = 0;
      for (LogEntry e : this.getEntries())
      {
         Goal g = e.getGoal();

         double rest = e.getHoursRemaining();

         g.setHoursTodo(rest);
         g.setHoursDone(g.getHoursDone() + e.getHoursDone());

         totalHours += e.getHoursDone();
      }

      for(Goal g : this.getMainGoal().getPreGoalsTransitive())
      {
         totalHours += g.getHoursTodo();
      }

      String burndownDataPoints = "";

      for (LogEntry e : this.getEntries())
      {
         totalHours -= e.getHoursDone();
         burndownDataPoints += String.format(Locale.ENGLISH, "{x: \"%s\", y: %.1f},\n", e.getDate(), totalHours);
      }


      CGUtil.replaceAll(buf, "burndowndatapoints", burndownDataPoints);

      return buf.toString();
   }

   //==========================================================================

   protected PropertyChangeSupport listeners = null;
   
   public boolean firePropertyChange(String propertyName, Object oldValue, Object newValue)
   {
      if (listeners != null) {
   		listeners.firePropertyChange(propertyName, oldValue, newValue);
   		return true;
   	}
   	return false;
   }
   
   public boolean addPropertyChangeListener(PropertyChangeListener listener) 
   {
   	if (listeners == null) {
   		listeners = new PropertyChangeSupport(this);
   	}
   	listeners.addPropertyChangeListener(listener);
   	return true;
   }
   
   public boolean addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
   	if (listeners == null) {
   		listeners = new PropertyChangeSupport(this);
   	}
   	listeners.addPropertyChangeListener(propertyName, listener);
   	return true;
   }
   
   public boolean removePropertyChangeListener(PropertyChangeListener listener) {
   	if (listeners != null) {
   		listeners.removePropertyChangeListener(listener);
   	}
   	return true;
   }

   public boolean removePropertyChangeListener(String propertyName,PropertyChangeListener listener) {
   	if (listeners != null) {
   		listeners.removePropertyChangeListener(propertyName, listener);
   	}
   	return true;
   }

   
   //==========================================================================
   
   
   public void removeYou()
   {
      withoutEntries(this.getEntries().toArray(new LogEntry[this.getEntries().size()]));
      setMainGoal(null);
      firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              one                       one
    * MikadoLog ----------------------------------- Goal
    *              mikadolog                   mainGoal
    * </pre>
    */
   
   public static final String PROPERTY_MAINGOAL = "mainGoal";

   private Goal mainGoal = null;

   public Goal getMainGoal()
   {
      return this.mainGoal;
   }

   public boolean setMainGoal(Goal value)
   {
      boolean changed = false;
      
      if (this.mainGoal != value)
      {
         Goal oldValue = this.mainGoal;
         
         this.mainGoal = value;
         
         firePropertyChange(PROPERTY_MAINGOAL, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public MikadoLog withMainGoal(Goal value)
   {
      setMainGoal(value);
      return this;
   } 

   public Goal createMainGoal()
   {
      Goal value = new Goal();
      withMainGoal(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * MikadoLog ----------------------------------- LogEntry
    *              parent                   entries
    * </pre>
    */
   
   public static final String PROPERTY_ENTRIES = "entries";

   private LogEntrySet entries = null;
   
   public LogEntrySet getEntries()
   {
      if (this.entries == null)
      {
         return LogEntrySet.EMPTY_SET;
      }
   
      return this.entries;
   }

   public MikadoLog withEntries(LogEntry... value)
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
               item.withParent(this);
               firePropertyChange(PROPERTY_ENTRIES, null, item);
            }
         }
      }
      return this;
   } 

   public MikadoLog withoutEntries(LogEntry... value)
   {
      for (LogEntry item : value)
      {
         if ((this.entries != null) && (item != null))
         {
            if (this.entries.remove(item))
            {
               item.setParent(null);
               firePropertyChange(PROPERTY_ENTRIES, item, null);
            }
         }
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
