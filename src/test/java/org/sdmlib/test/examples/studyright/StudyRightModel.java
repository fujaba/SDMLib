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

package org.sdmlib.test.examples.studyright;

import java.beans.PropertyChangeSupport;

import org.junit.Test;
import org.sdmlib.codegen.Parser;
import org.sdmlib.codegen.SymTabEntry;
import org.sdmlib.models.classes.Association;
import org.sdmlib.models.classes.Card;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.DataType;
import org.sdmlib.models.classes.Method;
import org.sdmlib.models.classes.Parameter;
import org.sdmlib.models.classes.Role;
import org.sdmlib.models.classes.logic.GenClass;
import org.sdmlib.serialization.PropertyChangeInterface;
import org.sdmlib.storyboards.Storyboard;
import org.sdmlib.storyboards.StoryboardManager;

public class StudyRightModel implements PropertyChangeInterface 
{
   @Test
   public void testStudyRightReverseClassModel()
   {

      Storyboard storyboard = new Storyboard("examples", "StudyRightReverseClassModel");

      storyboard.setSprint("Sprint.001.Booting");
      
      storyboard.add("Start situation: There are some java files. We parse them and generate a class model: ", BACKLOG, "ajahl", "02.04.2012 14:58:18", 0, 0);

      ClassModel model = new ClassModel("org.sdmlib.test.examples.studyright.model");

      Clazz professorClass = model.createClazz("org.sdmlib.test.examples.studyright.model.Professor")
      .withAttribute("name", DataType.STRING).with(model);

      Clazz topicClass = model.createClazz("org.sdmlib.test.examples.studyright.Topic")
      .withAttribute("title", DataType.STRING); 

      new Association()
      .withSource(professorClass, "prof", Card.ONE)
      .withTarget(topicClass, "topic", Card.ONE);
      
      Clazz roomClass = model.createClazz("org.sdmlib.test.examples.studyright.model.Room")
      .withAttribute("roomNo", DataType.STRING)
      .withAttribute("credits", DataType.INT);

      new Method("findPath", new Parameter(DataType.STRING), new Parameter(DataType.INT))
         .with(roomClass);

      new Association()
      .withSource(roomClass, "neighbors", Card.MANY)
      .withTarget(roomClass, "neighbors", Card.MANY);

      Clazz studentClass = model.createClazz("org.sdmlib.test.examples.studyright.model.Student")
      .withAttribute("name", DataType.STRING)
      .withAttribute("matrNo", DataType.INT);

      new Association()
      .withSource(roomClass, "in", Card.ONE)
      .withTarget(studentClass, "students", Card.MANY);

      Clazz universityClass = model.createClazz("org.sdmlib.test.examples.studyright.model.University")
      .withAttribute("name", DataType.STRING);

      new Association()
      .withSource(roomClass, "rooms", Card.MANY)
      .withTarget(universityClass, "uni", Card.ONE);

      new Association()
      .withSource(studentClass, "students", Card.MANY)
      .withTarget(universityClass, "uni", Card.ONE);


      // model.getGenerator().updateFromCode("examples test src", "org.sdmlib.test.examples.studyright");

      // model.insertModelCreationCodeHere("examples");

      storyboard.addClassDiagram(model);

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

      ClassModel model = new ClassModel("org.sdmlib.test.examples.studyright.model");

      Clazz lectureClass = model.createClazz("org.sdmlib.test.examples.studyright.model.Lecture").with(model)
      .withAttribute("Title", DataType.STRING);

      Clazz personClass = model.createClazz("org.sdmlib.test.examples.studyright.model.Person")
      .withInterface(true);

      new Method("findMyPosition")
      .with(personClass);

      new Method("findMyPosition", new Parameter(DataType.STRING)).with(personClass);

      new Method("findMyPosition", new Parameter(DataType.STRING), new Parameter(DataType.INT))
            .with(personClass);
      
      Clazz roomClass = model.createClazz("org.sdmlib.test.examples.studyright.model.Room")
      .withAttribute("roomNo", DataType.STRING)
      .withAttribute("credits", DataType.INT);

      new Method("studentCount").with(roomClass).withReturnType(DataType.INT);

      new Association()
      .withSource(roomClass, "neighbors", Card.MANY)
      .withTarget(roomClass, "neighbors", Card.MANY);

      new Association()
      .withSource(lectureClass, "lecture", Card.MANY)
      .withTarget(roomClass, "in", Card.ONE);

      Clazz universityClass = model.createClazz("org.sdmlib.test.examples.studyright.model.University")
      .withAttribute("name", DataType.STRING);

      new Association()
      .withSource(roomClass, "rooms", Card.MANY)
      .withTarget(universityClass, "uni", Card.ONE);

      Clazz femaleClass = model.createClazz("org.sdmlib.test.examples.studyright.model.Female")
      .withSuperClazz(personClass)
      .withAttribute("name", DataType.STRING);

      new Method("findMyPosition").with(femaleClass);

      new Method("findMyPosition", new Parameter(DataType.STRING)).with(femaleClass);

      new Method("findMyPosition", new Parameter(DataType.STRING), new Parameter(DataType.INT))
         .with(femaleClass);

      Clazz maleClass = model.createClazz("org.sdmlib.test.examples.studyright.model.Male")
      .withInterface(true)
      .withSuperClazz(personClass);

      Clazz professorClass = model.createClazz("org.sdmlib.test.examples.studyright.model.Professor")
      .withSuperClazz(femaleClass)
      .withAttribute("PersNr", DataType.INT);

      new Association()
      .withSource(lectureClass, "lecture", Card.MANY)
      .withTarget(professorClass, "has", Card.ONE);

      Clazz studentClass = model.createClazz("org.sdmlib.test.examples.studyright.model.Student")
      .withSuperClazz(maleClass)
      .withAttribute("name", DataType.STRING)
      .withAttribute("matrNo", DataType.INT)
      /*set superclass*/
      .withSuperClazz(femaleClass);

      new Method("findMyPosition").with(studentClass);

      new Method("findMyPosition", new Parameter(DataType.STRING)).with(studentClass);

      new Method("findMyPosition", new Parameter(DataType.STRING), new Parameter(DataType.INT))
         .with(studentClass);

      new Association()
      .withSource(lectureClass, "lecture", Card.MANY)
      .withTarget(studentClass, "listen", Card.ONE);


      // model.updateFromCode("examples", "examples test src", "org.sdmlib.test.examples.studyrightextends");

      // model.insertModelCreationCodeHere("examples");
      
      // model.removeAllGeneratedCode("examples", "examples", "examplehelpers");

      model.generate("src/test/java");

      storyboard.addClassDiagram(model);

      StoryboardManager.get()
      .add(storyboard)
      .dumpHTML();
   }

   @Test
   public void testStudyRightClassesCodeGen()
   {
      Storyboard storyboard = new Storyboard("examples", "StudyRightClassesCodeGen");

      storyboard.setSprint("Sprint.001.Booting");
      
      //============================================================
      storyboard.add("1. generate class University");

      ClassModel model = new ClassModel("org.sdmlib.test.examples.studyright.model");

      Clazz uniClass = model.createClazz("University")
      .withAttribute("name", DataType.STRING);

      storyboard.addClassDiagram(model);


      //============================================================
      storyboard.add("2. generate class Student with new notation", 
         IMPLEMENTATION, "zuendorf", "18.03.2012 23:05:42", 1, 0);

      Clazz studClass = model.createClazz("Student")
      .withAttribute("name", DataType.STRING)
      .withAttribute("matrNo", DataType.INT).with(model);

      storyboard.addClassDiagram(model);


      //============================================================
      storyboard.add("3. add uni --> stud assoc");

      Association uniToStud = new Association()
      .withSource(uniClass, "uni", Card.ONE)
      .withTarget(studClass, "students", Card.MANY); 

      storyboard.addClassDiagram(model);


      //============================================================
      storyboard.add("4. add uni --> room");

      Clazz roomClass = model.createClazz("Room")
      .withAttribute("roomNo", DataType.STRING)
      .withAttribute("credits", DataType.INT);

      new Method("findPath", new Parameter(DataType.STRING), new Parameter(DataType.INT))
         .with(roomClass);

      Association uniToRoom = new Association()
      .withSource(new Role(uniClass, "uni", Card.ONE).withKind(Role.AGGREGATION))
      .withTarget(roomClass, "rooms", Card.MANY); 

      Association doors = new Association().withSource(roomClass, "neighbors", Card.MANY)
            .withTarget(roomClass, "neighbors", Card.MANY);

      Association studsInRoom = new Association()
      .withSource(studClass, "students", Card.MANY)
      .withTarget(roomClass, "in", Card.ONE);

      storyboard.addClassDiagram(model);

      
      //============================================================
      storyboard.add("add assignments:");
      
      Clazz assignmentClass = model.createClazz("Assignment")
            .withAssoc(roomClass, "assignments", Card.MANY, "room", Card.ONE)
            .withAttribute("name", DataType.STRING)
            .withAttribute("points", DataType.INT);

//      Clazz assignmentClass = roomClass.createClassAndAssoc("Assignment", "assignments", Card.MANY, "room", Card.ONE)
//            .withAttributes("name", DataType.STRING, "points", DataType.INT);
      
      studClass.withAssoc(assignmentClass, "done", Card.MANY, "students", Card.ONE)
          .withAttribute("credits", DataType.INT)
           .withAttribute("motivation", DataType.INT);

      storyboard.addClassDiagram(model);

      
      //============================================================
      model.generate("src/test/java");

      storyboard.add("5. generate generic set for attributes and assocs", 
         IMPLEMENTATION, "zuendorf", "18.03.2012 23:05:42", 1, 0);

      GenClass genCLazz = model.getGenerator().getOrCreate(studClass);
      Parser parser = genCLazz.getOrCreateParser("examples");
//      Parser parser = studClass.getOrCreateParser("examples");

      int pos = parser.indexOf(Parser.METHOD + ":set(String,Object)");

      storyboard.assertTrue("found method set(String,Object) in class student", pos < 0);

//      SymTabEntry symTabEntry = parser.getSymTab().get(Parser.METHOD + ":set(String,Object)");
//
//      storyboard.assertNull("found symtab entry for method set(String,Object) ", symTabEntry);
//
//      String methodText = "<pre>   " + parser.getFileBody().substring(symTabEntry.getStartPos(), symTabEntry.getEndPos()+1) + "</pre>";
//
//      storyboard.add(methodText);


      //============================================================
      storyboard.add("6. generate generic get for attributes and assocs", 
         IMPLEMENTATION, "zuendorf", "22.03.2012 14:40:42", 1, 0);

      pos = parser.indexOf(Parser.METHOD + ":get(String)");

//      storyboard.assertTrue("found method get(String) in class student", pos >= 0);
//
//      symTabEntry = parser.getSymTab().get(Parser.METHOD + ":get(String)");
//
//      storyboard.assertNotNull("found symtab entry for method get(String) ", symTabEntry);
//
//      methodText = "<pre>   " + parser.getFileBody().substring(symTabEntry.getStartPos(), symTabEntry.getEndPos()+1) + "</pre>";
//
//      storyboard.add(methodText);

      //============================================================
      storyboard.add("7. generate creator classes", 
         IMPLEMENTATION, "zuendorf joern alex", "25.03.2012 22:32:42", 1, 0);

      storyboard.add("<a href='../examplehelpers/org/sdmlib/examples/studyright/creators/StudentCreator.java'>StudentCreator.java</a><br>");

      //============================================================
      storyboard.add("8. generate imports", 
         IMPLEMENTATION, "zuendorf", "25.03.2012 22:37:42", 1, 0);

      pos = parser.indexOf(Parser.IMPORT);
      String methodText = parser.getText().substring(pos, parser.getEndOfImports() + 1);

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

      SymTabEntry symTabEntry = parser.getSymTab().get(Parser.METHOD + ":removeYou()");

      storyboard.assertNotNull("found symtab entry for method removeYou() ", symTabEntry);

      methodText = "<pre>   " + parser.getText().substring(symTabEntry.getStartPos(), symTabEntry.getEndPos()+1) + "</pre>";

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

      ClassModel model = new ClassModel("org.sdmlib.test.examples.studyright.model");

      Clazz profClass = model.createClazz("Professor")
      .withAttribute("name", DataType.STRING);

      Clazz topicClass = model.createClazz("Topic")
      .withAttribute("title", DataType.STRING);

      profClass.withAssoc(topicClass, "topic", Card.ONE, "prof", Card.ONE);

      storyboard.addClassDiagram(model);

      model.generate("src/test/java");

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

