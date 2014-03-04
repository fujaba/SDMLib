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
import java.lang.reflect.Method;
import java.text.DateFormat;
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
import org.sdmlib.codegen.CGUtil;
import org.sdmlib.codegen.Parser;
import org.sdmlib.codegen.SymTabEntry;
import org.sdmlib.doc.GuiAdapter;
import org.sdmlib.doc.GraphViz.JsonToGraphViz;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Role.R;
import org.sdmlib.models.modelsets.ModelSet;
import org.sdmlib.models.objects.GenericAttribute;
import org.sdmlib.models.objects.GenericGraph;
import org.sdmlib.models.objects.GenericLink;
import org.sdmlib.models.objects.GenericObject;
import org.sdmlib.models.objects.creators.GenericObjectSet;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.serialization.Filter;
import org.sdmlib.serialization.graph.GraphConverter;
import org.sdmlib.serialization.graph.GraphIdMap;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonArray;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.json.JsonObject;
import org.sdmlib.serialization.logic.ConditionMap;
import org.sdmlib.serialization.logic.ValuesMap;
import org.sdmlib.storyboards.creators.StoryboardStepSet;
import org.sdmlib.utils.PropertyChangeInterface;
import org.sdmlib.utils.StrUtil;

public class Storyboard implements PropertyChangeInterface 
{
   public static final String MODELING = "modeling";
   public static final String ACTIVE = "active";
   public static final String DONE = "done";
   public static final String IMPLEMENTATION = "implementation";
   public static final String BACKLOG = "backlog";

   private String name;
   private GuiAdapter adapter;
   private String javaTestFileName; 

   
	public GuiAdapter getAdapter() {
		if(adapter==null){
			adapter = new JsonToGraphViz();
		}
		return adapter;
	}
	
	public Storyboard withAdapter(GuiAdapter adapter) {
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

         // search for a subdirectory containing the javaTestFile of the execution directory and search for the subdi
         File projectDir = new File(".");
         for (File subDir : projectDir.listFiles())
         {
            if (subDir.isDirectory())
            {
               if (new File(subDir.getName() + "/" + javaTestFileName).exists())
               {
                  // got it
                  this.rootDir = subDir.getName();
                  javaTestFileName = "../" + rootDir + "/" + javaTestFileName;

                  break;
               }
            }
         }

      }

      return this.rootDir;
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

      Exception e =  new RuntimeException();

      StackTraceElement[] stackTrace = e.getStackTrace();
      StackTraceElement callEntry = stackTrace[1];
      javaTestFileName = "../" + rootDir + "/" + callEntry.getClassName().replaceAll("\\.", "/") + ".java";
   }

   private static String backlog = "backlog";

   public void dumpHTML(KanbanEntry kanbanBoard) 
   {
      // get kanbanEntry
      KanbanEntry kanbanEntry = kanbanBoard.findOldEntry(this.getName()); 

      if (kanbanEntry == null)
      {
         Date today = new Date(System.currentTimeMillis());
         String todayString = DateFormat.getInstance().format(today);
         kanbanEntry = new KanbanEntry()
         .withName(this.getName())
         .withPhase(backlog)
         .withParent(kanbanBoard)
         .withLogEntries(		         
            new LogEntry()
            .withDate(todayString)
            .withPhase(backlog)
            .withDeveloper(System.getProperty("user.name"))
            .withHoursRemainingInTotal(0.0));
      }
      
      if (kanbanEntry.getPhase() == null)
      {
         kanbanEntry.setPhase(R.BACKLOG);
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
      Iterator<LogEntry> oldLogEntriesIter = ((HashSet<LogEntry>) kanbanEntry.getLogEntries().clone()).iterator();
      Date latestEntryTime = null;
      
      for (LogEntry newEntry : newLogEntries.values())
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
            LogEntry oldEntry = oldLogEntriesIter.next();
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
         LogEntry oldEntry = oldLogEntriesIter.next();
         kanbanEntry.removeFromLogEntries(oldEntry);
      }
      

      // generate the html text
      String htmlText = "<html>\n" +
            "<head>" +
            "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=9\">\n" +
            "<meta charset=\"utf-8\">\n" +
            "<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">\n" +
            "<link href=\"includes/diagramstyle.css\" rel=\"stylesheet\" type=\"text/css\">\r\n" +
            "\r\n" + 
            "<script src=\"includes/dagre.js\"></script>\r\n" + 
            "<script src=\"includes/graph.js\"></script>\r\n" + 
            "<script src=\"includes/layout_dagre.js\"></script>\r\n" + 
            "<script src=\"includes/lines.js\"></script>\r\n" + 
            "</head>" +
            "<body onload=\"init();\">\n" +
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
            System.out.println("Ups");
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

   public void addStep(String txt)
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


   public void coverage4GeneratedModelCode(Object root) 
   {
      if (root == null)
      {
         return; 
      }

      // derive creator class from root and create idMap
      String className = root.getClass().getName();
      String packageName = CGUtil.packageName(className) + ".creators";
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

         JsonIdMap copyMap = (JsonIdMap) new JsonIdMap().withCreator(jsonIdMap.getCreators());

         copyMap.decode(largestJsonArray);

         coverPOClasses(copyMap);
         coverSetClasses(copyMap);
         
         for (String key : copyMap.keySet())
         {
            Object object = copyMap.getObject(key);

            object.toString();

            Class<? extends Object> objectClass = object.getClass();

            Method removeMethod = objectClass.getMethod("removeYou");

            removeMethod.invoke(object);
         }

      }
      catch (Exception e)
      {
         // cannot find creator creator class, sorry.
         // e.printStackTrace();
      }
   }

   private void coverPOClasses(JsonIdMap copyMap)
   {
      // loop through objects in jsonIdMap, pack them into pattern object, read and write all attributes

      Pattern pattern = null;
      Class patternClass = null;
      
      for (String key : copyMap.keySet())
      {
         Object object = copyMap.getObject(key);

         SendableEntityCreator creatorClass = copyMap.getCreatorClass(object);

         String className = object.getClass().getName();
         String packageName = CGUtil.packageName(className) + ".creators";

         if (pattern == null)
         {
            String modelPatternClassName = packageName + ".ModelPattern";

            try
            {
               patternClass = Class.forName(modelPatternClassName);
               pattern = (Pattern) patternClass.newInstance();
            }
            catch (Exception e)
            {

            }
         }

         try
         {
            // cover hasElement methods
            String hasElemMethod = "hasElement" + CGUtil.shortClassName(className) + "PO";

            // add entry 
            Method method = patternClass.getMethod(hasElemMethod);
            method.invoke(pattern);
            
            pattern.removeAllFromElements();
            pattern.setHasMatch(true);
            
            // also the bound method
            method = patternClass.getMethod(hasElemMethod, object.getClass());
            Object patternObject = method.invoke(pattern, object);
            Class patternObjectClass = patternObject.getClass();
            
            // loop through attributes
            for (String attrName : creatorClass.getProperties())
            {
               try
               {
                  // call getter
                  method = patternObjectClass.getMethod("get" + StrUtil.upFirstChar(attrName));
                  Object value = method.invoke(patternObject);
                  
                  Class valueClass = value.getClass();
                  
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
                  
                  if (valueClass.isPrimitive() || valueClass == String.class)
                  {
                     // call with
                     method = patternObjectClass.getMethod("with" + StrUtil.upFirstChar(attrName), valueClass);
                     method.invoke(patternObject, value);
                     
                     // call has
                     method = patternObjectClass.getMethod("has" + StrUtil.upFirstChar(attrName), valueClass);
                     method.invoke(patternObject, value);
                  }
                  else
                  {
                     // model elem, 
                     // call has
                     method = patternObjectClass.getMethod("has" + StrUtil.upFirstChar(attrName));
                     value = method.invoke(patternObject);
                     
                     method = patternObjectClass.getMethod("has" + StrUtil.upFirstChar(attrName), value.getClass());
                     value = method.invoke(patternObject, value);
                  }
                  
               }
               catch (Exception e)
               {
                  // no problem, go on with next attr
               }
            }
            
            // allMatches
            method = patternObjectClass.getMethod("allMatches");
            method.invoke(patternObject);
            
            pattern.removeAllFromElements();
            pattern.setHasMatch(true);
            
            //
            //                  // get direct value
            //                  if (value instanceof Collection)
            //                  {
            //                     value = ((Collection) value).iterator().next();
            //                  }
            //
                              
            //
            //                  // call setter
            //                  Method setMethod = setClass.getMethod("with" + StrUtil.upFirstChar(attrName), valueClass);
            //                  setMethod.invoke(setObject, value);
            //
            //                  Method unsetMethod = setClass.getMethod("without" + StrUtil.upFirstChar(attrName), valueClass);
            //                  unsetMethod.invoke(setObject, value);
                           
            //
            //            // del entry
            //            Method withoutMethod = setClass.getMethod("without", object.getClass());
            //            withoutMethod.invoke(setObject, object);
         }
         catch (Exception e)
         {
            // no prolem, just lower coverage
         }
      }

   }

   private void coverSetClasses(JsonIdMap copyMap)
   {
      // loop through objects in jsonIdMap, pack them into set, read and write all attributes
      for (String key : copyMap.keySet())
      {
         Object object = copyMap.getObject(key);

         SendableEntityCreator creatorClass = copyMap.getCreatorClass(object);

         String className = object.getClass().getName();
         String packageName = CGUtil.packageName(className) + ".creators";
         String setClassName = packageName + "." + CGUtil.shortClassName(className) + "Set";

         try
         {
            Class setClass = Class.forName(setClassName);
            ModelSet setObject = (ModelSet) setClass.newInstance();

            // cover ModelSet methods
            String entryType = setObject.getEntryType();

            // add entry 
            Method withMethod = setClass.getMethod("with", object.getClass());
            withMethod.invoke(setObject, object);

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

                  // get direct value
                  if (value instanceof Collection)
                  {
                     value = ((Collection) value).iterator().next();
                  }

                  Class valueClass = value.getClass();

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

                  // call setter
                  Method setMethod = setClass.getMethod("with" + StrUtil.upFirstChar(attrName), valueClass);
                  setMethod.invoke(setObject, value);

                  Method unsetMethod = setClass.getMethod("without" + StrUtil.upFirstChar(attrName), valueClass);
                  unsetMethod.invoke(setObject, value);
               }
               catch (Exception e)
               {
                  // no prolem, go on with next attr
               }
            }

            // del entry
            Method withoutMethod = setClass.getMethod("without", object.getClass());
            withoutMethod.invoke(setObject, object);
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
      diagName = model.dumpClassDiagram(this.getRootDir(), diagName);
      this.addSVGImage(diagName);
   }

   public void addClassDiagram(ClassModel model, String rootDir)
   {
      String diagName = this.getName() + "ClassDiagram" + this.getStoryboardSteps().size();
      diagName = model.dumpClassDiagram(rootDir, diagName);
      this.addSVGImage(diagName);
   }

   public void addObjectDiagramWith(Object... elems) 
   {
      ArrayList tempElems = new ArrayList(Arrays.asList((Object[]) elems));
      tempElems.add(true);
      Object[] moreElems = tempElems.toArray();
      addObjectDiagram(moreElems);
   }

   private JsonIdMap jsonIdMap = null;

   private LinkedHashMap<String, String> iconMap = new LinkedHashMap<String, String>();

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

         if ( ! (i < elems.length) )
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
            explicitElems.addAll((Collection) object);
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
         }

         SendableEntityCreator objectCreator = jsonIdMap.getCreatorClass(object);

         if (objectCreator == null || objectCreator instanceof GenericCreator)
         {
            String className = object.getClass().getName();
            String packageName = CGUtil.packageName(className) + ".creators";
            className = packageName + ".CreatorCreator";

            Object idMap = null;
            try 
            {
               Class<?> creatorClass = Class.forName(className);
               Method method = creatorClass.getDeclaredMethod("createIdMap", String.class);

               idMap = method.invoke(null, "debug");

               jsonIdMap.withCreator((((JsonIdMap)idMap).getCreators()));
            }
            catch (Exception e)
            {
               // cannot find creator creator class, use generic idMap instead;
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
         addObjectDiagram(jsonIdMap, explicitElems);
      }
   }

	class RestrictToFilter extends ConditionMap {
		private LinkedHashSet<Object> explicitElems;

		public RestrictToFilter(LinkedHashSet<Object> explicitElems) {
			this.explicitElems = explicitElems;
		}

		@Override
		public boolean matches(ValuesMap values) {
			if (values.value != null
					&& ("Integer Float Double Long Boolean String"
							.indexOf(values.value.getClass().getSimpleName()) >= 0)) {
				return true;
			}
			return explicitElems.contains(values.value);
		}
	}   
   
   private JsonArray largestJsonArray = null;

   private Object largestRoot = null;

   public void addObjectDiagram(JsonIdMap jsonIdMap, Object root)
   {
      JsonArray jsonArray = jsonIdMap.toJsonArray(root, new Filter().withFull(true));

      if (largestJsonArray == null || largestJsonArray.size() <= jsonArray.size())
      {
         largestJsonArray = jsonArray;
         largestRoot = root;
      }

      // new diagram
      GraphConverter graphConverter = new GraphConverter();
      JsonObject objectModel=graphConverter.convertToJson(GraphIdMap.OBJECT, jsonArray, true);
      
      String text = 
         "<script>\n" + 
         "   var json = " + 
               objectModel.toString(3) + 
         "   \n" + 
         "   var g = new Graph(json, \"canvas" + this.getStepCounter() +"\");\n" + 
         "   var layouter = new GraphLayout.Dagre(g);\n" + 
         "   layouter.layout(0,0);  \n" + 
         "\n" + 
         "</script>\n";
      
      this.add(text);
   }

   public void addObjectDiagram(JsonIdMap jsonIdMap, Object root, boolean omitRoot)
   {
      JsonArray jsonArray = jsonIdMap.toJsonArray(root);

      if (largestJsonArray == null || largestJsonArray.size() <= jsonArray.size())
      {
         largestJsonArray = jsonArray;
         largestRoot = root;
      }

      String imgLink = getAdapter().withRootDir(getModelRootDir()).toImg(this.getName() + (this.getStoryboardSteps().size()+1), jsonArray, omitRoot, null);

      this.addToSteps(imgLink);
   }

   public void addObjectDiagram(JsonIdMap jsonIdMap, Object root, RestrictToFilter filter, String... aggregationRoles)
   {
      JsonArray jsonArray = jsonIdMap.toJsonArray(root, new Filter().withFull(true).withPropertyRegard(filter));

      if (largestJsonArray == null || largestJsonArray.size() <= jsonArray.size())
      {
         largestJsonArray = jsonArray;
         largestRoot = root;
      }

      String imgLink = getAdapter().withRootDir(getModelRootDir()).toImg(this.getName() + (this.getStoryboardSteps().size()+1), jsonArray, false, aggregationRoles);

      this.addToSteps(imgLink);
   }

   public void setKanbanPhase(String string)
   {
      // TODO Auto-generated method stub

   }

   private LinkedHashMap<String,LogEntry> newLogEntries = new LinkedHashMap<String,LogEntry>();

   public LinkedHashMap<String, LogEntry> getNewLogEntries()
   {
      return newLogEntries;
   }

   public void addLogEntry(String phase, String developer, String date, double hoursSpend, double hoursRemaining, String comment)
   {
      LogEntry logEntry = new LogEntry()
      .withDate(date)
      .withPhase(phase)
      .withDeveloper(developer)
      .withHoursSpend(hoursSpend)
      .withHoursRemainingInTotal(hoursRemaining)
      .withComment(comment);
      
      
      this.addLogEntry(logEntry);
   }
   
   public void addLogEntry(LogEntry entry)
   {
      newLogEntries.put(entry.getDate(), entry);
   }

   /**
    * Add an image to your storyboard.
    * Example: storyboard.addImage(model.dumpClassDiag("examples", "StudyRight with assignments class generation 02"));
    * @param image
    */
   public void addSVGImage(String imageFile)
   {
      this.addToSteps("<embed type=\"image/svg+xml\" src='" + imageFile + "'>");     
   }

   public void addImage(String imageFile)
   {
      this.addToSteps("<img src='" + imageFile + "'>");     
   }

   /**
    * Add an entry to your Kanban board TODO: document this, add phase entries to some generic class not into the example!!!
    *  Example storyboard.add("ExtendStoryboardByAddToDoMethod", BACKLOG, "zuendorf", "21.08.2012 15:57:42", 0, 1);
    * @param string
    * @param phase
    * @param developer
    * @param date
    * @param hoursSpend
    * @param hoursRemaining
    */
   public void add(String string, String phase, String developer, String date, double hoursSpend, double hoursRemaining)
   {
      add(string);
      addLogEntry(new LogEntry()
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

      LogEntry logEntry = todoEntry.findOrCreateLogEntry(date, phase)
            .withPhase(phase)
            .withHoursRemainingInTotal(hoursRemaining)
            .withHoursSpend(hoursSpend);

      man.dumpKanban();

      return todoEntry;
   }  


   private int codeStartLineNumber = -1;

   private ByteArrayOutputStream systemOutRecorder;

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

      Clazz clazz = new Clazz(className);

      Parser parser = clazz.getOrCreateParser(rootDir);

      int pos = parser.indexOf(Parser.METHOD + ":" + methodSignature);

      SymTabEntry symTabEntry = parser.getSymTab().get(Parser.METHOD + ":" + methodSignature);

      String methodText = "<pre>   " + 
      StrUtil.htmlEncode(parser.getFileBody().substring(symTabEntry.getStartPos(), symTabEntry.getEndPos()+1)) 
      + "</pre>";

      return methodText;
   }

   public void addGenericObjectDiag(GenericGraph graph)
   {
      this.addGenericObjectDiag(graph, GenericObject.EMPTY_SET);
   }

   public void addGenericObjectDiag(GenericGraph graph, GenericObjectSet hiddenObjects)
   {
      this.addGenericObjectDiag(this.getName() + "GenObjDiagStep" + this.getStoryboardSteps().size(), graph, hiddenObjects);
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
      this.add("Check: " + message + expected); 
      Assert.assertEquals("FAILED: " + message, expected, actual, delta);
   }

   public void assertTrue(String message, boolean condition)
   {
      this.add("Check: " + message); 
      Assert.assertTrue("FAILED: " + message, condition);
   }

   public void assertEquals(String message, int expected, int actual)
   {
      this.add("Check: " + message + expected); 
      Assert.assertEquals("FAILED: " + message, expected, actual);
   }

   public void assertNotNull(String message, Object obj)
   {
      this.add("Check: " + message + obj); 
      Assert.assertNotNull("FAILED: " + message, obj);
   }



   //==========================================================================

   public Object get(String attrName)
   {
      if (PROPERTY_STORYBOARDSTEPS.equalsIgnoreCase(attrName))
      {
         return getStoryboardSteps();
      }

      if (PROPERTY_WALL.equalsIgnoreCase(attrName))
      {
         return getWall();
      }

      if (PROPERTY_ROOTDIR.equalsIgnoreCase(attrName))
      {
         return getRootDir();
      }

      if (PROPERTY_STEPCOUNTER.equalsIgnoreCase(attrName))
      {
         return getStepCounter();
      }

      if (PROPERTY_STEPDONECOUNTER.equalsIgnoreCase(attrName))
      {
         return getStepDoneCounter();
      }

      return null;
   }


   //==========================================================================

   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_STORYBOARDSTEPS.equalsIgnoreCase(attrName))
      {
         addToStoryboardSteps((StoryboardStep) value);
         return true;
      }

      if ((PROPERTY_STORYBOARDSTEPS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromStoryboardSteps((StoryboardStep) value);
         return true;
      }

      if (PROPERTY_WALL.equalsIgnoreCase(attrName))
      {
         setWall((StoryboardWall) value);
         return true;
      }

      if (PROPERTY_ROOTDIR.equalsIgnoreCase(attrName))
      {
         setRootDir((String) value);
         return true;
      }

      if (PROPERTY_STEPCOUNTER.equalsIgnoreCase(attrName))
      {
         setStepCounter(Integer.parseInt(value.toString()));
         return true;
      }

      if (PROPERTY_STEPDONECOUNTER.equalsIgnoreCase(attrName))
      {
         setStepDoneCounter(Integer.parseInt(value.toString()));
         return true;
      }

      return false;
   }


   //==========================================================================

   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);

   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }

   public void addPropertyChangeListener(PropertyChangeListener listener) 
   {
      getPropertyChangeSupport().addPropertyChangeListener(listener);
   }


   //==========================================================================

   public void removeYou()
   {
      removeAllFromStoryboardSteps();
      setWall(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }


   public String toString()
   {
      StringBuilder _ = new StringBuilder();

      _.append(" ").append("Storyboard" + this.getStoryboardSteps().getFirst());
      _.append(" ").append(this.getRootDir());
      _.append(" ").append(this.getStepCounter());
      _.append(" ").append(this.getStepDoneCounter());
      return _.substring(1);
   }



   /********************************************************************
    * <pre>
    *              one                       many
    * Storyboard ----------------------------------- StoryboardStep
    *              storyboard                   storyboardSteps
    * </pre>
    */

   public static final String PROPERTY_STORYBOARDSTEPS = "storyboardSteps";

   private StoryboardStepSet storyboardSteps = null;

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

         changed = this.storyboardSteps.add (value);

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
         changed = this.storyboardSteps.remove (value);

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
    */

   public static final String PROPERTY_WALL = "wall";

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


   //==========================================================================

   public static final String PROPERTY_ROOTDIR = "rootDir";

   private String rootDir = null;

   public String getRootDir()
   {
      return this.rootDir;
   }

   public void setRootDir(String value)
   {
      if ( ! StrUtil.stringEquals(this.rootDir, value))
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


   //==========================================================================

   public static final String PROPERTY_MODELROOTDIR = "modelRootDir";

   private String modelRootDir = null;

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
      if ( ! StrUtil.stringEquals(this.modelRootDir, value))
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


   //==========================================================================

   public static final String PROPERTY_STEPCOUNTER = "stepCounter";

   private int stepCounter;

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


   //==========================================================================

   public static final String PROPERTY_STEPDONECOUNTER = "stepDoneCounter";

   private int stepDoneCounter;

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

   private String sprintName = null;
   
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
   
   private String kanbanWorkFlow = null;
   private String projectName = null;

   public void setKanbanWorkFlow(String string)
   {
      this.kanbanWorkFlow = string;
   }

   
   public void setProjectName(String string)
   {
      this.projectName = string;
   } 
}

