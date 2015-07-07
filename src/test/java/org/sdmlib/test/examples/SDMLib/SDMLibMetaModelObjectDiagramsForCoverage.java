package org.sdmlib.test.examples.SDMLib;

import java.util.ArrayList;

import org.junit.Test;
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

public class SDMLibMetaModelObjectDiagramsForCoverage
{
   @Test
   public void testPatternModelObjectsForCoverage()
   {
      Storyboard story = new Storyboard("src/test/java");
      
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

   @Test
   public void testSDMLibCodeGenObjectsForCoverage()
   {
      Storyboard story = new Storyboard("src/test/java");
      
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
      
      
      
      story.addObjectDiagram(model, statementEntry, localVarTableEntry, symTabEntry);
      
      story.dumpHTML();
   }

}
