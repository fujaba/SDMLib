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
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.sdmlib.CGUtil;
import org.sdmlib.StrUtil;
import org.sdmlib.serialization.PropertyChangeInterface;
import org.sdmlib.storyboards.util.KanbanEntrySet;
import org.sdmlib.storyboards.util.LogEntryStoryBoardSet;

import de.uniks.networkparser.json.JsonIdMap;
import java.beans.PropertyChangeListener;
import org.sdmlib.storyboards.util.LogEntryStoryBoardSet;

// file:///C:/Users/zuendorf/eclipseworkspaces/indigo/SDMLib/doc/StoryboardInfrastructure.html
public class KanbanEntry implements PropertyChangeInterface, Comparable<KanbanEntry>
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
   private KanbanEntrySet subentries;

   public boolean addToSubentries (KanbanEntry value)
   {
      boolean changed = false;

      if (value != null)
      {
         if (this.subentries == null)
         {
            this.subentries = new KanbanEntrySet();
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


   public static final String PROPERTY_FILES = "files";

   public static final Set<KanbanEntry> EMPTY_SET = new KanbanEntrySet();

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
   public LogEntryStoryBoard findOrCreateLogEntry(String date, String phase) 
   {
      LogEntryStoryBoard result = null;

      // find logEntry
      for (LogEntryStoryBoard logEntry : getLogEntries())
      {
         if (logEntry.getDate() != null && logEntry.getDate().equals(date))
         {
            return logEntry;
         }
      }

      // create new logEntry
      result = new LogEntryStoryBoard()
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

   public KanbanEntry linkToTest(String rootDir, String className) 
   {
      return linkToTest(rootDir, className, this.getName());
   }
   
   public KanbanEntry linkToTest(String rootDir, String className, String testName) 
   {
      int lastDotPos = className.lastIndexOf('.');

      String packageName = className.substring(0, lastDotPos);
      String fileName = rootDir + "." + className;

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
                     "import org.sdmlib.storyboards.LogEntry;\n" +
                     "import org.sdmlib.storyboards.Storyboard;\n" +
                     "import org.sdmlib.storyboards.StoryboardManager;\n" +
                     "   \n" +
                     "public class " + className + " \n" +
                     "{\n" +
                     "   private static final String MODELING = \"modeling\";\n" +
                     "   private static final String ACTIVE = \"active\";\n" +
                     "   private static final String DONE = \"done\";\n" +
                     "   private static final String IMPLEMENTATION = \"implementation\";\n" +
                     "   private static final String BACKLOG = \"backlog\";\n" +
                     "   private static final String BUG = \"bug\";\n" +
                     "}\n");

               String year = new SimpleDateFormat("yyyy").format(new Date(System.currentTimeMillis()));
               CGUtil.replace(fileBody, "<year>", year);

               CGUtil.replace(fileBody, "<developer>", System.getProperty("user.name"));
               
               StoryboardManager.get().printFile(javaFile, fileBody.toString());
      }

      // find or create test method
      String fileBody = StoryboardManager.get().readFile(javaFile);

      String methodName = "test" + testName;

      lastDotPos = fileBody.indexOf("public void " + methodName);

      if (lastDotPos < 0)
      {
         // generate test method
         String testBody = 
               "   @Test\n" +
                     "   public void " + methodName + "()\n" +
                     "   {\n" +
                     "      Storyboard storyboard = new Storyboard(\"" + rootDir + "\", \"" + testName + "\");\n" +
                     "      \n" +
                     "      storyboard.add(\"Start situation: \",\n" +
                     "         BACKLOG, " +
                     "\"" + System.getProperty("user.name") + "\", " +
                     "\"" + StoryboardManager.get().dateParser.format(new Date(System.currentTimeMillis())) +  "\", " +
                     "0, 0);\n" +
                     "      \n" +
                     "      storyboard.dumpHTML();\n" +
                     "   }\n\n";

         lastDotPos = fileBody.indexOf('{');

         fileBody = 
               fileBody.substring(0, lastDotPos+2)
               + testBody
               + fileBody.substring(lastDotPos+2);

         StoryboardManager.get().printFile(javaFile, fileBody);
      }

      return this;
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
      removeAllFromLogEntries();
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   protected final PropertyChangeSupport listeners = new PropertyChangeSupport(this);

   @Override
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }

   /********************************************************************
    * <pre>
    *              one                       many
    * KanbanEntry ----------------------------------- LogEntry
    *              kanbanEntry                   logEntries
    * </pre>
    */
   
   public static final String PROPERTY_LOGENTRIES = "logEntries";
   
   private LogEntryStoryBoardSet logEntries = null;
   
   public LogEntryStoryBoardSet getLogEntries()
   {
      if (this.logEntries == null)
      {
         return LogEntryStoryBoard.EMPTY_SET;
      }
   
      return this.logEntries;
   }
   
   public boolean addToLogEntries(LogEntryStoryBoard value)
   {
      boolean changed = false;
      
      if (value != null)
      {
         if (this.logEntries == null)
         {
            this.logEntries = new LogEntryStoryBoardSet();
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
   
   public boolean removeFromLogEntries(LogEntryStoryBoard value)
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
   
   public KanbanEntry withLogEntries(LogEntryStoryBoard value)
   {
      addToLogEntries(value);
      return this;
   } 
   
   public KanbanEntry withoutLogEntries(LogEntryStoryBoard value)
   {
      removeFromLogEntries(value);
      return this;
   } 
   
   public void removeAllFromLogEntries()
   {
      LinkedHashSet<LogEntryStoryBoard> tmpSet = new LinkedHashSet<LogEntryStoryBoard>(this.getLogEntries());
   
      for (LogEntryStoryBoard value : tmpSet)
      {
         this.removeFromLogEntries(value);
      }
   }

   @Override
   public int compareTo(KanbanEntry o)
   {
      if (this.getName() != null)
      {
         return this.getName().compareTo(o.getName());
      }
      
      return 0;
   }

   
   //==========================================================================
   
   public static final String PROPERTY_OLDNOOFLOGENTRIES = "oldNoOfLogEntries";
   
   private int oldNoOfLogEntries;

   public int getOldNoOfLogEntries()
   {
      return this.oldNoOfLogEntries;
   }
   
   public void setOldNoOfLogEntries(int value)
   {
      if (this.oldNoOfLogEntries != value)
      {
         int oldValue = this.oldNoOfLogEntries;
         this.oldNoOfLogEntries = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_OLDNOOFLOGENTRIES, oldValue, value);
      }
   }
   
   public KanbanEntry withOldNoOfLogEntries(int value)
   {
      setOldNoOfLogEntries(value);
      return this;
   } 

   @Override
   public String toString()
   {
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getName());
      _.append(" ").append(this.getOldNoOfLogEntries());
      _.append(" ").append(this.getPhases());
      return _.substring(1);
   }

   public ArrayList<LogEntryStoryBoard> getAllLogEntries()
   {
      ArrayList<LogEntryStoryBoard> allLogEntries = new ArrayList<LogEntryStoryBoard>();
      
      allLogEntries.addAll(this.getLogEntries());
      
      for (KanbanEntry subEntry : this.getSubentries())
      {
         allLogEntries.addAll(subEntry.getAllLogEntries());
      }
      
      return allLogEntries;
   }


   
   //==========================================================================
   
   public static final String PROPERTY_PHASES = "phases";
   
   private String phases;

   public String getPhases()
   {
      return this.phases;
   }
   
   public void setPhases(String value)
   {
      if ( ! StrUtil.stringEquals(this.phases, value))
      {
         String oldValue = this.phases;
         this.phases = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_PHASES, oldValue, value);
      }
   }
   
   public KanbanEntry withPhases(String value)
   {
      setPhases(value);
      return this;
   } 

   public KanbanEntry withLogEntries(LogEntryStoryBoard... value)
   {
      for (LogEntryStoryBoard item : value)
      {
         addToLogEntries(item);
      }
      return this;
   } 

   public KanbanEntry withoutLogEntries(LogEntryStoryBoard... value)
   {
      for (LogEntryStoryBoard item : value)
      {
         removeFromLogEntries(item);
      }
      return this;
   }

   public LogEntryStoryBoard createLogEntries()
   {
      LogEntryStoryBoard value = new LogEntryStoryBoard();
      withLogEntries(value);
      return value;
   } 
}

