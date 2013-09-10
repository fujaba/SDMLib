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


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Vector;

import org.junit.Assert;
import org.sdmlib.codegen.CGUtil;
import org.sdmlib.codegen.Parser;
import org.sdmlib.codegen.SymTabEntry;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.modelsets.ModelSet;
import org.sdmlib.models.objects.GenericAttribute;
import org.sdmlib.models.objects.GenericGraph;
import org.sdmlib.models.objects.GenericLink;
import org.sdmlib.models.objects.GenericObject;
import org.sdmlib.models.objects.creators.GenericObjectSet;
import org.sdmlib.serialization.IdMap;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonArray;
import org.sdmlib.serialization.json.JsonFilter;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.utils.StrUtil;



public class Storyboard 
{
   public static final String MODELING = "modeling";
   public static final String ACTIVE = "active";
   public static final String DONE = "done";
   public static final String IMPLEMENTATION = "implementation";
   public static final String BACKLOG = "backlog";


   private String name;

   private String javaTestFileName; 

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

   public Storyboard()
   {
      Exception e = new RuntimeException();

      StackTraceElement[] stackTrace = e.getStackTrace();
      StackTraceElement callEntry = stackTrace[1];
      javaTestFileName = "../src/" + callEntry.getClassName().replaceAll("\\.", "/") + ".java";

      String methodName = stackTrace[1].getMethodName();

      if (methodName.startsWith("test"))
      {
         methodName = methodName.substring(4);
      }

      setName(methodName);

      steps.add(name);


   }

   public Storyboard(String rootDir)
   {
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

      steps.add(name);


   }

   public Storyboard(String rootDir, String name)
   {
      setName(name);

      steps.add(name);

      Exception e =  new RuntimeException();

      StackTraceElement[] stackTrace = e.getStackTrace();
      StackTraceElement callEntry = stackTrace[1];
      javaTestFileName = "../" + rootDir + "/" + callEntry.getClassName().replaceAll("\\.", "/") + ".java";
   }

   public Vector<String> steps = new Vector<String>();

   private static String backlog = "backlog";;

   public void dumpHTML(KanbanEntry kanbanBoard) 
   {
      // get kanbanEntry
      KanbanEntry kanbanEntry = kanbanBoard.findOldEntry(this.getName()); 

      if (kanbanEntry == null)
      {
         Date today = new Date(System.currentTimeMillis());
         SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
         String todayString = df.format(today);
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

      // compute total remaining time
      double sumOfRemainingTime = 0.0;
      for (LogEntry newEntry : newLogEntries.values())
      {
         sumOfRemainingTime += newEntry.getHoursRemainingInTotal();
      }

      // update log entries
      for (LogEntry oldEntry : kanbanEntry.getLogEntries())
      {
         // do I have a new entry for this date
         String oldDate = oldEntry.getDate();
         LogEntry newLogEntry = newLogEntries.get(oldDate);

         if (newLogEntry != null)
         {
            // transfer values
            oldEntry.withDeveloper(newLogEntry.getDeveloper())
            .withHoursRemainingInPhase(newLogEntry.getHoursRemainingInPhase())
            .withHoursRemainingInTotal(sumOfRemainingTime)
            .withHoursSpend(newLogEntry.getHoursSpend())
            .withPhase(newLogEntry.getPhase());

            // remove from newLogEntries
            newLogEntries.remove(oldDate);
         }
      }    

      for (String key : newLogEntries.keySet())
      {
         LogEntry newLogEntry = newLogEntries.get(key);
         newLogEntry.setHoursRemainingInTotal(sumOfRemainingTime);
         kanbanEntry.addToLogEntries(newLogEntry);
      }

      // generate the html text
      String htmlText = "<html>\n" +
            "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=9\">\n" +
            "<body>\n" +
            "<p>Storyboard <a href='testfilename' type='text/x-java'>storyboardName</a></p>\n" +
            "$text\n" +
            "</body>\n" +
            "</html>\n";

      String storyboardName = (String) steps.get(0);

      htmlText = htmlText.replaceFirst("storyboardName", storyboardName);
      htmlText = htmlText.replaceFirst("testfilename", javaTestFileName);
      kanbanEntry.setName(storyboardName);

      String shortFileName = "" + storyboardName + ".html";
      String pathname = "doc/" + shortFileName;

      StringBuffer text = new StringBuffer();

      for (int i = 1; i < steps.size(); i++)
      {
         // steps may be strings
         Object object = steps.get(i);

         if (object instanceof String)
         {
            String content = (String) object;

            if (content.startsWith("["))
            {
               // yuml object diagram text, wrap into img
               String imgText = "<img src='http://yuml.me/diagram/scale:80/class/objDiagramText' />\n";
               imgText = imgText.replaceFirst("objDiagramText", content);
               text.append(imgText);
            }
            else if (content.startsWith("<"))
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
               text.append("<p>" + object + "</p>\n");
            }
         }
         else 
         {
            // in general serialize it to JSON
            String json = "don't know what to do with " + object; // new JsonObject(object).toString(2);
            text.append("<p>" + json + "</p>\n");
         }
      } // for

      htmlText = htmlText.replaceFirst("\\$text", text.toString());

      writeToFile(shortFileName, htmlText);
      
      coverage4GeneratedModelCode(largestRoot);
   }

   private void writeToFile(String imgName, String fileText)
   {
      try
      {
         BufferedWriter out = new BufferedWriter(new FileWriter("doc/" + imgName));

         out.write(fileText);
         out.close();
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
   }



   public void add(String string)
   {
      steps.add(string);      
   }

   public void addText(String string)
   {
      steps.add(string);      
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
         
         copyMap.readJson(largestJsonArray);

         for (String key : copyMap.getKeys())
         {
            Object object = copyMap.getObject(key);

            object.toString();

            Class<? extends Object> objectClass = object.getClass();

            Method removeMethod = objectClass.getMethod("removeYou");

            removeMethod.invoke(object);
         }
         
         coverSetClasses();

      }
      catch (Exception e)
      {
         // cannot find creator creator class, sorry.
      }
   }

   private void coverSetClasses()
   {
      // loop through objects in jsonIdMap, pack them into package, read and write all attributes
      for (String key : jsonIdMap.keySet())
      {
         Object object = jsonIdMap.getObject(key);
         
         SendableEntityCreator creatorClass = jsonIdMap.getCreatorClass(object);
         
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
            // no prolem, just lower coverage
         }
      }
      
   }

   public void addObjectDiagramWith(Object... elems) 
   {
      ArrayList tempElems = new ArrayList(Arrays.asList((Object[]) elems));
      tempElems.add(true);
      Object[] moreElems = tempElems.toArray();
      addObjectDiagram(moreElems);
   }
   
   private JsonIdMap jsonIdMap = null;

   public void addObjectDiagram(Object... elems) 
   {
      String objectName;
      Object object;
      Object root = null;
      LinkedHashSet<Object> explicitElems = new LinkedHashSet<Object>();
      boolean restrictToExplicitElems = false;

      // go through all diagram elems
      int i = 0;

      while (i < elems.length)
      {
         objectName = null; 
         object = null;

         if (elems[i] instanceof String)
         {
            // name for an object
            objectName = (String) elems[i];

            i++;

            if ( ! (i < elems.length) )
            {
               // ups no object for this name.
               break;
            }
         }

         object = elems[i];
         i++;

         if (object.equals(true))
         {
            restrictToExplicitElems = true;
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

         explicitElems.add(object);

         if (root == null)
         {
            root = object;
         }

         // do we have a JsonIdMap?
         if (jsonIdMap == null)
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

            }
            catch (Exception e)
            {
               // cannot find creator creator class, use generic idMap instead;
               idMap = new GenericIdMap();
            }

            jsonIdMap = (JsonIdMap) idMap;
         }

         // add to jsonIdMap
         if (objectName != null)
         {
            jsonIdMap.put(objectName, object);
         }         
      }

      // all names collected, dump it
      if (restrictToExplicitElems)
      {
         JsonFilter jsonFilter = new RestrictToFilter(explicitElems);
         addObjectDiagram(jsonIdMap, root, jsonFilter);
      }
      else
      {
         addObjectDiagram(jsonIdMap, root);
      }
   }

   class RestrictToFilter extends JsonFilter
   {

      private LinkedHashSet<Object> explicitElems;

      public RestrictToFilter(LinkedHashSet<Object> explicitElems)
      {
         this.explicitElems = explicitElems;
         withFullSerialization(true);
      }

      @Override
      public boolean isRegard(IdMap map, Object entity, String property,
            Object value, boolean isMany)
      {
         if (value != null && ("Integer Float Double Long Boolean String".indexOf(value.getClass().getSimpleName()) >= 0))
         {
            return true;
         }
         return explicitElems.contains(value);
      }
   }

   private JsonArray largestJsonArray = null;

   private Object largestRoot = null;

   public void addObjectDiagram(JsonIdMap jsonIdMap, Object root)
   {
      JsonFilter jsonFilter = (JsonFilter) new JsonFilter().withFullSerialization(true);
      JsonArray jsonArray = jsonIdMap.toJsonArray(root, jsonFilter);

      if (largestJsonArray == null || largestJsonArray.size() <= jsonArray.size())
      {
         largestJsonArray = jsonArray;
         largestRoot = root;
      }

      String imgLink = JsonToImg.get().toImg(this.getName() + (this.steps.size()+1), jsonArray);

      steps.add(imgLink);
   }

   public void addObjectDiagram(JsonIdMap jsonIdMap, Object root, boolean omitRoot)
   {
      JsonFilter jsonFilter = (JsonFilter) new JsonFilter().withFullSerialization(true);
      JsonArray jsonArray = jsonIdMap.toJsonArray(root, jsonFilter);

      if (largestJsonArray == null || largestJsonArray.size() <= jsonArray.size())
      {
         largestJsonArray = jsonArray;
         largestRoot = root;
      }

      String imgLink = JsonToImg.get().toImg(this.getName() + (this.steps.size()+1), jsonArray, omitRoot, null);

      steps.add(imgLink);
   }

   public void addObjectDiagram(JsonIdMap jsonIdMap, Object root, JsonFilter filter, String... aggregationRoles)
   {
      JsonArray jsonArray = jsonIdMap.toJsonArray(root, filter);

      if (largestJsonArray == null || largestJsonArray.size() <= jsonArray.size())
      {
         largestJsonArray = jsonArray;
         largestRoot = root;
      }

      String imgLink = JsonToImg.get().toImg(this.getName() + (this.steps.size()+1), jsonArray, false, aggregationRoles);

      steps.add(imgLink);
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

   public void addLogEntry(LogEntry entry)
   {
      newLogEntries.put(entry.getDate(), entry);
   }

   /**
    * Add an image to your storyboard.
    * Example: storyboard.addImage(model.dumpClassDiag("examples", "StudyRight with assignments class generation 02"));
    * @param image
    */
   public void addImage(String imageFile)
   {
      steps.add("<embed type=\"image/svg+xml\" src='" + imageFile + "'>");     
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

   public void addCode(String rootDir)
   {
      String className = "";
      // store code end line number
      int codeEndLineNumber = -1;

      Exception e = new RuntimeException();
      StackTraceElement[] stackTrace = e.getStackTrace();
      StackTraceElement callEntry = stackTrace[1];
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
                     this.add(buf.toString());
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

      String methodText = "   " + parser.getFileBody().substring(symTabEntry.getStartPos(), symTabEntry.getEndPos()+1);

      return methodText;
   }

   public void addGenericObjectDiag(GenericGraph graph)
   {
      this.addGenericObjectDiag(graph, GenericObject.EMPTY_SET);
   }

   public void addGenericObjectDiag(GenericGraph graph, GenericObjectSet hiddenObjects)
   {
      this.addGenericObjectDiag(this.getName() + "GenObjDiagStep" + this.steps.size(), graph, hiddenObjects);
   }

   private int objNo;

   public void addGenericObjectDiag(String diagramName, GenericGraph graph)
   {
      this.addGenericObjectDiag(diagramName, graph, GenericObject.EMPTY_SET);
   }

   public void addGenericObjectDiag(String diagramName, GenericGraph graph, GenericObjectSet hiddenObjects)
   {
      objNo = 0;

      // name all objects 
      LinkedHashMap<GenericObject, String> allObjects = new LinkedHashMap<GenericObject, String>();

      // String imgLink = JsonToImg.get().toImg(this.getName() + (this.steps.size()+1), jsonArray);      
      String link = "<embed type=\"image/svg+xml\" src='<imagename>'>\n";
      link = link.replaceFirst("<imagename>", diagramName + ".svg");

      // generate dot file
      String fileText = "graph ObjectDiagram {\n" +
            "   node [shape = none, fontsize = 10];\n" +
            "   edge [fontsize = 10];\n\n" +
            "<nodes>\n" +
            "<edges>" +
            "}\n";

      // list of nodes
      StringBuilder nodeBuilder = new StringBuilder();
      for (GenericObject currentObject : graph.getObjects())
      {
         if (hiddenObjects.contains(currentObject))
         {
            continue;
         }


         StringBuilder nodeLine = new StringBuilder(
            "<id> [label=<<table border='0' cellborder='borderSize' cellspacing='0'> iconrow<tr> <td> <u><id> :<classname></u></td></tr>attrText</table>>];\n"
               );

         String borderSize = "1";

         allObjects.put(currentObject, findNameFor(currentObject));

         String iconRow = "";

         if (currentObject.getIcon() != null)
         {
            iconRow = "<tr><td border='0'><img src=\"" + currentObject.getIcon() + "\"/></td></tr>";
            borderSize = "0";
         }

         String type = "_"; 
         if (currentObject.getType() != null)
         {
            type = currentObject.getType();
         }

         // go through attributes
         String attrText = "<tr><td border='borderSize'><table border='0' cellborder='0' cellspacing='0'></table></td></tr>";

         attrText = attrText.replaceFirst("borderSize", borderSize);

         for (GenericAttribute attr : currentObject.getAttrs())
         {
            String attrLine = "<tr><td><key> = \"<value>\"</td></tr>";
            attrLine = attrLine.replaceFirst("<key>", attr.getName());
            attrLine = attrLine.replaceFirst("<value>", attr.getValue());
            attrLine = attrLine.replaceFirst("borderSize", borderSize);

            attrText = attrText.replaceFirst("</table>", attrLine + "</table>");
         }

         if ( currentObject.getAttrs().isEmpty())
         {
            attrText = "";
         }

         CGUtil.replaceAll(nodeLine, 
            "iconrow", iconRow,
            "<id>", allObjects.get(currentObject),
            "<classname>", type, 
            "attrText", attrText, 
            "borderSize", borderSize
               );

         nodeBuilder.append(nodeLine.toString());
      }

      fileText = fileText.replaceFirst("<nodes>", nodeBuilder.toString());

      // now generate edges from edgeMap
      StringBuilder edgeBuilder = new StringBuilder();
      for (GenericLink currentLink : graph.getLinks())
      {
         if (hiddenObjects.contains(currentLink.getSrc()) || hiddenObjects.contains(currentLink.getTgt()))
         {
            continue;
         }

         String edgeLine = "<srcId> -- <tgtId> [headlabel = \"<headlabel>\" taillabel = \"<taillabel>\"];\n";
         edgeLine = edgeLine.replaceFirst("<srcId>", allObjects.get(currentLink.getSrc()));
         edgeLine = edgeLine.replaceFirst("<tgtId>", allObjects.get(currentLink.getTgt()));
         String headLabel = currentLink.getTgtLabel();
         if (headLabel == null)
         {
            headLabel = " ";
         }
         edgeLine = edgeLine.replaceFirst("<headlabel>", currentLink.getTgtLabel());
         String taillabel = currentLink.getSrcLabel();
         if (taillabel == null)
         {
            taillabel = " ";
         }
         edgeLine = edgeLine.replaceFirst("<taillabel>", taillabel);

         edgeBuilder.append(edgeLine);
      }

      fileText = fileText.replaceFirst("<edges>", edgeBuilder.toString());

      CallDot.callDot(diagramName,fileText);


      steps.add(link);
   }

   private String findNameFor(GenericObject currentObject)
   {
      if (currentObject.getName() != null)
      {
         return currentObject.getName();
      }

      objNo++;

      return "_" + objNo;
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

}
