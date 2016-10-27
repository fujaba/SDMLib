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

import javax.management.relation.Role;

import org.junit.Test;
import org.sdmlib.codegen.Parser;
import org.sdmlib.codegen.SymTabEntry;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.logic.GenClass;
import org.sdmlib.models.classes.logic.GenClazzEntity;
import org.sdmlib.serialization.PropertyChangeInterface;
import org.sdmlib.storyboards.Storyboard;

import de.uniks.networkparser.graph.Association;
import de.uniks.networkparser.graph.AssociationTypes;
import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;
import de.uniks.networkparser.graph.Method;
import de.uniks.networkparser.graph.Parameter;

public class StudyRightModel implements PropertyChangeInterface 
{
   /**
    * 
    * @see <a href='../../../../../../../../doc/StudyRightReverseClassModel.html'>StudyRightReverseClassModel.html</a>
    */
   @Test
   public void testStudyRightReverseClassModel()
   {

      Storyboard storyboard = new Storyboard();

      storyboard.add("Start situation: There are some java files. We parse them and generate a class model: ");

      ClassModel model = new ClassModel("org.sdmlib.test.examples.studyright.model");

      Clazz professorClass = model.createClazz("org.sdmlib.test.examples.studyright.model.Professor")
      .withAttribute("name", DataType.STRING);
      professorClass.setClassModel(model);

      Clazz topicClass = model.createClazz("org.sdmlib.test.examples.studyright.Topic")
      .withAttribute("title", DataType.STRING); 

      new Association(professorClass).with("prof").with(Cardinality.ONE)
      	.with(new Association(topicClass).with("topic").with(Cardinality.ONE));
      
      Clazz roomClass = model.createClazz("org.sdmlib.test.examples.studyright.model.Room")
      .withAttribute("roomNo", DataType.STRING)
      .withAttribute("credits", DataType.INT);

      new Method("findPath", new Parameter(DataType.STRING), new Parameter(DataType.INT))
         .with(roomClass);

      new Association(roomClass).with("neighbors").with(Cardinality.MANY)
      		.with(new Association(roomClass).with("neighbors").with(Cardinality.MANY));

      Clazz studentClass = model.createClazz("org.sdmlib.test.examples.studyright.model.Student")
      .withAttribute("name", DataType.STRING)
      .withAttribute("matrNo", DataType.INT);

      new Association(roomClass).with("in").with(Cardinality.ONE)
      	.with(new Association(studentClass).with("students").with(Cardinality.MANY));

      Clazz universityClass = model.createClazz("org.sdmlib.test.examples.studyright.model.University")
      .withAttribute("name", DataType.STRING);

      new Association(roomClass).with("rooms").with(Cardinality.MANY)
      	.with(new Association(universityClass).with("uni").with(Cardinality.ONE));

      new Association(studentClass).with("students").with(Cardinality.MANY)
      	.with(new Association(universityClass).with("uni").with(Cardinality.ONE));

      // model.getGenerator().updateFromCode("examples test src", "org.sdmlib.test.examples.studyright");

      // model.insertModelCreationCodeHere("examples");

      storyboard.addClassDiagram(model);

      storyboard.dumpHTML();
   }

     /**
    * 
    * @see <a href='../../../../../../../../doc/StudyRightExtendsReverseClassModel.html'>StudyRightExtendsReverseClassModel.html</a>
*/
//   @Test
   public void testStudyRightExtendsReverseClassModel()
   {

      Storyboard storyboard = new Storyboard();

      storyboard.add("Start situation: There are some java files. We parse them and generate a class model: ");

      ClassModel model = new ClassModel("org.sdmlib.test.examples.studyright.model");

      Clazz lectureClass = model.createClazz("org.sdmlib.test.examples.studyright.model.Lecture")
      .withAttribute("Title", DataType.STRING);
      lectureClass.setClassModel(model);

      Clazz personClass = model.createClazz("org.sdmlib.test.examples.studyright.model.Person")
      .enableInterface();

      new Method("findMyPosition")
      .with(personClass);

      new Method("findMyPosition", new Parameter(DataType.STRING)).with(personClass);

      new Method("findMyPosition", new Parameter(DataType.STRING), new Parameter(DataType.INT))
            .with(personClass);
      
      Clazz roomClass = model.createClazz("org.sdmlib.test.examples.studyright.model.Room")
      .withAttribute("roomNo", DataType.STRING)
      .withAttribute("credits", DataType.INT);

      new Method("studentCount").with(roomClass).with(DataType.INT);

      new Association(roomClass).with("neighbors").with(Cardinality.MANY)
      	.with(new Association(roomClass).with("neighbors").with(Cardinality.MANY));

      new Association(lectureClass).with("lecture").with(Cardinality.MANY)
      	.with(new Association(roomClass).with("in").with(Cardinality.ONE));

      Clazz universityClass = model.createClazz("org.sdmlib.test.examples.studyright.model.University")
      .withAttribute("name", DataType.STRING);

      new Association(roomClass).with("rooms").with(Cardinality.MANY)
      	.with(new Association(universityClass).with("uni").with(Cardinality.ONE));

      Clazz femaleClass = model.createClazz("org.sdmlib.test.examples.studyright.model.Female")
      .withSuperClazz(personClass)
      .withAttribute("name", DataType.STRING);

      new Method("findMyPosition").with(femaleClass);

      new Method("findMyPosition", new Parameter(DataType.STRING)).with(femaleClass);

      new Method("findMyPosition", new Parameter(DataType.STRING), new Parameter(DataType.INT))
         .with(femaleClass);

      Clazz maleClass = model.createClazz("org.sdmlib.test.examples.studyright.model.Male")
      .enableInterface()
      .withSuperClazz(personClass);

      Clazz professorClass = model.createClazz("org.sdmlib.test.examples.studyright.model.Professor")
      .withSuperClazz(femaleClass)
      .withAttribute("PersNr", DataType.INT);

      new Association(lectureClass).with("lecture").with(Cardinality.MANY)
      	.with(new Association(professorClass).with("has").with(Cardinality.ONE));

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

      new Association(lectureClass).with("lecture").with(Cardinality.MANY)
      	.with(new Association(studentClass).with("listen").with(Cardinality.ONE));


      // model.updateFromCode("examples", "examples test src", "org.sdmlib.test.examples.studyrightextends");

      // model.insertModelCreationCodeHere("examples");
      
      // model.removeAllGeneratedCode("examples", "examples", "examplehelpers");

      model.generate("src/test/java");

      storyboard.addClassDiagram(model);

      storyboard.dumpHTML();
   }

     /**
    * 
    * @see <a href='../../../../../../../../doc/StudyRightClassesCodeGen.html'>StudyRightClassesCodeGen.html</a>
*/
   @Test
   public void testStudyRightClassesCodeGen()
   {
      Storyboard storyboard = new Storyboard();

      //============================================================
      storyboard.add("1. generate class University");

      ClassModel model = new ClassModel("org.sdmlib.test.examples.studyright.model");

      Clazz uniClass = model.createClazz("University")
      .withAttribute("name", DataType.STRING);

      storyboard.addClassDiagram(model);


      //============================================================
      storyboard.add("2. generate class Student with new notation");

      Clazz studClass = model.createClazz("Student")
      .withAttribute("name", DataType.STRING)
      .withAttribute("matrNo", DataType.INT);
      studClass.setClassModel(model);

      storyboard.addClassDiagram(model);


      //============================================================
      storyboard.add("3. add uni --> stud assoc");

      Association uniToStud = new Association(uniClass).with("uni").with(Cardinality.ONE)
    	  .with(new Association(studClass).with("students").with(Cardinality.MANY)); 

      storyboard.addClassDiagram(model);


      //============================================================
      storyboard.add("4. add uni --> room");

      Clazz roomClass = model.createClazz("Room")
      .withAttribute("roomNo", DataType.STRING)
      .withAttribute("credits", DataType.INT);

      new Method("findPath", new Parameter(DataType.STRING), new Parameter(DataType.INT))
         .with(roomClass);

      Association uniToRoom = new Association(uniClass).with("uni").with(Cardinality.ONE).with(AssociationTypes.AGGREGATION)
    	  .with(new Association(roomClass).with("rooms").with(Cardinality.MANY)); 

      Association doors = new Association(roomClass).with("neighbors").with(Cardinality.MANY)
			.with(new Association(roomClass).with("neighbors").with(Cardinality.MANY));

      Association studsInRoom = new Association(studClass).with("students").with(Cardinality.MANY)
    	  .with(new Association(roomClass).with("in").with(Cardinality.ONE));

      storyboard.addClassDiagram(model);

      
      //============================================================
      storyboard.add("add assignments:");
      
      Clazz assignmentClass = model.createClazz("Assignment")
            .withBidirectional(roomClass, "assignments", Cardinality.MANY, "room", Cardinality.ONE)
            .withAttribute("name", DataType.STRING)
            .withAttribute("points", DataType.INT);

//      Clazz assignmentClass = roomClass.createClassAndAssoc("Assignment", "assignments", Cardinality.MANY, "room", Cardinality.ONE)
//            .withAttributes("name", DataType.STRING, "points", DataType.INT);
      
      studClass.withBidirectional(assignmentClass, "done", Cardinality.MANY, "students", Cardinality.ONE)
          .withAttribute("credits", DataType.INT)
           .withAttribute("motivation", DataType.INT);

      storyboard.addClassDiagram(model);

      
      //============================================================
      model.generate("src/test/java");

      storyboard.add("5. generate generic set for attributes and assocs");

      GenClazzEntity genCLazz = model.getGenerator().getOrCreate(studClass);
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
      storyboard.add("6. generate generic get for attributes and assocs");

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
      storyboard.add("7. generate creator classes");

      storyboard.add("<a href='../examplehelpers/org/sdmlib/examples/studyright/creators/StudentCreator.java'>StudentCreator.java</a><br>");

      //============================================================
      storyboard.add("8. generate imports");

      pos = parser.indexOf(Parser.IMPORT);
      String methodText = parser.getText().substring(pos, parser.getEndOfImports() + 1);

      storyboard.add(methodText);

      //============================================================
      storyboard.add("9. generate property change support");      

      storyboard.add("Caution: property change support needs not to be generated if the parent class does this already.");

      //============================================================
      storyboard.add("10. generate removeYou method");

      pos = parser.indexOf(Parser.METHOD + ":removeYou()");

      storyboard.assertTrue("found method removeYou) in class student", pos >= 0);

      SymTabEntry symTabEntry = parser.getSymTab().get(Parser.METHOD + ":removeYou()");

      storyboard.assertNotNull("found symtab entry for method removeYou() ", symTabEntry);

      methodText = "<pre>   " + parser.getText().substring(symTabEntry.getStartPos(), symTabEntry.getEndPos()+1) + "</pre>";

      storyboard.add(methodText);


      //============================================================
      storyboard.add("Alexander Jahl has added some support for inheritance. See StudyRightExtendsReverseClassModel");


      storyboard.add("generic set now works for double. Perhabs boolean and other are still missing");


      //============================================================
      storyboard.add("Solved: one to one assoc generate code that compiles. Also solved some import problems with ModelSets");

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

      storyboard.dumpHTML();
   }

     /**
    * 
    * @see <a href='../../../../../../../../doc/StudyRightOneToOneAssoc.html'>StudyRightOneToOneAssoc.html</a>
*/
   @Test
   public void testStudyRightOneToOneAssoc()
   {
      Storyboard storyboard = new Storyboard();

      //============================================================
      storyboard.add("Add class Prof --gives-- Topic");

      ClassModel model = new ClassModel("org.sdmlib.test.examples.studyright.model");

      Clazz profClass = model.createClazz("Professor")
      .withAttribute("name", DataType.STRING);

      Clazz topicClass = model.createClazz("Topic")
      .withAttribute("title", DataType.STRING);

      profClass.withBidirectional(topicClass, "topic", Cardinality.ONE, "prof", Cardinality.ONE);

      storyboard.addClassDiagram(model);

      model.generate("src/test/java");

      storyboard.add("One to one assocs now work. ");

      storyboard.dumpHTML();
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

