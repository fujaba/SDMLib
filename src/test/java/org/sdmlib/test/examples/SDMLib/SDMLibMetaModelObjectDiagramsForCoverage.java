package org.sdmlib.test.examples.SDMLib;

import java.util.ArrayList;
import org.sdmlib.codegen.LocalVarTableEntry;
import org.sdmlib.codegen.StatementEntry;
import org.sdmlib.codegen.SymTabEntry;
import org.sdmlib.codegen.Token;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.pattern.ReachabilityGraph;
import org.sdmlib.models.pattern.ReachableState;
import org.sdmlib.models.pattern.RuleApplication;
import org.sdmlib.storyboards.Storyboard;

public class SDMLibMetaModelObjectDiagramsForCoverage {
  /**
   * 
   * <p>
   * Storyboard <a href=
   * '.././src/test/java/org/sdmlib/test/examples/SDMLib/SDMLibMetaModelObjectDiagramsForCoverage.java'
   * type='text/x-java'>PatternModelObjectsForCoverage</a>
   * </p>
   * <p>
   * Create some objects just for coverage. This does not serve as an usage example.
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"A12 :
   * AttributeConstraint", "attributes":[ "attrName=null", "cmpOp=null", "doAllMatches=false",
   * "hasMatch=false", "hostGraphSrcObject=null", "modifier=null", "patternObjectName=null",
   * "src=null", "tgtValue=null", "upperTgtValue=null" ] }, { "type":"clazz", "id":"A5 :
   * AttributeConstraint", "attributes":[ "attrName=attr1", "cmpOp=null", "doAllMatches=false",
   * "hasMatch=false", "hostGraphSrcObject=null", "modifier=null", "patternObjectName=null",
   * "tgtValue=null", "upperTgtValue=null" ] }, { "type":"clazz", "id":"A8 : AttributeConstraint",
   * "attributes":[ "attrName=null", "cmpOp=null", "doAllMatches=false", "hasMatch=false",
   * "hostGraphSrcObject=null", "modifier=null", "patternObjectName=null", "src=null",
   * "tgtValue=null", "upperTgtValue=null" ] }, { "type":"clazz", "id":"C10 : CloneOp", "attributes":[
   * "doAllMatches=false", "hasMatch=true", "modifier=null", "patternObjectName=null" ] }, {
   * "type":"clazz", "id":"C13 : CardinalityConstraint", "attributes":[ "doAllMatches=false",
   * "hasMatch=false", "hostGraphSrcObject=null", "maxCard=0", "minCard=0", "modifier=null",
   * "patternObjectName=null", "src=null", "tgtRoleName=null" ] }, { "type":"clazz", "id":"C14 :
   * CloneOp", "attributes":[ "doAllMatches=false", "hasMatch=false", "modifier=null",
   * "patternObjectName=null" ] }, { "type":"clazz", "id":"C9 : CardinalityConstraint", "attributes":[
   * "doAllMatches=false", "hasMatch=false", "hostGraphSrcObject=null", "maxCard=0", "minCard=0",
   * "modifier=null", "patternObjectName=null", "src=null", "tgtRoleName=null" ] }, { "type":"clazz",
   * "id":"D11 : DestroyObjectElem", "attributes":[ "doAllMatches=false", "hasMatch=false",
   * "modifier=null", "patternObject=null", "patternObjectName=null" ] }, { "type":"clazz", "id":"D15
   * : DestroyObjectElem", "attributes":[ "doAllMatches=false", "hasMatch=false", "modifier=null",
   * "patternObject=null", "patternObjectName=null" ] }, { "type":"clazz", "id":"G16 :
   * GenericConstraint", "attributes":[ "condition=null", "doAllMatches=false", "hasMatch=false",
   * "modifier=null", "patternObjectName=null", "src=null", "text=null" ] }, { "type":"clazz",
   * "id":"L17 : LinkConstraint", "attributes":[ "doAllMatches=false", "hasMatch=false",
   * "hostGraphSrcObject=null", "modifier=null", "patternObjectName=null", "src=null", "tgt=null",
   * "tgtRoleName=null" ] }, { "type":"clazz", "id":"M18 : MatchIsomorphicConstraint", "attributes":[
   * "doAllMatches=false", "hasMatch=false", "modifier=null", "patternObjectName=null" ] }, {
   * "type":"clazz", "id":"M19 : MatchOtherThen", "attributes":[ "doAllMatches=false",
   * "forbidden=null", "hasMatch=false", "hostGraphSrcObject=null", "modifier=null",
   * "patternObjectName=null", "src=null" ] }, { "type":"clazz", "id":"N20 :
   * NegativeApplicationCondition", "attributes":[ "debugMode=0", "doAllMatches=false",
   * "hasMatch=true", "modifier=null", "name=null", "patternObjectName=null", "rgraph=null" ] }, {
   * "type":"clazz", "id":"O24 : OptionalSubPattern", "attributes":[ "currentSubPattern=null",
   * "debugMode=0", "doAllMatches=false", "hasMatch=true", "matchForward=true", "modifier=null",
   * "name=null", "patternObjectName=null", "rgraph=null" ] }, { "type":"clazz", "id":"P1 :
   * PatternObject", "attributes":[ "candidates=null", "currentMatch=match", "destroyElem=null",
   * "doAllMatches=false", "hasMatch=false", "modifier=bound", "patternObjectName=null" ] }, {
   * "type":"clazz", "id":"P21 : PatternLink", "attributes":[ "doAllMatches=false", "hasMatch=false",
   * "hostGraphSrcObject=null", "modifier=null", "patternObjectName=null", "tgtRoleName=null" ] }, {
   * "type":"clazz", "id":"P22 : PatternObject", "attributes":[ "candidates=null",
   * "currentMatch=null", "destroyElem=null", "doAllMatches=false", "hasMatch=false", "modifier=null",
   * "patternObjectName=null" ] }, { "type":"clazz", "id":"P26 : PatternLink", "attributes":[
   * "doAllMatches=false", "hasMatch=false", "hostGraphSrcObject=null", "modifier=null",
   * "patternObjectName=null", "src=null", "tgt=null", "tgtRoleName=null" ] }, { "type":"clazz",
   * "id":"P3 : Pattern", "attributes":[ "debugMode=0", "doAllMatches=false", "hasMatch=true",
   * "modifier=null", "name=null", "patternObjectName=null", "rgraph=null" ] }, { "type":"clazz",
   * "id":"P4 : Pattern", "attributes":[ "currentSubPattern=null", "debugMode=0",
   * "doAllMatches=false", "hasMatch=true", "modifier=null", "name=null", "patternObjectName=null",
   * "rgraph=null" ] }, { "type":"clazz", "id":"R2 : ReachabilityGraph" }, { "type":"clazz", "id":"R23
   * : RuleApplication", "attributes":[ "description=null" ] }, { "type":"clazz", "id":"R25 :
   * ReachableState", "attributes":[ "descr=0 0.0", "failureState=false", "graphRoot=null",
   * "metricValue=0.0", "number=0", "parent=null" ] }, { "type":"clazz", "id":"R6 : ReachableState",
   * "attributes":[ "descr=0 0.0", "failureState=false", "graphRoot=null", "metricValue=0.0",
   * "number=0" ] }, { "type":"clazz", "id":"R7 : ReachableState", "attributes":[ "descr=0 0.0",
   * "failureState=false", "graphRoot=null", "metricValue=0.0", "number=0", "parent=null" ] }, {
   * "type":"clazz", "id":"U27 : UnifyGraphsOp", "attributes":[ "doAllMatches=false",
   * "hasMatch=false", "modifier=null", "patternObjectName=null" ] } ], "edges":[ { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"attrConstraints", "id":"A5 : AttributeConstraint" },
   * "target":{ "cardinality":"one", "property":"src", "id":"P1 : PatternObject" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"elements", "id":"P21 : PatternLink"
   * }, "target":{ "cardinality":"one", "property":"pattern", "id":"P4 : Pattern" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"elements", "id":"P22 :
   * PatternObject" }, "target":{ "cardinality":"one", "property":"pattern", "id":"P4 : Pattern" } },
   * { "type":"assoc", "source":{ "cardinality":"many", "property":"elements", "id":"A5 :
   * AttributeConstraint" }, "target":{ "cardinality":"one", "property":"pattern", "id":"P4 : Pattern"
   * } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"elements", "id":"A8 :
   * AttributeConstraint" }, "target":{ "cardinality":"one", "property":"pattern", "id":"P3 : Pattern"
   * } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"elements", "id":"C9 :
   * CardinalityConstraint" }, "target":{ "cardinality":"one", "property":"pattern", "id":"P3 :
   * Pattern" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"elements", "id":"C10
   * : CloneOp" }, "target":{ "cardinality":"one", "property":"pattern", "id":"P3 : Pattern" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"elements", "id":"D11 :
   * DestroyObjectElem" }, "target":{ "cardinality":"one", "property":"pattern", "id":"P3 : Pattern" }
   * }, { "type":"assoc", "source":{ "cardinality":"many", "property":"elements", "id":"A12 :
   * AttributeConstraint" }, "target":{ "cardinality":"one", "property":"pattern", "id":"P3 : Pattern"
   * } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"elements", "id":"C13 :
   * CardinalityConstraint" }, "target":{ "cardinality":"one", "property":"pattern", "id":"P3 :
   * Pattern" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"elements", "id":"C14
   * : CloneOp" }, "target":{ "cardinality":"one", "property":"pattern", "id":"P3 : Pattern" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"elements", "id":"D15 :
   * DestroyObjectElem" }, "target":{ "cardinality":"one", "property":"pattern", "id":"P3 : Pattern" }
   * }, { "type":"assoc", "source":{ "cardinality":"many", "property":"elements", "id":"G16 :
   * GenericConstraint" }, "target":{ "cardinality":"one", "property":"pattern", "id":"P3 : Pattern" }
   * }, { "type":"assoc", "source":{ "cardinality":"many", "property":"elements", "id":"L17 :
   * LinkConstraint" }, "target":{ "cardinality":"one", "property":"pattern", "id":"P3 : Pattern" } },
   * { "type":"assoc", "source":{ "cardinality":"many", "property":"elements", "id":"M18 :
   * MatchIsomorphicConstraint" }, "target":{ "cardinality":"one", "property":"pattern", "id":"P3 :
   * Pattern" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"elements", "id":"M19
   * : MatchOtherThen" }, "target":{ "cardinality":"one", "property":"pattern", "id":"P3 : Pattern" }
   * }, { "type":"assoc", "source":{ "cardinality":"many", "property":"elements", "id":"N20 :
   * NegativeApplicationCondition" }, "target":{ "cardinality":"one", "property":"pattern", "id":"P3 :
   * Pattern" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"elements", "id":"O24
   * : OptionalSubPattern" }, "target":{ "cardinality":"one", "property":"pattern", "id":"N20 :
   * NegativeApplicationCondition" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"elements", "id":"P26 : PatternLink" }, "target":{ "cardinality":"one",
   * "property":"pattern", "id":"O24 : OptionalSubPattern" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"elements", "id":"U27 : UnifyGraphsOp" }, "target":{
   * "cardinality":"one", "property":"pattern", "id":"O24 : OptionalSubPattern" } }, { "type":"assoc",
   * "source":{ "cardinality":"one", "property":"pattern", "id":"P4 : Pattern" }, "target":{
   * "cardinality":"many", "property":"elements", "id":"P1 : PatternObject" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R23 : RuleApplication" },
   * "target":{ "cardinality":"one", "property":"src", "id":"R6 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"src", "id":"P1 : PatternObject" },
   * "target":{ "cardinality":"one", "property":"patternlink", "id":"P21 : PatternLink" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"states", "id":"R6 : ReachableState"
   * }, "target":{ "cardinality":"one", "property":"parent", "id":"R2 : ReachabilityGraph" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"tgt", "id":"P22 : PatternObject" },
   * "target":{ "cardinality":"one", "property":"patternlink", "id":"P21 : PatternLink" } }, {
   * "type":"assoc", "source":{ "cardinality":"one", "property":"tgt", "id":"R25 : ReachableState" },
   * "target":{ "cardinality":"many", "property":"resultOf", "id":"R23 : RuleApplication" } }, {
   * "type":"edge", "source":{ "cardinality":"many", "property":"todo", "id":"R7 : ReachableState" },
   * "target":{ "cardinality":"one", "property":"reachabilitygraph", "id":"R2 : ReachabilityGraph" } }
   * ] } ; json["options"]={"canvasid":"canvasPatternModelObjectsForCoverage2", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * 
   * @see <a href=
   *      '../../../../../../../../doc/PatternModelObjectsForCoverage.html'>PatternModelObjectsForCoverage.html</a>
   */
  // @Test
  public void testPatternModelObjectsForCoverage() {
    Storyboard story = new Storyboard().withDocDirName("doc/internal");

    story.add("Create some objects just for coverage. This does not serve as an usage example.");

    ReachabilityGraph reachabilityGraph = new ReachabilityGraph();

    ReachableState reachableState = reachabilityGraph.createStates();

    reachabilityGraph.createTodo();


    RuleApplication ruleapplication = reachableState.createRuleapplications();

    ruleapplication.createTgt();

    Pattern pattern = reachabilityGraph.createRules();

    PatternObject patternObject1 = (PatternObject) pattern.createElementsPatternObject();
    patternObject1.withModifier(Pattern.BOUND);
    patternObject1.withCurrentMatch("match");

    PatternLink patternLink = (PatternLink) pattern.createElementsPatternLink();

    PatternObject patternObject2 = (PatternObject) pattern.createElementsPatternObject();

    patternLink.setSrc(patternObject1);
    patternLink.setTgt(patternObject2);

    AttributeConstraint attributeConstraint = pattern.createAttributeConstraint().withAttrName("attr1");
    patternObject1.addToAttrConstraints(attributeConstraint);

    pattern = reachabilityGraph.createRules();
    pattern.createAttributeConstraint();
    pattern.createCardinalityConstraint();
    pattern.createCloneOp();
    pattern.createDestroyObjectElem();
    pattern.createElementsAttributeConstraint();
    pattern.createElementsCardinalityConstraint();
    pattern.createElementsCloneOp();
    pattern.createElementsDestroyObjectElem();
    pattern.createElementsGenericConstraint();
    pattern.createElementsLinkConstraint();
    pattern.createElementsMatchIsomorphicConstraint();
    pattern.createElementsMatchOtherThen();
    pattern.createElementsNegativeApplicationCondition();
    pattern.createElementsOptionalSubPattern();
    pattern.createElementsPatternLink();
    pattern.createElementsUnifyGraphsOp();


    story.addObjectDiagram(patternObject1, reachabilityGraph, pattern);

    story.dumpHTML();
  }

  /**
   * 
   * <p>
   * Storyboard <a href=
   * './src/test/java/org/sdmlib/test/examples/SDMLib/SDMLibMetaModelObjectDiagramsForCoverage.java'
   * type='text/x-java'>SDMLibCodeGenObjectsForCoverage</a>
   * </p>
   * <p>
   * Create some objects just for coverage. This does not serve as an usage example.
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"C1 : ClassModel",
   * "attributes":[ "name=org.sdmlib" ] }, { "type":"clazz", "id":"L3 : LocalVarTableEntry",
   * "attributes":[ "endPos=84", "name=me", "startPos=42", "type=int" ] }, { "type":"clazz", "id":"S2
   * : StatementEntry", "attributes":[ "assignTargetVarName=x", "endPos=77", "kind=polite",
   * "startPos=42" ] }, { "type":"clazz", "id":"S4 : SymTabEntry", "attributes":[ "annotations=null",
   * "annotationsStartPos=0", "bodyStartPos=42", "endPos=84", "kind=cool", "memberName=me",
   * "modifiers=grow", "preCommentEndPos=0", "preCommentStartPos=0", "startPos=43", "type=null" ] }, {
   * "type":"clazz", "id":"S5 : StatementEntry", "attributes":[ "assignTargetVarName=null",
   * "endPos=0", "kind=null", "startPos=0" ] } ], "edges":[ { "type":"assoc", "source":{
   * "cardinality":"many", "property":"bodyStats", "id":"S5 : StatementEntry" }, "target":{
   * "cardinality":"one", "property":"parent", "id":"S2 : StatementEntry" } } ] } ;
   * json["options"]={"canvasid":"canvasSDMLibCodeGenObjectsForCoverage2", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * 
   * @see <a href=
   *      '../../../../../../../../doc/SDMLibCodeGenObjectsForCoverage.html'>SDMLibCodeGenObjectsForCoverage.html</a>
   */
  // @Test
  public void testSDMLibCodeGenObjectsForCoverage() {
    Storyboard story = new Storyboard();

    story.add("Create some objects just for coverage. This does not serve as an usage example.");

    ClassModel model = new ClassModel("org.sdmlib");

    SymTabEntry symTabEntry = new SymTabEntry()
        .withBodyStartPos(42)
        .withEndPos(84)
        .withKind("cool")
        .withMemberName("me")
        .withModifiers("grow")
        .withStartPos(43);

    ArrayList<String> arrayList = new ArrayList<String>();
    arrayList.add("a");
    arrayList.add("bc");

    ArrayList<ArrayList<String>> arrayArrayList = new ArrayList<ArrayList<String>>();
    arrayArrayList.add(arrayList);

    LocalVarTableEntry localVarTableEntry = new LocalVarTableEntry()
        .withEndPos(84)
        .withInitSequence(arrayArrayList)
        .withName("me")
        .withStartPos(42)
        .withType("int");

    StatementEntry stat2 = new StatementEntry();

    StatementEntry statementEntry = new StatementEntry()
        .withAssignTargetVarName("x")
        .withBodyStats(stat2)
        .withEndPos(84)
        .withKind("polite")
        .withStartPos(42);

    Token token = new Token();
    token.endPos = 77;
    token.kind = 'v';
    token.startPos = 70;
    token.value = 42.23;

    statementEntry.withToken(token);



    // story.addObjectDiagram(model, statementEntry, localVarTableEntry, symTabEntry);

    // story.dumpHTML();
  }

}
