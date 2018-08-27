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

import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileTime;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.uniks.networkparser.ext.DiagramEditor;
import de.uniks.networkparser.json.JsonObject;
import guru.nidi.graphviz.attribute.Font;
import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.LinkTarget;
import guru.nidi.graphviz.model.Node;
import org.junit.Assert;
import org.sdmlib.CGUtil;
import org.sdmlib.StrUtil;
import org.sdmlib.codegen.LocalVarTableEntry;
import org.sdmlib.codegen.Parser;
import org.sdmlib.codegen.StatementEntry;
import org.sdmlib.codegen.SymTabEntry;
import org.sdmlib.doc.DocEnvironment;
import org.sdmlib.doc.GraphFactory;
import org.sdmlib.doc.interfaze.Adapter.GuiAdapter;
import org.sdmlib.models.SDMLibIdMap;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.logic.GenClazzEntity;
import org.sdmlib.models.modelsets.ModelSet;
import org.sdmlib.models.objects.GenericGraph;
import org.sdmlib.models.objects.GenericObject;
import org.sdmlib.models.objects.util.GenericObjectSet;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.tables.Table;
import org.sdmlib.serialization.PropertyChangeInterface;
import org.sdmlib.storyboards.util.StoryboardStepSet;

import de.uniks.networkparser.Filter;
import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.interfaces.ObjectCondition;
import de.uniks.networkparser.interfaces.SendableEntity;
import de.uniks.networkparser.interfaces.SendableEntityCreator;
import de.uniks.networkparser.json.JsonArray;
import de.uniks.networkparser.list.SimpleKeyValueList;

import javax.imageio.ImageIO;

import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;
import static guru.nidi.graphviz.model.Factory.to;

/**
 * A Storyboard collects entries for the generation of an html page from e.g. a JUnit test. This html page will be named like the story, i.e. like the method that created the Storyboard. It will be added to the refs.html and thus become part of the index.html. All these html files are stored in an directory "doc" located in the project root directory.
 *
 * @see #dumpHTML()
 * @see <a href="../../../../../../doc/index.html">SDMLib Storyboards</a>
 * @see <a href= '../../../../../../src/test/java/org/sdmlib/test/kanban/ProjectBoard.java'> ProjectBoard.java</a>
 */
public class StoryboardImpl implements PropertyChangeInterface, SendableEntity
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

   private String name = "";

   private GuiAdapter adapter;

   private String javaTestFileName = "";

   private JsonArray largestJsonArray = null;

   private Object largestRoot = null;

   private int stepDoneCounter;

   private int stepCounter;

   private String modelRootDir = null;

   private String rootDir = null;

   // private StoryboardWall wall = null;
   private StoryboardStepSet storyboardSteps = null;

   private int codeStartLineNumber = -1;

   private ByteArrayOutputStream systemOutRecorder;

   private IdMap jsonIdMap = null;

   private LinkedHashMap<String, String> iconMap = new LinkedHashMap<String, String>();
   private StackTraceElement callEntry;
   private String methodName;
   private String className;
   private String classFileName;
   private String fullFileName;
   private Parser parser;
   private SimpleKeyValueList<String, SymTabEntry> symTab;


   public String getJavaTestFileName()
   {
      return javaTestFileName;
   }


   public void setJavaTestFileName(String javaTestFileName)
   {
      this.javaTestFileName = javaTestFileName;
   }


   public StoryboardImpl withJsonIdMap(IdMap jsonIdMap)
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


   public StoryboardImpl withAdapter(GuiAdapter adapter)
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


   public StoryboardImpl withName(String name)
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

            if (callEntry.getClassName().equals(StoryboardImpl.class.getName())
                    || callEntry.getClassName().equals(Storyboard.class.getName())
                    || callEntry.getClassName().equals(StoryPage.class.getName()))
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
         String path = projectDir.getAbsolutePath();
         searchDirectoryTree(projectDir);

      }

      return this.rootDir;
   }


   public boolean searchDirectoryTree(File projectDir)
   {
      for (File subDir : projectDir.listFiles())
      {
         if (subDir.isDirectory())
         {
            String subPath = subDir.getPath();
            if (new File(subPath + "/" + javaTestFileName).exists())
            {
               // got it
               this.rootDir = subDir.getPath().replaceAll("\\\\", "/");

               javaTestFileName = rootDir + "/" + javaTestFileName;

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


   public StoryboardImpl()
   {
      findRootDir();

      Exception e = new RuntimeException();
      StackTraceElement[] stackTrace = e.getStackTrace();
      StackTraceElement callEntry = stackTrace[1];

      String testClassName = null;
      if (callEntry.getClassName().equals(Storyboard.class.getName()) ||
              callEntry.getClassName().equals(StoryPage.class.getName()))
      {
         callEntry = stackTrace[2];
         testMethodName = stackTrace[2].getMethodName();
         testClassName = CGUtil.shortClassName(stackTrace[2].getClassName());
      }
      else
      {
         testMethodName = stackTrace[1].getMethodName();
         testClassName = CGUtil.shortClassName(stackTrace[1].getClassName());
      }

      String storyName = testMethodName;

      if ("main".equals(storyName) && true)
      {
         storyName = testClassName;
      }

      if (storyName.startsWith("test"))
      {
         storyName = storyName.substring(4);
      }

      setName(storyName);

      // this.addToSteps(name);
   }



   void addToSteps(String text)
   {
      StoryboardStep storyStep = new StoryboardStep().withText(text);
      this.addToStoryboardSteps(storyStep);
   }


   // private String rootDir = null;

   /**
    * @deprecated Storyboards search for their root dir (like src or src/test/java) themself. Thus use the version without parameters.
    * @param rootDir The RootDir of Sources
    */
   @Deprecated
   public StoryboardImpl(String rootDir)
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

      // this.addToSteps(name);

   }


   /**
    * @deprecated Storyboards search for their root dir (like src or src/test/java) themself. Similarly, Storyboards get their name from the method they are used in. Name that method appropriately. Use the version without parameters.
    *
    * @param rootDir The RootDir of Sources
    * @param name Name of the html file and page title to be generated.
    */
   @Deprecated
   public StoryboardImpl(String rootDir, String name)
   {
      this.rootDir = rootDir;
      setName(name);

      // this.addToSteps(name);

      Exception e = new RuntimeException();

      StackTraceElement[] stackTrace = e.getStackTrace();
      StackTraceElement callEntry = stackTrace[1];
      javaTestFileName = "../" + rootDir + "/" + callEntry.getClassName().replaceAll("\\.", "/") + ".java";
   }


   private void writeToFile(String imgName, String fileText)
   {
      writeToDirFile(docDirName, imgName, fileText);
   }


   private void writeToDirFile(String dirName, String imgName, String fileText)
   {
      try
      {
         if (imgName.startsWith("<"))
         {
            System.out.println("Ups, invalid file name: " + imgName);
            return;
         }

         File oldFile = new File(dirName + "/" + imgName);

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

            in.close();

            String oldFileString = oldFileText.toString().trim();

            fileText = fileText.replaceAll("\\r", "");

            int oldStringLength = oldFileString.length();
            int newStringLength = fileText.length();

            if (oldFileString.equals(fileText.trim()))
            {
               // do not write file, no change
               return;
            }
         }

         BufferedWriter out = new BufferedWriter(new FileWriter(dirName + "/" + imgName));

         out.write(fileText);
         out.close();
      }
      catch (IOException e)
      {
         // e.printStackTrace();
      }
   }


   // private int stepCounter = 0;

   public StoryboardImpl addStep(String txt)
   {
      this.setStepCounter(this.getStepCounter() + 1);

      StringBuilder buf = new StringBuilder("<h4><a name = 'step_stepCounter'>Step stepCounter: text</a></h4>\n");
      CGUtil.replaceAll(buf,
              "stepCounter", "" + stepCounter,
              "text", txt);
      this.add(buf.toString());

      return this;
   }



   public void add(String string)
   {
      synchronized (this)
      {
         this.addToSteps(string);
         this.notifyAll();
      }
   }



   public void addText(String string)
   {
      this.add(string);
   }



   public void addTable(Table table)
   {
      String tableText = table.getHtmlTable();

      this.add(tableText);
   }

   public void addLineChart(Table table)
   {
      String tableText = table.getHtmlLineChart("tableChart"+getStoryboardSteps().size());

      this.add(tableText);
   }



   public void addBarChart(Table table)
   {
      String tableText = table.getHtmlBarChart("tableChart"+getStoryboardSteps().size());

      this.add(tableText);
   }



   public StoryboardImpl withMap(IdMap map)
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
            method.setAccessible(true);
            idMap = method.invoke(null, "debug");

            jsonIdMap = (IdMap) idMap;
         }

         if (largestJsonArray == null)
         {
            largestJsonArray = jsonIdMap.toJsonArray(root);
         }

         IdMap copyMap = (IdMap) new IdMap().with(jsonIdMap);

         copyMap.decode(largestJsonArray);

         coverSetAndPOClasses(copyMap);

         coverSeldomModelMethods(copyMap);

      }
      catch (Exception e)
      {
         // cannot find creator creator class, sorry.
         // e.printStackTrace();
      }
   }


   public void coverSeldomModelMethods(IdMap copyMap) throws NoSuchMethodException, IllegalAccessException,
           InvocationTargetException
   {
      LinkedHashSet<String> handledClassesNames = new LinkedHashSet<String>();

      LinkedHashSet<String> keySet = new LinkedHashSet<String>();
      keySet.addAll(copyMap.getCreators().keySet());
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


   public void coverSetAndPOClasses(IdMap copyMap) throws NoSuchMethodException, SecurityException
   {
      // loop through objects in idMap, pack them into set, read and write
      // all attributes
      LinkedHashSet<String> keySet = new LinkedHashSet<String>();
      keySet.addAll(copyMap.getKeyValue().keySet());
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

            try
            {
               hasPOMethod = setClass.getMethod("create" + CGUtil.shortClassName(className) + "PO");
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

                  try
                  {
                     Method hasMethod = setClass.getMethod("filter" + StrUtil.upFirstChar(attrName), valueClass);
                     hasMethod.invoke(setObject, value);

                     hasMethod = setClass.getMethod("filter" + StrUtil.upFirstChar(attrName), valueClass, valueClass);
                     hasMethod.invoke(setObject, value, value);
                  }
                  catch (Exception e)
                  {
                  }

                  try
                  {
                     Method hasMethod = setClass.getMethod("filter" + StrUtil.upFirstChar(attrName), Object.class);
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
                  creatorClass.setValue(object, attrName, value, SendableEntityCreator.REMOVE);

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
                        Method hasMethod = patternObjectClass.getMethod("filter" + StrUtil.upFirstChar(attrName),
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
                        Method hasMethod = patternObjectClass.getMethod("filter" + StrUtil.upFirstChar(attrName),
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
                        Method hasMethod = patternObjectClass.getMethod("filter" + StrUtil.upFirstChar(attrName));
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
                        SendableEntityCreator poCreator = copyMap.getCreator(poClassName, true, null);
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
                           Method method = patternObjectClass.getMethod("filter" + StrUtil.upFirstChar(attrName),
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

            // creatorClass.removeObject(object);
            Method removeMethod = creatorClass.getClass().getMethod("removeObject", Object.class);
            removeMethod.invoke(creatorClass, object);
         }
         catch (Exception e)
         {
            // no problem, just lower coverage
            // e.printStackTrace();
         }
      }

      // go through all creator classes and call createIdMap
      for (SendableEntityCreator creator : copyMap.getCreators().values())
      {
         try
         {
            Method createIdMapMethod = creator.getClass().getMethod("createIdMap", String.class);
            createIdMapMethod.invoke(creator, "t");
         }
         catch (Exception e)
         {
         }
      }

   }



   public void addClassDiagram(ClassModel model)
   {
      String diagScript = this.getName() + "ClassDiagram" + this.getStoryboardSteps().size();
      diagScript = model.dumpClassDiagram(diagScript);
      this.add(diagScript);
   }


   public void addClassDiagramAsImage(de.uniks.networkparser.ext.ClassModel model, int... dimensions)
   {
      String diagScript = this.getName() + "ClassDiagram" + this.getStoryboardSteps().size();
      String htmlString = model.dumpHTMLString();
      int startPos = htmlString.indexOf("var json=");
      int endPos = htmlString.indexOf("};", startPos);
      String bodyString = htmlString.substring(startPos+9, endPos+1);
      this.addAsImage(bodyString, true, "graphviz-java", dimensions);
   }


   public void addClassDiagramAsImage(ClassModel model, int... dimensions)
   {
      String diagScript = this.getName() + "ClassDiagram" + this.getStoryboardSteps().size();
      diagScript = model.dumpClassDiagram(diagScript);
      this.addAsImage(diagScript, true, dimensions);
   }



   public void addObjectDiagramWithViaGraphViz(Object... elems)
   {
      ArrayList<Object> tempElems = new ArrayList<Object>(Arrays.asList((Object[]) elems));
      tempElems.add(true);
      Object[] moreElems = tempElems.toArray();
      addObjectDiagramViaGraphViz(moreElems);
   }

   public void addObjectDiagramWith(Object... elems)
   {
      ArrayList<Object> tempElems = new ArrayList<Object>(Arrays.asList((Object[]) elems));
      tempElems.add(true);
      Object[] moreElems = tempElems.toArray();
      addObjectDiagram(moreElems);
   }



   public void addObjectDiagramViaGraphViz(Object... elems)
   {
      this.addObjectDiagramInternal("graphviz-java", elems);
   }



   public void addObjectDiagramAsImage(Object... elems)
   {
      this.addObjectDiagramInternal("diagramEditor", elems);
   }



   public void addObjectDiagram(Object... elems)
   {
      this.addObjectDiagramInternal("javaScript", elems);
   }


   private void addObjectDiagramInternal(String addAsImageMode, Object... elems)
   {
      String objectName;
      String objectIcon;
      Object object;
      Object root = null;
      LinkedHashSet<Object> explicitElems = new LinkedHashSet<Object>();
      boolean restrictToExplicitElems = false;
      int[] dimensions = new int[]{900, 600};

      // do we have a JsonIdMap?
      if (jsonIdMap == null)
      {
         // jsonIdMap = (IdMap) new GenericIdMap().withSessionId(null);
         jsonIdMap = (IdMap) new SDMLibIdMap("s").withSession(null).withTimeStamp(1);
         // FIXME TRY IF NESSESSARY jsonIdMap.getLogger().withError(false);
      }

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

         if (object instanceof Integer && i < elems.length && elems[i] instanceof Integer)
         {
            dimensions[0] = (Integer) object;
            dimensions[1] = (Integer) elems[i];
            i++;
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
         addObjectDiagram(jsonIdMap, explicitElems, jsonFilter, addAsImageMode, dimensions);
      }
      else
      {
         AlwaysTrueCondition conditionMap = new AlwaysTrueCondition();
         addObjectDiagram(jsonIdMap, explicitElems, conditionMap, addAsImageMode, dimensions);
      }
   }


   private class AlwaysTrueCondition implements ObjectCondition
   {
      public boolean update(Object value)
      {
         return true;
      }
   }



   private void addObjectDiagram(IdMap jsonIdMap, Object root, ObjectCondition filter, String addAsImageMode, int... dimensions)
   {
      JsonArray jsonArray = jsonIdMap.toJsonArray(root, Filter.createFull().withPropertyRegard(filter));

      if (largestJsonArray == null || largestJsonArray.size() <= jsonArray.size())
      {
         largestJsonArray = jsonArray;
         largestRoot = root;
      }

      String javaScript4Diag = "";
      if (addAsImageMode.equals("graphviz-java"))
      {
         javaScript4Diag = jsonArray.toString(3);
      }
      else
      {
         javaScript4Diag = getAdapter().withRootDir(getModelRootDir()).withIconMap(iconMap)
                 .toImg(this.getName() + (this.getStoryboardSteps().size() + 1), jsonArray);
      }

      if (addAsImageMode.equals("diagramEditor") || addAsImageMode.equals("graphviz-java"))
      {
         addAsImage(javaScript4Diag, true, addAsImageMode, dimensions);
      }
      else
      {
         this.addToSteps(javaScript4Diag);
      }

      // this.addObjectDiagramFromJsonArray(root, jsonArray);
   }




   void addSVGImage(String imageFile)
   {
      this.addToSteps("<embed type=\"image/svg+xml\" src='" + imageFile + "'>");
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
      if (callEntry.getClassName().equals(Storyboard.class.getName()) || callEntry.getClassName().equals(StoryPage.class.getName()))
      {
         callEntry = stackTrace[2];
      }
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

      int i = 1;

      while (callEntry.getMethodName().startsWith("addCode"))
      {
         i++;
         callEntry = stackTrace[i];
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
                     this.add("<pre><code class=\"java\" data-lang=\"java\">\n" + StrUtil.htmlEncode(buf.toString()) + "</code></pre>\n");
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
      ClassModel clazzModel = (ClassModel) clazz.getClassModel();
      GenClazzEntity generator = clazzModel.getGenerator().getOrCreate(clazz);

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


   public StoryboardImpl with(String string)
   {
      this.add(string);
      return this;
   }


   public void dumpJavaDoc(String targetClassName)
   {
      this.dumpHTML(targetClassName, null);
   }

   public void dumpJavaDoc(String targetClassName, String targetMethodName)
   {
      this.dumpHTML(targetClassName, targetMethodName);
   }


   public void dumpHTML()
   {
      this.dumpHTML(null, null);
   }

   public void addImage(String imageFile, int... dims)
   {
      int num = 0;
      if (this.storyboardSteps != null)
      {
         num = this.storyboardSteps.size();
      }

      int suffixPos = imageFile.lastIndexOf('.');
      String suffix = imageFile.substring(suffixPos);
      String shortStepName = getName() + "Step" + num;
      String targetImageName = this.docDirName + "/doc-files/_" + shortStepName + suffix;

      // copy images to doc-files
      Path docFilesDir = Paths.get(docDirName + "/doc-files");

      try
      {
         if ( ! Files.exists(docFilesDir))
         {
            Files.createDirectories(docFilesDir);
         }

         Path srcFile = Paths.get(imageFile);
         FileTime srclastModifiedTime = Files.getLastModifiedTime(srcFile);
         Path targetFile = Paths.get(targetImageName);

         if ( ! Files.exists(targetFile) || Files.getLastModifiedTime(targetFile).compareTo(srclastModifiedTime) < 0)
         {
            Files.copy(srcFile, targetFile, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
         }

         String imgFileName = shortStepName + suffix;
         String widthClause = "";
         int width = 400;
         try
         {
            BufferedImage bimg = ImageIO.read(new File(targetImageName));
            width = bimg.getWidth();
         } catch (IOException e)
         {
            e.printStackTrace();
         }

         if (dims != null && dims.length > 0)
         {
            width = dims[0];
         }

         widthClause = "width='" + width + "'";

         String imgTag = CGUtil.replaceAll("<img src='doc-files/_imgFileName' widthClause>\n",
               "imgFileName", imgFileName,
               "widthClause", widthClause
               );
         this.addToSteps(imgTag);
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }

   }


   public void addAsImage(String htmlbody, boolean autoClose, int... dimensions)
   {
      addAsImage(htmlbody, autoClose, "graphviz-java", dimensions);
   }


   public void addAsImage(String htmlbody, boolean autoClose, String addAsImageMode, int... dimensions)
   {
      // create a doc-files directory relative to doc dir
      try
      {
         Files.createDirectories(Paths.get(this.docDirName + "/doc-files"));
      }
      catch (IOException e)
      {
         Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);
      }

      // generate html containing only this step
      String newHtml = getPageTemplate();
      int pos = newHtml.indexOf("$text");

      String dimString = "null";

      if (dimensions != null && dimensions.length >= 2)
      {
         dimString = "" + dimensions[0] + ", " + dimensions[1];
      }

      newHtml = newHtml.substring(0, pos)
              + "<!-- " + dimString + " -->\n"
              + htmlbody.toString()
              + newHtml.substring(pos + "$text".length());

      newHtml = newHtml.replaceAll("\r", "");

      // compare with old html,
      int num = 0;
      if (this.storyboardSteps != null)
      {
         num = this.storyboardSteps.size();
      }
      String shortStepName = getName() + "Step" + num;
      String fullStepHtmlName = this.docDirName + "/_" + shortStepName + ".html";
      Path htmlFile = Paths.get(fullStepHtmlName);
      boolean htmlHasChanged = true;
      if (Files.exists(htmlFile))
      {
         try
         {
            byte[] bytes = Files.readAllBytes(htmlFile);
            String oldHtml = new String(bytes);
            oldHtml = oldHtml.replaceAll("\r", "");
            boolean equal = oldHtml.equals(newHtml);
            htmlHasChanged = !equal;
         }
         catch (IOException e)
         {
            Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);
         }
      }

            // if new / changed
      // if (! htmlHasChanged) return;

      // generate image in doc-files
      if (addAsImageMode.equals("graphviz-java"))
      {
         generateImageInDocFilesWithGraphVizJava(autoClose, htmlbody, shortStepName, fullStepHtmlName, htmlFile, dimensions);
      }
      else
      {
         generateImageInDocFilesWithDiagramEditor(autoClose, newHtml, shortStepName, fullStepHtmlName, htmlFile, dimensions);
      }

      // insert link to image in this storyboard
      int width = dimensions[0];
      try
      {
         String imageFileName = this.docDirName + "/doc-files/" + shortStepName + ".png";
         BufferedImage bimg = ImageIO.read(new File(imageFileName));
         width = bimg.getWidth();
      } catch (IOException e)
      {
         e.printStackTrace();
      }
      this.add("<img src=\"doc-files/" + shortStepName + ".png\" alt=\"" + shortStepName + ".png\" width='" + width + "'>\n");
   }


   private void generateImageInDocFilesWithGraphVizJava(boolean autoClose, String jsonString, String shortStepName, String fullStepHtmlName, Path htmlFile, int[] dimensions)
   {
      if (jsonString.startsWith("{"))
      {
         this.generateImageInDocFilesWithGraphVizJavaFromJsonObject(autoClose, jsonString, shortStepName, fullStepHtmlName, htmlFile);
      }
      else
      {
         this.generateImageInDocFilesWithGraphVizJavaFromJsonArray(autoClose, jsonString, shortStepName, fullStepHtmlName, htmlFile, dimensions);
      }
   }


   private void generateImageInDocFilesWithGraphVizJavaFromJsonObject(boolean autoClose, String jsonString, String shortStepName, String fullStepHtmlName, Path htmlFile)
   {
      try
      {
         JsonObject jsonDiagram = new JsonObject().withValue(jsonString);
         JsonArray nodesJson = jsonDiagram.getJsonArray("nodes");
         JsonArray edgesJson = jsonDiagram.getJsonArray("edges");

         LinkedHashMap<String, Node> nodeMap = new LinkedHashMap<String, Node>();

         StringBuilder dotString = new StringBuilder();
         dotString.append("" +
                 "digraph H {\n" +
                 "nodes \n" +
                 "edges \n" +
                 "}\n");


         String nodesString = makeClassDiagNodes(nodesJson);
         String edgesString = makeClassDiagEdges(edgesJson);

         CGUtil.replaceAll(dotString,
                 "nodes", nodesString,
                 "edges", edgesString
         );

         String imageFileName = this.docDirName + "/doc-files/" + shortStepName + ".png";
         // System.out.println(dotString.toString());
         Graphviz.fromString(dotString.toString()).render(Format.PNG).toFile(new File(imageFileName));
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
   }


   private void generateImageInDocFilesWithGraphVizJavaFromJsonArray(boolean autoClose, String jsonArrayString, String shortStepName, String fullStepHtmlName, Path htmlFile, int[] dimensions)
   {
      try
      {
         JsonArray jsonArray = new JsonArray().withValue(jsonArrayString);

         LinkedHashMap<String, Node> nodeMap = new LinkedHashMap<String, Node>();

         StringBuilder dotString = new StringBuilder();
         dotString.append("" +
                 "digraph H {\n" +
                 "nodes \n" +
                 "edges \n" +
                 "}\n");


         String nodesString = makeNodes(jsonArray);
         String edgesString = makeEdges(jsonArray);

         CGUtil.replaceAll(dotString,
                 "nodes", nodesString,
                 "edges", edgesString);
         String imageFileName = this.docDirName + "/doc-files/" + shortStepName + ".png";
         // System.out.println(dotString.toString());
         Graphviz.fromString(dotString.toString()).render(Format.PNG).toFile(new File(imageFileName));
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
   }

   private String makeClassDiagEdges(JsonArray jsonArray)
   {
      StringBuilder buf = new StringBuilder();

      for (Object o : jsonArray)
      {
         JsonObject jsonObj = (JsonObject) o;

         JsonObject sourceJson = jsonObj.getJsonObject("source");
         JsonObject targetJson = jsonObj.getJsonObject("target");

         String sourceId = CGUtil.shortClassName(sourceJson.getString("id"));
         String targetId = CGUtil.shortClassName(targetJson.getString("id"));

         String sourceLabel = sourceJson.getString("property");
         if ("many".equals(sourceJson.getString("cardinality")))
         {
            sourceLabel += " *";
         }

         String targetLabel = targetJson.getString("property");
         if ("many".equals(targetJson.getString("cardinality")))
         {
            targetLabel += " *";
         }

         buf.append(sourceId).append(" -> ").append(targetId)
                 .append(" [arrowhead=none fontsize=\"10\" " +
                         "taillabel=\"" + sourceLabel + "\" " +
                         "headlabel=\"" + targetLabel + "\"];\n");
      }

      return buf.toString();
   }


   private String makeEdges(JsonArray jsonArray)
   {
      StringBuilder buf = new StringBuilder();

      for (Object o : jsonArray)
      {
         JsonObject jsonObj = (JsonObject) o;

         String objId = (String) jsonObj.get("id");
         objId = StrUtil.downFirstChar(objId);
         String shortClassName = CGUtil.shortClassName(jsonObj.getString("class"));

         JsonObject props = jsonObj.getJsonObject("prop");

         for (String key : props.keySet())
         {
            Object value = props.getValue(key);

            if (value instanceof JsonArray)
            {
               ArrayList<LinkTarget> elemList = new ArrayList<LinkTarget>();

               for (Object elem : (JsonArray) value)
               {
                  JsonObject jsonElem = (JsonObject) elem;
                  String elemId = jsonElem.getString("id");
                  elemId = StrUtil.downFirstChar(elemId);

                  buf.append(objId).append(" -> ").append(elemId).append(" [arrowhead=none fontsize=\"10\" headlabel=\"" +
                          key + "\"];\n");
               }
            }
            else if (value instanceof JsonObject)
            {
               JsonObject jsonElem = (JsonObject) value;
               String elemId = jsonElem.getString("id");
               elemId = StrUtil.downFirstChar(elemId);

               buf.append(objId).append(" -> ").append(elemId).append(" [arrowhead=none fontsize=\"10\" headlabel=\"" +
                       key + "\"];\n");
            }
         }
      }

      return buf.toString();
   }


   private String makeClassDiagNodes(JsonArray jsonArray)
   {
      StringBuilder buf = new StringBuilder();

      for (Object o : jsonArray)
      {
         JsonObject jsonObj = (JsonObject) o;

         String objId = (String) jsonObj.get("id");
         String shortClassName = CGUtil.shortClassName(objId);

         String iconName = iconMap.get(jsonObj.getString("id"));

         String imageLink = "";
         if (iconName != null)
         {
            imageLink = "   image=\"doc-files/karli.png\"\n";
         }

         buf.append(shortClassName).append(" " +
                 "[\n" +
                 "   shape=plaintext\n" +
                 "   fontsize=\"10\"\n" +
                 imageLink +
                 "   label=<\n"  +
                 "     <table border='0' cellborder='1' cellspacing='0'>\n" +
                 "       <tr><td><u>")
                 .append(shortClassName)
                 .append("</u></td></tr>\n"  +
                         "       <tr><td>");

         JsonArray attrs = jsonObj.getJsonArray("attributes");

         for (Object attr : attrs)
         {
            String txt = (String) attr;

            buf.append(txt).append("<br  align='left'/>");
         }

         buf.append("</td></tr>\n" +
                 "     </table>\n" +
                 "  >];\n");
      }

      return buf.toString();
   }

   private String makeNodes(JsonArray jsonArray)
   {
      StringBuilder buf = new StringBuilder();

      for (Object o : jsonArray)
      {
         JsonObject jsonObj = (JsonObject) o;

         String objId = (String) jsonObj.get("id");
         objId = StrUtil.downFirstChar(objId);
         String shortClassName = CGUtil.shortClassName(jsonObj.getString("class"));

         String iconName = iconMap.get(jsonObj.getString("id"));

         String imageLink = "";
         if (iconName != null)
         {
            imageLink = "   image=\"doc-files/karli.png\"\n";
         }

         buf.append(objId).append(" " +
                 "[\n" +
                 "   shape=plaintext\n" +
                 "   fontsize=\"10\"\n" +
                 imageLink +
                 "   label=<\n"  +
                 "     <table border='0' cellborder='1' cellspacing='0'>\n" +
                 "       <tr><td><u>")
                 .append(objId).append(": ").append(shortClassName)
                 .append("</u></td></tr>\n"  +
                         "       <tr><td>");

         JsonObject props = jsonObj.getJsonObject("prop");

         for (String key : props.keySet())
         {
            Object value = props.getValue(key);

            if (value instanceof JsonArray || value instanceof JsonObject)
            {

            }
            else
            {
               buf.append(key).append(": ").append("" + value).append("<br  align='left'/>");
            }
         }

         buf.append("</td></tr>\n" +
                 "     </table>\n" +
                 "  >];\n");
      }

      return buf.toString();
   }


   private void generateImageInDocFilesWithDiagramEditor(boolean autoClose, String newHtml, String shortStepName, String fullStepHtmlName, Path htmlFile, int[] dimensions)
   {
      try
      {
         Files.write(htmlFile, newHtml.getBytes());
      }
      catch (IOException e)
      {
         Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);
      }

      File file = new File(fullStepHtmlName);
      // String urlString = file.toURI().toURL().toString();
//      DiagramEditor.convertToPNG(file, this.docDirName + "/doc-files/" + shortStepName + ".png", autoClose);
      DiagramEditor.converting(file, this.docDirName + "/doc-files/" + shortStepName + ".png", false, autoClose, dimensions);
      try
      {
         Thread.sleep(4000);
      }
      catch (InterruptedException e)
      {
         e.printStackTrace();
      }
   }



   public void dumpHTML(String targetClassName, String targetMethodName)
   {
      try
      {
         parseTestMethod();
         generateJavaDoc();
      }
      catch (java.lang.StringIndexOutOfBoundsException e)
      {
         // parser throws IOOB if java source not available at runtime
      }

      dumpIndexHtml();

      // generate the html text
      String htmlText = getPageTemplate();

      String storyboardName = this.getName();

      String storyNameLine = "<h3>Storyboard storyboardName</h3>\n";

      storyNameLine = storyNameLine.replaceFirst("storyboardName", storyboardName);
      storyNameLine = storyNameLine.replaceFirst("testfilename", javaTestFileName);

      String shortFileName = "" + storyboardName + ".html";
      String pathname = docDirName + "/" + shortFileName;

      StringBuffer text = new StringBuffer(storyNameLine);

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

      int pos = javaTestFileName.lastIndexOf("../");
      if (pos >= 0)
      {
         javaTestFileName = javaTestFileName.substring(pos + 3);
      }

      addStoryToJavaDoc(javaTestFileName, Parser.METHOD + ":" + testMethodName, text.toString());

      if (targetClassName != null && targetMethodName == null)
      {
         String targetShortName = CGUtil.shortClassName(targetClassName);
         String targetFileName = findJavaFile(targetShortName, symTab);
         addStoryToJavaDoc(targetFileName, Parser.CONSTRUCTOR + ":" + targetShortName, text.toString());
         addStoryToJavaDoc(targetFileName, Parser.CLASS + ":" + targetShortName, text.toString());
      }
      else if (targetClassName != null && targetMethodName != null)
      {
         // javadoc for method
         String targetShortName = CGUtil.shortClassName(targetClassName);
         String targetFileName = findJavaFile(targetShortName, symTab);
         addStoryToJavaDoc(targetFileName, Parser.METHOD + ":" + targetMethodName, text.toString());
      }

      pos = htmlText.indexOf("$text");

      htmlText = htmlText.substring(0, pos)
              + text.toString()
              + htmlText.substring(pos + "$text".length());

      writeToFile(shortFileName, htmlText);

      String entry = refForFile(storyboardName);
      addEntryToRefsHtml(docDirName, entry);

      // coverage4GeneratedModelCode(largestRoot);
   }

   private String getPageTemplate()
   {
      return "<!DOCTYPE html>\n"
              + "<html>\n" +
              "    <head>\n" +
              "    <meta charset=\"utf-8\">\n" +
              "    <link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">\n" +
              "    <link href=\"includes/diagramstyle.css\" rel=\"stylesheet\" type=\"text/css\">\n" +
              "\n" +
              "    <link rel=\"stylesheet\" href=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\n" +
              "    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.2.1/Chart.bundle.js\"></script>\n" +
              "    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js\"></script>\n" +
              "    <script src=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>" +
              "\n" +
              "    <script src=\"includes/dagre.min.js\"></script>\n" +
              "    <script src=\"includes/drawer.js\"></script>\n" +
              "    <script src=\"includes/graph.js\"></script>\n" +
              "    <script src=\"includes/Chart.bundle.js\"></script>\n" +
              "    <script src=\"highlight.pack.js\"></script>\n" +
              "    <script src=\"highlightjs-line-numbers.min.js\"></script>\n" +
              "    <script language=\"Javascript\">hljs.initHighlightingOnLoad();\n" +
              "                                  hljs.initLineNumbersOnLoad();</script>" +
              "    <style>\n" +
              "        canvas{\n" +
              "            -moz-user-select: none;\n" +
              "            -webkit-user-select: none;\n" +
              "            -ms-user-select: none;\n" +
              "        }\n" +
              "    </style>\n" +
              "    </head>\n" +
              "<body>\n" +
              "$text\n" +
              "</body>\n" +
              "</html>\n";
   }



   private void addEntryToRefsHtml(String dirName, String entry)
   {
      int pos;
      // add entry to refs.html
      try
      {
         byte[] readAllBytes = Files.readAllBytes(Paths.get(dirName + "/refs.html"));
         String refsText = new String(readAllBytes);

         pos = refsText.indexOf(entry.trim());

         if (pos < 0)
         {
            String newText = CGUtil.replaceAll(refsText, "</body>", entry + "</body>");

            writeToDirFile(dirName, "refs.html", newText);
         }
      }
      catch (IOException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   private String docDirName = "doc";


   public StoryboardImpl withDocDirName(String name)
   {
      this.docDirName = name;

      // the doc dir may be a subdir, generate enough ../ elements to reach the
      // root dir for the javafileName
      int pos = -1;
      do
      {
         pos = docDirName.indexOf('/', pos + 1);
         if (pos >= 0)
         {
            javaTestFileName = "../" + javaTestFileName;
         }
      }
      while (pos >= 0);

      return this;
   }


   private void dumpIndexHtml()
   {
      new File(docDirName).mkdirs();

      new DocEnvironment().copyJS(docDirName);

      // ensure style file
      File styleFile = new File(docDirName + "/style.css");

      if (!styleFile.exists())
      {
         String text = "" +
                 "BODY {color:#000000;background-color:#ffffff;font-family:Arial,Helvetica,Geneva,Sans-Serif}\n" +
                 "B {font-weight:bold;}\n" +
                 "\n" +
                 "H1 {font-family:Arial,Helvetica,Geneva,Sans-Serif;text-align:left;}\n" +
                 "H2 {color:#000000;font-family:Arial,Helvetica,Geneva,Sans-Serif;text-align:left;}\n" +
                 "H3 {color:#000000;font-family:Arial,Helvetica,Geneva,Sans-Serif;text-align:left;}\n" +
                 "\n" +
                 "P {font-family:Arial,Helvetica,Geneva,Sans-Serif;text-align:left;}\n" +
                 "PRE {font-family:Courier;text-align:left;font-size:12pt}\n" +
                 "\n" +
                 "TD {font-family:Arial,Helvetica,Geneva,Sans-Serif;}\n" +
                 "TH {font-family:Arial,Helvetica,Geneva,Sans-Serif;}\n" +
                 "\n" +
                 "DD {font-family:Arial,Helvetica,Geneva,Sans-Serif;}\n" +
                 "";

         writeToFile("style.css", text);
      }

      // ensure index.html
      File file = new File(docDirName + "/index.html");

      if (!file.exists())
      {
         String text = "<html>\n" +
                 "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=9\">\n" +
                 "<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">" +
                 "<frameset cols='250,*'>\n" +
                 "<frame src='refs.html' name='Index'>\n" +
                 "<frame name='Main'>a</frame>\n" +
                 "<noframes>\n" +
                 "  <body>\n" +
                 "        <p><a href='refs.html'>Index</a> <a href='refs.html'>Main</a></p>\n" +
                 "  </body>\n" +
                 "</noframes>\n" +
                 "</frameset>\n" +
                 "</html>\n";

         writeToFile("index.html", text);
      }

      // ensure refs.html
      file = new File(docDirName + "/refs.html");

      if (!file.exists())
      {
         // build index
         String refHtml = "<html>\n" +
                 "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=9\">\n" +
                 "<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">\n" +
                 "<body>\n" +
                 "</body>\n" +
                 "</html>\n";

         if (!docDirName.equals("doc"))
         {
            // add parent link
            String parentLink = "" +
                    "<body>\n" +
                    "<a href=\"../index.html\" target=\"_top\"> back to parent </a><br>\n" +
                    "<br>\n";

            refHtml = CGUtil.replaceAll(refHtml, "<body>\n", parentLink);

            // generate child link in ../refs.html
            String childLink = "" +
                    "<a href=\"subdir/index.html\" target=\"_top\">subdir</a><br>\n";

            int pos = docDirName.lastIndexOf('/');
            String childName = docDirName.substring(pos + 1, docDirName.length());
            childLink = CGUtil.replaceAll(childLink, "subdir", childName);

            addEntryToRefsHtml(docDirName + "/..", childLink);
         }

         writeToFile("refs.html", refHtml);
      }

   }


   public String refForFile(String filename)
   {
      String ref = "<a href=\"filename.html\" target=\"Main\">filename</a><br>\n ";
      ref = ref.replaceAll("filename", filename);
      return ref;
   }


   public void assertEquals(String message, double expected, double actual, double delta)
   {
      this.add("Check: " + message + " " + expected + " +-" + delta + " actual " + actual);

      if (Math.abs(expected - actual) > delta)
      {
         this.dumpHTML();
      }
      Assert.assertEquals("FAILED: " + message, expected, actual, delta);
   }


   public void assertEquals(String message, long expected, long actual, long delta)
   {
      this.add("Check: " + message + " " + expected + " +-" + delta + " actual " + actual);

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


   public void assertEquals(String message, long expected, long actual)
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



   public boolean addPropertyChangeListener(PropertyChangeListener listener)
   {
      getPropertyChangeSupport().addPropertyChangeListener(listener);
      return true;
   }



   public boolean addPropertyChangeListener(String propertyName, PropertyChangeListener listener)
   {
      getPropertyChangeSupport().addPropertyChangeListener(propertyName, listener);
      return true;
   }


   public boolean removePropertyChangeListener(PropertyChangeListener listener)
   {
      if (listeners != null)
      {
         listeners.removePropertyChangeListener(listener);
      }
      return true;
   }


   public boolean removePropertyChangeListener(String property,
                                               PropertyChangeListener listener)
   {
      if (listeners != null)
      {
         listeners.removePropertyChangeListener(property, listener);
      }
      return true;
   }
   // ==========================================================================


   public void removeYou()
   {
      // setWall(null);
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
    *
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


   public StoryboardImpl withStoryboardSteps(StoryboardStep... value)
   {
      for (StoryboardStep item : value)
      {
         addToStoryboardSteps(item);
      }
      return this;
   }


   public StoryboardImpl withoutStoryboardSteps(StoryboardStep... value)
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
    *
    * @return The StoryboardWall
    */
   // public StoryboardWall getWall()
   // {
   // return this.wall;
   // }

   // public boolean setWall(StoryboardWall value)
   // {
   // boolean changed = false;
   //
   // if (this.wall != value)
   // {
   // StoryboardWall oldValue = this.wall;
   //
   // if (this.wall != null)
   // {
   // this.wall = null;
   // oldValue.setStoryboard(null);
   // }
   //
   // this.wall = value;
   //
   // if (value != null)
   // {
   // value.withStoryboard(this);
   // }
   //
   // getPropertyChangeSupport().firePropertyChange(PROPERTY_WALL, oldValue,
   // value);
   // changed = true;
   // }
   //
   // return changed;
   // }

   // public Storyboard withWall(StoryboardWall value)
   // {
   // setWall(value);
   // return this;
   // }

   // public StoryboardWall createWall()
   // {
   // StoryboardWall value = new StoryboardWall();
   // withWall(value);
   // return value;
   // }

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


   public StoryboardImpl withRootDir(String value)
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


   public StoryboardImpl withModelRootDir(String value)
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


   public StoryboardImpl withStepCounter(int value)
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


   public StoryboardImpl withStepDoneCounter(int value)
   {
      setStepDoneCounter(value);
      return this;
   }



   public void addPreformatted(String expandedText)
   {
      expandedText = StrUtil.htmlEncode(expandedText);

      this.add("<pre>" + expandedText + "</pre>\n");

   }



   public void addPattern(PatternObject patternObject, boolean showMatch)
   {
      addPattern(patternObject.getPattern(), showMatch);
   }



   public void addPattern(Pattern pattern, boolean showMatch)
   {
      String diagName = "" + this.getName() + "PatternDiagram" + this.getStoryboardSteps().size();

      String link = pattern.dumpDiagram(diagName, showMatch);
      this.add(link);
   }


   public void dumpDiagram(PatternObject<?, ?> po, String name)
   {
      add(po.getPattern().dumpDiagram(name));
   }

   public static class RestrictToFilter implements ObjectCondition
   {
      private LinkedHashSet<Object> explicitElems;


      public RestrictToFilter(LinkedHashSet<Object> explicitElems)
      {
         this.explicitElems = explicitElems;

      }


      public boolean update(Object values)
      {
         PropertyChangeEvent evt = (PropertyChangeEvent) values;
         Object newValue = evt.getNewValue();
         if (newValue != null
                 && ("Integer Float Double Long Boolean String"
                 .indexOf(newValue.getClass().getSimpleName()) >= 0))
         {
            return true;
         }

         if (newValue != null && newValue instanceof Collection)
         {
            boolean allContained = true;
            for (Object elem : (Collection) newValue)
            {
               if ( ! explicitElems.contains(elem))
               {
                  return false;
               }
            }
            return true;
         }

         boolean contains = explicitElems.contains(newValue);
         return contains;
      }
   }


   private void generateJavaDoc()
   {


      SymTabEntry symTabEntry = parser.getSymTabEntry(Parser.METHOD + ":" + methodName + "()");

      if (symTabEntry == null)
         return; // <========== sudden death

      parser.parseMethodBody(symTabEntry);

      StatementEntry parentStatement = parser.getCurrentStatement().getParent();

      // loop through top level statements and look for interesting method calls
      for (StatementEntry statement : parentStatement.getBodyStatsTransitive())
      {
         // is it interesting?
         if (statement.toString().startsWith(" new Storyboard"))
         {
            continue;
         }

         if (statement.toString().startsWith(" new") && statement.getTokenList().size() >= 2)
         {
            // yes, an object is created try to refer to it
            String classUnderTestName = statement.getTokenList().get(1);
            String fullClassUnderTestName = findJavaFile(classUnderTestName, symTab);
            if (fullClassUnderTestName != null)
            {
               // add reference to javadoc
               addReferenceToJavaDoc(fullClassUnderTestName, Parser.CONSTRUCTOR + ":" + classUnderTestName,
                       className, methodName);
               addReferenceToJavaDoc(fullClassUnderTestName, Parser.CLASS + ":" + classUnderTestName, className, methodName);
            }
         }
         else
         {
            // try to find simple method calls
            ArrayList<String> tokenList = statement.getTokenList();

            if (tokenList.size() < 3)
               continue; // <==== sudden death

            String callString = tokenList.get(0);
            String[] split = callString.split("\\.");

            if (split.length < 2
                    || !tokenList.get(1).equals("("))
               continue; // <==== sudden death

            String varName = split[0];
            String methodUnderTestName = split[1];

            LocalVarTableEntry localVarTableEntry = parser.getLocalVarTable().get(varName);

            if (localVarTableEntry == null)
               return; // <=========== sudden death

            String classUnderTestName = localVarTableEntry.getType();

            if (classUnderTestName == null || classUnderTestName.startsWith("Storyboard"))
            {
               continue;
            }

            String fullClassUnderTestName = findJavaFile(classUnderTestName, symTab);

            if (fullClassUnderTestName == null)
               continue; // <==== sudden death

            addReferenceToJavaDoc(fullClassUnderTestName, Parser.METHOD + ":" + methodUnderTestName,
                    className, methodName);
         }
      }
   }

   private void parseTestMethod()
   {
      // search the (test) method that creates this story for methods it calls /
      // tests.

      // create a javadoc on such methods that refers to the test method.
      callEntry = getMyStackTraceElement();


      methodName = callEntry.getMethodName();
      className = callEntry.getClassName();
      classFileName = className.replaceAll("\\.", "/") + ".java";
      fullFileName = this.rootDir + "/" + classFileName;

      // parse the test method body
      parser = new Parser().withFileName(fullFileName);
      File javaFile = new File(fullFileName);

      parser.withFileBody(CGUtil.readFile(javaFile));

      parser.parse();

      symTab = parser.getSymTab();
   }

   private StackTraceElement getMyStackTraceElement()
   {
      // where am I called?
      Exception e = new RuntimeException();
      StackTraceElement[] stackTrace = e.getStackTrace();
      StackTraceElement callEntry = stackTrace[1];

      // find first method outside Storyboard and StoryPage
      int i = 1;

      while (true)
      {
         callEntry = stackTrace[i];

         if (callEntry.getClassName().equals(StoryboardImpl.class.getName())
                 || callEntry.getClassName().equals(Storyboard.class.getName())
                 || callEntry.getClassName().equals(StoryPage.class.getName()))
         {
            i++;
            continue;
         }
         else
         {
            break;
         }
      }
      return callEntry;
   }



   public void addStoryToJavaDoc(String classUnderTestName, String methodUnderTestName, String storyText)
   {
      try
      {
         // parse the class under test
         Parser parser = new Parser().withFileName(classUnderTestName);

         File javaFile = new File(classUnderTestName);

         if (!javaFile.exists())
            return; // <=================== sudden death

         parser.withFileBody(CGUtil.readFile(javaFile));

         parser.parse();

         ArrayList<SymTabEntry> symTabEntries = parser.getSymTabEntriesFor(methodUnderTestName);

         for (int k = symTabEntries.size() - 1; k >= 0; k--)
         {
            SymTabEntry symTabEntry = symTabEntries.get(k);

            int javaDocStartPos = symTabEntry.getPreCommentStartPos();
            int javaDocEndPos = symTabEntry.getPreCommentEndPos();
            String javaDocText = null;
            if (javaDocStartPos == 0)
            {
               // no javadoc yet
               javaDocStartPos = javaDocEndPos = symTabEntry.getAnnotationsStartPos() - 1;
               javaDocText = " /**\n"
                       + "    * \n"
                       + "    */\n"
                       + "   ";
            }
            else
            {
               javaDocText = parser.getFileBody().substring(javaDocStartPos, javaDocEndPos + 1);
            }

            StringBuilder buf = new StringBuilder();
            String[] split = storyText.split("\n");
            for (String line : split)
            {
               buf.append("    * ").append(line).append("\n");
            }

            buf.replace(0, 4, "");
            buf.append("    ");

            String hrefText = buf.toString();

            if (javaDocText.indexOf(hrefText) < 0)
            {
               // remove old stuff
               int oldStartPos = javaDocText.indexOf("* <");
               int oldEndPos = javaDocText.indexOf("* @");

               if (oldEndPos < 0)
               {
                  oldEndPos = javaDocText.indexOf("*/");
               }

               if (oldStartPos < 0 || oldStartPos > oldEndPos)
               {
                  oldStartPos = oldEndPos;
               }

               // add story

               if (oldEndPos < 0)
                  continue; // <================ sudden death

               javaDocText = javaDocText.substring(0, oldStartPos)
                       + hrefText + javaDocText.substring(oldEndPos);

               // write new javadoc
               parser.getFileBody().replace(javaDocStartPos, javaDocEndPos + 1, javaDocText);
               parser.withFileChanged(true);
               CGUtil.printFile(parser);
            }

            // copy images to doc-files
            Path docFilesDir = Paths.get(docDirName + "/doc-files");
            Path targetDir = Paths.get(classUnderTestName).getParent();
            Path targetDocFilesDir = Paths.get(targetDir.toString() + "/doc-files");

            if ( ! Files.exists(targetDocFilesDir))
            {
               Files.createDirectories(targetDocFilesDir);
            }

            for(String fileName : new File(docDirName + "/doc-files").list())
            {
               Path srcFile = docFilesDir.resolve(fileName);
               FileTime srclastModifiedTime = Files.getLastModifiedTime(srcFile);
               Path targetFile = targetDocFilesDir.resolve(fileName);

               if ( ! Files.exists(targetFile) || Files.getLastModifiedTime(targetFile).compareTo(srclastModifiedTime) < 0)
               {
                  Files.copy(srcFile, targetFile, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
               }
            }
         }
      }
      catch (Exception e)
      {
         // This may fail if the java source is not available, e.g. if run from
         // a jar file
         // No problem, just ignore it.
         // e.printStackTrace();
      }
   }





   public void addReferenceToJavaDoc(String classUnderTestName, String methodUnderTestName, String testClass, String testMethod)
   {
      try
      {
         if (classUnderTestName.startsWith("././"))
         {
            classUnderTestName = classUnderTestName.substring(2);
         }

         if ( ! classUnderTestName.startsWith(this.rootDir))
         {
            // target lives e.g. in src/main/java and we run in src/test/java. Thus target will not be able to refer to us.
            // do not add reference.
            return;
         }

         // parse the class under test
         Parser parser = new Parser().withFileName(classUnderTestName);

         File javaFile = new File(classUnderTestName);

         if (!javaFile.exists())
            return; // <=================== sudden death

         parser.withFileBody(CGUtil.readFile(javaFile));

         parser.parse();

         ArrayList<SymTabEntry> symTabEntries = parser.getSymTabEntriesFor(methodUnderTestName);

         for (int k = symTabEntries.size() - 1; k >= 0; k--)
         {
            SymTabEntry symTabEntry = symTabEntries.get(k);

            int javaDocStartPos = symTabEntry.getPreCommentStartPos();
            int javaDocEndPos = symTabEntry.getPreCommentEndPos();
            String javaDocText = null;
            if (javaDocStartPos == 0)
            {
               // no javadoc yet
               javaDocStartPos = javaDocEndPos = symTabEntry.getAnnotationsStartPos() - 1;
               javaDocText = "   /**\n"
                       + "    * \n"
                       + "    */\n"
                       + "   ";
            }
            else
            {
               javaDocText = parser.getFileBody().substring(javaDocStartPos, javaDocEndPos + 1);
            }

            String hrefText = "* @see " + testClass + "#" + testMethod;

            if (javaDocText.indexOf(hrefText) < 0)
            {
               // add reference

               int insertPos = javaDocText.indexOf("*/");

               if (insertPos < 0)
                  continue; // <================ sudden death

               javaDocText = javaDocText.substring(0, insertPos)
                       + hrefText + "\n " + javaDocText.substring(insertPos);

               // write new javadoc
               parser.getFileBody().replace(javaDocStartPos, javaDocEndPos + 1, javaDocText);
               parser.withFileChanged(true);
               CGUtil.printFile(parser);
            }
         }
      }
      catch (Exception e)
      {
         // This may fail if the java source is not available, e.g. if run from
         // a jar file
         // No problem, just ignore it.
         // e.printStackTrace();
      }
   }



   public void addReferenceToJavaDoc(String classUnderTestName, String methodUnderTestName, String testFileName)
   {
      try
      {
         if (classUnderTestName.startsWith("././"))
         {
            classUnderTestName = classUnderTestName.substring(2);
         }

         if ( ! classUnderTestName.startsWith(this.rootDir))
         {
            // target lives e.g. in src/main/java and we run in src/test/java. Thus target will not be able to refer to us.
            // do not add reference.
            return;
         }

         // parse the class under test
         Parser parser = new Parser().withFileName(classUnderTestName);

         File javaFile = new File(classUnderTestName);

         if (!javaFile.exists())
            return; // <=================== sudden death

         parser.withFileBody(CGUtil.readFile(javaFile));

         parser.parse();

         ArrayList<SymTabEntry> symTabEntries = parser.getSymTabEntriesFor(methodUnderTestName);

         for (int k = symTabEntries.size() - 1; k >= 0; k--)
         {
            SymTabEntry symTabEntry = symTabEntries.get(k);

            int javaDocStartPos = symTabEntry.getPreCommentStartPos();
            int javaDocEndPos = symTabEntry.getPreCommentEndPos();
            String javaDocText = null;
            if (javaDocStartPos == 0)
            {
               // no javadoc yet
               javaDocStartPos = javaDocEndPos = symTabEntry.getAnnotationsStartPos() - 1;
               javaDocText = "   /**\n"
                       + "    * \n"
                       + "    */\n"
                       + "   ";
            }
            else
            {
               javaDocText = parser.getFileBody().substring(javaDocStartPos, javaDocEndPos + 1);
            }

            // compute reference
            while (testFileName.startsWith("./"))
            {
               testFileName = testFileName.substring(2);
            }

            while (classUnderTestName.startsWith("./"))
            {
               classUnderTestName = classUnderTestName.substring(2);
            }

            String[] split = classUnderTestName.split("/");

            String href = "";
            for (int i = 0; i < split.length - 1; i++)
            {
               href += "../";
            }

            href += testFileName;

            String[] testFileSplit = testFileName.split("/");

            String hrefText = "* @see <a href='" + href + "'>" + testFileSplit[testFileSplit.length - 1] + "</a>";

            if (javaDocText.indexOf(hrefText) < 0)
            {
               // add reference

               int insertPos = javaDocText.indexOf("*/");

               if (insertPos < 0)
                  continue; // <================ sudden death

               javaDocText = javaDocText.substring(0, insertPos)
                       + hrefText + "\n " + javaDocText.substring(insertPos);

               // write new javadoc
               parser.getFileBody().replace(javaDocStartPos, javaDocEndPos + 1, javaDocText);
               parser.withFileChanged(true);
               CGUtil.printFile(parser);

            }
         }
      }
      catch (Exception e)
      {
         // This may fail if the java source is not available, e.g. if run from
         // a jar file
         // No problem, just ignore it.
         // e.printStackTrace();
      }
   }

   private LinkedHashMap<String, String> javaFileTable = null;

   private String testMethodName;


   private String findJavaFile(String classUnderTestName, SimpleKeyValueList<String, SymTabEntry> symTab)
   {
      if (javaFileTable == null)
      {
         LinkedHashSet<String> importedPackages = new LinkedHashSet<String>();
         // create list of imports
         for (String key : symTab.keySet())
         {
            if (key.startsWith("import:"))
            {
               String fullImportName = key.substring("import:".length());

               fullImportName = CGUtil.packageName(fullImportName);

               importedPackages.add(fullImportName);
            }
            else if (key.startsWith("package:"))
            {
               String fullImportName = key.substring("package:".length());

               importedPackages.add(fullImportName);
            }
         }

         // add package of test class

         javaFileTable = new LinkedHashMap<String, String>();

         searchJavaFiles(".", this.getRootDir(), "", importedPackages);

         File srcDir = new File("./src");

         if (srcDir.isDirectory())
         {
            for (File kid : srcDir.listFiles())
            {  // e.g ./src/main
               if (kid.isDirectory())
               {
                  for (File grandKid : kid.listFiles())
                  { // e.g ./src/main/java
                     if (grandKid.isDirectory() && grandKid.getName().equals("java"))
                     {
                        searchJavaFiles(".", "./src/" + kid.getName() + "/" + grandKid.getName(), "", importedPackages);
                     }
                  }
               }
            }
         }

      }

      return javaFileTable.get(classUnderTestName + ".java");
   }


   private void searchJavaFiles(String rootDir2, String fileName, String packageName,
                                LinkedHashSet<String> importedPackages)
   {
      File dir = new File(rootDir2 + "/" + fileName);

      if (fileName.endsWith(".java"))
      {
         // does it fit to imports?
         String filePackageName = packageName.substring(1, packageName.length() - fileName.length() - 1);

         if (importedPackages.contains(filePackageName))
         {
            javaFileTable.put(fileName, rootDir2 + "/" + fileName);
         }
      }
      else if (dir.isDirectory())
      {
         for (String kidName : dir.list())
         {
            searchJavaFiles(rootDir2 + "/" + fileName, kidName, packageName + "." + kidName, importedPackages);
         }
      }

   }

}
