package org.sdmlib.examples.SDMLib;

import org.junit.Test;
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
      
      story.addObjectDiagram(reachabilityGraph);
      
      story.dumpHTML();
   }
}
