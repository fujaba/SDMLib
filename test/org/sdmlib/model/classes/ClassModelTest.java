/*
   Copyright (c) 2012 Albert Z�ndorf

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

import org.junit.Test;
import org.sdmlib.kanban.ProjectBoard;
import org.sdmlib.models.classes.Association;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Role;
import org.sdmlib.scenarios.Scenario;
import org.sdmlib.scenarios.ScenarioManager;

public class ClassModelTest
{
   @Test
   public void testClassModelCodeGen()
   {
      Scenario scenario = new Scenario("test", "ClassModelCodeGen");

      //=======================================================================
      scenario.add("This test generates some classes with some elements. ");


      //=======================================================================
      scenario.add("We start by bootstrapping org.sdmlib.model.classes.ClassModel. ");

      ClassModel model = new ClassModel();
      
      Clazz modelClass = new Clazz("org.sdmlib.models.classes.ClassModel");
           
      Clazz clazzClass = new Clazz("org.sdmlib.models.classes.Clazz")
      .withAttribute("name", "String")
      .withAttribute("interfaze", "Boolean", "false");
      
      new Association()
      .withSource("classModel", modelClass, Role.ONE, Role.AGGREGATION)
      .withTarget("classes", clazzClass, Role.MANY);
      
      new Association()
      .withSource("superClass", clazzClass, Role.ONE)
      .withTarget("kindClasses", clazzClass, Role.MANY);
      
      new Association()
      .withSource("interfaces", clazzClass, Role.MANY)
      .withTarget("kindClassesAsInterface", clazzClass, Role.MANY);     
      
      Clazz attributeClass = new Clazz("org.sdmlib.models.classes.Attribute")
      .withAttribute("initialization", "String");
      
      new Association()
      .withSource("clazz", clazzClass, Role.ONE, Role.AGGREGATION)
      .withTarget("attributes", attributeClass, Role.MANY);
      
      
      Clazz methodClass = new Clazz("org.sdmlib.models.classes.Method")
      .withAttribute("signature", "String")
      .withAttribute("returnType", "String");     
      
      new Association()
      .withSource("clazz", clazzClass, Role.ONE, Role.AGGREGATION)
      .withTarget("methods", methodClass, Role.MANY);
      
      
      Clazz associationClass = new Clazz("org.sdmlib.models.classes.Association");
      
      new Association()
      .withSource("model", modelClass, Role.ONE, Role.AGGREGATION)
      .withTarget("associations", associationClass, Role.MANY);
      
      
      Clazz roleClass = new Clazz("org.sdmlib.models.classes.Role")
      .withAttribute("name", "String")
      .withAttribute("card", "String", "MANY")
      .withAttribute("kind", "String", "VANILLA");
      
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
      
      Clazz symTabEntryClass = new Clazz("org.sdmlib.codegen.SymTabEntry")
      .withAttribute("kind", "String")
      .withAttribute("memberName", "String")
      .withAttribute("type", "String")
      .withAttribute("startPos", "int")
      .withAttribute("bodyStartPos", "int")
      .withAttribute("endPos", "int")
      .withAttribute("modifiers", "String");
      
      Clazz localVarTableEntryClass = new Clazz("org.sdmlib.codegen.LocalVarTableEntry")
      .withAttribute("name", "String")
      .withAttribute("type", "String")
      .withAttribute("startPos", "int")
      .withAttribute("endPos", "int");
      // .withAttribute("initSequence", "ArrayList<ArrayList<String>>");
      
      
      Clazz statementEntry =  new Clazz("org.sdmlib.codegen.StatementEntry")
      .withAttribute("kind", "String")
      .withAttribute("tokenList", "ArrayList<String>")
      .withAttribute("assignTargetVarName", "String");
      
      new Association()
      .withSource("parent", statementEntry, Role.ONE)
      .withTarget("bodyStats", statementEntry, Role.MANY);
      
      
      scenario.add("Basic bootstrap done.", 
         ProjectBoard.IMPLEMENTATION, "zuendorf", "18.03.2012 23:35:42", 1, 0);
      
      scenario.addImage(model.dumpClassDiag("ClassModelClasses01"));
      
      scenario.add("Generate generic get and set and removeYou.", 
         ProjectBoard.IMPLEMENTATION, "zuendorf", "19.03.2012 00:19:42", 1, 0);

      scenario.add("Generate creator classes.", 
         ProjectBoard.IMPLEMENTATION, "zuendorf", "26.03.2012 22:54:42", 1, 0);

      scenario.add("Generate PatternObject classes.", 
         ProjectBoard.IMPLEMENTATION, "zuendorf", "20.07.2012 16:56:42", 1, 8);

      
      model.generate("src", "srcHelpers");
      
      ScenarioManager.get()
      .add(scenario)
      .dumpHTML();      
   }

}

