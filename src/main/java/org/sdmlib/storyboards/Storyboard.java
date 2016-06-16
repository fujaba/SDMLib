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
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

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
import org.sdmlib.serialization.PropertyChangeInterface;
import org.sdmlib.storyboards.util.StoryboardStepSet;

import de.uniks.networkparser.Filter;
import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.interfaces.SendableEntity;
import de.uniks.networkparser.interfaces.SendableEntityCreator;
import de.uniks.networkparser.interfaces.UpdateListener;
import de.uniks.networkparser.json.JsonArray;
import de.uniks.networkparser.list.SimpleKeyValueList;

/**
 * A Storyboard collects entries for the generation of an html page from e.g. a
 * JUnit test. This html page will be named like the story, i.e. like the method
 * that created the Storyboard. It will be added to the refs.html and thus
 * become part of the index.html. All these html files are stored in an
 * directory "doc" located in the project root directory.
 * 
 * @see #dumpHTML()
 * @see <a href="../../../../../../doc/index.html">SDMLib Storyboards</a>
 * @see <a href=
 *      '../../../../../../src/test/java/org/sdmlib/test/kanban/ProjectBoard.java'>
 *      ProjectBoard.java</a>
 */
public class Storyboard implements PropertyChangeInterface, SendableEntity
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

   public String getJavaTestFileName()
   {
      return javaTestFileName;
   }

   public void setJavaTestFileName(String javaTestFileName)
   {
      this.javaTestFileName = javaTestFileName;
   }

   public Storyboard withJsonIdMap(IdMap jsonIdMap)
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

            if (callEntry.getClassName().equals(Storyboard.class.getName())
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

      String testClassName = null;
      if (callEntry.getClassName().equals(StoryPage.class.getName()))
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

      if ("main".equals(storyName))
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

   private void addToSteps(String text)
   {
      StoryboardStep storyStep = new StoryboardStep().withText(text);
      this.addToStoryboardSteps(storyStep);
   }

   // private String rootDir = null;

   /**
    * @deprecated Storyboards search for their root dir (like src or
    *             src/test/java) themself. Thus use the version without
    *             parameters.
    * @param rootDir
    *           The RootDir of Sources
    */
   @Deprecated
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

   /**
    * @deprecated Storyboards search for their root dir (like src or
    *             src/test/java) themself. Similarly, Storyboards get their name
    *             from the method they are used in. Name that method
    *             appropriately. Use the version without parameters.
    * 
    * @param rootDir
    *           The RootDir of Sources
    * @param name
    *           Name of the html file and page title to be generated.
    */
   @Deprecated
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

   public Storyboard withMap(IdMap map)
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
               hasPOMethod = setClass.getMethod("filter" + CGUtil.shortClassName(className) + "PO");
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
                  creatorClass.setValue(object, attrName, value, IdMap.REMOVE);

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

   /**
    * Add a class diagram to the generated html page.
    * 
    * @param model
    *           The ClassModel for drawing
    */
   public void addClassDiagram(ClassModel model)
   {
      String diagName = this.getName() + "ClassDiagram" + this.getStoryboardSteps().size();
      diagName = model.dumpClassDiagram(diagName);
      this.add(diagName);
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

      // do we have a JsonIdMap?
      if (jsonIdMap == null)
      {
         // jsonIdMap = (IdMap) new GenericIdMap().withSessionId(null);
         jsonIdMap = (IdMap) new SDMLibIdMap("s").withSessionId(null);
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
         IdMap localIdMap = new SDMLibIdMap("s2").withSessionId(null);
         addObjectDiagram(localIdMap, explicitElems, jsonFilter);
      }
      else
      {
         AlwaysTrueCondition conditionMap = new AlwaysTrueCondition();
         IdMap localIdMap = new SDMLibIdMap("s2").withSessionId(null);
         addObjectDiagram(localIdMap, explicitElems, conditionMap);
      }
   }

   private class AlwaysTrueCondition implements UpdateListener
   {
      public boolean update(Object value)
      {
         return true;
      }
   }

   private void addObjectDiagram(IdMap jsonIdMap, Object root, UpdateListener filter)
   {
      JsonArray jsonArray = jsonIdMap.toJsonArray(root, new Filter().withFull(true).withPropertyRegard(filter));

      if (largestJsonArray == null || largestJsonArray.size() <= jsonArray.size())
      {
         largestJsonArray = jsonArray;
         largestRoot = root;
      }

      String imgLink = getAdapter().withRootDir(getModelRootDir()).withIconMap(iconMap)
         .toImg(this.getName() + (this.getStoryboardSteps().size() + 1), jsonArray);

      this.addToSteps(imgLink);

      // this.addObjectDiagramFromJsonArray(root, jsonArray);
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
      if (callEntry.getClassName().equals(StoryPage.class.getName()))
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

   public Storyboard with(String string)
   {
      this.add(string);
      return this;
   }

   /**
    * Generate an html page from this story. This html file will be named like
    * the story, i.e. like the method that created the Storyboard. It will be
    * added to the refs.html and thus become part of the index.html. All these
    * html files are stored in an directory "doc" located in the project root
    * directory.
    * 
    * @see <a href="../../../../../../doc/index.html">SDMLib Storyboards</a>
    */
   public void dumpHTML()
   {
      // copy Javascript files
      try
      {
         generateJavaDoc();
      }
      catch (java.lang.StringIndexOutOfBoundsException e)
      {
         // parser throws IOOB if java source not available at runtime
      }

      dumpIndexHtml();

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

      String shortFileName = "" + storyboardName + ".html";
      String pathname = "doc/" + shortFileName;

      addReferenceToJavaDoc(javaTestFileName.substring(3), Parser.METHOD + ":" + testMethodName, pathname);

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

      // add entry to refs.html
      try
      {
         byte[] readAllBytes = Files.readAllBytes(Paths.get("doc/refs.html"));
         String refsText = new String(readAllBytes);

         String entry = refForFile(storyboardName);

         pos = refsText.indexOf(entry);

         if (pos < 0)
         {
            String newText = CGUtil.replaceAll(refsText, "</body>", entry + "</body>");

            writeToFile("refs.html", newText);
         }
      }
      catch (IOException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      coverage4GeneratedModelCode(largestRoot);
   }

   private void dumpIndexHtml()
   {
      new File("doc").mkdirs();

      new DocEnvironment().copyJS("doc");

      // ensure style file
      File styleFile = new File("doc/style.css");

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
      File file = new File("doc/index.html");

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
      file = new File("doc/refs.html");

      if (!file.exists())
      {
         // build index
         String refHtml = "<html>\n" +
            "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=9\">\n" +
            "<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">\n" +
            "<body>\n" +
            "</body>\n" +
            "</html>\n";

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
      getPropertyChangeSupport().removePropertyChangeListener(listener);
      return true;
   }

   // ==========================================================================

   public void removeYou()
   {
      // setWall(null);
      removeAllFromStoryboardSteps();
      withoutStoryboardSteps(this.getStoryboardSteps().toArray(new StoryboardStep[this.getStoryboardSteps().size()]));
      setWall(null);
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

   public static class RestrictToFilter implements UpdateListener
   {
      private LinkedHashSet<Object> explicitElems;

      public RestrictToFilter(LinkedHashSet<Object> explicitElems)
      {
         this.explicitElems = explicitElems;

      }

      public boolean update(Object values)
      {
         PropertyChangeEvent evt = (PropertyChangeEvent) values;
         if (evt.getNewValue() != null
            && ("Integer Float Double Long Boolean String"
               .indexOf(evt.getNewValue().getClass().getSimpleName()) >= 0))
         {
            return true;
         }
         return explicitElems.contains(evt.getNewValue());
      }
   }

   private StoryboardWall wall = null;

   public StoryboardWall getWall()
   {
      return this.wall;
   }

   public boolean setWall(StoryboardWall value)
   {
      boolean changed = false;

      if (this.wall != value)
      {
         StoryboardWall oldValue = this.wall;

         if (this.wall != null)
         {
            this.wall = null;
            oldValue.setStoryboard(null);
         }

         this.wall = value;

         if (value != null)
         {
            value.withStoryboard(this);
         }

         getPropertyChangeSupport().firePropertyChange(PROPERTY_WALL, oldValue, value);
         changed = true;
      }

      return changed;
   }

   public Storyboard withWall(StoryboardWall value)
   {
      setWall(value);
      return this;
   }

   public StoryboardWall createWall()
   {
      StoryboardWall value = new StoryboardWall();
      withWall(value);
      return value;
   }

   private void generateJavaDoc()
   {
      // search the (test) method that creates this story for methods it calls /
      // tests.
      // create a javadoc on such methods that refers to the test method.

      // where am I called?
      Exception e = new RuntimeException();
      StackTraceElement[] stackTrace = e.getStackTrace();
      StackTraceElement callEntry = stackTrace[1];

      // find first method outside Storyboard and StoryPage
      int i = 1;

      while (true)
      {
         callEntry = stackTrace[i];

         if (callEntry.getClassName().equals(Storyboard.class.getName())
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

      String methodName = callEntry.getMethodName();
      String className = callEntry.getClassName();
      String classFileName = className.replaceAll("\\.", "/") + ".java";
      String fullFileName = this.rootDir + "/" + classFileName;

      // parse the test method body
      Parser parser = new Parser().withFileName(fullFileName);
      File javaFile = new File(fullFileName);

      parser.withFileBody(CGUtil.readFile(javaFile));

      parser.parse();

      SimpleKeyValueList<String, SymTabEntry> symTab = parser.getSymTab();

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

         if (statement.toString().startsWith(" new"))
         {
            // yes, an object is created try to refer to it
            String classUnderTestName = statement.getTokenList().get(1);
            String fullClassUnderTestName = findJavaFile(classUnderTestName, symTab);
            if (fullClassUnderTestName != null)
            {
               // add reference to javadoc
               addReferenceToJavaDoc(fullClassUnderTestName, Parser.CONSTRUCTOR + ":" + classUnderTestName,
                  fullFileName);
               addReferenceToJavaDoc(fullClassUnderTestName, Parser.CLASS + ":" + classUnderTestName, fullFileName);
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

            String fullClassUnderTestName = findJavaFile(classUnderTestName, symTab);

            if (fullClassUnderTestName == null)
               continue; // <==== sudden death

            addReferenceToJavaDoc(fullClassUnderTestName, Parser.METHOD + ":" + methodUnderTestName, fullFileName);
         }
      }
   }

   public void addReferenceToJavaDoc(String classUnderTestName, String methodUnderTestName, String testFileName)
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
