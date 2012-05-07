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
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.sdmlib.codegen.CGUtil;
import org.sdmlib.utils.PropertyChangeInterface;
import org.sdmlib.utils.StrUtil;
import org.sdmlib.serialization.json.JsonIdMap;

public class KanbanEntry implements PropertyChangeInterface
{
   public static final String PROPERTY_NAME = "name";

   private String name;

   public void setName(String value) 
   {
      if ( StrUtil.stringCompare (this.name, value) != 0 )
      {
         String oldValue = this.name;
         this.name = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_NAME, oldValue, value);
      }
   }

   public String getName() {
      return this.name;
   }
   
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

   public String getPhase() 
   {
      return this.phase;
   }
   
   public static final String PROPERTY_LAST_DEVELOPER = "lastDeveloper";

   private String lastDeveloper;

   public void setLastDeveloper(String value) {
      if ( StrUtil.stringCompare (this.lastDeveloper, value) != 0 )
      {
         String oldValue = this.lastDeveloper;
         this.lastDeveloper = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_LAST_DEVELOPER, oldValue, value);
      }
   }

   public String getLastDeveloper() 
   {
      return this.lastDeveloper;
   }
   
   public static final String PROPERTY_HOURS_REMAINING = "hoursRemaining";

   private double hoursRemaining;

   public void setHoursRemaining(double value) 
   {
      if ( this.hoursRemaining != value )
      {
         double oldValue = this.hoursRemaining;
         this.hoursRemaining = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_HOURS_REMAINING, oldValue, value);
      }
   }

   public double getHoursRemaining() 
   {
      return this.hoursRemaining;
   }
   
   public static final String PROPERTY_HOURS_SPEND = "hoursSpend";

   private double hoursSpend;

   public void setHoursSpend(double value) 
   {
      if ( this.hoursSpend != value )
      {
         double oldValue = this.hoursSpend;
         this.hoursSpend = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_HOURS_SPEND, oldValue, value);
      }
   }

   public double getHoursSpend() 
   {
      return this.hoursSpend;
   }
   
   /**
    * <pre>
    *           0..n     1..1
    * KanbanEntry ------------------------- KanbanEntry
    *           subentries        &lt;       parent
    * </pre>
    */

   public static final String PROPERTY_PARENT = "parent";
   private KanbanEntry parent;

   public boolean setParent (KanbanEntry value)		
   {
      boolean changed = false;

      if (this.parent != value)
      {
         KanbanEntry oldValue = this.parent;
         if (this.parent != null)
         {
            this.parent = null;
            oldValue.removeFromSubentries(this);
         }
         this.parent = value;

         if (value != null)
         {
            value.addToSubentries(this);
         }
         getPropertyChangeSupport().firePropertyChange(PROPERTY_PARENT, oldValue, value);
         changed = true;
      }
      return changed;
   }

   public KanbanEntry getParent ()	
   {
      return this.parent;
   }
   
   /**
    * <pre>
    *           1..1     0..n
    * KanbanEntry ------------------------- KanbanEntry
    *           parent        &gt;       subentries
    * </pre>
    */

   public static final String PROPERTY_SUBENTRIES = "subentries";
   private LinkedHashSet<KanbanEntry> subentries;

   public boolean addToSubentries (KanbanEntry value)
   {
      boolean changed = false;

      if (value != null)
      {
         if (this.subentries == null)
         {
            this.subentries = new LinkedHashSet<KanbanEntry>();
         }
         changed = this.subentries.add (value);
         if (changed)
         {
            value.setParent(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_SUBENTRIES, null, value);
         }
      }
      return changed;
   }

   public boolean removeFromSubentries (KanbanEntry value)	
   {
      boolean changed = false;

      if ((this.subentries != null) && (value != null))
      {
         changed = this.subentries.remove (value);
         if (changed)
         {
            value.setParent(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_SUBENTRIES, value, null);
         }
      }
      return changed;
   }

   public void removeAllFromSubentries ()
   {
      KanbanEntry tmpValue;
      Iterator<KanbanEntry> iter = this.getSubentries().iterator ();

      while (iter.hasNext ())
      {
         tmpValue = (KanbanEntry) iter.next ();
         this.removeFromSubentries (tmpValue);
      }
   }

   /**
    * <pre>
    *           1..1     0..n
    * KanbanEntry ------------------------- PhaseEntry
    *           kanbanEntry        &gt;       phaseEntries
    * </pre>
    */

   public static final String PROPERTY_PHASE_ENTRIES = "phaseEntries";
   private LinkedHashSet<PhaseEntry> phaseEntries;

   public boolean addToPhaseEntries (PhaseEntry value)
   {
      boolean changed = false;

      if (value != null)
      {
         if (this.phaseEntries == null)
         {
            this.phaseEntries = new LinkedHashSet<PhaseEntry> ();
         }
         changed = this.phaseEntries.add (value);
         if (changed)
         {
            value.withKanbanEntry(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_PHASE_ENTRIES, null, value);
         }
      }
      return changed;
   }

   public boolean removeFromPhaseEntries (PhaseEntry value)	
   {
      boolean changed = false;

      if ((this.phaseEntries != null) && (value != null))
      {
         changed = this.phaseEntries.remove (value);
         if (changed)
         {
            value.setKanbanEntry(null);
         }
      }
      return changed;
   }

   public void removeAllFromPhaseEntries ()
   {
      LinkedHashSet<PhaseEntry> tmpSet = new LinkedHashSet<PhaseEntry>(this.getPhaseEntries());
      
      for (PhaseEntry elem : tmpSet)
      {
         this.removeFromPhaseEntries(elem);
      }
   }

   public static final String PROPERTY_FILES = "files";

   public static final Set<KanbanEntry> EMPTY_SET = new LinkedHashSet<KanbanEntry>();

   private String files;

   public void setFiles(String value) {
      if ( StrUtil.stringCompare (this.files, value) != 0 )
      {
         String oldValue = this.files;
         this.files = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_FILES, oldValue, value);
      }
   }

   public String getFiles() {
      return this.files;
   }
   public LogEntry findOrCreateLogEntry(String date, String phase) 
   {
      LogEntry result = null;

      // find logEntry
      for (LogEntry logEntry : getLogEntries())
      {
         if (logEntry.getDate() != null && logEntry.getDate().equals(date))
         {
            return logEntry;
         }
      }

      // create new logEntry
      result = new LogEntry()
      .withDate(date)
      .withDeveloper(System.getProperty("user.name"))
      .withHoursSpend(0)
      .withHoursRemainingInTotal(0)
      .withPhase(phase)
      .withKanbanEntry(this);

      return result;
   }

   public KanbanEntry findOldEntry(String name) {
      KanbanEntry result = null;

      if (StrUtil.stringEquals(this.getName(), name))
      {
         return this;
      }
      else 
      {
         for (KanbanEntry subEntry : this.getSubentries())
         {
            result = subEntry.findOldEntry(name);

            if (result != null)
            {
               return result;
            }
         }
      }

      return result;
   }

   public KanbanEntry findOrCreate(String name) 
   {
      KanbanEntry result = findOldEntry(name);

      if (result == null)
      {
         result = new KanbanEntry()
         .withName(name);
      }

      return result;
   }

   public void linkToTest(String className, String testName) 
   {
      int firstDotPos = className.indexOf('.');
      int lastDotPos = className.lastIndexOf('.');

      String packageName = className.substring(firstDotPos+1, lastDotPos);
      String fileName = className;

      className = className.substring(lastDotPos+1);

      fileName = fileName.replaceAll("\\.", "/");

      fileName = fileName + ".java";

      File javaFile = new File(fileName);

      this.setFiles(fileName);

      if ( ! javaFile.exists())
      {
         // create it
         StringBuilder fileBody = new StringBuilder(
                     "/*\n" +
                     "   Copyright (c) <year> <developer> \n" +
                     "   \n" +
                     "   Permission is hereby granted, free of charge, to any person obtaining a copy of this software \n" +
                     "   and associated documentation files (the \"Software\"), to deal in the Software without restriction, \n" +
                     "   including without limitation the rights to use, copy, modify, merge, publish, distribute, \n" +
                     "   sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is \n" +
                     "   furnished to do so, subject to the following conditions: \n" +
                     "   \n" +
                     "   The above copyright notice and this permission notice shall be included in all copies or \n" +
                     "   substantial portions of the Software. \n" +
                     "   \n" +
                     "   The Software shall be used for Good, not Evil. \n" +
                     "   \n" +
                     "   THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING \n" +
                     "   BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND \n" +
                     "   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, \n" +
                     "   DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, \n" +
                     "   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. \n" +
                     " */\n" +
                     "   \n" + 
                     "package " + packageName + ";\n" +
                     "   \n" +
                     "import org.junit.Test;\n" +
                     "import org.sdmlib.scenarios.LogEntry;\n" +
                     "import org.sdmlib.scenarios.Scenario;\n" +
                     "import org.sdmlib.scenarios.ScenarioManager;\n" +
                     "   \n" +
                     "public class " + className + " \n" +
                     "{\n" +
                     "   private static final String MODELING = \"modeling\";\n" +
                     "   private static final String ACTIVE = \"active\";\n" +
                     "   private static final String DONE = \"done\";\n" +
                     "   private static final String IMPLEMENTATION = \"implementation\";\n" +
                     "   private static final String BACKLOG = \"backlog\";\n" +
                     "}\n");

               String year = new SimpleDateFormat("yyyy").format(new Date(System.currentTimeMillis()));
               CGUtil.replace(fileBody, "<year>", year);

               CGUtil.replace(fileBody, "<developer>", System.getProperty("user.name"));
               
               ScenarioManager.get().printFile(javaFile, fileBody.toString());
      }

      // find or create test method
      String fileBody = ScenarioManager.get().readFile(javaFile);

      String methodName = "test" + testName;

      lastDotPos = fileBody.indexOf("public void " + methodName);

      if (lastDotPos < 0)
      {
         // generate test method
         String testBody = 
               "   @Test\n" +
                     "   public void " + methodName + "()\n" +
                     "   {\n" +
                     "      Scenario scenario = new Scenario(\"" + testName + "\");\n" +
                     "      \n" +
                     "      scenario.add(\"Start situation: \",\n" +
                     "         BACKLOG, " +
                     "\"" + System.getProperty("user.name") + "\", " +
                     "\"" + ScenarioManager.get().dateParser.format(new Date(System.currentTimeMillis())) +  "\", " +
                     "0, 0);\n" +
                     "      \n" +
                     "      ScenarioManager.get()\n" +
                     "      .add(scenario)\n" +
                     "      .dumpHTML();\n" +
                     "   }\n\n";

         lastDotPos = fileBody.indexOf('{');

         fileBody = 
               fileBody.substring(0, lastDotPos+2)
               + testBody
               + fileBody.substring(lastDotPos+2);

         ScenarioManager.get().printFile(javaFile, fileBody);
      }

   }

   public KanbanEntry withParent(KanbanEntry newValue) 
   {
      this.setParent(newValue);
      return this;
   }

   public Set<KanbanEntry> getSubentries() 
   {
      if (this.subentries == null)
      {
         return KanbanEntry.EMPTY_SET;  
      }

      return this.subentries;
   }

   public KanbanEntry withSubentries(KanbanEntry newValue) {
      this.addToSubentries(newValue);
      return this;
   }

   public KanbanEntry withoutSubentries(KanbanEntry newValue) {
      this.removeFromSubentries(newValue);
      return this;
   }

   public Set<PhaseEntry> getPhaseEntries() {
      if (this.phaseEntries == null)
      {
         return PhaseEntry.EMPTY_SET;  
      }

      return this.phaseEntries;
   }

   public KanbanEntry withPhaseEntries(PhaseEntry newValue) {
      this.addToPhaseEntries(newValue);
      return this;
   }

   public KanbanEntry withoutPhaseEntries(PhaseEntry newValue) {
      this.removeFromPhaseEntries(newValue);
      return this;
   }

   public boolean set(String attrName, Object value) {
      if (PROPERTY_NAME.equalsIgnoreCase(attrName)) 
      {
         setName((String) value);
         return true;
      } 
      if (PROPERTY_LOGENTRIES.equalsIgnoreCase(attrName))
      {
         addToLogEntries((LogEntry) value);
         return true;
      }
      
      if ((PROPERTY_LOGENTRIES + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromLogEntries((LogEntry) value);
         return true;
      }

      else   if (PROPERTY_PHASE.equalsIgnoreCase(attrName)) 
      {
         setPhase((String) value);
         return true;
      } 
      else   if (PROPERTY_LAST_DEVELOPER.equalsIgnoreCase(attrName)) 
      {
         setLastDeveloper((String) value);
         return true;
      } 
      else   if (PROPERTY_HOURS_REMAINING.equalsIgnoreCase(attrName)) 
      {
         setHoursRemaining(Double.parseDouble(value.toString()));
         return true;
      } 
      else   if (PROPERTY_HOURS_SPEND.equalsIgnoreCase(attrName)) 
      {
         setHoursSpend(Double.parseDouble(value.toString()));
         return true;
      } 
      else   if (PROPERTY_PARENT.equalsIgnoreCase(attrName)) 
      {
         setParent((KanbanEntry) value);
         return true;
      } 
      else   if (PROPERTY_SUBENTRIES.equalsIgnoreCase(attrName)) 
      {
         addToSubentries((KanbanEntry) value);
         return true;
      } 
      else   if (PROPERTY_LOGENTRIES.equalsIgnoreCase(attrName)) 
      {
         addToLogEntries((LogEntry) value);
         return true;
      } 
      else   if (PROPERTY_PHASE_ENTRIES.equalsIgnoreCase(attrName)) 
      {
         addToPhaseEntries((PhaseEntry) value);
         return true;
      } 
      else   if (PROPERTY_FILES.equalsIgnoreCase(attrName)) 
      {
         setFiles((String) value);
         return true;
      }
      return false;
   }

   public Object get(String attrName) 
   {
      int pos=attrName.indexOf(".");
      String attribute = attrName;

      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }
      if (PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return getName();
      }

      if (PROPERTY_LOGENTRIES.equalsIgnoreCase(attrName))
      {
         return getLogEntries();
      }
      else if (PROPERTY_PHASE.equalsIgnoreCase(attribute))
      {
         return getPhase();
      }
      else if (PROPERTY_LAST_DEVELOPER.equalsIgnoreCase(attribute))
      {
         return getLastDeveloper();
      }
      else if (PROPERTY_HOURS_REMAINING.equalsIgnoreCase(attribute))
      {
         return getHoursRemaining();
      }
      else if (PROPERTY_HOURS_SPEND.equalsIgnoreCase(attribute))
      {
         return getHoursSpend();
      }
      else if (PROPERTY_PARENT.equalsIgnoreCase(attribute))
      {
         if (pos > 0)
         {
            return getParent().get(attrName.substring(pos + 1));
         }

         return getParent();
      }
      else if (PROPERTY_SUBENTRIES.equalsIgnoreCase(attribute))
      {
         return getSubentries();
      }
      else if (PROPERTY_LOGENTRIES.equalsIgnoreCase(attribute))
      {
         return getLogEntries();
      }
      else if (PROPERTY_PHASE_ENTRIES.equalsIgnoreCase(attribute))
      {
         return getPhaseEntries();
      }
      else if (PROPERTY_FILES.equalsIgnoreCase(attribute))
      {
         return getFiles();
      }
      return null;
   }

   public KanbanEntry withName(String newValue) {
      this.setName(newValue);
      return this;
   }

   public KanbanEntry withPhase(String newValue) {
      this.setPhase(newValue);
      return this;
   }

   public KanbanEntry withLastDeveloper(String newValue) {
      this.setLastDeveloper(newValue);
      return this;
   }

   public KanbanEntry withHoursRemaining(double newValue) {
      this.setHoursRemaining(newValue);
      return this;
   }

   public KanbanEntry withHoursSpend(double newValue) {
      this.setHoursSpend(newValue);
      return this;
   }

   public KanbanEntry withFiles(String newValue) {
      this.setFiles(newValue);
      return this;
   }


   public void removeYou()
   {
      this.setParent (null);
      removeAllFromSubentries();
      removeAllFromPhaseEntries();
      removeAllFromLogEntries();
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   protected final PropertyChangeSupport listeners = new PropertyChangeSupport(this);

   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }

   public PhaseEntry getOrCreatePhaseEntry(String wantedPhase)
   {
      PhaseEntry result = null; 
      
      for (PhaseEntry phaseEntry : getPhaseEntries())
      {
         if (StrUtil.stringEquals(phaseEntry.getPhase(), wantedPhase))
         {
            return phaseEntry;
         }  
      }
      
      result = new PhaseEntry()
      .withPhase(wantedPhase)
      .withKanbanEntry(this);
      
      return result;
   }

   
   /********************************************************************
    * <pre>
    *              one                       many
    * KanbanEntry ----------------------------------- LogEntry
    *              kanbanEntry                   logEntries
    * </pre>
    */
   
   public static final String PROPERTY_LOGENTRIES = "logEntries";
   
   private LinkedHashSet<LogEntry> logEntries = null;
   
   public LinkedHashSet<LogEntry> getLogEntries()
   {
      if (this.logEntries == null)
      {
         return LogEntry.EMPTY_SET;
      }
   
      return this.logEntries;
   }
   
   public boolean addToLogEntries(LogEntry value)
   {
      boolean changed = false;
      
      if (value != null)
      {
         if (this.logEntries == null)
         {
            this.logEntries = new LinkedHashSet<LogEntry>();
         }
         
         changed = this.logEntries.add (value);
         
         if (changed)
         {
            value.withKanbanEntry(this);
            // getPropertyChangeSupport().firePropertyChange(PROPERTY_LOGENTRIES, null, value);
         }
      }
         
      return changed;   
   }
   
   public boolean removeFromLogEntries(LogEntry value)
   {
      boolean changed = false;
      
      if ((this.logEntries != null) && (value != null))
      {
         changed = this.logEntries.remove (value);
         
         if (changed)
         {
            value.setKanbanEntry(null);
            // getPropertyChangeSupport().firePropertyChange(PROPERTY_LOGENTRIES, null, value);
         }
      }
         
      return changed;   
   }
   
   public KanbanEntry withLogEntries(LogEntry value)
   {
      addToLogEntries(value);
      return this;
   } 
   
   public KanbanEntry withoutLogEntries(LogEntry value)
   {
      removeFromLogEntries(value);
      return this;
   } 
   
   public void removeAllFromLogEntries()
   {
      LinkedHashSet<LogEntry> tmpSet = new LinkedHashSet<LogEntry>(this.getLogEntries());
   
      for (LogEntry value : tmpSet)
      {
         this.removeFromLogEntries(value);
      }
   }
}

