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
      Clazz sdmLibClazz = model.createClazz("SDMLibClass").withAttribute("name", DataType.STRING);
      Clazz modelClass = model.createClazz("ClassModel").withSuperClazz(sdmLibClazz);
           
      Clazz clazzClass = model.createClazz("Clazz").withSuperClazz(sdmLibClazz)
         .withAttribute("interfaze", DataType.BOOLEAN) 
         .withAttribute("external", DataType.BOOLEAN);
      
      new Association()
      .withSource(new Role(modelClass, "classModel", Card.ONE).withKind(Role.AGGREGATION))
      .withTarget(clazzClass, "classes", Card.MANY);
      
      new Association()
      .withSource(clazzClass, "superClazzes", Card.MANY)
      .withTarget(clazzClass, "kidClazzes", Card.MANY);
      
      
      Clazz valueClass = model.createClazz("Value").withSuperClazz(sdmLibClazz);
      valueClass.withAttribute("initialization", DataType.STRING)
         .withAttribute("type", DataType.ref(DataType.class));
      
      
      Clazz attributeClass = model.createClazz("Attribute").withSuperClazz(valueClass);
         
      new Association()
      .withSource(new Role(clazzClass, "clazz", Card.ONE).withKind(Role.AGGREGATION))
      .withTarget(attributeClass, "attributes", Card.MANY);
      
      
      Clazz methodClass = model.createClazz("Method").withSuperClazz(sdmLibClazz)
            .withAttribute("returnType", DataType.ref(DataType.class))
            .withAttribute("body", DataType.STRING);
      
      
      Clazz enumValueClass = model.createClazz("EnumerationValue").withSuperClazz(sdmLibClazz)
            .withAttribute("valueName", DataType.STRING);
      
      Clazz enumClass = model.createClazz("Enumeration").withSuperClazz(sdmLibClazz);
      
      new Association()
      .withSource(new Role(modelClass, "classModel", Card.ONE).withKind(Role.AGGREGATION))
      .withTarget(enumClass, "enumerations", Card.MANY);
      
      new Association()
      .withSource(new Role(enumClass, "enumeration", Card.ONE).withKind(Role.AGGREGATION))
      .withTarget(methodClass, "methods", Card.MANY);
      
      new Association()
      .withSource(new Role(enumClass, "enumeration", Card.ONE).withKind(Role.AGGREGATION))
      .withTarget(enumValueClass, "values", Card.MANY);
      
      model.createClazz("Parameter").withSuperClazz(valueClass).withAssoc(methodClass, "method", Card.ONE, "parameter", Card.MANY);
      
      new Association()
      .withSource(new Role(clazzClass, "clazz", Card.ONE).withKind(Role.AGGREGATION))
      .withTarget(methodClass, "methods", Card.MANY);
      
      Clazz associationClass = model.createClazz("Association").withSuperClazz(sdmLibClazz);
      
      Clazz roleClass = model.createClazz("Role").withSuperClazz(sdmLibClazz)
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

      model.createClazz("org.sdmlib.codegen.SymTabEntry")
      .withAttribute("kind", DataType.STRING)
      .withAttribute("memberName", DataType.STRING)
      .withAttribute("type", DataType.STRING)
      .withAttribute("startPos", DataType.INT)
      .withAttribute("bodyStartPos", DataType.INT)
      .withAttribute("endPos", DataType.INT)
      .withAttribute("modifiers", DataType.STRING);
      
      model.createClazz("org.sdmlib.codegen.LocalVarTableEntry")
      .withAttribute("name", DataType.STRING)
      .withAttribute("type", DataType.STRING)
      .withAttribute("startPos", DataType.INT)
      .withAttribute("endPos", DataType.INT);
      // .withAttribute("initSequence", "ArrayList<ArrayList<String>>");
      
      
      Clazz statementEntry =  model.createClazz("org.sdmlib.codegen.StatementEntry")
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

      

//      model.getGenerator()
//         .withIgnoreClazz("org.sdmlib.models.classes.Association")
//         .withIgnoreClazz("org.sdmlib.models.classes.util.RoleCreator")
//         .withIgnoreClazz("org.sdmlib.models.classes.Role")
//         .withIgnoreClazz("org.sdmlib.models.classes.util.MethodCreator")
//         .withIgnoreClazz("org.sdmlib.models.classes.util.AssociationCreator")
//         .withIgnoreClazz("org.sdmlib.models.classes.Method")
//         .withIgnoreClazz("org.sdmlib.models.classes.util.ClazzCreator")
//         .withIgnoreClazz("org.sdmlib.models.classes.util.ClazzSet")
//         .withIgnoreClazz("org.sdmlib.models.classes.Attribute")
//         .withShowDiff(DIFF.DIFF);
      
      model.generate("src/main/java");
      
//      StoryboardManager.get()
//      .add(storyboard)
//      .dumpHTML();      
   }

}

