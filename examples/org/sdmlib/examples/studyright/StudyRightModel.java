/*
   Copyright (c) 2012 zuendorf 

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

package org.sdmlib.examples.studyright;

import static org.sdmlib.models.classes.Role.R.INT;
import static org.sdmlib.models.classes.Role.R.MANY;
import static org.sdmlib.models.classes.Role.R.ONE;
import static org.sdmlib.models.classes.Role.R.STRING;

import java.beans.PropertyChangeSupport;

import org.junit.Test;
import org.sdmlib.codegen.Parser;
import org.sdmlib.codegen.SymTabEntry;
import org.sdmlib.models.classes.Association;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Method;
import org.sdmlib.models.classes.Role;
import org.sdmlib.storyboards.Storyboard;
import org.sdmlib.storyboards.StoryboardManager;
import org.sdmlib.utils.PropertyChangeInterface;

public class StudyRightModel implements PropertyChangeInterface 
{
   @Test
   public void testStudyRightReverseClassModel()
   {

      Storyboard storyboard = new Storyboard("examples", "StudyRightReverseClassModel");

      storyboard.add("Start situation: There are some java files. We parse them and generate a class model: ", BACKLOG, "ajahl", "02.04.2012 14:58:18", 0, 0);

      ClassModel model = new ClassModel();

      Clazz professorClass = new Clazz("org.sdmlib.examples.studyright.Professor")
      .withAttribute("name", STRING);

      Clazz topicClass = new Clazz("org.sdmlib.examples.studyright.Topic")
      .withAttribute("title", STRING); 

      new Association()
      .withSource("prof", professorClass, ONE)
      .withTarget("topic", topicClass, ONE);
      
      Clazz roomClass = new Clazz("org.sdmlib.examples.studyright.Room")
      .withAttribute("roomNo", STRING)
      .withAttribute("credits", INT);

      new Method()
      .withClazz(roomClass)
      .withSignature("findPath(String,int)");

      new Association()
      .withSource("neighbors", roomClass, MANY)
      .withTarget("neighbors", roomClass, MANY);

      Clazz studentClass = new Clazz("org.sdmlib.examples.studyright.Student")
      .withAttribute("name", STRING)
      .withAttribute("matrNo", INT);

      new Association()
      .withSource("in", roomClass, ONE)
      .withTarget("students", studentClass, MANY);

      Clazz universityClass = new Clazz("org.sdmlib.examples.studyright.University")
      .withAttribute("name", STRING);

      new Association()
      .withSource("rooms", roomClass, MANY)
      .withTarget("uni", universityClass, ONE);

      new Association()
      .withSource("students", studentClass, MANY)
      .withTarget("uni", universityClass, ONE);


      // model.updateFromCode("examples test src", "org.sdmlib.examples.studyright");

      // model.insertModelCreationCodeHere("examples");

      storyboard.addSVGImage(model.dumpClassDiagram("examples", "StudyRightReverseClassModel"));

      storyboard.add("Bug: running the test multiple times inserts the code multiple times <br>" +
            "Bug solved: there was no bug, there were just two classes with the same name in different packages. <br>" +
            "Hm, might be added to future work. ", DONE, "zuendorf", "19.05.2012 19:39:42", 1, 0);


      StoryboardManager.get()
      .add(storyboard)
      .dumpHTML();
   }

   @Test
   public void testStudyRightExtendsReverseClassModel()
   {

      Storyboard storyboard = new Storyboard("examples", "StudyRightExtendsReverseClassModel");

      storyboard.add("Start situation: There are some java files. We parse them and generate a class model: ", BACKLOG, "ajahl", "02.04.2012 14:58:18", 0, 0);

      ClassModel model = new ClassModel();

      Clazz lectureClass = new Clazz("org.sdmlib.examples.studyrightextends.Lecture")
      .withAttribute("Title", STRING);

      Clazz personClass = new Clazz("org.sdmlib.examples.studyrightextends.Person")
      .withInterfaze(true);

      new Method()
      .withClazz(personClass)
      .withSignature("findMyPosition()")
      .withReturnType("void");

      new Method()
      .withClazz(personClass)
      .withSignature("findMyPosition(String)")
      .withReturnType("void");

      new Method()
      .withClazz(personClass)
      .withSignature("findMyPosition(String,int)")
      .withReturnType("void");

      Clazz roomClass = new Clazz("org.sdmlib.examples.studyrightextends.Room")
      .withAttribute("roomNo", STRING)
      .withAttribute("credits", INT);

      new Method()
      .withClazz(roomClass)
      .withSignature("studentCount()")
      .withReturnType(INT);

      new Association()
      .withSource("neighbors", roomClass, MANY)
      .withTarget("neighbors", roomClass, MANY);

      new Association()
      .withSource("lecture", lectureClass, MANY)
      .withTarget("in", roomClass, ONE);

      Clazz universityClass = new Clazz("org.sdmlib.examples.studyrightextends.University")
      .withAttribute("name", STRING);

      new Association()
      .withSource("rooms", roomClass, MANY)
      .withTarget("uni", universityClass, ONE);

      Clazz femaleClass = new Clazz("org.sdmlib.examples.studyrightextends.Female")
      .withInterfaces(personClass)
      .withAttribute("name", STRING);

      new Method()
      .withClazz(femaleClass)
      .withSignature("findMyPosition()")
      .withReturnType("void");

      new Method()
      .withClazz(femaleClass)
      .withSignature("findMyPosition(String)")
      .withReturnType("void");

      new Method()
      .withClazz(femaleClass)
      .withSignature("findMyPosition(String,int)")
      .withReturnType("void");

      Clazz maleClass = new Clazz("org.sdmlib.examples.studyrightextends.Male")
      .withInterfaze(true)
      .withInterfaces(personClass);

      Clazz professorClass = new Clazz("org.sdmlib.examples.studyrightextends.Professor")
      .withSuperClass(femaleClass)
      .withAttribute("PersNr", INT);

      new Association()
      .withSource("lecture", lectureClass, MANY)
      .withTarget("has", professorClass, ONE);

      Clazz studentClass = new Clazz("org.sdmlib.examples.studyrightextends.Student")
      .withInterfaces(maleClass)
      .withAttribute("name", STRING)
      .withAttribute("matrNo", INT)
      /*set superclass*/
      .withSuperClass(femaleClass);

      new Method()
      .withClazz(studentClass)
      .withSignature("findMyPosition()")
      .withReturnType("void");

      new Method()
      .withClazz(studentClass)
      .withSignature("findMyPosition(String)")
      .withReturnType("void");

      new Method()
      .withClazz(studentClass)
      .withSignature("findMyPosition(String,int)")
      .withReturnType("void");

      new Association()
      .withSource("lecture", lectureClass, MANY)
      .withTarget("listen", studentClass, ONE);


      // model.updateFromCode("examples", "examples test src", "org.sdmlib.examples.studyrightextends");

      // model.insertModelCreationCodeHere("examples");
      
      // model.removeAllGeneratedCode("examples", "examples", "examplehelpers");

      model.generate("examples", "examplehelpers");

      storyboard.addSVGImage(model.dumpClassDiagram("examples", "StudyRightExtendsReverseClassModel"));

      StoryboardManager.get()
      .add(storyboard)
      .dumpHTML();
   }

   @Test
   public void testStudyRightClassesCodeGen()
   {
      Storyboard storyboard = new Storyboard("examples", "StudyRightClassesCodeGen");


      //============================================================
      storyboard.add("1. generate class University");

      ClassModel model = new ClassModel("org.sdmlib.examples.studyright");

      Clazz uniClass = new Clazz("University")
      .withAttribute("name", STRING);

      storyboard.addSVGImage(model.dumpClassDiagram("examples", "StudyRightClasses01"));


      //============================================================
      storyboard.add("2. generate class Student with new notation", 
         IMPLEMENTATION, "zuendorf", "18.03.2012 23:05:42", 1, 0);

      Clazz studClass = new Clazz("Student")
      .withAttribute("name", STRING)
      .withAttribute("matrNo", INT);

      storyboard.addSVGImage(model.dumpClassDiagram("examples", "StudyRightClasses02"));


      //============================================================
      storyboard.add("3. add uni --> stud assoc");

      Association uniToStud = new Association()
      .withSource("uni", uniClass, ONE)
      .withTarget("students", studClass, MANY); 

      storyboard.addSVGImage(model.dumpClassDiagram("examples", "StudyRightClasses03"));


      //============================================================
      storyboard.add("4. add uni --> room");

      Clazz roomClass = new Clazz("Room")
      .withAttribute("roomNo", STRING)
      .withAttribute("credits", INT);

      new Method().withClazz(roomClass).withSignature("findPath(String,int)").withReturnType("void");

      Association uniToRoom = new Association()
      .withSource("uni", uniClass, ONE, Role.AGGREGATION)
      .withTarget("rooms", roomClass, MANY); 

      Association doors = new Association().withSource("neighbors", roomClass, MANY)
            .withTarget("neighbors", roomClass, MANY);

      Association studsInRoom = new Association()
      .withSource("students", studClass, MANY)
      .withTarget("in", roomClass, ONE);

      storyboard.addSVGImage(model.dumpClassDiagram("examples", "StudyRightClasses04"));

      
      //============================================================
      storyboard.add("add assignments:");
      
      Clazz assignmentClass = roomClass.createClassAndAssoc("Assignment", "assignments", MANY, "room", ONE)
            .withAttributes("name", STRING, "points", INT);
      
      studClass.withAssoc(assignmentClass, "done", MANY, "students", ONE)
      .withAttributes("credits", INT, "motivation", INT);

      storyboard.addSVGImage(model.dumpClassDiagram("examples", "StudyRightClasses04b"));

      
      //============================================================
      model.generate("examples", "examplehelpers");

      storyboard.add("5. generate generic set for attributes and assocs", 
         IMPLEMENTATION, "zuendorf", "18.03.2012 23:05:42", 1, 0);

      Parser parser = studClass.getOrCreateParser("examples");

      int pos = parser.indexOf(Parser.METHOD + ":set(String,Object)");

      storyboard.assertTrue("found method set(String,Object) in class student", pos >= 0);

      SymTabEntry symTabEntry = parser.getSymTab().get(Parser.METHOD + ":set(String,Object)");

      storyboard.assertNotNull("found symtab entry for method set(String,Object) ", symTabEntry);

      String methodText = "<pre>   " + parser.getFileBody().substring(symTabEntry.getStartPos(), symTabEntry.getEndPos()+1) + "</pre>";

      storyboard.add(methodText);


      //============================================================
      storyboard.add("6. generate generic get for attributes and assocs", 
         IMPLEMENTATION, "zuendorf", "22.03.2012 14:40:42", 1, 0);

      pos = parser.indexOf(Parser.METHOD + ":get(String)");

      storyboard.assertTrue("found method get(String) in class student", pos >= 0);

      symTabEntry = parser.getSymTab().get(Parser.METHOD + ":get(String)");

      storyboard.assertNotNull("found symtab entry for method get(String) ", symTabEntry);

      methodText = "<pre>   " + parser.getFileBody().substring(symTabEntry.getStartPos(), symTabEntry.getEndPos()+1) + "</pre>";

      storyboard.add(methodText);

      //============================================================
      storyboard.add("7. generate creator classes", 
         IMPLEMENTATION, "zuendorf joern alex", "25.03.2012 22:32:42", 1, 0);

      storyboard.add("<a href='../examplehelpers/org/sdmlib/examples/studyright/creators/StudentCreator.java'>StudentCreator.java</a><br>");

      //============================================================
      storyboard.add("8. generate imports", 
         IMPLEMENTATION, "zuendorf", "25.03.2012 22:37:42", 1, 0);

      pos = parser.indexOf(Parser.IMPORT);
      methodText = parser.getFileBody().substring(pos, parser.getEndOfImports() + 1);

      storyboard.add(methodText);

      //============================================================
      storyboard.add("9. generate property change support", 
         IMPLEMENTATION, "zuendorf", "25.03.2012 22:39:42", 2, 0);      

      storyboard.add("Caution: property change support needs not to be generated if the parent class does this already.");

      //============================================================
      storyboard.add("10. generate removeYou method", 
         IMPLEMENTATION, "zuendorf", "26.03.2012 22:20:42", 2, 0);

      pos = parser.indexOf(Parser.METHOD + ":removeYou()");

      storyboard.assertTrue("found method removeYou) in class student", pos >= 0);

      symTabEntry = parser.getSymTab().get(Parser.METHOD + ":removeYou()");

      storyboard.assertNotNull("found symtab entry for method removeYou() ", symTabEntry);

      methodText = "<pre>   " + parser.getFileBody().substring(symTabEntry.getStartPos(), symTabEntry.getEndPos()+1) + "</pre>";

      storyboard.add(methodText);


      //============================================================
      storyboard.add("Alexander Jahl has added some support for inheritance. See StudyRightExtendsReverseClassModel", 
         IMPLEMENTATION, "zuendorf", "19.05.2012 19:59:42", 1, 0);


      storyboard.add("generic set now works for double. Perhabs boolean and other are still missing", 
         IMPLEMENTATION, "zuendorf", "19.05.2012 13:51:42", 1, 0);


      //============================================================
      storyboard.add("Solved: one to one assoc generate code that compiles. Also solved some import problems with ModelSets", 
         DONE, "zuendorf", "20.05.2012 20:01:42", 2, 0);

      // removed compile to get rid of tools.jar dependency. AZ
      //      storyboard.add("next. compile University.java");
      //
      //      String javaClassPath = System.getProperty("java.class.path");
      //
      //      String[] compArgs = new String[]
      //            {
      //            "-d", "bin",
      //            "-sourcepath", "examples",
      //            "-classpath", javaClassPath,
      //            "examples/org/sdmlib/examples/studyright/University.java"
      //            };
      //
      //      int compResult = Main.compile(compArgs);
      //
      //      Assert.assertEquals("compile did not work: ", 0, compResult);

      StoryboardManager.get()
      .add(storyboard)
      .dumpHTML();
   }

   @Test
   public void testStudyRightOneToOneAssoc()
   {
      Storyboard storyboard = new Storyboard("examples", "StudyRightOneToOneAssoc");

      //============================================================
      storyboard.add("Add class Prof --gives-- Topic");

      ClassModel model = new ClassModel();

      Clazz profClass = new Clazz("org.sdmlib.examples.studyright.Professor")
      .withAttribute("name", STRING);

      Clazz topicClass = new Clazz("org.sdmlib.examples.studyright.Topic")
      .withAttribute("title", STRING);

      new Association()
      .withSource("prof", profClass, ONE)
      .withTarget("topic", topicClass, ONE);

      storyboard.addClassDiagram(model);

      model.generate("examples", "examplehelpers");

      storyboard.add("One to one assocs now work. ", DONE, "zuendorf", "20.05.2012 15:19:42", 1, 0);

      StoryboardManager.get()
      .add(storyboard)
      .dumpHTML();
   }


   public static final String MODELING = "modeling";
   public static final String ACTIVE = "active";
   public static final String DONE = "done";
   public static final String IMPLEMENTATION = "implementation";
   public static final String BACKLOG = "backlog";
   public static final String BUG = "bug";


   //==========================================================================

   public Object get(String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;

      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      return null;
   }


   //==========================================================================

   public boolean set(String attrName, Object value)
   {
      return false;
   }


   //==========================================================================

   protected final PropertyChangeSupport listeners = new PropertyChangeSupport(this);

   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }


   //==========================================================================

   public void removeYou()
   {
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public void testStudyRightObjectStoryboards(  )
   {
      
   }
}

