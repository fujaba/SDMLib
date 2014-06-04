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
   
package org.sdmlib.examples.SDMLib;
   
import org.junit.Test;
import org.sdmlib.models.classes.Association;
import org.sdmlib.models.classes.Card;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.DataType;
import org.sdmlib.models.classes.SDMLibConfig;
import org.sdmlib.serialization.SDMLibJsonIdMap;
import org.sdmlib.storyboards.Storyboard;
import org.sdmlib.storyboards.StoryboardManager;
   
public class PatternModelCodeGen 
{
   @Test
   public void testPatternModelCodegen()
   {
      Storyboard storyboard = new Storyboard("test", "PatternModelCodegen");
      
      storyboard.setSprint("Sprint.001.Booting");
      
      storyboard.add("Start situation: ", SDMLibConfig.DONE, "zuendorf", "19.08.2012 22:52:42", 80, 0);
      
      ClassModel model = new ClassModel("org.sdmlib.models.pattern");
      
      Clazz patternElement = new Clazz("PatternElement")
      .withAttribute("modifier", DataType.STRING)
      .withAttribute("hasMatch", DataType.BOOLEAN, "false")
      .withAttribute("patternObjectName", DataType.STRING)
      .withAttribute("doAllMatches", DataType.BOOLEAN);
      
      Clazz pattern = new Clazz("Pattern")
         .withAttribute("currentSubPattern", DataType.ref("Pattern"))
         .withAttribute("debugMode", DataType.INT) 
         .withAttribute("trace", DataType.ref("StringBuilder"))
         .withAttribute("name", DataType.STRING)
         .withSuperClazzes(patternElement);
      
      model.createClazz(StringBuilder.class.getName());
      
      model.createClazz("NegativeApplicationCondition")
      .withSuperClazzes(pattern);
      
      model.createClazz("OptionalSubPattern")
      .withSuperClazzes(pattern)
      .withAttribute("matchForward", DataType.BOOLEAN);
      
      new Association()
      .withTarget(patternElement, "elements", Card.MANY)
      .withSource(pattern, "pattern", Card.ONE);
      
      Clazz patternObject = new Clazz("PatternObject")
      .withSuperClazzes(patternElement)
      .withAttribute("currentMatch", DataType.OBJECT) 
       .withAttribute("candidates", DataType.OBJECT);
      
      Clazz patternLink = new Clazz("PatternLink")
      .withSuperClazzes(patternElement)
      .withAttribute("tgtRoleName", DataType.STRING)
      .withAttribute("hostGraphSrcObject", DataType.OBJECT);
      
//      new Association()
//      .withTarget(patternObject, "tgt", R.ONE)
//      .withSource(patternLink, "incomming", R.MANY);
//      
//      new Association()
//      .withTarget(patternObject, "src", R.ONE)
//      .withSource(patternLink, "outgoing", R.MANY);
      
      Clazz attrConstraint = new Clazz("AttributeConstraint")
      .withSuperClazzes(patternElement)
      .withAttribute("attrName", DataType.STRING)
      .withAttribute("tgtValue", DataType.OBJECT)
      .withAttribute("upperTgtValue", DataType.OBJECT)
      .withAttribute("cmpOp", DataType.STRING)
      .withAttribute("hostGraphSrcObject", DataType.OBJECT);
      
      new Association()
      .withTarget(patternObject, "src", Card.ONE)
      .withSource(attrConstraint, "attrConstraints", Card.MANY);
      
      model.createClazz("LinkConstraint")
      .withSuperClazzes(patternLink);
      
      model.createClazz("MatchIsomorphicConstraint")
      .withSuperClazzes(patternElement);
      
      model.createClazz("CloneOp")
      .withSuperClazzes(patternElement);
      
      model.createClazz("UnifyGraphsOp")
      .withSuperClazzes(patternElement);

      Clazz destroyObjectClazz = new Clazz("DestroyObjectElem")
      .withSuperClazzes(patternElement);
      
      new Association()
      .withTarget(patternObject, "patternObject", Card.ONE)
      .withSource(destroyObjectClazz, "destroyElem", Card.ONE);
      
      model.createClazz("CardinalityConstraint")
      .withSuperClazzes(patternElement)
         .withAttribute("tgtRoleName", DataType.STRING)
         .withAttribute("hostGraphSrcObject", DataType.OBJECT)
         .withAttribute("minCard", DataType.LONG)
         .withAttribute("maxCard", DataType.LONG)
      .withAssoc(patternObject, "src", Card.ONE, "cardConstraints", Card.MANY);
      
      model.createClazz("MatchOtherThen")
         .withSuperClazzes(patternElement)
         .withAttribute("hostGraphSrcObject", DataType.OBJECT)
         .withAssoc(patternObject, "src", Card.ONE, "matchOtherThen", Card.MANY)
         .withAssoc(patternObject, "forbidden", Card.ONE, "excluders", Card.MANY);
      
      model.createClazz("GenericConstraint").withAttribute("text", DataType.STRING)
            .withSuperClazzes(patternElement);
      
      model.createClazz("org.sdmlib.serialization.json.JsonIdMap");
      
      model.createClazz(SDMLibJsonIdMap.class.getName());
      
      Clazz reachabilityGraph = model.createClazz("ReachabilityGraph");
      
      Clazz rState = new Clazz("ReachableState").withAssoc(reachabilityGraph, "states", Card.MANY, "parent", Card.ONE)
            .withAttribute("number", DataType.LONG)
            .withAttribute("graphRoot", DataType.OBJECT);
      
      Clazz ruleApplication = new Clazz("ruleApplication").withAssoc(rState, "ruleapplications", Card.MANY, "src", Card.ONE)
            .withAttribute("description", DataType.STRING);
      
      ruleApplication.withAssoc(rState, "tgt", Card.ONE, "resultOf", Card.MANY);
      
      reachabilityGraph.withAssoc(rState, "todo", Card.MANY, "master", Card.ONE);
      
      reachabilityGraph.withAssoc(pattern, "rules", Card.MANY, "rgraph", Card.ONE);
      
      model.generate("src");
      
      storyboard.addSVGImage(model.dumpClassDiagram("src"));
           
      StoryboardManager.get()
      .add(storyboard)
      .dumpHTML();
   }
}
