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

import java.beans.PropertyChangeSupport;

import org.junit.Assert;
import org.junit.Test;
import org.sdmlib.codegen.Parser;
import org.sdmlib.codegen.SymTabEntry;
import org.sdmlib.examples.studyright.creators.UniversityCreator;
import org.sdmlib.models.classes.Association;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Method;
import org.sdmlib.models.classes.Role;
import org.sdmlib.scenarios.Scenario;
import org.sdmlib.scenarios.ScenarioManager;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.utils.PropertyChangeInterface;

import com.sun.tools.javac.Main;

public class StudyRightClassesCodeGen implements PropertyChangeInterface 
{
   @Test
   public void testStudyRightReverseClassModel()
   {
  	 
      Scenario scenario = new Scenario("examples", "StudyRightReverseClassModel");

      scenario.add("Start situation: There are some java files. We parse them and generate a class model: ", BACKLOG, "ajahl", "02.04.2012 14:58:18", 0, 0);

      ClassModel model = new ClassModel();

   

      Clazz professorClass = new Clazz("org.sdmlib.examples.studyright.Professor")
      .withAttribute("name", "String");

      Clazz topicClass = new Clazz("org.sdmlib.examples.studyright.Topic")
      .withAttribute("title", "String");

      new Association()
			.withSource("prof", professorClass, "one")
			.withTarget("topic", topicClass, "one");
   Clazz roomClass = new Clazz("org.sdmlib.examples.studyright.Room")
      .withAttribute("roomNo", "String")
      .withAttribute("credits", "int");

      new Method()
			.withClazz(roomClass)
			.withSignature("findPath(String,int)");

      new Association()
			.withSource("neighbors", roomClass, "many")
			.withTarget("neighbors", roomClass, "many");

      Clazz studentClass = new Clazz("org.sdmlib.examples.studyright.Student")
      .withAttribute("name", "String")
      .withAttribute("matrNo", "int");

      new Association()
			.withSource("in", roomClass, "one")
			.withTarget("students", studentClass, "many");

      Clazz studyRightClassesCodeGenClass = new Clazz("org.sdmlib.examples.studyright.StudyRightClassesCodeGen");
      /* add method */
      new Method()
			.withClazz(studyRightClassesCodeGenClass)
			.withSignature("testStudyRightOneToOneAssoc()");

      new Method()
			.withClazz(studyRightClassesCodeGenClass)
			.withSignature("testStudyRightReverseClassModel()");

      new Method()
			.withClazz(studyRightClassesCodeGenClass)
			.withSignature("testStudyRightExtendsReverseClassModel()");

      new Method()
			.withClazz(studyRightClassesCodeGenClass)
			.withSignature("testStudyRightObjectScenarios()");

      new Method()
			.withClazz(studyRightClassesCodeGenClass)
			.withSignature("testStudyRightClassesCodeGen()");

      Clazz universityClass = new Clazz("org.sdmlib.examples.studyright.University")
      .withAttribute("name", "String");

      new Association()
			.withSource("rooms", roomClass, "many")
			.withTarget("uni", universityClass, "one");

      new Association()
			.withSource("students", studentClass, "many")
			.withTarget("uni", universityClass, "one");


      

      model.updateFromCode("examples", "examples test src", "org.sdmlib.examples.studyright");
      
      model.insertModelCreationCodeHere("examples");
      
      scenario.addImage(model.dumpClassDiag("StudyRightReverseClassModel"));

      scenario.add("Bug: running the test multiple times inserts the code multiple times \n" +
      		"Bug solved: there was no bug, there were just two classes with the same name in different packages. \n" +
      		"Hm, might be added to future work. ", DONE, "zuendorf", "19.05.2012 19:39:42", 1, 0);

      
      ScenarioManager.get()
      .add(scenario)
      .dumpHTML();
   }
   
   @Test
   public void testStudyRightExtendsReverseClassModel()
   {
  	 
  	  Scenario scenario = new Scenario("examples", "StudyRightExtendsReverseClassModel");

      scenario.add("Start situation: There are some java files. We parse them and generate a class model: ", BACKLOG, "ajahl", "02.04.2012 14:58:18", 0, 0);

      ClassModel model = new ClassModel();

      Clazz lectureClass = new Clazz("org.sdmlib.examples.studyrightextends.Lecture")
      .withAttribute("Title", "String");

      Clazz personClass = new Clazz("org.sdmlib.examples.studyrightextends.Person")
      .withInterfaze(true);

      new Method()
			.withClazz(personClass)
			.withSignature("findMyPosition()");

      new Method()
			.withClazz(personClass)
			.withSignature("findMyPosition(String)");

      new Method()
			.withClazz(personClass)
			.withSignature("findMyPosition(String,int)");

      new Method()
			.withClazz(personClass)
			.withSignature("getName()");

      new Method()
			.withClazz(personClass)
			.withSignature("setName(String)");

      new Method()
			.withClazz(personClass)
			.withSignature("withName(String)");

      Clazz roomClass = new Clazz("org.sdmlib.examples.studyrightextends.Room")
      .withAttribute("roomNo", "String")
      .withAttribute("credits", "int");

      new Method()
			.withClazz(roomClass)
			.withSignature("studentCount()");

      new Association()
			.withSource("neighbors", roomClass, "many")
			.withTarget("neighbors", roomClass, "many");

      new Association()
			.withSource("lecture", lectureClass, "many")
			.withTarget("in", roomClass, "one");

      Clazz universityClass = new Clazz("org.sdmlib.examples.studyrightextends.University")
      .withAttribute("name", "String");

      new Association()
			.withSource("rooms", roomClass, "many")
			.withTarget("uni", universityClass, "one");

      Clazz studyRightClassesCodeGenClass = new Clazz("org.sdmlib.examples.studyright.StudyRightClassesCodeGen");
      /* add method */
      new Method()
			.withClazz(studyRightClassesCodeGenClass)
			.withSignature("testStudyRightOneToOneAssoc()");
      /* add method */
      new Method()
			.withClazz(studyRightClassesCodeGenClass)
			.withSignature("testStudyRightClassesCodeGen()");
      /* add method */
      new Method()
			.withClazz(studyRightClassesCodeGenClass)
			.withSignature("testStudyRightObjectScenarios()");
      /* add method */
      new Method()
			.withClazz(studyRightClassesCodeGenClass)
			.withSignature("testStudyRightExtendsReverseClassModel()");
      /* add method */
      new Method()
			.withClazz(studyRightClassesCodeGenClass)
			.withSignature("testStudyRightReverseClassModel()");

      Clazz femaleClass = new Clazz("org.sdmlib.examples.studyrightextends.Female")
      .withInterfaces(personClass)
      .withAttribute("name", "String");

      new Method()
			.withClazz(femaleClass)
			.withSignature("findMyPosition()");

      new Method()
			.withClazz(femaleClass)
			.withSignature("findMyPosition(String)");

      new Method()
			.withClazz(femaleClass)
			.withSignature("findMyPosition(String,int)");

      Clazz maleClass = new Clazz("org.sdmlib.examples.studyrightextends.Male")
      .withInterfaze(true)
      .withInterfaces(personClass);

      Clazz professorClass = new Clazz("org.sdmlib.examples.studyrightextends.Professor")
      .withSuperClass(femaleClass)
      .withAttribute("PersNr", "int");

      new Association()
			.withSource("lecture", lectureClass, "many")
			.withTarget("has", professorClass, "one");

      Clazz studentClass = new Clazz("org.sdmlib.examples.studyrightextends.Student")
      .withInterfaces(maleClass)
      .withAttribute("name", "String")
      .withAttribute("matrNo", "int");

      new Method()
			.withClazz(studentClass)
			.withSignature("findMyPosition()");

      new Method()
			.withClazz(studentClass)
			.withSignature("findMyPosition(String)");

      new Method()
			.withClazz(studentClass)
			.withSignature("findMyPosition(String,int)");

      new Association()
			.withSource("lecture", lectureClass, "many")
			.withTarget("listen", studentClass, "one");


      

      model.updateFromCode("examples", "examples test src", "org.sdmlib.examples.studyrightextends");
      
      model.insertModelCreationCodeHere("examples");
      
      scenario.addImage(model.dumpClassDiag("StudyRightExtendsReverseClassModel"));

      ScenarioManager.get()
      .add(scenario)
      .dumpHTML();
   }

   @Test
   public void testStudyRightObjectScenarios()
   {
      Scenario scenario = new Scenario("examples", "StudyRightObjectScenarios");

      scenario.add("Start situation: use University class to build object structure",
         BACKLOG, "zuendorf", "25.03.2012 21:37:46", 0, 0);

      University uni = new University()
      .withName("StudyRight");

      Student albert = new Student()
      .withMatrNo(4242)
      .withName("Albert")
      .withUni(uni);

      Student nina = new Student()
      .withMatrNo(2323)
      .withName("Nina")
      .withUni(uni);

      Room mathRoom = new Room()
      .withRoomNo("math")
      .withCredits(42)  
      .withStudents(albert)
      .withUni(uni); 

      Room artsRoom = new Room()
      .withRoomNo("arts")
      .withCredits(23)
      .withNeighbors(mathRoom)
      .withUni(uni); 

      Room sportsRoom = new Room()
      .withRoomNo("sports")
      .withCredits(23)
      .withNeighbors(mathRoom)
      .withNeighbors(artsRoom)
      .withUni(uni); 

      Room examRoom = new Room()
      .withRoomNo("exam")
      .withCredits(0)
      .withNeighbors(sportsRoom)
      .withNeighbors(artsRoom)
      .withUni(uni);
      
      scenario.add("step 1: dump object diagram");

      JsonIdMap idMap = UniversityCreator.createIdMap("ajz");
      scenario.addObjectDiag(idMap, uni);

      Assert.assertEquals("false number of students:" , 2, uni.getStudents().size()); 

      scenario.add("step 2: add support for path navigation\n      call ");

      scenario.markCodeStart();
      int sum = albert.getUni().getRooms().getCredits().sum();
      scenario.addCode("examples");
      
      scenario.add(
            "      shall compute to 88\n" +
            "      Path classes are generated.", 
            DONE, "zuendorf joern alex", "19.05.2012 20:40:42", 1, 0);
      
      Assert.assertEquals("credits sum error", 88, sum);

      scenario.add( "Feature Request: model sets need to provide a navigation to any neighbors\n" +
      		"e.g.: ModelSet any = ModelSet.startWith(albert).getAny(); ", 
         BACKLOG, "zuendorf", "19.05.2012 20:42:42", 0, 2);
   

      // Assert.assertEquals("wrong number of neighbors for Albert", 2, any.size());

      scenario.add("step 3: call ");
      
      scenario.recordSystemOut();
      
      scenario.markCodeStart();
      mathRoom.findPath("", 88);
      scenario.addCode("examples");
      
      scenario.add("System.out: \n" + scenario.getSystemOut());


      ScenarioManager.get()
      .add(scenario)
      .dumpHTML();
   }

   @Test
   public void testStudyRightClassesCodeGen()
   {
      Scenario scenario = new Scenario("examples", "StudyRightClassesCodeGen");


      //============================================================
      scenario.add("1. generate class University");

      ClassModel model = new ClassModel();

      Clazz uniClass = new Clazz("org.sdmlib.examples.studyright.University")
      .withAttribute("name", "String");

      scenario.addImage(model.dumpClassDiag("StudyRightClasses01"));


      //============================================================
      scenario.add("2. generate class Student with new notation", 
         IMPLEMENTATION, "zuendorf", "18.03.2012 23:05:42", 1, 0);

      Clazz studClass = new Clazz("org.sdmlib.examples.studyright.Student")
      .withAttribute("name", "String")
      .withAttribute("matrNo", "int");

      scenario.addImage(model.dumpClassDiag("StudyRightClasses02"));


      //============================================================
      scenario.add("3. add uni --> stud assoc");

      Association uniToStud = new Association()
      .withSource("uni", uniClass, Role.ONE)
      .withTarget("students", studClass, Role.MANY); 

      scenario.addImage(model.dumpClassDiag("StudyRightClasses03"));


      //============================================================
      scenario.add("4. add uni --> room");

      Clazz roomClass = new Clazz("org.sdmlib.examples.studyright.Room")
      .withAttribute("roomNo", "String")
      .withAttribute("credits", "int");

      Association uniToRoom = new Association()
      .withSource("uni", uniClass, Role.ONE, Role.AGGREGATION)
      .withTarget("rooms", roomClass, Role.MANY); 

      Association doors = new Association().withSource("neighbors", roomClass, Role.MANY)
            .withTarget("neighbors", roomClass, Role.MANY);

      Association studsInRoom = new Association()
      .withSource("students", studClass, Role.MANY)
      .withTarget("in", roomClass, Role.ONE);

      scenario.addImage(model.dumpClassDiag("StudyRightClasses04"));

      //============================================================
      model.generate("examples", "examplehelpers");

      scenario.add("5. generate generic set for attributes and assocs", 
         IMPLEMENTATION, "zuendorf", "18.03.2012 23:05:42", 1, 0);

      Parser parser = studClass.getOrCreateParser("examples");
      
      int pos = parser.indexOf(Parser.METHOD + ":set(String,Object)");

      Assert.assertTrue("did not find method set(String,Object) in class student", pos >= 0);

      SymTabEntry symTabEntry = parser.getSymTab().get(Parser.METHOD + ":set(String,Object)");

      Assert.assertNotNull("did not find symtab entry for method set(String,Object)", symTabEntry);

      String methodText = "   " + parser.getFileBody().substring(symTabEntry.getStartPos(), symTabEntry.getEndPos()+1);

      scenario.add(methodText);


      //============================================================
      scenario.add("6. generate generic get for attributes and assocs", 
         IMPLEMENTATION, "zuendorf", "22.03.2012 14:40:42", 1, 0);

      pos = parser.indexOf(Parser.METHOD + ":get(String)");

      Assert.assertTrue("did not find method get(String) in class student", pos >= 0);

      symTabEntry = parser.getSymTab().get(Parser.METHOD + ":get(String)");

      Assert.assertNotNull("did not find symtab entry for method get(String)", symTabEntry);

      methodText = "   " + parser.getFileBody().substring(symTabEntry.getStartPos(), symTabEntry.getEndPos()+1);

      scenario.add(methodText);

      //============================================================
      scenario.add("7. generate creator classes", 
         IMPLEMENTATION, "zuendorf joern alex", "25.03.2012 22:32:42", 1, 0);

      scenario.add("<a href='../examplehelpers/org/sdmlib/examples/studyright/creators/StudentCreator.java'>StudentCreator.java</a><br>");

      //============================================================
      scenario.add("8. generate imports", 
         IMPLEMENTATION, "zuendorf", "25.03.2012 22:37:42", 1, 0);

      pos = parser.indexOf(Parser.IMPORT);
      methodText = parser.getFileBody().substring(pos, parser.getEndOfImports() + 1);

      scenario.add(methodText);

      //============================================================
      scenario.add("9. generate property change support", 
         IMPLEMENTATION, "zuendorf", "25.03.2012 22:39:42", 2, 0);      

      scenario.add("Caution: property change support needs not to be generated if the parent class does this already.");

      //============================================================
      scenario.add("10. generate removeYou method", 
         IMPLEMENTATION, "zuendorf", "26.03.2012 22:20:42", 2, 0);

      pos = parser.indexOf(Parser.METHOD + ":removeYou()");

      Assert.assertTrue("did not find method removeYou) in class student", pos >= 0);

      symTabEntry = parser.getSymTab().get(Parser.METHOD + ":removeYou()");

      Assert.assertNotNull("did not find symtab entry for method removeYou()", symTabEntry);

      methodText = "   " + parser.getFileBody().substring(symTabEntry.getStartPos(), symTabEntry.getEndPos()+1);

      scenario.add(methodText);


      //============================================================
      scenario.add("Alexander Jahl has added some support for inheritance. See StudyRightExtendsReverseClassModel", 
         IMPLEMENTATION, "zuendorf", "19.05.2012 19:59:42", 1, 0);

      
      scenario.add("generic set now works for double. Perhabs boolean and other are still missing", 
         IMPLEMENTATION, "zuendorf", "19.05.2012 13:51:42", 1, 0);


      //============================================================
      scenario.add("Solved: one to one assoc generate code that compiles. Also solved some import problems with ModelSets", 
         DONE, "zuendorf", "20.05.2012 20:01:42", 2, 0);
      
      scenario.add("next. compile University.java");

      String javaClassPath = System.getProperty("java.class.path");

      String[] compArgs = new String[]
            {
            "-d", "bin",
            "-sourcepath", "examples",
            "-classpath", javaClassPath,
            "examples/org/sdmlib/examples/studyright/University.java"
            };

      int compResult = Main.compile(compArgs);

      Assert.assertEquals("compile did not work: ", 0, compResult);

      ScenarioManager.get()
      .add(scenario)
      .dumpHTML();
   }
   
   @Test
   public void testStudyRightOneToOneAssoc()
   {
      Scenario scenario = new Scenario("examples", "StudyRightOneToOneAssoc");
      
    //============================================================
      scenario.add("Add class Prof --gives-- Topic");
      
      ClassModel model = new ClassModel();
      
      Clazz profClass = new Clazz("org.sdmlib.examples.studyright.Professor")
      .withAttribute("name", "String");

      Clazz topicClass = new Clazz("org.sdmlib.examples.studyright.Topic")
      .withAttribute("title", "String");
      
      new Association()
      .withSource("prof", profClass, Role.ONE)
      .withTarget("topic", topicClass, Role.ONE);

      scenario.addImage(model.dumpClassDiag("StudyRightOneToOneAssoc01"));
      
      model.generate("examples", "examplehelpers");

      scenario.add("One to one assocs now work. ", DONE, "zuendorf", "20.05.2012 15:19:42", 1, 0);
      
      ScenarioManager.get()
      .add(scenario)
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
}

