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

import org.junit.Assert;
import org.junit.Test;
import org.sdmlib.codegen.Parser;
import org.sdmlib.codegen.SymTabEntry;
import org.sdmlib.examples.studyright.creators.UniversityCreator;
import org.sdmlib.examples.studyright.modelsets.ModelSet;
import org.sdmlib.models.classes.Association;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Method;
import org.sdmlib.models.classes.Role;
import org.sdmlib.scenarios.Scenario;
import org.sdmlib.scenarios.ScenarioManager;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.utils.PropertyChangeInterface;
import org.sdmlib.utils.PropertyChangeSupport;

import com.sun.tools.javac.Main;

public class StudyRightClassesCodeGen implements PropertyChangeInterface 
{
   @Test
   public void testStudyRightReverseClassModel()
   {
  	 
      Scenario scenario = new Scenario("StudyRightReverseClassModel");

      scenario.add("Start situation: There are some java files. We parse them and generate a class model: ", BACKLOG, "zuendorf", "02.04.2012 14:58:18", 0, 0);

      ClassModel model = new ClassModel();


      Clazz groupAccountClass = new Clazz("org.sdmlib.examples.groupAccount.GroupAccount");

      new Method()
			.withClazz(groupAccountClass)
			.withSignature("updateBalances()");

      Clazz groupAccountTestsClass = new Clazz("org.sdmlib.examples.groupAccount.GroupAccountTests");

      new Method()
			.withClazz(groupAccountTestsClass)
			.withSignature("testGroupAccountRuleRecognition()");

      new Method()
			.withClazz(groupAccountTestsClass)
			.withSignature("testGroupAccountCodegen()");

      
      Clazz personClass = new Clazz("org.sdmlib.examples.groupAccount.Person")
      .withAttribute("name", "String") /* add attribut */
      .withAttribute("balance", "double") /* add attribut */;

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

      new Method()
			.withClazz(studyRightClassesCodeGenClass)
			.withSignature("testStudyRightReverseClassModel()");

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
      
      Clazz itemClass = new Clazz("org.sdmlib.examples.groupAccount.Item")
      .withAttribute("description", "String")
      .withAttribute("value", "double") /* add attribut */;

      model.updateFromCode("examples test src", "org.sdmlib.examples");

      model.insertModelCreationCodeHere("examples");
      
      scenario.addImage(model.dumpClassDiag("StudyRightReverseClassModel"));

      ScenarioManager.get()
      .add(scenario)
      .dumpHTML();
   }

   @Test
   public void testStudyRightObjectScenarios()
   {
      Scenario scenario = new Scenario("StudyRightObjectScenarios");

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
      int sum = ModelSet.startWith(albert).getUni().getRooms().getCredits().sum();
      scenario.addCode("examples");
      
      scenario.add(
            "      shall compute to 88\n" +
            "      Path classes need to be generated.", 
            MODELING, "zuendorf joern alex", "25.03.2012 14:57:42", 0, 0);
      
      Assert.assertEquals("credits sum error", 88, sum);

      ModelSet any = ModelSet.startWith(albert).getAny();

      Assert.assertEquals("wrong number of neighbors for Albert", 2, any.size());

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
      Scenario scenario = new Scenario("StudyRightClassesCodeGen");


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

      scenario.add("<a href='../examples/org/sdmlib/examples/studyright/creators/StudentCreator.java'>StudentCreator.java</a><br>");

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
      scenario.add("We need inheritance", 
         IMPLEMENTATION, "zuendorf", "26.03.2012 22:34:42", 0, 18);

      
      scenario.add("generic set does not work for boolean and double and probably more", 
         IMPLEMENTATION, "zuendorf", "05.04.2012 12:52:42", 0, 1);

      
      
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

   public static final String MODELING = "modeling";
   public static final String ACTIVE = "active";
   public static final String DONE = "done";
   public static final String IMPLEMENTATION = "implementation";
   public static final String BACKLOG = "backlog";


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

