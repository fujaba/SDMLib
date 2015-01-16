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

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

import org.junit.Assert;
import org.sdmlib.CGUtil;
import org.sdmlib.StrUtil;
import org.sdmlib.codegen.Parser;
import org.sdmlib.codegen.SymTabEntry;
import org.sdmlib.doc.GraphFactory;
import org.sdmlib.doc.interfaze.Adapter.GuiAdapter;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.logic.GenClass;
import org.sdmlib.models.modelsets.ModelSet;
import org.sdmlib.models.objects.GenericGraph;
import org.sdmlib.models.objects.GenericObject;
import org.sdmlib.models.objects.util.GenericObjectSet;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.serialization.EntityFactory;
import org.sdmlib.serialization.PropertyChangeInterface;
import org.sdmlib.storyboards.util.StoryboardStepSet;

import de.uniks.networkparser.Filter;
import de.uniks.networkparser.interfaces.SendableEntityCreator;
import de.uniks.networkparser.json.JsonArray;
import de.uniks.networkparser.json.JsonIdMap;
import de.uniks.networkparser.logic.ConditionMap;
import de.uniks.networkparser.logic.ValuesMap;

public class Storyboard implements PropertyChangeInterface
{
   public static final String PROPERTY_STEPDONECOUNTER = "stepDoneCounter";
   public static final String PROPERTY_STEPCOUNTER = "stepCounter";
   public static final String PROPERTY_MODELROOTDIR = "modelRootDir";
   public static final String PROPERTY_ROOTDIR = "rootDir";
   public static final String PROPERTY_WALL = "wall";
   public static final String PROPERTY_STORYBOARDSTEPS = "storyboardSteps";

   public static final String MODELING = "modeling";
   public static final String ACTIVE = "active";
   public static final String DONE = "done";
   public static final String IMPLEMENTATION = "implementation";
   public static final String BACKLOG = "backlog";

   private String name;
   private GuiAdapter adapter;
   private String javaTestFileName;
   private JsonArray largestJsonArray = null;
   private Object largestRoot = null;
   private String kanbanWorkFlow = null;
   private String projectName = null;
   private int stepDoneCounter;
   private String sprintName = null;
   private int stepCounter;
   private String modelRootDir = null;
   private String rootDir = null;
//   private StoryboardWall wall = null;
   private StoryboardStepSet storyboardSteps = null;
   private int codeStartLineNumber = -1;
   private ByteArrayOutputStream systemOutRecorder;
   private JsonIdMap jsonIdMap = null;
   private LinkedHashMap<String, String> iconMap = new LinkedHashMap<String, String>();

   public Storyboard withJsonIdMap(JsonIdMap jsonIdMap)
   {
      this.jsonIdMap = jsonIdMap;
      return this;
   }

   public GuiAdapter getAdapter()
   {
      if (adapter == null)
      {
         adapter = GraphFactory.getAdapter();
      }
      return adapter;
   }

   public Storyboard withAdapter(GuiAdapter adapter)
   {
      this.adapter = adapter;
      return this;
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public Storyboard withName(String name)
   {
      setName(name);
      return this;
   }

   public String findRootDir()
   {
      if (rootDir == null)
      {
         this.rootDir = "src";

         Exception e = new RuntimeException();

         StackTraceElement[] stackTrace = e.getStackTrace();
         StackTraceElement callEntry = stackTrace[1];

         // find first method outside Storyboard
         int i = 1;

         while (true)
         {
            callEntry = stackTrace[i];

            if (callEntry.getClassName().equals(Storyboard.class.getName()))
            {
               i++;
               continue;
            }
            else
            {
               break;
            }
         }

         javaTestFileName = callEntry.getClassName().replaceAll("\\.", "/") + ".java";

         // search for a subdirectory containing the javaTestFile of the
         // execution directory and search for the subdi
         File projectDir = new File(".");
         searchDirectoryTree(projectDir);

      }

      return this.rootDir;
   }

   private boolean searchDirectoryTree(File projectDir)
   {
      for (File subDir : projectDir.listFiles())
      {
         if (subDir.isDirectory())
         {
            String subPath = subDir.getPath();
            if (new File(subPath + "/" + javaTestFileName).exists())
            {
               // got it
               this.rootDir = subDir.getPath();
               javaTestFileName = "../" + rootDir + "/" + javaTestFileName;

               return true;
            }
            else
            {
               boolean done = searchDirectoryTree(subDir);
               if (done)
                  return true;
            }
         }
      }

      return false;
   }

   public Storyboard()
   {
      findRootDir();

      Exception e = new RuntimeException();
      StackTraceElement[] stackTrace = e.getStackTrace();
      StackTraceElement callEntry = stackTrace[1];

      String methodName = stackTrace[1].getMethodName();

      if (methodName.startsWith("test"))
      {
         methodName = methodName.substring(4);
      }

      setName(methodName);

      this.addToSteps(name);
   }

   private void addToSteps(String text)
   {
      StoryboardStep storyStep = new StoryboardStep().withText(text);
      this.addToStoryboardSteps(storyStep);
   }

   // private String rootDir = null;

   public Storyboard(String rootDir)
   {
      if (rootDir == null)
      {
         // do nothing just for getSendableInstance
         return;
      }

      this.rootDir = rootDir;

      Exception e = new RuntimeException();

      StackTraceElement[] stackTrace = e.getStackTrace();
      StackTraceElement callEntry = stackTrace[1];
      javaTestFileName = "../" + rootDir + "/" + callEntry.getClassName().replaceAll("\\.", "/") + ".java";

      String methodName = stackTrace[1].getMethodName();

      if (methodName.startsWith("test"))
      {
         methodName = methodName.substring(4);
      }

      setName(methodName);

      this.addToSteps(name);

   }

   public Storyboard(String rootDir, String name)
   {
      this.rootDir = rootDir;
      setName(name);

      this.addToSteps(name);

      Exception e = new RuntimeException();

      StackTraceElement[] stackTrace = e.getStackTrace();
      StackTraceElement callEntry = stackTrace[1];
      javaTestFileName = "../" + rootDir + "/" + callEntry.getClassName().replaceAll("\\.", "/") + ".java";
   }

   public void dumpHTML(KanbanEntry kanbanBoard)
   {
      // get kanbanEntry
      KanbanEntry kanbanEntry = kanbanBoard.findOldEntry(this.getName());

      if (kanbanEntry == null)
      {
         Date today = new Date(System.currentTimeMillis());
         SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss z");

         String todayString = dateFormat.format(today);
         kanbanEntry = new KanbanEntry()
            .withName(this.getName())
            .withPhase(BACKLOG)
            .withParent(kanbanBoard)
            .withLogEntries(
               new LogEntryStoryBoard()
                  .withDate(todayString)
                  .withPhase(BACKLOG)
                  .withDeveloper(System.getProperty("user.name"))
                  .withHoursRemainingInTotal(0.0));
      }

      if (kanbanEntry.getPhase() == null)
      {
         kanbanEntry.setPhase(Kanban.BACKLOG);
      }

      if (this.kanbanWorkFlow != null)
      {
         kanbanEntry.setPhases(this.kanbanWorkFlow);

         KanbanEntry parent = kanbanEntry.getParent();

         while (parent != null)
         {
            parent.setPhases(this.kanbanWorkFlow);
            parent = parent.getParent();
         }
      }

      if (this.projectName != null)
      {
         kanbanBoard.setName(projectName);
      }

      if (sprintName != null)
      {
         KanbanEntry sprintEntry = kanbanBoard.findOrCreate(sprintName);
         kanbanEntry.setParent(sprintEntry);

         if (sprintEntry.getParent() == null)
         {
            sprintEntry.setParent(kanbanBoard);
         }

         if (sprintEntry.getPhase() == null)
         {
            sprintEntry.setPhase(ACTIVE);
         }
      }

      // reuse old logentries to keep kanban.json stable
      double remainingTime = 0.0;
      double hoursSpend = 0.0;
      Iterator<LogEntryStoryBoard> oldLogEntriesIter = ((HashSet<LogEntryStoryBoard>) kanbanEntry.getLogEntries()
         .clone()).iterator();
      Date latestEntryTime = null;

      for (LogEntryStoryBoard newEntry : newLogEntries.values())
      {
         if (latestEntryTime == null)
         {
            latestEntryTime = newEntry.getParsedDate();
            remainingTime = newEntry.getHoursRemainingInTotal();
         }
         else if (latestEntryTime.compareTo(newEntry.getParsedDate()) < 0)
         {
            latestEntryTime = newEntry.getParsedDate();
            remainingTime = newEntry.getHoursRemainingInTotal();
         }

         hoursSpend += newEntry.getHoursSpend();

         if (oldLogEntriesIter.hasNext())
         {
            LogEntryStoryBoard oldEntry = oldLogEntriesIter.next();
            oldEntry.withDeveloper(newEntry.getDeveloper())
               .withDate(newEntry.getDate())
               .withHoursRemainingInTotal(newEntry.getHoursRemainingInTotal())
               .withHoursSpend(newEntry.getHoursSpend())
               .withPhase(newEntry.getPhase());
         }
         else
         {
            // out of old entries, just add new entry
            kanbanEntry.addToLogEntries(newEntry);
         }
      }

      kanbanEntry.setHoursRemaining(remainingTime);

      // remove obsolet oldLogEntries
      while (oldLogEntriesIter.hasNext())
      {
         LogEntryStoryBoard oldEntry = oldLogEntriesIter.next();
         kanbanEntry.removeFromLogEntries(oldEntry);
      }

      // generate the html text
      String htmlText = "<!DOCTYPE html>\n"
         + "<html>\n" +
            "<head>" +
            "<meta charset=\"utf-8\">\n" +
            "<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">\n" +
            "<link href=\"includes/diagramstyle.css\" rel=\"stylesheet\" type=\"text/css\">\n" +
            "\n" +
            "<script src=\"includes/dagre.min.js\"></script>\n" +
            "<script src=\"includes/drawer.js\"></script>\n" +
            "<script src=\"includes/graph.js\"></script>\n" +
            "</head>" +
            "<body>\n" +
            "<p>Storyboard <a href='testfilename' type='text/x-java'>storyboardName</a></p>\n" +
            "$text\n" +
            "</body>\n" +
            "</html>\n";

      String storyboardName = this.getName();

      htmlText = htmlText.replaceFirst("storyboardName", storyboardName);
      htmlText = htmlText.replaceFirst("testfilename", javaTestFileName);
      kanbanEntry.setName(storyboardName);

      String shortFileName = "" + storyboardName + ".html";
      String pathname = "doc/" + shortFileName;

      StringBuffer text = new StringBuffer();

      for (StoryboardStep step : this.getStoryboardSteps())
      {
         String content = step.getText();

         if (content.startsWith("<"))
         {
            // already html
            text.append(content);
         }
         else if (content.startsWith("screendump="))
         {
            String[] split = content.split("=");
            content = split.clone()[1];
            String imgText = "<img src='" + content + "' />\n";

            text.append(imgText);
         }
         else
         {
            text.append("<p>" + content + "</p>\n");
         }
      } // for

      int pos = htmlText.indexOf("$text");

      htmlText = htmlText.substring(0, pos)
         + text.toString()
         + htmlText.substring(pos + "$text".length());

      writeToFile(shortFileName, htmlText);

      coverage4GeneratedModelCode(largestRoot);
   }

   private void writeToFile(String imgName, String fileText)
   {
      try
      {
         if (imgName.startsWith("<"))
         {
            System.out.println("Ups, invalid file name: " + imgName);
            return;
         }

         File oldFile = new File("doc/" + imgName);

         if (oldFile.exists())
         {
            // load old file content and compare with new fileText, if no
            // change, do not write

            BufferedReader in = new BufferedReader(new FileReader(oldFile));
            StringBuilder oldFileText = new StringBuilder();

            String line = in.readLine();
            while (line != null)
            {
               oldFileText.append(line).append("\n");
               line = in.readLine();
            }

            String oldFileString = oldFileText.toString().trim();

            fileText = fileText.replaceAll("\\r", "");

            int oldStringLength = oldFileString.length();
            int newStringLength = fileText.length();

            // for (int i = 0; i < Math.min(oldStringLength, newStringLength);
            // i++)
            // {
            // if (oldFileString.charAt(i) != fileText.charAt(i))
            // {
            // System.out.println("Found diff");
            // }
            // }

            if (oldFileString.equals(fileText.trim()))
            {
               // do not write file, no change
               return;
            }
         }

         BufferedWriter out = new BufferedWriter(new FileWriter("doc/" + imgName));

         out.write(fileText);
         out.close();
      }
      catch (IOException e)
      {
         // e.printStackTrace();
      }
   }

   // private int stepCounter = 0;

   public Storyboard addStep(String txt)
   {
      if (stepCounter == 0)
      {
         this.add("Start: " + txt);
         this.setStepCounter(this.getStepCounter() + 1);
      }
      else
      {
         StringBuilder buf = new StringBuilder("<p><a name = 'step_stepCounter'>Step stepCounter: text</a></p>");
         CGUtil.replaceAll(buf,
            "stepCounter", "" + stepCounter,
            "text", txt);
         this.add(buf.toString());
         this.setStepCounter(this.getStepCounter() + 1);
      }
      return this;
   }

   public void add(String string)
   {
      this.addToSteps(string);
      synchronized (this)
      {
         this.notifyAll();
      }
   }

   public void addText(String string)
   {
      this.add(string);
   }

   public Storyboard withMap(JsonIdMap map)
   {
      this.jsonIdMap = map;
      return this;
   }

   public void coverage4GeneratedModelCode(Object root)
   {
      if (root == null)
      {
         return;
      }

      // derive creator class from root and create idMap
      String className = root.getClass().getName();
      String packageName = CGUtil.packageName(className) + ".util";
      className = packageName + ".CreatorCreator";

      Object idMap = null;
      try
      {
         if (jsonIdMap == null)
         {
            Class<?> creatorClass = Class.forName(className);
            Method method = creatorClass.getDeclaredMethod("createIdMap", String.class);

            idMap = method.invoke(null, "debug");

            jsonIdMap = (JsonIdMap) idMap;
         }

         if (largestJsonArray == null)
         {
            largestJsonArray = jsonIdMap.toJsonArray(root);
         }

         JsonIdMap copyMap = (JsonIdMap) new JsonIdMap().withCreator(jsonIdMap);

         copyMap.decode(largestJsonArray);

         coverSetAndPOClasses(copyMap);

         coverSeldomModelMethods(copyMap);

      }
      catch (Exception e)
      {
         // cannot find creator creator class, sorry.
         e.printStackTrace();
      }
   }

   public void coverSeldomModelMethods(JsonIdMap copyMap) throws NoSuchMethodException, IllegalAccessException,
         InvocationTargetException
   {
      LinkedHashSet<String> handledClassesNames = new LinkedHashSet<String>();

      LinkedHashSet<String> keySet = new LinkedHashSet<String>();
      keySet.addAll(copyMap.keySet());
      // loop through objects
      for (String key : keySet)
      {
         try
         {
            Object object = keySet;

            // that class is already handled?
            String className = object.getClass().getName();

            if (handledClassesNames.contains(className))
            {
               continue;
            }

            handledClassesNames.add(className);

            // call toString
            object.toString();

            Class<? extends Object> objectClass = object.getClass();

            try
            {
               Method addPropertyChangeListenerMetod = objectClass.getMethod("addPropertyChangeListener",
                  PropertyChangeListener.class);
               addPropertyChangeListenerMetod.invoke(object, new Object[]
               { null });
            }
            catch (Exception e)
            {
               // dont worry
            }

            // call createXY methods (some of them are not used in practice, e.g
            // student.createUniversity())
            for (Method m : objectClass.getMethods())
            {
               String methodName = m.getName();
               if (methodName.startsWith("create") && m.getParameterTypes().length == 0)
               {
                  try
                  {
                     m.invoke(object);
                  }
                  catch (Exception e)
                  {
                  }
               }

               if (methodName.startsWith("get") && methodName.endsWith("Transitive"))
               {
                  try
                  {
                     m.invoke(object);
                  }
                  catch (Exception e)
                  {
                  }
               }
            }

            Method removeMethod = objectClass.getMethod("removeYou");

            removeMethod.invoke(object);
         }
         catch (Exception x)
         {
            // dont worry
         }

      }
   }

   public void coverSetAndPOClasses(JsonIdMap copyMap)
   {
      // loop through objects in jsonIdMap, pack them into set, read and write
      // all attributes
      LinkedHashSet<String> keySet = new LinkedHashSet<String>();
      keySet.addAll(copyMap.keySet());
      for (String key : keySet)
      {
         Object object = copyMap.getObject(key);

         SendableEntityCreator creatorClass = copyMap.getCreatorClass(object);

         String className = object.getClass().getName();
         String packageName = CGUtil.packageName(className) + ".util";
         String setClassName = packageName + "." + CGUtil.shortClassName(className) + "Set";

         try
         {
            Class<?> setClass = Class.forName(setClassName);
            Object setObject = setClass.newInstance();

            if (setObject instanceof ModelSet)
            {
               // cover ModelSet methods
               String entryType = ((ModelSet) setObject).getEntryType();
            }

            // add entry
            Method withMethod = setClass.getMethod("with", new Class[]
            { Object.class });
            withMethod.invoke(setObject, object);
            withMethod.invoke(setObject, setObject);

            PatternObject patternObject = null;
            Class patternObjectClass = null;
            Method hasPOMethod = null;
            try
            {
               hasPOMethod = setClass.getMethod("has" + CGUtil.shortClassName(className) + "PO");
               patternObject = (PatternObject) hasPOMethod.invoke(setObject);

               patternObjectClass = patternObject.getClass();

               // call allMatches
               Method allMatchesMethod = patternObjectClass.getMethod("allMatches");
               allMatchesMethod.invoke(patternObject);

               // Method poConstructor =
               // patternObjectClass.getMethod(CGUtil.shortClassName(patternObjectClass.getName()));
               // poConstructor.invoke(null);

            }
            catch (Exception e)
            {
               // e.printStackTrace();
            }

            // toString
            String text = setObject.toString();

            // loop through attributes
            for (String attrName : creatorClass.getProperties())
            {
               try
               {
                  // call getter
                  Method getMethod = setClass.getMethod("get" + StrUtil.upFirstChar(attrName));

                  Object value = getMethod.invoke(setObject);
                  Object setValue = null;
                  // get direct value
                  if (value instanceof Collection)
                  {
                     setValue = value;
                     value = ((Collection<?>) value).iterator().next();
                  }

                  Class<?> valueClass = value.getClass();

                  if (value instanceof Integer)
                  {
                     valueClass = int.class;
                  }
                  else if (value instanceof Double)
                  {
                     valueClass = double.class;
                  }
                  else if (value instanceof Long)
                  {
                     valueClass = long.class;
                  }
                  else if (value instanceof Float)
                  {
                     valueClass = float.class;
                  }
                  else if (value instanceof Boolean)
                  {
                     valueClass = boolean.class;
                  }

                  // call setter
                  Method setMethod = setClass.getMethod("with" + StrUtil.upFirstChar(attrName), valueClass);
                  setMethod.invoke(setObject, value);

                  try
                  {
                     Method unsetMethod = setClass.getMethod("without" + StrUtil.upFirstChar(attrName), valueClass);
                     unsetMethod.invoke(setObject, value);
                  }
                  catch (Exception e)
                  {
                  }

                  try
                  {
                     Method hasMethod = setClass.getMethod("has" + StrUtil.upFirstChar(attrName), valueClass);
                     hasMethod.invoke(setObject, value);

                     hasMethod = setClass.getMethod("has" + StrUtil.upFirstChar(attrName), valueClass, valueClass);
                     hasMethod.invoke(setObject, value, value);
                  }
                  catch (Exception e)
                  {
                  }

                  try
                  {
                     Method hasMethod = setClass.getMethod("has" + StrUtil.upFirstChar(attrName), Object.class);
                     hasMethod.invoke(setObject, value);
                     if (setValue != null)
                     {
                        hasMethod.invoke(setObject, setValue);
                     }
                  }
                  catch (Exception e)
                  {
                  }

                  // also cover creatorclass set method
                  creatorClass.setValue(object, attrName, value, "");
                  creatorClass.setValue(object, attrName, value, JsonIdMap.REMOVE);

                  patternObject = (PatternObject) hasPOMethod.invoke(setObject);

                  if (patternObject != null)
                  {
                     // cover attr methods for pattern object
                     try
                     {
                        // getName
                        getMethod = patternObjectClass.getMethod("get" + StrUtil.upFirstChar(attrName));
                        getMethod.invoke(patternObject);
                     }
                     catch (Exception e)
                     {
                     }
                     try
                     {
                        // createName
                        withMethod = patternObjectClass.getMethod("with" + StrUtil.upFirstChar(attrName), valueClass);
                        withMethod.invoke(patternObject, value);
                     }
                     catch (Exception e)
                     {
                     }
                     try
                     {
                        // createName
                        Method createMethod = patternObjectClass.getMethod("create" + StrUtil.upFirstChar(attrName),
                           valueClass);
                        createMethod.invoke(patternObject, value);
                     }
                     catch (Exception e)
                     {
                     }
                     try
                     {
                        Method hasMethod = patternObjectClass.getMethod("has" + StrUtil.upFirstChar(attrName),
                           valueClass);
                        hasMethod.invoke(patternObject, value);
                     }
                     catch (Exception e)
                     {
                     }
                     try
                     {
                        Method hasMethod = patternObjectClass.getMethod("has" + StrUtil.upFirstChar(attrName),
                           valueClass, valueClass);
                        hasMethod.invoke(patternObject, value, value);
                     }
                     catch (Exception e)
                     {
                     }
                     try
                     {
                        Method hasMethod = patternObjectClass.getMethod("has" + StrUtil.upFirstChar(attrName));
                        hasMethod.invoke(patternObject);
                     }
                     catch (Exception e)
                     {
                     }
                     try
                     {
                        Method method = patternObjectClass.getMethod("create" + StrUtil.upFirstChar(attrName));
                        method.invoke(patternObject);
                     }
                     catch (Exception e)
                     {
                     }
                     try
                     {
                        String poClassName = CGUtil.helperClassName(valueClass.getName(), "PO");
                        SendableEntityCreator poCreator = copyMap.getCreator(poClassName, true);
                        Object po = poCreator.getSendableInstance(false);
                        try
                        {
                           Method method = patternObjectClass.getMethod("has" + StrUtil.upFirstChar(attrName),
                              po.getClass());
                           method.invoke(patternObject, po);
                        }
                        catch (Exception e)
                        {
                        }
                        try
                        {
                           Method method = patternObjectClass.getMethod("with" + StrUtil.upFirstChar(attrName),
                              po.getClass());
                           method.invoke(patternObject, po);
                        }
                        catch (Exception e)
                        {
                        }
                        try
                        {
                           Method method = patternObjectClass.getMethod("without" + StrUtil.upFirstChar(attrName),
                              po.getClass());
                           method.invoke(patternObject, po);
                        }
                        catch (Exception e)
                        {
                        }
                        try
                        {
                           Method method = patternObjectClass.getMethod("create" + StrUtil.upFirstChar(attrName),
                              po.getClass());
                           method.invoke(patternObject, po);
                        }
                        catch (Exception e)
                        {
                        }
                     }
                     catch (Exception e)
                     {
                     }

                  }

               }
               catch (Exception e)
               {
                  // no problem, go on with next attr
               }
            }

            // del entry
            Method withoutMethod = setClass.getMethod("without", object.getClass());
            withoutMethod.invoke(setObject, object);

            creatorClass.getValue(object, "foo.bar");

            ((EntityFactory) creatorClass).removeObject(object);
         }
         catch (Exception e)
         {
            // no problem, just lower coverage
            // e.printStackTrace();
         }
      }

   }

   public void addClassDiagram(ClassModel model)
   {
      String diagName = this.getName() + "ClassDiagram" + this.getStoryboardSteps().size();
      diagName = model.dumpClassDiagram(diagName);
      this.add(diagName);
   }

   public void addClassDiagram(ClassModel model, String rootDir)
   {
      String diagName = this.getName() + "ClassDiagram" + this.getStoryboardSteps().size();
      diagName = model.dumpClassDiagram(diagName);
      this.addSVGImage(diagName);
   }

   public void addObjectDiagramWith(Object... elems)
   {
      ArrayList<Object> tempElems = new ArrayList<Object>(Arrays.asList((Object[]) elems));
      tempElems.add(true);
      Object[] moreElems = tempElems.toArray();
      addObjectDiagram(moreElems);
   }

   public void addObjectDiagram(Object... elems)
   {
      String objectName;
      String objectIcon;
      Object object;
      Object root = null;
      LinkedHashSet<Object> explicitElems = new LinkedHashSet<Object>();
      boolean restrictToExplicitElems = false;

      // go through all diagram elems
      int i = 0;

      while (i < elems.length)
      {
         objectName = null;
         objectIcon = null;
         object = null;

         while (i < elems.length && elems[i] instanceof String)
         {
            String txt = (String) elems[i];
            String suffix = CGUtil.shortClassName(txt);

            if (txt.indexOf('.') >= 0 && "png gif tif".indexOf(suffix) >= 0)
            {
               // it is a file name
               objectIcon = txt;
            }
            else
            {
               // name for an object
               objectName = (String) elems[i];
            }
            i++;
         }

         if (!(i < elems.length))
         {
            // ups no object for this name.
            break;
         }

         object = elems[i];
         i++;

         if (object == null)
         {
            continue;
         }

         if (object.equals(true))
         {
            restrictToExplicitElems = true;
            continue;
         }

         if (object.getClass().isPrimitive())
         {
            // not an object
            continue;
         }

         if (object instanceof Collection)
         {
            explicitElems.addAll((Collection<?>) object);

            Collection<?> coll = (Collection<?>) object;
            if (!coll.isEmpty())
            {
               object = coll.iterator().next();
            }
            else
            {
               continue;
            }
         }
         else
         {
            // plain object
            explicitElems.add(object);
         }

         if (root == null)
         {
            root = object;
         }

         // do we have a JsonIdMap?
         if (jsonIdMap == null)
         {
            jsonIdMap = (JsonIdMap) new GenericIdMap().withSessionId(null);
            jsonIdMap.getLogger().withError(false);

            // try to infer creator class
            String className = object.getClass().getName();
            String creatorClassName = CGUtil.helperClassName(className, "Creator");
            try
            {
               Class<?> creatorClass = this.getClass().getClassLoader().loadClass(creatorClassName);
               Method createIdMapMethod = creatorClass.getDeclaredMethod("createIdMap", String.class);
               jsonIdMap = (JsonIdMap) createIdMapMethod.invoke(null, null);
               jsonIdMap.getLogger().withError(false);
            }
            catch (Exception e)
            {
               // did not work, thus generic must be enough
            }

         }

         SendableEntityCreator objectCreator = jsonIdMap.getCreatorClass(object);

         if (objectCreator == null || objectCreator instanceof GenericCreator)
         {
            String className = object.getClass().getName();
            String packageName = CGUtil.packageName(className) + ".util";
            className = CGUtil.helperClassName(className, "Creator");

            Object idMap = null;
            try
            {
               Class<?> creatorClass = Class.forName(className);
               Method method = creatorClass.getDeclaredMethod("createIdMap", String.class);

               idMap = method.invoke(null, "debug");

               jsonIdMap.withCreator((((JsonIdMap) idMap)));
            }
            catch (Exception e)
            {
               // cannot find creator creator class, use generic idMap instead;
               System.out.println("Could not find creator class for " + className);
               e.printStackTrace();
            }
         }

         // add to jsonIdMap
         if (objectName != null)
         {
            jsonIdMap.put(objectName, object);
         }
         else
         {
            objectName = jsonIdMap.getId(object);
         }

         if (objectIcon != null)
         {
            iconMap.put(objectName, objectIcon);
         }

      }

      // all names collected, dump it
      if (restrictToExplicitElems)
      {
         RestrictToFilter jsonFilter = new RestrictToFilter(explicitElems);
         addObjectDiagram(jsonIdMap, explicitElems, jsonFilter);
      }
      else
      {
         ConditionMap conditionMap = new AlwaysTrueCondition();
         addObjectDiagram(jsonIdMap, explicitElems, conditionMap);
      }
   }

   private class AlwaysTrueCondition extends ConditionMap
   {
      @Override
      public boolean matches(ValuesMap values)
      {
         // TODO Auto-generated method stub
         return true;
      }
   }

   public void addObjectDiagram(JsonIdMap jsonIdMap, Object root, ConditionMap filter)
   {
      JsonArray jsonArray = jsonIdMap.toJsonArray(root, new Filter().withFull(true).withPropertyRegard(filter));

      if (largestJsonArray == null || largestJsonArray.size() <=
         jsonArray.size())
      {
         largestJsonArray = jsonArray;
         largestRoot = root;
      }

      String imgLink =
            getAdapter().withRootDir(getModelRootDir()).withIconMap(iconMap)
               .toImg(this.getName() + (this.getStoryboardSteps().size() + 1), jsonArray);

      this.addToSteps(imgLink);

      // this.addObjectDiagramFromJsonArray(root, jsonArray);
   }

   public void setKanbanPhase(String string)
   {
      // TODO Auto-generated method stub

   }

   private LinkedHashMap<String, LogEntryStoryBoard> newLogEntries = new LinkedHashMap<String, LogEntryStoryBoard>();

   public LinkedHashMap<String, LogEntryStoryBoard> getNewLogEntries()
   {
      return newLogEntries;
   }

   public void addLogEntry(String phase, String developer, String date, double hoursSpend, double hoursRemaining,
         String comment)
   {
      LogEntryStoryBoard logEntry = new LogEntryStoryBoard()
         .withDate(date)
         .withPhase(phase)
         .withDeveloper(developer)
         .withHoursSpend(hoursSpend)
         .withHoursRemainingInTotal(hoursRemaining)
         .withComment(comment);

      this.addLogEntry(logEntry);
   }

   public void addLogEntry(LogEntryStoryBoard entry)
   {
      newLogEntries.put(entry.getDate(), entry);
   }

   /**
    * Add an image to your storyboard. Example:
    * storyboard.addImage(model.dumpClassDiag("examples",
    * "StudyRight with assignments class generation 02"));
    * 
    * @param image
    */

   void addSVGImage(String imageFile)
   {
      this.addToSteps("<embed type=\"image/svg+xml\" src='" + imageFile + "'>");
   }

   public void addImage(String imageFile)
   {
      this.addToSteps("<img src='" + imageFile + "'>");
   }

   /**
    * Add an entry to your Kanban board TODO: document this, add phase entries
    * to some generic class not into the example!!! Example
    * storyboard.add("ExtendStoryboardByAddToDoMethod", BACKLOG, "zuendorf",
    * "21.08.2012 15:57:42", 0, 1);
    * 
    * @param string The Description
    * @param phase The Phase of Project
    * @param developer The Developer
    * @param date The Current Date
    * @param hoursSpend Value of Time to Spend
    * @param hoursRemaining Value of remaining Hours
    */
   public void add(String string, String phase, String developer, String date, double hoursSpend, double hoursRemaining)
   {
      add(string);
      addLogEntry(new LogEntryStoryBoard()
         .withDate(date)
         .withPhase(phase)
         .withDeveloper(developer)
         .withHoursSpend(hoursSpend)
         .withHoursRemainingInTotal(hoursRemaining)
         .withComment("Achieved: " + string));
   }

   public KanbanEntry addToDo(String entryName, String phase, String developer,
         String date, double hoursSpend, double hoursRemaining)
   {
      StoryboardManager man = StoryboardManager.get();

      KanbanEntry kanbanBoard = man.loadOldKanbanEntries();

      KanbanEntry todoEntry = kanbanBoard.findOrCreate(entryName)
         .withLastDeveloper(developer)
         .withPhase(phase)
         .withParent(kanbanBoard);

      LogEntryStoryBoard logEntry = todoEntry.findOrCreateLogEntry(date, phase)
         .withPhase(phase)
         .withHoursRemainingInTotal(hoursRemaining)
         .withHoursSpend(hoursSpend);

      man.dumpKanban();

      return todoEntry;
   }

   public ByteArrayOutputStream getSystemOut()
   {
      return systemOutRecorder;
   }

   public void markCodeStart()
   {
      // store code start line number

      Exception e = new RuntimeException();

      StackTraceElement[] stackTrace = e.getStackTrace();
      StackTraceElement callEntry = stackTrace[1];
      codeStartLineNumber = callEntry.getLineNumber();
   }

   public void addCode()
   {
      addCode(this.getRootDir());
   }

   public void addCode(String rootDir)
   {
      String className = "";
      // store code end line number
      int codeEndLineNumber = -1;

      Exception e = new RuntimeException();
      StackTraceElement[] stackTrace = e.getStackTrace();
      StackTraceElement callEntry = stackTrace[1];
      if (callEntry.getMethodName().startsWith("addCode"))
      {
         callEntry = stackTrace[2];
      }
      codeEndLineNumber = callEntry.getLineNumber();

      className = callEntry.getClassName();

      // open java file and copy code lines
      String fileName = rootDir + "/" + className.replaceAll("\\.", "/") + ".java";
      File file = new File(fileName);

      if (file.exists())
      {
         try
         {
            BufferedReader in = new BufferedReader(new FileReader(file));

            String line = "";
            int lineNo = 0;

            StringBuilder buf = new StringBuilder();

            while (true)
            {
               line = in.readLine();

               if (line != null)
               {
                  lineNo++;

                  if (lineNo > codeStartLineNumber && lineNo < codeEndLineNumber)
                  {
                     buf.append(line).append('\n');
                  }

                  if (lineNo >= codeEndLineNumber)
                  {
                     this.add("<pre>" + StrUtil.htmlEncode(buf.toString()) + "</pre>");
                     in.close();
                     return;
                  }
               }
            }
         }
         catch (Exception ioe)
         {
            ioe.printStackTrace();
         }
      }
   }

   public void recordSystemOut()
   {
      systemOutRecorder = new ByteArrayOutputStream();
      System.setOut(new PrintStream(systemOutRecorder));
   }

   public String getMethodText(String rootDir, String className, String methodSignature)
   {
      ClassModel model = new ClassModel();

      Clazz clazz = model.createClazz(className);

      GenClass generator = clazz.getClassModel().getGenerator().getOrCreate(clazz);

      Parser parser = generator.getOrCreateParser(rootDir);

      int pos = parser.indexOf(Parser.METHOD + ":" + methodSignature);

      if (pos < 0)
      {
         return "did not find method " + methodSignature + " in class " + className;
      }

      SymTabEntry symTabEntry = parser.getSymTab().get(Parser.METHOD + ":" + methodSignature);

      String methodText = "<pre>   " +
         StrUtil.htmlEncode(parser.getText().substring(symTabEntry.getStartPos(), symTabEntry.getEndPos() + 1))
         + "</pre>";

      return methodText;
   }

   public void addGenericObjectDiag(GenericGraph graph)
   {
      this.addGenericObjectDiag(graph, GenericObject.EMPTY_SET);
   }

   public void addGenericObjectDiag(GenericGraph graph, GenericObjectSet hiddenObjects)
   {
      this.addGenericObjectDiag(this.getName() + "GenObjDiagStep" + this.getStoryboardSteps().size(), graph,
         hiddenObjects);
   }

   public void addGenericObjectDiag(String diagramName, GenericGraph graph)
   {
      this.addGenericObjectDiag(diagramName, graph, GenericObject.EMPTY_SET);
   }

   public void addGenericObjectDiag(String diagramName, GenericGraph graph, GenericObjectSet hiddenObjects)
   {
      String link = this.getAdapter().addGenericObjectDiag(diagramName, graph, hiddenObjects);
      this.addToSteps(link);
   }

   public Storyboard with(String string)
   {
      this.add(string);
      return this;
   }

   public void dumpHTML()
   {
      StoryboardManager.get()
         .add(this)
         .dumpHTML();
   }

   public void assertEquals(String message, double expected, double actual, double delta)
   {
      this.add("Check: " + message + " " + expected + " actual " + actual);

      if (Math.abs(expected - actual) > delta)
      {
         this.dumpHTML();
      }
      Assert.assertEquals("FAILED: " + message, expected, actual, delta);
   }

   public void assertEquals(String message, Object expected, Object actual)
   {
      this.add("Check: " + message + " " + expected + " actual " + actual);

      if (expected != actual)
      {
         this.dumpHTML();
      }
      Assert.assertEquals("FAILED: " + message, expected, actual);
   }

   public void assertTrue(String message, boolean condition)
   {
      this.add("Check: " + message + " " + condition);
      if (!condition)
      {
         this.dumpHTML();
      }
      Assert.assertTrue("FAILED: " + message, condition);
   }

   public void assertFalse(String message, boolean condition)
   {
      this.add("Check: " + message + " " + condition);
      if (condition)
      {
         this.dumpHTML();
      }
      Assert.assertFalse("FAILED: " + message, condition);
   }

   public void assertEquals(String message, int expected, int actual)
   {
      this.add("Check: " + message + " " + expected + " actual " + actual);
      if (expected != actual)
      {
         this.dumpHTML();
      }
      Assert.assertEquals("FAILED: " + message, expected, actual);
   }

   public void assertNotNull(String message, Object obj)
   {
      this.add("Check: " + message + " " + obj);
      if (obj == null)
      {
         this.dumpHTML();
      }
      Assert.assertNotNull("FAILED: " + message, obj);
   }

   public void assertNull(String message, Object obj)
   {
      this.add("Check: " + message + obj);
      if (obj != null)
      {
         this.dumpHTML();
      }
      Assert.assertNull("FAILED: " + message, obj);
   }

   // ==========================================================================

   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);

   @Override
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }

   public void addPropertyChangeListener(PropertyChangeListener listener)
   {
      getPropertyChangeSupport().addPropertyChangeListener(listener);
   }

   // ==========================================================================

   public void removeYou()
   {
//      setWall(null);
      removeAllFromStoryboardSteps();
      withoutStoryboardSteps(this.getStoryboardSteps().toArray(new StoryboardStep[this.getStoryboardSteps().size()]));
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   @Override
   public String toString()
   {
      StringBuilder s = new StringBuilder();

      s.append(" ").append("Storyboard" + this.getStoryboardSteps().getFirst());
      s.append(" ").append(this.getRootDir());
      s.append(" ").append(this.getStepCounter());
      s.append(" ").append(this.getStepDoneCounter());
      return s.substring(1);
   }

   /********************************************************************
    * <pre>
    *              one                       many
    * Storyboard ----------------------------------- StoryboardStep
    *              storyboard                   storyboardSteps
    * </pre>
    * @return The StoryboardWall
    */

   public StoryboardStepSet getStoryboardSteps()
   {
      if (this.storyboardSteps == null)
      {
         return StoryboardStep.EMPTY_SET;
      }

      return this.storyboardSteps;
   }

   public boolean addToStoryboardSteps(StoryboardStep value)
   {
      boolean changed = false;

      if (value != null)
      {
         if (this.storyboardSteps == null)
         {
            this.storyboardSteps = new StoryboardStepSet();
         }

         changed = this.storyboardSteps.add(value);

         if (changed)
         {
            value.withStoryboard(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_STORYBOARDSTEPS, null, value);
         }
      }

      return changed;
   }

   public boolean removeFromStoryboardSteps(StoryboardStep value)
   {
      boolean changed = false;

      if ((this.storyboardSteps != null) && (value != null))
      {
         changed = this.storyboardSteps.remove(value);

         if (changed)
         {
            value.setStoryboard(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_STORYBOARDSTEPS, value, null);
         }
      }

      return changed;
   }

   public Storyboard withStoryboardSteps(StoryboardStep... value)
   {
      for (StoryboardStep item : value)
      {
         addToStoryboardSteps(item);
      }
      return this;
   }

   public Storyboard withoutStoryboardSteps(StoryboardStep... value)
   {
      for (StoryboardStep item : value)
      {
         removeFromStoryboardSteps(item);
      }
      return this;
   }

   public void removeAllFromStoryboardSteps()
   {
      LinkedHashSet<StoryboardStep> tmpSet = new LinkedHashSet<StoryboardStep>(this.getStoryboardSteps());

      for (StoryboardStep value : tmpSet)
      {
         this.removeFromStoryboardSteps(value);
      }
   }

   public StoryboardStep createStoryboardSteps()
   {
      StoryboardStep value = new StoryboardStep();
      withStoryboardSteps(value);
      return value;
   }

   /********************************************************************
    * <pre>
    *              one                       one
    * Storyboard ----------------------------------- StoryboardWall
    *              storyboard                   wall
    * </pre>
    * @return The StoryboardWall
    */
//   public StoryboardWall getWall()
//   {
//      return this.wall;
//   }

//   public boolean setWall(StoryboardWall value)
//   {
//      boolean changed = false;
//
//      if (this.wall != value)
//      {
//         StoryboardWall oldValue = this.wall;
//
//         if (this.wall != null)
//         {
//            this.wall = null;
//            oldValue.setStoryboard(null);
//         }
//
//         this.wall = value;
//
//         if (value != null)
//         {
//            value.withStoryboard(this);
//         }
//
//         getPropertyChangeSupport().firePropertyChange(PROPERTY_WALL, oldValue, value);
//         changed = true;
//      }
//
//      return changed;
//   }

//   public Storyboard withWall(StoryboardWall value)
//   {
//      setWall(value);
//      return this;
//   }

//   public StoryboardWall createWall()
//   {
//      StoryboardWall value = new StoryboardWall();
//      withWall(value);
//      return value;
//   }

   // ==========================================================================
   public String getRootDir()
   {
      return this.rootDir;
   }

   public void setRootDir(String value)
   {
      if (!StrUtil.stringEquals(this.rootDir, value))
      {
         String oldValue = this.rootDir;
         this.rootDir = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_ROOTDIR, oldValue, value);
      }
   }

   public Storyboard withRootDir(String value)
   {
      setRootDir(value);
      return this;
   }

   // ==========================================================================
   public String getModelRootDir()
   {
      if (modelRootDir == null)
      {
         return this.rootDir;
      }
      return this.modelRootDir;
   }

   public void setModelRootDir(String value)
   {
      if (!StrUtil.stringEquals(this.modelRootDir, value))
      {
         String oldValue = this.modelRootDir;
         this.modelRootDir = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_MODELROOTDIR, oldValue, value);
      }
   }

   public Storyboard withModelRootDir(String value)
   {
      setModelRootDir(value);
      return this;
   }

   // ==========================================================================
   public int getStepCounter()
   {
      return this.stepCounter;
   }

   public void setStepCounter(int value)
   {
      if (this.stepCounter != value)
      {
         int oldValue = this.stepCounter;
         this.stepCounter = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_STEPCOUNTER, oldValue, value);
      }
   }

   public Storyboard withStepCounter(int value)
   {
      setStepCounter(value);
      return this;
   }

   // ==========================================================================

   public int getStepDoneCounter()
   {
      return this.stepDoneCounter;
   }

   public void setStepDoneCounter(int value)
   {
      if (this.stepDoneCounter != value)
      {
         int oldValue = this.stepDoneCounter;
         this.stepDoneCounter = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_STEPDONECOUNTER, oldValue, value);
      }
   }

   public Storyboard withStepDoneCounter(int value)
   {
      setStepDoneCounter(value);
      return this;
   }

   public void addPreformatted(String expandedText)
   {
      expandedText = StrUtil.htmlEncode(expandedText);

      this.add("<pre>" + expandedText + "</pre>");

   }

   public void addPattern(PatternObject patternObject, boolean b)
   {
      addPattern(patternObject.getPattern(), b);
   }

   public void addPattern(Pattern pattern, boolean b)
   {
      String diagName = "" + this.getName() + "PatternDiagram" + this.getStoryboardSteps().size();

      String link = pattern.dumpDiagram(diagName, b);
      this.add(link);
   }

   public void setSprint(String string)
   {
      this.sprintName = string;
   }

   public void removeFromProject()
   {
      // load the kanban board and remove entry and logs and generate and store
      StoryboardManager.get()
         .remove(this)
         .dumpHTML();
   }

   public void setKanbanWorkFlow(String string)
   {
      this.kanbanWorkFlow = string;
   }

   public void setProjectName(String string)
   {
      this.projectName = string;
   }

   public void dumpDiagram(PatternObject<?, ?> po, String name)
   {
      po.getPattern().dumpDiagram(name);
   }

   class RestrictToFilter extends ConditionMap
   {
      private LinkedHashSet<Object> explicitElems;

      public RestrictToFilter(LinkedHashSet<Object> explicitElems)
      {
         this.explicitElems = explicitElems;

      }

      @Override
      public boolean matches(ValuesMap values)
      {
         if (values.value != null
            && ("Integer Float Double Long Boolean String"
               .indexOf(values.value.getClass().getSimpleName()) >= 0))
         {
            return true;
         }
         return explicitElems.contains(values.value);
      }
   }

}
