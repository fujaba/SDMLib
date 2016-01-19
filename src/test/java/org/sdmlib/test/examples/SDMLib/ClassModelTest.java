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

package org.sdmlib.test.examples.SDMLib;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Feature;
import org.sdmlib.storyboards.StoryPage;

import de.uniks.networkparser.graph.Association;
import de.uniks.networkparser.graph.AssociationTypes;
import de.uniks.networkparser.graph.Attribute;
import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;
import de.uniks.networkparser.graph.Method;
import de.uniks.networkparser.graph.Modifier;
import de.uniks.networkparser.graph.Parameter;

public class ClassModelTest
{
     /**
    * 
    * @see <a href='../../../../../../../../doc/ClassModelCodeGen.html'>ClassModelCodeGen.html</a>
*/
   @Test
   public void testClassModelCodeGen()
   {
      StoryPage storyboard = new StoryPage();
      
      // =======================================================================
      // storyboard.add("This test generates some classes with some elements. ");

      // =======================================================================
      storyboard.add("We start by bootstrapping org.sdmlib.model.classes.ClassModel. ");

      ClassModel model = new ClassModel("org.sdmlib.models.classes");
      Clazz sdmLibClazz = model.createClazz("SDMLibClass").withAttribute("name", DataType.STRING);
      Clazz modelClass = model.createClazz("ClassModel").withSuperClazz(sdmLibClazz);

      Clazz clazzClass = model.createClazz("Clazz").withSuperClazz(sdmLibClazz)
         .withAttribute("interfaze", DataType.BOOLEAN)
         .withAttribute("external", DataType.BOOLEAN);

      new Association(modelClass).with("classModel").with(Cardinality.ONE).with(AssociationTypes.AGGREGATION)
      	.with(new Association(clazzClass).with("classes").with(Cardinality.MANY));

      new Association(clazzClass).with("superClazzes").with(Cardinality.MANY)
      	.with(new Association(clazzClass).with("kidClazzes").with(Cardinality.MANY));

      Clazz valueClass = model.createClazz("Value").withSuperClazz(sdmLibClazz);
      valueClass.withAttribute("initialization", DataType.STRING)
         .withAttribute("type", DataType.create(DataType.class));

      Clazz attributeClass = model.createClazz("Attribute").withSuperClazz(valueClass);

      new Association(clazzClass).with("clazz").with(Cardinality.ONE).with(AssociationTypes.AGGREGATION)
      	.with(new Association(attributeClass).with("attributes").with(Cardinality.MANY));

      Clazz methodClass = model.createClazz("Method").withSuperClazz(sdmLibClazz)
         .withAttribute("returnType", DataType.create(DataType.class))
         .withAttribute("body", DataType.STRING);

      Clazz annotationClass = model.createClazz("Annotation").withSuperClazz(sdmLibClazz);
      annotationClass
         .withMethod("createOverrideAnnotation", DataType.create(annotationClass))
         .withMethod("createDeprecatedAnnotation", DataType.create(annotationClass))
         .with(
            new Method("createSuppressWarningsAnnotation", DataType.create(annotationClass),
                  new Parameter(DataType.create("String...")).with("values")))
         .withMethod("createSafeVarargsAnnotation", DataType.create(annotationClass));
      
      Attribute deprecatedAnnotation = new Attribute("DEPRECATED", DataType.STRING).with(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL).withValue("Deprecated");
      Attribute overrideAnnotation = new Attribute("OVERRIDE", DataType.STRING).with(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL).withValue("Override");
      Attribute safeVarargsAnnotation = new Attribute("SAFE_VARGARGS", DataType.STRING).with(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL).withValue("SafeVarargs");
      Attribute suppressWarningsAnnotation = new Attribute("SUPPRESS_WARNINGS", DataType.STRING).with(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL).withValue("SuppressWarnings");

      annotationClass.with(deprecatedAnnotation, overrideAnnotation, safeVarargsAnnotation, suppressWarningsAnnotation);
      
      // ---- Enumeration ----

      Clazz enumClass = model.createClazz("Enumeration").withSuperClazz(sdmLibClazz);

      new Association(modelClass).with("classModel").with(Cardinality.ONE).with(AssociationTypes.AGGREGATION)
      	.with(new Association(enumClass).with("enumerations").with(Cardinality.MANY));

      // ---- Enumeration END ----

      model.createClazz("Parameter").withSuperClazz(valueClass)
         .withBidirectional(methodClass, "method", Cardinality.ONE, "parameter", Cardinality.MANY);

      new Association(clazzClass).with("clazz").with(Cardinality.ONE).with(AssociationTypes.AGGREGATION)
      	.with(new Association(methodClass).with("methods").with(Cardinality.MANY));

      Clazz associationClass = model.createClazz("Association").withSuperClazz(sdmLibClazz);

      Clazz roleClass = model.createClazz("Role").withSuperClazz(sdmLibClazz)
         .with(new Attribute("card", DataType.STRING).withValue("MANY"))
         .with(new Attribute("kind", DataType.STRING).withValue("VANILLA"));

      new Association(clazzClass).with("clazz").with(Cardinality.ONE)
      	.with(new Association(roleClass).with("roles").with(Cardinality.MANY));

      new Association(associationClass).with("assoc").with(Cardinality.ONE)
      	.with(new Association(roleClass).with("source").with(Cardinality.ONE));

      new Association(associationClass).with("assoc").with(Cardinality.ONE)
      	.with(new Association(roleClass).with("target").with(Cardinality.ONE));

      model.createClazz("org.sdmlib.codegen.SymTabEntry")
         .withAttribute("kind", DataType.STRING)
         .withAttribute("memberName", DataType.STRING)
         .withAttribute("type", DataType.STRING)
         .withAttribute("startPos", DataType.INT)
         .withAttribute("bodyStartPos", DataType.INT)
         .withAttribute("endPos", DataType.INT)
         .withAttribute("annotations", DataType.STRING)
         .withAttribute("modifiers", DataType.STRING)
         .withAttribute("annotationsStartPos", DataType.INT)
         .withAttribute("preCommentStartPos", DataType.INT)
         .withAttribute("preCommentEndPos", DataType.INT)
         ;

      model.createClazz("org.sdmlib.codegen.LocalVarTableEntry")
         .withAttribute("name", DataType.STRING)
         .withAttribute("type", DataType.STRING)
         .withAttribute("startPos", DataType.INT)
         .withAttribute("endPos", DataType.INT);
      // .withAttribute("initSequence", "ArrayList<ArrayList<String>>");

      Clazz statementEntry = model.createClazz("org.sdmlib.codegen.StatementEntry")
         .withAttribute("kind", DataType.STRING)
         .withAttribute("tokenList", DataType.create("java.util.ArrayList<String>", true))
         .withAttribute("assignTargetVarName", DataType.STRING)
         .withAttribute("startPos", DataType.INT)
         .withAttribute("endPos", DataType.INT);

      new Association(statementEntry).with("parent").with(Cardinality.ONE)
      	.with(new Association(statementEntry).with("bodyStats").with(Cardinality.MANY));

     storyboard.addClassDiagram(model);

      
      // model.getGenerator()
      // .withIgnoreClazz("org.sdmlib.models.classes.Association")
      // .withIgnoreClazz("org.sdmlib.models.classes.util.RoleCreator")
      // .withIgnoreClazz("org.sdmlib.models.classes.Role")
      // .withIgnoreClazz("org.sdmlib.models.classes.util.MethodCreator")
      // .withIgnoreClazz("org.sdmlib.models.classes.util.AssociationCreator")
      // .withIgnoreClazz("org.sdmlib.models.classes.Method")
      // .withIgnoreClazz("org.sdmlib.models.classes.util.ClazzCreator")
      // .withIgnoreClazz("org.sdmlib.models.classes.util.ClazzSet")
      // .withIgnoreClazz("org.sdmlib.models.classes.Attribute")
      // .withShowDiff(DIFF.DIFF);

      model.withoutFeature(Feature.PatternObject);
      model.generate("src/main/java");

      storyboard.dumpHTML();
   }
}
