package org.sdmlib.examples.SDMLib;

import org.junit.Test;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternElement;
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
}
