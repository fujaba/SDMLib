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

package org.sdmlib.model.classes;

import java.util.LinkedHashSet;

import org.junit.Test;
import org.sdmlib.kanban.ProjectBoard;
import org.sdmlib.models.classes.Association;
import org.sdmlib.models.classes.Attribute;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Role;
import org.sdmlib.scenarios.LogEntry;
import org.sdmlib.scenarios.Scenario;
import org.sdmlib.scenarios.ScenarioManager;

public class ClassModelTest
{
   @Test
   public void testClassModelCodeGen()
   {
      Scenario scenario = new Scenario("ClassModelCodeGen");
      
      scenario.add("This tests generates some classes with some elements. ");
      
      scenario.add("We start by bootstrapping org.sdmlib.model.classes.ClassModel. ");
      
      ClassModel model = new ClassModel();
      
      //=======================================================================
      Clazz symTabEntryClass = new Clazz()
      .withName("org.sdmlib.codegen.SymTabEntry");
      
      new Attribute().withName("kind").withType("String");
      new Attribute().withName("memberName").withType("String");
      new Attribute().withName("type").withType("String");
      new Attribute().withName("startPos").withType("int");
      new Attribute().withName("bodyStartPos").withType("int");
      new Attribute().withName("endPos").withType("int");
      
      //=======================================================================
      Clazz modelClass = new Clazz()
      .withName("org.sdmlib.models.classes.ClassModel");
           
      //=======================================================================
      Clazz clazzClass = new Clazz()
      .withName("org.sdmlib.models.classes.Clazz");
      
      new Attribute().withName("name").withType("String");
      
      new Association()
      .withSource("classModel", modelClass, Role.ONE, Role.AGGREGATION)
      .withTarget("classes", clazzClass, Role.MANY);
      
      //=======================================================================
      Clazz attributeClass = new Clazz()
      .withName("org.sdmlib.models.classes.Attribute");
      
      new Attribute().withName("initialization").withType("String");
      
      new Association()
      .withSource("clazz", clazzClass, Role.ONE, Role.AGGREGATION)
      .withTarget("attributes", attributeClass, Role.MANY);
      
      
    //=======================================================================
      Clazz methodClass = new Clazz()
      .withName("org.sdmlib.models.classes.Method");
      
      new Attribute().withName("signature").withType("String");
      
      
      new Association()
      .withSource("clazz", clazzClass, Role.ONE, Role.AGGREGATION)
      .withTarget("methods", methodClass, Role.MANY);
      
      //=======================================================================
      Clazz associationClass = new Clazz()
      .withName("org.sdmlib.models.classes.Association");
      
      new Association()
      .withSource("model", modelClass, Role.ONE, Role.AGGREGATION)
      .withTarget("associations", associationClass, Role.MANY);
      
      //=======================================================================
      Clazz roleClass = new Clazz()
      .withName("org.sdmlib.models.classes.Role");
      
      new Attribute().withName("name").withType("String").withInitialization("null");
      
      new Attribute().withName("card").withType("String").withInitialization("MANY");

      new Attribute().withName("kind").withType("String").withInitialization("VANILLA");
      
      new Association()
      .withSource("clazz", clazzClass, Role.ONE)
      .withTarget("sourceRoles", roleClass, Role.MANY);
      
      new Association()
      .withSource("clazz", clazzClass, Role.ONE)
      .withTarget("targetRoles", roleClass, Role.MANY);

      new Association()
      .withSource("assoc", associationClass, Role.ONE)
      .withTarget("source", roleClass, Role.ONE);

      new Association()
      .withSource("assoc", associationClass, Role.ONE)
      .withTarget("target", roleClass, Role.ONE);

      //      //=======================================================================
      //      Clazz codeGenUtilClass = new Clazz()
      //      .withName("org.sdmlib.codegen.CGUtil");
      //      
      //      //=======================================================================
      //      Clazz parserContextClass = new Clazz()
      //      .withName("org.sdmlib.codegen.Parser");
      
      
      
      model.generate("src");
      
      scenario.addImage(model.dumpClassDiag("ClassModelClasses01"));
            
      scenario.addLogEntry(new LogEntry()
      .withDate("16.03.2012 17:19:00")
      .withPhase(ProjectBoard.MODELING)
      .withDeveloper("zuendorf")
      .withHoursSpend(2)
      .withHoursRemainingInTotal(19)
      .withComment("Added SymTabEntry class."));
      
      ScenarioManager.get()
      .add(scenario)
      .dumpHTML();      
   }

}

