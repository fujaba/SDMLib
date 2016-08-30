
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

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.storyboards.Storyboard;

import de.uniks.networkparser.graph.Attribute;
import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;
import de.uniks.networkparser.interfaces.Condition;

public class PatternModelCodeGen
{
   /**
    * 
    * @see <a href= '../../../../../../../../doc/PatternModelCodegen.html'>PatternModelCodegen.html</a>
    * @see <a href= '../../../../../../../../doc/PatternModelCodegen.html'>PatternModelCodegen.html</a>
    * @see <a href= '../../../../../../../../doc/PatternModelCodegen.html'>PatternModelCodegen.html</a>
    * @see <a href= '../doc/PatternModelCodegen.html'>PatternModelCodegen.html</a>
    * @see <a href='../doc/PatternModelCodegen.html'>PatternModelCodegen.html</a>
    */
   @Test
   public void testPatternModelCodegen()
   {
      Storyboard storyboard = new Storyboard();

      storyboard.add("Start situation: ");

      ClassModel model = new ClassModel("org.sdmlib.models.pattern");

      Clazz patternElement = model.createClazz("PatternElement")
         .withAttribute("modifier", DataType.STRING)
         .with(new Attribute("hasMatch", DataType.BOOLEAN).withValue("false"))
         .withAttribute("patternObjectName", DataType.STRING)
         .withAttribute("doAllMatches", DataType.BOOLEAN);

      Clazz pattern = model.createClazz("Pattern")
         .withAttribute("debugMode", DataType.INT)
         .withAttribute("name", DataType.STRING)
         .withSuperClazz(patternElement);

      pattern.withUniDirectional(pattern, "currentSubPattern", Cardinality.ONE);

      model.createClazz("NegativeApplicationCondition")
         .withSuperClazz(pattern);

      model.createClazz("OptionalSubPattern")
         .withSuperClazz(pattern)
         .withAttribute("matchForward", DataType.BOOLEAN);

      pattern.withBidirectional(patternElement, "elements", Cardinality.MANY, "pattern", Cardinality.ONE);
      // new Association(patternElement).with("elements").with(Cardinality.MANY)
      // .with(new Association(pattern).with("pattern").with(Cardinality.ONE));

      Clazz patternObject = model.createClazz("PatternObject")
         .withSuperClazz(patternElement)
         .withAttribute("currentMatch", DataType.OBJECT)
         .withAttribute("candidates", DataType.OBJECT);

      Clazz patternLink = model.createClazz("PatternLink")
         .withSuperClazz(patternElement)
         .withAttribute("tgtRoleName", DataType.STRING)
         .withAttribute("hostGraphSrcObject", DataType.OBJECT);

      // new Association()
      // .withTarget(patternObject, "tgt", R.ONE)
      // .withSource(patternLink, "incomming", R.MANY);
      //
      // new Association()
      // .withTarget(patternObject, "src", R.ONE)
      // .withSource(patternLink, "outgoing", R.MANY);

      Clazz attrConstraint = model.createClazz("AttributeConstraint")
         .withSuperClazz(patternElement)
         .withAttribute("attrName", DataType.STRING)
         .withAttribute("tgtValue", DataType.OBJECT)
         .withAttribute("upperTgtValue", DataType.OBJECT)
         .withAttribute("cmpOp", DataType.STRING)
         .withAttribute("hostGraphSrcObject", DataType.OBJECT);

      patternObject.withBidirectional(attrConstraint, "attrConstraints", Cardinality.MANY, "src", Cardinality.ONE);
      // new Association(patternObject).with("src").with(Cardinality.ONE)
      // .with(new
      // Association(attrConstraint).with("attrConstraints").with(Cardinality.MANY));

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

      patternObject.withBidirectional(destroyObjectClazz, "destroyElem", Cardinality.ONE, "patternObject", Cardinality.ONE);
      // new
      // Association(patternObject).with("patternObject").with(Cardinality.ONE)
      // .with(new
      // Association(destroyObjectClazz).with("destroyElem").with(Cardinality.ONE));

      model.createClazz("CardinalityConstraint")
         .withSuperClazz(patternElement)
         .withAttribute("tgtRoleName", DataType.STRING)
         .withAttribute("hostGraphSrcObject", DataType.OBJECT)
         .withAttribute("minCard", DataType.LONG)
         .withAttribute("maxCard", DataType.LONG)
         .withBidirectional(patternObject, "src", Cardinality.ONE, "cardConstraints", Cardinality.MANY);

      model.createClazz("MatchOtherThen")
         .withSuperClazz(patternElement)
         .withAttribute("hostGraphSrcObject", DataType.OBJECT)
         .withBidirectional(patternObject, "src", Cardinality.ONE, "matchOtherThen", Cardinality.MANY)
         .withBidirectional(patternObject, "forbidden", Cardinality.ONE, "excluders", Cardinality.MANY);

      model.createClazz("GenericConstraint").withAttribute("text", DataType.STRING)
         .withUniDirectional(patternObject, "src", Cardinality.ONE)
         .withAttribute("condition", DataType.create(Condition.class))
         .withSuperClazz(patternElement);

      Clazz reachabilityGraph = model.createClazz("ReachabilityGraph");

      Clazz rState = model.createClazz("ReachableState")
         .withAttribute("number", DataType.LONG)
         .withAttribute("metricValue", DataType.DOUBLE)
         .withAttribute("graphRoot", DataType.OBJECT);

      reachabilityGraph.withUniDirectional(rState, "finalStates", Cardinality.MANY);
      reachabilityGraph.withBidirectional(rState, "states", Cardinality.MANY, "parent", Cardinality.ONE);

      Clazz ruleApplication = model.createClazz("RuleApplication")
         .withAttribute("description", DataType.STRING);

      ruleApplication.withBidirectional(rState, "src", Cardinality.ONE, "ruleapplications", Cardinality.MANY);

      ruleApplication.withBidirectional(rState, "tgt", Cardinality.ONE, "resultOf", Cardinality.MANY);

      ruleApplication.withUniDirectional(pattern, "rule", Cardinality.ONE);

      reachabilityGraph.withUniDirectional(rState, "todo", Cardinality.MANY);

      reachabilityGraph.withUniDirectional(pattern, "rules", Cardinality.MANY);

      // model.getGenerator().withShowDiff(DIFF.FULL);
      // GraphList list = new GraphList();
      // list.with(model.getClazzes().toArray());
      // model.generate("src/main/java");

      storyboard.addClassDiagram(model);

      storyboard.dumpHTML();
   }
}
