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
   
package org.sdmlib.test.examples.SDMLib;
   
import org.junit.Test;
import org.sdmlib.models.classes.Association;
import org.sdmlib.models.classes.Card;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.DataType;
import org.sdmlib.storyboards.StoryPage;
   
public class PatternModelCodeGen 
{
     /**
    * 
    * @see <a href='../../../../../../../../doc/PatternModelCodegen.html'>PatternModelCodegen.html</a>
*/
   @Test
   public void testPatternModelCodegen()
   {
      StoryPage storyboard = new StoryPage();
      
      storyboard.add("Start situation: ");
      
      ClassModel model = new ClassModel("org.sdmlib.models.pattern");
      
      Clazz patternElement = model.createClazz("PatternElement")
      .withAttribute("modifier", DataType.STRING)
      .withAttribute("hasMatch", DataType.BOOLEAN, "false")
      .withAttribute("patternObjectName", DataType.STRING)
      .withAttribute("doAllMatches", DataType.BOOLEAN);
      
      Clazz pattern = model.createClazz("Pattern")
         .withAttribute("currentSubPattern", DataType.ref("Pattern"))
         .withAttribute("debugMode", DataType.INT) 
         // .withAttribute("trace", DataType.ref("StringBuilder"))
         .withAttribute("name", DataType.STRING)
         .withSuperClazz(patternElement);
      
      // model.createClazz(StringBuilder.class.getName());
      
      model.createClazz("NegativeApplicationCondition")
      .withSuperClazz(pattern);
      
      model.createClazz("OptionalSubPattern")
      .withSuperClazz(pattern)
      .withAttribute("matchForward", DataType.BOOLEAN);

      new Association()
      .withTarget(patternElement, "elements", Card.MANY)
      .withSource(pattern, "pattern", Card.ONE);
      
      Clazz patternObject = model.createClazz("PatternObject")
      .withSuperClazz(patternElement)
      .withAttribute("currentMatch", DataType.OBJECT) 
       .withAttribute("candidates", DataType.OBJECT);
      
      Clazz patternLink = model.createClazz("PatternLink")
      .withSuperClazz(patternElement)
      .withAttribute("tgtRoleName", DataType.STRING)
      .withAttribute("hostGraphSrcObject", DataType.OBJECT);
      
//      new Association()
//      .withTarget(patternObject, "tgt", R.ONE)
//      .withSource(patternLink, "incomming", R.MANY);
//      
//      new Association()
//      .withTarget(patternObject, "src", R.ONE)
//      .withSource(patternLink, "outgoing", R.MANY);
      
      Clazz attrConstraint = model.createClazz("AttributeConstraint")
      .withSuperClazz(patternElement)
      .withAttribute("attrName", DataType.STRING)
      .withAttribute("tgtValue", DataType.OBJECT)
      .withAttribute("upperTgtValue", DataType.OBJECT)
      .withAttribute("cmpOp", DataType.STRING)
      .withAttribute("hostGraphSrcObject", DataType.OBJECT);
      
      new Association()
      .withTarget(patternObject, "src", Card.ONE)
      .withSource(attrConstraint, "attrConstraints", Card.MANY);
      
      model.createClazz("LinkConstraint")
      .withSuperClazz(patternLink);
      
      model.createClazz("MatchIsomorphicConstraint")
      .withSuperClazz(patternElement);
      
      model.createClazz("CloneOp")
      .withSuperClazz(patternElement);
      
      model.createClazz("UnifyGraphsOp")
      .withSuperClazz(patternElement);

      Clazz destroyObjectClazz = model.createClazz("DestroyObjectElem")
      .withSuperClazz(patternElement);
      
      new Association()
      .withTarget(patternObject, "patternObject", Card.ONE)
      .withSource(destroyObjectClazz, "destroyElem", Card.ONE);
      
      model.createClazz("CardinalityConstraint")
      .withSuperClazz(patternElement)
         .withAttribute("tgtRoleName", DataType.STRING)
         .withAttribute("hostGraphSrcObject", DataType.OBJECT)
         .withAttribute("minCard", DataType.LONG)
         .withAttribute("maxCard", DataType.LONG)
      .withAssoc(patternObject, "src", Card.ONE, "cardConstraints", Card.MANY);
      
      model.createClazz("MatchOtherThen")
         .withSuperClazz(patternElement)
         .withAttribute("hostGraphSrcObject", DataType.OBJECT)
         .withAssoc(patternObject, "src", Card.ONE, "matchOtherThen", Card.MANY)
         .withAssoc(patternObject, "forbidden", Card.ONE, "excluders", Card.MANY);
      
      model.createClazz("GenericConstraint").withAttribute("text", DataType.STRING)
            .withSuperClazz(patternElement);
            
      Clazz reachabilityGraph = model.createClazz("ReachabilityGraph");
      
      Clazz rState = model.createClazz("ReachableState")
            .withAttribute("number", DataType.LONG)
            .withAttribute("graphRoot", DataType.OBJECT);
      
      reachabilityGraph.withAssoc(rState, "states", Card.MANY, "parent", Card.ONE);
      
      Clazz ruleApplication = model.createClazz("RuleApplication")
            .withAttribute("description", DataType.STRING);
      
      ruleApplication.withAssoc(rState, "src", Card.ONE, "ruleapplications", Card.MANY);
      
      ruleApplication.withAssoc(rState, "tgt", Card.ONE, "resultOf", Card.MANY);
      
      reachabilityGraph.withAssoc(rState, "todo", Card.MANY, "master", Card.ONE);
      
      reachabilityGraph.withAssoc(pattern, "rules", Card.MANY, "rgraph", Card.ONE);
      
      // model.getGenerator().withShowDiff(DIFF.FULL);
      
      model.generate("src/main/java");
      
      storyboard.addClassDiagram(model);
           
      storyboard.dumpHTML();
   }
}
