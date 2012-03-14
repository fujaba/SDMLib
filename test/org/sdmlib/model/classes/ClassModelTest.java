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
      
      Clazz modelClass = new Clazz()
      .withName("org.sdmlib.models.classes.ClassModel");
      
      new Attribute()
      .withName("classes")
      .withType("LinkedHashSet<Clazz>");
           
      Clazz clazzClass = new Clazz()
      .withName("org.sdmlib.models.classes.Clazz");
      
      Clazz attributeClass = new Clazz()
      .withName("org.sdmlib.models.classes.Attribute");
      
      new Attribute()
      .withName("initialization")
      .withType("String");
      
      Clazz associationClass = new Clazz()
      .withName("org.sdmlib.models.classes.Association");
      
      new Attribute()
      .withName("source")
      .withType("Role");
      
      new Attribute()
      .withName("target")
      .withType("Role");
      
      new Association()
      .withSource("model", modelClass, Role.ONE, Role.AGGREGATION)
      .withTarget("associations", associationClass, Role.MANY);
      
      Clazz roleClass = new Clazz()
      .withName("org.sdmlib.models.classes.Role");
      
      new Attribute()
      .withName("name")
      .withType("String")
      .withInitialization("null");
      
      new Attribute()
      .withName("clazz")
      .withType("Clazz")
      .withInitialization("null")
      .generate("src");

      new Attribute()
      .withName("card")
      .withType("String")
      .withInitialization("MANY")
      .generate("src");

      new Attribute()
      .withName("kind")
      .withType("String")
      .withInitialization("VANILLA");

      Clazz codeGenUtilClass = new Clazz()
      .withName("org.sdmlib.codegen.CGUtil");
      
      Clazz parserContextClass = new Clazz()
      .withName("org.sdmlib.codegen.Parser");
      
      model.generate("src");
      
      scenario.addImage(model.dumpClassDiag("ClassModelClasses01"));
            
      scenario.addLogEntry(new LogEntry()
      .withDate("11.03.2012 00:27:00")
      .withPhase(ProjectBoard.MODELING)
      .withDeveloper("zuendorf")
      .withHoursSpend(1)
      .withHoursRemainingInTotal(19)
      .withComment("Start with an empty class with just licence text in it."));
      
      ScenarioManager.get()
      .add(scenario)
      .dumpHTML();      
   }

}

