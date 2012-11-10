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
import static org.sdmlib.models.classes.Role.R.*;
import org.sdmlib.scenarios.Scenario;
import org.sdmlib.scenarios.ScenarioManager;

public class ClassModelTest
{
   private static final String STRING = "String";
   private static final String INT = "int";
   private static final String BOOLEAN = "boolean";

   @Test
   public void testClassModelCodeGen()
   {
      Scenario scenario = new Scenario("test", "ClassModelCodeGen");

      //=======================================================================
      scenario.add("This test generates some classes with some elements. ");


      //=======================================================================
      scenario.add("We start by bootstrapping org.sdmlib.model.classes.ClassModel. ");

      ClassModel model = new ClassModel();
      
      Clazz modelClass = new Clazz("org.sdmlib.models.classes.ClassModel",
    		  "packageName", String.class.getSimpleName());
           
      Clazz clazzClass = new Clazz("org.sdmlib.models.classes.Clazz",
         "name", String.class.getSimpleName(),
         "interfaze", boolean.class.getSimpleName(), 
         "external", boolean.class.getSimpleName(), 
         "wrapped", BOOLEAN);
      
      new Association()
      .withSource("classModel", modelClass, ONE, Role.AGGREGATION)
      .withTarget("classes", clazzClass, MANY);
      
      new Association()
      .withSource("superClass", clazzClass, ONE)
      .withTarget("kindClasses", clazzClass, MANY);
      
      new Association()
      .withSource("interfaces", clazzClass, MANY)
      .withTarget("kindClassesAsInterface", clazzClass, MANY);     
      
      Clazz attributeClass = new Clazz("org.sdmlib.models.classes.Attribute")
      .withAttribute("initialization", STRING);
      
      new Association()
      .withSource("clazz", clazzClass, ONE, Role.AGGREGATION)
      .withTarget("attributes", attributeClass, MANY);
      
      
      Clazz methodClass = new Clazz("org.sdmlib.models.classes.Method")
      .withAttribute("signature", STRING)
      .withAttribute("returnType", STRING);     
      
      new Association()
      .withSource("clazz", clazzClass, ONE, Role.AGGREGATION)
      .withTarget("methods", methodClass, MANY);
      
      
      Clazz associationClass = new Clazz("org.sdmlib.models.classes.Association");
      
      new Association()
      .withSource("model", modelClass, ONE, Role.AGGREGATION)
      .withTarget("associations", associationClass, MANY);
      
      
      Clazz roleClass = new Clazz("org.sdmlib.models.classes.Role")
      .withAttribute("name", STRING)
      .withAttribute("card", STRING, "MANY")
      .withAttribute("kind", STRING, "VANILLA");
      
      new Association()
      .withSource("clazz", clazzClass, ONE)
      .withTarget("sourceRoles", roleClass, MANY);
      
      new Association()
      .withSource("clazz", clazzClass, ONE)
      .withTarget("targetRoles", roleClass, MANY);

      new Association()
      .withSource("assoc", associationClass, ONE)
      .withTarget("source", roleClass, ONE);

      new Association()
      .withSource("assoc", associationClass, ONE)
      .withTarget("target", roleClass, ONE);

      //      //=======================================================================
      //      Clazz codeGenUtilClass = new Clazz()
      //      .withName("org.sdmlib.codegen.CGUtil");
      //      
      //      //=======================================================================
      //      Clazz parserContextClass = new Clazz()
      //      .withName("org.sdmlib.codegen.Parser");
      
      Clazz symTabEntryClass = new Clazz("org.sdmlib.codegen.SymTabEntry")
      .withAttribute("kind", STRING)
      .withAttribute("memberName", STRING)
      .withAttribute("type", STRING)
      .withAttribute("startPos", INT)
      .withAttribute("bodyStartPos", INT)
      .withAttribute("endPos", INT)
      .withAttribute("modifiers", STRING);
      
      Clazz localVarTableEntryClass = new Clazz("org.sdmlib.codegen.LocalVarTableEntry")
      .withAttribute("name", STRING)
      .withAttribute("type", STRING)
      .withAttribute("startPos", INT)
      .withAttribute("endPos", INT);
      // .withAttribute("initSequence", "ArrayList<ArrayList<String>>");
      
      
      Clazz statementEntry =  new Clazz("org.sdmlib.codegen.StatementEntry")
      .withAttributes(
         "kind", STRING,
         "tokenList", "ArrayList<String>", 
         "assignTargetVarName", STRING, 
         "startPos", INT, 
         "endPos", INT);
      
      new Association()
      .withSource("parent", statementEntry, ONE)
      .withTarget("bodyStats", statementEntry, MANY);
      
      
      scenario.add("Basic bootstrap done.", 
         ProjectBoard.IMPLEMENTATION, "zuendorf", "18.03.2012 23:35:42", 1, 0);
      
      scenario.addImage(model.dumpClassDiag("src", "ClassModelClasses01"));
      
      scenario.add("Generate generic get and set and removeYou.", 
         ProjectBoard.IMPLEMENTATION, "zuendorf", "19.03.2012 00:19:42", 1, 0);

      scenario.add("Generate creator classes.", 
         ProjectBoard.IMPLEMENTATION, "zuendorf", "26.03.2012 22:54:42", 1, 0);

      scenario.add("Generate PatternObject classes.", 
         ProjectBoard.IMPLEMENTATION, "zuendorf", "19.08.2012 19:08:42", 8, 0);

      
      model.generate("src", "srcHelpers");
      
      ScenarioManager.get()
      .add(scenario)
      .dumpHTML();      
   }

}

