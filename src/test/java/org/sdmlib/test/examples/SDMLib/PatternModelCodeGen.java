/*
 * Copyright (c) 2012 zuendorf
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 * 
 * The Software shall be used for Good, not Evil.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.sdmlib.test.examples.SDMLib;

import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.storyboards.Storyboard;
import de.uniks.networkparser.graph.Association;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;

public class PatternModelCodeGen {
  /**
   * <p>
   * Storyboard
   * <a href='./src/test/java/org/sdmlib/test/examples/SDMLib/PatternModelCodeGen.java' type=
   * 'text/x-java'>PatternModelCodegen</a>
   * </p>
   * <p>
   * Start situation:
   * </p>
   * <script> var json = { "typ":"class", "nodes":[ { "typ":"node", "id":"AttributeConstraint",
   * "attributes":[ "attrName : String", "cmpOp : String", "hostGraphSrcObject : Object", "tgtValue :
   * Object", "upperTgtValue : Object" ] }, { "typ":"node", "id":"CardinalityConstraint",
   * "attributes":[ "hostGraphSrcObject : Object", "maxCard : long", "minCard : long", "tgtRoleName :
   * String" ] }, { "typ":"node", "id":"CloneOp" }, { "typ":"node", "id":"DestroyObjectElem" }, {
   * "typ":"node", "id":"GenericConstraint", "attributes":[ "text : String" ] }, { "typ":"node",
   * "id":"LinkConstraint" }, { "typ":"node", "id":"MatchIsomorphicConstraint" }, { "typ":"node",
   * "id":"MatchOtherThen", "attributes":[ "hostGraphSrcObject : Object" ] }, { "typ":"node",
   * "id":"NegativeApplicationCondition" }, { "typ":"node", "id":"OptionalSubPattern", "attributes":[
   * "matchForward : boolean" ] }, { "typ":"node", "id":"Pattern", "attributes":[ "debugMode : int",
   * "name : String" ] }, { "typ":"node", "id":"PatternElement", "attributes":[ "doAllMatches :
   * boolean", "hasMatch : boolean", "modifier : String", "patternObjectName : String" ] }, {
   * "typ":"node", "id":"PatternLink", "attributes":[ "hostGraphSrcObject : Object", "tgtRoleName :
   * String" ] }, { "typ":"node", "id":"PatternObject", "attributes":[ "candidates : Object",
   * "currentMatch : Object" ] }, { "typ":"node", "id":"ReachabilityGraph" }, { "typ":"node",
   * "id":"ReachableState", "attributes":[ "metricValue : double", "number : long" ] }, {
   * "typ":"node", "id":"RuleApplication", "attributes":[ "description : String" ] }, { "typ":"node",
   * "id":"UnifyGraphsOp" }, { "typ":"node", "id":"Object" } ], "edges":[ { "typ":"assoc", "source":{
   * "id":"AttributeConstraint", "cardinality":"many", "property":"attrConstraints" }, "target":{
   * "id":"PatternObject", "cardinality":"one", "property":"src" } }, { "typ":"generalisation",
   * "source":{ "id":"AttributeConstraint", "cardinality":"one", "property":"attributeconstraint" },
   * "target":{ "id":"PatternElement", "cardinality":"one", "property":"patternelement" } }, {
   * "typ":"assoc", "source":{ "id":"CardinalityConstraint", "cardinality":"many",
   * "property":"cardConstraints" }, "target":{ "id":"PatternObject", "cardinality":"one",
   * "property":"src" } }, { "typ":"generalisation", "source":{ "id":"CardinalityConstraint",
   * "cardinality":"one", "property":"cardinalityconstraint" }, "target":{ "id":"PatternElement",
   * "cardinality":"one", "property":"patternelement" } }, { "typ":"generalisation", "source":{
   * "id":"CloneOp", "cardinality":"one", "property":"cloneop" }, "target":{ "id":"PatternElement",
   * "cardinality":"one", "property":"patternelement" } }, { "typ":"assoc", "source":{
   * "id":"DestroyObjectElem", "cardinality":"one", "property":"destroyElem" }, "target":{
   * "id":"PatternObject", "cardinality":"one", "property":"patternObject" } }, {
   * "typ":"generalisation", "source":{ "id":"DestroyObjectElem", "cardinality":"one",
   * "property":"destroyobjectelem" }, "target":{ "id":"PatternElement", "cardinality":"one",
   * "property":"patternelement" } }, { "typ":"generalisation", "source":{ "id":"GenericConstraint",
   * "cardinality":"one", "property":"genericconstraint" }, "target":{ "id":"PatternElement",
   * "cardinality":"one", "property":"patternelement" } }, { "typ":"generalisation", "source":{
   * "id":"LinkConstraint", "cardinality":"one", "property":"linkconstraint" }, "target":{
   * "id":"PatternLink", "cardinality":"one", "property":"patternlink" } }, { "typ":"generalisation",
   * "source":{ "id":"MatchIsomorphicConstraint", "cardinality":"one",
   * "property":"matchisomorphicconstraint" }, "target":{ "id":"PatternElement", "cardinality":"one",
   * "property":"patternelement" } }, { "typ":"assoc", "source":{ "id":"MatchOtherThen",
   * "cardinality":"many", "property":"excluders" }, "target":{ "id":"PatternObject",
   * "cardinality":"one", "property":"forbidden" } }, { "typ":"assoc", "source":{
   * "id":"MatchOtherThen", "cardinality":"many", "property":"matchOtherThen" }, "target":{
   * "id":"PatternObject", "cardinality":"one", "property":"src" } }, { "typ":"generalisation",
   * "source":{ "id":"MatchOtherThen", "cardinality":"one", "property":"matchotherthen" }, "target":{
   * "id":"PatternElement", "cardinality":"one", "property":"patternelement" } }, {
   * "typ":"generalisation", "source":{ "id":"NegativeApplicationCondition", "cardinality":"one",
   * "property":"negativeapplicationcondition" }, "target":{ "id":"Pattern", "cardinality":"one",
   * "property":"pattern" } }, { "typ":"generalisation", "source":{ "id":"OptionalSubPattern",
   * "cardinality":"one", "property":"optionalsubpattern" }, "target":{ "id":"Pattern",
   * "cardinality":"one", "property":"pattern" } }, { "typ":"unidirectional", "source":{
   * "id":"Pattern", "cardinality":"one", "property":"currentSubPattern" }, "target":{ "id":"Pattern",
   * "cardinality":"one", "property":"pattern" } }, { "typ":"assoc", "source":{ "id":"Pattern",
   * "cardinality":"one", "property":"pattern" }, "target":{ "id":"PatternElement",
   * "cardinality":"many", "property":"elements" } }, { "typ":"assoc", "source":{ "id":"Pattern",
   * "cardinality":"many", "property":"rules" }, "target":{ "id":"ReachabilityGraph",
   * "cardinality":"one", "property":"rgraph" } }, { "typ":"generalisation", "source":{
   * "id":"Pattern", "cardinality":"one", "property":"pattern" }, "target":{ "id":"PatternElement",
   * "cardinality":"one", "property":"patternelement" } }, { "typ":"generalisation", "source":{
   * "id":"NegativeApplicationCondition", "cardinality":"one",
   * "property":"negativeapplicationcondition" }, "target":{ "id":"Pattern", "cardinality":"one",
   * "property":"pattern" } }, { "typ":"generalisation", "source":{ "id":"OptionalSubPattern",
   * "cardinality":"one", "property":"optionalsubpattern" }, "target":{ "id":"Pattern",
   * "cardinality":"one", "property":"pattern" } }, { "typ":"assoc", "source":{ "id":"PatternElement",
   * "cardinality":"many", "property":"elements" }, "target":{ "id":"Pattern", "cardinality":"one",
   * "property":"pattern" } }, { "typ":"generalisation", "source":{ "id":"Pattern",
   * "cardinality":"one", "property":"pattern" }, "target":{ "id":"PatternElement",
   * "cardinality":"one", "property":"patternelement" } }, { "typ":"generalisation", "source":{
   * "id":"PatternObject", "cardinality":"one", "property":"patternobject" }, "target":{
   * "id":"PatternElement", "cardinality":"one", "property":"patternelement" } }, {
   * "typ":"generalisation", "source":{ "id":"PatternLink", "cardinality":"one",
   * "property":"patternlink" }, "target":{ "id":"PatternElement", "cardinality":"one",
   * "property":"patternelement" } }, { "typ":"generalisation", "source":{ "id":"AttributeConstraint",
   * "cardinality":"one", "property":"attributeconstraint" }, "target":{ "id":"PatternElement",
   * "cardinality":"one", "property":"patternelement" } }, { "typ":"generalisation", "source":{
   * "id":"MatchIsomorphicConstraint", "cardinality":"one", "property":"matchisomorphicconstraint" },
   * "target":{ "id":"PatternElement", "cardinality":"one", "property":"patternelement" } }, {
   * "typ":"generalisation", "source":{ "id":"CloneOp", "cardinality":"one", "property":"cloneop" },
   * "target":{ "id":"PatternElement", "cardinality":"one", "property":"patternelement" } }, {
   * "typ":"generalisation", "source":{ "id":"UnifyGraphsOp", "cardinality":"one",
   * "property":"unifygraphsop" }, "target":{ "id":"PatternElement", "cardinality":"one",
   * "property":"patternelement" } }, { "typ":"generalisation", "source":{ "id":"DestroyObjectElem",
   * "cardinality":"one", "property":"destroyobjectelem" }, "target":{ "id":"PatternElement",
   * "cardinality":"one", "property":"patternelement" } }, { "typ":"generalisation", "source":{
   * "id":"CardinalityConstraint", "cardinality":"one", "property":"cardinalityconstraint" },
   * "target":{ "id":"PatternElement", "cardinality":"one", "property":"patternelement" } }, {
   * "typ":"generalisation", "source":{ "id":"MatchOtherThen", "cardinality":"one",
   * "property":"matchotherthen" }, "target":{ "id":"PatternElement", "cardinality":"one",
   * "property":"patternelement" } }, { "typ":"generalisation", "source":{ "id":"GenericConstraint",
   * "cardinality":"one", "property":"genericconstraint" }, "target":{ "id":"PatternElement",
   * "cardinality":"one", "property":"patternelement" } }, { "typ":"generalisation", "source":{
   * "id":"PatternLink", "cardinality":"one", "property":"patternlink" }, "target":{
   * "id":"PatternElement", "cardinality":"one", "property":"patternelement" } }, {
   * "typ":"generalisation", "source":{ "id":"LinkConstraint", "cardinality":"one",
   * "property":"linkconstraint" }, "target":{ "id":"PatternLink", "cardinality":"one",
   * "property":"patternlink" } }, { "typ":"assoc", "source":{ "id":"PatternObject",
   * "cardinality":"one", "property":"forbidden" }, "target":{ "id":"MatchOtherThen",
   * "cardinality":"many", "property":"excluders" } }, { "typ":"assoc", "source":{
   * "id":"PatternObject", "cardinality":"one", "property":"patternObject" }, "target":{
   * "id":"DestroyObjectElem", "cardinality":"one", "property":"destroyElem" } }, { "typ":"assoc",
   * "source":{ "id":"PatternObject", "cardinality":"one", "property":"src" }, "target":{
   * "id":"AttributeConstraint", "cardinality":"many", "property":"attrConstraints" } }, {
   * "typ":"assoc", "source":{ "id":"PatternObject", "cardinality":"one", "property":"src" },
   * "target":{ "id":"CardinalityConstraint", "cardinality":"many", "property":"cardConstraints" } },
   * { "typ":"assoc", "source":{ "id":"PatternObject", "cardinality":"one", "property":"src" },
   * "target":{ "id":"MatchOtherThen", "cardinality":"many", "property":"matchOtherThen" } }, {
   * "typ":"generalisation", "source":{ "id":"PatternObject", "cardinality":"one",
   * "property":"patternobject" }, "target":{ "id":"PatternElement", "cardinality":"one",
   * "property":"patternelement" } }, { "typ":"assoc", "source":{ "id":"ReachabilityGraph",
   * "cardinality":"one", "property":"parent" }, "target":{ "id":"ReachableState",
   * "cardinality":"many", "property":"states" } }, { "typ":"assoc", "source":{
   * "id":"ReachabilityGraph", "cardinality":"one", "property":"rgraph" }, "target":{ "id":"Pattern",
   * "cardinality":"many", "property":"rules" } }, { "typ":"unidirectional", "source":{
   * "id":"ReachableState", "cardinality":"many", "property":"finalStates" }, "target":{
   * "id":"ReachabilityGraph", "cardinality":"one", "property":"reachabilitygraph" } }, {
   * "typ":"unidirectional", "source":{ "id":"ReachableState", "cardinality":"many", "property":"todo"
   * }, "target":{ "id":"ReachabilityGraph", "cardinality":"one", "property":"reachabilitygraph" } },
   * { "typ":"unidirectional", "source":{ "id":"ReachableState", "cardinality":"many",
   * "property":"finalStates" }, "target":{ "id":"ReachabilityGraph", "cardinality":"one",
   * "property":"reachabilitygraph" } }, { "typ":"assoc", "source":{ "id":"ReachableState",
   * "cardinality":"one", "property":"src" }, "target":{ "id":"RuleApplication", "cardinality":"many",
   * "property":"ruleapplications" } }, { "typ":"assoc", "source":{ "id":"ReachableState",
   * "cardinality":"many", "property":"states" }, "target":{ "id":"ReachabilityGraph",
   * "cardinality":"one", "property":"parent" } }, { "typ":"assoc", "source":{ "id":"ReachableState",
   * "cardinality":"one", "property":"tgt" }, "target":{ "id":"RuleApplication", "cardinality":"many",
   * "property":"resultOf" } }, { "typ":"unidirectional", "source":{ "id":"ReachableState",
   * "cardinality":"many", "property":"todo" }, "target":{ "id":"ReachabilityGraph",
   * "cardinality":"one", "property":"reachabilitygraph" } }, { "typ":"unidirectional", "source":{
   * "id":"Object", "cardinality":"one", "property":"graphRoot" }, "target":{ "id":"ReachableState",
   * "cardinality":"one", "property":"reachablestate" } }, { "typ":"assoc", "source":{
   * "id":"RuleApplication", "cardinality":"many", "property":"resultOf" }, "target":{
   * "id":"ReachableState", "cardinality":"one", "property":"tgt" } }, { "typ":"assoc", "source":{
   * "id":"RuleApplication", "cardinality":"many", "property":"ruleapplications" }, "target":{
   * "id":"ReachableState", "cardinality":"one", "property":"src" } }, { "typ":"generalisation",
   * "source":{ "id":"UnifyGraphsOp", "cardinality":"one", "property":"unifygraphsop" }, "target":{
   * "id":"PatternElement", "cardinality":"one", "property":"patternelement" } }, {
   * "typ":"unidirectional", "source":{ "id":"Object", "cardinality":"one", "property":"graphRoot" },
   * "target":{ "id":"ReachableState", "cardinality":"one", "property":"reachablestate" } } ] } ; new
   * Graph(json, {"canvasid":"canvasPatternModelCodegenClassDiagram1", "display":"html", fontsize:10,
   * bar:false, propertyinfo:false}).layout(100,100); </script>
   * 
   * @see <a href='../../../../../../../../doc/PatternModelCodegen.html'>PatternModelCodegen.html</a>
   */
  // @Test
  public void testPatternModelCodegen() {
    Storyboard storyboard = new Storyboard();

    storyboard.add("Start situation: ");

    ClassModel model = new ClassModel("org.sdmlib.models.pattern");

    Clazz patternElement = model.createClazz("PatternElement")
        .withAttribute("modifier", DataType.STRING);
    patternElement.createAttribute("hasMatch", DataType.BOOLEAN).withValue("false");
    patternElement.withAttribute("patternObjectName", DataType.STRING)
        .withAttribute("doAllMatches", DataType.BOOLEAN);

    Clazz pattern = model.createClazz("Pattern")
        .withAttribute("debugMode", DataType.INT)
        .withAttribute("name", DataType.STRING)
        .withSuperClazz(patternElement);

    pattern.withUniDirectional(pattern, "currentSubPattern", Association.ONE);

    model.createClazz("NegativeApplicationCondition")
        .withSuperClazz(pattern);

    model.createClazz("OptionalSubPattern")
        .withSuperClazz(pattern)
        .withAttribute("matchForward", DataType.BOOLEAN);

    pattern.withBidirectional(patternElement, "elements", Association.MANY, "pattern", Association.ONE);
    // new Association(patternElement).with("elements").with(Association.MANY)
    // .with(new Association(pattern).with("pattern").with(Association.ONE));

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

    patternObject.withBidirectional(attrConstraint, "attrConstraints", Association.MANY, "src", Association.ONE);
    // new Association(patternObject).with("src").with(Association.ONE)
    // .with(new Association(attrConstraint).with("attrConstraints").with(Association.MANY));

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

    patternObject.withBidirectional(destroyObjectClazz, "destroyElem", Association.ONE, "patternObject", Association.ONE);
    // new
    // Association(patternObject).with("patternObject").with(Association.ONE)
    // .with(new
    // Association(destroyObjectClazz).with("destroyElem").with(Association.ONE));

    model.createClazz("CardinalityConstraint")
        .withSuperClazz(patternElement)
        .withAttribute("tgtRoleName", DataType.STRING)
        .withAttribute("hostGraphSrcObject", DataType.OBJECT)
        .withAttribute("minCard", DataType.LONG)
        .withAttribute("maxCard", DataType.LONG)
        .withBidirectional(patternObject, "src", Association.ONE, "cardConstraints", Association.MANY);

    model.createClazz("MatchOtherThen")
        .withSuperClazz(patternElement)
        .withAttribute("hostGraphSrcObject", DataType.OBJECT)
        .withBidirectional(patternObject, "src", Association.ONE, "matchOtherThen", Association.MANY)
        .withBidirectional(patternObject, "forbidden", Association.ONE, "excluders", Association.MANY);

    model.createClazz("GenericConstraint").withAttribute("text", DataType.STRING)
        .withSuperClazz(patternElement);

    Clazz reachabilityGraph = model.createClazz("ReachabilityGraph");

    Clazz rState = model.createClazz("ReachableState")
        .withAttribute("number", DataType.LONG)
        .withAttribute("metricValue", DataType.DOUBLE);

    Clazz objectClass = model.createClazz(Object.class.getName()).withExternal(true);

    rState.withUniDirectional(objectClass, "graphRoot", Association.ONE);

    reachabilityGraph.withUniDirectional(rState, "finalStates", Association.MANY);
    reachabilityGraph.withBidirectional(rState, "states", Association.MANY, "parent", Association.ONE);

    Clazz ruleApplication = model.createClazz("RuleApplication")
        .withAttribute("description", DataType.STRING);

    ruleApplication.withBidirectional(rState, "src", Association.ONE, "ruleapplications", Association.MANY);

    ruleApplication.withBidirectional(rState, "tgt", Association.ONE, "resultOf", Association.MANY);

    reachabilityGraph.withUniDirectional(rState, "todo", Association.MANY);

    reachabilityGraph.withBidirectional(pattern, "rules", Association.MANY, "rgraph", Association.ONE);

    // model.getGenerator().withShowDiff(DIFF.FULL);
    // GraphList list = new GraphList();
    // list.with(model.getClazzes().toArray());
    model.generate("src/main/java");

    storyboard.addClassDiagram(model);

    storyboard.dumpHTML();
  }
}
