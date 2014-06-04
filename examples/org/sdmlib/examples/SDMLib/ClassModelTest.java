/*
   Copyright (c) 2014 zuendorf 
   
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

package org.sdmlib.examples.SDMLib;

import org.junit.Test;
import org.sdmlib.models.classes.Association;
import org.sdmlib.models.classes.Card;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.DataType;
import org.sdmlib.models.classes.Role;
import org.sdmlib.models.classes.logic.GenClassModel.DIFF;

public class ClassModelTest
{
   @Test
   public void testClassModelCodeGen()
   {
//      Storyboard storyboard = new Storyboard("test", "ClassModelCodeGen");
//      storyboard.setSprint("Sprint.001.Booting");
      
      //=======================================================================
//      storyboard.add("This test generates some classes with some elements. ");


      //=======================================================================
//      storyboard.add("We start by bootstrapping org.sdmlib.model.classes.ClassModel. ");

      ClassModel model = new ClassModel("org.sdmlib.models.classes");
      Clazz sdmLibClazz = new Clazz("SDMLibClass").withAttribute("name", DataType.STRING);
      Clazz modelClass = model.createClazz("ClassModel").withSuperClass(sdmLibClazz).withAttribute("Name", DataType.STRING);
           
      Clazz clazzClass = new Clazz("Clazz").withSuperClass(sdmLibClazz)
         .withAttribute("interface", DataType.BOOLEAN) 
         .withAttribute("external", DataType.BOOLEAN);
      
      new Association()
      .withSource(modelClass, "classModel", Card.ONE, Role.AGGREGATION)
      .withTarget(clazzClass, "classes", Card.MANY);
      
      new Association()
      .withSource(clazzClass, "superclasses", Card.MANY)
      .withTarget(clazzClass, "kidclasses", Card.MANY);
      
      
      Clazz valueClass = new Clazz("Value").withSuperClass(sdmLibClazz);
      valueClass.withAttribute("initialization", DataType.STRING)
         .withAttribute("type", DataType.ref(DataType.class));
      
      
      Clazz attributeClass = new Clazz("Attribute").withSuperClass(valueClass);
         
      new Association()
      .withSource(clazzClass, "clazz", Card.ONE, Role.AGGREGATION)
      .withTarget(attributeClass, "attributes", Card.MANY);
      
      
      Clazz methodClass = new Clazz("Method").withSuperClass(sdmLibClazz)
            .withAttribute("returnType", DataType.ref(DataType.class))
            .withAttribute("body", DataType.STRING);
      
      model.createClazz("Parameter").withSuperClass(valueClass).withAssoc(methodClass, "method", Card.ONE, "parameter", Card.MANY);
      
      new Association()
      .withSource(clazzClass, "clazz", Card.ONE, Role.AGGREGATION)
      .withTarget(methodClass, "methods", Card.MANY);
      
      Clazz associationClass = new Clazz("Association").withSuperClass(sdmLibClazz);
      
      Clazz roleClass = new Clazz("Role").withSuperClass(sdmLibClazz)
      .withAttribute("card", DataType.STRING, "MANY")
      .withAttribute("kind", DataType.STRING, "VANILLA");
      
      new Association()
      .withSource(clazzClass, "clazz", Card.ONE)
      .withTarget(roleClass, "roles", Card.MANY);
      
      new Association()
      .withSource(associationClass, "assoc", Card.ONE)
      .withTarget(roleClass, "source", Card.ONE);

      new Association()
      .withSource(associationClass, "assoc", Card.ONE)
      .withTarget(roleClass, "target", Card.ONE);

      new Clazz("org.sdmlib.codegen.SymTabEntry")
      .withAttribute("kind", DataType.STRING)
      .withAttribute("memberName", DataType.STRING)
      .withAttribute("type", DataType.STRING)
      .withAttribute("startPos", DataType.INT)
      .withAttribute("bodyStartPos", DataType.INT)
      .withAttribute("endPos", DataType.INT)
      .withAttribute("modifiers", DataType.STRING);
      
      new Clazz("org.sdmlib.codegen.LocalVarTableEntry")
      .withAttribute("name", DataType.STRING)
      .withAttribute("type", DataType.STRING)
      .withAttribute("startPos", DataType.INT)
      .withAttribute("endPos", DataType.INT);
      // .withAttribute("initSequence", "ArrayList<ArrayList<String>>");
      
      
      Clazz statementEntry =  new Clazz("org.sdmlib.codegen.StatementEntry")
      .withAttribute("kind", DataType.STRING)
       .withAttribute("tokenList", DataType.ref("ArrayList<String>")) 
       .withAttribute("assignTargetVarName", DataType.STRING) 
       .withAttribute("startPos", DataType.INT) 
          .withAttribute("endPos", DataType.INT);
      
      new Association()
      .withSource(statementEntry, "parent", Card.ONE)
      .withTarget(statementEntry, "bodyStats", Card.MANY);
      
      
//      storyboard.add("Basic bootstrap done.", 
//         ProjectBoard.IMPLEMENTATION, "zuendorf", "18.03.2012 23:35:42", 1, 0);
//      
//      storyboard.addSVGImage(model.dumpClassDiagram("src", "ClassModelClasses01"));
//      
//      storyboard.add("Generate generic get and set and removeYou.", 
//         ProjectBoard.IMPLEMENTATION, "zuendorf", "19.03.2012 00:19:42", 1, 0);
//
//      storyboard.add("Generate creator classes.", 
//         ProjectBoard.IMPLEMENTATION, "zuendorf", "26.03.2012 22:54:42", 1, 0);
//
//      storyboard.add("Generate PatternObject classes.", 
//         ProjectBoard.IMPLEMENTATION, "zuendorf", "19.08.2012 19:08:42", 8, 0);

      

      model.getGenerator()
         .withIgnoreClazz("org.sdmlib.models.classes.Association")
         .withIgnoreClazz("org.sdmlib.models.classes.util.RoleCreator")
         .withIgnoreClazz("org.sdmlib.models.classes.Role")
         .withIgnoreClazz("org.sdmlib.models.classes.util.MethodCreator")
         .withIgnoreClazz("org.sdmlib.models.classes.util.AssociationCreator")
         .withShowDiff(DIFF.DIFF);
      
      model.generate("src");
      
//      StoryboardManager.get()
//      .add(storyboard)
//      .dumpHTML();      
   }

}

